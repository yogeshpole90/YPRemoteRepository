package RemedialAction;

import Utility_Package.ServerConfig;

import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.Keys;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.interactions.Actions;

/**
 * F3_Login - Login & Navigate to Remedial Action Tab
 * 
 * Flow:
 * 1. Open URL & Login
 * 2. Burger menu → All Case List
 * 3. Search case 411 → Double click
 * 4. Remedial Action tab click
 */
public class F3_Login extends F2_Setup {

	public void login() throws Exception
	{
		// Step 1: Open URL & Login
		driver.get("http://10.10.230.14:8181/lcs-finairoLending-1.0.1");
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

		// Step 5: Scroll to Remedial Action tab & click
		Thread.sleep(2000);
		WebElement remAct = driver.findElement(By.xpath("//a[contains(@href,'Remedial Action')]"));
		jse.executeScript("arguments[0].scrollIntoView({block:'center',behavior:'smooth'})", remAct);
		Thread.sleep(1000);
		remAct.click();
	}

}

