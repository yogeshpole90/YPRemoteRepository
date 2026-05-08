package seleniumClasses;

import java.util.concurrent.TimeUnit;

import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;

public class DatePicker {

	public static void main(String[] args) throws Exception {
		System.setProperty("webdriver.chrome.driver","D:\\chromedriver-win64\\chromedriver-win64\\chromedriver.exe");
		WebDriver driver = new ChromeDriver();
		driver.manage().window().maximize();
		driver.manage().timeouts().implicitlyWait(5, TimeUnit.SECONDS);
		Thread.sleep(1000);
		driver.get(	"https://testinzo.com/practice-playground/?utm_source=chatgpt.com");
		

		//DatePicker
		 WebElement picker = driver.findElement(By.xpath("//*[text() = 'Date Picker'][1]"));

			Thread.sleep(1000);
			JavascriptExecutor jse = (JavascriptExecutor) driver;
			//jse.executeAsyncScript("window.scrollBy(0,600)");	
			jse.executeScript("arguments[0].scrollIntoView(true);" ,picker);
			WebElement DOB = driver.findElement(By.xpath("//input[@type='date'][1]"));
			Thread.sleep(1000);
			DOB.sendKeys("15/05/2000");
			System.out.println(DOB);
			
			//FileUpload
			Thread.sleep(5000);
			 WebElement upload2 = driver.findElement(By.xpath("//*[text() = 'File Upload'][1]"));
			 jse.executeScript("arguments[0].scrollIntoView(true)", upload2);
			WebElement upload = driver.findElement(By.xpath("//*[@type = 'file'][1]"));		
			//jse.executeScript("arguments[0].scrollIntoView(true);" ,upload);
			upload.sendKeys("C:\\Users\\Yogesh.Pole\\Music\\FNF_Certificate.pdf");

			
			
			
			
			
			
			
			
			
			
		
			

	}

}
