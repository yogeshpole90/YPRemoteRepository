package Legal_Diary_Pkg;

import java.util.List;
import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;

public class LD25_Status extends LD2_Login {

	public void validateStatus() {
		List<WebElement> list = driver.findElements(By.id("status"));
		if (list.isEmpty() || !list.get(0).isDisplayed() || !list.get(0).isEnabled()) {
			logInfo("Status", "Field availability", "SKIPPED"); return;
		}
		WebElement f = list.get(0); String fn = "Status";
		jse.executeScript("window.scrollBy(0,70)");
		String excelVal = LD_ExcelUtil.getCellData(1, 21);
		logInfo(fn, "Excel value", excelVal);

		log(fn, "Should be visible on page", "true", String.valueOf(f.isDisplayed()), f.isDisplayed());
		log(fn, "Should be enabled", "true", String.valueOf(f.isEnabled()), f.isEnabled());

		f.clear(); f.sendKeys("Passed"); String v = f.getAttribute("value");
		log(fn, "Enter text 'Passed'", "Passed", v, v.equals("Passed"));

		f.clear(); f.sendKeys("12345"); v = f.getAttribute("value");
		log(fn, "Enter numeric '12345'", "Empty (reject)", v, v.isEmpty());

		f.clear(); f.sendKeys("@#$%"); v = f.getAttribute("value");
		log(fn, "Enter special chars", "Empty (reject)", v, v.isEmpty());

		f.clear(); v = f.getAttribute("value");
		log(fn, "Clear field", "Empty", v, v.isEmpty());

		f.clear(); f.sendKeys(excelVal);
		log(fn, "Final value set from Excel", excelVal, f.getAttribute("value"), true);
	}
}
