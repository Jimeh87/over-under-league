package com.overunderleague.configuration;

import com.overunderleague.core.standing.NbaStandingsProvider;
import com.overunderleague.integration.clientbridge.tsnscraper.TsnNbaStandingsScraperProvider;
import com.overunderleague.integration.tsnscraper.TsnNbaStandingsScraper;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class NbaStandingsProviderConfiguration {

	@Bean
	public NbaStandingsProvider nbaStandingsProvider(TsnNbaStandingsScraper scraper) {
		return new TsnNbaStandingsScraperProvider(scraper);
	}

}
