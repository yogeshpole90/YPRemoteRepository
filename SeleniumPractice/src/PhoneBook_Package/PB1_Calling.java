package PhoneBook_Package;

import Utility_Package.ReportManager;
import Utility_Package.TestListener;
import org.testng.annotations.AfterClass;
import org.testng.annotations.Listeners;
import org.testng.annotations.Test;

@Listeners(TestListener.class)
public class PB1_Calling extends PB2_Login {

	@Test
	public void init() throws Exception {

		ReportManager.setTestSuiteName("PhoneBook");
		ReportManager.startTest("PhoneBook - Full Validation");

		pbLogin();

		new PB3_ContactName().validate();
		new PB4_RelationDD().validate();
		new PB5_PhoneNumber().validate();
		new PB6_PhoneTypeDD().validate();
		new PB7_IsActiveDD().validate();
		new PB8_AddContact().validate();
		new PB9_ResetClose().validate();

		System.out.println("=================================================");
		System.out.println("FINAL assertAll() - PhoneBook All Results");
		sa.assertAll();
	}

	@AfterClass(alwaysRun = true)
	public void tearDown() {
		ReportManager.flushReport();
		if (driver != null) { driver.quit(); driver = null; }
	}
}
