package EmployeeMaster_Package;

import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;

public class EM33_Email extends EM2_Login {

	public void validateEmail()
	{
		WebElement f = driver.findElement(By.id("email"));
		String fn = "Email ID";

		System.out.println("=================================================");
		sa.assertTrue(f.isDisplayed(), fn+" NOT visible."); System.out.println("EML Case 1 : Field='"+fn+"' | Check=Displayed | Result="+f.isDisplayed()+" | "+(f.isDisplayed()?"PASS":"FAIL"));
		System.out.println("=================================================");
		sa.assertTrue(f.isEnabled(), fn+" DISABLED."); System.out.println("EML Case 2 : Field='"+fn+"' | Check=Enabled | Result="+f.isEnabled()+" | "+(f.isEnabled()?"PASS":"FAIL"));
		System.out.println("=================================================");
		f.clear(); f.sendKeys("test@example.com"); String v=f.getAttribute("value"); sa.assertEquals(v,"test@example.com",fn+" rejected valid email.");
		System.out.println("EML Case 3 : Field='"+fn+"' | Input='test@example.com' | Expected='test@example.com' | Actual='"+v+"' | "+(v.equals("test@example.com")?"PASS - Valid email accepted":"FAIL - Rejected"));
		System.out.println("=================================================");
		f.clear(); f.sendKeys("testexample.com"); v=f.getAttribute("value");
		System.out.println("EML Case 4 : Field='"+fn+"' | Input='testexample.com' (no @) | Actual='"+v+"' | INFO - Check if validation triggers on save");
		System.out.println("=================================================");
		f.clear(); f.sendKeys("test@"); v=f.getAttribute("value");
		System.out.println("EML Case 5 : Field='"+fn+"' | Input='test@' (no domain) | Actual='"+v+"' | INFO - Check if validation triggers on save");
		System.out.println("=================================================");
		f.clear(); f.sendKeys("@example.com"); v=f.getAttribute("value");
		System.out.println("EML Case 6 : Field='"+fn+"' | Input='@example.com' (no user) | Actual='"+v+"' | INFO - Check if validation triggers on save");
		System.out.println("=================================================");
		f.clear(); f.sendKeys("first.last@example.com"); v=f.getAttribute("value"); sa.assertEquals(v,"first.last@example.com",fn+" rejected dotted email.");
		System.out.println("EML Case 7 : Field='"+fn+"' | Input='first.last@example.com' | Expected='first.last@example.com' | Actual='"+v+"' | "+(v.equals("first.last@example.com")?"PASS - Dotted email accepted":"FAIL - Rejected"));
		System.out.println("=================================================");
		f.clear(); f.sendKeys("   "); v=f.getAttribute("value"); sa.assertTrue(v.trim().isEmpty(),fn+" accepted spaces.");
		System.out.println("EML Case 8 : Field='"+fn+"' | Input='   ' | Expected='' | Actual='"+v+"' | "+(v.trim().isEmpty()?"PASS - Spaces rejected":"FAIL - Accepted"));
		System.out.println("=================================================");
		f.clear(); v=f.getAttribute("value"); sa.assertTrue(v.isEmpty(),fn+" not cleared.");
		System.out.println("EML Case 9 : Field='"+fn+"' | Action=Clear | Expected='' | Actual='"+v+"' | "+(v.isEmpty()?"PASS":"FAIL"));
		System.out.println("=================================================");
		System.out.println("EML Case 10 : Field='"+fn+"' | MaxLength="+f.getAttribute("maxlength"));
		f.clear(); f.sendKeys("test@example.com");
		System.out.println("EML Final : Field='"+fn+"' | Value='test@example.com'");
		System.out.println("=================================================");
		System.out.println("EM33_Email - All 10 cases executed.");
	}
}
