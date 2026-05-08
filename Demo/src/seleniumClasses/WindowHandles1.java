package seleniumClasses;

import java.util.Iterator;
import java.util.Set;
import java.util.concurrent.TimeUnit;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;

public class WindowHandles1 {

	public static void main(String[] args) throws Exception {
		// TODO Auto-generated method stub
		System.setProperty("webdriver.chrome.driver","D:\\chromedriver-win64\\chromedriver-win64\\chromedriver.exe");
		WebDriver driver = new ChromeDriver();
		driver.manage().window().maximize();
		driver.manage().timeouts().implicitlyWait(5, TimeUnit.SECONDS);
		driver.get(	"http://172.21.0.39:8083/Kiya.aiCBS-10.2.0/LoginPage?tid=139&lang=en");

		Thread.sleep(1000);
		
		driver.findElement(By.xpath("//input[@id='loginId']")).sendKeys("SM969");
		driver.findElement(By.xpath("//input[@id='uiPwd']")).sendKeys("system$123");
		
		
		driver.findElement(By.xpath("//button[@id='userLogin']")).click();
		
		Thread.sleep(3000);

		Set<String> Popup = driver.getWindowHandles();
		System.out.println(Popup);
		System.out.println(Popup.size());
		
		Iterator<String> it =  Popup.iterator();
		String window1 = it.next();
		String window2 = it.next();
		
		//Print title
		Thread.sleep(2000);
		driver.switchTo().window(window1);
		System.out.println(window1);
		//PAGE IS BLANK.Title is also blank
		System.out.println(driver.getTitle());
		//driver.quit(); don't use quit before close
		//quit removes unique id
		
		//print title
		Thread.sleep(2000);
		driver.switchTo().window(window2);
		driver.manage().window().maximize();
		System.out.println(window2);
		System.out.println(driver.getTitle());//title 
		driver.findElement(By.xpath("//a[@class='item-nav']/div")).click();
		driver.findElement(By.xpath("//li[@id='CUSTOMER']/a")).click();

		//driver.findElement(By.xpath("//input[@id='docUpload']")).sendKeys(args);

	}

}
