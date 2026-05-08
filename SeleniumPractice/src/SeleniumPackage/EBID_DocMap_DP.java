package SeleniumPackage;

import java.io.IOException;
import java.util.concurrent.TimeUnit;
import org.testng.annotations.Test;
import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.Keys;
import org.openqa.selenium.TakesScreenshot;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.DataProvider;

public class EBID_DocMap_DP {
	WebDriver driver;
	JavascriptExecutor jse;


	@BeforeClass
	public void test1() throws Exception
	{
		System.setProperty("webdriver.chrome.driver","D:\\chromedriver-145\\chromedriver-win64\\chromedriver.exe");
		driver = new ChromeDriver();
		driver.manage().window().maximize();
		driver.manage().timeouts().implicitlyWait(15, TimeUnit.SECONDS);
		driver.get("http://172.21.0.46:8181/lcs-finairoLending-1.0.1");

		driver.findElement(By.id("loginId")).sendKeys("infraadmin");
		driver.findElement(By.id("uiPwd")).sendKeys("abcde@12345");
		driver.findElement(By.id("uiPwd")).sendKeys(Keys.TAB);

		Thread.sleep(1000);
		driver.findElement(By.id("userLogin")).click();
		jse =(JavascriptExecutor) driver;

		//burger 
		Thread.sleep(1000);
		driver.findElement(By.xpath("//*[@class='item-nav']/div")).click();

		//action doc map
		WebElement acdocmap = driver.findElement(By.xpath("//*[@id='ACTIONDOCMAP']/a"));
		jse.executeScript("arguments[0].style.backgroundColor='lightblue'", acdocmap);
		acdocmap.click();


	}

	@DataProvider
	public Object[][] giver()
	{
		return new Object[][]
				{
			{"Asset Repossession","Asset Reposses","yes","yes"},
			{"Full & Final Settlement","FNF","yes","yes"},
			{"Partial Settlement","Partial settlement","yes","yes"},
			{"Promise To Pay","PTP","yes","yes"},
			{"Release Asset","Release Asset","yes","yes"},
			{"Write Off ","Write Off","yes","yes"},
				};

	}


	@Test(dataProvider = "giver" , priority = 2)
	public void taker(String actionName1, String documentName1,String ifMandatoryUpload1,String ifOriginal1)
			throws Exception
	{
		//create
		//click add btn
		Thread.sleep(1500);
		takess();
		WebElement create1 = driver.findElement(By.id("addButton"));
		jse.executeScript("arguments[0].style.backgroundColor='lightblue'", create1);
		create1.click();
		takess();

		//actionName
		Thread.sleep(1000);
		WebElement acname1 = driver.findElement(By.id("actionName"));
		jse.executeScript("arguments[0].style.backgroundColor='lightblue'", acname1);
		acname1.sendKeys(actionName1);

		//documentName
		driver.findElement(By.id("documentName")).sendKeys(documentName1);

		//ifMandatoryUpload
		driver.findElement(By.id("ifMandatoryUpload")).sendKeys(ifMandatoryUpload1);

		//ifOriginal
		driver.findElement(By.id("ifOriginal")).sendKeys(ifOriginal1);
		Thread.sleep(1000);
		takess();

		//save	
		Thread.sleep(1000);
		jse = (JavascriptExecutor) driver;
		jse.executeScript("window.scrollBy(0,1000)");
		WebElement save1 = driver.findElement(By.id("save"));
		jse.executeScript("arguments[0].style.backgroundColor='lightblue'", save1);
		save1.click();
		Thread.sleep(2000);
		takess();

		//backButton
		Thread.sleep(1000);
		jse.executeScript("window.scrollBy(0,5000)");
		WebElement back1 = driver.findElement(By.id("backButton"));
		jse.executeScript("arguments[0].style.backgroundColor='lightblue'", back1);
		back1.click();


		//Search
		WebElement sr1 = driver.findElement(By.xpath("//*[@type='search']"));
		jse.executeScript("arguments[0].style.backgroundColor='lightblue'", sr1);
		sr1.sendKeys(documentName1);
		Thread.sleep(1000);
		takess();

		//view
		WebElement view1 = driver.findElement(By.xpath("//*[text()='"+documentName1+"']/parent::tr//*[text()='View']"));
		jse.executeScript("arguments[0].style.backgroundColor='lightblue'", view1);
		view1.click();

		Thread.sleep(1000);
		takess();

		//backButton
		Thread.sleep(2000);
		jse.executeScript("window.scrollBy(0,5000)");
		WebElement back2 = driver.findElement(By.id("backButton"));
		jse.executeScript("arguments[0].style.backgroundColor='lightblue'", back2);
		back2.click();

		//search
		driver.findElement(By.xpath("//*[@type='search']")).sendKeys(documentName1);
		Thread.sleep(1000);
		takess();

		//Edit
		Thread.sleep(1000);
		WebElement edit2 = driver.findElement(By.xpath("//*[text()='"+documentName1+"']/parent::tr//*[text()='Edit']"));
		jse.executeScript("arguments[0].style.backgroundColor='lightblue'", edit2);
		edit2.click();

		//saveFirm
		Thread.sleep(2000);
		jse.executeScript("window.scrollBy(0,5000)");
		WebElement save2 = driver.findElement(By.id("save"));
		jse.executeScript("arguments[0].style.backgroundColor='lightblue'", save2);
		save2.click();
		Thread.sleep(2000);
		takess();

		//backButton
		jse.executeScript("window.scrollBy(0,5000)");
		driver.findElement(By.id("backButton")).click();
		Thread.sleep(1000);
		takess();


		//disable
		Thread.sleep(1000);
		driver.findElement(By.xpath("//*[@type='search']")).sendKeys(documentName1);
		WebElement disable1 = driver.findElement(By.xpath("//*[text()='"+documentName1+"']/parent::tr//*[text()='Disable']"));
		jse.executeScript("arguments[0].style.backgroundColor='lightblue'", disable1);
		disable1.click();
		Thread.sleep(1000);



	}

	public void takess() throws IOException
	{
		TakesScreenshot ts = (TakesScreenshot) driver;
		//File src = ts.getScreenshotAs(OutputType.FILE);
		//File desc=  new File("D:\\Screenshots\\Action_Doc_Map"+"\\ss_"+ System.currentTimeMillis()+".png");
		//FileUtils.copyFile(src, desc);


	}

	@DataProvider
	public Object[][] giver2()
	{
		return new Object[][] {
			{" "," ","yes","yes"},
		};

	}

	@Test(dataProvider = "giver2" , priority = 1)
	public void taker2() throws Exception
	{
		//add btn click
		Thread.sleep(2000);
		takess();
		driver.findElement(By.id("addButton")).click();
		Thread.sleep(1500);
		takess();


		//save
		Thread.sleep(1000);
		jse.executeScript("window.scrollBy(0,5000)");
		driver.findElement(By.id("save")).click();
		Thread.sleep(2000);
		takess();


		//backButton
		Thread.sleep(2000);
		jse.executeScript("window.scrollBy(0,5000)");
		driver.findElement(By.id("backButton")).click();
		Thread.sleep(1000);
		takess();


	}


}
