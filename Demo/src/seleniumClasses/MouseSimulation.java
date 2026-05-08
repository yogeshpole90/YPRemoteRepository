package seleniumClasses;

import java.util.concurrent.TimeUnit;

import org.openqa.selenium.By;
import org.openqa.selenium.Keys;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.interactions.Actions;

public class MouseSimulation {

	public static void main(String[] args) throws Exception {
		// TODO Auto-generated method stub
		System.setProperty("webdriver.chrome.driver","D:\\chromedriver-win64\\chromedriver-win64\\chromedriver.exe");
		WebDriver driver = new ChromeDriver();
		driver.manage().window().maximize();
		driver.manage().timeouts().implicitlyWait(10, TimeUnit.SECONDS);
		driver.get("https://www.flipkart.com/");
		//https://demoqa.com/menu/

		Thread.sleep(3000);
		WebElement home = driver.findElement(By.xpath("//span[text()='Home & Furniture']"));
WebElement electronics = driver.findElement(By.xpath("//span[text()='Electronics']"));
		
	  //mouse simulation using Actions class
		Actions act = new Actions(driver);

		Thread.sleep(3000);
		//Hover to Home WebElement
		act.moveToElement(home).build().perform();

		//Right click
		//act.contextClick(home).build().perform();
		
		//Release ctrl from home = use after draganddrop
		act.release(home).build().perform();
		
		//perform key events
		Thread.sleep(3000);
		act.sendKeys(Keys.ENTER).build().perform();
		System.out.println("Entered");
		
		//move to element
		Thread.sleep(3000);
		act.moveToElement(electronics).build().perform();
		
		
		
		
	}

}
