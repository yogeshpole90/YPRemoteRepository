package PageObjectModel;

import java.util.List;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

public class LoginPage_Fac {
	//constructor
	WebDriver driver;
	public LoginPage_Fac(WebDriver driver) {
		// TODO Auto-generated constructor stub
		this.driver=driver;
		PageFactory.initElements( driver,this);
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
		(txt_username).sendKeys(user);;
	}
	public void setPassword(String pass )
	{
		(txt_password).sendKeys(pass);;
	}
	public void clickLogin( )
	{
		(txt_login).click();
	}



}
