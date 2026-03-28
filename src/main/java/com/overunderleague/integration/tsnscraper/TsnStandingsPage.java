package com.overunderleague.integration.tsnscraper;

import lombok.extern.slf4j.Slf4j;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.time.Duration;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

/**
 * Page object for https://www.tsn.ca/nba/standings/
 *
 * <p>Standings are rendered inside a {@code <bmw-standings>} custom element whose
 * shadow DOM is {@code mode: "open"}.  Standard Selenium locators cannot cross the
 * shadow boundary, so all waiting and extraction use {@code executeScript}.
 *
 * <h2>Observed page structure (shadow DOM)</h2>
 * <pre>
 * thead tr  →  RK | Team | W | L | PCT | GB | HOME | AWAY | CONF | DIV | L10 | STRK
 * tbody tr  →  1  | (img+link) | 55 | 20 | …
 *
 * Team cell contains:
 *   &lt;img alt="Boston Celtics"&gt;          ← team name
 *   &lt;a href="/nba/team-page/boston-celtics/5"&gt;  ← slug is second-to-last segment
 * </pre>
 */
@Slf4j
public class TsnStandingsPage {

	private static final String URL = "https://www.tsn.ca/nba/standings/";
	private static final Duration DEFAULT_TIMEOUT = Duration.ofSeconds(90);
	private static final int MIN_DATA_ROWS = 15;

	private final WebDriver driver;
	private final WebDriverWait wait;

	public TsnStandingsPage(WebDriver driver) {
		this.driver = driver;
		this.wait = new WebDriverWait(driver, DEFAULT_TIMEOUT);
	}

	public void navigate() {
		log.debug("Navigating to {}", URL);
		driver.get(URL);
		log.debug("Page loaded, current URL: {}", driver.getCurrentUrl());
	}

	public void waitForPageLoad() {
		log.debug("Waiting for document.readyState == 'complete'");
		wait.until(d -> ((JavascriptExecutor) d).executeScript("return document.readyState").equals("complete"));
		log.debug("Document ready");
	}

	/**
	 * Blocks until the shadow DOM has at least {@value MIN_DATA_ROWS} tbody rows.
	 */
	public void waitForStandings() {
		log.debug("Waiting for bmw-standings shadow DOM ({} rows minimum)...", MIN_DATA_ROWS);
		wait.until(d -> {
			Object result = ((JavascriptExecutor) d).executeScript(
					"var host = document.querySelector('bmw-standings');" +
					"if (!host || !host.shadowRoot) return false;" +
					"return host.shadowRoot.querySelectorAll('tbody tr').length >= " + MIN_DATA_ROWS + ";");
			return Boolean.TRUE.equals(result);
		});
		log.debug("Shadow DOM ready");
	}

	/**
	 * Extracts all team standings from the shadow DOM.
	 *
	 * <p>Column indices are derived from the {@code <thead>} header row so the
	 * extraction stays correct if TSN ever reorders columns.
	 *
	 * <p>Key behaviours:
	 * <ul>
	 *   <li>Team name comes from {@code img[alt]} (e.g. "Detroit Pistons") — the link
	 *       text is a screen-reader label, not the team name.</li>
	 *   <li>TSN team links end with a numeric ID ({@code /nba/team-page/detroit-pistons/8});
	 *       the slug is the second-to-last path segment.</li>
	 *   <li>The first column is a rank number (RK), not the team — the team cell is
	 *       identified by the presence of an {@code <img>} element.</li>
	 * </ul>
	 */
	public List<TsnTeamStanding> extractStandings() {
		log.debug("Extracting standings from shadow DOM...");

		String jsExtract =
				"var host = document.querySelector('bmw-standings');" +
				"if (!host || !host.shadowRoot) return [];" +
				"var shadow = host.shadowRoot;" +

				// Detect column indices from the FIRST thead only.
				// There are two conference tables (East + West), each with a full thead.
				// querySelectorAll('thead th') would return 24 headers (12×2), doubling indices.
				// Observed first-table header: RK(0) | Team(1) | W(2) | L(3) | WIN%(4) |
				//   GB(5) | CONF(6) | DIV(7) | HOME(8) | ROAD(9) | L10(10) | STR(11)
				"var firstThead = shadow.querySelector('thead');" +
				"var headerCells = firstThead ? Array.from(firstThead.querySelectorAll('th')) : [];" +
				"var headers = headerCells.map(function(h){ return h.textContent.trim().toUpperCase(); });" +
				"var winsIdx = -1, lossesIdx = -1, lastTenIdx = -1;" +
				"headers.forEach(function(h, i){" +
				"  if ((h === 'W' || h === 'WINS') && winsIdx === -1) winsIdx = i;" +
				"  else if ((h === 'L' || h === 'LOSSES') && lossesIdx === -1) lossesIdx = i;" +
				"  else if ((h === 'L10' || h.indexOf('L10') !== -1 || h === 'LAST 10') && lastTenIdx === -1) lastTenIdx = i;" +
				"});" +
				// Positional fallbacks matching observed column order: RK(0), Team(1), W(2), L(3), L10(10)
				"if (winsIdx === -1) winsIdx = 2;" +
				"if (lossesIdx === -1) lossesIdx = 3;" +
				"if (lastTenIdx === -1) lastTenIdx = 10;" +

				"var rows = Array.from(shadow.querySelectorAll('tbody tr'));" +
				"var result = [];" +
				"rows.forEach(function(row){" +
				"  var cells = Array.from(row.querySelectorAll('td'));" +
				"  if (cells.length <= lossesIdx) return;" +

				// The team cell contains an <img> with alt="Team Name" — find it.
				"  var teamCell = null;" +
				"  for (var i = 0; i < cells.length; i++){" +
				"    if (cells[i].querySelector('img')) { teamCell = cells[i]; break; }" +
				"  }" +
				"  if (!teamCell) return;" +

				// Team name: img alt is the reliable source ("Detroit Pistons").
				// Link text says "View the competitor page" (screen-reader label).
				"  var img = teamCell.querySelector('img');" +
				"  var teamName = img ? img.getAttribute('alt') : '';" +
				"  if (!teamName || !teamName.trim()) return;" +
				"  teamName = teamName.trim();" +

				// Slug: URL is /nba/team-page/detroit-pistons/8
				// Last segment is a numeric internal ID — slug is the segment before it.
				"  var link = teamCell.querySelector('a');" +
				"  var teamId = '';" +
				"  if (link && link.href) {" +
				"    var parts = link.href.replace(/\\/$/, '').split('/');" +
				"    var last = parts[parts.length - 1];" +
				"    teamId = /^\\d+$/.test(last) ? parts[parts.length - 2] : last;" +
				"  }" +

				"  var wins   = cells[winsIdx].textContent.trim();" +
				"  var losses = cells[lossesIdx].textContent.trim();" +
				"  var lastTen = lastTenIdx !== -1 && cells[lastTenIdx]" +
				"                ? cells[lastTenIdx].textContent.trim() : '0-0';" +
				"  result.push({teamName: teamName, id: teamId," +
				"               wins: wins, losses: losses, lastTen: lastTen," +
				"               _headers: headers.join(','), _winsIdx: winsIdx, _lossesIdx: lossesIdx});" +
				"});" +
				"return result;";

		@SuppressWarnings("unchecked")
		List<Map<String, Object>> jsResults =
				(List<Map<String, Object>>) ((JavascriptExecutor) driver).executeScript(jsExtract);

		if (jsResults == null || jsResults.isEmpty()) {
			log.warn("JavaScript extraction returned no rows — shadow DOM may not have loaded correctly");
			return List.of();
		}

		Map<String, Object> first = jsResults.get(0);
		log.debug("Column mapping — headers=[{}] winsIdx={} lossesIdx={}",
				first.get("_headers"), first.get("_winsIdx"), first.get("_lossesIdx"));

		List<TsnTeamStanding> standings = new ArrayList<>();
		int skipped = 0;

		for (Map<String, Object> row : jsResults) {
			try {
				String teamName = (String) row.get("teamName");
				String teamId   = (String) row.get("id");
				String wins     = (String) row.get("wins");
				String losses   = (String) row.get("losses");
				String lastTen  = (String) row.get("lastTen");

				int lastTenWins = 0;
				if (lastTen != null && lastTen.contains("-")) {
					lastTenWins = Integer.parseInt(lastTen.split("-")[0].trim());
				}

				standings.add(new TsnTeamStanding(
						teamName,
						teamId != null ? teamId : "",
						Integer.parseInt(wins.replaceAll("[^0-9]", "")),
						Integer.parseInt(losses.replaceAll("[^0-9]", "")),
						lastTenWins
				));

				log.debug("Extracted: {} ({}) W={} L={} L10={}", teamName, teamId, wins, losses, lastTen);
			} catch (Exception e) {
				log.warn("Skipping row due to parse error: {} — {}", e.getMessage(), row);
				skipped++;
			}
		}

		log.info("Extraction complete — teams={} skipped={}", standings.size(), skipped);
		return standings;
	}
}
