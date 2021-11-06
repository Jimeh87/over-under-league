package com.overunderleague.controller.api;

import lombok.Data;
import lombok.experimental.Accessors;

import java.util.ArrayList;
import java.util.List;

@Data
@Accessors(chain = true)
public class UserStandingDto {
	private String userNickname;
	private Integer points;
	private TeamTemperatureDto temperature;
	private List<UserTeamScoreDto> teamScores = new ArrayList<>();
}
