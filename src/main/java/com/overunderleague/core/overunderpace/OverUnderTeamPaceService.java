package com.overunderleague.core.overunderpace;

import com.overunderleague.core.overunder.OverUnderTeamDto;
import com.overunderleague.nbaclient.NbaClient;
import com.overunderleague.nbaclient.api.NbaTeamStandingDto;
import com.overunderleague.controller.api.OverUnderTeamPaceDto;
import com.overunderleague.core.overunder.OverUnderService;
import com.overunderleague.core.overunder.Team;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;
import java.util.List;

import static java.util.stream.Collectors.toList;

@Service
public class OverUnderTeamPaceService {

	@Autowired
	private NbaClient nbaClient;
	@Autowired
	private OverUnderService overUnderService;

	public List<OverUnderTeamPaceDto> list() {
		return getTeamStandings()
				.stream()
				.filter(this::isNbaTeam)
				.map(nbaTeamStandingDto -> toOverUnderTeamPaceDto(nbaTeamStandingDto, overUnderService.get(Team.getTeamByNbaTeamId(nbaTeamStandingDto.getTeamId()))))
				.collect(toList());
	}

	private boolean isNbaTeam(NbaTeamStandingDto nbaTeamStandingDto) {
		// NBA api returns teams not in the NBA (probably just for preseason).
		return Team.isNbaTeamIdExists(nbaTeamStandingDto.getTeamId());
	}

	private List<NbaTeamStandingDto> getTeamStandings() {
		return nbaClient.findCurrentStandings().getLeague().getStandard().getTeams();
	}

	private OverUnderTeamPaceDto toOverUnderTeamPaceDto(NbaTeamStandingDto teamStanding, OverUnderTeamDto overUnderTeam) {
		return new OverUnderTeamPaceDto()
				.setTeam(overUnderTeam.getTeam())
				.setTeamNickname(overUnderTeam.getNickname())
				.setWinOverUnder(overUnderTeam.getWinOverUnder())
				.setWins(teamStanding.getWin())
				.setLoses(teamStanding.getLoss())
				.setWinPercentage(teamStanding.getWinPct())
				.setPace(calculatePace(teamStanding, overUnderTeam.getWinOverUnder()));
	}

	private int calculatePace(NbaTeamStandingDto teamStanding, BigDecimal winOverUnder) {
		return PaceCalculator.builder()
				.wins(teamStanding.getWin())
				.loses(teamStanding.getLoss())
				.overUnder(winOverUnder.doubleValue())
				.build()
				.calculate();
	}
}
