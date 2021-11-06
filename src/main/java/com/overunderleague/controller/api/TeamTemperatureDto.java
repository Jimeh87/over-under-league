package com.overunderleague.controller.api;

import lombok.Data;
import lombok.experimental.Accessors;

@Data
@Accessors(chain = true)
public class TeamTemperatureDto {
	private Integer lastNumberOfGames;
	private Integer pointsInLastNumberOfGames;
	private Double pointPercentage;
	private TemperatureType temperature;
}
