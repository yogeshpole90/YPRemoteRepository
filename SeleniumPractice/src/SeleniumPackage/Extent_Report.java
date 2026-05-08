package SeleniumPackage;

import com.aventstack.extentreports.ExtentReports;
import com.aventstack.extentreports.ExtentTest;
import com.aventstack.extentreports.Status;
import com.aventstack.extentreports.reporter.ExtentSparkReporter;

public class Extent_Report {
	public static void main(String[] args) {
		//step 1: Report path
		String path =System.getProperty("", "");//report will save here.
		
		//step 2: Create reporter
		ExtentSparkReporter reporter = new ExtentSparkReporter(path);
		//html file creates here
		
		reporter.config().setReportName("Automation Test Report");
		//report ke andar heading
		
		reporter.config().setDocumentTitle("Test Result");
		
		//step 3: Attached reporter to ExtentReport
		ExtentReports extent = new ExtentReports();
		extent.attachReporter(reporter);
		
		//step 4 : create test
		ExtentTest test = extent.createTest("login Test");
		
		//step 5 : Add Logs
		test.log(Status.INFO, "Browser Launched");
		test.log(Status.PASS, "Login successfull");
		
		//step 6 : Save Report
		extent.flush();
		
		System.out.println("Reprot Generated! ");
		
		
	}

}
