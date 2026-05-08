package Login_Package;

import Utility_Package.ReportManager;
import Utility_Package.TestListener;
import org.testng.annotations.AfterClass;
import org.testng.annotations.Listeners;
import org.testng.annotations.Test;

@Listeners(TestListener.class)
public class L1_CallingClass extends L2_Setup {

	@Test(priority = 1)
	public void step1_Username() throws Exception {
		ReportManager.setTestSuiteName("Login");
		ReportManager.startTest("Login - Full Validation");
		L3_Username l3 = new L3_Username();
		l3.usernameValidation();
	}

	@Test(priority = 2)
	public void step2_Password() throws Exception {
		L4_Password l4 = new L4_Password();
		l4.passwordValidation();
	}

	@Test(priority = 3)
	public void step3_LoginCombos() throws Exception {
		L5_LoginCombos l5 = new L5_LoginCombos();
		l5.loginCombos();
	}

	@Test(priority = 4)
	public void step4_AssertAll() {
		System.out.println("========================================");
		System.out.println("  LOGIN VALIDATION - FINAL RESULTS");
		System.out.println("========================================");
		sa.assertAll();
	}

	@AfterClass(alwaysRun = true)
	public void tearDown() {
		ReportManager.flushReport();
		if (driver != null) { driver.quit(); driver = null; }
	}
}
