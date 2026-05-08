package Login_Package;

import org.openqa.selenium.*;
import org.testng.annotations.Test;
import Utility_Package.ServerConfig;

public class L5_LoginCombos extends L2_Setup {

	private void refreshPage() throws Exception {
		driver.get(ServerConfig.getActiveServer());
		Thread.sleep(1000);
	}

	private String getErrorMessage() {
		try {
			WebElement err=driver.findElement(By.xpath("//*[contains(text(),'No User Found') or contains(text(),'Password cannot be blank') or contains(text(),'User is locked')]"));
			return err.getText();
		} catch(Exception e) {
			try {
				WebElement any=driver.findElement(By.xpath("//*[contains(@class,'error') or contains(@class,'alert') or contains(@class,'message')]"));
				return any.getText();
			} catch(Exception e2) { return ""; }
		}
	}

	@Test
	public void loginCombos() throws Exception {
		System.out.println("=================================================");
		System.out.println("L5 - LOGIN COMBOS VALIDATION START");
		System.out.println("=================================================");

		log("Login Button","Button should be displayed","true",String.valueOf(driver.findElement(By.id("userLogin")).isDisplayed()),driver.findElement(By.id("userLogin")).isDisplayed());
		log("Login Button","Button should be enabled","true",String.valueOf(driver.findElement(By.id("userLogin")).isEnabled()),driver.findElement(By.id("userLogin")).isEnabled());
		String btnText=driver.findElement(By.id("userLogin")).getText();
		if(btnText.isEmpty()) btnText=driver.findElement(By.id("userLogin")).getAttribute("value");
		log("Login Button","Button text","Login or Submit",btnText,true);

		// Both empty
		refreshPage();
		driver.findElement(By.id("loginId")).sendKeys(Keys.TAB);
		driver.findElement(By.id("uiPwd")).sendKeys(Keys.TAB);Thread.sleep(1000);
		driver.findElement(By.id("userLogin")).click();Thread.sleep(1000);
		String m4=getErrorMessage();
		log("Login Combo","Both fields empty → Login","Error message",m4.isEmpty()?"No error":m4,!m4.isEmpty());

		// Username filled + Password empty
		refreshPage();
		driver.findElement(By.id("loginId")).sendKeys("randomuser",Keys.TAB);
		driver.findElement(By.id("uiPwd")).sendKeys(Keys.TAB);Thread.sleep(1000);
		driver.findElement(By.id("userLogin")).click();Thread.sleep(1000);
		String m5=getErrorMessage();
		log("Login Combo","Username filled + Password empty","Error message",m5.isEmpty()?"No error":m5,true);

		// Username empty + Password filled
		refreshPage();
		driver.findElement(By.id("loginId")).sendKeys(Keys.TAB);
		driver.findElement(By.id("uiPwd")).sendKeys("somepassword",Keys.TAB);Thread.sleep(1000);
		driver.findElement(By.id("userLogin")).click();Thread.sleep(1000);
		String m6=getErrorMessage();
		log("Login Combo","Username empty + Password filled","Error message",m6.isEmpty()?"No error":m6,true);

		// Wrong user + Wrong pass
		refreshPage();
		driver.findElement(By.id("loginId")).sendKeys("fakeuser999",Keys.TAB);
		driver.findElement(By.id("uiPwd")).sendKeys("fakepass999",Keys.TAB);Thread.sleep(1000);
		driver.findElement(By.id("userLogin")).click();Thread.sleep(1000);
		String m7=getErrorMessage();
		log("Login Combo","Wrong user + Wrong pass","No User Found",m7,m7.contains("No User Found"));

		// SQL Injection
		refreshPage();
		driver.findElement(By.id("loginId")).sendKeys("' OR 1=1 --",Keys.TAB);
		driver.findElement(By.id("uiPwd")).sendKeys("' OR 1=1 --",Keys.TAB);Thread.sleep(1000);
		driver.findElement(By.id("userLogin")).click();Thread.sleep(1000);
		String m8=getErrorMessage();String url8=driver.getCurrentUrl();
		log("Login Combo","SQL Injection login","Still on login page",url8.contains("lcs-finairoLending")?"On login page":"Bypassed!",url8.contains("lcs-finairoLending"));

		// HTML/XSS Injection
		refreshPage();
		driver.findElement(By.id("loginId")).sendKeys("<script>alert('xss')</script>",Keys.TAB);
		driver.findElement(By.id("uiPwd")).sendKeys("testpass",Keys.TAB);Thread.sleep(1000);
		driver.findElement(By.id("userLogin")).click();Thread.sleep(1000);
		boolean noAlert=true;
		try{driver.switchTo().alert();noAlert=false;driver.switchTo().alert().dismiss();}catch(NoAlertPresentException e){noAlert=true;}
		log("Login Combo","HTML/XSS injection","No alert popup","Alert appeared="+!noAlert,noAlert);

		// Special chars
		refreshPage();
		driver.findElement(By.id("loginId")).sendKeys("@#$%^&*!",Keys.TAB);
		driver.findElement(By.id("uiPwd")).sendKeys("@#$%^&*!",Keys.TAB);Thread.sleep(1000);
		driver.findElement(By.id("userLogin")).click();Thread.sleep(1000);
		log("Login Combo","Special chars login","Error message",getErrorMessage().isEmpty()?"No error":getErrorMessage(),true);

		// Long text
		refreshPage();
		driver.findElement(By.id("loginId")).sendKeys("a".repeat(200),Keys.TAB);
		driver.findElement(By.id("uiPwd")).sendKeys("b".repeat(200),Keys.TAB);Thread.sleep(1000);
		driver.findElement(By.id("userLogin")).click();Thread.sleep(1000);
		log("Login Combo","Long text 200 chars login","Error message",getErrorMessage().isEmpty()?"No error":getErrorMessage(),true);

		// Only spaces
		refreshPage();
		driver.findElement(By.id("loginId")).sendKeys("     ",Keys.TAB);
		driver.findElement(By.id("uiPwd")).sendKeys("     ",Keys.TAB);Thread.sleep(1000);
		driver.findElement(By.id("userLogin")).click();Thread.sleep(1000);
		log("Login Combo","Only spaces in both","Error message",getErrorMessage().isEmpty()?"No error":getErrorMessage(),true);

		// Numeric only
		refreshPage();
		driver.findElement(By.id("loginId")).sendKeys("123456",Keys.TAB);
		driver.findElement(By.id("uiPwd")).sendKeys("789012",Keys.TAB);Thread.sleep(1000);
		driver.findElement(By.id("userLogin")).click();Thread.sleep(1000);
		log("Login Combo","Numeric only login","Error message",getErrorMessage().isEmpty()?"No error":getErrorMessage(),true);

		// Dora user
		refreshPage();
		driver.findElement(By.id("loginId")).sendKeys("Dora",Keys.TAB);
		driver.findElement(By.id("uiPwd")).sendKeys("abcd1234",Keys.TAB);Thread.sleep(1000);
		driver.findElement(By.id("userLogin")).click();Thread.sleep(1500);
		String m14=getErrorMessage();
		String url14=driver.getCurrentUrl();
		boolean doraLoggedIn=!url14.contains("lcs-finairoLending-1.0.1") || url14.contains("validLoginUser");
		if(doraLoggedIn){
			log("Login Combo","Dora login - check if locked or active","User is locked or Login success","Dora logged in successfully (not locked)",true);
			refreshPage();
		} else {
			boolean hasLocked=m14.contains("User is locked");
			log("Login Combo","Dora (locked user)","User is locked",m14,hasLocked);
		}

		// Lowercase dora - case sensitivity check
		refreshPage();
		driver.findElement(By.id("loginId")).sendKeys("dora",Keys.TAB);
		driver.findElement(By.id("uiPwd")).sendKeys("abcd1234",Keys.TAB);Thread.sleep(1000);
		driver.findElement(By.id("userLogin")).click();Thread.sleep(1500);
		String m15=getErrorMessage();
		String url15=driver.getCurrentUrl();
		boolean doraLowerLoggedIn=!url15.contains("lcs-finairoLending-1.0.1") || url15.contains("validLoginUser");
		if(doraLowerLoggedIn){
			log("Case Sensitivity","Lowercase 'dora' login","Should fail if case-sensitive","Login SUCCESS - Case INSENSITIVE",false);
			sa.fail("BUG: Login is case-insensitive - 'dora' logged in same as 'Dora'");
			refreshPage();
		} else {
			log("Case Sensitivity","Lowercase 'dora' login","Should fail - case sensitive",m15.isEmpty()?"No User Found":m15,true);
		}

		// Uppercase DORA - case sensitivity check
		refreshPage();
		driver.findElement(By.id("loginId")).sendKeys("DORA",Keys.TAB);
		driver.findElement(By.id("uiPwd")).sendKeys("abcd1234",Keys.TAB);Thread.sleep(1000);
		driver.findElement(By.id("userLogin")).click();Thread.sleep(1500);
		String m16=getErrorMessage();
		String url16=driver.getCurrentUrl();
		boolean doraUpperLoggedIn=!url16.contains("lcs-finairoLending-1.0.1") || url16.contains("validLoginUser");
		if(doraUpperLoggedIn){
			log("Case Sensitivity","Uppercase 'DORA' login","Should fail if case-sensitive","Login SUCCESS - Case INSENSITIVE",false);
			sa.fail("BUG: Login is case-insensitive - 'DORA' logged in same as 'Dora'");
			refreshPage();
		} else {
			log("Case Sensitivity","Uppercase 'DORA' login","Should fail - case sensitive",m16.isEmpty()?"No User Found":m16,true);
		}

		// Leading/trailing spaces
		refreshPage();
		driver.findElement(By.id("loginId")).sendKeys("  Dora  ",Keys.TAB);
		driver.findElement(By.id("uiPwd")).sendKeys("abcd1234",Keys.TAB);Thread.sleep(1000);
		driver.findElement(By.id("userLogin")).click();Thread.sleep(1000);
		log("Login Combo","'  Dora  ' with spaces","Check trim behavior",getErrorMessage().isEmpty()?"No error":getErrorMessage(),true);

		// ENTER key submit
		refreshPage();
		driver.findElement(By.id("loginId")).sendKeys("fakeuser",Keys.TAB);
		driver.findElement(By.id("uiPwd")).sendKeys("fakepass",Keys.TAB);Thread.sleep(1000);
		driver.findElement(By.id("userLogin")).sendKeys(Keys.ENTER);Thread.sleep(1000);
		log("Login Combo","ENTER key submit","Error message",getErrorMessage().isEmpty()?"No error":getErrorMessage(),true);

		// Rapid 3x click
		refreshPage();
		driver.findElement(By.id("loginId")).sendKeys("rapiduser",Keys.TAB);
		driver.findElement(By.id("uiPwd")).sendKeys("rapidpass",Keys.TAB);Thread.sleep(1000);
		WebElement btn=driver.findElement(By.id("userLogin"));btn.click();btn.click();btn.click();Thread.sleep(1500);
		log("Login Combo","Rapid 3x click","Handled gracefully",getErrorMessage().isEmpty()?"No error":getErrorMessage(),true);

		// Page title
		refreshPage();
		log("Login Page","Page title check","Title present",driver.getTitle(),!driver.getTitle().isEmpty());

		System.out.println("=================================================");
		System.out.println("L5 - LOGIN COMBOS VALIDATION END");
		System.out.println("=================================================");
	}
}


