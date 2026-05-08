package UserCreation_Package;

import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;

public class UC17_Photo extends UC2_Login {

	public void validatePhoto()
	{
		WebElement f = driver.findElement(By.id("photo"));
		String fn = "Photo";

		log(fn, "Should be visible on page", "true", String.valueOf(f.isDisplayed()), f.isDisplayed());
		log(fn, "Should be enabled", "true", String.valueOf(f.isEnabled()), f.isEnabled());
		logInfo(fn, "Accept attribute", f.getAttribute("accept") != null ? f.getAttribute("accept") : "Not set");
		logInfo(fn, "Field type", "File upload - use sendKeys with file path");
	}
}
