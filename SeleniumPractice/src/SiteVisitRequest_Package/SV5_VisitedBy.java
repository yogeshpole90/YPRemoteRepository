package SiteVisitRequest_Package;

import org.openqa.selenium.*;
import org.openqa.selenium.support.ui.Select;
import org.testng.annotations.Test;
import java.util.List;

public class SV5_VisitedBy extends SV2_Login {

	@Test
	public void validateVisitedBy() throws Exception
	{
		System.out.println("=================================================");
		System.out.println("SV5 - VISITED BY VALIDATION START");
		System.out.println("=================================================");

		WebElement dd = driver.findElement(By.id("visitedBy"));
		Select sel = new Select(dd);
		List<WebElement> options = sel.getOptions();

		log("Visited By", "Should be visible on page", "true", String.valueOf(dd.isDisplayed()), dd.isDisplayed());
		sa.assertTrue(dd.isDisplayed(), "Visited By not displayed");

		log("Visited By", "Should be enabled", "true", String.valueOf(dd.isEnabled()), dd.isEnabled());
		sa.assertTrue(dd.isEnabled(), "Visited By not enabled");

		log("Visited By", "Check default selected value", "Default/placeholder", sel.getFirstSelectedOption().getText().trim(), true);

		log("Visited By", "Check total options count", ">1", String.valueOf(options.size()), options.size() > 1);
		sa.assertTrue(options.size() > 1, "Visited By has no options");

		StringBuilder allOpts = new StringBuilder();
		for (WebElement o : options) { allOpts.append(o.getText().trim()).append(" , "); }
		logInfo("Visited By", "All dropdown options", allOpts.toString());

		sel.selectByIndex(1); Thread.sleep(300);
		String idx1 = sel.getFirstSelectedOption().getText().trim();
		log("Visited By", "Select index 1", "Non-empty", idx1, !idx1.isEmpty());

		sel.selectByIndex(options.size() - 1); Thread.sleep(300);
		String lastOpt = sel.getFirstSelectedOption().getText().trim();
		log("Visited By", "Select last option", "Non-empty", lastOpt, !lastOpt.isEmpty());

		log("Visited By", "Should be single-select", "false", String.valueOf(sel.isMultiple()), !sel.isMultiple());
		sa.assertFalse(sel.isMultiple(), "Visited By should not be multi-select");

		sel.selectByIndex(1); Thread.sleep(300);
		log("Visited By", "Final value set for save", idx1, sel.getFirstSelectedOption().getText().trim(), true);

		System.out.println("=================================================");
		System.out.println("SV5 - VISITED BY VALIDATION END");
		System.out.println("=================================================");
	}
}
