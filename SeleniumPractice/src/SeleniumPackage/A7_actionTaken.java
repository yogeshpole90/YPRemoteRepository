package SeleniumPackage;

import java.util.List;
import org.openqa.selenium.By;
import org.openqa.selenium.Keys;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.Select;

/**
 * A7_actionTaken - Dropdown Validation for 'Action Taken' field
 * 
 * Validates:
 * 1. Displayed          2. Enabled            3. Not Multi-Select
 * 4. Options Count      5. Print All Options
 * 6. Default Selected   7. All Options Enabled
 * 8. Keyboard Accessible  9. Select by Visible Text
 */
public class A7_actionTaken extends A1_LoginSetup {

	public void actTaken() throws Exception
	{
		// Locate dropdown & wrap with Select
		WebElement actTaken = driver.findElement(By.id("actionTaken"));
		Select s = new Select(actTaken);
		List<WebElement> allOptions = s.getOptions();

		// Case 1: Dropdown is Displayed
		System.out.println("=================================================");
		sa.assertTrue(actTaken.isDisplayed(), "Action Taken not displayed.");
		System.out.println("Case 1 : Action Taken is Displayed.");

		// Case 2: Dropdown is Enabled
		System.out.println("=================================================");
		sa.assertTrue(actTaken.isEnabled(), "Action Taken is disabled.");
		System.out.println("Case 2 : Action Taken is Enabled.");

		// Case 3: Dropdown is NOT Multi-Select
		System.out.println("=================================================");
		sa.assertFalse(s.isMultiple(), "Action Taken is Multi-Select.");
		System.out.println("Case 3 : Is Multi-Select? " + s.isMultiple());

		// Case 4: Total Options Count
		System.out.println("=================================================");
		int actSize = allOptions.size();
		int expSize = 51;
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
		sa.assertNotNull(defaultSelected, "Default selection is null.");
		System.out.println("Case 6 : Default Selected → " + defaultSelected);

		// Case 7: All Options are Enabled
		System.out.println("=================================================");
		for (WebElement option : allOptions) {
			sa.assertTrue(option.isEnabled(), option.getText() + " is disabled!");
		}
		System.out.println("Case 7 : All options are Enabled.");

		// Case 8: Keyboard Accessible (Arrow Down key)
		System.out.println("=================================================");
		actTaken.sendKeys(Keys.DOWN);
		actTaken.sendKeys(Keys.DOWN);
		Thread.sleep(300);
		String keySelected = s.getFirstSelectedOption().getText();
		sa.assertNotNull(keySelected, "Dropdown not keyboard accessible.");
		System.out.println("Case 8 : Keyboard selected → " + keySelected);

		// Case 9: Select by Visible Text (6th option)
		System.out.println("=================================================");
		String expectedText = allOptions.get(5).getText();
		s.selectByVisibleText(expectedText);
		String textSelected = s.getFirstSelectedOption().getText();
		sa.assertEquals(textSelected, expectedText, "Select by text mismatch!");
		System.out.println("Case 9 : Selected by Text → " + textSelected);

		System.out.println("=================================================");
		System.out.println("A7_actionTaken - All cases executed.");
	}

}
