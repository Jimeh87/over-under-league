package com.overunderleague.integration.nbaclient.api;

import lombok.Data;
import lombok.experimental.Accessors;

@Data
@Accessors(chain = true)
public class NbaLeaguesStandingsDto {
	private NbaLeagueStandingsDto standard;
	private NbaLeagueStandingsDto africa;
	private NbaLeagueStandingsDto sacramento;
	private NbaLeagueStandingsDto vegas;
	private NbaLeagueStandingsDto utah;
}
