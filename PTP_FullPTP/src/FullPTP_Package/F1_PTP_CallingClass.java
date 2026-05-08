package FullPTP_Package;

import org.testng.annotations.Test;

/**
 * F1_PTP_CallingClass - Master Controller for PTP Section Validation
 * 
 * Execution Order:
 * Step 1 → Browser Launch           (A1_LoginSetup)
 * Step 2 → Login & Navigate         (F2_PTP_Login)
 * Step 3 → Switch to PTP Frame      (F3_PTP_Frame)
 * Step 4 → Overdue Amount - Numeric (F4_PTP_OverdueAmt)
 * Step 5 → PTP Start Date - Date    (F5_PTP_Date)
 * Step 6 → Remarks - TextArea       (F6_PTP_Remarks)
 * Step 7 → PTP Type - Dropdown      (F7_PTP_TypeDD)
 * Step 8 → Payment Mode - Dropdown  (F8_PTP_PayModeDD)
 * Step 9 → Planned Amount - Numeric (F9_PTP_PlannedAmt)
 * Step 10 → View & Edit Buttons     (F10_PTP_ViewEdit)
 * 
 * NOTE: sa.assertAll() called ONLY HERE at the end.
 */
public class F1_PTP_CallingClass extends A1_LoginSetup
{

	@Test
	public void init() throws Exception {

		// Step 1: Launch browser
		a1setup();

		// Step 2: Login, search case, open Remedial → PTP tab
		F2_PTP_Login login = new F2_PTP_Login();
		login.ptpLogin();

		// Step 3: Switch to PTP iframe
		F3_PTP_Frame frame = new F3_PTP_Frame();
		frame.switchToPTPFrame();

		// Step 4: Validate 'Overdue Amount' numeric field
		F4_PTP_OverdueAmt odAmt = new F4_PTP_OverdueAmt();
		odAmt.validateOverdueAmt();

		// Step 5: Validate 'PTP Start Date' date field
		F5_PTP_Date ptpDate = new F5_PTP_Date();
		ptpDate.validateDate();

		// Step 6: Validate 'Remarks' text area
		F6_PTP_Remarks remarks = new F6_PTP_Remarks();
		remarks.validateRemarks();

		// Step 7: Validate 'PTP Type' dropdown (scheduleType)
		F7_PTP_TypeDD ptpType = new F7_PTP_TypeDD();
		ptpType.validatePTPType();

		// Step 8: Validate 'Payment Mode' dropdown
		F8_PTP_PayModeDD payMode = new F8_PTP_PayModeDD();
		payMode.validatePayMode();

		// Step 9: Validate 'Planned Amount' numeric field
		F9_PTP_PlannedAmt planAmt = new F9_PTP_PlannedAmt();
		planAmt.validatePlannedAmt();

		// Step 10: Validate View & Edit buttons
		F10_PTP_ViewEdit viewEdit = new F10_PTP_ViewEdit();
		viewEdit.validateViewEdit();

		// FINAL: Report ALL soft assertion failures at once
		System.out.println("=================================================");
		System.out.println("FINAL assertAll() - PTP All Results");
		sa.assertAll();
	}

}
