package PhoneBook_Package;

import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;

public class PB9_ResetClose extends PB2_Login {

	public void validate() throws Exception {
		System.out.println("=================================================");
		System.out.println("PB9 - RESET & CLOSE BUTTON VALIDATION START");
		System.out.println("=================================================");

		// ===== RESET BUTTON =====
		WebElement resetBtn=driver.findElement(By.id("reset"));
		log("Reset","Should be visible","true",String.valueOf(resetBtn.isDisplayed()),resetBtn.isDisplayed());
		sa.assertTrue(resetBtn.isDisplayed(),"Reset not displayed");
		log("Reset","Should be enabled","true",String.valueOf(resetBtn.isEnabled()),resetBtn.isEnabled());
		sa.assertTrue(resetBtn.isEnabled(),"Reset disabled");
		log("Reset","Button text","Reset",resetBtn.getText().trim(),resetBtn.getText().trim().equals("Reset"));
		log("Reset","Button type","button",resetBtn.getAttribute("type"),resetBtn.getAttribute("type").equals("button"));

		// Fill fields then click Reset
		driver.findElement(By.id("contactName")).clear();
		driver.findElement(By.id("contactName")).sendKeys("Reset Test");
		driver.findElement(By.id("phone")).clear();
		driver.findElement(By.id("phone")).sendKeys("1234567890");
		Thread.sleep(300);

		resetBtn.click();
		Thread.sleep(500);

		String nameAfter=driver.findElement(By.id("contactName")).getAttribute("value");
		String phoneAfter=driver.findElement(By.id("phone")).getAttribute("value");
		log("Reset","Contact Name cleared after reset","Empty","'"+nameAfter+"'",nameAfter.isEmpty());
		log("Reset","Phone Number cleared after reset","Empty","'"+phoneAfter+"'",phoneAfter.isEmpty());

		// Check dropdowns reset to default
		String relAfter=new org.openqa.selenium.support.ui.Select(driver.findElement(By.id("contactRelation"))).getFirstSelectedOption().getText().trim();
		log("Reset","Relation DD reset to default","--SELECT--",relAfter,relAfter.contains("--SELECT"));
		String ptAfter=new org.openqa.selenium.support.ui.Select(driver.findElement(By.id("phoneType"))).getFirstSelectedOption().getText().trim();
		log("Reset","Phone Type DD reset to default","--SELECT--",ptAfter,ptAfter.contains("--SELECT"));
		String iaAfter=new org.openqa.selenium.support.ui.Select(driver.findElement(By.id("isActive"))).getFirstSelectedOption().getText().trim();
		log("Reset","Is Active DD reset to default","--SELECT--",iaAfter,iaAfter.contains("--SELECT"));

		// ===== CLOSE BUTTON =====
		WebElement closeBtn=driver.findElement(By.xpath("//a[@data-dismiss='modal' and contains(text(),'Close')]"));
		log("Close","Should be visible","true",String.valueOf(closeBtn.isDisplayed()),closeBtn.isDisplayed());
		sa.assertTrue(closeBtn.isDisplayed(),"Close not displayed");
		log("Close","Should be enabled","true",String.valueOf(closeBtn.isEnabled()),closeBtn.isEnabled());
		sa.assertTrue(closeBtn.isEnabled(),"Close disabled");
		log("Close","Button text","Close",closeBtn.getText().trim(),closeBtn.getText().trim().equals("Close"));

		// Click Close - page should reload
		closeBtn.click();
		Thread.sleep(1000);

		// After close, page reloads (onclick=location.reload), modal should not be visible
		boolean modalGone;
		try{
			modalGone=driver.findElements(By.id("contactName")).isEmpty()||!driver.findElement(By.id("contactName")).isDisplayed();
		}catch(Exception e){
			modalGone=true; // element not found = modal closed
		}
		log("Close","Modal closed after click","Modal not visible",modalGone?"Modal closed":"Modal still open",modalGone);

		System.out.println("=================================================");
		System.out.println("PB9 - RESET & CLOSE BUTTON VALIDATION END");
		System.out.println("=================================================");
	}
}


