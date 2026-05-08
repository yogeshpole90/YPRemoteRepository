package SeleniumPackage;

import java.lang.reflect.Array;
import java.util.List;

import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;

public class Checkbox {

	public static void main(String[] args) throws Exception {


		System.setProperty("webdriver.chrome.driver", "D:\\chromedriver-win64\\chromedriver-win64\\chromedriver.exe");
	WebDriver driver = new ChromeDriver();
	driver.manage().window().maximize();
	
	driver.navigate().to("https://testautomationpractice.blogspot.com/");
	Thread.sleep(3000);
	
	//JS Executor
	JavascriptExecutor js =  (JavascriptExecutor) driver ;
	js.executeScript("window.scrollBy(0,900)");
	
	//locate gender radio box
	List<WebElement> gen = driver.findElements(By.xpath("//div[contains(@class , 'post-body entry-content')]/div[3]/div"));
	System.out.println(gen.size());//working

	Thread.sleep(3000);
	for(WebElement gen1 : gen)
	{
		
		System.out.println(gen1.getText());//working
		
		
	}
	System.out.println("Out off Loop");
	
	Thread.sleep(5000);
	gen.get(1).click();
	
	Thread.sleep(5000);
	System.out.println(gen.get(1).isSelected());
	
	Thread.sleep(2000);
	System.out.println(gen.get(0).isDisplayed());

	System.out.println(gen.get(0).isEnabled());

	//working
	
	/*
	 * 2 Male Female Out off Loop true true false
	 */
	
	//week
	//div[@id='post-body-1307673142697428135']/div[4]/div
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	}

}
