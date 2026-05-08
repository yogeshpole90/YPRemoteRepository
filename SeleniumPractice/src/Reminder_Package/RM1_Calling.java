package Reminder_Package;

import Utility_Package.ReportManager;
import Utility_Package.TestListener;
import org.testng.annotations.AfterClass;
import org.testng.annotations.Listeners;
import org.testng.annotations.Test;

@Listeners(TestListener.class)
public class RM1_Calling extends RM2_Login {

	@Test
	public void init() throws Exception {

		ReportManager.setTestSuiteName("Reminder");
		ReportManager.startTest("Reminder - Full Validation");

		rmLogin();

		new RM3_Frame().switchToFrame();
		new RM4_ReminderTypeDD().validateReminderType();
		new RM5_ReminderDate().validateReminderDate();
		new RM6_CreatedDate().validateCreatedDate();
		new RM7_Remark().validateRemark();
		new RM8_ResetSave().validateResetSave();

		System.out.println("=================================================");
		System.out.println("FINAL assertAll() - Reminder All Results");
		sa.assertAll();
	}

	@AfterClass(alwaysRun = true)
	public void tearDown() {
		ReportManager.flushReport();
		if (driver != null) { driver.quit(); driver = null; }
	}
}
