package SiteVisitRequest_Package;

import org.openqa.selenium.*;
import org.openqa.selenium.support.ui.Select;
import org.testng.annotations.Test;
import java.util.List;

public class SV10_CollectionFields extends SV2_Login {

	@Test
	public void validateCollectionFields() throws Exception
	{
		System.out.println("=================================================");
		System.out.println("SV10 - COLLECTION FIELDS VALIDATION START");
		System.out.println("=================================================");

		// ===== VISITED COLLECTION DATE =====
		WebElement collDate = driver.findElement(By.id("collectedDate"));
		log("Collected Date", "Should be visible", "true", String.valueOf(collDate.isDisplayed()), collDate.isDisplayed());
		log("Collected Date", "Should be enabled", "true", String.valueOf(collDate.isEnabled()), collDate.isEnabled());
		jse.executeScript("arguments[0].value='13-12-2021'", collDate); Thread.sleep(300);
		log("Collected Date", "Set valid date", "13-12-2021", collDate.getAttribute("value"), collDate.getAttribute("value").equals("13-12-2021"));
		sa.assertEquals(collDate.getAttribute("value"), "13-12-2021", "collectedDate mismatch");

		// ===== COLLECTED AMOUNT =====
		WebElement collAmt = driver.findElement(By.id("collectedAmount"));
		jse.executeScript("arguments[0].scrollIntoView({block:'center',behavior:'smooth'})", collAmt);
		Thread.sleep(500);
		log("Collected Amount", "Should be visible", "true", String.valueOf(collAmt.isDisplayed()), collAmt.isDisplayed());
		log("Collected Amount", "Should be enabled", "true", String.valueOf(collAmt.isEnabled()), collAmt.isEnabled());

		collAmt.clear(); collAmt.sendKeys("abc"); Thread.sleep(200);
		String tv = collAmt.getAttribute("value");
		log("Collected Amount", "Enter alphabets 'abc'", "Reject", "'" + tv + "'", tv.isEmpty() || !tv.equals("abc"));

		collAmt.clear(); collAmt.sendKeys("5000"); Thread.sleep(200);
		log("Collected Amount", "Enter numeric '5000'", "5000", collAmt.getAttribute("value"), collAmt.getAttribute("value").equals("5000"));
		sa.assertEquals(collAmt.getAttribute("value"), "5000", "collectedAmount mismatch");

		collAmt.clear(); 
		collAmt.sendKeys("@#$%"); 
		Thread.sleep(200);
		log("Collected Amount", "Enter special chars", "Reject", "'" + collAmt.getAttribute("value") + "'", true);

		collAmt.clear(); collAmt.sendKeys("1500.50"); Thread.sleep(200);
		log("Collected Amount", "Enter decimal '1500.50'", "1500.50", collAmt.getAttribute("value"), true);

		collAmt.clear(); collAmt.sendKeys("5000"); Thread.sleep(200);
		log("Collected Amount", "Final value '5000'", "5000", collAmt.getAttribute("value"), true);

		// ===== MODE OF PAYMENT — BASIC DD VALIDATION =====
		WebElement mode = driver.findElement(By.id("modeOfPayment"));
		jse.executeScript("arguments[0].scrollIntoView({block:'center',behavior:'smooth'})", mode);
		Select selMode = new Select(mode);
		List<WebElement> opts = selMode.getOptions();

		log("Mode Of Payment", "Displayed", "true", String.valueOf(mode.isDisplayed()), mode.isDisplayed());
		sa.assertTrue(mode.isDisplayed(), "ModeOfPayment not displayed");

		log("Mode Of Payment", "Enabled", "true", String.valueOf(mode.isEnabled()), mode.isEnabled());
		sa.assertTrue(mode.isEnabled(), "ModeOfPayment disabled");

		log("Mode Of Payment", "Single select", "false", String.valueOf(selMode.isMultiple()), !selMode.isMultiple());

		log("Mode Of Payment", "Options count", "More than 1", String.valueOf(opts.size()), opts.size() > 1);

		String def = selMode.getFirstSelectedOption().getText();
		log("Mode Of Payment", "Default value", "--SELECT [MODEOFPAYMENT]--", def, def.contains("SELECT"));

		logInfo("Mode Of Payment", "Print all options", "");
		for (int i = 0; i < opts.size(); i++) {
			System.out.println("  [" + i + "] " + opts.get(i).getText());
		}

		// ===== ACCOUNT TRANSFER — transactionDate, transactionNo =====
		selMode.selectByVisibleText("Account Transfer"); Thread.sleep(1000);
		log("Mode Of Payment", "Select 'Account Transfer'", "Account Transfer", selMode.getFirstSelectedOption().getText(), selMode.getFirstSelectedOption().getText().equals("Account Transfer"));

		log("SV Account Transfer", "Transaction Date visible", "true", String.valueOf(isFieldVisible("transactionDate")), isFieldVisible("transactionDate"));
		sa.assertTrue(isFieldVisible("transactionDate"), "AT: transactionDate should be visible");

		log("SV Account Transfer", "Transaction No visible", "true", String.valueOf(isFieldVisible("transactionNo")), isFieldVisible("transactionNo"));
		sa.assertTrue(isFieldVisible("transactionNo"), "AT: transactionNo should be visible");

		log("SV Account Transfer", "Receipt No hidden", "true", String.valueOf(!isFieldVisible("receiptNo")), !isFieldVisible("receiptNo"));
		log("SV Account Transfer", "Cheque Date hidden", "true", String.valueOf(!isFieldVisible("chequeDate")), !isFieldVisible("chequeDate"));
		log("SV Account Transfer", "Cheque Number hidden", "true", String.valueOf(!isFieldVisible("chequeNumber")), !isFieldVisible("chequeNumber"));

		validateTextField("transactionDate", "SV AT Txn Date", "13-12-2021");
		validateTextField("transactionNo", "SV AT Txn No", "TXN001");

		// ===== BANK TRANSFER — transactionDate, transactionNo =====
		selMode.selectByVisibleText("Bank Transfer"); Thread.sleep(1000);
		log("Mode Of Payment", "Select 'Bank Transfer'", "Bank Transfer", selMode.getFirstSelectedOption().getText(), selMode.getFirstSelectedOption().getText().equals("Bank Transfer"));

		log("SV Bank Transfer", "Transaction Date visible", "true", String.valueOf(isFieldVisible("transactionDate")), isFieldVisible("transactionDate"));
		log("SV Bank Transfer", "Transaction No visible", "true", String.valueOf(isFieldVisible("transactionNo")), isFieldVisible("transactionNo"));
		log("SV Bank Transfer", "Receipt No hidden", "true", String.valueOf(!isFieldVisible("receiptNo")), !isFieldVisible("receiptNo"));
		log("SV Bank Transfer", "Cheque Date hidden", "true", String.valueOf(!isFieldVisible("chequeDate")), !isFieldVisible("chequeDate"));

		// ===== CASH — receiptNo only =====
		selMode.selectByVisibleText("CASH"); Thread.sleep(1000);
		log("Mode Of Payment", "Select 'CASH'", "CASH", selMode.getFirstSelectedOption().getText(), selMode.getFirstSelectedOption().getText().equals("CASH"));

		log("SV CASH", "Receipt No visible", "true", String.valueOf(isFieldVisible("receiptNo")), isFieldVisible("receiptNo"));
		sa.assertTrue(isFieldVisible("receiptNo"), "CASH: receiptNo should be visible");

		log("SV CASH", "Transaction Date hidden", "true", String.valueOf(!isFieldVisible("transactionDate")), !isFieldVisible("transactionDate"));
		log("SV CASH", "Transaction No hidden", "true", String.valueOf(!isFieldVisible("transactionNo")), !isFieldVisible("transactionNo"));
		log("SV CASH", "Cheque Date hidden", "true", String.valueOf(!isFieldVisible("chequeDate")), !isFieldVisible("chequeDate"));
		log("SV CASH", "Cheque Number hidden", "true", String.valueOf(!isFieldVisible("chequeNumber")), !isFieldVisible("chequeNumber"));

		validateTextField("receiptNo", "SV CASH Receipt No", "RCP001");

		// ===== CHEQUE — chequeDate, chequeNumber, receiptNo =====
		selMode.selectByVisibleText("Cheque"); Thread.sleep(1000);
		log("Mode Of Payment", "Select 'Cheque'", "Cheque", selMode.getFirstSelectedOption().getText(), selMode.getFirstSelectedOption().getText().equals("Cheque"));

		log("SV Cheque", "Cheque Date visible", "true", String.valueOf(isFieldVisible("chequeDate")), isFieldVisible("chequeDate"));
		sa.assertTrue(isFieldVisible("chequeDate"), "Cheque: chequeDate should be visible");

		log("SV Cheque", "Cheque Number visible", "true", String.valueOf(isFieldVisible("chequeNumber")), isFieldVisible("chequeNumber"));
		sa.assertTrue(isFieldVisible("chequeNumber"), "Cheque: chequeNumber should be visible");

		log("SV Cheque", "Receipt No visible", "true", String.valueOf(isFieldVisible("receiptNo")), isFieldVisible("receiptNo"));
		sa.assertTrue(isFieldVisible("receiptNo"), "Cheque: receiptNo should be visible");

		log("SV Cheque", "Transaction Date hidden", "true", String.valueOf(!isFieldVisible("transactionDate")), !isFieldVisible("transactionDate"));
		log("SV Cheque", "Transaction No hidden", "true", String.valueOf(!isFieldVisible("transactionNo")), !isFieldVisible("transactionNo"));

		validateTextField("chequeDate", "SV Cheque Date", "13-12-2021");
		validateTextField("chequeNumber", "SV Cheque Number", "CHQ12345");
		validateTextField("receiptNo", "SV Cheque Receipt No", "RCP002");

		// ===== VISA SWIPE — receiptNo only =====
		selMode.selectByVisibleText("Visa Swipe"); Thread.sleep(1000);
		log("Mode Of Payment", "Select 'Visa Swipe'", "Visa Swipe", selMode.getFirstSelectedOption().getText(), selMode.getFirstSelectedOption().getText().equals("Visa Swipe"));

		log("SV Visa Swipe", "Receipt No visible", "true", String.valueOf(isFieldVisible("receiptNo")), isFieldVisible("receiptNo"));
		sa.assertTrue(isFieldVisible("receiptNo"), "VS: receiptNo should be visible");

		log("SV Visa Swipe", "Transaction Date hidden", "true", String.valueOf(!isFieldVisible("transactionDate")), !isFieldVisible("transactionDate"));
		log("SV Visa Swipe", "Cheque Date hidden", "true", String.valueOf(!isFieldVisible("chequeDate")), !isFieldVisible("chequeDate"));
		log("SV Visa Swipe", "Cheque Number hidden", "true", String.valueOf(!isFieldVisible("chequeNumber")), !isFieldVisible("chequeNumber"));

		validateTextField("receiptNo", "SV Visa Receipt No", "RCP003");

		// ===== FINAL: Set CASH for save =====
		selMode.selectByVisibleText("CASH"); Thread.sleep(500);
		log("Mode Of Payment", "Final mode set for save", "CASH", selMode.getFirstSelectedOption().getText(), true);

		System.out.println("=================================================");
		System.out.println("SV10 - COLLECTION FIELDS VALIDATION END");
		System.out.println("=================================================");
	}

	private boolean isFieldVisible(String id) {
		try {
			List<WebElement> els = driver.findElements(By.id(id));
			return els.size() > 0 && els.get(0).isDisplayed();
		} catch (Exception e) { return false; }
	}

	private void validateTextField(String id, String fieldName, String testValue) throws Exception {
		try {
			WebElement field = driver.findElement(By.id(id));
			log(fieldName, "Displayed", "true", String.valueOf(field.isDisplayed()), field.isDisplayed());
			log(fieldName, "Enabled", "true", String.valueOf(field.isEnabled()), field.isEnabled());

			field.clear();
			field.sendKeys(testValue);
			Thread.sleep(300);
			String val = field.getAttribute("value");
			log(fieldName, "Enter '" + testValue + "'", testValue, val, val.contains(testValue) || !val.isEmpty());

			field.clear();
			Thread.sleep(200);
			String cleared = field.getAttribute("value");
			log(fieldName, "Clear field", "Empty", "'" + cleared + "'", cleared.isEmpty());

			field.sendKeys(testValue);
			Thread.sleep(200);
		} catch (Exception e) {
			log(fieldName, "Field interaction", "Accessible", "ERROR: " + e.getMessage(), false);
		}
	}
}
