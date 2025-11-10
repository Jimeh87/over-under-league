package com.overunderleague.core.standing;

import java.math.BigDecimal;
import java.math.RoundingMode;

public interface NbaTeamStanding {

	String getNbaTeamId();

	int getWins();

	int getLosses();


	int getLastTenGameWins();

	default BigDecimal getWinPercentage() {
		if (getWins() == 0 && getLosses() == 0) {
			return BigDecimal.ZERO;
		}
		return new BigDecimal(getWins())
				.divide(new BigDecimal(getWins()).add(new BigDecimal(getLosses())), RoundingMode.HALF_EVEN)
				.multiply(new BigDecimal("100"));
	}

}
