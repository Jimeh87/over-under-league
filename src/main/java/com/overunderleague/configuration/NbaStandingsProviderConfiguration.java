package com.overunderleague.configuration;

import com.overunderleague.core.standing.NbaStandingsProvider;
import com.overunderleague.integration.NbaStandingsScraper;
import com.overunderleague.integration.clientbridge.nbascraper.NbaStandingsScraperProvider;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class NbaStandingsProviderConfiguration {

	@Bean
	public NbaStandingsProvider nbaStandingsProvider(NbaStandingsScraper scraper) {
		return new NbaStandingsScraperProvider(scraper);
	}

}
