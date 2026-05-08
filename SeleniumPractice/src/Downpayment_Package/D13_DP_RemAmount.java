package Downpayment_Package;
import org.openqa.selenium.By; import org.openqa.selenium.WebElement;
public class D13_DP_RemAmount extends D2_DP_Login {
	public void validateRemAmount(){
		System.out.println("=================================================");
		System.out.println("D13 - REMAINING AMOUNT VALIDATION START");
		System.out.println("=================================================");
		WebElement r=driver.findElement(By.id("remAmt"));
		log("Rem Amount","Field should be visible","true",String.valueOf(r.isDisplayed()),r.isDisplayed());
		String ro=r.getAttribute("readonly");
		log("Rem Amount","Check enabled/readonly","Enabled or ReadOnly","enabled="+r.isEnabled()+" readonly="+ro,true);
		jse.executeScript("arguments[0].value='8000'",r);
		log("Rem Amount","Set '8000' via JS","8000",r.getAttribute("value"),r.getAttribute("value").equals("8000"));
		jse.executeScript("arguments[0].value='abcd'",r);
		log("Rem Amount","Set 'abcd' via JS","Rejected or Accepted",r.getAttribute("value"),true);
		jse.executeScript("arguments[0].value='@#$%'",r);
		log("Rem Amount","Set '@#$%' via JS","Rejected or Accepted",r.getAttribute("value"),true);
		jse.executeScript("arguments[0].value='-3000'",r);
		log("Rem Amount","Set '-3000' via JS","Rejected or Accepted",r.getAttribute("value"),true);
		jse.executeScript("arguments[0].value='2500.75'",r);
		log("Rem Amount","Set '2500.75' via JS","2500.75",r.getAttribute("value"),true);
		jse.executeScript("arguments[0].value='00700'",r);
		log("Rem Amount","Set '00700' via JS - leading zeros","00700 or 700",r.getAttribute("value"),true);
		jse.executeScript("arguments[0].value=''",r);
		log("Rem Amount","Clear via JS","Empty",r.getAttribute("value"),r.getAttribute("value").isEmpty());
		jse.executeScript("arguments[0].value='5000'",r);
		log("Rem Amount","Final value '5000'","5000",r.getAttribute("value"),true);
		System.out.println("=================================================");
		System.out.println("D13 - REMAINING AMOUNT VALIDATION END");
		System.out.println("=================================================");
	}
}


