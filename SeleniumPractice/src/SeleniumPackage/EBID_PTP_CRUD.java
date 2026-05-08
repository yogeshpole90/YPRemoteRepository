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

public class EBID_PTP_CRUD {

	WebDriver driver;
	JavascriptExecutor jse;
	Actions ac ;

	@BeforeClass
	public void Setup() throws Exception
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
	public Object[][] Giver()
	{
		return new Object[][]
				{		

			

			{"10000","19-12-2021","Full PTP","5000","5000","Account Transfer",null,null,null},
			{"8000","14-12-2021","Full PTP","4000","4000","CASH",null,null,null},
			{"6000","15-12-2021","Full PTP","3000","3000","Cheque","29-01-2026","30000101","1500"},
			{"4000","16-12-2021","Schedule PTP","2000","2000","NEFT",null,null,null},
			{"2000","17-12-2021","Schedule PTP","1000","1000","Visa Swipe",null,null,null},
			{"6000","15-12-2021","Schedule PTP","3000","3000","Cheque","29-01-2026","30000101","1500"},
			{"4000","16-12-2021","Downpayment + Schedule PTP","2000","2000","NEFT",null,null,null},
			{"2000","17-12-2021","Downpayment + Schedule PTP","1000","1000","Visa Swipe",null,null,null},
			{"6000","15-12-2021","Downpayment + Schedule PTP","3000","3000","Cheque","29-01-2026","30000101","1500"},
			{"6000","15-12-2021","Downpayment + Schedule PTP","3000","3000","Cheque","29-01-2026","30000101","1500"},
			//{"12000","21-12-2021","Full PTP","4000","4000","Cheque","29-01-2026","30000181","1800"},

				};

	}

	@Test(dataProvider = "Giver",priority = 2)
	public void taker(String odamt1,String dateOfPTPStart1,String ptpType1,String remain1,String plannedAmt1,
			String ppayMode,String pcheqDt,String pcheqno,String pcheqAmt
			) throws Exception
	{
		jse = (JavascriptExecutor) driver;

		driver.switchTo().defaultContent();

		WebElement mptp = driver.findElement(By.xpath("//ul[@id='myTab']/li[3]/a"));
		jse.executeScript("arguments[0].style.backgroundColor='lightblue'", mptp);

		ac = new Actions(driver);
		ac.doubleClick(mptp).build().perform();

		jse.executeScript("window.scrollBy(0,3000)");

		//child
		driver.switchTo().frame("fetchPTPMstTabFrame");

		//overdueAmount
		WebElement odamt = driver.findElement(By.id("overdueAmount"));
		jse.executeScript("arguments[0].style.backgroundColor='lightblue'", odamt);
		odamt.clear();
		odamt.sendKeys(odamt1);

		//planned startd date
		WebElement dateOfPTPStart = driver.findElement(By.id("dateOfPTPStart"));
		jse.executeScript("arguments[0].style.backgroundColor='lightblue'", dateOfPTPStart);
		dateOfPTPStart.clear();
		dateOfPTPStart.sendKeys(dateOfPTPStart1);



		//remarks
		driver.findElement(By.id("remarks")).sendKeys("OD and Remain. Amt is zero");

		//PTP Type - DD
		WebElement ptpType = driver.findElement(By.id("scheduleType"));
		jse.executeScript("arguments[0].style.backgroundColor='lightblue'", ptpType);
		Select s11 = new Select(ptpType);
		s11.selectByVisibleText(ptpType1);

		//remAmt
		WebElement remain = driver.findElement(By.id("remAmt"));
		remain.sendKeys(remain1);

		//plannedAmt
		driver.findElement(By.id("plannedAmt")).sendKeys(plannedAmt1);

		//paymentMode
		WebElement wpayMode = driver.findElement(By.xpath("//select[@id='paymentMode']"));
		jse.executeScript("arguments[0].scrollIntoView({block:'center',behavior:'smooth'})", wpayMode);
		Select s1 = new Select(wpayMode);
		s1.selectByVisibleText(ppayMode);


		//========================fix for cheque

		if ("Cheque".equalsIgnoreCase(ppayMode)) {

			if (pcheqDt != null) {
				driver.findElement(By.id("chequeDate")).sendKeys(pcheqDt);
			}

			if (pcheqno != null) {
				driver.findElement(By.id("chequeNumber")).sendKeys(pcheqno);
			}

			if (pcheqAmt != null) {
				driver.findElement(By.id("chequeAmt")).sendKeys(pcheqAmt);
			}

		}

		//downpayment multi-record
		WebElement downpay = driver.findElement(By.xpath("//*[text()='Schedule PTP']"));

		if(downpay.isDisplayed())
		{

			WebElement pldate1 = driver.findElement(By.id("planDate1"));
			pldate1.sendKeys("24-03-2026");
			pldate1.sendKeys(Keys.TAB);

			driver.findElement(By.id("plannedAmt1")).sendKeys("500");

			WebElement paymentMode1 = driver.findElement(By.id("paymentMode1"));

			Select s2 = new Select(paymentMode1);
			s2.selectByVisibleText("Cheque");

			driver.findElement(By.id("add3")).click();


		}
		//==============================================

		if(ptpType1 != "Downpayment + Schedule PTP") {
			//add
			WebElement add = driver.findElement(By.id("add"));
			jse.executeScript("arguments[0].scrollIntoView({block:'center'})",add );
			add.click();

		}

		//saveData
		Thread.sleep(2000);
		WebElement save = driver.findElement(By.id("saveData"));
		jse.executeScript("arguments[0].scrollIntoView({block:'center'})",save );
		Thread.sleep(2000);
		save.click();
		//Thread.sleep(800);

		//Parent
		driver.switchTo().parentFrame();
		jse.executeScript("arguments[0].scrollIntoView({block:'center'})",mptp );

		//Child
		driver.switchTo().frame("fetchPTPMstTabFrame");


		//View
		WebElement view = driver.findElement(By.xpath("//*[text()='"+dateOfPTPStart1+"']/parent::tr//td/a[contains(@class,'ViewBtn')]"));
		//jse.executeScript("arguments[0].style.backgroundColor='lightblue'", view);
		//view.click();

		//Record viewed
		//	jse.executeScript("arguments[0].scrollIntoView({block:'center'})",save );

		//disable
		WebElement disable = driver.findElement(By.xpath("//*[text()='Disable']"));
		jse.executeScript("arguments[0].scrollIntoView({block:'center'})",disable );
		Thread.sleep(2000);
		jse.executeScript("arguments[0].style.backgroundColor='lightblue'", disable);
		//disable.click();


		//refresh
		Thread.sleep(4000);
		driver.navigate().refresh();


	}
	@Test(priority = 3)
	public void ss() throws Exception
	{
		TakesScreenshot ts = (TakesScreenshot)driver;
		File src = ts.getScreenshotAs(OutputType.FILE);
		File dest = new File("D:\\Screenshots\\fb1.png");
		FileUtils.copyFile(src, dest);
		//apache poi use for Screenshot also
		System.out.println("ss taken");



	}
	@Test
	public void diable()
	{
		System.out.println("Disable");
		//try=error=skip


	}

}




