package SeleniumPackage;

import java.util.concurrent.TimeUnit;

import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.ui.Select;

public class Return5_FNF extends Return4_navig

{

	public void FNF() throws Exception
	{
		jse = (JavascriptExecutor) driver;
		driver.manage().timeouts().implicitlyWait(10, TimeUnit.SECONDS);
		//case opened
		WebElement caseopen = driver.findElement(By.xpath("//*[text()='406']"));
		act = new Actions(driver);
		act.doubleClick(caseopen).build().perform();

		//rem actions clicked
		WebElement remact = driver.findElement(By.xpath("//*[contains(@href,'Remedial Action')]"));
		jse.executeScript("arguments[0].scrollIntoView({block:'center'})", remact);
		Thread.sleep(2000);
		remact.click();

		//FNF
		WebElement FNF = driver.findElement(By.xpath("//*[contains(text() , 'Final Settelment')]"));
		act.doubleClick(FNF).build().perform();

		//Child frame
		driver.switchTo().frame("addSettlementMstFNFFrame");

		//fnfamt
		Thread.sleep(3000);
		WebElement fnfamt = driver.findElement(By.xpath("(//*[contains(@id,'willingToPaySettlementAmt')])[2]"));
		jse.executeScript("arguments[0].scrollIntoView({block:'center'})", fnfamt);
		fnfamt.sendKeys("3000");

		//preCloseDate
		WebElement preclosedate = driver.findElement(By.id("preCloseDate"));
		jse.executeScript("arguments[0].value='Mar 4, 2026';", preclosedate);
		

		//chargesToBeWaved
		WebElement waivedchrg = driver.findElement(By.id("chargesToBeWaved"));
		Select s1 = new Select(waivedchrg);
		s1.selectByVisibleText("yes");

		//chargeType
		WebElement chrgType = driver.findElement(By.id("chargeType"));
		Select s2 = new Select(chrgType);
		s2.selectByVisibleText("Penalty interest");


		//charges
		driver.findElement(By.id("totalcharges")).sendKeys("120");

		//remarks
		driver.findElement(By.id("remarks")).sendKeys("Tested on Date 04-03");

		// save
		driver.findElement(By.id("save1")).click();

		// parent frame reset (safe way)
		driver.switchTo().defaultContent();
		
		jse.executeScript("window.scrollBy(0,-300)");
		
		//child frame
		driver.switchTo().frame("addSettlementMstFNFFrame");
		

		// View
		WebElement view = driver.findElement(By.xpath("//tr[td[text()='3000']]/td[5]/a"));
		jse.executeScript("arguments[0].scrollIntoView{(block:'center')}", view);
		view.click();
		
		jse.executeScript("window.scrollBy(0,800)");


		// Edit
		WebElement edit = driver.findElement(By.xpath("//tr[td[text()='3000']]/td[6]/a"));
		jse.executeScript("arguments[0].scrollIntoView{(block:'center')}", edit);
		edit.click();

		//reset
		WebElement reset = driver.findElement(By.id("reset"));
		jse.executeScript("arguments[0].scrollIntoView{(block:'center')}", reset);
		Thread.sleep(2000);
		reset.click();
		
		driver.findElement(By.id("save1")).click();
		
		




























	}


}
