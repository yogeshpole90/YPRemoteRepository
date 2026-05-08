package SeleniumPackage;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.util.concurrent.TimeUnit;

import org.apache.commons.io.FileUtils;
import org.apache.poi.xssf.usermodel.XSSFSheet;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;
import org.openqa.selenium.Alert;
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

public class DDT_Ptp {

	WebDriver driver;
	JavascriptExecutor jse;
	Actions ac;

	@DataProvider (name= "Giver")
	public Object[][] test() throws Exception
	{
		FileInputStream fis = new FileInputStream("D:\\Excel_File_For_Selenium\\PTP_TestData.xlsx");
		XSSFWorkbook wb = new XSSFWorkbook(fis);
		XSSFSheet sh = wb.getSheetAt(0);

		//all row/col
		int totrow = sh.getPhysicalNumberOfRows();
		int totcol = sh.getRow(0).getPhysicalNumberOfCells();

		System.out.println("total rows :- "+totrow);
		System.out.println("total columns :- "+totcol);

		System.out.println("1*1 data is :- "+sh.getRow(1).getCell(1).toString());

		Object[][] data = new Object[totrow - 1][totcol];
		//int[][] ref = new int[2][2];
		//System.out.println(" Values of ref variable is :- "+ref);

		//print all values
		for(int i = 1; i < totrow; i++) {
			for(int j = 0; j < totcol; j++) {
				if(sh.getRow(i) != null && sh.getRow(i).getCell(j) != null) {
					data[i-1][j] = sh.getRow(i).getCell(j).toString();
				} else {
					data[i-1][j] = "";
				}
			}
		}
		return data;
	}
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
		driver.findElement(By.xpath("//input[@type='search']")).sendKeys("382");
		Thread.sleep(5000);
		WebElement case1 = driver.findElement(By.xpath("//td[text() = '382']"));
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
	@Test(dataProvider = "Giver",priority = 2)
	public void taker(String odamt1,String dateOfPTPStart1,String ptpType1,String remain1,
			String plannedAmt1,String ppayMode,String pcheqDt,
			String pcheqno,String pcheqAmt,String planDate11) throws Exception
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

			
			//planDate
			WebElement planDate = driver.findElement(By.xpath("(//*[@id='planDate1'])[1]"));
			planDate.clear();
			planDate.sendKeys(planDate11);
			planDate.sendKeys(Keys.TAB);
			
			driver.findElement(By.id("plannedAmt1")).sendKeys("500");

		
			WebElement paymentMode1 = driver.findElement(By.xpath("//*[@id='paymentMode1'][1]"));
			Select s5 = new Select(paymentMode1);
			s5.selectByVisibleText(ppayMode);
			
			
			Thread.sleep(5000);
			driver.findElement(By.id("add3")).click();

			//saveData
			Thread.sleep(2000);
			WebElement save = driver.findElement(By.id("saveData"));
			jse.executeScript("arguments[0].scrollIntoView({block:'center'})",save );
			Thread.sleep(2000);
			save.click();
			
			Thread.sleep(2000);
			/*
			 * Alert a = driver.switchTo().alert(); System.out.println(a.getText());
			 * a.accept();
			 */

		}
		//==============================================

		if(ptpType1 != "Downpayment + Schedule PTP") {
			//add
			WebElement add = driver.findElement(By.id("add"));
			jse.executeScript("arguments[0].scrollIntoView({block:'center'})",add );
			add.click();

			//saveData
			Thread.sleep(2000);
			WebElement save = driver.findElement(By.id("saveData"));
			jse.executeScript("arguments[0].scrollIntoView({block:'center'})",save );
			Thread.sleep(2000);
			save.click();

		}


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
		//	jse.executeScript("arguments[0].scrollIntoView({block:'center'})",save );Disable

		//disable
		//WebElement disable = driver.findElement(By.xpath("//*[text()='"+dateOfPTPStart1+"']/parent::tr//td/a[contains(@class,'Disable')]"));
		//jse.executeScript("arguments[0].scrollIntoView({block:'center'})",disable );
		Thread.sleep(2000);
		//jse.executeScript("arguments[0].style.backgroundColor='lightblue'", disable);
		//disable.click();


		//refresh
		Thread.sleep(4000);
		driver.navigate().refresh();


	}
	@Test(priority = 4 )
	public void ss() throws Exception
	{
		TakesScreenshot ts = (TakesScreenshot)driver;
		File src = ts.getScreenshotAs(OutputType.FILE);
		File dest = new File("D:\\Screenshots"+"\\ss"+System.currentTimeMillis()+".png");
		FileUtils.copyFile(src, dest);
		//apache poi use for Screenshot also
		System.out.println("ss taken...");



	}

	@Test
	public void takess() throws IOException
	{
		TakesScreenshot ts1 = (TakesScreenshot) driver;
		File src = ts1.getScreenshotAs(OutputType.FILE);
		File dest = new File("D:\\screnshot"+"\\ss_"+System.currentTimeMillis()+".png");
		FileUtils.copyFile(src, dest);
		System.out.println("SS Taken...");
		
		
		
	}

}

