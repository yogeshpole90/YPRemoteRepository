package seleniumClasses;

import java.io.File;
import java.io.FileInputStream;
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

public class File_Repo_FollowUp {
	WebDriver driver;
	JavascriptExecutor jse ;

	@BeforeClass
	public void setup() throws Exception
	{
	
		System.setProperty("webdriver.chrome.driver","D:/chromedriver-win64/chromedriver-win64/chromedriver.exe" );
		driver = new ChromeDriver();
		driver.manage().window().maximize();

		driver.manage().timeouts().implicitlyWait(10, TimeUnit.SECONDS);
		driver.get("http://172.21.0.46:8181/lcs-finairoLending-1.0.1/");
		//http://172.21.0.46:8181/lcs-finairoLending-1.0.1/
		//path Define
		File fs = new File("C:/Users/Yogesh.Pole/eclipse-workspace/Demo/FollowUp_Repo/Followup");
		
		//load = FileInputStream
		FileInputStream fis = new FileInputStream(fs);
		
		
		//read = Property
		Properties pro = new Properties();
		pro.load(fis);
		jse = (JavascriptExecutor) driver;
		driver.findElement(By.id(pro.getProperty("user"))).sendKeys("Shelly");
		driver.findElement(By.id(pro.getProperty("user"))).sendKeys(Keys.TAB);
		driver.findElement(By.id(pro.getProperty("pass"))).sendKeys(Keys.TAB);
		
		driver.findElement(By.id(pro.getProperty("login"))).click();
		
		
		//burger
		driver.findElement(By.xpath(pro.getProperty("burger"))).click();
		
		//collector list
		driver.findElement(By.xpath(pro.getProperty("cl"))).click();
		
		//case double clicked
		WebElement cas21207 = driver.findElement(By.xpath(pro.getProperty("21207")));
		Actions ac= new Actions(driver);
		ac.doubleClick(cas21207).build().perform();
		
		//follow up - menu
		WebElement follow = driver.findElement(By.xpath(pro.getProperty("follow")));
		
		JavascriptExecutor jse = (JavascriptExecutor) driver;
		jse.executeScript("arguments[0].scrollIntoView({block:'center',behavior:'smooth'})", follow);
		
		follow.click();
		
		//iframe
		driver.switchTo().frame("addcommunicationHistoryFrame");
	
		
	}
		@DataProvider
		public Object[][] followup()
		{
			
			return new Object[][]
					{
				{"CALL","SMS Borrower","Seize Asset","002103000207","POLICE","Yogesh1","Pass1","Test1"},
				{"EMAIL","Site Visit","Review case","002103000207","LEGAL","Yogesh2","Pass2","Test2"},
				{"Other","Promise to pay","Others","002103000207","FOLLOWUP","Yogesh3","Pass3","Test3"},
				{"SMS","Seize Asset","No Contact","002103000207","POLICE","Yogesh4","Pass4","Test4"},
				{"Visit","Review case","Changed address","002103000207","LEGAL","Yogesh5","Pass5","Test5"}

					};
			
			
		}
		@Test(dataProvider = "followup",priority = 1)
		public void taker(String commu,String actn,String result,String loanacc,String cshtype,
				String contact,String status,String remark)
		{
			
			driver.switchTo().parentFrame();
			
			//iframe
			driver.switchTo().frame("addcommunicationHistoryFrame");
			
			//communication Type
			WebElement commType = driver.findElement(By.id("communicationType"));
			jse.executeScript("arguments[0].scrollIntoView({block:'center',behavior:'smooth'})", commType);
			Select s1 = new Select(commType);
			s1.selectByVisibleText(commu);
		
			//action name
			WebElement action = driver.findElement(By.id("action"));
			Select s4 = new Select(action);
			s4.selectByVisibleText(actn);
			
			
			//Result
			WebElement result1 = driver.findElement(By.id("callStatus"));
			Select s5 = new Select(result1);
			s5.selectByVisibleText(result);
			

			
			
	    //loan acc number
			Select s = new Select(driver.findElement(By.id("loanAcNoSelect")));
			//s.selectByVisibleText("002103000207");
			s.selectByVisibleText(loanacc);
		//002103000207
			
			//cashType
			WebElement cashType = driver.findElement(By.id("cashType"));
			Select s2 = new Select(cashType);
			s2.selectByVisibleText(cshtype);
			
			//partyContactName
			WebElement contact1 = driver.findElement(By.id("partyContactName"));
			contact1.sendKeys(contact);
			
			//status
			WebElement status1 = driver.findElement(By.id("status"));
			status1.sendKeys(status);
			
			//remark
			WebElement remark1 = driver.findElement(By.id("remark"));
			remark1.sendKeys(remark);
			
			//save
			driver.findElement(By.id("saveData")).click();
			
			//clear
			status1.clear();
			contact1.clear();
			remark1.clear();

			
			
		
	    }
		
		@Test(priority = 3)
		public void calender1() throws Exception
		{
			Thread.sleep(3000);
			driver.switchTo().parentFrame();
			WebElement calender2 = driver.findElement(By.xpath("//ul[contains(@class ,'border-0')]/li[4]/a"));
			jse.executeScript("arguments[0].scrollIntoView({block:'center'})", calender2);
			
			Thread.sleep(2000);
			calender2.click();
		
		
		}
		
}
	


