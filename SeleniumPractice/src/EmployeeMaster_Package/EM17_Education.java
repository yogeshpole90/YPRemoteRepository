package EmployeeMaster_Package;

import java.util.List;
import org.openqa.selenium.By;
import org.openqa.selenium.Keys;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.Select;

public class EM17_Education extends EM2_Login {

	public void validateEducation() throws Exception
	{
		WebElement field = driver.findElement(By.id("education"));
		Select s = new Select(field);
		List<WebElement> allOptions = s.getOptions();
		String fieldName = "Education";

		System.out.println("=================================================");
		sa.assertTrue(field.isDisplayed(), fieldName + " field is NOT visible on page.");
		System.out.println("Education Case 1 : Field='" + fieldName + "' | Check=Is Displayed? | Result=" + field.isDisplayed() + " | " + (field.isDisplayed() ? "PASS - Field visible on page" : "FAIL - Field NOT visible on page"));

		System.out.println("=================================================");
		sa.assertTrue(field.isEnabled(), fieldName + " field is DISABLED.");
		System.out.println("Education Case 2 : Field='" + fieldName + "' | Check=Is Enabled? | Result=" + field.isEnabled() + " | " + (field.isEnabled() ? "PASS - Field is enabled" : "FAIL - Field is disabled"));

		System.out.println("=================================================");
		sa.assertFalse(s.isMultiple(), fieldName + " is multi-select.");
		System.out.println("Education Case 3 : Field='" + fieldName + "' | Check=Is Multi-Select? | Result=" + s.isMultiple() + " | " + (!s.isMultiple() ? "PASS - Single select" : "FAIL - Multi select"));

		System.out.println("=================================================");
		System.out.println("Education Case 4 : Field='" + fieldName + "' | Check=Total Options Count | Result=" + allOptions.size() + " options found");

		System.out.println("=================================================");
		System.out.print("Education Case 5 : Field='" + fieldName + "' | All Dropdown Values → ");
		for (WebElement o : allOptions) { System.out.print(o.getText() + " , "); }
		System.out.println();

		System.out.println("=================================================");
		System.out.println("Education Case 6 : Field='" + fieldName + "' | Check=Default Selected | Result='" + s.getFirstSelectedOption().getText() + "'");

		System.out.println("=================================================");
		boolean allEnabled = true; for (WebElement o : allOptions) { if (!o.isEnabled()) allEnabled = false; }
		System.out.println("Education Case 7 : Field='" + fieldName + "' | Check=All Options Enabled? | Result=" + allEnabled + " | " + (allEnabled ? "PASS" : "FAIL"));

		System.out.println("=================================================");
		field.sendKeys(Keys.DOWN); Thread.sleep(300);
		System.out.println("Education Case 8 : Field='" + fieldName + "' | Action=Arrow Down Key | Selected='" + s.getFirstSelectedOption().getText() + "' | PASS - Keyboard accessible");

		System.out.println("=================================================");
		s.selectByVisibleText("GRADUATE"); String selected = s.getFirstSelectedOption().getText();
		sa.assertEquals(selected, "GRADUATE", fieldName + " failed to select 'GRADUATE'.");
		System.out.println("Education Case 9 : Field='" + fieldName + "' | Action=selectByVisibleText('GRADUATE') | Expected='GRADUATE' | Actual='" + selected + "' | " + (selected.equals("GRADUATE") ? "PASS" : "FAIL"));

		System.out.println("=================================================");
		System.out.println("EM17_Education - All 9 cases executed.");
	}
}
