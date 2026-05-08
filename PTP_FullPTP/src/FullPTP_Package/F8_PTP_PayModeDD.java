package FullPTP_Package;

import java.util.List;
import org.openqa.selenium.By;
import org.openqa.selenium.Keys;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.Select;

/**
 * F8_PTP_PayModeDD - Dropdown Validation for 'Payment Mode'
 * 
 * Expected Options: Account Transfer, CASH, Cheque, NEFT, Visa Swipe, etc.
 * Cases: Displayed, Enabled, Not Multi-Select, Options Count,
 *        Print Options, Default, All Enabled, Keyboard, Select by Text
 */
public class F8_PTP_PayModeDD extends A1_LoginSetup {

	public void validatePayMode() throws Exception
	{
		WebElement payMode = driver.findElement(By.id("paymentMode"));
		jse.executeScript("arguments[0].scrollIntoView({block:'center',behavior:'smooth'})", payMode);
		Select s = new Select(payMode);
		List<WebElement> allOptions = s.getOptions();

		// Case 1: Displayed
		System.out.println("=================================================");
		sa.assertTrue(payMode.isDisplayed(), "Payment Mode not displayed.");
		System.out.println("PM Case 1 : Dropdown is Displayed.");

		// Case 2: Enabled
		System.out.println("=================================================");
		sa.assertTrue(payMode.isEnabled(), "Payment Mode is disabled.");
		System.out.println("PM Case 2 : Dropdown is Enabled.");

		// Case 3: NOT Multi-Select
		System.out.println("=================================================");
		sa.assertFalse(s.isMultiple(), "Payment Mode is multi-select.");
		System.out.println("PM Case 3 : Is Multi-Select? " + s.isMultiple());

		// Case 4: Options Count
		System.out.println("=================================================");
		int actSize = allOptions.size();
		System.out.println("PM Case 4 : Options count → " + actSize);

		// Case 5: Print All Options
		System.out.println("=================================================");
		System.out.print("PM Case 5 : Options → ");
		for (WebElement option : allOptions) {
			System.out.print(option.getText() + " , ");
		}
		System.out.println();

		// Case 6: Default Selected
		System.out.println("=================================================");
		String defaultSel = s.getFirstSelectedOption().getText();
		sa.assertNotNull(defaultSel, "Default is null.");
		System.out.println("PM Case 6 : Default → " + defaultSel);

		// Case 7: All Options Enabled
		System.out.println("=================================================");
		for (WebElement option : allOptions) {
			sa.assertTrue(option.isEnabled(), option.getText() + " is disabled!");
		}
		System.out.println("PM Case 7 : All options Enabled.");

		// Case 8: Keyboard Accessible
		System.out.println("=================================================");
		payMode.sendKeys(Keys.DOWN);
		Thread.sleep(300);
		String keySel = s.getFirstSelectedOption().getText();
		sa.assertNotNull(keySel, "Not keyboard accessible.");
		System.out.println("PM Case 8 : Keyboard selected → " + keySel);

		// Case 9: Select 'CASH' by Visible Text
		System.out.println("=================================================");
		s.selectByVisibleText("CASH");
		String cashSel = s.getFirstSelectedOption().getText();
		sa.assertEquals(cashSel, "CASH", "Select CASH failed.");
		System.out.println("PM Case 9 : Selected → " + cashSel);

		System.out.println("=================================================");
		System.out.println("F8_PTP_PayModeDD - All cases executed.");
	}

}
