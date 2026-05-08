package EmployeeMaster_Package;

import java.util.List;
import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.Select;

public class EM34_OfficeTel extends EM2_Login {

	public void validateOfficeTel() throws Exception
	{
		WebElement isdField = driver.findElement(By.id("isdOffTelephone"));
		Select isdSel = new Select(isdField);
		List<WebElement> isdOpts = isdSel.getOptions();
		String isdName = "Office Telephone ISD Code";
		String telName = "Office Telephone Number";

		System.out.println("=================================================");
		sa.assertTrue(isdField.isDisplayed(), isdName + " NOT visible.");
		System.out.println("Office Telephone Case 1 : Field='" + isdName + "' | Check=Is Displayed? | Result=" + isdField.isDisplayed() + " | " + (isdField.isDisplayed() ? "PASS - Field visible" : "FAIL - Not visible"));
		System.out.println("=================================================");
		sa.assertTrue(isdField.isEnabled(), isdName + " DISABLED.");
		System.out.println("Office Telephone Case 2 : Field='" + isdName + "' | Check=Is Enabled? | Result=" + isdField.isEnabled() + " | " + (isdField.isEnabled() ? "PASS - Enabled" : "FAIL - Disabled"));
		System.out.println("=================================================");
		System.out.println("Office Telephone Case 3 : Field='" + isdName + "' | Total Options=" + isdOpts.size());
		System.out.println("=================================================");
		System.out.print("Office Telephone Case 4 : Field='" + isdName + "' | All Values → ");
		for (WebElement o : isdOpts) { System.out.print(o.getText() + " , "); } System.out.println();
		System.out.println("=================================================");
		isdSel.selectByVisibleText("+248"); String isdV = isdSel.getFirstSelectedOption().getText();
		sa.assertEquals(isdV, "+248", isdName + " failed to select '+248'.");
		System.out.println("Office Telephone Case 5 : Field='" + isdName + "' | Action=selectByVisibleText('+248') | Expected='+248' | Actual='" + isdV + "' | " + (isdV.equals("+248") ? "PASS - Selected" : "FAIL"));

		WebElement tel = driver.findElement(By.id("officeTelephone"));
		System.out.println("=================================================");
		sa.assertTrue(tel.isDisplayed(), telName + " NOT visible.");
		System.out.println("Office Telephone Case 6 : Field='" + telName + "' | Check=Is Displayed? | Result=" + tel.isDisplayed() + " | " + (tel.isDisplayed() ? "PASS - Field visible" : "FAIL - Not visible"));
		System.out.println("=================================================");
		sa.assertTrue(tel.isEnabled(), telName + " DISABLED.");
		System.out.println("Office Telephone Case 7 : Field='" + telName + "' | Check=Is Enabled? | Result=" + tel.isEnabled() + " | " + (tel.isEnabled() ? "PASS - Enabled" : "FAIL - Disabled"));
		System.out.println("=================================================");
		tel.clear(); tel.sendKeys("2345678"); String v = tel.getAttribute("value");
		sa.assertEquals(v, "2345678", telName + " rejected numeric '2345678'.");
		System.out.println("Office Telephone Case 8 : Field='" + telName + "' | Input='2345678' | Expected='2345678' | Actual='" + v + "' | " + (v.equals("2345678") ? "PASS - Numeric accepted" : "FAIL - Rejected"));
		System.out.println("=================================================");
		tel.clear(); tel.sendKeys("abcdef"); v = tel.getAttribute("value");
		sa.assertTrue(v.isEmpty(), telName + " accepted alphabets 'abcdef'.");
		System.out.println("Office Telephone Case 9 : Field='" + telName + "' | Input='abcdef' | Expected='' (empty) | Actual='" + v + "' | " + (v.isEmpty() ? "PASS - Alphabets rejected" : "FAIL - Alphabets accepted"));
		System.out.println("=================================================");
		tel.clear(); tel.sendKeys("@#$%"); v = tel.getAttribute("value");
		sa.assertTrue(v.isEmpty(), telName + " accepted special chars '@#$%'.");
		System.out.println("Office Telephone Case 10 : Field='" + telName + "' | Input='@#$%' | Expected='' (empty) | Actual='" + v + "' | " + (v.isEmpty() ? "PASS - Special chars rejected" : "FAIL - Special chars accepted"));
		System.out.println("=================================================");
		tel.clear(); tel.sendKeys("   "); v = tel.getAttribute("value");
		sa.assertTrue(v.trim().isEmpty(), telName + " accepted spaces.");
		System.out.println("Office Telephone Case 11 : Field='" + telName + "' | Input='   ' (spaces) | Expected='' (empty) | Actual='" + v + "' | " + (v.trim().isEmpty() ? "PASS - Spaces rejected" : "FAIL - Spaces accepted"));
		System.out.println("=================================================");
		tel.clear(); v = tel.getAttribute("value");
		sa.assertTrue(v.isEmpty(), telName + " not cleared.");
		System.out.println("Office Telephone Case 12 : Field='" + telName + "' | Action=Clear | Expected='' (empty) | Actual='" + v + "' | " + (v.isEmpty() ? "PASS - Cleared" : "FAIL - Not cleared"));
		System.out.println("=================================================");
		System.out.println("Office Telephone Case 13 : Field='" + telName + "' | Check=Max Length | Value=" + tel.getAttribute("maxlength"));
		tel.clear(); tel.sendKeys("2345678");
		System.out.println("Office Telephone Final : Field='" + isdName + "'='+248' | Field='" + telName + "'='2345678'");
		System.out.println("=================================================");
		System.out.println("EM34_OfficeTel - All 13 cases executed.");
	}
}
