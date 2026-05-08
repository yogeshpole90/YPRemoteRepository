package com.lcs.automation.tests;

import com.lcs.automation.base.BaseTest;
import com.lcs.automation.listeners.TestListener;
import com.lcs.automation.pages.remedialaction.RemedialActionPage;
import com.lcs.automation.reporting.ReportManager;
import com.lcs.automation.utils.LoginUtil;

import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Listeners;
import org.testng.annotations.Test;

@Listeners(TestListener.class)
public class RemedialActionTest extends BaseTest {

	RemedialActionPage page = new RemedialActionPage();

	@BeforeClass
	public void setup() throws Exception {
		setupBrowser();
		ReportManager.startTest("Remedial Action - Full Validation");
		LoginUtil.loginAndNavigate("411", "Remedial Action");
		page.switchToFrame();
	}

	@Test(priority = 1)
	public void testMandatory() throws Exception {
		page.validateMandatory();
	}

	@Test(priority = 2)
	public void testActionNameDD() throws Exception {
		page.validateActionNameDD();
	}

	@Test(priority = 3)
	public void testComments() throws Exception {
		page.validateComments();
	}

	@Test(priority = 4)
	public void testSaveView() throws Exception {
		page.validateSaveView();
	}

	@AfterClass(alwaysRun = true)
	public void tearDown() {
		sa.assertAll();
		ReportManager.flushReport();
		closeBrowser();
	}
}
