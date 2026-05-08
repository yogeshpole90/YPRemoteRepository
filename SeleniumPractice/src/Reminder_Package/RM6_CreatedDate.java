package Reminder_Package;

import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;

public class RM6_CreatedDate extends RM2_Login {



	public void validateCreatedDate() throws Exception
	{
		System.out.println("=================================================");
		System.out.println("RM6 - REMINDER CREATED DATE VALIDATION START");
		System.out.println("=================================================");

		WebElement createdDate = driver.findElement(By.id("reminderCreateDate"));

		// TC1: Visible
		boolean isDisplayed = createdDate.isDisplayed();
		log("Created Date", "Created Date field should be visible on page", "true", String.valueOf(isDisplayed), isDisplayed);
		sa.assertTrue(isDisplayed, "Created Date should be visible");

		// TC2: Enabled
		boolean isEnabled = createdDate.isEnabled();
		log("Created Date", "Created Date field should be enabled", "true", String.valueOf(isEnabled), isEnabled);

		// TC3: hasDatepicker class
		String classAttr = createdDate.getAttribute("class");
		boolean dpCheck = classAttr != null && classAttr.contains("hasDatepicker");
		log("Created Date", "Created Date should have datepicker class", "hasDatepicker", classAttr, dpCheck);

		// TC4: Placeholder
		String placeholder = createdDate.getAttribute("placeholder");
		log("Created Date", "Placeholder should be 'enter reminder created date'", "enter reminder created date", placeholder, "enter reminder created date".equals(placeholder));

		// TC5: Check if auto-populated or empty
		String val = createdDate.getAttribute("value");
		log("Created Date", "Check Created Date value (auto-populated or empty)", "Date value or empty", "'" + val + "'", true);

		// TC6: Input type
		String inputType = createdDate.getAttribute("type");
		log("Created Date", "Input type should be 'text'", "text", inputType, "text".equals(inputType));

		System.out.println("=================================================");
		System.out.println("RM6 - REMINDER CREATED DATE VALIDATION END");
		System.out.println("=================================================");
	}
}


