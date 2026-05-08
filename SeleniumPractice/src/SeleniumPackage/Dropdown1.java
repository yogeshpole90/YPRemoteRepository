package SeleniumPackage;

import java.util.List;

import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;

public class Dropdown1 {

	public static void main(String[] args) throws Exception {
		// TODO Auto-generated method stub
		System.setProperty("webdriver.chrome.driver", "D:\\chromedriver-win64\\chromedriver-win64\\chromedriver.exe");
		WebDriver driver = new ChromeDriver();
		driver.manage().window().maximize();
		driver.navigate().to("https://testautomationpractice.blogspot.com/");
		
		Thread.sleep(3000);
		 JavascriptExecutor js = (JavascriptExecutor)driver;
		js.executeAsyncScript("window.scrollBy(0,700)");
		
		Thread.sleep(50000);

		List<WebElement> dd = driver.findElements(By.xpath("(//select[@id='country'])[1]"));
		
		System.out.println("Total " + dd.size());
		System.out.println(dd.getFirst().getText());
		Thread.sleep(3000);
		System.out.println(dd.getLast().getText());
		Thread.sleep(3000);
		dd.get(4).click();
		System.out.println(dd.get(4).isEnabled());
		System.out.println("Stop");
		Thread.sleep(3000);

		
		
		
		
		
		
		
		
		
		
		
		
	}

}
