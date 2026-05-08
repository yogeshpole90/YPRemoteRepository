package seleniumClasses;

import java.util.List;
import java.util.concurrent.TimeUnit;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.interactions.Actions;

public class MouseSimulation2 {

	public static void main(String[] args) throws Exception {
		// TODO Auto-generated method stub
		System.setProperty("webdriver.chrome.driver","D:\\chromedriver-win64\\chromedriver-win64\\chromedriver.exe");
		WebDriver driver = new ChromeDriver();
		driver.manage().window().maximize();
		driver.manage().timeouts().implicitlyWait(10, TimeUnit.SECONDS);
		driver.get("https://www.testautomationcentral.com/demo/drag_and_drop");

		Thread.sleep(3000);
		/*
		 * //switching frame List<WebElement> allframes =
		 * driver.findElements(By.tagName("iframe"));
		 * System.out.println("Total Frames on web page = " + allframes.size());
		 * driver.switchTo().frame(0);
		 */
		
		//will find draggable and droppable WE
		WebElement draggableElement = driver.findElement(By.id("draggable"));
		WebElement droppableElement = driver.findElement(By.id("droppable"));
		
		//use actions class, drag and drop WE
		Thread.sleep(3000);
		Actions act =new Actions(driver);
		act.dragAndDrop(draggableElement, droppableElement).build().perform();
		
		
		
		
		
		

		
		
		
		
		
		
		
		
		

	}

}
