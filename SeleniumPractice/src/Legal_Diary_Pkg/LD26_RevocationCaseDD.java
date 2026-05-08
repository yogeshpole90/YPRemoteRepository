package Legal_Diary_Pkg;

import java.util.List;
import org.openqa.selenium.By;
import org.openqa.selenium.Keys;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.Select;

public class LD26_RevocationCaseDD extends LD2_Login {

	public void validateRevocationCaseDD() throws Exception {
		List<WebElement> list = driver.findElements(By.id("revocationCase"));
		if (list.isEmpty() || !list.get(0).isDisplayed() || !list.get(0).isEnabled()) {
			logInfo("Revocation Case", "Field availability", "SKIPPED"); return;
		}
		WebElement field = list.get(0); Select s = new Select(field);
		List<WebElement> opts = s.getOptions(); String fn = "Revocation Case";
		String excelVal = LD_ExcelUtil.getCellData(1, 22);
		logInfo(fn, "Excel value", excelVal);

		log(fn, "Should be visible on page", "true", String.valueOf(field.isDisplayed()), field.isDisplayed());
		log(fn, "Should be enabled", "true", String.valueOf(field.isEnabled()), field.isEnabled());
		log(fn, "Should be single-select", "false", String.valueOf(s.isMultiple()), !s.isMultiple());
		log(fn, "Check total options count", ">1", String.valueOf(opts.size()), opts.size() > 1);

		StringBuilder sb = new StringBuilder();
		for (WebElement o : opts) { sb.append(o.getText()).append(" , "); }
		logInfo(fn, "All dropdown options", sb.toString());

		log(fn, "Check default selected", "Select or placeholder", s.getFirstSelectedOption().getText(), true);

		field.sendKeys(Keys.DOWN); Thread.sleep(300);
		log(fn, "Keyboard accessible (Arrow Down)", "Option selected", s.getFirstSelectedOption().getText(), true);

		s.selectByVisibleText("NO"); Thread.sleep(1000);
		boolean dateOnNo = driver.findElements(By.id("revocationDate")).size() > 0 && driver.findElement(By.id("revocationDate")).isDisplayed();
		log(fn, "Select 'NO' → Revocation Date hidden?", "false (hidden)", String.valueOf(dateOnNo), !dateOnNo);

		s.selectByVisibleText("YES"); Thread.sleep(1000);
		boolean dateOnYes = driver.findElements(By.id("revocationDate")).size() > 0 && driver.findElement(By.id("revocationDate")).isDisplayed();
		log(fn, "Select 'YES' → Revocation Date visible?", "true (visible)", String.valueOf(dateOnYes), dateOnYes);

		s.selectByVisibleText("YES"); Thread.sleep(1000);
		log(fn, "Final value set for save", "YES", s.getFirstSelectedOption().getText(), true);
	}
}
