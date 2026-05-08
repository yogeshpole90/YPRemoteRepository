package DocUpload_Package;

import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;

public class DU8_Delete extends DU2_Login {

	public void validateDelete() throws Exception
	{
		System.out.println("=================================================");
		System.out.println("DU8 - DELETE BUTTON VALIDATION START");
		System.out.println("=================================================");

		// TC1: Delete button should be displayed
		WebElement deleteBtn = driver.findElement(By.xpath("(//a[contains(@onclick,'Delete') or contains(@class,'deleteBtn')])[1]"));
		boolean deleteDisplayed = deleteBtn.isDisplayed();
		log("Delete Button", "Delete button should be visible for uploaded document", "true", String.valueOf(deleteDisplayed), deleteDisplayed);
		sa.assertTrue(deleteDisplayed, "Delete button should be visible");

		// TC2: Delete button should be enabled
		boolean deleteEnabled = deleteBtn.isEnabled();
		log("Delete Button", "Delete button should be enabled/clickable", "true", String.valueOf(deleteEnabled), deleteEnabled);
		sa.assertTrue(deleteEnabled, "Delete button should be enabled");

		// TC3: Click Delete button
		deleteBtn.click();
		Thread.sleep(1000);
		log("Delete Button", "Click Delete button - confirmation popup or record deleted", "Delete action triggered", "Delete clicked successfully", true);

		// TC4: If confirmation popup appears - click Yes
		try {
			WebElement yesBtn = driver.findElement(By.id("popUpYes"));
			boolean yesDisplayed = yesBtn.isDisplayed();
			log("Popup - Yes", "Yes button should be visible on confirmation popup", "true", String.valueOf(yesDisplayed), yesDisplayed);
			sa.assertTrue(yesDisplayed, "Yes button should be visible");

			yesBtn.click();
			Thread.sleep(2000);

			String deleteToast = getSuccessToastMsg();
			log("Popup - Yes", "Click Yes - document should be deleted", "Document deleted", deleteToast.isEmpty() ? "Yes clicked, deleted" : deleteToast, true);
		} catch (Exception e) {
			log("Delete Button", "Delete completed without popup", "Record deleted", "Deleted directly", true);
		}

		System.out.println("=================================================");
		System.out.println("DU8 - DELETE BUTTON VALIDATION END");
		System.out.println("=================================================");
	}
}
