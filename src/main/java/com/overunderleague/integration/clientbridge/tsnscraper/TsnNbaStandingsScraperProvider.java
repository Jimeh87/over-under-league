package com.overunderleague.integration.clientbridge.tsnscraper;

import com.overunderleague.core.standing.NbaStandingsProvider;
import com.overunderleague.core.standing.NbaTeamStanding;
import com.overunderleague.integration.tsnscraper.TsnNbaStandingsScraper;
import lombok.RequiredArgsConstructor;

import java.util.List;
import java.util.stream.Collectors;

@RequiredArgsConstructor
public class TsnNbaStandingsScraperProvider implements NbaStandingsProvider {

	private final TsnNbaStandingsScraper tsnNbaStandingsScraper;

	@Override
	public List<NbaTeamStanding> getStandings() {
		return tsnNbaStandingsScraper.scrape()
				.stream()
				.map(TsnNbaStandingsScraperNbaStandingAdapter::new)
				.collect(Collectors.toList());
	}

}

