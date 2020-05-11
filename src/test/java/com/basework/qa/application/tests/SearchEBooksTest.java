package com.basework.qa.application.tests;

import com.basework.qa.application.pages.HomeLandingPage;
import com.basework.qa.application.pages.SearchLandingPage;
import com.basework.qa.application.support.CloudFilter;
import com.core.drivers.BaseClass;

import org.testng.Assert;
//import org.openqa.selenium.By;
//import org.openqa.selenium.Keys;
import org.testng.annotations.Test;

public class SearchEBooksTest extends BaseClass{
	private HomeLandingPage  home;
	private SearchLandingPage searchPage;
	private static final boolean DEFAULT_ENABLE= true; //toggle while development
	
	@Test (enabled=DEFAULT_ENABLE, description = "Navigate to Search page")
	public void navigationtoSearchWorks()
	{
		home = new HomeLandingPage();
		home.accessHomePage();
		searchPage = home.switchtoEbookSearchPage();
		
	}
	
	@Test (enabled=DEFAULT_ENABLE, dependsOnMethods = {"navigationtoSearchWorks"}, description = "Navigate to Search page")
	public void searchResultsValid()
	{
		final String testSearchContent = "apart";
		searchPage.search(testSearchContent, CloudFilter.Data);
		Assert.assertTrue(searchPage.getfirstRecord().getText().contains(testSearchContent));
	}
}
