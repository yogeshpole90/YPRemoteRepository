package PhoneBook_Package;

import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;

public class PB5_PhoneNumber extends PB2_Login {

	public void validate() throws Exception {
		System.out.println("=================================================");
		System.out.println("PB5 - PHONE NUMBER VALIDATION START");
		System.out.println("=================================================");
		WebElement ph=driver.findElement(By.id("phone"));
		log("Phone Number","Should be visible","true",String.valueOf(ph.isDisplayed()),ph.isDisplayed());
		sa.assertTrue(ph.isDisplayed(),"Not displayed");
		log("Phone Number","Should be enabled","true",String.valueOf(ph.isEnabled()),ph.isEnabled());
		sa.assertTrue(ph.isEnabled(),"Disabled");
		String inputType=ph.getAttribute("type");
		log("Phone Number","Input type should be 'number'","number",inputType,inputType.equals("number"));
		ph.clear();log("Phone Number","Empty initially","Empty","'"+ph.getAttribute("value")+"'",ph.getAttribute("value").isEmpty());

		// Valid 10 digit
		ph.clear();ph.sendKeys("9999999999");Thread.sleep(200);
		log("Phone Number","Enter valid 10 digit '9999999999'","9999999999",ph.getAttribute("value"),ph.getAttribute("value").equals("9999999999"));

		// Alphabets - should reject (type=number)
		ph.clear();ph.sendKeys("abcdef");Thread.sleep(200);
		String av=ph.getAttribute("value");
		log("Phone Number","Enter alphabets 'abcdef' - should reject","Empty/rejected",av,av.isEmpty()||!av.equals("abcdef"));

		// Special chars - should reject
		ph.clear();ph.sendKeys("@#$%^&");Thread.sleep(200);
		String sv=ph.getAttribute("value");
		log("Phone Number","Enter special chars - should reject","Empty/rejected",sv,sv.isEmpty()||!sv.equals("@#$%^&"));

		// Less than 10 digits
		ph.clear();ph.sendKeys("12345");Thread.sleep(200);
		String minVal=ph.getAttribute("value");
		log("Phone Number","Enter 5 digits (less than 10)","Accepted","Length="+minVal.length(),minVal.equals("12345"));

		// More than 10 digits
		ph.clear();ph.sendKeys("99999999999999");Thread.sleep(200);
		String maxVal=ph.getAttribute("value");
		log("Phone Number","Enter 14 digits (more than 10)","Truncated to max","Length="+maxVal.length(),maxVal.length()<=10);

		// All zeros
		ph.clear();ph.sendKeys("0000000000");Thread.sleep(200);
		String zeroVal=ph.getAttribute("value");
		log("Phone Number","Enter all zeros '0000000000'","0000000000",zeroVal,zeroVal.equals("0000000000"));

		// Negative
		ph.clear();ph.sendKeys("-9999999999");Thread.sleep(200);
		String nv=ph.getAttribute("value");
		log("Phone Number","Enter negative - should reject","No negative",nv,!nv.contains("-")||nv.isEmpty());

		// Decimal
		ph.clear();ph.sendKeys("999.999");Thread.sleep(200);
		String decVal=ph.getAttribute("value");
		log("Phone Number","Enter decimal '999.999' - should reject decimal","No decimal",decVal,!decVal.contains("."));

		// Spaces
		ph.clear();ph.sendKeys("     ");Thread.sleep(200);
		String spVal=ph.getAttribute("value");
		log("Phone Number","Enter spaces only - should reject","Empty/rejected","'"+spVal+"'",spVal.isEmpty());

		// Final value
		ph.clear();ph.sendKeys("9876543210");Thread.sleep(200);
		log("Phone Number","Final value '9876543210' for save","9876543210",ph.getAttribute("value"),ph.getAttribute("value").equals("9876543210"));

		System.out.println("=================================================");
		System.out.println("PB5 - PHONE NUMBER VALIDATION END");
		System.out.println("=================================================");
	}
}


