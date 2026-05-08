package FullPTP_Package;

import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;

public class F9_PTP_PlannedAmt extends F2_PTP_Login {

	public void validatePlannedAmt() throws Exception
	{
		WebElement f = driver.findElement(By.id("plannedAmt"));
		String fn = "Planned Amount";

		log(fn, "Should be visible on page", "true", String.valueOf(f.isDisplayed()), f.isDisplayed());
		sa.assertTrue(f.isDisplayed());

		boolean isEnabled = f.isEnabled();
		String readOnly = f.getAttribute("readonly");
		logInfo(fn, "Field state", "Enabled=" + isEnabled + " | ReadOnly=" + readOnly);

		Object[][] data = PTP_ExcelReader.getByTcPrefix(PTP_ExcelReader.TC.PLANNED_AMT);

		for (Object[] row : data) {
			String input = row[PTP_ExcelReader.Cols.INPUT].toString();
			String expected = row[PTP_ExcelReader.Cols.EXPECTED].toString();
			String desc = row[PTP_ExcelReader.Cols.DESCRIPTION].toString();
			String checkType = row[PTP_ExcelReader.Cols.CHECK_TYPE].toString();

			jse.executeScript("arguments[0].value='" + input + "'", f);
			String actual = f.getAttribute("value");

			switch (checkType) {
				case "equals":
					log(fn, desc, expected, actual, actual.equals(expected));
					sa.assertEquals(actual, expected, desc);
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
