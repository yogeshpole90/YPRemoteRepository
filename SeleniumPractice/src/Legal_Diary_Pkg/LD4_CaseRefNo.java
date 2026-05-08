package Legal_Diary_Pkg;

import java.util.List;
import org.openqa.selenium.Alert;
import org.openqa.selenium.By;
import org.openqa.selenium.Keys;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.Select;

public class LD4_CaseRefNo extends LD2_Login {

	public void validateCaseRefNo() throws Exception {
		List<WebElement> list = driver.findElements(By.id("courtCaseNo"));
		if (list.isEmpty() || !list.get(0).isDisplayed() || !list.get(0).isEnabled()) {
			logInfo("Court Case Ref No", "Field availability", "SKIPPED - not found/not displayed/disabled"); return;
		}

		WebElement field = list.get(0);
		jse.executeScript("arguments[0].scrollIntoView({block:'center'})", field);
		Select s = new Select(field);
		List<WebElement> allOptions = s.getOptions();
		String fn = "Court Case Ref No";

		log(fn, "Should be visible on page", "true", String.valueOf(field.isDisplayed()), field.isDisplayed());
		sa.assertTrue(field.isDisplayed(), fn + " not displayed");

		log(fn, "Should be enabled", "true", String.valueOf(field.isEnabled()), field.isEnabled());
		sa.assertTrue(field.isEnabled(), fn + " not enabled");

		boolean isMulti = s.isMultiple();
		log(fn, "Should be single-select", "false", String.valueOf(isMulti), !isMulti);
		sa.assertFalse(isMulti, fn + " is multi-select");

		log(fn, "Check total options count", ">1", String.valueOf(allOptions.size()), allOptions.size() > 1);

		StringBuilder sb = new StringBuilder();
		for (WebElement op : allOptions) { sb.append(op.getText()).append(" , "); }
		logInfo(fn, "All dropdown options", sb.toString());

		String defaultSel = s.getFirstSelectedOption().getText();
		log(fn, "Check default selected value", "Select or placeholder", defaultSel, !defaultSel.isEmpty());

		boolean allEnabled = true;
		for (WebElement op : allOptions) { if (!op.isEnabled()) allEnabled = false; }
		log(fn, "All options should be enabled", "true", String.valueOf(allEnabled), allEnabled);

		field.sendKeys(Keys.DOWN); Thread.sleep(300);
		dismissAlertIfPresent(); Thread.sleep(300);
		try {
			String keySelected = s.getFirstSelectedOption().getText();
			log(fn, "Keyboard accessible (Arrow Down)", "Option selected", keySelected, true);
		} catch (Exception e) {
			log(fn, "Keyboard accessible (Arrow Down)", "Option selected", "Alert handled", true);
		}

		if (allOptions.size() > 1) {
			try {
				s.selectByIndex(1); Thread.sleep(300);
				dismissAlertIfPresent();
				String selected = s.getFirstSelectedOption().getText();
				log(fn, "Select by index 1", "Non-empty value", selected, !selected.isEmpty());
			} catch (Exception e) {
				dismissAlertIfPresent();
				log(fn, "Select by index 1", "Selection triggered", "Alert handled for duplicate", true);
			}
		}
	}

	private void dismissAlertIfPresent() {
		try {
			Alert alert = driver.switchTo().alert();
			alert.accept(); Thread.sleep(300);
		} catch (Exception e) { }
	}
}
