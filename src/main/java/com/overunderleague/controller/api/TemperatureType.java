package com.overunderleague.controller.api;

import lombok.AllArgsConstructor;
import lombok.Getter;

import java.util.function.Function;
import java.util.function.Predicate;

@AllArgsConstructor
public enum TemperatureType {
	HOT(.1, .2),
	COLD(-.1, -.2),
	NEUTRAL(null, null);

	@Getter
	private final Double userThreshold;
	@Getter
	private final Double teamThreshold;


	public static TemperatureType fromUserPercentage(Double percentage) {
		return fromPercentage(percentage, TemperatureType::getUserThreshold);
	}
	public static TemperatureType fromTeamPercentage(Double percentage) {
		return fromPercentage(percentage, TemperatureType::getTeamThreshold);
	}

	private static TemperatureType fromPercentage(Double percentage, Function<TemperatureType, Double> thresholdFn) {
		if (percentage == null) {
			return NEUTRAL;
		}

		if (percentage >= thresholdFn.apply(HOT)) {
			return HOT;
		}

		if (percentage <= thresholdFn.apply(COLD)) {
			return COLD;
		}

		return NEUTRAL;
	}

}
