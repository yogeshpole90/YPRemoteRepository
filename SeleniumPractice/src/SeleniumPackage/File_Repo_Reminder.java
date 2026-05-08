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
import org.openqa.selenium.interactions.Actions;
import org.testng.annotations.BeforeClass;

public class File_Repo_Reminder {
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
		File fs1 = new File("C:\\Users\\Yogesh.Pole\\eclipse-workspace\\SeleniumPractice\\Reminder_Repo\\Remider1");
		
		//load = FileInputStream
		FileInputStream fis = new FileInputStream(fs1);
		
		
		//read = Property
		Properties pro1 = new Properties();
		pro1.load(fis);
		jse = (JavascriptExecutor) driver;
		driver.findElement(By.id(pro1.getProperty("user"))).sendKeys("Shelly");
		driver.findElement(By.id(pro1.getProperty("user"))).sendKeys(Keys.TAB);
		driver.findElement(By.id(pro1.getProperty("pass"))).sendKeys(Keys.TAB);
		
		driver.findElement(By.id(pro1.getProperty("login"))).click();
		
		
		//burger
		driver.findElement(By.xpath(pro1.getProperty("burger"))).click();
		
		//collector list
		driver.findElement(By.xpath(pro1.getProperty("cl"))).click();
		
		//case double clicked
		WebElement cas21207 = driver.findElement(By.xpath(pro1.getProperty("21207")));
		Actions ac= new Actions(driver);
		ac.doubleClick(cas21207).build().perform();
		
		//communication his  - menu
		WebElement comhis1 = driver.findElement(By.xpath(pro1.getProperty("commuhis")));
		jse.executeScript("arguments[0].scrollIntoView({block:'center'})", comhis1);
		comhis1.click();
		
		//default frames
		driver.switchTo().defaultContent();
		
		//Child frame
		driver.switchTo().frame("fetchReminderDtlsPageFrame");
		
		//reminder
		WebElement reminder1 = driver.findElement(By.xpath("//ul[@id='myTab']/li/a"));
		
		jse.executeScript("arguments[0].scrollIntoView({block:'center',behavior:'smooth'})", reminder1);
		
		reminder1.click();
		
		//iframe
		driver.switchTo().frame("fetchReminderDtlsPageFrame");
	
	}

}
