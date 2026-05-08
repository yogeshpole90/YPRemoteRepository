package com.lcs.automation.listeners;

import com.lcs.automation.reporting.ReportManager;
import org.testng.ITestContext;
import org.testng.ITestListener;
import org.testng.ITestResult;

public class TestListener implements ITestListener {

	public void onStart(ITestContext context) {
		System.out.println(">> Suite Started: " + context.getName());
	}

	public void onTestStart(ITestResult result) {
		System.out.println(">> Test Started: " + result.getMethod().getMethodName());
	}

	public void onTestSuccess(ITestResult result) {
		System.out.println(">> PASSED: " + result.getMethod().getMethodName());
	}

	public void onTestFailure(ITestResult result) {
		System.out.println(">> FAILED: " + result.getMethod().getMethodName());
		System.out.println(">> Error: " + result.getThrowable().getMessage());
	}

	public void onTestSkipped(ITestResult result) {
		System.out.println(">> SKIPPED: " + result.getMethod().getMethodName());
	}

	public void onFinish(ITestContext context) {
		System.out.println(">> Passed: " + context.getPassedTests().size());
		System.out.println(">> Failed: " + context.getFailedTests().size());
		ReportManager.flushReport();
	}
}
