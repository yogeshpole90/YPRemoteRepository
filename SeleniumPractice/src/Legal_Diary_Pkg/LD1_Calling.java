package Legal_Diary_Pkg;

import Utility_Package.ReportManager;
import Utility_Package.TestListener;
import org.testng.annotations.AfterClass;
import org.testng.annotations.Listeners;
import org.testng.annotations.Test;

@Listeners(TestListener.class)
public class LD1_Calling extends LD2_Login {

	@Test
	public void init() throws Exception {

		ReportManager.setTestSuiteName("LegalDiary");
		ReportManager.startTest("Legal Diary - Full Validation");

		LD_ExcelUtil.initExcel("D:\\Excel_File_For_Selenium\\PTP_TestData.xlsx", "LegalDiary");

		LDLogin();

		new LD3_Frame().frame();
		new LD4_CaseRefNo().validateCaseRefNo();
		new LD5_FilingDate().validateFilingDate();
		new LD6_DateAllocated().validateDateAllocated();
		new LD30_CurrencyDD().validateCurrency();
		new LD31_SuitAmount().validateSuitAmount();
		new LD7_CourtCaseTypeDD().validateCourtCaseTypeDD();
		new LD8_CourtFeeTypeDD().validateCourtFeeTypeDD();
		new LD9_CourtFee().validateCourtFee();
		new LD10_ProcessFee().validateProcessFee();
		new LD11_ReplevinBondFee().validateReplevinBondFee();
		new LD12_ExecutionFee().validateExecutionFee();
		new LD13_PetitionFee().validatePetitionFee();
		new LD14_OtherFee().validateOtherFee();
		new LD15_TotalCourtFee().validateTotalCourtFee();
		new LD16_DocsHandedDate().validateDocsHandedDate();
		new LD17_Remarks().validateRemarks();
		new LD18_CaseInitiatedByDD().validateCaseInitiatedByDD();
		new LD19_BankruptcyCaseDD().validateBankruptcyCaseDD();
		new LD20_BankruptcyCaseDate().validateBankruptcyCaseDate();
		new LD21_BankruptcyCaseNo().validateBankruptcyCaseNo();
		new LD22_HearingDate().validateHearingDate();
		new LD23_NextHearingDate().validateNextHearingDate();
		new LD24_TransferPvtExecutorDD().validateTransferPvtExecutorDD();
		new LD25_Status().validateStatus();
		new LD26_RevocationCaseDD().validateRevocationCaseDD();
		new LD27_RevocationDate().validateRevocationDate();
		new LD28_ChooseDocument().validateChooseDocument();
		new LD29_SaveButton().validateSave();
		new LD32_ViewEditDisable().validateViewEditDisable();

		System.out.println("=================================================");
		System.out.println("FINAL assertAll() - Legal Diary All Results");
		sa.assertAll();
	}

	@AfterClass(alwaysRun = true)
	public void tearDown() {
		ReportManager.flushReport();
		if (driver != null) { driver.quit(); driver = null; }
	}
}
