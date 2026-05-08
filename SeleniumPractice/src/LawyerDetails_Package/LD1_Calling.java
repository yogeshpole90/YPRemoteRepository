package LawyerDetails_Package;

import Utility_Package.ReportManager;
import Utility_Package.TestListener;
import org.testng.annotations.AfterClass;
import org.testng.annotations.Listeners;
import org.testng.annotations.Test;

@Listeners(TestListener.class)
public class LD1_Calling extends LD2_Login {
	@Test
	public void init() throws Exception {
		ReportManager.setTestSuiteName("LawyerDetails");
		ReportManager.startTest("Lawyer Details - Full Validation");
		ldLogin();
		try{new LD3_AddButton().validate();}catch(Exception e){System.out.println("LD3 → ERROR: "+e.getMessage());}
		try{new LD4_LawFirmCodeDD().validate();}catch(Exception e){System.out.println("LD4 → ERROR: "+e.getMessage());}
		try{new LD5_LawyerName().validate();}catch(Exception e){System.out.println("LD5 → ERROR: "+e.getMessage());}
		try{new LD6_RefCode().validate();}catch(Exception e){System.out.println("LD6 → ERROR: "+e.getMessage());}
		try{new LD7_QualificationDD().validate();}catch(Exception e){System.out.println("LD7 → ERROR: "+e.getMessage());}
		try{new LD8_Experience().validate();}catch(Exception e){System.out.println("LD8 → ERROR: "+e.getMessage());}
		try{new LD9_MobileEmail().validate();}catch(Exception e){System.out.println("LD9 → ERROR: "+e.getMessage());}
		try{new LD10_SaveCRUD().validate();}catch(Exception e){System.out.println("LD10 → ERROR: "+e.getMessage());}
		System.out.println("=================================================");
		System.out.println("FINAL assertAll() - Lawyer Details All Results");
		sa.assertAll();
	}

	@AfterClass(alwaysRun = true)
	public void tearDown() {
		ReportManager.flushReport();
		if (driver != null) { driver.quit(); driver = null; }
	}
}
