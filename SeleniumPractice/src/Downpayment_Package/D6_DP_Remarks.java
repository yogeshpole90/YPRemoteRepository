package Downpayment_Package;

import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;

public class D6_DP_Remarks extends D2_DP_Login {



	public void validateRemarks()
	{
		System.out.println("=================================================");
		System.out.println("D6 - REMARKS VALIDATION START");
		System.out.println("=================================================");

		WebElement remarks = driver.findElement(By.id("remarks"));

		log("Remarks", "Field should be visible on page", "true", String.valueOf(remarks.isDisplayed()), remarks.isDisplayed());
		sa.assertTrue(remarks.isDisplayed(), "Remarks not displayed.");

		log("Remarks", "Field should be enabled/editable", "true", String.valueOf(remarks.isEnabled()), remarks.isEnabled());
		sa.assertTrue(remarks.isEnabled(), "Remarks is disabled.");

		remarks.clear(); remarks.sendKeys("Downpayment test remark");
		String textVal = remarks.getAttribute("value");
		log("Remarks", "Enter text 'Downpayment test remark'", "Downpayment test remark", textVal, textVal.equals("Downpayment test remark"));
		sa.assertEquals(textVal, "Downpayment test remark", "Not accepting text.");

		remarks.clear(); remarks.sendKeys("Test@#$%&*!");
		String splVal = remarks.getAttribute("value");
		log("Remarks", "Enter special chars 'Test@#$%&*!'", "Test@#$%&*!", splVal, splVal.equals("Test@#$%&*!"));
		sa.assertEquals(splVal, "Test@#$%&*!", "Not accepting special chars.");

		remarks.clear(); remarks.sendKeys("12345");
		String numVal = remarks.getAttribute("value");
		log("Remarks", "Enter numeric '12345'", "12345", numVal, numVal.equals("12345"));
		sa.assertEquals(numVal, "12345", "Not accepting numeric.");

		remarks.clear(); remarks.sendKeys("Test123Remark");
		String mixVal = remarks.getAttribute("value");
		log("Remarks", "Enter alphanumeric 'Test123Remark'", "Test123Remark", mixVal, mixVal.equals("Test123Remark"));
		sa.assertEquals(mixVal, "Test123Remark", "Not accepting alphanumeric.");

		remarks.clear();
		String emptyVal = remarks.getAttribute("value");
		log("Remarks", "Clear field - should be empty", "Empty", "'" + emptyVal + "'", emptyVal.isEmpty());
		sa.assertTrue(emptyVal.isEmpty(), "Field not cleared.");

		remarks.clear(); remarks.sendKeys("     ");
		String spaceVal = remarks.getAttribute("value");
		log("Remarks", "Enter spaces only", "Spaces accepted or rejected", "'" + spaceVal + "'", true);

		String maxLenAttr = remarks.getAttribute("maxlength");
		log("Remarks", "Check maxlength attribute", "Maxlength value", maxLenAttr != null ? maxLenAttr : "null (no limit)", true);

		remarks.clear(); remarks.sendKeys("Line1\nLine2\nLine3");
		String multiLine = remarks.getAttribute("value");
		log("Remarks", "Enter multi-line text", "Multi-line accepted", multiLine.contains("\n") ? "Multi-line accepted" : "Multi-line rejected", multiLine.contains("\n"));
		sa.assertTrue(multiLine.contains("\n"), "Not accepting multi-line.");

		String readOnly = remarks.getAttribute("readonly");
		log("Remarks", "Field should NOT be read-only", "readonly = null", "readonly = " + readOnly, readOnly == null);
		sa.assertNull(readOnly, "Remarks is read-only.");

		remarks.clear(); remarks.sendKeys("Downpayment validation test record");
		log("Remarks", "Final value set for record save", "Downpayment validation test record", remarks.getAttribute("value"), true);

		System.out.println("=================================================");
		System.out.println("D6 - REMARKS VALIDATION END");
		System.out.println("=================================================");
	}
}


