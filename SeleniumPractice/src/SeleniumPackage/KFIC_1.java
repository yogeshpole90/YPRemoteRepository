package SeleniumPackage;

import java.io.File;
import java.io.IOException;
import java.util.List;
import java.util.concurrent.TimeUnit;

import org.apache.commons.io.FileUtils;
import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.Keys;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.ui.Select;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

public class KFIC_1 {
	WebDriver driver;
	@BeforeClass
	public void setup () throws Exception
	{
		System.setProperty("webdriver.chrome.driver","D:\\chromedriver144\\chromedriver-win64\\chromedriver.exe");
		driver = new ChromeDriver();
		driver.manage().window().maximize();
		driver.manage().timeouts().implicitlyWait(15, TimeUnit.SECONDS);
		Thread.sleep(1000);
		driver.get(	"http://172.21.0.93:7723/lcs-finairoLending-1.0.1/");
		Thread.sleep(2000);

		driver.findElement(By.id("languageCode")).sendKeys(Keys.TAB);
		driver.findElement(By.id("loginId")).sendKeys("KCO1");
		driver.findElement(By.id("loginId")).sendKeys(Keys.TAB);
		driver.findElement(By.id("uiPwd")).sendKeys(Keys.TAB);
		Thread.sleep(1000);
		driver.findElement(By.id("userLogin")).click();
		Thread.sleep(4000);

		//Burger
		driver.findElement(By.xpath("//*[@class='item-nav']/div")).click();

		//menu = inbox list
		driver.findElement(By.xpath("//*[@id='INBOXLIST']/a")).click();


		//case clicked
		WebElement case967 = driver.findElement(By.xpath("//*[text()='7232967']"));


		Actions act = new Actions(driver);
		act.doubleClick(case967).build().perform();

		//charges = vertical-menu
		WebElement charges = driver.findElement(By.xpath("//*[contains(@href,'=Charges')]"));


		//javascript scroll
		JavascriptExecutor jse  = (JavascriptExecutor) driver;
		jse.executeScript("arguments[0].scrollIntoView({block:'center'})", charges);

		charges.click();


		//switch to child frame
		driver.switchTo().frame("fetchChargesWaiverPageFrame");

		//scroll
		WebElement pre = driver.findElement(By.xpath("//*[@id='dt-authdata_previous']"));
		jse.executeScript("arguments[0].scrollIntoView({block:'center'})", pre);

		//pagination
		List<WebElement> pages = driver.findElements(By.xpath("//*[@id='dt-authdata_paginate']/ul/li"));
		pages.get(4).click();

		//checkbox
		driver.findElement(By.xpath("(//*[@data-index='34'])[1]")).click();
		Thread.sleep(1000);
		WebElement waived = driver.findElement(By.xpath("(//*[@data-index='34'])[2]"));
		waived.sendKeys(Keys.CONTROL+"a");
		waived.sendKeys(Keys.DELETE);
		Thread.sleep(1000);
		waived.sendKeys("2");

		//Mode of Payment...
		WebElement mode = driver.findElement(By.xpath("(//*[@data-index='34'])[3]"));
		Select s1 = new Select(mode);
		s1.selectByVisibleText("Cash");


		//save
		//Thread.sleep(2000);
		ss();
		Thread.sleep(2000);
		driver.findElement(By.id("save")).click();
		//Thread.sleep(1000);
		
		driver.switchTo().parentFrame();
		jse.executeScript("window.scrollBy(0,-100)");
		Thread.sleep(700);
		ss();
		
		//sendForApproval
		Thread.sleep(700);
		jse.executeScript("window.scrollBy(0,100)");
		
		driver.switchTo().frame("fetchChargesWaiverPageFrame");
		driver.findElement(By.id("sendForApproval")).click();
		Thread.sleep(700);
		
		driver.switchTo().parentFrame();
		jse.executeScript("window.scrollBy(0,-100)");
		ss();



	}
	@Test
	public void ss() throws IOException
	{

		//type cast = driver - WD = WD ss mtd nhi hy. type cast = mtd can used...
		
		TakesScreenshot ts = (TakesScreenshot) driver;
		File src = ts.getScreenshotAs(OutputType.FILE);
		File dest = new File("D:\\Screenshots\\Waived" + "\\ss_"+ System.currentTimeMillis()+".png");
		FileUtils.copyFile(src, dest);
		
	}
}
