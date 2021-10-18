package com.overunderleague.nbaclient.api;

import lombok.Data;
import lombok.experimental.Accessors;

@Data
@Accessors(chain = true)
public class NbaStandingsDto {
	private NbaStandingsInternalDto _internal;
	private NbaLeaguesStandingsDto league;
}
