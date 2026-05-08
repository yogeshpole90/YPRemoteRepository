package SeleniumPackage;

import java.util.Iterator;
import java.util.Set;
import java.util.concurrent.TimeUnit;

import org.openqa.selenium.By;
import org.openqa.selenium.Keys;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.interactions.Actions;

public class Move_TO {

	public static void main(String[] args) throws InterruptedException {

		System.setProperty("webdriver.chrome.driver", "D:\\chromedriver-145\\chromedriver-win64\\chromedriver.exe");
		WebDriver driver = new ChromeDriver();
		driver.manage().window().maximize();
		driver.manage().timeouts().implicitlyWait(10, TimeUnit.SECONDS);
		driver.get("http://172.21.0.39:8083/Kiya.aiCBS-10.2.0/");
		//LOGIN
		driver.findElement(By.id("loginId")).sendKeys("SM969");
		driver.findElement(By.id("loginId")).sendKeys(Keys.TAB);
		driver.findElement(By.id("uiPwd")).sendKeys("system$123");
		driver.findElement(By.id("uiPwd")).sendKeys(Keys.TAB);
		driver.findElement(By.id("userLogin")).click();
		//window
		Set<String> allwin = driver.getWindowHandles();
		Iterator<String> it = allwin.iterator();
		String win1 = it.next();
		String win2 = it.next();
		
		System.out.println(win1+"\n"+win2);
		
		//windows 2
		//driver.switchTo().window(win1);
		driver.switchTo().window(win2);
		driver.manage().timeouts().implicitlyWait(10, TimeUnit.SECONDS);
		driver.manage().window().maximize();
		
		//burger
		driver.findElement(By.xpath("//a[contains(@class,'item-nav')]/div")).click();
		//retail
		driver.findElement(By.xpath("//a[contains(@href,'customerList.action')]")).click();
		//customer
		WebElement case1 = driver.findElement(By.xpath("//*[text()='315907']"));
		//
		Thread.sleep(2000);
		Actions act = new Actions(driver);
		act.moveToElement(case1).build().perform();
		//app/rej
		Thread.sleep(2000);
		driver.findElement(By.xpath("//*[contains(@href,'javascript:callAuthfn()')]")).click();
		
		Thread.sleep(5000);
		driver.quit();
		
		
		
		
		
		
		
		
		
		
		
		
		
		
		
		
		
		
		
		
		
		
		

		


	}

}
