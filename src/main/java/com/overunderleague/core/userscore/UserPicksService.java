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
					UserPickDto.of(MAGIC, OVER),
					UserPickDto.of(CLIPPERS, UNDER),
					UserPickDto.of(WARRIORS, OVER),
					UserPickDto.of(PISTONS, OVER),
					UserPickDto.of(TIMBERWOLVES, UNDER)

			),
			UserPicksDto.of(
					"Mark",
					UserPickDto.of(BUCKS, UNDER),
					UserPickDto.of(KINGS, OVER),
					UserPickDto.of(WARRIORS, UNDER),
					UserPickDto.of(WIZARDS, UNDER),
					UserPickDto.of(ROCKETS, UNDER)

			),
			UserPicksDto.of(
					"Justin",
					UserPickDto.of(RAPTORS, OVER),
					UserPickDto.of(HAWKS, OVER),
					UserPickDto.of(TIMBERWOLVES, OVER),
					UserPickDto.of(BULLS, OVER),
					UserPickDto.of(NETS, OVER)
			),
			UserPicksDto.of(
					"Joel",
					UserPickDto.of(CAVALIERS, OVER),
					UserPickDto.of(PELICANS, OVER),
					UserPickDto.of(PACERS, UNDER),
					UserPickDto.of(LAKERS, OVER),
					UserPickDto.of(RAPTORS, UNDER)
			),
			UserPicksDto.of(
					"Stu",
					UserPickDto.of(THUNDER, OVER),
					UserPickDto.of(SUNS, OVER),
					UserPickDto.of(PELICANS, UNDER),
					UserPickDto.of(LAKERS, UNDER),
					UserPickDto.of(ROCKETS, OVER)
			),
			UserPicksDto.of(
					"Jim",
					UserPickDto.of(KINGS, UNDER),
					UserPickDto.of(MAGIC, UNDER),
					UserPickDto.of(JAZZ, UNDER),
					UserPickDto.of(SEVENTY_SIXERS, OVER),
					UserPickDto.of(BULLS, UNDER)
			),
			UserPicksDto.of(
					"Amanpal",
					UserPickDto.of(NUGGETS, OVER),
					UserPickDto.of(SPURS, OVER),
					UserPickDto.of(KNICKS, OVER),
					UserPickDto.of(TRAIL_BLAZERS, OVER),
					UserPickDto.of(TRAIL_BLAZERS, UNDER)
			),
			UserPicksDto.of(
					"Dan",
					UserPickDto.of(GRIZZLIES, OVER),
					UserPickDto.of(CELTICS, UNDER),
					UserPickDto.of(CAVALIERS, UNDER),
					UserPickDto.of(BUCKS, OVER),
					UserPickDto.of(NUGGETS, UNDER)
			),
			UserPicksDto.of(
					"Landon",
					UserPickDto.of(PACERS, OVER),
					UserPickDto.of(MAVERICKS, OVER),
					UserPickDto.of(HORNETS, UNDER),
					UserPickDto.of(WIZARDS, OVER),
					UserPickDto.of(CELTICS, OVER)
			)
	);

	public List<UserPicksDto> list() {
		return USER_PICKS;
	}
}
