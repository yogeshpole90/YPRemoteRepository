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

public class DDT {
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
		WebElement police = driver.findElement(By.xpath("//a[contains(@href,'Police Complaint')]"));
		jse.executeScript("arguments[0].scrollIntoView(true)", police);
		Thread.sleep(2000);
		police.click();

	}
	
	@Test(dataProvider = "Giver")
	public void test()
	{
		
		
		
		
		
		
	}

}
