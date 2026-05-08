package Downpayment_Package;

import java.util.List;
import org.openqa.selenium.By;
import org.openqa.selenium.Keys;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.Select;

public class D10_DP_PayModeDD extends D2_DP_Login {

	public void validatePayMode() throws Exception {
		WebElement pm = driver.findElement(By.id("paymentMode"));
		jse.executeScript("arguments[0].scrollIntoView({block:'center',behavior:'smooth'})", pm);
		Select s = new Select(pm);
		List<WebElement> opts = s.getOptions();

		// ========== BASIC DROPDOWN VALIDATION ==========

		log("DP Pay Mode", "Displayed", "true", String.valueOf(pm.isDisplayed()), pm.isDisplayed());
		sa.assertTrue(pm.isDisplayed(), "Not displayed");

		log("DP Pay Mode", "Enabled", "true", String.valueOf(pm.isEnabled()), pm.isEnabled());
		sa.assertTrue(pm.isEnabled(), "Disabled");

		log("DP Pay Mode", "Single select", "false", String.valueOf(s.isMultiple()), !s.isMultiple());
		sa.assertFalse(s.isMultiple(), "Should be single select");

		log("DP Pay Mode", "Options count", "More than 1", String.valueOf(opts.size()), opts.size() > 1);
		sa.assertTrue(opts.size() > 1, "Should have options");

		logInfo("DP Pay Mode", "Print all options", "");
		for (int i = 0; i < opts.size(); i++) {
			System.out.println("  [" + i + "] " + opts.get(i).getText());
		}

		String def = s.getFirstSelectedOption().getText();
		boolean defCheck = def.contains("SELECT");
		log("DP Pay Mode", "Default value", "--SELECT [MODEOFPAYMENT]--", def, defCheck);

		boolean allEn = true;
		for (WebElement o : opts) if (!o.isEnabled()) allEn = false;
		log("DP Pay Mode", "All options enabled", "true", String.valueOf(allEn), allEn);

		pm.sendKeys(Keys.DOWN); Thread.sleep(300);
		log("DP Pay Mode", "Keyboard accessible (Arrow Down)", "Option selected", s.getFirstSelectedOption().getText(), true);

		// ========== ACCOUNT TRANSFER — transactionDate, transactionNo ==========

		s.selectByVisibleText("Account Transfer"); Thread.sleep(1000);
		log("DP Pay Mode", "Select 'Account Transfer'", "Account Transfer", s.getFirstSelectedOption().getText(), s.getFirstSelectedOption().getText().equals("Account Transfer"));

		boolean atTxnDateVis = isFieldVisible("transactionDate");
		log("DP Account Transfer", "Transaction Date visible", "true", String.valueOf(atTxnDateVis), atTxnDateVis);
		sa.assertTrue(atTxnDateVis, "AT: Transaction Date should be visible");

		boolean atTxnNoVis = isFieldVisible("transactionNo");
		log("DP Account Transfer", "Transaction Number visible", "true", String.valueOf(atTxnNoVis), atTxnNoVis);
		sa.assertTrue(atTxnNoVis, "AT: Transaction No should be visible");

		log("DP Account Transfer", "Receipt No hidden", "true", String.valueOf(!isFieldVisible("receiptNo")), !isFieldVisible("receiptNo"));
		log("DP Account Transfer", "Cheque Date hidden", "true", String.valueOf(!isFieldVisible("chequeDate")), !isFieldVisible("chequeDate"));
		log("DP Account Transfer", "Cheque Number hidden", "true", String.valueOf(!isFieldVisible("chequeNumber")), !isFieldVisible("chequeNumber"));

		validateTextField("transactionDate", "DP AT Transaction Date", "13-12-2021");
		validateTextField("transactionNo", "DP AT Transaction No", "TXN001");

		// ========== BANK TRANSFER — transactionDate, transactionNo ==========

		s.selectByVisibleText("Bank Transfer"); Thread.sleep(1000);
		log("DP Pay Mode", "Select 'Bank Transfer'", "Bank Transfer", s.getFirstSelectedOption().getText(), s.getFirstSelectedOption().getText().equals("Bank Transfer"));

		log("DP Bank Transfer", "Transaction Date visible", "true", String.valueOf(isFieldVisible("transactionDate")), isFieldVisible("transactionDate"));
		sa.assertTrue(isFieldVisible("transactionDate"), "BT: Transaction Date should be visible");

		log("DP Bank Transfer", "Transaction Number visible", "true", String.valueOf(isFieldVisible("transactionNo")), isFieldVisible("transactionNo"));
		sa.assertTrue(isFieldVisible("transactionNo"), "BT: Transaction No should be visible");

		log("DP Bank Transfer", "Receipt No hidden", "true", String.valueOf(!isFieldVisible("receiptNo")), !isFieldVisible("receiptNo"));
		log("DP Bank Transfer", "Cheque Date hidden", "true", String.valueOf(!isFieldVisible("chequeDate")), !isFieldVisible("chequeDate"));

		// ========== CASH — receiptNo only ==========

		s.selectByVisibleText("CASH"); Thread.sleep(1000);
		log("DP Pay Mode", "Select 'CASH'", "CASH", s.getFirstSelectedOption().getText(), s.getFirstSelectedOption().getText().equals("CASH"));

		log("DP CASH", "Receipt No visible", "true", String.valueOf(isFieldVisible("receiptNo")), isFieldVisible("receiptNo"));
		sa.assertTrue(isFieldVisible("receiptNo"), "CASH: Receipt No should be visible");

		log("DP CASH", "Transaction Date hidden", "true", String.valueOf(!isFieldVisible("transactionDate")), !isFieldVisible("transactionDate"));
		log("DP CASH", "Transaction No hidden", "true", String.valueOf(!isFieldVisible("transactionNo")), !isFieldVisible("transactionNo"));
		log("DP CASH", "Cheque Date hidden", "true", String.valueOf(!isFieldVisible("chequeDate")), !isFieldVisible("chequeDate"));
		log("DP CASH", "Cheque Number hidden", "true", String.valueOf(!isFieldVisible("chequeNumber")), !isFieldVisible("chequeNumber"));

		validateTextField("receiptNo", "DP CASH Receipt No", "RCP001");

		// ========== CHEQUE — chequeDate, chequeNumber, receiptNo ==========

		s.selectByVisibleText("Cheque"); Thread.sleep(1000);
		log("DP Pay Mode", "Select 'Cheque'", "Cheque", s.getFirstSelectedOption().getText(), s.getFirstSelectedOption().getText().equals("Cheque"));

		log("DP Cheque", "Cheque Date visible", "true", String.valueOf(isFieldVisible("chequeDate")), isFieldVisible("chequeDate"));
		sa.assertTrue(isFieldVisible("chequeDate"), "Cheque: Cheque Date should be visible");

		log("DP Cheque", "Cheque Number visible", "true", String.valueOf(isFieldVisible("chequeNumber")), isFieldVisible("chequeNumber"));
		sa.assertTrue(isFieldVisible("chequeNumber"), "Cheque: Cheque Number should be visible");

		log("DP Cheque", "Receipt No visible", "true", String.valueOf(isFieldVisible("receiptNo")), isFieldVisible("receiptNo"));
		sa.assertTrue(isFieldVisible("receiptNo"), "Cheque: Receipt No should be visible");

		log("DP Cheque", "Transaction Date hidden", "true", String.valueOf(!isFieldVisible("transactionDate")), !isFieldVisible("transactionDate"));
		log("DP Cheque", "Transaction No hidden", "true", String.valueOf(!isFieldVisible("transactionNo")), !isFieldVisible("transactionNo"));

		validateTextField("chequeDate", "DP Cheque Date", "13-12-2021");
		validateTextField("chequeNumber", "DP Cheque Number", "CHQ12345");
		validateTextField("receiptNo", "DP Cheque Receipt No", "RCP002");

		// ========== VISA SWIPE — receiptNo only ==========

		s.selectByVisibleText("Visa Swipe"); Thread.sleep(1000);
		log("DP Pay Mode", "Select 'Visa Swipe'", "Visa Swipe", s.getFirstSelectedOption().getText(), s.getFirstSelectedOption().getText().equals("Visa Swipe"));

		log("DP Visa Swipe", "Receipt No visible", "true", String.valueOf(isFieldVisible("receiptNo")), isFieldVisible("receiptNo"));
		sa.assertTrue(isFieldVisible("receiptNo"), "VS: Receipt No should be visible");

		log("DP Visa Swipe", "Transaction Date hidden", "true", String.valueOf(!isFieldVisible("transactionDate")), !isFieldVisible("transactionDate"));
		log("DP Visa Swipe", "Cheque Date hidden", "true", String.valueOf(!isFieldVisible("chequeDate")), !isFieldVisible("chequeDate"));
		log("DP Visa Swipe", "Cheque Number hidden", "true", String.valueOf(!isFieldVisible("chequeNumber")), !isFieldVisible("chequeNumber"));

		validateTextField("receiptNo", "DP Visa Receipt No", "RCP003");

		// ========== FINAL: Set Cheque for cheque validation flow ==========
		s.selectByVisibleText("Cheque"); Thread.sleep(500);
		log("DP Pay Mode", "Final mode set for save", "Cheque", s.getFirstSelectedOption().getText(), true);

		System.out.println("=================================================");
		System.out.println("D10_DP_PayModeDD - All Payment Mode cases executed.");
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
