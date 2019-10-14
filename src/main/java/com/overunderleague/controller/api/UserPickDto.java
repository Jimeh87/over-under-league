package com.overunderleague.controller.api;

import lombok.Value;

@Value(staticConstructor = "of")
public class UserPickDto {
	private String teamId;
	private WagerType wagerType;
}
