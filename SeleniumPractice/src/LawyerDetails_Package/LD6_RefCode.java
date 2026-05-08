package LawyerDetails_Package;

import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;

public class LD6_RefCode extends LD2_Login {
	public static String refCode;

	private void dismissAlert(){try{driver.switchTo().alert().accept();}catch(Exception e){}}
	private void safeClear(WebElement el){try{el.clear();}catch(Exception e){dismissAlert();el.clear();}dismissAlert();}

	public void validate() throws Exception {
		System.out.println("=================================================");
		System.out.println("LD6 - LAWYER REF CODE VALIDATION START");
		System.out.println("=================================================");

		refCode = "REF" + System.currentTimeMillis() % 100000;
		System.out.println(">> Generated Unique Ref Code: " + refCode);

		WebElement r=driver.findElement(By.id("lawyerRefCode"));
		log("Ref Code","Should be visible","true",String.valueOf(r.isDisplayed()),r.isDisplayed());
		log("Ref Code","Should be enabled","true",String.valueOf(r.isEnabled()),r.isEnabled());
		safeClear(r);log("Ref Code","Empty initially","Empty","'"+r.getAttribute("value")+"'",r.getAttribute("value").isEmpty());

		// Alphanumeric
		safeClear(r);r.sendKeys("REF999");Thread.sleep(200);dismissAlert();
		log("Ref Code","Enter alphanumeric 'REF999'","REF999",r.getAttribute("value"),r.getAttribute("value").equals("REF999"));

		// Only numeric
		safeClear(r);r.sendKeys("999888");Thread.sleep(200);dismissAlert();
		log("Ref Code","Enter numeric '999888'","999888",r.getAttribute("value"),r.getAttribute("value").equals("999888"));

		// Only alphabets
		safeClear(r);r.sendKeys("ABCDEF");Thread.sleep(200);dismissAlert();
		log("Ref Code","Enter alphabets 'ABCDEF'","ABCDEF",r.getAttribute("value"),r.getAttribute("value").equals("ABCDEF"));

		// Special chars - alert expected
		safeClear(r);r.sendKeys("@#$%");Thread.sleep(500);
		String alertMsg="";
		try{alertMsg=driver.switchTo().alert().getText();driver.switchTo().alert().accept();}catch(Exception e){}
		String splVal="";
		try{splVal=r.getAttribute("value");}catch(Exception e){dismissAlert();splVal=r.getAttribute("value");}
		boolean splRejected=!alertMsg.isEmpty()||splVal.isEmpty()||!splVal.equals("@#$%");
		log("Ref Code","Enter special chars '@#$%' - should reject","Alert: only alphanumeric",alertMsg.isEmpty()?"value="+splVal:"Alert: "+alertMsg,splRejected);

		// Spaces
		safeClear(r);r.sendKeys("     ");Thread.sleep(500);dismissAlert();
		String spVal="";try{spVal=r.getAttribute("value");}catch(Exception e){dismissAlert();spVal=r.getAttribute("value");}
		log("Ref Code","Enter spaces only","Check","'"+spVal+"'",true);

		// Clear
		safeClear(r);
		log("Ref Code","Clear field","Empty","'"+r.getAttribute("value")+"'",r.getAttribute("value").isEmpty());

		// Maxlength
		String ml=r.getAttribute("maxlength");
		log("Ref Code","Check maxlength","Maxlength",ml!=null?ml:"null (no limit)",true);

		// Final unique value
		safeClear(r);r.sendKeys(refCode);Thread.sleep(200);dismissAlert();
		log("Ref Code","Final unique value '"+refCode+"' for save",refCode,r.getAttribute("value"),r.getAttribute("value").equals(refCode));

		System.out.println("=================================================");
		System.out.println("LD6 - LAWYER REF CODE VALIDATION END");
		System.out.println("=================================================");
	}
}

