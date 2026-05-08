package SeleniumPackage;

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
import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.ui.Select;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;


public class EBID_DocUpload {
	WebDriver driver;
	JavascriptExecutor jse;

	@BeforeClass
	public void setup() throws Exception {


		System.setProperty("webdriver.chrome.driver","D:\\chromedriver-145\\chromedriver-win64\\chromedriver.exe");
		
		ChromeOptions options = new ChromeOptions();
		options.addArguments("--incognito");
		driver = new ChromeDriver(options);

		driver.manage().window().maximize();
		driver.manage().timeouts().implicitlyWait(10, TimeUnit.SECONDS);
		
		
		driver.get(	"http://172.21.0.46:8181/lcs-finairoLending-1.0.1");

		//driver.findElement(By.id("loginId")).sendKeys(Keys.TAB);
		driver.findElement(By.id("loginId")).sendKeys("Dora");
		driver.findElement(By.id("loginId")).sendKeys(Keys.TAB);
		driver.findElement(By.id("uiPwd")).sendKeys("abcde@12345");
		driver.findElement(By.id("uiPwd")).sendKeys(Keys.TAB);
		Thread.sleep(1000);
		driver.findElement(By.id("userLogin")).click();
		Thread.sleep(4000);

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


		//vertical-menu = document
		WebElement doc = driver.findElement(By.xpath("//*[contains(@href,'activeTab=Document')]"));
		jse.executeScript("arguments[0].scrollIntoView({block:'center'})", doc);
		doc.click();
		
		//





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

		driver.switchTo().parentFrame();
		//documentUploadPageFrame
		driver.switchTo().frame("documentUploadPageFrame");

		//actionName
		WebElement acname = driver.findElement(By.xpath("//select[@id='actionName']"));
		jse.executeScript("arguments[0].scrollIntoView({block:'center'})", acname);
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

		Thread.sleep(1000);
		
		//
		WebElement search = driver.findElement(By.xpath("//*[@id='dt-basicDetails_filter']/label/input"));
		jse.executeScript("arguments[0].scrollIntoView({center:'center'})", search);
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










