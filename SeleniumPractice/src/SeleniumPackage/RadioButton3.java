package SeleniumPackage;

import java.util.List;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;

public class RadioButton3 {

	public static void main(String[] args) {
		System.setProperty("webdriver.chrome.driver", "D:\\chromedriver-win64\\chromedriver-win64\\chromedriver.exe");
		WebDriver driver = new ChromeDriver();
		driver.manage().window().maximize();
		driver.get("https://demoqa.com/radio-button");
	List<WebElement> cb = driver .findElements(By.xpath("//input[@name='like']/following-sibling::label"));
 
	for(WebElement cb1 : cb)
	{
		System.out.println(cb1.getText());
	}
	
	cb.get(0).click();
	cb.get(1).click();
	cb.get(2).click();
	System.out.println("isEnabled "+ cb.get(2).isEnabled());
	System.out.println("isSelected " + cb.get(2).isSelected());

	}

}
