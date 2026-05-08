package seleniumClasses;

import java.util.concurrent.TimeUnit;

import org.apache.log4j.Logger;
import org.apache.log4j.PropertyConfigurator;
import org.openqa.selenium.By;
import org.openqa.selenium.Keys;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

public class Log4j_4 {
	WebDriver driver;

	@BeforeClass
	public void setup() throws Exception
	{
		
		Logger logger = Logger.getLogger("Log4j_4");
		
		PropertyConfigurator.configure("C:\\Users\\Yogesh.Pole\\eclipse-workspace\\Demo\\log4j.properties");
		
		System.setProperty("webdriver.chrome.driver","D:\\chromedriver144\\chromedriver-win64\\chromedriver.exe");
		driver = new ChromeDriver();
		logger.info("Chrome Driver Launched");
		
		driver.manage().window().maximize();
		logger.info("Maximized");
		
		driver.manage().timeouts().implicitlyWait(15, TimeUnit.SECONDS);
		logger.info("Implicit Wait");
		
		Thread.sleep(1000);
		driver.get(	"http://172.21.0.93:7723/lcs-finairoLending-1.0.1/");
		logger.info("Navigation on webPage");
		
		Thread.sleep(2000);

		driver.findElement(By.id("languageCode")).sendKeys(Keys.TAB);
		driver.findElement(By.id("loginId")).sendKeys("KCO1");
		driver.findElement(By.id("loginId")).sendKeys(Keys.TAB);
		logger.info("User ID entered");
		
		driver.findElement(By.id("uiPwd")).sendKeys(Keys.TAB);
		Thread.sleep(1000);
		logger.info("pwd entered");
		
		driver.findElement(By.id("userLogin")).click();
		Thread.sleep(4000);
        logger.info("Looged In");
		
		//Burger
		driver.findElement(By.xpath("//*[@class='item-nav']/div")).click();
		logger.info("HamBurger Clicked");
		
		
		
		
	}
	@Test
	public void test1()
	{
		
	}

}
