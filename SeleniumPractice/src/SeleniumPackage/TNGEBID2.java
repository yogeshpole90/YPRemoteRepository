package SeleniumPackage;

import java.util.concurrent.TimeUnit;

import org.openqa.selenium.By;
import org.openqa.selenium.Keys;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;

public class TNGEBID2 {
	WebDriver driver;
	
	@BeforeClass
	public void  BClass() throws Exception
	{

	    System.setProperty("webdriver.chrome.driver",
	        "D:\\chromedriver-win64\\chromedriver-win64\\chromedriver.exe");

	    driver = new ChromeDriver();
	    driver.manage().window().maximize();
	    driver.manage().timeouts().implicitlyWait(15, TimeUnit.SECONDS);

	    driver.get("http://172.21.0.46:8181/lcs-finairoLending-1.0.1");

	    Thread.sleep(3000);


	}
	@DataProvider
	public Object[][] Shummy()
	{
		return new Object[][] {
			//user , Pwd
			{"",""},
			{"yogesh","Pass_1"},
			{"Anshuman","Pass_2"},
			{"123", "Pass_3"},
			{"Ab@#$#","Pass_4"},
			{"Shelly","abcde@12345"}

		};
	}
	@Test(dataProvider = "Shummy")
	public void mummy(String user, String pass) throws Exception
	{

		//user id
		WebElement userid = driver.findElement(By.id("loginId"));
		userid.clear();
		userid.sendKeys(user);

		//pwd
		WebElement pwd = driver.findElement(By.id("uiPwd"));
		pwd.clear();
		pwd.sendKeys(pass);

		//click on login
		driver.findElement(By.id("userLogin")).click();
		
		Thread.sleep(2000);
		//clear all
		/*
		 * userid.clear(); pwd.clear();
		 */
		
	}

}
