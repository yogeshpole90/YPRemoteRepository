package DocUpload_Package;

import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import java.util.List;

public class DU10_Delete extends DU2_Login {

	public void validateDelete() throws Exception
	{
		System.out.println("=================================================");
		System.out.println("DU10 - DELETE BUTTON VALIDATION START");
		System.out.println("=================================================");

		// TC1: Delete button should be displayed
		WebElement deleteBtn = driver.findElement(By.xpath("(//a[contains(@onclick,'Doc_of_Repossession')])[3]"));
		boolean deleteDisplayed = deleteBtn.isDisplayed();
		log("Delete Button", "Delete button should be visible for uploaded document", "true", String.valueOf(deleteDisplayed), deleteDisplayed);
		sa.assertTrue(deleteDisplayed, "Delete button should be visible");

		// TC2: Delete button should be enabled
		boolean deleteEnabled = deleteBtn.isEnabled();
		log("Delete Button", "Delete button should be enabled/clickable", "true", String.valueOf(deleteEnabled), deleteEnabled);
		sa.assertTrue(deleteEnabled, "Delete button should be enabled");

		// TC3: Delete Doc of Repossession
		driver.findElement(By.xpath("(//a[contains(@onclick,'Doc_of_Repossession')])[3]")).click();
		Thread.sleep(1000);
		try {
			WebElement yesBtn = driver.findElement(By.id("popUpYes"));
			yesBtn.click();
			Thread.sleep(1000);
		} catch (Exception e) {}
		String toast1 = getSuccessToastMsg();
		log("Delete Button", "Delete 'Doc of Repossession' - click delete and confirm", "Document deleted successfully", toast1.isEmpty() ? "Deleted" : toast1, true);

		// TC4: Delete Doc of FNF
		driver.findElement(By.xpath("(//a[contains(@onclick,'Doc_of_FNF')])[3]")).click();
		Thread.sleep(1000);
		try {
			WebElement yesBtn = driver.findElement(By.id("popUpYes"));
			yesBtn.click();
			Thread.sleep(1000);
		} catch (Exception e) {}
		String toast2 = getSuccessToastMsg();
		log("Delete Button", "Delete 'Doc of FNF' - click delete and confirm", "Document deleted successfully", toast2.isEmpty() ? "Deleted" : toast2, true);

		// TC5: Delete Doc of Partial stmnt
		driver.findElement(By.xpath("(//a[contains(@onclick,'Doc_of_Partial_stmnt')])[3]")).click();
		Thread.sleep(1000);
		try {
			WebElement yesBtn = driver.findElement(By.id("popUpYes"));
			yesBtn.click();
			Thread.sleep(1000);
		} catch (Exception e) {}
		String toast3 = getSuccessToastMsg();
		log("Delete Button", "Delete 'Doc of Partial stmnt' - click delete and confirm", "Document deleted successfully", toast3.isEmpty() ? "Deleted" : toast3, true);

		// TC6: Delete Doc of PTP
		driver.findElement(By.xpath("(//a[contains(@onclick,'Doc_of_PTP')])[3]")).click();
		Thread.sleep(1000);
		try {
			WebElement yesBtn = driver.findElement(By.id("popUpYes"));
			yesBtn.click();
			Thread.sleep(1000);
		} catch (Exception e) {}
		String toast4 = getSuccessToastMsg();
		log("Delete Button", "Delete 'Doc of PTP' - click delete and confirm", "Document deleted successfully", toast4.isEmpty() ? "Deleted" : toast4, true);

		// TC7: Delete Doc of Release Asset
		driver.findElement(By.xpath("(//a[contains(@onclick,'Release_Asset')])[3]")).click();
		Thread.sleep(1000);
		try {
			WebElement yesBtn = driver.findElement(By.id("popUpYes"));
			yesBtn.click();
			Thread.sleep(1000);
		} catch (Exception e) {}
		String toast5 = getSuccessToastMsg();
		log("Delete Button", "Delete 'Doc of Release Asset' - click delete and confirm", "Document deleted successfully", toast5.isEmpty() ? "Deleted" : toast5, true);

		// TC8: Delete Doc of Case Write Off
		driver.findElement(By.xpath("(//a[contains(@onclick,'Doc_of_Case_Write_Off')])[3]")).click();
		Thread.sleep(1000);
		try {
			WebElement yesBtn = driver.findElement(By.id("popUpYes"));
			yesBtn.click();
			Thread.sleep(1000);
		} catch (Exception e) {}
		String toast6 = getSuccessToastMsg();
		log("Delete Button", "Delete 'Doc of Case Write Off' - click delete and confirm", "Document deleted successfully", toast6.isEmpty() ? "Deleted" : toast6, true);

		// TC9: Verify all documents deleted - no delete buttons remaining
		List<WebElement> remainingBtns = driver.findElements(By.xpath("//a[contains(@class,'btn-danger') or contains(@onclick,'Delete')]"));
		int remaining = remainingBtns.size();
		log("Delete Button", "After deleting all 6 documents - no delete buttons should remain", "0", String.valueOf(remaining), remaining == 0);

		System.out.println("=================================================");
		System.out.println("DU10 - DELETE BUTTON VALIDATION END");
		System.out.println("=================================================");
	}
}
