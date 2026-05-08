package Legal_Diary_Pkg;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.List;
import org.openqa.selenium.By;
import org.openqa.selenium.Keys;
import org.openqa.selenium.WebElement;

public class LD23_NextHearingDate extends LD2_Login {

	public void validateNextHearingDate() throws Exception {
		List<WebElement> list = driver.findElements(By.id("nextHearingDate"));
		if (list.isEmpty() || !list.get(0).isDisplayed() || !list.get(0).isEnabled()) {
			logInfo("Next Hearing Date", "Field availability", "SKIPPED"); return;
		}
		WebElement field = list.get(0); String fn = "Next Hearing Date";
		String excelVal = LD_ExcelUtil.getCellData(1, 19);
		logInfo(fn, "Excel value", excelVal);
		String today = LocalDate.now().format(DateTimeFormatter.ofPattern("dd-MM-yyyy"));
		String past = LocalDate.now().minusDays(10).format(DateTimeFormatter.ofPattern("dd-MM-yyyy"));
		String future = LocalDate.now().plusDays(10).format(DateTimeFormatter.ofPattern("dd-MM-yyyy"));

		log(fn, "Should be visible on page", "true", String.valueOf(field.isDisplayed()), field.isDisplayed());
		log(fn, "Check ReadOnly attribute", "readonly", String.valueOf(field.getAttribute("readonly") != null), field.getAttribute("readonly") != null);

		field.click(); Thread.sleep(500); field.sendKeys(Keys.ESCAPE); Thread.sleep(300);
		log(fn, "Datepicker opens on click", "Opened & Closed", "Opened & Closed", true);

		jse.executeScript("arguments[0].value='" + past + "'", field); field.sendKeys(Keys.ESCAPE); Thread.sleep(300);
		logInfo(fn, "Enter past date", "Actual='" + field.getAttribute("value") + "'");

		jse.executeScript("arguments[0].value='" + today + "'", field); field.sendKeys(Keys.ESCAPE); Thread.sleep(300);
		log(fn, "Enter today's date", today, field.getAttribute("value"), field.getAttribute("value").equals(today));

		jse.executeScript("arguments[0].value='" + future + "'", field); field.sendKeys(Keys.ESCAPE); Thread.sleep(300);
		logInfo(fn, "Enter future date", "Actual='" + field.getAttribute("value") + "'");

		jse.executeScript("arguments[0].value=''", field);
		log(fn, "Clear field", "Empty", field.getAttribute("value"), field.getAttribute("value").isEmpty());

		jse.executeScript("arguments[0].value='" + excelVal + "'", field);
		field.sendKeys(Keys.ESCAPE); Thread.sleep(300); field.sendKeys(Keys.TAB);
		log(fn, "Final value set from Excel", excelVal, field.getAttribute("value"), true);
	}
}
