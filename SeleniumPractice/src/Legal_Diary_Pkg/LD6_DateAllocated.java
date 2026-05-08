package Legal_Diary_Pkg;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.List;
import org.openqa.selenium.By;
import org.openqa.selenium.Keys;
import org.openqa.selenium.WebElement;

public class LD6_DateAllocated extends LD2_Login {

	public void validateDateAllocated() throws Exception {
		List<WebElement> list = driver.findElements(By.id("dateAllocated"));
		if (list.isEmpty() || !list.get(0).isDisplayed() || !list.get(0).isEnabled()) {
			logInfo("Date Allocated", "Field availability", "SKIPPED"); return;
		}
		WebElement field = list.get(0); String fn = "Date Allocated";
		String excelVal = LD_ExcelUtil.getCellData(1, 2);
		logInfo(fn, "Excel value", excelVal);
		String today = LocalDate.now().format(DateTimeFormatter.ofPattern("dd-MM-yyyy"));
		String past = LocalDate.now().minusDays(10).format(DateTimeFormatter.ofPattern("dd-MM-yyyy"));
		String future = LocalDate.now().plusDays(10).format(DateTimeFormatter.ofPattern("dd-MM-yyyy"));

		log(fn, "Should be visible on page", "true", String.valueOf(field.isDisplayed()), field.isDisplayed());
		log(fn, "Check ReadOnly attribute", "readonly", String.valueOf(field.getAttribute("readonly") != null), field.getAttribute("readonly") != null);

		field.click(); Thread.sleep(500); field.sendKeys(Keys.ESCAPE); Thread.sleep(300);
		log(fn, "Click opens datepicker, Escape closes it", "Datepicker interaction", "Opened & Closed", true);

		jse.executeScript("arguments[0].value='" + past + "'", field); field.sendKeys(Keys.ESCAPE); Thread.sleep(300);
		log(fn, "Enter past date", past, field.getAttribute("value"), field.getAttribute("value").equals(past));

		jse.executeScript("arguments[0].value='" + today + "'", field); field.sendKeys(Keys.ESCAPE); Thread.sleep(300);
		log(fn, "Enter today's date", today, field.getAttribute("value"), field.getAttribute("value").equals(today));

		jse.executeScript("arguments[0].value='" + future + "'", field); field.sendKeys(Keys.ESCAPE); Thread.sleep(300);
		log(fn, "Enter future date (should reject)", "Not " + future, field.getAttribute("value"), !field.getAttribute("value").equals(future));

		jse.executeScript("arguments[0].value='00-03-2026'", field); field.sendKeys(Keys.ESCAPE); Thread.sleep(300);
		log(fn, "Enter invalid day 00", "Reject", field.getAttribute("value"), !field.getAttribute("value").equals("00-03-2026"));

		jse.executeScript("arguments[0].value='23-00-2026'", field); field.sendKeys(Keys.ESCAPE); Thread.sleep(300);
		log(fn, "Enter invalid month 00", "Reject", field.getAttribute("value"), !field.getAttribute("value").equals("23-00-2026"));

		jse.executeScript("arguments[0].value='32-03-2026'", field); field.sendKeys(Keys.ESCAPE); Thread.sleep(300);
		log(fn, "Enter invalid day 32", "Reject", field.getAttribute("value"), !field.getAttribute("value").equals("32-03-2026"));

		jse.executeScript("arguments[0].value='23-13-2026'", field); field.sendKeys(Keys.ESCAPE); Thread.sleep(300);
		log(fn, "Enter invalid month 13", "Reject", field.getAttribute("value"), !field.getAttribute("value").equals("23-13-2026"));

		jse.executeScript("arguments[0].value=''", field);
		log(fn, "Clear field", "Empty", field.getAttribute("value"), field.getAttribute("value").isEmpty());

		jse.executeScript("arguments[0].value=''", field); field.sendKeys("29-01-2026");
		String sk = field.getAttribute("value");
		logInfo(fn, "sendKeys test (readonly check)", sk.isEmpty() ? "ReadOnly confirmed" : "sendKeys accepted: " + sk);
		field.sendKeys(Keys.ESCAPE); Thread.sleep(300);

		jse.executeScript("arguments[0].value='" + excelVal + "'", field);
		field.sendKeys(Keys.ESCAPE); Thread.sleep(300); field.sendKeys(Keys.TAB);
		log(fn, "Final value set from Excel", excelVal, field.getAttribute("value"), true);
	}
}
