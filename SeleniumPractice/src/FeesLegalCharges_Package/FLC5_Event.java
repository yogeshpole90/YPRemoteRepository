package FeesLegalCharges_Package;

import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;

public class FLC5_Event extends FLC2_Login {

	public void validate() throws Exception {
		System.out.println("=================================================");
		System.out.println("FLC5 - EVENT FIELD VALIDATION START");
		System.out.println("=================================================");
		WebElement ev=driver.findElement(By.id("event"));
		log("Event","Should be visible","true",String.valueOf(ev.isDisplayed()),ev.isDisplayed());
		log("Event","Should be enabled","true",String.valueOf(ev.isEnabled()),ev.isEnabled());
		String ph=ev.getAttribute("placeholder");
		log("Event","Placeholder","Please Enter Event",ph,ph!=null&&ph.contains("Enter Event"));
		String ml=ev.getAttribute("maxlength");
		log("Event","Maxlength should be 60","60",ml,ml!=null&&ml.equals("60"));
		ev.clear();log("Event","Empty initially","Empty","'"+ev.getAttribute("value")+"'",ev.getAttribute("value").isEmpty());
		ev.clear();ev.sendKeys("Test Event");Thread.sleep(200);
		log("Event","Enter text 'Test Event'","Test Event",ev.getAttribute("value"),ev.getAttribute("value").equals("Test Event"));
		ev.clear();Thread.sleep(200);
		log("Event","Clear field","Empty","'"+ev.getAttribute("value")+"'",ev.getAttribute("value").isEmpty());
		ev.clear();ev.sendKeys("@#$%^&*!");Thread.sleep(200);
		log("Event","Enter special chars","@#$%^&*!",ev.getAttribute("value"),ev.getAttribute("value").equals("@#$%^&*!"));
		ev.clear();ev.sendKeys("1234567890");Thread.sleep(200);
		log("Event","Enter numeric","1234567890",ev.getAttribute("value"),ev.getAttribute("value").equals("1234567890"));
		ev.clear();ev.sendKeys("Event_123");Thread.sleep(200);
		log("Event","Enter alphanumeric","Event_123",ev.getAttribute("value"),ev.getAttribute("value").equals("Event_123"));
		ev.clear();ev.sendKeys("     ");Thread.sleep(200);
		log("Event","Enter spaces only","Spaces","'"+ev.getAttribute("value")+"'",true);
		ev.clear();ev.sendKeys("a".repeat(65));Thread.sleep(200);
		log("Event","Enter 65 chars (max 60)","Max 60 chars","Length="+ev.getAttribute("value").length(),ev.getAttribute("value").length()<=60);
		ev.clear();ev.sendKeys("Court Hearing Event");Thread.sleep(200);
		log("Event","Final value for save","Court Hearing Event",ev.getAttribute("value"),true);
		System.out.println("=================================================");
		System.out.println("FLC5 - EVENT FIELD VALIDATION END");
		System.out.println("=================================================");
	}
}

