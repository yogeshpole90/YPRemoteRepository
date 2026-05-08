package SeleniumPackage;

import java.util.concurrent.TimeUnit;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.interactions.Actions;

public class button_1 {

	public static void main(String[] args) throws Exception {
		// TODO Auto-generated method stub
		
		System.setProperty("webdriver.chrome.driver", "D:\\chromedriver-win64\\chromedriver-win64\\chromedriver.exe");
		WebDriver driver = new ChromeDriver();
		driver.manage().window().maximize();
		driver.manage().timeouts().implicitlyWait(10, TimeUnit.SECONDS);
		driver.get("https://demoqa.com/buttons");

		WebElement dblclick = driver.findElement(By.id("doubleClickBtn"));
		
		Actions act = new Actions(driver);
		Thread.sleep(2000);
		act.doubleClick(dblclick).build().perform();
		
		//rightClickBtn
		Thread.sleep(2000);
		WebElement rtclick = driver.findElement(By.id("rightClickBtn"));
		act.contextClick(rtclick).build().perform();
		
		//P9CU8
		Thread.sleep(2000);
		driver.findElement(By.xpath("//*[text()='Click Me']")).click();

	
	
	

	}

}
