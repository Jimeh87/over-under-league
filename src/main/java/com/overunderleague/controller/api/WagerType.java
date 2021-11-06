package com.overunderleague.controller.api;

import lombok.AllArgsConstructor;

@AllArgsConstructor
public enum WagerType {
	OVER(1),
	UNDER(-1);

	private int paceToScoreConversion;

	public int toPoints(int pace) {
		return pace * paceToScoreConversion;
	}
}
