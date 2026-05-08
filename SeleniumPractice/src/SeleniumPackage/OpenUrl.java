package SeleniumPackage;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.interactions.Actions;

public class OpenUrl {

	public static void main(String[] args) throws Exception {
		// TODO Auto-generated method stub
		
		System.setProperty("webdriver.chrome.driver", "D:\\chromedriver-win64\\chromedriver-win64\\chromedriver.exe");

		WebDriver driver =new ChromeDriver();
		driver.manage().window().maximize();
		
		driver.get("http://172.21.0.93:7723/lcs-finairoLending-1.0.1");
		
		//wait for 2sec
		Thread.sleep(2000);
		driver.findElement(By.xpath("//input[@id='loginId']")).sendKeys("KCO1");
		//input[@id='loginId']  not comma but equals to will used after AN...
		
		driver.findElement(By.xpath("//input[@id='uiPwd']")).clear();
		
		//wait
		Thread.sleep(2000);
		driver.findElement(By.xpath("//input[@id='uiPwd']")).sendKeys("abcde@12345");
		
		//wait 2 Sec
		Thread.sleep(2000);
		driver.findElement(By.xpath("//button[@id='userLogin']")).click();
		
		
//when AN is not present,go to parent AN and use then take indexing untill desired highlighted field
	driver.findElement(By.xpath("//a[@class='item-nav']/div[1]")).click();
	driver.findElement(By.xpath("//li[@id='COLLECTORLIST']//a[1]")).click();
	
	
	//double click using actons class
	Thread.sleep(4000);
	 WebElement element = driver.findElement(By.xpath("//td[@class='sorting_1']"));
		
	Actions actions =new Actions(driver);
	actions.doubleClick(element).perform();
	
	//Scrolling using javascriptexecutor
	
	
	
	
	
	
	
	
	
	}

}
