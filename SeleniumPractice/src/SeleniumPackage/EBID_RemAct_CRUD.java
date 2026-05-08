package SeleniumPackage;

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

public class EBID_RemAct_CRUD {
	WebDriver driver;
	JavascriptExecutor jse;
	Actions ac ;

	@BeforeClass
	public void Setup() throws Exception
	{
		System.setProperty("webdriver.chrome.driver", "D:\\chromedriver144\\chromedriver-win64\\chromedriver.exe");
		driver = new ChromeDriver();
		driver.manage().window().maximize();
		driver.manage().timeouts().implicitlyWait(12, TimeUnit.SECONDS);

		driver.get("http://172.21.0.46:8181/lcs-finairoLending-1.0.1");
		driver.findElement(By.id("loginId")).sendKeys("Shelly");
		driver.findElement(By.id("loginId")).sendKeys(Keys.TAB);
		driver.findElement(By.id("uiPwd")).sendKeys("abcde@12345");

		driver.findElement(By.id("uiPwd")).sendKeys(Keys.TAB);

		//login clicked
		driver.findElement(By.id("userLogin")).click();
		Thread.sleep(2000);

		//burger
		driver.findElement(By.xpath("//a[@class='item-nav']/div")).click();


		//inbox
		driver.findElement(By.xpath("//*[@id='COMMONCOLLECTORLIST']/a")).click();

		//case double click
		driver.findElement(By.xpath("//input[@type='search']")).sendKeys("366");
		Thread.sleep(5000);
		WebElement case1 = driver.findElement(By.xpath("//td[text() = '366']"));
		Actions act =new Actions(driver);
		act.doubleClick(case1).build().perform();

		//remedial = vert menu
		WebElement wremedial = driver.findElement(By.xpath("//ul[contains(@class,'border-0')]/li[8]/a"));
		jse = (JavascriptExecutor) driver;
		jse.executeScript("arguments[0].scrollIntoView({block:'center' , behavior:'smooth'})", wremedial);
		Thread.sleep(1000);
		wremedial.click();

	}
	@DataProvider
	public Object[][] Giver()
	{
		return new Object[][]
				{
			{"Asset Repossession","commments1"},
			{"Full & Final Settlement","commments2"},
			{"Partial Settlement","commments3"},
			{"Promise To Pay","commments4"},
			{"Release Asset","commments5"},
			{"Write Off","commments6"}

				};
	}

	@Test(dataProvider = "Giver",priority = 1)
	public void taker(String pactionId , String pcommments) throws Exception
	{
		//parent
		driver.switchTo().defaultContent();

		//Child Frame
		driver.switchTo().frame("caseMstListPageFrame");

		//actionId
		//WebElement wview = driver.findElement(By.xpath("//a[text()='View']"));
		//view for scroll purpose

		WebElement wactionId = driver.findElement(By.xpath("//select[@id='actionId']"));
		jse.executeScript("arguments[0].scrollIntoView({block:'center',behavior:'smooth'})", wactionId);
		Select s1 = new Select(wactionId);
		s1.selectByVisibleText(pactionId);

		//commments
		driver.findElement(By.id("commments")).sendKeys(pcommments);

		//save
		driver.findElement(By.id("save")).click();
		Thread.sleep(1000);

		//view
		WebElement wwview = driver.findElement(By.xpath("//a[text()='View']"));
		wwview.click();
		Thread.sleep(2000);
		
		jse.executeScript("arguments[0].scrollBy(0,-600)");

		driver.navigate().refresh();


	}


}
