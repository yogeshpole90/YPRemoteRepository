package FeesLegalCharges_Package;

import Utility_Package.ReportManager;
import Utility_Package.TestListener;
import org.testng.annotations.AfterClass;
import org.testng.annotations.Listeners;
import org.testng.annotations.Test;

@Listeners(TestListener.class)
public class FLC1_Calling extends FLC2_Login {

	@Test
	public void init() throws Exception {

		ReportManager.setTestSuiteName("FeesLegalCharges");
		ReportManager.startTest("Fees & Legal Charges - Full Validation");

		flcLogin();

		new FLC3_Frame().switchToFrame();
		new FLC4_ChargeName().validate();
		new FLC5_Event().validate();
		new FLC6_PayableAmt().validate();
		new FLC7_CurrencyDD().validate();
		new FLC8_ExpenseDate().validate();
		new FLC9_Remarks().validate();
		new FLC10_SaveViewEditDelete().validate();

		System.out.println("=================================================");
		System.out.println("FINAL assertAll() - Fees & Legal Charges All Results");
		sa.assertAll();
	}

	@AfterClass(alwaysRun = true)
	public void tearDown() {
		ReportManager.flushReport();
		if (driver != null) { driver.quit(); driver = null; }
	}
}
