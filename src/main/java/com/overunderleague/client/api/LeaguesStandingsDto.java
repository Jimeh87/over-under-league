package com.overunderleague.client.api;

import lombok.Data;
import lombok.experimental.Accessors;

@Data
@Accessors(chain = true)
public class LeaguesStandingsDto {
	private LeagueStandingsDto standard;
	private LeagueStandingsDto africa;
	private LeagueStandingsDto sacramento;
	private LeagueStandingsDto vegas;
	private LeagueStandingsDto utah;
}
