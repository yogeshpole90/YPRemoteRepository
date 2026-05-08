package SeleniumPackage;

import javax.naming.Context;
import org.apache.logging.log4j.Logger;
import org.apache.logging.log4j.LogManager;
import org.testng.IAlterSuiteListener;
import org.testng.ITestContext;
import org.testng.ITestListener;
import org.testng.ITestResult;

public class A_Listener implements ITestListener,IAlterSuiteListener {

	public static Logger logger = LogManager.getLogger(A_Listener.class);

	public void onTestStart(ITestResult result)
	{
		logger.info("Starting: {} " , result.getMethod().getMethodName() ,"-----------------");
		

	}
	public void onStart(ITestContext context)
	{
		logger.info("=== Suite Started ==="+ context.getName() +"=========");
		
	}
	public void onTestSuccess(ITestResult result)
	{
		logger.info("PASSED : {} " , result.getMethod().getMethodName() );
		

	}
	public void onTestFailure(ITestResult result)
	{
		
		logger.error("Failed : {} - {} ", result.getMethod().getMethodName(), 
				 result.getThrowable().getMessage());

	}
	
	public void onTestSkipped(ITestResult result)
	{

		logger.warn("SKIPPED :{} ", result.getMethod().getMethodName());

	}
	public void onFinish(ITestContext context)
	{
		int pass = context.getPassedTests().size();
		int fail = context.getFailedTests().size();
		int skip = context.getSkippedTests().size();
		logger.info("Passed : {} "+pass);
		logger.error("Fail : {} "+ fail);
		logger.info("Skip : {} "+skip);
		logger.info(context.getSuite());
	
	
	}

}
