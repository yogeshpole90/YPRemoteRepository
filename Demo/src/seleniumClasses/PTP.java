package seleniumClasses;

import java.util.concurrent.TimeUnit;

import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.Keys;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.interactions.Actions;

public class PTP {

	public static void main(String[] args) throws Exception {
		
		System.setProperty("webdriver.chrome.driver","D:\\chromedriver144\\chromedriver-win64\\chromedriver.exe");
		WebDriver driver = new ChromeDriver();
		driver.manage().window().maximize();
		driver.manage().timeouts().implicitlyWait(15, TimeUnit.SECONDS);
		driver.get("http://172.21.0.46:8181/lcs-finairoLending-1.0.1");
		
		
		
		
		
		
		
		
		
		
		
		
		
		
		
		
		
		
		
		
		driver.findElement(By.id("loginId")).sendKeys("Shelly");
		driver.findElement(By.id("uiPwd")).sendKeys("abcde@12345");

		driver.findElement(By.id("uiPwd")).sendKeys(Keys.TAB);
		
		Thread.sleep(1000);
		driver.findElement(By.id("userLogin")).click();
		
		//burger
		driver.findElement(By.xpath("//*[@class='item-nav']/div")).click();
		
		//Inbox list
		driver.findElement(By.xpath("//*[@id='COLLECTORLIST']/a")).click();
		
		//case
		WebElement case366 = driver.findElement(By.xpath("//*[text()='366']"));
		
		Actions act = new Actions(driver);
		act.doubleClick(case366).build().perform();
		
		//vertical-Menu
		WebElement remaction = driver.findElement(By.xpath("//*[contains(@href , 'Remedial Action')]"));
		JavascriptExecutor jse = (JavascriptExecutor) driver;
		jse.executeScript("arguments[0].scrollIntoView({block:'center'})", remaction);
		remaction.click();
		
		
		
		
		
		
		
		
		
		
		
		
		
		
		
		
		
		
		

	}

}
