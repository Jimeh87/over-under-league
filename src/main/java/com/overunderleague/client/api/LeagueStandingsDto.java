package com.overunderleague.client.api;

import lombok.Data;
import lombok.experimental.Accessors;

import java.util.ArrayList;
import java.util.List;

@Data
@Accessors(chain = true)
public class LeagueStandingsDto {
	private Integer seasonYear;
	private Integer seasonStageId;
	private List<TeamStandingDto> teams = new ArrayList<>();
}
