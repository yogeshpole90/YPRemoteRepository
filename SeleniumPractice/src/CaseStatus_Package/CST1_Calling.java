package CaseStatus_Package;

import Utility_Package.ReportManager;
import Utility_Package.TestListener;
import org.testng.annotations.AfterClass;
import org.testng.annotations.Listeners;
import org.testng.annotations.Test;

@Listeners(TestListener.class)
public class CST1_Calling extends CST2_Login {

	@Test
	public void init() throws Exception {

		ReportManager.setTestSuiteName("CaseStatus");
		ReportManager.startTest("Case Status - Full Validation");

		cstLogin();

		new CST3_Frame().switchToFrame();
		new CST4_CaseStatusDD().validateCaseStatus();
		new CST5_Remark().validateRemark();
		new CST6_ResetSave().validateResetSave();
		new CST7_ViewEditDisable().validateViewEditDisable();

		System.out.println("=================================================");
		System.out.println("FINAL assertAll() - Case Status All Results");
		sa.assertAll();
	}

	@AfterClass(alwaysRun = true)
	public void tearDown() {
		ReportManager.flushReport();
		if (driver != null) { driver.quit(); driver = null; }
	}
}
