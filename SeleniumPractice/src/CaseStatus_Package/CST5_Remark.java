package CaseStatus_Package;

import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;

public class CST5_Remark extends CST2_Login {



	public void validateRemark() throws Exception
	{
		System.out.println("=================================================");
		System.out.println("CST5 - REMARK FIELD VALIDATION START");
		System.out.println("=================================================");

		WebElement remark = driver.findElement(By.id("remarks"));

		// TC1: Remark field should be displayed
		boolean isDisplayed = remark.isDisplayed();
		log("Remark", "Remark textarea should be visible on page", "true", String.valueOf(isDisplayed), isDisplayed);
		sa.assertTrue(isDisplayed, "Remark field should be visible");

		// TC2: Remark field should be enabled
		boolean isEnabled = remark.isEnabled();
		log("Remark", "Remark textarea should be enabled/editable", "true", String.valueOf(isEnabled), isEnabled);
		sa.assertTrue(isEnabled, "Remark field should be enabled");

		// TC3: Remark field should be empty initially
		String initialVal = remark.getAttribute("value");
		boolean emptyCheck = initialVal == null || initialVal.isEmpty();
		log("Remark", "Remark field should be empty initially", "Empty", "'" + initialVal + "'", emptyCheck);
		sa.assertTrue(emptyCheck, "Remark should be empty initially");

		// TC4: Enter text in Remark field
		remark.clear();
		remark.sendKeys("Test Remark for Case Status");
		Thread.sleep(500);
		String enteredVal = remark.getAttribute("value");
		boolean enterCheck = enteredVal.equals("Test Remark for Case Status");
		log("Remark", "Enter text in Remark field", "Test Remark for Case Status", enteredVal, enterCheck);
		sa.assertEquals(enteredVal, "Test Remark for Case Status", "Remark value mismatch");

		// TC5: Clear Remark field
		remark.clear();
		Thread.sleep(500);
		String clearedVal = remark.getAttribute("value");
		boolean clearCheck = clearedVal.isEmpty();
		log("Remark", "Clear Remark field - should become empty", "Empty", "'" + clearedVal + "'", clearCheck);
		sa.assertTrue(clearCheck, "Remark should be empty after clear");

		// TC6: Enter special characters
		remark.clear();
		remark.sendKeys("@#$%^&*()!~");
		Thread.sleep(500);
		String splVal = remark.getAttribute("value");
		boolean splCheck = splVal.equals("@#$%^&*()!~");
		log("Remark", "Enter special characters in Remark", "@#$%^&*()!~", splVal, splCheck);
		sa.assertEquals(splVal, "@#$%^&*()!~", "Special characters should be accepted");

		// TC7: Enter numeric value
		remark.clear();
		remark.sendKeys("1234567890");
		Thread.sleep(500);
		String numVal = remark.getAttribute("value");
		boolean numCheck = numVal.equals("1234567890");
		log("Remark", "Enter numeric value in Remark", "1234567890", numVal, numCheck);
		sa.assertEquals(numVal, "1234567890", "Numeric value should be accepted");

		// TC8: Re-enter valid remark for save
		remark.clear();
		remark.sendKeys("Case Status Remark");
		Thread.sleep(500);
		String finalVal = remark.getAttribute("value");
		boolean finalCheck = finalVal.equals("Case Status Remark");
		log("Remark", "Re-enter valid remark for further tests", "Case Status Remark", finalVal, finalCheck);
		sa.assertEquals(finalVal, "Case Status Remark", "Final remark value mismatch");

		System.out.println("=================================================");
		System.out.println("CST5 - REMARK FIELD VALIDATION END");
		System.out.println("=================================================");
	}
}


