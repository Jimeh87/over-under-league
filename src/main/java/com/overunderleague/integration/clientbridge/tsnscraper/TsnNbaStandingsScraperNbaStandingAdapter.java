package com.overunderleague.integration.clientbridge.tsnscraper;

import com.overunderleague.core.overunder.Team;
import com.overunderleague.core.standing.NbaTeamStanding;
import com.overunderleague.integration.tsnscraper.TsnTeamStanding;
import lombok.RequiredArgsConstructor;

@RequiredArgsConstructor
public class TsnNbaStandingsScraperNbaStandingAdapter implements NbaTeamStanding {

	private final TsnTeamStanding teamStanding;

	@Override
	public Team getTeam() {
		return Team.findByTsnSlug(teamStanding.id())
				.orElseThrow(() -> new IllegalArgumentException(
						"Cannot resolve TSN slug to NBA Team enum: name='" + teamStanding.teamName()
						+ "', slug='" + teamStanding.id() + "'"));
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
