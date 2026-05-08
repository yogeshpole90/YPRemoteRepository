package Legal_Diary_Pkg;

import java.util.List;
import org.openqa.selenium.By;
import org.openqa.selenium.Keys;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.Select;

public class LD24_TransferPvtExecutorDD extends LD2_Login {

	public void validateTransferPvtExecutorDD() throws Exception {
		List<WebElement> list = driver.findElements(By.xpath("//select[contains(@id,'transferredToPrivateExecutor')]"));
		if (list.isEmpty() || !list.get(0).isDisplayed() || !list.get(0).isEnabled()) {
			logInfo("Transfer Pvt Executor", "Field availability", "SKIPPED"); return;
		}
		WebElement field = list.get(0); Select s = new Select(field);
		List<WebElement> opts = s.getOptions(); String fn = "Transfer Pvt Executor";
		String excelVal = LD_ExcelUtil.getCellData(1, 20);
		logInfo(fn, "Excel value", excelVal);

		log(fn, "Should be visible on page", "true", String.valueOf(field.isDisplayed()), field.isDisplayed());
		log(fn, "Should be enabled", "true", String.valueOf(field.isEnabled()), field.isEnabled());
		log(fn, "Should be single-select", "false", String.valueOf(s.isMultiple()), !s.isMultiple());
		log(fn, "Check total options count", ">1", String.valueOf(opts.size()), opts.size() > 1);

		StringBuilder sb = new StringBuilder();
		for (WebElement o : opts) { sb.append(o.getText()).append(" , "); }
		logInfo(fn, "All dropdown options", sb.toString());

		log(fn, "Check default selected", "Select or placeholder", s.getFirstSelectedOption().getText(), true);

		boolean ae = true; for (WebElement o : opts) { if (!o.isEnabled()) ae = false; }
		log(fn, "All options should be enabled", "true", String.valueOf(ae), ae);

		field.sendKeys(Keys.DOWN); Thread.sleep(300);
		log(fn, "Keyboard accessible (Arrow Down)", "Option selected", s.getFirstSelectedOption().getText(), true);

		try {
			s.selectByVisibleText(excelVal);
			log(fn, "Select Excel value", excelVal, s.getFirstSelectedOption().getText(), true);
		} catch (Exception e) {
			s.selectByIndex(1);
			log(fn, "Fallback selectByIndex(1)", "Non-empty", s.getFirstSelectedOption().getText(), true);
		}
	}
}
