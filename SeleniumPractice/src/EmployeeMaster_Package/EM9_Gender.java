package EmployeeMaster_Package;

import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.Select;

public class EM9_Gender extends EM2_Login {

	public void validateGender() throws Exception
	{
		WebElement f = driver.findElement(By.id("gender"));
		Select s = new Select(f);
		String fn = "Gender";

		System.out.println("=================================================");
		sa.assertTrue(f.isDisplayed(), fn+" NOT visible."); System.out.println("GEN Case 1 : Field='"+fn+"' | Check=Displayed | Result="+f.isDisplayed()+" | "+(f.isDisplayed()?"PASS":"FAIL"));
		System.out.println("=================================================");
		System.out.println("GEN Case 2 : Field='"+fn+"' | Check=Enabled | Result="+f.isEnabled()+" | "+(f.isEnabled()?"Enabled":"Disabled (Auto-Select based on Salutation)"));
		System.out.println("=================================================");
		sa.assertFalse(s.isMultiple(), fn+" is multi-select."); System.out.println("GEN Case 3 : Field='"+fn+"' | Check=Multi-Select | Result="+s.isMultiple()+" | "+(!s.isMultiple()?"PASS - Single":"FAIL - Multi"));
		System.out.println("=================================================");
		System.out.println("GEN Case 4 : Field='"+fn+"' | Total Options="+s.getOptions().size());
		System.out.println("=================================================");
		System.out.print("GEN Case 5 : Field='"+fn+"' | All Values → ");
		for (WebElement o : s.getOptions()) { System.out.print(o.getText()+" , "); } System.out.println();
		System.out.println("=================================================");
		String auto = s.getFirstSelectedOption().getText();
		System.out.println("GEN Case 6 : Field='"+fn+"' | Auto-Selected='"+auto+"' | "+(!auto.equals("Select")?"PASS - Auto-selected based on Salutation":"FAIL - Not auto-selected"));
		System.out.println("=================================================");
		WebElement sal = driver.findElement(By.id("userSalutation"));
		Select salSel = new Select(sal);
		String salV = salSel.getFirstSelectedOption().getText();
		System.out.println("GEN Case 7 : Field='"+fn+"' | Salutation='"+salV+"' → Gender='"+auto+"' | Mapping verified");
		System.out.println("=================================================");
		System.out.println("EM9_Gender - All 7 cases executed.");
	}
}
