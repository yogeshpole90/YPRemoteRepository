package SeleniumPackage;

import java.io.File;
import java.io.IOException;
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
import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;

public class KFIC_Remind {
	WebDriver driver;
	@BeforeClass
	public void setup() throws Exception
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

		//collector list
		driver.findElement(By.xpath("(//*[contains(@href,'COLLECTORLIST')])[1]")).click();

		//case
		WebElement case299 = driver.findElement(By.xpath("//*[text()='121113299']"));
		Actions act = new Actions(driver);
		act.doubleClick(case299).build().perform();

		//follup
		WebElement follup = driver.findElement(By.xpath("//*[contains(@href,'Follow-Up')]"));
		JavascriptExecutor jse  = (JavascriptExecutor) driver;
		jse.executeScript("arguments[0].scrollIntoView({block:'center'})", follup);

		Thread.sleep(1000);
		follup.click();

		//reminder
		WebElement remind = driver.findElement(By.xpath("(//*[contains(text(),'Reminder')])[1]"));
		act.doubleClick(remind).build().perform();



	}

	@DataProvider
	public Object[][] giver()

	{

		return new Object[][]
				{
			{"Reminder","Soft Reminder","23-02-2026","02","30","PM","03","30","PM","Good1"},
			{"Sent Mail","Soft","24-02-2026","03","30","PM","04","30","PM","Good2"},
			{"Site Visit","not Soft","25-02-2026","04","30","PM","05","30","PM","Good3"},
			{"Sent Mail","Soft Reminder","26-02-2026","05","30","PM","06","30","PM","Good4"},

				};
	}


	@Test(dataProvider = "giver")
	public void taker(String reminderType1, String reminderName1,String reminderDate1,
			String timehr1, String timemin1,String startAmPm1,
			String endTimeHr1,String endTimeMin1,String endAmPm1, String remarks1) throws IOException, Exception
	{

		driver.switchTo().parentFrame();

		
		//child frame
		driver.switchTo().frame("fetchReminderDtlsPageFrame");

		//reminderType
		WebElement reminderType = driver.findElement(By.id("reminderType"));
		JavascriptExecutor jse1 = (JavascriptExecutor) driver;
		jse1.executeScript("arguments[0].scrollIntoView({block:'center'})", reminderType);

		Select s1 = new Select(reminderType);
		s1.selectByVisibleText(reminderType1);

		//reminderName
		driver.findElement(By.id("reminderName")).sendKeys(reminderName1);

		//reminderDate
		WebElement reminddate = driver.findElement(By.id("reminderDate"));
		jse1.executeScript("arguments[0].removeAttribute('readonly');", reminddate);
		reminddate.sendKeys(reminderDate1);
		reminddate.sendKeys(Keys.TAB);
		//jse1.executeScript("arguments[0].value='reminddate1';",reminddate );



		//startTimeHr
		WebElement starthr = driver.findElement(By.id("startTimeHr"));
		Select s2 = new Select(starthr);
		s2.selectByVisibleText(timehr1);

		//
		WebElement timemin = driver.findElement(By.id("startTimeMin"));
		Select s3 = new Select(timemin);
		s3.selectByVisibleText(timemin1);


		//
		WebElement startAmPm = driver.findElement(By.id("startAmPm"));
		Select s4 = new Select(startAmPm);
		s4.selectByVisibleText(startAmPm1);

		//+++++ End Time +++++++++//
		WebElement endTimeHr = driver.findElement(By.id("endTimeHr"));
		Select s5 = new Select(endTimeHr);
		s5.selectByVisibleText(endTimeHr1);

		//
		WebElement endTimeMin = driver.findElement(By.id("endTimeMin"));
		Select s6 = new Select(endTimeMin);
		s6.selectByVisibleText(endTimeMin1);

		//
		WebElement endAmPm = driver.findElement(By.id("endAmPm"));
		Select s7 = new Select(endAmPm);
		s7.selectByVisibleText(endAmPm1);


		//remarks
		driver.findElement(By.id("remarks")).sendKeys(remarks1);

		//
		ss();
		Thread.sleep(2000);
		driver.findElement(By.id("save")).click();
		Thread.sleep(2000);
		ss();

		//


	}
	@Test
	public void ss() throws IOException
	{
		TakesScreenshot ts = (TakesScreenshot) driver;
		File src = ts.getScreenshotAs(OutputType.FILE);
		File dest = new File("D:\\Screenshots\\KFIC_Remin"+"\\ss_"+System.currentTimeMillis()+".png");
		FileUtils.copyFile(src, dest);

		System.out.println("Path is :- " +dest.getAbsolutePath());


	}
	@Test
	public void sstaker()
	{
		TakesScreenshot ts1 = (TakesScreenshot) driver;
		File src = ts1.getScreenshotAs(OutputType.FILE);
		//new File
		
	}





}


