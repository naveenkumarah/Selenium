package com.naveen.selenium.page;

import java.util.Date;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.How;

public class HomePage {
	final WebDriver driver;
	
	@FindBy(id="main-search-box")
	private  WebElement searchBox;
	
	//
	@FindBy(how = How.XPATH, xpath ="//*[@id='global-search']/fieldset/button")
	private  WebElement searchButton;
	
	@FindBy(how = How.XPATH,xpath="//*[@id='account-nav']/ul/li[4]/a")
	private  WebElement profileMenu;
	
	
	
	@FindBy(how = How.LINK_TEXT, linkText ="Sign Out")
	private  WebElement logoutButton;
	
	public HomePage(WebDriver driver) {
        this.driver = driver;
    }

	public void logout() {
		//searchBox.sendKeys("Shruthi");
		//searchButton.click();
		//
		//Actions actions = new Actions(driver);
		//actions.moveToElement(profileMenu);
		System.out.println(profileMenu.getTagName());
		profileMenu.click();
		  System.out.println(new Date());
		//actions.moveToElement(logoutButton);
		//logoutButton.click();
		
		
	}
}
