package com.overunderleague.integration.nbaclient.api;

import lombok.Data;
import lombok.experimental.Accessors;

import java.util.ArrayList;
import java.util.List;

@Data
@Accessors(chain = true)
public class NbaLeagueStandingsDto {
	private Integer seasonYear;
	private Integer seasonStageId;
	private List<NbaTeamStandingDto> teams = new ArrayList<>();
}
