package CaseStatus_Package;

import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.Select;

public class CST6_ResetSave extends CST2_Login {

	public void validateResetSave() throws Exception
	{
		System.out.println("=================================================");
		System.out.println("CST6 - RESET & SAVE BUTTON VALIDATION START");
		System.out.println("=================================================");

		// ==================== SAVE BUTTON ====================

		WebElement saveBtn = driver.findElement(By.id("saveData"));
		log("Save Button", "Should be visible", "true", String.valueOf(saveBtn.isDisplayed()), saveBtn.isDisplayed());
		sa.assertTrue(saveBtn.isDisplayed(), "Save button should be visible");

		log("Save Button", "Should be enabled", "true", String.valueOf(saveBtn.isEnabled()), saveBtn.isEnabled());
		sa.assertTrue(saveBtn.isEnabled(), "Save button should be enabled");

		// TC1: Save without data — mandatory check
		saveBtn.click();
		Thread.sleep(1000);
		String errorToast = getToastMsg();
		log("Save Button", "Save without data - mandatory check", "Please Fill Required Details", errorToast, errorToast.equals("Please Fill Required Details"));
		sa.assertEquals(errorToast, "Please Fill Required Details", "Mandatory toast mismatch");

		// TC2: Save with only DD selected (no remark)
		Thread.sleep(1000);
		new Select(driver.findElement(By.id("caseStatusId"))).selectByValue("LGL");
		Thread.sleep(500);
		driver.findElement(By.id("remarks")).clear();
		Thread.sleep(300);
		driver.findElement(By.id("saveData")).click();
		Thread.sleep(1000);
		String ddOnlyToast = getToastMsg();
		log("Save Button", "Save with only DD, Remark empty", "Error toast", ddOnlyToast.isEmpty() ? "No toast" : ddOnlyToast, !ddOnlyToast.isEmpty());

		// TC3: Save with only remark (no DD)
		Thread.sleep(1000);
		new Select(driver.findElement(By.id("caseStatusId"))).selectByIndex(0);
		Thread.sleep(500);
		driver.findElement(By.id("remarks")).clear();
		driver.findElement(By.id("remarks")).sendKeys("Test Remark Only");
		Thread.sleep(300);
		driver.findElement(By.id("saveData")).click();
		Thread.sleep(1000);
		String remarkOnlyToast = getToastMsg();
		log("Save Button", "Save with only Remark, DD not selected", "Error toast", remarkOnlyToast.isEmpty() ? "No toast" : remarkOnlyToast, !remarkOnlyToast.isEmpty());

		// TC4: Save with valid data
		Thread.sleep(1000);
		new Select(driver.findElement(By.id("caseStatusId"))).selectByValue("LGL");
		Thread.sleep(500);
		driver.findElement(By.id("remarks")).clear();
		driver.findElement(By.id("remarks")).sendKeys("Case Status Remark - Valid Save");
		Thread.sleep(500);
		driver.findElement(By.id("saveData")).click();
		Thread.sleep(1000);

		// Search field frame ke andar hai — scroll to search → toast capture
		try {
			WebElement search = driver.findElement(By.xpath("//input[@type='search' and @aria-controls='dt-basicDetails']"));
			jse.executeScript("arguments[0].scrollIntoView({block:'center',behavior:'smooth'})", search);
			Thread.sleep(1000);
		} catch (Exception e) { }

		String successToast = getSuccessToastMsg();
		log("Save Button", "Save with valid data", "Success toast", successToast.isEmpty() ? "No toast" : successToast, !successToast.isEmpty());

		System.out.println("=================================================");
		System.out.println("CST6 - RESET & SAVE BUTTON VALIDATION END");
		System.out.println("=================================================");
	}
}
