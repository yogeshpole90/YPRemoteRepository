package pageObjects;

import org.openqa.selenium.Keys;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

public class LoginHomePage extends BasePage{

	//constructor
	public LoginHomePage(WebDriver driver) {
		super(driver);
	}


	//locator Page Object Factory
	@FindBy(xpath = "//input[@id='loginId']")
	WebElement txt_username;

	@FindBy(xpath = "//input[@id='uiPwd']")
	WebElement txt_password;

	@FindBy(xpath = "//button[@id='userLogin']")
	WebElement txt_login;

	//action method
	public void setUserName(String user )
	{
		(txt_username).sendKeys(user);
		txt_username.sendKeys(Keys.TAB);
	}
	public void setPassword(String pass )
	{
		(txt_password).sendKeys(pass);
		txt_password.sendKeys(Keys.TAB);
	}
	public void clickLogin( ) throws InterruptedException
	{
		Thread.sleep(2000);
		(txt_login).click();
	}



}


