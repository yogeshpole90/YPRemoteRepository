package LawFirm_Package;

import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;

public class LF3_AddButton extends LF2_Login {

	public void validate() throws Exception {
		System.out.println("=================================================");
		System.out.println("LF3 - ADD BUTTON VALIDATION START");
		System.out.println("=================================================");
		WebElement btn=driver.findElement(By.id("addButton"));
		log("Add Button","Should be visible","true",String.valueOf(btn.isDisplayed()),btn.isDisplayed());
		log("Add Button","Should be enabled","true",String.valueOf(btn.isEnabled()),btn.isEnabled());
		btn.click(); Thread.sleep(1000);
		log("Add Button","Click - form should open","Form opened","Add clicked",true);
		System.out.println("=================================================");
		System.out.println("LF3 - ADD BUTTON VALIDATION END");
		System.out.println("=================================================");
	}
}

