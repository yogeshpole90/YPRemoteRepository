package seleniumClasses;

import org.apache.log4j.Logger;
import org.apache.log4j.PropertyConfigurator;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;

public class Log4J_2 {

	public static void main(String[] args) {
		//add logger class name and take class name
		Logger logger = Logger.getLogger("Log4J_2");
		
		//configure log4J properties file
		PropertyConfigurator.configure("C:\\Users\\Yogesh.Pole\\eclipse-workspace\\Demo\\log4j.properties");
		logger.info("File Configured");
		
		//Set up
		System.setProperty("webdriver.chrome.driver", "D:/chromedriver-win64/chromedriver-win64/chromedriver.exe");
		logger.info("Properties of instance Defined");
		
		//instance created
		WebDriver driver = new ChromeDriver();
		logger.info("Instance created");
		
		//navigation to WebPage
		driver.get("http://172.21.0.46:8181/lcs-finairoLending-1.0.1");
		logger.info("Navigated to WebPage");
		
		
		
		

	}

}
