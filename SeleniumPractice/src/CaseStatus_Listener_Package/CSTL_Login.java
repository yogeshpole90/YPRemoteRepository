package CaseStatus_Listener_Package;

import Utility_Package.ReportManager;
import com.aventstack.extentreports.ExtentTest;

import java.util.concurrent.TimeUnit;
import org.openqa.selenium.*;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.interactions.Actions;
import org.testng.asserts.SoftAssert;

public class CSTL_Login {

	public static WebDriver driver;
	public static JavascriptExecutor jse;
	public static Actions act;
	public static SoftAssert sa;
	public static int tcCounter = 1;

	public static void log(String field, String testDesc, String expected, String actual, boolean pass) {
		System.out.println("----------------------------------------------");
		System.out.println("TC_CSTL_" + (tcCounter++) + " | Field: " + field);
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
		System.out.println("TC_CSTL_" + (tcCounter++) + " | Field: " + field);
		System.out.println("  Test     : " + testDesc);
		System.out.println("  Value    : " + value);
		System.out.println("  Status   : \u2139\ufe0f INFO");
		ExtentTest test = ReportManager.getTest();
		if (test != null) test.info("\u2139\ufe0f " + field + " | " + testDesc + " | Value: " + value);
	}

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

	public static void cstLogin() throws Exception {
		if (driver == null) {
			System.setProperty("webdriver.chrome.driver", "D:\\chromedriver-146\\chromedriver-win64\\chromedriver.exe");
			driver = new ChromeDriver();
			driver.manage().window().maximize();
			driver.manage().timeouts().implicitlyWait(15, TimeUnit.SECONDS);
		}
		sa = new SoftAssert();
		driver.get("http://10.10.230.14:8181/lcs-finairoLending-1.0.1/");
		driver.findElement(By.id("loginId")).sendKeys("Shelly", Keys.TAB);
		driver.findElement(By.id("uiPwd")).sendKeys("abcd1234", Keys.TAB);
		Thread.sleep(2000);
		driver.findElement(By.id("userLogin")).click();
		jse = (JavascriptExecutor) driver;
		act = new Actions(driver);
		driver.findElement(By.xpath("//*[@class='item-nav']/div")).click();
		Thread.sleep(1000);
		driver.findElement(By.xpath("//*[contains(@href,'menuCode=COMMONCOLLECTORLIST')]")).click();
		Thread.sleep(2000);
		driver.findElement(By.xpath("//*[@type='search']")).sendKeys("15");
		Thread.sleep(2000);
		WebElement case15 = driver.findElement(By.xpath("//*[text()='15']"));
		act.doubleClick(case15).build().perform();
		Thread.sleep(2000);
		WebElement caseLink = driver.findElement(By.xpath("//a[contains(@href,'activeTab=Account Information')]"));
		jse.executeScript("arguments[0].scrollIntoView({block:'center',behavior:'smooth'})", caseLink);
		Thread.sleep(1000);
		caseLink.click();
		Thread.sleep(2000);
		System.out.println("=================================================");
		System.out.println("CSTL_Login - Logged in & Navigated (Case ID: 15)");
	}
}
