package DemandLetter_Package;

import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import java.util.List;

public class DL9_ViewEditDelete extends DL2_Login {

	public void validateViewEditDelete() throws Exception
	{
		System.out.println("=================================================");
		System.out.println("DL9 - VIEW / EDIT / DELETE VALIDATION START");
		System.out.println("=================================================");

		// ==================== VIEW ====================

		int viewCount = driver.findElements(By.cssSelector("a.ViewBtn")).size();
		log("View Button", "Total View buttons", ">0", String.valueOf(viewCount), viewCount > 0);
		sa.assertTrue(viewCount > 0, "No View buttons found");

		if (viewCount > 0) {
			WebElement lastView = driver.findElement(By.xpath("(//a[contains(@class,'ViewBtn')])[last()]"));
			lastView.click();
			Thread.sleep(2000);
			log("View Button", "Clicked last View", "View opened", "Clicked", true);

			verifyField("caseNo", "Case No");
			verifyField("demandLetterType", "Notice Type");
			verifyField("sendingDate", "Issuance Date");
			verifyField("userName", "User Name");
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

			// Modify User Name
			WebElement userField = driver.findElement(By.id("userName"));
			String oldVal = userField.getAttribute("value");
			userField.clear();
			userField.sendKeys("UpdatedUser");
			Thread.sleep(300);
			log("Edit → User Name", "Modify value", "UpdatedUser", "Old='" + oldVal + "' → New='UpdatedUser'", true);

			// Save
			driver.findElement(By.xpath("//button[contains(text(),'Save')]")).click();
			Thread.sleep(2000);

			String updateToast = getSuccessToastMsg();
			if (updateToast.isEmpty()) updateToast = getToastMsg();
			log("Update Toast", "Toast after update", "Success toast", updateToast.isEmpty() ? "No toast" : updateToast, !updateToast.isEmpty());
			Thread.sleep(2000);
		}

		// ==================== VIEW AFTER EDIT ====================

		int viewCount2 = driver.findElements(By.cssSelector("a.ViewBtn")).size();

		if (viewCount2 > 0) {
			WebElement lastView2 = driver.findElement(By.xpath("(//a[contains(@class,'ViewBtn')])[last()]"));
			lastView2.click();
			Thread.sleep(2000);
			log("View After Edit", "Clicked View to verify", "View opened", "Clicked", true);

			try {
				WebElement un = driver.findElement(By.id("userName"));
				String val = un.getAttribute("value");
				boolean match = val != null && val.contains("UpdatedUser");
				log("View → User Name (After Edit)", "Should show updated value", "UpdatedUser", "'" + val + "'", match);
			} catch (Exception e) {
				log("View → User Name (After Edit)", "Field", "Found", "NOT FOUND", false);
			}
		}

		// ==================== DELETE ====================

		int beforeCount = driver.findElements(By.cssSelector("a.deleteBtn")).size();
		log("Delete Button", "Total Delete buttons", ">0", String.valueOf(beforeCount), beforeCount > 0);
		sa.assertTrue(beforeCount > 0, "No Delete buttons found");

		if (beforeCount > 0) {
			WebElement lastDelete = driver.findElement(By.xpath("(//a[contains(@class,'deleteBtn')])[last()]"));
			lastDelete.click();
			Thread.sleep(2000);

			String deleteToast = getSuccessToastMsg();
			if (deleteToast.isEmpty()) deleteToast = getToastMsg();
			log("Delete Toast", "Toast after delete", "Success toast", deleteToast.isEmpty() ? "No toast" : deleteToast, !deleteToast.isEmpty());

			Thread.sleep(2000);
			int afterCount = driver.findElements(By.cssSelector("a.deleteBtn")).size();
			log("Delete Verify", "Record count decreased", String.valueOf(beforeCount - 1), String.valueOf(afterCount), afterCount < beforeCount);
		}

		System.out.println("=================================================");
		System.out.println("DL9 - VIEW / EDIT / DELETE VALIDATION END");
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
}
