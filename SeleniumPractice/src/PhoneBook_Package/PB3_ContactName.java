package PhoneBook_Package;

import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;

public class PB3_ContactName extends PB2_Login {

	public void validate() throws Exception {
		System.out.println("=================================================");
		System.out.println("PB3 - CONTACT NAME VALIDATION START");
		System.out.println("=================================================");
		WebElement cn=driver.findElement(By.id("contactName"));
		log("Contact Name","Should be visible","true",String.valueOf(cn.isDisplayed()),cn.isDisplayed());
		sa.assertTrue(cn.isDisplayed(),"Not displayed");
		log("Contact Name","Should be enabled","true",String.valueOf(cn.isEnabled()),cn.isEnabled());
		sa.assertTrue(cn.isEnabled(),"Disabled");
		cn.clear();log("Contact Name","Empty initially","Empty","'"+cn.getAttribute("value")+"'",cn.getAttribute("value").isEmpty());
		cn.clear();cn.sendKeys("Yogesh Pole");Thread.sleep(200);
		log("Contact Name","Enter text 'Yogesh Pole'","Yogesh Pole",cn.getAttribute("value"),cn.getAttribute("value").equals("Yogesh Pole"));
		cn.clear();Thread.sleep(200);
		log("Contact Name","Clear field","Empty","'"+cn.getAttribute("value")+"'",cn.getAttribute("value").isEmpty());
		cn.clear();cn.sendKeys("@#$%^&*!");Thread.sleep(200);
		log("Contact Name","Enter special chars","@#$%^&*!",cn.getAttribute("value"),cn.getAttribute("value").equals("@#$%^&*!"));
		cn.clear();cn.sendKeys("1234567890");Thread.sleep(200);
		log("Contact Name","Enter numeric","1234567890",cn.getAttribute("value"),cn.getAttribute("value").equals("1234567890"));
		cn.clear();cn.sendKeys("Name123");Thread.sleep(200);
		log("Contact Name","Enter alphanumeric","Name123",cn.getAttribute("value"),cn.getAttribute("value").equals("Name123"));
		cn.clear();
		cn.sendKeys("     ");
		Thread.sleep(200);
		String spVal=cn.getAttribute("value");
		log("Contact Name","Enter spaces only","Spaces accepted","'"+spVal+"'",spVal.trim().length()>0||spVal.length()==5);
		String ml=cn.getAttribute("maxlength");
		log("Contact Name","Check maxlength","Has maxlength",ml!=null?ml:"null (no limit)",ml!=null&&!ml.isEmpty());
		cn.clear();cn.sendKeys("Test Contact");Thread.sleep(200);
		log("Contact Name","Final value 'Test Contact' for save","Test Contact",cn.getAttribute("value"),cn.getAttribute("value").equals("Test Contact"));
		System.out.println("=================================================");
		System.out.println("PB3 - CONTACT NAME VALIDATION END");
		System.out.println("=================================================");
	}
}

