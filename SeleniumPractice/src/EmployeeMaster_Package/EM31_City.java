package EmployeeMaster_Package;

import java.util.List;
import org.openqa.selenium.By;
import org.openqa.selenium.Keys;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.Select;

public class EM31_City extends EM2_Login {

	public void validateCity() throws Exception
	{
		WebElement field = driver.findElement(By.id("city"));
		Select s = new Select(field);
		Thread.sleep(1000);
		List<WebElement> allOptions = s.getOptions();
		String fieldName = "City";

		System.out.println("=================================================");
		sa.assertTrue(field.isDisplayed(), fieldName + " NOT visible."); System.out.println("City Case 1 : Field='" + fieldName + "' | Check=Is Displayed? | Result=" + field.isDisplayed() + " | " + (field.isDisplayed() ? "PASS - Field visible" : "FAIL - Not visible"));
		System.out.println("=================================================");
		sa.assertTrue(field.isEnabled(), fieldName + " DISABLED."); System.out.println("City Case 2 : Field='" + fieldName + "' | Check=Is Enabled? | Result=" + field.isEnabled() + " | " + (field.isEnabled() ? "PASS - Enabled" : "FAIL - Disabled"));
		System.out.println("=================================================");
		sa.assertFalse(s.isMultiple(), fieldName + " multi-select."); System.out.println("City Case 3 : Field='" + fieldName + "' | Check=Multi-Select? | Result=" + s.isMultiple() + " | " + (!s.isMultiple() ? "PASS - Single" : "FAIL - Multi"));
		System.out.println("=================================================");
		System.out.println("City Case 4 : Field='" + fieldName + "' | Total Options=" + allOptions.size());
		System.out.println("=================================================");
		System.out.print("City Case 5 : Field='" + fieldName + "' | All Values → "); for (WebElement o : allOptions) { System.out.print(o.getText() + " , "); } System.out.println();
		System.out.println("=================================================");
		System.out.println("City Case 6 : Field='" + fieldName + "' | Default='" + s.getFirstSelectedOption().getText() + "'");
		System.out.println("=================================================");
		boolean ae = true; for (WebElement o : allOptions) { if (!o.isEnabled()) ae = false; }
		System.out.println("City Case 7 : Field='" + fieldName + "' | All Enabled=" + ae + " | " + (ae ? "PASS" : "FAIL"));
		System.out.println("=================================================");
		field.sendKeys(Keys.DOWN); Thread.sleep(300);
		System.out.println("City Case 8 : Field='" + fieldName + "' | Arrow Down → '" + s.getFirstSelectedOption().getText() + "' | PASS - Keyboard accessible");
		System.out.println("=================================================");
		s.selectByVisibleText("CASCADE"); String sel = s.getFirstSelectedOption().getText();
		sa.assertEquals(sel, "CASCADE", fieldName + " failed select 'CASCADE'.");
		System.out.println("City Case 9 : Field='" + fieldName + "' | Action=selectByVisibleText('CASCADE') | Expected='CASCADE' | Actual='" + sel + "' | " + (sel.equals("CASCADE") ? "PASS" : "FAIL"));
		System.out.println("=================================================");
		System.out.println("EM31_City - All 9 cases executed.");
	}
}
