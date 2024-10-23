package com.overunderleague.core.overunder;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.core.io.ClassPathResource;
import org.springframework.core.io.Resource;
import org.springframework.stereotype.Service;

import javax.annotation.PostConstruct;
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

	private static final String OVER_UNDER_CSV_PATH = "over-under-2024.csv";

	private List<OverUnderTeamDto> overUnderList;
	private Map<Team, OverUnderTeamDto> overUnderByTeam;

	@PostConstruct
	void loadOverUnderList() throws IOException {
		List<List<String>> entries = parseOverUnderFile();
		if (entries.size() != 30) {
			throw new IllegalStateException(String.format("Incorrect number of teams[%s]", entries.size()));
		}

		this.overUnderList = entries.stream()
				.map(entry -> OverUnderTeamDto.of(Team.valueOf(entry.get(0)), new BigDecimal(entry.get(1))))
				.collect(Collectors.toList());

		this.overUnderByTeam = this.overUnderList.stream()
				.collect(Collectors.toMap(OverUnderTeamDto::getTeam, Function.identity()));
	}

	private List<List<String>> parseOverUnderFile() throws IOException {
		ClassPathResource classPathResource = new ClassPathResource(OVER_UNDER_CSV_PATH);
		List<List<String>> entries = new ArrayList<>();
		try (Scanner scanner = new Scanner(classPathResource.getInputStream())) {
			while (scanner.hasNextLine()) {
				entries.add(parseCsvLine(scanner.nextLine()));
			}
		}

		return entries;
	}

	private List<String> parseCsvLine(String line) {
		List<String> values = new ArrayList<>();
		try (Scanner rowScanner = new Scanner(line)) {
			rowScanner.useDelimiter(",");
			while (rowScanner.hasNext()) {
				values.add(rowScanner.next());
			}
		}
		return values;
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
