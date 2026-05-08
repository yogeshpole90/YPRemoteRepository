package EmployeeMaster_Package;

import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;

public class EM26_Addr1 extends EM2_Login {

	public void validateAddr1()
	{
		WebElement f = driver.findElement(By.id("address1"));
		String fn = "Address 1";

		System.out.println("=================================================");
		sa.assertTrue(f.isDisplayed(), fn+" NOT visible."); System.out.println("AD1 Case 1 : Field='"+fn+"' | Check=Displayed | Result="+f.isDisplayed()+" | "+(f.isDisplayed()?"PASS":"FAIL"));
		System.out.println("=================================================");
		sa.assertTrue(f.isEnabled(), fn+" DISABLED."); System.out.println("AD1 Case 2 : Field='"+fn+"' | Check=Enabled | Result="+f.isEnabled()+" | "+(f.isEnabled()?"PASS":"FAIL"));
		System.out.println("=================================================");
		f.clear(); f.sendKeys("123 Main Street"); String v=f.getAttribute("value"); sa.assertEquals(v,"123 Main Street",fn+" rejected alphanumeric.");
		System.out.println("AD1 Case 3 : Field='"+fn+"' | Input='123 Main Street' | Expected='123 Main Street' | Actual='"+v+"' | "+(v.equals("123 Main Street")?"PASS - Alphanumeric accepted":"FAIL - Rejected"));
		System.out.println("=================================================");
		f.clear(); f.sendKeys("Flat #12, Block-A"); v=f.getAttribute("value"); sa.assertEquals(v,"Flat #12, Block-A",fn+" rejected special chars in address.");
		System.out.println("AD1 Case 4 : Field='"+fn+"' | Input='Flat #12, Block-A' | Expected='Flat #12, Block-A' | Actual='"+v+"' | "+(v.equals("Flat #12, Block-A")?"PASS - Special chars accepted":"FAIL - Rejected"));
		System.out.println("=================================================");
		f.clear(); f.sendKeys("Mumbai"); v=f.getAttribute("value"); sa.assertEquals(v,"Mumbai",fn+" rejected alphabets.");
		System.out.println("AD1 Case 5 : Field='"+fn+"' | Input='Mumbai' | Expected='Mumbai' | Actual='"+v+"' | "+(v.equals("Mumbai")?"PASS - Alphabets accepted":"FAIL - Rejected"));
		System.out.println("=================================================");
		f.clear(); f.sendKeys("400001"); v=f.getAttribute("value"); sa.assertEquals(v,"400001",fn+" rejected numeric.");
		System.out.println("AD1 Case 6 : Field='"+fn+"' | Input='400001' | Expected='400001' | Actual='"+v+"' | "+(v.equals("400001")?"PASS - Numeric accepted":"FAIL - Rejected"));
		System.out.println("=================================================");
		f.clear(); f.sendKeys("   "); v=f.getAttribute("value");
		System.out.println("AD1 Case 7 : Field='"+fn+"' | Input='   ' | Actual='"+v+"' | "+(v.trim().isEmpty()?"INFO - Blank":"INFO - Spaces stored"));
		System.out.println("=================================================");
		f.clear(); v=f.getAttribute("value"); sa.assertTrue(v.isEmpty(),fn+" not cleared.");
		System.out.println("AD1 Case 8 : Field='"+fn+"' | Action=Clear | Expected='' | Actual='"+v+"' | "+(v.isEmpty()?"PASS":"FAIL"));
		System.out.println("=================================================");
		System.out.println("AD1 Case 9 : Field='"+fn+"' | MaxLength="+f.getAttribute("maxlength"));
		System.out.println("=================================================");
		System.out.println("AD1 Case 10 : Field='"+fn+"' | ReadOnly="+(f.getAttribute("readonly")==null?"No":"Yes"));
		f.clear(); f.sendKeys("123 Main Street");
		System.out.println("AD1 Final : Field='"+fn+"' | Value='123 Main Street'");
		System.out.println("=================================================");
		System.out.println("EM26_Addr1 - All 10 cases executed.");
	}
}
