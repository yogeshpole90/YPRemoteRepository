package SeleniumPackage;

import java.util.concurrent.TimeUnit;

import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.Keys;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.Select;
import org.openqa.selenium.support.ui.WebDriverWait;

public class EBIDRemedial {

	public static void main(String[] args) throws Exception {
		// TODO Auto-generated method stub
		System.setProperty("webdriver.chrome.driver","D:\\chromedriver-win64\\chromedriver-win64\\chromedriver.exe");
		
		WebDriver driver = new ChromeDriver();
		
		driver.manage().window().maximize();
		driver.manage().timeouts().implicitlyWait(10, TimeUnit.SECONDS);
		Thread.sleep(1000);
		driver.get(	"http://172.21.0.46:8181/lcs-finairoLending-1.0.1");
		Thread.sleep(2000);
		
		//driver.findElement(By.id("loginId")).sendKeys(Keys.TAB);
		driver.findElement(By.id("loginId")).sendKeys("Shelly");
		driver.findElement(By.id("loginId")).sendKeys(Keys.TAB);
		driver.findElement(By.id("uiPwd")).sendKeys(Keys.TAB);
		Thread.sleep(1000);
		driver.findElement(By.id("userLogin")).click();
		Thread.sleep(4000);

		//burger button click
		driver.findElement(By.xpath("//a[@class='item-nav']/div")).click();
		Thread.sleep(2000);
		//inbox list clicked
		driver.findElement(By.xpath("//li[@id='COLLECTORLIST']/a")).click();
		
		//locate case
		Thread.sleep(4000);
		WebElement case2 = driver.findElement(By.xpath("//tr/td[text() = '10314']"));

		//actions class
		Actions act = new Actions(driver);
		act.doubleClick(case2).build().perform();
		
		//Remedial act - Vertical Menu
		JavascriptExecutor jse = (JavascriptExecutor) driver ;
		WebElement remedial = driver.findElement(By.xpath("//ul[contains(@class,'border-0')]/li[8]/a"));
		jse.executeScript("arguments[0].scrollIntoView({block : 'center'})", remedial);
		
		Thread.sleep(2000);
		remedial.click();
		
		Thread.sleep(3000);
	WebElement ptp = driver.findElement(By.xpath("//a[contains(text(),'Promise to pay')]"));
		jse.executeScript("arguments[0].scrollIntoView({block : 'center'})", ptp);
		
		act.doubleClick(ptp).build().perform();
		
		Thread.sleep(1000);
		
	
		
		driver.switchTo().frame("fetchPTPMstTabFrame");
		WebElement downpay = driver.findElement(By.xpath("//em[text()='Downpayment + Schedule PTP']"));
		jse.executeScript("arguments[0].scrollIntoView({block : 'center'})", downpay);
		Thread.sleep(2000);
		downpay.click();
		
		driver.switchTo().parentFrame();
		jse.executeScript("window.scrollBy(0,1000)");
		
		driver.switchTo().frame("fetchPTPMstTabFrame");
		//enter data in fields
		WebElement planned = driver.findElement(By.id("plannedAmt"));
		
		//planned amount
		planned.clear();
		planned.sendKeys("24000");
		
		//payment mde
		Thread.sleep(1000);
		WebElement paymode = driver.findElement(By.xpath("//select[@id='paymentMode']"));
		Select mode=new Select(paymode);
		mode.selectByVisibleText("Account Transfer");
		
		//remain amount
		driver.findElement(By.id("remAmt")).sendKeys("1000");
		//planned date -1
		WebElement plandate1 = driver.findElement(By.id("planDate1"));
		plandate1.sendKeys("20-01-2022");
		plandate1.sendKeys(Keys.TAB);
		Thread.sleep(1000);
		//planned amt -1
		driver.findElement(By.xpath("//input[@id='plannedAmt1']")).sendKeys("1000");
		//payment mode -1
		WebElement paymode1 = driver.findElement(By.id("paymentMode1"));
		Select paymode2 =new Select(paymode1);
		paymode2.selectByVisibleText("Account Transfer");
		
		driver.findElement(By.id("add3")).click();
		
		driver.findElement(By.id("saveData")).click();
		
		WebElement searchchild = driver.findElement(By.xpath("(//input[@aria-controls='dt-basicDetails'])"));
		jse.executeScript("arguments[0].scrollIntoView({block:'center'})", searchchild);
		
		
		driver.switchTo().parentFrame();
		jse.executeScript("window.scrollBy(0,-70)");
		
		//new Section - FNF
		Thread.sleep(2000);
		 WebElement fnf = driver.findElement(By.xpath("(//ul[@id='myTab']/li)[4]"));
		Actions act1 = new Actions(driver);
		act1.doubleClick(fnf).build().perform();
		jse.executeScript("window.scrollBy(0,1000)");
		
		//swicth to child
		Thread.sleep(1000);
		driver.switchTo().frame("addSettlementMstFNFFrame");//addSettlementMstFNFFrame
		
		//fnf amt
		driver.findElement(By.id("willingToPaySettlementAmt")).sendKeys("1000");
		//fnf date
		/*
		 * WebElement fnfdate = driver.findElement(By.id("preCloseDate"));
		 * fnfdate.sendKeys("Jan 1, 2026"); fnfdate.sendKeys(Keys.TAB);
		 */
		
		WebElement date = driver.findElement(By.id("preCloseDate"));

		
		jse.executeScript("arguments[0].value='Jan 9, 2026';", date);

		
		Thread.sleep(1000);
		//charges waived - dd
		WebElement chargewaive = driver.findElement(By.id("chargesToBeWaved"));
		Select cw = new Select(chargewaive);
		cw.selectByVisibleText("no");
		
		Thread.sleep(500);
		//charges TType
		WebElement chargetype = driver.findElement(By.id("chargeType"));
		Select ct = new Select(chargetype);
		ct.selectByVisibleText("Penalty interest");
		Thread.sleep(500);
		
		driver.findElement(By.id("totalcharges")).sendKeys("120");

		//remarks
		driver.findElement(By.id("remarks")).sendKeys("Tested by Selenium");
		
		driver.findElement(By.xpath("(//button[@id='save1'])")).click();
		
		
		//swicth to parent
		Thread.sleep(3000);
		driver.switchTo().parentFrame();
		jse.executeScript("window.scrollBy(0,-400)");
		
		//PARTIAL SETTLEMENT
		
		WebElement partial = driver.findElement(By.xpath("(//ul[@id='myTab'])/li[5]"));
		act.doubleClick(partial).build().perform();
		
		//jse.executeScript("window.scrollBy(0,800)");
		Thread.sleep(1000);
		
		//CHILD frame swicth
	    driver.switchTo().frame("addSettlementMstFrame");
		
		Thread.sleep(3000);
		
		//Partial amount
		driver.findElement(By.id("partlRepmntAmnt")).sendKeys("3000");
	
		//Type of partial repayment
		WebElement typeofpar = driver.findElement(By.id("typeOfPartlRepmnt"));
		Select s1 = new Select(typeofpar);
		s1.selectByVisibleText("Unchange Tenure");
		
		
		//reason for payment
		WebElement reason = driver.findElement(By.id("reasonOfPayoff"));
		Select s2  = new Select(reason);
		s2.selectByVisibleText("Other Reasons");
		
		driver.findElement(By.id("collectionOfficerRemark")).sendKeys("Tested by Selenium");
		
		
		driver.findElement(By.id("save")).click();
		
		
		Thread.sleep(3000);
		//PARENT FRAME
		driver.switchTo().parentFrame();
		jse.executeScript("window.scrollBy(0,-200)");
		
		//new section = Write-Off
		WebElement writeoff = driver.findElement(By.xpath("(//ul[@id='myTab'])/li[6]"));
		act.doubleClick(writeoff).build().perform();
	//	jse.executeScript("window.scrollBy(0,100)");
		Thread.sleep(1000);
		
		//CHILD
		driver.switchTo().frame("fetchCaseWriteOffDtlsPageFrame");
		/*
		 * WebDriverWait wait = new WebDriverWait(driver, 10);
		 * wait.until(ExpectedConditions.frameToBeAvailableAndSwitchToIt(
		 * "addSettlementMstFrame"));
		 *///FINAL STMT AMT
		
		
		Thread.sleep(2000);
		WebElement finalstmt = driver.findElement(By.xpath("//input[@id='finalSettlementAmt']"));
		jse.executeScript("arguments[0].scrollIntoView({behavior:'smooth', block: 'center'})", finalstmt);
		Thread.sleep(1000);
		finalstmt.clear();
		finalstmt.sendKeys("489800");
		
		//WRITE OFF REASON
		WebElement offreason = driver.findElement(By.id("writeOffReason"));
		Select s3 = new Select(offreason);
		
		s3.selectByVisibleText("2-Suspected as Terrorist");
		
		//write off date
		driver.findElement(By.id("writeOffDate")).sendKeys("03-02-2021");
		//tran background
		driver.findElement(By.id("transactionBackground")).sendKeys("Good 5 Star");
		
		//NPA category
		WebElement npacate = driver.findElement(By.id("npaCategory"));
		Select s4 = new Select(npacate);
		s4.selectByVisibleText("Watch list 1");
		
		//Remarks
		driver.findElement(By.id("remarks")).sendKeys("Tested by Selenium");
		//final stmt amt
		//driver.findElement(By.id("finalSettlementAmt")).sendKeys("48980");

		//SAVE
		Thread.sleep(2000);
		driver.findElement(By.id("save")).click();
		
		Thread.sleep(2000);
		
		//new section = CASE CLOSUREE
		//PARENT FRAME
		driver.switchTo().parentFrame();
		//jse.executeScript("window.scrollBy(0,-200)");
		WebElement Caseclose = driver.findElement(By.xpath("//ul[@id='myTab']/li[7]/a"));
		jse.executeScript("arguments[0].scrollIntoView({behavior:'smooth', block:'center'})", Caseclose);
		act.doubleClick(Caseclose).build().perform();
		Thread.sleep(2000);
		//NEW CHILD FRAME = CASE CLOSURE
		driver.switchTo().frame("fetchCaeCloseDtlsPageFrame");	
		
		jse.executeScript("window.scrollBy(0,800)");
		//case close reason
		WebElement closereason = driver.findElement(By.xpath("//select[@id='caseCloserReason']"));
		Thread.sleep(2000);
		
		Select s5 =new Select(closereason);
		s5.selectByVisibleText("case Closed");
		//case close remarks
		driver.findElement(By.id("caseCloserRemark")).sendKeys("");
		
		Thread.sleep(2000);
		driver.findElement(By.id("save")).click();
		
		
		
		
		
		
		
		
		
		
		
		
		
		
		
		
		
		
		
		
		
		
		
		
		
		
		
		
		
		
		
		
		
		
		
		
		


		
	
		

	}

}
