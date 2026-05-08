package SeleniumPackage;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
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

public class Assertion_KFICLogin   {
	WebDriver driver;
	WebElement id;
	WebElement pwd;
	WebElement logbtn;

	@DataProvider(name="Giver")
	public Object[][] ApachePOI() throws Exception
	{
		FileInputStream fis = new FileInputStream("D:\\Excel_File_For_Selenium\\PTP_TestData.xlsx");
		XSSFWorkbook wb = new XSSFWorkbook(fis);
		XSSFSheet sh = wb.getSheet("KFICLogin");


		int  totcol= sh.getRow(0).getPhysicalNumberOfCells();
		int  totrow = sh.getPhysicalNumberOfRows();
		System.out.println(totrow +" * "+ totcol);

		Object[][] data = new Object[totrow-1][totcol];

		for(int i=1;i<totrow;i++)
		{
			for(int j=0;j<totcol;j++)

			{
				if(sh.getRow(i)!=null && sh.getRow(i).getCell(j)!=null)
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
	public void setup()
	{
		System.setProperty("webdriver.chrome.driver", "D:\\chromedriver-146\\chromedriver-win64\\chromedriver.exe");
		driver = new ChromeDriver();
		driver.manage().window().maximize();
		driver.manage().timeouts().implicitlyWait(10, TimeUnit.SECONDS);
		driver.get("http://172.21.0.93:7723/lcs-finairoLending-1.0.1");


	}

	@Test(dataProvider = "Giver")
	public void giver(String Remarks,String id1,String pwd1,String ExpectedResult1) throws InterruptedException
	{
		System.out.println(" = "+Remarks+"===============");
		Thread.sleep(2000);
		login();

		id.clear();
		id.sendKeys(id1);
		id.sendKeys(Keys.TAB);

		pwd.clear();
		pwd.sendKeys(pwd1);
		pwd.sendKeys(Keys.TAB);

		Thread.sleep(2000);
		logbtn.click();

		List<WebElement> actualerrors = driver.findElements(By.xpath("//*[contains(@class,'msg-toast')]/em"));

		for(WebElement actualerror:actualerrors)
		{
			String ExpectedResult=ExpectedResult1;
			String actualresult = actualerror.getText().trim();

			try
			{
				Assert.assertEquals(actualresult, ExpectedResult,"Validation is MisMatched!!!"+actualerror );
			}
			catch(Exception e)
			{
				System.out.println("Catch exception : " + e);
			}
			System.out.println("actualerror displaying is :- "+ actualresult);
			System.out.println("Test Case Passed!!!");

		}
		Thread.sleep(2000);
		driver.navigate().refresh();
		Thread.sleep(4000);

	}

	public void login()
	{
		id = driver.findElement(By.id("loginId"));
		pwd=driver.findElement(By.id("uiPwd"));
		logbtn=driver.findElement(By.id("userLogin"));

	}

}
