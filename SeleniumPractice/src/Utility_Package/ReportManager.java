package Utility_Package;

import java.io.File;
import java.text.SimpleDateFormat;
import java.util.Date;

import org.apache.commons.io.FileUtils;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;
import org.openqa.selenium.WebDriver;

import com.aventstack.extentreports.*;
import com.aventstack.extentreports.reporter.ExtentSparkReporter;
import com.aventstack.extentreports.reporter.configuration.Theme;

public class ReportManager {

    private static ExtentReports extent;
    private static ExtentTest test;

    private static String testSuiteName = "AutomationReport";
    //default value

    public static void setTestSuiteName(String name) {
        testSuiteName = name;
    }

    public static ExtentReports getReport() {
        if (extent == null) {
            String timestamp = new SimpleDateFormat("dd-MM-yyyy_HH-mm-ss").format(new Date());
            String reportPath = "D:\\TestReports\\" + testSuiteName + "_" + timestamp + ".html";

            ExtentSparkReporter spark = new ExtentSparkReporter(reportPath);
            spark.config().setTheme(Theme.DARK);//dark theme
            //browser title
            spark.config().setDocumentTitle("LCS Automation Report");
            //report heading
            spark.config().setReportName("Selenium Test Execution Results");
            //time format
            spark.config().setTimeStampFormat("dd-MM-yyyy hh:mm:ss a");

            extent = new ExtentReports();
            extent.attachReporter(spark);
            extent.setSystemInfo("Project", "LCS - Finairo Lending");
            extent.setSystemInfo("Tester", "Yogesh Pole");
            extent.setSystemInfo("Environment", "QA");
            extent.setSystemInfo("Browser", "Chrome");
            extent.setSystemInfo("OS", System.getProperty("os.name"));
            extent.setSystemInfo("Java Version", System.getProperty("java.version"));
            extent.setSystemInfo("URL", "http://10.10.230.14:8181/lcs-finairoLending-1.0.1");

            System.out.println(">> REPORT INITIALIZED: " + reportPath);
        }
        return extent;
    }

    // Naya test start karo
    public static ExtentTest startTest(String testName) {
        test = getReport().createTest(testName);
        return test;
    }

    // Naya test with description
    public static ExtentTest startTest(String testName, String description) {
        test = getReport().createTest(testName, description);
        return test;
    }

    // Get current test
    public static ExtentTest getTest() {
        return test;
    }

    // Screenshot leke report me attach karo
    public static String takeScreenshot(WebDriver driver, String screenshotName) {
        try {
            String timestamp = new SimpleDateFormat("dd-MM-yyyy_HH-mm-ss").format(new Date());
            String path = "D:\\TestReports\\Screenshots\\" + screenshotName + "_" + timestamp + ".png";

            // Folder create karo agar nahi hai
            new File("D:\\TestReports\\Screenshots").mkdirs();

            File src = ((TakesScreenshot) driver).getScreenshotAs(OutputType.FILE);
            FileUtils.copyFile(src, new File(path));
            System.out.println(">> SCREENSHOT SAVED: " + path);
            return path;
        } catch (Exception e) {
            System.out.println(">> SCREENSHOT FAILED: " + e.getMessage());
            return "";
        }
    }

    // Screenshot leke report me attach karo (FAIL pe)
    public static void attachScreenshot(WebDriver driver, String screenshotName) {
        String path = takeScreenshot(driver, screenshotName);
        if (!path.isEmpty() && test != null) {
            try {
                test.addScreenCaptureFromPath(path, screenshotName);
            } catch (Exception e) {
                test.info("Screenshot path: " + path);
            }
        }
    }

    // Report save karo — end me
    public static void flushReport() {
        if (extent != null) {
            extent.flush();
            System.out.println(">> REPORT FLUSHED & SAVED SUCCESSFULLY");
        }
    }
}
