package SeleniumPackage;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.util.Properties;
import java.util.concurrent.TimeUnit;

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

public class EBID_Police_Datapro1 {
	
	WebDriver driver;
	JavascriptExecutor jse;
	File fs ;
	FileInputStream fis;
	Properties pro;
	Actions act;
	
	@BeforeClass
	public void Police() throws Exception
	{
		System.setProperty("webdriver.chrome.driver", "D:\\chromedriver-145\\chromedriver-win64\\chromedriver.exe");
		driver= new ChromeDriver();
		driver.manage().window().maximize();
		driver.manage().timeouts().implicitlyWait(15, TimeUnit.SECONDS);
		driver.get("http://172.21.0.46:8181/lcs-finairoLending-1.0.1");
		
		driver.findElement(By.id("loginId")).sendKeys("Dora");
		driver.findElement(By.id("loginId")).sendKeys(Keys.TAB);

		driver.findElement(By.id("uiPwd")).sendKeys("abcde@12345");

		driver.findElement(By.id("uiPwd")).sendKeys(Keys.TAB);

		driver.findElement(By.id("userLogin")).click();
		
		//file path > load  > Read
		fs = new File("C:/Users/Yogesh.Pole/eclipse-workspace/SeleniumPractice/FNF_Repo/Police_Repo");

		fis = new FileInputStream(fs);
		
		pro = new Properties();
		pro.load(fis);
		
		
		//Burger Icon
		driver.findElement(By.xpath(pro.getProperty("burger"))).click();
		
		
		//Inbox List
		driver.findElement(By.xpath(pro.getProperty("inbox"))).click();
		
		//case 19791
		WebElement cas19791 = driver.findElement(By.xpath(pro.getProperty("cas19791")));
		act = new Actions(driver);
		act.doubleClick(cas19791).build().perform();
		
		//police complaint = menu
		driver.findElement(By.xpath(pro.getProperty("police"))).click();
		
		
		
	
		
	}
	@DataProvider
	public Object[][] Giver()
	{
		return new Object[][]
	 {
				{"787891","CAR", "After Care","26-01-2026","Arrears fully settled","29-01-2026","150","Case with Public Prosecution"},
				{"9292","CHEATING CASE","Auction/Bid Asset","27-01-2026","Account regularised","30-01-2026","@@12","Waiting for Prosecution"},
				{"CASE@93","CIVIL CASE","Auction/Bid Asset","28-01-2026","Account back to Normal","31-01-2026","ABC","Case with Public Prosecution"},
				{"787894","PDC","BU Form @ DCRCM","29-01-2026"," SLA Contact","29-01-2026","150RS","Waiting for Prosecution"},
				{"787895","VEHICLE CASE","BU Memo @ DCRCM","29-01-2026","Arrears fully settled","30-01-2026","123,00","Case with Public Prosecution"}
		
	  };
    }
		
	@Test(dataProvider = "Giver",priority = 1)
	public void test(String caseno,String caseTpe1,String acttkn,String actdate,
			String conRslt,String resultDt,String policeactamt,String proseStat1) throws Exception
	{
		System.out.println("Test");
		//Parent frame
		driver.switchTo().defaultContent();
		//child frame
		driver.switchTo().frame("viewPoliceComplaintRegisterFrame");
		
		//policeCaseNo
		WebElement pcaseno = driver.findElement(By.id("policeCaseNo"));
		pcaseno.sendKeys(caseno);
		
		//caseType
		WebElement caseTpe = driver.findElement(By.xpath(pro.getProperty("casetype2")));
		Select s1= new Select(caseTpe);
		s1.selectByVisibleText(caseTpe1);
		
		//actionTaken
		WebElement actionTkn = driver.findElement(By.xpath(pro.getProperty("actionTaken")));
		Select s2 = new Select(actionTkn);
		s2.selectByVisibleText(acttkn);
		
		//actionDate
		WebElement actiondate = driver.findElement(By.id("actionDate"));
		actiondate.sendKeys(actdate);
		
		
		//contactResult
		WebElement contactRes = driver.findElement(By.xpath(pro.getProperty("contactResult")));
		Select s3 = new Select(contactRes);
		s3.selectByVisibleText(conRslt);
		
		//resultDate
		driver.findElement(By.id("resultDate")).sendKeys(resultDt);
		
		driver.findElement(By.id("resultDate")).sendKeys(Keys.TAB);

		
		//policeActionAmount
		WebElement wactamount = driver.findElement(By.id("policeActionAmount"));
		wactamount.sendKeys(policeactamt);
		
		//prosecutionStatus
		WebElement proseStat = driver.findElement(By.xpath(pro.getProperty("prosecutionStatus")));
		Select s5 = new Select(proseStat);
		s5.selectByVisibleText(proseStat1);
		
		Thread.sleep(2000);
		//saveData
		driver.findElement(By.id("saveData")).click();
		Thread.sleep(1000);
		
		pcaseno.clear();
		wactamount.clear();
		
		driver.navigate().refresh();
		Thread.sleep(1000);
		
		
	}

}
