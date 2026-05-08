package SeleniumPackage;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.util.concurrent.TimeUnit;

import org.apache.poi.xssf.usermodel.XSSFSheet;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;
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

public class DDT_Legal {
	WebDriver driver;
	Actions act;
	JavascriptExecutor jse;

	@BeforeClass
	public void setup() throws Exception
	{
		System.setProperty("webdriver.chrome.driver","D:\\chromedriver-145\\chromedriver-win64\\chromedriver.exe");
		driver = new ChromeDriver();
		driver.manage().window().maximize();
		driver.manage().timeouts().implicitlyWait(15, TimeUnit.SECONDS);
		driver.get("http://10.10.230.15:8181/lcs-finairoLending-1.0.1/");

		driver.findElement(By.id("loginId")).sendKeys("Dora");
		driver.findElement(By.id("loginId")).sendKeys(Keys.TAB);
		driver.findElement(By.id("uiPwd")).sendKeys("abcd1234");
		driver.findElement(By.id("uiPwd")).sendKeys(Keys.TAB);

		Thread.sleep(1000);
		driver.findElement(By.id("userLogin")).click();

		//burger 
		Thread.sleep(1000);
		driver.findElement(By.xpath("//a[@class='item-nav']/div")).click();

		//inbox
		driver.findElement(By.xpath("//*[@id='COMMONCOLLECTORLIST']/a")).click();

		//case double click
		driver.findElement(By.xpath("//input[@type='search']")).sendKeys("406");
		Thread.sleep(5000);
		WebElement case1 = driver.findElement(By.xpath("//td[text() = '406']"));
		act =new Actions(driver);
		act.doubleClick(case1).build().perform();

		//vertical menu = legal process
		Thread.sleep(1000);
		WebElement legalprocess = driver.findElement(By.xpath("//*[contains(@href,'=Legal Process')]"));
		jse =  (JavascriptExecutor) driver;
		jse.executeScript("arguments[0].scrollIntoView({behavior:'smooth',block:'center'})", legalprocess);
		legalprocess.click();

	}

	@DataProvider (name="Giver")
	public Object[][] giver() throws Exception
	{
		//File fs = new File("D:\\Excel_File_For_Selenium\\PTP_TestData.xlsx");
		FileInputStream fis = new FileInputStream("D:\\Excel_File_For_Selenium\\PTP_TestData.xlsx");
		XSSFWorkbook wb = new XSSFWorkbook(fis);
		XSSFSheet sh = wb.getSheet("CourtCaseDetails");

		int totrow = sh.getPhysicalNumberOfRows();
		int totcol = sh.getRow(0).getPhysicalNumberOfCells();

		System.out.println("Total Row * Col : "+ totrow +" * "+totcol );

		Object [][] data = new Object[totrow-1][totcol];
		for(int i=1;i<totrow-1;i++)
		{
			for(int j =0;j<totcol;j++)

			{
				if(sh.getRow(i) != null || sh.getRow(i).getCell(j) !=null)
				{
					data[i-1][j]= sh.getRow(i).getCell(j).toString();
				}
				else
				{
					System.out.println("");
				}
			}
		}

		return data;

	}

	@Test (dataProvider = "Giver")
	public void test(String casetype1,String suitAmount1,String requestDate1,String lawyerName1,
			String caseInitBy1,String allocatedate1,String lawFirmName1,String dochandlaw1,
			String bankruptCase1,String bandruptdate1,String bankruptcyCaseNo1) throws Exception
	{
		jse =  (JavascriptExecutor) driver;
		act =new Actions(driver);

		//section = court case details 
		WebElement courtcase = driver.findElement(By.xpath("//*[contains(@class,'nav-link a')]"));
		jse.executeScript("arguments[0].scrollIntoView({block:'center',behavior:'smooth'})", courtcase);

		act.doubleClick(courtcase).build().perform();
		//jse.executeScript("window.scrollBy(0,3000)");


		//switch into frame
		driver.switchTo().frame("courtCaseMstListPageFrame");//

		//Thread.sleep(2000);
		//locate court Case Type
		WebElement casetype = driver.findElement(By.xpath("//select[@id='courtCaseType']"));
		jse.executeScript("arguments[0].scrollIntoView({block:'center',behavior:'smooth'})", casetype);
		
		//if not null then select...
		if(casetype1 != null && !casetype1.trim().isEmpty()) 
		{
			Select s1 = new Select(casetype);
			s1.selectByVisibleText(casetype1);
		}
		
		//suitAmount
		WebElement suitAmount = driver.findElement(By.id("suitAmount"));
		suitAmount.clear();
		suitAmount.sendKeys(suitAmount1);
		
		//requestDate
		WebElement requestDate = driver.findElement(By.id("requestDate"));
		requestDate.sendKeys(requestDate1);
		requestDate.sendKeys(Keys.TAB);

		//lawyerName
		Thread.sleep(1000);
		WebElement lawyerName = driver.findElement(By.id("lawyerName"));
		Select s2 = new Select(lawyerName);
		s2.selectByVisibleText(lawyerName1);

		//caseInitiatedBy
		WebElement caseInitiatedBy = driver.findElement(By.id("caseInitiatedBy"));
		Select s3 =new Select(caseInitiatedBy);
		s3.selectByVisibleText(caseInitBy1);

		//allocatedDate
		WebElement allocatedDate = driver.findElement(By.id("allocatedDate"));
		allocatedDate.sendKeys(allocatedate1);
		allocatedDate.sendKeys(Keys.TAB);

		//lawFirmName
		driver.findElement(By.id("lawFirmName")).sendKeys(lawFirmName1);

		//dcHandleLawyerDate
		WebElement dcHandleLawyerDate = driver.findElement(By.id("dcHandleLawyerDate"));
		dcHandleLawyerDate.sendKeys(dochandlaw1);
		dcHandleLawyerDate.sendKeys(Keys.TAB);

		//bankruptcyCase
		WebElement bankruptcyCase = driver.findElement(By.id("bankruptcyCase"));

		Select s4 =new Select(bankruptcyCase);
		s4.selectByVisibleText(bankruptCase1);
		//
		if(bankruptCase1.equalsIgnoreCase("Yes"))
		{
			WebElement bandruptdate = driver.findElement(By.id("bankruptcyCaseDate"));
			bandruptdate.sendKeys(bandruptdate1);
			bandruptdate.sendKeys(Keys.TAB);

			driver.findElement(By.id("bankruptcyCaseNo")).sendKeys(bankruptcyCaseNo1);

		}



		// All fields filled → Save click
		WebElement save2 = driver.findElement(By.id("save"));
		save2.click();


		Thread.sleep(800);
		driver.switchTo().parentFrame();
		WebElement head1 = driver.findElement(By.xpath("//*[contains(@class,'nav-link a')]"));
		jse.executeScript("arguments[0].scrollIntoView({block:'center',behavior:'smooth'})", head1);


		Thread.sleep(3000);
		driver.navigate().refresh();
		Thread.sleep(2000);

	}
	
	@Test(priority = 4)
	public void test(String courtcaseno1,String filldate1,String dateAllocated1)
	{
		driver.switchTo().parentFrame();
		driver.switchTo().frame("getLegalDiaryDataFrame");
		
		
		WebElement legaldiary = driver.findElement(By.xpath("//*[contains(@onclick,'LegalDiaryData')]"));
		jse.executeScript("arguments[0].scrollIntoView({block:'center',behavior:'smooth'})", legaldiary);
		
		//dropdown
		WebElement courtcaseno = driver.findElement(By.id("courtCaseNo"));
		Select s1 = new Select(courtcaseno);
		s1.selectByVisibleText(courtcaseno1);
		
		//
		WebElement filldate = driver.findElement(By.id("filingDate"));
		jse.executeScript("arguments[0].value = arguments[1];", filldate, filldate1);
		
		//dateAllocated
		WebElement dateAllocated = driver.findElement(By.id("dateAllocated"));
		jse.executeScript("arguments[0].value = arguments[1];", dateAllocated, dateAllocated1);
		dateAllocated.sendKeys(Keys.TAB);
		
		//
		
		
		
		
		
		
		
	}
}

