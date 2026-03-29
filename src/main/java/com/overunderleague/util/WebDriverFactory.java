package com.overunderleague.util;

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
		return createChromeDriver(false);
	}

	public static WebDriver createChromeDriver(boolean headless) {
		log.debug("Setting up ChromeDriver (headless={})...", headless);

		ChromeOptions options = new ChromeOptions();
		if (headless) {
			options.addArguments("--headless=new");
		}

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

		options.addArguments("--disable-gpu", "--no-sandbox", "--disable-setuid-sandbox");
		// Required in Docker: default /dev/shm is 64MB, Chrome crashes without this
		options.addArguments("--disable-dev-shm-usage");
		// Required under gVisor (DigitalOcean App Platform uses runsc):
		// Chrome's zygote/renderer process forking is intercepted by gVisor as container
		// creation, which fails. Running single-process prevents any forking.
		options.addArguments("--single-process", "--no-zygote");
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

