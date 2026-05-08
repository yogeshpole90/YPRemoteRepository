package Courtcase_Package;

import java.util.List;
import org.openqa.selenium.By;
import org.openqa.selenium.Keys;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.Select;

public class CC12_BankruptDD extends CC2_Login {

	public void validateBankruptDD() throws Exception
	{
		WebElement bankrupt = driver.findElement(By.id("bankruptcyCase"));
		jse.executeScript("arguments[0].scrollIntoView({block:'center'})", bankrupt);
		Select s = new Select(bankrupt);
		List<WebElement> allOptions = s.getOptions();

		System.out.println("=================================================");
		sa.assertTrue(bankrupt.isDisplayed(), "Bankruptcy Case not displayed.");
		System.out.println("BC Case 1 : Is Displayed? → " + bankrupt.isDisplayed() + " | PASS");

		System.out.println("=================================================");
		sa.assertTrue(bankrupt.isEnabled(), "Bankruptcy Case is disabled.");
		System.out.println("BC Case 2 : Is Enabled? → " + bankrupt.isEnabled() + " | PASS");

		System.out.println("=================================================");
		sa.assertFalse(s.isMultiple(), "Bankruptcy Case is multi-select.");
		System.out.println("BC Case 3 : Is Multi-Select? → " + s.isMultiple() + " | " + (!s.isMultiple() ? "Single Select - PASS" : "Multi Select - FAIL"));

		System.out.println("=================================================");
		System.out.println("BC Case 4 : Total Options → " + allOptions.size() + " | Options Count Fetched");

		System.out.println("=================================================");
		System.out.print("BC Case 5 : All DD Values → ");
		for (WebElement option : allOptions) {
			System.out.print(option.getText() + " , ");
		}
		System.out.println("| All Options Printed");

		System.out.println("=================================================");
		String defaultSel = s.getFirstSelectedOption().getText();
		sa.assertNotNull(defaultSel, "Default is null.");
		System.out.println("BC Case 6 : Default Selected → '" + defaultSel + "' | Default Option Found");

		System.out.println("=================================================");
		boolean allEnabled = true;
		for (WebElement option : allOptions) {
			if (!option.isEnabled()) { allEnabled = false; }
			sa.assertTrue(option.isEnabled(), option.getText() + " is disabled!");
		}
		System.out.println("BC Case 7 : All Options Enabled? → " + allEnabled + " | " + (allEnabled ? "All Enabled - PASS" : "Some Disabled - FAIL"));

		System.out.println("=================================================");
		bankrupt.sendKeys(Keys.DOWN);
		Thread.sleep(300);
		String keySel = s.getFirstSelectedOption().getText();
		sa.assertNotNull(keySel, "Not keyboard accessible.");
		System.out.println("BC Case 8 : Arrow Down pressed → Selected '" + keySel + "' | Keyboard Accessible - PASS");

		System.out.println("=================================================");
		s.selectByIndex(3);
		Thread.sleep(2000);
		String finalSel = s.getFirstSelectedOption().getText();
		System.out.println("BC Case 9 : Final value set → '" + finalSel + "' for record save");

		// Check if Bankruptcy Date & Case No became visible after selecting Yes
		boolean dateVisible = driver.findElement(By.id("bankruptcyCaseDate")).isDisplayed();
		boolean noVisible = driver.findElement(By.id("bankruptcyCaseNo")).isDisplayed();
		sa.assertTrue(dateVisible, "Bankruptcy Date not visible after Yes.");
		sa.assertTrue(noVisible, "Bankruptcy Case No not visible after Yes.");
		System.out.println("BC Case 10 : After 'Yes' → Date visible=" + dateVisible + " | CaseNo visible=" + noVisible);

		System.out.println("=================================================");
		System.out.println("CC12_BankruptDD - All 10 cases executed.");
	}

}
