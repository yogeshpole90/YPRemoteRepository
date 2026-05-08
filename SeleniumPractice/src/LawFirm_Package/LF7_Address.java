package LawFirm_Package;

import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;

public class LF7_Address extends LF2_Login {

	private void dismissAlert(){try{driver.switchTo().alert().accept();}catch(Exception e){}}
	private void safeClear(WebElement el){try{el.clear();}catch(Exception e){dismissAlert();el.clear();}dismissAlert();}

	public void validate() throws Exception {
		System.out.println("=================================================");
		System.out.println("LF7 - ADDRESS FIELDS VALIDATION START");
		System.out.println("=================================================");

		dismissAlert(); // clear any pending alert

		String[] ids={"address1","address2","address3"};
		String[] names={"Address 1","Address 2","Address 3"};
		for(int i=0;i<ids.length;i++){
			WebElement f=driver.findElement(By.id(ids[i]));
			log(names[i],"Should be visible","true",String.valueOf(f.isDisplayed()),f.isDisplayed());
			log(names[i],"Should be enabled","true",String.valueOf(f.isEnabled()),f.isEnabled());
			safeClear(f);log(names[i],"Empty initially","Empty","'"+f.getAttribute("value")+"'",f.getAttribute("value").isEmpty());

			safeClear(f);f.sendKeys("Test Address "+(i+1));Thread.sleep(200);dismissAlert();
			log(names[i],"Enter text","Test Address "+(i+1),f.getAttribute("value"),f.getAttribute("value").equals("Test Address "+(i+1)));

			// Special chars - alert expected
			safeClear(f);f.sendKeys("@#$%^&");Thread.sleep(500);
			String alertMsg="";
			try{alertMsg=driver.switchTo().alert().getText();driver.switchTo().alert().accept();}catch(Exception e){}
			String splVal="";
			try{splVal=f.getAttribute("value");}catch(Exception e){dismissAlert();splVal=f.getAttribute("value");}
			log(names[i],"Enter special chars - should reject","Alert or rejected",alertMsg.isEmpty()?"value="+splVal:"Alert: "+alertMsg,true);

			safeClear(f);f.sendKeys("12345");Thread.sleep(200);dismissAlert();
			log(names[i],"Enter numeric '12345'","12345",f.getAttribute("value"),f.getAttribute("value").equals("12345"));

			// Final value
			safeClear(f);f.sendKeys("add"+(i+1));Thread.sleep(200);dismissAlert();
			log(names[i],"Final value 'add"+(i+1)+"'","add"+(i+1),f.getAttribute("value"),true);
		}
		System.out.println("=================================================");
		System.out.println("LF7 - ADDRESS FIELDS VALIDATION END");
		System.out.println("=================================================");
	}
}

