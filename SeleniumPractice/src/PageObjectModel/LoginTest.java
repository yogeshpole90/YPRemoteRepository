package PageObjectModel;

import java.util.concurrent.TimeUnit;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.testng.Assert;
import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

public class LoginTest {
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
	
	void testLogin()
	{
		LoginPage lp = new LoginPage(driver);
		lp.setUserName("Shelly");
		lp.setPassword("abcd1234");
		lp.clickLogin();
		
		Assert.assertEquals(driver.getTitle(),"Kiya.ai - Lending Solutions" );
		
		
	}
	@AfterClass
	void teardown()
	{
		driver.quit();

	}

}
