package SeleniumPackage;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.util.concurrent.TimeUnit;

import org.apache.commons.io.FileUtils;
import org.apache.poi.ss.usermodel.DataFormatter;
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
import org.openqa.selenium.support.ui.Select;
import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;

public class EBID_PTP_DDT {

	WebDriver driver;
	JavascriptExecutor jse;
	Actions ac;

	// ================= Excel Read Method =================
	public static Object[][] getExcelData(String filePath, String sheetName) throws Exception {
		FileInputStream fis = new FileInputStream(filePath);
		XSSFWorkbook workbook = new XSSFWorkbook(fis);
		XSSFSheet sheet = workbook.getSheet(sheetName);

		int rows = sheet.getPhysicalNumberOfRows();
		int cols = sheet.getRow(0).getPhysicalNumberOfCells();

		Object[][] data = new Object[rows - 1][cols];
		DataFormatter formatter = new DataFormatter();

		for (int i = 1; i < rows; i++) {
			for (int j = 0; j < cols; j++) {
				if (sheet.getRow(i) != null && sheet.getRow(i).getCell(j) != null) {
					data[i - 1][j] = formatter.formatCellValue(sheet.getRow(i).getCell(j));
				} else {
					data[i - 1][j] = "";
				}
			}
		}

		workbook.close();
		fis.close();
		return data;
	}

	// ================= DataProvider =================
	@DataProvider(name = "Giver")
	public Object[][] giver() throws Exception {
		return getExcelData("D:\\Excel_File_For_Selenium\\PTP_TestData.xlsx", "EBID_PTP");
	}

	// ================= Setup =================
	@BeforeClass
	public void setup() throws Exception {
		System.setProperty("webdriver.chrome.driver", "D:\\chromedriver-145\\chromedriver-win64\\chromedriver.exe");
		driver = new ChromeDriver();
		driver.manage().window().maximize();
		driver.manage().timeouts().implicitlyWait(12, TimeUnit.SECONDS);

		driver.get("http://172.21.0.46:8181/lcs-finairoLending-1.0.1");
		driver.findElement(By.id("loginId")).sendKeys("Dora");
		driver.findElement(By.id("loginId")).sendKeys(Keys.TAB);
		driver.findElement(By.id("uiPwd")).sendKeys("abcde@12345");
		driver.findElement(By.id("uiPwd")).sendKeys(Keys.TAB);
		Thread.sleep(2000);
		driver.findElement(By.id("userLogin")).click();
		Thread.sleep(2000);

		jse = (JavascriptExecutor) driver;

		// Navigation: Burger -> Inbox -> Case Search -> Double Click
		driver.findElement(By.xpath("//a[@class='item-nav']/div")).click();
		driver.findElement(By.xpath("//*[@id='COMMONCOLLECTORLIST']/a")).click();
		driver.findElement(By.xpath("//input[@type='search']")).sendKeys("528");
		Thread.sleep(5000);
		WebElement case1 = driver.findElement(By.xpath("//td[text()='528']"));
		jse.executeScript("arguments[0].style.backgroundColor='lightblue'", case1);
		ac = new Actions(driver);
		ac.doubleClick(case1).build().perform();

		// Remedial menu click
		WebElement wremedial = driver.findElement(By.xpath("//ul[contains(@class,'border-0')]/li[8]/a"));
		jse.executeScript("arguments[0].scrollIntoView({block:'center',behavior:'smooth'})", wremedial);
		Thread.sleep(1000);
		jse.executeScript("arguments[0].style.backgroundColor='lightblue'", wremedial);
		wremedial.click();
	}

	// ================= Test Method =================
	@Test(dataProvider = "Giver", priority = 2)
	public void taker(String odamt1, String dateOfPTPStart1, String ptpType1, String remain1, String plannedAmt1,
			String ppayMode, String pcheqDt, String pcheqno, String pcheqAmt) throws Exception {

		driver.switchTo().defaultContent();
		WebElement mptp = driver.findElement(By.xpath("//ul[@id='myTab']/li[3]/a"));
		jse.executeScript("arguments[0].style.backgroundColor='lightblue'", mptp);
		ac.doubleClick(mptp).build().perform();
		jse.executeScript("window.scrollBy(0,3000)");

		// Switch to child frame
		driver.switchTo().frame("fetchPTPMstTabFrame");

		// Overdue Amount
		WebElement odamt = driver.findElement(By.id("overdueAmount"));
		jse.executeScript("arguments[0].style.backgroundColor='lightblue'", odamt);
		odamt.clear();
		odamt.sendKeys(odamt1);

		// Planned Start Date
		WebElement dateOfPTPStart = driver.findElement(By.id("dateOfPTPStart"));
		jse.executeScript("arguments[0].style.backgroundColor='lightblue'", dateOfPTPStart);
		dateOfPTPStart.clear();
		dateOfPTPStart.sendKeys(dateOfPTPStart1);

		// Remarks
		driver.findElement(By.id("remarks")).sendKeys("OD and Remain. Amt is zero");

		// PTP Type
		WebElement ptpType = driver.findElement(By.id("scheduleType"));
		jse.executeScript("arguments[0].style.backgroundColor='lightblue'", ptpType);
		Select s11 = new Select(ptpType);
		s11.selectByVisibleText(ptpType1);

		// Remain & Planned
		driver.findElement(By.id("remAmt")).sendKeys(remain1);
		driver.findElement(By.id("plannedAmt")).sendKeys(plannedAmt1);

		// Payment Mode
		WebElement wpayMode = driver.findElement(By.id("paymentMode"));
		jse.executeScript("arguments[0].scrollIntoView({block:'center',behavior:'smooth'})", wpayMode);
		new Select(wpayMode).selectByVisibleText(ppayMode);

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


		// Add button click if not Downpayment
		if(ptpType1 != "Downpayment + Schedule PTP") {
			//add
			WebElement add = driver.findElement(By.id("add"));
			jse.executeScript("arguments[0].scrollIntoView({block:'center'})",add );
			add.click();

		}

		// Save
		Thread.sleep(2000);
		WebElement save = driver.findElement(By.id("saveData"));
		jse.executeScript("arguments[0].scrollIntoView({block:'center'})", save);
		Thread.sleep(2000);
		save.click();

		// Refresh page
		driver.switchTo().parentFrame();
		jse.executeScript("arguments[0].scrollIntoView({block:'center'})", mptp);
		driver.switchTo().frame("fetchPTPMstTabFrame");
		driver.navigate().refresh();
	}

	// ================= Screenshot Method =================
	@Test(priority = 3)
	public void ss() throws IOException {
		if (driver != null) {
			TakesScreenshot ts = (TakesScreenshot) driver;
			File src = ts.getScreenshotAs(OutputType.FILE);
			File dest = new File("D:\\Screenshots\\fb1.png");
			FileUtils.copyFile(src, dest);
			System.out.println("Screenshot taken");
		}
	}

	@Test
	public void diable() {
		System.out.println("Disable test placeholder");
	}

	// ================= Teardown =================
	@AfterClass
	public void teardown() {
		if (driver != null) {
			driver.quit();
		}
	}
}