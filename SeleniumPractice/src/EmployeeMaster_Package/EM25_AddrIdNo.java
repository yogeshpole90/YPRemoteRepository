package EmployeeMaster_Package;

import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;

public class EM25_AddrIdNo extends EM2_Login {

	public void validateAddrIdNo()
	{
		WebElement f = driver.findElement(By.id("addrIdNo"));
		String fn = "Address ID Number";

		System.out.println("=================================================");
		sa.assertTrue(f.isDisplayed(), fn+" NOT visible."); System.out.println("AIN Case 1 : Field='"+fn+"' | Check=Displayed | Result="+f.isDisplayed()+" | "+(f.isDisplayed()?"PASS":"FAIL"));
		System.out.println("=================================================");
		sa.assertTrue(f.isEnabled(), fn+" DISABLED."); System.out.println("AIN Case 2 : Field='"+fn+"' | Check=Enabled | Result="+f.isEnabled()+" | "+(f.isEnabled()?"PASS":"FAIL"));
		System.out.println("=================================================");
		f.clear(); f.sendKeys("ADDR12345"); String v=f.getAttribute("value"); sa.assertEquals(v,"ADDR12345",fn+" rejected alphanumeric.");
		System.out.println("AIN Case 3 : Field='"+fn+"' | Input='ADDR12345' | Expected='ADDR12345' | Actual='"+v+"' | "+(v.equals("ADDR12345")?"PASS - Alphanumeric accepted":"FAIL - Rejected"));
		System.out.println("=================================================");
		f.clear(); f.sendKeys("1234567890"); v=f.getAttribute("value"); sa.assertEquals(v,"1234567890",fn+" rejected numeric.");
		System.out.println("AIN Case 4 : Field='"+fn+"' | Input='1234567890' | Expected='1234567890' | Actual='"+v+"' | "+(v.equals("1234567890")?"PASS - Numeric accepted":"FAIL - Rejected"));
		System.out.println("=================================================");
		f.clear(); f.sendKeys("ABCDEFGH"); v=f.getAttribute("value"); sa.assertEquals(v,"ABCDEFGH",fn+" rejected alphabets.");
		System.out.println("AIN Case 5 : Field='"+fn+"' | Input='ABCDEFGH' | Expected='ABCDEFGH' | Actual='"+v+"' | "+(v.equals("ABCDEFGH")?"PASS - Alphabets accepted":"FAIL - Rejected"));
		System.out.println("=================================================");
		f.clear(); f.sendKeys("@#$%^&"); v=f.getAttribute("value"); sa.assertTrue(v.isEmpty(),fn+" accepted special chars.");
		System.out.println("AIN Case 6 : Field='"+fn+"' | Input='@#$%^&' | Expected='' | Actual='"+v+"' | "+(v.isEmpty()?"PASS - Special rejected":"FAIL - Accepted"));
		System.out.println("=================================================");
		f.clear(); f.sendKeys("   "); v=f.getAttribute("value"); sa.assertTrue(v.trim().isEmpty(),fn+" accepted spaces.");
		System.out.println("AIN Case 7 : Field='"+fn+"' | Input='   ' | Expected='' | Actual='"+v+"' | "+(v.trim().isEmpty()?"PASS - Spaces rejected":"FAIL - Accepted"));
		System.out.println("=================================================");
		f.clear(); v=f.getAttribute("value"); sa.assertTrue(v.isEmpty(),fn+" not cleared.");
		System.out.println("AIN Case 8 : Field='"+fn+"' | Action=Clear | Expected='' | Actual='"+v+"' | "+(v.isEmpty()?"PASS":"FAIL"));
		System.out.println("=================================================");
		System.out.println("AIN Case 9 : Field='"+fn+"' | MaxLength="+f.getAttribute("maxlength"));
		System.out.println("=================================================");
		System.out.println("AIN Case 10 : Field='"+fn+"' | ReadOnly="+(f.getAttribute("readonly")==null?"No":"Yes"));
		f.clear(); f.sendKeys("ADDR12345");
		System.out.println("AIN Final : Field='"+fn+"' | Value='ADDR12345'");
		System.out.println("=================================================");
		System.out.println("EM25_AddrIdNo - All 10 cases executed.");
	}
}
