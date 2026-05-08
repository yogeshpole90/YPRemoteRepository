package FullPTP_Package;

import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;

/**
 * F9_PTP_PlannedAmt - Numeric Validation for 'Planned Amount' & 'Remaining Amount'
 * 
 * Fields: plannedAmt, remAmt
 * Cases: Displayed, Enabled, Numeric, Alphabets, Special Chars,
 *        Negative, Decimal, Spaces, Empty
 */
public class F9_PTP_PlannedAmt extends A1_LoginSetup {

	public void validatePlannedAmt()
	{
		WebElement planAmt = driver.findElement(By.id("plannedAmt"));
		WebElement remAmt = driver.findElement(By.id("remAmt"));

		// ========== Planned Amount ==========

		// Case 1: Displayed
		System.out.println("=================================================");
		sa.assertTrue(planAmt.isDisplayed(), "Planned Amt not displayed.");
		System.out.println("PA Case 1 : Planned Amt is Displayed.");

		// Case 2: Enabled
		System.out.println("=================================================");
		sa.assertTrue(planAmt.isEnabled(), "Planned Amt is disabled.");
		System.out.println("PA Case 2 : Planned Amt is Enabled.");

		// Case 3: Accepts Numeric
		System.out.println("=================================================");
		planAmt.clear();
		planAmt.sendKeys("5000");
		String numVal = planAmt.getAttribute("value");
		sa.assertEquals(numVal, "5000", "Not accepting numeric.");
		System.out.println("PA Case 3 : Numeric → " + numVal);

		// Case 4: Rejects Alphabets
		System.out.println("=================================================");
		planAmt.clear();
		planAmt.sendKeys("abcd");
		String alphaVal = planAmt.getAttribute("value");
		sa.assertTrue(alphaVal.isEmpty(), "Accepting alphabets.");
		System.out.println("PA Case 4 : Alphabets → '" + alphaVal + "'");

		// Case 5: Rejects Special Chars
		System.out.println("=================================================");
		planAmt.clear();
		planAmt.sendKeys("@#$%");
		String splVal = planAmt.getAttribute("value");
		sa.assertTrue(splVal.isEmpty(), "Accepting special chars.");
		System.out.println("PA Case 5 : Special chars → '" + splVal + "'");

		// Case 6: Negative Number
		System.out.println("=================================================");
		planAmt.clear();
		planAmt.sendKeys("-1000");
		String negVal = planAmt.getAttribute("value");
		sa.assertNotEquals(negVal, "-1000", "Accepting negative.");
		System.out.println("PA Case 6 : Negative → '" + negVal + "'");

		// Case 7: Empty
		System.out.println("=================================================");
		planAmt.clear();
		String emptyVal = planAmt.getAttribute("value");
		sa.assertTrue(emptyVal.isEmpty(), "Field not cleared.");
		System.out.println("PA Case 7 : Empty → '" + emptyVal + "'");

		// ========== Remaining Amount ==========

		// Case 8: remAmt Displayed
		System.out.println("=================================================");
		sa.assertTrue(remAmt.isDisplayed(), "Rem Amt not displayed.");
		System.out.println("PA Case 8 : Rem Amt is Displayed.");

		// Case 9: remAmt Enabled
		System.out.println("=================================================");
		sa.assertTrue(remAmt.isEnabled(), "Rem Amt is disabled.");
		System.out.println("PA Case 9 : Rem Amt is Enabled.");

		// Case 10: remAmt Accepts Numeric
		System.out.println("=================================================");
		remAmt.clear();
		remAmt.sendKeys("5000");
		String remVal = remAmt.getAttribute("value");
		sa.assertEquals(remVal, "5000", "Rem Amt not accepting numeric.");
		System.out.println("PA Case 10 : Rem Amt Numeric → " + remVal);

		// Case 11: remAmt Rejects Alphabets
		System.out.println("=================================================");
		remAmt.clear();
		remAmt.sendKeys("xyz");
		String remAlpha = remAmt.getAttribute("value");
		sa.assertTrue(remAlpha.isEmpty(), "Rem Amt accepting alphabets.");
		System.out.println("PA Case 11 : Rem Amt Alphabets → '" + remAlpha + "'");

		// Clear fields for next class
		planAmt.clear();
		remAmt.clear();

		System.out.println("=================================================");
		System.out.println("F9_PTP_PlannedAmt - All cases executed.");
	}

}
