package SeleniumPackage;

import java.util.List;
import java.util.concurrent.TimeUnit;

import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;

public class Page_tableprint {

	public static void main(String[] args) {
		// TODO Auto-generated method stub
		System.setProperty("webdriver.chrome.driver","D:\\chromedriver-win64\\chromedriver-win64\\chromedriver.exe");
		WebDriver driver = new ChromeDriver();
		driver.manage().window().maximize();
		driver.manage().timeouts().implicitlyWait(15, TimeUnit.SECONDS);
		driver.get("https://testautomationpractice.blogspot.com/");
		

		//pagination
		List<WebElement> allpage = driver.findElements(By.xpath("//*[@id='pagination']/li"));
		System.out.println("Total Pages Are :- " + allpage.size());
		
		JavascriptExecutor jse = (JavascriptExecutor) driver;
		jse.executeScript("arguments[0].scrollIntoView({block:'center'})", allpage.get(0));
		for(WebElement allpage1 : allpage)
		{
		System.out.println("pages "+ allpage1.getText());	
		}
		
		
		List<WebElement> alltable = driver.findElements(By.xpath("//table[@id='productTable']"));
		
		List<WebElement> allrow = driver.findElements(By.xpath("//table[@id='productTable']/tbody/tr"));
		//table[@id='productTable']/tbody/tr
		
		for(WebElement allrow1 : allrow)
		{
			System.out.println("Row Data Are  -");
			System.out.println( allrow1.getText());
		}
		
		
		
		List<WebElement> col = driver.findElements(By.xpath("//table[@id='productTable']/thead/tr"));
		System.out.println("Header Size :-" +col.size());
		
		
		for(WebElement col1 : col)
		{
			System.out.println("Column Header are :- "+col1.getText());
		}
		
		
		
		
		
		

	}

}
