package Legal_Diary_Pkg;

import java.util.List;
import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;

public class LD11_ReplevinBondFee extends LD2_Login {

	public void validateReplevinBondFee() {
		List<WebElement> list = driver.findElements(By.id("replevinBondFee_txt"));
		if (list.isEmpty() || !list.get(0).isDisplayed() || !list.get(0).isEnabled()) {
			logInfo("Replevin Bond Fee", "Field availability", "SKIPPED"); return;
		}
		WebElement f = list.get(0); String fn = "Replevin Bond Fee";
		String excelVal = LD_ExcelUtil.getCellData(1, 7);
		logInfo(fn, "Excel value", excelVal);

		log(fn, "Should be visible on page", "true", String.valueOf(f.isDisplayed()), f.isDisplayed());
		log(fn, "Should be enabled", "true", String.valueOf(f.isEnabled()), f.isEnabled());
		f.clear(); f.sendKeys("160"); String v = f.getAttribute("value");
		log(fn, "Enter numeric value '160'", "160", v, v.equals("160"));
		f.clear(); f.sendKeys("abcd"); v = f.getAttribute("value");
		log(fn, "Enter alphabets (should reject)", "Empty", v, v.isEmpty());
		f.clear(); f.sendKeys("@#$%"); v = f.getAttribute("value");
		log(fn, "Enter special chars (should reject)", "Empty", v, v.isEmpty());
		f.clear(); f.sendKeys("-160"); v = f.getAttribute("value");
		log(fn, "Enter negative (should reject)", "Not -160", v, !v.equals("-160"));
		f.clear(); v = f.getAttribute("value");
		log(fn, "Clear field", "Empty", v, v.isEmpty());
		f.clear(); f.sendKeys(excelVal);
		log(fn, "Final value set from Excel", excelVal, f.getAttribute("value"), true);
	}
}
