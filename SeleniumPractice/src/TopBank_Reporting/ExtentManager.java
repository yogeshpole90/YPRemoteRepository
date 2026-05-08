// File: top-bank-automation/src/main/java/TopBank_Reporting/ExtentManager.java
package TopBank_Reporting;

import com.aventstack.extentreports.ExtentReports;
import com.aventstack.extentreports.ExtentTest;
import com.aventstack.extentreports.reporter.ExtentSparkReporter;
import com.aventstack.extentreports.reporter.configuration.Theme;
import TopBank_Config.ConfigManager;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import java.text.SimpleDateFormat;
import java.util.Date;

/**
 * Thread-safe ExtentReports manager.
 * ExtentReports instance is shared across threads (it is thread-safe internally).
 * Each thread gets its own ExtentTest via ThreadLocal.
 */
public class ExtentManager {
    private static final Logger logger = LogManager.getLogger(ExtentManager.class);
    private static volatile ExtentReports extent;
    private static final ThreadLocal<ExtentTest> currentTest = new ThreadLocal<>();
    private static String reportPath;
    private static final Object lock = new Object();

    private ExtentManager() {
    }

    public static ExtentReports getInstance() {
        if (extent == null) {
            synchronized (lock) {
                if (extent == null) {
                    initializeReport();
                }
            }
        }
        return extent;
    }

    private static void initializeReport() {
        String timestamp = new SimpleDateFormat("yyyyMMdd_HHmmss").format(new Date());
        String reportDir = ConfigManager.get("report.path");
        if (reportDir == null || reportDir.isEmpty()) {
            reportDir = "reports/";
        }
        reportPath = reportDir + "LoanLimit_Report_" + timestamp + ".html";

        ExtentSparkReporter sparkReporter = new ExtentSparkReporter(reportPath);
        sparkReporter.config().setDocumentTitle("TOP Bank Loan Automation");
        sparkReporter.config().setReportName("Loan Automation Test Report");
        sparkReporter.config().setTheme(Theme.STANDARD);
        sparkReporter.config().setEncoding("UTF-8");

        extent = new ExtentReports();
        extent.attachReporter(sparkReporter);
        extent.setSystemInfo("Project", "TOP Bank");
        extent.setSystemInfo("Environment", ConfigManager.get("base.url"));
        extent.setSystemInfo("Browser", ConfigManager.get("browser"));
        extent.setSystemInfo("Execution Date",
                new SimpleDateFormat("dd-MMM-yyyy HH:mm:ss").format(new Date()));

        logger.info("ExtentReports initialized: {}", reportPath);
    }

    /**
     * Create a test - thread-safe. Each thread stores its own ExtentTest.
     */
    public static ExtentTest createTest(String testName) {
        ExtentTest test = getInstance().createTest(testName);
        currentTest.set(test);
        return test;
    }

    public static ExtentTest createTest(String testName, String description) {
        ExtentTest test = getInstance().createTest(testName, description);
        currentTest.set(test);
        return test;
    }

    public static ExtentTest getTest() {
        return currentTest.get();
    }

    public static ExtentTest createNode(String nodeName) {
        ExtentTest test = currentTest.get();
        if (test == null) {
            return createTest(nodeName);
        }
        return test.createNode(nodeName);
    }

    public static synchronized void flush() {
        if (extent != null) {
            extent.flush();
            logger.info("Report written to: {}", reportPath);
        }
    }

    public static String getReportPath() {
        return reportPath;
    }

    public static void removeTest() {
        currentTest.remove();
    }
}
