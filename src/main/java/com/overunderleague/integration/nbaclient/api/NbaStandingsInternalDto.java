package com.overunderleague.integration.nbaclient.api;

import lombok.Data;
import lombok.experimental.Accessors;

@Data
@Accessors(chain = true)
public class NbaStandingsInternalDto {
	private String pubDateTime;
}
