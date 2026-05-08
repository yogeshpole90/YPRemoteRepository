package FollowUp_Package;

import org.openqa.selenium.*;
import org.testng.annotations.Test;

public class FU11_SaveButton extends FU2_Login {

	@Test
	public void validateSave() throws Exception
	{
		System.out.println("========== SAVE BUTTON ==========");
		int caseNo = 1;

		WebElement saveBtn = driver.findElement(By.id("saveData"));

		// Case 1: Displayed
		System.out.println("Case " + caseNo + " | saveData displayed | " + saveBtn.isDisplayed());
		sa.assertTrue(saveBtn.isDisplayed(), "BUG: saveData not displayed");
		caseNo++;

		// Case 2: Enabled
		System.out.println("Case " + caseNo + " | saveData enabled | " + saveBtn.isEnabled());
		sa.assertTrue(saveBtn.isEnabled(), "BUG: saveData not enabled");
		caseNo++;

		// Case 3: Click Save
		jse.executeScript("arguments[0].scrollIntoView({block:'center'})", saveBtn);
		Thread.sleep(500);
		saveBtn.click();
		Thread.sleep(3000);

		// Switch to parent for message
		driver.switchTo().parentFrame();
		Thread.sleep(500);
		driver.switchTo().defaultContent();
		jse.executeScript("window.scrollTo(0,0)");
		Thread.sleep(1000);

		try {
			String msg = driver.findElement(By.xpath("//*[@class='msg-toast msg-success msg-showing']/em")).getText().trim();
			System.out.println("Case " + caseNo + " | Save message | " + msg);
		} catch(Exception e) {
			System.out.println("Case " + caseNo + " | Save message not found");
		}
		caseNo++;

		System.out.println("========== SAVE BUTTON Complete ==========\n");
	}
}
