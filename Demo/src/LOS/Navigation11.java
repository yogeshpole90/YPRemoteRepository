package LOS;

import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.interactions.Actions;

public class Navigation11 extends Setup{
	
	public void navigated() throws Exception 
	{

		Thread.sleep(2000);
		
		//jse scroll
		JavascriptExecutor jse = (JavascriptExecutor) driver;

		//burger
		driver.findElement(By.xpath("//a[@class='item-nav']/div")).click();



		//inbox
		driver.findElement(By.xpath("//*[@id='COMMONCOLLECTORLIST']/a")).click();

		//case double click
		driver.findElement(By.xpath("//input[@type='search']")).sendKeys("15");
		Thread.sleep(5000);
		WebElement case1 = driver.findElement(By.xpath("//td[text() = '15']"));
		jse.executeScript("arguments[0].style.backgroundColor='lightblue'", case1);

		Actions act =new Actions(driver);
		act.doubleClick(case1).build().perform();



		//remedial = vert menu
		WebElement wremedial = driver.findElement(By.xpath("//ul[contains(@class,'border-0')]/li[8]/a"));

		jse.executeScript("arguments[0].scrollIntoView({block:'center' , behavior:'smooth'})", wremedial);
		Thread.sleep(1000);
		jse.executeScript("arguments[0].style.backgroundColor='lightblue'", wremedial);
		wremedial.click();


	}

}
