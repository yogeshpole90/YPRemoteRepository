package Courtcase_Package;

import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;

public class CC14_BankruptNo extends CC2_Login {

	public void validateBankruptNo() throws Exception
	{
		WebElement bkNo = driver.findElement(By.id("bankruptcyCaseNo"));
		jse.executeScript("arguments[0].scrollIntoView({block:'center'})", bkNo);

		// Pre-check: field visible only if Bankruptcy Case = Yes
		if (!bkNo.isDisplayed()) {
			System.out.println("=================================================");
			System.out.println("BN SKIP : Bankruptcy Case No NOT visible (Bankruptcy Case != Yes) | SKIPPED");
			sa.fail("BUG: Bankruptcy Case No NOT visible even after Yes!");
			return;
		}

		System.out.println("=================================================");
		sa.assertTrue(bkNo.isDisplayed(), "Bankruptcy Case No not displayed.");
		System.out.println("BN Case 1 : Is Displayed? → " + bkNo.isDisplayed() + " | PASS");

		System.out.println("=================================================");
		sa.assertTrue(bkNo.isEnabled(), "Bankruptcy Case No is disabled.");
		System.out.println("BN Case 2 : Is Enabled? → " + bkNo.isEnabled() + " | PASS");

		System.out.println("=================================================");
		bkNo.clear();
		bkNo.sendKeys("12345");
		String numVal = bkNo.getAttribute("value");
		sa.assertEquals(numVal, "12345", "Not accepting numeric.");
		System.out.println("BN Case 3 : Entered '12345' → Got '" + numVal + "' | Accepted Numeric");

		System.out.println("=================================================");
		bkNo.clear();
		bkNo.sendKeys("abcdef");
		String alphaVal = bkNo.getAttribute("value");
		System.out.println("BN Case 4 : Entered 'abcdef' → Got '" + alphaVal + "' | " + (alphaVal.equals("abcdef") ? "Accepted Alphabets" : "Rejected Alphabets"));

		System.out.println("=================================================");
		bkNo.clear();
		bkNo.sendKeys("@#$%");
		String splVal = bkNo.getAttribute("value");
		System.out.println("BN Case 5 : Entered '@#$%' → Got '" + splVal + "' | " + (splVal.equals("@#$%") ? "Accepted Special Chars" : "Rejected Special Chars"));

		System.out.println("=================================================");
		bkNo.clear();
		bkNo.sendKeys("Case123");
		String mixVal = bkNo.getAttribute("value");
		System.out.println("BN Case 6 : Entered 'Case123' → Got '" + mixVal + "' | " + (mixVal.equals("Case123") ? "Accepted Alphanumeric" : "Rejected Alphanumeric"));

		System.out.println("=================================================");
		bkNo.clear();
		String emptyVal = bkNo.getAttribute("value");
		sa.assertTrue(emptyVal.isEmpty(), "Field not clearing.");
		System.out.println("BN Case 7 : Cleared → Got '" + emptyVal + "' | " + (emptyVal.isEmpty() ? "Field is Empty - PASS" : "Field not Empty - FAIL"));

		System.out.println("=================================================");
		bkNo.clear();
		bkNo.sendKeys("   ");
		String spaceVal = bkNo.getAttribute("value");
		System.out.println("BN Case 8 : Entered '   ' → Got '" + spaceVal + "' | " + (spaceVal.trim().isEmpty() ? "Only Spaces" : "Has Content"));

		System.out.println("=================================================");
		bkNo.clear();
		bkNo.sendKeys("99999999999999999999");
		String maxVal = bkNo.getAttribute("value");
		System.out.println("BN Case 9 : Entered 20 chars → Got length " + maxVal.length() + " | Max Length Check");

		System.out.println("=================================================");
		bkNo.clear();
		bkNo.sendKeys("-100");
		String negVal = bkNo.getAttribute("value");
		System.out.println("BN Case 10 : Entered '-100' → Got '" + negVal + "' | " + (negVal.equals("-100") ? "Accepted Negative" : "Rejected Negative"));

		System.out.println("=================================================");
		bkNo.clear();
		bkNo.sendKeys("BK2025001");
		String finalVal = bkNo.getAttribute("value");
		System.out.println("BN Case 11 : Final value set → '" + finalVal + "' for record save");

		System.out.println("=================================================");
		System.out.println("CC14_BankruptNo - All 11 cases executed.");
	}

}
