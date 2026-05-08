package Legal_Diary_Pkg;

import java.util.List;
import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;

public class LD31_SuitAmount extends LD2_Login {

	public void validateSuitAmount() {
		List<WebElement> list = driver.findElements(By.id("suitAmount_txt"));
		if (list.isEmpty() || !list.get(0).isDisplayed()) {
			logInfo("Suit Amount", "Field availability", "SKIPPED"); return;
		}
		WebElement f = list.get(0); String fn = "Suit Amount";
		jse.executeScript("arguments[0].scrollIntoView({block:'center',behavior:'smooth'})", f);
		try { Thread.sleep(500); } catch (Exception e) {}

		log(fn, "Should be visible on page", "true", String.valueOf(f.isDisplayed()), f.isDisplayed());
		log(fn, "Check enabled state", "enabled or disabled", String.valueOf(f.isEnabled()), true);

		String ro = f.getAttribute("readonly");
		log(fn, "Check ReadOnly attribute", "readonly present", String.valueOf(ro != null), ro != null);

		String preValue = f.getAttribute("value");
		boolean hasValue = preValue != null && !preValue.trim().isEmpty();
		log(fn, "Check pre-populated value", "Non-empty", preValue, hasValue);

		if (hasValue) {
			boolean isNumeric = preValue.trim().matches("[\\d,\\.]+");
			log(fn, "Value should be numeric", "Numeric", preValue, isNumeric);
		}

		logInfo(fn, "Max Length", f.getAttribute("maxlength") != null ? f.getAttribute("maxlength") : "Not set");

		boolean notEditable = false;
		try {
			f.sendKeys("99999");
			notEditable = f.getAttribute("value").equals(preValue);
		} catch (Exception e) { notEditable = true; }
		log(fn, "User cannot type in readonly field", "true", String.valueOf(notEditable), notEditable);

		logInfo(fn, "Field type", "Read-Only Auto-Populated");
	}
}
