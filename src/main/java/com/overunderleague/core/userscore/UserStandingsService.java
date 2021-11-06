package com.overunderleague.core.userscore;

import com.overunderleague.controller.api.*;
import com.overunderleague.core.overunder.Team;
import com.overunderleague.core.overunderpace.OverUnderTeamPaceService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Map;
import java.util.function.Function;

import static java.util.Collections.reverseOrder;
import static java.util.Comparator.comparing;
import static java.util.stream.Collectors.toList;
import static java.util.stream.Collectors.toMap;

@Service
public class UserStandingsService {

	@Autowired
	private OverUnderTeamPaceService overUnderTeamPaceService;
	@Autowired
	private UserPicksService userPicksService;
	@Autowired
	private TeamTemperatureService teamTemperatureService;

	public List<UserStandingDto> list() {
		Map<Team, OverUnderTeamPaceDto> teamPaceByTeam = getTeamPaceByTeam();

		return userPicksService.list()
				.stream()
				.map(userPick -> toUserScore(userPick, toUserTeamScores(userPick, teamPaceByTeam)))
				.sorted(reverseOrder(comparing(UserStandingDto::getPoints)))
				.collect(toList());
	}

	private Map<Team, OverUnderTeamPaceDto> getTeamPaceByTeam() {
		return overUnderTeamPaceService.list()
				.stream()
				.collect(toMap(OverUnderTeamPaceDto::getTeam, Function.identity()));
	}

	private List<UserTeamScoreDto> toUserTeamScores(UserPicksDto userPick, Map<Team, OverUnderTeamPaceDto> teamPaceByTeam) {
		return userPick.getUserPicks()
				.stream()
				.map(pick -> toUserTeamScore(pick, teamPaceByTeam.get(pick.getTeam())))
				.sorted(reverseOrder(comparing(UserTeamScoreDto::getPoints)))
				.collect(toList());
	}

	private UserTeamScoreDto toUserTeamScore(UserPickDto pick, OverUnderTeamPaceDto teamPace) {
		return new UserTeamScoreDto()
				.setTeam(pick.getTeam())
				.setTeamNickname(teamPace.getTeamNickname())
				.setWager(pick.getWagerType())
				.setWinOverUnder(teamPace.getWinOverUnder())
				.setWins(teamPace.getWins())
				.setLoses(teamPace.getLoses())
				.setTemperature(teamTemperatureService.get(pick, teamPace))
				.setWager(pick.getWagerType())
				.setPoints(pick.getWagerType().toPoints(teamPace.getPace()));
	}

	private UserStandingDto toUserScore(UserPicksDto userPick, List<UserTeamScoreDto> userTeamScores) {
		return new UserStandingDto()
				.setUserNickname(userPick.getUserNickname())
				.setPoints(calculateScore(userTeamScores))
				.setTemperature(calculateUserTemperature(userTeamScores))
				.setTeamScores(userTeamScores);
	}

	private TeamTemperatureDto calculateUserTemperature(List<UserTeamScoreDto> userTeamScores) {
		return teamTemperatureService.get(userTeamScores.stream()
				.map(UserTeamScoreDto::getTemperature)
				.collect(toList()));
	}

	private Integer calculateScore(List<UserTeamScoreDto> userTeamScores) {
		return userTeamScores
				.stream()
				.mapToInt(UserTeamScoreDto::getPoints)
				.sum();
	}

}
