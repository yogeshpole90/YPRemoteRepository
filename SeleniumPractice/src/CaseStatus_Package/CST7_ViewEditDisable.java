package CaseStatus_Package;

import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import java.util.List;

public class CST7_ViewEditDisable extends CST2_Login {

	public void validateViewEditDisable() throws Exception
	{
		System.out.println("=================================================");
		System.out.println("CST7 - VIEW / EDIT / DELETE VALIDATION START");
		System.out.println("=================================================");

		// ==================== VIEW ====================

		int viewCount = driver.findElements(By.xpath("//a[contains(@onclick,'ViewData')]")).size();
		log("View Button", "Total View buttons", ">0", String.valueOf(viewCount), viewCount > 0);
		sa.assertTrue(viewCount > 0, "No View buttons found");

		if (viewCount > 0) {
			WebElement lastView = driver.findElement(By.xpath("(//a[contains(@onclick,'ViewData')])[last()]"));
			log("View Button", "Displayed", "true", String.valueOf(lastView.isDisplayed()), lastView.isDisplayed());
			log("View Button", "Enabled", "true", String.valueOf(lastView.isEnabled()), lastView.isEnabled());
			log("View Button", "Text", "View", lastView.getText().trim(), lastView.getText().trim().equals("View"));

			lastView.click();
			Thread.sleep(2000);
			log("View Button", "Clicked last View", "View opened", "Clicked", true);

			// Verify data in View
			verifyFieldHasData("caseStatusId", "Case Status DD");
			verifyFieldHasData("remarks", "Remarks");

			// Check read-only
			try {
				WebElement rmk = driver.findElement(By.id("remarks"));
				boolean isReadOnly = rmk.getAttribute("readonly") != null || rmk.getAttribute("disabled") != null || !rmk.isEnabled();
				log("View Mode", "Fields should be read-only", "Read-only", String.valueOf(isReadOnly), isReadOnly);
			} catch (Exception e) { }

			scrollToSearch();
		}

		// ==================== EDIT + UPDATE ====================

		int editCount = driver.findElements(By.cssSelector("a.editBtn")).size();
		log("Edit Button", "Total Edit buttons", ">0", String.valueOf(editCount), editCount > 0);
		sa.assertTrue(editCount > 0, "No Edit buttons found");

		if (editCount > 0) {
			WebElement lastEdit = driver.findElement(By.xpath("(//a[contains(@class,'editBtn')])[last()]"));
			log("Edit Button", "Displayed", "true", String.valueOf(lastEdit.isDisplayed()), lastEdit.isDisplayed());
			log("Edit Button", "Enabled", "true", String.valueOf(lastEdit.isEnabled()), lastEdit.isEnabled());

			lastEdit.click();
			Thread.sleep(2000);
			log("Edit Button", "Clicked last Edit", "Edit mode opened", "Clicked", true);

			// Modify Remark
			WebElement remark = driver.findElement(By.id("remarks"));
			String oldRemark = remark.getAttribute("value");
			remark.clear();
			remark.sendKeys("Updated Remark via Edit");
			Thread.sleep(500);
			log("Edit → Remarks", "Modify remark", "Updated Remark via Edit", "Old='" + oldRemark + "' → New='Updated Remark via Edit'", true);

			// Click Save
			WebElement saveBtn = driver.findElement(By.id("saveData"));
			log("Save Button", "Displayed", "true", String.valueOf(saveBtn.isDisplayed()), saveBtn.isDisplayed());
			log("Save Button", "Enabled", "true", String.valueOf(saveBtn.isEnabled()), saveBtn.isEnabled());
			saveBtn.click();
			Thread.sleep(1000);

			// Scroll to search → capture toast
			scrollToSearch();
			Thread.sleep(1000);

			String updateToast = getSuccessToastMsg();
			log("Update Toast", "Toast after update", "Success toast", updateToast.isEmpty() ? "No toast" : updateToast, !updateToast.isEmpty());

			Thread.sleep(2000);
		}

		// ==================== VIEW AFTER EDIT ====================

		int viewCount2 = driver.findElements(By.xpath("//a[contains(@onclick,'ViewData')]")).size();

		if (viewCount2 > 0) {
			WebElement lastView2 = driver.findElement(By.xpath("(//a[contains(@onclick,'ViewData')])[last()]"));
			lastView2.click();
			Thread.sleep(2000);
			log("View After Edit", "Clicked View to verify", "View opened", "Clicked", true);

			try {
				WebElement rmk = driver.findElement(By.id("remarks"));
				String val = rmk.getAttribute("value");
				if (val == null || val.isEmpty()) val = rmk.getText();
				boolean match = val != null && val.contains("Updated Remark via Edit");
				log("View → Remarks (After Edit)", "Should show updated value", "Updated Remark via Edit", "'" + val + "'", match);
				sa.assertTrue(match, "Remarks not updated in View");
			} catch (Exception e) {
				log("View → Remarks (After Edit)", "Remarks field", "Found", "NOT FOUND", false);
			}

			scrollToSearch();
		}

		// ==================== DELETE ====================

		int beforeCount = driver.findElements(By.cssSelector("a.deleteBtn")).size();
		log("Delete Button", "Total Delete buttons", ">0", String.valueOf(beforeCount), beforeCount > 0);
		sa.assertTrue(beforeCount > 0, "No Delete buttons found");

		if (beforeCount > 0) {
			WebElement lastDelete = driver.findElement(By.xpath("(//a[contains(@class,'deleteBtn')])[last()]"));
			log("Delete Button", "Displayed", "true", String.valueOf(lastDelete.isDisplayed()), lastDelete.isDisplayed());
			log("Delete Button", "Enabled", "true", String.valueOf(lastDelete.isEnabled()), lastDelete.isEnabled());

			lastDelete.click();
			Thread.sleep(1000);
			log("Delete Button", "Clicked last Delete", "Popup should appear", "Clicked", true);

			// Popup — Yes / No
			WebElement yesBtn = driver.findElement(By.id("popUpYes"));
			log("Delete Popup", "Yes button visible", "true", String.valueOf(yesBtn.isDisplayed()), yesBtn.isDisplayed());
			log("Delete Popup", "Yes button enabled", "true", String.valueOf(yesBtn.isEnabled()), yesBtn.isEnabled());

			try {
				WebElement noBtn = driver.findElement(By.xpath("//a[contains(@onclick,'DeleteDataCancelled')]"));
				log("Delete Popup", "No button visible", "true", String.valueOf(noBtn.isDisplayed()), noBtn.isDisplayed());
			} catch (Exception e) { }

			yesBtn.click();
			Thread.sleep(1000);

			// Scroll to search → capture toast
			scrollToSearch();
			Thread.sleep(1000);

			String deleteToast = getSuccessToastMsg();
			log("Delete Toast", "Toast after delete", "Success toast", deleteToast.isEmpty() ? "No toast" : deleteToast, !deleteToast.isEmpty());

			// Verify record count decreased
			Thread.sleep(2000);
			int afterCount = driver.findElements(By.cssSelector("a.deleteBtn")).size();
			log("Delete Verify", "Record count decreased", String.valueOf(beforeCount - 1), String.valueOf(afterCount), afterCount < beforeCount);
		}

		System.out.println("=================================================");
		System.out.println("CST7 - VIEW / EDIT / DELETE VALIDATION END");
		System.out.println("=================================================");
	}

	private void verifyFieldHasData(String id, String fieldName) {
		try {
			WebElement f = driver.findElement(By.id(id));
			String val = f.getAttribute("value");
			if (val == null || val.isEmpty()) val = f.getText();
			boolean hasData = val != null && !val.trim().isEmpty();
			log("View → " + fieldName, "Should have data", "Non-empty", "'" + val + "'", hasData);
		} catch (Exception e) {
			log("View → " + fieldName, "Field should exist", "Found", "NOT FOUND", false);
		}
	}

	private void scrollToSearch() {
		try {
			WebElement search = driver.findElement(By.xpath("//input[@type='search' and @aria-controls='dt-basicDetails']"));
			jse.executeScript("arguments[0].scrollIntoView({block:'center',behavior:'smooth'})", search);
			Thread.sleep(1000);
		} catch (Exception e) { }
	}
}
