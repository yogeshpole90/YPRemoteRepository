package LegalOrder_Package;

import org.openqa.selenium.*;
import org.openqa.selenium.support.ui.Select;
import org.testng.annotations.Test;

public class LO4_LoanAcNo extends LO2_Login {

	@Test
	public void validateLoanAcNo() throws Exception
	{
		System.out.println("========== LOAN ACCOUNT NO (Dropdown) ==========");
		int caseNo = 1;

		WebElement dd = driver.findElement(By.id("loanAcNo"));
		jse.executeScript("arguments[0].scrollIntoView({block:'center'})", dd);
		Thread.sleep(500);

		// Case 1: Displayed
		System.out.println("Case " + caseNo + " | loanAcNo displayed | " + dd.isDisplayed());
		sa.assertTrue(dd.isDisplayed(), "BUG: loanAcNo not displayed");
		caseNo++;

		// Case 2: Enabled
		System.out.println("Case " + caseNo + " | loanAcNo enabled | " + dd.isEnabled());
		sa.assertTrue(dd.isEnabled(), "BUG: loanAcNo not enabled");
		caseNo++;

		// Case 3: Is it a dropdown
		Select sel = new Select(dd);
		int optCount = sel.getOptions().size();
		System.out.println("Case " + caseNo + " | loanAcNo options count | " + optCount);
		sa.assertTrue(optCount > 0, "BUG: loanAcNo has no options");
		caseNo++;

		// Case 4: Default value (index 0 = --SELECT--)
		String defaultVal = sel.getFirstSelectedOption().getText().trim();
		System.out.println("Case " + caseNo + " | loanAcNo default value | " + defaultVal);
		caseNo++;

		// Case 5: Select valid option (index 1)
		sel.selectByIndex(1);
		Thread.sleep(500);
		String selectedVal = sel.getFirstSelectedOption().getText().trim();
		System.out.println("Case " + caseNo + " | loanAcNo selected | " + selectedVal);
		sa.assertFalse(selectedVal.isEmpty(), "BUG: loanAcNo selected value is empty");
		caseNo++;

		System.out.println("========== LOAN ACCOUNT NO Complete ==========\n");
	}
}
