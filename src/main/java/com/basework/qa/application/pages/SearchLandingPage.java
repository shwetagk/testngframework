package com.basework.qa.application.pages;


import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;

import com.basework.qa.application.support.CloudFilter;
import com.basework.qa.application.support.SearchBox;
import com.basework.qa.application.support.SearchResult;
import com.core.drivers.BaseClass;

public class SearchLandingPage extends BaseClass{
	
	private SearchBox sBox;
	private SearchResult searchResult;
	
	public SearchLandingPage(){
		sBox = new SearchBox();
		searchResult = new SearchResult();
	}
	/**
	* This clears the text if present.
	*/	
	public void searchReset()
	{
	sBox.clearText();
	}
	
	/**
	* Search using the provided query string and filter.
	*
	* @param search
	* @param filter
	*/
	public void search(String search, CloudFilter filter)
	{
	//setFilter(filter);
	sBox.setText(search);
	}
	
	
	 /**
	* Open the filter list, select the requested filter if not already selected, and close the filter list.
	*
	* @param filter
	*            do nothing if null
	*/
	private void setFilter(CloudFilter filter)
	{
	if (filter == null)
	return;
	driver.findElement(By.cssSelector("span[.facet-item__value]=" + filter.toString())).click();
	}

	public WebElement getfirstRecord() {
		
		return searchResult.getTable().get(0);
	}
}
