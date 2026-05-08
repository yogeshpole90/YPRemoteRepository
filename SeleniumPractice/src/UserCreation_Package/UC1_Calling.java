package UserCreation_Package;

import Utility_Package.ReportManager;
import Utility_Package.TestListener;
import org.testng.annotations.AfterClass;
import org.testng.annotations.Listeners;
import org.testng.annotations.Test;

@Listeners(TestListener.class)
public class UC1_Calling extends UC2_Login
{
	@Test
	public void init() throws Exception {

		ReportManager.setTestSuiteName("UserCreation");
		ReportManager.startTest("User Creation - Full Validation");

		ucLogin();

		new UC3_UserName().validateUserName();
		new UC4_EmpId().validateEmpId();
		new UC5_UserRole().validateUserRole();
		new UC6_Branch().validateBranch();
		new UC7_Salutation().validateSalutation();
		new UC8_FirstName().validateFirstName();
		new UC9_MiddleName().validateMiddleName();
		new UC10_LastName().validateLastName();
		new UC11_DisplayName().validateDisplayName();
		new UC12_ReportMgr().validateReportMgr();
		new UC13_UserCategory().validateUserCategory();
		new UC14_Language().validateLanguage();
		new UC15_Mobile().validateMobile();
		new UC16_Email().validateEmail();
		new UC17_Photo().validatePhoto();
		new UC18_HnwCategory().validateHnwCategory();
		new UC19B_BranchAccess().validateBranchAccess();
		new UC19_Checkboxes().validateCheckboxes();
		new UC20_Save().validateSave();

		// ========== PRINT ALL SAVED DATA ==========
		System.out.println("\n\n");
		System.out.println("╔═══════════════════════════════════════════════════════════╗");
		System.out.println("║         USER CREATION - SAVED DATA SUMMARY                ║");
		System.out.println("╠═══════════════════════════════════════════=═══════════════╣");
		try {
			System.out.println("║ User Name          : " + getVal("loginId"));
			System.out.println("║ Employee ID        : " + driver.findElement(org.openqa.selenium.By.id("select2-employeeId-container")).getAttribute("title"));
			System.out.println("║ User Role          : " + getDD("roleCode"));
			System.out.println("║ Assigned Branch    : " + driver.findElement(org.openqa.selenium.By.id("select2-assignedBranch-container")).getAttribute("title"));
			System.out.println("║ Salutation         : " + getDD("userSalutation"));
			System.out.println("║ First Name         : " + getVal("userFName"));
			System.out.println("║ Middle Name        : " + getVal("userMName"));
			System.out.println("║ Last Name          : " + getVal("userLName"));
			System.out.println("║ Display Name       : " + getVal("userDisplayName"));
			System.out.println("║ Reporting Manager  : " + getDD("reportingUserCode"));
			System.out.println("║ User Category      : " + getDD("userTypeCode"));
			System.out.println("║ Language           : " + getDD("preferLang"));
			System.out.println("║ Mobile ISD         : " + getDD("isdmobileNo1"));
			System.out.println("║ Mobile Number      : " + getVal("mobileNo1"));
			System.out.println("║ Email ID           : " + getVal("emailId"));
			System.out.println("║ HNW Category       : " + getDD("hnwCategory"));
		} catch (Exception e) {
			System.out.println("║ ERROR reading some fields: " + e.getMessage());
		}
		System.out.println("╚═══════════════════════════════════════════════════════════╝");
		System.out.println("\n");

		System.out.println("=================================================");
		System.out.println("FINAL assertAll() - User Creation All Results");
		sa.assertAll();
	}

	@AfterClass(alwaysRun = true)
	public void tearDown() {
		ReportManager.flushReport();
		if (driver != null) { driver.quit(); driver = null; }
	}
}
