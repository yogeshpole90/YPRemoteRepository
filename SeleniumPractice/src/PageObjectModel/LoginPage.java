package PageObjectModel;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;

public class LoginPage {
	//1.constructor = initialize web Driver
	//2.locators
	//3.Action methods

	//1
	WebDriver driver;
	LoginPage(WebDriver driver)
	{

		this.driver=driver;

	}
	//2
	//store in variable = 2 steps
	//From Selectors HUB.
	By txt_username_loc = (By.xpath("//input[@id='loginId']"));
	By txt_password_loc = (By.xpath("//input[@id='uiPwd']"));
	By txt_login_loc = (By.xpath("//button[@id='userLogin']"));

	//3
	//Action methods - call from actual test case
	public void setUserName(String user )
	{
		driver.findElement(txt_username_loc).sendKeys(user);;
	}
	public void setPassword(String pass )
	{
		driver.findElement(txt_password_loc).sendKeys(pass);;
	}
	public void clickLogin( )
	{
		driver.findElement(txt_login_loc).click();
	}

	//Con




}
