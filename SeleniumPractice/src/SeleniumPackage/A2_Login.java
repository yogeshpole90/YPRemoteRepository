package SeleniumPackage;

import Utility_Package.ServerConfig;

import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.Keys;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.interactions.Actions;

/**
 * A2_Login - Handles Login & Case Navigation
 * 
 * Flow:
 * 1. Open application URL
 * 2. Enter credentials & login
 * 3. Initialize JSExecutor & Actions
 * 4. Open hamburger menu → All Case List
 * 5. Search case "411" & double-click to open
 * 6. Scroll to Police Complaint tab & click
 */
public class A2_Login extends A1_LoginSetup
{

	public void a2login() throws Exception
	{
		// Step 1: Open application URL
		driver.get(Utility_Package.ServerConfig.getActiveServer());

		// Step 2: Enter Username & Password, then click Login
		driver.findElement(By.id("loginId")).sendKeys("Shelly", Keys.TAB);
		driver.findElement(By.id("uiPwd")).sendKeys("abcd1234", Keys.TAB);
		Thread.sleep(2000);
		driver.findElement(By.id("userLogin")).click();

		// Step 3: Initialize JavascriptExecutor & Actions for later use
		jse = (JavascriptExecutor) driver;
		act = new Actions(driver);

		// Step 4: Click hamburger menu → navigate to All Case List
		driver.findElement(By.xpath("//*[@class='item-nav']/div")).click();
		driver.findElement(By.xpath("//*[@id='COLLECTORLIST']/a")).click();

		// Step 5: Search for case "411" & double-click to open it
		driver.findElement(By.xpath("//*[@type='search']")).sendKeys("411");
		Thread.sleep(2000);
		WebElement case1 = driver.findElement(By.xpath("//*[text()='411']"));
		act.doubleClick(case1).build().perform();

		// Step 6: Scroll to Police Complaint tab & click
		Thread.sleep(2000);
		WebElement police = driver.findElement(By.xpath("//a[contains(@href,'Police Complaint')]"));
		jse.executeScript("arguments[0].scrollIntoView(true)", police);
		Thread.sleep(2000);
		police.click();
	}
}

