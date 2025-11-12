package com.overunderleague.integration.nbascraper;

import lombok.extern.slf4j.Slf4j;
import org.openqa.selenium.WebDriver;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.stereotype.Component;

import java.util.List;

@Slf4j
@Component
public class NbaStandingsScraper {

	@Cacheable("nbaStandings")
	public List<NbaStandingsPage.TeamStanding> scrape() {
		log.info("Starting NBA standings scrape...");
		WebDriver driver = WebDriverFactory.createChromeDriver();

		try {
			NbaStandingsPage page = new NbaStandingsPage(driver);

			page.navigate();
			page.waitForPageLoad();
			page.dismissCookieConsent();
			page.waitForContent();
			page.waitForStandings();

			return page.extractStandings();
		} finally {
			driver.quit();
			log.debug("WebDriver closed");
		}
	}

}
