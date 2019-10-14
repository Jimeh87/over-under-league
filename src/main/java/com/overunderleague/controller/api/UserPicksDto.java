package com.overunderleague.controller.api;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.ToString;

import java.util.Arrays;
import java.util.List;

@Getter
@AllArgsConstructor
@ToString
public class UserPicksDto {
	private String userNickname;
	private List<UserPickDto> userPicks;

	public static UserPicksDto of(String userNickname, UserPickDto... userPickDtos) {
		return new UserPicksDto(userNickname, Arrays.asList(userPickDtos));
	}
}
