package SeleniumPackage;

import java.util.List;
import java.util.concurrent.TimeUnit;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.support.ui.Select;

public class Dropdown3 {

	public static void main(String[] args) {
		System.setProperty("webdriver.chrome.driver", "D:\\chromedriver-win64\\chromedriver-win64\\chromedriver.exe");
		 WebDriver driver = new ChromeDriver();
	        driver.manage().window().maximize();

	        driver.manage().timeouts().implicitlyWait(20, TimeUnit.SECONDS);
	        // Open site
	        driver.get("https://the-internet.herokuapp.com/dropdown");

	        // Locate dropdown
	        WebElement dropdownElement = driver.findElement(By.id("dropdown"));

	        // Create Select object
	        Select dropdown = new Select(dropdownElement);

	        // Select by visible text
	       // dropdown.selectByVisibleText("Option 1");

	        // OR select by value
	        // dropdown.selectByValue("2");

	        // OR select by index
	        dropdown.selectByIndex(2);
	

	}

}
