package SeleniumPackage;

import java.util.concurrent.TimeUnit;
import org.openqa.selenium.*;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.interactions.Actions;
import org.testng.annotations.BeforeClass;
import org.testng.asserts.SoftAssert;

public class A1_LoginSetup 
{

	public static WebDriver driver;  // static → one instance for all
	public static JavascriptExecutor jse;
	public static Actions act;
	public static SoftAssert sa = new SoftAssert();  // static → ek hi sa saare classes share karenge

	@BeforeClass
	public void a1setup() throws Exception 
	{
		if(driver == null)
		{  
			
			// only one browser instance
			System.setProperty("webdriver.chrome.driver","D:\\chromedriver-146\\chromedriver-win64\\chromedriver.exe");
			driver = new ChromeDriver();
			driver.manage().window().maximize();
			driver.manage().timeouts().implicitlyWait(15, TimeUnit.SECONDS);

		}
	}


}
