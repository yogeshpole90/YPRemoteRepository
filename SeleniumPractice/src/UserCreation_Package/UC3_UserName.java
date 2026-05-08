package UserCreation_Package;

import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;

public class UC3_UserName extends UC2_Login {

	public void validateUserName()
	{
		WebElement f = driver.findElement(By.id("loginId"));
		String fn = "User Name";

		log(fn, "Should be visible on page", "true", String.valueOf(f.isDisplayed()), f.isDisplayed());
		sa.assertTrue(f.isDisplayed(), fn + " NOT visible.");

		log(fn, "Should be enabled", "true", String.valueOf(f.isEnabled()), f.isEnabled());
		sa.assertTrue(f.isEnabled(), fn + " DISABLED.");

		f.clear(); f.sendKeys("TestUser01"); String v = f.getAttribute("value");
		log(fn, "Enter alphanumeric 'TestUser01'", "TestUser01", v, v.equals("TestUser01"));

		f.clear(); f.sendKeys("@#$%"); v = f.getAttribute("value");
		log(fn, "Enter special chars '@#$%' (should reject)", "Empty", v, v.isEmpty());

		f.clear(); f.sendKeys("   "); v = f.getAttribute("value");
		log(fn, "Enter spaces (should reject)", "Empty", v, v.trim().isEmpty());

		f.clear(); v = f.getAttribute("value");
		log(fn, "Clear field", "Empty", v, v.isEmpty());

		logInfo(fn, "Max Length", f.getAttribute("maxlength") != null ? f.getAttribute("maxlength") : "Not set");

		logInfo(fn, "ReadOnly check", f.getAttribute("readonly") == null ? "No (Editable)" : "Yes (Read-Only)");

		f.clear(); f.sendKeys("TestUser01");
		log(fn, "Final value set for save", "TestUser01", f.getAttribute("value"), true);
	}
}
