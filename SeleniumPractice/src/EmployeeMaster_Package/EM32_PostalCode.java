package EmployeeMaster_Package;

import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;

public class EM32_PostalCode extends EM2_Login {

	public void validatePostalCode()
	{
		WebElement f = driver.findElement(By.id("postalCode"));
		String fn = "Postal Code";

		System.out.println("=================================================");
		sa.assertTrue(f.isDisplayed(), fn+" NOT visible."); System.out.println("PC Case 1 : Field='"+fn+"' | Check=Displayed | Result="+f.isDisplayed()+" | "+(f.isDisplayed()?"PASS":"FAIL"));
		System.out.println("=================================================");
		sa.assertTrue(f.isEnabled(), fn+" DISABLED."); System.out.println("PC Case 2 : Field='"+fn+"' | Check=Enabled | Result="+f.isEnabled()+" | "+(f.isEnabled()?"PASS":"FAIL"));
		System.out.println("=================================================");
		f.clear(); f.sendKeys("400001"); String v=f.getAttribute("value"); sa.assertEquals(v,"400001",fn+" rejected numeric.");
		System.out.println("PC Case 3 : Field='"+fn+"' | Input='400001' | Expected='400001' | Actual='"+v+"' | "+(v.equals("400001")?"PASS - Numeric accepted":"FAIL - Rejected"));
		System.out.println("=================================================");
		f.clear(); f.sendKeys("abcdef"); v=f.getAttribute("value"); sa.assertTrue(v.isEmpty(),fn+" accepted alphabets.");
		System.out.println("PC Case 4 : Field='"+fn+"' | Input='abcdef' | Expected='' | Actual='"+v+"' | "+(v.isEmpty()?"PASS - Alphabets rejected":"FAIL - Alphabets accepted"));
		System.out.println("=================================================");
		f.clear(); f.sendKeys("@#$%"); v=f.getAttribute("value"); sa.assertTrue(v.isEmpty(),fn+" accepted special chars.");
		System.out.println("PC Case 5 : Field='"+fn+"' | Input='@#$%' | Expected='' | Actual='"+v+"' | "+(v.isEmpty()?"PASS - Special rejected":"FAIL - Accepted"));
		System.out.println("=================================================");
		f.clear(); f.sendKeys("400abc"); v=f.getAttribute("value"); sa.assertNotEquals(v,"400abc",fn+" accepted alphanumeric.");
		System.out.println("PC Case 6 : Field='"+fn+"' | Input='400abc' | Expected=NOT '400abc' | Actual='"+v+"' | "+(!v.equals("400abc")?"PASS - Alphanumeric rejected":"FAIL - Accepted"));
		System.out.println("=================================================");
		f.clear(); f.sendKeys("   "); v=f.getAttribute("value"); sa.assertTrue(v.trim().isEmpty(),fn+" accepted spaces.");
		System.out.println("PC Case 7 : Field='"+fn+"' | Input='   ' | Expected='' | Actual='"+v+"' | "+(v.trim().isEmpty()?"PASS - Spaces rejected":"FAIL - Accepted"));
		System.out.println("=================================================");
		f.clear(); v=f.getAttribute("value"); sa.assertTrue(v.isEmpty(),fn+" not cleared.");
		System.out.println("PC Case 8 : Field='"+fn+"' | Action=Clear | Expected='' | Actual='"+v+"' | "+(v.isEmpty()?"PASS":"FAIL"));
		System.out.println("=================================================");
		System.out.println("PC Case 9 : Field='"+fn+"' | MaxLength="+f.getAttribute("maxlength"));
		System.out.println("=================================================");
		f.clear(); f.sendKeys("-400001"); v=f.getAttribute("value"); sa.assertNotEquals(v,"-400001",fn+" accepted negative.");
		System.out.println("PC Case 10 : Field='"+fn+"' | Input='-400001' | Expected=NOT '-400001' | Actual='"+v+"' | "+(!v.equals("-400001")?"PASS - Negative rejected":"FAIL - Accepted"));
		f.clear(); f.sendKeys("400001");
		System.out.println("PC Final : Field='"+fn+"' | Value='400001'");
		System.out.println("=================================================");
		System.out.println("EM32_PostalCode - All 10 cases executed.");
	}
}
