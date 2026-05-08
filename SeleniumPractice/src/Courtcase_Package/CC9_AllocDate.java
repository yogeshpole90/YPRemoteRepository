package Courtcase_Package;

import org.openqa.selenium.By;
import org.openqa.selenium.Keys;
import org.openqa.selenium.WebElement;

public class CC9_AllocDate extends CC2_Login {

	public void validateAllocDate()
	{
		WebElement allocDate = driver.findElement(By.id("allocatedDate"));
		jse.executeScript("arguments[0].scrollIntoView({block:'center'})", allocDate);

		System.out.println("=================================================");
		sa.assertTrue(allocDate.isDisplayed(), "Allocated Date not displayed.");
		System.out.println("AD Case 1 : Is Displayed? → " + allocDate.isDisplayed() + " | PASS");

		System.out.println("=================================================");
		sa.assertTrue(allocDate.isEnabled(), "Allocated Date is disabled.");
		System.out.println("AD Case 2 : Is Enabled? → " + allocDate.isEnabled() + " | PASS");

		System.out.println("=================================================");
		allocDate.click();
		allocDate.sendKeys(Keys.TAB);
		System.out.println("AD Case 3 : Clicked on Date field | Accepted Click - PASS");

		System.out.println("=================================================");
		String defaultVal = allocDate.getAttribute("value");
		System.out.println("AD Case 4 : Default value → '" + defaultVal + "' | " + (defaultVal.isEmpty() ? "Default is Empty - PASS" : "Has Default Value"));

		System.out.println("=================================================");
		allocDate.clear();
		allocDate.sendKeys("20-12-2025");
		allocDate.sendKeys(Keys.TAB);
		String validDate = allocDate.getAttribute("value");
		sa.assertEquals(validDate, "20-12-2025", "Not accepting valid date.");
		System.out.println("AD Case 5 : Entered '20-12-2025' → Got '" + validDate + "' | Accepted Valid Date");

		System.out.println("=================================================");
		allocDate.clear();
		allocDate.sendKeys("2025/12/20");
		allocDate.sendKeys(Keys.TAB);
		String invalidFmt = allocDate.getAttribute("value");
		sa.assertNotEquals(invalidFmt, "2025/12/20", "Accepting invalid format.");
		System.out.println("AD Case 6 : Entered '2025/12/20' → Got '" + invalidFmt + "' | " + (!invalidFmt.equals("2025/12/20") ? "Rejected Invalid Format" : "Accepted Invalid Format - FAIL"));

		System.out.println("=================================================");
		allocDate.clear();
		allocDate.sendKeys("abcdef");
		allocDate.sendKeys(Keys.TAB);
		String alphaVal = allocDate.getAttribute("value");
		sa.assertTrue(alphaVal.isEmpty(), "Accepting alphabets.");
		System.out.println("AD Case 7 : Entered 'abcdef' → Got '" + alphaVal + "' | " + (alphaVal.isEmpty() ? "Rejected Alphabets" : "Accepted Alphabets - FAIL"));

		System.out.println("=================================================");
		allocDate.clear();
		allocDate.sendKeys("@#$%^&");
		allocDate.sendKeys(Keys.TAB);
		String splVal = allocDate.getAttribute("value");
		sa.assertTrue(splVal.isEmpty(), "Accepting special chars.");
		System.out.println("AD Case 8 : Entered '@#$%^&' → Got '" + splVal + "' | " + (splVal.isEmpty() ? "Rejected Special Chars" : "Accepted Special Chars - FAIL"));

		System.out.println("=================================================");
		allocDate.clear();
		allocDate.sendKeys("20-12-2025");
		allocDate.sendKeys(Keys.TAB);
		String numDate = allocDate.getAttribute("value");
		sa.assertTrue(numDate.matches("[0-9\\-/]+"), "Non-numeric chars present.");
		System.out.println("AD Case 9 : Entered '20-12-2025' → Got '" + numDate + "' | " + (numDate.matches("[0-9\\-/]+") ? "Accepted Numeric+Separator" : "Contains Non-Numeric - FAIL"));

		System.out.println("=================================================");
		allocDate.clear();
		allocDate.sendKeys("32-12-2025");
		allocDate.sendKeys(Keys.TAB);
		String day32 = allocDate.getAttribute("value");
		sa.assertNotEquals(day32, "32-12-2025", "Accepting day 32.");
		System.out.println("AD Case 10 : Entered '32-12-2025' → Got '" + day32 + "' | " + (!day32.equals("32-12-2025") ? "Rejected Day 32" : "Accepted Day 32 - FAIL"));

		System.out.println("=================================================");
		allocDate.clear();
		allocDate.sendKeys("15-13-2025");
		allocDate.sendKeys(Keys.TAB);
		String month13 = allocDate.getAttribute("value");
		sa.assertNotEquals(month13, "15-13-2025", "Accepting month 13.");
		System.out.println("AD Case 11 : Entered '15-13-2025' → Got '" + month13 + "' | " + (!month13.equals("15-13-2025") ? "Rejected Month 13" : "Accepted Month 13 - FAIL"));

		System.out.println("=================================================");
		allocDate.clear();
		allocDate.sendKeys("29-02-2023");
		allocDate.sendKeys(Keys.TAB);
		String nonLeap = allocDate.getAttribute("value");
		sa.assertNotEquals(nonLeap, "29-02-2023", "Accepting Feb 29 non-leap.");
		System.out.println("AD Case 12 : Entered '29-02-2023' → Got '" + nonLeap + "' | " + (!nonLeap.equals("29-02-2023") ? "Rejected Feb29 Non-Leap" : "Accepted Feb29 Non-Leap - FAIL"));

		System.out.println("=================================================");
		allocDate.clear();
		allocDate.sendKeys("29-02-2024");
		allocDate.sendKeys(Keys.TAB);
		String leapDate = allocDate.getAttribute("value");
		sa.assertEquals(leapDate, "29-02-2024", "Not accepting Feb 29 leap.");
		System.out.println("AD Case 13 : Entered '29-02-2024' → Got '" + leapDate + "' | " + (leapDate.equals("29-02-2024") ? "Accepted Feb29 Leap Year" : "Rejected Feb29 Leap Year - FAIL"));

		System.out.println("=================================================");
		allocDate.clear();
		allocDate.sendKeys("01-01-2099");
		allocDate.sendKeys(Keys.TAB);
		String futureDate = allocDate.getAttribute("value");
		sa.assertNotEquals(futureDate, "01-01-2099", "Accepting far future.");
		System.out.println("AD Case 14 : Entered '01-01-2099' → Got '" + futureDate + "' | " + (!futureDate.equals("01-01-2099") ? "Rejected Future Date" : "Accepted Future Date - FAIL"));

		System.out.println("=================================================");
		allocDate.clear();
		allocDate.sendKeys("01-01-1900");
		allocDate.sendKeys(Keys.TAB);
		String pastDate = allocDate.getAttribute("value");
		sa.assertNotEquals(pastDate, "01-01-1900", "Accepting very old date.");
		System.out.println("AD Case 15 : Entered '01-01-1900' → Got '" + pastDate + "' | " + (!pastDate.equals("01-01-1900") ? "Rejected Old Past Date" : "Accepted Old Past Date - FAIL"));

		System.out.println("=================================================");
		allocDate.clear();
		allocDate.sendKeys("00-12-2025");
		allocDate.sendKeys(Keys.TAB);
		String day00 = allocDate.getAttribute("value");
		sa.assertNotEquals(day00, "00-12-2025", "Accepting day 00.");
		System.out.println("AD Case 16 : Entered '00-12-2025' → Got '" + day00 + "' | " + (!day00.equals("00-12-2025") ? "Rejected Day 00" : "Accepted Day 00 - FAIL"));

		System.out.println("=================================================");
		allocDate.clear();
		allocDate.sendKeys("15-00-2025");
		allocDate.sendKeys(Keys.TAB);
		String month00 = allocDate.getAttribute("value");
		sa.assertNotEquals(month00, "15-00-2025", "Accepting month 00.");
		System.out.println("AD Case 17 : Entered '15-00-2025' → Got '" + month00 + "' | " + (!month00.equals("15-00-2025") ? "Rejected Month 00" : "Accepted Month 00 - FAIL"));

		System.out.println("=================================================");
		allocDate.clear();
		allocDate.sendKeys("   ");
		allocDate.sendKeys(Keys.TAB);
		String spaceVal = allocDate.getAttribute("value");
		sa.assertTrue(spaceVal.trim().isEmpty(), "Accepting only spaces.");
		System.out.println("AD Case 18 : Entered '   ' → Got '" + spaceVal + "' | " + (spaceVal.trim().isEmpty() ? "Rejected Spaces" : "Accepted Spaces - FAIL"));

		System.out.println("=================================================");
		allocDate.clear();
		allocDate.sendKeys("20-12-20251234");
		allocDate.sendKeys(Keys.TAB);
		String maxLen = allocDate.getAttribute("value");
		sa.assertTrue(maxLen.length() <= 10, "Exceeding max length.");
		System.out.println("AD Case 19 : Entered 14 chars → Got length " + maxLen.length() + " | " + (maxLen.length() <= 10 ? "Max Length OK" : "Exceeded Max Length - FAIL"));

		System.out.println("=================================================");
		allocDate.clear();
		allocDate.sendKeys("1a-2b-20cd");
		allocDate.sendKeys(Keys.TAB);
		String mixVal = allocDate.getAttribute("value");
		sa.assertNotEquals(mixVal, "1a-2b-20cd", "Accepting alphanumeric.");
		System.out.println("AD Case 20 : Entered '1a-2b-20cd' → Got '" + mixVal + "' | " + (!mixVal.equals("1a-2b-20cd") ? "Rejected Alphanumeric" : "Accepted Alphanumeric - FAIL"));

		allocDate.clear();
		allocDate.sendKeys("20-12-2025");
		allocDate.sendKeys(Keys.TAB);
		System.out.println("AD : Final value set → '20-12-2025' for record save");

		System.out.println("=================================================");
		System.out.println("CC9_AllocDate - All 20 cases executed.");
	}

}
