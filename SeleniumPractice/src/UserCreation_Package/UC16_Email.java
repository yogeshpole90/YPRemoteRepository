package UserCreation_Package;

import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;

public class UC16_Email extends UC2_Login {

	public void validateEmail()
	{
		WebElement f = driver.findElement(By.id("emailId"));
		String fn = "E-mail ID";

		log(fn, "Should be visible on page", "true", String.valueOf(f.isDisplayed()), f.isDisplayed());

		boolean enabled = f.isEnabled();
		String ro = f.getAttribute("readonly");
		logInfo(fn, "Field state", "Enabled=" + enabled + " | ReadOnly=" + ro);

		String autoVal = f.getAttribute("value");
		if (!enabled || ro != null || f.getAttribute("disabled") != null) {
			log(fn, "Read-Only auto-fetched value", "Non-empty", autoVal, !autoVal.isEmpty());
		} else {
			log(fn, "Auto-fetched from Employee ID", "Non-empty", autoVal, !autoVal.isEmpty());
		}
	}
}
