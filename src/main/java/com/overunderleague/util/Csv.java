package com.overunderleague.util;

import lombok.SneakyThrows;
import lombok.experimental.UtilityClass;
import org.springframework.core.io.ClassPathResource;

import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

@UtilityClass
public class Csv {

	@SneakyThrows
	public static List<List<String>> parse(String filePath) {
		ClassPathResource classPathResource = new ClassPathResource(filePath);
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

}
