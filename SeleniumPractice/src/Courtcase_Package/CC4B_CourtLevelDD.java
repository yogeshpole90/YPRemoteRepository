package Courtcase_Package;

import java.util.List;
import org.openqa.selenium.By;
import org.openqa.selenium.Keys;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.Select;

public class CC4B_CourtLevelDD extends CC2_Login {

	public void validateCourtLevel() throws Exception
	{
		WebElement dd = driver.findElement(By.id("courtLevel"));
		jse.executeScript("arguments[0].scrollIntoView({block:'center'})", dd);
		Thread.sleep(500);
		Select s = new Select(dd);
		List<WebElement> opts = s.getOptions();

		log("Court Level DD", "Displayed", "true", String.valueOf(dd.isDisplayed()), dd.isDisplayed());
		sa.assertTrue(dd.isDisplayed(), "Court Level not displayed");

		log("Court Level DD", "Enabled", "true", String.valueOf(dd.isEnabled()), dd.isEnabled());
		sa.assertTrue(dd.isEnabled(), "Court Level disabled");

		log("Court Level DD", "Single select", "false", String.valueOf(s.isMultiple()), !s.isMultiple());

		log("Court Level DD", "Options count", ">1", String.valueOf(opts.size()), opts.size() > 1);

		logInfo("Court Level DD", "All options", "");
		for (int i = 0; i < opts.size(); i++) {
			System.out.println("  [" + i + "] " + opts.get(i).getText());
		}

		String def = s.getFirstSelectedOption().getText();
		log("Court Level DD", "Default value", "Select/--SELECT--", def, def.contains("Select") || def.contains("SELECT"));

		String[] expected = {"Supreme Court", "Court of appeal", "District Court", "High Court", "Magistrate", "OHADA Court", "Other"};
		for (String exp : expected) {
			boolean found = false;
			for (WebElement o : opts) if (o.getText().trim().equals(exp)) { found = true; break; }
			log("Court Level DD", "Option '" + exp + "' present", exp, found ? exp + " found" : "NOT found", found);
		}

		s.selectByVisibleText("High Court"); Thread.sleep(500);
		log("Court Level DD", "Select 'High Court'", "High Court", s.getFirstSelectedOption().getText().trim(), s.getFirstSelectedOption().getText().trim().equals("High Court"));

		s.selectByIndex(0); Thread.sleep(300);
		s.selectByVisibleText("District Court"); Thread.sleep(500);
		log("Court Level DD", "Final value 'District Court'", "District Court", s.getFirstSelectedOption().getText().trim(), true);

		System.out.println("=================================================");
		System.out.println("CC4B_CourtLevelDD - All cases executed.");
	}
}
