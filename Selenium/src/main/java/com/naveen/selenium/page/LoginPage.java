package com.naveen.selenium.page;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.How;

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
     public void login(String login, String password) {
         enterLogin(login);
         enterPassword(password);
         clickLoginButton();
         //return PageFactory.initElements(driver, HomePage.class);
     }
}
