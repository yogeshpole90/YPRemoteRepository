package com.agat.los.reporting;

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

import com.agat.los.config.ConfigManager;

public class ExtentManager {

    private static ExtentReports extent;
    private static ExtentTest parentTest;
    private static String reportPath;
    private static int passCount = 0;
    private static int failCount = 0;
    private static int infoCount = 0;
    private static boolean initialized = false;

    public static void initReport(String suiteName) {
        if (initialized) return; // Only initialize once
        try {
            String timestamp = new SimpleDateFormat("dd-MM-yyyy_HH-mm-ss").format(new Date());
            String reportDir = ConfigManager.get("report.path") != null ? ConfigManager.get("report.path") : "reports/";
            java.io.File dir = new java.io.File(reportDir);
            if (!dir.exists()) dir.mkdirs();
            reportPath = reportDir + "AGAT_LOS_E2E_" + timestamp + ".html";

            ExtentSparkReporter spark = new ExtentSparkReporter(reportPath);
            spark.config().setDocumentTitle("AGAT LOS - E2E Automation Report");
            spark.config().setReportName("Lead to Loan Activation - E2E Test Report");
            spark.config().setTheme(Theme.STANDARD);
            spark.config().setTimelineEnabled(true);

            extent = new ExtentReports();
            extent.attachReporter(spark);
            extent.setSystemInfo("Application", ConfigManager.get("app"));
            extent.setSystemInfo("Environment", ConfigManager.get("base.url"));
            extent.setSystemInfo("Browser", ConfigManager.get("browser"));
            extent.setSystemInfo("OS", System.getProperty("os.name"));
            extent.setSystemInfo("User", ConfigManager.get("username"));
            extent.setSystemInfo("Execution Date", new SimpleDateFormat("dd-MMM-yyyy HH:mm").format(new Date()));

            initialized = true;
            System.out.println(">> REPORT INITIALIZED: " + reportPath);
        } catch (Exception e) {
            System.out.println(">> REPORT INIT FAILED: " + e.getMessage());
        }
    }

    public static void startTest(String testName) {
        if (extent != null) {
            parentTest = extent.createTest(testName);
        }
    }

    public static ExtentTest getTest() {
        return parentTest;
    }

    public static void pass(String field, String desc, String expected, String actual) {
        passCount++;
        if (getTest() != null)
            getTest().log(Status.PASS, "\u2705 " + field + " | " + desc + " | Expected: " + expected + " | Actual: " + actual);
    }

    public static void fail(String field, String desc, String expected, String actual, WebDriver driver) {
        failCount++;
        if (getTest() != null) {
            getTest().log(Status.FAIL, "\u274c " + field + " | " + desc + " | Expected: " + expected + " | Actual: " + actual);
            attachScreenshot(driver, "FAIL_" + field.replace(" ", "_"));
        }
    }

    public static void info(String field, String desc, String value) {
        infoCount++;
        if (getTest() != null)
            getTest().log(Status.INFO, "\u2139\ufe0f " + field + " | " + desc + " | Value: " + value);
    }

    public static void flush() {
        if (extent != null) {
            extent.flush();
            System.out.println(">> REPORT FLUSHED: " + reportPath);
            System.out.println(">> SUMMARY: PASS=" + passCount + " | FAIL=" + failCount + " | INFO=" + infoCount);
        }
    }

    public static void attachScreenshot(WebDriver driver, String name) {
        try {
            String base64 = ((TakesScreenshot) driver).getScreenshotAs(OutputType.BASE64);
            if (getTest() != null) getTest().addScreenCaptureFromBase64String(base64, name);
        } catch (Exception e) {}
    }

    public static int getPassCount() { return passCount; }
    public static int getFailCount() { return failCount; }
    public static int getInfoCount() { return infoCount; }
}
