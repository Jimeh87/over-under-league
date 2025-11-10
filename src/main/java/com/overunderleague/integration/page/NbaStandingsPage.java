package com.overunderleague.integration.page;

import lombok.Data;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.time.Duration;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

@Slf4j
public class NbaStandingsPage {

	private static final String URL = "https://www.nba.com/standings";
	private static final Duration DEFAULT_TIMEOUT = Duration.ofSeconds(60);
	private static final int MIN_COLUMNS_REQUIRED = 10;
	private static final int MIN_ROWS_WITH_CONTENT = 5;

	private final WebDriver driver;
	private final WebDriverWait wait;

	public NbaStandingsPage(WebDriver driver) {
		this.driver = driver;
		this.wait = new WebDriverWait(driver, DEFAULT_TIMEOUT);
	}

	public void navigate() {
		log.debug("Navigating to {}", URL);
		driver.get(URL);
		log.debug("Page loaded, URL: {}", driver.getCurrentUrl());
	}

	public void waitForPageLoad() {
		log.debug("Waiting for document.readyState to be 'complete'");
		wait.until(webDriver -> ((JavascriptExecutor) webDriver)
				.executeScript("return document.readyState").equals("complete"));
		log.debug("Document ready state: {}",
				((JavascriptExecutor) driver).executeScript("return document.readyState"));
	}

	public void dismissCookieConsent() {
		log.debug("Checking for cookie consent banners...");
		try {
			Thread.sleep(2000);
			List<WebElement> cookieButtons = driver.findElements(By.cssSelector(
					"button[id*='cookie'], button[class*='cookie'], button[id*='accept'], " +
							"button[class*='accept'], [aria-label*='Accept'], [aria-label*='Cookie']"));
			log.debug("Found {} potential cookie buttons", cookieButtons.size());

			for (WebElement button : cookieButtons) {
				if (button.isDisplayed()) {
					log.debug("Clicking cookie consent button");
					button.click();
					Thread.sleep(1000);
					break;
				}
			}
		} catch (Exception e) {
			log.debug("No cookie banner found: {}", e.getMessage());
		}
	}

	public void waitForContent() {
		log.debug("Waiting for page content to appear...");
		try {
			wait.until(webDriver -> {
				String bodyText = webDriver.findElement(By.tagName("body")).getText();
				return bodyText.length() > 100;
			});
			log.debug("Page has content");
		} catch (Exception e) {
			log.debug("Page content check failed: {}", e.getMessage());
		}

		// Additional wait for React to render
		log.debug("Waiting additional time for React to render...");
		try {
			Thread.sleep(5000);
		} catch (InterruptedException e) {
			Thread.currentThread().interrupt();
		}
	}

	/**
	 * Waits for the standings table to be fully loaded and ready.
	 * This method handles all the necessary waiting steps internally.
	 */
	public void waitForStandings() {
		log.debug("Waiting for standings to be ready...");

		waitForTable();
		waitForTableContent();
		waitForNetworkRequests();
		scrollToLoadContent();
		waitForVisibleRows();

		log.debug("Standings are ready!");
	}

	private void waitForTable() {
		log.debug("Waiting for table element to appear in DOM...");
		try {
			wait.until(ExpectedConditions.presenceOfElementLocated(By.tagName("table")));
			log.debug("Table element found!");
		} catch (org.openqa.selenium.TimeoutException e) {
			log.debug("Table not found after timeout. Checking for alternative structures...");
			checkForAlternativeStructures();
		}
	}

	private void checkForAlternativeStructures() {
		List<WebElement> standingsDivs = driver.findElements(By.cssSelector(
				"[class*='standings'], [class*='Standings'], [id*='standings'], [id*='Standings']"));
		log.debug("Found {} elements with 'standings' in class/id", standingsDivs.size());

		List<WebElement> iframes = driver.findElements(By.tagName("iframe"));
		if (!iframes.isEmpty()) {
			log.debug("Attempting to switch to first iframe...");
			try {
				driver.switchTo().frame(iframes.get(0));
				int iframeTables = driver.findElements(By.tagName("table")).size();
				int iframeTrs = driver.findElements(By.tagName("tr")).size();
				log.debug("Iframe - tables: {}, tr: {}", iframeTables, iframeTrs);
				if (iframeTables == 0 && iframeTrs == 0) {
					driver.switchTo().defaultContent();
				}
			} catch (Exception e) {
				log.debug("Could not switch to iframe: {}", e.getMessage());
				driver.switchTo().defaultContent();
			}
		}
	}

	private void waitForTableContent() {
		log.debug("Waiting for table to have content (rows with data)...");
		try {
			wait.until(webDriver -> {
				List<WebElement> tables = webDriver.findElements(By.tagName("table"));
				if (tables.isEmpty()) {
					log.debug("No tables found yet");
					return false;
				}

				for (int i = 0; i < tables.size(); i++) {
					WebElement table = tables.get(i);
					List<WebElement> rows = table.findElements(By.cssSelector("tbody tr"));
					log.debug("Table {} has {} tbody rows", i + 1, rows.size());

					if (!rows.isEmpty()) {
						for (int j = 0; j < Math.min(rows.size(), 3); j++) {
							WebElement row = rows.get(j);
							String rowText = row.getText().trim();
							int tdCount = row.findElements(By.tagName("td")).size();
							log.debug("Row {} text length: {}, td count: {}", j + 1, rowText.length(), tdCount);
							if (!rowText.isEmpty() && tdCount > 0) {
								log.debug("Found row with content!");
								return true;
							}
						}
					}
				}
				return false;
			});
			log.debug("Table content check passed");
		} catch (Exception e) {
			log.debug("Table content check failed: {}", e.getMessage());
		}
	}

	private void waitForNetworkRequests() {
		log.debug("Checking for network requests completion...");
		try {
			wait.until(webDriver -> {
				Object result = ((JavascriptExecutor) webDriver).executeScript(
						"return (typeof window.fetch === 'undefined' || " +
								"document.querySelectorAll('table tbody tr').length > 0 || " +
								"document.readyState === 'complete')");
				return Boolean.TRUE.equals(result);
			});
			log.debug("Network requests check passed");
		} catch (Exception e) {
			log.debug("Network requests check failed: {}", e.getMessage());
		}
	}

	private void scrollToLoadContent() {
		log.debug("Scrolling to bottom of page...");
		((JavascriptExecutor) driver).executeScript("window.scrollTo(0, document.body.scrollHeight);");
		try {
			Thread.sleep(2000);
		} catch (InterruptedException e) {
			Thread.currentThread().interrupt();
		}

		log.debug("Scrolling back to top...");
		((JavascriptExecutor) driver).executeScript("window.scrollTo(0, 0);");
		try {
			Thread.sleep(1000);
		} catch (InterruptedException e) {
			Thread.currentThread().interrupt();
		}
	}

	private void waitForVisibleRows() {
		log.debug("Final check: Waiting for at least {} visible rows with content...", MIN_ROWS_WITH_CONTENT);
		wait.until(webDriver -> {
			List<WebElement> rows = webDriver.findElements(By.cssSelector("table tbody tr"));
			log.debug("Found {} total rows", rows.size());

			if (rows.isEmpty()) {
				log.debug("No rows found yet");
				return false;
			}

			int rowsWithContent = 0;
			for (int i = 0; i < Math.min(rows.size(), 10); i++) {
				WebElement row = rows.get(i);
				boolean isDisplayed = row.isDisplayed();
				String text = row.getText().trim();
				int tdCount = row.findElements(By.tagName("td")).size();

				if (isDisplayed && !text.isEmpty() && tdCount >= 5) {
					rowsWithContent++;
					if (rowsWithContent <= 3) {
						log.debug("Row {} has content: displayed={}, text length={}, td count={}",
								i + 1, isDisplayed, text.length(), tdCount);
					}
				}
			}
			log.debug("Rows with sufficient content: {}", rowsWithContent);
			return rowsWithContent >= MIN_ROWS_WITH_CONTENT;
		});
		log.debug("Final content check passed!");
	}

	public List<TeamStanding> extractStandings() {
		log.debug("Extracting all row data using JavaScript...");

		String jsExtract =
				"var rows = document.querySelectorAll('table tbody tr'); " +
						"var result = []; " +
						"for (var i = 0; i < rows.length; i++) { " +
						"  var row = rows[i]; " +
						"  var ths = row.querySelectorAll('th'); " +
						"  if (ths.length > 0) continue; " +
						"  var link = row.querySelector('a[class*=\"Anchor\"]') || row.querySelector('a'); " +
						"  if (!link) continue; " +
						"  var cols = row.querySelectorAll('td'); " +
						"  if (cols.length < " + MIN_COLUMNS_REQUIRED + ") continue; " +
						"  var teamName = link.textContent.trim(); " +
						"  if (teamName.startsWith('team=')) { " +
						"    teamName = teamName.substring(5).trim(); " +
						"  } " +
						"  teamName = teamName.replace(/^\\d+\\s*/, '').trim(); " +
						"  var teamUrl = link.href; " +
						"  var teamId = ''; " +
						"  if (teamUrl) { " +
						"    var urlParts = teamUrl.split('/team/'); " +
						"    if (urlParts.length > 1) { " +
						"      var pathPart = urlParts[1].split('/')[0].split('?')[0]; " +
						"      teamId = pathPart; " +
						"    } else { " +
						"      urlParts = teamUrl.split('/teams/'); " +
						"      if (urlParts.length > 1) { " +
						"        var pathPart = urlParts[1].split('/')[0].split('?')[0]; " +
						"        teamId = pathPart; " +
						"      } " +
						"    } " +
						"  } " +
						"  var wins = cols[1] ? cols[1].textContent.trim() : ''; " +
						"  var losses = cols[2] ? cols[2].textContent.trim() : ''; " +
						"  var lastTen = cols[cols.length - 2] ? cols[cols.length - 2].textContent.trim() : ''; " +
						"  result.push({team: teamName, id: teamId, wins: wins, losses: losses, lastTen: lastTen}); " +
						"} " +
						"return result;";

		@SuppressWarnings("unchecked")
		List<Map<String, Object>> jsResults = (List<Map<String, Object>>)
				((JavascriptExecutor) driver).executeScript(jsExtract);

		log.debug("JavaScript extraction found {} teams", jsResults.size());

		List<TeamStanding> standings = new ArrayList<>();
		int processedCount = 0;
		int skippedCount = 0;

		for (Map<String, Object> jsResult : jsResults) {
			try {
				String teamName = (String) jsResult.get("team");
				String teamId = (String) jsResult.get("id");
				String wins = (String) jsResult.get("wins");
				String losses = (String) jsResult.get("losses");
				String lastTen = (String) jsResult.get("lastTen");

				if (teamName == null || teamName.isEmpty()) {
					skippedCount++;
					continue;
				}

				log.debug("Row {}: team='{}', id={}, wins={}, losses={}",
						processedCount + skippedCount + 1, teamName, teamId, wins, losses);

				TeamStanding standing = new TeamStanding(
						teamName,
						teamId,
						Integer.parseInt(wins),
						Integer.parseInt(losses),
						Integer.parseInt(lastTen.split("-")[0])
				);

				standings.add(standing);
				processedCount++;
				log.debug("Added team: {} (W:{} L:{})", teamName, wins, losses);
			} catch (Exception e) {
				log.debug("Error processing JavaScript result: {}", e.getMessage());
				skippedCount++;
			}
		}

		log.debug("Extraction complete! Processed: {}, Skipped: {}, Total teams: {}",
				processedCount, skippedCount, standings.size());

		return standings;
	}

	public record TeamStanding(String teamName, String id, int wins, int losses, int lastTenWins) {
	}
}

