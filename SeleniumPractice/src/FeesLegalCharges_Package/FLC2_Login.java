package FeesLegalCharges_Package;

import Utility_Package.ReportManager;
import com.aventstack.extentreports.ExtentTest;

import java.util.concurrent.TimeUnit;
import org.openqa.selenium.*;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.interactions.Actions;
import org.testng.asserts.SoftAssert;
import Utility_Package.ServerConfig;

public class FLC2_Login {

	public static WebDriver driver;
	public static JavascriptExecutor jse;
	public static Actions act;
	public static SoftAssert sa = new SoftAssert();
	public static int tcCounter = 1;

	public static void log(String field, String testDesc, String expected, String actual, boolean pass) {
		System.out.println("----------------------------------------------");
		System.out.println("TC_FLC_" + (tcCounter++) + " | Field: " + field);
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
		System.out.println("TC_FLC_" + (tcCounter++) + " | Field: " + field);
		System.out.println("  Test     : " + testDesc);
		System.out.println("  Value    : " + value);
		System.out.println("  Status   : \u2139\ufe0f INFO");
		ExtentTest test = ReportManager.getTest();
		if (test != null) test.info("\u2139\ufe0f " + field + " | " + testDesc + " | Value: " + value);
	}

	public String getToastMsg() {
		try { Thread.sleep(500);
			WebElement t=driver.findElement(By.xpath("//div[contains(@class,'msg-toast') and contains(@class,'msg-error')]//em"));
			String m=t.getText(); System.out.println(">> ERROR TOAST: "+m); return m;
		} catch(Exception e){return "";}
	}
	public String getSuccessToastMsg() {
		try { Thread.sleep(500);
			WebElement t=driver.findElement(By.xpath("//div[contains(@class,'msg-toast') and contains(@class,'msg-success')]//em"));
			String m=t.getText(); System.out.println(">> SUCCESS TOAST: "+m); return m;
		} catch(Exception e){return "";}
	}

	public void flcLogin() throws Exception
	{
		if(driver == null)
		{
			System.setProperty("webdriver.chrome.driver","D:\\chromedriver-146\\chromedriver-win64\\chromedriver.exe");
			driver = new ChromeDriver();
			driver.manage().window().maximize();
			driver.manage().timeouts().implicitlyWait(10, TimeUnit.SECONDS);
		}

		driver.get(ServerConfig.getActiveServer());
		driver.findElement(By.id("loginId")).sendKeys("Shelly", Keys.TAB);
		driver.findElement(By.id("uiPwd")).sendKeys("abcd1234", Keys.TAB);
		Thread.sleep(1000);
		driver.findElement(By.id("userLogin")).click();

		jse = (JavascriptExecutor) driver;
		act = new Actions(driver);

		// Burger menu → All Case List
		driver.findElement(By.xpath("//*[@class='item-nav']/div")).click();
		Thread.sleep(500);
		driver.findElement(By.xpath("//*[contains(@href,'menuCode=COMMONCOLLECTORLIST')]")).click();
		Thread.sleep(1000);

		// Search case & double click
		driver.findElement(By.xpath("//*[@type='search']")).sendKeys("411");
		Thread.sleep(1000);
		WebElement case1 = driver.findElement(By.xpath("//*[text()='411']"));
		act.doubleClick(case1).build().perform();
		Thread.sleep(1000);

		// Navigate to Legal Process tab
		WebElement legal = driver.findElement(By.xpath("//*[contains(@href,'=Legal Process')]"));
		jse.executeScript("arguments[0].scrollIntoView({block:'center'})", legal);
		Thread.sleep(500);
		legal.click();
		Thread.sleep(1000);

		System.out.println("=================================================");
		System.out.println("FLC2_Login - Logged in & Navigated to Legal Process");
	}
}
