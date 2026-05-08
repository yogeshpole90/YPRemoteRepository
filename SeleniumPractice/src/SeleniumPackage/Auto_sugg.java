package SeleniumPackage;

import java.util.concurrent.TimeUnit;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;

public class Auto_sugg {

	public static void main(String[] args) {
		
		System.setProperty("webdriver.chrome.driver", "D:\\chromedriver-win64\\chromedriver-win64\\chromedriver.exe");
		WebDriver driver = new ChromeDriver();
		driver.manage().window().maximize();
		driver.manage().timeouts().implicitlyWait(10, TimeUnit.SECONDS);
		driver.get("https://demoqa.com/auto-complete");
		
		WebElement multisugg = driver.findElement(By.xpath("(//*[contains(@id,'autoCompleteMultipleInput')])"));
		multisugg.sendKeys("blue");
		driver.findElement(By.xpath("//*[contains(text(),'Blue')]")).click();
		
		multisugg.sendKeys("white");
		driver.findElement(By.xpath("//*[contains(text(),'White')]")).click();

		multisugg.sendKeys("black");
		driver.findElement(By.xpath("//*[contains(text(),'Black')]")).click();

		multisugg.sendKeys("green");
		driver.findElement(By.xpath("//*[contains(text(),'Green')]")).click();

		//*[contains(text(),'White')]
		

	}

}
