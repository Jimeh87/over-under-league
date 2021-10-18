package com.overunderleague.core.overunder;

import lombok.AllArgsConstructor;
import lombok.Getter;

import java.util.Arrays;
import java.util.Map;
import java.util.function.Function;
import java.util.stream.Collectors;

@Getter
@AllArgsConstructor
public enum Team {
	BUCKS("1610612749", "Bucks"),
	BULLS("1610612741", "Bulls"),
	CAVALIERS("1610612739", "Cavaliers"),
	CELTICS("1610612738", "Celtics"),
	CLIPPERS("1610612746", "Clippers"),
	GRIZZLIES("1610612763", "Grizzlies"),
	HAWKS("1610612737", "Hawks"),
	HEAT("1610612748", "Heat"),
	HORNETS("1610612766", "Hornets"),
	JAZZ("1610612762", "Jazz"),
	KINGS("1610612758", "KINGS"),
	KNICKS("1610612752", "Knicks"),
	LAKERS("1610612747", "Lakers"),
	MAGIC("1610612753", "Magic"),
	MAVERICKS("1610612742", "Mavericks"),
	NETS("1610612751", "Nets"),
	NUGGETS("1610612743", "Nuggets"),
	PACERS("1610612754", "Pacers"),
	PELICANS("1610612740", "Pelicans"),
	PISTONS("1610612765", "Pistons"),
	RAPTORS("1610612761", "Raptors"),
	ROCKETS("1610612745", "Rockets"),
	SEVENTY_SIXERS("1610612755", "76ers"),
	SPURS("1610612759", "Spurs"),
	SUNS("1610612756", "Suns"),
	THUNDER("1610612760", "Thunder"),
	TIMBERWOLVES("1610612750", "Timberwolves"),
	TRAIL_BLAZERS("1610612757", "Trail Blazers"),
	WARRIORS("1610612744", "Warriors"),
	WIZARDS("1610612764", "Wizards");

	private final String nbaTeamId;
	private final String nickname;

	private static final Map<String, Team> TEAM_BY_NBA_TEAM_ID = Arrays.stream(Team.values())
			.collect(Collectors.toMap(Team::getNbaTeamId, Function.identity()));

	public static boolean isNbaTeamIdExists(String nbaId) {
		return TEAM_BY_NBA_TEAM_ID.containsKey(nbaId);
	}

	public static Team getTeamByNbaTeamId(String nbaId) {
		if (!isNbaTeamIdExists(nbaId)) {
			throw new IllegalArgumentException(String.format("Team not found for NBA ID [%s]", nbaId));
		}
		return TEAM_BY_NBA_TEAM_ID.get(nbaId);
	}
}
