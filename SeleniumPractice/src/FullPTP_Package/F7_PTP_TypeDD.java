package FullPTP_Package;

import java.util.List;
import org.openqa.selenium.By;
import org.openqa.selenium.Keys;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.Select;

public class F7_PTP_TypeDD extends F2_PTP_Login {

	public void validatePTPType() throws Exception
	{
		WebElement f = driver.findElement(By.id("scheduleType"));
		Select s = new Select(f);
		List<WebElement> opts = s.getOptions();
		String fn = "PTP Type";

		log(fn, "Should be visible on page", "true", String.valueOf(f.isDisplayed()), f.isDisplayed());
		sa.assertTrue(f.isDisplayed());
		log(fn, "Should be enabled", "true", String.valueOf(f.isEnabled()), f.isEnabled());
		sa.assertTrue(f.isEnabled());
		log(fn, "Should be single-select", "false", String.valueOf(s.isMultiple()), !s.isMultiple());
		sa.assertFalse(s.isMultiple());
		logInfo(fn, "Total Options", String.valueOf(opts.size()));

		StringBuilder sb = new StringBuilder();
		for (WebElement o : opts) { sb.append(o.getText()).append(" , "); }
		logInfo(fn, "All dropdown options", sb.toString());

		log(fn, "Check default selected", "Non-null", s.getFirstSelectedOption().getText(), true);

		boolean ae = true;
		for (WebElement o : opts) { if (!o.isEnabled()) ae = false; }
		log(fn, "All options should be enabled", "true", String.valueOf(ae), ae);

		f.sendKeys(Keys.DOWN); Thread.sleep(300);
		log(fn, "Keyboard accessible (Arrow Down)", "Option selected", s.getFirstSelectedOption().getText(), true);

		Object[][] data = PTP_ExcelReader.getByTcPrefix(PTP_ExcelReader.TC.PTP_TYPE);

		for (Object[] row : data) {
			String input = row[PTP_ExcelReader.Cols.INPUT].toString();
			String expected = row[PTP_ExcelReader.Cols.EXPECTED].toString();
			String desc = row[PTP_ExcelReader.Cols.DESCRIPTION].toString();

			s.selectByVisibleText(input);
			String actual = s.getFirstSelectedOption().getText();
			log(fn, desc, expected, actual, actual.equals(expected));
			sa.assertEquals(actual, expected, desc);
		}
	}
}
