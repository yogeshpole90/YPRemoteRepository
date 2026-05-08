package pageObjects;

import java.util.concurrent.TimeUnit;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.support.PageFactory;
import org.testng.annotations.BeforeClass;

public class BasePage {
	WebDriver driver;
    final Logger logger = LogManager.getLogger(this.getClass());

	public  BasePage(WebDriver driver)
	{
		this.driver = driver;
		PageFactory.initElements( driver,this);
	}

}
