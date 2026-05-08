package com.lcs.automation.utils;

import com.lcs.automation.base.BaseTest;
import com.lcs.automation.config.AppConfig;
import org.openqa.selenium.*;

public class LoginUtil extends BaseTest {

	public static void loginAndNavigate(String caseNo, String tabName) throws Exception {
		driver.get(AppConfig.URL);
		driver.findElement(By.id("loginId")).sendKeys(AppConfig.USERNAME, Keys.TAB);
		driver.findElement(By.id("uiPwd")).sendKeys(AppConfig.PASSWORD, Keys.TAB);
		Thread.sleep(2000);
		driver.findElement(By.id("userLogin")).click();

		// Burger menu → All Case List
		driver.findElement(By.xpath("//*[@class='item-nav']/div")).click();
		driver.findElement(By.xpath("//*[contains(@href,'menuCode=COMMONCOLLECTORLIST')]")).click();

		// Search case & double click
		driver.findElement(By.xpath("//*[@type='search']")).sendKeys(caseNo);
		Thread.sleep(2000);
		WebElement caseEl = driver.findElement(By.xpath("//*[text()='" + caseNo + "']"));
		act.doubleClick(caseEl).build().perform();

		// Navigate to tab
		Thread.sleep(2000);
		WebElement tab = driver.findElement(By.xpath("//a[contains(@href,'" + tabName + "')]"));
		jse.executeScript("arguments[0].scrollIntoView({block:'center',behavior:'smooth'})", tab);
		Thread.sleep(1000);
		tab.click();
		Thread.sleep(2000);

		System.out.println(">> Logged in & Navigated to " + tabName + " (Case: " + caseNo + ")");
	}
}
