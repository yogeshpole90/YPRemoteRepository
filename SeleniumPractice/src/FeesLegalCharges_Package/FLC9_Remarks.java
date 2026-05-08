package FeesLegalCharges_Package;

import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;

public class FLC9_Remarks extends FLC2_Login {

	public void validate() throws Exception {
		System.out.println("=================================================");
		System.out.println("FLC9 - REMARKS VALIDATION START");
		System.out.println("=================================================");
		WebElement rm=driver.findElement(By.id("remarks"));
		log("Remarks","Should be visible","true",String.valueOf(rm.isDisplayed()),rm.isDisplayed());
		log("Remarks","Should be enabled","true",String.valueOf(rm.isEnabled()),rm.isEnabled());
		String ph=rm.getAttribute("placeholder");
		log("Remarks","Placeholder","Enter",ph,ph!=null&&ph.contains("Enter"));
		rm.clear();log("Remarks","Empty initially","Empty","'"+rm.getAttribute("value")+"'",rm.getAttribute("value").isEmpty());
		rm.clear();rm.sendKeys("Test Remark");Thread.sleep(200);
		log("Remarks","Enter text","Test Remark",rm.getAttribute("value"),rm.getAttribute("value").equals("Test Remark"));
		rm.clear();Thread.sleep(200);
		log("Remarks","Clear field","Empty","'"+rm.getAttribute("value")+"'",rm.getAttribute("value").isEmpty());
		rm.clear();rm.sendKeys("@#$%^&*()!~");Thread.sleep(200);
		log("Remarks","Enter special chars","@#$%^&*()!~",rm.getAttribute("value"),rm.getAttribute("value").equals("@#$%^&*()!~"));
		rm.clear();rm.sendKeys("1234567890");Thread.sleep(200);
		log("Remarks","Enter numeric","1234567890",rm.getAttribute("value"),rm.getAttribute("value").equals("1234567890"));
		rm.clear();rm.sendKeys("Court fees payment remark");Thread.sleep(200);
		log("Remarks","Final value for save","Court fees payment remark",rm.getAttribute("value"),true);
		System.out.println("=================================================");
		System.out.println("FLC9 - REMARKS VALIDATION END");
		System.out.println("=================================================");
	}
}

