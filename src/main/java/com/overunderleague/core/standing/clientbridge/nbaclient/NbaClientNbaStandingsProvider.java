package com.overunderleague.core.standing.clientbridge.nbaclient;

import com.overunderleague.integration.nbaclient.NbaClient;
import com.overunderleague.core.standing.NbaStandingsProvider;
import com.overunderleague.core.standing.NbaTeamStanding;
import lombok.AllArgsConstructor;

import java.util.List;
import java.util.stream.Collectors;

@AllArgsConstructor
public class NbaClientNbaStandingsProvider implements NbaStandingsProvider {

	private final NbaClient nbaClient;

	@Override
	public List<NbaTeamStanding> getStandings() {
		return nbaClient.findCurrentStandings()
				.getLeague()
				.getStandard()
				.getTeams()
				.stream()
				.map(NbaClientTeamStandingBridge::new)
				.collect(Collectors.toList());
	}

}
