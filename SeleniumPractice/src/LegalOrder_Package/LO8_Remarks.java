package LegalOrder_Package;

import org.openqa.selenium.*;
import org.testng.annotations.Test;

public class LO8_Remarks extends LO2_Login {

	@Test
	public void validateRemarks() throws Exception
	{
		System.out.println("========== REMARKS ==========");
		int caseNo = 1;

		WebElement field = driver.findElement(By.id("remark"));

		// Case 1: Displayed
		System.out.println("Case " + caseNo + " | remark displayed | " + field.isDisplayed());
		sa.assertTrue(field.isDisplayed(), "BUG: remark not displayed");
		caseNo++;

		// Case 2: Enabled
		System.out.println("Case " + caseNo + " | remark enabled | " + field.isEnabled());
		sa.assertTrue(field.isEnabled(), "BUG: remark not enabled");
		caseNo++;

		// Case 3: Empty check
		field.clear();
		String emptyVal = field.getAttribute("value");
		System.out.println("Case " + caseNo + " | remark empty | " + emptyVal);
		sa.assertTrue(emptyVal.isEmpty(), "BUG: remark not empty after clear");
		caseNo++;

		// Case 4: Enter text
		field.sendKeys("Test Remark Selenium");
		String textVal = field.getAttribute("value");
		System.out.println("Case " + caseNo + " | remark text entered | " + textVal);
		sa.assertEquals(textVal, "Test Remark Selenium", "BUG: remark value mismatch");
		caseNo++;

		// Case 5: Special characters
		field.clear();
		field.sendKeys("@#$%&*!");
		String specialVal = field.getAttribute("value");
		System.out.println("Case " + caseNo + " | remark special chars | " + specialVal);
		caseNo++;

		// Keep valid value
		field.clear();
		field.sendKeys("Test Remark Selenium");

		System.out.println("========== REMARKS Complete ==========\n");
	}
}
