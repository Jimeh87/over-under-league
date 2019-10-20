package com.overunderleague.client.api;

import lombok.Data;
import lombok.experimental.Accessors;

@Data
@Accessors(chain = true)
public class StandingsDto {
	StandingsInternalDto _internal;
	LeaguesStandingsDto league;
}
