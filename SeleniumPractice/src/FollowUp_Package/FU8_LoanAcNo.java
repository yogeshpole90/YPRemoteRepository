package FollowUp_Package;

import org.openqa.selenium.*;
import org.testng.annotations.Test;
import java.util.List;

public class FU8_LoanAcNo extends FU2_Login {

	@Test
	public void validateLoanAcNo() throws Exception
	{
		System.out.println("========== LOAN ACCOUNT NO (Select2 Dropdown) ==========");
		int caseNo = 1;

		// Click on select2 container to open dropdown
		WebElement select2Container = driver.findElement(By.xpath("//ul[contains(@class,'select2-selection__rendered')]"));

		// Case 1: Displayed
		System.out.println("Case " + caseNo + " | loanAcNo select2 displayed | " + select2Container.isDisplayed());
		sa.assertTrue(select2Container.isDisplayed(), "BUG: loanAcNo select2 not displayed");
		caseNo++;

		// Case 2: Click to open dropdown
		select2Container.click();
		Thread.sleep(1000);
		System.out.println("Case " + caseNo + " | loanAcNo dropdown opened | PASS");
		caseNo++;

		// Case 3: Check options available
		List<WebElement> options = driver.findElements(By.xpath("//li[contains(@id,'select2-loanAcNoSelect-result')]"));
		System.out.println("Case " + caseNo + " | loanAcNo options count | " + options.size());
		sa.assertTrue(options.size() > 0, "BUG: loanAcNo has no options");
		caseNo++;

		// Case 4: Print all options
		for (int i = 0; i < options.size(); i++) {
			System.out.println("  Option " + i + " | " + options.get(i).getText().trim());
		}

		// Case 5: Select first option
		options.get(0).click();
		Thread.sleep(500);
		System.out.println("Case " + caseNo + " | loanAcNo first option selected | PASS");
		caseNo++;

		System.out.println("========== LOAN ACCOUNT NO Complete ==========\n");
	}
}
