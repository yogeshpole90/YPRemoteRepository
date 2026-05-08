package SeleniumPackage;
//TAB IS MISSING
import java.io.File;
import java.util.concurrent.TimeUnit;

import org.apache.commons.io.FileUtils;
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

public class EBID_Legal {

	public static void main(String[] args) throws Exception {
		// TODO Auto-generated method stub
		System.setProperty("webdriver.chrome.driver","D:\\chromedriver-145\\chromedriver-win64\\chromedriver.exe");
		WebDriver driver = new ChromeDriver();
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
		Actions act =new Actions(driver);
		act.doubleClick(case1).build().perform();
		
		//vertical menu = legal process
		Thread.sleep(1000);
		WebElement legalprocess = driver.findElement(By.xpath("//*[contains(@href,'=Legal Process')]"));
		JavascriptExecutor jse =  (JavascriptExecutor) driver;
		jse.executeScript("arguments[0].scrollIntoView({behavior:'smooth',block:'center'})", legalprocess);
		legalprocess.click();
		
		//section = court case details 
		WebElement courtcase = driver.findElement(By.xpath("//ul[@id='myTab']/li/a"));
		act.doubleClick(courtcase).build().perform();
		//jse.executeScript("window.scrollBy(0,3000)");

		
		//switch into frame
		driver.switchTo().frame("courtCaseMstListPageFrame");

		//Thread.sleep(2000);
		//locate court Case Type
		WebElement casetype = driver.findElement(By.xpath("//select[@id='courtCaseType']"));
		jse.executeScript("arguments[0].scrollIntoView({block:'center'})", casetype);
		Select s1 = new Select(casetype);
		s1.selectByVisibleText("Civil");
		
		//suitAmount
		WebElement suitAmount = driver.findElement(By.id("suitAmount"));
		suitAmount.clear();
		suitAmount.sendKeys("120");
		
		//requestDate
		WebElement requestDate = driver.findElement(By.id("requestDate"));
		requestDate.sendKeys("22-01-2026");
		requestDate.sendKeys(Keys.TAB);
		
		//lawyerName
		Thread.sleep(1000);
		WebElement lawyerName = driver.findElement(By.id("lawyerName"));
		Select s2 = new Select(lawyerName);
		s2.selectByVisibleText("SeyChambers");
		
		//caseInitiatedBy
		WebElement caseInitiatedBy = driver.findElement(By.id("caseInitiatedBy"));
		Select s3 =new Select(caseInitiatedBy);
		s3.selectByVisibleText("Bank/Creditor");
		
		//allocatedDate
		WebElement allocatedDate = driver.findElement(By.id("allocatedDate"));
		allocatedDate.sendKeys("23-01-2026");
		allocatedDate.sendKeys(Keys.TAB);
		
		//lawFirmName
		driver.findElement(By.id("lawFirmName")).sendKeys("Selenium Firm");
		
		//dcHandleLawyerDate
		WebElement dcHandleLawyerDate = driver.findElement(By.id("dcHandleLawyerDate"));
		dcHandleLawyerDate.sendKeys("22-01-2026");
		dcHandleLawyerDate.sendKeys(Keys.TAB);
		
		//bankruptcyCase
		WebElement bankruptcyCase = driver.findElement(By.id("bankruptcyCase"));
		
		Select s4 =new Select(bankruptcyCase);
		s4.selectByVisibleText("NO");
		
		//save
		Thread.sleep(3000);
		WebElement save1 = driver.findElement(By.id("save"));
		save1.click();
		
		WebElement search1 = driver.findElement(By.xpath("//input[@type='search']"));
		jse.executeScript("arguments[0].scrollIntoView({block:center})", search1);
		System.out.println("Court Case Details Saved Successfully");

		
		//Screenshot taking
		boolean save2 = driver.getPageSource().contains("Error");
		if(save2 == true) 
		{
			//Screenshot
			Thread.sleep(1000);
			TakesScreenshot ts = (TakesScreenshot) driver;
			File src = ts.getScreenshotAs(OutputType.FILE);
			File desc = new File("D:\\Screenshots\\ebid7_" + System.currentTimeMillis() +".png");

			FileUtils.copyFile(src, desc);
			System.out.println("As not saved  = SS taken");

		}
		//2 ::::New Section = Legal Diary

		//Parent frame
		driver.switchTo().parentFrame();
		WebElement legaldiary = driver.findElement(By.xpath("//ul[@id='myTab']/li[2]"));
		jse.executeScript("arguments[0].scrollIntoView({block: 'center'})", legaldiary);
		act.doubleClick(legaldiary).build().perform();
		
		//child frame
		driver.switchTo().frame("getLegalDiaryDataFrame");
		
		//Locate  caserefNo
		WebElement caserefNo = driver.findElement(By.id("courtCaseNo"));
		jse.executeScript("arguments[0].scrollIntoView({block: 'center'})", caserefNo);
		caserefNo.sendKeys("120055");
		
		//filingDate
		WebElement filingDate = driver.findElement(By.id("filingDate"));
		jse.executeScript("arguments[0].value='28-01-2026';", filingDate);
		//js.executeScript("arguments[0].value='Jan 9, 2026';", date);
		filingDate.sendKeys(Keys.TAB);
		
		//dateAllocated
		WebElement dateAllocated = driver.findElement(By.id("dateAllocated"));
		jse.executeScript("arguments[0].value='29-01-2026';", dateAllocated);
		dateAllocated.sendKeys(Keys.TAB);
		
		
		//courtCaseType
		WebElement courtCaseType = driver.findElement(By.xpath("//select[@id='courtCaseType']"));
		Select s5 =new Select(courtCaseType);
		s5.selectByVisibleText("Criminal");
		
		//courtFeeType
		WebElement courtFeeType = driver.findElement(By.xpath("//select[@id='courtFeeType']"));
		Select s6 = new Select(courtFeeType);
		s6.selectByVisibleText("Lawyer’s fees and charges");
		
		//courtFee
		driver.findElement(By.id("courtFee")).sendKeys("120");
		
		//processFee
		driver.findElement(By.id("processFee")).sendKeys("140");
		
		//replevinBondFee
		driver.findElement(By.id("replevinBondFee")).sendKeys("160");
		
		//executionFee
		driver.findElement(By.id("executionFee")).sendKeys("180");
		
		//petitionFee
		driver.findElement(By.id("petitionFee")).sendKeys("200");
		
		//otherFee
		driver.findElement(By.id("otherFee")).sendKeys("-200");
		
		//totalCourtFee
		driver.findElement(By.id("totalCourtFee")).sendKeys("-100");
		
		//documentsHandedToLawyerDate
		WebElement handeddate = driver.findElement(By.id("documentsHandedToLawyerDate"));
		handeddate.sendKeys("23-01-2026");
		handeddate.sendKeys(Keys.TAB);
		
		//remarks
		driver.findElement(By.id("remarks")).sendKeys("Tested by Selenium");
		
		//caseInitiatedby
		WebElement caseinitiatedby = driver.findElement(By.xpath("//select[@id='caseInitiatedby']"));
		Select s7 = new Select(caseinitiatedby);
		s7.selectByVisibleText("Bank/Creditor");
		
		//bankruptcyCase
		WebElement bankruptcyCas = driver.findElement(By.xpath("(//select[@id='bankruptcyCase'])"));
		Select s8 = new Select(bankruptcyCas);
		s8.selectByVisibleText("NO");
		
		//hearingDate
		WebElement hearingDate = driver.findElement(By.id("hearingDate"));
		hearingDate.sendKeys("29-01-2026");
		hearingDate.sendKeys(Keys.TAB);
		
		//nextHearingDate
		WebElement nextHearingDate = driver.findElement(By.id("nextHearingDate"));
		nextHearingDate.sendKeys("29-01-2026");
		nextHearingDate.sendKeys(Keys.TAB);
		
		//transferredToPrivateExecutordiv
		WebElement trnsfertoPvt = driver.findElement(By.xpath("//select[contains(@id,'transferredToPrivateExecutor')]"));
		Select s9 = new Select(trnsfertoPvt);
		s9.selectByVisibleText("NO");
		
		//status
		jse.executeScript("window.scrollBy(0,70)");
		driver.findElement(By.id("status")).sendKeys("Passed");
		
		//saveData
		Thread.sleep(3000);
		driver.findElement(By.id("saveData")).click();
		Thread.sleep(2000);
		
		boolean save3 = driver.getPageSource().contains("Saved");
		
		//Screenshot
		if(save3==false)
		{
			
		TakesScreenshot ts1 = (TakesScreenshot) driver;
		File src1 = ts1.getScreenshotAs(OutputType.FILE);
		File desc1 = new File("D:\\Screenshots\\ebid3_" + System.currentTimeMillis() +".png");
		FileUtils.copyFile(src1, desc1);
		 
		}
		
		//### 3 new Section : legal order details
		Thread.sleep(2000);
		driver.switchTo().parentFrame();
		
      	// legal order clicked
		WebElement legalorder = driver.findElement(By.xpath("//*[@id='myTab']/li[3]/a"));
		jse.executeScript("arguments[0].scrollIntoView({block:'center'})", legalorder);
		act.doubleClick(legalorder).build().perform();
		
		//Switch to Child
		driver.switchTo().frame("getLegalDetailDataFrame");
		
		//loanAcNo
		WebElement loanAcNo = driver.findElement(By.xpath("//select[@id='loanAcNo']"));
		jse.executeScript("window.scrollBy(0,90)");
		Select s10 = new Select(loanAcNo);
		s10.selectByVisibleText("006607000122");
		//
		
		//orderType
		WebElement orderType = driver.findElement(By.xpath("//select[@id='orderType']"));
		Select s11 = new Select(orderType);
		s11.selectByVisibleText("Judgment");
		
		//orderDate
		WebElement orderDate = driver.findElement(By.id("orderDate"));
		orderDate.sendKeys("09-01-2026");
		
		//cancellationDate
		driver.findElement(By.id("cancellationDate")).sendKeys("09-01-2026");
		
		//remark
		driver.findElement(By.id("remark")).sendKeys("Tested by Selenium");
		
		//saveBtn
		Thread.sleep(3000);
		driver.findElement(By.id("saveBtn")).click();
		
		
		//###new section 4 : Fees and Charges
		driver.switchTo().parentFrame();
		
		//fees and charges - clicked
		WebElement fees = driver.findElement(By.xpath("//*[@id='myTab']/li[4]/a"));
		jse.executeScript("arguments[0].scrollIntoView({block:'center'})", fees);
		act.doubleClick(fees).build().perform();
		
		//Child FRAME
		driver.switchTo().frame("viewFessAndChargeFrame");
		
		//locate chargeName
		WebElement chargeName = driver.findElement(By.id("chargeName"));
		Select s12 =new Select(chargeName);
		s12.selectByVisibleText("Appeal Fees");
		
		//event
		driver.findElement(By.id("event")).sendKeys("Travel Ban Seized");
		
		
		//payableAmount
		driver.findElement(By.id("payableAmount")).sendKeys("10000");
		
		//expenseDate
		driver.findElement(By.id("expenseDate")).sendKeys("24-01-2026");
		
		//remarks
		driver.findElement(By.id("remarks")).sendKeys("Tested by Selenium");
		
		//
		Thread.sleep(3000);
		driver.findElement(By.id("saveFessCharge")).click();
		
		//ss
		boolean save4 = driver.getPageSource().contains("Error");
		if(save4==false)
		{
			TakesScreenshot ts4 = (TakesScreenshot) driver;
			File src4 = ts4.getScreenshotAs(OutputType.FILE);
			File dec4 = new File("D:\\Screenshots\\ebid3_" + System.currentTimeMillis() +".png");
			FileUtils.copyFile(src4, dec4);
			System.out.println("charges ss taken");
			
		}

		
		System.out.println("Fees and Charges Record Saved Successfully");
		
		
		//
		
		
		
		
		
		
		
		
		
		
		
		
		
		
		

	}

}
