package Calendar_Package;

import org.openqa.selenium.*;
import org.openqa.selenium.support.ui.*;
import org.testng.annotations.Test;

public class CAL7_CreateRecord extends CAL2_Setup {

	@Test
	public void createRecord() throws Exception
	{
		System.out.println("=================================================");
		System.out.println("CAL7 - CALENDAR CREATE RECORD START");
		System.out.println("=================================================");

		WebElement dropdownBtn = driver.findElement(By.xpath("//*[@id='dropdownMenu-calendarType']"));
		WebDriverWait wait = new WebDriverWait(driver, 15);

		// ========== DAILY RECORD ==========
		System.out.println("---------- DAILY RECORD ----------");
		jse.executeScript("arguments[0].click()", dropdownBtn); Thread.sleep(500);
		jse.executeScript("arguments[0].click()", driver.findElement(By.xpath("//a[@data-action='toggle-daily']")));
		Thread.sleep(2000);
		jse.executeScript("window.scrollBy(0,3000)"); Thread.sleep(2000);

		WebElement dailySlot = driver.findElement(By.xpath("(//div[contains(@class,'tui-full-calendar-time-date-s')])[1]"));
		jse.executeScript("arguments[0].scrollIntoView({block:'center'})", dailySlot); Thread.sleep(500);
		wait.until(ExpectedConditions.elementToBeClickable(dailySlot));
		dailySlot.click(); Thread.sleep(2000);

		WebElement popupDD1 = driver.findElement(By.xpath("//button[contains(@class,'tui-full-calendar-dropdown-button')]"));
		jse.executeScript("arguments[0].scrollIntoView({block:'center'})", popupDD1); Thread.sleep(500);

		fillPopup("Daily PTP", "Pune", "Daily Test", "2026-03-25 09:00", "2026-03-25 09:30", "PTP");
		jse.executeScript("arguments[0].click()", driver.findElement(By.xpath("//button[contains(@class,'tui-full-calendar-popup-save')]")));
		Thread.sleep(2000);
		log("Daily Record", "Create PTP record in Daily view with Subject='Daily PTP', Location='Pune', Type='PTP'", "Record saved successfully", "Saved", true);

		jse.executeScript("window.scrollTo(0,0)"); Thread.sleep(1000);

		// ========== WEEKLY RECORD ==========
		System.out.println("---------- WEEKLY RECORD ----------");
		jse.executeScript("arguments[0].click()", dropdownBtn); Thread.sleep(500);
		jse.executeScript("arguments[0].click()", driver.findElement(By.xpath("//a[@data-action='toggle-weekly']")));
		Thread.sleep(2000);
		jse.executeScript("window.scrollBy(0,3000)"); Thread.sleep(2000);

		WebElement weeklySlot = driver.findElement(By.xpath("(//div[contains(@class,'tui-full-calendar-time-date-s')])[1]"));
		jse.executeScript("arguments[0].scrollIntoView({block:'center'})", weeklySlot); Thread.sleep(500);
		wait.until(ExpectedConditions.elementToBeClickable(weeklySlot));
		weeklySlot.click(); Thread.sleep(2000);

		WebElement popupDD2 = driver.findElement(By.xpath("//button[contains(@class,'tui-full-calendar-dropdown-button')]"));
		jse.executeScript("arguments[0].scrollIntoView({block:'center'})", popupDD2); Thread.sleep(500);

		fillPopup("Weekly Call", "Mumbai", "Weekly Test", "2026-03-25 10:00", "2026-03-25 10:30", "Call");
		jse.executeScript("arguments[0].click()", driver.findElement(By.xpath("//button[contains(@class,'tui-full-calendar-popup-save')]")));
		Thread.sleep(2000);
		log("Weekly Record", "Create Call record in Weekly view with Subject='Weekly Call', Location='Mumbai', Type='Call'", "Record saved successfully", "Saved", true);

		jse.executeScript("window.scrollTo(0,0)"); Thread.sleep(1000);

		// ========== MONTHLY RECORD ==========
		System.out.println("---------- MONTHLY RECORD ----------");
		jse.executeScript("arguments[0].click()", dropdownBtn); Thread.sleep(500);
		jse.executeScript("arguments[0].click()", driver.findElement(By.xpath("//a[@data-action='toggle-monthly']")));
		Thread.sleep(1000);

		WebElement monthlyCell = driver.findElement(By.xpath("(//div[contains(@class,'tui-full-calendar-month-week-item')])[2]"));
		jse.executeScript("arguments[0].scrollIntoView({block:'center'})", monthlyCell); Thread.sleep(500);
		wait.until(ExpectedConditions.elementToBeClickable(monthlyCell));
		monthlyCell.click(); Thread.sleep(2000);

		WebElement popupDD3 = driver.findElement(By.xpath("//button[contains(@class,'tui-full-calendar-dropdown-button')]"));
		jse.executeScript("arguments[0].scrollIntoView({block:'center'})", popupDD3); Thread.sleep(500);

		fillPopup("Monthly Site Visit", "Delhi", "Monthly Test", "2026-03-25 14:00", "2026-03-25 14:30", "Site Visit");
		jse.executeScript("arguments[0].click()", driver.findElement(By.xpath("//button[contains(@class,'tui-full-calendar-popup-save')]")));
		Thread.sleep(2000);
		log("Monthly Record", "Create Site Visit record in Monthly view with Subject='Monthly Site Visit', Location='Delhi', Type='Site Visit'", "Record saved successfully", "Saved", true);

		System.out.println("=================================================");
		System.out.println("CAL7 - CALENDAR CREATE RECORD END");
		System.out.println("=================================================");
	}

	private void fillPopup(String subject, String location, String desc, String startDt, String endDt, String calType) throws Exception
	{
		// Select calendar type dropdown
		driver.findElement(By.xpath("//button[contains(@class,'tui-full-calendar-dropdown-button')]")).click();
		Thread.sleep(500);
		driver.findElement(By.xpath("//li[contains(@class,'tui-full-calendar-dropdown-menu-item')]//span[text()='" + calType + "']")).click();
		Thread.sleep(500);
		log("Popup - Calendar Type", "Select '" + calType + "' from popup dropdown", calType, calType, true);

		// Subject
		WebElement subjectField = driver.findElement(By.xpath("//input[@placeholder='Subject']"));
		subjectField.sendKeys(subject);
		log("Popup - Subject", "Enter subject '" + subject + "'", subject, subjectField.getAttribute("value"), subjectField.getAttribute("value").equals(subject));

		// Location
		WebElement locationField = driver.findElement(By.xpath("//input[@placeholder='Location']"));
		locationField.sendKeys(location);
		log("Popup - Location", "Enter location '" + location + "'", location, locationField.getAttribute("value"), locationField.getAttribute("value").equals(location));

		// Description
		WebElement descField = driver.findElement(By.xpath("//input[@placeholder='Description']"));
		descField.sendKeys(desc);
		log("Popup - Description", "Enter description '" + desc + "'", desc, descField.getAttribute("value"), descField.getAttribute("value").equals(desc));

		// Start date
		WebElement startDate = driver.findElement(By.id("tui-full-calendar-schedule-start-date"));
		startDate.click(); startDate.sendKeys(Keys.CONTROL, "a"); startDate.sendKeys(Keys.DELETE);
		startDate.sendKeys(startDt); startDate.sendKeys(Keys.ESCAPE); Thread.sleep(500);
		log("Popup - Start Date", "Set start date '" + startDt + "'", startDt, startDate.getAttribute("value"), true);

		// End date
		WebElement endDate = driver.findElement(By.id("tui-full-calendar-schedule-end-date"));
		endDate.click(); endDate.sendKeys(Keys.CONTROL + "a"); endDate.sendKeys(Keys.DELETE);
		endDate.sendKeys(endDt); endDate.sendKeys(Keys.ESCAPE); Thread.sleep(500);
		log("Popup - End Date", "Set end date '" + endDt + "'", endDt, endDate.getAttribute("value"), true);

		Thread.sleep(1000);
	}

}
