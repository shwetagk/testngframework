package com.core.drivers;

import java.net.MalformedURLException;
import java.net.URL;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.remote.DesiredCapabilities;

import com.core.support.Browser;

import io.appium.java_client.AppiumDriver;
import io.appium.java_client.MobileElement;
import io.appium.java_client.remote.MobileCapabilityType;

public class AndroidWebDriverConfigurator extends WebDriverConfigurator{
	AppiumDriver<MobileElement> driver;
	public AndroidWebDriverConfigurator(DesiredCapabilities requestedCapabilities)
	{
		super(requestedCapabilities);
	}
	
	@Override
	public String getBrowserName()
	{
	return "android";
	}

	@Override
	public void configureDesiredCapabilities()
	{
	
	this.capabilities.setCapability("browserName", "android"); // android //iPhone
	this.capabilities.setCapability("platform", "ANDROID"); // ANDROID //MAC
	this.capabilities.setCapability("device", "Samsung Galaxy S5"); // Samsung Galaxy S4 //iPhone 5S
	this.capabilities.setCapability(MobileCapabilityType.PLATFORM_NAME, "ANDROID");
	this.capabilities.setCapability(MobileCapabilityType.PLATFORM_VERSION, "10");
	this.capabilities.setCapability(MobileCapabilityType.DEVICE_NAME, "Pixel3aAPI29");
	this.capabilities.setCapability(MobileCapabilityType.UDID, "emulator-5554");
	this.capabilities.setCapability(MobileCapabilityType.NEW_COMMAND_TIMEOUT, 60);
	//this.capabilities.setCapability(MobileCapabilityType.APP, "");
	this.capabilities.setCapability(MobileCapabilityType.BROWSER_NAME, "CHROME");
	this.capabilities.setCapability("chromedriverExecutable",appiumchromedriverPath());


}

	@Override
	public WebDriver getLocalDriver()  {
		if (this.driver != null) {
			return this.driver;
		}
		this.configureDesiredCapabilities();
		URL url = null;
		try {
			url = new URL("http://127.0.0.1:4723/wd/hub");
		} catch (MalformedURLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		this.driver = new AppiumDriver<MobileElement>(url, this.capabilities);
		return this.driver;
	}
}

