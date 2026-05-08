package CaseStatus_Package;

import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.Select;
import java.util.List;

public class CST4_CaseStatusDD extends CST2_Login {



	public void validateCaseStatus() throws Exception
	{
		System.out.println("=================================================");
		System.out.println("CST4 - CASE STATUS DROPDOWN VALIDATION START");
		System.out.println("=================================================");

		WebElement dropdown = driver.findElement(By.id("caseStatusId"));
		jse.executeScript("arguments[0].scrollIntoView({block:'center',behavior:'smooth'})", dropdown);
		Thread.sleep(1000);
		Select select = new Select(dropdown);

		// TC1: Dropdown should be displayed
		boolean isDisplayed = dropdown.isDisplayed();
		log("Case Status DD", "Dropdown should be visible on page", "true", String.valueOf(isDisplayed), isDisplayed);
		sa.assertTrue(isDisplayed, "Case Status dropdown should be visible");

		// TC2: Dropdown should be enabled
		boolean isEnabled = dropdown.isEnabled();
		log("Case Status DD", "Dropdown should be enabled/clickable", "true", String.valueOf(isEnabled), isEnabled);
		sa.assertTrue(isEnabled, "Case Status dropdown should be enabled");

		// TC3: Default selected value
		String defaultVal = select.getFirstSelectedOption().getText().trim();
		boolean defaultCheck = defaultVal.contains("--SELECT") || defaultVal.equals("Select");
		log("Case Status DD", "Default value should be --SELECT [caseStatus]--", "--SELECT [caseStatus]--", defaultVal, defaultCheck);
		sa.assertTrue(defaultCheck, "Default should be --SELECT [caseStatus]--");

		// TC4: Total options count (2 default + 10 actual = 12)
		List<WebElement> options = select.getOptions();
		int totalOptions = options.size();
		log("Case Status DD", "Total dropdown options count", "12", String.valueOf(totalOptions), totalOptions == 12);
		sa.assertEquals(totalOptions, 12, "Total options should be 12");

		// TC5: Print all options
		System.out.println("----------------------------------------------");
		System.out.println("All Dropdown Options:");
		for (int i = 0; i < options.size(); i++) {
			System.out.println("  [" + i + "] " + options.get(i).getText() + " (value=" + options.get(i).getAttribute("value") + ")");
		}

		// TC6-TC15: Validate each expected option is present
		String[] expectedOptions = {"Closed", "Deceased", "Declared Bankruptcy", "Legal", "Payment", "Permanent Disability", "Police", "Settlement", "Skipped", "Warning"};
		for (String exp : expectedOptions) {
			boolean found = false;
			for (WebElement opt : options) {
				if (opt.getText().trim().equals(exp)) { found = true; break; }
			}
			log("Case Status DD", "Option '" + exp + "' should be present in dropdown", exp + " present", found ? exp + " found" : exp + " NOT found", found);
			sa.assertTrue(found, "Option missing: " + exp);
		}

		// TC16: Select by visible text - Legal
		select.selectByVisibleText("Legal");
		Thread.sleep(1000);
		String selected = select.getFirstSelectedOption().getText().trim();
		boolean legalCheck = selected.equals("Legal");
		log("Case Status DD", "Select by visible text 'Legal'", "Legal", selected, legalCheck);
		sa.assertEquals(selected, "Legal", "Legal should be selected");

		// TC17: Select by value - STL (Settlement)
		select.selectByValue("STL");
		Thread.sleep(1000);
		selected = select.getFirstSelectedOption().getText().trim();
		boolean stlCheck = selected.equals("Settlement");
		log("Case Status DD", "Select by value 'STL'", "Settlement", selected, stlCheck);
		sa.assertEquals(selected, "Settlement", "Settlement should be selected");

		// TC18: Select by index - Closed (index 2)
		select.selectByIndex(2);
		Thread.sleep(1000);
		selected = select.getFirstSelectedOption().getText().trim();
		boolean indexCheck = selected.equals("Closed");
		log("Case Status DD", "Select by index 2", "Closed", selected, indexCheck);
		sa.assertEquals(selected, "Closed", "Closed should be selected at index 2");

		// TC19: Reset dropdown to default
		select.selectByIndex(0);
		Thread.sleep(500);
		String resetVal = select.getFirstSelectedOption().getText().trim();
		boolean resetCheck = resetVal.contains("Select");
		log("Case Status DD", "Reset dropdown to default value", "Select", resetVal, resetCheck);
		sa.assertTrue(resetCheck, "Dropdown should reset to default");

		System.out.println("=================================================");
		System.out.println("CST4 - CASE STATUS DROPDOWN VALIDATION END");
		System.out.println("=================================================");
	}
}


