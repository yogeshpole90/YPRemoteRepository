package SeleniumPackage;

import java.util.Set;
import java.util.concurrent.TimeUnit;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;

public class Webtables_1 {

	public static void main(String[] args) throws Exception {
		
			
			System.setProperty("webdriver.chrome.driver", "D:\\chromedriver-win64\\chromedriver-win64\\chromedriver.exe");
		WebDriver driver = new ChromeDriver();
		driver.manage().window().maximize();
		driver.manage().timeouts().implicitlyWait(10, TimeUnit.SECONDS);
		driver.get("https://demoqa.com/webtables");
		
		//add
		WebElement add = driver.findElement(By.id("addNewRecordButton"));
		add.click();
		
		Set<String> allhand = driver.getWindowHandles();
		for(String allhan2 : allhand)
		{
			System.out.println(allhan2);
		}
		
		//create new record
		driver.findElement(By.id("firstName")).sendKeys("YYogesh");
		driver.findElement(By.id("lastName")).sendKeys("Pole");

		driver.findElement(By.id("userEmail")).sendKeys("yp@gmail.com");

		driver.findElement(By.id("age")).sendKeys("25");

		driver.findElement(By.id("salary")).sendKeys("25000");

		driver.findElement(By.id("department")).sendKeys("IT");

		driver.findElement(By.id("submit")).click();

		//Update
		driver.findElement(By.xpath("(//*[contains(@class,'action-buttons')])[4]/span[1]")).click();
		
		Thread.sleep(4000);
		driver.findElement(By.id("firstName")).clear();
		driver.findElement(By.id("firstName")).sendKeys("Lokesh");
		driver.findElement(By.id("submit")).click();
		
		//Delete
		Thread.sleep(4000);
		driver.findElement(By.xpath("(//*[contains(@class,'action-buttons')])[4]/span[2]")).click();
		
		

	}

}
