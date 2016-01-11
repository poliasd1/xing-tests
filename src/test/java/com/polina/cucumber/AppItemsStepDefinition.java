package com.polina.cucumber;

import org.junit.Assert;
import org.openqa.selenium.Alert;
import org.openqa.selenium.By;
import org.openqa.selenium.Keys;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.firefox.FirefoxDriver;

import cucumber.api.java.After;
import cucumber.api.java.Before;
import cucumber.api.java.en.Given;
import cucumber.api.java.en.Then;
import cucumber.api.java.en.When;

public class AppItemsStepDefinition extends BaseTests {
	
	
	private String itemName ;
	private String itemDescription;
	
	public String getItemDescription() {
		return itemDescription;
	}

	public void setItemDescription(String itemDescription) {
		this.itemDescription = itemDescription;
	}

	public String getItemName() {
		return itemName;
	}

	public void setItemName(String itemName) {
		this.itemName = itemName;
	}

	@Before public void setup() 
	{ 
		openApp();
	} 
	
		
	@Given("^I click on new item$") 
	public void addNewItem() { 
		//Write term in google textbox 
		WebElement newItemLink = getElement(By.linkText("New Item"));
		newItemLink.click();
	}
	
	
	@When("^I enter name \"([^\"]*)\"$")
	public void enterItemName(String itemName) throws InterruptedException{
		setItemName(itemName);
		WebElement itemNameTextBox = getElement(By.id("item_name"));
		itemNameTextBox.sendKeys(itemName);
	}
	
	@When("^I enter description \"([^\"]*)\"$")
	public void enterItemDescription(String description){
		setItemDescription(description);
		WebElement itemNameTextBox = getElement(By.id("item_description"));
		itemNameTextBox.sendKeys(description);
	}
	
	@When("^I press create item$")
	public void uItem(){
		WebElement createItemBox = getElement(By.name("commit"));
		createItemBox.click();
	}
	
	@When("^I press update item$")
	public void createItem(){
		WebElement createItemBox = getElement(By.name("commit"));
		createItemBox.click();
	}
	
	@When("^I go Back$")
	public void backToItems(){
	
		WebElement backLink = getElement(By.linkText("Back"));
		backLink.click();
	}
	
	@When("^I click on edit item$") 
	public void editItem() { 
		
		WebElement editItemLink = getElement(By.linkText("Edit"));
		editItemLink.click();
	}
	
	@When("^I click on destroy last item$") 
	public void destroyLastItem() { 
		setItemName(getElement(By.cssSelector("table>tbody tr:last-child>td:nth-child(1)")).getText());
		setItemDescription(getElement(By.cssSelector("table>tbody tr:last-child>td:nth-child(2)")).getText());
		WebElement destroyItemLink = getElement(By.cssSelector("table>tbody tr:last-child>td:nth-child(5)>a"));
		destroyItemLink.click();
		Alert alert=driver.switchTo().alert();
		String alertText = alert.getText();
        System.out.println("Alert data: " + alertText);
        alert.accept();
	}
	
	@When("^I click on show last item$") 
	public void showLastItem() { 
		//Write term in google textbox 
		setItemName(getElement(By.cssSelector("table>tbody tr:last-child>td:nth-child(1)")).getText());
		setItemDescription(getElement(By.cssSelector("table>tbody tr:last-child>td:nth-child(2)")).getText());
		WebElement destroyItemLink = getElement(By.cssSelector("table>tbody tr:last-child>td:nth-child(4)>a"));
		destroyItemLink.click();
	
	}
	
	@Then("^Item exists in main view$")
	public void itemInMainView(){
		String actualItemName = getElement(By.cssSelector("table>tbody tr:last-child>td:nth-child(1)")).getText();
		String actualItemDescription = getElement(By.cssSelector("table>tbody tr:last-child>td:nth-child(2)")).getText();
		Assert.assertEquals("The name doesn't exist in the list", actualItemName, getItemName());
		Assert.assertEquals("The description doesn't exist in the list", actualItemDescription, getItemDescription());
	}
	
	@Then("^Item is created$")
	public void itemExists(){
		WebElement actulItemName = getElement(By.id("notice"));
		Assert.assertEquals("The notice text doesn't appear.", actulItemName.getText().trim(), "Item was successfully created.");
		itemIsShown();
	}
	
	@Then("^Item is updated$")
	public void itemUpdated(){
		WebElement actulItemName = getElement(By.id("notice"));
		Assert.assertEquals("The notice text doesn't appear.", actulItemName.getText().trim(), "Item was successfully updated.");
		itemIsShown();
	}
	
	@Then("^Last items are deleted$")
	public void itemIsdeleted(){
		String actualItemName = getElement(By.cssSelector("table>tbody tr:last-child>td:nth-child(1)")).getText();
		String actualItemDescription = getElement(By.cssSelector("table>tbody tr:last-child>td:nth-child(2)")).getText();
		
		Assert.assertNotEquals("The name doesn't exist in the list", actualItemName, getItemName());
		Assert.assertNotEquals("The description doesn't exist in the list", actualItemDescription, getItemDescription());
	}
	
	@Then("^Item is shown$")
	public void itemIsShown(){
		String bodyText =  getElement(By.tagName("body")).getText();
		Assert.assertTrue("Name doesn't appear", bodyText.contains(getItemName()));
		Assert.assertTrue("Description doesn't appear", bodyText.contains(getItemDescription()));
	}
	
	@Then("^Item \"([^\"]*)\" does not exist$")
	public void itemNotExists(){
		WebElement itemNames = getElement(By.cssSelector("table>tbody td:nth-child(1)y"));
		Assert.assertFalse("The element still exist.", itemNames.getText().contains(getItemName()));
	}
	
	@After 
	public void closeBrowser() {
		driver.quit(); 
	}
	
}
	
