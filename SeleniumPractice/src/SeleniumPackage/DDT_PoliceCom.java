package SeleniumPackage;


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
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.Select;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;

public class DDT_PoliceCom {
	WebDriver driver;
	JavascriptExecutor jse;
	Actions act;

	@DataProvider(name="Giver")
	public Object[][] giver() throws Exception
	{

		FileInputStream fis = new FileInputStream("D:\\Excel_File_For_Selenium\\PTP_TestData.xlsx");
		XSSFWorkbook wb = new XSSFWorkbook(fis);
		XSSFSheet sh = wb.getSheet("PoliceComplaint");

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
		driver.get("http://10.10.230.14:8181/lcs-finairoLending-1.0.1");
		//
		driver.findElement(By.id("loginId")).sendKeys("Shelly");
		driver.findElement(By.id("loginId")).sendKeys(Keys.TAB);

		driver.findElement(By.id("uiPwd")).sendKeys("abcd1234");

		driver.findElement(By.id("uiPwd")).sendKeys(Keys.TAB);

		Thread.sleep(2000);
		driver.findElement(By.id("userLogin")).click();

		//decalaration
		jse = (JavascriptExecutor) driver;
		act =new Actions(driver);

		//burger
		driver.findElement(By.xpath("//*[@class='item-nav']/div")).click();

		//all case list
		driver.findElement(By.xpath("//*[@id='COLLECTORLIST']/a")).click();

		//search
		driver.findElement(By.xpath("//*[@type='search']")).sendKeys("411");
		//case - clicked
		Thread.sleep(2000);
		WebElement case1 = driver.findElement(By.xpath("//*[text()='411']"));
		act.doubleClick(case1).build().perform();

		//followUP
		Thread.sleep(2000);
		WebElement police = driver.findElement(By.xpath("//a[contains(@href,'Police Complaint')]"));
		jse.executeScript("arguments[0].scrollIntoView(true)", police);
		Thread.sleep(2000);
		police.click();

	}

	@Test(dataProvider = "Giver")
	public void Test(String caseType1,String actTaken1,String actDate1,String Result1,
			String resultDate1,String branch1,String policeActionAmount1,String transferDate1,
			String prosecstatus1) throws Exception
	{
		//=============parent===============//
		driver.switchTo().parentFrame();
		WebElement policereg = driver.findElement(By.xpath("//*[contains(@onclick,'PoliceComplaint')]"));
		jse.executeScript("arguments[0].scrollIntoView({block:'center',behavior:'smooth'})", policereg);

		//double click
		act.doubleClick(policereg).build().perform();
		
		//===============child=================//
		driver.switchTo().frame("viewPoliceComplaintRegisterFrame");//viewPoliceComplaintRegisterFrame

		//
		WebElement caseType = driver.findElement(By.id("caseType"));
		jse.executeScript("arguments[0].scrollIntoView({block:'center',behavior:'smooth'})", caseType);
		Select s1 = new Select(caseType);

		//
		WebElement actTaken = driver.findElement(By.id("actionTaken"));
		Select s2 = new Select(actTaken);


		if(caseType1 != null || !caseType1.trim().isEmpty() || actTaken1 != null || 
				!actTaken1.trim().isEmpty()  )

		{

			s1.selectByVisibleText(caseType1);
			s2.selectByVisibleText(actTaken1);

		}


		//actionDate
		WebElement actDate = driver.findElement(By.id("actionDate"));
		actDate.clear();
		actDate.sendKeys(actDate1);
		actDate.sendKeys(Keys.TAB);
		
		//contactResult
		WebElement Result = driver.findElement(By.id("contactResult"));//resultDate
		Select s3 = new Select(Result);
		s3.selectByVisibleText(Result1);

		//resultDate
		WebElement resultDate = driver.findElement(By.id("resultDate"));
		resultDate.clear();
		resultDate.sendKeys(resultDate1);
		resultDate.sendKeys(Keys.TAB);

		//policeStationBranch
		WebElement branch = driver.findElement(By.id("policeStationBranch"));
		Select s4 = new Select(branch);
		s4.selectByVisibleText(branch1);

		//driver.manage().window().maximize();
		driver.findElement(By.id("policeActionAmount")).sendKeys(policeActionAmount1);

		//
		WebElement transferDate = driver.findElement(By.id("transferDate"));
		transferDate.sendKeys(transferDate1);
		transferDate.sendKeys(Keys.TAB);

		//
		WebElement prosecstatus = driver.findElement(By.id("prosecutionStatus"));
		Select s8 = new Select(prosecstatus);
		s8.selectByVisibleText(prosecstatus1);

		//
		Thread.sleep(1000);
		driver.findElement(By.id("saveData")).click();
		//
		driver.switchTo().parentFrame();
		WebElement policereghead = driver.findElement(By.xpath("//*[contains(@onclick,'PoliceComplaint')]"));
		jse.executeScript("arguments[0].scrollIntoView({block:'center',behavior:'smooth'})", policereghead);

		//child frame
		driver.switchTo().frame("viewPoliceComplaintRegisterFrame");
		
		WebElement viewlast = driver.findElement(By.xpath("(//a[text()='View'])[last()]"));
		jse.executeScript("arguments[0].scrollIntoView({block:'center',behavior:'smooth'})", viewlast);
		
		//view
		Thread.sleep(2000);
		viewlast.click();
		jse.executeScript("window.scrollBy(0,700)");
		Thread.sleep(2000);

		//edit
		Thread.sleep(2000);
		WebElement editlast = driver.findElement(By.xpath("(//a[contains(@onclick , 'EditData')])[last()]"));
		jse.executeScript("arguments[0].scrollIntoView({block:'center',behavior:'smooth'})", editlast);
		Thread.sleep(2000);
		editlast.click();//
		jse.executeScript("window.scrollBy(0,700)");
		Thread.sleep(2000);

		//disable
		Thread.sleep(2000);
		WebElement disable = driver.findElement(By.xpath("(//a[contains(@onclick , 'DeleteData')])[last()]"));
		jse.executeScript("arguments[0].scrollIntoView({block:'center',behavior:'smooth'})", disable);
		Thread.sleep(2000);
		editlast.click();
		jse.executeScript("window.scrollBy(0,700)");
		Thread.sleep(2000);

		//Refresh
		Thread.sleep(2000);
		driver.navigate().refresh();
		Thread.sleep(2000);
		

		




	}
}
