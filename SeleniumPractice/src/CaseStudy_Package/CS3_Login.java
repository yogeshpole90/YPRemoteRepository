package CaseStudy_Package;

import Utility_Package.ServerConfig;

import org.openqa.selenium.*;
import org.openqa.selenium.interactions.Actions;

/**
 * CS3_Login - Login & Open Case 411
 * 
 * Flow:
 * 1. Open URL & Login
 * 2. Burger menu → All Case List
 * 3. Search case 411 → Double click
 * (Case Study page loads directly after double click)
 */
public class CS3_Login extends CS2_Setup {

	public void login() throws Exception
	{
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

		// Step 4: Search case 411 & double click to open
		driver.findElement(By.xpath("//*[@type='search']")).sendKeys("411");
		Thread.sleep(2000);
		WebElement case1 = driver.findElement(By.xpath("//*[text()='411']"));
		act.doubleClick(case1).build().perform();
		Thread.sleep(3000);
	}

}

