package Reminder_Package;

import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;

public class RM7_Remark extends RM2_Login {



	public void validateRemark() throws Exception
	{
		System.out.println("=================================================");
		System.out.println("RM7 - REMARK FIELD VALIDATION START");
		System.out.println("=================================================");

		WebElement remark = driver.findElement(By.id("remarks"));

		// TC1: Visible
		boolean isDisplayed = remark.isDisplayed();
		log("Remark", "Remark textarea should be visible on page", "true", String.valueOf(isDisplayed), isDisplayed);
		sa.assertTrue(isDisplayed, "Remark should be visible");

		// TC2: Enabled
		boolean isEnabled = remark.isEnabled();
		log("Remark", "Remark textarea should be enabled/editable", "true", String.valueOf(isEnabled), isEnabled);
		sa.assertTrue(isEnabled, "Remark should be enabled");

		// TC3: Empty initially
		String initialVal = remark.getAttribute("value");
		boolean emptyCheck = initialVal == null || initialVal.isEmpty();
		log("Remark", "Remark should be empty initially", "Empty", "'" + initialVal + "'", emptyCheck);

		// TC4: Enter text
		remark.clear();
		remark.sendKeys("Test Reminder Remark");
		Thread.sleep(200);
		String enteredVal = remark.getAttribute("value");
		log("Remark", "Enter text 'Test Reminder Remark'", "Test Reminder Remark", enteredVal, enteredVal.equals("Test Reminder Remark"));
		sa.assertEquals(enteredVal, "Test Reminder Remark", "Remark value mismatch");

		// TC5: Clear
		remark.clear();
		Thread.sleep(200);
		String clearedVal = remark.getAttribute("value");
		log("Remark", "Clear Remark field", "Empty", "'" + clearedVal + "'", clearedVal.isEmpty());

		// TC6: Special characters
		remark.clear();
		remark.sendKeys("@#$%^&*()!~");
		Thread.sleep(200);
		String splVal = remark.getAttribute("value");
		log("Remark", "Enter special characters '@#$%^&*()!~'", "@#$%^&*()!~", splVal, splVal.equals("@#$%^&*()!~"));

		// TC7: Numeric
		remark.clear();
		remark.sendKeys("1234567890");
		Thread.sleep(200);
		String numVal = remark.getAttribute("value");
		log("Remark", "Enter numeric value '1234567890'", "1234567890", numVal, numVal.equals("1234567890"));

		// TC8: Re-enter for save
		remark.clear();
		remark.sendKeys("Reminder Remark for Save");
		Thread.sleep(200);
		log("Remark", "Re-enter valid remark for save", "Reminder Remark for Save", remark.getAttribute("value"), true);

		System.out.println("=================================================");
		System.out.println("RM7 - REMARK FIELD VALIDATION END");
		System.out.println("=================================================");
	}
}


