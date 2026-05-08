package Reminder_Package;

import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.Select;

public class RM8_ResetSave extends RM2_Login {



	private Select getDD() { return new Select(driver.findElement(By.id("reminderType"))); }
	private WebElement getDate() { return driver.findElement(By.id("reminderDate")); }
	private WebElement getCreatedDate() { return driver.findElement(By.id("reminderCreateDate")); }
	private WebElement getRemark() { return driver.findElement(By.id("remarks")); }
	private void clickSave() throws Exception { driver.findElement(By.id("save")).click(); Thread.sleep(300); }
	private void clickReset() throws Exception { driver.findElement(By.id("reset")).click(); Thread.sleep(300); }

	private void selectCall() throws Exception {
		try {
			selectCall();
		} catch (Exception e) {
			Thread.sleep(500);
			new Select(driver.findElement(By.id("reminderType"))).selectByVisibleText("Call");
		}
	}

	private void selectDefault() throws Exception {
		try {
			selectDefault();
		} catch (Exception e) {
			Thread.sleep(500);
			new Select(driver.findElement(By.id("reminderType"))).selectByIndex(0);
		}
	}

	public void validateResetSave() throws Exception
	{
		System.out.println("=================================================");
		System.out.println("RM8 - RESET & SAVE BUTTON VALIDATION START");
		System.out.println("=================================================");

		// ========== SAVE BUTTON ==========
		System.out.println("========== SAVE BUTTON TESTS ==========");

		// TC1: Save button visible
		boolean saveDisplayed = driver.findElement(By.id("save")).isDisplayed();
		log("Save Button", "Save button should be visible on page", "true", String.valueOf(saveDisplayed), saveDisplayed);
		sa.assertTrue(saveDisplayed, "Save button should be visible");

		// TC2: Save button enabled
		boolean saveEnabled = driver.findElement(By.id("save")).isEnabled();
		log("Save Button", "Save button should be enabled/clickable", "true", String.valueOf(saveEnabled), saveEnabled);
		sa.assertTrue(saveEnabled, "Save button should be enabled");

		// ========== MANDATORY FIELD VALIDATION ==========
		System.out.println("========== MANDATORY FIELD VALIDATION ==========");

		// TC3: ALL fields blank
		selectDefault();
		getDate().clear();
		getRemark().clear();
		Thread.sleep(200);
		clickSave();
		String allBlankToast = getToastMsg();
		log("Mandatory Check", "Save with ALL fields blank", "Mandatory error toast", allBlankToast.isEmpty() ? "No toast" : allBlankToast, !allBlankToast.isEmpty());

		// TC4: Reminder Type BLANK + others filled
		selectDefault();
		getDate().clear(); getDate().sendKeys("17-07-2025");
		getCreatedDate().clear(); getCreatedDate().sendKeys("17-07-2025");
		getRemark().clear(); getRemark().sendKeys("Test Remark");
		Thread.sleep(200);
		clickSave();
		String typeBlankToast = getToastMsg();
		log("Mandatory Check", "Save with Reminder Type BLANK, Date & Created Date & Remark filled", "Reminder Type is required.", typeBlankToast.isEmpty() ? "No toast" : typeBlankToast, typeBlankToast.contains("Reminder Type"));

		// TC5: Reminder Date BLANK + others filled
		selectCall();
		getDate().clear();
		getCreatedDate().clear(); getCreatedDate().sendKeys("17-07-2025");
		getRemark().clear(); getRemark().sendKeys("Test Remark");
		Thread.sleep(200);
		clickSave();
		String dateBlankToast = getToastMsg();
		log("Mandatory Check", "Save with Reminder Date BLANK, Type & Created Date & Remark filled", "Reminder Date is required.", dateBlankToast.isEmpty() ? "No toast" : dateBlankToast, dateBlankToast.contains("Reminder Date"));

		// TC6: Created Date BLANK + others filled
		selectCall();
		getDate().clear(); getDate().sendKeys("17-07-2025");
		getCreatedDate().clear();
		getRemark().clear(); getRemark().sendKeys("Test Remark");
		Thread.sleep(200);
		clickSave();
		String createdBlankToast = getToastMsg();
		String createdSuccessToast = getSuccessToastMsg();
		if (!createdBlankToast.isEmpty()) {
			log("Mandatory Check", "Save with Created Date BLANK - Created Date is mandatory", "Error toast", createdBlankToast, true);
		} else {
			log("Mandatory Check", "Save with Created Date BLANK - Created Date is NOT mandatory (optional field)", "No error toast = Created Date optional", createdSuccessToast.isEmpty() ? "Saved without toast" : createdSuccessToast, true);
		}

		// TC7: Remark BLANK + others filled
		selectCall();
		getDate().clear(); getDate().sendKeys("17-07-2025");
		getCreatedDate().clear(); getCreatedDate().sendKeys("17-07-2025");
		getRemark().clear();
		Thread.sleep(200);
		clickSave();
		String remarkBlankToast = getToastMsg();
		String remarkSuccessToast = getSuccessToastMsg();
		if (!remarkBlankToast.isEmpty()) {
			log("Mandatory Check", "Save with Remark BLANK - Remark is mandatory", "Error toast", remarkBlankToast, true);
		} else {
			log("Mandatory Check", "Save with Remark BLANK - Remark is optional", "Remark is optional", "No error toast - Remark is optional", true);
		}

		// ========== RESET BUTTON ==========
		System.out.println("========== RESET BUTTON TESTS ==========");

		selectCall();
		getDate().clear(); getDate().sendKeys("17-07-2025");
		getCreatedDate().clear(); getCreatedDate().sendKeys("17-07-2025");
		getRemark().clear(); getRemark().sendKeys("Test Reset");
		Thread.sleep(200);

		// TC8: Reset button visible
		boolean resetDisplayed = driver.findElement(By.id("reset")).isDisplayed();
		log("Reset Button", "Reset button should be visible on page", "true", String.valueOf(resetDisplayed), resetDisplayed);
		sa.assertTrue(resetDisplayed, "Reset button should be visible");

		// TC9: Reset button enabled
		boolean resetEnabled = driver.findElement(By.id("reset")).isEnabled();
		log("Reset Button", "Reset button should be enabled/clickable", "true", String.valueOf(resetEnabled), resetEnabled);
		sa.assertTrue(resetEnabled, "Reset button should be enabled");

		// TC10: Click Reset
		clickReset();

		// TC11: Verify dropdown reset
		String ddVal = getDD().getFirstSelectedOption().getText().trim();
		log("Reset Button", "After Reset - dropdown should reset to default", "--SELECT--", ddVal, ddVal.contains("--SELECT"));

		// TC12: Verify date reset
		String dateVal = getDate().getAttribute("value");
		log("Reset Button", "After Reset - date field should be empty", "Empty", "'" + dateVal + "'", dateVal == null || dateVal.isEmpty());

		// TC13: Verify remark reset
		String remarkVal = getRemark().getAttribute("value");
		log("Reset Button", "After Reset - remark should be empty", "Empty", "'" + remarkVal + "'", remarkVal == null || remarkVal.isEmpty());

		// ========== SAVE WITH VALID DATA ==========
		System.out.println("========== SAVE WITH VALID DATA ==========");

		selectCall();
		getDate().clear(); getDate().sendKeys("17-07-2025");
		getCreatedDate().clear(); getCreatedDate().sendKeys("17-07-2025");
		getRemark().clear(); getRemark().sendKeys("Reminder Remark - Valid Save");
		Thread.sleep(200);

		String finalDD = getDD().getFirstSelectedOption().getText().trim();
		String finalDate = getDate().getAttribute("value");
		String finalRemark = getRemark().getAttribute("value");

		clickSave();
		String successToast = getSuccessToastMsg();
		boolean saveSuccess = !successToast.isEmpty();
		log("Save Button", "Save with valid data", "Success toast", successToast.isEmpty() ? "No toast" : successToast, saveSuccess);

		System.out.println("=================================================");
		System.out.println("RM8 - RESET & SAVE BUTTON VALIDATION END");
		System.out.println("=================================================");
	}
}


