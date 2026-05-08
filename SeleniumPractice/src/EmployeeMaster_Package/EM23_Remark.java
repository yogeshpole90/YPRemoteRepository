package EmployeeMaster_Package;

import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;

public class EM23_Remark extends EM2_Login {

	public void validateRemark()
	{
		WebElement rmk = driver.findElement(By.id("remark"));
		String field = "Remarks";

		System.out.println("=================================================");
		sa.assertTrue(rmk.isDisplayed(), field + " field is NOT visible.");
		System.out.println("RMK Case 1 : Field='" + field + "' | Check=Is Displayed? | Result=" + rmk.isDisplayed() + " | " + (rmk.isDisplayed() ? "PASS" : "FAIL"));

		System.out.println("=================================================");
		sa.assertTrue(rmk.isEnabled(), field + " field is DISABLED.");
		System.out.println("RMK Case 2 : Field='" + field + "' | Check=Is Enabled? | Result=" + rmk.isEnabled() + " | " + (rmk.isEnabled() ? "PASS" : "FAIL"));

		System.out.println("=================================================");
		rmk.clear(); rmk.sendKeys("Employee test remark");
		String v3 = rmk.getAttribute("value");
		sa.assertEquals(v3, "Employee test remark", field + " not accepting text.");
		System.out.println("RMK Case 3 : Field='" + field + "' | Input='Employee test remark' | Expected='Employee test remark' | Actual='" + v3 + "' | " + (v3.equals("Employee test remark") ? "PASS - Text accepted" : "FAIL - Text rejected"));

		System.out.println("=================================================");
		rmk.clear(); rmk.sendKeys("Test@#$%&*!");
		String v4 = rmk.getAttribute("value");
		sa.assertEquals(v4, "Test@#$%&*!", field + " not accepting special chars.");
		System.out.println("RMK Case 4 : Field='" + field + "' | Input='Test@#$%&*!' | Expected='Test@#$%&*!' | Actual='" + v4 + "' | " + (v4.equals("Test@#$%&*!") ? "PASS - Special chars accepted" : "FAIL - Special chars rejected"));

		System.out.println("=================================================");
		rmk.clear(); rmk.sendKeys("12345");
		String v5 = rmk.getAttribute("value");
		sa.assertEquals(v5, "12345", field + " not accepting numeric.");
		System.out.println("RMK Case 5 : Field='" + field + "' | Input='12345' | Expected='12345' | Actual='" + v5 + "' | " + (v5.equals("12345") ? "PASS - Numeric accepted" : "FAIL - Numeric rejected"));

		System.out.println("=================================================");
		rmk.clear(); rmk.sendKeys("Test123Remark");
		String v6 = rmk.getAttribute("value");
		sa.assertEquals(v6, "Test123Remark", field + " not accepting alphanumeric.");
		System.out.println("RMK Case 6 : Field='" + field + "' | Input='Test123Remark' | Expected='Test123Remark' | Actual='" + v6 + "' | " + (v6.equals("Test123Remark") ? "PASS - Alphanumeric accepted" : "FAIL - Alphanumeric rejected"));

		System.out.println("=================================================");
		rmk.clear();
		String v7 = rmk.getAttribute("value");
		sa.assertTrue(v7.isEmpty(), field + " not clearing.");
		System.out.println("RMK Case 7 : Field='" + field + "' | Action=Clear | Expected='' (empty) | Actual='" + v7 + "' | " + (v7.isEmpty() ? "PASS - Cleared" : "FAIL - Not cleared"));

		System.out.println("=================================================");
		rmk.clear(); rmk.sendKeys("     ");
		String v8 = rmk.getAttribute("value");
		System.out.println("RMK Case 8 : Field='" + field + "' | Input='     ' (spaces) | Actual='" + v8 + "' | " + (v8.trim().isEmpty() ? "INFO - Blank value" : "INFO - Spaces stored"));

		System.out.println("=================================================");
		System.out.println("RMK Case 9 : Field='" + field + "' | Check=Max Length | Value=" + rmk.getAttribute("maxlength"));

		System.out.println("=================================================");
		rmk.clear(); rmk.sendKeys("Line1\nLine2\nLine3");
		String v10 = rmk.getAttribute("value");
		sa.assertTrue(v10.contains("\n"), field + " not accepting multi-line text.");
		System.out.println("RMK Case 10 : Field='" + field + "' | Input=Multi-line | Actual='" + v10.replace("\n","\\n") + "' | " + (v10.contains("\n") ? "PASS - Multi-line accepted" : "FAIL - Multi-line rejected"));

		System.out.println("=================================================");
		System.out.println("RMK Case 11 : Field='" + field + "' | Check=ReadOnly | Value=" + (rmk.getAttribute("readonly") == null ? "No (Editable)" : "Yes (Read-Only)"));

		rmk.clear(); rmk.sendKeys("Employee Master validation test");
		System.out.println("RMK : Final → Field='" + field + "' | Value='Employee Master validation test' set for record save");
		System.out.println("=================================================");
		System.out.println("EM23_Remark - All 11 cases executed.");
	}
}
