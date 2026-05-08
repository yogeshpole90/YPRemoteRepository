package SeleniumPackage;

import java.io.File;
import java.io.IOException;
import java.util.concurrent.TimeUnit;

import org.apache.commons.io.FileUtils;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.testng.ITestResult;
import org.testng.annotations.Test;

public class Listener {
	@Test
	public void listener(ITestResult result) throws Exception
	{
		System.setProperty("webdriver.chrome.driver","D:\\chromedriver144\\chromedriver-win64\\chromedriver.exe");
		WebDriver driver = new ChromeDriver();
		driver.manage().window().maximize();
		driver.manage().timeouts().implicitlyWait(15, TimeUnit.SECONDS);

		if(result.getStatus() == ITestResult.FAILURE)
		{
			TakesScreenshot ts = (TakesScreenshot)driver;
			File temp = ts.getScreenshotAs(OutputType.FILE);
			File dest = new File("d:\\ss"+ System.currentTimeMillis()+".png");
			FileUtils.copyFile(temp, dest);
			System.out.println("Absolute File Path"+ dest.getAbsoluteFile());
			
			
			
		}
	}

}
