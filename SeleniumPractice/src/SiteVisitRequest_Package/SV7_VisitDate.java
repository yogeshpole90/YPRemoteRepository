package SiteVisitRequest_Package;

import org.openqa.selenium.*;
import org.testng.annotations.Test;

public class SV7_VisitDate extends SV2_Login {

	@Test
	public void validateVisitDate() throws Exception
	{
		System.out.println("=================================================");
		System.out.println("SV7 - VISIT DATE VALIDATION START");
		System.out.println("=================================================");

		WebElement f = driver.findElement(By.id("visitDate"));

		log("Visit Date", "Should be visible", "true", String.valueOf(f.isDisplayed()), f.isDisplayed());
		sa.assertTrue(f.isDisplayed(), "Visit Date not displayed");

		log("Visit Date", "Should be enabled", "true", String.valueOf(f.isEnabled()), f.isEnabled());
		sa.assertTrue(f.isEnabled(), "Visit Date not enabled");

		log("Visit Date", "Check default value", "Empty or pre-filled", "'" + f.getAttribute("value") + "'", true);

		jse.executeScript("arguments[0].value='99-99-9999'", f); Thread.sleep(300);
		log("Visit Date", "Set invalid date '99-99-9999'", "Accepted by field", f.getAttribute("value"), true);

		jse.executeScript("arguments[0].value=''", f); Thread.sleep(300);
		log("Visit Date", "Set empty value", "Empty", "'" + f.getAttribute("value") + "'", f.getAttribute("value").isEmpty());
		sa.assertTrue(f.getAttribute("value").isEmpty(), "Visit Date not empty");

		jse.executeScript("arguments[0].value='28-03-2026'", f); Thread.sleep(300);
		String v = f.getAttribute("value");
		log("Visit Date", "Set valid date '28-03-2026'", "28-03-2026", v, v.equals("28-03-2026"));
		sa.assertEquals(v, "28-03-2026", "Visit Date value mismatch");

		logInfo("Visit Date", "Check readonly attribute", String.valueOf(f.getAttribute("readonly")));
		logInfo("Visit Date", "Check placeholder attribute", String.valueOf(f.getAttribute("placeholder")));
		logInfo("Visit Date", "Check field type attribute", String.valueOf(f.getAttribute("type")));

		jse.executeScript("arguments[0].value='28-03-2026'", f);
		log("Visit Date", "Final value set for save", "28-03-2026", f.getAttribute("value"), true);

		System.out.println("=================================================");
		System.out.println("SV7 - VISIT DATE VALIDATION END");
		System.out.println("=================================================");
	}
}
