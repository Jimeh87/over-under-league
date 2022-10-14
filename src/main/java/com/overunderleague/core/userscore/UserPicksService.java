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
					UserPickDto.of(RAPTORS, OVER),
					UserPickDto.of(SEVENTY_SIXERS, OVER),
					UserPickDto.of(BUCKS, OVER),
					UserPickDto.of(TIMBERWOLVES, UNDER)
			),
			UserPicksDto.of(
					"Amanpal",
					UserPickDto.of(NUGGETS, OVER),
					UserPickDto.of(KINGS, OVER),
					UserPickDto.of(PELICANS, OVER),
					UserPickDto.of(CELTICS, UNDER)
			),
			UserPicksDto.of(
					"Langdon",
					UserPickDto.of(LAKERS, UNDER),
					UserPickDto.of(CLIPPERS, UNDER),
					UserPickDto.of(KNICKS, OVER),
					UserPickDto.of(MAGIC, OVER)
			),
			UserPicksDto.of(
					"Joel",
					UserPickDto.of(HAWKS, UNDER),
					UserPickDto.of(MAVERICKS, UNDER),
					UserPickDto.of(ROCKETS, OVER),
					UserPickDto.of(SUNS, UNDER)
			),
			UserPicksDto.of(
					"Justin",
					UserPickDto.of(CLIPPERS, OVER),
					UserPickDto.of(THUNDER, UNDER),
					UserPickDto.of(BULLS, OVER),
					UserPickDto.of(HEAT, UNDER)
			),
			UserPicksDto.of(
					"Jim",
					UserPickDto.of(HORNETS, OVER),
					UserPickDto.of(GRIZZLIES, OVER),
					UserPickDto.of(PISTONS, UNDER),
					UserPickDto.of(TRAIL_BLAZERS, UNDER)
			),
			UserPicksDto.of(
					"Mark",
					UserPickDto.of(NETS, UNDER),
					UserPickDto.of(HAWKS, OVER),
					UserPickDto.of(PELICANS, UNDER),
					UserPickDto.of(KNICKS, UNDER)
			),
			UserPicksDto.of(
					"Peng",
					UserPickDto.of(MAGIC, UNDER),
					UserPickDto.of(SPURS, OVER),
					UserPickDto.of(JAZZ, OVER),
					UserPickDto.of(CAVALIERS, UNDER)
			),
			UserPicksDto.of(
					"Brennan",
					UserPickDto.of(JAZZ, UNDER),
					UserPickDto.of(PACERS, UNDER),
					UserPickDto.of(ROCKETS, UNDER),
					UserPickDto.of(WIZARDS, UNDER)
			),
			UserPicksDto.of(
					"Dan",
					UserPickDto.of(WARRIORS, OVER),
					UserPickDto.of(MAVERICKS, OVER),
					UserPickDto.of(KINGS, UNDER),
					UserPickDto.of(HORNETS, UNDER)
			)
	);

	public List<UserPicksDto> list() {
		return USER_PICKS;
	}
}
