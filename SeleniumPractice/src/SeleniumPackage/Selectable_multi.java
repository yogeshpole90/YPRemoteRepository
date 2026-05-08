package SeleniumPackage;

import java.util.concurrent.TimeUnit;

import org.openqa.selenium.By;
import org.openqa.selenium.Keys;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.interactions.Actions;

public class Selectable_multi {

	public static void main(String[] args) throws Exception {
		// TODO Auto-generated method stub

		System.setProperty("webdriver.chrome.driver", "D:\\chromedriver-win64\\chromedriver-win64\\chromedriver.exe");
		WebDriver driver = new ChromeDriver();
		driver.manage().window().maximize();
		driver.manage().timeouts().implicitlyWait(10, TimeUnit.SECONDS);
		driver.get("https://demo.automationtesting.in/Register.html");
		
		//move to ele
		WebElement interact = driver.findElement(By.xpath("//*[text()='Interactions ']"));
		Actions act = new Actions(driver);
		act.moveToElement(interact).build().perform();
		
		//click
		Thread.sleep(2000);
		driver.findElement(By.xpath("(//*[text()='Selectable '])")).click();
		
		//
		WebElement serial = driver.findElement(By.xpath("//*[text()='Serialize ']"));
		serial.click();
		
		WebElement it1 = driver.findElement(By.xpath("//ul[@class='SerializeFunc']/li[1]"));
		WebElement it2 = driver.findElement(By.xpath("//ul[@class='SerializeFunc']/li[2]"));
		WebElement it3 = driver.findElement(By.xpath("//ul[@class='SerializeFunc']/li[3]"));
		WebElement it4 = driver.findElement(By.xpath("//ul[@class='SerializeFunc']/li[4]"));
		
		act.keyDown(Keys.CONTROL).click(it1).click(it2).click(it3).click(it4).keyUp(Keys.CONTROL).build().perform();
		
      //  Thread.sleep(2000);		
		//act.keyDown(Keys.CONTROL).click(it1).click(it2).click(it3).click(it4).keyUp(Keys.CONTROL).build().perform();


	}

}
