package seleniumClasses;

import java.util.concurrent.TimeUnit;

import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.Keys;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.interactions.Actions;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;

public class TNGDataprovider2 {


	WebDriver driver;
	JavascriptExecutor jse;

	@BeforeClass
	public void test() throws Exception
	{
		System.setProperty("webdriver.chrome.driver","D:\\chromedriver-win64\\chromedriver-win64\\chromedriver.exe");


		 driver = new ChromeDriver();

		driver.manage().window().maximize();
		driver.manage().timeouts().implicitlyWait(10, TimeUnit.SECONDS);
		Thread.sleep(1000);
		driver.get(	"http://172.21.0.46:8181/lcs-finairoLending-1.0.1");
		Thread.sleep(2000);
		https://www.test.edzotech.com/?utm_source=chatgpt.com

		//driver.findElement(By.id("loginId")).sendKeys(Keys.TAB);
		driver.findElement(By.id("loginId")).sendKeys("Dora");
		driver.findElement(By.id("loginId")).sendKeys(Keys.TAB);
		driver.findElement(By.id("uiPwd")).sendKeys(Keys.TAB);
		Thread.sleep(1000);
		driver.findElement(By.id("userLogin")).click();
		Thread.sleep(4000);

		//burger button click
		driver.findElement(By.xpath("//a[@class='item-nav']/div")).click();
		Thread.sleep(2000);
		//inbox list clicked
		driver.findElement(By.xpath("//li[@id='COLLECTORLIST']/a")).click();

		//locate case
		WebElement case2 = driver.findElement(By.xpath("//*[text()='22234']"));

		//actions class
		Actions act = new Actions(driver);
		act.doubleClick(case2).build().perform();

		//Click Document vertical menu
		Thread.sleep(3000);
		WebElement rem = driver.findElement(By.xpath("//ul[contains(@class,'d-block border-0')]/li[8]/a"));
		 jse = (JavascriptExecutor) driver;
		jse.executeScript("arguments[0].scrollIntoView({behavior : 'smooth' ,block : 'center'})", rem);

		rem.click();
		
		 driver.switchTo().frame("caseMstListPageFrame");




	}

	@DataProvider
	public Object[][] dataSet()
	{
		return new Object[][]
				{
			
			{"Asset Repossession"},
			{"Full & Final Settlement"},
			{"Partial Settlement"},
			{"Promise To Pay"},
			{"Release Asset"},
			{"Write Off "},
			
				};	

	}
	
	@Test(dataProvider  = "dataSet")
	public void taker(String actid)
	{
		

		//caseMstListPageFrame
		
		WebElement actid1 = driver.findElement(By.id("actionId"));

		jse.executeScript("arguments[0].scrollIntoView({block:'center'})", actid1);
		
		actid1.sendKeys(actid);
		
		driver.findElement(By.id("save")).click();
	

  }

}
