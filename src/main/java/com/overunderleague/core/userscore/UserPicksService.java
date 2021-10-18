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
					"Stu",
					UserPickDto.of(JAZZ, OVER),
					UserPickDto.of(SUNS, OVER),
					UserPickDto.of(WARRIORS, UNDER),
					UserPickDto.of(BUCKS, OVER),
					UserPickDto.of(BULLS, UNDER)
			),
			UserPicksDto.of(
					"Amanpal",
					UserPickDto.of(NUGGETS, OVER),
					UserPickDto.of(RAPTORS, OVER),
					UserPickDto.of(LAKERS, OVER),
					UserPickDto.of(CAVALIERS, OVER),
					UserPickDto.of(THUNDER, OVER)
			),
			UserPicksDto.of(
					"Landon",
					UserPickDto.of(BULLS, OVER),
					UserPickDto.of(HEAT, OVER),
					UserPickDto.of(WARRIORS, OVER),
					UserPickDto.of(WIZARDS, UNDER),
					UserPickDto.of(KNICKS, OVER)
			),
			UserPicksDto.of(
					"Joel",
					UserPickDto.of(KINGS, UNDER),
					UserPickDto.of(PELICANS, UNDER),
					UserPickDto.of(CLIPPERS, UNDER),
					UserPickDto.of(PACERS, UNDER),
					UserPickDto.of(TIMBERWOLVES, OVER)
			),
			UserPicksDto.of(
					"Justin",
					UserPickDto.of(HAWKS, OVER),
					UserPickDto.of(MAVERICKS, OVER),
					UserPickDto.of(CLIPPERS, OVER),
					UserPickDto.of(HORNETS, OVER),
					UserPickDto.of(THUNDER, UNDER)
			),
			UserPicksDto.of(
					"Jim",
					UserPickDto.of(LAKERS, UNDER),
					UserPickDto.of(PELICANS, OVER),
					UserPickDto.of(NETS, UNDER),
					UserPickDto.of(TRAIL_BLAZERS, OVER),
					UserPickDto.of(HEAT, UNDER)
			),
			UserPicksDto.of(
					"Mark",
					UserPickDto.of(NETS, OVER),
					UserPickDto.of(ROCKETS, UNDER),
					UserPickDto.of(HORNETS, UNDER),
					UserPickDto.of(CAVALIERS, UNDER),
					UserPickDto.of(SEVENTY_SIXERS, OVER)
			),
			UserPicksDto.of(
					"Peng",
					UserPickDto.of(SPURS, OVER),
					UserPickDto.of(WIZARDS, OVER),
					UserPickDto.of(RAPTORS, UNDER),
					UserPickDto.of(CELTICS, OVER),
					UserPickDto.of(JAZZ, UNDER)
			),
			UserPicksDto.of(
					"Brennan",
					UserPickDto.of(KNICKS, UNDER),
					UserPickDto.of(KINGS, OVER),
					UserPickDto.of(TRAIL_BLAZERS, UNDER),
					UserPickDto.of(PISTONS, OVER),
					UserPickDto.of(NUGGETS, UNDER)
			),
			UserPicksDto.of(
					"Dan",
					UserPickDto.of(SEVENTY_SIXERS, UNDER),
					UserPickDto.of(GRIZZLIES, UNDER),
					UserPickDto.of(TIMBERWOLVES, UNDER),
					UserPickDto.of(MAGIC, OVER),
					UserPickDto.of(CELTICS, UNDER)
			)
	);

	public List<UserPicksDto> list() {
		return USER_PICKS;
	}
}
