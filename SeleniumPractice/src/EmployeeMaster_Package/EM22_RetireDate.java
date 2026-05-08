package EmployeeMaster_Package;

import org.openqa.selenium.By;
import org.openqa.selenium.Keys;
import org.openqa.selenium.WebElement;

public class EM22_RetireDate extends EM2_Login {

	public void validateRetireDate() throws InterruptedException
	{
		// App Date=13-12-2021, RetireDate should be future from join date
		WebElement dt = driver.findElement(By.id("retireDate"));

		System.out.println("=================================================");
		sa.assertTrue(dt.isDisplayed(), "RetireDate field is NOT visible on page.");
		System.out.println("RD Case 1 : Is Displayed? → " + dt.isDisplayed() + " | " + (dt.isDisplayed() ? "PASS - Field visible" : "FAIL - Field NOT visible"));

		System.out.println("=================================================");
		sa.assertTrue(dt.isEnabled(), "RetireDate field is DISABLED, cannot interact.");
		System.out.println("RD Case 2 : Is Enabled? → " + dt.isEnabled() + " | " + (dt.isEnabled() ? "PASS - Field enabled" : "FAIL - Field disabled"));

		System.out.println("=================================================");
		dt.click();
		System.out.println("RD Case 3 : Clicked on field | PASS - Field is clickable");

		System.out.println("=================================================");
		String def = dt.getAttribute("value");
		System.out.println("RD Case 4 : Default value → '" + def + "' | " + (def.isEmpty() ? "PASS - Default is empty" : "INFO - Default has value: " + def));

		System.out.println("=================================================");
		dt.clear(); dt.sendKeys("31-12-2050");
		String validDate = dt.getAttribute("value");
		sa.assertEquals(validDate, "31-12-2050", "Valid retire date 31-12-2050 NOT accepted.");
		System.out.println("RD Case 5 : Entered '31-12-2050' → Got '" + validDate + "' | " + (validDate.equals("31-12-2050") ? "PASS - Valid future date accepted" : "FAIL - Valid date rejected"));

		System.out.println("=================================================");
		dt.clear(); dt.sendKeys("2050/12/31");
		String invalidFmt = dt.getAttribute("value");
		sa.assertNotEquals(invalidFmt, "2050/12/31", "Invalid format YYYY/MM/DD accepted. Only DD-MM-YYYY allowed.");
		System.out.println("RD Case 6 : Entered '2050/12/31' → Got '" + invalidFmt + "' | " + (!invalidFmt.equals("2050/12/31") ? "PASS - Invalid format rejected" : "FAIL - Invalid format accepted"));

		System.out.println("=================================================");
		dt.clear(); dt.sendKeys("abcdef");
		String alphaVal = dt.getAttribute("value");
		sa.assertTrue(alphaVal.isEmpty(), "Alphabets 'abcdef' accepted in RetireDate. Only numeric+separator allowed.");
		System.out.println("RD Case 7 : Entered 'abcdef' → Got '" + alphaVal + "' | " + (alphaVal.isEmpty() ? "PASS - Alphabets rejected" : "FAIL - Alphabets accepted"));

		System.out.println("=================================================");
		dt.clear(); dt.sendKeys("@#$%^&");
		String splVal = dt.getAttribute("value");
		sa.assertTrue(splVal.isEmpty(), "Special chars '@#$%^&' accepted in RetireDate. Only numeric+separator allowed.");
		System.out.println("RD Case 8 : Entered '@#$%^&' → Got '" + splVal + "' | " + (splVal.isEmpty() ? "PASS - Special chars rejected" : "FAIL - Special chars accepted"));

		System.out.println("=================================================");
		dt.clear(); dt.sendKeys("31-12-2050");
		sa.assertTrue(dt.getAttribute("value").matches("[0-9\\-/]+"), "RetireDate contains non-numeric chars.");
		System.out.println("RD Case 9 : Numeric+Separator check → " + (dt.getAttribute("value").matches("[0-9\\-/]+") ? "PASS" : "FAIL - Non-numeric chars found"));

		System.out.println("=================================================");
		dt.clear(); dt.sendKeys("32-12-2050");
		String day32 = dt.getAttribute("value");
		sa.assertNotEquals(day32, "32-12-2050", "Day 32 accepted. Max day should be 31.");
		System.out.println("RD Case 10 : Entered '32-12-2050' → Got '" + day32 + "' | " + (!day32.equals("32-12-2050") ? "PASS - Day 32 rejected" : "FAIL - Day 32 accepted"));

		System.out.println("=================================================");
		dt.clear(); dt.sendKeys("15-13-2050");
		String month13 = dt.getAttribute("value");
		sa.assertNotEquals(month13, "15-13-2050", "Month 13 accepted. Max month should be 12.");
		System.out.println("RD Case 11 : Entered '15-13-2050' → Got '" + month13 + "' | " + (!month13.equals("15-13-2050") ? "PASS - Month 13 rejected" : "FAIL - Month 13 accepted"));

		System.out.println("=================================================");
		dt.clear(); dt.sendKeys("29-02-2023");
		String nonLeap = dt.getAttribute("value");
		sa.assertNotEquals(nonLeap, "29-02-2023", "Feb 29 accepted for non-leap year 2023.");
		System.out.println("RD Case 12 : Entered '29-02-2023' → Got '" + nonLeap + "' | " + (!nonLeap.equals("29-02-2023") ? "PASS - Feb29 non-leap rejected" : "FAIL - Feb29 accepted for non-leap 2023"));

		System.out.println("=================================================");
		dt.clear(); dt.sendKeys("29-02-2048");
		String leapDate = dt.getAttribute("value");
		sa.assertEquals(leapDate, "29-02-2048", "Feb 29 rejected for leap year 2048. 2048 IS a leap year.");
		System.out.println("RD Case 13 : Entered '29-02-2048' → Got '" + leapDate + "' | " + (leapDate.equals("29-02-2048") ? "PASS - Feb29 leap accepted" : "FAIL - Feb29 rejected for leap 2048"));

		System.out.println("=================================================");
		dt.clear(); dt.sendKeys("01-01-2019");
		String pastDate = dt.getAttribute("value");
		dt.sendKeys(Keys.TAB); Thread.sleep(500);
		String pastToast = getToastMsg();
		sa.assertNotEquals(pastDate, "01-01-2019", "Past retire date 2019 accepted. RetireDate should be after join date 13-12-2020.");
		System.out.println("RD Case 14 : Entered '01-01-2019' → Got '" + pastDate + "' | Toast: '" + pastToast + "' | " + (!pastDate.equals("01-01-2019") || !pastToast.isEmpty() ? "PASS - Past retire date rejected" : "FAIL - Past date accepted"));

		System.out.println("=================================================");
		dt.clear(); dt.sendKeys("01-01-1900");
		String oldDate = dt.getAttribute("value");
		dt.sendKeys(Keys.TAB); Thread.sleep(500);
		String oldToast = getToastMsg();
		sa.assertNotEquals(oldDate, "01-01-1900", "Very old date 1900 accepted as RetireDate.");
		System.out.println("RD Case 15 : Entered '01-01-1900' → Got '" + oldDate + "' | Toast: '" + oldToast + "' | " + (!oldDate.equals("01-01-1900") || !oldToast.isEmpty() ? "PASS - Very old date rejected" : "FAIL - Year 1900 accepted"));

		System.out.println("=================================================");
		dt.clear(); dt.sendKeys("00-12-2050");
		String day00 = dt.getAttribute("value");
		sa.assertNotEquals(day00, "00-12-2050", "Day 00 accepted. Day should be between 01-31.");
		System.out.println("RD Case 16 : Entered '00-12-2050' → Got '" + day00 + "' | " + (!day00.equals("00-12-2050") ? "PASS - Day 00 rejected" : "FAIL - Day 00 accepted"));

		System.out.println("=================================================");
		dt.clear(); dt.sendKeys("15-00-2050");
		String month00 = dt.getAttribute("value");
		sa.assertNotEquals(month00, "15-00-2050", "Month 00 accepted. Month should be between 01-12.");
		System.out.println("RD Case 17 : Entered '15-00-2050' → Got '" + month00 + "' | " + (!month00.equals("15-00-2050") ? "PASS - Month 00 rejected" : "FAIL - Month 00 accepted"));

		System.out.println("=================================================");
		dt.clear(); dt.sendKeys("   ");
		String spaceVal = dt.getAttribute("value");
		sa.assertTrue(spaceVal.trim().isEmpty(), "Only spaces accepted as RetireDate.");
		System.out.println("RD Case 18 : Entered '   ' → Got '" + spaceVal + "' | " + (spaceVal.trim().isEmpty() ? "PASS - Spaces rejected" : "FAIL - Spaces accepted"));

		System.out.println("=================================================");
		dt.clear(); dt.sendKeys("31-12-20501234");
		String maxLen = dt.getAttribute("value");
		sa.assertTrue(maxLen.length() <= 10, "Exceeded max length 10 for RetireDate.");
		System.out.println("RD Case 19 : Entered 14 chars → Got length " + maxLen.length() + " | " + (maxLen.length() <= 10 ? "PASS - Max length OK" : "FAIL - Length " + maxLen.length() + " exceeds 10"));

		System.out.println("=================================================");
		dt.clear(); dt.sendKeys("1a-2b-20cd");
		String mixVal = dt.getAttribute("value");
		sa.assertNotEquals(mixVal, "1a-2b-20cd", "Alphanumeric mix accepted in RetireDate.");
		System.out.println("RD Case 20 : Entered '1a-2b-20cd' → Got '" + mixVal + "' | " + (!mixVal.equals("1a-2b-20cd") ? "PASS - Alphanumeric rejected" : "FAIL - Alphanumeric accepted"));

		// Final: Set valid retire date (future from join date)
		dt.clear(); dt.sendKeys("31-12-2050"); dt.sendKeys(Keys.TAB);
		System.out.println("RD : Final value set → '31-12-2050' (future from join date 13-12-2020)");
		System.out.println("=================================================");
		System.out.println("EM22_RetireDate - All 20 cases executed.");
	}
}
