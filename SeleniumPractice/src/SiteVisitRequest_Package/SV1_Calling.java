package SiteVisitRequest_Package;

import Utility_Package.ReportManager;
import Utility_Package.TestListener;
import org.testng.annotations.AfterClass;
import org.testng.annotations.Listeners;
import org.testng.annotations.Test;

@Listeners(TestListener.class)
public class SV1_Calling extends SV2_Login {

	@Test
	public void init() throws Exception
	{
		ReportManager.setTestSuiteName("SiteVisitRequest");
		ReportManager.startTest("Site Visit Request - Full Validation");

		SVLogin();

		SV3_Frame frame = new SV3_Frame();
		frame.frame();

		new SV4_VisitType().validateVisitType();
		new SV5_VisitedBy().validateVisitedBy();
		new SV6_VisitInitiatedDate().validateVisitInitiatedDate();
		new SV7_VisitDate().validateVisitDate();
		new SV8_CustomerResponse().validateCustomerResponse();
		new SV9_Collection().validateCollection();
		new SV10_CollectionFields().validateCollectionFields();
		new SV11_Remarks().validateRemarks();
		new SV13_SaveButton().validateSave();
		new SV14_ViewEditDisable().validateViewEditDisable();

		System.out.println("=================================================");
		System.out.println("FINAL assertAll() - Site Visit Request All Results");
		sa.assertAll();
	}

	@AfterClass(alwaysRun = true)
	public void tearDown() {
		ReportManager.flushReport();
		if (driver != null) { driver.quit(); driver = null; }
	}
}
