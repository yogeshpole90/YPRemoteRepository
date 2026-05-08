package EmployeeMaster_Package;

import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;

public class EM3_EmpId extends EM2_Login {

	public void validateEmpId()
	{
		WebElement empId = driver.findElement(By.id("empId"));
		String field = "Employee ID";

		System.out.println("=================================================");
		sa.assertTrue(empId.isDisplayed(), field + " field is NOT visible on page.");
		System.out.println("EID Case 1 : Field='" + field + "' | Check=Is Displayed? | Result=" + empId.isDisplayed() + " | " + (empId.isDisplayed() ? "PASS" : "FAIL - Field not visible"));

		System.out.println("=================================================");
		sa.assertTrue(empId.isEnabled(), field + " field is DISABLED.");
		System.out.println("EID Case 2 : Field='" + field + "' | Check=Is Enabled? | Result=" + empId.isEnabled() + " | " + (empId.isEnabled() ? "PASS" : "FAIL - Field disabled"));

		System.out.println("=================================================");
		empId.clear(); empId.sendKeys("EMP001");
		String v3 = empId.getAttribute("value");
		sa.assertEquals(v3, "EMP001", field + " not accepting alphanumeric 'EMP001'.");
		System.out.println("EID Case 3 : Field='" + field + "' | Input='EMP001' | Expected='EMP001' | Actual='" + v3 + "' | " + (v3.equals("EMP001") ? "PASS - Alphanumeric accepted" : "FAIL - Alphanumeric rejected"));

		System.out.println("=================================================");
		empId.clear(); empId.sendKeys("12345");
		String v4 = empId.getAttribute("value");
		sa.assertEquals(v4, "12345", field + " not accepting numeric '12345'.");
		System.out.println("EID Case 4 : Field='" + field + "' | Input='12345' | Expected='12345' | Actual='" + v4 + "' | " + (v4.equals("12345") ? "PASS - Numeric accepted" : "FAIL - Numeric rejected"));

		System.out.println("=================================================");
		empId.clear(); empId.sendKeys("ABCDE");
		String v5 = empId.getAttribute("value");
		sa.assertEquals(v5, "ABCDE", field + " not accepting alphabets 'ABCDE'.");
		System.out.println("EID Case 5 : Field='" + field + "' | Input='ABCDE' | Expected='ABCDE' | Actual='" + v5 + "' | " + (v5.equals("ABCDE") ? "PASS - Alphabets accepted" : "FAIL - Alphabets rejected"));

		System.out.println("=================================================");
		empId.clear(); empId.sendKeys("@#$%");
		String v6 = empId.getAttribute("value");
		sa.assertTrue(v6.isEmpty(), field + " accepting special chars '@#$%'. Should reject.");
		System.out.println("EID Case 6 : Field='" + field + "' | Input='@#$%' | Expected='' (empty) | Actual='" + v6 + "' | " + (v6.isEmpty() ? "PASS - Special chars rejected" : "FAIL - Special chars '@#$%' accepted"));

		System.out.println("=================================================");
		empId.clear(); empId.sendKeys("   ");
		String v7 = empId.getAttribute("value");
		sa.assertTrue(v7.trim().isEmpty(), field + " accepting only spaces. Should reject blank input.");
		System.out.println("EID Case 7 : Field='" + field + "' | Input='   ' (spaces) | Expected='' (empty) | Actual='" + v7 + "' | " + (v7.trim().isEmpty() ? "PASS - Spaces rejected" : "FAIL - Spaces accepted as value"));

		System.out.println("=================================================");
		empId.clear();
		String v8 = empId.getAttribute("value");
		sa.assertTrue(v8.isEmpty(), field + " not clearing properly.");
		System.out.println("EID Case 8 : Field='" + field + "' | Action=Clear | Expected='' (empty) | Actual='" + v8 + "' | " + (v8.isEmpty() ? "PASS - Field cleared" : "FAIL - Field not cleared"));

		System.out.println("=================================================");
		String maxLen = empId.getAttribute("maxlength");
		System.out.println("EID Case 9 : Field='" + field + "' | Check=Max Length | Value=" + (maxLen != null ? maxLen : "null (No limit set)"));

		System.out.println("=================================================");
		String readOnly = empId.getAttribute("readonly");
		System.out.println("EID Case 10 : Field='" + field + "' | Check=ReadOnly | Value=" + (readOnly == null ? "No (Editable)" : "Yes (Read-Only)"));

		empId.clear(); empId.sendKeys("EMP001");
		System.out.println("EID : Final → Field='" + field + "' | Value='EMP001' set for record save");
		System.out.println("=================================================");
		System.out.println("EM3_EmpId - All 10 cases executed.");
	}

}
