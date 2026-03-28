package com.overunderleague.core.overunder;

import lombok.AllArgsConstructor;
import lombok.Getter;

import java.util.Arrays;
import java.util.Locale;
import java.util.Map;
import java.util.Optional;
import java.util.function.Function;
import java.util.stream.Collectors;

@Getter
@AllArgsConstructor
public enum Team {
	BUCKS("1610612749", "Bucks", "milwaukee-bucks"),
	BULLS("1610612741", "Bulls", "chicago-bulls"),
	CAVALIERS("1610612739", "Cavaliers", "cleveland-cavaliers"),
	CELTICS("1610612738", "Celtics", "boston-celtics"),
	CLIPPERS("1610612746", "Clippers", "los-angeles-clippers"),
	GRIZZLIES("1610612763", "Grizzlies", "memphis-grizzlies"),
	HAWKS("1610612737", "Hawks", "atlanta-hawks"),
	HEAT("1610612748", "Heat", "miami-heat"),
	HORNETS("1610612766", "Hornets", "charlotte-hornets"),
	JAZZ("1610612762", "Jazz", "utah-jazz"),
	KINGS("1610612758", "Kings", "sacramento-kings"),
	KNICKS("1610612752", "Knicks", "new-york-knicks"),
	LAKERS("1610612747", "Lakers", "los-angeles-lakers"),
	MAGIC("1610612753", "Magic", "orlando-magic"),
	MAVERICKS("1610612742", "Mavericks", "dallas-mavericks"),
	NETS("1610612751", "Nets", "brooklyn-nets"),
	NUGGETS("1610612743", "Nuggets", "denver-nuggets"),
	PACERS("1610612754", "Pacers", "indiana-pacers"),
	PELICANS("1610612740", "Pelicans", "new-orleans-pelicans"),
	PISTONS("1610612765", "Pistons", "detroit-pistons"),
	RAPTORS("1610612761", "Raptors", "toronto-raptors"),
	ROCKETS("1610612745", "Rockets", "houston-rockets"),
	SEVENTY_SIXERS("1610612755", "76ers", "philadelphia-76ers"),
	SPURS("1610612759", "Spurs", "san-antonio-spurs"),
	SUNS("1610612756", "Suns", "phoenix-suns"),
	THUNDER("1610612760", "Thunder", "oklahoma-city-thunder"),
	TIMBERWOLVES("1610612750", "Timberwolves", "minnesota-timberwolves"),
	TRAIL_BLAZERS("1610612757", "Trail Blazers", "portland-trail-blazers"),
	WARRIORS("1610612744", "Warriors", "golden-state-warriors"),
	WIZARDS("1610612764", "Wizards", "washington-wizards");

	private final String nbaTeamId;
	private final String nickname;
	private final String tsnSlug;

	private static final Map<String, Team> TEAM_BY_NBA_TEAM_ID = Arrays.stream(Team.values())
			.collect(Collectors.toMap(Team::getNbaTeamId, Function.identity()));

	private static final Map<String, Team> TEAM_BY_TSN_SLUG = Arrays.stream(Team.values())
			.collect(Collectors.toMap(Team::getTsnSlug, Function.identity()));

	public static boolean isNbaTeamIdExists(String nbaId) {
		return TEAM_BY_NBA_TEAM_ID.containsKey(nbaId);
	}

	public static Team getTeamByNbaTeamId(String nbaId) {
		if (!isNbaTeamIdExists(nbaId)) {
			throw new IllegalArgumentException(String.format("Team not found for NBA ID [%s]", nbaId));
		}
		return TEAM_BY_NBA_TEAM_ID.get(nbaId);
	}

	public static Optional<Team> findByTsnSlug(String slug) {
		if (slug == null || slug.isBlank()) return Optional.empty();
		return Optional.ofNullable(TEAM_BY_TSN_SLUG.get(slug.trim().toLowerCase(Locale.ROOT)));
	}
}
