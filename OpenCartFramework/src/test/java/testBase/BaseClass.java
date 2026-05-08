package testBase;

import java.io.FileReader;
import java.io.IOException;
import java.util.Properties;
import java.util.concurrent.TimeUnit;

import org.apache.commons.lang3.RandomStringUtils;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.edge.EdgeDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.testng.annotations.AfterTest;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.Parameters;

public class BaseClass {

	public static WebDriver driver;
	public Logger logger = LogManager.getLogger(this.getClass());
	public static Properties p ;
	@BeforeTest
	@Parameters({"os", "browser"})
	public void setup(String os,String br) throws Exception
	{
		logger.info(">>>>> SETUP STARTED - OS: " + os + " | Browser: " + br);
		
		//loading config file
		FileReader file = new FileReader("src/test/resources/Config.properties");
		p = new Properties();
		p.load(file);
		logger.info(">>>>> Config loaded. appurl=" + p.getProperty("appurl"));


		switch (br.toLowerCase())
		{

		case "chrome":
			driver = new ChromeDriver();
			break;

		case "firefox":
			driver = new FirefoxDriver();
			break;

		case "edge":
			driver = new EdgeDriver();
			break;

		}


		driver.manage().deleteAllCookies();
		driver.manage().timeouts().implicitlyWait(10,TimeUnit.SECONDS);
		driver.manage().window().maximize();
		driver.get(p.getProperty("appurl"));//config file

	}

	@AfterTest
	public void teardown() throws InterruptedException
	{
		Thread.sleep(500);
		driver.quit();
	}


	public String Randomstring()
	{
		String generatedstrings = RandomStringUtils.randomAlphabetic(5);
		return generatedstrings;
	}
	public String Randomnumber()
	{
		String generatednumeric = RandomStringUtils.randomNumeric(10);
		return generatednumeric;
	}
	public String randomAlphanum()
	{
		String generatedstrings = RandomStringUtils.randomAlphabetic(5);
		String generatednumeric = RandomStringUtils.randomNumeric(10);
		return (generatedstrings+generatednumeric);
	}

}
