package pageObjects;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.CacheLookup;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

public class LoginPage {

	public WebDriver ldriver;

	// constructor
	// initiate driver
	public LoginPage(WebDriver rdriver) {
		ldriver = rdriver;
		PageFactory.initElements(rdriver, this);
	}
	
	@FindBy(name = "Email")
	@CacheLookup
	WebElement txtbox_email_name;

	@FindBy(name = "Password")
	@CacheLookup
	WebElement textbox_password_name;

	@FindBy(xpath = "//button[contains(text(),'Log in')]")
	@CacheLookup
	WebElement button_login_xpath;

	@FindBy(xpath = "//a[contains(text(),'Logout')]")
	@CacheLookup
	WebElement link_logout_xpath;
	
	// Enter Email
	public void setEmail(String email) {
		txtbox_email_name.clear();
		txtbox_email_name.sendKeys(email);
	}

	// Enter Password
	public void setPassword(String password) {
		textbox_password_name.clear();
		textbox_password_name.sendKeys(password);
	}

	// Click Login button
	public void clickLogin() {
		button_login_xpath.click();
	}

	// Click Logout
	public void clickLogout() {
		link_logout_xpath.click();
	}
	
}