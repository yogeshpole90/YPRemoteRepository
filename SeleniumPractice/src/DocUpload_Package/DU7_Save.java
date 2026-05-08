package DocUpload_Package;

import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.Select;

public class DU7_Save extends DU2_Login {

	public void validateSave() throws Exception
	{
		System.out.println("=================================================");
		System.out.println("DU7 - SAVE BUTTON VALIDATION START");
		System.out.println("=================================================");

		WebElement saveBtn = driver.findElement(By.id("saveData"));

		boolean saveDisplayed = saveBtn.isDisplayed();
		log("Save Button", "Should be visible", "true", String.valueOf(saveDisplayed), saveDisplayed);
		sa.assertTrue(saveDisplayed, "Save button should be visible");

		boolean saveEnabled = saveBtn.isEnabled();
		log("Save Button", "Should be enabled", "true", String.valueOf(saveEnabled), saveEnabled);
		sa.assertTrue(saveEnabled, "Save button should be enabled");

		saveBtn.click();
		Thread.sleep(1000);
		String errorToast = getToastMsg();
		boolean mandatoryCheck = errorToast.equals("Please Fill Required Details");
		log("Save Button", "Save without data - mandatory check", "Please Fill Required Details", errorToast.isEmpty() ? "No toast" : errorToast, mandatoryCheck);
		sa.assertEquals(errorToast, "Please Fill Required Details", "Mandatory toast message mismatch");

		// TC4: Save with only Action Name selected
		WebElement dropdown = driver.findElement(By.id("actionName"));
		Select select = new Select(dropdown);
		select.selectByVisibleText("Asset Repossession");
		Thread.sleep(500);
		saveBtn = driver.findElement(By.id("saveData"));
		saveBtn.click();
		Thread.sleep(1000);
		String ddOnlyToast = getToastMsg();
		log("Save Button", "Save with only DD selected", "Error toast", ddOnlyToast.isEmpty() ? "No toast" : ddOnlyToast, !ddOnlyToast.isEmpty());

		// TC5: Save with valid data - all fields filled (re-find elements to avoid stale reference)
		WebElement dropdown2 = driver.findElement(By.id("actionName"));
		Select select2 = new Select(dropdown2);
		select2.selectByVisibleText("Asset Repossession");
		Thread.sleep(500);

		WebElement docName = driver.findElement(By.id("documentName"));
		docName.clear();
		docName.sendKeys("Doc of Repossession");
		Thread.sleep(500);

		WebElement uploadField = driver.findElement(By.id("documentData"));
		uploadField.sendKeys("C:\\Users\\Yogesh.Pole\\Music\\COLLATERAL_SEIZED_LETTER.pdf");
		Thread.sleep(500);

		String finalDD = select2.getFirstSelectedOption().getText().trim();
		String finalDocName = docName.getAttribute("value");

		saveBtn = driver.findElement(By.id("saveData"));
		saveBtn.click();
		Thread.sleep(2000);

		String successToast = getSuccessToastMsg();
		boolean saveSuccess = !successToast.isEmpty();
		log("Save Button", "Save with valid data", "Success toast", successToast.isEmpty() ? "No toast" : successToast, saveSuccess);

		System.out.println("=================================================");
		System.out.println("DU7 - SAVE BUTTON VALIDATION END");
		System.out.println("=================================================");
	}
}
