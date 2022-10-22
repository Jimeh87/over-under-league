package com.overunderleague.core.overunderpace;

import com.overunderleague.controller.api.OverUnderTeamPaceDto;
import com.overunderleague.core.overunder.OverUnderService;
import com.overunderleague.core.overunder.OverUnderTeamDto;
import com.overunderleague.core.overunder.Team;
import com.overunderleague.core.standing.NbaStandingsService;
import com.overunderleague.core.standing.NbaTeamStanding;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;
import java.util.List;

import static java.util.stream.Collectors.toList;

@Service
public class OverUnderTeamPaceService {

	@Autowired
	private NbaStandingsService nbaStandingsService;
	@Autowired
	private OverUnderService overUnderService;

	public List<OverUnderTeamPaceDto> list() {
		return getTeamStandings()
				.stream()
				.filter(this::isNbaTeam)
				.map(nbaTeamStandingDto -> toOverUnderTeamPaceDto(nbaTeamStandingDto, overUnderService.get(Team.getTeamByNbaTeamId(nbaTeamStandingDto.getNbaTeamId()))))
				.collect(toList());
	}

	private boolean isNbaTeam(NbaTeamStanding nbaTeamStanding) {
		// NBA api returns teams not in the NBA (probably just for preseason).
		return Team.isNbaTeamIdExists(nbaTeamStanding.getNbaTeamId());
	}

	private List<NbaTeamStanding> getTeamStandings() {
		return nbaStandingsService.getStandings();
	}

	private OverUnderTeamPaceDto toOverUnderTeamPaceDto(NbaTeamStanding teamStanding, OverUnderTeamDto overUnderTeam) {
		return new OverUnderTeamPaceDto()
				.setTeam(overUnderTeam.getTeam())
				.setTeamNickname(overUnderTeam.getNickname())
				.setWinOverUnder(overUnderTeam.getWinOverUnder())
				.setWins(teamStanding.getWin())
				.setLoses(teamStanding.getLoss())
				.setWinPercentage(teamStanding.getWinPct())
				.setWinsInLastTenGames(teamStanding.getLastTenWin())
				.setPaceInLastTenGames(calculatePaceOverLastTenGames(teamStanding, overUnderTeam.getWinOverUnder()))
				.setPace(calculatePace(teamStanding, overUnderTeam.getWinOverUnder()));
	}

	private int calculatePace(NbaTeamStanding teamStanding, BigDecimal winOverUnder) {
		return PaceCalculator.builder()
				.wins(teamStanding.getWin())
				.loses(teamStanding.getLoss())
				.overUnder(winOverUnder.doubleValue())
				.build()
				.calculate();
	}

	private int calculatePaceOverLastTenGames(NbaTeamStanding teamStanding, BigDecimal winOverUnder) {
		int previousNumberOfGames = Math.min(10, teamStanding.getWin() + teamStanding.getLoss());
		return PaceCalculator.builder()
				.wins(teamStanding.getLastTenWin())
				.loses(previousNumberOfGames - teamStanding.getLastTenWin())
				.overUnder(winOverUnder.doubleValue())
				.build()
				.calculate();
	}
}
