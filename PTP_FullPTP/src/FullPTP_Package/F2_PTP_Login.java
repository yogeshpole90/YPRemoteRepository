package FullPTP_Package;

import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.Keys;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.interactions.Actions;

/**
 * F2_PTP_Login - Login & Navigate to PTP Tab
 * 
 * Flow:
 * 1. Open URL & Login
 * 2. Burger menu → All Case List
 * 3. Search case → Double click
 * 4. Remedial tab → PTP tab click
 */
public class F2_PTP_Login extends A1_LoginSetup
{

	public void ptpLogin() throws Exception
	{
		// Step 1: Open URL & Login
		driver.get("http://10.10.230.14:8181/lcs-finairoLending-1.0.1");
		driver.findElement(By.id("loginId")).sendKeys("<username>", Keys.TAB);
		driver.findElement(By.id("uiPwd")).sendKeys("<password>", Keys.TAB);
		Thread.sleep(2000);
		driver.findElement(By.id("userLogin")).click();

		// Step 2: Initialize JSExecutor & Actions
		jse = (JavascriptExecutor) driver;
		act = new Actions(driver);

		// Step 3: Burger menu → All Case List
		driver.findElement(By.xpath("//*[@class='item-nav']/div")).click();
		driver.findElement(By.xpath("//*[@id='COLLECTORLIST']/a")).click();

		// Step 4: Search case & double click to open
		driver.findElement(By.xpath("//*[@type='search']")).sendKeys("411");
		Thread.sleep(2000);
		WebElement case1 = driver.findElement(By.xpath("//*[text()='411']"));
		act.doubleClick(case1).build().perform();

		// Step 5: Scroll to Remedial tab & click
		Thread.sleep(2000);
		WebElement remedial = driver.findElement(By.xpath("//ul[contains(@class,'border-0')]/li[8]/a"));
		jse.executeScript("arguments[0].scrollIntoView({block:'center',behavior:'smooth'})", remedial);
		Thread.sleep(1000);
		remedial.click();

		// Step 6: Click PTP tab
		Thread.sleep(2000);
		WebElement ptpTab = driver.findElement(By.xpath("//ul[@id='myTab']/li[3]/a"));
		act.doubleClick(ptpTab).build().perform();
		jse.executeScript("window.scrollBy(0,3000)");
	}

}
