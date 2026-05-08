package Calendar_Package;

import Utility_Package.ServerConfig;

import org.openqa.selenium.*;
import org.openqa.selenium.interactions.Actions;

/**
 * CAL3_Login - Login & Open Case 411
 */
public class CAL3_Login extends CAL2_Setup {

	public void login() throws Exception
	{
		driver.get(Utility_Package.ServerConfig.getActiveServer());
		driver.findElement(By.id("loginId")).sendKeys("Shelly", Keys.TAB);
		driver.findElement(By.id("uiPwd")).sendKeys("abcd1234", Keys.TAB);
		Thread.sleep(2000);
		driver.findElement(By.id("userLogin")).click();

		jse = (JavascriptExecutor) driver;
		act = new Actions(driver);

		driver.findElement(By.xpath("//*[@class='item-nav']/div")).click();
		driver.findElement(By.xpath("//*[contains(@href,'menuCode=COMMONCOLLECTORLIST')]")).click();

		driver.findElement(By.xpath("//*[@type='search']")).sendKeys("411");
		Thread.sleep(2000);
		WebElement case1 = driver.findElement(By.xpath("//*[text()='411']"));
		act.doubleClick(case1).build().perform();
		Thread.sleep(3000);
	}

}

