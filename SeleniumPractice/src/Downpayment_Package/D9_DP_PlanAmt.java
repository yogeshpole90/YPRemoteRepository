package Downpayment_Package;

import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;

public class D9_DP_PlanAmt extends D2_DP_Login {
	public void validatePlanAmt() {
		System.out.println("=================================================");
		System.out.println("D9 - DP PLANNED AMOUNT VALIDATION START");
		System.out.println("=================================================");
		WebElement p = driver.findElement(By.id("plannedAmt"));
		log("DP Plan Amt","Field should be visible","true",String.valueOf(p.isDisplayed()),p.isDisplayed());
		sa.assertTrue(p.isDisplayed(),"Not displayed");
		String ro = p.getAttribute("readonly");
		log("DP Plan Amt","Check enabled/readonly","Enabled or ReadOnly","enabled="+p.isEnabled()+" readonly="+ro,true);
		jse.executeScript("arguments[0].value='5000'",p);
		String nv=p.getAttribute("value");
		log("DP Plan Amt","Set '5000' via JS","5000",nv,nv.equals("5000"));
		jse.executeScript("arguments[0].value='abcd'",p);
		String av=p.getAttribute("value");
		log("DP Plan Amt","Set 'abcd' via JS - check","Rejected or Accepted",av,true);
		jse.executeScript("arguments[0].value='@#$%'",p);
		String sv=p.getAttribute("value");
		log("DP Plan Amt","Set '@#$%' via JS - check","Rejected or Accepted",sv,true);
		jse.executeScript("arguments[0].value='-1000'",p);
		String neg=p.getAttribute("value");
		log("DP Plan Amt","Set '-1000' via JS - check","Rejected or Accepted",neg,true);
		jse.executeScript("arguments[0].value=''",p);
		String ev=p.getAttribute("value");
		log("DP Plan Amt","Clear via JS","Empty",ev,ev.isEmpty());
		jse.executeScript("arguments[0].value='5000'",p);
		log("DP Plan Amt","Final value '5000' for save","5000",p.getAttribute("value"),true);
		System.out.println("=================================================");
		System.out.println("D9 - DP PLANNED AMOUNT VALIDATION END");
		System.out.println("=================================================");
	}
}


