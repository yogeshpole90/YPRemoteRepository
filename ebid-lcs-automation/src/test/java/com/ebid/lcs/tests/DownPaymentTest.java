package com.ebid.lcs.tests;

import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.Keys;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.Select;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Listeners;
import org.testng.annotations.Test;

import java.time.Duration;
import java.util.List;

import com.ebid.lcs.base.BaseTest;
import com.ebid.lcs.config.ConfigManager;
import com.ebid.lcs.driver.DriverManager;
import com.ebid.lcs.excel.ExcelReader;
import com.ebid.lcs.excel.SheetConstants;
import com.ebid.lcs.listeners.TestListener;
import com.ebid.lcs.reporting.ExtentManager;
import com.ebid.lcs.utils.DBConnection;

import java.sql.ResultSet;

@Listeners(TestListener.class)
public class DownPaymentTest extends BaseTest {

    private void dismissAlert() {
        try {
            driver.switchTo().alert().accept();
        } catch (Exception e) {
        }
    }

    private void hideDatepicker() {
        try {
            jse.executeScript(
                    "var dp = document.querySelectorAll('.datepicker, .ui-datepicker, .daterangepicker, .bootstrap-datetimepicker-widget');"
                            +
                            "dp.forEach(function(el){ el.style.display='none'; });");
        } catch (Exception e) {
        }
        try {
            driver.findElement(By.tagName("body")).sendKeys(Keys.ESCAPE);
        } catch (Exception e) {
        }
    }

    @BeforeClass
    public void setup() throws Exception {
        ExtentManager.initReport("Downpayment");
        ExtentManager.startTest("Downpayment - Full Validation");

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
        logInfo("Frame", "Switched to", "fetchPTPMstTabFrame");
    }

    @Test(priority = 1)
    public void validateCurrency() throws Exception {
        WebElement currency = driver.findElement(By.id("currency"));
        jse.executeScript("arguments[0].scrollIntoView({block:'center'})", currency);
        Select selCurrency = new Select(currency);

        log("currency", "Displayed", "true", String.valueOf(currency.isDisplayed()), currency.isDisplayed());
        log("currency", "Enabled", "true", String.valueOf(currency.isEnabled()), currency.isEnabled());

        selCurrency.selectByIndex(2);
        Thread.sleep(500);
        String selectedCurrency = selCurrency.getFirstSelectedOption().getText().trim();
        log("currency", "Select currency", "Non-empty", selectedCurrency,
                !selectedCurrency.isEmpty() && !selectedCurrency.contains("SELECT"));
    }

    @Test(priority = 2)
    public void validateOverdueAmt() throws Exception {
        WebElement f = driver.findElement(By.id("overdueAmount"));
        String fn = "Overdue Amount";

        log(fn, "Should be visible", "true", String.valueOf(f.isDisplayed()), f.isDisplayed());
        log(fn, "Should be enabled", "true", String.valueOf(f.isEnabled()), f.isEnabled());

        Object[][] data = ExcelReader.getByTcPrefix(SheetConstants.SHEET_DOWNPAYMENT, "OD_");
        runFieldValidation(f, fn, data, "sendKeys");
    }

    @Test(priority = 3)
    public void validatePTPDate() throws Exception {
        WebElement f = driver.findElement(By.id("dateOfPTPStart"));
        String fn = "PTP Start Date";

        log(fn, "Should be visible", "true", String.valueOf(f.isDisplayed()), f.isDisplayed());
        log(fn, "Should be enabled", "true", String.valueOf(f.isEnabled()), f.isEnabled());

        Object[][] data = ExcelReader.getByTcPrefix(SheetConstants.SHEET_DOWNPAYMENT, "DT_");
        runFieldValidationWithTab(f, fn, data);
    }

    @Test(priority = 4)
    public void validateRemarks() throws Exception {
        WebElement f = driver.findElement(By.id("remarks"));
        String fn = "Remarks";

        log(fn, "Should be visible", "true", String.valueOf(f.isDisplayed()), f.isDisplayed());
        log(fn, "Should be enabled", "true", String.valueOf(f.isEnabled()), f.isEnabled());

        Object[][] data = ExcelReader.getByTcPrefix(SheetConstants.SHEET_DOWNPAYMENT, "RM_");
        runFieldValidation(f, fn, data, "sendKeys");
    }

    @Test(priority = 5)
    public void validateScheduleType() throws Exception {
        WebElement f = driver.findElement(By.id("scheduleType"));
        Select s = new Select(f);
        String fn = "Schedule Type";

        log(fn, "Should be visible", "true", String.valueOf(f.isDisplayed()), f.isDisplayed());
        log(fn, "Should be enabled", "true", String.valueOf(f.isEnabled()), f.isEnabled());
        log(fn, "Single-select", "false", String.valueOf(s.isMultiple()), !s.isMultiple());
        logInfo(fn, "Total Options", String.valueOf(s.getOptions().size()));

        List<WebElement> opts = s.getOptions();
        StringBuilder sb = new StringBuilder();
        for (WebElement o : opts) {
            sb.append(o.getText()).append(" , ");
        }
        logInfo(fn, "All dropdown options", sb.toString());

        f.sendKeys(Keys.DOWN);
        Thread.sleep(300);
        log(fn, "Keyboard accessible (Arrow Down)", "Option selected", s.getFirstSelectedOption().getText(), true);

        // Select Downpayment + Schedule PTP
        s.selectByVisibleText("Downpayment + Schedule PTP");
        Thread.sleep(2000);
        String actual = s.getFirstSelectedOption().getText();
        log(fn, "Select 'Downpayment + Schedule PTP'", "Downpayment + Schedule PTP", actual,
                actual.equals("Downpayment + Schedule PTP"));
        sa.assertEquals(actual, "Downpayment + Schedule PTP");
    }

    @Test(priority = 6)
    public void validateDPPlanDate() throws Exception {
        WebElement f = driver.findElement(By.id("planDate"));
        String fn = "DP Planned Date";

        log(fn, "Should be visible", "true", String.valueOf(f.isDisplayed()), f.isDisplayed());
        log(fn, "Should be enabled", "true", String.valueOf(f.isEnabled()), f.isEnabled());

        Object[][] data = ExcelReader.getByTcPrefix(SheetConstants.SHEET_DOWNPAYMENT, "PD_");
        runFieldValidationWithTab(f, fn, data);
    }

    @Test(priority = 7)
    public void validateDPPlanAmt() throws Exception {
        WebElement f = driver.findElement(By.id("plannedAmt"));
        String fn = "DP Planned Amount";

        log(fn, "Should be visible", "true", String.valueOf(f.isDisplayed()), f.isDisplayed());
        logInfo(fn, "Field state", "Enabled=" + f.isEnabled() + " | ReadOnly=" + f.getAttribute("readonly"));

        Object[][] data = ExcelReader.getByTcPrefix(SheetConstants.SHEET_DOWNPAYMENT, "PA_");
        runFieldValidation(f, fn, data, "js");
    }

    @Test(priority = 8)
    public void validatePaymentMode() throws Exception {
        WebElement payMode = driver.findElement(By.id("paymentMode"));
        jse.executeScript("arguments[0].scrollIntoView({block:'center',behavior:'smooth'})", payMode);
        Select s = new Select(payMode);
        String fn = "DP Payment Mode";

        log(fn, "Displayed", "true", String.valueOf(payMode.isDisplayed()), payMode.isDisplayed());
        log(fn, "Enabled", "true", String.valueOf(payMode.isEnabled()), payMode.isEnabled());
        log(fn, "Single select", "false", String.valueOf(s.isMultiple()), !s.isMultiple());

        List<WebElement> allOptions = s.getOptions();
        log(fn, "Options count", "More than 1", String.valueOf(allOptions.size()), allOptions.size() > 1);
        StringBuilder sb = new StringBuilder();
        for (WebElement o : allOptions) {
            sb.append(o.getText()).append(" , ");
        }
        logInfo(fn, "All dropdown options", sb.toString());

        s.selectByVisibleText("Account Transfer");
        Thread.sleep(500);
        log(fn, "Select 'Account Transfer'", "Account Transfer", s.getFirstSelectedOption().getText(),
                s.getFirstSelectedOption().getText().equals("Account Transfer"));
        log("DP AT", "Transaction Date visible", "true", String.valueOf(isFieldVisible("transactionDate")),
                isFieldVisible("transactionDate"));
        log("DP AT", "Transaction No visible", "true", String.valueOf(isFieldVisible("transactionNo")),
                isFieldVisible("transactionNo"));
        validateDateField("transactionDate", "DP AT Transaction Date", "13-12-2021");
        validateTextField("transactionNo", "DP AT Transaction No", "TXN001");

        s.selectByVisibleText("CASH");
        Thread.sleep(500);
        log(fn, "Select 'CASH'", "CASH", s.getFirstSelectedOption().getText(),
                s.getFirstSelectedOption().getText().equals("CASH"));
        log("DP CASH", "Receipt No visible", "true", String.valueOf(isFieldVisible("receiptNo")),
                isFieldVisible("receiptNo"));
        validateTextField("receiptNo", "DP CASH Receipt No", "RCP001");

        s.selectByVisibleText("Cheque");
        Thread.sleep(500);
        log(fn, "Select 'Cheque'", "Cheque", s.getFirstSelectedOption().getText(),
                s.getFirstSelectedOption().getText().equals("Cheque"));
        log("DP Cheque", "Cheque Date visible", "true", String.valueOf(isFieldVisible("chequeDate")),
                isFieldVisible("chequeDate"));
        log("DP Cheque", "Cheque Number visible", "true", String.valueOf(isFieldVisible("chequeNumber")),
                isFieldVisible("chequeNumber"));
        validateDateField("chequeDate", "DP Cheque Date", "13-12-2021");
        validateTextField("chequeNumber", "DP Cheque Number", "CHQ12345");
        validateTextField("receiptNo", "DP Cheque Receipt No", "RCP002");

        s.selectByVisibleText("Cheque");
        Thread.sleep(300);
        log(fn, "Final mode set for save", "Cheque", s.getFirstSelectedOption().getText(),
                s.getFirstSelectedOption().getText().equals("Cheque"));
    }

    @Test(priority = 9)
    public void validateFillAndSave() throws Exception {
        // 1. Set valid Currency
        WebElement currency = driver.findElement(By.id("currency"));
        jse.executeScript("arguments[0].scrollIntoView({block:'center'})", currency);
        Select selCurrency = new Select(currency);
        selCurrency.selectByVisibleText("EURO");
        Thread.sleep(500);
        log("Fill", "Set currency", "EURO", selCurrency.getFirstSelectedOption().getText(), 
                selCurrency.getFirstSelectedOption().getText().equals("EURO"));

        // 2. Set valid Overdue Amount (only if blank or 0)
        WebElement overdueAmt = driver.findElement(By.id("overdueAmount"));
        String overdueVal = overdueAmt.getAttribute("value").trim();
        if (overdueVal.isEmpty() || overdueVal.equals("0") || overdueVal.equals("0.0")) {
            overdueAmt.clear();
            overdueAmt.sendKeys("1500000");
            overdueAmt.sendKeys(Keys.TAB);
            Thread.sleep(300);
            dismissAlert();
            log("Fill", "Set overdueAmount", "1500000", overdueAmt.getAttribute("value"), true);
        } else {
            logInfo("Fill", "overdueAmount already has value", overdueVal);
        }

        // 3. Set valid PTP Start Date
        WebElement ptpDate = driver.findElement(By.id("dateOfPTPStart"));
        ptpDate.clear();
        ptpDate.sendKeys("01-07-2025");
        ptpDate.sendKeys(Keys.TAB);
        hideDatepicker();
        Thread.sleep(300);
        log("Fill", "Set PTP Start Date", "01-07-2025", ptpDate.getAttribute("value"), true);

        // 4. Set valid Remarks
        WebElement remarks = driver.findElement(By.id("remarks"));
        remarks.clear();
        remarks.sendKeys("Test");
        Thread.sleep(200);
        log("Fill", "Set remarks", "Test", remarks.getAttribute("value"), true);

        // 5. Set Schedule Type = Downpayment + Schedule PTP
        WebElement schedType = driver.findElement(By.id("scheduleType"));
        Select selSched = new Select(schedType);
        selSched.selectByVisibleText("Downpayment + Schedule PTP");
        Thread.sleep(2000);
        log("Fill", "Set scheduleType", "Downpayment + Schedule PTP", 
                selSched.getFirstSelectedOption().getText(), true);

        // 6. Fill BOTH multiline sections FIRST, then click Add buttons

        // Set Remaining Amount (only if blank or 0)
        WebElement remAmt = driver.findElement(By.id("remAmt"));
        String remVal = remAmt.getAttribute("value").trim();
        if (remVal.isEmpty() || remVal.equals("0") || remVal.equals("0.0")) {
            remAmt.clear();
            remAmt.sendKeys("1500000");
            remAmt.sendKeys(Keys.TAB);
            Thread.sleep(300);
            dismissAlert();
            log("Fill", "Set remAmt", "1500000", remAmt.getAttribute("value"), true);
        } else {
            logInfo("Fill", "remAmt already has value", remVal);
        }

        // --- Partial Payment section ---
        WebElement planDate = driver.findElement(By.id("planDate"));
        jse.executeScript("arguments[0].scrollIntoView({block:'center'})", planDate);
        jse.executeScript("arguments[0].value='01-07-2025'", planDate);
        hideDatepicker();
        Thread.sleep(300);
        log("DP Fill", "Set planDate", "01-07-2025", planDate.getAttribute("value"), true);

        WebElement plannedAmt = driver.findElement(By.id("plannedAmt"));
        jse.executeScript("arguments[0].value='70000.0'", plannedAmt);
        jse.executeScript("arguments[0].dispatchEvent(new Event('change'))", plannedAmt);
        Thread.sleep(500);
        dismissAlert();
        log("DP Fill", "Set plannedAmt", "70000.0", plannedAmt.getAttribute("value"), true);

        Select dpMode = new Select(driver.findElement(By.id("paymentMode")));
        dpMode.selectByVisibleText("CASH");
        Thread.sleep(500);
        log("DP Fill", "Set paymentMode", "CASH", dpMode.getFirstSelectedOption().getText(), true);

        WebElement receiptNo = driver.findElement(By.id("receiptNo"));
        receiptNo.clear();
        receiptNo.sendKeys("12345");
        Thread.sleep(200);
        log("DP Fill", "Set receiptNo", "12345", receiptNo.getAttribute("value"), true);

        // --- Schedule PTP section ---
        WebElement planDate1 = driver.findElement(By.id("planDate1"));
        jse.executeScript("arguments[0].scrollIntoView({block:'center'})", planDate1);
        jse.executeScript("arguments[0].value='07-01-2025'", planDate1);
        hideDatepicker();
        Thread.sleep(300);
        log("Sch Fill", "Set planDate1", "07-01-2025", planDate1.getAttribute("value"), true);

        WebElement plannedAmt1 = driver.findElement(By.id("plannedAmt1"));
        jse.executeScript("arguments[0].value='1430000.0'", plannedAmt1);
        jse.executeScript("arguments[0].dispatchEvent(new Event('change'))", plannedAmt1);
        Thread.sleep(500);
        dismissAlert();
        log("Sch Fill", "Set plannedAmt1", "1430000.0", plannedAmt1.getAttribute("value"), true);

        WebElement payMode1 = driver.findElement(By.id("paymentMode1"));
        jse.executeScript("arguments[0].scrollIntoView({block:'center'})", payMode1);
        Select schMode = new Select(payMode1);
        schMode.selectByVisibleText("CASH");
        Thread.sleep(500);
        log("Sch Fill", "Set paymentMode1", "CASH", schMode.getFirstSelectedOption().getText(), true);

        try {
            WebElement receiptNo1 = driver.findElement(By.id("receiptNo1"));
            if (receiptNo1.isDisplayed()) {
                receiptNo1.clear();
                receiptNo1.sendKeys("12345");
                Thread.sleep(200);
                log("Sch Fill", "Set receiptNo1", "12345", receiptNo1.getAttribute("value"), true);
            }
        } catch (Exception e) {}

        // 7. Click 1st Add button (Partial Payment)
        WebElement add2 = driver.findElement(By.id("add2"));
        jse.executeScript("arguments[0].scrollIntoView({block:'center'})", add2);
        Thread.sleep(500);
        jse.executeScript("arguments[0].click()", add2);
        Thread.sleep(2000);
        dismissAlert();
        log("Add", "Click DP Add (add2)", "Clicked", "Clicked", true);

        // 8. Click 2nd Add button (Schedule PTP)
        WebElement add3 = driver.findElement(By.id("add3"));
        jse.executeScript("arguments[0].scrollIntoView({block:'center'})", add3);
        Thread.sleep(500);
        jse.executeScript("arguments[0].click()", add3);
        Thread.sleep(2000);
        dismissAlert();
        log("Add", "Click Schedule Add (add3)", "Clicked", "Clicked", true);

        // 9. Save
        WebElement saveBtn = driver.findElement(By.id("saveData"));
        jse.executeScript("arguments[0].scrollIntoView({block:'center'})", saveBtn);
        Thread.sleep(500);
        log("Save", "Save button visible", "true", String.valueOf(saveBtn.isDisplayed()), saveBtn.isDisplayed());
        jse.executeScript("arguments[0].click()", saveBtn);
        Thread.sleep(3000);
        dismissAlert();

        String toast = getSuccessToast();
        log("Save", "Save Downpayment", "Success", toast.isEmpty() ? "No toast" : toast, !toast.isEmpty());

        sa.assertAll();
    }

    @Test(priority = 15)
    public void validateDatabase() throws Exception {
        String today = java.time.LocalDate.now().toString();
        String query = "SELECT TOP 1 * FROM d310025 ORDER BY createdDate DESC, createdTime DESC";
        ResultSet rs = DBConnection.executeQuery(query);

        if (rs == null || !rs.next()) {
            log("DB", "Record found in d310025", "Yes", "No", false);
            sa.fail("No record found in d310025");
            DBConnection.close(rs);
            return;
        }

        log("DB", "Record found", "Yes", "Yes", true);

        String dbCreatedDate = rs.getString("createdDate");
        log("DB", "createdDate contains today", today, dbCreatedDate,
                dbCreatedDate != null && dbCreatedDate.contains(today));
        sa.assertTrue(dbCreatedDate != null && dbCreatedDate.contains(today), "createdDate should contain today");

        String dbIsActive = rs.getString("isActive");
        log("DB", "isActive", "1", dbIsActive, "1".equals(dbIsActive));
        sa.assertEquals(dbIsActive, "1", "isActive should be 1");

        String dbRemarks = rs.getString("remarks");
        log("DB", "remarks", "Downpayment validation test record", dbRemarks,
                dbRemarks != null && dbRemarks.contains("Downpayment validation test record"));

        String dbCaseNo = rs.getString("caseNo");
        log("DB", "caseNo", "CASE_0005282011204001137", dbCaseNo,
                dbCaseNo != null && dbCaseNo.contains("CASE_0005282011204001137"));

        String dbPaymentMode = rs.getString("paymentMode");
        log("DB", "paymentMode", "Not Null", dbPaymentMode, dbPaymentMode != null && !dbPaymentMode.isEmpty());

        logInfo("DB", "createdTime", rs.getString("createdTime"));

        DBConnection.close(rs);
        sa.assertAll();
    }

    // ========== HELPER METHODS ==========

    private boolean isFieldVisible(String id) {
        try {
            List<WebElement> els = driver.findElements(By.id(id));
            return els.size() > 0 && els.get(0).isDisplayed();
        } catch (Exception e) {
            return false;
        }
    }

    private void validateTextField(String id, String fieldName, String testValue) throws Exception {
        try {
            WebElement field = driver.findElement(By.id(id));
            field.clear();
            field.sendKeys(testValue);
            Thread.sleep(200);
            hideDatepicker();
            String val = field.getAttribute("value");
            log(fieldName, "Enter '" + testValue + "'", testValue, val, val.contains(testValue) || !val.isEmpty());
            field.clear();
            hideDatepicker();
            field.sendKeys(testValue);
            Thread.sleep(200);
            hideDatepicker();
        } catch (Exception e) {
            log(fieldName, "Field interaction", "Accessible", "ERROR: " + e.getMessage(), false);
        }
    }

    private void validateDateField(String id, String fieldName, String testValue) throws Exception {
        try {
            WebElement field = driver.findElement(By.id(id));
            jse.executeScript("arguments[0].value='" + testValue + "'", field);
            hideDatepicker();
            String val = field.getAttribute("value");
            log(fieldName, "Enter '" + testValue + "'", testValue, val, val.contains(testValue) || !val.isEmpty());
            jse.executeScript("arguments[0].value=''", field);
            hideDatepicker();
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
                f.sendKeys(Keys.TAB);
                try {
                    Thread.sleep(300);
                } catch (Exception e) {
                }
                dismissAlert();
            } else {
                f.clear();
                if (!input.isEmpty())
                    f.sendKeys(input);
                f.sendKeys(Keys.TAB);
                try {
                    Thread.sleep(300);
                } catch (Exception e) {
                }
                dismissAlert();
            }
            String actual = f.getAttribute("value");

            switch (checkType) {
                case "equals":
                    String cleanActual = actual.replaceAll(",", "").replaceAll("\\.0+$", "");
                    String cleanExpected = expected.replaceAll(",", "").replaceAll("\\.0+$", "");
                    log(fn, desc, expected, actual, cleanActual.equals(cleanExpected));
                    sa.assertEquals(cleanActual, cleanExpected, desc);
                    break;
                case "notEquals":
                    String cleanActual2 = actual.replaceAll(",", "").replaceAll("\\.0+$", "");
                    String cleanInput = input.replaceAll(",", "").replaceAll("\\.0+$", "");
                    log(fn, desc, "Not " + input, actual, !cleanActual2.equals(cleanInput));
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
            if (!input.isEmpty())
                f.sendKeys(input);
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
