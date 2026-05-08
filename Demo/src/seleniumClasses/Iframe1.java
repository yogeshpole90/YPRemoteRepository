package seleniumClasses;

import java.util.concurrent.TimeUnit;

import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;

public class Iframe1 {

	public static void main(String[] args) throws Exception {
		System.setProperty("webdriver.chrome.driver","D:\\chromedriver-win64\\chromedriver-win64\\chromedriver.exe");
		WebDriver driver = new ChromeDriver();
		driver.manage().window().maximize();
		driver.manage().timeouts().implicitlyWait(5, TimeUnit.SECONDS);
		driver.get(	"https://jqueryui.com/switchClass/");

		Thread.sleep(1000);
		JavascriptExecutor jse = (JavascriptExecutor) driver;
		jse.executeScript("window.scrollBy(0,50)");
		
		Thread.sleep(1000);
		int allframe = driver.findElements(By.tagName("iframe")).size();
		System.out.println(allframe);
		
		Thread.sleep(1000);
		for(int i=0;i<allframe;i++)
		{
			driver.switchTo().frame(i);
		driver.findElement(By.xpath("//button[@id='button']")).click();
		}
		
		
		
		
	
	}

}
