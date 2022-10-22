package com.overunderleague.core.userscore;

import com.overunderleague.controller.api.OverUnderTeamPaceDto;
import com.overunderleague.controller.api.TeamTemperatureDto;
import com.overunderleague.controller.api.TemperatureType;
import com.overunderleague.controller.api.UserPickDto;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.function.Function;

@Service
public class TeamTemperatureService {
	private static final int PREVIOUS_GAME_COUNT = 10;

	public TeamTemperatureDto get(UserPickDto pick, OverUnderTeamPaceDto teamPace) {
		int games = Math.min(PREVIOUS_GAME_COUNT, teamPace.getWins() + teamPace.getLoses());
		int points = pick.getWagerType().toPoints(teamPace.getPaceInLastTenGames());
		return toTeamTemperatureDto(games, points, TemperatureType::fromTeamPercentage);
	}

	public TeamTemperatureDto get(List<TeamTemperatureDto> teamTemperatures) {
		int games = teamTemperatures.stream()
				.mapToInt(TeamTemperatureDto::getLastNumberOfGames)
				.sum();

		int points = teamTemperatures.stream()
				.mapToInt(TeamTemperatureDto::getPointsInLastNumberOfGames)
				.sum();

		return toTeamTemperatureDto(games, points, TemperatureType::fromUserPercentage);
	}

	private TeamTemperatureDto toTeamTemperatureDto(int games, int points, Function<Double, TemperatureType> temperatureFn) {
		Double pointPercentage = calculatePointPercentage(games, points);
		return new TeamTemperatureDto()
				.setLastNumberOfGames(games)
				.setPointsInLastNumberOfGames(points)
				.setPointPercentage(pointPercentage)
				.setTemperature(temperatureFn.apply(pointPercentage));
	}

	private static Double calculatePointPercentage(int games, double points) {
		return games < PREVIOUS_GAME_COUNT ? null : points / (double) games;
	}

}
