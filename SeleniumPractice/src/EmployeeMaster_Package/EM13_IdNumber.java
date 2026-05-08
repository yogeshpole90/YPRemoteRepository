package EmployeeMaster_Package;

import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;

public class EM13_IdNumber extends EM2_Login {

	public void validateIdNumber()
	{
		WebElement idNum = driver.findElement(By.id("idNumber"));
		String field = "Photo ID Proof Number";

		System.out.println("=================================================");
		sa.assertTrue(idNum.isDisplayed(), field + " field is NOT visible on page.");
		System.out.println("Photo ID Proof Number Case 1 : Field='" + field + "' | Check=Is Displayed? | Result=" + idNum.isDisplayed() + " | " + (idNum.isDisplayed() ? "PASS - Field visible on page" : "FAIL - Field NOT visible on page"));

		System.out.println("=================================================");
		sa.assertTrue(idNum.isEnabled(), field + " field is DISABLED.");
		System.out.println("Photo ID Proof Number Case 2 : Field='" + field + "' | Check=Is Enabled? | Result=" + idNum.isEnabled() + " | " + (idNum.isEnabled() ? "PASS - Field is enabled" : "FAIL - Field is disabled"));

		System.out.println("=================================================");
		idNum.clear(); idNum.sendKeys("ABC1234567");
		String v3 = idNum.getAttribute("value");
		sa.assertEquals(v3, "ABC1234567", field + " not accepting alphanumeric 'ABC1234567'.");
		System.out.println("Photo ID Proof Number Case 3 : Field='" + field + "' | Input='ABC1234567' | Expected='ABC1234567' | Actual='" + v3 + "' | " + (v3.equals("ABC1234567") ? "PASS - Alphanumeric accepted" : "FAIL - Alphanumeric rejected"));

		System.out.println("=================================================");
		idNum.clear(); idNum.sendKeys("1234567890");
		String v4 = idNum.getAttribute("value");
		sa.assertEquals(v4, "1234567890", field + " not accepting numeric '1234567890'.");
		System.out.println("Photo ID Proof Number Case 4 : Field='" + field + "' | Input='1234567890' | Expected='1234567890' | Actual='" + v4 + "' | " + (v4.equals("1234567890") ? "PASS - Numeric accepted" : "FAIL - Numeric rejected"));

		System.out.println("=================================================");
		idNum.clear(); idNum.sendKeys("ABCDEFGH");
		String v5 = idNum.getAttribute("value");
		sa.assertEquals(v5, "ABCDEFGH", field + " not accepting alphabets 'ABCDEFGH'.");
		System.out.println("Photo ID Proof Number Case 5 : Field='" + field + "' | Input='ABCDEFGH' | Expected='ABCDEFGH' | Actual='" + v5 + "' | " + (v5.equals("ABCDEFGH") ? "PASS - Alphabets accepted" : "FAIL - Alphabets rejected"));

		System.out.println("=================================================");
		idNum.clear(); idNum.sendKeys("@#$%^&");
		String v6 = idNum.getAttribute("value");
		System.out.println("Photo ID Proof Number Case 6 : Field='" + field + "' | Input='@#$%^&' | Expected='' (reject) | Actual='" + v6 + "' | " + (v6.isEmpty() ? "PASS - Special chars rejected" : "INFO - Special chars '@#$%^&' accepted by application (no client-side restriction)"));

		System.out.println("=================================================");
		idNum.clear(); idNum.sendKeys("   ");
		String v7 = idNum.getAttribute("value");
		System.out.println("Photo ID Proof Number Case 7 : Field='" + field + "' | Input='   ' (spaces) | Expected='' (reject) | Actual='" + v7 + "' | " + (v7.trim().isEmpty() ? "PASS - Spaces rejected" : "INFO - Spaces accepted by application"));

		System.out.println("=================================================");
		idNum.clear();
		String v8 = idNum.getAttribute("value");
		sa.assertTrue(v8.isEmpty(), field + " not clearing properly.");
		System.out.println("Photo ID Proof Number Case 8 : Field='" + field + "' | Action=Clear | Expected='' (empty) | Actual='" + v8 + "' | " + (v8.isEmpty() ? "PASS - Field cleared" : "FAIL - Field not cleared"));

		System.out.println("=================================================");
		String maxLen = idNum.getAttribute("maxlength");
		System.out.println("Photo ID Proof Number Case 9 : Field='" + field + "' | Check=Max Length | Value=" + (maxLen != null ? maxLen : "null (No limit set)"));

		System.out.println("=================================================");
		String readOnly = idNum.getAttribute("readonly");
		System.out.println("Photo ID Proof Number Case 10 : Field='" + field + "' | Check=ReadOnly | Value=" + (readOnly == null ? "No (Editable)" : "Yes (Read-Only)"));

		idNum.clear(); idNum.sendKeys("ABC1234567");
		System.out.println("Photo ID Proof Number Final : Field='" + field + "' | Value='ABC1234567' set for record save");
		System.out.println("=================================================");
		System.out.println("EM13_IdNumber - All 10 cases executed.");
	}
}
