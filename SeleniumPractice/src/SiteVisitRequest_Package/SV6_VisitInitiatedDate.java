package SiteVisitRequest_Package;

import org.openqa.selenium.*;
import org.testng.annotations.Test;

public class SV6_VisitInitiatedDate extends SV2_Login {

	@Test
	public void validateVisitInitiatedDate() throws Exception
	{
		System.out.println("=================================================");
		System.out.println("SV6 - VISIT INITIATED DATE VALIDATION START");
		System.out.println("=================================================");

		WebElement f = driver.findElement(By.id("visitInitiatedt"));

		log("Visit Initiated Date", "Should be visible", "true", String.valueOf(f.isDisplayed()), f.isDisplayed());
		sa.assertTrue(f.isDisplayed(), "Visit Initiated Date not displayed");

		log("Visit Initiated Date", "Should be enabled", "true", String.valueOf(f.isEnabled()), f.isEnabled());
		sa.assertTrue(f.isEnabled(), "Visit Initiated Date not enabled");

		String def = f.getAttribute("value");
		log("Visit Initiated Date", "Check default value", "Empty or pre-filled", "'" + def + "'", true);

		jse.executeScript("arguments[0].value='99-99-9999'", f); Thread.sleep(300);
		log("Visit Initiated Date", "Set invalid date '99-99-9999'", "Accepted by field (validation on save)", f.getAttribute("value"), true);

		jse.executeScript("arguments[0].value=''", f); Thread.sleep(300);
		log("Visit Initiated Date", "Set empty value", "Empty", "'" + f.getAttribute("value") + "'", f.getAttribute("value").isEmpty());
		sa.assertTrue(f.getAttribute("value").isEmpty(), "Visit Initiated Date not empty after clear");

		jse.executeScript("arguments[0].value='27-03-2026'", f); Thread.sleep(300);
		String v = f.getAttribute("value");
		log("Visit Initiated Date", "Set valid date '27-03-2026'", "27-03-2026", v, v.equals("27-03-2026"));
		sa.assertEquals(v, "27-03-2026", "Visit Initiated Date value mismatch");

		logInfo("Visit Initiated Date", "Check readonly attribute", String.valueOf(f.getAttribute("readonly")));
		logInfo("Visit Initiated Date", "Check placeholder attribute", String.valueOf(f.getAttribute("placeholder")));
		logInfo("Visit Initiated Date", "Check field type attribute", String.valueOf(f.getAttribute("type")));

		jse.executeScript("arguments[0].value='27-03-2026'", f);
		log("Visit Initiated Date", "Final value set for save", "27-03-2026", f.getAttribute("value"), true);

		System.out.println("=================================================");
		System.out.println("SV6 - VISIT INITIATED DATE VALIDATION END");
		System.out.println("=================================================");
	}
}
