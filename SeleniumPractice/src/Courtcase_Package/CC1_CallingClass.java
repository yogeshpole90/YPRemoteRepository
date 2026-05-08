package Courtcase_Package;

import Utility_Package.ReportManager;
import Utility_Package.TestListener;
import org.testng.annotations.AfterClass;
import org.testng.annotations.Listeners;
import org.testng.annotations.Test;

@Listeners(TestListener.class)
public class CC1_CallingClass extends CC2_Login {

	@Test
	public void init() throws Exception {

		ReportManager.setTestSuiteName("CourtCase");
		ReportManager.startTest("Court Case - Full Validation");

		ccLogin();

		new CC3_frame().frame();

		new CC4B_CourtLevelDD().validateCourtLevel();
		new CC4_CaseTypeDD().validateCaseTypeDD();
		new CC5B_CurrencyDD().validateCurrency();
		new CC5C_FilingNumber().validateFilingNumber();
		new CC5_SuitAmount().validateSuitAmount();
		new CC6_RequestDate().validateRequestDate();
		new CC10_LawFirmName().validateLawFirmName();
		new CC7_LawyerDD().validateLawyerDD();
		new CC11_DocHandleDate().validateDocHandleDate();
		new CC8_CaseInitDD().validateCaseInitDD();
		new CC9_AllocDate().validateAllocDate();
		new CC12_BankruptDD().validateBankruptDD();
		new CC13_BankruptDate().validateBankruptDate();
		new CC14_BankruptNo().validateBankruptNo();
		new CC15_SaveViewDisable().validateSaveViewDisable();

		System.out.println("=================================================");
		System.out.println("FINAL assertAll() - Court Case All Results");
		sa.assertAll();
	}

	@AfterClass(alwaysRun = true)
	public void tearDown() {
		ReportManager.flushReport();
		if (driver != null) { driver.quit(); driver = null; }
	}
}
