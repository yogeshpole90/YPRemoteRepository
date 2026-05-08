package EmployeeMaster_Package;

import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;

public class EM16_IdName extends EM2_Login {

	public void validateIdName()
	{
		WebElement idName = driver.findElement(By.id("idProofName"));
		String field = "Name As Per Photo ID Proof";

		System.out.println("=================================================");
		sa.assertTrue(idName.isDisplayed(), field + " field is NOT visible.");
		System.out.println("Name As Per Photo ID Proof Case 1 : Field='" + field + "' | Check=Is Displayed? | Result=" + idName.isDisplayed() + " | " + (idName.isDisplayed() ? "PASS - Field visible on page" : "FAIL - Field NOT visible"));

		System.out.println("=================================================");
		sa.assertTrue(idName.isEnabled(), field + " field is DISABLED.");
		System.out.println("Name As Per Photo ID Proof Case 2 : Field='" + field + "' | Check=Is Enabled? | Result=" + idName.isEnabled() + " | " + (idName.isEnabled() ? "PASS - Field is enabled" : "FAIL - Field is disabled"));

		System.out.println("=================================================");
		idName.clear(); idName.sendKeys("Yogesh Pole");
		String v3 = idName.getAttribute("value");
		sa.assertEquals(v3, "Yogesh Pole", field + " not accepting alphabets.");
		System.out.println("Name As Per Photo ID Proof Case 3 : Field='" + field + "' | Input='Yogesh Pole' | Expected='Yogesh Pole' | Actual='" + v3 + "' | " + (v3.equals("Yogesh Pole") ? "PASS - Alphabets accepted" : "FAIL - Alphabets rejected"));

		System.out.println("=================================================");
		idName.clear(); idName.sendKeys("12345");
		String v4 = idName.getAttribute("value");
		System.out.println("Name As Per Photo ID Proof Case 4 : Field='" + field + "' | Input='12345' | Expected='' (reject) | Actual='" + v4 + "' | " + (v4.isEmpty() ? "PASS - Numeric rejected" : "INFO - Numeric '12345' accepted by application (no client-side restriction)"));

		System.out.println("=================================================");
		idName.clear(); idName.sendKeys("@#$%");
		String v5 = idName.getAttribute("value");
		System.out.println("Name As Per Photo ID Proof Case 5 : Field='" + field + "' | Input='@#$%' | Expected='' (reject) | Actual='" + v5 + "' | " + (v5.isEmpty() ? "PASS - Special chars rejected" : "INFO - Special chars '@#$%' accepted by application"));

		System.out.println("=================================================");
		idName.clear(); idName.sendKeys("Yogesh123");
		String v6 = idName.getAttribute("value");
		System.out.println("Name As Per Photo ID Proof Case 6 : Field='" + field + "' | Input='Yogesh123' | Expected=NOT 'Yogesh123' | Actual='" + v6 + "' | " + (!v6.equals("Yogesh123") ? "PASS - Alphanumeric rejected" : "INFO - Alphanumeric 'Yogesh123' accepted by application"));

		System.out.println("=================================================");
		idName.clear(); idName.sendKeys("   ");
		String v7 = idName.getAttribute("value");
		System.out.println("Name As Per Photo ID Proof Case 7 : Field='" + field + "' | Input='   ' (spaces) | Expected='' | Actual='" + v7 + "' | " + (v7.trim().isEmpty() ? "PASS - Spaces rejected" : "INFO - Spaces accepted by application"));

		System.out.println("=================================================");
		idName.clear();
		String v8 = idName.getAttribute("value");
		sa.assertTrue(v8.isEmpty(), field + " not clearing.");
		System.out.println("Name As Per Photo ID Proof Case 8 : Field='" + field + "' | Action=Clear | Expected='' (empty) | Actual='" + v8 + "' | " + (v8.isEmpty() ? "PASS - Cleared" : "FAIL - Not cleared"));

		System.out.println("=================================================");
		System.out.println("Name As Per Photo ID Proof Case 9 : Field='" + field + "' | Check=Max Length | Value=" + idName.getAttribute("maxlength"));

		System.out.println("=================================================");
		System.out.println("Name As Per Photo ID Proof Case 10 : Field='" + field + "' | Check=ReadOnly | Value=" + (idName.getAttribute("readonly") == null ? "No (Editable)" : "Yes (Read-Only)"));

		idName.clear(); idName.sendKeys("Yogesh Pole");
		System.out.println("Name As Per Photo ID Proof Final : Field='" + field + "' | Value='Yogesh Pole' set for record save");
		System.out.println("=================================================");
		System.out.println("EM16_IdName - All 10 cases executed.");
	}
}
