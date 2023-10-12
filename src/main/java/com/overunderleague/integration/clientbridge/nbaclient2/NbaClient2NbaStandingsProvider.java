package com.overunderleague.integration.clientbridge.nbaclient2;

import com.overunderleague.integration.nbaclient2.NbaClient2;
import com.overunderleague.integration.nbaclient2.api.StandingGroup;
import com.overunderleague.core.standing.NbaStandingsProvider;
import com.overunderleague.core.standing.NbaTeamStanding;
import lombok.AllArgsConstructor;

import java.util.List;
import java.util.stream.Collectors;

@AllArgsConstructor
public class NbaClient2NbaStandingsProvider implements NbaStandingsProvider {

	private final NbaClient2 nbaClient2;

	@Override
	public List<NbaTeamStanding> getStandings() {
		return nbaClient2.findCurrentStandings()
				.getPayload()
				.getStandingGroups()
				.stream()
				.map(StandingGroup::getTeams)
				.flatMap(List::stream)
				.map(NbaClient2TeamStandingBridge::new)
				.collect(Collectors.toList());
	}

}
