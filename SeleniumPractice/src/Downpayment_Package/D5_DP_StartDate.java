package Downpayment_Package;

import org.openqa.selenium.By;
import org.openqa.selenium.Keys;
import org.openqa.selenium.WebElement;

public class D5_DP_StartDate extends D2_DP_Login {



	public void validateStartDate()
	{
		System.out.println("=================================================");
		System.out.println("D5 - PTP START DATE VALIDATION START");
		System.out.println("=================================================");

		WebElement ptpDate = driver.findElement(By.id("dateOfPTPStart"));

		log("PTP Start Date", "Field should be visible on page", "true", String.valueOf(ptpDate.isDisplayed()), ptpDate.isDisplayed());
		sa.assertTrue(ptpDate.isDisplayed(), "PTP Date not displayed.");

		log("PTP Start Date", "Field should be enabled/editable", "true", String.valueOf(ptpDate.isEnabled()), ptpDate.isEnabled());
		sa.assertTrue(ptpDate.isEnabled(), "PTP Date is disabled.");

		ptpDate.click(); ptpDate.sendKeys(Keys.TAB);
		log("PTP Start Date", "Click on Date field - should be clickable", "Clickable", "Click accepted", true);

		String defaultVal = ptpDate.getAttribute("value");
		log("PTP Start Date", "Default value should be empty", "Empty", "'" + defaultVal + "'", defaultVal.isEmpty());

		ptpDate.clear(); ptpDate.sendKeys("19-12-2021"); ptpDate.sendKeys(Keys.TAB);
		String validDate = ptpDate.getAttribute("value");
		log("PTP Start Date", "Enter valid date '19-12-2021'", "19-12-2021", validDate, validDate.equals("19-12-2021"));
		sa.assertEquals(validDate, "19-12-2021", "Not accepting valid date.");

		ptpDate.clear(); ptpDate.sendKeys("2021/12/19"); ptpDate.sendKeys(Keys.TAB);
		String invalidFmt = ptpDate.getAttribute("value");
		log("PTP Start Date", "Enter invalid format '2021/12/19' - should be rejected", "Not 2021/12/19", invalidFmt, !invalidFmt.equals("2021/12/19"));
		sa.assertNotEquals(invalidFmt, "2021/12/19", "Accepting invalid format.");

		ptpDate.clear(); ptpDate.sendKeys("abcdef"); ptpDate.sendKeys(Keys.TAB);
		String alphaVal = ptpDate.getAttribute("value");
		log("PTP Start Date", "Enter alphabets 'abcdef' - should be rejected", "Empty (rejected)", alphaVal, alphaVal.isEmpty());
		sa.assertTrue(alphaVal.isEmpty(), "Accepting alphabets.");

		ptpDate.clear(); ptpDate.sendKeys("@#$%^&"); ptpDate.sendKeys(Keys.TAB);
		String splVal = ptpDate.getAttribute("value");
		log("PTP Start Date", "Enter special chars '@#$%^&' - should be rejected", "Empty (rejected)", splVal, splVal.isEmpty());
		sa.assertTrue(splVal.isEmpty(), "Accepting special chars.");

		ptpDate.clear(); ptpDate.sendKeys("19-12-2021"); ptpDate.sendKeys(Keys.TAB);
		String numDate = ptpDate.getAttribute("value");
		log("PTP Start Date", "Enter numeric+separator '19-12-2021'", "Numeric+separator only", numDate, numDate.matches("[0-9\\-/]+"));
		sa.assertTrue(numDate.matches("[0-9\\-/]+"), "Non-numeric chars present.");

		ptpDate.clear(); ptpDate.sendKeys("32-12-2021"); ptpDate.sendKeys(Keys.TAB);
		String day32 = ptpDate.getAttribute("value");
		log("PTP Start Date", "Enter invalid day 32 (32-12-2021) - should be REJECTED", "Not 32-12-2021", day32, !day32.equals("32-12-2021"));
		sa.assertNotEquals(day32, "32-12-2021", "Accepting day 32.");

		ptpDate.clear(); ptpDate.sendKeys("15-13-2021"); ptpDate.sendKeys(Keys.TAB);
		String month13 = ptpDate.getAttribute("value");
		log("PTP Start Date", "Enter invalid month 13 (15-13-2021) - should be REJECTED", "Not 15-13-2021", month13, !month13.equals("15-13-2021"));
		sa.assertNotEquals(month13, "15-13-2021", "Accepting month 13.");

		ptpDate.clear(); ptpDate.sendKeys("29-02-2023"); ptpDate.sendKeys(Keys.TAB);
		String nonLeap = ptpDate.getAttribute("value");
		log("PTP Start Date", "Enter 29-02-2023 (non-leap year) - should be REJECTED", "Not 29-02-2023", nonLeap, !nonLeap.equals("29-02-2023"));
		sa.assertNotEquals(nonLeap, "29-02-2023", "Accepting Feb 29 non-leap.");

		ptpDate.clear(); ptpDate.sendKeys("29-02-2024"); ptpDate.sendKeys(Keys.TAB);
		String leapDate = ptpDate.getAttribute("value");
		log("PTP Start Date", "Enter 29-02-2024 (leap year) - should be accepted", "29-02-2024", leapDate, leapDate.equals("29-02-2024"));
		sa.assertEquals(leapDate, "29-02-2024", "Not accepting Feb 29 leap.");

		ptpDate.clear(); ptpDate.sendKeys("01-01-2099"); ptpDate.sendKeys(Keys.TAB);
		String futureDate = ptpDate.getAttribute("value");
		log("PTP Start Date", "Enter far future date '01-01-2099' - should be REJECTED", "Not 01-01-2099", futureDate, !futureDate.equals("01-01-2099"));
		sa.assertNotEquals(futureDate, "01-01-2099", "Accepting far future.");

		ptpDate.clear(); ptpDate.sendKeys("01-01-1900"); ptpDate.sendKeys(Keys.TAB);
		String pastDate = ptpDate.getAttribute("value");
		log("PTP Start Date", "Enter very old date '01-01-1900' - should be REJECTED", "Not 01-01-1900", pastDate, !pastDate.equals("01-01-1900"));
		sa.assertNotEquals(pastDate, "01-01-1900", "Accepting very old date.");

		ptpDate.clear(); ptpDate.sendKeys("00-12-2021"); ptpDate.sendKeys(Keys.TAB);
		String day00 = ptpDate.getAttribute("value");
		log("PTP Start Date", "Enter zero day '00-12-2021' - should be REJECTED", "Not 00-12-2021", day00, !day00.equals("00-12-2021"));
		sa.assertNotEquals(day00, "00-12-2021", "Accepting day 00.");

		ptpDate.clear(); ptpDate.sendKeys("15-00-2021"); ptpDate.sendKeys(Keys.TAB);
		String month00 = ptpDate.getAttribute("value");
		log("PTP Start Date", "Enter zero month '15-00-2021' - should be REJECTED", "Not 15-00-2021", month00, !month00.equals("15-00-2021"));
		sa.assertNotEquals(month00, "15-00-2021", "Accepting month 00.");

		ptpDate.clear(); ptpDate.sendKeys("   "); ptpDate.sendKeys(Keys.TAB);
		String spaceVal = ptpDate.getAttribute("value");
		log("PTP Start Date", "Enter spaces only - should be rejected", "Empty (rejected)", spaceVal.trim(), spaceVal.trim().isEmpty());
		sa.assertTrue(spaceVal.trim().isEmpty(), "Accepting only spaces.");

		ptpDate.clear(); ptpDate.sendKeys("19-12-20211234"); ptpDate.sendKeys(Keys.TAB);
		String maxLen = ptpDate.getAttribute("value");
		log("PTP Start Date", "Enter 14 chars - max length check", "Length <= 10", "Length = " + maxLen.length(), maxLen.length() <= 10);
		sa.assertTrue(maxLen.length() <= 10, "Exceeding max length.");

		ptpDate.clear(); ptpDate.sendKeys("1a-2b-20cd"); ptpDate.sendKeys(Keys.TAB);
		String mixVal = ptpDate.getAttribute("value");
		log("PTP Start Date", "Enter alphanumeric '1a-2b-20cd' - should be rejected", "Not 1a-2b-20cd", mixVal, !mixVal.equals("1a-2b-20cd"));
		sa.assertNotEquals(mixVal, "1a-2b-20cd", "Accepting alphanumeric.");

		ptpDate.clear(); ptpDate.sendKeys("19-12-2025"); ptpDate.sendKeys(Keys.TAB);
		log("PTP Start Date", "Final value set '19-12-2025' for record save", "19-12-2025", ptpDate.getAttribute("value"), true);

		System.out.println("=================================================");
		System.out.println("D5 - PTP START DATE VALIDATION END");
		System.out.println("=================================================");
	}
}


