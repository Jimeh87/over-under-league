package com.overunderleague.core.standing.clientbridge.nbaclient2;

import com.overunderleague.integration.nbaclient2.api.Team;
import com.overunderleague.core.standing.NbaTeamStanding;
import lombok.AllArgsConstructor;

import java.math.BigDecimal;

@AllArgsConstructor
public class NbaClient2TeamStandingBridge implements NbaTeamStanding {

	private final Team team;

	@Override
	public String getNbaTeamId() {
		return team.getProfile().getId();
	}

	@Override
	public Integer getWin() {
		return team.getStandings().getWins();
	}

	@Override
	public Integer getLoss() {
		return team.getStandings().getLosses();
	}

	@Override
	public BigDecimal getWinPct() {
		return team.getStandings().getWinPct();
	}

	@Override
	public Integer getLastTenWin() {
		return Integer.valueOf(team.getStandings().getLast10().split("-")[0]);
	}

}
