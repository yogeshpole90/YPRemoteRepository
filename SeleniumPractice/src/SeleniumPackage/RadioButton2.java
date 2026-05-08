package SeleniumPackage;

import java.util.List;

import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;

public class RadioButton2 {

	public static void main(String[] args) throws Exception {
		// TODO Auto-generated method stub
		System.setProperty("webdriver.chrome.driver", "D:\\chromedriver-win64\\chromedriver-win64\\chromedriver.exe");
		WebDriver driver = new ChromeDriver();
		driver.manage().window().maximize();
		
		driver.navigate().to("https://testautomationpractice.blogspot.com/");
		
		JavascriptExecutor js =(JavascriptExecutor) driver;
		js.executeScript("window.scrollBy(0,900)");
		
		Thread.sleep(3000);
		List<WebElement> cbox = driver .findElements(By.xpath("//div[@id='post-body-1307673142697428135']/div[4]/div"));

		
		System.out.println(cbox.size());
		
		for(WebElement cbox1 : cbox)
		{
			System.out.print(cbox1.getText());	
			System.out.print(",");
			
		}
		System.out.println("Out");
		
		cbox.get(0).click();
		System.out.println(cbox.get(0).isSelected());
		cbox.get(1).click();
		cbox.get(2).click();
		cbox.get(3).click();
		cbox.get(4).click();
		cbox.get(5).click();
		cbox.get(6).click();
		System.out.println("last");
		System.out.println(cbox.getLast().getText());
		
		System.out.println("first");

		System.out.println(cbox.getFirst().getText());
		
		
		System.out.println(cbox.get(6).isSelected());
		
		
		System.out.println("Stop");

		
		
		
		
		
		
		
		
		
		
		
		
		
		
	}

}
