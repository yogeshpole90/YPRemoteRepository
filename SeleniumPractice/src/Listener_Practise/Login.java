package Listener_Practise;

import org.testng.annotations.Listeners;
import org.testng.annotations.Test;
import org.apache.log4j.Priority;
import org.openqa.selenium.By;
import org.openqa.selenium.Keys;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
@Listeners(Extent_Report_1.class)

public class Login {
	WebDriver driver;
	
	@Test(priority = 1)
	public void mathodstart() throws Exception
	{
		System.setProperty("webdriver.chrome.driver","D:\\chromedriver-146\\chromedriver-win64\\chromedriver.exe");
		driver = new ChromeDriver();
		driver.get("http://10.10.230.16:8181/lcs-finairoLending-1.0.1");
		driver.manage().window().maximize();
	}

	@Test(priority = 2)
	public void methodfail() throws Exception
	{
		driver.findElement(By.id("userLogin")).click();
	}

	@Test(dependsOnMethods ="methodpass" , priority  = 3)
	public void methodskip()
	{

		System.out.println("Skipping");

	}

	@Test(priority = 4)
	public void methodpass() throws Exception
	{
		driver.findElement(By.id("loginId")).clear();
		driver.findElement(By.id("loginId")).sendKeys("Shelly",Keys.TAB);
		driver.findElement(By.id("uiPwd")).clear();
		driver.findElement(By.id("uiPwd")).sendKeys("abcd1234",Keys.TAB);
		Thread.sleep(1000);
		driver.findElement(By.id("userLogin")).click();
	}

	@Test(priority = 5)
	public void mathodfinish()
	{
		System.out.println("Finished");
	}
	




}
