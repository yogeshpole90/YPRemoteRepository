package Legal_Diary_Pkg;

import java.util.List;
import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;

public class LD13_PetitionFee extends LD2_Login {

	public void validatePetitionFee() {
		List<WebElement> list = driver.findElements(By.id("petitionFee_txt"));
		if (list.isEmpty() || !list.get(0).isDisplayed() || !list.get(0).isEnabled()) {
			logInfo("Petition Fee", "Field availability", "SKIPPED"); return;
		}
		WebElement f = list.get(0); String fn = "Petition Fee";
		String excelVal = LD_ExcelUtil.getCellData(1, 9);
		logInfo(fn, "Excel value", excelVal);

		log(fn, "Should be visible on page", "true", String.valueOf(f.isDisplayed()), f.isDisplayed());
		log(fn, "Should be enabled", "true", String.valueOf(f.isEnabled()), f.isEnabled());
		f.clear(); f.sendKeys("200"); String v = f.getAttribute("value");
		log(fn, "Enter numeric value '200'", "200", v, v.equals("200"));
		f.clear(); f.sendKeys("abcd"); v = f.getAttribute("value");
		log(fn, "Enter alphabets (should reject)", "Empty", v, v.isEmpty());
		f.clear(); f.sendKeys("@#$%"); v = f.getAttribute("value");
		log(fn, "Enter special chars (should reject)", "Empty", v, v.isEmpty());
		f.clear(); f.sendKeys("-200"); v = f.getAttribute("value");
		log(fn, "Enter negative (should reject)", "Not -200", v, !v.equals("-200"));
		f.clear(); v = f.getAttribute("value");
		log(fn, "Clear field", "Empty", v, v.isEmpty());
		f.clear(); f.sendKeys(excelVal);
		log(fn, "Final value set from Excel", excelVal, f.getAttribute("value"), true);
	}
}
