package SeleniumPackage;

import java.util.List;
import java.util.concurrent.TimeUnit;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;

public class CheckBox3 {

	public static void main(String[] args) throws Exception {
		System.setProperty("webdriver.chrome.driver", "D:\\chromedriver-win64\\chromedriver-win64\\chromedriver.exe");
		WebDriver driver =new ChromeDriver();
		
		driver.manage().timeouts().implicitlyWait(10,TimeUnit.SECONDS);
		// 

		driver.get("https://testing.qaautomationlabs.com/checkbox.php");
		List<WebElement> ss = driver.findElements(By.xpath("//input[@class='mr-2']/parent::label"));
		System.out.println(ss.size());
	
		
		Thread.sleep(3000);
		ss.isEmpty();
		ss.get(0).click();
		ss.get(1).click();
		Thread.sleep(3000);

		ss.get(2).click();
		Thread.sleep(3000);

		ss.get(3).click();
		System.out.println(ss.get(3).isEnabled());
		
		for(WebElement ss1 : ss)
		{
			System.out.println(ss1.getText());
		}
		
		
		
		
	}

}
