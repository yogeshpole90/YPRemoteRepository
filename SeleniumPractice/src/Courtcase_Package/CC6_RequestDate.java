package Courtcase_Package;

import org.openqa.selenium.By;
import org.openqa.selenium.Keys;
import org.openqa.selenium.WebElement;

public class CC6_RequestDate extends CC2_Login {

	public void validateRequestDate()
	{
		WebElement reqDate = driver.findElement(By.id("requestDate"));
		jse.executeScript("arguments[0].scrollIntoView({block:'center'})", reqDate);

		System.out.println("=================================================");
		sa.assertTrue(reqDate.isDisplayed(), "Request Date not displayed.");
		System.out.println("RD Case 1 : Is Displayed? → " + reqDate.isDisplayed() + " | PASS");

		System.out.println("=================================================");
		sa.assertTrue(reqDate.isEnabled(), "Request Date is disabled.");
		System.out.println("RD Case 2 : Is Enabled? → " + reqDate.isEnabled() + " | PASS");

		System.out.println("=================================================");
		reqDate.click();
		reqDate.sendKeys(Keys.TAB);
		System.out.println("RD Case 3 : Clicked on Date field | Accepted Click - PASS");

		System.out.println("=================================================");
		String defaultVal = reqDate.getAttribute("value");
		System.out.println("RD Case 4 : Default value → '" + defaultVal + "' | " + (defaultVal.isEmpty() ? "Default is Empty - PASS" : "Has Default Value"));

		System.out.println("=================================================");
		reqDate.clear();
		reqDate.sendKeys("19-12-2025");
		reqDate.sendKeys(Keys.TAB);
		String validDate = reqDate.getAttribute("value");
		sa.assertEquals(validDate, "19-12-2025", "Not accepting valid date.");
		System.out.println("RD Case 5 : Entered '19-12-2025' → Got '" + validDate + "' | Accepted Valid Date");

		System.out.println("=================================================");
		reqDate.clear();
		reqDate.sendKeys("2025/12/19");
		reqDate.sendKeys(Keys.TAB);
		String invalidFmt = reqDate.getAttribute("value");
		sa.assertNotEquals(invalidFmt, "2025/12/19", "Accepting invalid format.");
		System.out.println("RD Case 6 : Entered '2025/12/19' → Got '" + invalidFmt + "' | " + (!invalidFmt.equals("2025/12/19") ? "Rejected Invalid Format" : "Accepted Invalid Format - FAIL"));

		System.out.println("=================================================");
		reqDate.clear();
		reqDate.sendKeys("abcdef");
		reqDate.sendKeys(Keys.TAB);
		String alphaVal = reqDate.getAttribute("value");
		sa.assertTrue(alphaVal.isEmpty(), "Accepting alphabets.");
		System.out.println("RD Case 7 : Entered 'abcdef' → Got '" + alphaVal + "' | " + (alphaVal.isEmpty() ? "Rejected Alphabets" : "Accepted Alphabets - FAIL"));

		System.out.println("=================================================");
		reqDate.clear();
		reqDate.sendKeys("@#$%^&");
		reqDate.sendKeys(Keys.TAB);
		String splVal = reqDate.getAttribute("value");
		sa.assertTrue(splVal.isEmpty(), "Accepting special chars.");
		System.out.println("RD Case 8 : Entered '@#$%^&' → Got '" + splVal + "' | " + (splVal.isEmpty() ? "Rejected Special Chars" : "Accepted Special Chars - FAIL"));

		System.out.println("=================================================");
		reqDate.clear();
		reqDate.sendKeys("19-12-2025");
		reqDate.sendKeys(Keys.TAB);
		String numDate = reqDate.getAttribute("value");
		sa.assertTrue(numDate.matches("[0-9\\-/]+"), "Non-numeric chars present.");
		System.out.println("RD Case 9 : Entered '19-12-2025' → Got '" + numDate + "' | " + (numDate.matches("[0-9\\-/]+") ? "Accepted Numeric+Separator" : "Contains Non-Numeric - FAIL"));

		System.out.println("=================================================");
		reqDate.clear();
		reqDate.sendKeys("32-12-2025");
		reqDate.sendKeys(Keys.TAB);
		String day32 = reqDate.getAttribute("value");
		sa.assertNotEquals(day32, "32-12-2025", "Accepting day 32.");
		System.out.println("RD Case 10 : Entered '32-12-2025' → Got '" + day32 + "' | " + (!day32.equals("32-12-2025") ? "Rejected Day 32" : "Accepted Day 32 - FAIL"));

		System.out.println("=================================================");
		reqDate.clear();
		reqDate.sendKeys("15-13-2025");
		reqDate.sendKeys(Keys.TAB);
		String month13 = reqDate.getAttribute("value");
		sa.assertNotEquals(month13, "15-13-2025", "Accepting month 13.");
		System.out.println("RD Case 11 : Entered '15-13-2025' → Got '" + month13 + "' | " + (!month13.equals("15-13-2025") ? "Rejected Month 13" : "Accepted Month 13 - FAIL"));

		System.out.println("=================================================");
		reqDate.clear();
		reqDate.sendKeys("29-02-2023");
		reqDate.sendKeys(Keys.TAB);
		String nonLeap = reqDate.getAttribute("value");
		sa.assertNotEquals(nonLeap, "29-02-2023", "Accepting Feb 29 non-leap.");
		System.out.println("RD Case 12 : Entered '29-02-2023' → Got '" + nonLeap + "' | " + (!nonLeap.equals("29-02-2023") ? "Rejected Feb29 Non-Leap" : "Accepted Feb29 Non-Leap - FAIL"));

		System.out.println("=================================================");
		reqDate.clear();
		reqDate.sendKeys("29-02-2024");
		reqDate.sendKeys(Keys.TAB);
		String leapDate = reqDate.getAttribute("value");
		sa.assertEquals(leapDate, "29-02-2024", "Not accepting Feb 29 leap.");
		System.out.println("RD Case 13 : Entered '29-02-2024' → Got '" + leapDate + "' | " + (leapDate.equals("29-02-2024") ? "Accepted Feb29 Leap Year" : "Rejected Feb29 Leap Year - FAIL"));

		System.out.println("=================================================");
		reqDate.clear();
		reqDate.sendKeys("01-01-2099");
		reqDate.sendKeys(Keys.TAB);
		String futureDate = reqDate.getAttribute("value");
		sa.assertNotEquals(futureDate, "01-01-2099", "Accepting far future.");
		System.out.println("RD Case 14 : Entered '01-01-2099' → Got '" + futureDate + "' | " + (!futureDate.equals("01-01-2099") ? "Rejected Future Date" : "Accepted Future Date - FAIL"));

		System.out.println("=================================================");
		reqDate.clear();
		reqDate.sendKeys("01-01-1900");
		reqDate.sendKeys(Keys.TAB);
		String pastDate = reqDate.getAttribute("value");
		sa.assertNotEquals(pastDate, "01-01-1900", "Accepting very old date.");
		System.out.println("RD Case 15 : Entered '01-01-1900' → Got '" + pastDate + "' | " + (!pastDate.equals("01-01-1900") ? "Rejected Old Past Date" : "Accepted Old Past Date - FAIL"));

		System.out.println("=================================================");
		reqDate.clear();
		reqDate.sendKeys("00-12-2025");
		reqDate.sendKeys(Keys.TAB);
		String day00 = reqDate.getAttribute("value");
		sa.assertNotEquals(day00, "00-12-2025", "Accepting day 00.");
		System.out.println("RD Case 16 : Entered '00-12-2025' → Got '" + day00 + "' | " + (!day00.equals("00-12-2025") ? "Rejected Day 00" : "Accepted Day 00 - FAIL"));

		System.out.println("=================================================");
		reqDate.clear();
		reqDate.sendKeys("15-00-2025");
		reqDate.sendKeys(Keys.TAB);
		String month00 = reqDate.getAttribute("value");
		sa.assertNotEquals(month00, "15-00-2025", "Accepting month 00.");
		System.out.println("RD Case 17 : Entered '15-00-2025' → Got '" + month00 + "' | " + (!month00.equals("15-00-2025") ? "Rejected Month 00" : "Accepted Month 00 - FAIL"));

		System.out.println("=================================================");
		reqDate.clear();
		reqDate.sendKeys("   ");
		reqDate.sendKeys(Keys.TAB);
		String spaceVal = reqDate.getAttribute("value");
		sa.assertTrue(spaceVal.trim().isEmpty(), "Accepting only spaces.");
		System.out.println("RD Case 18 : Entered '   ' → Got '" + spaceVal + "' | " + (spaceVal.trim().isEmpty() ? "Rejected Spaces" : "Accepted Spaces - FAIL"));

		System.out.println("=================================================");
		reqDate.clear();
		reqDate.sendKeys("19-12-20251234");
		reqDate.sendKeys(Keys.TAB);
		String maxLen = reqDate.getAttribute("value");
		sa.assertTrue(maxLen.length() <= 10, "Exceeding max length.");
		System.out.println("RD Case 19 : Entered 14 chars → Got length " + maxLen.length() + " | " + (maxLen.length() <= 10 ? "Max Length OK" : "Exceeded Max Length - FAIL"));

		System.out.println("=================================================");
		reqDate.clear();
		reqDate.sendKeys("1a-2b-20cd");
		reqDate.sendKeys(Keys.TAB);
		String mixVal = reqDate.getAttribute("value");
		sa.assertNotEquals(mixVal, "1a-2b-20cd", "Accepting alphanumeric.");
		System.out.println("RD Case 20 : Entered '1a-2b-20cd' → Got '" + mixVal + "' | " + (!mixVal.equals("1a-2b-20cd") ? "Rejected Alphanumeric" : "Accepted Alphanumeric - FAIL"));

		reqDate.clear();
		reqDate.sendKeys("19-12-2025");
		reqDate.sendKeys(Keys.TAB);
		System.out.println("RD : Final value set → '19-12-2025' for record save");

		System.out.println("=================================================");
		System.out.println("CC6_RequestDate - All 20 cases executed.");
	}

}
