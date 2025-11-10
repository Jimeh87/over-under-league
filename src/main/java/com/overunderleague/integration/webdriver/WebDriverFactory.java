package com.overunderleague.integration.webdriver;

import io.github.bonigarcia.wdm.WebDriverManager;
import lombok.extern.slf4j.Slf4j;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;

import java.time.Duration;

@Slf4j
public class WebDriverFactory {
	
	private static final String USER_AGENT = 
		"Mozilla/5.0 (Macintosh; Intel Mac OS X 10_15_7) AppleWebKit/537.36 (KHTML, like Gecko) Chrome/120.0.0.0 Safari/537.36";
	
	public static WebDriver createChromeDriver() {
		log.debug("Setting up ChromeDriver...");
		WebDriverManager.chromedriver().setup();
		log.debug("ChromeDriver setup complete");
		
		ChromeOptions options = new ChromeOptions();
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

