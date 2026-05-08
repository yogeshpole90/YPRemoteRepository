package seleniumClasses;

import java.util.List;
import java.util.concurrent.TimeUnit;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebDriver.Timeouts;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;

public class ImplicitWait {

	public static void main(String[] args) throws Exception {
		System.setProperty("webdriver.chrome.driver","D:\\chromedriver-win64\\chromedriver-win64\\chromedriver.exe" );		
		WebDriver driver = new ChromeDriver();
		driver.manage().window().maximize();
		
		//wait
		driver.manage().timeouts().implicitlyWait(30, TimeUnit.SECONDS);
		
		//go
		driver.get("https://www.google.com");

		driver.findElement(By.xpath("//textarea[@name='q']")).sendKeys("How Stuff Works");
		
		//handle autosuggestions
		 WebElement AllSugg = driver.findElement(By.xpath("//*[@role='listbox']"));
		 List<WebElement> AllSearch = AllSugg.findElements(By.tagName("li"));
         int size = AllSearch.size();
         System.out.println(size);
								
		
		
		for(int i=0; i < AllSearch.size();i++)
		{
			String AllsuggestionList = AllSearch.get(i).getText();
			String FinalResult = "How Stuff Works for Kids";
			
			if(AllsuggestionList.equalsIgnoreCase(FinalResult))
				
			{
				
				AllSearch.get(i).click();
				System.out.println("You Have Selected "+ FinalResult);
				break;
				
			}
			
		}
	
		
	}

}


















