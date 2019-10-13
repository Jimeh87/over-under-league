package com.overunderleague.core.overunderpace;

import com.overunderleague.client.NbaClient;
import com.overunderleague.client.api.StandingsDto;
import com.overunderleague.client.api.TeamStandingDto;
import com.overunderleague.controller.api.OverUnderTeamPaceDto;
import com.overunderleague.core.overunder.OverUnderService;
import com.overunderleague.core.overunder.OverUnderTeamDto;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;
import java.util.List;
import java.util.Map;
import java.util.function.Predicate;
import java.util.stream.Collectors;

import static java.util.stream.Collectors.toList;

@Service
public class OverUnderTeamPaceService {

	@Autowired
	private NbaClient nbaClient;
	@Autowired
	private OverUnderService overUnderService;

	public List<OverUnderTeamPaceDto> list() {
		Map<String, BigDecimal> overUnderByTeamId = getOverUnderByTeamId();

		StandingsDto standings = nbaClient.findCurrentStandings();
		return getTeamStandings(standings)
				.stream()
				.filter(isNbaTeam(overUnderByTeamId))
				.map(teamStandingDto -> toOverUnderTeamPaceDto(teamStandingDto, overUnderByTeamId.get(teamStandingDto.getTeamId())))
				.collect(toList());
	}

	private Predicate<TeamStandingDto> isNbaTeam(Map<String, BigDecimal> overUnderByTeamId) {
		// A little hacky.. NBA api returns teams not in the NBA (probably just for preseason).
		return teamStandingDto -> overUnderByTeamId.containsKey(teamStandingDto.getTeamId());
	}

	private List<TeamStandingDto> getTeamStandings(StandingsDto standings) {
		return standings.getLeague().getStandard().getTeams();
	}

	private Map<String, BigDecimal> getOverUnderByTeamId() {
		return overUnderService.list()
				.stream()
				.collect(Collectors.toMap(OverUnderTeamDto::getTeamId, OverUnderTeamDto::getWinOverUnder));
	}

	private OverUnderTeamPaceDto toOverUnderTeamPaceDto(TeamStandingDto teamStanding, BigDecimal winOverUnder) {
		return new OverUnderTeamPaceDto()
				.setTeamId(teamStanding.getTeamId())
				.setTeamNickname(teamStanding.getTeamSitesOnly().getTeamNickname())
				.setWins(teamStanding.getWin())
				.setLoses(teamStanding.getLoss())
				.setWinOverUnder(winOverUnder)
				.setPace(calculatePace(teamStanding, winOverUnder));
	}

	private int calculatePace(TeamStandingDto teamStanding, BigDecimal winOverUnder) {
		return PaceCalculator.builder()
				.wins(teamStanding.getWin())
				.loses(teamStanding.getLoss())
				.overUnder(winOverUnder.doubleValue())
				.build()
				.calculate();
	}
}
