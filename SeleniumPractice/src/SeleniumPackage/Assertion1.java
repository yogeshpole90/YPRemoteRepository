package SeleniumPackage;

import java.util.concurrent.TimeUnit;

import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.testng.Assert;
import org.testng.annotations.Test;

public class Assertion1 {

	WebDriver driver ;


	@Test
	public void testcase1() throws Exception
	{

		String expectedvalidation = "No User Found With Entered login id";

		System.setProperty("webdriver.chrome.driver","D:\\chromedriver-146\\chromedriver-win64\\chromedriver.exe");
		driver = new ChromeDriver();
		driver.manage().window().maximize();
		driver.manage().timeouts().implicitlyWait(15, TimeUnit.SECONDS);
		driver.get("http://10.10.230.15:8181/lcs-finairoLending-1.0.1/");

		//use javascript executor for scrolling.
		//JavascriptExecutor jse =(JavascriptExecutor) driver;
		Thread.sleep(2000);
		driver.findElement(By.id("userLogin")).click();
		//
		//inspect the error message
		Thread.sleep(2000);

		String actualvalidation = driver.findElement(By.xpath("//*[contains(text(),'No User Found With Entered login id')]")).getText();
		//True Assertion
		Assert.assertEquals(actualvalidation, expectedvalidation);
		System.out.println("Equal Assertion...");
		
		//give extra letter into expected and check assertion...
		
		//=================False Assertion
		Thread.sleep(2000);
		Assert.assertFalse(driver.findElement(By.xpath("//*[contains(text(),'No User Found With Entered login id')]")).isSelected());
		System.out.println("False Assertion successfully validated...");
		
		//=================True Assertion
		Assert.assertTrue(driver.findElement(By.xpath("//*[contains(text(),'No User Found With Entered login id')]")).isDisplayed());
		System.out.println("True Assertion Successfully validated...");
		
		
		








	}
}
