package com.overunderleague.integration.clientbridge.nbascraper;

import com.overunderleague.core.standing.NbaStandingsProvider;
import com.overunderleague.core.standing.NbaTeamStanding;
import com.overunderleague.integration.nbascraper.NbaStandingsScraper;
import lombok.RequiredArgsConstructor;

import java.util.List;
import java.util.stream.Collectors;

@RequiredArgsConstructor
public class NbaStandingsScraperProvider implements NbaStandingsProvider {

	private final NbaStandingsScraper nbaStandingsScraper;

	@Override
	public List<NbaTeamStanding> getStandings() {
		return nbaStandingsScraper.scrape()
				.stream()
				.map(NbaStandingsScraperNbaStandingAdapter::new)
				.collect(Collectors.toList());
	}

}
