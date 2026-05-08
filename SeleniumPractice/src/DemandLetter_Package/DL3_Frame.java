package DemandLetter_Package;

import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;

public class DL3_Frame extends DL2_Login {

	public void switchToFrame() throws Exception
	{
		// Demand Letter tab click
		WebElement dlTab = driver.findElement(By.xpath("//a[contains(text(),'Demand Letter')]"));
		jse.executeScript("arguments[0].scrollIntoView({block:'center',behavior:'smooth'})", dlTab);
		Thread.sleep(1000);
		act.doubleClick(dlTab).build().perform();
		Thread.sleep(2000);

		// Switch to Demand Letter frame
		WebElement frame = driver.findElement(By.id("addNewDemandLetterFrame"));
		jse.executeScript("arguments[0].scrollIntoView({block:'center',behavior:'smooth'})", frame);
		Thread.sleep(1000);
		driver.switchTo().frame("addNewDemandLetterFrame");

		System.out.println("=================================================");
		System.out.println("Switched to Demand Letter Frame: addNewDemandLetterFrame");
	}
}
