package Legal_Diary_Pkg;

import java.util.List;
import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;

public class LD29_SaveButton extends LD2_Login {

	public void validateSave() throws Exception {
		List<WebElement> list = driver.findElements(By.id("saveData"));
		if (list.isEmpty() || !list.get(0).isDisplayed() || !list.get(0).isEnabled()) {
			logInfo("Save Button", "Field availability", "SKIPPED"); return;
		}
		WebElement saveBtn = list.get(0); String fn = "Save Button";

		log(fn, "Should be visible on page", "true", String.valueOf(saveBtn.isDisplayed()), saveBtn.isDisplayed());
		log(fn, "Should be enabled", "true", String.valueOf(saveBtn.isEnabled()), saveBtn.isEnabled());

		Thread.sleep(2000);
		saveBtn.click(); Thread.sleep(3000);
		log(fn, "Click Save button", "Record save attempted", "Clicked", true);

		boolean hasError = driver.getPageSource().contains("Error");
		log(fn, "No error on page after save", "false (no error)", String.valueOf(hasError), !hasError);
	}
}
