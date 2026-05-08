package SiteVisitRequest_Package;

import org.openqa.selenium.*;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.annotations.Test;
import java.util.List;

public class SV14_ViewEditDisable extends SV2_Login {

	String updatedRemarks = "Updated by Selenium " + System.currentTimeMillis() % 100000;

	@Test
	public void validateViewEditDisable() throws Exception
	{
		System.out.println("=================================================");
		System.out.println("SV14 - VIEW / EDIT / UPDATE / DELETE VALIDATION START");
		System.out.println("=================================================");

		// After SV13 save, driver is on defaultContent — page reloaded
		// View/Edit/Delete buttons are on main page (not inside frame)
		// Wait for buttons to load
		Thread.sleep(3000);

		// =================== STEP 1: VIEW LAST RECORD ===================

		// Scroll down to find buttons
		jse.executeScript("window.scrollTo({top: document.body.scrollHeight, behavior:'smooth'})");
		Thread.sleep(2000);

		int viewCount = driver.findElements(By.cssSelector("a.ViewBtn")).size();
		log("View Button", "Total View buttons", ">0", String.valueOf(viewCount), viewCount > 0);

		if (viewCount > 0) {
			WebElement lastView = driver.findElement(By.xpath("(//a[contains(@class,'ViewBtn')])[last()]"));
			jse.executeScript("arguments[0].scrollIntoView({block:'center'})", lastView);
			Thread.sleep(500);

			log("View Button", "Displayed", "true", String.valueOf(lastView.isDisplayed()), lastView.isDisplayed());
			log("View Button", "Enabled", "true", String.valueOf(lastView.isEnabled()), lastView.isEnabled());

			lastView.click();
			Thread.sleep(3000);
			log("View Button", "Clicked last View", "View opened", "Clicked", true);

			// Switch to frame to read view data
			try { driver.switchTo().frame("createSiteVisitDetailsFrame"); } catch (Exception e) { }
			Thread.sleep(1000);

			verifyFieldHasData("visitType", "Visit Type");
			verifyFieldHasData("visitedBy", "Visited By");
			verifyFieldHasData("visitInitiatedt", "Visit Initiated Date");
			verifyFieldHasData("visitDate", "Visit Date");
			verifyFieldHasData("customerResponse", "Customer Response");
			verifyFieldHasData("collection", "Collection");
			verifyFieldHasData("remarks", "Remarks");

			// Check read-only
			try {
				WebElement rmk = driver.findElement(By.id("remarks"));
				boolean isReadOnly = rmk.getAttribute("readonly") != null || rmk.getAttribute("disabled") != null;
				log("View Mode", "Fields should be read-only", "Read-only", String.valueOf(isReadOnly), isReadOnly);
			} catch (Exception e) { }

			// Go back to main page
			driver.switchTo().defaultContent();
			scrollToTop();
			Thread.sleep(2000);
		} else {
			log("View Button", "No View buttons found", "Buttons exist", "0 found — check if page reloaded", false);
		}

		// =================== STEP 2: EDIT LAST RECORD ===================

		jse.executeScript("window.scrollTo({top: document.body.scrollHeight, behavior:'smooth'})");
		Thread.sleep(2000);

		int editCount = driver.findElements(By.cssSelector("a.editBtn")).size();
		log("Edit Button", "Total Edit buttons", ">0", String.valueOf(editCount), editCount > 0);

		if (editCount > 0) {
			WebElement lastEdit = driver.findElement(By.xpath("(//a[contains(@class,'editBtn')])[last()]"));
			jse.executeScript("arguments[0].scrollIntoView({block:'center'})", lastEdit);
			Thread.sleep(500);

			log("Edit Button", "Displayed", "true", String.valueOf(lastEdit.isDisplayed()), lastEdit.isDisplayed());
			log("Edit Button", "Enabled", "true", String.valueOf(lastEdit.isEnabled()), lastEdit.isEnabled());

			lastEdit.click();
			Thread.sleep(3000);
			log("Edit Button", "Clicked last Edit", "Edit mode opened", "Clicked", true);

			// Switch to frame to edit
			try { driver.switchTo().frame("createSiteVisitDetailsFrame"); } catch (Exception e) { }
			Thread.sleep(1000);

			// Modify Remarks
			try {
				WebElement remarks = driver.findElement(By.id("remarks"));
				jse.executeScript("arguments[0].scrollIntoView({block:'center'})", remarks);
				Thread.sleep(500);
				String oldRemarks = remarks.getAttribute("value");
				remarks.clear();
				remarks.sendKeys(updatedRemarks);
				Thread.sleep(300);
				log("Edit → Remarks", "Modify remarks", updatedRemarks, "Old='" + oldRemarks + "' → New='" + updatedRemarks + "'", true);
			} catch (Exception e) {
				log("Edit → Remarks", "Modify remarks", "Accessible", "ERROR: " + e.getMessage(), false);
			}

			// Click Save (Update)
			try {
				WebElement saveBtn = driver.findElement(By.id("saveData"));
				jse.executeScript("arguments[0].scrollIntoView({block:'center'})", saveBtn);
				Thread.sleep(500);
				saveBtn.click();
				Thread.sleep(2000);
				log("Update Save", "Clicked Save after edit", "Save attempted", "Clicked", true);

				// Switch out, scroll to top, capture toast
				driver.switchTo().parentFrame();
				driver.switchTo().defaultContent();
				scrollToTop();
				Thread.sleep(2000);

				String toast = captureToast();
				log("Update Toast", "Toast after update", "Success toast", toast.isEmpty() ? "No toast" : toast, !toast.isEmpty());
			} catch (Exception e) {
				log("Update Save", "Save button", "Found", "ERROR: " + e.getMessage(), false);
			}

			Thread.sleep(3000);
		}

		// =================== STEP 3: VIEW AFTER EDIT ===================

		jse.executeScript("window.scrollTo({top: document.body.scrollHeight, behavior:'smooth'})");
		Thread.sleep(2000);

		int viewCount2 = driver.findElements(By.cssSelector("a.ViewBtn")).size();

		if (viewCount2 > 0) {
			WebElement lastView2 = driver.findElement(By.xpath("(//a[contains(@class,'ViewBtn')])[last()]"));
			jse.executeScript("arguments[0].scrollIntoView({block:'center'})", lastView2);
			Thread.sleep(500);
			lastView2.click();
			Thread.sleep(3000);
			log("View After Edit", "Clicked View to verify", "View opened", "Clicked", true);

			try { driver.switchTo().frame("createSiteVisitDetailsFrame"); } catch (Exception e) { }
			Thread.sleep(1000);

			try {
				WebElement rmk = driver.findElement(By.id("remarks"));
				String viewRemarks = rmk.getAttribute("value");
				if (viewRemarks == null || viewRemarks.isEmpty()) viewRemarks = rmk.getText();
				boolean match = viewRemarks != null && viewRemarks.contains("Updated by Selenium");
				log("View → Remarks (After Edit)", "Should show updated value", updatedRemarks, "'" + viewRemarks + "'", match);
				sa.assertTrue(match, "Remarks not updated in View");
			} catch (Exception e) {
				log("View → Remarks (After Edit)", "Remarks field", "Found", "NOT FOUND", false);
			}

			driver.switchTo().defaultContent();
			scrollToTop();
			Thread.sleep(2000);
		}

		// =================== STEP 4: DELETE LAST RECORD ===================

		jse.executeScript("window.scrollTo({top: document.body.scrollHeight, behavior:'smooth'})");
		Thread.sleep(2000);

		int beforeCount = driver.findElements(By.cssSelector("a.deleteBtn")).size();
		log("Delete Button", "Total Delete buttons", ">0", String.valueOf(beforeCount), beforeCount > 0);

		if (beforeCount > 0) {
			WebElement lastDelete = driver.findElement(By.xpath("(//a[contains(@class,'deleteBtn')])[last()]"));
			jse.executeScript("arguments[0].scrollIntoView({block:'center'})", lastDelete);
			Thread.sleep(500);

			log("Delete Button", "Displayed", "true", String.valueOf(lastDelete.isDisplayed()), lastDelete.isDisplayed());
			log("Delete Button", "Enabled", "true", String.valueOf(lastDelete.isEnabled()), lastDelete.isEnabled());

			lastDelete.click();
			Thread.sleep(1000);
			log("Delete Button", "Clicked last Delete", "Delete triggered", "Clicked", true);

			// Handle popup — popUpYes
			try {
				WebElement yesBtn = driver.findElement(By.id("popUpYes"));
				log("Delete Popup", "Yes button visible", "true", String.valueOf(yesBtn.isDisplayed()), yesBtn.isDisplayed());
				yesBtn.click();
				Thread.sleep(2000);
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

			// Scroll to top → capture toast
			scrollToTop();
			Thread.sleep(2000);

			String deleteToast = captureToast();
			log("Delete Toast", "Toast after delete", "Success toast", deleteToast.isEmpty() ? "No toast" : deleteToast, true);

			// Verify record count decreased
			Thread.sleep(2000);
			int afterCount = driver.findElements(By.cssSelector("a.deleteBtn")).size();
			log("Delete Verify", "Record count decreased", String.valueOf(beforeCount - 1), String.valueOf(afterCount), afterCount < beforeCount);
		}

		System.out.println("=================================================");
		System.out.println("SV14 - VIEW / EDIT / UPDATE / DELETE VALIDATION END");
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

	private void scrollToTop() {
		jse.executeScript("window.scrollTo({top:0, behavior:'smooth'})");
	}

	private String captureToast() {
		try {
			List<WebElement> s = driver.findElements(By.cssSelector("div.msg-toast.msg-success.msg-showing em"));
			if (!s.isEmpty()) return "SUCCESS: " + s.get(0).getText().trim();
		} catch (Exception e) { }
		try {
			List<WebElement> er = driver.findElements(By.cssSelector("div.msg-toast.msg-error.msg-showing em"));
			if (!er.isEmpty()) return "ERROR: " + er.get(0).getText().trim();
		} catch (Exception e) { }
		return "";
	}
}
