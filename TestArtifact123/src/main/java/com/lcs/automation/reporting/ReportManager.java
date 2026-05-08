package com.lcs.automation.reporting;

import com.lcs.automation.config.AppConfig;
import com.aventstack.extentreports.*;
import com.aventstack.extentreports.reporter.ExtentSparkReporter;
import com.aventstack.extentreports.reporter.configuration.Theme;
import java.text.SimpleDateFormat;
import java.util.Date;

public class ReportManager {

	private static ExtentReports extent;
	private static ExtentTest test;

	public static ExtentReports getReport() {
		if (extent == null) {
			String timestamp = new SimpleDateFormat("dd-MM-yyyy_HH-mm-ss").format(new Date());
			String path = AppConfig.REPORT_PATH + "Report_" + timestamp + ".html";

			ExtentSparkReporter spark = new ExtentSparkReporter(path);
			spark.config().setTheme(Theme.DARK);
			spark.config().setReportName("LCS Automation Report");
			spark.config().setDocumentTitle("LCS Test Results");

			extent = new ExtentReports();
			extent.attachReporter(spark);
			extent.setSystemInfo("Project", "LCS - Finairo Lending");
			extent.setSystemInfo("Browser", "Chrome");

			System.out.println(">> REPORT INITIALIZED: " + path);
		}
		return extent;
	}

	public static ExtentTest startTest(String testName) {
		test = getReport().createTest(testName);
		return test;
	}

	public static ExtentTest getTest() { return test; }

	public static void flushReport() {
		if (extent != null) {
			extent.flush();
			System.out.println(">> REPORT SAVED");
		}
	}
}
