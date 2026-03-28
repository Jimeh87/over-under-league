package com.overunderleague.integration.tsnscraper;

import com.overunderleague.util.WebDriverFactory;
import lombok.extern.slf4j.Slf4j;
import org.openqa.selenium.WebDriver;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.stereotype.Component;

import java.util.List;

@Slf4j
@Component
public class TsnNbaStandingsScraper {

	@Value("${app.tsnscraper.headless}")
	private boolean headless;

	@Cacheable("tsnStandings")
	public List<TsnTeamStanding> scrape() {
		log.info("Starting TSN NBA standings scrape (headless={})...", headless);
		WebDriver driver = WebDriverFactory.createChromeDriver(headless);

		try {
			TsnStandingsPage page = new TsnStandingsPage(driver);

			page.navigate();
			page.waitForPageLoad();
			page.waitForStandings();

			List<TsnTeamStanding> standings = page.extractStandings();
			log.info("TSN scrape complete — {} teams found", standings.size());
			return standings;
		} finally {
			driver.quit();
			log.debug("WebDriver closed");
		}
	}
}
