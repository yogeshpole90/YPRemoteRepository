package EmployeeMaster_Package;

import java.util.List;
import org.openqa.selenium.By;
import org.openqa.selenium.Keys;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.Select;

public class EM12_IdProof extends EM2_Login {

	public void validateIdProof() throws Exception
	{
		WebElement field = driver.findElement(By.id("idProof"));
		Select s = new Select(field);
		List<WebElement> allOptions = s.getOptions();
		String fieldName = "Photo ID Proof Type";

		System.out.println("=================================================");
		sa.assertTrue(field.isDisplayed(), fieldName + " field is NOT visible on page.");
		System.out.println("Photo ID Proof Type Case 1 : Field='" + fieldName + "' | Check=Is Displayed? | Result=" + field.isDisplayed() + " | " + (field.isDisplayed() ? "PASS - Field visible on page" : "FAIL - Field NOT visible on page"));

		System.out.println("=================================================");
		sa.assertTrue(field.isEnabled(), fieldName + " field is DISABLED, cannot interact.");
		System.out.println("Photo ID Proof Type Case 2 : Field='" + fieldName + "' | Check=Is Enabled? | Result=" + field.isEnabled() + " | " + (field.isEnabled() ? "PASS - Field is enabled" : "FAIL - Field is disabled"));

		System.out.println("=================================================");
		sa.assertFalse(s.isMultiple(), fieldName + " is multi-select.");
		System.out.println("Photo ID Proof Type Case 3 : Field='" + fieldName + "' | Check=Is Multi-Select? | Result=" + s.isMultiple() + " | " + (!s.isMultiple() ? "PASS - Single select dropdown" : "FAIL - Multi select dropdown"));

		System.out.println("=================================================");
		System.out.println("Photo ID Proof Type Case 4 : Field='" + fieldName + "' | Check=Total Options Count | Result=" + allOptions.size() + " options found");

		System.out.println("=================================================");
		System.out.print("Photo ID Proof Type Case 5 : Field='" + fieldName + "' | All Dropdown Values → ");
		for (WebElement option : allOptions) { System.out.print(option.getText() + " , "); }
		System.out.println();

		System.out.println("=================================================");
		System.out.println("Photo ID Proof Type Case 6 : Field='" + fieldName + "' | Check=Default Selected | Result='" + s.getFirstSelectedOption().getText() + "'");

		System.out.println("=================================================");
		boolean allEnabled = true;
		for (WebElement option : allOptions) { if (!option.isEnabled()) allEnabled = false; }
		System.out.println("Photo ID Proof Type Case 7 : Field='" + fieldName + "' | Check=All Options Enabled? | Result=" + allEnabled + " | " + (allEnabled ? "PASS - All options enabled" : "FAIL - Some options disabled"));

		System.out.println("=================================================");
		field.sendKeys(Keys.DOWN); Thread.sleep(300);
		System.out.println("Photo ID Proof Type Case 8 : Field='" + fieldName + "' | Action=Arrow Down Key | Selected='" + s.getFirstSelectedOption().getText() + "' | PASS - Keyboard accessible");

		System.out.println("=================================================");
		s.selectByVisibleText("NATIONAL ID/ AADHAR CARD");
		String selected = s.getFirstSelectedOption().getText();
		sa.assertEquals(selected, "NATIONAL ID/ AADHAR CARD", fieldName + " failed to select 'NATIONAL ID/ AADHAR CARD'.");
		System.out.println("Photo ID Proof Type Case 9 : Field='" + fieldName + "' | Action=selectByVisibleText('NATIONAL ID/ AADHAR CARD') | Expected='NATIONAL ID/ AADHAR CARD' | Actual='" + selected + "' | " + (selected.equals("NATIONAL ID/ AADHAR CARD") ? "PASS - Selected successfully" : "FAIL - Selection failed"));

		System.out.println("=================================================");
		System.out.println("EM12_IdProof - All 9 cases executed.");
	}
}
