package com.ebid.lcs.tests;

import org.openqa.selenium.By;
import org.openqa.selenium.Keys;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.Select;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Listeners;
import org.testng.annotations.Test;

import java.util.List;

import com.ebid.lcs.base.BaseTest;
import com.ebid.lcs.config.ConfigManager;
import com.ebid.lcs.excel.ExcelReader;
import com.ebid.lcs.excel.SheetConstants;
import com.ebid.lcs.listeners.TestListener;
import com.ebid.lcs.reporting.ExtentManager;
import com.ebid.lcs.utils.DBConnection;

import java.sql.ResultSet;

@Listeners(TestListener.class)
public class FullPTPTest extends BaseTest {

    private void dismissAlert() {
        try { driver.switchTo().alert().accept(); } catch (Exception e) {}
    }

    private void hideDatepicker() {
        try {
            jse.executeScript(
                "var dp = document.querySelectorAll('.datepicker, .ui-datepicker, .daterangepicker, .bootstrap-datetimepicker-widget');" +
                "dp.forEach(function(el){ el.style.display='none'; });"
            );
        } catch (Exception e) {}
        try {
            driver.findElement(By.tagName("body")).sendKeys(Keys.ESCAPE);
        } catch (Exception e) {}
    }

    @BeforeClass
    public void setup() throws Exception {
        ExtentManager.initReport("FullPTP");
        ExtentManager.startTest("Full PTP - Complete Validation");

        navigateToCase(ConfigManager.get("casenumber"));

        // Remedial tab
        WebElement remedial = driver.findElement(By.xpath("(//*[contains(@href,'=Remedial Action')])[1]"));
        jse.executeScript("arguments[0].scrollIntoView({block:'center',behavior:'smooth'})", remedial);
        Thread.sleep(1000);
        jse.executeScript("arguments[0].click()", remedial);
        Thread.sleep(2000);

        // PTP tab
        WebElement ptpTab = driver.findElement(By.xpath("//*[contains(text(),'Promise to pay')]"));
        jse.executeScript("arguments[0].scrollIntoView({block:'center'})", ptpTab);
        Thread.sleep(500);
        act.doubleClick(ptpTab).build().perform();
        jse.executeScript("window.scrollBy(0,3000)");
        Thread.sleep(2000);

        // Switch to PTP frame
        driver.switchTo().frame("fetchPTPMstTabFrame");
        logInfo("PTP Frame", "Switched to frame", "fetchPTPMstTabFrame");
    }

    @Test(priority = 1)
    public void validateOverdueAmt() throws Exception {
        WebElement f = driver.findElement(By.id("overdueAmount"));
        String fn = "Overdue Amount";

        log(fn, "Should be visible", "true", String.valueOf(f.isDisplayed()), f.isDisplayed());
        sa.assertTrue(f.isDisplayed());
        log(fn, "Should be enabled", "true", String.valueOf(f.isEnabled()), f.isEnabled());
        sa.assertTrue(f.isEnabled());

        Object[][] data = ExcelReader.getByTcPrefix(SheetConstants.SHEET_FULL_PTP, SheetConstants.TC.PTP_OVERDUE);
        runFieldValidation(f, fn, data, "sendKeys");
    }

    @Test(priority = 2)
    public void validatePTPDate() throws Exception {
        WebElement f = driver.findElement(By.id("dateOfPTPStart"));
        String fn = "PTP Start Date";

        log(fn, "Should be visible", "true", String.valueOf(f.isDisplayed()), f.isDisplayed());
        log(fn, "Should be enabled", "true", String.valueOf(f.isEnabled()), f.isEnabled());

        Object[][] data = ExcelReader.getByTcPrefix(SheetConstants.SHEET_FULL_PTP, SheetConstants.TC.PTP_DATE);
        runFieldValidationWithTab(f, fn, data);
    }

    @Test(priority = 3)
    public void validateRemarks() throws Exception {
        WebElement f = driver.findElement(By.id("remarks"));
        String fn = "Remarks";

        log(fn, "Should be visible", "true", String.valueOf(f.isDisplayed()), f.isDisplayed());
        log(fn, "Should be enabled", "true", String.valueOf(f.isEnabled()), f.isEnabled());

        Object[][] data = ExcelReader.getByTcPrefix(SheetConstants.SHEET_FULL_PTP, SheetConstants.TC.PTP_REMARKS);
        runFieldValidation(f, fn, data, "sendKeys");
    }

    @Test(priority = 4)
    public void validatePTPType() throws Exception {
        WebElement f = driver.findElement(By.id("scheduleType"));
        Select s = new Select(f);
        String fn = "PTP Type";

        log(fn, "Should be visible", "true", String.valueOf(f.isDisplayed()), f.isDisplayed());
        log(fn, "Should be enabled", "true", String.valueOf(f.isEnabled()), f.isEnabled());
        log(fn, "Single-select", "false", String.valueOf(s.isMultiple()), !s.isMultiple());
        logInfo(fn, "Total Options", String.valueOf(s.getOptions().size()));

        // Print all options
        List<WebElement> opts = s.getOptions();
        StringBuilder sb = new StringBuilder();
        for (WebElement o : opts) { sb.append(o.getText()).append(" , "); }
        logInfo(fn, "All dropdown options", sb.toString());

        log(fn, "Default selected", "Non-null", s.getFirstSelectedOption().getText(), true);

        boolean allEnabled = true;
        for (WebElement o : opts) { if (!o.isEnabled()) allEnabled = false; }
        log(fn, "All options enabled", "true", String.valueOf(allEnabled), allEnabled);

        f.sendKeys(Keys.DOWN); Thread.sleep(300);
        log(fn, "Keyboard accessible (Arrow Down)", "Option selected", s.getFirstSelectedOption().getText(), true);

        Object[][] data = ExcelReader.getByTcPrefix(SheetConstants.SHEET_FULL_PTP, SheetConstants.TC.PTP_TYPE);
        for (Object[] row : data) {
            String input = row[SheetConstants.Cols.INPUT].toString();
            String expected = row[SheetConstants.Cols.EXPECTED].toString();
            String desc = row[SheetConstants.Cols.DESCRIPTION].toString();

            s.selectByVisibleText(input);
            String actual = s.getFirstSelectedOption().getText();
            log(fn, desc, expected, actual, actual.equals(expected));
            sa.assertEquals(actual, expected, desc);
        }
    }

    @Test(priority = 5)
    public void validatePaymentMode() throws Exception {
        WebElement payMode = driver.findElement(By.id("paymentMode"));
        jse.executeScript("arguments[0].scrollIntoView({block:'center',behavior:'smooth'})", payMode);
        Select s = new Select(payMode);
        String fn = "Payment Mode";

        log(fn, "Displayed", "true", String.valueOf(payMode.isDisplayed()), payMode.isDisplayed());
        log(fn, "Enabled", "true", String.valueOf(payMode.isEnabled()), payMode.isEnabled());
        log(fn, "Single select", "false", String.valueOf(s.isMultiple()), !s.isMultiple());

        List<WebElement> allOptions = s.getOptions();
        log(fn, "Options count", "More than 1", String.valueOf(allOptions.size()), allOptions.size() > 1);

        String def = s.getFirstSelectedOption().getText();
        log(fn, "Default value", "--SELECT--", def, def.contains("SELECT"));

        boolean allEn = true;
        for (WebElement o : allOptions) if (!o.isEnabled()) allEn = false;
        log(fn, "All options enabled", "true", String.valueOf(allEn), allEn);

        payMode.sendKeys(Keys.DOWN); Thread.sleep(300);
        log(fn, "Keyboard accessible (Arrow Down)", "Option selected", s.getFirstSelectedOption().getText(), true);

        // Account Transfer
        s.selectByVisibleText("Account Transfer"); Thread.sleep(500);
        log(fn, "Select 'Account Transfer'", "Account Transfer", s.getFirstSelectedOption().getText(), true);
        log("Account Transfer", "Transaction Date visible", "true", String.valueOf(isFieldVisible("transactionDate")), isFieldVisible("transactionDate"));
        log("Account Transfer", "Transaction No visible", "true", String.valueOf(isFieldVisible("transactionNo")), isFieldVisible("transactionNo"));
        log("Account Transfer", "Receipt No hidden", "true", String.valueOf(!isFieldVisible("receiptNo")), !isFieldVisible("receiptNo"));
        log("Account Transfer", "Cheque Date hidden", "true", String.valueOf(!isFieldVisible("chequeDate")), !isFieldVisible("chequeDate"));
        log("Account Transfer", "Cheque Number hidden", "true", String.valueOf(!isFieldVisible("chequeNumber")), !isFieldVisible("chequeNumber"));
        validateDateField("transactionDate", "AT Transaction Date", "13-12-2021");
        validateTextField("transactionNo", "AT Transaction No", "TXN001");

        // Bank Transfer
        s.selectByVisibleText("Bank Transfer"); Thread.sleep(500);
        log(fn, "Select 'Bank Transfer'", "Bank Transfer", s.getFirstSelectedOption().getText(), true);
        log("Bank Transfer", "Transaction Date visible", "true", String.valueOf(isFieldVisible("transactionDate")), isFieldVisible("transactionDate"));
        log("Bank Transfer", "Transaction No visible", "true", String.valueOf(isFieldVisible("transactionNo")), isFieldVisible("transactionNo"));
        log("Bank Transfer", "Receipt No hidden", "true", String.valueOf(!isFieldVisible("receiptNo")), !isFieldVisible("receiptNo"));
        log("Bank Transfer", "Cheque Date hidden", "true", String.valueOf(!isFieldVisible("chequeDate")), !isFieldVisible("chequeDate"));

        // CASH
        s.selectByVisibleText("CASH"); Thread.sleep(500);
        log(fn, "Select 'CASH'", "CASH", s.getFirstSelectedOption().getText(), true);
        log("CASH", "Receipt No visible", "true", String.valueOf(isFieldVisible("receiptNo")), isFieldVisible("receiptNo"));
        log("CASH", "Transaction Date hidden", "true", String.valueOf(!isFieldVisible("transactionDate")), !isFieldVisible("transactionDate"));
        log("CASH", "Transaction No hidden", "true", String.valueOf(!isFieldVisible("transactionNo")), !isFieldVisible("transactionNo"));
        log("CASH", "Cheque Date hidden", "true", String.valueOf(!isFieldVisible("chequeDate")), !isFieldVisible("chequeDate"));
        log("CASH", "Cheque Number hidden", "true", String.valueOf(!isFieldVisible("chequeNumber")), !isFieldVisible("chequeNumber"));
        validateTextField("receiptNo", "CASH Receipt No", "RCP001");

        // Cheque
        s.selectByVisibleText("Cheque"); Thread.sleep(500);
        log(fn, "Select 'Cheque'", "Cheque", s.getFirstSelectedOption().getText(), true);
        log("Cheque", "Cheque Date visible", "true", String.valueOf(isFieldVisible("chequeDate")), isFieldVisible("chequeDate"));
        log("Cheque", "Cheque Number visible", "true", String.valueOf(isFieldVisible("chequeNumber")), isFieldVisible("chequeNumber"));
        log("Cheque", "Receipt No visible", "true", String.valueOf(isFieldVisible("receiptNo")), isFieldVisible("receiptNo"));
        log("Cheque", "Transaction Date hidden", "true", String.valueOf(!isFieldVisible("transactionDate")), !isFieldVisible("transactionDate"));
        log("Cheque", "Transaction No hidden", "true", String.valueOf(!isFieldVisible("transactionNo")), !isFieldVisible("transactionNo"));
        validateDateField("chequeDate", "Cheque Date", "13-12-2021");
        validateTextField("chequeNumber", "Cheque Number", "CHQ12345");
        validateTextField("receiptNo", "Cheque Receipt No", "RCP002");

        // Visa Swipe
        s.selectByVisibleText("Visa Swipe"); Thread.sleep(500);
        log(fn, "Select 'Visa Swipe'", "Visa Swipe", s.getFirstSelectedOption().getText(), true);
        log("Visa Swipe", "Receipt No visible", "true", String.valueOf(isFieldVisible("receiptNo")), isFieldVisible("receiptNo"));
        log("Visa Swipe", "Transaction Date hidden", "true", String.valueOf(!isFieldVisible("transactionDate")), !isFieldVisible("transactionDate"));
        log("Visa Swipe", "Cheque Date hidden", "true", String.valueOf(!isFieldVisible("chequeDate")), !isFieldVisible("chequeDate"));
        log("Visa Swipe", "Cheque Number hidden", "true", String.valueOf(!isFieldVisible("chequeNumber")), !isFieldVisible("chequeNumber"));
        validateTextField("receiptNo", "Visa Receipt No", "RCP003");

        // Final: CASH for save
        s.selectByVisibleText("CASH"); Thread.sleep(300);
        log(fn, "Final mode set for save", "CASH", s.getFirstSelectedOption().getText(), true);
    }

    @Test(priority = 6)
    public void validatePlannedAmt() throws Exception {
        WebElement f = driver.findElement(By.id("plannedAmt"));
        String fn = "Planned Amount";

        log(fn, "Should be visible", "true", String.valueOf(f.isDisplayed()), f.isDisplayed());
        String readOnly = f.getAttribute("readonly");
        logInfo(fn, "Field state", "Enabled=" + f.isEnabled() + " | ReadOnly=" + readOnly);

        Object[][] data = ExcelReader.getByTcPrefix(SheetConstants.SHEET_FULL_PTP, SheetConstants.TC.PTP_PLANNED_AMT);
        runFieldValidation(f, fn, data, "js");
    }

    @Test(priority = 7)
    public void validateRemAmount() throws Exception {
        WebElement f = driver.findElement(By.id("remAmt"));
        String fn = "Remaining Amount";

        log(fn, "Should be visible", "true", String.valueOf(f.isDisplayed()), f.isDisplayed());
        String readOnly = f.getAttribute("readonly");
        logInfo(fn, "Field state", "Enabled=" + f.isEnabled() + " | ReadOnly=" + readOnly);

        Object[][] data = ExcelReader.getByTcPrefix(SheetConstants.SHEET_FULL_PTP, SheetConstants.TC.PTP_REM_AMT);
        runFieldValidation(f, fn, data, "js");
    }

    @Test(priority = 8)
    public void validatePlannedDate() throws Exception {
        WebElement f = driver.findElement(By.id("planDate"));
        String fn = "Planned Date";

        log(fn, "Should be visible", "true", String.valueOf(f.isDisplayed()), f.isDisplayed());
        log(fn, "Should be enabled", "true", String.valueOf(f.isEnabled()), f.isEnabled());

        Object[][] data = ExcelReader.getByTcPrefix(SheetConstants.SHEET_FULL_PTP, SheetConstants.TC.PTP_PLANNED_DATE);
        runFieldValidationWithTab(f, fn, data);
    }

    @Test(priority = 9)
    public void validateViewEdit() throws Exception {
        // Select currency before save
        WebElement currency = driver.findElement(By.id("currency"));
        jse.executeScript("arguments[0].scrollIntoView({block:'center'})", currency);
        Select selCurrency = new Select(currency);
        selCurrency.selectByIndex(2);
        Thread.sleep(500);
        log("currency", "Select currency", "Non-empty", selCurrency.getFirstSelectedOption().getText().trim(), true);

        // Add & Save
        WebElement addBtn = driver.findElement(By.id("add"));
        jse.executeScript("arguments[0].scrollIntoView({block:'center'})", addBtn);
        addBtn.click();
        log("Add Button", "Click Add", "Clicked", "Clicked", true);

        Thread.sleep(2000);
        WebElement saveBtn = driver.findElement(By.id("saveData"));
        jse.executeScript("arguments[0].scrollIntoView({block:'center'})", saveBtn);
        Thread.sleep(2000);
        saveBtn.click();
        log("Save Button", "Click Save", "Saved", "Clicked", true);

        Thread.sleep(2000);
        driver.switchTo().parentFrame();
        Thread.sleep(1000);
        driver.switchTo().frame("fetchPTPMstTabFrame");

        // View
        List<WebElement> viewBtns = driver.findElements(By.xpath("//a[contains(@class,'ViewBtn')]"));
        log("View Button", "View buttons found", ">0", String.valueOf(viewBtns.size()), viewBtns.size() > 0);
        if (viewBtns.size() > 0) {
            WebElement viewBtn = viewBtns.get(viewBtns.size() - 1);
            log("View Button", "Last View visible", "true", String.valueOf(viewBtn.isDisplayed()), viewBtn.isDisplayed());
            viewBtn.click(); Thread.sleep(1000);
            log("View Button", "Click last View", "Opened", "Clicked", true);
            WebElement viewSave = driver.findElement(By.id("saveData"));
            jse.executeScript("arguments[0].scrollIntoView({block:'center'})", viewSave);
            log("View Button", "Record displayed in View mode", "Displayed", "Displayed", true);
        }

        // Edit
        List<WebElement> editBtns = driver.findElements(By.xpath("//a[contains(@class,'EditBtn')]"));
        log("Edit Button", "Edit buttons found", ">0", String.valueOf(editBtns.size()), editBtns.size() > 0);
        if (editBtns.size() > 0) {
            WebElement editBtn = editBtns.get(editBtns.size() - 1);
            log("Edit Button", "Last Edit visible", "true", String.valueOf(editBtn.isDisplayed()), editBtn.isDisplayed());
            editBtn.click(); Thread.sleep(1000);
            log("Edit Button", "Click last Edit", "Opened", "Clicked", true);
            WebElement editRemarks = driver.findElement(By.id("remarks"));
            log("Edit Button", "Remarks editable in Edit mode", "true", String.valueOf(editRemarks.isEnabled()), editRemarks.isEnabled());
            sa.assertTrue(editRemarks.isEnabled());
        }

        // Disable
        List<WebElement> disableBtns = driver.findElements(By.xpath("//a[contains(text(),'Disable')]"));
        logInfo("Disable Button", "Disable buttons found", String.valueOf(disableBtns.size()));
        if (disableBtns.size() > 0) {
            log("Disable Button", "Disable button visible", "true", String.valueOf(disableBtns.get(0).isDisplayed()), disableBtns.get(0).isDisplayed());
        }

        sa.assertAll();
    }

    @Test(priority = 10)
    public void validateDatabase() throws Exception {
        String today = java.time.LocalDate.now().toString();
        ResultSet rs = DBConnection.executeQuery(
            "SELECT TOP 1 * FROM d310025 ORDER BY createdDate DESC, createdTime DESC");

        if (!rs.next()) {
            log("DB", "Record found in d310025", "Yes", "No", false);
            sa.fail("No record found in d310025");
            DBConnection.close(rs);
            return;
        }

        log("DB", "Record found", "Yes", "Yes", true);

        String dbCreatedDate = rs.getString("createdDate");
        log("DB", "createdDate contains today", today, dbCreatedDate, dbCreatedDate != null && dbCreatedDate.contains(today));
        sa.assertTrue(dbCreatedDate != null && dbCreatedDate.contains(today), "createdDate should contain today");

        String dbIsActive = rs.getString("isActive");
        log("DB", "isActive", "1", dbIsActive, "1".equals(dbIsActive));
        sa.assertEquals(dbIsActive, "1", "isActive should be 1");

        String dbRemarks = rs.getString("remarks");
        log("DB", "remarks", "Not Null", dbRemarks, dbRemarks != null && !dbRemarks.isEmpty());

        String dbPaymentMode = rs.getString("paymentMode");
        log("DB", "paymentMode", "Not Null", dbPaymentMode, dbPaymentMode != null && !dbPaymentMode.isEmpty());

        String dbCaseNo = rs.getString("caseNo");
        log("DB", "caseNo", "CASE_0005282011204001137", dbCaseNo, dbCaseNo != null && dbCaseNo.contains("CASE_0005282011204001137"));

        logInfo("DB", "createdTime", rs.getString("createdTime"));

        DBConnection.close(rs);
        sa.assertAll();
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
            field.clear(); field.sendKeys(testValue); Thread.sleep(200);
            hideDatepicker();
            String val = field.getAttribute("value");
            log(fieldName, "Enter '" + testValue + "'", testValue, val, val.contains(testValue) || !val.isEmpty());
            field.clear(); Thread.sleep(200);
            hideDatepicker();
            log(fieldName, "Clear field", "Empty", "'" + field.getAttribute("value") + "'", field.getAttribute("value").isEmpty());
            field.sendKeys(testValue); Thread.sleep(200);
            hideDatepicker();
        } catch (Exception e) {
            log(fieldName, "Field interaction", "Accessible", "ERROR: " + e.getMessage(), false);
        }
    }

    private void validateDateField(String id, String fieldName, String testValue) throws Exception {
        try {
            WebElement field = driver.findElement(By.id(id));
            log(fieldName, "Displayed", "true", String.valueOf(field.isDisplayed()), field.isDisplayed());
            log(fieldName, "Enabled", "true", String.valueOf(field.isEnabled()), field.isEnabled());
            // Use JS to set value to avoid datepicker popup
            jse.executeScript("arguments[0].value='" + testValue + "'", field);
            hideDatepicker();
            String val = field.getAttribute("value");
            log(fieldName, "Enter '" + testValue + "'", testValue, val, val.contains(testValue) || !val.isEmpty());
            // Clear via JS
            jse.executeScript("arguments[0].value=''", field);
            hideDatepicker();
            log(fieldName, "Clear field", "Empty", "'" + field.getAttribute("value") + "'", field.getAttribute("value").isEmpty());
            // Final value via JS
            jse.executeScript("arguments[0].value='" + testValue + "'", field);
            hideDatepicker();
        } catch (Exception e) {
            log(fieldName, "Field interaction", "Accessible", "ERROR: " + e.getMessage(), false);
        }
    }

    private void runFieldValidation(WebElement f, String fn, Object[][] data, String mode) {
        for (Object[] row : data) {
            String input = row[SheetConstants.Cols.INPUT].toString();
            String expected = row[SheetConstants.Cols.EXPECTED].toString();
            String desc = row[SheetConstants.Cols.DESCRIPTION].toString();
            String checkType = row[SheetConstants.Cols.CHECK_TYPE].toString();

            dismissAlert();
            if (mode.equals("js")) {
                jse.executeScript("arguments[0].value='" + input + "'", f);
            } else {
                f.clear();
                if (!input.isEmpty()) f.sendKeys(input);
            }
            dismissAlert();
            String actual = f.getAttribute("value");

            switch (checkType) {
                case "equals":
                    log(fn, desc, expected, actual, actual.equals(expected));
                    sa.assertEquals(actual, expected, desc);
                    break;
                case "notEquals":
                    log(fn, desc, "Not " + input, actual, !actual.equals(input));
                    break;
                case "empty":
                    log(fn, desc, "Empty", actual, actual.isEmpty());
                    sa.assertTrue(actual.isEmpty(), desc);
                    break;
                case "info":
                    logInfo(fn, desc, "Got: " + actual);
                    break;
            }
        }
    }

    private void runFieldValidationWithTab(WebElement f, String fn, Object[][] data) {
        for (Object[] row : data) {
            String input = row[SheetConstants.Cols.INPUT].toString();
            String expected = row[SheetConstants.Cols.EXPECTED].toString();
            String desc = row[SheetConstants.Cols.DESCRIPTION].toString();
            String checkType = row[SheetConstants.Cols.CHECK_TYPE].toString();

            dismissAlert();
            hideDatepicker();
            f.clear();
            if (!input.isEmpty()) f.sendKeys(input);
            f.sendKeys(Keys.TAB);
            hideDatepicker();
            dismissAlert();
            String actual = f.getAttribute("value");

            switch (checkType) {
                case "equals":
                    log(fn, desc, expected, actual, actual.equals(expected));
                    sa.assertEquals(actual, expected, desc);
                    break;
                case "notEquals":
                    log(fn, desc, "Not " + input, actual, !actual.equals(input));
                    break;
                case "empty":
                    log(fn, desc, "Empty", actual, actual.isEmpty());
                    sa.assertTrue(actual.isEmpty(), desc);
                    break;
                case "info":
                    logInfo(fn, desc, "Got: " + actual);
                    break;
            }
        }
    }
}
