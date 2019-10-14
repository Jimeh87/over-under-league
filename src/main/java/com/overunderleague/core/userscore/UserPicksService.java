package com.overunderleague.core.userscore;

import com.overunderleague.controller.api.UserPickDto;
import com.overunderleague.controller.api.UserPicksDto;
import com.overunderleague.controller.api.WagerType;
import org.springframework.stereotype.Service;

import java.util.Arrays;
import java.util.List;

@Service
public class UserPicksService {

	private static final List<UserPicksDto> USER_PICKS = Arrays.asList(
			UserPicksDto.of(
					"Amanpal",
					UserPickDto.of("1610612754", WagerType.UNDER),
					UserPickDto.of("1610612740", WagerType.OVER),
					UserPickDto.of("1610612751", WagerType.UNDER),
					UserPickDto.of("1610612763", WagerType.UNDER)
			),
			UserPicksDto.of(
					"Brennan",
					UserPickDto.of("1610612754", WagerType.OVER),
					UserPickDto.of("1610612749", WagerType.UNDER),
					UserPickDto.of("1610612740", WagerType.OVER),
					UserPickDto.of("1610612763", WagerType.OVER)
			),
			UserPicksDto.of(
					"Chris",
					UserPickDto.of("1610612748", WagerType.OVER),
					UserPickDto.of("1610612760", WagerType.UNDER),
					UserPickDto.of("1610612742", WagerType.UNDER),
					UserPickDto.of("1610612741", WagerType.UNDER)
			),
			UserPicksDto.of(
					"Dan",
					UserPickDto.of("1610612766", WagerType.UNDER),
					UserPickDto.of("1610612759", WagerType.UNDER),
					UserPickDto.of("1610612750", WagerType.UNDER),
					UserPickDto.of("1610612737", WagerType.UNDER)
			),
			UserPicksDto.of(
					"Jim",
					UserPickDto.of("1610612762", WagerType.UNDER),
					UserPickDto.of("1610612758", WagerType.OVER),
					UserPickDto.of("1610612746", WagerType.OVER),
					UserPickDto.of("1610612761", WagerType.OVER)
			),
			UserPicksDto.of(
					"Joel",
					UserPickDto.of("1610612757", WagerType.OVER),
					UserPickDto.of("1610612756", WagerType.OVER),
					UserPickDto.of("1610612752", WagerType.OVER),
					UserPickDto.of("1610612747", WagerType.OVER)
			),
			UserPicksDto.of(
					"Justin",
					UserPickDto.of("1610612744", WagerType.UNDER),
					UserPickDto.of("1610612739", WagerType.OVER),
					UserPickDto.of("1610612764", WagerType.UNDER),
					UserPickDto.of("1610612765", WagerType.OVER)
			),
			UserPicksDto.of(
					"Landon",
					UserPickDto.of("1610612753", WagerType.OVER),
					UserPickDto.of("1610612745", WagerType.UNDER),
					UserPickDto.of("1610612755", WagerType.OVER),
					UserPickDto.of("1610612760", WagerType.UNDER)
			),
			UserPicksDto.of(
					"Mark",
					UserPickDto.of("1610612744", WagerType.OVER),
					UserPickDto.of("1610612739", WagerType.UNDER),
					UserPickDto.of("1610612764", WagerType.OVER),
					UserPickDto.of("1610612765", WagerType.UNDER)
			),
			UserPicksDto.of(
					"Stu",
					UserPickDto.of("1610612753", WagerType.UNDER),
					UserPickDto.of("1610612745", WagerType.OVER),
					UserPickDto.of("1610612755", WagerType.UNDER),
					UserPickDto.of("1610612760", WagerType.OVER)
			)
	);

	public List<UserPicksDto> list() {
		return USER_PICKS;
	}
}
