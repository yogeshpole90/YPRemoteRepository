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

public class EBID_Partial_stmt {

	WebDriver driver;
	JavascriptExecutor jse;
	@BeforeClass
	public void Test() throws Exception
	{
		System.setProperty("webdriver.chrome.driver", "D:\\chromedriver-145\\chromedriver-win64\\chromedriver.exe");
		driver = new ChromeDriver();
		driver.manage().window().maximize();
		driver.manage().timeouts().implicitlyWait(12, TimeUnit.SECONDS);

		driver.get("http://172.21.0.46:8181/lcs-finairoLending-1.0.1");
		driver.findElement(By.id("loginId")).sendKeys("Dora");
		driver.findElement(By.id("loginId")).sendKeys(Keys.TAB);
		driver.findElement(By.id("uiPwd")).sendKeys("abcde@12345");

		driver.findElement(By.id("uiPwd")).sendKeys(Keys.TAB);

		//login clicked
		Thread.sleep(2000);
		driver.findElement(By.id("userLogin")).click();
		Thread.sleep(2000);
		//jse scroll
		jse = (JavascriptExecutor) driver;

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
	@DataProvider
	public Object[][] giver()
	{
		return new Object[][] 
				{
			{"2520","Change Tenure","Bad Outstanding Customer","Test 1"},
			{"3530","Unchange Tenure","Other Reasons","Test 2"},
			
				};


	}

	@Test(dataProvider = "giver")
	public void taker(String partlRepmntAmnt1,String typeOfPartlRepmnt1 ,
			String reasonOfPayoff1,String Remark1) throws Exception
	{

		WebElement partial = driver.findElement(By.xpath("//*[contains(text(),'Partial Settelment')]"));
		Actions act = new Actions(driver);
		act.doubleClick(partial).build().perform();


		//child
		driver.switchTo().frame("addSettlementMstFrame");

		//partlRepmntAmnt
		driver.findElement(By.id("partlRepmntAmnt")).sendKeys(partlRepmntAmnt1);

		//remainingOutstandingAmountToBePaid
		//driver.findElement(By.id("remainingOutstandingAmountToBePaid")).sendKeys(remainOsAmt1);

		//typeOfPartlRepmnt
		WebElement typepartialrepay = driver.findElement(By.id("typeOfPartlRepmnt"));
		Select s1= new Select(typepartialrepay);
		s1.selectByVisibleText(typeOfPartlRepmnt1);

		//reasonOfPayoff
		driver.findElement(By.id("reasonOfPayoff")).sendKeys(reasonOfPayoff1);

		//collectionOfficerRemark
		driver.findElement(By.id("collectionOfficerRemark")).sendKeys(Remark1);

		//save
		driver.findElement(By.id("save")).click();
		
		//View
		WebElement view = driver.findElement(By.xpath("//*[text()='"+partlRepmntAmnt1+"']//parent::tr/td/a[text()='View']"));
		jse.executeScript("arguments[0].scrollIntoView({block:'center'})", view);
		
		Thread.sleep(3000);
		view.click();
		
		jse.executeScript("window.scrollBy(0,800)");

		//edit
		Thread.sleep(3000);
		WebElement edit = driver.findElement(By.xpath("//*[text()='"+partlRepmntAmnt1+"']//parent::tr/td/a[contains(@class,'editBtn')]"));
		jse.executeScript("arguments[0].scrollIntoView({block:'center'})", edit);
		Thread.sleep(3000);
		edit.click();
		
		
		//updated - clicked save
		WebElement saveupd = driver.findElement(By.id("save"));
		jse.executeScript("arguments[0].scrollIntoView({block:'center'})", saveupd);
		Thread.sleep(3000);
		
		saveupd.click();
		
		
		jse.executeScript("window.scrollBy(0,-800)");

		
		
	}

}
