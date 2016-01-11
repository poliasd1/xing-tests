package com.polina.cucumber;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import junit.framework.TestCase;

public class BaseTests {
	
	
	protected WebDriver driver;	
	
	/**
	 * 
	 * Opens the application
	 */
	protected void openApp(){
		driver = new FirefoxDriver(); 
		driver.get("http://localhost:3000");
		driver.manage().window().maximize();
	}
	
	/**
	 * 
	 * Waits for an element to be loaded
	 * @param seleniumElement - Selenium Element
	 * @return Web Element
	 */
	protected WebElement getElement(By seleniumElement){
		WebDriverWait wait = new WebDriverWait(driver, 20);
		wait.until(ExpectedConditions.visibilityOfElementLocated(seleniumElement));
		return driver.findElement(seleniumElement);
	}
	
}
