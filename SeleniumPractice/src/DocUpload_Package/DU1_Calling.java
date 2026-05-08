package DocUpload_Package;

import Utility_Package.ReportManager;
import Utility_Package.TestListener;
import org.testng.annotations.AfterClass;
import org.testng.annotations.Listeners;
import org.testng.annotations.Parameters;
import org.testng.annotations.Test;

@Listeners(TestListener.class)
public class DU1_Calling extends DU2_Login {

	@Test
	@Parameters({ "os","browser"})
	public void init(String os,String br) throws Exception {

		ReportManager.setTestSuiteName("DocUpload");
		ReportManager.startTest("Document Upload - Full Validation");

		duLogin(os, br);

		DU3_Frame frame = new DU3_Frame();
		copyContext(frame);
		frame.switchToFrame();

		DU4_ActionNameDD actionDD = new DU4_ActionNameDD();
		copyContext(actionDD);
		actionDD.validateActionName();

		DU5_DocumentName docName = new DU5_DocumentName();
		copyContext(docName);
		docName.validateDocumentName();

		DU6_FileUpload fileUpload = new DU6_FileUpload();
		copyContext(fileUpload);
		fileUpload.validateFileUpload();

		DU7_Save save = new DU7_Save();
		copyContext(save);
		save.validateSave();

		DU8_View view = new DU8_View();
		copyContext(view);
		view.validateView();

		DU9_Download download = new DU9_Download();
		copyContext(download);
		download.validateDownload();

		DU10_Delete delete = new DU10_Delete();
		copyContext(delete);
		delete.validateDelete();

		System.out.println("=================================================");
		System.out.println("FINAL assertAll() - Document Upload All Results");
		sa.assertAll();
	}

	private void copyContext(DU2_Login target) {
		target.driver = this.driver;
		target.jse = this.jse;
		target.act = this.act;
		target.sa = this.sa;
		target.browserName = this.browserName;
	}

	@AfterClass(alwaysRun = true)
	public void tearDown() {
		ReportManager.flushReport();
		if (driver != null) { driver.quit(); driver = null; }
	}
}
