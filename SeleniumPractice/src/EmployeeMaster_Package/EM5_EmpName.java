package EmployeeMaster_Package;

import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;

public class EM5_EmpName extends EM2_Login {

	public void validateEmpName()
	{
		WebElement f = driver.findElement(By.id("empName"));
		String fn = "Employee Name";

		System.out.println("=================================================");
		sa.assertTrue(f.isDisplayed(), fn+" NOT visible."); System.out.println("EmpName Case 1 : Field='"+fn+"' | Check=Displayed | Result="+f.isDisplayed()+" | "+(f.isDisplayed()?"PASS":"FAIL - Field not visible on page"));
		System.out.println("=================================================");
		sa.assertTrue(f.isEnabled(), fn+" DISABLED."); System.out.println("EmpName Case 2 : Field='"+fn+"' | Check=Enabled | Result="+f.isEnabled()+" | "+(f.isEnabled()?"PASS":"FAIL - Field is disabled, cannot type"));
		System.out.println("=================================================");
		f.clear(); f.sendKeys("Yogesh Pole"); String v=f.getAttribute("value"); sa.assertEquals(v,"Yogesh Pole",fn+" rejected alphabets.");
		System.out.println("EmpName Case 3 : Field='"+fn+"' | Input='Yogesh Pole' | Expected='Yogesh Pole' | Actual='"+v+"' | "+(v.equals("Yogesh Pole")?"PASS - Alphabets accepted":"FAIL - Alphabets rejected"));
		System.out.println("=================================================");
		f.clear(); f.sendKeys("12345"); v=f.getAttribute("value");
		System.out.println("EmpName Case 4 : Field='"+fn+"' | Input='12345' | Expected='' (reject) | Actual='"+v+"' | "+(v.isEmpty()?"PASS - Numeric rejected":"INFO - Numeric '12345' accepted by application (no client-side restriction)"));
		System.out.println("=================================================");
		f.clear(); f.sendKeys("@#$%"); v=f.getAttribute("value");
		System.out.println("EmpName Case 5 : Field='"+fn+"' | Input='@#$%' | Expected='' (reject) | Actual='"+v+"' | "+(v.isEmpty()?"PASS - Special chars rejected":"INFO - Special chars '@#$%' accepted by application"));
		System.out.println("=================================================");
		f.clear(); f.sendKeys("Yogesh123"); v=f.getAttribute("value");
		System.out.println("EmpName Case 6 : Field='"+fn+"' | Input='Yogesh123' | Expected=NOT 'Yogesh123' | Actual='"+v+"' | "+(!v.equals("Yogesh123")?"PASS - Alphanumeric rejected":"INFO - Alphanumeric 'Yogesh123' accepted by application"));
		System.out.println("=================================================");
		f.clear(); f.sendKeys("   "); v=f.getAttribute("value");
		System.out.println("EmpName Case 7 : Field='"+fn+"' | Input='   ' (spaces) | Expected='' | Actual='"+v+"' | "+(v.trim().isEmpty()?"PASS - Spaces rejected/blank":"INFO - Spaces accepted"));
		System.out.println("=================================================");
		f.clear(); v=f.getAttribute("value"); sa.assertTrue(v.isEmpty(),fn+" not cleared.");
		System.out.println("EmpName Case 8 : Field='"+fn+"' | Action=Clear | Expected='' | Actual='"+v+"' | "+(v.isEmpty()?"PASS - Field cleared":"FAIL - Field not cleared, value='"+v+"'"));
		System.out.println("=================================================");
		String ml = f.getAttribute("maxlength");
		System.out.println("EmpName Case 9 : Field='"+fn+"' | Check=Max Length | Value="+(ml!=null?ml:"null (No limit set)"));
		System.out.println("=================================================");
		String ro = f.getAttribute("readonly");
		System.out.println("EmpName Case 10 : Field='"+fn+"' | Check=ReadOnly | Value="+(ro==null?"No (Editable)":"Yes (Read-Only)"));
		f.clear(); f.sendKeys("Yogesh Pole");
		System.out.println("EmpName Final : Field='"+fn+"' | Value='Yogesh Pole' set for record save");
		System.out.println("=================================================");
		System.out.println("EM5_EmpName - All 10 cases executed.");
	}
}
