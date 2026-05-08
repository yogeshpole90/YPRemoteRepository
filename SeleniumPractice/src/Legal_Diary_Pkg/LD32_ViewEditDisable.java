package Legal_Diary_Pkg;

import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import java.util.List;

public class LD32_ViewEditDisable extends LD2_Login {

	public void validateViewEditDisable() throws Exception {

		String viewXpath = "//a[contains(@class,'ViewBtn')]";
		String editXpath = "//a[contains(@class,'editBtn')]";
		String deleteXpath = "//a[contains(@class,'deleteBtn')]";

		// VIEW
		List<WebElement> viewBtns = driver.findElements(By.xpath(viewXpath));
		logInfo("View Button", "Total View Buttons found", String.valueOf(viewBtns.size()));

		if (!viewBtns.isEmpty()) {
			WebElement lastView = viewBtns.get(viewBtns.size() - 1);
			log("View Button", "Last View button visible", "true", String.valueOf(lastView.isDisplayed()), lastView.isDisplayed());
			sa.assertTrue(lastView.isDisplayed());
			log("View Button", "Last View button enabled", "true", String.valueOf(lastView.isEnabled()), lastView.isEnabled());
			sa.assertTrue(lastView.isEnabled());
			lastView.click(); Thread.sleep(2000);
			log("View Button", "Click last View button", "View opened", "Clicked record " + viewBtns.size(), true);
			try {
				WebElement closeBtn = driver.findElement(By.xpath("//button[contains(@class,'close') or contains(text(),'Close') or contains(text(),'Back')]"));
				closeBtn.click(); Thread.sleep(1000);
				log("View Button", "Close View popup", "Closed", "Closed", true);
			} catch (Exception e) {
				logInfo("View Button", "Close button", "Not found, continuing...");
			}
		}

		// EDIT
		List<WebElement> editBtns = driver.findElements(By.xpath(editXpath));
		logInfo("Edit Button", "Total Edit Buttons found", String.valueOf(editBtns.size()));

		if (!editBtns.isEmpty()) {
			WebElement lastEdit = editBtns.get(editBtns.size() - 1);
			log("Edit Button", "Last Edit button visible", "true", String.valueOf(lastEdit.isDisplayed()), lastEdit.isDisplayed());
			sa.assertTrue(lastEdit.isDisplayed());
			log("Edit Button", "Last Edit button enabled", "true", String.valueOf(lastEdit.isEnabled()), lastEdit.isEnabled());
			sa.assertTrue(lastEdit.isEnabled());
			lastEdit.click(); Thread.sleep(2000);
			log("Edit Button", "Click last Edit button", "Edit opened", "Clicked record " + editBtns.size(), true);
			try {
				WebElement closeBtn = driver.findElement(By.xpath("//button[contains(@class,'close') or contains(text(),'Close') or contains(text(),'Back')]"));
				closeBtn.click(); Thread.sleep(1000);
				log("Edit Button", "Close Edit popup", "Closed", "Closed", true);
			} catch (Exception e) {
				logInfo("Edit Button", "Close button", "Not found, continuing...");
			}
		}

		// DISABLE/DELETE
		List<WebElement> deleteBtns = driver.findElements(By.xpath(deleteXpath));
		logInfo("Disable Button", "Total Disable Buttons found", String.valueOf(deleteBtns.size()));

		if (!deleteBtns.isEmpty()) {
			WebElement lastDelete = deleteBtns.get(deleteBtns.size() - 1);
			log("Disable Button", "Last Disable button visible", "true", String.valueOf(lastDelete.isDisplayed()), lastDelete.isDisplayed());
			sa.assertTrue(lastDelete.isDisplayed());
			log("Disable Button", "Last Disable button enabled", "true", String.valueOf(lastDelete.isEnabled()), lastDelete.isEnabled());
			sa.assertTrue(lastDelete.isEnabled());
			lastDelete.click(); Thread.sleep(1000);
			log("Disable Button", "Click last Disable button", "Disable triggered", "Clicked record " + deleteBtns.size(), true);
			try {
				driver.switchTo().alert().accept(); Thread.sleep(1000);
				log("Disable Button", "Accept alert", "Alert accepted", "Record disabled", true);
			} catch (Exception e) {
				logInfo("Disable Button", "Alert", "No alert appeared");
			}
		}
	}
}
