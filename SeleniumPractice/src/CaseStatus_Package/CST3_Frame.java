package CaseStatus_Package;

import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;

public class CST3_Frame extends CST2_Login {

	public void switchToFrame() throws Exception
	{
		// Case Status tab click
		WebElement caseStatusTab = driver.findElement(By.xpath("//a[contains(text(),'Case Status')]"));
		jse.executeScript("arguments[0].scrollIntoView({block:'center',behavior:'smooth'})", caseStatusTab);
		Thread.sleep(1000);
		act.doubleClick(caseStatusTab).build().perform();
		Thread.sleep(2000);

		// Switch to Case Status frame (update frame ID as per actual)
		WebElement frame = driver.findElement(By.id("viewCaseStatusFrame"));
		jse.executeScript("arguments[0].scrollIntoView({block:'center',behavior:'smooth'})", frame);
		Thread.sleep(1000);
		driver.switchTo().frame("viewCaseStatusFrame");

		System.out.println("=================================================");
		System.out.println("Switched to Case Status Frame: viewCaseStatusFrame");
	}
}
