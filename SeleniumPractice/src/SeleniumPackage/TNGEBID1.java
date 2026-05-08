package SeleniumPackage;

import java.util.concurrent.TimeUnit;

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

public class TNGEBID1 {
	WebDriver driver; //class level
	JavascriptExecutor jse;

	//@Test(priority = 1)
	@BeforeClass
	public void Ebid() throws Exception
	{
		System.setProperty("webdriver.chrome.driver",
				"D:\\chromedriver-145\\chromedriver-win64\\chromedriver.exe");

		driver = new ChromeDriver();
		driver.manage().window().maximize();
		driver.manage().timeouts().implicitlyWait(15, TimeUnit.SECONDS);

		driver.get("http://172.21.0.46:8181/lcs-finairoLending-1.0.1");

		driver.findElement(By.id("loginId")).sendKeys("Dora");
		driver.findElement(By.id("loginId")).sendKeys(Keys.TAB);
		driver.findElement(By.id("uiPwd")).sendKeys("abcde@12345");
		driver.findElement(By.id("uiPwd")).sendKeys(Keys.TAB);
		driver.findElement(By.id("userLogin")).click();

		Thread.sleep(2000);

		//burger button click
		driver.findElement(By.xpath("//a[@class='item-nav']/div")).click();
		Thread.sleep(2000);

		//inbox list clicked
		driver.findElement(By.xpath("//*[@id='COMMONCOLLECTORLIST']/a")).click();

		jse = (JavascriptExecutor) driver;

		//search 
		driver.findElement(By.xpath("//*[@id = 'dt-allcollectorData_filter']/label/input")).sendKeys("395");

		//case double click
		WebElement cas395 = driver.findElement(By.xpath("//td[text() = '395']"));

		Actions act = new Actions(driver);
		act.doubleClick(cas395).build().perform();

		Thread.sleep(3000);
		WebElement remedial =driver.findElement(By.xpath("//ul[contains(@class ,'lst-section')]/li[8]/a"));

		jse.executeScript("arguments[0].scrollIntoView(true)", remedial);
		remedial.click();



	}
	@DataProvider(name = "writeOffData")
	public Object[][] writeOffData() 
	{
		return new Object[][] 
				{
			// finalAmt , writeOffReason ,writeOffDate, tranback , npaCategory , remarks , 
			{"2000", "4-Customer",  "27-01-2026", "good","Doubtful","Test case 1"},
			{"3000", "3-Suspected as Criminal", "28-01-2026", "better","Loss", "Test case 2"},
			{"4000", "2-Suspected as Terrorist" , "29-01-2026", "best","Substandard", "Test case 3"}
				};
	}

	@Test(dataProvider = "writeOffData")
	public void createWriteOff(
			String finalAmt,
			String writeOffReason,
			String writeOffDate,
			String tranback,
			String npaCategory,
			String remarks
			) throws Exception {

		driver.switchTo().defaultContent();
		Actions act = new Actions(driver);

		WebElement writeoff =driver.findElement(By.xpath("//ul[@id='myTab']/li[6]/a"));
		act.doubleClick(writeoff).build().perform();
		driver.switchTo().frame("fetchCaseWriteOffDtlsPageFrame");

		// Final Settlement Amount
		WebElement finalSettlementAmt = driver.findElement(By.id("finalSettlementAmt"));
		finalSettlementAmt.clear();
		finalSettlementAmt.sendKeys(finalAmt);

		// Write Off Reason (dropdown)
		WebElement Write = driver.findElement(By.id("writeOffReason"));
		Select reason = new Select(Write);
		reason.selectByVisibleText(writeOffReason);

		// Write Off Date
		WebElement date = driver.findElement(By.id("writeOffDate"));
		date.clear();
		date.sendKeys(writeOffDate);
		date.sendKeys(Keys.TAB);

		//transactionBackground
		WebElement transactionBackground = driver.findElement(By.id("transactionBackground"));
		transactionBackground.clear();
		transactionBackground.sendKeys(tranback);

		// NPA Category (dropdown)
		WebElement npaCategory2 = driver.findElement(By.id("npaCategory"));
		Select npa = new Select(npaCategory2);
		npa.selectByVisibleText(npaCategory);

		// Remarks
		WebElement remarks2 =  driver.findElement(By.id("remarks"));
		remarks2.clear();
		remarks2.sendKeys(remarks);



		Thread.sleep(2000); // avoid in real tests, use WebDriverWait instead
		finalSettlementAmt.clear();
		//  Write.clear(); dropdown doesnot support clear
		date.clear();
		date.sendKeys(Keys.TAB);
		transactionBackground.clear();
		// npaCategory2.clear(); dropdown doesnot support clear
		remarks2.clear();

		driver.findElement(By.id("save")).click();
		Thread.sleep(800);
		jse.executeScript("window.scrollBy(0,-700)");

		driver.navigate().refresh();




	}
}