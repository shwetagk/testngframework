package com.basework.qa.application.support;
import java.util.List;

import org.openqa.selenium.*;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import com.core.drivers.BaseClass;


public class SearchResult extends BaseClass{
	
	public List<WebElement> getTable(){
		WebDriverWait wait = new WebDriverWait(driver, 30);
		wait.until(ExpectedConditions.elementToBeClickable(By.xpath("//article")));
		return driver.findElements(By.xpath("//article"));
	}
	}
