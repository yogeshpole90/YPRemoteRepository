package UserCreation_Package;

import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;

public class UC19_Checkboxes extends UC2_Login {

	public void validateCheckboxes() throws Exception
	{
		jse.executeScript("window.scrollBy(0,500)"); Thread.sleep(1000);

		// Multiple Branch Access
		String fn1 = "Multiple Branch Access";
		WebElement cb1 = driver.findElement(By.name("mulBranchAcccess"));
		jse.executeScript("arguments[0].scrollIntoView({block:'center'})", cb1); Thread.sleep(500);

		log(fn1, "Should be visible on page", "true", String.valueOf(cb1.isDisplayed()), cb1.isDisplayed());
		log(fn1, "Should be enabled", "true", String.valueOf(cb1.isEnabled()), cb1.isEnabled());
		logInfo(fn1, "Is Selected?", String.valueOf(cb1.isSelected()));

		if (!cb1.isSelected()) { jse.executeScript("arguments[0].click()", cb1); Thread.sleep(500); }
		log(fn1, "Check the checkbox", "true (selected)", String.valueOf(cb1.isSelected()), cb1.isSelected());

		// Allow Concurrent Login
		String fn2 = "Concurrent Login";
		WebElement radioYes = driver.findElement(By.id("allowConcurrentLoginY"));
		WebElement radioNo = driver.findElement(By.id("allowConcurrentLoginN"));
		jse.executeScript("arguments[0].scrollIntoView({block:'center'})", radioYes); Thread.sleep(500);

		log(fn2, "Yes Radio visible", "true", String.valueOf(radioYes.isDisplayed()), radioYes.isDisplayed());
		log(fn2, "Yes Radio enabled", "true", String.valueOf(radioYes.isEnabled()), radioYes.isEnabled());
		logInfo(fn2, "Default state", "Yes=" + radioYes.isSelected() + " | No=" + radioNo.isSelected());

		jse.executeScript("arguments[0].click()", radioYes); Thread.sleep(500);
		log(fn2, "Click YES radio", "true (selected)", String.valueOf(radioYes.isSelected()), radioYes.isSelected());

		jse.executeScript("arguments[0].click()", radioNo); Thread.sleep(500);
		log(fn2, "Click NO radio", "true (selected)", String.valueOf(radioNo.isSelected()), radioNo.isSelected());

		// Forced Auto Expiry
		String fn3 = "Forced Auto Expiry";
		WebElement fpYes = driver.findElement(By.name("forcePwdChgYN"));
		jse.executeScript("arguments[0].scrollIntoView({block:'center'})", fpYes); Thread.sleep(500);

		log(fn3, "Should be visible on page", "true", String.valueOf(fpYes.isDisplayed()), fpYes.isDisplayed());
		log(fn3, "Should be enabled", "true", String.valueOf(fpYes.isEnabled()), fpYes.isEnabled());
		logInfo(fn3, "Is Selected?", String.valueOf(fpYes.isSelected()));

		jse.executeScript("arguments[0].click()", fpYes); Thread.sleep(500);
		log(fn3, "Click checkbox", "Selected", String.valueOf(fpYes.isSelected()), fpYes.isSelected());
	}
}
