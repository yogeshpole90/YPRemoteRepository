package Legal_Diary_Pkg;

import java.util.List;
import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;

public class LD17_Remarks extends LD2_Login {

	public void validateRemarks() {
		List<WebElement> list = driver.findElements(By.id("remarks"));
		if (list.isEmpty() || !list.get(0).isDisplayed() || !list.get(0).isEnabled()) {
			logInfo("Remarks", "Field availability", "SKIPPED"); return;
		}
		WebElement f = list.get(0); String fn = "Remarks";
		String excelVal = LD_ExcelUtil.getCellData(1, 13);
		logInfo(fn, "Excel value", excelVal);

		log(fn, "Should be visible on page", "true", String.valueOf(f.isDisplayed()), f.isDisplayed());
		log(fn, "Should be enabled", "true", String.valueOf(f.isEnabled()), f.isEnabled());

		f.clear(); f.sendKeys("Legal diary test remark"); String v = f.getAttribute("value");
		log(fn, "Enter text", "Legal diary test remark", v, v.equals("Legal diary test remark"));

		f.clear(); f.sendKeys("Test@#$%&*!"); v = f.getAttribute("value");
		log(fn, "Enter special chars", "Test@#$%&*!", v, v.equals("Test@#$%&*!"));

		f.clear(); f.sendKeys("12345"); v = f.getAttribute("value");
		log(fn, "Enter numeric", "12345", v, v.equals("12345"));

		f.clear(); v = f.getAttribute("value");
		log(fn, "Clear field", "Empty", v, v.isEmpty());

		logInfo(fn, "Max Length", f.getAttribute("maxlength") != null ? f.getAttribute("maxlength") : "Not set");

		f.clear(); f.sendKeys(excelVal);
		log(fn, "Final value set from Excel", excelVal, f.getAttribute("value"), true);
	}
}
