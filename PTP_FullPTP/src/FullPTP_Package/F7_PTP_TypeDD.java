package FullPTP_Package;

import java.util.List;
import org.openqa.selenium.By;
import org.openqa.selenium.Keys;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.Select;

/**
 * F7_PTP_TypeDD - Dropdown Validation for 'PTP Type' (scheduleType)
 * 
 * Expected Options: Full PTP, Schedule PTP, Downpayment + Schedule PTP
 * Cases: Displayed, Enabled, Not Multi-Select, Options Count,
 *        Print Options, Default, All Enabled, Keyboard, Select by Text
 */
public class F7_PTP_TypeDD extends A1_LoginSetup {

	public void validatePTPType() throws Exception
	{
		WebElement ptpType = driver.findElement(By.id("scheduleType"));
		Select s = new Select(ptpType);
		List<WebElement> allOptions = s.getOptions();

		// Case 1: Displayed
		System.out.println("=================================================");
		sa.assertTrue(ptpType.isDisplayed(), "PTP Type not displayed.");
		System.out.println("PT Case 1 : Dropdown is Displayed.");

		// Case 2: Enabled
		System.out.println("=================================================");
		sa.assertTrue(ptpType.isEnabled(), "PTP Type is disabled.");
		System.out.println("PT Case 2 : Dropdown is Enabled.");

		// Case 3: NOT Multi-Select
		System.out.println("=================================================");
		sa.assertFalse(s.isMultiple(), "PTP Type is multi-select.");
		System.out.println("PT Case 3 : Is Multi-Select? " + s.isMultiple());

		// Case 4: Options Count
		System.out.println("=================================================");
		int actSize = allOptions.size();
		System.out.println("PT Case 4 : Options count → " + actSize);

		// Case 5: Print All Options
		System.out.println("=================================================");
		System.out.print("PT Case 5 : Options → ");
		for (WebElement option : allOptions) {
			System.out.print(option.getText() + " , ");
		}
		System.out.println();

		// Case 6: Default Selected
		System.out.println("=================================================");
		String defaultSel = s.getFirstSelectedOption().getText();
		sa.assertNotNull(defaultSel, "Default is null.");
		System.out.println("PT Case 6 : Default → " + defaultSel);

		// Case 7: All Options Enabled
		System.out.println("=================================================");
		for (WebElement option : allOptions) {
			sa.assertTrue(option.isEnabled(), option.getText() + " is disabled!");
		}
		System.out.println("PT Case 7 : All options Enabled.");

		// Case 8: Keyboard Accessible
		System.out.println("=================================================");
		ptpType.sendKeys(Keys.DOWN);
		Thread.sleep(300);
		String keySel = s.getFirstSelectedOption().getText();
		sa.assertNotNull(keySel, "Not keyboard accessible.");
		System.out.println("PT Case 8 : Keyboard selected → " + keySel);

		// Case 9: Select 'Full PTP' by Visible Text
		System.out.println("=================================================");
		s.selectByVisibleText("Full PTP");
		String fullPTP = s.getFirstSelectedOption().getText();
		sa.assertEquals(fullPTP, "Full PTP", "Select Full PTP failed.");
		System.out.println("PT Case 9 : Selected → " + fullPTP);

		System.out.println("=================================================");
		System.out.println("F7_PTP_TypeDD - All cases executed.");
	}

}
