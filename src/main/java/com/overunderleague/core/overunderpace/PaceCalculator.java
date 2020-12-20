package com.overunderleague.core.overunderpace;

import lombok.Builder;

@Builder
public class PaceCalculator {

	static final int TOTAL_GAMES = 72;

	private double wins;
	private double loses;
	private double overUnder;

	public int calculate() {
		double overUnderWinPercentage = overUnder / TOTAL_GAMES;
		double currentTotalGames = wins + loses;
		double currentWinPercentage = currentTotalGames != 0 ? wins / currentTotalGames : overUnderWinPercentage;

		return (int) Math.round((currentWinPercentage - overUnderWinPercentage) * currentTotalGames);
	}
}
