package Listener_Practise;

import org.testng.ITestContext;
import org.testng.ITestListener;
import org.testng.ITestResult;
import org.testng.annotations.Test;

import com.aventstack.extentreports.ExtentReports;
import com.aventstack.extentreports.ExtentTest;
import com.aventstack.extentreports.reporter.ExtentSparkReporter;
import com.aventstack.extentreports.reporter.configuration.Theme;

public class Extent_Report_1 implements ITestListener {

	static ExtentReports extentRepo;//complete Report
	ExtentSparkReporter spark;// File of Report
	static ExtentTest test;//single test entry in report
	
	public static void logResult(String field,String desc,String expected,
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
	public void onStart(ITestContext context)
	{

		spark = new ExtentSparkReporter("D:\\TestReports"+ "\\folder\\LCS-Automation"+System.currentTimeMillis()+".html");
		spark.config().setTheme(Theme.DARK);
		spark.config().setReportName("Blank Report");

		//manager
		extentRepo = new ExtentReports();

		//given control
		extentRepo.attachReporter(spark);
	}
	//=============Start==============
	@Override
	public void onTestStart(ITestResult result)
	{
		test = extentRepo.createTest(result.getName());
	}
	//=============Success===============
	@Override
	public void onTestSuccess(ITestResult result)
	{
		//entry created from onTestStart and status updated from logresult();
		//test = extentRepo.createTest(result.getName());
		//test.pass("PASS : " + result.getName()+" | Expected: Pass | Actual: Pass");
		
	}
	//============Failed=============
	@Override
	public void onTestFailure(ITestResult result)
	{
		//test = extentRepo.createTest(result.getName());
		//test.fail("FAIL : " + result.getThrowable().getMessage()+" | Expected: Fail | Actual: Fail");
		
	}
	// =============Skipped===================
	@Override
	public void onTestSkipped(ITestResult result)
	{
		test = extentRepo.createTest(result.getName());
		test.skip("SKIPPED : "+result.getName()+ " | Actual : Skipped ");
		
	}
	//===========Suite Khatam===========Save karo
	@Override
	public void onFinish(ITestContext context)
	{
		extentRepo.flush();
	}

}

