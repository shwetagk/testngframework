package com.core.drivers;

import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.Arrays;
import java.util.HashMap;
import java.util.Map;
import java.util.logging.Level;

import org.openqa.selenium.Dimension;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.logging.LogType;
import org.openqa.selenium.logging.LoggingPreferences;
import org.openqa.selenium.remote.CapabilityType;
import org.openqa.selenium.remote.DesiredCapabilities;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;


public class ChromeWebDriverConfigurator extends WebDriverConfigurator
{
	private static final Logger logger = LoggerFactory.getLogger(ChromeWebDriverConfigurator.class);

	private static final Dimension DEFAULT_WINDOW_DIMENSIONS = new Dimension(1280,1024);

	private static final String[] chromeDriverPaths = {getchromeDriverPaths()};

	public ChromeWebDriverConfigurator(DesiredCapabilities requestedCapabilities)
	{
		super(requestedCapabilities);
	}

	@Override
	public String getBrowserName()
	{
		return "Chrome";
	}

	@Override
	protected void configureDesiredCapabilities()
	{
		this.capabilities = DesiredCapabilities.chrome();
		ChromeOptions options = new ChromeOptions();

		options.addArguments("--window-size=1280,1024");
		options.addArguments("--disable-popup-blocking");

		
		//configure chrome's user data directory for remote export tests only
		boolean export = Boolean.parseBoolean((String) requestedCapabilities.getCapability("export"));
		Map<String, Object> prefs = new HashMap<>();
		prefs.put("credentials_enable_service", false);
		prefs.put("profile.password_manager_enabled", false);
		options.setExperimentalOption("prefs", prefs);

		//Handle mobile emulation (Galen)
		Object mobileEmulation = requestedCapabilities.getCapability("mobileEmulation");
		if (mobileEmulation != null)
			options.setExperimentalOption("mobileEmulation", mobileEmulation);


		//Configure driver so that we can fetch all browser console logs
		LoggingPreferences logPrefs = new LoggingPreferences();
		logPrefs.enable(LogType.BROWSER, Level.ALL);
		this.capabilities.setCapability(CapabilityType.LOGGING_PREFS, logPrefs);
		this.capabilities.setCapability("goog:loggingPrefs", logPrefs);
		options.setCapability(CapabilityType.LOGGING_PREFS, logPrefs);

		this.capabilities.setCapability(ChromeOptions.CAPABILITY, options);
	}
	@Override
	public WebDriver getLocalDriver()
	{
		// Check to see if ChromeDriver path was already set
		String existingDriverPath = System.getProperty("webdriver.chrome.driver");
		// If it wasn't it, set it some other way
		if (existingDriverPath == null)
		{
			String driverPath = Arrays.stream(chromeDriverPaths)
					// Filter down to paths that exist
					.filter(path -> Files.exists(Paths.get(path)))
					// Get the first existing path, or if there are none, throw a RuntimeException
					.findFirst().orElseThrow(() -> new RuntimeException(
							"Could not find ChromeDriver in expected locations.  " +
									"Please either set webdriver.chrome.driver to " +
							"the path or automation.webdrivermanager to true."));

			System.setProperty("webdriver.chrome.driver", driverPath);
			}

		else
		{
			logger.debug("Found existing ChromeDriver path @ {}. Leaving it alone.", existingDriverPath);
		}

		WebDriver driver = new ChromeDriver(capabilities);

		//driver.manage().window().setSize(DEFAULT_WINDOW_DIMENSIONS);

		return driver;
	}
}