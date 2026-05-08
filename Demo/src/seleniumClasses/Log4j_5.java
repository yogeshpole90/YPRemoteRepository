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

public class Log4j_5 {

	@BeforeClass
	public void setup()
	{
		Logger logger = Logger.getLogger("Log4j_5");
		PropertyConfigurator.configure("C:\\Users\\Yogesh.Pole\\eclipse-workspace\\Demo\\log4j.properties");

		//
		logger.info("Execution Starts...");
		System.setProperty("webdriver.chrome.driver", "D:\\chromedriver-145\\chromedriver-win64\\chromedriver.exe");

		logger.info("Instance Created...");
		WebDriver driver = new ChromeDriver();

		logger.info("Navigated to URL...");
		driver.get("http://172.21.0.46:8181/lcs-finairoLending-1.0.1");

		logger.info("Implictly wait added upto 15 Seconds...");
		driver.manage().timeouts().implicitlyWait(15, TimeUnit.SECONDS);

		logger.info("Maximized...");
		driver.manage().window().maximize();

		logger.info("ID Entered...");
		driver.findElement(By.id("loginId")).sendKeys("Dora");

		logger.info("Tab clicked...");
		driver.findElement(By.id("loginId")).sendKeys(Keys.TAB);

		driver.findElement(By.id("uiPwd")).sendKeys("abcde@12345");

		logger.info("pwd entered...");
		logger.info("Login btn clicked...");
		logger.info("Logged in Successfully...");
		logger.info("Navigated into Application...");
		logger.info("Successfully Navigated into Application...");
		

	}

	@Test
	public void test()
	{



	}


}

