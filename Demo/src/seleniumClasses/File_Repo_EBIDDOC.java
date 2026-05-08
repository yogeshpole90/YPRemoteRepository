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

public class File_Repo_EBIDDOC{
	
	WebDriver driver;

	@BeforeClass
	public void setup() throws Exception {
		
		//locate file
		File fs = new File("C:\\Users\\Yogesh.Pole\\eclipse-workspace\\Demo\\Object_Repository\\File_Properties");
		
		//load file
		FileInputStream fis = new FileInputStream(fs);
		
		//read file
		Properties pro =new Properties();
		pro.load(fis);
		
		
		System.setProperty("webdriver.chrome.driver","D:\\chromedriver-win64\\chromedriver-win64\\chromedriver.exe");
		 driver = new ChromeDriver();
		driver.manage().window().maximize();
		driver.manage().timeouts().implicitlyWait(10, TimeUnit.SECONDS);
		Thread.sleep(1000);
		driver.get(	"http://172.21.0.46:8181/lcs-finairoLending-1.0.1");
		Thread.sleep(2000);
		
		driver.findElement(By.id(pro.getProperty("loginId"))).sendKeys("Shelly");
		driver.findElement(By.id(pro.getProperty("loginId"))).sendKeys(Keys.TAB);
		driver.findElement(By.id(pro.getProperty("pass"))).sendKeys(Keys.TAB);
		Thread.sleep(1000);
		driver.findElement(By.id(pro.getProperty("login"))).click();
		Thread.sleep(4000);
		
		//burger
		driver.findElement(By.xpath(pro.getProperty("burger"))).click();
		
		//collector list
		driver.findElement(By.xpath(pro.getProperty("cl"))).click();
		
		//case double click
	WebElement cas9842 = driver.findElement(By.xpath(pro.getProperty("case1")));

		Actions act = new Actions(driver);
		act.doubleClick(cas9842).build().perform();
		
		
		//vertical-menu = document
		WebElement doc1 = driver.findElement(By.xpath(pro.getProperty("doc")));

		JavascriptExecutor jse = (JavascriptExecutor) driver;
		jse.executeScript("arguments[0].scrollIntoView({block: 'center',behavior:'smooth'})",doc1 );
		
		Thread.sleep(1000);
		doc1.click();
		
		// Child frame : documentUploadPageFrame
		driver.switchTo().frame(pro.getProperty("childdoc"));
		
		WebElement actionName = driver.findElement(By.id("actionName"));
		jse.executeScript("arguments[0].scrollIntoView({block: 'center',behavior:'smooth'})", actionName);
		
	}
		@DataProvider
		public Object[][] doc()
		{
			
			return new Object [][]
		{
				{"Asset Repossession","Doc of Repossession","C:\\Users\\Yogesh.Pole\\Music\\COLLATERAL_SEIZED_LETTER.pdf"},
				{"Full & Final Settlement","Doc of FNF","C:/Users/Yogesh.Pole/Music/FNF_Certificate.pdf"},
				{"Partial Settlement","Doc of Partial stmnt","C:/Users/Yogesh.Pole/Music/PTP_LETTER.pdf"},
				{"Promise To Pay","Doc of PTP","C:/Users/Yogesh.Pole/Music/PTP_LETTER.pdf"},
				{"Release Asset","Doc of Release Asset","C:/Users/Yogesh.Pole/Music/PTP_LETTER.pdf"},
				{"Write Off","Doc of Case Write Off","C:/Users/Yogesh.Pole/Music/WRITE_OFF_LETTER.pdf"},
					
		};
		
		}
		@Test(dataProvider = "doc" ,priority = 1)
		public void enterdata(String actionName,String Docname , String upload) throws Exception
		{
			//actionName
			WebElement acname = driver.findElement(By.xpath("//select[@id='actionName']"));
			Select an = new Select(acname);
			an.selectByVisibleText(actionName);//from parameter
			
			//documentName
			WebElement Docname1 = driver.findElement(By.id("documentName"));
			Docname1.sendKeys(Docname);
			
			//document Upload
			WebElement upload1 = driver.findElement(By.id("documentData"));
			upload1.sendKeys(upload);
			
			//save
			driver.findElement(By.id("saveData")).click();
			
			Thread.sleep(2000);
			

			
			
		}
		@Test(priority = 2)
		public void delete() throws Exception
		{
			driver.findElement(By.xpath("(//a[contains(@onclick,'Release_Asset')])[3]")).click();
			driver.findElement(By.xpath("(//a[contains(@onclick,'Doc_of_Case_Write_Off')])[3]")).click();
			driver.findElement(By.xpath("(//a[contains(@onclick,'Doc_of_PTP')])[3]")).click();
			driver.findElement(By.xpath("(//a[contains(@onclick,'Doc_of_Partial_stmnt')])[3]")).click();
			driver.findElement(By.xpath("(//a[contains(@onclick,'Doc_of_FNF')])[3]")).click();
			driver.findElement(By.xpath("(//a[contains(@onclick,'Doc_of_Repossession')])[3]")).click();

		Thread.sleep(2000);
		
		}
		
}
		
		
		

		
		
		

