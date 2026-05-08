package FullPTP_Package;

import org.openqa.selenium.By;
import org.openqa.selenium.Keys;
import org.openqa.selenium.WebElement;

public class F5_PTP_Date extends F2_PTP_Login {

	public void validateDate() throws Exception
	{
		WebElement f = driver.findElement(By.id("dateOfPTPStart"));
		String fn = "PTP Start Date";

		log(fn, "Should be visible on page", "true", String.valueOf(f.isDisplayed()), f.isDisplayed());
		sa.assertTrue(f.isDisplayed());
		log(fn, "Should be enabled", "true", String.valueOf(f.isEnabled()), f.isEnabled());
		sa.assertTrue(f.isEnabled());

		Object[][] data = PTP_ExcelReader.getByTcPrefix(PTP_ExcelReader.TC.PTP_DATE);

		for (Object[] row : data) {
			String input = row[PTP_ExcelReader.Cols.INPUT].toString();
			String expected = row[PTP_ExcelReader.Cols.EXPECTED].toString();
			String desc = row[PTP_ExcelReader.Cols.DESCRIPTION].toString();
			String checkType = row[PTP_ExcelReader.Cols.CHECK_TYPE].toString();

			f.clear();
			if (!input.isEmpty()) f.sendKeys(input);
			f.sendKeys(Keys.TAB);
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
