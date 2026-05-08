package Utility_Package;

import org.apache.log4j.Logger;
import org.testng.ITestContext;
import org.testng.ITestListener;
import org.testng.ITestResult;

public class TestListener implements ITestListener {

    private static final Logger logger = Logger.getLogger(TestListener.class);

    @Override
    public void onStart(ITestContext context) {
        logger.info("=================================================");
        logger.info("TEST SUITE STARTED: " + context.getName());
        logger.info("=================================================");
    }

    @Override
    public void onTestStart(ITestResult result) {
        logger.info("TEST STARTED: " + result.getMethod().getMethodName());
    }

    @Override
    public void onTestSuccess(ITestResult result) {
        logger.info("TEST PASSED: " + result.getMethod().getMethodName());
        if (ReportManager.getTest() != null) {
            ReportManager.getTest().pass("\u2705 TEST PASSED: " + result.getMethod().getMethodName());
        }
    }

    @Override
    public void onTestFailure(ITestResult result) {
        logger.error("TEST FAILED: " + result.getMethod().getMethodName());
        logger.error("ERROR: " + result.getThrowable().getMessage());

        if (ReportManager.getTest() != null) {
            ReportManager.getTest().fail("\u274c TEST FAILED: " + result.getMethod().getMethodName());
            ReportManager.getTest().fail("Error: " + result.getThrowable().getMessage());

            // Screenshot on failure
            try {
                Object testClass = result.getInstance();
                java.lang.reflect.Field driverField = testClass.getClass().getSuperclass().getDeclaredField("driver");
                driverField.setAccessible(true);
                org.openqa.selenium.WebDriver driver = (org.openqa.selenium.WebDriver) driverField.get(testClass);

                if (driver != null) {
                    ReportManager.attachScreenshot(driver, "FAIL_" + result.getMethod().getMethodName());
                }
            } catch (Exception e) {
                logger.warn("Could not capture screenshot: " + e.getMessage());
            }
        }
    }

    @Override
    public void onTestSkipped(ITestResult result) {
        logger.warn("TEST SKIPPED: " + result.getMethod().getMethodName());
        if (ReportManager.getTest() != null) {
            ReportManager.getTest().skip("\u26a0\ufe0f TEST SKIPPED: " + result.getMethod().getMethodName());
        }
    }

    @Override
    public void onFinish(ITestContext context) {
        int passed = context.getPassedTests().size();
        int failed = context.getFailedTests().size();
        int skipped = context.getSkippedTests().size();

        logger.info("=================================================");
        logger.info("TEST SUITE FINISHED: " + context.getName());
        logger.info("Total  : " + (passed + failed + skipped));
        logger.info("Passed : " + passed);
        logger.info("Failed : " + failed);
        logger.info("Skipped: " + skipped);
        logger.info("=================================================");

        ReportManager.flushReport();
    }
}
