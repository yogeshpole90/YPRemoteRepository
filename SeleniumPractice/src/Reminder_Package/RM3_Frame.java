package Reminder_Package;

import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;

public class RM3_Frame extends RM2_Login {

	public void switchToFrame() throws Exception
	{
		WebElement rmTab = driver.findElement(By.xpath("//a[contains(text(),'Reminder')]"));
		jse.executeScript("arguments[0].scrollIntoView({block:'center'})", rmTab);
		Thread.sleep(500);
		act.doubleClick(rmTab).build().perform();
		Thread.sleep(1000);

		WebElement frame = driver.findElement(By.id("fetchReminderDtlsPageFrame"));
		jse.executeScript("arguments[0].scrollIntoView({block:'center'})", frame);
		Thread.sleep(500);
		driver.switchTo().frame("fetchReminderDtlsPageFrame");

		System.out.println("=================================================");
		System.out.println("Switched to Reminder Frame: fetchReminderDtlsPageFrame");
	}
}
