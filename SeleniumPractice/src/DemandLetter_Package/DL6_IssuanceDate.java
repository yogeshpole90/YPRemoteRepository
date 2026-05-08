package DemandLetter_Package;

import org.openqa.selenium.By;
import org.openqa.selenium.Keys;
import org.openqa.selenium.WebElement;

public class DL6_IssuanceDate extends DL2_Login {

	public void validateIssuanceDate() throws Exception
	{
		System.out.println("=================================================");
		System.out.println("DL6 - ISSUANCE DATE VALIDATION START");
		System.out.println("=================================================");

		WebElement dt;

		// TC1: Displayed
		dt = driver.findElement(By.id("sendingDate"));
		log("Issuance Date", "Displayed", "true", String.valueOf(dt.isDisplayed()), dt.isDisplayed());
		sa.assertTrue(dt.isDisplayed(), "Not displayed");

		// TC2: Enabled
		log("Issuance Date", "Enabled", "true", String.valueOf(dt.isEnabled()), dt.isEnabled());
		sa.assertTrue(dt.isEnabled(), "Disabled");

		// TC3: Placeholder
		String ph = dt.getAttribute("placeholder");
		log("Issuance Date", "Placeholder", "Please enter date here", ph, "Please enter date here".equals(ph));

		// TC4: hasDatepicker class
		String cls = dt.getAttribute("class");
		log("Issuance Date", "Has datepicker class", "hasDatepicker", cls, cls != null && cls.contains("hasDatepicker"));

		// TC5: Input type
		log("Issuance Date", "Input type", "text", dt.getAttribute("type"), "text".equals(dt.getAttribute("type")));

		// TC6: Empty initially
		String initVal = dt.getAttribute("value");
		log("Issuance Date", "Empty initially", "Empty", "'" + initVal + "'", initVal == null || initVal.isEmpty());

		// TC7: Valid date
		dt = driver.findElement(By.id("sendingDate"));
		dt.clear(); dt.sendKeys("22-04-2026"); dt.sendKeys(Keys.TAB); Thread.sleep(500);
		log("Issuance Date", "Enter valid date '22-04-2026'", "22-04-2026", driver.findElement(By.id("sendingDate")).getAttribute("value"), true);

		// TC8: Clear
		dt = driver.findElement(By.id("sendingDate"));
		dt.clear(); Thread.sleep(300);
		log("Issuance Date", "Clear field", "Empty", "'" + driver.findElement(By.id("sendingDate")).getAttribute("value") + "'", driver.findElement(By.id("sendingDate")).getAttribute("value").isEmpty());

		// TC9: Alphabets — should reject
		dt = driver.findElement(By.id("sendingDate"));
		dt.clear(); dt.sendKeys("abcdef"); dt.sendKeys(Keys.TAB); Thread.sleep(500);
		String alphaVal = driver.findElement(By.id("sendingDate")).getAttribute("value");
		log("Issuance Date", "Enter alphabets 'abcdef'", "Reject", "'" + alphaVal + "'", alphaVal.isEmpty() || !alphaVal.equals("abcdef"));

		// TC10: Special chars — should reject
		dt = driver.findElement(By.id("sendingDate"));
		dt.clear(); dt.sendKeys("@#$%^&"); dt.sendKeys(Keys.TAB); Thread.sleep(500);
		String splVal = driver.findElement(By.id("sendingDate")).getAttribute("value");
		log("Issuance Date", "Enter special chars '@#$%^&'", "Reject", "'" + splVal + "'", splVal.isEmpty() || !splVal.equals("@#$%^&"));

		// TC11: Day 32 — invalid
		dt = driver.findElement(By.id("sendingDate"));
		dt.clear(); dt.sendKeys("32-12-2021"); dt.sendKeys(Keys.TAB); Thread.sleep(500);
		String day32 = driver.findElement(By.id("sendingDate")).getAttribute("value");
		log("Issuance Date", "Day 32 '32-12-2021'", "Reject", "'" + day32 + "'", !day32.equals("32-12-2021") || day32.isEmpty());

		// TC12: Month 13 — invalid
		dt = driver.findElement(By.id("sendingDate"));
		dt.clear(); dt.sendKeys("15-13-2021"); dt.sendKeys(Keys.TAB); Thread.sleep(500);
		String month13 = driver.findElement(By.id("sendingDate")).getAttribute("value");
		log("Issuance Date", "Month 13 '15-13-2021'", "Reject", "'" + month13 + "'", !month13.equals("15-13-2021") || month13.isEmpty());

		// TC13: Feb 29 non-leap — invalid
		dt = driver.findElement(By.id("sendingDate"));
		dt.clear(); dt.sendKeys("29-02-2023"); dt.sendKeys(Keys.TAB); Thread.sleep(500);
		String feb29 = driver.findElement(By.id("sendingDate")).getAttribute("value");
		log("Issuance Date", "Feb 29 non-leap '29-02-2023'", "Reject", "'" + feb29 + "'", !feb29.equals("29-02-2023") || feb29.isEmpty());

		// TC14: Feb 29 leap — valid
		dt = driver.findElement(By.id("sendingDate"));
		dt.clear(); dt.sendKeys("29-02-2024"); dt.sendKeys(Keys.TAB); Thread.sleep(500);
		String feb29Leap = driver.findElement(By.id("sendingDate")).getAttribute("value");
		log("Issuance Date", "Feb 29 leap '29-02-2024'", "Accept", "'" + feb29Leap + "'", true);

		// TC15: Day 00 — invalid
		dt = driver.findElement(By.id("sendingDate"));
		dt.clear(); dt.sendKeys("00-12-2021"); dt.sendKeys(Keys.TAB); Thread.sleep(500);
		String day00 = driver.findElement(By.id("sendingDate")).getAttribute("value");
		log("Issuance Date", "Day 00 '00-12-2021'", "Reject", "'" + day00 + "'", !day00.equals("00-12-2021") || day00.isEmpty());

		// TC16: Month 00 — invalid
		dt = driver.findElement(By.id("sendingDate"));
		dt.clear(); dt.sendKeys("15-00-2021"); dt.sendKeys(Keys.TAB); Thread.sleep(500);
		String month00 = driver.findElement(By.id("sendingDate")).getAttribute("value");
		log("Issuance Date", "Month 00 '15-00-2021'", "Reject", "'" + month00 + "'", !month00.equals("15-00-2021") || month00.isEmpty());

		// TC17: Future date
		dt = driver.findElement(By.id("sendingDate"));
		dt.clear(); dt.sendKeys("01-01-2099"); dt.sendKeys(Keys.TAB); Thread.sleep(500);
		String future = driver.findElement(By.id("sendingDate")).getAttribute("value");
		log("Issuance Date", "Far future '01-01-2099'", "Check", "'" + future + "'", true);

		// TC18: Old past date
		dt = driver.findElement(By.id("sendingDate"));
		dt.clear(); dt.sendKeys("01-01-1900"); dt.sendKeys(Keys.TAB); Thread.sleep(500);
		String oldPast = driver.findElement(By.id("sendingDate")).getAttribute("value");
		log("Issuance Date", "Old past '01-01-1900'", "Check", "'" + oldPast + "'", true);

		// TC19: Spaces only
		dt = driver.findElement(By.id("sendingDate"));
		dt.clear(); dt.sendKeys("     "); dt.sendKeys(Keys.TAB); Thread.sleep(500);
		String spaces = driver.findElement(By.id("sendingDate")).getAttribute("value");
		log("Issuance Date", "Spaces only", "Reject", "'" + spaces + "'", spaces.trim().isEmpty());

		// TC20: Numeric without separator
		dt = driver.findElement(By.id("sendingDate"));
		dt.clear(); dt.sendKeys("22042026"); dt.sendKeys(Keys.TAB); Thread.sleep(500);
		String noSep = driver.findElement(By.id("sendingDate")).getAttribute("value");
		log("Issuance Date", "Without separator '22042026'", "Check", "'" + noSep + "'", true);

		// TC21: Alphanumeric mix
		dt = driver.findElement(By.id("sendingDate"));
		dt.clear(); dt.sendKeys("1a-2b-20cd"); dt.sendKeys(Keys.TAB); Thread.sleep(500);
		String mix = driver.findElement(By.id("sendingDate")).getAttribute("value");
		log("Issuance Date", "Alphanumeric '1a-2b-20cd'", "Reject", "'" + mix + "'", !mix.equals("1a-2b-20cd") || mix.isEmpty());

		// TC22: Final valid date for save
		dt = driver.findElement(By.id("sendingDate"));
		dt.clear(); dt.sendKeys("22-04-2026"); dt.sendKeys(Keys.TAB); Thread.sleep(500);
		log("Issuance Date", "Final value '22-04-2026'", "22-04-2026", driver.findElement(By.id("sendingDate")).getAttribute("value"), true);

		System.out.println("=================================================");
		System.out.println("DL6 - ISSUANCE DATE VALIDATION END");
		System.out.println("=================================================");
	}
}
