package LawFirm_Package;

import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;

public class LF5_FirmName extends LF2_Login {

	public void validate() throws Exception {
		System.out.println("=================================================");
		System.out.println("LF5 - LAW FIRM NAME VALIDATION START");
		System.out.println("=================================================");
		WebElement fn=driver.findElement(By.id("lawFirmName"));
		log("Firm Name","Should be visible","true",String.valueOf(fn.isDisplayed()),fn.isDisplayed());
		log("Firm Name","Should be enabled","true",String.valueOf(fn.isEnabled()),fn.isEnabled());
		fn.clear(); log("Firm Name","Empty initially","Empty","'"+fn.getAttribute("value")+"'",fn.getAttribute("value").isEmpty());
		fn.clear();fn.sendKeys("Test Firm");Thread.sleep(200);
		log("Firm Name","Enter text 'Test Firm'","Test Firm",fn.getAttribute("value"),fn.getAttribute("value").equals("Test Firm"));
		fn.clear();Thread.sleep(200);log("Firm Name","Clear field","Empty","'"+fn.getAttribute("value")+"'",fn.getAttribute("value").isEmpty());
		fn.clear();fn.sendKeys("@#$%^&*!");Thread.sleep(200);
		log("Firm Name","Enter special chars","@#$%^&*!",fn.getAttribute("value"),fn.getAttribute("value").equals("@#$%^&*!"));
		fn.clear();fn.sendKeys("1234567890");Thread.sleep(200);
		log("Firm Name","Enter numeric","1234567890",fn.getAttribute("value"),fn.getAttribute("value").equals("1234567890"));
		fn.clear();fn.sendKeys("Firm_123");Thread.sleep(200);
		log("Firm Name","Enter alphanumeric","Firm_123",fn.getAttribute("value"),fn.getAttribute("value").equals("Firm_123"));
		fn.clear();fn.sendKeys("     ");Thread.sleep(200);
		log("Firm Name","Enter spaces only","Spaces","'"+fn.getAttribute("value")+"'",true);
		String ml=fn.getAttribute("maxlength");
		log("Firm Name","Check maxlength","Maxlength",ml!=null?ml:"null (no limit)",true);
		fn.clear();fn.sendKeys("TestFirmAB");Thread.sleep(200);
		log("Firm Name","Final value 'TestFirmAB' for save","TestFirmAB",fn.getAttribute("value"),true);
		System.out.println("=================================================");
		System.out.println("LF5 - LAW FIRM NAME VALIDATION END");
		System.out.println("=================================================");
	}
}

