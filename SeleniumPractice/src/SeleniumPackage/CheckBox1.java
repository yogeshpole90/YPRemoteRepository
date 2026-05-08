package SeleniumPackage;

import java.util.List;
import java.util.concurrent.TimeUnit;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;

public class CheckBox1 {

	public static void main(String[] args) throws Exception {
		
		System.setProperty("webdriver.chrome.driver","D:\\chromedriver-win64\\chromedriver-win64\\chromedriver.exe" );
		WebDriver driver= new ChromeDriver();
		driver.manage().window().maximize();

        driver.manage().timeouts().implicitlyWait(10, TimeUnit.SECONDS);
       
		driver.get("https://practice.expandtesting.com/checkboxes");
		
		 List<WebElement> cb = driver.findElements(By.xpath("//input[@type='checkbox']"));
		//input[@type='checkbox']
		System.out.println( cb.size());
	Thread.sleep(2000);
	
	cb.get(0).click();
	cb.get(1).click();
	
	System.out.println("Is Slected "+cb.get(1).isSelected());
	
		for(WebElement cb1: cb)
		{
			System.out.println(cb1.getText());
		}
		
	//	driver.quit();


	}

}
