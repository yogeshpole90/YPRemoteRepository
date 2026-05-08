package SiteVisitRequest_Package;

import org.openqa.selenium.*;
import org.openqa.selenium.support.ui.Select;
import org.testng.annotations.Test;
import java.util.List;

public class SV4_VisitType extends SV2_Login {

	@Test
	public void validateVisitType() throws Exception
	{
		System.out.println("=================================================");
		System.out.println("SV4 - VISIT TYPE VALIDATION START");
		System.out.println("=================================================");

		WebElement dd = driver.findElement(By.id("visitType"));
		jse.executeScript("arguments[0].scrollIntoView({block:'center'})", dd);
		Thread.sleep(500);
		Select sel = new Select(dd);
		List<WebElement> options = sel.getOptions();

		log("Visit Type", "Should be visible on page", "true", String.valueOf(dd.isDisplayed()), dd.isDisplayed());
		sa.assertTrue(dd.isDisplayed(), "Visit Type not displayed");

		log("Visit Type", "Should be enabled", "true", String.valueOf(dd.isEnabled()), dd.isEnabled());
		sa.assertTrue(dd.isEnabled(), "Visit Type not enabled");

		String defaultVal = sel.getFirstSelectedOption().getText().trim();
		log("Visit Type", "Check default selected value", "--SELECT-- or similar", defaultVal, !defaultVal.isEmpty());

		log("Visit Type", "Check total options count", ">1", String.valueOf(options.size()), options.size() > 1);
		sa.assertTrue(options.size() > 1, "Visit Type has no options");

		StringBuilder allOpts = new StringBuilder();
		for (WebElement o : options) { allOpts.append(o.getText().trim()).append(" , "); }
		logInfo("Visit Type", "All dropdown options", allOpts.toString());

		sel.selectByIndex(0); Thread.sleep(300);
		log("Visit Type", "Select index 0 (default/placeholder)", "Placeholder", sel.getFirstSelectedOption().getText().trim(), true);

		sel.selectByIndex(1); Thread.sleep(300);
		String idx1 = sel.getFirstSelectedOption().getText().trim();
		log("Visit Type", "Select index 1 (first valid option)", "Non-empty value", idx1, !idx1.isEmpty());
		sa.assertFalse(idx1.isEmpty(), "Visit Type index 1 empty");

		sel.selectByIndex(options.size() - 1); Thread.sleep(300);
		String lastOpt = sel.getFirstSelectedOption().getText().trim();
		log("Visit Type", "Select last option (index " + (options.size()-1) + ")", "Non-empty value", lastOpt, !lastOpt.isEmpty());

		log("Visit Type", "Should be single-select (not multi)", "false", String.valueOf(sel.isMultiple()), !sel.isMultiple());
		sa.assertFalse(sel.isMultiple(), "Visit Type should not be multi-select");

		sel.selectByIndex(1); Thread.sleep(300);
		log("Visit Type", "Final value set for save", idx1, sel.getFirstSelectedOption().getText().trim(), true);

		System.out.println("=================================================");
		System.out.println("SV4 - VISIT TYPE VALIDATION END");
		System.out.println("=================================================");
	}
}
