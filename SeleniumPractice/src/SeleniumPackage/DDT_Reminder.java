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
import org.openqa.selenium.support.ui.Select;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;

public class DDT_Reminder {

	//declare
	WebDriver driver;
	JavascriptExecutor jse;
	Actions act;

	@DataProvider(name="Giver")
	public Object[][] excel() throws Exception
	{
		FileInputStream fis = new FileInputStream("D:\\Excel_File_For_Selenium\\PTP_TestData.xlsx");
		XSSFWorkbook wb = new XSSFWorkbook(fis);
		XSSFSheet sh = wb.getSheet("Reminder");

		int totrow = sh.getPhysicalNumberOfRows();//7
		int totcol = sh.getRow(0).getPhysicalNumberOfCells();//4

		Object[][] data = new Object[totrow-1][totcol];
		for(int i=1;i<totrow;i++)
		{
			for(int j=0;j< totcol ;j++)
			{
				if(sh.getRow(i)!= null && sh.getRow(i).getCell(j)!=null)
				{
					data[i-1][j]=sh.getRow(i).getCell(j).toString();

				}
				else 
				{
					System.out.println("");
				}
			}
		}


		return data;

	}

	@BeforeClass
	public void login() throws Exception
	{
		System.setProperty("webdriver.chrome.driver", "D:\\chromedriver-145\\chromedriver-win64\\chromedriver.exe");
		driver = new ChromeDriver();
		driver.manage().window().maximize();
		driver.manage().timeouts().implicitlyWait(10, TimeUnit.SECONDS);

		driver.get("http://172.21.0.46:8181/lcs-finairoLending-1.0.1/");

		//Login credentials
		driver.findElement(By.id("loginId")).sendKeys("Dora");
		driver.findElement(By.id("loginId")).sendKeys(Keys.TAB);

		driver.findElement(By.id("uiPwd")).sendKeys("abcde@12345");
		driver.findElement(By.id("uiPwd")).sendKeys(Keys.TAB);
		Thread.sleep(2000);
		driver.findElement(By.id("userLogin")).click();

		//initialize 
		//===================================
		jse= (JavascriptExecutor) driver;
		act = new Actions(driver);
		//==============================


		//ham-burger
		driver.findElement(By.xpath("//*[@class='item-nav']/div")).click();

		//All Cases List
		driver.findElement(By.xpath("//*[@id='COMMONCOLLECTORLIST']/a")).click();

		//case Clicked
		driver.findElement(By.xpath("//*[@type='search']")).sendKeys("610");
		WebElement case1 = driver.findElement(By.xpath("//*[text()='610']"));
		act.doubleClick(case1).build().perform();

		//vertical menu
		WebElement commhis = driver.findElement(By.xpath("//*[contains(@href,'=Communication History')]"));
		jse.executeScript("arguments[0].scrollIntoView({block:'center',behavior:'smooth'})", commhis);
		Thread.sleep(1000);
		commhis.click();

	}
	@Test(dataProvider = "Giver")
	public void test(String reminderType1,String reminderDate1,String remicrdate1,
			String remarks1) throws Exception
	{
		//=========Parent Frame================//
		driver.switchTo().parentFrame();
		WebElement rem = driver.findElement(By.xpath("(//a[contains(text(),'Reminder')])[1]"));
		jse.executeScript("arguments[0].scrollIntoView({block:'center',behavior:'smooth'})", rem);

		//===========Child Frame=============//
		driver.switchTo().frame("fetchReminderDtlsPageFrame");
		
		//reminderType
		WebElement remindType = driver.findElement(By.id("reminderType"));
		jse.executeScript("arguments[0].scrollIntoView({block:'center',behavior:'smooth'})", remindType);
		
		if(reminderType1 != null && !reminderType1.trim().isEmpty()) 
		{

			Select s1 = new Select(remindType);
			s1.selectByVisibleText(reminderType1);
		}
		//reminderDate
		driver.findElement(By.id("reminderDate")).sendKeys(reminderDate1);

		//reminderCreateDate
		WebElement remicrdate = driver.findElement(By.id("reminderCreateDate"));
		remicrdate.clear();
		remicrdate.sendKeys(remicrdate1);

		//remarks
		driver.findElement(By.id("remarks")).sendKeys(remarks1);

		//save
		driver.findElement(By.id("save")).click();

		//=========parent
		Thread.sleep(800);
		driver.switchTo().parentFrame();
		driver.switchTo().parentFrame();
		WebElement rem1 = driver.findElement(By.xpath("(//a[contains(text(),'Reminder')])[1]"));
		jse.executeScript("arguments[0].scrollIntoView({block:'center',behavior:'smooth'})", rem1);


		//Ref
		driver.navigate().refresh();
		//


	}

}
