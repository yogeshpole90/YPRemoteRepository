package com.lcs.automation.base;

import com.lcs.automation.config.AppConfig;
import com.lcs.automation.reporting.ReportManager;
import com.aventstack.extentreports.ExtentTest;

import java.util.concurrent.TimeUnit;
import org.openqa.selenium.*;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.interactions.Actions;
import org.testng.asserts.SoftAssert;

public class BaseTest {

	public static WebDriver driver;
	public static JavascriptExecutor jse;
	public static Actions act;
	public static SoftAssert sa = new SoftAssert();
	public static int tcCounter = 1;

	// Log — Console + Report
	public static void log(String field, String testDesc, String expected, String actual, boolean pass) {
		System.out.println("----------------------------------------------");
		System.out.println("TC_" + (tcCounter++) + " | Field: " + field);
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
			}
		}
	}

	public static void logInfo(String field, String testDesc, String value) {
		System.out.println("----------------------------------------------");
		System.out.println("TC_" + (tcCounter++) + " | Field: " + field);
		System.out.println("  Test     : " + testDesc);
		System.out.println("  Value    : " + value);
		System.out.println("  Status   : \u2139\ufe0f INFO");
		ExtentTest test = ReportManager.getTest();
		if (test != null) test.info("\u2139\ufe0f " + field + " | " + testDesc + " | Value: " + value);
	}

	// Toast capture
	public String getToastMsg() {
		try {
			Thread.sleep(500);
			WebElement toast = driver.findElement(By.cssSelector("div.msg-toast.msg-error.msg-showing em"));
			String msg = toast.getText();
			System.out.println(">> ERROR TOAST: " + msg);
			return msg;
		} catch (Exception e) { return ""; }
	}

	public String getSuccessToastMsg() {
		try {
			Thread.sleep(500);
			WebElement toast = driver.findElement(By.cssSelector("div.msg-toast.msg-success.msg-showing em"));
			String msg = toast.getText();
			System.out.println(">> SUCCESS TOAST: " + msg);
			return msg;
		} catch (Exception e) { return ""; }
	}

	// Browser setup
	public void setupBrowser() {
		if (driver == null) {
			System.setProperty("webdriver.chrome.driver", AppConfig.CHROME_DRIVER);
			driver = new ChromeDriver();
			driver.manage().window().maximize();
			driver.manage().timeouts().implicitlyWait(AppConfig.WAIT_SECONDS, TimeUnit.SECONDS);
			jse = (JavascriptExecutor) driver;
			act = new Actions(driver);
		}
	}

	// Browser close
	public void closeBrowser() {
		if (driver != null) { driver.quit(); driver = null; }
	}
}
