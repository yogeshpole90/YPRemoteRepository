package CaseStudy_Package;

import Utility_Package.ReportManager;
import Utility_Package.TestListener;
import org.testng.annotations.AfterClass;
import org.testng.annotations.Listeners;
import org.testng.annotations.Test;

@Listeners(TestListener.class)
public class CS1_Calling extends CS2_Setup {

	@Test
	public void Call() throws Exception
	{
		ReportManager.setTestSuiteName("CaseStudy");
		ReportManager.startTest("Case Study - Full Validation");

		CS2_Setup cs2 = new CS2_Setup();
		cs2.setup();

		// Step 2: Login + Open Case 411
		CS3_Login cs3 = new CS3_Login();
		cs3.login();

		// Step 3: Switch to frame
		CS4_Frame cs4 = new CS4_Frame();
		cs4.switchFrame();

		// Step 4: Loan Details (12 fields)
		CS5_LoanDetails cs5 = new CS5_LoanDetails();
		cs5.validateLoanDetails();

		// Step 5: Overdue Details (7 fields)
		CS6_OverdueDetails cs6 = new CS6_OverdueDetails();
		cs6.validateOverdueDetails();

		// Step 6: Repayment Details (message check)
		//CS7_RepaymentDetails cs7 = new CS7_RepaymentDetails();
		//cs7.validateRepaymentDetails();

		// Step 7: Collateral Details (8 fields)
		CS8_CollateralDetails cs8 = new CS8_CollateralDetails();
		cs8.validateCollateralDetails();

		System.out.println("=================================================");
		System.out.println("FINAL assertAll() - Case Study All Results");
		sa.assertAll();
	}

	@AfterClass(alwaysRun = true)
	public void tearDown() {
		ReportManager.flushReport();
		if (driver != null) { driver.quit(); driver = null; }
	}
}
