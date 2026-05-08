package CaseStatus_Package;

import java.text.SimpleDateFormat;
import java.util.Date;

import org.apache.log4j.Logger;
import org.testng.ITestContext;
import org.testng.ITestListener;
import org.testng.ITestResult;
import org.testng.annotations.Test;

import com.aventstack.extentreports.ExtentReports;
import com.aventstack.extentreports.ExtentTest;
import com.aventstack.extentreports.reporter.ExtentSparkReporter;
import com.aventstack.extentreports.reporter.configuration.Theme;

public class TestReport implements ITestListener{
	
	public static Logger logger = Logger.getLogger(TestReport.class);

	public static ExtentReports extent;
	public static ExtentSparkReporter spark;
	public static ExtentTest test;

	
	@Override
	public void onStart(ITestContext context)
	{

		Date date = new Date();
		SimpleDateFormat sdf = new SimpleDateFormat("dd-MM-yyyy_HH-mm-ss_a");
		String timestamp = sdf.format(date);

		//Manager Created
		extent = new ExtentReports();
	
		spark = new ExtentSparkReporter("D:\\TestReports\\LCS_"+timestamp+".html");
		spark.config().setDocumentTitle("LCS - Automation");
		spark.config().setReportName("Test-Validations");
		spark.config().setTheme(Theme.DARK);

		//Control Given
		extent.attachReporter(spark);

	}
	
	@Override
	public void onTestSuccess(ITestResult result)
	{
		test = extent.createTest("Passed: "+result.getMethod().getMethodName());
	}
	@Override
	public void onTestSkipped(ITestResult result)
	{
		test = extent.createTest("Skipped: "+result.getMethod().getMethodName());

	}
	public void onTestFailure(ITestResult result)
	{
		test = extent.createTest("Failed: "+result.getMethod().getMethodName());
	}
	@Override
	public void onFinish(ITestContext context)
	{
		extent.flush();
		logger.info(extent.getReport());
		logger.info("flushed..");
		
	}


}
