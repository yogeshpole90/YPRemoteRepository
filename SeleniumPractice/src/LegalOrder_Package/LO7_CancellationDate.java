package LegalOrder_Package;

import org.openqa.selenium.*;
import org.testng.annotations.Test;

public class LO7_CancellationDate extends LO2_Login {

	@Test
	public void validateCancellationDate() throws Exception
	{
		System.out.println("========== CANCELLATION DATE ==========");
		int caseNo = 1;

		WebElement field = driver.findElement(By.id("cancellationDate"));

		// Case 1: Displayed
		System.out.println("Case " + caseNo + " | cancellationDate displayed | " + field.isDisplayed());
		sa.assertTrue(field.isDisplayed(), "BUG: cancellationDate not displayed");
		caseNo++;

		// Case 2: Enabled
		System.out.println("Case " + caseNo + " | cancellationDate enabled | " + field.isEnabled());
		sa.assertTrue(field.isEnabled(), "BUG: cancellationDate not enabled");
		caseNo++;

		// Case 3: Clear & enter invalid date
		field.clear();
		field.sendKeys("99-99-9999");
		field.sendKeys(Keys.TAB);
		Thread.sleep(500);
		String invalidVal = field.getAttribute("value");
		System.out.println("Case " + caseNo + " | cancellationDate invalid input | " + invalidVal);
		caseNo++;

		// Case 4: Clear & enter valid date
		field.clear();
		field.sendKeys("30-03-2026");
		field.sendKeys(Keys.TAB);
		Thread.sleep(500);
		String validVal = field.getAttribute("value");
		System.out.println("Case " + caseNo + " | cancellationDate valid input | " + validVal);
		sa.assertFalse(validVal.isEmpty(), "BUG: cancellationDate value is empty after valid input");
		caseNo++;

		// Case 5: Empty check
		field.clear();
		field.sendKeys(Keys.TAB);
		Thread.sleep(500);
		String emptyVal = field.getAttribute("value");
		System.out.println("Case " + caseNo + " | cancellationDate empty check | " + emptyVal);
		caseNo++;

		// Keep valid value
		field.clear();
		field.sendKeys("30-03-2026");
		field.sendKeys(Keys.TAB);
		Thread.sleep(500);

		System.out.println("========== CANCELLATION DATE Complete ==========\n");
	}
}
