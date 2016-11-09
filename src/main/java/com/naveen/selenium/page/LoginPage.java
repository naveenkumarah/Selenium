package com.naveen.selenium.page;

import java.util.Date;
import java.util.NoSuchElementException;
import java.util.concurrent.TimeUnit;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.How;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.FluentWait;
import org.openqa.selenium.support.ui.Wait;

import com.google.common.base.Function;
import com.naveen.selenium.util.SeleniumUtils;

public class LoginPage {
	final WebDriver driver;
	
	@FindBy(id="login-email")
	private  WebElement loginEdit;
	
	@FindBy(id="login-password")
	private  WebElement passwordEdit;
	
	@FindBy(how = How.NAME, using ="submit")
	private  WebElement loginButton;
	
	public LoginPage(WebDriver driver) {
        this.driver = driver;
    }
/*	 private By getBy(String fieldName) {
	       try {
	            return new Annotations(this.getClass().getDeclaredField(fieldName)).buildBy();
	       } catch (NoSuchFieldException e) { return null; }
	   }
*/
	 public void enterLogin(String login) {
         loginEdit.clear();
         loginEdit.sendKeys(login);
     }
     public void enterPassword(String password) {
    	 passwordEdit.clear();
         passwordEdit.sendKeys(password);
     }
     public void clickLoginButton() {
         loginButton.click();
     }
     public HomePage login(String login, String password){
        enterLogin(login);
         enterPassword(password);
         clickLoginButton();
         System.out.println(new Date());
      //  driver.manage().timeouts().implicitlyWait(10, TimeUnit.SECONDS);
        SeleniumUtils.findElement(driver,By.xpath("//*[@id='account-nav']/ul/li[4]/a"),10);
        /* Wait<WebDriver> wait=new FluentWait<WebDriver>(driver)
        		 .withTimeout(10, TimeUnit.SECONDS)
        		 .pollingEvery(5, TimeUnit.SECONDS)
        		 .ignoring(NoSuchElementException.class);
         WebElement ele=wait.until(new Function<WebDriver,WebElement>(){

			@Override
			public WebElement apply(WebDriver d) {
				return d.findElement(By.xpath("//*[@id='account-nav']/ul/li[4]/a"));
			}
        	 
         }); */
       
         //System.out.println(driver.findElement(By.xpath("//a[contains(text(),'Sign Out')]")));
         return PageFactory.initElements(driver, HomePage.class);
     }
}
