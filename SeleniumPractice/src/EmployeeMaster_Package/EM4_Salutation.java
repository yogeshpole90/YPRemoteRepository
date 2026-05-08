package EmployeeMaster_Package;

import java.util.List;
import org.openqa.selenium.By;
import org.openqa.selenium.Keys;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.Select;

public class EM4_Salutation extends EM2_Login {

	public void validateSalutation() throws Exception
	{
		WebElement f = driver.findElement(By.id("userSalutation"));
		Select s = new Select(f);
		List<WebElement> opts = s.getOptions();
		String fn = "Salutation";

		System.out.println("=================================================");
		sa.assertTrue(f.isDisplayed(), fn+" NOT visible."); System.out.println("SAL Case 1 : Field='"+fn+"' | Check=Displayed | Result="+f.isDisplayed()+" | "+(f.isDisplayed()?"PASS":"FAIL"));
		System.out.println("=================================================");
		sa.assertTrue(f.isEnabled(), fn+" DISABLED."); System.out.println("SAL Case 2 : Field='"+fn+"' | Check=Enabled | Result="+f.isEnabled()+" | "+(f.isEnabled()?"PASS":"FAIL"));
		System.out.println("=================================================");
		sa.assertFalse(s.isMultiple(), fn+" is multi-select."); System.out.println("SAL Case 3 : Field='"+fn+"' | Check=Multi-Select | Result="+s.isMultiple()+" | "+(!s.isMultiple()?"PASS - Single select":"FAIL - Multi select"));
		System.out.println("=================================================");
		System.out.println("SAL Case 4 : Field='"+fn+"' | Total Options="+opts.size());
		System.out.println("=================================================");
		System.out.print("SAL Case 5 : Field='"+fn+"' | All Values → ");
		for (WebElement o : opts) { System.out.print(o.getText()+" , "); } System.out.println();
		System.out.println("=================================================");
		String def = s.getFirstSelectedOption().getText();
		System.out.println("SAL Case 6 : Field='"+fn+"' | Default Selected='"+def+"'");
		System.out.println("=================================================");
		boolean allEn = true; for (WebElement o : opts) { if (!o.isEnabled()) allEn = false; }
		System.out.println("SAL Case 7 : Field='"+fn+"' | Check=All Options Enabled | Result="+allEn+" | "+(allEn?"PASS":"FAIL - Some disabled"));
		System.out.println("=================================================");
		f.sendKeys(Keys.DOWN); Thread.sleep(300); String keyV = s.getFirstSelectedOption().getText();
		System.out.println("SAL Case 8 : Field='"+fn+"' | Action=Arrow Down | Selected='"+keyV+"' | "+(keyV!=null?"PASS - Keyboard accessible":"FAIL"));
		System.out.println("=================================================");
		s.selectByVisibleText("MR."); String sel = s.getFirstSelectedOption().getText();
		sa.assertEquals(sel, "MR.", fn+" failed to select MR.");
		System.out.println("SAL Case 9 : Field='"+fn+"' | Action=selectByVisibleText('MR.') | Expected='MR.' | Actual='"+sel+"' | "+(sel.equals("MR.")?"PASS":"FAIL"));
		System.out.println("=================================================");
		System.out.println("EM4_Salutation - All 9 cases executed.");
	}
}
