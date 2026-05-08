package UserCreation_Package;

import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;

public class UC11_DisplayName extends UC2_Login {

	public void validateDisplayName()
	{
		WebElement f = driver.findElement(By.id("userDisplayName"));
		String fn = "Display Name";

		log(fn, "Should be visible on page", "true", String.valueOf(f.isDisplayed()), f.isDisplayed());
		sa.assertTrue(f.isDisplayed());
		log(fn, "Should be enabled", "true", String.valueOf(f.isEnabled()), f.isEnabled());
		sa.assertTrue(f.isEnabled());

		f.clear(); f.sendKeys("TestDisp"); String v = f.getAttribute("value");
		log(fn, "Enter text 'TestDisp'", "TestDisp", v, v.equals("TestDisp"));

		f.clear(); f.sendKeys("@#$%"); v = f.getAttribute("value");
		log(fn, "Enter special chars (should reject)", "Empty", v, v.isEmpty());

		f.clear(); f.sendKeys("   "); v = f.getAttribute("value");
		log(fn, "Enter spaces (should reject)", "Empty", v, v.trim().isEmpty());

		f.clear(); v = f.getAttribute("value");
		log(fn, "Clear field", "Empty", v, v.isEmpty());

		logInfo(fn, "Max Length", f.getAttribute("maxlength") != null ? f.getAttribute("maxlength") : "Not set");

		f.clear(); f.sendKeys("TestDisp");
		log(fn, "Final value set for save", "TestDisp", f.getAttribute("value"), true);
	}
}
