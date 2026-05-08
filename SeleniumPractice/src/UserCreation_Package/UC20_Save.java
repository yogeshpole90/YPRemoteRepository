package UserCreation_Package;

import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;

public class UC20_Save extends UC2_Login {

	public void validateSave() throws Exception
	{
		WebElement f = driver.findElement(By.id("btnSave"));
		jse.executeScript("arguments[0].scrollIntoView({block:'center'})", f); Thread.sleep(1000);
		String fn = "Save Button";

		log(fn, "Should be visible on page", "true", String.valueOf(f.isDisplayed()), f.isDisplayed());
		log(fn, "Should be enabled", "true", String.valueOf(f.isEnabled()), f.isEnabled());

		Thread.sleep(2000);
		jse.executeScript("arguments[0].click()", f); Thread.sleep(3000);
		log(fn, "Click Save button", "Record save attempted", "Clicked", true);

		String toast = getToastMsg();
		log(fn, "No error toast after save", "Empty (no error)", toast, toast.isEmpty());
	}
}
