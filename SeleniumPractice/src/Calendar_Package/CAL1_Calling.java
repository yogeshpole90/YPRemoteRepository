package Calendar_Package;

import Utility_Package.ReportManager;
import Utility_Package.TestListener;
import org.testng.annotations.AfterClass;
import org.testng.annotations.Listeners;
import org.testng.annotations.Test;

@Listeners(TestListener.class)
public class CAL1_Calling extends CAL2_Setup {

	@Test
	public void Call() throws Exception
	{
		ReportManager.setTestSuiteName("Calendar");//in path,file name add
		ReportManager.startTest("Calendar - Full Validation");//inside report

		CAL2_Setup cal2 = new CAL2_Setup();
		cal2.setup();

		CAL3_Login cal3 = new CAL3_Login();
		cal3.login();

		CAL4_Navigate cal4 = new CAL4_Navigate();
		cal4.navigateToCalendar();

		CAL5_Checkboxes cal5 = new CAL5_Checkboxes();
		cal5.validateCheckboxes();

		CAL6_DropdownNavigation cal6 = new CAL6_DropdownNavigation();
		cal6.validateDropdownNavigation();

		CAL7_CreateRecord cal7 = new CAL7_CreateRecord();
		cal7.createRecord();

		System.out.println("=================================================");
		System.out.println("FINAL assertAll() - Calendar All Results");
		sa.assertAll();
	}

	@AfterClass(alwaysRun = true)
	public void tearDown() {
		ReportManager.flushReport();
		if (driver != null) 
		{
			driver.quit(); 
		    driver = null; 
		    
		}
	}
}
