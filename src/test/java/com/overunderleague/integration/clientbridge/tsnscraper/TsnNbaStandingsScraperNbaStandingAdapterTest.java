package com.overunderleague.integration.clientbridge.tsnscraper;

import com.overunderleague.core.overunder.Team;
import com.overunderleague.integration.tsnscraper.TsnTeamStanding;
import org.junit.jupiter.api.Test;

import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.assertThatThrownBy;

class TsnNbaStandingsScraperNbaStandingAdapterTest {

	@Test
	void delegatesWinsLossesAndLastTen() {
		var adapter = adapter("Boston Celtics", "boston-celtics", 55, 20, 7);

		assertThat(adapter.getWins()).isEqualTo(55);
		assertThat(adapter.getLosses()).isEqualTo(20);
		assertThat(adapter.getLastTenGameWins()).isEqualTo(7);
	}

	@Test
	void resolvesTeamBySlug() {
		assertThat(adapter("", "boston-celtics", 0, 0, 0).getTeam()).isEqualTo(Team.CELTICS);
		assertThat(adapter("", "milwaukee-bucks", 0, 0, 0).getTeam()).isEqualTo(Team.BUCKS);
		assertThat(adapter("", "portland-trail-blazers", 0, 0, 0).getTeam()).isEqualTo(Team.TRAIL_BLAZERS);
		assertThat(adapter("", "philadelphia-76ers", 0, 0, 0).getTeam()).isEqualTo(Team.SEVENTY_SIXERS);
		assertThat(adapter("", "oklahoma-city-thunder", 0, 0, 0).getTeam()).isEqualTo(Team.THUNDER);
	}

	@Test
	void throwsForUnresolvableSlug() {
		assertThatThrownBy(() -> adapter("Unknown FC", "unknown-fc", 0, 0, 0).getTeam())
				.isInstanceOf(IllegalArgumentException.class)
				.hasMessageContaining("unknown-fc");
	}

	private static TsnNbaStandingsScraperNbaStandingAdapter adapter(
			String name, String slug, int wins, int losses, int lastTen) {
		return new TsnNbaStandingsScraperNbaStandingAdapter(
				new TsnTeamStanding(name, slug, wins, losses, lastTen));
	}
}
