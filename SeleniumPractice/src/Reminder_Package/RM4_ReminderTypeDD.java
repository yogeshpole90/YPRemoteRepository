package Reminder_Package;

import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.Select;
import java.util.List;

public class RM4_ReminderTypeDD extends RM2_Login {



	public void validateReminderType() throws Exception
	{
		System.out.println("=================================================");
		System.out.println("RM4 - REMINDER TYPE DROPDOWN VALIDATION START");
		System.out.println("=================================================");

		WebElement dropdown = driver.findElement(By.id("reminderType"));
		jse.executeScript("arguments[0].scrollIntoView({block:'center',behavior:'smooth'})", dropdown);
		Thread.sleep(300);
		Select select = new Select(dropdown);

		// TC1: Dropdown visible
		boolean isDisplayed = dropdown.isDisplayed();
		log("Reminder Type DD", "Dropdown should be visible on page", "true", String.valueOf(isDisplayed), isDisplayed);
		sa.assertTrue(isDisplayed, "Reminder Type dropdown should be visible");

		// TC2: Dropdown enabled
		boolean isEnabled = dropdown.isEnabled();
		log("Reminder Type DD", "Dropdown should be enabled/clickable", "true", String.valueOf(isEnabled), isEnabled);
		sa.assertTrue(isEnabled, "Reminder Type dropdown should be enabled");

		// TC3: Default value
		String defaultVal = select.getFirstSelectedOption().getText().trim();
		boolean defaultCheck = defaultVal.contains("--SELECT");
		log("Reminder Type DD", "Default value should be --SELECT [REMINDERTYPE]--", "--SELECT [REMINDERTYPE]--", defaultVal, defaultCheck);
		sa.assertTrue(defaultCheck, "Default should be --SELECT [REMINDERTYPE]--");

		// TC4: Total options (1 default + 3 actual = 4)
		List<WebElement> options = select.getOptions();
		int totalOptions = options.size();
		log("Reminder Type DD", "Total dropdown options count", "4", String.valueOf(totalOptions), totalOptions == 4);
		sa.assertEquals(totalOptions, 4, "Total options should be 4");

		// TC5: Print all options
		System.out.println("----------------------------------------------");
		System.out.println("All Dropdown Options:");
		for (int i = 0; i < options.size(); i++) {
			System.out.println("  [" + i + "] " + options.get(i).getText() + " (value=" + options.get(i).getAttribute("value") + ")");
		}

		// TC6-TC8: Verify each option present
		String[] expectedOptions = {"Call", "Mail", "Site Visit"};
		for (String exp : expectedOptions) {
			boolean found = false;
			for (WebElement opt : options) {
				if (opt.getText().trim().equals(exp)) { found = true; break; }
			}
			log("Reminder Type DD", "Option '" + exp + "' should be present in dropdown", exp + " present", found ? exp + " found" : exp + " NOT found", found);
			sa.assertTrue(found, "Option missing: " + exp);
		}

		// TC9: Select Call
		select.selectByVisibleText("Call");
		Thread.sleep(300);
		String selected = select.getFirstSelectedOption().getText().trim();
		log("Reminder Type DD", "Select by visible text 'Call'", "Call", selected, selected.equals("Call"));
		sa.assertEquals(selected, "Call", "Call should be selected");

		// TC10: Select by value MAIL
		select.selectByValue("MAIL");
		Thread.sleep(300);
		selected = select.getFirstSelectedOption().getText().trim();
		log("Reminder Type DD", "Select by value 'MAIL'", "Mail", selected, selected.equals("Mail"));
		sa.assertEquals(selected, "Mail", "Mail should be selected");

		// TC11: Select Site Visit
		select.selectByVisibleText("Site Visit");
		Thread.sleep(300);
		selected = select.getFirstSelectedOption().getText().trim();
		log("Reminder Type DD", "Select by visible text 'Site Visit'", "Site Visit", selected, selected.equals("Site Visit"));
		sa.assertEquals(selected, "Site Visit", "Site Visit should be selected");

		// TC12: Reset to default
		select.selectByIndex(0);
		Thread.sleep(200);
		String resetVal = select.getFirstSelectedOption().getText().trim();
		log("Reminder Type DD", "Reset dropdown to default value", "--SELECT--", resetVal, resetVal.contains("--SELECT"));
		sa.assertTrue(resetVal.contains("--SELECT"), "Dropdown should reset to default");

		// TC13: Final - Set dropdown to index 1 (Call) for next tests
		select.selectByIndex(1);
		Thread.sleep(200);
		String finalVal = select.getFirstSelectedOption().getText().trim();
		log("Reminder Type DD", "Set dropdown to 'Call' (index 1) for further tests", "Call", finalVal, finalVal.equals("Call"));
		sa.assertEquals(finalVal, "Call", "Call should be selected for further tests");

		System.out.println("=================================================");
		System.out.println("RM4 - REMINDER TYPE DROPDOWN VALIDATION END");
		System.out.println("=================================================");
	}
}


