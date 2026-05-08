package LegalOrder_Package;

import org.openqa.selenium.*;
import org.openqa.selenium.support.ui.Select;
import org.testng.annotations.Test;

public class LO5_OrderType extends LO2_Login {

	@Test
	public void validateOrderType() throws Exception
	{
		System.out.println("========== ORDER TYPE (Dropdown) ==========");
		int caseNo = 1;

		WebElement dd = driver.findElement(By.id("orderType"));

		// Case 1: Displayed
		System.out.println("Case " + caseNo + " | orderType displayed | " + dd.isDisplayed());
		sa.assertTrue(dd.isDisplayed(), "BUG: orderType not displayed");
		caseNo++;

		// Case 2: Enabled
		System.out.println("Case " + caseNo + " | orderType enabled | " + dd.isEnabled());
		sa.assertTrue(dd.isEnabled(), "BUG: orderType not enabled");
		caseNo++;

		// Case 3: Options count
		Select sel = new Select(dd);
		int optCount = sel.getOptions().size();
		System.out.println("Case " + caseNo + " | orderType options count | " + optCount);
		sa.assertTrue(optCount > 0, "BUG: orderType has no options");
		caseNo++;

		// Case 4: Default value
		String defaultVal = sel.getFirstSelectedOption().getText().trim();
		System.out.println("Case " + caseNo + " | orderType default value | " + defaultVal);
		caseNo++;

		// Case 5: Select valid option (index 2)
		sel.selectByIndex(2);
		Thread.sleep(500);
		String selectedVal = sel.getFirstSelectedOption().getText().trim();
		System.out.println("Case " + caseNo + " | orderType selected | " + selectedVal);
		sa.assertFalse(selectedVal.isEmpty(), "BUG: orderType selected value is empty");
		caseNo++;

		System.out.println("========== ORDER TYPE Complete ==========\n");
	}
}
