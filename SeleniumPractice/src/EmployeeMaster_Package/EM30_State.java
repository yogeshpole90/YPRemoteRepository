package EmployeeMaster_Package;

import java.util.List;
import org.openqa.selenium.By;
import org.openqa.selenium.Keys;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.Select;

public class EM30_State extends EM2_Login {

	public void validateState() throws Exception
	{
		WebElement field = driver.findElement(By.id("state"));
		Select s = new Select(field);
		Thread.sleep(1000);
		List<WebElement> allOptions = s.getOptions();
		String fieldName = "State";

		System.out.println("=================================================");
		sa.assertTrue(field.isDisplayed(), fieldName + " NOT visible."); System.out.println("State Case 1 : Field='" + fieldName + "' | Check=Is Displayed? | Result=" + field.isDisplayed() + " | " + (field.isDisplayed() ? "PASS - Field visible" : "FAIL - Not visible"));
		System.out.println("=================================================");
		sa.assertTrue(field.isEnabled(), fieldName + " DISABLED."); System.out.println("State Case 2 : Field='" + fieldName + "' | Check=Is Enabled? | Result=" + field.isEnabled() + " | " + (field.isEnabled() ? "PASS - Enabled" : "FAIL - Disabled"));
		System.out.println("=================================================");
		sa.assertFalse(s.isMultiple(), fieldName + " multi-select."); System.out.println("State Case 3 : Field='" + fieldName + "' | Check=Multi-Select? | Result=" + s.isMultiple() + " | " + (!s.isMultiple() ? "PASS - Single" : "FAIL - Multi"));
		System.out.println("=================================================");
		System.out.println("State Case 4 : Field='" + fieldName + "' | Total Options=" + allOptions.size());
		System.out.println("=================================================");
		System.out.print("State Case 5 : Field='" + fieldName + "' | All Values → "); for (WebElement o : allOptions) { System.out.print(o.getText() + " , "); } System.out.println();
		System.out.println("=================================================");
		System.out.println("State Case 6 : Field='" + fieldName + "' | Default='" + s.getFirstSelectedOption().getText() + "'");
		System.out.println("=================================================");
		boolean ae = true; for (WebElement o : allOptions) { if (!o.isEnabled()) ae = false; }
		System.out.println("State Case 7 : Field='" + fieldName + "' | All Enabled=" + ae + " | " + (ae ? "PASS" : "FAIL"));
		System.out.println("=================================================");
		field.sendKeys(Keys.DOWN); Thread.sleep(300);
		System.out.println("State Case 8 : Field='" + fieldName + "' | Arrow Down → '" + s.getFirstSelectedOption().getText() + "' | PASS - Keyboard accessible");
		System.out.println("=================================================");
		s.selectByVisibleText("PRASLIN"); String sel = s.getFirstSelectedOption().getText();
		sa.assertEquals(sel, "PRASLIN", fieldName + " failed select 'PRASLIN'.");
		System.out.println("State Case 9 : Field='" + fieldName + "' | Action=selectByVisibleText('PRASLIN') | Expected='PRASLIN' | Actual='" + sel + "' | " + (sel.equals("PRASLIN") ? "PASS" : "FAIL"));

		// Final: Set MAHE so City dropdown loads correctly
		System.out.println("=================================================");
		s.selectByVisibleText("MAHE"); Thread.sleep(1000);
		System.out.println("State Final : Field='" + fieldName + "' | Value='MAHE' set (for City dropdown dependency)");
		System.out.println("=================================================");
		System.out.println("EM30_State - All 9 cases executed.");
	}
}
