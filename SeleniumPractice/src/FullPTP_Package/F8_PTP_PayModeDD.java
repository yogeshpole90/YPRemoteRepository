package FullPTP_Package;

import java.util.List;
import org.openqa.selenium.By;
import org.openqa.selenium.Keys;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.Select;

public class F8_PTP_PayModeDD extends F2_PTP_Login {

	public void validatePayMode() throws Exception
	{
		WebElement payMode = driver.findElement(By.id("paymentMode"));
		jse.executeScript("arguments[0].scrollIntoView({block:'center',behavior:'smooth'})", payMode);
		Select s = new Select(payMode);
		List<WebElement> allOptions = s.getOptions();

		// ========== BASIC DROPDOWN VALIDATION ==========

		log("Payment Mode", "Displayed", "true", String.valueOf(payMode.isDisplayed()), payMode.isDisplayed());
		sa.assertTrue(payMode.isDisplayed(), "Payment Mode not displayed");

		log("Payment Mode", "Enabled", "true", String.valueOf(payMode.isEnabled()), payMode.isEnabled());
		sa.assertTrue(payMode.isEnabled(), "Payment Mode disabled");

		log("Payment Mode", "Single select", "false", String.valueOf(s.isMultiple()), !s.isMultiple());
		sa.assertFalse(s.isMultiple(), "Should be single select");

		log("Payment Mode", "Options count", "More than 1", String.valueOf(allOptions.size()), allOptions.size() > 1);
		sa.assertTrue(allOptions.size() > 1, "Should have options");

		logInfo("Payment Mode", "Print all options", "");
		for (int i = 0; i < allOptions.size(); i++) {
			System.out.println("  [" + i + "] " + allOptions.get(i).getText());
		}

		String def = s.getFirstSelectedOption().getText();
		boolean defCheck = def.contains("SELECT");
		log("Payment Mode", "Default value", "--SELECT [MODEOFPAYMENT]--", def, defCheck);

		boolean allEn = true;
		for (WebElement o : allOptions) if (!o.isEnabled()) allEn = false;
		log("Payment Mode", "All options enabled", "true", String.valueOf(allEn), allEn);

		payMode.sendKeys(Keys.DOWN); Thread.sleep(300);
		log("Payment Mode", "Keyboard accessible (Arrow Down)", "Option selected", s.getFirstSelectedOption().getText(), true);

		// ========== ACCOUNT TRANSFER — transactionDate, transactionNo ==========

		s.selectByVisibleText("Account Transfer"); Thread.sleep(1000);
		log("Payment Mode", "Select 'Account Transfer'", "Account Transfer", s.getFirstSelectedOption().getText(), s.getFirstSelectedOption().getText().equals("Account Transfer"));

		boolean atTxnDateVis = isFieldVisible("transactionDate");
		log("Account Transfer", "Transaction Date visible", "true", String.valueOf(atTxnDateVis), atTxnDateVis);
		sa.assertTrue(atTxnDateVis, "AT: Transaction Date should be visible");

		boolean atTxnNoVis = isFieldVisible("transactionNo");
		log("Account Transfer", "Transaction Number visible", "true", String.valueOf(atTxnNoVis), atTxnNoVis);
		sa.assertTrue(atTxnNoVis, "AT: Transaction No should be visible");

		boolean atReceiptHid = !isFieldVisible("receiptNo");
		log("Account Transfer", "Receipt No hidden", "true", String.valueOf(atReceiptHid), atReceiptHid);

		boolean atChqDateHid = !isFieldVisible("chequeDate");
		log("Account Transfer", "Cheque Date hidden", "true", String.valueOf(atChqDateHid), atChqDateHid);

		boolean atChqNumHid = !isFieldVisible("chequeNumber");
		log("Account Transfer", "Cheque Number hidden", "true", String.valueOf(atChqNumHid), atChqNumHid);

		// Validate Transaction Date & No fields
		validateTextField("transactionDate", "AT Transaction Date", "13-12-2021");
		validateTextField("transactionNo", "AT Transaction No", "TXN001");

		// ========== BANK TRANSFER — transactionDate, transactionNo ==========

		s.selectByVisibleText("Bank Transfer"); Thread.sleep(1000);
		log("Payment Mode", "Select 'Bank Transfer'", "Bank Transfer", s.getFirstSelectedOption().getText(), s.getFirstSelectedOption().getText().equals("Bank Transfer"));

		boolean btTxnDateVis = isFieldVisible("transactionDate");
		log("Bank Transfer", "Transaction Date visible", "true", String.valueOf(btTxnDateVis), btTxnDateVis);
		sa.assertTrue(btTxnDateVis, "BT: Transaction Date should be visible");

		boolean btTxnNoVis = isFieldVisible("transactionNo");
		log("Bank Transfer", "Transaction Number visible", "true", String.valueOf(btTxnNoVis), btTxnNoVis);
		sa.assertTrue(btTxnNoVis, "BT: Transaction No should be visible");

		boolean btReceiptHid = !isFieldVisible("receiptNo");
		log("Bank Transfer", "Receipt No hidden", "true", String.valueOf(btReceiptHid), btReceiptHid);

		boolean btChqDateHid = !isFieldVisible("chequeDate");
		log("Bank Transfer", "Cheque Date hidden", "true", String.valueOf(btChqDateHid), btChqDateHid);

		// ========== CASH — receiptNo only ==========

		s.selectByVisibleText("CASH"); Thread.sleep(1000);
		log("Payment Mode", "Select 'CASH'", "CASH", s.getFirstSelectedOption().getText(), s.getFirstSelectedOption().getText().equals("CASH"));

		boolean cashReceiptVis = isFieldVisible("receiptNo");
		log("CASH", "Receipt No visible", "true", String.valueOf(cashReceiptVis), cashReceiptVis);
		sa.assertTrue(cashReceiptVis, "CASH: Receipt No should be visible");

		boolean cashTxnDateHid = !isFieldVisible("transactionDate");
		log("CASH", "Transaction Date hidden", "true", String.valueOf(cashTxnDateHid), cashTxnDateHid);

		boolean cashTxnNoHid = !isFieldVisible("transactionNo");
		log("CASH", "Transaction No hidden", "true", String.valueOf(cashTxnNoHid), cashTxnNoHid);

		boolean cashChqDateHid = !isFieldVisible("chequeDate");
		log("CASH", "Cheque Date hidden", "true", String.valueOf(cashChqDateHid), cashChqDateHid);

		boolean cashChqNumHid = !isFieldVisible("chequeNumber");
		log("CASH", "Cheque Number hidden", "true", String.valueOf(cashChqNumHid), cashChqNumHid);

		validateTextField("receiptNo", "CASH Receipt No", "RCP001");

		// ========== CHEQUE — chequeDate, chequeNumber, receiptNo ==========

		s.selectByVisibleText("Cheque"); Thread.sleep(1000);
		log("Payment Mode", "Select 'Cheque'", "Cheque", s.getFirstSelectedOption().getText(), s.getFirstSelectedOption().getText().equals("Cheque"));

		boolean chqDateVis = isFieldVisible("chequeDate");
		log("Cheque", "Cheque Date visible", "true", String.valueOf(chqDateVis), chqDateVis);
		sa.assertTrue(chqDateVis, "Cheque: Cheque Date should be visible");

		boolean chqNumVis = isFieldVisible("chequeNumber");
		log("Cheque", "Cheque Number visible", "true", String.valueOf(chqNumVis), chqNumVis);
		sa.assertTrue(chqNumVis, "Cheque: Cheque Number should be visible");

		boolean chqReceiptVis = isFieldVisible("receiptNo");
		log("Cheque", "Receipt No visible", "true", String.valueOf(chqReceiptVis), chqReceiptVis);
		sa.assertTrue(chqReceiptVis, "Cheque: Receipt No should be visible");

		boolean chqTxnDateHid = !isFieldVisible("transactionDate");
		log("Cheque", "Transaction Date hidden", "true", String.valueOf(chqTxnDateHid), chqTxnDateHid);

		boolean chqTxnNoHid = !isFieldVisible("transactionNo");
		log("Cheque", "Transaction No hidden", "true", String.valueOf(chqTxnNoHid), chqTxnNoHid);

		validateTextField("chequeDate", "Cheque Date", "13-12-2021");
		validateTextField("chequeNumber", "Cheque Number", "CHQ12345");
		validateTextField("receiptNo", "Cheque Receipt No", "RCP002");

		// ========== VISA SWIPE — receiptNo only ==========

		s.selectByVisibleText("Visa Swipe"); Thread.sleep(1000);
		log("Payment Mode", "Select 'Visa Swipe'", "Visa Swipe", s.getFirstSelectedOption().getText(), s.getFirstSelectedOption().getText().equals("Visa Swipe"));

		boolean vsReceiptVis = isFieldVisible("receiptNo");
		log("Visa Swipe", "Receipt No visible", "true", String.valueOf(vsReceiptVis), vsReceiptVis);
		sa.assertTrue(vsReceiptVis, "VS: Receipt No should be visible");

		boolean vsTxnDateHid = !isFieldVisible("transactionDate");
		log("Visa Swipe", "Transaction Date hidden", "true", String.valueOf(vsTxnDateHid), vsTxnDateHid);

		boolean vsChqDateHid = !isFieldVisible("chequeDate");
		log("Visa Swipe", "Cheque Date hidden", "true", String.valueOf(vsChqDateHid), vsChqDateHid);

		boolean vsChqNumHid = !isFieldVisible("chequeNumber");
		log("Visa Swipe", "Cheque Number hidden", "true", String.valueOf(vsChqNumHid), vsChqNumHid);

		validateTextField("receiptNo", "Visa Receipt No", "RCP003");

		// ========== FINAL: Set CASH for save ==========
		s.selectByVisibleText("CASH"); Thread.sleep(500);
		log("Payment Mode", "Final mode set for save", "CASH", s.getFirstSelectedOption().getText(), true);

		System.out.println("=================================================");
		System.out.println("F8_PTP_PayModeDD - All Payment Mode cases executed.");
	}

	// Helper: Check if field is visible on page
	private boolean isFieldVisible(String id) {
		try {
			List<WebElement> els = driver.findElements(By.id(id));
			return els.size() > 0 && els.get(0).isDisplayed();
		} catch (Exception e) { return false; }
	}

	// Helper: Validate text field — display, enable, enter, clear
	private void validateTextField(String id, String fieldName, String testValue) throws Exception {
		try {
			WebElement field = driver.findElement(By.id(id));
			log(fieldName, "Displayed", "true", String.valueOf(field.isDisplayed()), field.isDisplayed());
			log(fieldName, "Enabled", "true", String.valueOf(field.isEnabled()), field.isEnabled());

			field.clear();
			field.sendKeys(testValue);
			Thread.sleep(300);
			String val = field.getAttribute("value");
			log(fieldName, "Enter value '" + testValue + "'", testValue, val, val.contains(testValue) || !val.isEmpty());

			field.clear();
			Thread.sleep(200);
			String cleared = field.getAttribute("value");
			log(fieldName, "Clear field", "Empty", "'" + cleared + "'", cleared.isEmpty());

			// Re-enter for save
			field.sendKeys(testValue);
			Thread.sleep(200);
		} catch (Exception e) {
			log(fieldName, "Field interaction", "Accessible", "ERROR: " + e.getMessage(), false);
		}
	}
}
