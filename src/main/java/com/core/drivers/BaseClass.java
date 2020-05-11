package com.core.drivers;

import java.util.concurrent.TimeUnit;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.remote.DesiredCapabilities;
import org.testng.Assert;
import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Parameters;

import com.core.support.Browser;
import com.core.support.Devices;

public class BaseClass {
	public static WebDriver driver;
	public static String mybrowser;
	public static String mydevice;
	public static String mychromeDriverPaths;
	public static String myappiumchromedriverPath, mytestUrl, mytitle;
	
	public static WebDriver getDriver() {
        return driver;
    }
	
	public static String getBrowser() {
        return mybrowser;
    }
	
	public static String getDevice() {
        return mydevice;
    }
	public static String getchromeDriverPaths() {
        return mychromeDriverPaths;
    }
	
	public static String appiumchromedriverPath() {
        return myappiumchromedriverPath;
    }
	
	public static String getTestUrl() {
		return mytestUrl;
    }
	
	public static String getTitle() {
		return mytitle;
    }
	
	@BeforeClass
	@Parameters({"browser", "device", "chromeDriverPaths", "appiumchromedriverPath", "testUrl", "title"})
	public void instantiateDriver(String browser, String device, String chromeDriverPaths, 
			String appiumchromedriverPath, String testUrl,String title) throws Exception {
		mybrowser = browser;
		mydevice = device;
		mychromeDriverPaths = chromeDriverPaths;
		myappiumchromedriverPath = appiumchromedriverPath;
		mytestUrl = testUrl;
		mytitle = title;
		if(browser.equalsIgnoreCase(Browser.CHROME.name()) && device.equalsIgnoreCase(Devices.Mobile.name())) {
			DesiredCapabilities caps = new DesiredCapabilities();
			caps.setCapability("autoAcceptAlerts", true);
			AndroidWebDriverConfigurator Androidconf = new AndroidWebDriverConfigurator(caps);
			driver = Androidconf.getLocalDriver();
			driver.manage().timeouts().implicitlyWait(10, TimeUnit.SECONDS);
		}
		else if(browser.equalsIgnoreCase(Browser.CHROME.name()) && device.equalsIgnoreCase(Devices.Laptop.name())) {
			DesiredCapabilities caps = new DesiredCapabilities();
			caps.setCapability("autoAcceptAlerts", true);
			ChromeWebDriverConfigurator Chromeconf = new ChromeWebDriverConfigurator(caps);
			driver = Chromeconf.getLocalDriver();
			driver.manage().timeouts().implicitlyWait(10, TimeUnit.SECONDS);
			}
		else {
			Assert.fail("Other test devices and browser not yet supported");
		}
		}
	
	@AfterClass

    public void afterSuite() {

       driver.close();
       driver.quit();

    }
	}

