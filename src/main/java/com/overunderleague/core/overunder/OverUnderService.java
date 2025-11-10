package com.overunderleague.core.overunder;

import com.overunderleague.util.Csv;
import jakarta.annotation.PostConstruct;
import org.springframework.core.io.ClassPathResource;
import org.springframework.stereotype.Service;

import java.io.IOException;
import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.Scanner;
import java.util.function.Function;
import java.util.stream.Collectors;

@Service
public class OverUnderService {

	private static final String OVER_UNDER_CSV_PATH = "over-under-2025.csv";

	private List<OverUnderTeamDto> overUnderList;
	private Map<Team, OverUnderTeamDto> overUnderByTeam;

	@PostConstruct
	void loadOverUnderList() {
		List<List<String>> entries = Csv.parse(OVER_UNDER_CSV_PATH);
		if (entries.size() != 30) {
			throw new IllegalStateException(String.format("Incorrect number of teams[%s]", entries.size()));
		}

		this.overUnderList = entries.stream()
				.map(entry -> OverUnderTeamDto.of(Team.valueOf(entry.get(0)), new BigDecimal(entry.get(1))))
				.collect(Collectors.toList());

		this.overUnderByTeam = this.overUnderList.stream()
				.collect(Collectors.toMap(OverUnderTeamDto::getTeam, Function.identity()));
	}

	public List<OverUnderTeamDto> list() {
		return overUnderList;
	}

	public OverUnderTeamDto get(Team team) {
		if (!overUnderByTeam.containsKey(team)) {
			throw new IllegalArgumentException(String.format("Team not found %s", team));
		}
		return overUnderByTeam.get(team);
	}

}
