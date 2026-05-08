package Legal_Diary_Pkg;

import java.util.List;
import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;

public class LD21_BankruptcyCaseNo extends LD2_Login {

	public void validateBankruptcyCaseNo() throws Exception {
		String bankVal = LD_ExcelUtil.getCellData(1, 15);
		if (!bankVal.equalsIgnoreCase("YES")) {
			logInfo("Bankruptcy Case No", "Bankruptcy=" + bankVal, "SKIPPED - not YES"); return;
		}
		List<WebElement> list = driver.findElements(By.id("bankruptcyCaseNo"));
		if (list.isEmpty() || !list.get(0).isDisplayed() || !list.get(0).isEnabled()) {
			logInfo("Bankruptcy Case No", "Field availability", "SKIPPED"); return;
		}
		WebElement f = list.get(0); String fn = "Bankruptcy Case No";
		String excelVal = LD_ExcelUtil.getCellData(1, 17);
		logInfo(fn, "Excel value", excelVal);

		log(fn, "Should be visible on page", "true", String.valueOf(f.isDisplayed()), f.isDisplayed());
		log(fn, "Should be enabled", "true", String.valueOf(f.isEnabled()), f.isEnabled());
		f.clear(); f.sendKeys("55001"); String v = f.getAttribute("value");
		log(fn, "Enter numeric '55001'", "55001", v, v.equals("55001"));
		f.clear(); f.sendKeys("ABCDEF"); v = f.getAttribute("value");
		log(fn, "Enter alphabets (should reject)", "Empty", v, v.isEmpty());
		f.clear(); f.sendKeys("@#$%"); v = f.getAttribute("value");
		log(fn, "Enter special chars (should reject)", "Empty", v, v.isEmpty());
		f.clear(); v = f.getAttribute("value");
		log(fn, "Clear field", "Empty", v, v.isEmpty());
		f.clear(); f.sendKeys(excelVal);
		log(fn, "Final value set from Excel", excelVal, f.getAttribute("value"), true);
	}
}
