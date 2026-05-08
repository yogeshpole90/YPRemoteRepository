package SeleniumPackage;

import java.util.concurrent.TimeUnit;

import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.Keys;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;

public class DisableDate {

	public static void main(String[] args) {
		// TODO Auto-generated method stub

		System.setProperty("webdriver.chrome.driver", "D:\\chromedriver-win64\\chromedriver-win64\\chromedriver.exe");
		WebDriver driver = new ChromeDriver();
		driver.manage().window().maximize();
		driver.manage().timeouts().implicitlyWait(10, TimeUnit.SECONDS);
		driver.get("https://demo.automationtesting.in/Datepicker.html");
		//disbale
		
		WebElement date1 = driver.findElement(By.id("datepicker1"));
		date1.sendKeys("12-02-2026");
		JavascriptExecutor jse =(JavascriptExecutor) driver;
		jse.executeScript("arguments[0].value='12-02-2026';", date1);
		date1.sendKeys(Keys.TAB);
		//2nd one
		WebElement date2 = driver.findElement(By.id("datepicker2"));
		date2.sendKeys("12-02-2026");
		date2.sendKeys(Keys.TAB);
		
		

	}

}
