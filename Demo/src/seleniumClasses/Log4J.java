package seleniumClasses;

import java.util.concurrent.TimeUnit;

import org.apache.log4j.Logger;
import org.apache.log4j.PropertyConfigurator;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;

//import org.apache.log4j.Logger;

public class Log4J {

	public static void main(String[] args) {
		// add this stmt =Logger logger = Logger and import
		Logger logger = Logger.getLogger("Log4J");
		
		//configure log4J properties file
		PropertyConfigurator.configure("C:\\Users\\Yogesh.Pole\\eclipse-workspace\\Demo\\log4j.properties");
		
		//Open Chrome Browser
		System.setProperty("webdriver.chrome.driver", "D:/chromedriver-win64/chromedriver-win64/chromedriver.exe");
		WebDriver driver= new ChromeDriver();
		logger.info("Chrome instance Enabled");
		
		//Max
		driver.manage().window().maximize();
		logger.info("Maximized window");
		
		//wait
		driver.manage().timeouts().implicitlyWait(12, TimeUnit.SECONDS);
		logger.info("Implicitly waited till 12 Sec ");
		
		//navigate
		driver.get("https://www.facebook.com");
		logger.info("Navigated to webPage");

	}

}
