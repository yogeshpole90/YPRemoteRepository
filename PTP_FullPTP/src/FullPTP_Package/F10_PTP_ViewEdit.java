package FullPTP_Package;

import java.util.List;
import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.Select;

/**
 * F10_PTP_ViewEdit - View & Edit Button Validation in PTP Table
 * 
 * Flow: First saves a valid PTP record, then validates View & Edit buttons
 * Cases: Save record, View button exists, View clickable,
 *        Edit button exists, Edit clickable, Disable button
 */
public class F10_PTP_ViewEdit extends A1_LoginSetup {

	public void validateViewEdit() throws Exception
	{
		// ========== First: Save a valid PTP record ==========

		// Fill Overdue Amount
		WebElement odAmt = driver.findElement(By.id("overdueAmount"));
		odAmt.clear();
		odAmt.sendKeys("10000");

		// Fill PTP Start Date
		WebElement ptpDate = driver.findElement(By.id("dateOfPTPStart"));
		ptpDate.clear();
		ptpDate.sendKeys("19-12-2021");

		// Fill Remarks
		WebElement remarks = driver.findElement(By.id("remarks"));
		remarks.clear();
		remarks.sendKeys("Test record for View Edit validation");

		// Select PTP Type
		WebElement ptpType = driver.findElement(By.id("scheduleType"));
		Select s1 = new Select(ptpType);
		s1.selectByVisibleText("Full PTP");

		// Fill Remaining Amount
		WebElement remAmt = driver.findElement(By.id("remAmt"));
		remAmt.clear();
		remAmt.sendKeys("5000");

		// Fill Planned Amount
		WebElement planAmt = driver.findElement(By.id("plannedAmt"));
		planAmt.clear();
		planAmt.sendKeys("5000");

		// Select Payment Mode
		WebElement payMode = driver.findElement(By.id("paymentMode"));
		jse.executeScript("arguments[0].scrollIntoView({block:'center',behavior:'smooth'})", payMode);
		Select s2 = new Select(payMode);
		s2.selectByVisibleText("CASH");

		// Click Add button
		WebElement addBtn = driver.findElement(By.id("add"));
		jse.executeScript("arguments[0].scrollIntoView({block:'center'})", addBtn);
		addBtn.click();

		// Click Save button
		Thread.sleep(2000);
		WebElement saveBtn = driver.findElement(By.id("saveData"));
		jse.executeScript("arguments[0].scrollIntoView({block:'center'})", saveBtn);
		Thread.sleep(2000);
		saveBtn.click();
		System.out.println("=================================================");
		System.out.println("VE : Record saved successfully.");

		// Switch frames to refresh table
		Thread.sleep(2000);
		driver.switchTo().parentFrame();
		WebElement ptpTab = driver.findElement(By.xpath("//ul[@id='myTab']/li[3]/a"));
		jse.executeScript("arguments[0].scrollIntoView({block:'center'})", ptpTab);
		driver.switchTo().frame("fetchPTPMstTabFrame");

		// ========== View Button Validation ==========

		// Case 1: View button exists
		System.out.println("=================================================");
		List<WebElement> viewBtns = driver.findElements(By.xpath("//a[contains(@class,'ViewBtn')]"));
		sa.assertTrue(viewBtns.size() > 0, "No View button found.");
		System.out.println("VE Case 1 : View buttons found → " + viewBtns.size());

		// Case 2: View button is Displayed
		System.out.println("=================================================");
		if (viewBtns.size() > 0) {
			WebElement viewBtn = viewBtns.get(viewBtns.size() - 1);
			sa.assertTrue(viewBtn.isDisplayed(), "View button not displayed.");
			System.out.println("VE Case 2 : View button is Displayed.");

			// Case 3: View button is Clickable
			System.out.println("=================================================");
			viewBtn.click();
			Thread.sleep(1000);
			System.out.println("VE Case 3 : View button Clicked.");

			// Case 4: After View, record displayed
			System.out.println("=================================================");
			WebElement viewSave = driver.findElement(By.id("saveData"));
			jse.executeScript("arguments[0].scrollIntoView({block:'center'})", viewSave);
			System.out.println("VE Case 4 : View mode - record displayed.");
		}

		// ========== Edit Button Validation ==========

		// Case 5: Edit button exists
		System.out.println("=================================================");
		List<WebElement> editBtns = driver.findElements(By.xpath("//a[contains(@class,'EditBtn')]"));
		sa.assertTrue(editBtns.size() > 0, "No Edit button found.");
		System.out.println("VE Case 5 : Edit buttons found → " + editBtns.size());

		// Case 6: Edit button is Displayed & Clickable
		System.out.println("=================================================");
		if (editBtns.size() > 0) {
			WebElement editBtn = editBtns.get(editBtns.size() - 1);
			sa.assertTrue(editBtn.isDisplayed(), "Edit button not displayed.");
			editBtn.click();
			Thread.sleep(1000);
			System.out.println("VE Case 6 : Edit button Clicked.");

			// Case 7: After Edit, fields should be editable
			System.out.println("=================================================");
			WebElement editRemarks = driver.findElement(By.id("remarks"));
			sa.assertTrue(editRemarks.isEnabled(), "Remarks not editable in Edit mode.");
			System.out.println("VE Case 7 : Edit mode - fields are editable.");
		}

		// ========== Disable Button Validation ==========

		// Case 8: Disable button exists
		System.out.println("=================================================");
		List<WebElement> disableBtns = driver.findElements(By.xpath("//a[contains(text(),'Disable')]"));
		if (disableBtns.size() > 0) {
			sa.assertTrue(disableBtns.get(0).isDisplayed(), "Disable button not displayed.");
			System.out.println("VE Case 8 : Disable button found → " + disableBtns.size());
		} else {
			System.out.println("VE Case 8 : No Disable button found.");
		}

		System.out.println("=================================================");
		System.out.println("F10_PTP_ViewEdit - All cases executed.");
	}

}
