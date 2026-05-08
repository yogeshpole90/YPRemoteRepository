package SiteVisitRequest_Package;

import org.openqa.selenium.*;
import org.testng.annotations.Test;
import java.util.List;

public class SV13_SaveButton extends SV2_Login {

	@Test
	public void validateSave() throws Exception
	{
		System.out.println("=================================================");
		System.out.println("SV13 - SAVE BUTTON VALIDATION START");
		System.out.println("=================================================");

		WebElement saveBtn = driver.findElement(By.id("saveData"));

		log("Save Button", "Should be visible", "true", String.valueOf(saveBtn.isDisplayed()), saveBtn.isDisplayed());
		sa.assertTrue(saveBtn.isDisplayed(), "Save button not displayed");

		log("Save Button", "Should be enabled", "true", String.valueOf(saveBtn.isEnabled()), saveBtn.isEnabled());
		sa.assertTrue(saveBtn.isEnabled(), "Save button not enabled");

		// Click Save
		jse.executeScript("arguments[0].scrollIntoView({block:'center'})", saveBtn);
		Thread.sleep(500);
		saveBtn.click();
		Thread.sleep(2000);

		// Switch out of frame to capture toast
		driver.switchTo().parentFrame();
		Thread.sleep(500);
		driver.switchTo().defaultContent();
		Thread.sleep(500);

		// Scroll to TOP of page — toast upar dikhta hai
		jse.executeScript("window.scrollTo({top:0, behavior:'smooth'})");
		Thread.sleep(2000);

		// Capture toast
		String successMsg = "";
		try {
			List<WebElement> st = driver.findElements(By.cssSelector("div.msg-toast.msg-success.msg-showing em"));
			if (!st.isEmpty()) successMsg = st.get(0).getText().trim();
		} catch (Exception e) { }

		String errorMsg = "";
		try {
			List<WebElement> et = driver.findElements(By.cssSelector("div.msg-toast.msg-error.msg-showing em"));
			if (!et.isEmpty()) errorMsg = et.get(0).getText().trim();
		} catch (Exception e) { }

		if (!successMsg.isEmpty()) {
			log("Save Button", "Toast after save", "Success toast", "SUCCESS: " + successMsg, true);
		} else if (!errorMsg.isEmpty()) {
			log("Save Button", "Toast after save", "Success toast", "ERROR: " + errorMsg, false);
		} else {
			log("Save Button", "Toast after save", "Toast message", "No toast captured", false);
		}

		// Wait for page to reload after save
		Thread.sleep(3000);

		System.out.println("=================================================");
		System.out.println("SV13 - SAVE BUTTON VALIDATION END");
		System.out.println("=================================================");
	}
}
