package com.overunderleague.controller.api;

import lombok.Data;
import lombok.experimental.Accessors;

import java.util.ArrayList;
import java.util.List;

@Data
@Accessors(chain = true)
public class UserScoreDto {
	private String userNickname;
	private Integer score;
	private List<UserTeamScoreDto> teamScores = new ArrayList<>();
}
