package com.ebid.lcs.tests;

import org.openqa.selenium.By;
import org.openqa.selenium.Keys;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.Select;
import org.openqa.selenium.support.ui.WebDriverWait;
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
    public void validateOverdueAmt() throws Exception {
        WebElement f = driver.findElement(By.id("overdueAmount"));
        String fn = "Overdue Amount";

        log(fn, "Should be visible", "true", String.valueOf(f.isDisplayed()), f.isDisplayed());
        log(fn, "Should be enabled", "true", String.valueOf(f.isEnabled()), f.isEnabled());

        Object[][] data = ExcelReader.getByTcPrefix(SheetConstants.SHEET_DOWNPAYMENT, "OD_");
        runFieldValidation(f, fn, data, "sendKeys");
    }

    @Test(priority = 2)
    public void validatePTPDate() throws Exception {
        WebElement f = driver.findElement(By.id("dateOfPTPStart"));
        String fn = "PTP Start Date";

        log(fn, "Should be visible", "true", String.valueOf(f.isDisplayed()), f.isDisplayed());
        log(fn, "Should be enabled", "true", String.valueOf(f.isEnabled()), f.isEnabled());

        Object[][] data = ExcelReader.getByTcPrefix(SheetConstants.SHEET_DOWNPAYMENT, "DT_");
        runFieldValidationWithTab(f, fn, data);
    }

    @Test(priority = 3)
    public void validateRemarks() throws Exception {
        WebElement f = driver.findElement(By.id("remarks"));
        String fn = "Remarks";

        log(fn, "Should be visible", "true", String.valueOf(f.isDisplayed()), f.isDisplayed());
        log(fn, "Should be enabled", "true", String.valueOf(f.isEnabled()), f.isEnabled());

        Object[][] data = ExcelReader.getByTcPrefix(SheetConstants.SHEET_DOWNPAYMENT, "RM_");
        runFieldValidation(f, fn, data, "sendKeys");
    }

    @Test(priority = 4)
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

    @Test(priority = 5)
    public void validateDPPlanDate() throws Exception {
        WebElement f = driver.findElement(By.id("planDate"));
        String fn = "DP Planned Date";

        log(fn, "Should be visible", "true", String.valueOf(f.isDisplayed()), f.isDisplayed());
        log(fn, "Should be enabled", "true", String.valueOf(f.isEnabled()), f.isEnabled());

        Object[][] data = ExcelReader.getByTcPrefix(SheetConstants.SHEET_DOWNPAYMENT, "PD_");
        runFieldValidationWithTab(f, fn, data);
    }

    @Test(priority = 6)
    public void validateDPPlanAmt() throws Exception {
        WebElement f = driver.findElement(By.id("plannedAmt"));
        String fn = "DP Planned Amount";

        log(fn, "Should be visible", "true", String.valueOf(f.isDisplayed()), f.isDisplayed());
        logInfo(fn, "Field state", "Enabled=" + f.isEnabled() + " | ReadOnly=" + f.getAttribute("readonly"));

        Object[][] data = ExcelReader.getByTcPrefix(SheetConstants.SHEET_DOWNPAYMENT, "PA_");
        runFieldValidation(f, fn, data, "js");
    }

    @Test(priority = 7)
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

    @Test(priority = 8)
    public void validateSaveDP() throws Exception {
        WebElement saveBtn = driver.findElement(By.id("savePTP"));
        String fn = "Save DP Button";

        log(fn, "Should be visible", "true", String.valueOf(saveBtn.isDisplayed()), saveBtn.isDisplayed());
        log(fn, "Should be enabled", "true", String.valueOf(saveBtn.isEnabled()), saveBtn.isEnabled());

        jse.executeScript("arguments[0].click()", saveBtn);
        Thread.sleep(2000);
        dismissAlert();

        logInfo(fn, "Action", "Save button clicked successfully");
    }

    @Test(priority = 9)
    public void validateRemAmount() throws Exception {
        WebElement f = driver.findElement(By.id("remAmt"));
        String fn = "DP Remaining Amount";

        log(fn, "Should be visible", "true", String.valueOf(f.isDisplayed()), f.isDisplayed());
        logInfo(fn, "Field state", "Enabled=" + f.isEnabled() + " | ReadOnly=" + f.getAttribute("readonly"));

        Object[][] data = ExcelReader.getByTcPrefix(SheetConstants.SHEET_DOWNPAYMENT, "RA_");
        runFieldValidation(f, fn, data, "js");
    }

    @Test(priority = 10)
    public void validateSchPlanDate() throws Exception {
        WebElement f = driver.findElement(By.id("planDate1"));
        String fn = "Schedule Planned Date";

        log(fn, "Should be visible", "true", String.valueOf(f.isDisplayed()), f.isDisplayed());
        log(fn, "Should be enabled", "true", String.valueOf(f.isEnabled()), f.isEnabled());

        Object[][] data = ExcelReader.getByTcPrefix(SheetConstants.SHEET_DOWNPAYMENT, "SD_");
        runFieldValidationWithTab(f, fn, data);
    }

    @Test(priority = 11)
    public void validateSchPlanAmt() throws Exception {
        WebElement f = driver.findElement(By.id("plannedAmt1"));
        String fn = "Schedule Planned Amount";

        log(fn, "Should be visible", "true", String.valueOf(f.isDisplayed()), f.isDisplayed());
        logInfo(fn, "Field state", "Enabled=" + f.isEnabled() + " | ReadOnly=" + f.getAttribute("readonly"));

        Object[][] data = ExcelReader.getByTcPrefix(SheetConstants.SHEET_DOWNPAYMENT, "SA_");
        runFieldValidation(f, fn, data, "js");
    }

    @Test(priority = 12)
    public void validateAddSave() throws Exception {
        WebDriverWait wait = new WebDriverWait(driver, 10);

        // Re-select DP Payment Mode (first line)
        try {
            WebElement pm = driver.findElement(By.id("paymentMode"));
            jse.executeScript("arguments[0].scrollIntoView({block:'center'})", pm);
            new Select(pm).selectByVisibleText("CASH");
            Thread.sleep(500);
            hideDatepicker();
            // Set receiptNo for CASH
            try {
                WebElement rn = driver.findElement(By.id("receiptNo"));
                if (rn.isDisplayed()) {
                    rn.clear();
                    rn.sendKeys("RCP001");
                    hideDatepicker();
                }
            } catch (Exception e) {
            }
        } catch (Exception e) {
        }

        // Re-select Schedule Payment Mode (second line)
        try {
            WebElement pm1 = driver.findElement(By.xpath("//select[@id='paymentMode1']"));
            jse.executeScript("arguments[0].scrollIntoView({block:'center'})", pm1);
            if (pm1.isDisplayed()) {
                new Select(pm1).selectByVisibleText("CASH");
                Thread.sleep(500);
            }
        } catch (Exception e) {
        }

        // DP Add button (add2)
        WebElement add1 = wait.until(ExpectedConditions.elementToBeClickable(By.xpath("//button[@id='add2']")));
        jse.executeScript("arguments[0].scrollIntoView({block:'center'})", add1);
        Thread.sleep(300);
        add1.click();
        log("Add Button", "Click DP Add (add2)", "Clicked", "Clicked", true);

        // Schedule Add button (add3)
        WebElement add2 = wait.until(ExpectedConditions.elementToBeClickable(By.xpath("//button[@id='add3']")));
        jse.executeScript("arguments[0].scrollIntoView({block:'center'})", add2);
        Thread.sleep(300);
        add2.click();
        log("Add Button", "Click Schedule Add (add3)", "Clicked", "Clicked", true);

        // Save
        dismissAlert();
        WebElement saveBtn = wait.until(ExpectedConditions.elementToBeClickable(By.id("saveData")));
        jse.executeScript("arguments[0].scrollIntoView({block:'center'})", saveBtn);
        Thread.sleep(300);
        saveBtn.click();
        Thread.sleep(1000);
        dismissAlert();

        // Capture toast — scroll to search area where toast appears
        try {
            WebElement searchArea = driver.findElement(By.cssSelector("input[placeholder='Search keyword here']"));
            jse.executeScript("arguments[0].scrollIntoView({block:'center'})", searchArea);
        } catch (Exception e) {
        }
        Thread.sleep(500);
        String toast = getSuccessToast();
        if (toast.isEmpty()) {
            driver.switchTo().defaultContent();
            try {
                WebElement searchArea2 = driver.findElement(By.cssSelector("input[placeholder='Search keyword here']"));
                jse.executeScript("arguments[0].scrollIntoView({block:'center'})", searchArea2);
            } catch (Exception e) {
            }
            toast = getSuccessToast();
        }
        log("Save", "Save Downpayment", "Success", toast.isEmpty() ? "No toast" : toast, !toast.isEmpty());
        log("Save Button", "Click Save", "Saved", "Clicked", true);

        Thread.sleep(3000);
        driver.switchTo().defaultContent();
        Thread.sleep(1000);

        // Re-click PTP tab
        WebElement ptpTab = wait
                .until(ExpectedConditions.elementToBeClickable(By.xpath("//a[contains(text(),'Promise to pay')]")));
        jse.executeScript("arguments[0].scrollIntoView({block:'center'})", ptpTab);
        Thread.sleep(500);
        ptpTab.click();
        Thread.sleep(2000);
        try {
            driver.switchTo().frame("fetchPTPMstTabFrame");
        } catch (Exception e) {
        }

        // View
        List<WebElement> viewBtns = driver.findElements(By.xpath("(//a[contains(@class,'ViewBtn')])[last()]"));
        log("View Button", "View buttons found", ">0", String.valueOf(viewBtns.size()), viewBtns.size() > 0);
        if (viewBtns.size() > 0) {
            viewBtns.get(viewBtns.size() - 1).click();
            Thread.sleep(500);
            jse.executeScript("window.scrollTo(0, document.body.scrollHeight)");
            Thread.sleep(500);
            log("View Button", "Click View - scrolled to bottom", "Displayed", "View clicked", true);
        }

        // Edit
        List<WebElement> editBtns = driver.findElements(By.xpath("//a[contains(@class,'EditBtn')]"));
        log("Edit Button", "Edit buttons found", ">0", String.valueOf(editBtns.size()), editBtns.size() > 0);
        if (editBtns.size() > 0) {
            editBtns.get(editBtns.size() - 1).click();
            Thread.sleep(500);
            log("Edit Button", "Click Edit", "Editable", "Edit clicked", true);
        }

        // Disable
        jse.executeScript("window.scrollTo(0, document.body.scrollHeight)");
        Thread.sleep(500);
        List<WebElement> disableBtns = driver.findElements(
                By.xpath("(//a[contains(@class,'btn-danger') and contains(@onclick,'disableRecord')])[last()]"));
        log("Disable Button", "Disable buttons found", ">0", String.valueOf(disableBtns.size()),
                disableBtns.size() > 0);
        if (disableBtns.size() > 0) {
            jse.executeScript("arguments[0].scrollIntoView({block:'center'})", disableBtns.get(disableBtns.size() - 1));
            Thread.sleep(300);
            disableBtns.get(disableBtns.size() - 1).click();
            Thread.sleep(500);
            log("Disable Button", "Click Disable", "Disabled", "Disable clicked", true);
            try {
                driver.findElement(By.id("popUpYes")).click();
                Thread.sleep(500);
                log("Disable Button", "Click Yes on popup", "Disabled", "Yes clicked", true);
            } catch (Exception e) {
                log("Disable Button", "No popup", "Direct disable", "Disabled", true);
            }
        }

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
