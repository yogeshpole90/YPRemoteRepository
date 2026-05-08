package Login_Package;

import org.openqa.selenium.*;
import org.testng.annotations.Test;
import Utility_Package.ServerConfig;

public class L3_Username extends L2_Setup {

	@Test
	public void usernameValidation() throws Exception {
		System.out.println("=================================================");
		System.out.println("L3 - USERNAME FIELD VALIDATION START");
		System.out.println("=================================================");
		WebElement uid=driver.findElement(By.id("loginId"));
		WebElement pwd=driver.findElement(By.id("uiPwd"));

		log("Username","Field should be displayed","true",String.valueOf(uid.isDisplayed()),uid.isDisplayed());
		sa.assertTrue(uid.isDisplayed(),"Username not displayed");
		log("Username","Field should be enabled","true",String.valueOf(uid.isEnabled()),uid.isEnabled());
		sa.assertTrue(uid.isEnabled(),"Username not enabled");
		uid.clear();
		log("Username","Empty field value","Empty","'"+uid.getAttribute("value")+"'",uid.getAttribute("value").isEmpty());
		uid.clear();uid.sendKeys("     ");
		log("Username","Enter only spaces","Spaces stored","'"+uid.getAttribute("value")+"'",true);
		
		uid.clear();
		uid.sendKeys("@#$%^&*!");
		log("Username","Enter special chars '@#$%^&*!'","@#$%^&*!",uid.getAttribute("value"),uid.getAttribute("value").equals("@#$%^&*!"));
		
		uid.clear();
		uid.sendKeys("' OR 1=1 --");
		log("Username","SQL Injection '\" OR 1=1 --'","Stored as-is",uid.getAttribute("value"),uid.getAttribute("value").equals("' OR 1=1 --"));
		uid.clear();uid.sendKeys("<script>alert('hack')</script>");
		log("Username","HTML injection","Stored as-is",uid.getAttribute("value"),true);
		uid.clear();uid.sendKeys("123456789");
		log("Username","Enter numeric '123456789'","123456789",uid.getAttribute("value"),uid.getAttribute("value").equals("123456789"));
		uid.clear();uid.sendKeys("test123user");
		log("Username","Enter alphanumeric 'test123user'","test123user",uid.getAttribute("value"),uid.getAttribute("value").equals("test123user"));
		uid.clear();uid.sendKeys("   testuser");
		log("Username","Enter leading spaces '   testuser'","Stored","'"+uid.getAttribute("value")+"'",true);
		uid.clear();uid.sendKeys("testuser   ");
		log("Username","Enter trailing spaces 'testuser   '","Stored","'"+uid.getAttribute("value")+"'",true);
		uid.clear();String lt="a".repeat(200);uid.sendKeys(lt);
		log("Username","Enter 200 chars - long text","Length check","Length="+uid.getAttribute("value").length(),true);
		String ml=uid.getAttribute("maxlength");
		log("Username","Check maxlength attribute","Maxlength value",ml!=null?ml:"null (no limit)",true);
		uid.clear();uid.sendKeys("A");
		log("Username","Enter single char 'A'","A",uid.getAttribute("value"),uid.getAttribute("value").equals("A"));
		uid.clear();uid.sendKeys("\u00E9\u00F1\u00FC\u00E0");
		log("Username","Enter unicode chars","Unicode stored",uid.getAttribute("value"),true);
		uid.clear();uid.sendKeys("tabtest",Keys.TAB);
		String focId=driver.switchTo().activeElement().getAttribute("id");
		log("Username","TAB key moves to password","uiPwd",focId,focId.equals("uiPwd"));
		uid.clear();uid.sendKeys("entertest",Keys.ENTER);Thread.sleep(1000);
		String url=driver.getCurrentUrl();
		log("Username","ENTER key from username","Page action",url,true);
		if(!url.contains("lcs-finairoLending")){driver.get(ServerConfig.getActiveServer());Thread.sleep(2000);}
		uid=driver.findElement(By.id("loginId"));
		uid.clear();uid.sendKeys("firstvalue");uid.clear();uid.sendKeys("secondvalue");
		log("Username","Clear & retype","secondvalue",uid.getAttribute("value"),uid.getAttribute("value").equals("secondvalue"));
		uid.clear();String ph=uid.getAttribute("placeholder");
		log("Username","Placeholder attribute","Placeholder value",ph!=null?ph:"null",true);
		log("Username","Field type attribute","text",uid.getAttribute("type"),uid.getAttribute("type").equals("text"));
		uid.clear();
		System.out.println("=================================================");
		System.out.println("L3 - USERNAME FIELD VALIDATION END");
		System.out.println("=================================================");
	}
}


