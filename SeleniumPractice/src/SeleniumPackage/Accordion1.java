package SeleniumPackage;

import java.util.concurrent.TimeUnit;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;

public class Accordion1 {
	public static void main(String[] args) {

		System.setProperty("webdriver.chrome.driver", "D:\\chromedriver-win64\\chromedriver-win64\\chromedriver.exe");
		WebDriver driver = new ChromeDriver();
		driver.manage().window().maximize();
		driver.manage().timeouts().implicitlyWait(10, TimeUnit.SECONDS);
		driver.get("https://demo.automationtesting.in/Accordion.html");
		
		//2nd one
		WebElement two = driver.findElement(By.xpath("//b[text()='Collapsible Group 2 - Single Line Coding']"));
		two.click();
		
		//4th
		WebElement four = driver.findElement(By.xpath("//b[text()='Collapsible Group 4 - Cross Browser Testing']"));
		four.click();
		
		
		two.click();
		
		
		
	}

}
