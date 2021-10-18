package com.overunderleague.core.overunderpace;

import lombok.Builder;

@Builder
public class PaceCalculator {

	static final int TOTAL_GAMES = 82;

	private final double wins;
	private final double loses;
	private final double overUnder;

	public int calculate() {
		return calculate(TOTAL_GAMES);
	}

	public int calculate(int totalGames) {
		double overUnderWinPercentage = overUnder / totalGames;
		double currentTotalGames = wins + loses;
		double currentWinPercentage = currentTotalGames != 0 ? wins / currentTotalGames : overUnderWinPercentage;

		return (int) Math.round((currentWinPercentage - overUnderWinPercentage) * currentTotalGames);
	}
}
