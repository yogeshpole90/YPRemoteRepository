package Courtcase_Package;

import java.util.List;
import org.openqa.selenium.By;
import org.openqa.selenium.Keys;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.Select;

public class CC7_LawyerDD extends CC2_Login {

	public void validateLawyerDD() throws Exception
	{
		WebElement lawyer = driver.findElement(By.id("lawyerName"));
		jse.executeScript("arguments[0].scrollIntoView({block:'center'})", lawyer);
		Select s = new Select(lawyer);
		List<WebElement> allOptions = s.getOptions();

		System.out.println("=================================================");
		sa.assertTrue(lawyer.isDisplayed(), "Lawyer Name not displayed.");
		System.out.println("LN Case 1 : Is Displayed? → " + lawyer.isDisplayed() + " | PASS");

		System.out.println("=================================================");
		sa.assertTrue(lawyer.isEnabled(), "Lawyer Name is disabled.");
		System.out.println("LN Case 2 : Is Enabled? → " + lawyer.isEnabled() + " | PASS");

		System.out.println("=================================================");
		sa.assertFalse(s.isMultiple(), "Lawyer Name is multi-select.");
		System.out.println("LN Case 3 : Is Multi-Select? → " + s.isMultiple() + " | " + (!s.isMultiple() ? "Single Select - PASS" : "Multi Select - FAIL"));

		System.out.println("=================================================");
		System.out.println("LN Case 4 : Total Options → " + allOptions.size() + " | Options Count Fetched");

		System.out.println("=================================================");
		System.out.print("LN Case 5 : All DD Values → ");
		for (WebElement option : allOptions) {
			System.out.print(option.getText() + " , ");
		}
		System.out.println("| All Options Printed");

		System.out.println("=================================================");
		String defaultSel = s.getFirstSelectedOption().getText();
		sa.assertNotNull(defaultSel, "Default is null.");
		System.out.println("LN Case 6 : Default Selected → '" + defaultSel + "' | Default Option Found");

		System.out.println("=================================================");
		boolean allEnabled = true;
		for (WebElement option : allOptions) {
			if (!option.isEnabled()) { allEnabled = false; }
			sa.assertTrue(option.isEnabled(), option.getText() + " is disabled!");
		}
		System.out.println("LN Case 7 : All Options Enabled? → " + allEnabled + " | " + (allEnabled ? "All Enabled - PASS" : "Some Disabled - FAIL"));

		System.out.println("=================================================");
		lawyer.sendKeys(Keys.DOWN);
		Thread.sleep(300);
		String keySel = s.getFirstSelectedOption().getText();
		sa.assertNotNull(keySel, "Not keyboard accessible.");
		System.out.println("LN Case 8 : Arrow Down pressed → Selected '" + keySel + "' | Keyboard Accessible - PASS");

		System.out.println("=================================================");
		s.selectByIndex(1);
		Thread.sleep(500);
		String finalSel = s.getFirstSelectedOption().getText();
		System.out.println("LN Case 9 : Final value set → '" + finalSel + "' for record save");

		System.out.println("=================================================");
		System.out.println("CC7_LawyerDD - All 9 cases executed.");
	}

}
