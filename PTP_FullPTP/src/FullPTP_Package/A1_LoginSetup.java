package FullPTP_Package;

import java.util.concurrent.TimeUnit;
import org.openqa.selenium.*;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.interactions.Actions;
import org.testng.annotations.BeforeClass;
import org.testng.asserts.SoftAssert;

/**
 * A1_LoginSetup - Base Class
 * All static → one instance shared across all F classes
 */
public class A1_LoginSetup 
{
	public static WebDriver driver;
	public static JavascriptExecutor jse;
	public static Actions act;
	public static SoftAssert sa = new SoftAssert();

	@BeforeClass
	public void a1setup() throws Exception 
	{
		if(driver == null)
		{
			System.setProperty("webdriver.chrome.driver","D:\\chromedriver-146\\chromedriver-win64\\chromedriver.exe");
			driver = new ChromeDriver();
			driver.manage().window().maximize();
			driver.manage().timeouts().implicitlyWait(15, TimeUnit.SECONDS);
		}
	}
}
