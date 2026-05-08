package PageObjectModel;

import org.testng.annotations.AfterClass;
import org.testng.annotations.Test;
import org.testng.annotations.BeforeMethod;
import org.testng.AssertJUnit;
import java.util.concurrent.TimeUnit;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.testng.Assert;
import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

public class LoginTest_Fact {

	WebDriver driver;
	
	@BeforeClass
	public void setUp()
	{
		System.setProperty("webdriver.chrome.driver", "D:\\chromedriver-146\\chromedriver-win64\\chromedriver.exe");
		driver = new ChromeDriver();
		driver.manage().timeouts().implicitlyWait(10, TimeUnit.SECONDS);
		driver.manage().window().maximize();
		driver.get("http://10.10.230.16:8181/lcs-finairoLending-1.0.1");

	}
	@Test

	void testLogin() throws InterruptedException
	{
		LoginPage_Fac lp2 = new LoginPage_Fac(driver);
		lp2.setUserName("Shelly");
		lp2.setPassword("abcd1234");
		lp2.clickLogin();

		Thread.sleep(2000);
		AssertJUnit.assertEquals(driver.getTitle(),"Kiya.ai - Lending Solutions" );
		Thread.sleep(2000);

	}
	@AfterClass
	void teardown()
	{
		driver.quit();

	}

}


