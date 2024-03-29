package com.overunderleague.core.overunder;

import org.springframework.stereotype.Service;

import java.math.BigDecimal;
import java.util.Arrays;
import java.util.List;
import java.util.Map;
import java.util.function.Function;
import java.util.stream.Collectors;

@Service
public class OverUnderService {

	private static final List<OverUnderTeamDto> OVER_UNDER_LIST = Arrays.asList(
			OverUnderTeamDto.of(Team.BUCKS,          new BigDecimal("55.5")),
			OverUnderTeamDto.of(Team.BULLS,          new BigDecimal("37.5")),
			OverUnderTeamDto.of(Team.CAVALIERS,      new BigDecimal("50.5")),
			OverUnderTeamDto.of(Team.CELTICS,        new BigDecimal("54.5")),
			OverUnderTeamDto.of(Team.CLIPPERS,       new BigDecimal("45.5")),
			OverUnderTeamDto.of(Team.GRIZZLIES,      new BigDecimal("46.5")),
			OverUnderTeamDto.of(Team.HAWKS,          new BigDecimal("42.5")),
			OverUnderTeamDto.of(Team.HEAT,           new BigDecimal("45.5")),
			OverUnderTeamDto.of(Team.HORNETS,        new BigDecimal("30.5")),
			OverUnderTeamDto.of(Team.JAZZ,           new BigDecimal("36.5")),
			OverUnderTeamDto.of(Team.KINGS,          new BigDecimal("44.5")),
			OverUnderTeamDto.of(Team.KNICKS,         new BigDecimal("44.5")),
			OverUnderTeamDto.of(Team.LAKERS,         new BigDecimal("47.5")),
			OverUnderTeamDto.of(Team.MAGIC,          new BigDecimal("36.5")),
			OverUnderTeamDto.of(Team.MAVERICKS,      new BigDecimal("44.5")),
			OverUnderTeamDto.of(Team.NETS,           new BigDecimal("37.5")),
			OverUnderTeamDto.of(Team.NUGGETS,        new BigDecimal("52.5")),
			OverUnderTeamDto.of(Team.PACERS,         new BigDecimal("37.5")),
			OverUnderTeamDto.of(Team.PELICANS,       new BigDecimal("43.5")),
			OverUnderTeamDto.of(Team.PISTONS,        new BigDecimal("27.5")),
			OverUnderTeamDto.of(Team.RAPTORS,        new BigDecimal("36.5")),
			OverUnderTeamDto.of(Team.ROCKETS,        new BigDecimal("31.5")),
			OverUnderTeamDto.of(Team.SEVENTY_SIXERS, new BigDecimal("50.5")),
			OverUnderTeamDto.of(Team.SPURS,          new BigDecimal("30.5")),
			OverUnderTeamDto.of(Team.SUNS,           new BigDecimal("51.5")),
			OverUnderTeamDto.of(Team.THUNDER,        new BigDecimal("44.5")),
			OverUnderTeamDto.of(Team.TIMBERWOLVES,   new BigDecimal("43.5")),
			OverUnderTeamDto.of(Team.TRAIL_BLAZERS,  new BigDecimal("29.5")),
			OverUnderTeamDto.of(Team.WARRIORS,       new BigDecimal("48.5")),
			OverUnderTeamDto.of(Team.WIZARDS,        new BigDecimal("24.5"))
	);

	private static final Map<Team, OverUnderTeamDto> OVER_UNDER_BY_TEAM = OVER_UNDER_LIST.stream()
			.collect(Collectors.toMap(OverUnderTeamDto::getTeam, Function.identity()));

	public List<OverUnderTeamDto> list(){
		return OVER_UNDER_LIST;
	}

	public OverUnderTeamDto get(Team team) {
		if (!OVER_UNDER_BY_TEAM.containsKey(team)) {
			throw new IllegalArgumentException(String.format("Team not found %s", team));
		}
		return OVER_UNDER_BY_TEAM.get(team);
	}

}
