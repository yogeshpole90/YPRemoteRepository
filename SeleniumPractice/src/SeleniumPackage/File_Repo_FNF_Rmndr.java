package SeleniumPackage;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.util.Properties;
import java.util.concurrent.TimeUnit;

import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.Keys;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.ui.Select;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;
import org.testng.internal.ReporterConfig.Property;

public class File_Repo_FNF_Rmndr {
	WebDriver driver;
	JavascriptExecutor jse ;
	Actions act;
	File fs;
	FileInputStream fis;
	Properties pro;
	
	
	@BeforeClass
	public void FNF() throws Exception
	{
		System.setProperty("webdriver.chrome.driver", "D:/chromedriver-win64/chromedriver-win64/chromedriver.exe");
		driver= new ChromeDriver();
		driver.manage().window().maximize();
		driver.manage().timeouts().implicitlyWait(15, TimeUnit.SECONDS);
		driver.get("http://172.21.0.46:8181/lcs-finairoLending-1.0.1");
		
		//username
		driver.findElement(By.id("loginId")).sendKeys("Dora");
		driver.findElement(By.id("loginId")).sendKeys(Keys.TAB);
		
		//pwd
		driver.findElement(By.id("uiPwd")).sendKeys(Keys.TAB);
		
		//login button clicked
		driver.findElement(By.id("userLogin")).click();
		
		//file path > Load > Read
		//file path specified
		 fs = new File("C:/Users/Yogesh.Pole/eclipse-workspace/SeleniumPractice/FNF_Repo/FNF_Repo_1");
		
		 fis = new FileInputStream(fs);//Load
	
		 pro = new Properties();//read
		pro.load(fis);
		
		
		//burger
		driver.findElement(By.xpath(pro.getProperty("burger"))).click();
		
		//collector list
		driver.findElement(By.xpath(pro.getProperty("cl"))).click();
		
		
		//Case double click
		WebElement cas10314 = driver.findElement(By.xpath(pro.getProperty("10314")));
		act = new Actions(driver);
		act.doubleClick(cas10314).build().perform();
		
		//remedial action 
		WebElement comhis = driver.findElement(By.xpath(pro.getProperty("comhis")));
		jse = (JavascriptExecutor) driver;
		jse.executeScript("arguments[0].scrollIntoView({block:'center',behavior:'smooth'})", comhis);
		Thread.sleep(2000);
		comhis.click();
		
		
		
		
	}
	@DataProvider
	public Object[][] Giver()
	{
		return new Object[][]
				{
			{"Call","25-01-2026","remarks1"},
			{"Mail","26-01-2026","remarks2"},
			{"Site Visit","28-01-2026","remarks3"}
				};
	}
	
	@Test(dataProvider = "Giver",priority = 1)
	public void test(String remindertype1,String reminderDate1,String remarks1) throws Exception
	{
		//System.out.println("Test");
		driver.switchTo().defaultContent();
		//Child frame switched
		driver.switchTo().frame(pro.getProperty("chchild"));
		
		//reminderType
		WebElement reminderType = driver.findElement(By.xpath(pro.getProperty("remindertype")));
		jse.executeScript("arguments[0].scrollIntoView({block:'center',behavior:'smooth'})", reminderType);
		Thread.sleep(2000);
		
		Select s1 = new Select(reminderType);
		s1.selectByVisibleText(remindertype1);
		
		//reminderDate
		WebElement reminderDate = driver.findElement(By.id("reminderDate"));
		reminderDate.sendKeys(reminderDate1);
		reminderDate.sendKeys(Keys.TAB);
		
		//remarks
		driver.findElement(By.id("remarks")).sendKeys(remarks1);
		
		//save
		Thread.sleep(2000);
		driver.findElement(By.id("save")).click();
		

	
	
	}
	
	@Test(priority = 2)
	public void calender1() throws Exception
	{
		Thread.sleep(3000);
		driver.switchTo().defaultContent();
		WebElement calender2 = driver.findElement(By.xpath("//ul[contains(@class ,'border-0')]/li[4]/a"));
		jse.executeScript("arguments[0].scrollIntoView({block:'center'})", calender2);
		
		Thread.sleep(2000);
		calender2.click();
		jse.executeScript("window.scrollBy(0,400)");
	
	}

}
