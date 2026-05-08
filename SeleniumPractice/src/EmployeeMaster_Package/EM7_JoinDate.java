package EmployeeMaster_Package;

import org.openqa.selenium.By;
import org.openqa.selenium.Keys;
import org.openqa.selenium.WebElement;

public class EM7_JoinDate extends EM2_Login {

	public void validateJoinDate() throws InterruptedException
	{
		// App Date = 13-12-2021, Valid JoinDate = 13-12-2020 (1 yr before)
		WebElement joinDate = driver.findElement(By.id("joinDate"));

		System.out.println("=================================================");
		sa.assertTrue(joinDate.isDisplayed(), "JoinDate field is NOT visible on page.");
		System.out.println("JD Case 1 : Is Displayed? → " + joinDate.isDisplayed() + " | " + (joinDate.isDisplayed() ? "PASS - Field visible" : "FAIL - Field NOT visible"));

		System.out.println("=================================================");
		sa.assertTrue(joinDate.isEnabled(), "JoinDate field is DISABLED, cannot interact.");
		System.out.println("JD Case 2 : Is Enabled? → " + joinDate.isEnabled() + " | " + (joinDate.isEnabled() ? "PASS - Field enabled" : "FAIL - Field disabled"));

		System.out.println("=================================================");
		joinDate.click();
		System.out.println("JD Case 3 : Clicked on field | PASS - Field is clickable");

		System.out.println("=================================================");
		String defaultVal = joinDate.getAttribute("value");
		System.out.println("JD Case 4 : Default value → '" + defaultVal + "' | " + (defaultVal.isEmpty() ? "PASS - Default is empty as expected" : "INFO - Default has value: " + defaultVal));

		System.out.println("=================================================");
		joinDate.clear(); joinDate.sendKeys("13-12-2020");
		String validDate = joinDate.getAttribute("value");
		sa.assertEquals(validDate, "13-12-2020", "Valid date 13-12-2020 NOT accepted. JoinDate should accept past date before app date 13-12-2021.");
		System.out.println("JD Case 5 : Entered '13-12-2020' → Got '" + validDate + "' | " + (validDate.equals("13-12-2020") ? "PASS - Valid date accepted" : "FAIL - Valid date rejected"));

		System.out.println("=================================================");
		joinDate.clear(); joinDate.sendKeys("2020/12/13");
		String invalidFmt = joinDate.getAttribute("value");
		sa.assertNotEquals(invalidFmt, "2020/12/13", "Invalid format YYYY/MM/DD accepted. Only DD-MM-YYYY should be allowed.");
		System.out.println("JD Case 6 : Entered '2020/12/13' → Got '" + invalidFmt + "' | " + (!invalidFmt.equals("2020/12/13") ? "PASS - Invalid format rejected" : "FAIL - Invalid format YYYY/MM/DD accepted"));

		System.out.println("=================================================");
		joinDate.clear(); joinDate.sendKeys("abcdef");
		String alphaVal = joinDate.getAttribute("value");
		sa.assertTrue(alphaVal.isEmpty(), "Alphabets 'abcdef' accepted in date field. Only numeric+separator allowed.");
		System.out.println("JD Case 7 : Entered 'abcdef' → Got '" + alphaVal + "' | " + (alphaVal.isEmpty() ? "PASS - Alphabets rejected" : "FAIL - Alphabets accepted in date field"));

		System.out.println("=================================================");
		joinDate.clear(); joinDate.sendKeys("@#$%^&");
		String splVal = joinDate.getAttribute("value");
		sa.assertTrue(splVal.isEmpty(), "Special chars '@#$%^&' accepted in date field. Only numeric+separator allowed.");
		System.out.println("JD Case 8 : Entered '@#$%^&' → Got '" + splVal + "' | " + (splVal.isEmpty() ? "PASS - Special chars rejected" : "FAIL - Special chars accepted in date field"));

		System.out.println("=================================================");
		joinDate.clear(); joinDate.sendKeys("13-12-2020");
		String numDate = joinDate.getAttribute("value");
		sa.assertTrue(numDate.matches("[0-9\\-/]+"), "Date contains non-numeric characters. Expected only digits and separators.");
		System.out.println("JD Case 9 : Entered '13-12-2020' → Got '" + numDate + "' | " + (numDate.matches("[0-9\\-/]+") ? "PASS - Only numeric+separator" : "FAIL - Contains non-numeric chars"));

		System.out.println("=================================================");
		joinDate.clear(); joinDate.sendKeys("32-12-2020"); joinDate.sendKeys(Keys.TAB); Thread.sleep(500);
		String day32 = joinDate.getAttribute("value"); String day32Toast = getToastMsg();
		System.out.println("Joining Date Case 10 : Field='Joining Date' | Input='32-12-2020' | Actual='" + day32 + "' | Toast='" + day32Toast + "' | " + (!day32Toast.isEmpty() ? "PASS - Day 32 rejected via toast" : "INFO - Day 32 accepted by field, no toast"));

		System.out.println("=================================================");
		joinDate.clear(); joinDate.sendKeys("15-13-2020"); joinDate.sendKeys(Keys.TAB); Thread.sleep(500);
		String month13 = joinDate.getAttribute("value"); String m13Toast = getToastMsg();
		System.out.println("Joining Date Case 11 : Field='Joining Date' | Input='15-13-2020' | Actual='" + month13 + "' | Toast='" + m13Toast + "' | " + (!m13Toast.isEmpty() ? "PASS - Month 13 rejected via toast" : "INFO - Month 13 accepted by field, no toast"));

		System.out.println("=================================================");
		joinDate.clear(); joinDate.sendKeys("29-02-2019"); joinDate.sendKeys(Keys.TAB); Thread.sleep(500);
		String nonLeap = joinDate.getAttribute("value"); String nlToast = getToastMsg();
		System.out.println("Joining Date Case 12 : Field='Joining Date' | Input='29-02-2019' (non-leap) | Actual='" + nonLeap + "' | Toast='" + nlToast + "' | " + (!nlToast.isEmpty() ? "PASS - Feb29 non-leap rejected via toast" : "INFO - Feb29 non-leap accepted, no toast"));

		System.out.println("=================================================");
		joinDate.clear(); joinDate.sendKeys("29-02-2020");
		String leapDate = joinDate.getAttribute("value");
		sa.assertEquals(leapDate, "29-02-2020", "Feb 29 rejected for leap year 2020. 2020 IS a leap year, should accept.");
		System.out.println("JD Case 13 : Entered '29-02-2020' → Got '" + leapDate + "' | " + (leapDate.equals("29-02-2020") ? "PASS - Feb29 leap year accepted" : "FAIL - Feb29 rejected for leap year 2020"));

		System.out.println("=================================================");
		joinDate.clear(); joinDate.sendKeys("01-01-2099");
		String futureDate = joinDate.getAttribute("value");
		joinDate.sendKeys(Keys.TAB); Thread.sleep(500);
		String futureToast = getToastMsg();
		System.out.println("Joining Date Case 14 : Field='Joining Date' | Input='01-01-2099' (future) | Actual='" + futureDate + "' | Toast='" + futureToast + "' | " + (!futureToast.isEmpty() ? "PASS - Future date rejected via toast" : "INFO - Future date accepted, no toast"));

		System.out.println("=================================================");
		joinDate.clear(); joinDate.sendKeys("01-01-1900");
		String pastDate = joinDate.getAttribute("value");
		joinDate.sendKeys(Keys.TAB); Thread.sleep(500);
		String pastToast = getToastMsg();
		System.out.println("Joining Date Case 15 : Field='Joining Date' | Input='01-01-1900' (very old) | Actual='" + pastDate + "' | Toast='" + pastToast + "' | " + (!pastToast.isEmpty() ? "PASS - Old date rejected via toast" : "INFO - Old date accepted, no toast"));

		System.out.println("=================================================");
		joinDate.clear(); joinDate.sendKeys("00-12-2020"); joinDate.sendKeys(Keys.TAB); Thread.sleep(500);
		String day00 = joinDate.getAttribute("value"); String d00Toast = getToastMsg();
		System.out.println("Joining Date Case 16 : Field='Joining Date' | Input='00-12-2020' (day 00) | Actual='" + day00 + "' | Toast='" + d00Toast + "' | " + (!d00Toast.isEmpty() ? "PASS - Day 00 rejected via toast" : "INFO - Day 00 accepted, no toast"));

		System.out.println("=================================================");
		joinDate.clear(); joinDate.sendKeys("15-00-2020"); joinDate.sendKeys(Keys.TAB); Thread.sleep(500);
		String month00 = joinDate.getAttribute("value"); String m00Toast = getToastMsg();
		System.out.println("Joining Date Case 17 : Field='Joining Date' | Input='15-00-2020' (month 00) | Actual='" + month00 + "' | Toast='" + m00Toast + "' | " + (!m00Toast.isEmpty() ? "PASS - Month 00 rejected via toast" : "INFO - Month 00 accepted, no toast"));

		System.out.println("=================================================");
		joinDate.clear(); joinDate.sendKeys("   ");
		String spaceVal = joinDate.getAttribute("value");
		sa.assertTrue(spaceVal.trim().isEmpty(), "Only spaces accepted as date value. Field should not accept blank spaces.");
		System.out.println("JD Case 18 : Entered '   ' → Got '" + spaceVal + "' | " + (spaceVal.trim().isEmpty() ? "PASS - Spaces rejected/blank" : "FAIL - Spaces accepted as value"));

		System.out.println("=================================================");
		joinDate.clear(); joinDate.sendKeys("13-12-20201234");
		String maxLen = joinDate.getAttribute("value");
		sa.assertTrue(maxLen.length() <= 10, "Exceeded max length 10. Date DD-MM-YYYY should be max 10 chars.");
		System.out.println("JD Case 19 : Entered 14 chars → Got length " + maxLen.length() + " | " + (maxLen.length() <= 10 ? "PASS - Max length 10 OK" : "FAIL - Length " + maxLen.length() + " exceeds max 10"));

		System.out.println("=================================================");
		joinDate.clear(); joinDate.sendKeys("1a-2b-20cd");
		String mixVal = joinDate.getAttribute("value");
		sa.assertNotEquals(mixVal, "1a-2b-20cd", "Alphanumeric mix '1a-2b-20cd' accepted. Date should be pure numeric.");
		System.out.println("JD Case 20 : Entered '1a-2b-20cd' → Got '" + mixVal + "' | " + (!mixVal.equals("1a-2b-20cd") ? "PASS - Alphanumeric mix rejected" : "FAIL - Alphanumeric mix accepted in date"));

		// Final: Set valid join date for record save (1 yr before app date)
		joinDate.clear(); joinDate.sendKeys("13-12-2020"); joinDate.sendKeys(Keys.TAB);
		System.out.println("JD : Final value set → '13-12-2020' (1 yr before app date 13-12-2021)");
		System.out.println("=================================================");
		System.out.println("EM7_JoinDate - All 20 cases executed.");
	}

}
