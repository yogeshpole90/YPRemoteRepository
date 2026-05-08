package seleniumClasses;

import java.util.List;
import java.util.concurrent.TimeUnit;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;

public class ImplicitWait2 {

	public static void main(String[] args) throws Exception {
		System.setProperty("webdriver.chrome.driver","D:\\chromedriver-win64\\chromedriver-win64\\chromedriver.exe" );		
		WebDriver driver = new ChromeDriver();
		driver.manage().window().maximize();
		
		//wait
		driver.manage().timeouts().implicitlyWait(10, TimeUnit.SECONDS);
		
		//go
		driver.get("https://www.google.com");

		driver.findElement(By.xpath("//textarea[@name='q']")).sendKeys("How Stuff Works");
		
		//handle autosuggestions
		  List<WebElement> AllSugg = driver.findElements(By.xpath("//*[@role='listbox']/li"));
		 System.out.println("Total "+ AllSugg.size());
		 
		 
		 //print
			/*
			 * for(WebElement AllSugg2 : AllSugg) { System.out.println(AllSugg2.getText());
			 * 
			 * }
			 */
			
			
			for(int i=0;i<AllSugg.size();i++)
			{
				String AllSugg2 = AllSugg.get(i).getText();
				String Finalsearch = "how stuff works Science";
			  if(AllSugg2.equalsIgnoreCase(Finalsearch) )
			  {
				  Thread.sleep(3000);
				  AllSugg.get(i).click();
				  System.out.println("You Searched :" + Finalsearch );
				  break;
			  }
			}
		  
		  
								


	}

}
