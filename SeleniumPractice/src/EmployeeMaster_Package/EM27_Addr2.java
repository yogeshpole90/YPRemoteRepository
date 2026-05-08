package EmployeeMaster_Package;

import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;

public class EM27_Addr2 extends EM2_Login {

	public void validateAddr2()
	{
		WebElement f = driver.findElement(By.id("address2"));
		String fn = "Address 2";

		System.out.println("=================================================");
		sa.assertTrue(f.isDisplayed(), fn+" NOT visible."); System.out.println("AD2 Case 1 : Field='"+fn+"' | Check=Displayed | Result="+f.isDisplayed()+" | "+(f.isDisplayed()?"PASS":"FAIL"));
		System.out.println("=================================================");
		sa.assertTrue(f.isEnabled(), fn+" DISABLED."); System.out.println("AD2 Case 2 : Field='"+fn+"' | Check=Enabled | Result="+f.isEnabled()+" | "+(f.isEnabled()?"PASS":"FAIL"));
		System.out.println("=================================================");
		f.clear(); f.sendKeys("Block B, Floor 2"); String v=f.getAttribute("value"); sa.assertEquals(v,"Block B, Floor 2",fn+" rejected alphanumeric.");
		System.out.println("AD2 Case 3 : Field='"+fn+"' | Input='Block B, Floor 2' | Expected='Block B, Floor 2' | Actual='"+v+"' | "+(v.equals("Block B, Floor 2")?"PASS - Accepted":"FAIL - Rejected"));
		System.out.println("=================================================");
		f.clear(); f.sendKeys("Near #5, Sector-10"); v=f.getAttribute("value"); sa.assertEquals(v,"Near #5, Sector-10",fn+" rejected special.");
		System.out.println("AD2 Case 4 : Field='"+fn+"' | Input='Near #5, Sector-10' | Expected='Near #5, Sector-10' | Actual='"+v+"' | "+(v.equals("Near #5, Sector-10")?"PASS - Special accepted":"FAIL - Rejected"));
		System.out.println("=================================================");
		f.clear(); f.sendKeys("   "); v=f.getAttribute("value");
		System.out.println("AD2 Case 5 : Field='"+fn+"' | Input='   ' | Actual='"+v+"' | "+(v.trim().isEmpty()?"INFO - Blank":"INFO - Spaces stored"));
		System.out.println("=================================================");
		f.clear(); v=f.getAttribute("value"); sa.assertTrue(v.isEmpty(),fn+" not cleared.");
		System.out.println("AD2 Case 6 : Field='"+fn+"' | Action=Clear | Expected='' | Actual='"+v+"' | "+(v.isEmpty()?"PASS":"FAIL"));
		System.out.println("=================================================");
		System.out.println("AD2 Case 7 : Field='"+fn+"' | MaxLength="+f.getAttribute("maxlength"));
		System.out.println("=================================================");
		System.out.println("AD2 Case 8 : Field='"+fn+"' | ReadOnly="+(f.getAttribute("readonly")==null?"No":"Yes"));
		f.clear(); f.sendKeys("Block B, Floor 2");
		System.out.println("AD2 Final : Field='"+fn+"' | Value='Block B, Floor 2'");
		System.out.println("=================================================");
		System.out.println("EM27_Addr2 - All 8 cases executed.");
	}
}
