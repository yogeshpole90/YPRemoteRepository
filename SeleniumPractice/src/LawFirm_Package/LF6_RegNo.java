package LawFirm_Package;

import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;

public class LF6_RegNo extends LF2_Login {
	public static String regNo;

	private void dismissAlert() {
		try{driver.switchTo().alert().accept();}catch(Exception e){}
	}

	private void safeClear(WebElement el) {
		try{el.clear();}catch(Exception e){dismissAlert();el.clear();}
		dismissAlert();
	}

	public void validate() throws Exception {
		System.out.println("=================================================");
		System.out.println("LF6 - REGISTRATION NUMBER VALIDATION START");
		System.out.println("=================================================");

		regNo = "REG" + System.currentTimeMillis() % 100000;
		System.out.println(">> Generated Unique Reg No: " + regNo);

		WebElement rn=driver.findElement(By.id("registrationNumber"));
		log("Reg No","Should be visible","true",String.valueOf(rn.isDisplayed()),rn.isDisplayed());
		log("Reg No","Should be enabled","true",String.valueOf(rn.isEnabled()),rn.isEnabled());

		safeClear(rn);
		log("Reg No","Empty initially","Empty","'"+rn.getAttribute("value")+"'",rn.getAttribute("value").isEmpty());

		// Alphanumeric
		safeClear(rn);rn.sendKeys("REG123");Thread.sleep(200);dismissAlert();
		log("Reg No","Enter alphanumeric 'REG123'","REG123",rn.getAttribute("value"),rn.getAttribute("value").equals("REG123"));

		// Only numeric
		safeClear(rn);rn.sendKeys("999888");Thread.sleep(200);dismissAlert();
		log("Reg No","Enter numeric '999888'","999888",rn.getAttribute("value"),rn.getAttribute("value").equals("999888"));

		// Only alphabets
		safeClear(rn);rn.sendKeys("ABCDEF");Thread.sleep(200);dismissAlert();
		log("Reg No","Enter alphabets 'ABCDEF'","ABCDEF",rn.getAttribute("value"),rn.getAttribute("value").equals("ABCDEF"));

		// Special chars - alert expected
		safeClear(rn);rn.sendKeys("@#$%");Thread.sleep(500);
		String alertMsg="";
		try{alertMsg=driver.switchTo().alert().getText();driver.switchTo().alert().accept();}catch(Exception e){}
		String splVal="";
		try{splVal=rn.getAttribute("value");}catch(Exception e){dismissAlert();splVal=rn.getAttribute("value");}
		boolean splRejected=!alertMsg.isEmpty()||splVal.isEmpty()||!splVal.equals("@#$%");
		log("Reg No","Enter special chars '@#$%' - should reject","Alert: only alphanumeric",alertMsg.isEmpty()?"No alert, value="+splVal:"Alert: "+alertMsg,splRejected);

		// Spaces only
		safeClear(rn);rn.sendKeys("     ");Thread.sleep(500);dismissAlert();
		String spVal="";
		try{spVal=rn.getAttribute("value");}catch(Exception e){dismissAlert();spVal=rn.getAttribute("value");}
		log("Reg No","Enter spaces only","Check","'"+spVal+"'",true);

		// Clear check
		safeClear(rn);
		log("Reg No","Clear field","Empty","'"+rn.getAttribute("value")+"'",rn.getAttribute("value").isEmpty());

		// Maxlength
		String ml=rn.getAttribute("maxlength");
		log("Reg No","Check maxlength","Maxlength",ml!=null?ml:"null (no limit)",true);

		// Final unique value
		safeClear(rn);rn.sendKeys(regNo);Thread.sleep(200);dismissAlert();
		log("Reg No","Final unique value '"+regNo+"' for save",regNo,rn.getAttribute("value"),rn.getAttribute("value").equals(regNo));

		System.out.println("=================================================");
		System.out.println("LF6 - REGISTRATION NUMBER VALIDATION END");
		System.out.println("=================================================");
	}
}

