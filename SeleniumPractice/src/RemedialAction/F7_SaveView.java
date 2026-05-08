package RemedialAction;

import java.util.List;
import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;

/**
 * F7_SaveView - Save + Success Message + View + Disable Validation
 * 
 * Flow:
 * 1. Click Save button (id: save)
 * 2. Check 'Saved Successfully' message
 * 3. Click last View button in grid
 * 4. Check if data populated in fields
 * 5. Click Disable button + alert handle
 */
public class F7_SaveView extends F2_Setup {

	public void validateSaveView() throws Exception
	{
		// ========== Save Button ==========

		WebElement saveBtn = driver.findElement(By.id("save"));
		jse.executeScript("arguments[0].scrollIntoView({block:'center'})", saveBtn);
		Thread.sleep(500);
		saveBtn.click();
		System.out.println("=================================================");
		System.out.println("SV Case 1 : Save button clicked");

		Thread.sleep(2000);

		// ========== Success Message Check ==========

		List<WebElement> successMsg = driver.findElements(By.xpath("//*[contains(text(), 'Saved Successfully')]"));
		boolean msgFound = successMsg.size() > 0 && successMsg.get(0).isDisplayed();
		sa.assertTrue(msgFound, "BUG: 'Saved Successfully' message not displayed after save!");
		System.out.println("=================================================");
		System.out.println("SV Case 2 : Success message visible? → " + msgFound + " | " + (msgFound ? "PASS" : "Message NOT Found - BUG"));

		Thread.sleep(2000);

		// ========== View Button - Last Record in Grid ==========

		System.out.println("=================================================");
		List<WebElement> viewBtns = driver.findElements(By.xpath("//*[text()='View']"));
		sa.assertTrue(viewBtns.size() > 0, "No View button found.");
		System.out.println("SV Case 3 : View buttons count → " + viewBtns.size() + " | " + (viewBtns.size() > 0 ? "View Button Found" : "NOT Found - FAIL"));

		if (viewBtns.size() > 0) {
			// Click last View button (latest saved record)
			WebElement viewBtn = viewBtns.get(viewBtns.size() - 1);//get (indexing)
			System.out.println("=================================================");
			viewBtn.click();
			Thread.sleep(2000);
			System.out.println("SV Case 4 : Last View button clicked");

			// Check if fields have data after View click
			String actVal = driver.findElement(By.id("actionId")).getAttribute("value");
			String cmtVal = driver.findElement(By.id("commments")).getAttribute("value");

			System.out.println("SV Case 5 : View Data Check →");
			System.out.println("   actionId  = '" + actVal + "' | " + (!actVal.isEmpty() ? "Data Found" : "EMPTY - BUG"));
			System.out.println("   commments = '" + cmtVal + "' | " + (!cmtVal.isEmpty() ? "Data Found" : "EMPTY - BUG"));

			boolean allPopulated = !actVal.isEmpty() && !cmtVal.isEmpty();
			sa.assertTrue(allPopulated, "BUG: View click - fields are EMPTY!");
			System.out.println("SV Case 6 : All fields populated? → " + allPopulated + " | " + (allPopulated ? "PASS" : "BUG - Data not visible"));
		}

		// ========== Disable Button ==========

		System.out.println("=================================================");
		List<WebElement> disableBtns = driver.findElements(By.xpath("//a[contains(text(),'Disable')]"));
		sa.assertTrue(disableBtns.size() > 0, "No Disable button found.");
		System.out.println("SV Case 7 : Disable buttons count → " + disableBtns.size() + " | " + (disableBtns.size() > 0 ? "Disable Button Found" : "NOT Found - FAIL"));

		if (disableBtns.size() > 0) {
			// Click last Disable button
			WebElement disableBtn = disableBtns.get(disableBtns.size() - 1);
			System.out.println("=================================================");
			disableBtn.click();
			Thread.sleep(1000);

			// Handle confirmation alert if any
			try {
				String alertText = driver.switchTo().alert().getText();
				System.out.println("SV Case 8 : Disable alert → '" + alertText + "'");
				driver.switchTo().alert().accept();
				System.out.println("SV Case 9 : Disable confirmed | Record Disabled");
			} catch (Exception e) {
				System.out.println("SV Case 8 : No alert on Disable | Disabled directly");
			}
		}

		System.out.println("=================================================");
		System.out.println("F7_SaveView - All cases executed.");
	}

}
