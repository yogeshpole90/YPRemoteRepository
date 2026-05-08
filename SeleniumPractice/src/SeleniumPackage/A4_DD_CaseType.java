package SeleniumPackage;

import java.util.List;
import org.openqa.selenium.By;
import org.openqa.selenium.Keys;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.Select;

/**
 * A4_DDValidate - Dropdown Validation for 'Case Type' field
 * 
 * Validates:
 * 1. Displayed       2. Enabled         3. Not Multi-Select
 * 4. Options Count   5. Print All Options
 * 6. Default Selected Option            7. All Options Enabled
 * 8. Keyboard Accessible                9. Select by Value/Text
 */
public class A4_DD_CaseType extends A1_LoginSetup {

	public void validateDropdown() throws Exception
	{
		// Scroll to policeCaseNo area so dropdown is visible
		WebElement policeCaseNo = driver.findElement(By.id("policeCaseNo"));
		jse.executeScript("arguments[0].scrollIntoView({block:'center',behavior:'smooth'})", policeCaseNo);

		// Locate dropdown & wrap with Select
		WebElement dropdownElement = driver.findElement(By.id("caseType"));
		Select s = new Select(dropdownElement);
		List<WebElement> allOptions = s.getOptions();

		// Case 1: Dropdown is Displayed
		System.out.println("=================================================");
		sa.assertTrue(dropdownElement.isDisplayed(), "Dropdown not displayed!");
		System.out.println("Case 1 : Dropdown is Displayed.");

		// Case 2: Dropdown is Enabled
		System.out.println("=================================================");
		sa.assertTrue(dropdownElement.isEnabled(), "Dropdown not enabled!");
		System.out.println("Case 2 : Dropdown is Enabled.");

		// Case 3: Dropdown is NOT Multi-Select
		System.out.println("=================================================");
		sa.assertFalse(s.isMultiple(), "Dropdown should not be multi-select!");
		System.out.println("Case 3 : Is Multi-Select? " + s.isMultiple());

		// Case 4: Total Options Count
		System.out.println("=================================================");
		int actSize = allOptions.size();
		int expSize = 6;
		sa.assertEquals(actSize, expSize, "Options count mismatch!");
		System.out.println("Case 4 : Actual=" + actSize + " | Expected=" + expSize);

		// Case 5: Print All Dropdown Options
		System.out.println("=================================================");
		System.out.print("Case 5 : Options → ");
		for (WebElement option : allOptions) {
			System.out.print(option.getText() + " , ");
		}
		System.out.println();

		// Case 6: Default Selected Option
		System.out.println("=================================================");
		String defaultSelected = s.getFirstSelectedOption().getText();
		sa.assertNotNull(defaultSelected, "No default option selected!");
		System.out.println("Case 6 : Default Selected → " + defaultSelected);

		// Case 7: All Options are Enabled
		System.out.println("=================================================");
		for (WebElement option : allOptions) {
			sa.assertTrue(option.isEnabled(), option.getText() + " is disabled!");
		}
		System.out.println("Case 7 : All options are Enabled.");

		// Case 8: Keyboard Accessible (Arrow Down key)
		System.out.println("=================================================");
		dropdownElement.sendKeys(Keys.DOWN);
		Thread.sleep(300);
		String keySelected = s.getFirstSelectedOption().getText();
		sa.assertNotNull(keySelected, "Dropdown not keyboard accessible!");
		System.out.println("Case 8 : Keyboard selected → " + keySelected);

		// Case 9: Select by Visible Text
		System.out.println("=================================================");
		s.selectByVisibleText(allOptions.get(1).getText());
		String textSelected = s.getFirstSelectedOption().getText();
		sa.assertEquals(textSelected, allOptions.get(1).getText(), "Select by text failed!");
		System.out.println("Case 9 : Selected by Text → " + textSelected);

		System.out.println("=================================================");
		System.out.println("A4_DD_CaseType - All cases executed.");
	}

}
