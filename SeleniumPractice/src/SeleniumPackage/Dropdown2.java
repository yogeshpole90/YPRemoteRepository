package SeleniumPackage;

import java.util.List;

import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.support.ui.Select;

public class Dropdown2 {

	public static void main(String[] args) throws Exception {
		System.setProperty("webdriver.chrome.driver", "D:\\chromedriver-win64\\chromedriver-win64\\chromedriver.exe");
		WebDriver driver = new ChromeDriver();
		
		driver.manage().window().maximize();
		driver.navigate().to("https://testautomationpractice.blogspot.com/");
		
		Thread.sleep(3000);
		JavascriptExecutor js = (JavascriptExecutor) driver;
		js.executeAsyncScript("window.scrollBy(0,600)");
		
		Thread.sleep(2000);
		
		  WebElement coun = driver.findElement(By.xpath("//select[@id='country']"));
		

			Thread.sleep(5000);
	      Select coun2 = new Select (coun);

			Thread.sleep(2000);
	     List<WebElement> tot = coun2.getOptions();

			Thread.sleep(2000);
		System.out.println(tot);
		for(int i=0;i<tot.size();i++) 
		{
			String ddvalues = tot.get(i).getText();
		
			if(ddvalues.equalsIgnoreCase("India"))
			{
				
				tot.get(i).click();
				
			}
			
		}
		 

	System.out.println("Last line");
		
		
		
	}


}
