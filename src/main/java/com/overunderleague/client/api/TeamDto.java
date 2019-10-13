package com.overunderleague.client.api;

import lombok.Data;
import lombok.experimental.Accessors;

@Data
@Accessors(chain = true)
public class TeamDto {
	private String teamKey;
	private String teamName;
	private String teamCode;
	private String teamNickname;
	private String teamTricode;
	private String clinchedConference;
	private String clinchedDivision;
	private String clinchedPlayoffs;
	private String streakText;
}
