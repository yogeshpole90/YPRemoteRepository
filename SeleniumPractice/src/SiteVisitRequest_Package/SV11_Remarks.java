package SiteVisitRequest_Package;

import org.openqa.selenium.*;
import org.testng.annotations.Test;

public class SV11_Remarks extends SV2_Login {

	@Test
	public void validateRemarks() throws Exception
	{
		System.out.println("=================================================");
		System.out.println("SV11 - REMARKS VALIDATION START");
		System.out.println("=================================================");

		WebElement f = driver.findElement(By.id("remarks"));

		log("Remarks", "Should be visible", "true", String.valueOf(f.isDisplayed()), f.isDisplayed());
		sa.assertTrue(f.isDisplayed(), "Remarks not displayed");

		log("Remarks", "Should be enabled", "true", String.valueOf(f.isEnabled()), f.isEnabled());
		sa.assertTrue(f.isEnabled(), "Remarks not enabled");

		f.clear();
		log("Remarks", "Clear field — should be empty", "Empty", "'" + f.getAttribute("value") + "'", f.getAttribute("value").isEmpty());

		f.sendKeys("Test Remark Selenium");
		log("Remarks", "Enter text 'Test Remark Selenium'", "Test Remark Selenium", f.getAttribute("value"), f.getAttribute("value").equals("Test Remark Selenium"));
		sa.assertEquals(f.getAttribute("value"), "Test Remark Selenium", "Remarks mismatch");

		f.clear(); f.sendKeys("@#$%&*!()");
		log("Remarks", "Enter special chars '@#$%&*!()'", "@#$%&*!()", f.getAttribute("value"), f.getAttribute("value").equals("@#$%&*!()"));

		f.clear(); f.sendKeys("12345");
		log("Remarks", "Enter numeric '12345'", "12345", f.getAttribute("value"), f.getAttribute("value").equals("12345"));

		f.clear(); f.sendKeys("This is a very long remark text to check the maximum character limit of the remarks field");
		log("Remarks", "Enter long text — check max length", "Accepted", "Length=" + f.getAttribute("value").length(), true);

		f.clear(); f.sendKeys("   ");
		log("Remarks", "Enter spaces only", "Spaces", "'" + f.getAttribute("value") + "'", true);

		f.clear(); f.sendKeys("' OR 1=1 --");
		log("Remarks", "Enter SQL injection text", "Accepted by field", f.getAttribute("value"), true);

		f.clear(); f.sendKeys("<script>alert('test')</script>");
		log("Remarks", "Enter HTML injection text", "Accepted by field", f.getAttribute("value"), true);

		f.clear(); f.sendKeys("Test Remark Selenium");
		log("Remarks", "Final value 'Test Remark Selenium' for save", "Test Remark Selenium", f.getAttribute("value"), true);

		System.out.println("=================================================");
		System.out.println("SV11 - REMARKS VALIDATION END");
		System.out.println("=================================================");
	}
}
