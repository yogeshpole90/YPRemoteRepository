package CaseStatus_Package;

import java.text.SimpleDateFormat;
import java.util.Date;

import org.apache.log4j.Logger;
import org.testng.ITestContext;
import org.testng.ITestListener;
import org.testng.ITestResult;
import com.aventstack.extentreports.ExtentReports;
import com.aventstack.extentreports.ExtentTest;
import com.aventstack.extentreports.reporter.ExtentSparkReporter;
import com.aventstack.extentreports.reporter.configuration.Theme;

public class Listener implements ITestListener{

	static ExtentReports extent;
	static ExtentSparkReporter spark;
	static ExtentTest test;

	public static final Logger logger = Logger.getLogger(Listener.class);
	@Override
	public void onStart(ITestContext context) 
	{
		// not implemented
		Date curreDate = new Date();
		SimpleDateFormat sdf = new SimpleDateFormat("dd-MM-yyy_HH-mm-ss");
		String timestamp = sdf.format(curreDate);
		//manager
		extent = new ExtentReports();
		//driver
		spark = new ExtentSparkReporter("D:\\TestReports\\LCS - Automation\\"+timestamp+".html");
		//given control
		extent.attachReporter(spark);
		spark.config().setTheme(Theme.DARK);
		spark.config().setReportName("Report of Case Status:");

		logger.info("Report configured.");


	}
	public void log(String field,String desc,String expected,
			String actual,boolean pass)
	{

		if(pass)
		{
			test.pass("Pass : "+" | "+"Field: "+field+" | "+desc + " | Expected: "+expected+ " | Actual: "+ actual +" | "+pass);
		}
		else
		{
			test.fail("Fail : "+" | "+"Field: "+field +" | "+desc+" | Expected : "+expected+" | Actual : "+actual+" | "+pass);
		}
	}

	@Override
	public  void onTestStart(ITestResult result) {

		//test = extent.createTest(result.getTestName());//
		test = extent.createTest(result.getMethod().getMethodName());
	}

	@Override
	public  void onTestSuccess(ITestResult result) {
		// not implemented


	}
	@Override
	public void onTestFailure(ITestResult result) {
		// not implemented
	}

	@Override
	public void onTestSkipped(ITestResult result) {
		// not implemented
		test = extent.createTest(result.getName());
		test.skip("Skipped" );
	}



	@Override
	public void onFinish(ITestContext context) {
		int passed = context.getPassedTests().size();
		int failed = context.getFailedTests().size();
		int skipped = context.getSkippedTests().size();

		logger.info("Suite Name: " + context.getName());
		logger.info("Total: " + passed+failed+skipped);
		logger.info("Passed: " + passed +"\n" 
				+ "Failed: "+failed +"\n"
				+ "Skipped: "+skipped);;

				extent.flush();


	}

}


