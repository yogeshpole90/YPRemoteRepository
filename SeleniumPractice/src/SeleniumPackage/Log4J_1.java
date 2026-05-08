package SeleniumPackage;

import java.util.concurrent.TimeUnit;

import org.apache.log4j.Logger;//this is so imp- for consile clickable blue link
//import java.util.logging.Logger;
import org.apache.log4j.PropertyConfigurator;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;

public class Log4J_1 {

	public static void main(String[] args) {
		
		//Logger instance created
		Logger logger = Logger.getLogger("Log4J_1");
		

		//configure file path
		PropertyConfigurator.configure("C:\\Users\\Yogesh.Pole\\eclipse-workspace\\SeleniumPractice\\Log4J.properties");
	    logger.info("Path Configured");
	    
	    //System setup
	    System.setProperty("webdriver.chrome.driver", "D:/chromedriver-win64/chromedriver-win64/chromedriver.exe");
	    WebDriver driver = new ChromeDriver();
	    logger.info("properties Defined");
	    
	    //Wait
	    driver.manage().timeouts().implicitlyWait(12, TimeUnit.SECONDS);
	    logger.info("Implicit Wait of 12 Sec Configured");
	    
	    //max
	    driver.manage().window().maximize();
	    logger.info("WebPage is Maximized");
	    
		//navigate to WebPage
	    driver.get("http://172.21.0.46:8181/lcs-finairoLending-1.0.1");
	    logger.info("Navigated to WebPage");
	    
	    //Login
	    
	  
	    
	    
	    
	}

}
