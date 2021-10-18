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
public class UserScoreService {

	@Autowired
	private OverUnderTeamPaceService overUnderTeamPaceService;
	@Autowired
	private UserPicksService userPicksService;

	public List<UserScoreDto> list() {
		Map<Team, OverUnderTeamPaceDto> teamPaceByTeam = getTeamPaceByTeam();

		return userPicksService.list()
				.stream()
				.map(userPick -> toUserScore(userPick, toUserTeamScores(userPick, teamPaceByTeam)))
				.sorted(reverseOrder(comparing(UserScoreDto::getScore)))
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
				.sorted(reverseOrder(comparing(UserTeamScoreDto::getScore)))
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
				.setWager(pick.getWagerType())
				.setScore(pick.getWagerType().toScore(teamPace.getPace()));
	}

	private UserScoreDto toUserScore(UserPicksDto userPick, List<UserTeamScoreDto> userTeamScores) {
		return new UserScoreDto()
				.setUserNickname(userPick.getUserNickname())
				.setScore(calculateScore(userTeamScores))
				.setTeamScores(userTeamScores);
	}

	private Integer calculateScore(List<UserTeamScoreDto> userTeamScores) {
		return userTeamScores
				.stream()
				.mapToInt(UserTeamScoreDto::getScore)
				.sum();
	}

}
