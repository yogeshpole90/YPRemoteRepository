package FollowUp_Package;

import Utility_Package.ReportManager;
import Utility_Package.TestListener;
import org.testng.annotations.AfterClass;
import org.testng.annotations.Listeners;
import org.testng.annotations.Test;

@Listeners(TestListener.class)
public class FU1_Calling extends FU2_Login {

	@Test
	public void init() throws Exception
	{
		ReportManager.setTestSuiteName("FollowUp");
		ReportManager.startTest("Follow Up - Full Validation");

		FULogin();

		new FU3_Frame().frame();
		new FU4_CommunicationType().validateCommunicationType();
		new FU5_Action().validateAction();
		new FU6_Result().validateResult();
		new FU7_Dates().validateDates();
		new FU8_LoanAcNo().validateLoanAcNo();
		new FU9_CashType().validateCashType();
		new FU10_TextFields().validateTextFields();
		new FU11_SaveButton().validateSave();

		System.out.println("=================================================");
		System.out.println("FINAL assertAll() - Follow Up All Results");
		sa.assertAll();
	}

	@AfterClass(alwaysRun = true)
	public void tearDown() {
		ReportManager.flushReport();
		if (driver != null) { driver.quit(); driver = null; }
	}
}
