package com.overunderleague.core.overunder;

import org.springframework.stereotype.Service;

import java.math.BigDecimal;
import java.util.Arrays;
import java.util.List;

@Service
public class OverUnderService {

	private static final List<OverUnderTeamDto> OVER_UNDER_LIST = Arrays.asList(
			OverUnderTeamDto.of("1610612754", "Pacers", new BigDecimal("38.5")),
			OverUnderTeamDto.of("1610612749", "Bucks", new BigDecimal("49.5")),
			OverUnderTeamDto.of("1610612740", "Pelicans", new BigDecimal("34")),
			OverUnderTeamDto.of("1610612738", "Celtics", new BigDecimal("45.5")),
			OverUnderTeamDto.of("1610612751", "Nets", new BigDecimal("45")),
			OverUnderTeamDto.of("1610612743", "Nuggets", new BigDecimal("43.5")),
			OverUnderTeamDto.of("1610612763", "Grizzlies", new BigDecimal("32")),
			OverUnderTeamDto.of("1610612748", "Heat", new BigDecimal("43")),
			OverUnderTeamDto.of("1610612760", "Thunder", new BigDecimal("21")),
			OverUnderTeamDto.of("1610612755", "76ers", new BigDecimal("43")),
			OverUnderTeamDto.of("1610612745", "Rockets", new BigDecimal("34.5")),
			OverUnderTeamDto.of("1610612753", "Magic", new BigDecimal("31.5")),
			OverUnderTeamDto.of("1610612765", "Pistons", new BigDecimal("24")),
			OverUnderTeamDto.of("1610612764", "Wizards", new BigDecimal("33.5")),
			OverUnderTeamDto.of("1610612739", "Cavaliers", new BigDecimal("22")),
			OverUnderTeamDto.of("1610612744", "Warriors", new BigDecimal("39")),
			OverUnderTeamDto.of("1610612747", "Lakers", new BigDecimal("48")),
			OverUnderTeamDto.of("1610612752", "Knicks", new BigDecimal("21.5")),
			OverUnderTeamDto.of("1610612756", "Suns", new BigDecimal("38")),
			OverUnderTeamDto.of("1610612757", "Trail Blazers", new BigDecimal("40.5")),
			OverUnderTeamDto.of("1610612761", "Raptors", new BigDecimal("42")),
			OverUnderTeamDto.of("1610612746", "Clippers", new BigDecimal("48")),
			OverUnderTeamDto.of("1610612758", "Kings", new BigDecimal("27.5")),
			OverUnderTeamDto.of("1610612762", "Jazz", new BigDecimal("42")),
			OverUnderTeamDto.of("1610612737", "Hawks", new BigDecimal("35.5")),
			OverUnderTeamDto.of("1610612750", "Timberwolves", new BigDecimal("30")),
			OverUnderTeamDto.of("1610612759", "Spurs", new BigDecimal("31")),
			OverUnderTeamDto.of("1610612766", "Hornets", new BigDecimal("26.5")),
			OverUnderTeamDto.of("1610612741", "Bulls", new BigDecimal("29")),
			OverUnderTeamDto.of("1610612742", "Mavericks", new BigDecimal("42.5"))
	);

	public List<OverUnderTeamDto> list(){
		return OVER_UNDER_LIST;
	}

}
