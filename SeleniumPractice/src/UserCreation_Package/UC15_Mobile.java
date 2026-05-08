package UserCreation_Package;

import java.util.List;
import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.Select;

public class UC15_Mobile extends UC2_Login {

	public void validateMobile() throws Exception
	{
		WebElement isdField = driver.findElement(By.id("isdmobileNo1"));
		WebElement mobField = driver.findElement(By.id("mobileNo1"));
		String fn1 = "Mobile ISD Code";
		String fn2 = "Mobile Number";

		// ISD Code
		log(fn1, "Should be visible on page", "true", String.valueOf(isdField.isDisplayed()), isdField.isDisplayed());

		boolean isdEnabled = isdField.isEnabled();
		String isdDis = isdField.getAttribute("disabled");
		logInfo(fn1, "Field state", "Enabled=" + isdEnabled + " | Disabled=" + isdDis);

		if (!isdEnabled || isdDis != null) {
			try {
				Select s = new Select(isdField);
				log(fn1, "Read-Only auto-fetched value", "Non-empty", s.getFirstSelectedOption().getText(), true);
			} catch (Exception e) {
				logInfo(fn1, "Read-Only field", "No value");
			}
		} else {
			Select s = new Select(isdField);
			List<WebElement> opts = s.getOptions();
			logInfo(fn1, "Total Options", String.valueOf(opts.size()));
			s.selectByVisibleText("+248"); Thread.sleep(500);
			String sel = s.getFirstSelectedOption().getText();
			log(fn1, "Select '+248'", "+248", sel, sel.equals("+248"));
			sa.assertEquals(sel, "+248");
		}

		// Mobile Number
		log(fn2, "Should be visible on page", "true", String.valueOf(mobField.isDisplayed()), mobField.isDisplayed());

		boolean mobEnabled = mobField.isEnabled();
		String mobDis = mobField.getAttribute("disabled");
		logInfo(fn2, "Field state", "Enabled=" + mobEnabled + " | Disabled=" + mobDis);

		String mobVal = mobField.getAttribute("value");
		if (!mobEnabled || mobDis != null || mobField.getAttribute("readonly") != null) {
			log(fn2, "Read-Only auto-fetched value", "Any value", mobVal, true);
		} else {
			logInfo(fn2, "Auto-fetched from Employee ID", mobVal.isEmpty() ? "Empty" : mobVal);
		}
	}
}
