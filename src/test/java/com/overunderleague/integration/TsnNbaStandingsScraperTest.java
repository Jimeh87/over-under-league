package com.overunderleague.integration;

import com.overunderleague.core.overunder.Team;
import com.overunderleague.integration.tsnscraper.TsnNbaStandingsScraper;
import com.overunderleague.integration.tsnscraper.TsnTeamStanding;
import org.junit.jupiter.api.Disabled;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.List;
import java.util.stream.Collectors;

import static org.assertj.core.api.Assertions.assertThat;

/**
 * Integration tests that hit the live TSN standings page.
 * All tests are @Disabled to avoid network calls in CI — enable to verify the scraper locally.
 */
@SpringBootTest
class TsnNbaStandingsScraperTest {

	@Autowired
	private TsnNbaStandingsScraper scraper;

	@Disabled("Hits live TSN page; enable locally to verify scraper")
	@Test
	void scrapeReturnsAllThirtyTeams() {
		var standings = scraper.scrape();
		assertThat(standings).hasSize(30);
	}

	@Disabled("Hits live TSN page; enable locally to verify scraper")
	@Test
	void everyTeamHasValidRecord() {
		var standings = scraper.scrape();

		assertThat(standings).isNotEmpty();
		standings.forEach(s -> {
			assertThat(s.teamName()).as("teamName for %s", s).isNotBlank();
			assertThat(s.wins()).as("wins for %s", s.teamName()).isGreaterThanOrEqualTo(0);
			assertThat(s.losses()).as("losses for %s", s.teamName()).isGreaterThanOrEqualTo(0);
			assertThat(s.wins() + s.losses()).as("games played for %s", s.teamName())
					.isGreaterThan(0).isLessThanOrEqualTo(82);
			assertThat(s.lastTenWins()).as("lastTenWins for %s", s.teamName()).isBetween(0, 10);
		});
	}

	@Disabled("Hits live TSN page; enable locally to verify scraper")
	@Test
	void teamNamesAreUnique() {
		var standings = scraper.scrape();
		var names = standings.stream().map(TsnTeamStanding::teamName).collect(Collectors.toList());
		assertThat(names.stream().distinct().count())
				.as("Every team name must be unique — duplicates indicate a parsing bug")
				.isEqualTo(names.size());
	}

	@Disabled("Hits live TSN page; enable locally to verify scraper")
	@Test
	void allSlugsResolveToKnownNbaTeam() {
		var standings = scraper.scrape();

		standings.forEach(s ->
				assertThat(Team.findByTsnSlug(s.id()))
						.as("Slug '%s' (team '%s') must resolve to a Team enum value", s.id(), s.teamName())
						.isPresent());
	}

	@Disabled("Hits live TSN page; enable locally to verify scraper")
	@Test
	void allThirtyTeamEnumValuesArePresent() {
		var standings = scraper.scrape();

		var resolvedTeams = standings.stream()
				.map(s -> Team.findByTsnSlug(s.id()))
				.filter(java.util.Optional::isPresent)
				.map(java.util.Optional::get)
				.collect(Collectors.toSet());

		assertThat(resolvedTeams).containsExactlyInAnyOrder(Team.values());
	}

	@Disabled("Hits live TSN page; enable locally to verify scraper")
	@Test
	void gamesPlayedIsConsistentAcrossLeague() {
		var standings = scraper.scrape();

		int min = standings.stream().mapToInt(s -> s.wins() + s.losses()).min().orElse(0);
		int max = standings.stream().mapToInt(s -> s.wins() + s.losses()).max().orElse(0);

		assertThat(max - min)
				.as("All teams should have played roughly the same number of games (within 2)")
				.isLessThanOrEqualTo(2);
	}

	@Disabled("Hits live TSN page; enable locally to verify scraper")
	@Test
	void printStandingsForManualVerification() {
		var standings = scraper.scrape();

		System.out.println("\n=== TSN NBA Standings ===");
		standings.forEach(s -> System.out.printf(
				"%-30s  slug=%-30s  W=%-3d L=%-3d L10=%d  team=%s%n",
				s.teamName(), s.id(), s.wins(), s.losses(), s.lastTenWins(),
				Team.findByTsnSlug(s.id()).map(Enum::name).orElse("UNRESOLVED")));
		System.out.println("========================\n");

		assertThat(standings).isNotEmpty();
	}
}
