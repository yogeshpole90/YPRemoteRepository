package LawFirm_Package;

import Utility_Package.ReportManager;
import Utility_Package.TestListener;
import org.testng.annotations.AfterClass;
import org.testng.annotations.Listeners;
import org.testng.annotations.Test;

@Listeners(TestListener.class)
public class LF1_Calling extends LF2_Login {
	@Test
	public void init() throws Exception {
		ReportManager.setTestSuiteName("LawFirm");
		ReportManager.startTest("Law Firm Master - Full Validation");
		lfLogin();
		try{new LF3_AddButton().validate();}catch(Exception e){System.out.println("LF3 → ERROR: "+e.getMessage());}
		try{new LF4_FirmTypeDD().validate();}catch(Exception e){System.out.println("LF4 → ERROR: "+e.getMessage());}
		try{new LF5_FirmName().validate();}catch(Exception e){System.out.println("LF5 → ERROR: "+e.getMessage());}
		try{new LF6_RegNo().validate();}catch(Exception e){System.out.println("LF6 → ERROR: "+e.getMessage());}
		try{new LF7_Address().validate();}catch(Exception e){System.out.println("LF7 → ERROR: "+e.getMessage());}
		try{new LF8_CountryStateCity().validate();}catch(Exception e){System.out.println("LF8 → ERROR: "+e.getMessage());}
		try{new LF9_ContactFields().validate();}catch(Exception e){System.out.println("LF9 → ERROR: "+e.getMessage());}
		try{new LF10_SaveViewEditDisable().validate();}catch(Exception e){System.out.println("LF10 → ERROR: "+e.getMessage());}
		System.out.println("=================================================");
		System.out.println("FINAL assertAll() - Law Firm Master All Results");
		sa.assertAll();
	}

	@AfterClass(alwaysRun = true)
	public void tearDown() {
		ReportManager.flushReport();
		if (driver != null) { driver.quit(); driver = null; }
	}
}
