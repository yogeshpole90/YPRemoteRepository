package seleniumClasses;

import java.util.List;

import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.remote.RemoteWebDriver;

public class dropdown_search {

	public static void main(String[] args) {
		// TODO Auto-generated method stub

		WebDriver driver = new ChromeDriver();
		
		//country
		
		WebElement count1 = driver.findElement(By.xpath("//*[contains(@id, 'select2-countryC-container')]"));
		JavascriptExecutor jse = (JavascriptExecutor) driver;
		jse.executeScript("arguments[0].scrollIntoView({block:'center'})", count1);		

		//	Country dd
		
		// 1.Click on DD.
		driver.findElement(By.id("select2-countryC-container")).click();
		
		//2.dd list start displaying. inspect search and pass sendkeys.
		driver.findElement(By.xpath("//*[contains(@class,'select2-search_')]")).sendKeys("Afghanistan");

		//3. capture all dd in list.
		List<WebElement> allOptions = driver.findElements(By.xpath("//*[contains(@id,'select2-countryC')]/li"));

		//for loop
		for(WebElement option : allOptions)
		{
			//space also counts.
			if(option.getText().equalsIgnoreCase("Afghanistan"))
			{
				option.click();
				break;
			}
		}

		try {

			//city dd
			driver.findElement(By.id("select2-communeCodeC-container")).click();
			driver.findElement(By.id("select2-search__field")).sendKeys("Bassar");

			// All city options 
			List<WebElement> cityList = driver.findElements(By.xpath("//*[contains(@id,'select2-communeCodeC')]/li"));

			// For loop laga ke match karo
			for (int i = 0; i < cityList.size(); i++) {

				String cityText = cityList.get(i).getText();

				if (cityText.equalsIgnoreCase("Bassar")) {
					cityList.get(i).click();
					break;
				}

			}
		}
		catch(Exception e)
		{
			System.out.println(" City Dropdown is unable to select from Drodpown");
		}

	}
}