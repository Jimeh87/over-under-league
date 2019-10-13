package com.overunderleague.core.overunder;

import org.springframework.stereotype.Service;

import java.math.BigDecimal;
import java.util.Arrays;
import java.util.List;

@Service
public class OverUnderService {

	// TODO: Update with correct over/under
	private static final List<OverUnderTeamDto> OVER_UNDER_LIST = Arrays.asList(
			OverUnderTeamDto.of("1610612754", "Pacers", new BigDecimal("46.5")),
			OverUnderTeamDto.of("1610612749", "Bucks", new BigDecimal("57.5")),
			OverUnderTeamDto.of("1610612740", "Pelicans", new BigDecimal("39.5")),
			OverUnderTeamDto.of("1610612738", "Celtics", new BigDecimal("48.5")),
			OverUnderTeamDto.of("1610612751", "Nets", new BigDecimal("43.5")),
			OverUnderTeamDto.of("1610612743", "Nuggets", new BigDecimal("52.5")),
			OverUnderTeamDto.of("1610612763", "Grizzlies", new BigDecimal("27.5")),
			OverUnderTeamDto.of("1610612748", "Heat", new BigDecimal("43.5")),
			OverUnderTeamDto.of("1610612760", "Thunder", new BigDecimal("31.5")),
			OverUnderTeamDto.of("1610612755", "76ers", new BigDecimal("54.5")),
			OverUnderTeamDto.of("1610612745", "Rockets", new BigDecimal("53.5")),
			OverUnderTeamDto.of("1610612753", "Magic", new BigDecimal("41.5")),
			OverUnderTeamDto.of("1610612765", "Pistons", new BigDecimal("37.5")),
			OverUnderTeamDto.of("1610612764", "Wizards", new BigDecimal("26.5")),
			OverUnderTeamDto.of("1610612739", "Cavaliers", new BigDecimal("24.5")),
			OverUnderTeamDto.of("1610612744", "Warriors", new BigDecimal("47.5")),
			OverUnderTeamDto.of("1610612747", "Lakers", new BigDecimal("51.5")),
			OverUnderTeamDto.of("1610612752", "Knicks", new BigDecimal("26.5")),
			OverUnderTeamDto.of("1610612756", "Suns", new BigDecimal("28.5")),
			OverUnderTeamDto.of("1610612757", "Trail Blazers", new BigDecimal("46.5")),
			OverUnderTeamDto.of("1610612761", "Raptors", new BigDecimal("46.5")),
			OverUnderTeamDto.of("1610612746", "Clippers", new BigDecimal("53.5")),
			OverUnderTeamDto.of("1610612758", "Kings", new BigDecimal("37.5")),
			OverUnderTeamDto.of("1610612762", "Jazz", new BigDecimal("53.5")),
			OverUnderTeamDto.of("1610612737", "Hawks", new BigDecimal("33.5")),
			OverUnderTeamDto.of("1610612750", "Timberwolves", new BigDecimal("35.5")),
			OverUnderTeamDto.of("1610612759", "Spurs", new BigDecimal("46.5")),
			OverUnderTeamDto.of("1610612766", "Hornets", new BigDecimal("23.5")),
			OverUnderTeamDto.of("1610612741", "Bulls", new BigDecimal("32.5")),
			OverUnderTeamDto.of("1610612742", "Mavericks", new BigDecimal("40.5"))
	);

	public List<OverUnderTeamDto> list(){
		return OVER_UNDER_LIST;
	}

}
