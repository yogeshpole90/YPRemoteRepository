package SeleniumPackage;

import java.util.concurrent.TimeUnit;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.interactions.Actions;

public class Resizable1 {

	public static void main(String[] args) throws Exception {
		// TODO Auto-generated method stub
		System.setProperty("webdriver.chrome.driver", "D:\\chromedriver-win64\\chromedriver-win64\\chromedriver.exe");
		WebDriver driver = new ChromeDriver();
		driver.manage().window().maximize();
		driver.manage().timeouts().implicitlyWait(10, TimeUnit.SECONDS);
		driver.get("https://demo.automationtesting.in/Register.html");
		
		//interactions
		WebElement inter1 = driver.findElement(By.xpath("//*[text()='Interactions ']"));
		Actions act = new Actions(driver);
		act.moveToElement(inter1).build().perform();
		
		WebElement resize = driver.findElement(By.xpath("//*[text()='Resizable']"));
		resize.click();
		
		
		//resize
		WebElement expand = driver.findElement(By.xpath("//*[@id='resizable']/div[3]"));
		
		//Hori
		Thread.sleep(2000);
		act.dragAndDropBy(expand, 100, 0).build().perform();
		Thread.sleep(2000);
		act.dragAndDropBy(expand, -100, 0).build().perform();

		//vertical
		Thread.sleep(2000);
		act.dragAndDropBy(expand, 0,100).build().perform();
		Thread.sleep(2000);
		act.dragAndDropBy(expand, 0,-100).build().perform();


		//both
		Thread.sleep(2000);
		act.dragAndDropBy(expand, 100, 100).build().perform();

		
		
		

	}

}
