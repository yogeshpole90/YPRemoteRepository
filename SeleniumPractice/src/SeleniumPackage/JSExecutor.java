package SeleniumPackage;

import java.util.concurrent.TimeUnit;

import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;

public class JSExecutor {

	public static void main(String[] args) throws Exception {
		// TODO Auto-generated method stub
		System.setProperty("webdriver.chrome.driver", "D:\\chromedriver-win64\\chromedriver-win64\\chromedriver.exe");
         WebDriver driver = new ChromeDriver();
         
         //delete cookies
         driver.manage().deleteAllCookies();
         driver.manage().timeouts().implicitlyWait(10, TimeUnit.SECONDS);
         driver.get("https://demoqa.com/select-menu");
         
         JavascriptExecutor jse =(JavascriptExecutor) driver;
         //scroll down and then up
         Thread.sleep(3000);
         jse.executeScript("window.scrollBy(0,500)");
         Thread.sleep(3000);
         jse.executeScript("window.scrollBy(0,-200)");


	}

}
