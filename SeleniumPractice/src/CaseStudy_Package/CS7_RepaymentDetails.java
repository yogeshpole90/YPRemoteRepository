package CaseStudy_Package;

import org.openqa.selenium.*;
import org.testng.annotations.Test;

/**
 * CS7_RepaymentDetails - REPAYMENT DETAILS Subsection Validation
 * 
 * Currently shows: "No repayment details available."
 * Check if message displayed or data present.
 */
public class CS7_RepaymentDetails extends CS2_Setup {

	@Test
	public void validateRepaymentDetails() throws Exception
	{
		System.out.println("========== REPAYMENT DETAILS ==========");

		// Scroll to REPAYMENT DETAILS section
		WebElement repaySection = driver.findElement(By.xpath("//*[text()='REPAYMENT DETAILS']"));
		jse.executeScript("arguments[0].scrollIntoView({block:'center',behavior:'smooth'})", repaySection);
		Thread.sleep(1000);

		// Case 1: Export button displayed
		WebElement exportBtn = driver.findElement(By.id("exportRepaymentBtn"));
		boolean btnDisplayed = exportBtn.isDisplayed();
		System.out.println("Case 1 | Export button displayed | Expected: true | Actual: " + btnDisplayed);
		sa.assertTrue(btnDisplayed, "BUG: Export Repayment button not displayed");

		// Case 2: Export button enabled
		boolean btnEnabled = exportBtn.isEnabled();
		System.out.println("Case 2 | Export button enabled | Expected: true | Actual: " + btnEnabled);
		sa.assertTrue(btnEnabled, "BUG: Export Repayment button not enabled");

		// Case 3: Click export → check 'No Data Found' message
		exportBtn.click();
		Thread.sleep(2000);
		try
		{
			WebElement msg = driver.findElement(By.xpath("//*[text()='No Data Found']"));
			System.out.println("Case 3 | Export click | Message: '" + msg.getText() + "' | Result: INFO - No repayment data");
		}
		catch (NoSuchElementException e)
		{
			System.out.println("Case 3 | Export click | 'No Data Found' NOT shown | Result: INFO - Data may have exported");
		}

		System.out.println("REPAYMENT DETAILS Summary: 3 cases checked\n");
	}

}
