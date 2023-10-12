package com.overunderleague.integration.clientbridge.nbaclient;

import com.overunderleague.integration.nbaclient.api.NbaTeamStandingDto;
import com.overunderleague.core.standing.NbaTeamStanding;
import lombok.AllArgsConstructor;

import java.math.BigDecimal;

@AllArgsConstructor
public class NbaClientTeamStandingBridge implements NbaTeamStanding {

	private final NbaTeamStandingDto nbaTeamStandingDto;

	@Override
	public String getNbaTeamId() {
		return nbaTeamStandingDto.getTeamId();
	}

	@Override
	public Integer getWin() {
		return nbaTeamStandingDto.getWin();
	}

	@Override
	public Integer getLoss() {
		return nbaTeamStandingDto.getLoss();
	}

	@Override
	public BigDecimal getWinPct() {
		return nbaTeamStandingDto.getWinPct();
	}

	@Override
	public Integer getLastTenWin() {
		return nbaTeamStandingDto.getLastTenWin();
	}

}
