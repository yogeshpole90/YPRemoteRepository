package SiteVisitRequest_Package;

import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;

public class SV3_Frame extends SV2_Login {

	public void frame() throws Exception
	{
		// Site Visit Request double click
		WebElement siteVisit = driver.findElement(By.xpath("//*[contains(text(),'Site Visit Request')]"));
		jse.executeScript("arguments[0].scrollIntoView({block:'center',behavior:'smooth'})", siteVisit);
		Thread.sleep(1000);
		act.doubleClick(siteVisit).build().perform();
		Thread.sleep(2000);

		// Switch to frame
		driver.switchTo().frame("createSiteVisitDetailsFrame");
		System.out.println("=================================================");
		System.out.println("Switched to Site Visit Request Frame: createSiteVisitDetailsFrame");

		// Scroll to first dropdown (visitType)
		WebElement firstDD = driver.findElement(By.id("visitType"));
		jse.executeScript("arguments[0].scrollIntoView({block:'center'})", firstDD);
		Thread.sleep(500);
	}
}
