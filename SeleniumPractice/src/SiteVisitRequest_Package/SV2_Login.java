package SiteVisitRequest_Package;

import Utility_Package.ServerConfig;
import Utility_Package.ReportManager;
import com.aventstack.extentreports.ExtentTest;

import java.util.concurrent.TimeUnit;
import org.openqa.selenium.*;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.interactions.Actions;
import org.testng.asserts.SoftAssert;

public class SV2_Login {

	public static WebDriver driver;
	public static JavascriptExecutor jse;
	public static Actions act;
	public static SoftAssert sa = new SoftAssert();
	public static int tcCounter = 1;

	// Log method — Console + ExtentReport + Screenshot on FAIL
	public static void log(String field, String testDesc, String expected, String actual, boolean pass) {
		// 1. Console me print
		System.out.println("----------------------------------------------");
		System.out.println("TC_SV_" + (tcCounter++) + " | Field: " + field);
		System.out.println("  Test     : " + testDesc);
		System.out.println("  Expected : " + expected);
		System.out.println("  Actual   : " + actual);
		System.out.println("  Status   : " + (pass ? "\u2705 PASS" : "\u274c FAIL"));

		// 2. ExtentReport me likho
		ExtentTest test = ReportManager.getTest();
		if (test != null) {
			if (pass) {
				test.pass("\u2705 Field: " + field + " | Test: " + testDesc + " | Expected: " + expected + " | Actual: " + actual);
			} else {
				test.fail("\u274c Field: " + field + " | Test: " + testDesc + " | Expected: " + expected + " | Actual: " + actual);
				// 3. FAIL pe screenshot attach karo
				if (driver != null) {
					ReportManager.attachScreenshot(driver, "FAIL_" + field.replace(" ", "_"));
				}
			}
		}
	}

	// Info log — Console + ExtentReport
	public static void logInfo(String field, String testDesc, String value) {
		System.out.println("----------------------------------------------");
		System.out.println("TC_SV_" + (tcCounter++) + " | Field: " + field);
		System.out.println("  Test     : " + testDesc);
		System.out.println("  Value    : " + value);
		System.out.println("  Status   : \u2139\ufe0f INFO");

		ExtentTest test = ReportManager.getTest();
		if (test != null) {
			test.info("\u2139\ufe0f Field: " + field + " | Test: " + testDesc + " | Value: " + value);
		}
	}

	public static void SVLogin() throws Exception
	{
		if(driver == null)
		{
			System.setProperty("webdriver.chrome.driver","D:\\chromedriver-146\\chromedriver-win64\\chromedriver.exe");
			driver = new ChromeDriver();
			driver.manage().window().maximize();
			driver.manage().timeouts().implicitlyWait(15, TimeUnit.SECONDS);
		}

		driver.get(Utility_Package.ServerConfig.getActiveServer());
		driver.findElement(By.id("loginId")).sendKeys("Shelly", Keys.TAB);
		driver.findElement(By.id("uiPwd")).sendKeys("abcd1234", Keys.TAB);
		Thread.sleep(2000);
		driver.findElement(By.id("userLogin")).click();

		jse = (JavascriptExecutor) driver;
		act = new Actions(driver);

		driver.findElement(By.xpath("//*[@class='item-nav']/div")).click();
		driver.findElement(By.xpath("//*[contains(@href,'menuCode=COMMONCOLLECTORLIST')]")).click();

		driver.findElement(By.xpath("//*[@type='search']")).sendKeys("550");
		Thread.sleep(2000);
		WebElement case1 = driver.findElement(By.xpath("//*[text()='550']"));
		act.doubleClick(case1).build().perform();

		Thread.sleep(2000);
		WebElement legal = driver.findElement(By.xpath("//*[contains(@href,'Follow-Up')]"));
		jse.executeScript("arguments[0].scrollIntoView({block:'center',behavior:'smooth'})", legal);
		Thread.sleep(1000);
		legal.click();
		Thread.sleep(2000);
	}

}
