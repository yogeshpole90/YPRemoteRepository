package FeesLegalCharges_Package;

import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;

public class FLC3_Frame extends FLC2_Login {

	public void switchToFrame() throws Exception
	{
		// Fees & Legal Charges tab click
		WebElement flcTab = driver.findElement(By.xpath("//a[contains(text(),'Fees') and contains(text(),'Charge')]"));
		jse.executeScript("arguments[0].scrollIntoView({block:'center'})", flcTab);
		Thread.sleep(500);
		act.doubleClick(flcTab).build().perform();
		Thread.sleep(1000);

		// Switch to Fees & Legal Charges frame
		WebElement frame = driver.findElement(By.id("viewFessAndChargeFrame"));
		jse.executeScript("arguments[0].scrollIntoView({block:'center'})", frame);
		Thread.sleep(500);
		driver.switchTo().frame("viewFessAndChargeFrame");

		System.out.println("=================================================");
		System.out.println("Switched to Fees & Legal Charges Frame: viewFessAndChargeFrame");
	}
}
