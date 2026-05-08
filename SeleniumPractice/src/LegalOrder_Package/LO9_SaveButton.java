package LegalOrder_Package;

import org.openqa.selenium.*;
import org.testng.annotations.Test;

public class LO9_SaveButton extends LO2_Login {

	@Test
	public void validateSave() throws Exception
	{
		System.out.println("========== SAVE / VIEW / EDIT / DELETE ==========");
		int caseNo = 1;

		WebElement saveBtn = driver.findElement(By.id("saveBtn"));

		// Case 1: Save Displayed
		System.out.println("Case " + caseNo + " | saveBtn displayed | " + saveBtn.isDisplayed());
		sa.assertTrue(saveBtn.isDisplayed(), "BUG: saveBtn not displayed");
		caseNo++;

		// Case 2: Save Enabled
		System.out.println("Case " + caseNo + " | saveBtn enabled | " + saveBtn.isEnabled());
		sa.assertTrue(saveBtn.isEnabled(), "BUG: saveBtn not enabled");
		caseNo++;

		// Case 3: Click Save
		jse.executeScript("arguments[0].scrollIntoView({block:'center'})", saveBtn);
		Thread.sleep(500);
		saveBtn.click();
		Thread.sleep(3000);

		// Scroll up for message
		driver.switchTo().defaultContent();
		jse.executeScript("window.scrollTo(0,0)");
		Thread.sleep(1000);

		// Case 4: Save message check
		try {
			String saveMsg = driver.findElement(By.xpath("//*[@class='msg-toast msg-success msg-showing']/em")).getText().trim();
			System.out.println("Case " + caseNo + " | Save message | " + saveMsg);
			sa.assertTrue(saveMsg.contains("Legal Order Detail Saved Successfully"), "BUG: Save message mismatch: " + saveMsg);
		} catch(Exception e) {
			System.out.println("Case " + caseNo + " | Save message not found | FAIL");
			sa.fail("BUG: Save success message not displayed");
		}
		caseNo++;

		// Switch back to frame
		driver.switchTo().frame("getLegalDetailDataFrame");
		Thread.sleep(1000);

		// Case 5: View button click
		WebElement viewBtn = driver.findElement(By.xpath("//*[contains(@class,'ViewBtn')]"));
		jse.executeScript("arguments[0].scrollIntoView({block:'center'})", viewBtn);
		Thread.sleep(500);
		viewBtn.click();
		Thread.sleep(2000);
		System.out.println("Case " + caseNo + " | View button clicked | PASS");
		caseNo++;

		// Case 6: Edit button click
		WebElement editBtn = driver.findElement(By.xpath("//*[contains(@class,'editBtn')]"));
		jse.executeScript("arguments[0].scrollIntoView({block:'center'})", editBtn);
		Thread.sleep(500);
		editBtn.click();
		Thread.sleep(2000);

		// Save after edit
		driver.findElement(By.id("saveBtn")).click();
		Thread.sleep(3000);

		// Scroll up for update message
		driver.switchTo().defaultContent();
		jse.executeScript("window.scrollTo(0,0)");
		Thread.sleep(1000);

		// Case 7: Update message check
		try {
			String updateMsg = driver.findElement(By.xpath("//*[@class='msg-toast msg-success msg-showing']/em")).getText().trim();
			System.out.println("Case " + caseNo + " | Edit + Save → Update message | " + updateMsg);
			sa.assertTrue(updateMsg.contains("Legal Order Detail Updated Successfully"), "BUG: Update message mismatch: " + updateMsg);
		} catch(Exception e) {
			System.out.println("Case " + caseNo + " | Update message not found | FAIL");
			sa.fail("BUG: Update success message not displayed");
		}
		caseNo++;

		// Switch back to frame
		driver.switchTo().frame("getLegalDetailDataFrame");
		Thread.sleep(1000);

		// Case 8: Delete button click
		WebElement deleteBtn = driver.findElement(By.xpath("//*[contains(@class,'deleteBtn')]"));
		jse.executeScript("arguments[0].scrollIntoView({block:'center'})", deleteBtn);
		Thread.sleep(500);
		deleteBtn.click();
		Thread.sleep(3000);

		// Scroll up for delete message
		driver.switchTo().defaultContent();
		jse.executeScript("window.scrollTo(0,0)");
		Thread.sleep(1000);

		// Case 9: Delete message check
		try {
			String deleteMsg = driver.findElement(By.xpath("//*[@class='msg-toast msg-success msg-showing']/em")).getText().trim();
			System.out.println("Case " + caseNo + " | Delete message | " + deleteMsg);
			sa.assertTrue(deleteMsg.contains("Legal Order Delete Sucessfully"), "BUG: Delete message mismatch: " + deleteMsg);
		} catch(Exception e) {
			System.out.println("Case " + caseNo + " | Delete message not found | FAIL");
			sa.fail("BUG: Delete success message not displayed");
		}
		caseNo++;

		System.out.println("========== SAVE / VIEW / EDIT / DELETE Complete ==========\n");
	}
}
