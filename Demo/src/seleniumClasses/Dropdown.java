package seleniumClasses;

import java.util.List;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.support.ui.Select;

public class Dropdown {

	public static void main(String[] args) throws Exception {
		System.setProperty("webdriver.chrome.driver", "D:\\chromedriver-win64\\chromedriver-win64\\chromedriver.exe");
		WebDriver driver = new ChromeDriver();
		
		driver.manage().window().maximize();
		
		driver.navigate().to("https://www.facebook.com");
		//clicked on sign up button
		driver.findElement(By.xpath("//*[@data-testid='open-registration-form-button']")).click();
		
		Thread.sleep(3000);
		
		 //1st way =mostly used

		//click month dropdown
		  List<WebElement> birthmonth = driver.findElements(By.xpath("//select[@id ='month']/option"));
		System.out.println("Total " + birthmonth.size());
		birthmonth.get(9).click();
		System.out.println(birthmonth.get(1).isEnabled());
		 
		 
		 System.out.println("1st finished");
		 
		//2nd way recomended
		

		//click month dropdown
		 Thread.sleep(5000);
		 WebElement bm = driver.findElement(By.xpath("//select[@id ='month']"));
		
		Select month = new Select(bm); 
		
		//by visibke text
		month.selectByVisibleText("Apr");
		
		//by value
		month.selectByValue("12");
		
		//by index
		month.selectByIndex(10);
		
		//get current value from dd
		System.out.println(month.getFirstSelectedOption().getText());
		
		
		//3rd way - store all dd values in list // used this by shammi
		// WebElement bm = driver.findElement(By.xpath("//select[@id ='month']"));
			//Select month = new Select(bm); 
		List<WebElement> dropdown = month.getOptions();
		System.out.println("total dd values are " + dropdown.size());
		for(int i=0;i<dropdown.size();i++) 
		{
			String ddvalues = dropdown.get(i).getText();
		
			if(ddvalues.equalsIgnoreCase("Aug"))
			{
				
				dropdown.get(i).click();
				
			}
			
		}
		//4th
		System.out.println(month.isMultiple());//false check can we select multi dd
		month.selectByIndex(2);
		month.selectByIndex(5);
		
		
		
		
		
		
		
		
		
		
		
		
		
		
		
		
		
		
		

 
	}

}
