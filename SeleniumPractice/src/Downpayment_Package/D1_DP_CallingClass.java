package Downpayment_Package;

import Utility_Package.ReportManager;
import Utility_Package.TestListener;
import org.testng.annotations.AfterClass;
import org.testng.annotations.Listeners;
import org.testng.annotations.Test;

@Listeners(TestListener.class)
public class D1_DP_CallingClass extends D2_DP_Login
{

	@Test
	public void init() throws Exception {

		ReportManager.setTestSuiteName("Downpayment");
		ReportManager.startTest("Downpayment - Full Validation");

		dpLogin();

		// Step 2: Switch to PTP iframe
		D3_DP_Frame frame = new D3_DP_Frame();
		frame.switchToPTPFrame();

		// Step 3: Validate 'Overdue Amount'
		D4_DP_OverdueAmt odAmt = new D4_DP_OverdueAmt();
		odAmt.validateOverdueAmt();

		// Step 4: Validate 'PTP Start Date'
		D5_DP_StartDate startDate = new D5_DP_StartDate();
		startDate.validateStartDate();

		// Step 5: Validate 'Remarks'
		D6_DP_Remarks remarks = new D6_DP_Remarks();
		remarks.validateRemarks();

		// Step 6: Validate 'Schedule Type' DD → select 'Downpayment + Schedule PTP'
		D7_DP_TypeDD typeDD = new D7_DP_TypeDD();
		typeDD.validateTypeDD();

		// Step 7: Validate DP 'Planned Date'
		D8_DP_PlanDate dpPlanDate = new D8_DP_PlanDate();
		dpPlanDate.validatePlanDate();

		// Step 8: Validate DP 'Planned Amount' (read-only via JS)
		D9_DP_PlanAmt dpPlanAmt = new D9_DP_PlanAmt();
		dpPlanAmt.validatePlanAmt();

		// Step 9: Validate DP 'Payment Mode' DD
		D10_DP_PayModeDD dpPayMode = new D10_DP_PayModeDD();
		dpPayMode.validatePayMode();

		// Step 10: Validate 'Cheque Date'
		D11_DP_ChequeDate chqDate = new D11_DP_ChequeDate();
		chqDate.validateChequeDate();

		// Step 11: Validate 'Cheque Number'
		D12_DP_ChequeNum chqNum = new D12_DP_ChequeNum();
		chqNum.validateChequeNum();

		// Step 12: Validate 'Remaining Amount' (read-only via JS)
		D13_DP_RemAmount remAmt = new D13_DP_RemAmount();
		remAmt.validateRemAmount();

		// Step 13: Validate Schedule 'Planned Date' (planDate1)
		D14_DP_SchPlanDate schPlanDate = new D14_DP_SchPlanDate();
		schPlanDate.validateSchPlanDate();

		// Step 14: Validate Schedule 'Planned Amount' (plannedAmt1 via JS)
		D15_DP_SchPlanAmt schPlanAmt = new D15_DP_SchPlanAmt();
		schPlanAmt.validateSchPlanAmt();

		// Step 15: Validate Schedule 'Payment Mode' (paymentMode1)
		D16_DP_SchPayMode schPayMode = new D16_DP_SchPayMode();
		schPayMode.validateSchPayMode();

		// Step 16: Add + Save + View/Edit/Disable
		D17_DP_AddSave addSave = new D17_DP_AddSave();
		addSave.validateAddSave();

		System.out.println("=================================================");
		System.out.println("FINAL assertAll() - Downpayment All Results");
		sa.assertAll();
	}

	@AfterClass(alwaysRun = true)
	public void tearDown() {
		ReportManager.flushReport();
		if (driver != null) { driver.quit(); driver = null; }
	}
}
