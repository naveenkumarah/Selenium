package com.naveen.selenium.page;

import org.openqa.jetty.util.TestCase;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.support.PageFactory;
import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

import com.naveen.selenium.page.LoginPage;
import com.naveen.selenium.util.SeleniumUtils;

public class LoginPageTest extends TestCase {
	public LoginPageTest(String testCase) {
		super(testCase);
	}

	private static String driverPath = "";
	private static String appURL = "https://www.linkedin.com";

	private WebDriver driver;
	LoginPage loginPage;

	@BeforeClass
	public void setup() throws Exception {
		String browser = "safari";
		driver = SeleniumUtils.getWebDriver(browser, driverPath);
		driver.get(appURL);
		loginPage = PageFactory.initElements(driver, LoginPage.class);
		
	}

	@Test(priority = 0)
	public void digitarTexto() throws Exception {

		String login = "";
		String pass = "";
		loginPage.login(login, pass);
		// SeleniumUtils.captureScreenshot(driver,
		// "/apps/work/SpringWorkspace/Selenium/src/reports/", "screen");
	}

	@AfterClass
	public void tearDown() throws Exception {
		driver.quit();
	}
}