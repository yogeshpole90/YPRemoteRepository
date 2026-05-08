package dempo1;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

public class Class {
	
	
	public static void main(String[] args) throws InterruptedException {
		
		
		System.setProperty("webdriver.chrome.driver", "D:\\Driver\\chromedriver-win64 (142)\\chromedriver-win64\\chromedriver.exe");
		
		
		WebDriver driver = new ChromeDriver();
		  WebDriverWait wait = new WebDriverWait (driver, 20);
		
		 driver.get("http://172.21.0.39:8083/Kiya.aiCBS-10.2.0/");
         driver.manage().window().maximize();
         
       //  WebElement userId = wait.until(ExpectedConditions.visibilityOfElementLocated(
              //   By.id("loginId")
      //   ));
         
         WebElement userId = driver.findElement(By.id("loginId"));
         userId.sendKeys("100");

         // Enter password
         WebElement password = driver.findElement(By.id("uiPwd"));
         password.sendKeys("system$123");
		
         WebElement continueBtn = driver.findElement(By.id("userLogin"));
         continueBtn.click();
         
         wait.until(ExpectedConditions.alertIsPresent());//javascript:;
         driver.switchTo().alert().accept();
         System.out.println("Alert accepted successfully!");
         
         //WebElement menu1 = driver.findElement(By.className("item-nav"));//class="item-nav"
         driver.switchTo().alert().accept();
         driver.findElement(By.xpath("//a[@href='javascript:;']")).click();
         
         
         
         
         


}
}
