package LegalOrder_Package;

import Utility_Package.ReportManager;
import Utility_Package.TestListener;
import org.testng.annotations.AfterClass;
import org.testng.annotations.Listeners;
import org.testng.annotations.Test;

@Listeners(TestListener.class)
public class LO1_Calling extends LO2_Login {

	@Test
	public void init() throws Exception
	{
		ReportManager.setTestSuiteName("LegalOrder");
		ReportManager.startTest("Legal Order - Full Validation");

		LOLogin();

		new LO3_Frame().frame();
		new LO4_LoanAcNo().validateLoanAcNo();
		new LO5_OrderType().validateOrderType();
		new LO6_OrderDate().validateOrderDate();
		new LO7_CancellationDate().validateCancellationDate();
		new LO8_Remarks().validateRemarks();
		new LO9_SaveButton().validateSave();

		System.out.println("=================================================");
		System.out.println("FINAL assertAll() - Legal Order All Results");
		sa.assertAll();
	}

	@AfterClass(alwaysRun = true)
	public void tearDown() {
		ReportManager.flushReport();
		if (driver != null) { driver.quit(); driver = null; }
	}
}
