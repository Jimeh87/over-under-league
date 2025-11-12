package com.overunderleague.integration;

import com.overunderleague.integration.nbascraper.NbaStandingsScraper;
import org.junit.jupiter.api.Disabled;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

@SpringBootTest
class NbaStandingsScraperTest {

	@Autowired
	private NbaStandingsScraper scraper;

	@Disabled
	@Test
	void testIt() {
		System.out.println(scraper.scrape());
	}

}
