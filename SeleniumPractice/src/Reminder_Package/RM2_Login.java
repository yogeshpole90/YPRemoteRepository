package Reminder_Package;

import Utility_Package.ServerConfig;
import Utility_Package.ReportManager;
import com.aventstack.extentreports.ExtentTest;

import java.util.concurrent.TimeUnit;
import org.openqa.selenium.*;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.interactions.Actions;
import org.testng.asserts.SoftAssert;

public class RM2_Login {

	public static WebDriver driver;
	public static JavascriptExecutor jse;
	public static Actions act;
	public static SoftAssert sa = new SoftAssert();
	public static int tcCounter = 1;

	public static void log(String field, String testDesc, String expected, String actual, boolean pass) {
		System.out.println("----------------------------------------------");
		System.out.println("TC_RM_" + (tcCounter++) + " | Field: " + field);
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
		System.out.println("TC_RM_" + (tcCounter++) + " | Field: " + field);
		System.out.println("  Test     : " + testDesc);
		System.out.println("  Value    : " + value);
		System.out.println("  Status   : \u2139\ufe0f INFO");
		ExtentTest test = ReportManager.getTest();
		if (test != null) test.info("\u2139\ufe0f " + field + " | " + testDesc + " | Value: " + value);
	}

	public String getToastMsg() {
		try {
			WebElement searchBox = driver.findElement(By.xpath("//input[contains(@placeholder,'Search keyword')]"));
			jse.executeScript("arguments[0].scrollIntoView({block:'center'})", searchBox);
			Thread.sleep(300);
			WebElement toast = driver.findElement(By.xpath("//div[contains(@class,'msg-toast') and contains(@class,'msg-error')]//em"));
			String msg = toast.getText();
			System.out.println(">> ERROR TOAST: " + msg);
			return msg;
		} catch (Exception e) { return ""; }
	}

	public String getSuccessToastMsg() {
		try {
			WebElement searchBox = driver.findElement(By.xpath("//input[contains(@placeholder,'Search keyword')]"));
			jse.executeScript("arguments[0].scrollIntoView({block:'center'})", searchBox);
			Thread.sleep(300);
			WebElement toast = driver.findElement(By.xpath("//div[contains(@class,'msg-toast') and contains(@class,'msg-success')]//em"));
			String msg = toast.getText();
			System.out.println(">> SUCCESS TOAST: " + msg);
			return msg;
		} catch (Exception e) { return ""; }
	}

	public static void rmLogin() throws Exception
	{
		if(driver == null)
		{
			System.setProperty("webdriver.chrome.driver","D:\\chromedriver-148\\chromedriver-win64\\chromedriver.exe");
			driver = new ChromeDriver();
			driver.manage().window().maximize();
			driver.manage().timeouts().implicitlyWait(10, TimeUnit.SECONDS);
		}

		driver.get(Utility_Package.ServerConfig.getActiveServer());
		driver.findElement(By.id("loginId")).sendKeys("Dora", Keys.TAB);
		driver.findElement(By.id("uiPwd")).sendKeys("abcd1234", Keys.TAB);
		Thread.sleep(1000);
		driver.findElement(By.id("userLogin")).click();

		jse = (JavascriptExecutor) driver;
		act = new Actions(driver);

		driver.findElement(By.xpath("//*[@class='item-nav']/div")).click();
		Thread.sleep(500);

		driver.findElement(By.xpath("//*[contains(@href,'menuCode=COMMONCOLLECTORLIST')]")).click();
		Thread.sleep(1000);

		driver.findElement(By.xpath("//*[@type='search']")).sendKeys("593");
		Thread.sleep(1000);

		WebElement case593 = driver.findElement(By.xpath("//*[text()='593']"));
		act.doubleClick(case593).build().perform();
		Thread.sleep(1000);

		WebElement commTab = driver.findElement(By.xpath("//a[contains(@href,'activeTab=Communication History')]"));
		jse.executeScript("arguments[0].scrollIntoView({block:'center'})", commTab);
		Thread.sleep(500);
		commTab.click();
		Thread.sleep(1000);

		System.out.println("=================================================");
		System.out.println("RM2_Login - Logged in & Navigated to Communication History (Case ID: 593)");
	}
}

