package Legal_Diary_Pkg;

import java.util.List;
import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;

public class LD28_ChooseDocument extends LD2_Login {

	public void validateChooseDocument() throws Exception {
		String revVal = LD_ExcelUtil.getCellData(1, 22);
		if (!revVal.equalsIgnoreCase("YES")) {
			logInfo("Choose Document", "Revocation=" + revVal, "SKIPPED - not YES"); return;
		}
		List<WebElement> list = driver.findElements(By.id("chooseDocument"));
		if (list.isEmpty() || !list.get(0).isDisplayed()) {
			logInfo("Choose Document", "Field availability", "SKIPPED"); return;
		}
		WebElement fileUpload = driver.findElement(By.id("documentData"));
		String fn = "Choose Document";
		String excelVal = LD_ExcelUtil.getCellData(1, 24);
		logInfo(fn, "Excel value (file path)", excelVal);

		log(fn, "Should be visible on page", "true", String.valueOf(fileUpload.isDisplayed()), fileUpload.isDisplayed());
		log(fn, "Should be enabled", "true", String.valueOf(fileUpload.isEnabled()), fileUpload.isEnabled());

		if (!excelVal.isEmpty()) {
			fileUpload.sendKeys(excelVal);
			log(fn, "Upload file from Excel path", excelVal, fileUpload.getAttribute("value"), true);
		} else {
			logInfo(fn, "Upload file", "SKIPPED - No file path in Excel");
		}
	}
}
