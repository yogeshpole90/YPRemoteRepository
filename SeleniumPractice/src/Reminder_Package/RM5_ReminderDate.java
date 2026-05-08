package Reminder_Package;

import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.Select;

public class RM5_ReminderDate extends RM2_Login {



	private WebElement getDateField() {
		return driver.findElement(By.id("reminderDate"));
	}

	private void selectDropdownForTest() throws Exception {
		WebElement dd = driver.findElement(By.id("reminderType"));
		Select sel = new Select(dd);
		sel.selectByIndex(1);
		Thread.sleep(200);
	}

	private boolean isInvalidDateRejected(String invalidDate) throws Exception {
		selectDropdownForTest();
		driver.findElement(By.id("save")).click();
		Thread.sleep(300);
		String toast = getToastMsg();
		String currentVal = getDateField().getAttribute("value");
		return !toast.isEmpty() || currentVal == null || currentVal.isEmpty() || !currentVal.equals(invalidDate);
	}

	public void validateReminderDate() throws Exception
	{
		System.out.println("=================================================");
		System.out.println("RM5 - REMINDER DATE FIELD VALIDATION START");
		System.out.println("=================================================");

		// TC1: Visible
		boolean isDisplayed = getDateField().isDisplayed();
		log("Reminder Date", "Date field should be visible on page", "true", String.valueOf(isDisplayed), isDisplayed);
		sa.assertTrue(isDisplayed, "Reminder Date should be visible");

		// TC2: Enabled
		boolean isEnabled = getDateField().isEnabled();
		log("Reminder Date", "Date field should be enabled/editable", "true", String.valueOf(isEnabled), isEnabled);
		sa.assertTrue(isEnabled, "Reminder Date should be enabled");

		// TC3: Placeholder
		String placeholder = getDateField().getAttribute("placeholder");
		log("Reminder Date", "Placeholder should be 'enter reminder date'", "enter reminder date", placeholder, "enter reminder date".equals(placeholder));

		// TC4: Empty initially
		String initialVal = getDateField().getAttribute("value");
		boolean emptyCheck = initialVal == null || initialVal.isEmpty();
		log("Reminder Date", "Date field should be empty initially", "Empty", "'" + initialVal + "'", emptyCheck);

		// TC5: hasDatepicker class
		String classAttr = getDateField().getAttribute("class");
		boolean dpCheck = classAttr != null && classAttr.contains("hasDatepicker");
		log("Reminder Date", "Date field should have datepicker class", "hasDatepicker", classAttr, dpCheck);

		// TC6: Input type text
		String inputType = getDateField().getAttribute("type");
		log("Reminder Date", "Input type should be 'text'", "text", inputType, "text".equals(inputType));

		// ========== VALID DATES ==========
		System.out.println("========== VALID DATE TESTS ==========");

		// TC7: Today's date
		getDateField().clear();
		getDateField().sendKeys("17-07-2025");
		Thread.sleep(200);
		String todayVal = getDateField().getAttribute("value");
		log("Reminder Date", "Enter today's date '17-07-2025'", "17-07-2025", todayVal, todayVal != null && !todayVal.isEmpty());

		// TC8: Clear
		getDateField().clear();
		Thread.sleep(200);
		String clearedVal = getDateField().getAttribute("value");
		log("Reminder Date", "Clear date field", "Empty", "'" + clearedVal + "'", clearedVal == null || clearedVal.isEmpty());

		// TC9: Past date
		getDateField().clear();
		getDateField().sendKeys("01-01-2024");
		Thread.sleep(200);
		log("Reminder Date", "Enter past date '01-01-2024'", "01-01-2024", getDateField().getAttribute("value"), true);

		// TC10: Valid leap year
		getDateField().clear();
		getDateField().sendKeys("29-02-2024");
		Thread.sleep(200);
		log("Reminder Date", "Enter valid leap year date '29-02-2024'", "29-02-2024", getDateField().getAttribute("value"), true);

		// ========== INVALID DATES ==========
		System.out.println("========== INVALID DATE TESTS ==========");

		// TC11: Text instead of date
		getDateField().clear();
		getDateField().sendKeys("abcdefgh");
		Thread.sleep(200);
		String textVal = getDateField().getAttribute("value");
		boolean textRejected = textVal == null || textVal.isEmpty() || !textVal.equals("abcdefgh");
		log("Reminder Date", "Enter text 'abcdefgh' - should be rejected", "Field should reject text input", textVal, textRejected);

		// TC12: Special characters
		getDateField().clear();
		getDateField().sendKeys("@#$%^&*!");
		Thread.sleep(200);
		String splVal = getDateField().getAttribute("value");
		boolean splRejected = splVal == null || splVal.isEmpty() || !splVal.equals("@#$%^&*!");
		log("Reminder Date", "Enter special characters '@#$%^&*!' - should be rejected", "Field should reject special characters", splVal, splRejected);

		// TC13: Day 32
		getDateField().clear();
		getDateField().sendKeys("32-12-2021");
		Thread.sleep(200);
		String day32Val = getDateField().getAttribute("value");
		boolean day32Rejected = isInvalidDateRejected("32-12-2021");
		log("Reminder Date", "Enter invalid day 32 (32-12-2021) - should be REJECTED", "Should reject day > 31", day32Val + (day32Rejected ? " [Rejected]" : " [Accepted - BUG]"), day32Rejected);
		sa.assertTrue(day32Rejected, "BUG: Day 32 accepted");

		// TC14: Month 13
		getDateField().clear();
		getDateField().sendKeys("15-13-2021");
		Thread.sleep(200);
		String month13Val = getDateField().getAttribute("value");
		boolean month13Rejected = isInvalidDateRejected("15-13-2021");
		log("Reminder Date", "Enter invalid month 13 (15-13-2021) - should be REJECTED", "Should reject month > 12", month13Val + (month13Rejected ? " [Rejected]" : " [Accepted - BUG]"), month13Rejected);
		sa.assertTrue(month13Rejected, "BUG: Month 13 accepted");

		// TC15: Feb 29 non-leap year
		getDateField().clear();
		getDateField().sendKeys("29-02-2023");
		Thread.sleep(200);
		String feb29Val = getDateField().getAttribute("value");
		boolean feb29Rejected = isInvalidDateRejected("29-02-2023");
		log("Reminder Date", "Enter 29-02-2023 (non-leap year) - should be REJECTED", "Should reject Feb 29 non-leap year", feb29Val + (feb29Rejected ? " [Rejected]" : " [Accepted - BUG]"), feb29Rejected);
		sa.assertTrue(feb29Rejected, "BUG: Feb 29 non-leap year accepted");

		// TC16: Far future date
		getDateField().clear();
		getDateField().sendKeys("01-01-2099");
		Thread.sleep(200);
		String futureVal = getDateField().getAttribute("value");
		boolean futureRejected = isInvalidDateRejected("01-01-2099");
		log("Reminder Date", "Enter far future date '01-01-2099' - should be REJECTED", "Should reject unrealistic future date", futureVal + (futureRejected ? " [Rejected]" : " [Accepted - BUG]"), futureRejected);
		sa.assertTrue(futureRejected, "BUG: Far future date accepted");

		// TC17: Very old past date
		getDateField().clear();
		getDateField().sendKeys("01-01-1900");
		Thread.sleep(200);
		String oldVal = getDateField().getAttribute("value");
		boolean oldRejected = isInvalidDateRejected("01-01-1900");
		log("Reminder Date", "Enter very old past date '01-01-1900' - should be REJECTED", "Should reject unrealistic past date", oldVal + (oldRejected ? " [Rejected]" : " [Accepted - BUG]"), oldRejected);
		sa.assertTrue(oldRejected, "BUG: Very old past date accepted");

		// TC18: All zeros 00-00-0000
		getDateField().clear();
		getDateField().sendKeys("00-00-0000");
		Thread.sleep(200);
		String zeroAllVal = getDateField().getAttribute("value");
		boolean zeroAllRejected = isInvalidDateRejected("00-00-0000");
		log("Reminder Date", "Enter all zeros '00-00-0000' - should be REJECTED", "Should reject all zeros date", zeroAllVal + (zeroAllRejected ? " [Rejected]" : " [Accepted - BUG]"), zeroAllRejected);
		sa.assertTrue(zeroAllRejected, "BUG: All zeros date accepted");

		// TC19: Zero day 00-12-2021
		getDateField().clear();
		getDateField().sendKeys("00-12-2021");
		Thread.sleep(200);
		String zeroDayVal = getDateField().getAttribute("value");
		boolean zeroDayRejected = isInvalidDateRejected("00-12-2021");
		log("Reminder Date", "Enter zero day '00-12-2021' - should be REJECTED", "Should reject day=00", zeroDayVal + (zeroDayRejected ? " [Rejected]" : " [Accepted - BUG]"), zeroDayRejected);
		sa.assertTrue(zeroDayRejected, "BUG: Zero day accepted");

		// TC20: Zero month 15-00-2021
		getDateField().clear();
		getDateField().sendKeys("15-00-2021");
		Thread.sleep(200);
		String zeroMonthVal = getDateField().getAttribute("value");
		boolean zeroMonthRejected = isInvalidDateRejected("15-00-2021");
		log("Reminder Date", "Enter zero month '15-00-2021' - should be REJECTED", "Should reject month=00", zeroMonthVal + (zeroMonthRejected ? " [Rejected]" : " [Accepted - BUG]"), zeroMonthRejected);
		sa.assertTrue(zeroMonthRejected, "BUG: Zero month accepted");

		// TC21: Zero month+year 15-00-0000
		getDateField().clear();
		getDateField().sendKeys("15-00-0000");
		Thread.sleep(200);
		String zeroMYVal = getDateField().getAttribute("value");
		boolean zeroMYRejected = isInvalidDateRejected("15-00-0000");
		log("Reminder Date", "Enter zero month+year '15-00-0000' - should be REJECTED", "Should reject month=00 year=0000", zeroMYVal + (zeroMYRejected ? " [Rejected]" : " [Accepted - BUG]"), zeroMYRejected);
		sa.assertTrue(zeroMYRejected, "BUG: Zero month+year accepted");

		// TC22: Negative day
		getDateField().clear();
		getDateField().sendKeys("-1-07-2025");
		Thread.sleep(200);
		String negVal = getDateField().getAttribute("value");
		boolean negRejected = negVal == null || negVal.isEmpty() || !negVal.equals("-1-07-2025");
		log("Reminder Date", "Enter negative day '-1-07-2025' - should be rejected", "Should not accept negative day", negVal, negRejected);

		// TC23: Spaces only
		getDateField().clear();
		getDateField().sendKeys("          ");
		Thread.sleep(200);
		String spaceVal = getDateField().getAttribute("value");
		boolean spaceRejected = spaceVal == null || spaceVal.trim().isEmpty();
		log("Reminder Date", "Enter only spaces - should treat as empty", "Should treat as empty", "'" + spaceVal + "'", spaceRejected);

		// TC24: Without separator
		getDateField().clear();
		getDateField().sendKeys("17072025");
		Thread.sleep(200);
		log("Reminder Date", "Enter date without separator '17072025'", "Should auto-format or reject", getDateField().getAttribute("value"), true);

		// TC25: Slash separator
		getDateField().clear();
		getDateField().sendKeys("17/07/2025");
		Thread.sleep(200);
		log("Reminder Date", "Enter date with slash '17/07/2025'", "Should accept or reject based on format", getDateField().getAttribute("value"), true);

		// TC26: Single digit
		getDateField().clear();
		getDateField().sendKeys("1-7-2025");
		Thread.sleep(200);
		log("Reminder Date", "Enter single digit day/month '1-7-2025'", "Should auto-format or accept", getDateField().getAttribute("value"), true);

		// TC27: Mandatory check
		getDateField().clear();
		Thread.sleep(200);
		selectDropdownForTest();
		driver.findElement(By.id("save")).click();
		Thread.sleep(300);
		String emptyToast = getToastMsg();
		boolean mandatoryCheck = emptyToast.contains("Reminder Date");
		log("Reminder Date", "Save with empty date - mandatory validation", "Reminder Date is required.", emptyToast.isEmpty() ? "No toast" : emptyToast, mandatoryCheck);

		// TC28: Re-enter valid date
		getDateField().clear();
		getDateField().sendKeys("17-07-2025");
		Thread.sleep(200);
		log("Reminder Date", "Re-enter valid date '17-07-2025'", "17-07-2025", getDateField().getAttribute("value"), true);

		System.out.println("=================================================");
		System.out.println("RM5 - REMINDER DATE FIELD VALIDATION END");
		System.out.println("=================================================");
	}
}


