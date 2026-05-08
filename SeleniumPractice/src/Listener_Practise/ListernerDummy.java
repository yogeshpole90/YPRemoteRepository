package Listener_Practise;

import org.apache.log4j.Logger;
import org.testng.ITestContext;
import org.testng.ITestListener;
import org.testng.ITestResult;

public class ListernerDummy implements ITestListener {

	private static final Logger logger = Logger.getLogger(ListernerDummy.class);

	public void onStart(ITestContext context) {
		logger.info("=================================================");
		logger.info("Suite Started: " + context.getName());
		logger.info("=================================================");
	}

	public void onTestStart(ITestResult result) {
		logger.info("Starting: " + result.getMethod().getMethodName());
	    
	}

	public void onTestSuccess(ITestResult result) {
		logger.info("PASSED: " + result.getMethod().getMethodName());
		
	}

	public void onTestFailure(ITestResult result) {
		logger.error("FAILED: " + result.getMethod().getMethodName() + " - " + result.getThrowable().getMessage());
	}

	public void onTestSkipped(ITestResult result) {
		logger.warn("SKIPPED: " + result.getMethod().getMethodName());
	}

	public void onFinish(ITestContext context) {
		logger.info("=================================================");
		logger.info("Suite Finished: " + context.getName());
		logger.info("Passed : " + context.getPassedTests().size());
		logger.error("Failed : " + context.getFailedTests().size());
		logger.warn("Skipped: " + context.getSkippedTests().size());
		logger.info("=================================================");
	}
}
