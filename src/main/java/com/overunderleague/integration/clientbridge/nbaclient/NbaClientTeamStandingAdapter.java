package com.overunderleague.integration.clientbridge.nbaclient;

import com.overunderleague.integration.nbaclient.api.NbaTeamStandingDto;
import com.overunderleague.core.standing.NbaTeamStanding;
import lombok.AllArgsConstructor;

import java.math.BigDecimal;

@AllArgsConstructor
public class NbaClientTeamStandingAdapter implements NbaTeamStanding {

	private final NbaTeamStandingDto nbaTeamStandingDto;

	@Override
	public String getNbaTeamId() {
		return nbaTeamStandingDto.getTeamId();
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
