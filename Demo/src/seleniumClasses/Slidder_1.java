package seleniumClasses;

import java.util.List;
import java.util.concurrent.TimeUnit;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.interactions.Actions;

public class Slidder_1 {
	public static void main(String[] args) throws InterruptedException {
		System.setProperty("webdriver.chrome.driver", "D:\\chromedriver-145\\chromedriver-win64\\chromedriver.exe");
		WebDriver driver = new ChromeDriver();
		driver.manage().window().maximize();
		driver.manage().timeouts().implicitlyWait(10, TimeUnit.SECONDS);
		driver.get("https://demoqa.com/sortable");

		Thread.sleep(2000);

		Actions act = new Actions(driver);

		//list demo-tabpane-list
		List<WebElement> alllist = driver.findElements(By.xpath("//*[@id='demo-tabpane-list']/div/div/div"));
		WebElement post1 = alllist.get(0);
		WebElement post2 = alllist.get(1);
		WebElement post3 = alllist.get(2);
		WebElement post4 = alllist.get(3);
		WebElement post5 = alllist.get(4);
		WebElement post6 = alllist.get(5);

		act.dragAndDrop(post6, post1).build().perform();
		act.dragAndDrop(post6, post2).build().perform();
		act.dragAndDrop(post6, post3).build().perform();
		act.dragAndDrop(post6, post4).build().perform();
		act.dragAndDrop(post6, post5).build().perform();
		act.dragAndDrop(post6, post6).build().perform();
		
		//elements are drag one stepped back after 6 moves to 1st position
		//box = 1st position
		
		
		

		//act.dragAndDrop(post4, post3).build().perform();

		// act.dragAndDrop(post3, post4).build().perform();

		//act.dragAndDrop(post2, post5).build().perform();

		//act.dragAndDrop(post1, post6).build().perform();



	}

}
