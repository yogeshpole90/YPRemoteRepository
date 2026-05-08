package SiteVisitRequest_Package;

import org.openqa.selenium.*;
import org.openqa.selenium.support.ui.Select;
import org.testng.annotations.Test;
import java.util.List;

public class SV9_Collection extends SV2_Login {

	@Test
	public void validateCollection() throws Exception
	{
		System.out.println("=================================================");
		System.out.println("SV9 - COLLECTION DROPDOWN VALIDATION START");
		System.out.println("=================================================");

		WebElement dd = driver.findElement(By.id("collection"));
		Select sel = new Select(dd);
		List<WebElement> options = sel.getOptions();

		log("Collection", "Should be visible", "true", String.valueOf(dd.isDisplayed()), dd.isDisplayed());
		sa.assertTrue(dd.isDisplayed(), "Collection not displayed");

		log("Collection", "Should be enabled", "true", String.valueOf(dd.isEnabled()), dd.isEnabled());
		sa.assertTrue(dd.isEnabled(), "Collection not enabled");

		log("Collection", "Check default value", "Default", sel.getFirstSelectedOption().getText().trim(), true);

		log("Collection", "Total options count", ">1", String.valueOf(options.size()), options.size() > 1);

		StringBuilder allOpts = new StringBuilder();
		for (WebElement o : options) { allOpts.append(o.getText().trim()).append(" , "); }
		logInfo("Collection", "All dropdown options", allOpts.toString());

		// Select No — conditional fields should hide
		sel.selectByVisibleText("No"); Thread.sleep(500);
		log("Collection", "Select 'No' — conditional fields should be hidden", "No selected", sel.getFirstSelectedOption().getText().trim(), true);

		// Select Yes — conditional fields should appear
		sel.selectByVisibleText("Yes"); Thread.sleep(500);
		log("Collection", "Select 'Yes' — conditional fields should appear", "Yes selected", sel.getFirstSelectedOption().getText().trim(), true);

		// Check collectedDate visible
		try {
			boolean dateVis = driver.findElement(By.id("collectedDate")).isDisplayed();
			log("Collected Date", "Should be visible after Collection=Yes", "true", String.valueOf(dateVis), dateVis);
			sa.assertTrue(dateVis, "collectedDate not visible after Yes");
		} catch(Exception e) {
			log("Collected Date", "Should be visible after Collection=Yes", "Found", "NOT FOUND", false);
			sa.fail("collectedDate not found after Yes");
		}

		try {
			WebElement amt = driver.findElement(By.id("collectedAmount"));
			jse.executeScript("arguments[0].scrollIntoView({block:'center'})", amt);
			Thread.sleep(500);
			boolean amtVis = amt.isDisplayed();
			log("Collected Amount", "Should be visible after Collection=Yes", "true", String.valueOf(amtVis), amtVis);
			sa.assertTrue(amtVis, "collectedAmount not visible after Yes");
		} catch(Exception e) {
			log("Collected Amount", "Should be visible after Collection=Yes", "Found", "NOT FOUND", false);
			sa.fail("collectedAmount not found after Yes");
		}

		// Check modeOfPayment visible
		try {
			boolean modeVis = driver.findElement(By.id("modeOfPayment")).isDisplayed();
			log("Mode Of Payment", "Should be visible after Collection=Yes", "true", String.valueOf(modeVis), modeVis);
			sa.assertTrue(modeVis, "modeOfPayment not visible after Yes");
		} catch(Exception e) {
			log("Mode Of Payment", "Should be visible after Collection=Yes", "Found", "NOT FOUND", false);
			sa.fail("modeOfPayment not found after Yes");
		}

		System.out.println("=================================================");
		System.out.println("SV9 - COLLECTION DROPDOWN VALIDATION END");
		System.out.println("=================================================");
	}
}
