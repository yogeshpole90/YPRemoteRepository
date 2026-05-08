package Calendar_Package;

import org.openqa.selenium.*;
import org.testng.annotations.Test;

public class CAL6_DropdownNavigation extends CAL2_Setup {

	@Test
	public void validateDropdownNavigation() throws Exception
	{
		System.out.println("=================================================");
		System.out.println("CAL6 - CALENDAR DROPDOWN & NAVIGATION START");
		System.out.println("=================================================");

		WebElement dropdownBtn = driver.findElement(By.xpath("//*[@id='dropdownMenu-calendarType']"));
		WebElement rangeText = driver.findElement(By.id("renderRange"));
		WebElement forwardBtn = driver.findElement(By.className("ic-arrow-line-right"));
		WebElement backwardBtn = driver.findElement(By.className("ic-arrow-line-left"));

		// Dropdown button checks
		log("Dropdown Button", "Should be visible on page", "true", String.valueOf(dropdownBtn.isDisplayed()), dropdownBtn.isDisplayed());
		sa.assertTrue(dropdownBtn.isDisplayed(), "Dropdown button not displayed");

		log("Dropdown Button", "Should be enabled", "true", String.valueOf(dropdownBtn.isEnabled()), dropdownBtn.isEnabled());
		sa.assertTrue(dropdownBtn.isEnabled(), "Dropdown button not enabled");

		log("Date Range Text", "Should be visible on page", "true", String.valueOf(rangeText.isDisplayed()), rangeText.isDisplayed());
		sa.assertTrue(rangeText.isDisplayed(), "Date range text not displayed");

		// ========== DAILY ==========
		dropdownBtn.click(); Thread.sleep(500);
		driver.findElement(By.xpath("//a[@data-action='toggle-daily']")).click(); Thread.sleep(1000);
		String dailyRange = rangeText.getText().trim();
		log("Daily View", "Select Daily view — date range should display", "Non-empty date range", dailyRange, !dailyRange.isEmpty());
		sa.assertFalse(dailyRange.isEmpty(), "Date range empty after Daily");

		// Daily Forward
		String beforeFwdDaily = rangeText.getText().trim();
		forwardBtn.click(); Thread.sleep(1000);
		String afterFwdDaily = rangeText.getText().trim();
		log("Daily Forward", "Click forward arrow — date should change to next day", beforeFwdDaily + " → should change", afterFwdDaily, !afterFwdDaily.equals(beforeFwdDaily));
		sa.assertNotEquals(afterFwdDaily, beforeFwdDaily, "Daily forward did not change date");

		// Daily Backward
		String beforeBwdDaily = rangeText.getText().trim();
		backwardBtn.click(); Thread.sleep(1000);
		String afterBwdDaily = rangeText.getText().trim();
		log("Daily Backward", "Click backward arrow — date should change to previous day", beforeBwdDaily + " → should change", afterBwdDaily, !afterBwdDaily.equals(beforeBwdDaily));
		sa.assertNotEquals(afterBwdDaily, beforeBwdDaily, "Daily backward did not change date");

		// ========== WEEKLY ==========
		dropdownBtn.click(); Thread.sleep(500);
		driver.findElement(By.xpath("//a[@data-action='toggle-weekly']")).click(); Thread.sleep(1000);
		String weeklyRange = rangeText.getText().trim();
		log("Weekly View", "Select Weekly view — date range should show week span", "Date range with ~ or -", weeklyRange, weeklyRange.contains("~") || weeklyRange.contains("-") || weeklyRange.length() > 12);
		sa.assertTrue(weeklyRange.contains("~") || weeklyRange.contains("-") || weeklyRange.length() > 12, "Weekly range invalid");

		// Weekly Forward
		String beforeFwdWeekly = rangeText.getText().trim();
		forwardBtn.click(); Thread.sleep(1000);
		String afterFwdWeekly = rangeText.getText().trim();
		log("Weekly Forward", "Click forward arrow — week should change to next week", beforeFwdWeekly + " → should change", afterFwdWeekly, !afterFwdWeekly.equals(beforeFwdWeekly));
		sa.assertNotEquals(afterFwdWeekly, beforeFwdWeekly, "Weekly forward did not change");

		// Weekly Backward
		String beforeBwdWeekly = rangeText.getText().trim();
		backwardBtn.click(); Thread.sleep(1000);
		String afterBwdWeekly = rangeText.getText().trim();
		log("Weekly Backward", "Click backward arrow — week should change to previous week", beforeBwdWeekly + " → should change", afterBwdWeekly, !afterBwdWeekly.equals(beforeBwdWeekly));
		sa.assertNotEquals(afterBwdWeekly, beforeBwdWeekly, "Weekly backward did not change");

		// ========== MONTHLY ==========
		dropdownBtn.click(); Thread.sleep(500);
		driver.findElement(By.xpath("//a[@data-action='toggle-monthly']")).click(); Thread.sleep(1000);
		String monthlyRange = rangeText.getText().trim();
		log("Monthly View", "Select Monthly view — month name should display", "Non-empty month range", monthlyRange, !monthlyRange.isEmpty());
		sa.assertFalse(monthlyRange.isEmpty(), "Monthly range empty");

		// Monthly Forward
		String beforeFwdMonthly = rangeText.getText().trim();
		forwardBtn.click(); Thread.sleep(1000);
		String afterFwdMonthly = rangeText.getText().trim();
		log("Monthly Forward", "Click forward arrow — month should change to next month", beforeFwdMonthly + " → should change", afterFwdMonthly, !afterFwdMonthly.equals(beforeFwdMonthly));
		sa.assertNotEquals(afterFwdMonthly, beforeFwdMonthly, "Monthly forward did not change");

		// Monthly Backward
		String beforeBwdMonthly = rangeText.getText().trim();
		backwardBtn.click(); Thread.sleep(1000);
		String afterBwdMonthly = rangeText.getText().trim();
		log("Monthly Backward", "Click backward arrow — month should change to previous month", beforeBwdMonthly + " → should change", afterBwdMonthly, !afterBwdMonthly.equals(beforeBwdMonthly));
		sa.assertNotEquals(afterBwdMonthly, beforeBwdMonthly, "Monthly backward did not change");

		System.out.println("=================================================");
		System.out.println("CAL6 - CALENDAR DROPDOWN & NAVIGATION END");
		System.out.println("=================================================");
	}

}
