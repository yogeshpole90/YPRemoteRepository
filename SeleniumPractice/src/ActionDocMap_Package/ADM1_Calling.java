package ActionDocMap_Package;

import Utility_Package.ReportManager;
import Utility_Package.TestListener;
import org.testng.annotations.AfterClass;
import org.testng.annotations.Listeners;
import org.testng.annotations.Test;

@Listeners(TestListener.class)
public class ADM1_Calling extends ADM2_Login {

	@Test
	public void init() throws Exception {

		ReportManager.setTestSuiteName("ActionDocMap");
		ReportManager.startTest("Action Doc Map - Full Validation");

		admLogin();

		new ADM3_AddButton().validateAddButton();
		new ADM4_ActionNameDD().validateActionName();
		new ADM5_DocumentName().validateDocumentName();
		new ADM6_MandatoryUploadDD().validateMandatoryUpload();
		new ADM7_IfOriginalDD().validateIfOriginal();
		new ADM8_SaveBack().validateSaveBack();

		System.out.println("=================================================");
		System.out.println("FINAL assertAll() - Action Doc Map All Results");
		sa.assertAll();
	}

	@AfterClass(alwaysRun = true)
	public void tearDown() {
		ReportManager.flushReport();
		if (driver != null) { driver.quit(); driver = null; }
	}
}
