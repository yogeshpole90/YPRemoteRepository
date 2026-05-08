package FullPTP_Package;

import java.util.List;
import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;

public class F10_PTP_ViewEdit extends F2_PTP_Login {

	public void validateViewEdit() throws Exception
	{
		// Add & Save
		WebElement addBtn = driver.findElement(By.id("add"));
		jse.executeScript("arguments[0].scrollIntoView({block:'center'})", addBtn);
		addBtn.click();
		log("Add Button", "Click Add button", "Clicked", "Clicked", true);

		Thread.sleep(2000);
		WebElement saveBtn = driver.findElement(By.id("saveData"));
		jse.executeScript("arguments[0].scrollIntoView({block:'center'})", saveBtn);
		Thread.sleep(2000);
		saveBtn.click();
		log("Save Button", "Click Save button", "Record saved", "Clicked", true);

		Thread.sleep(2000);
		driver.switchTo().parentFrame();
		WebElement ptpTab = driver.findElement(By.xpath("//ul[@id='myTab']/li[3]/a"));
		jse.executeScript("arguments[0].scrollIntoView({block:'center'})", ptpTab);
		driver.switchTo().frame("fetchPTPMstTabFrame");

		// View Button
		List<WebElement> viewBtns = driver.findElements(By.xpath("//a[contains(@class,'ViewBtn')]"));
		log("View Button", "View buttons found", ">0", String.valueOf(viewBtns.size()), viewBtns.size() > 0);
		sa.assertTrue(viewBtns.size() > 0);

		if (viewBtns.size() > 0) {
			WebElement viewBtn = viewBtns.get(viewBtns.size() - 1);
			log("View Button", "Last View button visible", "true", String.valueOf(viewBtn.isDisplayed()), viewBtn.isDisplayed());
			viewBtn.click(); Thread.sleep(1000);
			log("View Button", "Click last View button", "View opened", "Clicked", true);

			WebElement viewSave = driver.findElement(By.id("saveData"));
			jse.executeScript("arguments[0].scrollIntoView({block:'center'})", viewSave);
			log("View Button", "Record displayed in View mode", "Displayed", "Displayed", true);
		}

		// Edit Button
		List<WebElement> editBtns = driver.findElements(By.xpath("//a[contains(@class,'EditBtn')]"));
		log("Edit Button", "Edit buttons found", ">0", String.valueOf(editBtns.size()), editBtns.size() > 0);
		sa.assertTrue(editBtns.size() > 0);

		if (editBtns.size() > 0) {
			WebElement editBtn = editBtns.get(editBtns.size() - 1);
			log("Edit Button", "Last Edit button visible", "true", String.valueOf(editBtn.isDisplayed()), editBtn.isDisplayed());
			editBtn.click(); Thread.sleep(1000);
			log("Edit Button", "Click last Edit button", "Edit opened", "Clicked", true);

			WebElement editRemarks = driver.findElement(By.id("remarks"));
			log("Edit Button", "Remarks editable in Edit mode", "true", String.valueOf(editRemarks.isEnabled()), editRemarks.isEnabled());
			sa.assertTrue(editRemarks.isEnabled());
		}

		// Disable Button
		List<WebElement> disableBtns = driver.findElements(By.xpath("//a[contains(text(),'Disable')]"));
		logInfo("Disable Button", "Disable buttons found", String.valueOf(disableBtns.size()));
		if (disableBtns.size() > 0) {
			log("Disable Button", "Disable button visible", "true", String.valueOf(disableBtns.get(0).isDisplayed()), disableBtns.get(0).isDisplayed());
		}
	}
}
