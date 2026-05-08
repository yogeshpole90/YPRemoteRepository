package Downpayment_Package;

import java.util.List;
import org.openqa.selenium.By;
import org.openqa.selenium.Keys;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.Select;

public class D7_DP_TypeDD extends D2_DP_Login {



	public void validateTypeDD() throws Exception
	{
		System.out.println("=================================================");
		System.out.println("D7 - SCHEDULE TYPE DROPDOWN VALIDATION START");
		System.out.println("=================================================");

		WebElement typeDD = driver.findElement(By.id("scheduleType"));
		Select s = new Select(typeDD);
		List<WebElement> allOptions = s.getOptions();

		log("Schedule Type DD", "Dropdown should be visible on page", "true", String.valueOf(typeDD.isDisplayed()), typeDD.isDisplayed());
		sa.assertTrue(typeDD.isDisplayed(), "Schedule Type not displayed.");

		log("Schedule Type DD", "Dropdown should be enabled/clickable", "true", String.valueOf(typeDD.isEnabled()), typeDD.isEnabled());
		sa.assertTrue(typeDD.isEnabled(), "Schedule Type is disabled.");

		log("Schedule Type DD", "Dropdown should be single select", "false", String.valueOf(s.isMultiple()), !s.isMultiple());
		sa.assertFalse(s.isMultiple(), "Schedule Type is multi-select.");

		log("Schedule Type DD", "Total dropdown options count", "More than 1", String.valueOf(allOptions.size()), allOptions.size() > 1);

		System.out.println("----------------------------------------------");
		System.out.println("All Dropdown Options:");
		for (int i = 0; i < allOptions.size(); i++) {
			System.out.println("  [" + i + "] " + allOptions.get(i).getText() + " (value=" + allOptions.get(i).getAttribute("value") + ")");
		}

		String defaultSel = s.getFirstSelectedOption().getText();
		log("Schedule Type DD", "Default selected option", "Default option", defaultSel, defaultSel != null);

		boolean allEnabled = true;
		for (WebElement option : allOptions) {
			if (!option.isEnabled()) allEnabled = false;
		}
		log("Schedule Type DD", "All options should be enabled", "All enabled", String.valueOf(allEnabled), allEnabled);

		typeDD.sendKeys(Keys.DOWN);
		Thread.sleep(300);
		String keySel = s.getFirstSelectedOption().getText();
		log("Schedule Type DD", "Arrow Down key - keyboard accessible", "Option selected via keyboard", keySel, keySel != null);

		s.selectByVisibleText("Downpayment + Schedule PTP");
		String dpSel = s.getFirstSelectedOption().getText();
		log("Schedule Type DD", "Select 'Downpayment + Schedule PTP' by visible text", "Downpayment + Schedule PTP", dpSel, dpSel.equals("Downpayment + Schedule PTP"));
		sa.assertEquals(dpSel, "Downpayment + Schedule PTP", "Select Downpayment failed.");

		Thread.sleep(2000);

		System.out.println("=================================================");
		System.out.println("D7 - SCHEDULE TYPE DROPDOWN VALIDATION END");
		System.out.println("=================================================");
	}
}


