package SeleniumPackage;

import org.testng.annotations.Test;

/**
 * A2_CallingClass - Master Test Controller
 * 
 * Execution Order:
 * Step 1 → Browser Launch        (A1_LoginSetup)
 * Step 2 → Login & Navigate      (A2_Login)
 * Step 3 → Switch to Frame       (A3_Police)
 * Step 4 → Case Type DD - 9 Cases   (A4_DD_CaseType)
 * Step 5 → Action Date - 23 Cases   (A5_DateValidation)
 * Step 6 → Action Amount - 12 Cases (A6_ActAmt)
 * Step 7 → Action Taken DD - 9 Cases(A7_actionTaken)
 * 
 * NOTE: sa.assertAll() is called ONLY HERE at the end.
 * All child classes collect failures using SoftAssert (sa),
 * but do NOT call assertAll() — so execution never stops midway.
 */
public class A2_CallingClass extends A1_LoginSetup
{

	@Test
	public void init() throws Exception {

		// Step 1: Launch browser
		a1setup();

		// Step 2: Login, search case 411, open Police Complaint tab
		A2_Login login = new A2_Login();
		login.a2login();

		// Step 3: Switch to Police Complaint iframe
		A3_Police police = new A3_Police();
		police.switchToPoliceFrame();

		// Step 4: Validate 'Case Type' Dropdown
		A4_DD_CaseType dd = new A4_DD_CaseType();
		dd.validateDropdown();

		// Step 5: Validate 'Action Date' field
		A5_DateValidation date = new A5_DateValidation();
		date.date1();

		// Step 6: Validate 'Police Action Amount' numeric field
		A6_ActAmt num = new A6_ActAmt();
		num.numeric();

		// Step 7: Validate 'Action Taken' Dropdown
		A7_actionTaken at = new A7_actionTaken();
		at.actTaken();

		// FINAL: Report ALL soft assertion failures at once
		System.out.println("=================================================");
		System.out.println("FINAL assertAll() - Reporting all results");
		
		//should be write at Last ONLY
		sa.assertAll();
	}

}
