package SiteVisitRequest_Package;

import org.openqa.selenium.*;
import org.openqa.selenium.support.ui.Select;
import org.testng.annotations.Test;
import java.util.List;

public class SV8_CustomerResponse extends SV2_Login {

	@Test
	public void validateCustomerResponse() throws Exception
	{
		System.out.println("=================================================");
		System.out.println("SV8 - CUSTOMER RESPONSE VALIDATION START");
		System.out.println("=================================================");

		WebElement dd = driver.findElement(By.id("customerResponse"));
		Select sel = new Select(dd);
		List<WebElement> options = sel.getOptions();

		log("Customer Response", "Should be visible", "true", String.valueOf(dd.isDisplayed()), dd.isDisplayed());
		sa.assertTrue(dd.isDisplayed(), "Customer Response not displayed");

		log("Customer Response", "Should be enabled", "true", String.valueOf(dd.isEnabled()), dd.isEnabled());
		sa.assertTrue(dd.isEnabled(), "Customer Response not enabled");

		log("Customer Response", "Check default value", "Default/placeholder", sel.getFirstSelectedOption().getText().trim(), true);

		log("Customer Response", "Total options count", ">1", String.valueOf(options.size()), options.size() > 1);
		sa.assertTrue(options.size() > 1, "Customer Response has no options");

		StringBuilder allOpts = new StringBuilder();
		for (WebElement o : options) { allOpts.append(o.getText().trim()).append(" , "); }
		logInfo("Customer Response", "All dropdown options", allOpts.toString());

		sel.selectByIndex(1); Thread.sleep(300);
		log("Customer Response", "Select index 1", "Non-empty", sel.getFirstSelectedOption().getText().trim(), true);

		sel.selectByIndex(options.size() - 1); Thread.sleep(300);
		log("Customer Response", "Select last option", "Non-empty", sel.getFirstSelectedOption().getText().trim(), true);

		log("Customer Response", "Should be single-select", "false", String.valueOf(sel.isMultiple()), !sel.isMultiple());
		sa.assertFalse(sel.isMultiple(), "Customer Response should not be multi-select");

		sel.selectByIndex(1);
		log("Customer Response", "Final value set for save", sel.getFirstSelectedOption().getText().trim(), sel.getFirstSelectedOption().getText().trim(), true);

		System.out.println("=================================================");
		System.out.println("SV8 - CUSTOMER RESPONSE VALIDATION END");
		System.out.println("=================================================");
	}
}
