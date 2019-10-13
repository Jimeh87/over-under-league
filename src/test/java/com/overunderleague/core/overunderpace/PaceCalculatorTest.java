package com.overunderleague.core.overunderpace;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.CsvSource;

import static org.junit.jupiter.api.Assertions.assertEquals;

class PaceCalculatorTest {

	@Test
	void testCalculate_OverUnderAndWinLoseMatch_ExpectZeroReturned() {
		int pace = PaceCalculator.builder()
				.wins(20)
				.loses(PaceCalculator.TOTAL_GAMES - 20)
				.overUnder(20)
				.build()
				.calculate();

		assertEquals(0, pace);
	}

	@Test
	void testCalculate_OneOver_ExpectOneReturned() {
		int pace = PaceCalculator.builder()
				.wins(24)
				.loses(PaceCalculator.TOTAL_GAMES - 24)
				.overUnder(23.5)
				.build()
				.calculate();

		assertEquals(1, pace);
	}

	@Test
	void testCalculate_OneUnder_ExpectMinusOneReturned() {
		int pace = PaceCalculator.builder()
				.wins(22)
				.loses(PaceCalculator.TOTAL_GAMES - 23)
				.overUnder(23.5)
				.build()
				.calculate();

		assertEquals(-1, pace);
	}

	@Test
	void testCalculate_OneAndZeroTeam_ExpectOneReturned() {
		int pace = PaceCalculator.builder()
				.wins(1)
				.loses(0)
				.overUnder(10)
				.build()
				.calculate();

		assertEquals(1, pace);
	}

	@Test
	void testCalculate_EightAndZeroTeamWithHighOverUnder_ExpectThreeReturned() {
		int pace = PaceCalculator.builder()
				.wins(8)
				.loses(0)
				.overUnder(51)
				.build()
				.calculate();

		assertEquals(3, pace);
	}

	@Test
	void testCalculate_NoGamesPlayed_ExpectZeroReturned() {
		int pace = PaceCalculator.builder()
				.wins(0)
				.loses(0)
				.overUnder(10)
				.build()
				.calculate();

		assertEquals(0, pace);
	}

	@Test
	void testCalculate_ZeroOverUnder_ExpectWinTotalToMatchPace() {
		int pace = PaceCalculator.builder()
				.wins(10)
				.loses(0)
				.overUnder(0)
				.build()
				.calculate();

		assertEquals(10, pace);
	}

	@ParameterizedTest(name = "wins: {0} loses: {1} overunder: {2} expected pace: {3}")
	@CsvSource({
			"20,20,57.5,-8",
			"60,11,51.5,15",
			"4,2,34.5,1",
			"0,15,30,-5"
	})
	void testSamples(int wins, int loses, double overunder, int expectedPace) {
		int pace = PaceCalculator.builder()
				.wins(wins)
				.loses(loses)
				.overUnder(overunder)
				.build()
				.calculate();

		assertEquals(expectedPace, pace, "expected pace");
	}

}
