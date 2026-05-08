package EmployeeMaster_Package;

import org.openqa.selenium.By;
import org.openqa.selenium.Keys;
import org.openqa.selenium.WebElement;

public class EM14_IssueDate extends EM2_Login {

	public void validateIssueDate() throws InterruptedException
	{
		// App Date=13-12-2021, IssueDate should be past date before app date
		WebElement dt = driver.findElement(By.id("issueDate"));

		System.out.println("=================================================");
		sa.assertTrue(dt.isDisplayed(), "IssueDate field is NOT visible on page.");
		System.out.println("ISD Case 1 : Is Displayed? → " + dt.isDisplayed() + " | " + (dt.isDisplayed() ? "PASS - Field visible" : "FAIL - Field NOT visible"));

		System.out.println("=================================================");
		sa.assertTrue(dt.isEnabled(), "IssueDate field is DISABLED, cannot interact.");
		System.out.println("ISD Case 2 : Is Enabled? → " + dt.isEnabled() + " | " + (dt.isEnabled() ? "PASS - Field enabled" : "FAIL - Field disabled"));

		System.out.println("=================================================");
		dt.click();
		System.out.println("ISD Case 3 : Clicked on field | PASS - Field is clickable");

		System.out.println("=================================================");
		String def = dt.getAttribute("value");
		System.out.println("ISD Case 4 : Default value → '" + def + "' | " + (def.isEmpty() ? "PASS - Default is empty" : "INFO - Default has value: " + def));

		System.out.println("=================================================");
		dt.clear(); dt.sendKeys("01-06-2020");
		String validDate = dt.getAttribute("value");
		sa.assertEquals(validDate, "01-06-2020", "Valid issue date 01-06-2020 NOT accepted. Should accept past date before app date 13-12-2021.");
		System.out.println("ISD Case 5 : Entered '01-06-2020' → Got '" + validDate + "' | " + (validDate.equals("01-06-2020") ? "PASS - Valid date accepted" : "FAIL - Valid date rejected"));

		System.out.println("=================================================");
		dt.clear(); dt.sendKeys("2020/06/01");
		String invalidFmt = dt.getAttribute("value");
		sa.assertNotEquals(invalidFmt, "2020/06/01", "Invalid format YYYY/MM/DD accepted. Only DD-MM-YYYY allowed.");
		System.out.println("ISD Case 6 : Entered '2020/06/01' → Got '" + invalidFmt + "' | " + (!invalidFmt.equals("2020/06/01") ? "PASS - Invalid format rejected" : "FAIL - Invalid format accepted"));

		System.out.println("=================================================");
		dt.clear(); dt.sendKeys("abcdef");
		String alphaVal = dt.getAttribute("value");
		sa.assertTrue(alphaVal.isEmpty(), "Alphabets 'abcdef' accepted in IssueDate. Only numeric+separator allowed.");
		System.out.println("ISD Case 7 : Entered 'abcdef' → Got '" + alphaVal + "' | " + (alphaVal.isEmpty() ? "PASS - Alphabets rejected" : "FAIL - Alphabets accepted"));

		System.out.println("=================================================");
		dt.clear(); dt.sendKeys("@#$%^&");
		String splVal = dt.getAttribute("value");
		sa.assertTrue(splVal.isEmpty(), "Special chars '@#$%^&' accepted in IssueDate. Only numeric+separator allowed.");
		System.out.println("ISD Case 8 : Entered '@#$%^&' → Got '" + splVal + "' | " + (splVal.isEmpty() ? "PASS - Special chars rejected" : "FAIL - Special chars accepted"));

		System.out.println("=================================================");
		dt.clear(); dt.sendKeys("01-06-2020");
		sa.assertTrue(dt.getAttribute("value").matches("[0-9\\-/]+"), "IssueDate contains non-numeric chars.");
		System.out.println("ISD Case 9 : Numeric+Separator check → " + (dt.getAttribute("value").matches("[0-9\\-/]+") ? "PASS" : "FAIL - Non-numeric chars found"));

		System.out.println("=================================================");
		dt.clear(); dt.sendKeys("32-06-2020");
		String day32 = dt.getAttribute("value");
		sa.assertNotEquals(day32, "32-06-2020", "Day 32 accepted. Max day should be 31.");
		System.out.println("ISD Case 10 : Entered '32-06-2020' → Got '" + day32 + "' | " + (!day32.equals("32-06-2020") ? "PASS - Day 32 rejected" : "FAIL - Day 32 accepted"));

		System.out.println("=================================================");
		dt.clear(); dt.sendKeys("01-13-2020");
		String month13 = dt.getAttribute("value");
		sa.assertNotEquals(month13, "01-13-2020", "Month 13 accepted. Max month should be 12.");
		System.out.println("ISD Case 11 : Entered '01-13-2020' → Got '" + month13 + "' | " + (!month13.equals("01-13-2020") ? "PASS - Month 13 rejected" : "FAIL - Month 13 accepted"));

		System.out.println("=================================================");
		dt.clear(); dt.sendKeys("29-02-2019");
		String nonLeap = dt.getAttribute("value");
		sa.assertNotEquals(nonLeap, "29-02-2019", "Feb 29 accepted for non-leap year 2019.");
		System.out.println("ISD Case 12 : Entered '29-02-2019' → Got '" + nonLeap + "' | " + (!nonLeap.equals("29-02-2019") ? "PASS - Feb29 non-leap rejected" : "FAIL - Feb29 accepted for non-leap 2019"));

		System.out.println("=================================================");
		dt.clear(); dt.sendKeys("29-02-2020");
		String leapDate = dt.getAttribute("value");
		sa.assertEquals(leapDate, "29-02-2020", "Feb 29 rejected for leap year 2020. 2020 IS a leap year.");
		System.out.println("ISD Case 13 : Entered '29-02-2020' → Got '" + leapDate + "' | " + (leapDate.equals("29-02-2020") ? "PASS - Feb29 leap accepted" : "FAIL - Feb29 rejected for leap 2020"));

		System.out.println("=================================================");
		dt.clear(); dt.sendKeys("01-01-2099");
		String futureDate = dt.getAttribute("value");
		dt.sendKeys(Keys.TAB); Thread.sleep(500);
		String futureToast = getToastMsg();
		sa.assertNotEquals(futureDate, "01-01-2099", "Future date 2099 accepted. IssueDate should be before app date 13-12-2021.");
		System.out.println("ISD Case 14 : Entered '01-01-2099' → Got '" + futureDate + "' | Toast: '" + futureToast + "' | " + (!futureDate.equals("01-01-2099") || !futureToast.isEmpty() ? "PASS - Future date rejected" : "FAIL - Future date accepted"));

		System.out.println("=================================================");
		dt.clear(); dt.sendKeys("01-01-1900");
		String pastDate = dt.getAttribute("value");
		dt.sendKeys(Keys.TAB); Thread.sleep(500);
		String pastToast = getToastMsg();
		sa.assertNotEquals(pastDate, "01-01-1900", "Very old date 1900 accepted. Should have minimum year limit.");
		System.out.println("ISD Case 15 : Entered '01-01-1900' → Got '" + pastDate + "' | Toast: '" + pastToast + "' | " + (!pastDate.equals("01-01-1900") || !pastToast.isEmpty() ? "PASS - Very old date rejected" : "FAIL - Year 1900 accepted"));

		System.out.println("=================================================");
		dt.clear(); dt.sendKeys("00-06-2020");
		String day00 = dt.getAttribute("value");
		sa.assertNotEquals(day00, "00-06-2020", "Day 00 accepted. Day should be between 01-31.");
		System.out.println("ISD Case 16 : Entered '00-06-2020' → Got '" + day00 + "' | " + (!day00.equals("00-06-2020") ? "PASS - Day 00 rejected" : "FAIL - Day 00 accepted"));

		System.out.println("=================================================");
		dt.clear(); dt.sendKeys("01-00-2020");
		String month00 = dt.getAttribute("value");
		sa.assertNotEquals(month00, "01-00-2020", "Month 00 accepted. Month should be between 01-12.");
		System.out.println("ISD Case 17 : Entered '01-00-2020' → Got '" + month00 + "' | " + (!month00.equals("01-00-2020") ? "PASS - Month 00 rejected" : "FAIL - Month 00 accepted"));

		System.out.println("=================================================");
		dt.clear(); dt.sendKeys("   ");
		String spaceVal = dt.getAttribute("value");
		sa.assertTrue(spaceVal.trim().isEmpty(), "Only spaces accepted as IssueDate.");
		System.out.println("ISD Case 18 : Entered '   ' → Got '" + spaceVal + "' | " + (spaceVal.trim().isEmpty() ? "PASS - Spaces rejected" : "FAIL - Spaces accepted"));

		System.out.println("=================================================");
		dt.clear(); dt.sendKeys("01-06-20201234");
		String maxLen = dt.getAttribute("value");
		sa.assertTrue(maxLen.length() <= 10, "Exceeded max length 10 for IssueDate.");
		System.out.println("ISD Case 19 : Entered 14 chars → Got length " + maxLen.length() + " | " + (maxLen.length() <= 10 ? "PASS - Max length OK" : "FAIL - Length " + maxLen.length() + " exceeds 10"));

		System.out.println("=================================================");
		dt.clear(); dt.sendKeys("1a-2b-20cd");
		String mixVal = dt.getAttribute("value");
		sa.assertNotEquals(mixVal, "1a-2b-20cd", "Alphanumeric mix accepted in IssueDate.");
		System.out.println("ISD Case 20 : Entered '1a-2b-20cd' → Got '" + mixVal + "' | " + (!mixVal.equals("1a-2b-20cd") ? "PASS - Alphanumeric rejected" : "FAIL - Alphanumeric accepted"));

		// Final: Set valid issue date
		dt.clear(); dt.sendKeys("01-06-2020"); dt.sendKeys(Keys.TAB);
		System.out.println("ISD : Final value set → '01-06-2020' (before app date 13-12-2021)");
		System.out.println("=================================================");
		System.out.println("EM14_IssueDate - All 20 cases executed.");
	}
}
