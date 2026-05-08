package Listener_Practise;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.testng.ITestContext;
import org.testng.ITestListener;
import org.testng.ITestResult;

public class Listerner_2 implements ITestListener {

	private static final Logger logger = LogManager.getLogger(Listerner_2.class);

	public void onTestStart(ITestResult result) {
		logger.info("TEST STARTED: {}", result.getMethod().getMethodName());
		logger.info("Status: {}", result.getStatus());
	}

	public void onTestSuccess(ITestResult result) {
		logger.info("TEST PASSED: {}", result.getMethod().getMethodName());
		logger.info("Status: {}", result.getStatus());
	}

	public void onTestFailure(ITestResult result) {
		logger.error("TEST FAILED: {} - {}", result.getMethod().getMethodName(), result.getThrowable().getMessage());
		logger.error("Status: {}", result.getStatus());
	}

	public void onTestSkipped(ITestResult result) {
		logger.warn("TEST SKIPPED: {}", result.getMethod().getMethodName());
		logger.warn("Status: {}", result.getStatus());
	}

	public void onStart(ITestContext context) {
		logger.info("=================================================");
		logger.info("SUITE STARTED: {}", context.getName());
		logger.info("=================================================");
	}

	public void onFinish(ITestContext context) {
		logger.info("=================================================");
		logger.info("SUITE FINISHED: {}", context.getName());
		logger.info("Passed : {}", context.getPassedTests().size());
		logger.info("Failed : {}", context.getFailedTests().size());
		logger.info("Skipped: {}", context.getSkippedTests().size());
		logger.info("=================================================");
	}
}
