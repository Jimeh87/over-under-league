package com.overunderleague.integration.clientbridge.nbaclient2;

import com.overunderleague.integration.nbaclient2.api.Team;
import com.overunderleague.core.standing.NbaTeamStanding;
import lombok.AllArgsConstructor;

import java.math.BigDecimal;

@AllArgsConstructor
public class NbaClient2TeamStandingAdapter implements NbaTeamStanding {

	private final Team team;

	@Override
	public String getNbaTeamId() {
		return team.getProfile().getId();
	}

	@Override
	public int getWins() {
		return team.getStandings().getWins();
	}

	@Override
	public int getLosses() {
		return team.getStandings().getLosses();
	}

	@Override
	public BigDecimal getWinPercentage() {
		return team.getStandings().getWinPct();
	}

	@Override
	public int getLastTenGameWins() {
		return Integer.parseInt(team.getStandings().getLast10().split("-")[0]);
	}

}
