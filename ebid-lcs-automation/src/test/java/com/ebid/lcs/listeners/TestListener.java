package com.ebid.lcs.listeners;

import org.testng.ITestContext;
import org.testng.ITestListener;
import org.testng.ITestResult;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import com.ebid.lcs.reporting.ExtentManager;

public class TestListener implements ITestListener {

    private static final Logger logger = LogManager.getLogger(TestListener.class);

    public void onTestStart(ITestResult result) {
        logger.info("TEST STARTED: " + result.getMethod().getMethodName());
        ExtentManager.createNode(result.getMethod().getMethodName());
    }

    public void onTestSuccess(ITestResult result) {
        logger.info("TEST PASSED: " + result.getMethod().getMethodName());
    }

    public void onTestFailure(ITestResult result) {
        logger.error("TEST FAILED: " + result.getMethod().getMethodName());
        logger.error("ERROR: " + result.getThrowable().getMessage());
        if (ExtentManager.getTest() != null) {
            ExtentManager.getTest().fail("Exception: " + result.getThrowable().getMessage());
        }
    }

    public void onTestSkipped(ITestResult result) {
        logger.warn("TEST SKIPPED: " + result.getMethod().getMethodName());
        if (ExtentManager.getTest() != null) {
            ExtentManager.getTest().skip("Skipped: " + result.getMethod().getMethodName());
        }
    }

    public void onStart(ITestContext context) {
        logger.info("=================================================");
        logger.info("SUITE STARTED: " + context.getName());
        logger.info("=================================================");
    }

    public void onFinish(ITestContext context) {
        logger.info("=================================================");
        logger.info("SUITE FINISHED: " + context.getName());
        logger.info("Total: " + (context.getPassedTests().size() + context.getFailedTests().size()));
        logger.info("Passed: " + context.getPassedTests().size());
        logger.info("Failed: " + context.getFailedTests().size());
        logger.info("=================================================");
    }
}
