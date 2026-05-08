package SeleniumPackage;

import java.lang.reflect.Array;
import java.util.Set;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;

public class Contains {

	public static void main(String[] args) throws Exception {
//contains method can use every time even if AV is not Dynamic
		
		System.setProperty("webdriver.chrome.driver", "D:\\chromedriver-win64\\chromedriver-win64\\chromedriver.exe");
		
		WebDriver driver = new ChromeDriver();
		
		
		
		driver.get("http://172.21.0.93:7723/lcs-finairoLending-1.0.1");
		//driver.findElement(By.xpath("//input[@id ='loginId']")).sendKeys("KCO1");
		driver.findElement(By.xpath("//input[contains(@id,'loginId')]")).sendKeys("KCO1");

		//LoginID
		driver.findElement(By.xpath("//input[contains(@id,'uiPwd')]")).clear();
		
		//pswd
		//Thread.sleep(2000);
		driver.findElement(By.xpath("//input[contains(@id,'uiPwd')]")).sendKeys("abcde@12345");
		
		//Login button
		Thread.sleep(2000);
		driver.findElement(By.xpath("//button[contains(@id,'userLogin')]")).click();
		
		String ss = driver.getWindowHandle();
		System.out.println(ss);
		
		Set<String> tt = driver.getWindowHandles();
		for(String yy: tt)
		{
			System.out.println(yy);
		}
		
	
		
		
		
		
		
		
		
		
		
		
		
		
		
		
		
		
		
		
		
		
		
		
		
		
		
		
		
		
		

	}

}
