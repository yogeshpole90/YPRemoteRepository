package EmployeeMaster_Package;

import java.util.List;
import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.Select;

public class EM35_Mobile extends EM2_Login {

	public void validateMobile() throws Exception
	{
		WebElement isdField = driver.findElement(By.id("isdMobile"));
		Select isdSel = new Select(isdField);
		List<WebElement> isdOpts = isdSel.getOptions();
		String isdName = "Mobile ISD Code";
		String mobName = "Mobile Number";

		System.out.println("=================================================");
		sa.assertTrue(isdField.isDisplayed(), isdName + " NOT visible.");
		System.out.println("Mobile Number Case 1 : Field='" + isdName + "' | Check=Is Displayed? | Result=" + isdField.isDisplayed() + " | " + (isdField.isDisplayed() ? "PASS - Field visible" : "FAIL - Not visible"));
		System.out.println("=================================================");
		sa.assertTrue(isdField.isEnabled(), isdName + " DISABLED.");
		System.out.println("Mobile Number Case 2 : Field='" + isdName + "' | Check=Is Enabled? | Result=" + isdField.isEnabled() + " | " + (isdField.isEnabled() ? "PASS - Enabled" : "FAIL - Disabled"));
		System.out.println("=================================================");
		System.out.println("Mobile Number Case 3 : Field='" + isdName + "' | Total Options=" + isdOpts.size());
		System.out.println("=================================================");
		System.out.print("Mobile Number Case 4 : Field='" + isdName + "' | All Values → ");
		for (WebElement o : isdOpts) { System.out.print(o.getText() + " , "); } System.out.println();
		System.out.println("=================================================");
		isdSel.selectByVisibleText("+248"); String isdV = isdSel.getFirstSelectedOption().getText();
		sa.assertEquals(isdV, "+248", isdName + " failed to select '+248'.");
		System.out.println("Mobile Number Case 5 : Field='" + isdName + "' | Action=selectByVisibleText('+248') | Expected='+248' | Actual='" + isdV + "' | " + (isdV.equals("+248") ? "PASS - Selected" : "FAIL"));

		WebElement mob = driver.findElement(By.id("mobile"));
		System.out.println("=================================================");
		sa.assertTrue(mob.isDisplayed(), mobName + " NOT visible.");
		System.out.println("Mobile Number Case 6 : Field='" + mobName + "' | Check=Is Displayed? | Result=" + mob.isDisplayed() + " | " + (mob.isDisplayed() ? "PASS - Field visible" : "FAIL - Not visible"));
		System.out.println("=================================================");
		sa.assertTrue(mob.isEnabled(), mobName + " DISABLED.");
		System.out.println("Mobile Number Case 7 : Field='" + mobName + "' | Check=Is Enabled? | Result=" + mob.isEnabled() + " | " + (mob.isEnabled() ? "PASS - Enabled" : "FAIL - Disabled"));
		System.out.println("=================================================");
		mob.clear(); mob.sendKeys("9876543210"); String v = mob.getAttribute("value");
		sa.assertEquals(v, "9876543210", mobName + " rejected numeric '9876543210'.");
		System.out.println("Mobile Number Case 8 : Field='" + mobName + "' | Input='9876543210' | Expected='9876543210' | Actual='" + v + "' | " + (v.equals("9876543210") ? "PASS - Numeric accepted" : "FAIL - Rejected"));
		System.out.println("=================================================");
		mob.clear(); mob.sendKeys("abcdef"); v = mob.getAttribute("value");
		sa.assertTrue(v.isEmpty(), mobName + " accepted alphabets 'abcdef'.");
		System.out.println("Mobile Number Case 9 : Field='" + mobName + "' | Input='abcdef' | Expected='' (empty) | Actual='" + v + "' | " + (v.isEmpty() ? "PASS - Alphabets rejected" : "FAIL - Alphabets accepted"));
		System.out.println("=================================================");
		mob.clear(); mob.sendKeys("@#$%"); v = mob.getAttribute("value");
		sa.assertTrue(v.isEmpty(), mobName + " accepted special chars '@#$%'.");
		System.out.println("Mobile Number Case 10 : Field='" + mobName + "' | Input='@#$%' | Expected='' (empty) | Actual='" + v + "' | " + (v.isEmpty() ? "PASS - Special chars rejected" : "FAIL - Special chars accepted"));
		System.out.println("=================================================");
		mob.clear(); mob.sendKeys("98765abc"); v = mob.getAttribute("value");
		sa.assertNotEquals(v, "98765abc", mobName + " accepted alphanumeric '98765abc'.");
		System.out.println("Mobile Number Case 11 : Field='" + mobName + "' | Input='98765abc' | Expected=NOT '98765abc' | Actual='" + v + "' | " + (!v.equals("98765abc") ? "PASS - Alphanumeric rejected" : "FAIL - Alphanumeric accepted"));
		System.out.println("=================================================");
		mob.clear(); mob.sendKeys("   "); v = mob.getAttribute("value");
		sa.assertTrue(v.trim().isEmpty(), mobName + " accepted spaces.");
		System.out.println("Mobile Number Case 12 : Field='" + mobName + "' | Input='   ' (spaces) | Expected='' (empty) | Actual='" + v + "' | " + (v.trim().isEmpty() ? "PASS - Spaces rejected" : "FAIL - Spaces accepted"));
		System.out.println("=================================================");
		mob.clear(); v = mob.getAttribute("value");
		sa.assertTrue(v.isEmpty(), mobName + " not cleared.");
		System.out.println("Mobile Number Case 13 : Field='" + mobName + "' | Action=Clear | Expected='' (empty) | Actual='" + v + "' | " + (v.isEmpty() ? "PASS - Cleared" : "FAIL - Not cleared"));
		System.out.println("=================================================");
		System.out.println("Mobile Number Case 14 : Field='" + mobName + "' | Check=Max Length | Value=" + mob.getAttribute("maxlength"));
		mob.clear(); mob.sendKeys("9876543210");
		System.out.println("Mobile Number Final : Field='" + isdName + "'='+248' | Field='" + mobName + "'='9876543210'");
		System.out.println("=================================================");
		System.out.println("EM35_Mobile - All 14 cases executed.");
	}
}
