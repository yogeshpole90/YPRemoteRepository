package SeleniumPackage;

import java.io.File;
import java.io.IOException;

import org.apache.commons.io.FileUtils;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;
import org.openqa.selenium.WebDriver;
import org.testng.ITestResult;
import org.testng.annotations.Test;

public class TakeScreenshotonFail1 {

	WebDriver driver;
	@Test
	public void captureScreenshot(ITestResult result) throws IOException
	{
		if(ITestResult.FAILURE == result.getStatus())
		{
			//ref takescreenshot + casting
			TakesScreenshot ts = (TakesScreenshot) driver;
			//use getss() mtd to capture ss in form of File
			//getss mtd return type is FILE
			File src = ts.getScreenshotAs(OutputType.FILE);//capture SS in form/Output of FILE
			File dest = new File ("./screenshot/"+ result.getName()+ ".png");
			//copy file to project level
			//
			FileUtils.copyFile(src,dest );
			System.out.println(result.getName()+"method() ScreenShot Captured.");

		}



	}

}
