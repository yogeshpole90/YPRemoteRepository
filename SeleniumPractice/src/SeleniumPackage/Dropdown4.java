package SeleniumPackage;

import java.util.List;
import java.util.concurrent.TimeUnit;

import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.support.ui.Select;

public class Dropdown4 {

	public static void main(String[] args) throws Exception {
		// TODO Auto-generated method stub
		System.setProperty("webdriver.chrome.driver","D:\\chromedriver-win64\\chromedriver-win64\\chromedriver.exe" );
         WebDriver driver=new ChromeDriver();
         driver.manage().window().maximize();
         driver.manage().timeouts().implicitlyWait(10, TimeUnit.SECONDS);
        
		driver.get("https://demoqa.com/select-menu");
		
		/*
		 * JavascriptExecutor js = (JavascriptExecutor ) driver;
		 * js.executeAsyncScript("window.scrollBy(0,300)");
		 */
	 WebElement dd = driver.findElement(By.xpath("//select[@id='oldSelectMenu']"));
		
		//all printed from dd
	 //System.out.println("All DD = "+dd.getText());
	
		
		Select dropdown = new Select(dd);
		//System.out.println(dd.isEnabled());
		System.out.println("Select Class started");
		Thread.sleep(2000);
		dropdown.selectByIndex(1);
	//System.out.println("Get options = " + dropdown.getOptions());
	
		
		//2nd selected
		Thread.sleep(2000);
		dropdown.selectByIndex(2);
		System.out.println(dropdown.getFirstSelectedOption().getText());

		//3rd selected
		Thread.sleep(2000);
		dropdown.selectByIndex(4);
		System.out.println("Select Class Ended");
		System.out.println(dropdown.getFirstSelectedOption().getText());

		List<WebElement> ss = dropdown.getAllSelectedOptions();

		for(WebElement ss2 :ss)
		{
			System.out.println("Selected Options are " + ss2.getText());
			//in muliti select use for check which is currenlty selected
		}

		
		
		
	}

}
