package com.lcs.automation.pages.remedialaction;

import com.lcs.automation.base.BaseTest;
import org.openqa.selenium.*;
import org.openqa.selenium.support.ui.Select;
import java.util.List;

public class RemedialActionPage extends BaseTest {

	// Frame switch
	public void switchToFrame() {
		driver.switchTo().frame("caseMstListPageFrame");
		System.out.println(">> Switched to Remedial Action Frame");
	}

	// Mandatory check — save without data
	public void validateMandatory() throws Exception {
		WebElement saveBtn = driver.findElement(By.id("save"));
		jse.executeScript("arguments[0].scrollIntoView({block:'center'})", saveBtn);
		Thread.sleep(300);
		saveBtn.click();
		Thread.sleep(500);

		String toast = getToastMsg();
		log("Mandatory Check", "Save without filling any field", "Error toast", toast.isEmpty() ? "No toast" : toast, !toast.isEmpty());
		sa.assertTrue(!toast.isEmpty(), "Mandatory message not shown");
	}

	// Action Name dropdown validation
	public void validateActionNameDD() throws Exception {
		WebElement dd = driver.findElement(By.id("actionId"));
		jse.executeScript("arguments[0].scrollIntoView({block:'center'})", dd);
		Select s = new Select(dd);
		List<WebElement> opts = s.getOptions();

		log("Action Name DD", "Displayed", "true", String.valueOf(dd.isDisplayed()), dd.isDisplayed());
		sa.assertTrue(dd.isDisplayed(), "Not displayed");

		log("Action Name DD", "Enabled", "true", String.valueOf(dd.isEnabled()), dd.isEnabled());
		sa.assertTrue(dd.isEnabled(), "Disabled");

		log("Action Name DD", "Single select", "false", String.valueOf(s.isMultiple()), !s.isMultiple());
		log("Action Name DD", "Options count", ">1", String.valueOf(opts.size()), opts.size() > 1);

		logInfo("Action Name DD", "All options", "");
		for (int i = 0; i < opts.size(); i++) {
			System.out.println("  [" + i + "] " + opts.get(i).getText());
		}

		String def = s.getFirstSelectedOption().getText();
		log("Action Name DD", "Default value", "Default", def, def != null);

		s.selectByIndex(2); Thread.sleep(300);
		log("Action Name DD", "Final value for save", "Selected", s.getFirstSelectedOption().getText(), true);
	}

	// Comments field validation
	public void validateComments() throws Exception {
		WebElement c = driver.findElement(By.id("commments"));
		jse.executeScript("arguments[0].scrollIntoView({block:'center'})", c);

		log("Comments", "Displayed", "true", String.valueOf(c.isDisplayed()), c.isDisplayed());
		sa.assertTrue(c.isDisplayed(), "Not displayed");

		log("Comments", "Enabled", "true", String.valueOf(c.isEnabled()), c.isEnabled());
		sa.assertTrue(c.isEnabled(), "Disabled");

		c.clear(); c.sendKeys("Test comment"); Thread.sleep(200);
		log("Comments", "Enter text", "Test comment", c.getAttribute("value"), c.getAttribute("value").equals("Test comment"));

		c.clear(); c.sendKeys("12345"); Thread.sleep(200);
		log("Comments", "Enter numeric", "12345", c.getAttribute("value"), c.getAttribute("value").equals("12345"));

		c.clear(); c.sendKeys("@#$%^&"); Thread.sleep(200);
		log("Comments", "Enter special chars", "@#$%^&", c.getAttribute("value"), c.getAttribute("value").equals("@#$%^&"));

		c.clear(); Thread.sleep(200);
		log("Comments", "Clear field", "Empty", c.getAttribute("value"), c.getAttribute("value").isEmpty());

		c.clear(); c.sendKeys("Remedial action validation test"); Thread.sleep(200);
		log("Comments", "Final value for save", "Set", c.getAttribute("value"), true);
	}

	// Save and View
	public void validateSaveView() throws Exception {
		WebElement saveBtn = driver.findElement(By.id("save"));
		jse.executeScript("arguments[0].scrollIntoView({block:'center'})", saveBtn);
		Thread.sleep(300);

		log("Save Button", "Displayed", "true", String.valueOf(saveBtn.isDisplayed()), saveBtn.isDisplayed());
		log("Save Button", "Enabled", "true", String.valueOf(saveBtn.isEnabled()), saveBtn.isEnabled());

		saveBtn.click();
		Thread.sleep(500);

		String toast = getSuccessToastMsg();
		log("Save Button", "Toast after save", "Success toast", toast.isEmpty() ? "No toast" : toast, !toast.isEmpty());

		Thread.sleep(1000);

		// View last record
		List<WebElement> viewBtns = driver.findElements(By.xpath("//*[text()='View']"));
		log("View Button", "View buttons count", ">0", String.valueOf(viewBtns.size()), viewBtns.size() > 0);
		sa.assertTrue(viewBtns.size() > 0, "No View button");

		if (viewBtns.size() > 0) {
			viewBtns.get(viewBtns.size() - 1).click();
			Thread.sleep(1000);
			log("View Button", "Clicked last View", "Record displayed", "Clicked", true);

			try {
				String actVal = driver.findElement(By.id("actionId")).getAttribute("value");
				String cmtVal = driver.findElement(By.id("commments")).getAttribute("value");
				log("View Data", "Action Name", "Not empty", actVal.isEmpty() ? "EMPTY" : actVal, !actVal.isEmpty());
				log("View Data", "Comments", "Not empty", cmtVal.isEmpty() ? "EMPTY" : cmtVal, !cmtVal.isEmpty());
			} catch (Exception e) {
				log("View Data", "Fields after View", "Data populated", "Error: " + e.getMessage(), false);
			}
		}
	}
}
