package testCase;

import static org.testng.Assert.fail;

import java.util.concurrent.TimeUnit;

import org.apache.commons.lang3.RandomStringUtils;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.testng.Assert;
import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

import pageObjects.LoginHomePage;
import testBase.BaseClass;

public class TC001_LoginTest extends BaseClass {
	

	@Test
	public void  verifyLogin() throws InterruptedException
	{
		try {
			
		
		logger.debug("debug");
		logger.info("==========Starting TC001===========");
		LoginHomePage logpage = new LoginHomePage(driver);
		logpage.setUserName(p.getProperty("id"));
		logpage.setPassword(p.getProperty("pass"));
		logpage.clickLogin();
		logger.info("Clicked on Login Button");
		
		Thread.sleep(2000);
		Assert.assertEquals(driver.getTitle(), "Kiya.ai - Lending Solutions");
		
		System.out.println(" Randomstring() ========");
		System.out.println(Randomstring().toUpperCase());
		System.out.println(Randomstring().toLowerCase()+"@gmail.com");

		System.out.println(" Randomnumber() ========");
		 System.out.println(Randomnumber());
		 
		 System.out.println("randomAlphanum()=======");
		 System.out.println(randomAlphanum());
		 
		 }
		catch(Exception e)
		{
			logger.error("test failed");
			logger.debug("debug logs");
			Assert.fail();

			
		}

	}
	

}
