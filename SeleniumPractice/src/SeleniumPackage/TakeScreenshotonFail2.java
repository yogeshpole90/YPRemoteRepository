package SeleniumPackage;

import java.io.IOException;
import java.util.concurrent.TimeUnit;

import org.openqa.selenium.By;
import org.openqa.selenium.Keys;
import org.openqa.selenium.chrome.ChromeDriver;
import org.testng.ITestResult;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.Test;

public class TakeScreenshotonFail2 {

	TakeScreenshotonFail1 t1 = new TakeScreenshotonFail1();

	@Test(priority = 1)
	public void dologin() throws Exception
	{
		System.setProperty("webdriver.chrome.driver","D:\\chromedriver-146\\chromedriver-win64\\chromedriver.exe");
		t1.driver = new ChromeDriver();
		t1.driver.manage().window().maximize();
		t1.driver.manage().timeouts().implicitlyWait(5, TimeUnit.SECONDS);
		t1.driver.get("http://10.10.230.15:8181/lcs-finairoLending-1.0.1/");

		Thread.sleep(2000);
		t1.driver.findElement(By.id("loginId")).sendKeys("Dora");
		t1.driver.findElement(By.id("loginId")).sendKeys(Keys.TAB);
		t1.driver.findElement(By.id("uiPwd")).sendKeys("abcd123");
		t1.driver.findElement(By.id("uiPwd")).sendKeys(Keys.TAB);

		//passing wrong ID...
		Thread.sleep(2000);		
		t1.driver.findElement(By.id("userLogin")).click();
	}
	@Test(priority = 2)
	public void assertionCheck()
	{
		System.out.println("Assertion Checked...");
	}

	@AfterMethod
	public void takescrenShotOnFail(ITestResult result) throws IOException
	{
		t1.captureScreenshot(result);

	}

}
