package DemandLetter_Package;

import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.Select;
import java.util.List;

public class DL5_NoticeTypeDD extends DL2_Login {



	public void validateNoticeType() throws Exception
	{
		System.out.println("=================================================");
		System.out.println("DL5 - NOTICE TYPE DROPDOWN VALIDATION START");
		System.out.println("=================================================");

		WebElement dropdown = driver.findElement(By.id("demandLetterType"));
		jse.executeScript("arguments[0].scrollIntoView({block:'center',behavior:'smooth'})", dropdown);
		Thread.sleep(1000);
		Select select = new Select(dropdown);

		// TC1: Dropdown should be displayed
		boolean isDisplayed = dropdown.isDisplayed();
		log("Notice Type DD", "Dropdown should be visible on page", "true", String.valueOf(isDisplayed), isDisplayed);
		sa.assertTrue(isDisplayed, "Notice Type dropdown should be visible");

		// TC2: Dropdown should be enabled
		boolean isEnabled = dropdown.isEnabled();
		log("Notice Type DD", "Dropdown should be enabled/clickable", "true", String.valueOf(isEnabled), isEnabled);
		sa.assertTrue(isEnabled, "Notice Type dropdown should be enabled");

		// TC3: Default selected value
		String defaultVal = select.getFirstSelectedOption().getText().trim();
		boolean defaultCheck = defaultVal.contains("--SELECT");
		log("Notice Type DD", "Default value should be --SELECT [DEMANDLETTERTYPE]--", "--SELECT [DEMANDLETTERTYPE]--", defaultVal, defaultCheck);
		sa.assertTrue(defaultCheck, "Default should be --SELECT [DEMANDLETTERTYPE]--");

		// TC4: Total options count (1 default + 3 actual = 4)
		List<WebElement> options = select.getOptions();
		int totalOptions = options.size();
		log("Notice Type DD", "Total dropdown options count", "4", String.valueOf(totalOptions), totalOptions == 4);
		sa.assertEquals(totalOptions, 4, "Total options should be 4");

		// TC5: Print all options
		System.out.println("----------------------------------------------");
		System.out.println("All Dropdown Options:");
		for (int i = 0; i < options.size(); i++) {
			System.out.println("  [" + i + "] " + options.get(i).getText() + " (value=" + options.get(i).getAttribute("value") + ")");
		}

		// TC6: Verify "First Demand Letter" option present
		String[] expectedOptions = {"First Demand Letter", "Second Demand Letter", "Third Demand Letter"};
		for (String exp : expectedOptions) {
			boolean found = false;
			for (WebElement opt : options) {
				if (opt.getText().trim().equals(exp)) { found = true; break; }
			}
			log("Notice Type DD", "Option '" + exp + "' should be present in dropdown", exp + " present", found ? exp + " found" : exp + " NOT found", found);
			sa.assertTrue(found, "Option missing: " + exp);
		}

		// TC9: Select by visible text - First Demand Letter
		select.selectByVisibleText("First Demand Letter");
		Thread.sleep(1000);
		String selected = select.getFirstSelectedOption().getText().trim();
		boolean fdlCheck = selected.equals("First Demand Letter");
		log("Notice Type DD", "Select by visible text 'First Demand Letter'", "First Demand Letter", selected, fdlCheck);
		sa.assertEquals(selected, "First Demand Letter", "First Demand Letter should be selected");

		// TC10: Select by value - SDL
		select.selectByValue("SDL");
		Thread.sleep(1000);
		selected = select.getFirstSelectedOption().getText().trim();
		boolean sdlCheck = selected.equals("Second Demand Letter");
		log("Notice Type DD", "Select by value 'SDL'", "Second Demand Letter", selected, sdlCheck);
		sa.assertEquals(selected, "Second Demand Letter", "Second Demand Letter should be selected");

		// TC11: Select by value - TDL
		select.selectByValue("TDL");
		Thread.sleep(1000);
		selected = select.getFirstSelectedOption().getText().trim();
		boolean tdlCheck = selected.equals("Third Demand Letter");
		log("Notice Type DD", "Select by value 'TDL'", "Third Demand Letter", selected, tdlCheck);
		sa.assertEquals(selected, "Third Demand Letter", "Third Demand Letter should be selected");

		// TC12: Reset to default
		select.selectByIndex(0);
		Thread.sleep(500);
		String resetVal = select.getFirstSelectedOption().getText().trim();
		boolean resetCheck = resetVal.contains("--SELECT");
		log("Notice Type DD", "Reset dropdown to default value", "--SELECT--", resetVal, resetCheck);
		sa.assertTrue(resetCheck, "Dropdown should reset to default");

		System.out.println("=================================================");
		System.out.println("DL5 - NOTICE TYPE DROPDOWN VALIDATION END");
		System.out.println("=================================================");
	}
}


