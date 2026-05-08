package EmployeeMaster_Package;

import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;

public class EM28_Addr3 extends EM2_Login {

	public void validateAddr3()
	{
		WebElement f = driver.findElement(By.id("address3"));
		String fn = "Address 3";

		System.out.println("=================================================");
		sa.assertTrue(f.isDisplayed(), fn+" NOT visible."); System.out.println("AD3 Case 1 : Field='"+fn+"' | Check=Displayed | Result="+f.isDisplayed()+" | "+(f.isDisplayed()?"PASS":"FAIL"));
		System.out.println("=================================================");
		sa.assertTrue(f.isEnabled(), fn+" DISABLED."); System.out.println("AD3 Case 2 : Field='"+fn+"' | Check=Enabled | Result="+f.isEnabled()+" | "+(f.isEnabled()?"PASS":"FAIL"));
		System.out.println("=================================================");
		f.clear(); f.sendKeys("Landmark Tower 3"); String v=f.getAttribute("value"); sa.assertEquals(v,"Landmark Tower 3",fn+" rejected alphanumeric.");
		System.out.println("AD3 Case 3 : Field='"+fn+"' | Input='Landmark Tower 3' | Expected='Landmark Tower 3' | Actual='"+v+"' | "+(v.equals("Landmark Tower 3")?"PASS - Accepted":"FAIL - Rejected"));
		System.out.println("=================================================");
		f.clear(); f.sendKeys("Opp. #7, Lane-3"); v=f.getAttribute("value"); sa.assertEquals(v,"Opp. #7, Lane-3",fn+" rejected special.");
		System.out.println("AD3 Case 4 : Field='"+fn+"' | Input='Opp. #7, Lane-3' | Expected='Opp. #7, Lane-3' | Actual='"+v+"' | "+(v.equals("Opp. #7, Lane-3")?"PASS - Special accepted":"FAIL - Rejected"));
		System.out.println("=================================================");
		f.clear(); f.sendKeys("   "); v=f.getAttribute("value");
		System.out.println("AD3 Case 5 : Field='"+fn+"' | Input='   ' | Actual='"+v+"' | "+(v.trim().isEmpty()?"INFO - Blank":"INFO - Spaces stored"));
		System.out.println("=================================================");
		f.clear(); v=f.getAttribute("value"); sa.assertTrue(v.isEmpty(),fn+" not cleared.");
		System.out.println("AD3 Case 6 : Field='"+fn+"' | Action=Clear | Expected='' | Actual='"+v+"' | "+(v.isEmpty()?"PASS":"FAIL"));
		System.out.println("=================================================");
		System.out.println("AD3 Case 7 : Field='"+fn+"' | MaxLength="+f.getAttribute("maxlength"));
		System.out.println("=================================================");
		System.out.println("AD3 Case 8 : Field='"+fn+"' | ReadOnly="+(f.getAttribute("readonly")==null?"No":"Yes"));
		f.clear(); f.sendKeys("Landmark Tower 3");
		System.out.println("AD3 Final : Field='"+fn+"' | Value='Landmark Tower 3'");
		System.out.println("=================================================");
		System.out.println("EM28_Addr3 - All 8 cases executed.");
	}
}
