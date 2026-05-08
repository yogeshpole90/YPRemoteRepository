package DocUpload_Package;

import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;

public class DU3_Frame extends DU2_Login {

	public void switchToFrame() throws Exception
	{
		// Switch to Document Upload frame
		WebElement frame = driver.findElement(By.id("documentUploadPageFrame"));
		jse.executeScript("arguments[0].scrollIntoView({block:'center',behavior:'smooth'})", frame);
		Thread.sleep(1000);
		driver.switchTo().frame("documentUploadPageFrame");

		System.out.println("=================================================");
		System.out.println("Switched to Document Upload Frame: documentUploadPageFrame");
	}
}
