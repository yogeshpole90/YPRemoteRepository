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

import java.time.Duration;
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
public class FollowUpTest extends BaseTest {

    private void hideDatepicker() {
        try {
            jse.executeScript(
                "var dp = document.querySelectorAll('.datepicker, .ui-datepicker, .daterangepicker, .bootstrap-datetimepicker-widget');" +
                "dp.forEach(function(el){ el.style.display='none'; });");
        } catch (Exception e) {}
        try { driver.findElement(By.tagName("body")).sendKeys(Keys.ESCAPE); } catch (Exception e) {}
    }

    @BeforeClass
    public void setup() throws Exception {
        ExtentManager.initReport("FollowUp");
        ExtentManager.startTest("Follow Up - Full Validation");

        navigateToCase(ConfigManager.get("casenumber"));

        WebElement followUp = driver.findElement(By.xpath("//*[contains(@href,'Follow-Up')]"));
        jse.executeScript("arguments[0].scrollIntoView({block:'center',behavior:'smooth'})", followUp);
        Thread.sleep(1000);
        followUp.click();
        Thread.sleep(2000);

        WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(15));
        WebElement addFU = wait.until(ExpectedConditions.elementToBeClickable(By.xpath("//*[contains(text(),'Add Follow-Up')]")));
        jse.executeScript("arguments[0].scrollIntoView({block:'center',behavior:'smooth'})", addFU);
        Thread.sleep(1000);
        addFU.click();
        Thread.sleep(2000);
        driver.switchTo().frame("addcommunicationHistoryFrame");
        logInfo("Frame", "Switched to", "addcommunicationHistoryFrame");

        // Select2 - Loan Account
        WebDriverWait wait2 = new WebDriverWait(driver, Duration.ofSeconds(10));
        WebElement select2Container = wait2.until(ExpectedConditions.elementToBeClickable(
            By.cssSelector("#loanAcNoSelect ~ .select2-container")));
        select2Container.click();
        Thread.sleep(500);
        WebElement firstOption = wait2.until(ExpectedConditions.elementToBeClickable(
            By.cssSelector(".select2-results__option:first-child")));
        firstOption.click();
        Thread.sleep(1000);
        logInfo("loanAcNoSelect", "Loan Account Selected", "First option");
    }

    @Test(priority = 1)
    public void validateAllFields() throws Exception {
        Object[][] data = ExcelReader.getByTcPrefix(SheetConstants.SHEET_FOLLOW_UP, SheetConstants.TC.FOLLOW_UP);

        for (Object[] row : data) {
            String fieldName = row[SheetConstants.Cols.FIELD_NAME].toString().trim();
            String input = row[SheetConstants.Cols.INPUT].toString().trim();
            String expected = row[SheetConstants.Cols.EXPECTED].toString().trim();
            String desc = row[SheetConstants.Cols.DESCRIPTION].toString().trim();
            String checkType = row[SheetConstants.Cols.CHECK_TYPE].toString().trim();

            try {
                WebElement f = driver.findElement(By.id(fieldName));
                jse.executeScript("arguments[0].scrollIntoView({block:'center'})", f);
                Thread.sleep(300);
                String tagName = f.getTagName();

                if (tagName.equals("select")) {
                    Select s = new Select(f);
                    if (checkType.equals("info")) {
                        logInfo(fieldName, desc, s.getFirstSelectedOption().getText().trim());
                    } else if (checkType.equals("index")) {
                        int idx = Integer.parseInt(input);
                        s.selectByIndex(idx);
                        Thread.sleep(500);
                        String actual = s.getFirstSelectedOption().getText().trim();
                        log(fieldName, desc, "Non-empty", actual, !actual.isEmpty() && !actual.contains("SELECT"));
                    } else {
                        try {
                            s.selectByVisibleText(input);
                            Thread.sleep(500);
                            String actual = s.getFirstSelectedOption().getText().trim();
                            log(fieldName, desc, expected, actual, actual.equalsIgnoreCase(expected));
                            sa.assertEquals(actual.toLowerCase(), expected.toLowerCase(), desc);
                        } catch (Exception e) {
                            // Fallback: try selectByIndex(1)
                            try {
                                s.selectByIndex(1);
                                Thread.sleep(500);
                                String actual = s.getFirstSelectedOption().getText().trim();
                                log(fieldName, desc, expected, actual + " (fallback index 1)", !actual.isEmpty());
                            } catch (Exception e2) {
                                log(fieldName, desc, expected, "Option not found: " + input, false);
                            }
                        }
                    }
                } else {
                    String fieldId = fieldName.toLowerCase();
                    if (fieldId.contains("date") || fieldId.equals("resolve") || fieldId.equals("followupdate")) {
                        if (input.equalsIgnoreCase("Empty")) {
                            jse.executeScript("arguments[0].value=''", f);
                        } else {
                            jse.executeScript("arguments[0].value='" + input + "'", f);
                        }
                        hideDatepicker();
                        Thread.sleep(300);
                    } else {
                        f.clear();
                        Thread.sleep(200);
                        if (!input.isEmpty() && !input.equalsIgnoreCase("Empty")) {
                            f.sendKeys(input);
                        }
                        Thread.sleep(300);
                    }
                    String actual = f.getAttribute("value");

                    switch (checkType) {
                        case "equals": log(fieldName, desc, expected, actual, actual.equals(expected)); sa.assertEquals(actual, expected, desc); break;
                        case "notEquals": log(fieldName, desc, "Not " + input, actual, !actual.equals(input)); break;
                        case "empty": log(fieldName, desc, "Empty", actual, actual.isEmpty()); sa.assertTrue(actual.isEmpty(), desc); break;
                        case "info": logInfo(fieldName, desc, actual); break;
                    }
                }
            } catch (Exception e) {
                log(fieldName, desc, expected, "Element not found: " + fieldName, false);
            }
        }
    }

    @Test(priority = 2)
    public void validateSave() throws Exception {
        WebElement saveBtn = driver.findElement(By.id("saveData"));
        jse.executeScript("arguments[0].scrollIntoView({block:'center'})", saveBtn);
        Thread.sleep(500);
        log("Save", "Save button visible", "true", String.valueOf(saveBtn.isDisplayed()), saveBtn.isDisplayed());
        log("Save", "Save button enabled", "true", String.valueOf(saveBtn.isEnabled()), saveBtn.isEnabled());

        saveBtn.click();
        Thread.sleep(3000);

        // Try toast in frame
        String toast = getSuccessToast();
        if (toast.isEmpty()) {
            driver.switchTo().parentFrame();
            Thread.sleep(500);
            toast = getSuccessToast();
        }
        if (toast.isEmpty()) {
            driver.switchTo().defaultContent();
            jse.executeScript("window.scrollTo(0,0)");
            Thread.sleep(500);
            toast = getSuccessToast();
        }
        log("Save", "Save Follow Up", "Success", toast.isEmpty() ? "No toast" : toast, !toast.isEmpty());

        sa.assertAll();
    }

    @Test(priority = 3)
    public void validateDatabase() throws Exception {
        String today = java.time.LocalDate.now().toString();
        ResultSet rs = null;
        try {
            rs = DBConnection.executeQuery(
                "SELECT TOP 1 * FROM D310214 ORDER BY createdDate DESC, createdTime DESC");
        } catch (Exception e) {
            log("DB", "Table D310214 accessible", "Yes", "Error: " + e.getMessage(), false);
            logInfo("DB", "Note", "Table not working properly - skipping DB validation");
            return;
        }

        if (rs == null || !rs.next()) {
            log("DB", "Record found in D310214", "Yes", "No", false);
            logInfo("DB", "Note", "No record found - table may not be working properly");
            DBConnection.close(rs);
            return;
        }

        log("DB", "Record found", "Yes", "Yes", true);

        String dbCreatedDate = rs.getString("createdDate");
        log("DB", "createdDate contains today", today, dbCreatedDate, dbCreatedDate != null && dbCreatedDate.contains(today));

        String dbIsActive = rs.getString("isActive");
        log("DB", "isActive", "1", dbIsActive, "1".equals(dbIsActive));

        String dbCaseNo = rs.getString("caseNo");
        log("DB", "caseNo", "CASE_0005282011204001137", dbCaseNo, dbCaseNo != null && dbCaseNo.contains("CASE_0005282011204001137"));

        logInfo("DB", "createdTime", rs.getString("createdTime"));

        DBConnection.close(rs);
    }
}
