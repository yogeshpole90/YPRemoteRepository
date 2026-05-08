package com.ebid.lcs.reporting;

import java.text.SimpleDateFormat;
import java.util.Date;

import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;
import org.openqa.selenium.WebDriver;

import com.aventstack.extentreports.ExtentReports;
import com.aventstack.extentreports.ExtentTest;
import com.aventstack.extentreports.reporter.ExtentSparkReporter;

import com.ebid.lcs.config.ConfigManager;

public class ExtentManager {

    private static ExtentReports extent;
    private static ExtentTest test;
    private static String reportPath;

    public static void initReport(String suiteName) {
        try {
            String timestamp = new SimpleDateFormat("dd-MM-yyyy_HH-mm-ss").format(new Date());
            String reportDir = ConfigManager.get("report.path") != null ? ConfigManager.get("report.path") : "reports/";
            java.io.File dir = new java.io.File(reportDir);
            if (!dir.exists()) dir.mkdirs();
            reportPath = reportDir + suiteName + "_" + timestamp + ".html";
            ExtentSparkReporter spark = new ExtentSparkReporter(reportPath);
            spark.config().setDocumentTitle("EBID LCS Automation Report");
            spark.config().setReportName(suiteName);
            extent = new ExtentReports();
            extent.attachReporter(spark);
            System.out.println(">> REPORT INITIALIZED: " + reportPath);
        } catch (Exception e) {
            System.out.println(">> REPORT INIT FAILED: " + e.getMessage());
            e.printStackTrace();
        }
    }

    public static void startTest(String testName) {
        test = extent.createTest(testName);
    }

    public static ExtentTest getTest() {
        return test;
    }

    public static void flush() {
        if (extent != null) {
            extent.flush();
            System.out.println(">> REPORT FLUSHED: " + reportPath);
        }
    }

    public static void attachScreenshot(WebDriver driver, String name) {
        try {
            String base64 = ((TakesScreenshot) driver).getScreenshotAs(OutputType.BASE64);
            if (test != null) {
                test.addScreenCaptureFromBase64String(base64, name);
            }
        } catch (Exception e) {
            System.out.println(">> SCREENSHOT FAILED: " + e.getMessage());
        }
    }
}
