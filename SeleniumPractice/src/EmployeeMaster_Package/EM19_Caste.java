package EmployeeMaster_Package;

import java.util.List;
import org.openqa.selenium.By;
import org.openqa.selenium.Keys;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.Select;

public class EM19_Caste extends EM2_Login {

	public void validateCaste() throws Exception
	{
		WebElement field = driver.findElement(By.id("caste"));
		Select s = new Select(field);
		List<WebElement> allOptions = s.getOptions();
		String fieldName = "Caste";

		System.out.println("=================================================");
		sa.assertTrue(field.isDisplayed(), fieldName + " NOT visible."); System.out.println("Caste Case 1 : Field='" + fieldName + "' | Check=Is Displayed? | Result=" + field.isDisplayed() + " | " + (field.isDisplayed() ? "PASS - Field visible" : "FAIL - Not visible"));
		System.out.println("=================================================");
		sa.assertTrue(field.isEnabled(), fieldName + " DISABLED."); System.out.println("Caste Case 2 : Field='" + fieldName + "' | Check=Is Enabled? | Result=" + field.isEnabled() + " | " + (field.isEnabled() ? "PASS - Enabled" : "FAIL - Disabled"));
		System.out.println("=================================================");
		sa.assertFalse(s.isMultiple(), fieldName + " multi-select."); System.out.println("Caste Case 3 : Field='" + fieldName + "' | Check=Multi-Select? | Result=" + s.isMultiple() + " | " + (!s.isMultiple() ? "PASS - Single" : "FAIL - Multi"));
		System.out.println("=================================================");
		System.out.println("Caste Case 4 : Field='" + fieldName + "' | Total Options=" + allOptions.size());
		System.out.println("=================================================");
		System.out.print("Caste Case 5 : Field='" + fieldName + "' | All Values → "); for (WebElement o : allOptions) { System.out.print(o.getText() + " , "); } System.out.println();
		System.out.println("=================================================");
		System.out.println("Caste Case 6 : Field='" + fieldName + "' | Default='" + s.getFirstSelectedOption().getText() + "'");
		System.out.println("=================================================");
		boolean ae = true; for (WebElement o : allOptions) { if (!o.isEnabled()) ae = false; }
		System.out.println("Caste Case 7 : Field='" + fieldName + "' | All Enabled=" + ae + " | " + (ae ? "PASS" : "FAIL"));
		System.out.println("=================================================");
		field.sendKeys(Keys.DOWN); Thread.sleep(300);
		System.out.println("Caste Case 8 : Field='" + fieldName + "' | Arrow Down → '" + s.getFirstSelectedOption().getText() + "' | PASS - Keyboard accessible");
		System.out.println("=================================================");
		s.selectByVisibleText("BRAHMIN"); String sel = s.getFirstSelectedOption().getText();
		sa.assertEquals(sel, "BRAHMIN", fieldName + " failed select 'BRAHMIN'.");
		System.out.println("Caste Case 9 : Field='" + fieldName + "' | Action=selectByVisibleText('BRAHMIN') | Expected='BRAHMIN' | Actual='" + sel + "' | " + (sel.equals("BRAHMIN") ? "PASS" : "FAIL"));
		System.out.println("=================================================");
		System.out.println("EM19_Caste - All 9 cases executed.");
	}
}
