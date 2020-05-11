package com.core.drivers;

import java.net.MalformedURLException;
import org.openqa.selenium.Platform;
import org.openqa.selenium.Proxy;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.remote.CapabilityType;
import org.openqa.selenium.remote.DesiredCapabilities;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public abstract class WebDriverConfigurator extends BaseClass
{

	protected String browserVersion;
	protected String operatingSystem;
	protected DesiredCapabilities capabilities;
	protected DesiredCapabilities requestedCapabilities;
	protected Proxy proxy;

	private static final Logger logger = LoggerFactory.getLogger(WebDriverConfigurator.class);

	public WebDriverConfigurator(DesiredCapabilities requestedCapabilities)
	{
		this.requestedCapabilities = requestedCapabilities;
		// Set browser version and os
		this.browserVersion = requestedCapabilities.getVersion();
		this.operatingSystem = requestedCapabilities.getCapability("operatingSystem") != null
				? (String) requestedCapabilities.getCapability("operatingSystem") : null; // requestedCapabilities.getPlatform() != null ?
				// requestedCapabilities.getPlatform().toString() :
				// null;

				// Set Proxy (optional)
				Object proxyObj = requestedCapabilities.getCapability(CapabilityType.PROXY);
				this.proxy = proxyObj != null ? (Proxy) proxyObj : null;

				// Create capabilities object to be built and used at WebDriver startup
				this.capabilities = new DesiredCapabilities();
	}

	public abstract String getBrowserName();

	protected abstract void configureDesiredCapabilities();

	protected abstract WebDriver getLocalDriver() throws MalformedURLException;

	private static void addOSAndBrowserVersionCapabilities(DesiredCapabilities settingsObject, String browserVersion, String operatingSystem)
	{
		logger.trace("Setting desired browser version to {}", browserVersion);
		settingsObject.setVersion(browserVersion);

		logger.trace(
				"Disabling selenium RemoteWebDriver automatic screenshots (we will still automatically take screenshots on failure with our own code)");
		settingsObject.setCapability("webdriver.remote.quietExceptions", true);

		if (operatingSystem.equalsIgnoreCase("Win7"))
		{
			logger.trace("Setting desired platform to Windows Vista/Win7");
			settingsObject.setPlatform(Platform.VISTA);
		}
		else if (operatingSystem.equalsIgnoreCase("Mac"))
		{
			logger.trace("Setting desired platform to Mac");
			settingsObject.setPlatform(Platform.MAC);
		}
		else if (operatingSystem.equalsIgnoreCase("Any"))
		{
			logger.trace("Setting desired platform to Any");
			settingsObject.setPlatform(Platform.ANY);
		}
		else
		{
			Platform platform = null;

			try
			{
				platform = Platform.valueOf(operatingSystem.toUpperCase());
				settingsObject.setPlatform(platform);
			}
			catch (IllegalArgumentException ex)
			{
				logger.trace("There was a problem matching the requested operating system with a value of the Selenium Grid platform enumeration.",
						ex);
				logger.warn("Default selenium grid platform selection will be used. Unhandled operating system {}", operatingSystem);
			}
		}
	}
}