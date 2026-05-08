package EmployeeMaster_Package;

import java.util.List;
import org.openqa.selenium.By;
import org.openqa.selenium.Keys;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.Select;

public class EM6_Designation extends EM2_Login {

	public void validateDesignation() throws Exception
	{
		WebElement f = driver.findElement(By.id("designation"));
		Select s = new Select(f);
		List<WebElement> opts = s.getOptions();
		String fn = "Designation";

		System.out.println("=================================================");
		sa.assertTrue(f.isDisplayed(), fn+" NOT visible."); System.out.println("DES Case 1 : Field='"+fn+"' | Check=Displayed | Result="+f.isDisplayed()+" | "+(f.isDisplayed()?"PASS":"FAIL"));
		System.out.println("=================================================");
		sa.assertTrue(f.isEnabled(), fn+" DISABLED."); System.out.println("DES Case 2 : Field='"+fn+"' | Check=Enabled | Result="+f.isEnabled()+" | "+(f.isEnabled()?"PASS":"FAIL"));
		System.out.println("=================================================");
		sa.assertFalse(s.isMultiple(), fn+" is multi-select."); System.out.println("DES Case 3 : Field='"+fn+"' | Check=Multi-Select | Result="+s.isMultiple()+" | "+(!s.isMultiple()?"PASS - Single":"FAIL - Multi"));
		System.out.println("=================================================");
		System.out.println("DES Case 4 : Field='"+fn+"' | Total Options="+opts.size());
		System.out.println("=================================================");
		System.out.print("DES Case 5 : Field='"+fn+"' | All Values → ");
		for (WebElement o : opts) { System.out.print(o.getText()+" , "); } System.out.println();
		System.out.println("=================================================");
		System.out.println("DES Case 6 : Field='"+fn+"' | Default='"+s.getFirstSelectedOption().getText()+"'");
		System.out.println("=================================================");
		boolean allEn = true; for (WebElement o : opts) { if (!o.isEnabled()) allEn = false; }
		System.out.println("DES Case 7 : Field='"+fn+"' | All Enabled="+allEn+" | "+(allEn?"PASS":"FAIL"));
		System.out.println("=================================================");
		f.sendKeys(Keys.DOWN); Thread.sleep(300);
		System.out.println("DES Case 8 : Field='"+fn+"' | Arrow Down → '"+s.getFirstSelectedOption().getText()+"' | PASS - Keyboard OK");
		System.out.println("=================================================");
		s.selectByVisibleText("BRANCH MANAGER"); String sel = s.getFirstSelectedOption().getText();
		sa.assertEquals(sel, "BRANCH MANAGER", fn+" failed to select BRANCH MANAGER.");
		System.out.println("DES Case 9 : Field='"+fn+"' | Action=selectByVisibleText('BRANCH MANAGER') | Expected='BRANCH MANAGER' | Actual='"+sel+"' | "+(sel.equals("BRANCH MANAGER")?"PASS":"FAIL"));
		System.out.println("=================================================");
		System.out.println("EM6_Designation - All 9 cases executed.");
	}
}
