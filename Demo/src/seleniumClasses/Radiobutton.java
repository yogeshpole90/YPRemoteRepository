package seleniumClasses;

import java.util.List;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;

public class Radiobutton {

	public static void main(String[] args) throws Exception {
		
		//chrome browser property
		System.setProperty("webdriver.chrome.driver", "D:\\chromedriver-win64\\chromedriver-win64\\chromedriver.exe");
		
		//launch browser instance
		WebDriver driver =new ChromeDriver();
		
		driver.manage().window().maximize();//max
		
		//after 2 sec,url opens
		Thread.sleep(2000);
		driver.get("https:\\www.facebook.com");
		

		//wait + click Create new acc
		Thread.sleep(2000);
		driver.findElement(By.xpath("//a[@data-testid='open-registration-form-button']")).click();
		
		//if we has multiple element and attribute value is same.then we can use findelements instead of findelement
		
		//1st Approach = not good takes more time.
		Thread.sleep(2000);
		driver.findElement(By.xpath("//input[@value='1']")).click();//Select Female

		Thread.sleep(2000);
		driver.findElement(By.xpath("//input[@value='2']")).click();//Select Male

		Thread.sleep(2000);
		driver.findElement(By.xpath("//input[@value='-1']")).click();//Select Customer
		
		//2nd Approach = by creating list for all WE and then click by indexing
		List<WebElement> radios = driver.findElements(By.xpath("//input[@type='radio']"));
		
		System.out.println("No of WE in List = "+ radios.size());
		
		// isEnable/isDisable/isSelected
		System.out.println(radios.get(0).isEnabled());//clickable or not
		System.out.println(radios.get(1).isDisplayed()); //displaying or not on UI
		System.out.println(radios.get(2).isSelected()); //choosen or not
		
		//wait
		Thread.sleep(2000);
		radios.get(0).click();
		
		System.out.println(radios.get(2).isSelected());//false
		
		
		
		
		
		
		
		
		
		
		
		
		
		
		
		
		
		
		
		
		
		
		
		
		
		
		
		
		
		
		
		
		
		
		
		
		
		
		
		
		
		
		
	}

}
