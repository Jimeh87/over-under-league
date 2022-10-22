package com.overunderleague.core.standing;

import java.math.BigDecimal;

public interface NbaTeamStanding {
	String getNbaTeamId();
	Integer getWin();
	Integer getLoss();
	BigDecimal getWinPct();
	Integer getLastTenWin();
}
