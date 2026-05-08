package UserCreation_Package;

import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;

public class UC9_MiddleName extends UC2_Login {

	public void validateMiddleName()
	{
		WebElement f = driver.findElement(By.id("userMName"));
		String fn = "Middle Name";

		log(fn, "Should be visible on page", "true", String.valueOf(f.isDisplayed()), f.isDisplayed());

		boolean enabled = f.isEnabled();
		String ro = f.getAttribute("readonly");
		logInfo(fn, "Field state", "Enabled=" + enabled + " | ReadOnly=" + ro);

		String autoVal = f.getAttribute("value");
		if (!enabled || ro != null || f.getAttribute("disabled") != null) {
			log(fn, "Read-Only auto-fetched value", "Any value", autoVal, true);
		} else {
			logInfo(fn, "Auto-fetched from Employee ID", autoVal.isEmpty() ? "Empty (may not have middle name)" : autoVal);
		}
	}
}
