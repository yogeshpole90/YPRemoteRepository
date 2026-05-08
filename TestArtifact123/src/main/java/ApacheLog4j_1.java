import java.util.concurrent.TimeUnit;
import java.util.logging.Logger;

import org.apache.log4j.PropertyConfigurator;
import org.openqa.selenium.By;
import org.openqa.selenium.Keys;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
public class ApacheLog4j_1 {

	public static void main(String[] args) {
		//


		Logger logger  = Logger.getLogger("ApacheLog4j_1");
		PropertyConfigurator.configure("C:\\Users\\Yogesh.Pole\\eclipse-workspace\\TestArtifact123\\src\\main\\resources\\log4j.properties");
        
		//Setup
		System.setProperty("webdriver.chrome.driver", "D:/chromedriver-win64/chromedriver-win64/chromedriver.exe");
		WebDriver driver  =new ChromeDriver();
		driver.manage().timeouts().implicitlyWait(12,TimeUnit.SECONDS);
		
		//Max.
		driver.manage().window().maximize();
		logger.info("maximized");
		
		//navigate
		driver.get("http://172.21.0.46:8181/lcs-finairoLending-1.0.1");
		logger.info("Navigated to Site");
		
		//login
		
		//id
		driver.findElement(By.id("loginId")).sendKeys("Dora");
		logger.info("Id entered :- Dora ");
		
		//pwd
		driver.findElement(By.id("uiPwd")).clear();
		driver.findElement(By.id("uiPwd")).sendKeys("abcde@12345");
		driver.findElement(By.id("uiPwd")).sendKeys(Keys.TAB);
		logger.info("Pwd entered :- abcde@12345 ");

		//clicked 
		driver.findElement(By.id("userLogin")).click();
		logger.info("Logged in Successfully");
		
		




		
		
		
		
		    
		}

		
	}


