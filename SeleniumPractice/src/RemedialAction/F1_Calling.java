package RemedialAction;

import Utility_Package.ReportManager;
import Utility_Package.TestListener;
import org.testng.annotations.AfterClass;
import org.testng.annotations.Listeners;
import org.testng.annotations.Test;

@Listeners(TestListener.class)
public class F1_Calling extends F2_Setup {

	@Test
	public void Call() throws Exception
	{
		ReportManager.setTestSuiteName("RemedialAction");
		ReportManager.startTest("Remedial Action - Full Validation");

		F2_Setup f2 = new F2_Setup();
		f2.setup();

		// Step 2: Login + Navigate to Remedial Action tab
		F3_Login f3 = new F3_Login();
		f3.login();

		// Step 3: Switch to Remedial Action frame
		F4_remact f4 = new F4_remact();
		f4.remact();

		// Step 4: Mandatory field check (save without filling)
		F7_Mandatory f7 = new F7_Mandatory();
		f7.validateMandatory();

		// Step 5: Validate Action Name dropdown (id: actionId)
		F5_ActionNameDD f5 = new F5_ActionNameDD();
		f5.validateActionNameDD();

		// Step 6: Validate Comments field (id: commments)
		F6_Comments f6 = new F6_Comments();
		f6.validateComments();

		// Step 7: Save + Success Message + View last record
		F8_SaveView f8 = new F8_SaveView();
		f8.validateSaveView();

		System.out.println("=================================================");
		System.out.println("FINAL assertAll() - Remedial Action All Results");
		sa.assertAll();
	}

	@AfterClass(alwaysRun = true)
	public void tearDown() {
		ReportManager.flushReport();
		if (driver != null) { driver.quit(); driver = null; }
	}
}
