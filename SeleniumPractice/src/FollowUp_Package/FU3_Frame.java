package FollowUp_Package;

import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;

public class FU3_Frame extends FU2_Login {

	public void frame() throws Exception
	{
		// Scroll to Add Follow-Up section
		WebElement addFollowUp = driver.findElement(By.xpath("//*[contains(text(),'Add Follow-Up')]"));
		jse.executeScript("arguments[0].scrollIntoView({block:'center',behavior:'smooth'})", addFollowUp);
		Thread.sleep(1000);

		driver.switchTo().frame("addcommunicationHistoryFrame");
		System.out.println("=================================================");
		System.out.println("Switched to Follow Up Frame: addcommunicationHistoryFrame");

		// Scroll to first dropdown
		WebElement firstDD = driver.findElement(By.id("communicationType"));
		jse.executeScript("arguments[0].scrollIntoView({block:'center'})", firstDD);
		Thread.sleep(500);
	}
}
