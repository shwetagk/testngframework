package com.basework.qa.application.support;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import com.core.drivers.BaseClass;


public class SearchBox extends BaseClass{
	
	private WebDriver localdriver;
	private static final By searchButton = By.cssSelector("input[value='Search']");
	private static final By searchTextBox = By.cssSelector("input[id='edit-search']");
	
	public SearchBox()
	{
		localdriver = getDriver();
		
	}
	
	public void clearText(){
		
		localdriver.findElement(searchTextBox).click();
		localdriver.findElement(searchTextBox).sendKeys("");
		localdriver.findElement(searchButton).click();
	}
	public void  setText(String searchText) {
		WebDriverWait wait = new WebDriverWait(localdriver, 10);
		wait.until(ExpectedConditions.elementToBeClickable(searchTextBox));
		localdriver.findElement(searchTextBox).click();
		localdriver.findElement(searchTextBox).sendKeys(searchText);
		localdriver.findElement(searchButton).click();
	}

}
