package SeleniumPackage;

import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.annotations.Test;
public class A3_Police extends A1_LoginSetup {

	public void switchToPoliceFrame() {
		// browser already open, same driver used
		driver.switchTo().parentFrame();

		WebDriverWait wait = new WebDriverWait(driver, 15);
		WebElement policereg = wait.until(ExpectedConditions.elementToBeClickable(By.xpath("//a[contains(text(),'Police Complaint')]")));

		jse.executeScript("arguments[0].scrollIntoView({block:'center',behavior:'smooth'})", policereg);
		act.doubleClick(policereg).build().perform();

		driver.switchTo().frame("viewPoliceComplaintRegisterFrame");
	}
}