package com.ebid.lcs.reporting;

import java.text.SimpleDateFormat;
import java.util.Date;

import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;
import org.openqa.selenium.WebDriver;

import com.aventstack.extentreports.ExtentReports;
import com.aventstack.extentreports.ExtentTest;
import com.aventstack.extentreports.Status;
import com.aventstack.extentreports.reporter.ExtentSparkReporter;
import com.aventstack.extentreports.reporter.configuration.Theme;

import com.ebid.lcs.config.ConfigManager;

public class ExtentManager {

    private static ExtentReports extent;
    private static ExtentTest parentTest;
    private static ExtentTest childTest;
    private static String reportPath;
    private static int passCount = 0;
    private static int failCount = 0;
    private static int infoCount = 0;

    public static void initReport(String suiteName) {
        try {
            String timestamp = new SimpleDateFormat("dd-MM-yyyy_HH-mm-ss").format(new Date());
            String reportDir = ConfigManager.get("report.path") != null ? ConfigManager.get("report.path") : "reports/";
            java.io.File dir = new java.io.File(reportDir);
            if (!dir.exists()) dir.mkdirs();
            reportPath = reportDir + suiteName + "_" + timestamp + ".html";

            ExtentSparkReporter spark = new ExtentSparkReporter(reportPath);
            spark.config().setDocumentTitle("EBID LCS - " + suiteName);
            spark.config().setReportName(suiteName + " Test Report");
            spark.config().setTheme(Theme.STANDARD);
            spark.config().setTimelineEnabled(true);

            extent = new ExtentReports();
            extent.attachReporter(spark);
            extent.setSystemInfo("Application", "EBID LCS");
            extent.setSystemInfo("Module", suiteName);
            extent.setSystemInfo("Environment", ConfigManager.get("base.url"));
            extent.setSystemInfo("Browser", ConfigManager.get("browser"));
            extent.setSystemInfo("OS", System.getProperty("os.name"));

            passCount = 0;
            failCount = 0;
            infoCount = 0;

            System.out.println(">> REPORT INITIALIZED: " + reportPath);
        } catch (Exception e) {
            System.out.println(">> REPORT INIT FAILED: " + e.getMessage());
            e.printStackTrace();
        }
    }

    public static void startTest(String testName) {
        if (extent != null) {
            parentTest = extent.createTest(testName);
        }
    }

    public static ExtentTest createNode(String nodeName) {
        if (parentTest != null) {
            childTest = parentTest.createNode(nodeName);
            return childTest;
        }
        return null;
    }

    public static ExtentTest getTest() {
        return childTest != null ? childTest : parentTest;
    }

    public static void pass(String field, String desc, String expected, String actual) {
        passCount++;
        String msg = "✅ " + field + " | " + desc + " | Expected: " + expected + " | Actual: " + actual;
        if (getTest() != null) {
            getTest().log(Status.PASS, msg);
        }
    }

    public static void fail(String field, String desc, String expected, String actual, WebDriver driver) {
        failCount++;
        String msg = "❌ " + field + " | " + desc + " | Expected: " + expected + " | Actual: " + actual;
        if (getTest() != null) {
            getTest().log(Status.FAIL, msg);
            attachScreenshot(driver, "FAIL_" + field.replace(" ", "_"));
        }
    }

    public static void info(String field, String desc, String value) {
        infoCount++;
        String msg = "ℹ️ " + field + " | " + desc + " | Value: " + value;
        if (getTest() != null) {
            getTest().log(Status.INFO, msg);
        }
    }

    public static void warning(String field, String desc, String value) {
        String msg = "⚠️ " + field + " | " + desc + " | Value: " + value;
        if (getTest() != null) {
            getTest().log(Status.WARNING, msg);
        }
    }

    public static void flush() {
        if (extent != null) {
            if (parentTest != null) {
                parentTest.info("📊 Summary: PASS=" + passCount + " | FAIL=" + failCount + " | INFO=" + infoCount);
            }
            extent.flush();
            System.out.println(">> REPORT FLUSHED: " + reportPath);
            System.out.println(">> SUMMARY: PASS=" + passCount + " | FAIL=" + failCount + " | INFO=" + infoCount);
        }
    }

    public static void attachScreenshot(WebDriver driver, String name) {
        try {
            String base64 = ((TakesScreenshot) driver).getScreenshotAs(OutputType.BASE64);
            if (getTest() != null) {
                getTest().addScreenCaptureFromBase64String(base64, name);
            }
        } catch (Exception e) {
            System.out.println(">> SCREENSHOT FAILED: " + e.getMessage());
        }
    }

    public static String getReportPath() {
        return reportPath;
    }
}
