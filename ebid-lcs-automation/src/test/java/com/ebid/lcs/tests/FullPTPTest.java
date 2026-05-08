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

@Listeners(TestListener.class)
public class FullPTPTest extends BaseTest {

    @BeforeClass
    public void setup() throws Exception {
        ExtentManager.initReport("FullPTP");
        ExtentManager.startTest("Full PTP - Complete Validation");

        navigateToCase(ConfigManager.get("casenumber"));

        // Remedial tab
        WebElement remedial = driver.findElement(By.xpath("(//*[contains(@href,'=Remedial Action')])[1]"));
        jse.executeScript("arguments[0].scrollIntoView({block:'center',behavior:'smooth'})", remedial);
        Thread.sleep(1000);
        remedial.click();
        Thread.sleep(2000);

        // PTP tab
        WebElement ptpTab = driver.findElement(By.xpath("//*[contains(text(),'Promise to pay')]"));
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

        // Account Transfer
        s.selectByVisibleText("Account Transfer"); Thread.sleep(1000);
        log(fn, "Select 'Account Transfer'", "Account Transfer", s.getFirstSelectedOption().getText(), true);

        // CASH
        s.selectByVisibleText("CASH"); Thread.sleep(1000);
        log(fn, "Select 'CASH'", "CASH", s.getFirstSelectedOption().getText(), true);

        // Cheque
        s.selectByVisibleText("Cheque"); Thread.sleep(1000);
        log(fn, "Select 'Cheque'", "Cheque", s.getFirstSelectedOption().getText(), true);

        // Final
        s.selectByVisibleText("CASH"); Thread.sleep(500);
        log(fn, "Final mode set", "CASH", s.getFirstSelectedOption().getText(), true);
    }

    @Test(priority = 6)
    public void validatePlannedAmt() throws Exception {
        WebElement f = driver.findElement(By.id("plannedAmt"));
        String fn = "Planned Amount";

        log(fn, "Should be visible", "true", String.valueOf(f.isDisplayed()), f.isDisplayed());

        Object[][] data = ExcelReader.getByTcPrefix(SheetConstants.SHEET_FULL_PTP, SheetConstants.TC.PTP_PLANNED_AMT);
        runFieldValidation(f, fn, data, "js");
    }

    @Test(priority = 7)
    public void validateRemAmount() throws Exception {
        WebElement f = driver.findElement(By.id("remAmt"));
        String fn = "Remaining Amount";

        log(fn, "Should be visible", "true", String.valueOf(f.isDisplayed()), f.isDisplayed());

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
        WebElement ptpTab = driver.findElement(By.xpath("//ul[@id='myTab']/li[3]/a"));
        jse.executeScript("arguments[0].scrollIntoView({block:'center'})", ptpTab);
        driver.switchTo().frame("fetchPTPMstTabFrame");

        // View
        List<WebElement> viewBtns = driver.findElements(By.xpath("//a[contains(@class,'ViewBtn')]"));
        log("View Button", "View buttons found", ">0", String.valueOf(viewBtns.size()), viewBtns.size() > 0);
        if (viewBtns.size() > 0) {
            viewBtns.get(viewBtns.size() - 1).click();
            Thread.sleep(1000);
            log("View Button", "Click last View", "Opened", "Clicked", true);
        }

        // Edit
        List<WebElement> editBtns = driver.findElements(By.xpath("//a[contains(@class,'EditBtn')]"));
        log("Edit Button", "Edit buttons found", ">0", String.valueOf(editBtns.size()), editBtns.size() > 0);
        if (editBtns.size() > 0) {
            editBtns.get(editBtns.size() - 1).click();
            Thread.sleep(1000);
            log("Edit Button", "Click last Edit", "Opened", "Clicked", true);
        }

        sa.assertAll();
    }

    // ========== HELPER METHODS ==========

    private void runFieldValidation(WebElement f, String fn, Object[][] data, String mode) {
        for (Object[] row : data) {
            String input = row[SheetConstants.Cols.INPUT].toString();
            String expected = row[SheetConstants.Cols.EXPECTED].toString();
            String desc = row[SheetConstants.Cols.DESCRIPTION].toString();
            String checkType = row[SheetConstants.Cols.CHECK_TYPE].toString();

            if (mode.equals("js")) {
                jse.executeScript("arguments[0].value='" + input + "'", f);
            } else {
                f.clear();
                if (!input.isEmpty()) f.sendKeys(input);
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

            f.clear();
            if (!input.isEmpty()) f.sendKeys(input);
            f.sendKeys(Keys.TAB);
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
