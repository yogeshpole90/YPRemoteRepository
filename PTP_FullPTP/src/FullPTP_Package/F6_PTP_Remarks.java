package FullPTP_Package;

import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;

/**
 * F6_PTP_Remarks - TextArea Validation for 'Remarks' field
 * 
 * Cases: Displayed, Enabled, Accepts Text, Special Chars, Numeric,
 *        Empty, Spaces, Max Length, Multi-line, Placeholder
 */
public class F6_PTP_Remarks extends A1_LoginSetup {

	public void validateRemarks()
	{
		WebElement remarks = driver.findElement(By.id("remarks"));

		// Case 1: Displayed
		System.out.println("=================================================");
		sa.assertTrue(remarks.isDisplayed(), "Remarks not displayed.");
		System.out.println("RM Case 1 : Field is Displayed.");

		// Case 2: Enabled
		System.out.println("=================================================");
		sa.assertTrue(remarks.isEnabled(), "Remarks is disabled.");
		System.out.println("RM Case 2 : Field is Enabled.");

		// Case 3: Accepts Text
		System.out.println("=================================================");
		remarks.clear();
		remarks.sendKeys("OD and Remain Amt is zero");
		String textVal = remarks.getAttribute("value");
		sa.assertEquals(textVal, "OD and Remain Amt is zero", "Not accepting text.");
		System.out.println("RM Case 3 : Accepts text → " + textVal);

		// Case 4: Accepts Special Characters (textarea should allow)
		System.out.println("=================================================");
		remarks.clear();
		remarks.sendKeys("Test@#$%&*!");
		String splVal = remarks.getAttribute("value");
		sa.assertEquals(splVal, "Test@#$%&*!", "Not accepting special chars.");
		System.out.println("RM Case 4 : Special chars → " + splVal);

		// Case 5: Accepts Numeric
		System.out.println("=================================================");
		remarks.clear();
		remarks.sendKeys("12345");
		String numVal = remarks.getAttribute("value");
		sa.assertEquals(numVal, "12345", "Not accepting numeric.");
		System.out.println("RM Case 5 : Numeric → " + numVal);

		// Case 6: Accepts Alphanumeric Mix
		System.out.println("=================================================");
		remarks.clear();
		remarks.sendKeys("Test123Remark");
		String mixVal = remarks.getAttribute("value");
		sa.assertEquals(mixVal, "Test123Remark", "Not accepting alphanumeric.");
		System.out.println("RM Case 6 : Alphanumeric → " + mixVal);

		// Case 7: Empty/Blank
		System.out.println("=================================================");
		remarks.clear();
		String emptyVal = remarks.getAttribute("value");
		sa.assertTrue(emptyVal.isEmpty(), "Field not cleared.");
		System.out.println("RM Case 7 : Empty → '" + emptyVal + "'");

		// Case 8: Spaces Only
		System.out.println("=================================================");
		remarks.clear();
		remarks.sendKeys("     ");
		String spaceVal = remarks.getAttribute("value");
		System.out.println("RM Case 8 : Spaces only → '" + spaceVal + "'");

		// Case 9: Max Length Check
		System.out.println("=================================================");
		String maxLenAttr = remarks.getAttribute("maxlength");
		if (maxLenAttr != null) {
			int maxLen = Integer.parseInt(maxLenAttr);
			System.out.println("RM Case 9 : Max Length → " + maxLen);
		} else {
			System.out.println("RM Case 9 : No maxlength attribute found.");
		}

		// Case 10: Multi-line Text
		System.out.println("=================================================");
		remarks.clear();
		remarks.sendKeys("Line1\nLine2\nLine3");
		String multiLine = remarks.getAttribute("value");
		sa.assertTrue(multiLine.contains("\n"), "Not accepting multi-line.");
		System.out.println("RM Case 10 : Multi-line → " + multiLine);

		// Case 11: Read-Only Check (should NOT be readonly)
		System.out.println("=================================================");
		String readOnly = remarks.getAttribute("readonly");
		sa.assertNull(readOnly, "Remarks is read-only.");
		System.out.println("RM Case 11 : Read-only? " + readOnly);

		// Clear field for next class
		remarks.clear();

		System.out.println("=================================================");
		System.out.println("F6_PTP_Remarks - All cases executed.");
	}

}
