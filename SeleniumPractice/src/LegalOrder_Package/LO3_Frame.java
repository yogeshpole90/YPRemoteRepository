package LegalOrder_Package;

import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;

public class LO3_Frame extends LO2_Login {

	public void frame() throws Exception
	{
		// Legal Order tab click
		WebElement legalOrder = driver.findElement(By.xpath("//a[contains(text(),'Legal Order')]"));
		jse.executeScript("arguments[0].scrollIntoView({block:'center',behavior:'smooth'})", legalOrder);
		Thread.sleep(1000);
		act.doubleClick(legalOrder).build().perform();

		Thread.sleep(2000);

		// Switch to Legal Order frame
		driver.switchTo().frame("getLegalDetailDataFrame");
		System.out.println("=================================================");
		System.out.println("Switched to Legal Order Frame: getLegalDetailDataFrame");
	}
}
