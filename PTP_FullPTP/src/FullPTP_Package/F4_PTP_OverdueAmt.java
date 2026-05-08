package FullPTP_Package;

import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;

/**
 * F4_PTP_OverdueAmt - Numeric Validation for 'Overdue Amount' field
 * 
 * Cases: Displayed, Enabled, Numeric, Alphabets, Special Chars,
 *        Alphanumeric, Negative, Decimal, Spaces, Leading Zeros, Max Length
 */
public class F4_PTP_OverdueAmt extends A1_LoginSetup {

	public void validateOverdueAmt()
	{
		WebElement odAmt = driver.findElement(By.id("overdueAmount"));

		// Case 1: Displayed
		System.out.println("=================================================");
		sa.assertTrue(odAmt.isDisplayed(), "Overdue Amount not displayed.");
		System.out.println("OD Case 1 : Field is Displayed.");

		// Case 2: Enabled
		System.out.println("=================================================");
		sa.assertTrue(odAmt.isEnabled(), "Overdue Amount is disabled.");
		System.out.println("OD Case 2 : Field is Enabled.");

		// Case 3: Accepts Numeric
		System.out.println("=================================================");
		odAmt.clear();
		odAmt.sendKeys("10000");
		String numVal = odAmt.getAttribute("value");
		sa.assertEquals(numVal, "10000", "Not accepting numeric.");
		System.out.println("OD Case 3 : Accepts Numeric → " + numVal);

		// Case 4: Rejects Alphabets
		System.out.println("=================================================");
		odAmt.clear();
		odAmt.sendKeys("abcd");
		String alphaVal = odAmt.getAttribute("value");
		sa.assertTrue(alphaVal.isEmpty(), "Accepting alphabets.");
		System.out.println("OD Case 4 : Alphabets → '" + alphaVal + "'");

		// Case 5: Rejects Special Characters
		System.out.println("=================================================");
		odAmt.clear();
		odAmt.sendKeys("@#$%");
		String splVal = odAmt.getAttribute("value");
		sa.assertTrue(splVal.isEmpty(), "Accepting special chars.");
		System.out.println("OD Case 5 : Special chars → '" + splVal + "'");

		// Case 6: Rejects Alphanumeric Mix
		System.out.println("=================================================");
		odAmt.clear();
		odAmt.sendKeys("abc123");
		String mixVal = odAmt.getAttribute("value");
		sa.assertNotEquals(mixVal, "abc123", "Accepting alphanumeric.");
		System.out.println("OD Case 6 : Alphanumeric → '" + mixVal + "'");

		// Case 7: Negative Number
		System.out.println("=================================================");
		odAmt.clear();
		odAmt.sendKeys("-5000");
		String negVal = odAmt.getAttribute("value");
		sa.assertNotEquals(negVal, "-5000", "Accepting negative.");
		System.out.println("OD Case 7 : Negative → '" + negVal + "'");

		// Case 8: Decimal Value
		System.out.println("=================================================");
		odAmt.clear();
		odAmt.sendKeys("1000.50");
		String decVal = odAmt.getAttribute("value");
		System.out.println("OD Case 8 : Decimal → '" + decVal + "'");

		// Case 9: Spaces Only
		System.out.println("=================================================");
		odAmt.clear();
		odAmt.sendKeys("   ");
		String spaceVal = odAmt.getAttribute("value");
		sa.assertTrue(spaceVal.trim().isEmpty(), "Accepting only spaces.");
		System.out.println("OD Case 9 : Spaces → '" + spaceVal + "'");

		// Case 10: Leading Zeros
		System.out.println("=================================================");
		odAmt.clear();
		odAmt.sendKeys("00500");
		String zeroVal = odAmt.getAttribute("value");
		System.out.println("OD Case 10 : Leading zeros → '" + zeroVal + "'");

		// Case 11: Empty/Blank
		System.out.println("=================================================");
		odAmt.clear();
		String emptyVal = odAmt.getAttribute("value");
		sa.assertTrue(emptyVal.isEmpty(), "Field not cleared.");
		System.out.println("OD Case 11 : Empty field → '" + emptyVal + "'");

		System.out.println("=================================================");
		System.out.println("F4_PTP_OverdueAmt - All cases executed.");
	}

}
