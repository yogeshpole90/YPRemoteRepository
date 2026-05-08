package FullPTP_Package;

import Utility_Package.ServerConfig;
import Utility_Package.ReportManager;
import com.aventstack.extentreports.ExtentTest;

import java.util.concurrent.TimeUnit;
import org.openqa.selenium.*;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.interactions.Actions;
import org.testng.asserts.SoftAssert;

public class F2_PTP_Login
{
	public static WebDriver driver;
	public static JavascriptExecutor jse;
	public static Actions act;
	public static SoftAssert sa = new SoftAssert();
	public static int tcCounter = 1;

	public static void log(String field, String testDesc, String expected, String actual, boolean pass) {
		System.out.println("----------------------------------------------");
		System.out.println("TC_PTP_" + (tcCounter++) + " | Field: " + field);
		System.out.println("  Test     : " + testDesc);
		System.out.println("  Expected : " + expected);
		System.out.println("  Actual   : " + actual);
		System.out.println("  Status   : " + (pass ? "\u2705 PASS" : "\u274c FAIL"));
		ExtentTest test = ReportManager.getTest();
		if (test != null) {
			if (pass) {
				test.pass("\u2705 " + field + " | " + testDesc + " | Expected: " + expected + " | Actual: " + actual);
			} else {
				test.fail("\u274c " + field + " | " + testDesc + " | Expected: " + expected + " | Actual: " + actual);
				if (driver != null) ReportManager.attachScreenshot(driver, "FAIL_" + field.replace(" ", "_"));
			}
		}
	}

	public static void logInfo(String field, String testDesc, String value) {
		System.out.println("----------------------------------------------");
		System.out.println("TC_PTP_" + (tcCounter++) + " | Field: " + field);
		System.out.println("  Test     : " + testDesc);
		System.out.println("  Value    : " + value);
		System.out.println("  Status   : \u2139\ufe0f INFO");
		ExtentTest test = ReportManager.getTest();
		if (test != null) test.info("\u2139\ufe0f " + field + " | " + testDesc + " | Value: " + value);
	}

	public void ptpLogin() throws Exception
	{
		if(driver == null)
		{
			System.setProperty("webdriver.chrome.driver","D:\\chromedriver-146\\chromedriver-win64\\chromedriver.exe");
			driver = new ChromeDriver();
			driver.manage().window().maximize();
			driver.manage().timeouts().implicitlyWait(15, TimeUnit.SECONDS);
		}

		// Step 1: Open URL & Login
		driver.get(Utility_Package.ServerConfig.getActiveServer());
		driver.findElement(By.id("loginId")).sendKeys("Shelly", Keys.TAB);
		driver.findElement(By.id("uiPwd")).sendKeys("abcd1234", Keys.TAB);
		Thread.sleep(2000);
		driver.findElement(By.id("userLogin")).click();

		// Step 2: Initialize JSExecutor & Actions
		jse = (JavascriptExecutor) driver;
		act = new Actions(driver);

		// Step 3: Burger menu → All Case List
		driver.findElement(By.xpath("//*[@class='item-nav']/div")).click();
		driver.findElement(By.xpath("//*[contains(@href,'menuCode=COMMONCOLLECTORLIST')]")).click();

		// Step 4: Search case & double click to open
		driver.findElement(By.xpath("//*[@type='search']")).sendKeys("411");
		Thread.sleep(2000);
		WebElement case1 = driver.findElement(By.xpath("//*[text()='411']"));
		act.doubleClick(case1).build().perform();

		// Step 5: Scroll to Remedial tab & click
		Thread.sleep(2000);
		WebElement remedial = driver.findElement(By.xpath("(//*[contains(@href,'=Remedial Action')])[1]"));
		jse.executeScript("arguments[0].scrollIntoView({block:'center',behavior:'smooth'})", remedial);
		Thread.sleep(1000);
		remedial.click();

		// Step 6: Click PTP tab
		Thread.sleep(2000);
		WebElement ptpTab = driver.findElement(By.xpath("//*[contains(text(),'Promise to pay')]"));
		act.doubleClick(ptpTab).build().perform();
		jse.executeScript("window.scrollBy(0,3000)");
	}

}

