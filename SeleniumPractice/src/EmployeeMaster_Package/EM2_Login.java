package EmployeeMaster_Package;

import Utility_Package.ServerConfig;
import Utility_Package.ReportManager;
import com.aventstack.extentreports.ExtentTest;

import java.util.concurrent.TimeUnit;
import org.openqa.selenium.*;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.asserts.SoftAssert;

public class EM2_Login
{
	public static WebDriver driver;
	public static JavascriptExecutor jse;
	public static Actions act;
	public static SoftAssert sa = new SoftAssert();
	public static int tcCounter = 1;

	public static void log(String field, String testDesc, String expected, String actual, boolean pass) {
		System.out.println("----------------------------------------------");
		System.out.println("TC_EM_" + (tcCounter++) + " | Field: " + field);
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
		System.out.println("TC_EM_" + (tcCounter++) + " | Field: " + field);
		System.out.println("  Test     : " + testDesc);
		System.out.println("  Value    : " + value);
		System.out.println("  Status   : \u2139\ufe0f INFO");
		ExtentTest test = ReportManager.getTest();
		if (test != null) test.info("\u2139\ufe0f " + field + " | " + testDesc + " | Value: " + value);
	}

	public String getToastMsg()
	{
		try {
			Thread.sleep(500);
			WebElement toast = driver.findElement(By.cssSelector("div.msg-toast.msg-error.msg-showing em"));
			String msg = toast.getText();
			System.out.println(">> ERROR TOAST: " + msg);
			return msg;
		} catch (Exception e) {
			return "";
		}
	}

	public void emLogin() throws Exception
	{
		if(driver == null)
		{
			System.setProperty("webdriver.chrome.driver","D:\\chromedriver-148\\chromedriver-win64\\chromedriver.exe");
			driver = new ChromeDriver();
			driver.manage().window().maximize();
			driver.manage().timeouts().implicitlyWait(15, TimeUnit.SECONDS);
		}

		// Step 1: Open URL & Login
		driver.get(Utility_Package.ServerConfig.getActiveServer());
		driver.findElement(By.id("loginId")).sendKeys("infraadmin", Keys.TAB);
		driver.findElement(By.id("uiPwd")).sendKeys("abcd1234", Keys.TAB);
		Thread.sleep(2000);
		driver.findElement(By.id("userLogin")).click();

		// Step 2: Initialize JSExecutor & Actions
		jse = (JavascriptExecutor) driver;
		act = new Actions(driver);

		// Step 3: Burger menu click
		Thread.sleep(2000);
		driver.findElement(By.xpath("//*[@class='item-nav']/div")).click();

		// Step 4: Administration click
		Thread.sleep(1000);
		driver.findElement(By.xpath("//*[@id='Administration']/a")).click();

		// Step 5: User Master click
		Thread.sleep(1000);
		driver.findElement(By.xpath("//*[@id='usermgmtAdm']/a")).click();

		// Step 6: Employee Master click
		Thread.sleep(1000);
		driver.findElement(By.xpath("//*[@id='EMPLOYEEMST']/a")).click();

		// Step 7: Wait until page loads completely
		WebDriverWait wait = new WebDriverWait(driver, 15);
		wait.until(ExpectedConditions.elementToBeClickable(By.xpath("//*[@id='btnAddEmp']")));

		// Step 8: Click Add Employee button (Create Mode)
		driver.findElement(By.xpath("//*[@id='btnAddEmp']")).click();
		Thread.sleep(2000);

		System.out.println("=================================================");
		System.out.println("EM2_Login - Logged in & Opened Employee Master Create Mode");
	}

}

