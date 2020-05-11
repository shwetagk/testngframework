package com.basework.qa.application.pages;

import org.openqa.selenium.By;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.Assert;

import com.core.drivers.BaseClass;
import com.core.support.Devices;


public class HomeLandingPage extends BaseClass{
	private static final By SearchLink = By.xpath("//a[text()='Search']");
	private static final By OpenMenu = By.xpath("//button[@aria-label = 'Open navigation menu']");
	
	public void accessHomePage() {
		driver.get(getTestUrl());
		Assert.assertEquals(driver.getTitle(), getTitle(), "Home page did not appear");
		driver.findElement(By.xpath("//a[@id='CybotCookiebotDialogBodyLevelButtonAccept']")).click();
		}
	
	public SearchLandingPage switchtoEbookSearchPage()
	{
		if (getDevice()==Devices.Mobile.name())driver.findElement(OpenMenu).click();
		driver.findElement(SearchLink).click();
		WebDriverWait wait = new WebDriverWait(driver, 15);
		wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//article")));
		Assert.assertTrue(driver.getTitle().contains("Search"),"Search page did not load");
		return new SearchLandingPage();
	}

}
