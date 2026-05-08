package Courtcase_Package;

import org.openqa.selenium.By;
import org.openqa.selenium.Keys;
import org.openqa.selenium.WebElement;

public class CC13_BankruptDate extends CC2_Login {

	public void validateBankruptDate()
	{
		WebElement bkDate = driver.findElement(By.id("bankruptcyCaseDate"));
		jse.executeScript("arguments[0].scrollIntoView({block:'center'})", bkDate);

		// Pre-check: field visible only if Bankruptcy Case = Yes
		if (!bkDate.isDisplayed()) {
			System.out.println("=================================================");
			System.out.println("BD SKIP : Bankruptcy Date NOT visible (Bankruptcy Case != Yes) | SKIPPED");
			sa.fail("BUG: Bankruptcy Date NOT visible even after Yes!");
			return;
		}

		System.out.println("=================================================");
		sa.assertTrue(bkDate.isDisplayed(), "Bankruptcy Date not displayed.");
		System.out.println("BD Case 1 : Is Displayed? → " + bkDate.isDisplayed() + " | PASS");

		System.out.println("=================================================");
		sa.assertTrue(bkDate.isEnabled(), "Bankruptcy Date is disabled.");
		System.out.println("BD Case 2 : Is Enabled? → " + bkDate.isEnabled() + " | PASS");

		System.out.println("=================================================");
		jse.executeScript("arguments[0].click()", bkDate);
		bkDate.sendKeys(Keys.TAB);
		System.out.println("BD Case 3 : Clicked on Date field | Accepted Click - PASS");

		System.out.println("=================================================");
		String defaultVal = bkDate.getAttribute("value");
		System.out.println("BD Case 4 : Default value → '" + defaultVal + "' | " + (defaultVal.isEmpty() ? "Default is Empty - PASS" : "Has Default Value"));

		System.out.println("=================================================");
		bkDate.clear();
		bkDate.sendKeys("22-12-2025");
		bkDate.sendKeys(Keys.TAB);
		String validDate = bkDate.getAttribute("value");
		sa.assertEquals(validDate, "22-12-2025", "Not accepting valid date.");
		System.out.println("BD Case 5 : Entered '22-12-2025' → Got '" + validDate + "' | Accepted Valid Date");

		System.out.println("=================================================");
		bkDate.clear();
		bkDate.sendKeys("2025/12/22");
		bkDate.sendKeys(Keys.TAB);
		String invalidFmt = bkDate.getAttribute("value");
		sa.assertNotEquals(invalidFmt, "2025/12/22", "Accepting invalid format.");
		System.out.println("BD Case 6 : Entered '2025/12/22' → Got '" + invalidFmt + "' | " + (!invalidFmt.equals("2025/12/22") ? "Rejected Invalid Format" : "Accepted Invalid Format - FAIL"));

		System.out.println("=================================================");
		bkDate.clear();
		bkDate.sendKeys("abcdef");
		bkDate.sendKeys(Keys.TAB);
		String alphaVal = bkDate.getAttribute("value");
		sa.assertTrue(alphaVal.isEmpty(), "Accepting alphabets.");
		System.out.println("BD Case 7 : Entered 'abcdef' → Got '" + alphaVal + "' | " + (alphaVal.isEmpty() ? "Rejected Alphabets" : "Accepted Alphabets - FAIL"));

		System.out.println("=================================================");
		bkDate.clear();
		bkDate.sendKeys("@#$%^&");
		bkDate.sendKeys(Keys.TAB);
		String splVal = bkDate.getAttribute("value");
		sa.assertTrue(splVal.isEmpty(), "Accepting special chars.");
		System.out.println("BD Case 8 : Entered '@#$%^&' → Got '" + splVal + "' | " + (splVal.isEmpty() ? "Rejected Special Chars" : "Accepted Special Chars - FAIL"));

		System.out.println("=================================================");
		bkDate.clear();
		bkDate.sendKeys("22-12-2025");
		bkDate.sendKeys(Keys.TAB);
		String numDate = bkDate.getAttribute("value");
		sa.assertTrue(numDate.matches("[0-9\\-/]+"), "Non-numeric chars present.");
		System.out.println("BD Case 9 : Entered '22-12-2025' → Got '" + numDate + "' | " + (numDate.matches("[0-9\\-/]+") ? "Accepted Numeric+Separator" : "Contains Non-Numeric - FAIL"));

		System.out.println("=================================================");
		bkDate.clear();
		bkDate.sendKeys("32-12-2025");
		bkDate.sendKeys(Keys.TAB);
		String day32 = bkDate.getAttribute("value");
		sa.assertNotEquals(day32, "32-12-2025", "Accepting day 32.");
		System.out.println("BD Case 10 : Entered '32-12-2025' → Got '" + day32 + "' | " + (!day32.equals("32-12-2025") ? "Rejected Day 32" : "Accepted Day 32 - FAIL"));

		System.out.println("=================================================");
		bkDate.clear();
		bkDate.sendKeys("15-13-2025");
		bkDate.sendKeys(Keys.TAB);
		String month13 = bkDate.getAttribute("value");
		sa.assertNotEquals(month13, "15-13-2025", "Accepting month 13.");
		System.out.println("BD Case 11 : Entered '15-13-2025' → Got '" + month13 + "' | " + (!month13.equals("15-13-2025") ? "Rejected Month 13" : "Accepted Month 13 - FAIL"));

		System.out.println("=================================================");
		bkDate.clear();
		bkDate.sendKeys("29-02-2023");
		bkDate.sendKeys(Keys.TAB);
		String nonLeap = bkDate.getAttribute("value");
		sa.assertNotEquals(nonLeap, "29-02-2023", "Accepting Feb 29 non-leap.");
		System.out.println("BD Case 12 : Entered '29-02-2023' → Got '" + nonLeap + "' | " + (!nonLeap.equals("29-02-2023") ? "Rejected Feb29 Non-Leap" : "Accepted Feb29 Non-Leap - FAIL"));

		System.out.println("=================================================");
		bkDate.clear();
		bkDate.sendKeys("29-02-2024");
		bkDate.sendKeys(Keys.TAB);
		String leapDate = bkDate.getAttribute("value");
		sa.assertEquals(leapDate, "29-02-2024", "Not accepting Feb 29 leap.");
		System.out.println("BD Case 13 : Entered '29-02-2024' → Got '" + leapDate + "' | " + (leapDate.equals("29-02-2024") ? "Accepted Feb29 Leap Year" : "Rejected Feb29 Leap Year - FAIL"));

		System.out.println("=================================================");
		bkDate.clear();
		bkDate.sendKeys("01-01-2099");
		bkDate.sendKeys(Keys.TAB);
		String futureDate = bkDate.getAttribute("value");
		sa.assertNotEquals(futureDate, "01-01-2099", "Accepting far future.");
		System.out.println("BD Case 14 : Entered '01-01-2099' → Got '" + futureDate + "' | " + (!futureDate.equals("01-01-2099") ? "Rejected Future Date" : "Accepted Future Date - FAIL"));

		System.out.println("=================================================");
		bkDate.clear();
		bkDate.sendKeys("01-01-1900");
		bkDate.sendKeys(Keys.TAB);
		String pastDate = bkDate.getAttribute("value");
		sa.assertNotEquals(pastDate, "01-01-1900", "Accepting very old date.");
		System.out.println("BD Case 15 : Entered '01-01-1900' → Got '" + pastDate + "' | " + (!pastDate.equals("01-01-1900") ? "Rejected Old Past Date" : "Accepted Old Past Date - FAIL"));

		System.out.println("=================================================");
		bkDate.clear();
		bkDate.sendKeys("00-12-2025");
		bkDate.sendKeys(Keys.TAB);
		String day00 = bkDate.getAttribute("value");
		sa.assertNotEquals(day00, "00-12-2025", "Accepting day 00.");
		System.out.println("BD Case 16 : Entered '00-12-2025' → Got '" + day00 + "' | " + (!day00.equals("00-12-2025") ? "Rejected Day 00" : "Accepted Day 00 - FAIL"));

		System.out.println("=================================================");
		bkDate.clear();
		bkDate.sendKeys("15-00-2025");
		bkDate.sendKeys(Keys.TAB);
		String month00 = bkDate.getAttribute("value");
		sa.assertNotEquals(month00, "15-00-2025", "Accepting month 00.");
		System.out.println("BD Case 17 : Entered '15-00-2025' → Got '" + month00 + "' | " + (!month00.equals("15-00-2025") ? "Rejected Month 00" : "Accepted Month 00 - FAIL"));

		System.out.println("=================================================");
		bkDate.clear();
		bkDate.sendKeys("   ");
		bkDate.sendKeys(Keys.TAB);
		String spaceVal = bkDate.getAttribute("value");
		sa.assertTrue(spaceVal.trim().isEmpty(), "Accepting only spaces.");
		System.out.println("BD Case 18 : Entered '   ' → Got '" + spaceVal + "' | " + (spaceVal.trim().isEmpty() ? "Rejected Spaces" : "Accepted Spaces - FAIL"));

		System.out.println("=================================================");
		bkDate.clear();
		bkDate.sendKeys("22-12-20251234");
		bkDate.sendKeys(Keys.TAB);
		String maxLen = bkDate.getAttribute("value");
		sa.assertTrue(maxLen.length() <= 10, "Exceeding max length.");
		System.out.println("BD Case 19 : Entered 14 chars → Got length " + maxLen.length() + " | " + (maxLen.length() <= 10 ? "Max Length OK" : "Exceeded Max Length - FAIL"));

		System.out.println("=================================================");
		bkDate.clear();
		bkDate.sendKeys("1a-2b-20cd");
		bkDate.sendKeys(Keys.TAB);
		String mixVal = bkDate.getAttribute("value");
		sa.assertNotEquals(mixVal, "1a-2b-20cd", "Accepting alphanumeric.");
		System.out.println("BD Case 20 : Entered '1a-2b-20cd' → Got '" + mixVal + "' | " + (!mixVal.equals("1a-2b-20cd") ? "Rejected Alphanumeric" : "Accepted Alphanumeric - FAIL"));

		bkDate.clear();
		bkDate.sendKeys("22-12-2025");
		bkDate.sendKeys(Keys.TAB);
		System.out.println("BD : Final value set → '22-12-2025' for record save");

		System.out.println("=================================================");
		System.out.println("CC13_BankruptDate - All 20 cases executed.");
	}

}
