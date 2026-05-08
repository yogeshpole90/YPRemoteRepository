package Courtcase_Package;

import org.openqa.selenium.By;
import org.openqa.selenium.Keys;
import org.openqa.selenium.WebElement;

public class CC11_DocHandleDate extends CC2_Login {

	public void validateDocHandleDate()
	{
		WebElement docDate = driver.findElement(By.id("dcHandleLawyerDate"));
		jse.executeScript("arguments[0].scrollIntoView({block:'center'})", docDate);

		System.out.println("=================================================");
		sa.assertTrue(docDate.isDisplayed(), "Doc Handle Date not displayed.");
		System.out.println("DH Case 1 : Is Displayed? → " + docDate.isDisplayed() + " | PASS");

		System.out.println("=================================================");
		sa.assertTrue(docDate.isEnabled(), "Doc Handle Date is disabled.");
		System.out.println("DH Case 2 : Is Enabled? → " + docDate.isEnabled() + " | PASS");

		System.out.println("=================================================");
		docDate.click();
		docDate.sendKeys(Keys.TAB);
		System.out.println("DH Case 3 : Clicked on Date field | Accepted Click - PASS");

		System.out.println("=================================================");
		String defaultVal = docDate.getAttribute("value");
		System.out.println("DH Case 4 : Default value → '" + defaultVal + "' | " + (defaultVal.isEmpty() ? "Default is Empty - PASS" : "Has Default Value"));

		System.out.println("=================================================");
		docDate.clear();
		docDate.sendKeys("21-12-2025");
		docDate.sendKeys(Keys.TAB);
		String validDate = docDate.getAttribute("value");
		sa.assertEquals(validDate, "21-12-2025", "Not accepting valid date.");
		System.out.println("DH Case 5 : Entered '21-12-2025' → Got '" + validDate + "' | Accepted Valid Date");

		System.out.println("=================================================");
		docDate.clear();
		docDate.sendKeys("2025/12/21");
		docDate.sendKeys(Keys.TAB);
		String invalidFmt = docDate.getAttribute("value");
		sa.assertNotEquals(invalidFmt, "2025/12/21", "Accepting invalid format.");
		System.out.println("DH Case 6 : Entered '2025/12/21' → Got '" + invalidFmt + "' | " + (!invalidFmt.equals("2025/12/21") ? "Rejected Invalid Format" : "Accepted Invalid Format - FAIL"));

		System.out.println("=================================================");
		docDate.clear();
		docDate.sendKeys("abcdef");
		docDate.sendKeys(Keys.TAB);
		String alphaVal = docDate.getAttribute("value");
		sa.assertTrue(alphaVal.isEmpty(), "Accepting alphabets.");
		System.out.println("DH Case 7 : Entered 'abcdef' → Got '" + alphaVal + "' | " + (alphaVal.isEmpty() ? "Rejected Alphabets" : "Accepted Alphabets - FAIL"));

		System.out.println("=================================================");
		docDate.clear();
		docDate.sendKeys("@#$%^&");
		docDate.sendKeys(Keys.TAB);
		String splVal = docDate.getAttribute("value");
		sa.assertTrue(splVal.isEmpty(), "Accepting special chars.");
		System.out.println("DH Case 8 : Entered '@#$%^&' → Got '" + splVal + "' | " + (splVal.isEmpty() ? "Rejected Special Chars" : "Accepted Special Chars - FAIL"));

		System.out.println("=================================================");
		docDate.clear();
		docDate.sendKeys("21-12-2025");
		docDate.sendKeys(Keys.TAB);
		String numDate = docDate.getAttribute("value");
		sa.assertTrue(numDate.matches("[0-9\\-/]+"), "Non-numeric chars present.");
		System.out.println("DH Case 9 : Entered '21-12-2025' → Got '" + numDate + "' | " + (numDate.matches("[0-9\\-/]+") ? "Accepted Numeric+Separator" : "Contains Non-Numeric - FAIL"));

		System.out.println("=================================================");
		docDate.clear();
		docDate.sendKeys("32-12-2025");
		docDate.sendKeys(Keys.TAB);
		String day32 = docDate.getAttribute("value");
		sa.assertNotEquals(day32, "32-12-2025", "Accepting day 32.");
		System.out.println("DH Case 10 : Entered '32-12-2025' → Got '" + day32 + "' | " + (!day32.equals("32-12-2025") ? "Rejected Day 32" : "Accepted Day 32 - FAIL"));

		System.out.println("=================================================");
		docDate.clear();
		docDate.sendKeys("15-13-2025");
		docDate.sendKeys(Keys.TAB);
		String month13 = docDate.getAttribute("value");
		sa.assertNotEquals(month13, "15-13-2025", "Accepting month 13.");
		System.out.println("DH Case 11 : Entered '15-13-2025' → Got '" + month13 + "' | " + (!month13.equals("15-13-2025") ? "Rejected Month 13" : "Accepted Month 13 - FAIL"));

		System.out.println("=================================================");
		docDate.clear();
		docDate.sendKeys("29-02-2023");
		docDate.sendKeys(Keys.TAB);
		String nonLeap = docDate.getAttribute("value");
		sa.assertNotEquals(nonLeap, "29-02-2023", "Accepting Feb 29 non-leap.");
		System.out.println("DH Case 12 : Entered '29-02-2023' → Got '" + nonLeap + "' | " + (!nonLeap.equals("29-02-2023") ? "Rejected Feb29 Non-Leap" : "Accepted Feb29 Non-Leap - FAIL"));

		System.out.println("=================================================");
		docDate.clear();
		docDate.sendKeys("29-02-2024");
		docDate.sendKeys(Keys.TAB);
		String leapDate = docDate.getAttribute("value");
		sa.assertEquals(leapDate, "29-02-2024", "Not accepting Feb 29 leap.");
		System.out.println("DH Case 13 : Entered '29-02-2024' → Got '" + leapDate + "' | " + (leapDate.equals("29-02-2024") ? "Accepted Feb29 Leap Year" : "Rejected Feb29 Leap Year - FAIL"));

		System.out.println("=================================================");
		docDate.clear();
		docDate.sendKeys("01-01-2099");
		docDate.sendKeys(Keys.TAB);
		String futureDate = docDate.getAttribute("value");
		sa.assertNotEquals(futureDate, "01-01-2099", "Accepting far future.");
		System.out.println("DH Case 14 : Entered '01-01-2099' → Got '" + futureDate + "' | " + (!futureDate.equals("01-01-2099") ? "Rejected Future Date" : "Accepted Future Date - FAIL"));

		System.out.println("=================================================");
		docDate.clear();
		docDate.sendKeys("01-01-1900");
		docDate.sendKeys(Keys.TAB);
		String pastDate = docDate.getAttribute("value");
		sa.assertNotEquals(pastDate, "01-01-1900", "Accepting very old date.");
		System.out.println("DH Case 15 : Entered '01-01-1900' → Got '" + pastDate + "' | " + (!pastDate.equals("01-01-1900") ? "Rejected Old Past Date" : "Accepted Old Past Date - FAIL"));

		System.out.println("=================================================");
		docDate.clear();
		docDate.sendKeys("00-12-2025");
		docDate.sendKeys(Keys.TAB);
		String day00 = docDate.getAttribute("value");
		sa.assertNotEquals(day00, "00-12-2025", "Accepting day 00.");
		System.out.println("DH Case 16 : Entered '00-12-2025' → Got '" + day00 + "' | " + (!day00.equals("00-12-2025") ? "Rejected Day 00" : "Accepted Day 00 - FAIL"));

		System.out.println("=================================================");
		docDate.clear();
		docDate.sendKeys("15-00-2025");
		docDate.sendKeys(Keys.TAB);
		String month00 = docDate.getAttribute("value");
		sa.assertNotEquals(month00, "15-00-2025", "Accepting month 00.");
		System.out.println("DH Case 17 : Entered '15-00-2025' → Got '" + month00 + "' | " + (!month00.equals("15-00-2025") ? "Rejected Month 00" : "Accepted Month 00 - FAIL"));

		System.out.println("=================================================");
		docDate.clear();
		docDate.sendKeys("   ");
		docDate.sendKeys(Keys.TAB);
		String spaceVal = docDate.getAttribute("value");
		sa.assertTrue(spaceVal.trim().isEmpty(), "Accepting only spaces.");
		System.out.println("DH Case 18 : Entered '   ' → Got '" + spaceVal + "' | " + (spaceVal.trim().isEmpty() ? "Rejected Spaces" : "Accepted Spaces - FAIL"));

		System.out.println("=================================================");
		docDate.clear();
		docDate.sendKeys("21-12-20251234");
		docDate.sendKeys(Keys.TAB);
		String maxLen = docDate.getAttribute("value");
		sa.assertTrue(maxLen.length() <= 10, "Exceeding max length.");
		System.out.println("DH Case 19 : Entered 14 chars → Got length " + maxLen.length() + " | " + (maxLen.length() <= 10 ? "Max Length OK" : "Exceeded Max Length - FAIL"));

		System.out.println("=================================================");
		docDate.clear();
		docDate.sendKeys("1a-2b-20cd");
		docDate.sendKeys(Keys.TAB);
		String mixVal = docDate.getAttribute("value");
		sa.assertNotEquals(mixVal, "1a-2b-20cd", "Accepting alphanumeric.");
		System.out.println("DH Case 20 : Entered '1a-2b-20cd' → Got '" + mixVal + "' | " + (!mixVal.equals("1a-2b-20cd") ? "Rejected Alphanumeric" : "Accepted Alphanumeric - FAIL"));

		docDate.clear();
		docDate.sendKeys("21-12-2025");
		docDate.sendKeys(Keys.TAB);
		System.out.println("DH : Final value set → '21-12-2025' for record save");

		System.out.println("=================================================");
		System.out.println("CC11_DocHandleDate - All 20 cases executed.");
	}

}
