package EmployeeMaster_Package;

import org.openqa.selenium.By;
import org.openqa.selenium.Keys;
import org.openqa.selenium.WebElement;

public class EM8_BirthDate extends EM2_Login {

	public void validateBirthDate() throws InterruptedException
	{
		// App Date=13-12-2021, JoinDate=13-12-2020, DOB must be 18+ yrs before join → valid DOB=13-12-2002 or earlier
		WebElement dob = driver.findElement(By.id("birthDate"));

		System.out.println("=================================================");
		sa.assertTrue(dob.isDisplayed(), "DOB field is NOT visible on page.");
		System.out.println("DOB Case 1 : Is Displayed? → " + dob.isDisplayed() + " | " + (dob.isDisplayed() ? "PASS - Field visible" : "FAIL - Field NOT visible"));

		System.out.println("=================================================");
		sa.assertTrue(dob.isEnabled(), "DOB field is DISABLED, cannot interact.");
		System.out.println("DOB Case 2 : Is Enabled? → " + dob.isEnabled() + " | " + (dob.isEnabled() ? "PASS - Field enabled" : "FAIL - Field disabled"));

		System.out.println("=================================================");
		dob.click();
		System.out.println("DOB Case 3 : Clicked on field | PASS - Field is clickable");

		System.out.println("=================================================");
		String defaultVal = dob.getAttribute("value");
		System.out.println("DOB Case 4 : Default value → '" + defaultVal + "' | " + (defaultVal.isEmpty() ? "PASS - Default is empty" : "INFO - Default has value: " + defaultVal));

		System.out.println("=================================================");
		dob.clear(); dob.sendKeys("10-05-1995");
		String validDate = dob.getAttribute("value");
		sa.assertEquals(validDate, "10-05-1995", "Valid DOB 10-05-1995 NOT accepted. Age=25 yrs from join date, should be valid (18+).");
		System.out.println("DOB Case 5 : Entered '10-05-1995' → Got '" + validDate + "' | " + (validDate.equals("10-05-1995") ? "PASS - Valid DOB accepted (age 25)" : "FAIL - Valid DOB rejected"));

		System.out.println("=================================================");
		dob.clear(); dob.sendKeys("1995/05/10");
		String invalidFmt = dob.getAttribute("value");
		sa.assertNotEquals(invalidFmt, "1995/05/10", "Invalid format YYYY/MM/DD accepted. Only DD-MM-YYYY allowed.");
		System.out.println("DOB Case 6 : Entered '1995/05/10' → Got '" + invalidFmt + "' | " + (!invalidFmt.equals("1995/05/10") ? "PASS - Invalid format rejected" : "FAIL - Invalid format YYYY/MM/DD accepted"));

		System.out.println("=================================================");
		dob.clear(); dob.sendKeys("abcdef");
		String alphaVal = dob.getAttribute("value");
		sa.assertTrue(alphaVal.isEmpty(), "Alphabets 'abcdef' accepted in DOB. Only numeric+separator allowed.");
		System.out.println("DOB Case 7 : Entered 'abcdef' → Got '" + alphaVal + "' | " + (alphaVal.isEmpty() ? "PASS - Alphabets rejected" : "FAIL - Alphabets accepted in DOB field"));

		System.out.println("=================================================");
		dob.clear(); dob.sendKeys("@#$%^&");
		String splVal = dob.getAttribute("value");
		sa.assertTrue(splVal.isEmpty(), "Special chars '@#$%^&' accepted in DOB. Only numeric+separator allowed.");
		System.out.println("DOB Case 8 : Entered '@#$%^&' → Got '" + splVal + "' | " + (splVal.isEmpty() ? "PASS - Special chars rejected" : "FAIL - Special chars accepted in DOB"));

		System.out.println("=================================================");
		dob.clear(); dob.sendKeys("10-05-1995");
		String numDate = dob.getAttribute("value");
		sa.assertTrue(numDate.matches("[0-9\\-/]+"), "DOB contains non-numeric chars. Expected only digits and separators.");
		System.out.println("DOB Case 9 : Entered '10-05-1995' → Got '" + numDate + "' | " + (numDate.matches("[0-9\\-/]+") ? "PASS - Only numeric+separator" : "FAIL - Contains non-numeric chars"));

		System.out.println("=================================================");
		dob.clear(); dob.sendKeys("32-05-1995");
		String day32 = dob.getAttribute("value");
		sa.assertNotEquals(day32, "32-05-1995", "Day 32 accepted in DOB. Max day should be 31.");
		System.out.println("DOB Case 10 : Entered '32-05-1995' → Got '" + day32 + "' | " + (!day32.equals("32-05-1995") ? "PASS - Day 32 rejected" : "FAIL - Day 32 accepted, max should be 31"));

		System.out.println("=================================================");
		dob.clear(); dob.sendKeys("10-13-1995");
		String month13 = dob.getAttribute("value");
		sa.assertNotEquals(month13, "10-13-1995", "Month 13 accepted in DOB. Max month should be 12.");
		System.out.println("DOB Case 11 : Entered '10-13-1995' → Got '" + month13 + "' | " + (!month13.equals("10-13-1995") ? "PASS - Month 13 rejected" : "FAIL - Month 13 accepted, max should be 12"));

		System.out.println("=================================================");
		dob.clear(); dob.sendKeys("29-02-1995");
		String nonLeap = dob.getAttribute("value");
		sa.assertNotEquals(nonLeap, "29-02-1995", "Feb 29 accepted for non-leap year 1995. 1995 is not a leap year.");
		System.out.println("DOB Case 12 : Entered '29-02-1995' → Got '" + nonLeap + "' | " + (!nonLeap.equals("29-02-1995") ? "PASS - Feb29 non-leap rejected" : "FAIL - Feb29 accepted for non-leap 1995"));

		System.out.println("=================================================");
		dob.clear(); dob.sendKeys("29-02-1996");
		String leapDate = dob.getAttribute("value");
		sa.assertEquals(leapDate, "29-02-1996", "Feb 29 rejected for leap year 1996. 1996 IS a leap year, should accept.");
		System.out.println("DOB Case 13 : Entered '29-02-1996' → Got '" + leapDate + "' | " + (leapDate.equals("29-02-1996") ? "PASS - Feb29 leap year accepted" : "FAIL - Feb29 rejected for leap year 1996"));

		System.out.println("=================================================");
		dob.clear(); dob.sendKeys("01-01-2010");
		String youngDOB = dob.getAttribute("value");
		dob.sendKeys(Keys.TAB); Thread.sleep(500);
		String youngToast = getToastMsg();
		sa.assertNotEquals(youngDOB, "01-01-2010", "DOB 2010 accepted. Age=10 from join date 2020, must be 18+.");
		System.out.println("DOB Case 14 : Entered '01-01-2010' → Got '" + youngDOB + "' | Toast: '" + youngToast + "' | " + (!youngDOB.equals("01-01-2010") || !youngToast.isEmpty() ? "PASS - Under 18 DOB rejected" : "FAIL - Under 18 DOB accepted"));

		System.out.println("=================================================");
		dob.clear(); dob.sendKeys("01-01-1900");
		String pastDate = dob.getAttribute("value");
		dob.sendKeys(Keys.TAB); Thread.sleep(500);
		String pastToast = getToastMsg();
		sa.assertNotEquals(pastDate, "01-01-1900", "Very old DOB 1900 accepted. Should have minimum year limit.");
		System.out.println("DOB Case 15 : Entered '01-01-1900' → Got '" + pastDate + "' | Toast: '" + pastToast + "' | " + (!pastDate.equals("01-01-1900") || !pastToast.isEmpty() ? "PASS - Very old DOB rejected" : "FAIL - Year 1900 accepted"));

		System.out.println("=================================================");
		dob.clear(); dob.sendKeys("00-05-1995");
		String day00 = dob.getAttribute("value");
		sa.assertNotEquals(day00, "00-05-1995", "Day 00 accepted in DOB. Day should be between 01-31.");
		System.out.println("DOB Case 16 : Entered '00-05-1995' → Got '" + day00 + "' | " + (!day00.equals("00-05-1995") ? "PASS - Day 00 rejected" : "FAIL - Day 00 accepted, min should be 01"));

		System.out.println("=================================================");
		dob.clear(); dob.sendKeys("10-00-1995");
		String month00 = dob.getAttribute("value");
		sa.assertNotEquals(month00, "10-00-1995", "Month 00 accepted in DOB. Month should be between 01-12.");
		System.out.println("DOB Case 17 : Entered '10-00-1995' → Got '" + month00 + "' | " + (!month00.equals("10-00-1995") ? "PASS - Month 00 rejected" : "FAIL - Month 00 accepted, min should be 01"));

		System.out.println("=================================================");
		dob.clear(); dob.sendKeys("   ");
		String spaceVal = dob.getAttribute("value");
		sa.assertTrue(spaceVal.trim().isEmpty(), "Only spaces accepted as DOB. Field should not accept blank spaces.");
		System.out.println("DOB Case 18 : Entered '   ' → Got '" + spaceVal + "' | " + (spaceVal.trim().isEmpty() ? "PASS - Spaces rejected/blank" : "FAIL - Spaces accepted as DOB value"));

		System.out.println("=================================================");
		dob.clear(); dob.sendKeys("10-05-19951234");
		String maxLen = dob.getAttribute("value");
		sa.assertTrue(maxLen.length() <= 10, "Exceeded max length 10. DOB DD-MM-YYYY should be max 10 chars.");
		System.out.println("DOB Case 19 : Entered 14 chars → Got length " + maxLen.length() + " | " + (maxLen.length() <= 10 ? "PASS - Max length 10 OK" : "FAIL - Length " + maxLen.length() + " exceeds max 10"));

		System.out.println("=================================================");
		dob.clear(); dob.sendKeys("1a-2b-19cd");
		String mixVal = dob.getAttribute("value");
		sa.assertNotEquals(mixVal, "1a-2b-19cd", "Alphanumeric mix '1a-2b-19cd' accepted. DOB should be pure numeric.");
		System.out.println("DOB Case 20 : Entered '1a-2b-19cd' → Got '" + mixVal + "' | " + (!mixVal.equals("1a-2b-19cd") ? "PASS - Alphanumeric mix rejected" : "FAIL - Alphanumeric mix accepted in DOB"));

		// Final: Set valid DOB (age 25 from join date 13-12-2020)
		dob.clear(); dob.sendKeys("10-05-1995"); dob.sendKeys(Keys.TAB);
		System.out.println("DOB : Final value set → '10-05-1995' (age 25 from join date, 18+ valid)");
		System.out.println("=================================================");
		System.out.println("EM8_BirthDate - All 20 cases executed.");
	}
}
