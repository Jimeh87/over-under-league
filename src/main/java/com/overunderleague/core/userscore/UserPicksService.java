package com.overunderleague.core.userscore;

import com.overunderleague.controller.api.UserPickDto;
import com.overunderleague.controller.api.UserPicksDto;
import org.springframework.stereotype.Service;

import java.util.Arrays;
import java.util.List;

import static com.overunderleague.controller.api.WagerType.OVER;
import static com.overunderleague.controller.api.WagerType.UNDER;
import static com.overunderleague.core.overunder.Team.*;

@Service
public class UserPicksService {

	private static final List<UserPicksDto> USER_PICKS = Arrays.asList(
			UserPicksDto.of(
					"Brennan",
					UserPickDto.of(JAZZ, OVER),
					UserPickDto.of(MAGIC, OVER),
					UserPickDto.of(PELICANS, UNDER),
					UserPickDto.of(MAVERICKS, OVER)
			),
			UserPicksDto.of(
					"Mark",
					UserPickDto.of(BUCKS, OVER),
					UserPickDto.of(ROCKETS, UNDER),
					UserPickDto.of(LAKERS, OVER),
					UserPickDto.of(HAWKS, UNDER)
			),
			UserPicksDto.of(
					"Justin",
					UserPickDto.of(TIMBERWOLVES, OVER),
					UserPickDto.of(SEVENTY_SIXERS, UNDER),
					UserPickDto.of(SPURS, OVER),
					UserPickDto.of(KNICKS, OVER)
			),
			UserPicksDto.of(
					"Joel",
					UserPickDto.of(HAWKS, OVER),
					UserPickDto.of(WARRIORS, OVER),
					UserPickDto.of(GRIZZLIES, UNDER),
					UserPickDto.of(PISTONS, OVER)
			),
			UserPicksDto.of(
					"Stu",
					UserPickDto.of(THUNDER, OVER),
					UserPickDto.of(CLIPPERS, UNDER),
					UserPickDto.of(MAVERICKS, UNDER),
					UserPickDto.of(KINGS, UNDER)
			),
			UserPicksDto.of(
					"Jim",
					UserPickDto.of(RAPTORS, OVER),
					UserPickDto.of(WIZARDS, OVER),
					UserPickDto.of(BULLS, OVER),
					UserPickDto.of(THUNDER, UNDER)
			),
			UserPicksDto.of(
					"Amanpal",
					UserPickDto.of(PACERS, OVER),
					UserPickDto.of(TRAIL_BLAZERS, OVER),
					UserPickDto.of(CLIPPERS, OVER),
					UserPickDto.of(SUNS, OVER)
			),
			UserPicksDto.of(
					"Dan",
					UserPickDto.of(NUGGETS, OVER),
					UserPickDto.of(HEAT, UNDER),
					UserPickDto.of(NETS, UNDER),
					UserPickDto.of(LAKERS, UNDER)
			),
			UserPicksDto.of(
					"Landon",
					UserPickDto.of(GRIZZLIES, OVER),
					UserPickDto.of(PELICANS, OVER),
					UserPickDto.of(CAVALIERS, OVER),
					UserPickDto.of(KNICKS, UNDER)
			)
	);

	public List<UserPicksDto> list() {
		return USER_PICKS;
	}
}
