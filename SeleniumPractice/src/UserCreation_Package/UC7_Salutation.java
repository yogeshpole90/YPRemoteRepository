package UserCreation_Package;

import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.Select;

public class UC7_Salutation extends UC2_Login {

	public void validateSalutation() throws Exception
	{
		WebElement f = driver.findElement(By.id("userSalutation"));
		String fn = "Salutation";

		log(fn, "Should be visible on page", "true", String.valueOf(f.isDisplayed()), f.isDisplayed());

		boolean enabled = f.isEnabled();
		String ro = f.getAttribute("readonly");
		String dis = f.getAttribute("disabled");
		logInfo(fn, "Field state", "Enabled=" + enabled + " | ReadOnly=" + ro + " | Disabled=" + dis);

		if (!enabled || ro != null || dis != null) {
			try {
				Select s = new Select(f);
				String autoVal = s.getFirstSelectedOption().getText();
				log(fn, "Read-Only auto-fetched value", "Non-empty", autoVal, !autoVal.equals("Select"));
			} catch (Exception e) {
				logInfo(fn, "Read-Only field", "No value selected");
			}
		} else {
			Select s = new Select(f);
			String autoVal = s.getFirstSelectedOption().getText();
			log(fn, "Auto-fetched from Employee ID", "Non-empty", autoVal, !autoVal.equals("Select"));
		}
	}
}
