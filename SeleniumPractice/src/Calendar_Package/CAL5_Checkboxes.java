package Calendar_Package;

import org.openqa.selenium.*;
import java.util.List;
import org.testng.annotations.Test;

public class CAL5_Checkboxes extends CAL2_Setup {

	@Test
	public void validateCheckboxes() throws Exception
	{
		System.out.println("=================================================");
		System.out.println("CAL5 - CALENDAR CHECKBOXES VALIDATION START");
		System.out.println("=================================================");

		String[] values = {"99", "100", "101", "102", "CALL", "MAIL", "SITE VISIT"};
		String[] labels = {"PTP", "Site Visited", "Next Court Case", "Next Hearing", "Call", "Mail", "Site Visit"};

		for (int i = 0; i < values.length; i++)
		{
			try
			{
				WebElement cb = driver.findElement(By.xpath("//input[@value='" + values[i] + "']"));
				jse.executeScript("arguments[0].scrollIntoView({block:'center',behavior:'smooth'})", cb);
				Thread.sleep(500);

				// Default selected check
				boolean selected = cb.isSelected();
				log(labels[i] + " Checkbox", "Should be selected by default (value=" + values[i] + ")", "true", String.valueOf(selected), selected);
				sa.assertTrue(selected, labels[i] + " checkbox not selected by default");

				// Uncheck
				jse.executeScript("arguments[0].click()", cb);
				Thread.sleep(500);
				boolean afterUncheck = cb.isSelected();
				log(labels[i] + " Checkbox", "After uncheck — should be deselected", "false", String.valueOf(afterUncheck), !afterUncheck);
				sa.assertFalse(afterUncheck, labels[i] + " still selected after uncheck");

				// Re-check
				jse.executeScript("arguments[0].click()", cb);
				Thread.sleep(500);
				boolean afterRecheck = cb.isSelected();
				log(labels[i] + " Checkbox", "After re-check — should be selected again", "true", String.valueOf(afterRecheck), afterRecheck);
				sa.assertTrue(afterRecheck, labels[i] + " not selected after re-check");
			}
			catch (NoSuchElementException e)
			{
				log(labels[i] + " Checkbox", "Element should exist on page (value=" + values[i] + ")", "Found", "NOT FOUND", false);
				sa.fail(labels[i] + " checkbox not found on page");
			}
		}

		// Total checkboxes count
		List<WebElement> allCb = driver.findElements(By.xpath("//input[@class='tui-full-calendar-checkbox-round']"));
		log("All Checkboxes", "Total round checkboxes count on page", "7", String.valueOf(allCb.size()), allCb.size() == 7);
		sa.assertEquals(allCb.size(), 7, "Expected 7 checkboxes, found " + allCb.size());

		System.out.println("=================================================");
		System.out.println("CAL5 - CALENDAR CHECKBOXES VALIDATION END");
		System.out.println("=================================================");
	}

}
