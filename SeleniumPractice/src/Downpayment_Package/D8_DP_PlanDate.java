package Downpayment_Package;

import org.openqa.selenium.By;
import org.openqa.selenium.Keys;
import org.openqa.selenium.WebElement;

public class D8_DP_PlanDate extends D2_DP_Login {
	public void validatePlanDate() {
		System.out.println("=================================================");
		System.out.println("D8 - DP PLANNED DATE VALIDATION START");
		System.out.println("=================================================");
		WebElement d = driver.findElement(By.id("planDate"));
		log("DP Plan Date","Field should be visible","true",String.valueOf(d.isDisplayed()),d.isDisplayed());
		sa.assertTrue(d.isDisplayed(),"Not displayed");
		log("DP Plan Date","Field should be enabled","true",String.valueOf(d.isEnabled()),d.isEnabled());
		sa.assertTrue(d.isEnabled(),"Disabled");
		d.click(); d.sendKeys(Keys.TAB);
		log("DP Plan Date","Click on field","Clickable","Click accepted",true);
		String def=d.getAttribute("value");
		log("DP Plan Date","Default value should be empty","Empty","'"+def+"'",def.isEmpty());
		d.clear();d.sendKeys("25-12-2025");d.sendKeys(Keys.TAB);
		String v=d.getAttribute("value");
		log("DP Plan Date","Enter valid date '25-12-2025'","25-12-2025",v,v.equals("25-12-2025"));
		d.clear();d.sendKeys("2025/12/25");d.sendKeys(Keys.TAB);
		String inv=d.getAttribute("value");
		log("DP Plan Date","Enter invalid format '2025/12/25'","Not 2025/12/25",inv,!inv.equals("2025/12/25"));
		d.clear();d.sendKeys("abcdef");d.sendKeys(Keys.TAB);
		String a=d.getAttribute("value");
		log("DP Plan Date","Enter alphabets 'abcdef' - reject","Empty",a,a.isEmpty());
		d.clear();d.sendKeys("@#$%^&");d.sendKeys(Keys.TAB);
		String s=d.getAttribute("value");
		log("DP Plan Date","Enter special chars - reject","Empty",s,s.isEmpty());
		d.clear();d.sendKeys("32-12-2025");d.sendKeys(Keys.TAB);
		String d32=d.getAttribute("value");
		log("DP Plan Date","Enter day 32 (32-12-2025) - REJECT","Not 32-12-2025",d32,!d32.equals("32-12-2025"));
		d.clear();d.sendKeys("15-13-2025");d.sendKeys(Keys.TAB);
		String m13=d.getAttribute("value");
		log("DP Plan Date","Enter month 13 (15-13-2025) - REJECT","Not 15-13-2025",m13,!m13.equals("15-13-2025"));
		d.clear();d.sendKeys("29-02-2023");d.sendKeys(Keys.TAB);
		String nl=d.getAttribute("value");
		log("DP Plan Date","Enter 29-02-2023 non-leap - REJECT","Not 29-02-2023",nl,!nl.equals("29-02-2023"));
		d.clear();d.sendKeys("29-02-2024");d.sendKeys(Keys.TAB);
		String lp=d.getAttribute("value");
		log("DP Plan Date","Enter 29-02-2024 leap year","29-02-2024",lp,lp.equals("29-02-2024"));
		d.clear();d.sendKeys("01-01-2099");d.sendKeys(Keys.TAB);
		String fu=d.getAttribute("value");
		log("DP Plan Date","Enter far future '01-01-2099' - REJECT","Not 01-01-2099",fu,!fu.equals("01-01-2099"));
		d.clear();d.sendKeys("01-01-1900");d.sendKeys(Keys.TAB);
		String pa=d.getAttribute("value");
		log("DP Plan Date","Enter old past '01-01-1900' - REJECT","Not 01-01-1900",pa,!pa.equals("01-01-1900"));
		d.clear();d.sendKeys("00-12-2025");d.sendKeys(Keys.TAB);
		String d0=d.getAttribute("value");
		log("DP Plan Date","Enter day 00 - REJECT","Not 00-12-2025",d0,!d0.equals("00-12-2025"));
		d.clear();d.sendKeys("15-00-2025");d.sendKeys(Keys.TAB);
		String m0=d.getAttribute("value");
		log("DP Plan Date","Enter month 00 - REJECT","Not 15-00-2025",m0,!m0.equals("15-00-2025"));
		d.clear();d.sendKeys("   ");d.sendKeys(Keys.TAB);
		String sp=d.getAttribute("value");
		log("DP Plan Date","Enter spaces only - reject","Empty",sp.trim(),sp.trim().isEmpty());
		d.clear();d.sendKeys("25-12-2025");d.sendKeys(Keys.TAB);
		log("DP Plan Date","Final value '25-12-2025' for save","25-12-2025",d.getAttribute("value"),true);
		System.out.println("=================================================");
		System.out.println("D8 - DP PLANNED DATE VALIDATION END");
		System.out.println("=================================================");
	}
}


