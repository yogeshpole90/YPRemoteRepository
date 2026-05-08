package ActionDocMap_Package;

import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;

public class ADM3_AddButton extends ADM2_Login {

	public void validateAddButton() throws Exception {
		System.out.println("=================================================");
		System.out.println("ADM3 - ADD BUTTON VALIDATION START");
		System.out.println("=================================================");

		WebElement addBtn = driver.findElement(By.id("addButton"));
		log("Add Button","Add button should be visible","true",String.valueOf(addBtn.isDisplayed()),addBtn.isDisplayed());
		sa.assertTrue(addBtn.isDisplayed(),"Add button not visible");
		log("Add Button","Add button should be enabled","true",String.valueOf(addBtn.isEnabled()),addBtn.isEnabled());
		sa.assertTrue(addBtn.isEnabled(),"Add button not enabled");
		addBtn.click();
		Thread.sleep(1000);
		log("Add Button","Click Add button - create form should open","Form opened","Add clicked",true);

		System.out.println("=================================================");
		System.out.println("ADM3 - ADD BUTTON VALIDATION END");
		System.out.println("=================================================");
	}
}


