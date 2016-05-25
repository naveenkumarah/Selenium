package com.naveen.selenium.util;
import java.io.File;
import java.io.IOException;

import org.apache.commons.io.FileUtils;
import org.openqa.selenium.By;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;
import org.openqa.selenium.TimeoutException;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.safari.SafariDriver;
import org.openqa.selenium.support.ui.ExpectedCondition;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.Select;
import org.openqa.selenium.support.ui.Wait;
import org.openqa.selenium.support.ui.WebDriverWait;


public class SeleniumUtils {
	
	public static WebDriver getWebDriver(String browser,String driverPath) throws Exception{
		if("safari".equalsIgnoreCase(browser)){
			WebDriver driver = new SafariDriver();
			driver.manage().window().maximize();
			return driver;
		}else if("chrome".equalsIgnoreCase(browser)){
			System.setProperty("webdriver.chrome.driver", driverPath+"chromedriver.exe");
			WebDriver driver = new ChromeDriver();
			driver.manage().window().maximize();
		}else if("ie".equalsIgnoreCase(browser)){
			System.setProperty("webdriver.ie.driver", driverPath+"IEdriver.exe");
			WebDriver driver =null;
			driver.manage().window().maximize();
		}else{
			throw new Exception("This Browser not supported:"+browser);
		}
		return null;
		
	}

	public static void populateDropDown(WebDriver driver, By by, String value) {

		Select select = new Select(driver.findElement(by));
		select.selectByValue(value);

	}

	public static ExpectedCondition<WebElement> visibilityOfElementLocated(final By locator) {
		return new ExpectedCondition<WebElement>() {
			public WebElement apply(WebDriver driver) {
				WebElement toReturn = driver.findElement(locator);
				if (toReturn.isDisplayed()) {
					return toReturn;
				}
				return null;
			}
		};
	}

	public static void populateTextBox(WebDriver driver, By by, String value) {
		WebElement inputElement = driver.findElement(by);
		if ("".equals(value)) {
			inputElement.clear();
		} else {
			inputElement.sendKeys(value);
		}
	}
	public static void clearAndPopulateTextBox(WebDriver driver, By by, String value) {
		WebElement inputElement = driver.findElement(by);
		inputElement.clear();
		inputElement.sendKeys(value);
	}

	public static void checkRadio(WebDriver driver, By by) {
		WebElement inputElement = driver.findElement(by);
		inputElement.click();
	}


	public static void goToTab(WebDriver driver, By by) {
		waitUntilClickable(driver, by);
		driver.findElement(by).click();
	}
	
	public static WebElement waitForVisibility(WebDriver driver, By by) {
		return waitForVisibility(driver, by, 45);
	}
	
	public static WebElement waitUntilClickable(WebDriver driver, By by){
		WebDriverWait wait = new WebDriverWait(driver, 45);
		WebElement element = wait.until(ExpectedConditions.elementToBeClickable(by));
		return element;
	}

	public static WebElement waitForVisibility(WebDriver driver, By by, int waitTime) {
		Wait<WebDriver> wait = new WebDriverWait(driver, waitTime);
		WebElement divElement = wait.until(visibilityOfElementLocated(by));
		return divElement;
	}


	public static WebElement switchToNewWindow(WebDriver driver, String iframeId) {
		driver.switchTo().frame(iframeId);
		WebElement window = driver.switchTo().activeElement();
		return window;
	}


	public static WebElement findElement(WebDriver driver, By by, int timeoutInSeconds){
	    try {
			WebDriverWait wait = new WebDriverWait(driver, timeoutInSeconds);
			wait.until(ExpectedConditions.presenceOfElementLocated(by));
		} catch (TimeoutException e) {
			e.printStackTrace();
			return null;
		} 
	    return driver.findElement(by);
	}
	
	public static boolean isEnabled(WebDriver driver, String eachField) {
		return (driver.findElement(By.id(eachField)).isEnabled());
	}

	public static boolean isDisabled(WebDriver driver, String eachField) {
		return (!driver.findElement(By.id(eachField)).isEnabled());
	}
	
	public static boolean isVisible(WebDriver driver, String eachField) {
		return (driver.findElement(By.id(eachField)).isDisplayed());
	}
	
	public static boolean isInvisible(WebDriver driver, String eachField) {
		return (!driver.findElement(By.id(eachField)).isDisplayed());
	}
	/*

	public static String getTextFromId(WebDriver driver, WebElement navigator, String id) {

		//String text = navigator.findElement(By.id(id)).getText();
		System.out.print(id); 
		final WebElement element = findElement(driver, By.id(id),3);
		
		ElementType currentElementType=getCurrentElement(element);
		String text=StringUtils.EMPTY;
		switch (currentElementType) {
		case INPUT:
			text=element.getAttribute("value");
			break;
		case DIV:
			text=element.getText().trim();
			break;
		case TEXTAREA:
			//text=element.getText();
			text =  (String)((JavascriptExecutor) driver).executeScript("return document.getElementById('"+id+"').value","");
			break;
		case SELECT:
			//Select select=new Select(element);
			//text=select.getFirstSelectedOption().getAttribute("value");
			text =  (String)((JavascriptExecutor) driver).executeScript("return document.getElementById('"+id+"').value","");
			break;	
		default:
			break;
		}
		
		System.out.println(" : Text value : "+text);
		return text;
		
	}
	
	private static ElementType getCurrentElement(WebElement element) {
		String tagName=element.getTagName();
		ElementType elementType=null;
		if (StringUtils.equalsIgnoreCase(tagName, Constants.INPUT)){
			elementType=ElementType.INPUT;
		}
		else if (StringUtils.equalsIgnoreCase(tagName, Constants.SELECT)){
			elementType=ElementType.SELECT;
		} 
		else if (StringUtils.equalsIgnoreCase(tagName, Constants.DIV)){
			elementType=ElementType.DIV;
		} 
		else if (StringUtils.equalsIgnoreCase(tagName, Constants.TEXTAREA)){
			elementType=ElementType.TEXTAREA;
		} 
		else{
			logger.error("Unhandled element type : "+element.getTagName());
		}
		
		return elementType;
	}
	
	
	public static  List<Message> checkEnabledFields(WebDriver driver, List<String> enabledFields) {
		System.out.println("Check enabled fields");
		if (enabledFields==null) return ListUtils.EMPTY_LIST;
		List<Message> messages=new ArrayList<Message>();
		boolean result=false;
		for (String eachField: enabledFields) {
			result=isEnabled(driver, eachField);
			messages.add(ValidationReportUtils.constructFieldValidationMessageFromResult(result, Constants.ENABLED, Constants.DISABLED, eachField));
		}
		return messages;
	}

	
	public static  List<Message> checkDisabledFields(WebDriver driver, List<String> fields) {
		if (fields==null) return ListUtils.EMPTY_LIST;
		List<Message> messages=new ArrayList<Message>();
		boolean result=false;
		for (String eachField: fields) {
			result=isDisabled(driver, eachField);
			messages.add(ValidationReportUtils.constructFieldValidationMessageFromResult(result, Constants.DISABLED, Constants.ENABLED, eachField));
		}
		return messages;
		
	}
	
	public static  List<Message> checkVisibleFields(WebDriver driver, List<String> fields) {
		if (fields==null) return ListUtils.EMPTY_LIST;
		List<Message> messages=new ArrayList<Message>();
		boolean result=false;
		for (String eachField: fields) {
			result=isVisible(driver, eachField);
			messages.add(ValidationReportUtils.constructFieldValidationMessageFromResult(result, Constants.VISIBLE, Constants.INVISIBLE, eachField));
		}
		return messages;
		
	}

	public static  List<Message> checkInvisibleFields(WebDriver driver, List<String> fields) {
		if (fields==null) return ListUtils.EMPTY_LIST;
		List<Message> messages=new ArrayList<Message>();
		boolean result=false;
		for (String eachField: fields) {
			result=isInvisible(driver, eachField);
			messages.add(ValidationReportUtils.constructFieldValidationMessageFromResult(result, Constants.INVISIBLE, Constants.VISIBLE, eachField));
		}
		return messages;
		
	}
	*/
	public static String captureScreenshot(WebDriver driver, String folder, String fileName) {
		
		File screenshotFile = ((TakesScreenshot)driver).getScreenshotAs(OutputType.FILE);
		File targetFile=new File(folder, fileName+".png");
		try {
			FileUtils.copyFile(screenshotFile,targetFile );
		} catch (IOException e) {
			e.printStackTrace();
		}

		return targetFile.getAbsolutePath();
	} 
}
