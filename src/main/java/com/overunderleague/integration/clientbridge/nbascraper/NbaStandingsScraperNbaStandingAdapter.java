package com.overunderleague.integration.clientbridge.nbascraper;

import com.overunderleague.core.standing.NbaTeamStanding;
import com.overunderleague.integration.page.NbaStandingsPage;
import lombok.RequiredArgsConstructor;

@RequiredArgsConstructor
public class NbaStandingsScraperNbaStandingAdapter implements NbaTeamStanding {

	private final NbaStandingsPage.TeamStanding teamStanding;

	@Override
	public String getNbaTeamId() {
		return teamStanding.id();
	}

	@Override
	public int getWins() {
		return teamStanding.wins();
	}

	@Override
	public int getLosses() {
		return teamStanding.losses();
	}

	@Override
	public int getLastTenGameWins() {
		return teamStanding.lastTenWins();
	}
}
