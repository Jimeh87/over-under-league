package com.overunderleague.core.standing;

import java.util.List;

@FunctionalInterface
public interface NbaStandingsProvider {
	List<NbaTeamStanding> getStandings();
}
