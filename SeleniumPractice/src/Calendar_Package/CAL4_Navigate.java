package Calendar_Package;

import org.openqa.selenium.*;

/**
 * CAL4_Navigate - Scroll to Calendar tab & click
 */
public class CAL4_Navigate extends CAL2_Setup {

	public void navigateToCalendar() throws Exception
	{
		Thread.sleep(2000);
		WebElement calendarTab = driver.findElement(By.xpath("//*[contains(@href,'=Calendar')]"));
		jse.executeScript("arguments[0].scrollIntoView({block:'center',behavior:'smooth'})", calendarTab);
		Thread.sleep(1000);
		calendarTab.click();
		Thread.sleep(1000);
		WebElement viewall = driver.findElement(By.xpath("//*[text()='View all']"));
		jse.executeScript("arguments[0].scrollIntoView({block:'center',behavior:'smooth'})", viewall);

		
	}

}
