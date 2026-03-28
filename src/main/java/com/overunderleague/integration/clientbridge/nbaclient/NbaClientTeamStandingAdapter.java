package com.overunderleague.integration.clientbridge.nbaclient;

import com.overunderleague.core.overunder.Team;
import com.overunderleague.integration.nbaclient.api.NbaTeamStandingDto;
import com.overunderleague.core.standing.NbaTeamStanding;
import lombok.AllArgsConstructor;

import java.math.BigDecimal;

@AllArgsConstructor
public class NbaClientTeamStandingAdapter implements NbaTeamStanding {

	private final NbaTeamStandingDto nbaTeamStandingDto;

	@Override
	public Team getTeam() {
		return Team.getTeamByNbaTeamId(nbaTeamStandingDto.getTeamId());
	}

	@Override
	public int getWins() {
		return nbaTeamStandingDto.getWin();
	}

	@Override
	public int getLosses() {
		return nbaTeamStandingDto.getLoss();
	}

	@Override
	public BigDecimal getWinPercentage() {
		return nbaTeamStandingDto.getWinPct();
	}

	@Override
	public int getLastTenGameWins() {
		return nbaTeamStandingDto.getLastTenWin();
	}

}
