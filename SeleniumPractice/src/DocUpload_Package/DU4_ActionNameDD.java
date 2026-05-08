package DocUpload_Package;

import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.Select;
import java.util.List;

public class DU4_ActionNameDD extends DU2_Login {

	public void validateActionName() throws Exception
	{
		System.out.println("=================================================");
		System.out.println("DU4 - ACTION NAME DROPDOWN VALIDATION START");
		System.out.println("=================================================");

		WebElement dropdown = driver.findElement(By.id("actionName"));
		jse.executeScript("arguments[0].scrollIntoView({block:'center',behavior:'smooth'})", dropdown);
		Thread.sleep(1000);
		Select select = new Select(dropdown);

		// TC1: Dropdown should be displayed
		boolean isDisplayed = dropdown.isDisplayed();
		log("Action Name DD", "Dropdown should be visible on page", "true", String.valueOf(isDisplayed), isDisplayed);
		sa.assertTrue(isDisplayed, "Action Name dropdown should be visible");

		boolean isEnabled = dropdown.isEnabled();
		log("Action Name DD", "Dropdown should be enabled/clickable", "true", String.valueOf(isEnabled), isEnabled);
		sa.assertTrue(isEnabled, "Action Name dropdown should be enabled");

		String defaultVal = select.getFirstSelectedOption().getText().trim();
		boolean defaultCheck = defaultVal.contains("Select") || defaultVal.contains("--");
		log("Action Name DD", "Default value should be Select/--SELECT--", "Select", defaultVal, defaultCheck);
		sa.assertTrue(defaultCheck, "Default should be Select");

		List<WebElement> options = select.getOptions();
		int totalOptions = options.size();
		log("Action Name DD", "Dropdown should have multiple options", "More than 1", String.valueOf(totalOptions), totalOptions > 1);
		sa.assertTrue(totalOptions > 1, "Dropdown should have options");

		// TC5: Print all options
		System.out.println("----------------------------------------------");
		System.out.println("All Dropdown Options:");
		for (int i = 0; i < options.size(); i++) {
			System.out.println("  [" + i + "] " + options.get(i).getText() + " (value=" + options.get(i).getAttribute("value") + ")");
		}

		// TC6-TC11: Validate each expected option
		String[] expectedOptions = {"Asset Repossession", "Full & Final Settlement", "Partial Settlement", "Promise To Pay", "Release Asset", "Write Off"};
		for (String exp : expectedOptions) {
			boolean found = false;
			for (WebElement opt : options) {
				if (opt.getText().trim().equals(exp)) { found = true; break; }
			}
			log("Action Name DD", "Option '" + exp + "' should be present", exp + " present", found ? exp + " found" : exp + " NOT found", found);
			sa.assertTrue(found, "Option missing: " + exp);
		}

		// TC12: Select by visible text - Asset Repossession
		select.selectByVisibleText("Asset Repossession");
		Thread.sleep(1000);
		String selected = select.getFirstSelectedOption().getText().trim();
		boolean selectCheck = selected.equals("Asset Repossession");
		log("Action Name DD", "Select by visible text 'Asset Repossession'", "Asset Repossession", selected, selectCheck);
		sa.assertEquals(selected, "Asset Repossession", "Asset Repossession should be selected");

		select.selectByVisibleText("Promise To Pay");
		Thread.sleep(1000);
		selected = select.getFirstSelectedOption().getText().trim();
		boolean ptpCheck = selected.equals("Promise To Pay");
		log("Action Name DD", "Select by visible text 'Promise To Pay'", "Promise To Pay", selected, ptpCheck);
		sa.assertEquals(selected, "Promise To Pay", "Promise To Pay should be selected");

		select.selectByIndex(0);
		Thread.sleep(500);
		String resetVal = select.getFirstSelectedOption().getText().trim();
		boolean resetCheck = resetVal.contains("Select") || resetVal.contains("--");
		log("Action Name DD", "Reset dropdown to default value", "Select", resetVal, resetCheck);
		sa.assertTrue(resetCheck, "Dropdown should reset to default");

		System.out.println("=================================================");
		System.out.println("DU4 - ACTION NAME DROPDOWN VALIDATION END");
		System.out.println("=================================================");
	}
}
