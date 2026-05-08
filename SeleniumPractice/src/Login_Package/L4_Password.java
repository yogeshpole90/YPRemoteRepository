package Login_Package;

import org.openqa.selenium.*;
import org.testng.annotations.Test;

public class L4_Password extends L2_Setup {

	@Test
	public void passwordValidation() throws Exception {
		System.out.println("=================================================");
		System.out.println("L4 - PASSWORD FIELD VALIDATION START");
		System.out.println("=================================================");
		WebElement pwd=driver.findElement(By.id("uiPwd"));

		log("Password","Field should be displayed","true",String.valueOf(pwd.isDisplayed()),pwd.isDisplayed());
		sa.assertTrue(pwd.isDisplayed(),"Password not displayed");
		log("Password","Field should be enabled","true",String.valueOf(pwd.isEnabled()),pwd.isEnabled());
		sa.assertTrue(pwd.isEnabled(),"Password not enabled");
		log("Password","Field type should be password (masked)","password",pwd.getAttribute("type"),pwd.getAttribute("type").equals("password"));
		sa.assertEquals(pwd.getAttribute("type"),"password","Not masked");
		pwd.clear();
		log("Password","Empty field value","Empty","'"+pwd.getAttribute("value")+"'",pwd.getAttribute("value").isEmpty());
		pwd.clear();pwd.sendKeys("     ");
		log("Password","Enter only spaces","Spaces stored","'"+pwd.getAttribute("value")+"'",true);
		pwd.clear();pwd.sendKeys("@#$%^&*!~`");
		log("Password","Enter special chars","@#$%^&*!~`",pwd.getAttribute("value"),pwd.getAttribute("value").equals("@#$%^&*!~`"));
		pwd.clear();pwd.sendKeys("' OR 1=1 --");
		log("Password","SQL Injection","Stored as-is",pwd.getAttribute("value"),pwd.getAttribute("value").equals("' OR 1=1 --"));
		pwd.clear();pwd.sendKeys("<script>alert('hack')</script>");
		log("Password","HTML injection","Stored as-is",pwd.getAttribute("value"),true);
		pwd.clear();pwd.sendKeys("123456789");
		log("Password","Enter numeric '123456789'","123456789",pwd.getAttribute("value"),pwd.getAttribute("value").equals("123456789"));
		pwd.clear();pwd.sendKeys("pass123word");
		log("Password","Enter alphanumeric 'pass123word'","pass123word",pwd.getAttribute("value"),pwd.getAttribute("value").equals("pass123word"));
		pwd.clear();pwd.sendKeys("   password");
		log("Password","Enter leading spaces","Stored","'"+pwd.getAttribute("value")+"'",true);
		pwd.clear();pwd.sendKeys("password   ");
		log("Password","Enter trailing spaces","Stored","'"+pwd.getAttribute("value")+"'",true);
		pwd.clear();String lt="x".repeat(200);pwd.sendKeys(lt);
		log("Password","Enter 200 chars","Length check","Length="+pwd.getAttribute("value").length(),true);
		String ml=pwd.getAttribute("maxlength");
		log("Password","Check maxlength attribute","Maxlength value",ml!=null?ml:"null (no limit)",true);
		pwd.clear();pwd.sendKeys("A");
		log("Password","Enter single char 'A'","A",pwd.getAttribute("value"),pwd.getAttribute("value").equals("A"));
		pwd.clear();pwd.sendKeys("\u00E9\u00F1\u00FC\u00E0");
		log("Password","Enter unicode chars","Unicode stored",pwd.getAttribute("value"),true);
		pwd.clear();pwd.sendKeys("tabtest",Keys.TAB);
		String focId=driver.switchTo().activeElement().getAttribute("id");
		log("Password","TAB key from password","Next field",focId,true);
		pwd.clear();String ph=pwd.getAttribute("placeholder");
		log("Password","Placeholder attribute","Placeholder value",ph!=null?ph:"null",true);
		pwd.clear();pwd.sendKeys("firstpass");pwd.clear();pwd.sendKeys("secondpass");
		log("Password","Clear & retype","secondpass",pwd.getAttribute("value"),pwd.getAttribute("value").equals("secondpass"));
		pwd.clear();pwd.sendKeys("abcdef");pwd.sendKeys(Keys.BACK_SPACE,Keys.BACK_SPACE);
		log("Password","Backspace 2x from 'abcdef'","abcd",pwd.getAttribute("value"),pwd.getAttribute("value").equals("abcd"));
		pwd.clear();
		System.out.println("=================================================");
		System.out.println("L4 - PASSWORD FIELD VALIDATION END");
		System.out.println("=================================================");
	}
}


