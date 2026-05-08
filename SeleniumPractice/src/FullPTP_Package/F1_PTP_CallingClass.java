package FullPTP_Package;

import Utility_Package.ReportManager;
import Utility_Package.TestListener;
import org.testng.annotations.AfterClass;
import org.testng.annotations.Listeners;
import org.testng.annotations.Test;

@Listeners(TestListener.class)
public class F1_PTP_CallingClass extends F2_PTP_Login
{

	@Test
	public void init() throws Exception {

		ReportManager.setTestSuiteName("FullPTP");
		ReportManager.startTest("Full PTP - Full Validation");

		ptpLogin();

		// Step 2: Switch to PTP iframe
		F3_PTP_Frame frame = new F3_PTP_Frame();
		frame.switchToPTPFrame();

		// Step 3: Validate 'Overdue Amount' numeric field
		F4_PTP_OverdueAmt odAmt = new F4_PTP_OverdueAmt();
		odAmt.validateOverdueAmt();

		// Step 4: Validate 'PTP Start Date' date field
		F5_PTP_Date ptpDate = new F5_PTP_Date();
		ptpDate.validateDate();

		// Step 5: Validate 'Remarks' text area
		F6_PTP_Remarks remarks = new F6_PTP_Remarks();
		remarks.validateRemarks();

		// Step 6: Validate 'PTP Type' dropdown (scheduleType)
		F7_PTP_TypeDD ptpType = new F7_PTP_TypeDD();
		ptpType.validatePTPType();

		// Step 7: Validate 'Payment Mode' dropdown
		F8_PTP_PayModeDD payMode = new F8_PTP_PayModeDD();
		payMode.validatePayMode();

		// Step 8: Validate 'Planned Amount' numeric field
		F9_PTP_PlannedAmt planAmt = new F9_PTP_PlannedAmt();
		planAmt.validatePlannedAmt();

		// Step 9: Validate 'Remaining Amount' numeric field
		F11_PTP_RemAmount remAmt = new F11_PTP_RemAmount();
		remAmt.validateRemAmount();

		// Step 10: Validate 'Planned Date' date field
		F12_PTP_PlannedDate planDate = new F12_PTP_PlannedDate();
		planDate.validatePlannedDate();

		// Step 11: Validate View & Edit buttons
		F10_PTP_ViewEdit viewEdit = new F10_PTP_ViewEdit();
		viewEdit.validateViewEdit();

		System.out.println("=================================================");
		System.out.println("FINAL assertAll() - PTP All Results");
		sa.assertAll();
	}

	@AfterClass(alwaysRun = true)
	public void tearDown() {
		ReportManager.flushReport();
		if (driver != null) { driver.quit(); driver = null; }
	}
}
