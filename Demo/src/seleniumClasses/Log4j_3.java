package seleniumClasses;

import org.apache.log4j.Logger;
import org.apache.log4j.PropertyConfigurator;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;

public class Log4j_3 {

	public static void main(String[] args) {
		
		Logger logger = Logger.getLogger("Log4j_3");
		PropertyConfigurator.configure("C:\\Users\\Yogesh.Pole\\eclipse-workspace\\Demo\\log4j.properties");
		
		System.setProperty("webdriver.chrome.driver", "D:/chromedriver-win64/chromedriver-win64/chromedriver.exe");
		WebDriver driver = new ChromeDriver();
		driver.get("http://172.21.0.46:8181/lcs-finairoLending-1.0.1");
		logger.info("Navigated to WebApplication");
		
		
		
		


	}

}
