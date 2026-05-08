package CaseStudy_Package;

import org.openqa.selenium.*;

/**
 * CS4_Frame - Scroll to Case Study section (bydefault open)
 */
public class CS4_Frame extends CS2_Setup {

	public void switchFrame() throws Exception
	{
		Thread.sleep(2000);
		WebElement caseStudy = driver.findElement(By.xpath("//*[contains(@href,'=Case Study')]"));
		jse.executeScript("arguments[0].scrollIntoView({block:'center',behavior:'smooth'})", caseStudy);
		Thread.sleep(1000);
	}

}
