package SeleniumPackage;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
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

public class DDT_follUp {
	WebDriver driver;
	JavascriptExecutor jse;
	Actions act;

	@DataProvider(name="Giver")
	public Object[][] setup() throws Exception 
	{
		FileInputStream fis = new FileInputStream("D:\\Excel_File_For_Selenium\\PTP_TestData.xlsx");
		XSSFWorkbook wb = new XSSFWorkbook(fis);
		XSSFSheet sh = wb.getSheet("AddFollowUP");

		int totrow = sh.getPhysicalNumberOfRows();
		int totcol = sh.getRow(0).getPhysicalNumberOfCells();

		System.out.println("Tot Row :- "+totrow);
		System.out.println("Tot Col :- "+ totcol);

		Object[][] data = new Object[totrow-1][totcol];



		for(int i = 1; i < totrow; i++) {
			for(int j = 0; j < totcol; j++) {
				if(sh.getRow(i) != null && sh.getRow(i).getCell(j) != null) {
					data[i-1][j] = sh.getRow(i).getCell(j).toString();
				} else {
					data[i-1][j] = "";
				}
			}
		}
		return data ;

	}

	@BeforeClass
	public void setup1() throws Exception

	{
		System.setProperty("webdriver.chrome.driver", "D:\\chromedriver-145\\chromedriver-win64\\chromedriver.exe");
		driver = new ChromeDriver();
		driver.get("http://172.21.0.46:8181/lcs-finairoLending-1.0.1/");
		driver.manage().window().maximize();

		driver.manage().timeouts().implicitlyWait(15, TimeUnit.SECONDS);
		driver.findElement(By.id("loginId")).sendKeys("Dora");
		driver.findElement(By.id("loginId")).sendKeys(Keys.TAB);
		driver.findElement(By.id("uiPwd")).sendKeys("abcde@12345");
		driver.findElement(By.id("uiPwd")).sendKeys(Keys.TAB);

		Thread.sleep(2000);
		driver.findElement(By.id("userLogin")).click();

		//ham-burger icon
		driver.findElement(By.xpath("//*[@class='item-nav']/div")).click();
		Thread.sleep(1000);
		//allcase list
		driver.findElement(By.xpath("//*[@id='COMMONCOLLECTORLIST']/a")).click();
		//search
		driver.findElement(By.xpath("//*[@type='search']")).sendKeys("401");

		//
		WebElement case1 = driver.findElement(By.xpath("//*[text()='401']"));
		act = new Actions(driver);
		act.doubleClick(case1).build().perform();

		//Follow Up
		WebElement follup = driver.findElement(By.xpath("//*[contains(@href,'=Follow-Up')]"));
		jse=(JavascriptExecutor) driver;
		jse.executeScript("arguments[0].scrollIntoView({block:'center',behavior:'smooth'})", follup);
		Thread.sleep(2000);
		follup.click();

		//






	}
	@Test(dataProvider = "Giver")
	public void giver(String commType1,String action1,String result1 ,String resldate1,
			String actdate1,String loanacc,String cashType1,String partyCont1,String status1,
			String remark1) throws Exception 

	{
		//parent frame
		driver.switchTo().parentFrame();
		WebElement follup1 = driver.findElement(By.xpath("//*[contains(text(), 'Add Follow-Up')]"));
		act.doubleClick(follup1).build().perform();

		//=============1st Child frame =========//
		//comm type
		driver.switchTo().frame("addcommunicationHistoryFrame");

		WebElement commType = driver.findElement(By.id("communicationType"));
		jse.executeScript("arguments[0].scrollIntoView({block:'center'})", commType);
		Select s1 = new Select(commType);
		s1.selectByVisibleText(commType1);


		//action
		WebElement action = driver.findElement(By.id("action"));
		Select s2 = new Select(action);
		s2.selectByVisibleText(action1);

		//result
		WebElement result = driver.findElement(By.id("callStatus"));
		Select s3 =new Select(result);
		s3.selectByVisibleText(result1);

		//rslt date
		WebElement resldate = driver.findElement(By.id("resolve"));
		resldate.clear();
		resldate.sendKeys(resldate1);
		resldate.sendKeys(Keys.TAB);

		//Action date
		WebElement actdate = driver.findElement(By.id("followUpDate"));
		actdate.clear();
		actdate.sendKeys(actdate1);
		actdate.sendKeys(Keys.TAB);

		//loan acno
		driver.findElement(By.xpath("//*[@class='select2-search__field']")).sendKeys("0004");
		driver.findElement(By.xpath("(//*[contains(text(),'"+loanacc+"')])[2]")).click();

		//cashType
		driver.findElement(By.id("cashType")).sendKeys(cashType1);

		//partyContactName
		driver.findElement(By.id("partyContactName")).sendKeys(partyCont1);

		//status
		driver.findElement(By.id("status")).sendKeys(status1);

		//remark
		driver.findElement(By.id("remark")).sendKeys(remark1);


		//saveData
		Thread.sleep(800);
		WebElement save1 = driver.findElement(By.id("saveData"));
		//jse.executeScript("arguments[0].scrollIntoView({block:'center'})", save1);
		Thread.sleep(3000);
		save1.click();

		//refresh
		Thread.sleep(3000);
		driver.navigate().refresh();
		Thread.sleep(2000);
	





	}





}


