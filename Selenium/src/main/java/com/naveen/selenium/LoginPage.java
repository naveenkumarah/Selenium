package com.naveen.selenium;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;

import com.naveen.selenium.util.SeleniumUtils;

public class LoginPage {
	private static String driverPath="";
	private static String appURL="https://www.linkedin.com";
	
	public static void main(String[] args) throws Exception {
		String browser="safari";
		WebDriver driver=SeleniumUtils.getWebDriver(browser,driverPath);
		driver.get(appURL);
		SeleniumUtils.clearAndPopulateTextBox(driver, By.id("login-email"), "");
		SeleniumUtils.clearAndPopulateTextBox(driver, By.id("login-password"), "");
		SeleniumUtils.goToTab(driver, By.name("submit"));
		SeleniumUtils.captureScreenshot(driver, "/apps/work/SpringWorkspace/Selenium/src/reports/", "screen");
	}
}
