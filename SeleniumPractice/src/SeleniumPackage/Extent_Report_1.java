package SeleniumPackage;

import org.testng.annotations.Test;

import com.aventstack.extentreports.ExtentReports;
import com.aventstack.extentreports.reporter.ExtentSparkReporter;
import com.aventstack.extentreports.reporter.configuration.Theme;

public class Extent_Report_1 {
	@Test
	public void extentReport()
	{
		//manager
		ExtentReports extentRepo = new ExtentReports();
		
		//driver
		//sub folder
		ExtentSparkReporter spark = new ExtentSparkReporter("D:\\TestReports"+ "\\folder\\report1.html");
		spark.config().setTheme(Theme.DARK);
		spark.config().setReportName("Blank Report");
		
		//given control
		extentRepo.attachReporter(spark);
		
		extentRepo.createTest("Test").pass("Logged In");
		extentRepo.flush();
		
		
		
	}

}
