package DemandLetter_Package;

import Utility_Package.ReportManager;
import Utility_Package.TestListener;
import org.testng.annotations.AfterClass;
import org.testng.annotations.Listeners;
import org.testng.annotations.Test;

@Listeners(TestListener.class)
public class DL1_Calling extends DL2_Login {

	@Test
	public void init() throws Exception {

		ReportManager.setTestSuiteName("DemandLetter");
		ReportManager.startTest("Demand Letter - Full Validation");

		dlLogin();

		new DL3_Frame().switchToFrame();
		new DL4_CaseNo().validateCaseNo();
		new DL5_NoticeTypeDD().validateNoticeType();
		new DL6_IssuanceDate().validateIssuanceDate();
		new DL7_UserName().validateUserName();
		new DL8_ResetSave().validateResetSave();
		new DL9_ViewEditDelete().validateViewEditDelete();

		System.out.println("=================================================");
		System.out.println("FINAL assertAll() - Demand Letter All Results");
		sa.assertAll();
	}

	@AfterClass(alwaysRun = true)
	public void tearDown() {
		ReportManager.flushReport();
		if (driver != null) { driver.quit(); driver = null; }
	}
}
