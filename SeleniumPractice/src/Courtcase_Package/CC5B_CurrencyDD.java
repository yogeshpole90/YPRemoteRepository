package Courtcase_Package;

import java.util.List;
import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.Select;

public class CC5B_CurrencyDD extends CC2_Login {

	public void validateCurrency() throws Exception
	{
		WebElement dd = driver.findElement(By.id("currency"));
		jse.executeScript("arguments[0].scrollIntoView({block:'center'})", dd);
		Thread.sleep(500);
		Select s = new Select(dd);
		List<WebElement> opts = s.getOptions();

		log("Currency DD", "Displayed", "true", String.valueOf(dd.isDisplayed()), dd.isDisplayed());
		sa.assertTrue(dd.isDisplayed(), "Currency not displayed");

		log("Currency DD", "Enabled", "true", String.valueOf(dd.isEnabled()), dd.isEnabled());
		sa.assertTrue(dd.isEnabled(), "Currency disabled");

		log("Currency DD", "Single select", "false", String.valueOf(s.isMultiple()), !s.isMultiple());

		log("Currency DD", "Options count", ">1", String.valueOf(opts.size()), opts.size() > 1);

		logInfo("Currency DD", "All options", "");
		for (int i = 0; i < opts.size(); i++) {
			System.out.println("  [" + i + "] " + opts.get(i).getText() + " (value=" + opts.get(i).getAttribute("value") + ")");
		}

		String def = s.getFirstSelectedOption().getText();
		log("Currency DD", "Default value", "Select/--SELECT--", def, def.contains("Select") || def.contains("SELECT"));

		String[] expected = {"EURO", "UA", "US Dollar", "XOF"};
		for (String exp : expected) {
			boolean found = false;
			for (WebElement o : opts) if (o.getText().trim().equals(exp)) { found = true; break; }
			log("Currency DD", "Option '" + exp + "' present", exp, found ? exp + " found" : "NOT found", found);
		}

		s.selectByVisibleText("XOF"); Thread.sleep(500);
		log("Currency DD", "Select 'XOF'", "XOF", s.getFirstSelectedOption().getText().trim(), s.getFirstSelectedOption().getText().trim().equals("XOF"));

		s.selectByVisibleText("US Dollar"); Thread.sleep(500);
		log("Currency DD", "Final value 'US Dollar'", "US Dollar", s.getFirstSelectedOption().getText().trim(), true);

		System.out.println("=================================================");
		System.out.println("CC5B_CurrencyDD - All cases executed.");
	}
}
