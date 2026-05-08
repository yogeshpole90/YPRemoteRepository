package Courtcase_Package;

import java.util.List;
import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;

public class CC15_SaveViewDisable extends CC2_Login {

	public void validateSaveViewDisable() throws Exception
	{
		System.out.println("=================================================");
		System.out.println("CC15 - SAVE / VIEW / EDIT / DELETE VALIDATION START");
		System.out.println("=================================================");

		// ==================== SAVE ====================

		WebElement saveBtn = driver.findElement(By.xpath("//button[@id='save']"));
		jse.executeScript("arguments[0].scrollIntoView({block:'center'})", saveBtn);
		Thread.sleep(500);

		log("Save Button", "Displayed", "true", String.valueOf(saveBtn.isDisplayed()), saveBtn.isDisplayed());
		log("Save Button", "Enabled", "true", String.valueOf(saveBtn.isEnabled()), saveBtn.isEnabled());

		saveBtn.click();
		Thread.sleep(1000);

		// Scroll to search → toast capture
		scrollToSearch();
		Thread.sleep(1000);
		String saveToast = getSuccessToastMsg();
		log("Save Toast", "Toast after save", "Success toast", saveToast.isEmpty() ? "No toast" : saveToast, !saveToast.isEmpty());

		// Alert check
		try {
			String alertText = driver.switchTo().alert().getText();
			log("Save Alert", "Alert after save", "Alert text", alertText, true);
			driver.switchTo().alert().accept();
			Thread.sleep(1000);
		} catch (Exception e) { }

		// Switch to table frame
		Thread.sleep(2000);
		driver.switchTo().parentFrame();
		WebElement legalTab = driver.findElement(By.xpath("//*[contains(@href,'=Legal Process')]"));
		jse.executeScript("arguments[0].scrollIntoView({block:'center'})", legalTab);
		driver.switchTo().frame("courtCaseMstListPageFrame");
		Thread.sleep(1000);

		// ==================== VIEW ====================

		int viewCount = driver.findElements(By.cssSelector("a.ViewBtn")).size();
		log("View Button", "Total View buttons", ">0", String.valueOf(viewCount), viewCount > 0);
		sa.assertTrue(viewCount > 0, "No View buttons found");

		if (viewCount > 0) {
			WebElement lastView = driver.findElement(By.xpath("(//a[contains(@class,'ViewBtn')])[last()]"));
			lastView.click();
			Thread.sleep(2000);
			log("View Button", "Clicked last View", "View opened", "Clicked", true);

			verifyField("courtLevel", "Court Level");
			verifyField("courtCaseType", "Court Case Type");
			verifyField("currency", "Currency");
			verifyField("filingNumber", "Filing Number");
			verifyField("suitAmount_txt", "Suit Amount");
			verifyField("requestDate", "Request Date");
			verifyField("lawFirmName", "Law Firm Name");
			verifyField("lawyerName", "Lawyer Name");
			verifyField("dcHandleLawyerDate", "Lawyer Engagement Date");
			verifyField("caseInitiatedBy", "Case Initiated By");
			verifyField("allocatedDate", "Allocated Date");

			// Back to table
			driver.switchTo().parentFrame();
			jse.executeScript("arguments[0].scrollIntoView({block:'center'})", legalTab);
			driver.switchTo().frame("courtCaseMstListPageFrame");
			Thread.sleep(1000);
		}

		// ==================== EDIT + UPDATE ====================

		int editCount = driver.findElements(By.cssSelector("a.editBtn")).size();
		log("Edit Button", "Total Edit buttons", ">0", String.valueOf(editCount), editCount > 0);
		sa.assertTrue(editCount > 0, "No Edit buttons found");

		if (editCount > 0) {
			WebElement lastEdit = driver.findElement(By.xpath("(//a[contains(@class,'editBtn')])[last()]"));
			lastEdit.click();
			Thread.sleep(2000);
			log("Edit Button", "Clicked last Edit", "Edit mode opened", "Clicked", true);

			// Modify Suit Amount
			try {
				WebElement suitAmt = driver.findElement(By.id("suitAmount_txt"));
				jse.executeScript("arguments[0].scrollIntoView({block:'center'})", suitAmt);
				String oldVal = suitAmt.getAttribute("value");
				jse.executeScript("arguments[0].value='99999'", suitAmt);
				Thread.sleep(300);
				log("Edit → Suit Amount", "Modify value", "99999", "Old='" + oldVal + "' → New='99999'", true);
			} catch (Exception e) {
				log("Edit → Suit Amount", "Modify value", "Accessible", "ERROR: " + e.getMessage(), false);
			}

			// Click Save
			WebElement updateBtn = driver.findElement(By.xpath("//button[@id='save']"));
			jse.executeScript("arguments[0].scrollIntoView({block:'center'})", updateBtn);
			Thread.sleep(500);
			updateBtn.click();
			Thread.sleep(1000);

			// Scroll to search → toast capture
			scrollToSearch();
			Thread.sleep(1000);
			String updateToast = getSuccessToastMsg();
			log("Update Toast", "Toast after update", "Success toast", updateToast.isEmpty() ? "No toast" : updateToast, !updateToast.isEmpty());

			try {
				String alertText = driver.switchTo().alert().getText();
				log("Update Alert", "Alert after update", "Alert text", alertText, true);
				driver.switchTo().alert().accept();
				Thread.sleep(1000);
			} catch (Exception e) { }

			// Back to table
			Thread.sleep(2000);
			driver.switchTo().parentFrame();
			jse.executeScript("arguments[0].scrollIntoView({block:'center'})", legalTab);
			driver.switchTo().frame("courtCaseMstListPageFrame");
			Thread.sleep(1000);
		}

		// ==================== DELETE ====================

		int beforeCount = driver.findElements(By.cssSelector("a.deleteBtn")).size();
		log("Delete Button", "Total Delete buttons", ">0", String.valueOf(beforeCount), beforeCount > 0);
		sa.assertTrue(beforeCount > 0, "No Delete buttons found");

		if (beforeCount > 0) {
			WebElement lastDelete = driver.findElement(By.xpath("(//a[contains(@class,'deleteBtn')])[last()]"));
			lastDelete.click();
			Thread.sleep(1000);
			log("Delete Button", "Clicked last Delete", "Popup should appear", "Clicked", true);

			// Popup — Yes
			try {
				WebElement yesBtn = driver.findElement(By.id("popUpYes"));
				log("Delete Popup", "Yes button visible", "true", String.valueOf(yesBtn.isDisplayed()), yesBtn.isDisplayed());
				yesBtn.click();
				Thread.sleep(1000);

				// Scroll to search → toast capture
				scrollToSearch();
				Thread.sleep(1000);
				String deleteToast = getSuccessToastMsg();
				log("Delete Toast", "Toast after delete", "Success toast", deleteToast.isEmpty() ? "No toast" : deleteToast, !deleteToast.isEmpty());

				log("Delete Popup", "Clicked Yes", "Record deleted", "Yes clicked", true);
			} catch (Exception e) {
				try {
					driver.switchTo().alert().accept();
					Thread.sleep(2000);
					log("Delete Confirm", "Alert accepted", "Deleted", "Alert accepted", true);
				} catch (Exception e2) {
					log("Delete Confirm", "No confirmation", "Popup/Alert", "Neither found", false);
				}
			}

			// Verify count decreased
			Thread.sleep(2000);
			int afterCount = driver.findElements(By.cssSelector("a.deleteBtn")).size();
			log("Delete Verify", "Record count decreased", String.valueOf(beforeCount - 1), String.valueOf(afterCount), afterCount < beforeCount);
		}

		System.out.println("=================================================");
		System.out.println("CC15 - SAVE / VIEW / EDIT / DELETE VALIDATION END");
		System.out.println("=================================================");
	}

	private void verifyField(String id, String fieldName) {
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
