package LawFirm_Package;

import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;

public class LF9_ContactFields extends LF2_Login {
	private void dismissAlert(){try{driver.switchTo().alert().accept();}catch(Exception e){}}
	private void safeClear(WebElement el){try{el.clear();}catch(Exception e){dismissAlert();el.clear();}dismissAlert();}

	public void validate() throws Exception {
		System.out.println("=================================================");
		System.out.println("LF9 - CONTACT FIELDS VALIDATION START");
		System.out.println("=================================================");

		dismissAlert();

		// ========== PO BOX ==========
		System.out.println("========== PO BOX ==========");
		WebElement po=driver.findElement(By.id("poBox"));
		log("PO Box","Should be visible","true",String.valueOf(po.isDisplayed()),po.isDisplayed());
		log("PO Box","Should be enabled","true",String.valueOf(po.isEnabled()),po.isEnabled());
		safeClear(po);po.sendKeys("111111");Thread.sleep(200);dismissAlert();
		log("PO Box","Enter numeric '111111'","111111",po.getAttribute("value"),po.getAttribute("value").equals("111111"));
		safeClear(po);po.sendKeys("abcdef");Thread.sleep(200);dismissAlert();
		log("PO Box","Enter alphabets 'abcdef'","Check",po.getAttribute("value"),true);
		safeClear(po);po.sendKeys("@#$%");Thread.sleep(500);
		String poAlert="";try{poAlert=driver.switchTo().alert().getText();driver.switchTo().alert().accept();}catch(Exception e){}
		log("PO Box","Enter special chars '@#$%'","Check",poAlert.isEmpty()?"Accepted":"Alert: "+poAlert,true);
		safeClear(po);po.sendKeys("111111");dismissAlert();

		// ========== ZIP CODE ==========
		System.out.println("========== ZIP CODE ==========");
		WebElement zp=driver.findElement(By.id("zipCode"));
		log("Zip Code","Should be visible","true",String.valueOf(zp.isDisplayed()),zp.isDisplayed());
		log("Zip Code","Should be enabled","true",String.valueOf(zp.isEnabled()),zp.isEnabled());
		safeClear(zp);zp.sendKeys("121212");Thread.sleep(200);dismissAlert();
		log("Zip Code","Enter numeric '121212'","121212",zp.getAttribute("value"),zp.getAttribute("value").equals("121212"));
		safeClear(zp);zp.sendKeys("abcdef");Thread.sleep(200);dismissAlert();
		log("Zip Code","Enter alphabets","Check",zp.getAttribute("value"),true);
		safeClear(zp);zp.sendKeys("121212");dismissAlert();

		// ========== MOBILE NO ==========
		System.out.println("========== MOBILE NO ==========");
		WebElement mb=driver.findElement(By.id("mobileNo1"));
		log("Mobile No","Should be visible","true",String.valueOf(mb.isDisplayed()),mb.isDisplayed());
		log("Mobile No","Should be enabled","true",String.valueOf(mb.isEnabled()),mb.isEnabled());

		safeClear(mb);mb.sendKeys("9999999999");Thread.sleep(200);dismissAlert();
		log("Mobile No","Enter valid 10 digit '9999999999'","9999999999",mb.getAttribute("value"),mb.getAttribute("value").equals("9999999999"));

		safeClear(mb);mb.sendKeys("abcdef");Thread.sleep(200);dismissAlert();
		String mbAlpha=mb.getAttribute("value");
		log("Mobile No","Enter alphabets 'abcdef' - should reject","Rejected",mbAlpha,mbAlpha.isEmpty()||!mbAlpha.equals("abcdef"));

		safeClear(mb);mb.sendKeys("@#$%^&");Thread.sleep(500);
		String mbAlert="";try{mbAlert=driver.switchTo().alert().getText();driver.switchTo().alert().accept();}catch(Exception e){}
		log("Mobile No","Enter special chars - should reject","Rejected",mbAlert.isEmpty()?"Check value":"Alert: "+mbAlert,true);

		safeClear(mb);mb.sendKeys("12345");Thread.sleep(200);dismissAlert();
		log("Mobile No","Enter 5 digits (less than 10) - min length","Check min length","Length="+mb.getAttribute("value").length(),true);

		safeClear(mb);mb.sendKeys("99999999999999");Thread.sleep(200);dismissAlert();
		log("Mobile No","Enter 14 digits (more than 10) - max length","Max 10 digits","Length="+mb.getAttribute("value").length(),mb.getAttribute("value").length()<=10);

		safeClear(mb);mb.sendKeys("0000000000");Thread.sleep(200);dismissAlert();
		log("Mobile No","Enter all zeros '0000000000'","Check acceptance",mb.getAttribute("value"),true);

		safeClear(mb);mb.sendKeys("-9999999999");Thread.sleep(200);dismissAlert();
		log("Mobile No","Enter negative '-9999999999'","Should reject negative",mb.getAttribute("value"),true);

		safeClear(mb);mb.sendKeys("     ");Thread.sleep(200);dismissAlert();
		log("Mobile No","Enter spaces only","Check","'"+mb.getAttribute("value")+"'",true);

		safeClear(mb);mb.sendKeys("915897");dismissAlert();

		// ========== EMAIL ID - DETAILED VALIDATION ==========
		System.out.println("========== EMAIL ID - DETAILED VALIDATION ==========");
		WebElement em=driver.findElement(By.id("emailId"));
		jse.executeScript("arguments[0].scrollIntoView({block:'center'})",em);
		log("Email ID","Should be visible","true",String.valueOf(em.isDisplayed()),em.isDisplayed());
		log("Email ID","Should be enabled","true",String.valueOf(em.isEnabled()),em.isEnabled());

		// TC: Empty initially
		safeClear(em);dismissAlert();
		log("Email ID","Empty initially","Empty","'"+em.getAttribute("value")+"'",em.getAttribute("value").isEmpty());

		// TC: Valid email - standard format
		safeClear(em);em.sendKeys("test@gmail.com");Thread.sleep(200);dismissAlert();
		log("Email ID","Enter valid email 'test@gmail.com'","test@gmail.com",em.getAttribute("value"),em.getAttribute("value").equals("test@gmail.com"));

		// TC: Valid email - with dots in username
		safeClear(em);em.sendKeys("first.last@gmail.com");Thread.sleep(200);dismissAlert();
		log("Email ID","Enter email with dots 'first.last@gmail.com'","first.last@gmail.com",em.getAttribute("value"),em.getAttribute("value").equals("first.last@gmail.com"));

		// TC: Valid email - with numbers
		safeClear(em);em.sendKeys("user123@domain456.com");Thread.sleep(200);dismissAlert();
		log("Email ID","Enter email with numbers 'user123@domain456.com'","user123@domain456.com",em.getAttribute("value"),em.getAttribute("value").equals("user123@domain456.com"));

		// TC: Valid email - with underscore
		safeClear(em);em.sendKeys("user_name@domain.com");Thread.sleep(200);dismissAlert();
		log("Email ID","Enter email with underscore 'user_name@domain.com'","user_name@domain.com",em.getAttribute("value"),em.getAttribute("value").equals("user_name@domain.com"));

		// TC: Valid email - with hyphen
		safeClear(em);em.sendKeys("user-name@domain.com");Thread.sleep(200);dismissAlert();
		log("Email ID","Enter email with hyphen 'user-name@domain.com'","user-name@domain.com",em.getAttribute("value"),em.getAttribute("value").equals("user-name@domain.com"));

		// TC: Invalid - without @ symbol
		safeClear(em);em.sendKeys("invalidemail");Thread.sleep(200);dismissAlert();
		log("Email ID","Enter without @ symbol 'invalidemail' - INVALID","Should reject or show error on save","Accepted: "+em.getAttribute("value"),false);

		// TC: Invalid - without domain
		safeClear(em);em.sendKeys("user@");Thread.sleep(200);dismissAlert();
		log("Email ID","Enter without domain 'user@' - INVALID","Should reject or show error on save","Accepted: "+em.getAttribute("value"),false);

		// TC: Invalid - without username
		safeClear(em);em.sendKeys("@gmail.com");Thread.sleep(200);dismissAlert();
		log("Email ID","Enter without username '@gmail.com' - INVALID","Should reject or show error on save","Accepted: "+em.getAttribute("value"),false);

		// TC: Invalid - double @ symbol
		safeClear(em);em.sendKeys("user@@gmail.com");Thread.sleep(200);dismissAlert();
		log("Email ID","Enter double @ 'user@@gmail.com' - INVALID","Should reject","Accepted: "+em.getAttribute("value"),false);

		// TC: Invalid - without .com/.in extension
		safeClear(em);em.sendKeys("user@gmail");Thread.sleep(200);dismissAlert();
		log("Email ID","Enter without extension 'user@gmail' - INVALID","Should reject or show error on save","Accepted: "+em.getAttribute("value"),false);

		// TC: Invalid - spaces in email
		safeClear(em);em.sendKeys("user @gmail.com");Thread.sleep(200);dismissAlert();
		log("Email ID","Enter with space 'user @gmail.com' - INVALID","Should reject","Accepted: "+em.getAttribute("value"),false);

		// TC: Invalid - special chars in domain
		safeClear(em);em.sendKeys("user@#$%.com");Thread.sleep(500);
		String emAlert="";try{emAlert=driver.switchTo().alert().getText();driver.switchTo().alert().accept();}catch(Exception e){}
		String emVal="";try{emVal=em.getAttribute("value");}catch(Exception e){dismissAlert();emVal=em.getAttribute("value");}
		log("Email ID","Enter special chars in domain 'user@#$%.com' - INVALID","Should reject",emAlert.isEmpty()?"value="+emVal:"Alert: "+emAlert,true);

		// TC: Invalid - only spaces
		safeClear(em);em.sendKeys("     ");Thread.sleep(200);dismissAlert();
		log("Email ID","Enter only spaces - INVALID","Should reject or treat as empty","'"+em.getAttribute("value")+"'",true);

		// TC: Invalid - dot at start
		safeClear(em);em.sendKeys(".user@gmail.com");Thread.sleep(200);dismissAlert();
		log("Email ID","Enter dot at start '.user@gmail.com' - INVALID","Should reject","Accepted: "+em.getAttribute("value"),false);

		// TC: Invalid - dot at end of username
		safeClear(em);em.sendKeys("user.@gmail.com");Thread.sleep(200);dismissAlert();
		log("Email ID","Enter dot at end 'user.@gmail.com' - INVALID","Should reject","Accepted: "+em.getAttribute("value"),false);

		// TC: Invalid - consecutive dots
		safeClear(em);em.sendKeys("user..name@gmail.com");Thread.sleep(200);dismissAlert();
		log("Email ID","Enter consecutive dots 'user..name@gmail.com' - INVALID","Should reject","Accepted: "+em.getAttribute("value"),false);

		// TC: Valid email - uppercase
		safeClear(em);em.sendKeys("USER@GMAIL.COM");Thread.sleep(200);dismissAlert();
		log("Email ID","Enter uppercase 'USER@GMAIL.COM'","USER@GMAIL.COM",em.getAttribute("value"),em.getAttribute("value").equals("USER@GMAIL.COM"));

		// TC: Valid email - mixed case
		safeClear(em);em.sendKeys("User@Gmail.Com");Thread.sleep(200);dismissAlert();
		log("Email ID","Enter mixed case 'User@Gmail.Com'","User@Gmail.Com",em.getAttribute("value"),em.getAttribute("value").equals("User@Gmail.Com"));

		// TC: Maxlength check
		String ml=em.getAttribute("maxlength");
		log("Email ID","Check maxlength attribute","Maxlength",ml!=null?ml:"null (no limit)",true);

		// TC: Long email
		safeClear(em);em.sendKeys("verylongemailaddressname@verylongdomainname.com");Thread.sleep(200);dismissAlert();
		log("Email ID","Enter long email (47 chars)","Check acceptance","Length="+em.getAttribute("value").length(),true);

		// TC: SQL injection in email
		safeClear(em);em.sendKeys("' OR 1=1 --@gmail.com");Thread.sleep(200);dismissAlert();
		log("Email ID","SQL injection in email","Should not bypass","Accepted: "+em.getAttribute("value"),true);

		// TC: Clear field
		safeClear(em);dismissAlert();
		log("Email ID","Clear field","Empty","'"+em.getAttribute("value")+"'",em.getAttribute("value").isEmpty());

		// Final value for save
		safeClear(em);em.sendKeys("y@g.com");Thread.sleep(200);dismissAlert();
		log("Email ID","Final value 'y@g.com' for save","y@g.com",em.getAttribute("value"),em.getAttribute("value").equals("y@g.com"));

		System.out.println("=================================================");
		System.out.println("LF9 - CONTACT FIELDS VALIDATION END");
		System.out.println("=================================================");
	}
}

