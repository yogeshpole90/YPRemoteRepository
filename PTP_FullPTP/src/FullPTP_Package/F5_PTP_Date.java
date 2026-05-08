package FullPTP_Package;

import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;

/**
 * F5_PTP_Date - Date Validation for 'PTP Start Date' field
 * 
 * Cases: Displayed, Enabled, Clickable, Default Empty, Valid Date,
 *        Invalid Format, Alphabets, Special Chars, Numeric Only,
 *        Invalid Day/Month, Leap Year, Future/Past, Spaces, Max Length
 */
public class F5_PTP_Date extends A1_LoginSetup {

	public void validateDate()
	{
		WebElement ptpDate = driver.findElement(By.id("dateOfPTPStart"));

		// Case 1: Displayed
		System.out.println("=================================================");
		sa.assertTrue(ptpDate.isDisplayed(), "PTP Date not displayed.");
		System.out.println("DT Case 1 : Field is Displayed.");

		// Case 2: Enabled
		System.out.println("=================================================");
		sa.assertTrue(ptpDate.isEnabled(), "PTP Date is disabled.");
		System.out.println("DT Case 2 : Field is Enabled.");

		// Case 3: Clickable
		System.out.println("=================================================");
		ptpDate.click();
		System.out.println("DT Case 3 : Field is Clickable.");

		// Case 4: Default Empty
		System.out.println("=================================================");
		String defaultVal = ptpDate.getAttribute("value");
		sa.assertTrue(defaultVal.isEmpty(), "Default date not empty.");
		System.out.println("DT Case 4 : Default → '" + defaultVal + "'");

		// Case 5: Valid Date (DD-MM-YYYY)
		System.out.println("=================================================");
		ptpDate.clear();
		ptpDate.sendKeys("19-12-2021");
		String validDate = ptpDate.getAttribute("value");
		sa.assertEquals(validDate, "19-12-2021", "Not accepting valid date.");
		System.out.println("DT Case 5 : Valid Date → " + validDate);

		// Case 6: Invalid Format (YYYY/MM/DD)
		System.out.println("=================================================");
		ptpDate.clear();
		ptpDate.sendKeys("2021/12/19");
		String invalidFmt = ptpDate.getAttribute("value");
		sa.assertNotEquals(invalidFmt, "2021/12/19", "Accepting invalid format.");
		System.out.println("DT Case 6 : Invalid format → '" + invalidFmt + "'");

		// Case 7: Alphabets Rejected
		System.out.println("=================================================");
		ptpDate.clear();
		ptpDate.sendKeys("abcdef");
		String alphaVal = ptpDate.getAttribute("value");
		sa.assertTrue(alphaVal.isEmpty(), "Accepting alphabets.");
		System.out.println("DT Case 7 : Alphabets → '" + alphaVal + "'");

		// Case 8: Special Characters Rejected
		System.out.println("=================================================");
		ptpDate.clear();
		ptpDate.sendKeys("@#$%^&");
		String splVal = ptpDate.getAttribute("value");
		sa.assertTrue(splVal.isEmpty(), "Accepting special chars.");
		System.out.println("DT Case 8 : Special chars → '" + splVal + "'");

		// Case 9: Only Numeric + Separator
		System.out.println("=================================================");
		ptpDate.clear();
		ptpDate.sendKeys("19-12-2021");
		String numDate = ptpDate.getAttribute("value");
		sa.assertTrue(numDate.matches("[0-9\\-/]+"), "Non-numeric chars present.");
		System.out.println("DT Case 9 : Numeric check → " + numDate);

		// Case 10: Invalid Day (32)
		System.out.println("=================================================");
		ptpDate.clear();
		ptpDate.sendKeys("32-12-2021");
		String day32 = ptpDate.getAttribute("value");
		sa.assertNotEquals(day32, "32-12-2021", "Accepting day 32.");
		System.out.println("DT Case 10 : Day 32 → '" + day32 + "'");

		// Case 11: Invalid Month (13)
		System.out.println("=================================================");
		ptpDate.clear();
		ptpDate.sendKeys("15-13-2021");
		String month13 = ptpDate.getAttribute("value");
		sa.assertNotEquals(month13, "15-13-2021", "Accepting month 13.");
		System.out.println("DT Case 11 : Month 13 → '" + month13 + "'");

		// Case 12: Feb 29 Non-Leap Year
		System.out.println("=================================================");
		ptpDate.clear();
		ptpDate.sendKeys("29-02-2023");
		String nonLeap = ptpDate.getAttribute("value");
		sa.assertNotEquals(nonLeap, "29-02-2023", "Accepting Feb 29 non-leap.");
		System.out.println("DT Case 12 : Feb 29 non-leap → '" + nonLeap + "'");

		// Case 13: Feb 29 Leap Year (Valid)
		System.out.println("=================================================");
		ptpDate.clear();
		ptpDate.sendKeys("29-02-2024");
		String leapDate = ptpDate.getAttribute("value");
		sa.assertEquals(leapDate, "29-02-2024", "Not accepting Feb 29 leap.");
		System.out.println("DT Case 13 : Feb 29 leap → " + leapDate);

		// Case 14: Future Date
		System.out.println("=================================================");
		ptpDate.clear();
		ptpDate.sendKeys("01-01-2099");
		String futureDate = ptpDate.getAttribute("value");
		sa.assertNotEquals(futureDate, "01-01-2099", "Accepting far future.");
		System.out.println("DT Case 14 : Future date → '" + futureDate + "'");

		// Case 15: Very Old Past Date
		System.out.println("=================================================");
		ptpDate.clear();
		ptpDate.sendKeys("01-01-1900");
		String pastDate = ptpDate.getAttribute("value");
		sa.assertNotEquals(pastDate, "01-01-1900", "Accepting very old date.");
		System.out.println("DT Case 15 : Past date → '" + pastDate + "'");

		// Case 16: Day 00
		System.out.println("=================================================");
		ptpDate.clear();
		ptpDate.sendKeys("00-12-2021");
		String day00 = ptpDate.getAttribute("value");
		sa.assertNotEquals(day00, "00-12-2021", "Accepting day 00.");
		System.out.println("DT Case 16 : Day 00 → '" + day00 + "'");

		// Case 17: Month 00
		System.out.println("=================================================");
		ptpDate.clear();
		ptpDate.sendKeys("15-00-2021");
		String month00 = ptpDate.getAttribute("value");
		sa.assertNotEquals(month00, "15-00-2021", "Accepting month 00.");
		System.out.println("DT Case 17 : Month 00 → '" + month00 + "'");

		// Case 18: Spaces Only
		System.out.println("=================================================");
		ptpDate.clear();
		ptpDate.sendKeys("   ");
		String spaceVal = ptpDate.getAttribute("value");
		sa.assertTrue(spaceVal.trim().isEmpty(), "Accepting only spaces.");
		System.out.println("DT Case 18 : Spaces → '" + spaceVal + "'");

		// Case 19: Max Length (DD-MM-YYYY = 10 chars)
		System.out.println("=================================================");
		ptpDate.clear();
		ptpDate.sendKeys("19-12-20211234");
		String maxLen = ptpDate.getAttribute("value");
		sa.assertTrue(maxLen.length() <= 10, "Exceeding max length.");
		System.out.println("DT Case 19 : Max length → " + maxLen.length());

		// Case 20: Alphanumeric Mix
		System.out.println("=================================================");
		ptpDate.clear();
		ptpDate.sendKeys("1a-2b-20cd");
		String mixVal = ptpDate.getAttribute("value");
		sa.assertNotEquals(mixVal, "1a-2b-20cd", "Accepting alphanumeric.");
		System.out.println("DT Case 20 : Alphanumeric → '" + mixVal + "'");

		// Clear field for next class
		ptpDate.clear();

		System.out.println("=================================================");
		System.out.println("F5_PTP_Date - All cases executed.");
	}

}
