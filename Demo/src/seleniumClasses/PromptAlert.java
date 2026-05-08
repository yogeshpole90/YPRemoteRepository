package seleniumClasses;

import java.util.concurrent.TimeUnit;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;

public class PromptAlert {

	public static void main(String[] args) throws Exception {
		System.setProperty("webdriver.chrome.driver","D:\\chromedriver-win64\\chromedriver-win64\\chromedriver.exe");
		WebDriver driver = new ChromeDriver();
		driver.manage().window().maximize();
		driver.manage().timeouts().implicitlyWait(5, TimeUnit.SECONDS);
		Thread.sleep(1000);
		driver.get(	"https://demo.automationtesting.in/Alerts.html");
		
		Thread.sleep(2000);
		driver.findElement(By.xpath("//a[text() ='Alert with Textbox ']")).click();
		Thread.sleep(2000);
		driver.findElement(By.xpath("//button[@class='btn btn-info']")).click();
		

		Thread.sleep(4000);
		driver.switchTo().alert().sendKeys("i am entering text");
		Thread.sleep(4000);
		driver.switchTo().alert().accept();

		
		
		
		 
		
		
		
		
		
		
		
		
		
		
	}

}
