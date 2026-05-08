package SeleniumPackage;

import java.util.List;
import java.util.concurrent.TimeUnit;

import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.Keys;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

public class autosugg {

	public static void main(String[] args) throws Exception {

		System.setProperty("webdriver.chrome.driver", "D:\\chromedriver-win64\\chromedriver-win64\\chromedriver.exe");
		WebDriver driver = new ChromeDriver();
		driver.manage().window().maximize();
		driver.manage().timeouts().implicitlyWait(10, TimeUnit.SECONDS);
		driver.get("https://demo.automationtesting.in/AutoComplete.html");
		
		//searchbox
		WebElement auto = driver.findElement(By.xpath("//input[@id='searchbox']"));
		JavascriptExecutor jse = (JavascriptExecutor) driver;
		jse.executeScript("arguments[0].scrollIntoView({block:'center',behavior:'smooth'})", auto);
		Thread.sleep(2000);
		
		//driver.findElement(By.xpath("(//*[contains(@class,'ui-widget')])[1]")).sendKeys("ind");
		
		auto.sendKeys("ind");
		
		//all list
		//first locate serach box ,scroll below and find ul tag for all options.
		List<WebElement> autosug = driver.findElements(By.xpath("(//*[contains(@class,'ui-widget')])[2]/li"));	
		System.out.println("Total :-"+ autosug.size());
		String zero = autosug.get(0).getText();
		String one = autosug.get(1).getText();
		String two = autosug.get(2).getText();
		System.out.println("Dropdown are :- " + zero+" , " + one + " , " + two );

		
		for(int i=0;i<autosug.size();i++)
		{
			 String sugg = autosug.get(i).getText();
			 String finalsrch = "India";
			
			if(sugg.equalsIgnoreCase(finalsrch))
			{
				
				Thread.sleep(2000);
				String SelectedText = autosug.get(i).getText();
				autosug.get(i).click();
				//after click,page refreshes.
				System.out.println("Your Selected Option is :-" + SelectedText);
				break;
				
			}
		}
		//erase selected option.but it should be out of for body.
		//driver.findElement(By.xpath("//*[contains(@class,'close')]")).click();

		//new autosuggestion
		auto.sendKeys("afg");

		WebDriverWait wait = new WebDriverWait(driver, 10);

		wait.until(ExpectedConditions.visibilityOfElementLocated(
		        By.xpath("//ul[contains(@id,'ui-id-1')]/li")));

		List<WebElement> allsug2 = driver.findElements(By.xpath("(//*[contains(@id,'ui-id-1')])/li"));
		System.out.println("Total DD for 2nd search :- " + allsug2.size());
		
		//print all dd option
	
		for(WebElement ele : allsug2)
		{
			String finalsrch2 ="Afghanistan"; 
			if(ele.getText().trim().equalsIgnoreCase(finalsrch2) && !ele.getText().contains(","))
			{
				System.out.println("Selected 2nd DD option is :-"+ ele.getText());
				ele.click();
				break;
				
			}
				
			
				
			
		}
		
		//Afghanistan
	
	
		
		
		
		
	


		
		
		
		
		
		
		
		
		

		

	}

}
