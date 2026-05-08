package SeleniumPackage;

import Utility_Package.ServerConfig;

import java.io.FileInputStream;
import java.io.IOException;
import java.util.List;
import java.util.concurrent.TimeUnit;

import org.apache.poi.xssf.usermodel.XSSFSheet;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;
import org.openqa.selenium.By;
import org.openqa.selenium.Keys;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.testng.Assert;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;

public class Assertion_Login {

	WebDriver driver;

	WebElement id;
	WebElement pwd;
	WebElement logbtn;

	@DataProvider (name="Giver")
	public Object[][] apachePOI() throws Exception
	{
		FileInputStream fis = new FileInputStream("D:\\Excel_File_For_Selenium\\PTP_TestData.xlsx");
		XSSFWorkbook wb = new XSSFWorkbook(fis);
		XSSFSheet sh = wb.getSheet("Loginvalidation");

		int totrow = sh.getPhysicalNumberOfRows();
		int totcol = sh.getRow(0).getPhysicalNumberOfCells();
		System.out.println(totcol + " * " + totrow);

		Object[][] data = new Object[totrow - 1][totcol];

		for(int i = 1;i < totrow;i++)
		{
			for(int j=0;j<totcol;j++)
			{
				if(sh.getRow(i)!=null && sh.getRow(i).getCell(j)!=null)
				{
					data[i-1][j]= sh.getRow(i).getCell(j).toString();
				}

				else 
				{
					data[i-1][j]="";
				}
			}
		}
		return data;

	}

	@BeforeClass
	public void setup() {

		System.setProperty("webdriver.chrome.driver", "D:\\chromedriver-146\\chromedriver-win64\\chromedriver.exe");

		driver = new ChromeDriver();
		driver.manage().window().maximize();
		driver.manage().timeouts().implicitlyWait(10, TimeUnit.SECONDS);
		driver.get(Utility_Package.ServerConfig.getActiveServer());
	}

	@Test(dataProvider="Giver")
	public void loginTest(String Remarks,String id1,String pwd1, String expected) throws Exception
	{
		 System.out.println("=="+Remarks+"===========================================");
		getElements();

		id.clear();
		id.sendKeys(id1);
		id.sendKeys(Keys.TAB);

		pwd.clear();
		pwd.sendKeys(pwd1);
		pwd.sendKeys(Keys.TAB);

		Thread.sleep(1000);
		logbtn.click();
		Thread.sleep(2000);

		// 1. Sab error elements xpath se le lo
		List<WebElement> errorElements = driver.findElements(By.xpath("//*[contains(@class,'msg-toast')]/em"));

		// 2. Loop karke assertion lagao
		for (WebElement errorEle : errorElements) 
		{
			String actualText = errorEle.getText().trim(); // text nikal lo aur trim karo
			System.out.println("Found error: " + actualText);

			// 3. Assertion ke saath check karo
			Assert.assertTrue(actualText.equalsIgnoreCase(expected),"Unexpected error message: " + actualText);
		   
		}

		Thread.sleep(4000);
		driver.navigate().refresh();
		Thread.sleep(2000);

	}

	public void getElements() 
	{

		id = driver.findElement(By.id("loginId"));
		pwd = driver.findElement(By.id("uiPwd"));
		logbtn = driver.findElement(By.id("userLogin"));

	}
	
	//Spelling
	@Test
	public void spelling()
	{
		driver.findElement(By.xpath("//*[text()='User Id']"));
		driver.findElement(By.xpath("//*[text()='Password']"));
		driver.findElement(By.xpath("//*[text()='Login']"));

		
		
	}
	
	
	
}

