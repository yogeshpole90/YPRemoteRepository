package Skeleton_pkg;
import java.io.File;
import java.util.concurrent.TimeUnit;

import org.apache.commons.io.FileUtils;
import org.apache.log4j.Priority;
import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.Keys;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.support.ui.Select;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;

public class Law_Firm  { 
	WebDriver driver;

	
    @BeforeClass
	public void bc() throws Exception
	{

		//burger 
		Thread.sleep(1000);
		driver.findElement(By.xpath("//*[@class='item-nav']/div")).click();

		//law firm
		driver.findElement(By.xpath("//*[@id='LAWFIRMMST']/a")).click();

	}

	@DataProvider
	public Object[][] giver()
	{
		return new Object[][]
				{

			{"GOVERMET","ab","129","add1","add2","add3","Seychelles","MAHE","ANSEAUXPINS","111111","121212","915897","y@g.com"},
			{"GROUP OF LAWERS PVT LTD","cd","456","add1","add2","add3","Seychelles","MAHE","ANSEAUXPINS","222222","131313","999999","p@g.com"},
			{"GOVERMET","ef","789","add1","add2","add3","Seychelles","MAHE","ANSEAUXPINS","333333","141414","8888877777","b@g.com"},
			{"GROUP OF LAWERS PVT LTD","gh","101112","add1","add2","add3","Seychelles","MAHE","ANSEAUXPINS","444444","151515","6666699999","a@g.com"},
			{"GOVERMET","ij","131415","add1","add2","add3","Seychelles","MAHE","ANSEAUXPINS","555555","161616","1111122222","c@g.com"},

				};

	}

	@DataProvider
	public Object[][] giver2()
	{
		return new Object[][]
				{
			{"","","","","","","","","","","","",""},

				};

	}

	@Test(priority = 1 , dataProvider = "giver2" )
	public void taker2(String firmType1,String lawFirmName1,
			String regno1,String address11,String address22,String address33,
			String cncode1,String stcode1,String cityCode1,String poBox1,
			String zipCode1,String mobileNo11,String emailId1) throws Exception {

		screnshot();

		driver.findElement(By.id("addButton")).click();

		JavascriptExecutor jse1 = (JavascriptExecutor) driver;
		jse1.executeScript("window.scrollBy(0,5000)");
		driver.findElement(By.id("saveFirm")).click();
		screnshot();


		//backButton
		Thread.sleep(2000);
		jse1.executeScript("window.scrollBy(0,5000)");
		driver.findElement(By.id("backButton")).click();

	}

	@Test(dataProvider = "giver",priority = 2)
	public void taker(String firmType1,String lawFirmName1,String regno1,
			String address11,String address22,String address33,String cncode1,
			String stcode1,String cityCode1,String poBox1,String zipCode1,
			String mobileNo11,String emailId1) throws Exception
	{

		//+++++++++++++++ CREATE +++++++++++++++++++++//
		//addButtonaddButton
		screnshot();
		Thread.sleep(1000);
		driver.findElement(By.id("addButton")).click();

		//firmType
		Thread.sleep(1000);
		screnshot();
		WebElement firmType = driver.findElement(By.id("firmType"));
		Select s1  = new Select(firmType);
		s1.selectByVisibleText(firmType1);

		//lawFirmName
		WebElement lawFirmName = driver.findElement(By.id("lawFirmName"));
		lawFirmName.sendKeys(lawFirmName1);

		//registrationNumber
		WebElement regno = driver.findElement(By.id("registrationNumber"));
		regno.sendKeys(regno1);

		//address1
		driver.findElement(By.id("address1")).sendKeys(address11);

		driver.findElement(By.id("address2")).sendKeys(address22);

		driver.findElement(By.id("address3")).sendKeys(address33);


		//countryCode
		WebElement cncode = driver.findElement(By.id("countryCode"));
		Select s2 = new Select(cncode);
		s2.selectByVisibleText(cncode1);//Seychelles

		//stateCode
		WebElement stcode = driver.findElement(By.id("stateCode"));
		Select s3 = new Select(stcode);
		s3.selectByVisibleText(stcode1);

		//cityCode
		WebElement cityCode = driver.findElement(By.id("cityCode"));
		Select s4 = new Select(cityCode);
		s4.selectByVisibleText(cityCode1);

		//poBox
		driver.findElement(By.id("poBox")).sendKeys(poBox1);

		//zipCode
		driver.findElement(By.id("zipCode")).sendKeys(zipCode1);

		//mobileNo1
		driver.findElement(By.id("mobileNo1")).sendKeys(mobileNo11);

		//emailId
		driver.findElement(By.id("emailId")).sendKeys(emailId1);


		//saveFirm
		screnshot();

		Thread.sleep(2000);
		JavascriptExecutor jse = (JavascriptExecutor) driver;
		jse.executeScript("window.scrollBy(0,5000)");
		driver.findElement(By.id("saveFirm")).click();

		screnshot();

		//backButton
		Thread.sleep(2000);
		jse.executeScript("window.scrollBy(0,5000)");
		driver.findElement(By.id("backButton")).click();

		//search
		driver.findElement(By.xpath("//*[@type='search']")).sendKeys(regno1);
		screnshot();

		//View
		Thread.sleep(1000);
		driver.findElement(By.xpath("//td[text()='"+regno1+"']/parent::tr//a[text()='View']")).click();
		screnshot();

		//backButton
		Thread.sleep(2000);
		jse.executeScript("window.scrollBy(0,5000)");
		driver.findElement(By.id("backButton")).click();

		//search
		driver.findElement(By.xpath("//*[@type='search']")).sendKeys(regno1);
		screnshot();

		//Edit
		driver.findElement(By.xpath("//td[text()='"+regno1+"']/parent::tr//a[text()='Edit']")).click();

		//saveFirm
		Thread.sleep(2000);
		jse.executeScript("window.scrollBy(0,5000)");
		driver.findElement(By.id("saveFirm")).click();
		Thread.sleep(1000);
		screnshot();




		//backButton
		Thread.sleep(2000);
		jse.executeScript("window.scrollBy(0,5000)");
		driver.findElement(By.id("backButton")).click();

		//search
		driver.findElement(By.xpath("//*[@type='search']")).sendKeys(regno1);
		screnshot();

		//disable
		Thread.sleep(2000);
		driver.findElement(By.xpath("//*[text()='"+regno1+"']/parent::tr//a[text()='Disable']")).click();
		screnshot();
	}
	//ss mthod
	public void screnshot() throws Exception {


		Thread.sleep(500);
		TakesScreenshot ts = (TakesScreenshot) driver;
		File src = ts.getScreenshotAs(OutputType.FILE);
		File desc = new File("D:\\Screenshots"+ "\\ss_" +System.currentTimeMillis()+ ".png");
		FileUtils.copyFile(src, desc);

		Thread.sleep(2000);




	}
	
}

