package FullPTP_Package;

import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;

public class F4_PTP_OverdueAmt extends F2_PTP_Login {

	public void validateOverdueAmt() throws Exception
	{
		WebElement f = driver.findElement(By.id("overdueAmount"));
		String fn = "Overdue Amount";

		log(fn, "Should be visible on page", "true", String.valueOf(f.isDisplayed()), f.isDisplayed());
		sa.assertTrue(f.isDisplayed());
		log(fn, "Should be enabled", "true", String.valueOf(f.isEnabled()), f.isEnabled());
		sa.assertTrue(f.isEnabled());

		Object[][] data = PTP_ExcelReader.getByTcPrefix(PTP_ExcelReader.TC.OVERDUE_AMT);

		for (Object[] row : data) {
			String input = row[PTP_ExcelReader.Cols.INPUT].toString();
			String expected = row[PTP_ExcelReader.Cols.EXPECTED].toString();
			String desc = row[PTP_ExcelReader.Cols.DESCRIPTION].toString();
			String checkType = row[PTP_ExcelReader.Cols.CHECK_TYPE].toString();

			f.clear();
			if (!input.isEmpty()) f.sendKeys(input);
			String actual = f.getAttribute("value");

			switch (checkType) {
				case "equals":
					log(fn, desc, expected, actual, actual.equals(expected));
					sa.assertEquals(actual, expected, desc);
					break;
				case "notEquals":
					log(fn, desc, "Not " + input, actual, !actual.equals(input));
					break;
				case "empty":
					log(fn, desc, "Empty", actual, actual.isEmpty());
					sa.assertTrue(actual.isEmpty(), desc);
					break;
				case "info":
					logInfo(fn, desc, "Got: " + actual);
					break;
			}
		}
	}
}
