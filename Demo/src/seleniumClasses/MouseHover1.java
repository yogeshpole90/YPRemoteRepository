package seleniumClasses;

import java.util.concurrent.TimeUnit;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.interactions.Actions;

public class MouseHover1 {

	public static void main(String[] args) {
		// TODO Auto-generated method stub
		System.setProperty("webdriver.chrome.driver","D:\\chromedriver-win64\\chromedriver-win64\\chromedriver.exe");
		WebDriver driver = new ChromeDriver();
		driver.manage().window().maximize();
		driver.manage().timeouts().implicitlyWait(10, TimeUnit.SECONDS);
		driver.get("https://demoqa.com/menu");
		
		Actions act = new Actions(driver);
		WebElement menu3 = driver.findElement(By.xpath("//ul[@id='nav']/li[2]/a"));
		act.moveToElement(menu3).build().perform();
		
		driver.findElement(By.xpath("//li[3]/a[text()='SUB SUB LIST »']")).click();
		
		
		
		

	}

}
