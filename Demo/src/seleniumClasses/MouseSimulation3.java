package seleniumClasses;

import java.util.List;
import java.util.concurrent.TimeUnit;

import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.interactions.Actions;

public class MouseSimulation3 {

	public static void main(String[] args) throws Exception {		
		System.setProperty("webdriver.chrome.driver","D:\\chromedriver-win64\\chromedriver-win64\\chromedriver.exe");
		WebDriver driver = new ChromeDriver();
		driver.manage().window().maximize();
		driver.manage().timeouts().implicitlyWait(10, TimeUnit.SECONDS);
		driver.get(	"https://jqueryui.com/droppable/");


		Thread.sleep(2000);
		JavascriptExecutor jse = (JavascriptExecutor) driver;
		jse.executeScript("window.scrollBy(30,30)");
		
		
		
		Thread.sleep(2000);
		List<WebElement> allframes = driver.findElements(By.tagName("iframe"));
		System.out.println("Total No of frames = "+ allframes.size());
		
		driver.switchTo().frame(0);
		WebElement drag = driver.findElement(By.xpath("//div[@id='draggable']"));
		WebElement drop = driver.findElement(By.xpath("//div[@id='droppable']"));
		
		
		Thread.sleep(2000);
		Actions act =new Actions(driver);		
		//1st mtd, drag one to other element
		//act.dragAndDrop(drag, drop).build().perform();
		
		//actions = class
		//drag and drop = functions
		
		
		//2nd mtd, drag to specific position
		act.dragAndDropBy(drag, 100, 0).build().perform();
		
		
		
		

		
		
	}

}
