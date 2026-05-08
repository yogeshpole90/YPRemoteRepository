package EmployeeMaster_Package;

import Utility_Package.ReportManager;
import Utility_Package.TestListener;
import org.testng.annotations.AfterClass;
import org.testng.annotations.Listeners;
import org.testng.annotations.Test;

@Listeners(TestListener.class)
public class EM1_Calling extends EM2_Login
{
	@Test
	public void init() throws Exception {

		ReportManager.setTestSuiteName("EmployeeMaster");
		ReportManager.startTest("Employee Master - Full Validation");

		emLogin();

		EM3_EmpId empId = new EM3_EmpId(); empId.validateEmpId();
		EM4_Salutation sal = new EM4_Salutation(); sal.validateSalutation();
		EM5_EmpName name = new EM5_EmpName(); name.validateEmpName();
		EM6_Designation des = new EM6_Designation(); des.validateDesignation();
		EM7_JoinDate jd = new EM7_JoinDate(); jd.validateJoinDate();
		EM8_BirthDate dob = new EM8_BirthDate(); dob.validateBirthDate();
		EM9_Gender gen = new EM9_Gender(); gen.validateGender();
		EM10_EmpType et = new EM10_EmpType(); et.validateEmpType();
		EM11_BloodGrp bg = new EM11_BloodGrp(); bg.validateBloodGrp();
		EM12_IdProof idp = new EM12_IdProof(); idp.validateIdProof();
		EM13_IdNumber idn = new EM13_IdNumber(); idn.validateIdNumber();
		EM14_IssueDate isd = new EM14_IssueDate(); isd.validateIssueDate();
		EM15_DocIssued dib = new EM15_DocIssued(); dib.validateDocIssued();
		EM16_IdName ipn = new EM16_IdName(); ipn.validateIdName();
		EM17_Education edu = new EM17_Education(); edu.validateEducation();
		EM18_Religion rel = new EM18_Religion(); rel.validateReligion();
		EM19_Caste cst = new EM19_Caste(); cst.validateCaste();
		EM20_SubCaste sc = new EM20_SubCaste(); sc.validateSubCaste();
		EM21_Status sts = new EM21_Status(); sts.validateStatus();
		EM22_RetireDate rd = new EM22_RetireDate(); rd.validateRetireDate();
		EM23_Remark rmk = new EM23_Remark(); rmk.validateRemark();
		EM24_AddrIdType ait = new EM24_AddrIdType(); ait.validateAddrIdType();
		EM25_AddrIdNo ain = new EM25_AddrIdNo(); ain.validateAddrIdNo();
		EM26_Addr1 a1 = new EM26_Addr1(); a1.validateAddr1();
		EM27_Addr2 a2 = new EM27_Addr2(); a2.validateAddr2();
		EM28_Addr3 a3 = new EM28_Addr3(); a3.validateAddr3();
		EM29_Country ctr = new EM29_Country(); ctr.validateCountry();
		EM30_State stt = new EM30_State(); stt.validateState();
		EM31_City cty = new EM31_City(); cty.validateCity();
		EM32_PostalCode pc = new EM32_PostalCode(); pc.validatePostalCode();
		EM33_Email eml = new EM33_Email(); eml.validateEmail();
		EM34_OfficeTel otl = new EM34_OfficeTel(); otl.validateOfficeTel();
		EM35_Mobile mob = new EM35_Mobile(); mob.validateMobile();

		System.out.println("=================================================");
		System.out.println("FINAL assertAll() - Employee Master All Results");
		sa.assertAll();
	}

	@AfterClass(alwaysRun = true)
	public void tearDown() {
		ReportManager.flushReport();
		if (driver != null) { driver.quit(); driver = null; }
	}
}
