package SeleniumPackage;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.util.concurrent.TimeUnit;

import org.apache.commons.io.FileUtils;
import org.apache.poi.xssf.usermodel.XSSFSheet;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;
import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.Keys;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.Select;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;

public class DDT_SiteVisitReq {
	WebDriver driver;
	JavascriptExecutor jse;
	Actions act;

	@DataProvider(name="Giver")
	public Object[][] giver() throws Exception
	{

		FileInputStream fis = new FileInputStream("D:\\Excel_File_For_Selenium\\PTP_TestData.xlsx");
		XSSFWorkbook wb = new XSSFWorkbook(fis);
		XSSFSheet sh = wb.getSheet("SiteVisitRequest");

		int totrow = sh.getPhysicalNumberOfRows();
		int totcol = sh.getRow(0).getPhysicalNumberOfCells();

		System.out.println("Tot Row :- "+totrow);
		System.out.println("Tot col :- "+totcol);

		Object[][] data = new Object[totrow-1][totcol];
		for(int i=1;i<totrow;i++)
		{
			for(int j=0;j<totcol;j++)
			{
				if(sh.getRow(i) != null && sh.getRow(i).getCell(j) != null)
				{
					data[i-1][j] = sh.getRow(i).getCell(j).toString();
				}
				else
				{
					System.out.println("");
				}
			}
		}
		return data ;

	}

	@BeforeClass
	public void setup() throws Exception
	{

		System.setProperty("webdriver.chrome.driver", "D:\\chromedriver-145\\chromedriver-win64\\chromedriver.exe");
		driver =new ChromeDriver();
		driver.manage().window().maximize();
		driver.manage().timeouts().implicitlyWait(15, TimeUnit.SECONDS);

		//get
		driver.get("http://172.21.0.46:8181/lcs-finairoLending-1.0.1");
		//
		driver.findElement(By.id("loginId")).sendKeys("Dora");
		driver.findElement(By.id("loginId")).sendKeys(Keys.TAB);

		driver.findElement(By.id("uiPwd")).sendKeys("abcde@12345");

		driver.findElement(By.id("uiPwd")).sendKeys(Keys.TAB);

		Thread.sleep(2000);
		driver.findElement(By.id("userLogin")).click();

		//decalaration
		jse = (JavascriptExecutor) driver;
		act =new Actions(driver);

		//burger
		driver.findElement(By.xpath("//*[@class='item-nav']/div")).click();

		//all case list
		driver.findElement(By.xpath("//*[@id='COMMONCOLLECTORLIST']/a")).click();

		//search
		driver.findElement(By.xpath("//*[@type='search']")).sendKeys("635");
		//case - clicked
		Thread.sleep(2000);
		WebElement case1 = driver.findElement(By.xpath("//*[text()='635']"));
		act.doubleClick(case1).build().perform();

		//followUP


		Thread.sleep(2000);
		WebElement follUP = driver.findElement(By.xpath("//*[contains(@href,'Follow-Up')]"));
		jse.executeScript("arguments[0].scrollIntoView(true)", follUP);
		Thread.sleep(2000);
		follUP.click();

	}
	@Test(dataProvider = "Giver")
	public void test(String visitTp1,String visitedBy1,String visitInitiatedt1,String visitDate1,
			String customerResp1,String collection1,String visitcolldate1 ,String collectedAmount1,
			String mode1,String chequeDate1,String receiptNo1,String remarks1,
			String latitude1, String longitude1) throws Exception
	{
		//=========parent frame ================
		driver.switchTo().parentFrame();

		WebElement sitevstreq = driver.findElement(By.xpath("//*[contains(text(),'Site Visit Request')]"));
		jse.executeScript("arguments[0].scrollIntoView({block:'center'})", sitevstreq);
		act =new Actions(driver);
		act.doubleClick(sitevstreq).build().perform();

		//===========2nd child frame ============//
		driver.switchTo().frame("createSiteVisitDetailsFrame");

		//visitType
		WebElement visitTp = driver.findElement(By.id("visitType"));
		jse.executeScript("arguments[0].scrollIntoView({block:'center'})", visitTp);
		Select s1 = new Select(visitTp);
		s1.selectByVisibleText(visitTp1);

		//visitedBy
		WebElement visitedBy = driver.findElement(By.id("visitedBy"));
		Select s2 = new Select(visitedBy);
		s2.selectByVisibleText(visitedBy1);

		// visitInitiatedt
		WebElement initiatedate = driver.findElement(By.id("visitInitiatedt"));
		jse.executeScript("arguments[0].value=arguments[1];", initiatedate, visitInitiatedt1);

		// visitDate
		WebElement visitdate = driver.findElement(By.id("visitDate"));
		jse.executeScript("arguments[0].value=arguments[1];", visitdate, visitDate1);

		//customerResponse
		WebElement customerResp = driver.findElement(By.id("customerResponse"));
		Select s3 = new Select(customerResp);
		s3.selectByVisibleText(customerResp1);

		//collection
		WebElement collection = driver.findElement(By.id("collection"));
		Select s4 = new Select(collection);
		s4.selectByVisibleText(collection1);

		//if yes
		if(collection1.equalsIgnoreCase("yes"))
		{

			//date
			WebElement visitcolldate = driver.findElement(By.id("collectedDate"));//.sendKeys(collectedDate1);
			jse.executeScript("arguments[0].value=arguments[1]",visitcolldate,visitcolldate1);

			driver.findElement(By.id("collectedAmount")).sendKeys(collectedAmount1);

			//mode
			WebElement mode = driver.findElement(By.id("modeOfPayment"));
			Select s5 = new Select(mode);
			s5.selectByVisibleText(mode1);

			if(mode1.equalsIgnoreCase("Cheque"))
			{
				//chequeDate1
				driver.findElement(By.xpath("//input[@id='chequeDate']")).sendKeys(chequeDate1);

				//receiptNo 
				driver.findElement(By.id("receiptNo")).sendKeys(receiptNo1);

			}


			//remarks
			Thread.sleep(2000);
			WebDriverWait wait = new WebDriverWait(driver, 10);

			WebElement remarks = wait.until(ExpectedConditions.elementToBeClickable(By.id("remarks")));
			remarks.sendKeys(remarks1);

			//latitude
			WebElement lati = driver.findElement(By.id("latitude"));
			lati.clear();
			lati.sendKeys(latitude1);

			//longitude
			WebElement longi = driver.findElement(By.id("longitude"));
			longi.clear();
			longi.sendKeys(longitude1);

			//saveData
			Thread.sleep(2000);
			driver.findElement(By.id("saveData")).click();


		}

		//if no
		if(collection1.equalsIgnoreCase("no"))
		{

			//remarks
			Thread.sleep(2000);
			WebDriverWait wait = new WebDriverWait(driver, 10);

			WebElement remarks = wait.until(ExpectedConditions.elementToBeClickable(By.id("remarks")));
			remarks.sendKeys(remarks1);

			//latitude
			WebElement lati = driver.findElement(By.id("latitude"));
			lati.clear();
			lati.sendKeys(latitude1);

			//longitude
			WebElement longi = driver.findElement(By.id("longitude"));
			longi.clear();
			longi.sendKeys(longitude1);


			//saveData
			Thread.sleep(2000);
			driver.findElement(By.id("saveData")).click();
		}

		if(visitTp1.isEmpty() || visitedBy1.isEmpty() || visitInitiatedt1.isEmpty() || visitDate1.isEmpty())
		{
			driver.findElement(By.id("saveData")).click();
			System.out.println("Mandatory validation checked");
			return;
		}

		driver.switchTo().parentFrame();
		Thread.sleep(800);
		WebElement sitevstreq1 = driver.findElement(By.xpath("//*[contains(text(),'Site Visit Request')]"));
		jse.executeScript("arguments[0].scrollIntoView({block:'center',behavior:'smooth'})", sitevstreq1);

		//ref
		Thread.sleep(3000);
		driver.navigate().refresh();



	}

	public void test() throws IOException
	{

		TakesScreenshot ts= (TakesScreenshot) driver;
		File src = ts.getScreenshotAs(OutputType.FILE);
		File dest = new File("D:\\Screenshots\\SS_ss" + "_SS_"+System.currentTimeMillis() +".png");
		FileUtils.copyFile(src, dest);

	}	


}
