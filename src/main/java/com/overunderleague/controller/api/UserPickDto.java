package com.overunderleague.controller.api;

import com.overunderleague.core.overunder.Team;
import lombok.Value;

@Value(staticConstructor = "of")
public class UserPickDto {
	private Team team;
	private WagerType wagerType;
}
