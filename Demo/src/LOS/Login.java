package LOS;

import org.openqa.selenium.By;
import org.openqa.selenium.Keys;
import org.openqa.selenium.WebDriver;

public class Login extends Setup{

	WebDriver driver;
	public Login(WebDriver driver) {
		this.driver = driver;
	}

	public void logined() throws Exception
	{

		driver.get("http://172.21.0.46:8181/lcs-finairoLending-1.0.1");
		driver.findElement(By.id("loginId")).sendKeys("Dora");
		driver.findElement(By.id("loginId")).sendKeys(Keys.TAB);
		driver.findElement(By.id("uiPwd")).sendKeys("abcde@12345");

		driver.findElement(By.id("uiPwd")).sendKeys(Keys.TAB);

		//login clicked
		Thread.sleep(2000);
		driver.findElement(By.id("userLogin")).click();

	}
}
