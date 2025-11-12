package com.overunderleague.integration.nbascraper;

import io.github.bonigarcia.wdm.WebDriverManager;
import lombok.extern.slf4j.Slf4j;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;

import java.time.Duration;

@Slf4j
public class WebDriverFactory {

	// Crashes anytime I get creative here so leaving it as is
	private static final String USER_AGENT = "Mozilla/5.0 (Macintosh; Intel Mac OS X 10_15_7) AppleWebKit/537.36 (KHTML, like Gecko) Chrome/120.0.0.0 Safari/537.36";
	
	public static WebDriver createChromeDriver() {
		log.debug("Setting up ChromeDriver...");
		
		ChromeOptions options = new ChromeOptions();
		
		String chromeBin = System.getenv("CHROME_BIN");
		if (chromeBin != null && !chromeBin.isEmpty()) {
			options.setBinary(chromeBin);
			log.debug("Using Chrome binary from CHROME_BIN: {}", chromeBin);
		}
		
		String chromedriverPath = System.getenv("CHROMEDRIVER_PATH");
		if (chromedriverPath != null && !chromedriverPath.isEmpty()) {
			System.setProperty("webdriver.chrome.driver", chromedriverPath);
			log.debug("Using ChromeDriver from CHROMEDRIVER_PATH: {}", chromedriverPath);
		} else {
			WebDriverManager.chromedriver().setup();
			log.debug("ChromeDriver setup complete via WebDriverManager");
		}
		
		options.addArguments("--headless", "--disable-gpu", "--no-sandbox");
		options.addArguments("--user-agent=" + USER_AGENT);
		
		WebDriver driver = new ChromeDriver(options);
		log.debug("ChromeDriver initialized");
		
		configureTimeouts(driver);
		
		return driver;
	}
	
	private static void configureTimeouts(WebDriver driver) {
		driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(10));
		driver.manage().timeouts().pageLoadTimeout(Duration.ofSeconds(30));
		driver.manage().timeouts().scriptTimeout(Duration.ofSeconds(30));
	}
}

