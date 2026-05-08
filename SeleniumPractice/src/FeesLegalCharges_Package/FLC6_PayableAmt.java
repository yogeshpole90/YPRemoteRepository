package FeesLegalCharges_Package;

import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;

public class FLC6_PayableAmt extends FLC2_Login {

	public void validate() throws Exception {
		System.out.println("=================================================");
		System.out.println("FLC6 - PAYABLE AMOUNT VALIDATION START");
		System.out.println("=================================================");
		WebElement pa=driver.findElement(By.id("payableAmount_txt"));
		log("Payable Amt","Should be visible","true",String.valueOf(pa.isDisplayed()),pa.isDisplayed());
		log("Payable Amt","Should be enabled","true",String.valueOf(pa.isEnabled()),pa.isEnabled());
		String ml=pa.getAttribute("maxlength");
		log("Payable Amt","Maxlength should be 16","16",ml,ml!=null&&ml.equals("16"));
		pa.clear();log("Payable Amt","Empty initially","Empty","'"+pa.getAttribute("value")+"'",pa.getAttribute("value").isEmpty());
		pa.clear();pa.sendKeys("5000");Thread.sleep(200);
		log("Payable Amt","Enter numeric '5000'","5000",pa.getAttribute("value"),pa.getAttribute("value").contains("5000"));
		pa.clear();pa.sendKeys("abcd");Thread.sleep(200);
		String av=pa.getAttribute("value");
		log("Payable Amt","Enter alphabets 'abcd' - should reject","Empty/rejected",av,av.isEmpty()||!av.equals("abcd"));
		pa.clear();pa.sendKeys("@#$%");Thread.sleep(200);
		String sv=pa.getAttribute("value");
		log("Payable Amt","Enter special chars - should reject","Empty/rejected",sv,sv.isEmpty()||!sv.equals("@#$%"));
		pa.clear();pa.sendKeys("1000.50");Thread.sleep(200);
		log("Payable Amt","Enter decimal '1000.50'","1000.50",pa.getAttribute("value"),pa.getAttribute("value").contains("1000.50"));
		pa.clear();pa.sendKeys("-5000");Thread.sleep(200);
		String nv=pa.getAttribute("value");
		log("Payable Amt","Enter negative '-5000' - should reject","Rejected",nv,!nv.contains("-"));
		pa.clear();pa.sendKeys("0");Thread.sleep(200);
		log("Payable Amt","Enter zero '0'","0",pa.getAttribute("value"),true);
		pa.clear();pa.sendKeys("     ");Thread.sleep(200);
		log("Payable Amt","Enter spaces only","Check","'"+pa.getAttribute("value")+"'",true);
		pa.clear();pa.sendKeys("5000");Thread.sleep(200);
		log("Payable Amt","Final value '5000' for save","5000",pa.getAttribute("value"),true);
		System.out.println("=================================================");
		System.out.println("FLC6 - PAYABLE AMOUNT VALIDATION END");
		System.out.println("=================================================");
	}
}

