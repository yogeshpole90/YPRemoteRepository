package DemandLetter_Package;

import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;

public class DL4_CaseNo extends DL2_Login {



	public void validateCaseNo() throws Exception
	{
		System.out.println("=================================================");
		System.out.println("DL4 - CASE NO FIELD VALIDATION START");
		System.out.println("=================================================");

		WebElement caseNo = driver.findElement(By.id("caseNo"));
		jse.executeScript("arguments[0].scrollIntoView({block:'center',behavior:'smooth'})", caseNo);
		Thread.sleep(1000);

		// TC1: Case No should be displayed
		boolean isDisplayed = caseNo.isDisplayed();
		log("Case No", "Case No field should be visible on page", "true", String.valueOf(isDisplayed), isDisplayed);
		sa.assertTrue(isDisplayed, "Case No should be visible");

		// TC2: Case No should be read-only
		String readonlyAttr = caseNo.getAttribute("readonly");
		boolean isReadOnly = readonlyAttr != null;
		log("Case No", "Case No field should be read-only (non-editable)", "readonly=readonly", "readonly=" + readonlyAttr, isReadOnly);
		sa.assertTrue(isReadOnly, "Case No should be read-only");

		// TC3: Case No should not be empty
		String caseVal = caseNo.getAttribute("value").trim();
		boolean notEmpty = !caseVal.isEmpty();
		log("Case No", "Case No should not be empty", "Not empty", caseVal, notEmpty);
		sa.assertTrue(notEmpty, "Case No should not be empty");

		// TC4: Case No should contain CASE_ prefix
		boolean hasPrefix = caseVal.contains("CASE_");
		log("Case No", "Case No should contain 'CASE_' prefix", "CASE_ prefix present", caseVal, hasPrefix);
		sa.assertTrue(hasPrefix, "Case No should contain CASE_ prefix");

		// TC5: Case No input type should be text
		String inputType = caseNo.getAttribute("type");
		boolean typeCheck = inputType.equals("text");
		log("Case No", "Case No input type should be 'text'", "text", inputType, typeCheck);
		sa.assertEquals(inputType, "text", "Input type should be text");

		// TC6: Case No maxlength should be 60
		String maxLen = caseNo.getAttribute("maxlength");
		boolean maxCheck = "60".equals(maxLen);
		log("Case No", "Case No maxlength should be 60", "60", maxLen, maxCheck);
		sa.assertEquals(maxLen, "60", "Maxlength should be 60");

		System.out.println("=================================================");
		System.out.println("DL4 - CASE NO FIELD VALIDATION END");
		System.out.println("=================================================");
	}
}


