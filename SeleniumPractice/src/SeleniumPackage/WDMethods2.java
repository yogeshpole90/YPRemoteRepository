package SeleniumPackage;

import java.util.List;

import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

public class WDMethods2 {

	public static void main(String[] args) throws Exception {
		// TODO Auto-generated method stub
		System.setProperty("webdriver.chrome.driver", "D:\\chromedriver-win64\\chromedriver-win64\\chromedriver.exe");

		WebDriver driver = new ChromeDriver();
		driver.manage().window().maximize();
		
		driver.navigate().to("http://172.21.0.93:7723/lcs-finairoLending-1.0.1");
		
		Thread.sleep(2000);
		driver.findElement(By.xpath("//div/input[@id='loginId']")).sendKeys("KCO1");

		driver.findElement(By.xpath("//div/input[@id='uiPwd']")).clear();
		Thread.sleep(2000);
		driver.findElement(By.xpath("//div/input[@id='uiPwd']")).sendKeys("abcde@12345");
		

		//login button clicked
		Thread.sleep(2000);
		driver.findElement(By.xpath("//div/button[@id='userLogin']")).click();
		

		//3 lines clicked
		Thread.sleep(2000);
		driver.findElement(By.xpath("//a[@class='item-nav']/div/div")).click();
		
		//collector list -  clicked
		Thread.sleep(2000);
	driver.findElement(By.xpath("//ul[@class='lst-super-sub-nav']/li[2]")).click();	//ul[@class='lst-super-sub-nav']/li[2]
		//driver.findElement(By.xpath("//ul/li[@id ='INBOXLIST']")).click();
		
		Actions ac = new Actions(driver);
		Thread.sleep(2000);

		//row selection //table/tbody/tr[@role='row']/td[2]
		 WebElement case1 = driver.findElement(By.xpath("//table/tbody/tr[@role='row']/td[2]"));
		ac.doubleClick(case1).perform();
		System.out.println("double clicked");
		
		Thread.sleep(2000);
		JavascriptExecutor js = (JavascriptExecutor) driver;
		js.executeAsyncScript("window.scrollBy(0,500)");
		
		//charges  - clicked
		Thread.sleep(20000);
		driver.findElement(By.xpath("//a[@class='sn-active']")).click();
		
		Thread.sleep(50000);
		List<WebElement> bb = driver.findElements(By.xpath("//div[@id='dt-authdata_wrapper']/div[2]/div/div/table/tbody/tr[@role='row']"));
		
		for (WebElement cc: bb)	
        {
	        System.out.println(cc.getSize());
        }
	      	System.out.println("Stop");
		
		
		
		
		
		
		
		
	}

}
