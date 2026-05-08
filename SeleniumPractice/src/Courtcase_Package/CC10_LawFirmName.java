package Courtcase_Package;

import java.util.List;
import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.Select;

public class CC10_LawFirmName extends CC2_Login {

	public void validateLawFirmName() throws Exception
	{
		WebElement dd = driver.findElement(By.id("lawFirmName"));
		jse.executeScript("arguments[0].scrollIntoView({block:'center'})", dd);
		Thread.sleep(500);
		Select s = new Select(dd);
		List<WebElement> opts = s.getOptions();

		log("Law Firm Name DD", "Displayed", "true", String.valueOf(dd.isDisplayed()), dd.isDisplayed());
		sa.assertTrue(dd.isDisplayed(), "Law Firm Name not displayed");

		log("Law Firm Name DD", "Enabled", "true", String.valueOf(dd.isEnabled()), dd.isEnabled());
		sa.assertTrue(dd.isEnabled(), "Law Firm Name disabled");

		log("Law Firm Name DD", "Single select", "false", String.valueOf(s.isMultiple()), !s.isMultiple());

		log("Law Firm Name DD", "Options count", ">1", String.valueOf(opts.size()), opts.size() > 1);

		logInfo("Law Firm Name DD", "All options", "");
		for (int i = 0; i < opts.size(); i++) {
			System.out.println("  [" + i + "] " + opts.get(i).getText() + " (value=" + opts.get(i).getAttribute("value") + ")");
		}

		String def = s.getFirstSelectedOption().getText();
		log("Law Firm Name DD", "Default value", "Select", def, def.contains("Select"));

		// Select LF00000005
		s.selectByValue("LF00000005"); Thread.sleep(500);
		String selected = s.getFirstSelectedOption().getText().trim();
		log("Law Firm Name DD", "Select LF00000005", "LF00000005", selected, selected.equals("LF00000005"));

		log("Law Firm Name DD", "Final value for save", "LF00000005", s.getFirstSelectedOption().getText().trim(), true);

		System.out.println("=================================================");
		System.out.println("CC10_LawFirmName - All cases executed.");
	}
}
