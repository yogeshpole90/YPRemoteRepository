package EmployeeMaster_Package;

import java.util.List;
import org.openqa.selenium.By;
import org.openqa.selenium.Keys;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.Select;

public class EM11_BloodGrp extends EM2_Login {

	public void validateBloodGrp() throws Exception
	{
		WebElement field = driver.findElement(By.id("bloodGroup"));
		Select s = new Select(field);
		List<WebElement> allOptions = s.getOptions();
		String fieldName = "Blood Group";

		System.out.println("=================================================");
		sa.assertTrue(field.isDisplayed(), fieldName + " field is NOT visible on page.");
		System.out.println("Blood Group Case 1 : Field='" + fieldName + "' | Check=Is Displayed? | Result=" + field.isDisplayed() + " | " + (field.isDisplayed() ? "PASS - Field visible on page" : "FAIL - Field NOT visible on page"));

		System.out.println("=================================================");
		sa.assertTrue(field.isEnabled(), fieldName + " field is DISABLED, cannot interact.");
		System.out.println("Blood Group Case 2 : Field='" + fieldName + "' | Check=Is Enabled? | Result=" + field.isEnabled() + " | " + (field.isEnabled() ? "PASS - Field is enabled" : "FAIL - Field is disabled"));

		System.out.println("=================================================");
		sa.assertFalse(s.isMultiple(), fieldName + " is multi-select. Should be single select.");
		System.out.println("Blood Group Case 3 : Field='" + fieldName + "' | Check=Is Multi-Select? | Result=" + s.isMultiple() + " | " + (!s.isMultiple() ? "PASS - Single select dropdown" : "FAIL - Multi select dropdown"));

		System.out.println("=================================================");
		System.out.println("Blood Group Case 4 : Field='" + fieldName + "' | Check=Total Options Count | Result=" + allOptions.size() + " options found");

		System.out.println("=================================================");
		System.out.print("Blood Group Case 5 : Field='" + fieldName + "' | All Dropdown Values → ");
		for (WebElement option : allOptions) { System.out.print(option.getText() + " , "); }
		System.out.println();

		System.out.println("=================================================");
		String defaultSelected = s.getFirstSelectedOption().getText();
		System.out.println("Blood Group Case 6 : Field='" + fieldName + "' | Check=Default Selected | Result='" + defaultSelected + "'");

		System.out.println("=================================================");
		boolean allEnabled = true;
		for (WebElement option : allOptions) { if (!option.isEnabled()) allEnabled = false; }
		System.out.println("Blood Group Case 7 : Field='" + fieldName + "' | Check=All Options Enabled? | Result=" + allEnabled + " | " + (allEnabled ? "PASS - All options enabled" : "FAIL - Some options disabled"));

		System.out.println("=================================================");
		field.sendKeys(Keys.DOWN); Thread.sleep(300);
		String keySelected = s.getFirstSelectedOption().getText();
		System.out.println("Blood Group Case 8 : Field='" + fieldName + "' | Action=Arrow Down Key | Selected='" + keySelected + "' | " + (keySelected != null ? "PASS - Keyboard accessible" : "FAIL - Not keyboard accessible"));

		System.out.println("=================================================");
		s.selectByVisibleText("B+");
		String selected = s.getFirstSelectedOption().getText();
		sa.assertEquals(selected, "B+", fieldName + " failed to select 'B+'.");
		System.out.println("Blood Group Case 9 : Field='" + fieldName + "' | Action=selectByVisibleText('B+') | Expected='B+' | Actual='" + selected + "' | " + (selected.equals("B+") ? "PASS - Selected successfully" : "FAIL - Selection failed"));

		System.out.println("=================================================");
		System.out.println("EM11_BloodGrp - All 9 cases executed.");
	}
}
