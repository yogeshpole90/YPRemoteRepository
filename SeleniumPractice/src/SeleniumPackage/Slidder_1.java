package SeleniumPackage;

import java.util.List;
import java.util.concurrent.TimeUnit;

import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.interactions.Actions;

public class Slidder_1 {

	public static void main(String[] args) throws Exception {

		System.setProperty("webdriver.chrome.driver",
				"D:\\chromedriver-win64\\chromedriver-win64\\chromedriver.exe");

		WebDriver driver = new ChromeDriver();
		driver.manage().window().maximize();
		driver.manage().timeouts().implicitlyWait(15, TimeUnit.SECONDS);
		driver.get("https://testautomationpractice.blogspot.com/");
	//	https://www.automationtesting.co.uk/tables.html

		//scroll
		WebElement scroll = driver.findElement(By.xpath("//*[text()='Price range:']"));
		JavascriptExecutor jse = (JavascriptExecutor) driver;
		jse.executeScript("arguments[0].scrollIntoView({block:'center'})", scroll);
		
		Thread.sleep(2000);
		//driver.get("https://testautomationpractice.blogspot.com/");
		List<WebElement> handles = driver.findElements(By.className("ui-slider-handle"));

		WebElement left = handles.get(0);   // 200 wala
		WebElement right = handles.get(1);  // 400 wala

		Actions act = new Actions(driver);

		// thoda right move karo (trial se adjust kar lena)
		act.dragAndDropBy(left, 88, 0).perform();  

		act.dragAndDropBy(right, 70, 0).perform();
		//act.dragAndDropBy(right, 176, 0).perform();

		


	}

}
