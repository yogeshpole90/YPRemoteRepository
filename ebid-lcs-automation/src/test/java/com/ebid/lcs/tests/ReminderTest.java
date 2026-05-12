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

import java.util.HashSet;
import java.util.List;
import java.util.Set;

import com.ebid.lcs.base.BaseTest;
import com.ebid.lcs.config.ConfigManager;
import com.ebid.lcs.excel.ExcelReader;
import com.ebid.lcs.excel.SheetConstants;
import com.ebid.lcs.listeners.TestListener;
import com.ebid.lcs.reporting.ExtentManager;

@Listeners(TestListener.class)
public class ReminderTest extends BaseTest {

    @BeforeClass
    public void setup() throws Exception {
        ExtentManager.initReport("Reminder");
        ExtentManager.startTest("Reminder - Full Validation");

        navigateToCase(ConfigManager.get("casenumber"));

        WebElement tab = driver.findElement(By.xpath("//*[contains(@href,'activeTab=Account Information')]"));
        jse.executeScript("arguments[0].scrollIntoView({block:'center',behavior:'smooth'})", tab);
        Thread.sleep(1000);
        tab.click();
        Thread.sleep(2000);

        WebDriverWait wait = new WebDriverWait(driver, 15);
        WebElement rmTab = wait.until(ExpectedConditions.elementToBeClickable(By.xpath("//a[contains(text(),'Reminder')]")));
        jse.executeScript("arguments[0].scrollIntoView({block:'center',behavior:'smooth'})", rmTab);
        Thread.sleep(1000);
        act.doubleClick(rmTab).build().perform();
        Thread.sleep(2000);

        driver.switchTo().frame("reminderFrame");
        logInfo("Frame", "Switched to", "reminderFrame");
    }

    @Test(priority = 1)
    public void validateReminderTypeDD() throws Exception {
        WebElement dd = driver.findElement(By.id("reminderType"));
        jse.executeScript("arguments[0].scrollIntoView({block:'center',behavior:'smooth'})", dd);
        Thread.sleep(300);
        Select select = new Select(dd);
        String fn = "Reminder Type DD";

        log(fn, "Displayed", "true", String.valueOf(dd.isDisplayed()), dd.isDisplayed());
        sa.assertTrue(dd.isDisplayed());
        log(fn, "Enabled", "true", String.valueOf(dd.isEnabled()), dd.isEnabled());
        sa.assertTrue(dd.isEnabled());
        log(fn, "Single select", "false", String.valueOf(select.isMultiple()), !select.isMultiple());

        String defaultVal = select.getFirstSelectedOption().getText().trim();
        log(fn, "Default value", "--SELECT--", defaultVal, defaultVal.contains("SELECT"));

        List<WebElement> options = select.getOptions();
        log(fn, "Options count", ">1", String.valueOf(options.size()), options.size() > 1);

        for (int i = 0; i < options.size(); i++) {
            logInfo(fn, "Option [" + i + "]", options.get(i).getText().trim());
        }

        // No duplicates
        Set<String> unique = new HashSet<>();
        boolean hasDup = false;
        for (WebElement opt : options) { if (!unique.add(opt.getText().trim())) { hasDup = true; break; } }
        log(fn, "No duplicate options", "false", String.valueOf(hasDup), !hasDup);

        // Select from Excel
        Object[][] data = ExcelReader.getByTcPrefix(SheetConstants.SHEET_REMINDER, SheetConstants.TC.REMINDER);
        for (Object[] row : data) {
            String fieldName = row[SheetConstants.Cols.FIELD_NAME].toString().trim();
            if (!fieldName.equals("reminderType")) continue;

            String input = row[SheetConstants.Cols.INPUT].toString().trim();
            String expected = row[SheetConstants.Cols.EXPECTED].toString().trim();
            String desc = row[SheetConstants.Cols.DESCRIPTION].toString();

            try {
                select.selectByVisibleText(input);
                Thread.sleep(300);
                String actual = select.getFirstSelectedOption().getText().trim();
                log(fn, desc, expected, actual, actual.equals(expected));
                sa.assertEquals(actual, expected, desc);
            } catch (Exception e) {
                log(fn, desc, expected, "Option not found: " + input, false);
            }
        }

        // Reset and set final
        select.selectByIndex(0);
        Thread.sleep(200);
        log(fn, "Reset to default", "--SELECT--", select.getFirstSelectedOption().getText().trim(), true);
        select.selectByIndex(1);
        Thread.sleep(200);
        log(fn, "Final selection for save", select.getFirstSelectedOption().getText().trim(), select.getFirstSelectedOption().getText().trim(), true);
    }

    @Test(priority = 2)
    public void validateReminderDate() throws Exception {
        WebElement d = driver.findElement(By.id("reminderDate"));
        String fn = "Reminder Date";

        log(fn, "Displayed", "true", String.valueOf(d.isDisplayed()), d.isDisplayed());
        sa.assertTrue(d.isDisplayed());
        log(fn, "Enabled", "true", String.valueOf(d.isEnabled()), d.isEnabled());
        sa.assertTrue(d.isEnabled());

        String placeholder = d.getAttribute("placeholder");
        logInfo(fn, "Placeholder", placeholder);
        String classAttr = d.getAttribute("class");
        logInfo(fn, "Class (datepicker)", classAttr);

        Object[][] data = ExcelReader.getByTcPrefix(SheetConstants.SHEET_REMINDER, SheetConstants.TC.REMINDER);
        for (Object[] row : data) {
            String fieldName = row[SheetConstants.Cols.FIELD_NAME].toString().trim();
            if (!fieldName.equals("reminderDate")) continue;

            String input = row[SheetConstants.Cols.INPUT].toString().trim();
            String expected = row[SheetConstants.Cols.EXPECTED].toString().trim();
            String desc = row[SheetConstants.Cols.DESCRIPTION].toString();
            String checkType = row[SheetConstants.Cols.CHECK_TYPE].toString();

            d.clear();
            if (!input.isEmpty() && !input.equalsIgnoreCase("Empty")) d.sendKeys(input);
            d.sendKeys(Keys.TAB);
            Thread.sleep(300);
            String actual = d.getAttribute("value");

            switch (checkType) {
                case "equals": log(fn, desc, expected, actual, actual.equals(expected)); sa.assertEquals(actual, expected, desc); break;
                case "notEquals": log(fn, desc, "Not " + input, actual, !actual.equals(input)); break;
                case "empty": log(fn, desc, "Empty", actual, actual.isEmpty()); break;
                case "info": logInfo(fn, desc, actual); break;
            }
        }
    }

    @Test(priority = 3)
    public void validateCreatedDate() throws Exception {
        WebElement d = driver.findElement(By.id("reminderCreateDate"));
        String fn = "Created Date";

        log(fn, "Displayed", "true", String.valueOf(d.isDisplayed()), d.isDisplayed());
        log(fn, "Enabled", "true", String.valueOf(d.isEnabled()), d.isEnabled());

        String defVal = d.getAttribute("value");
        logInfo(fn, "Default value", defVal);

        d.clear();
        d.sendKeys("11-05-2026");
        d.sendKeys(Keys.TAB);
        Thread.sleep(300);
        String actual = d.getAttribute("value");
        log(fn, "Enter valid date", "11-05-2026", actual, actual.equals("11-05-2026"));
    }

    @Test(priority = 4)
    public void validateRemarks() throws Exception {
        WebElement f = driver.findElement(By.id("remarks"));
        String fn = "Remarks";

        log(fn, "Displayed", "true", String.valueOf(f.isDisplayed()), f.isDisplayed());
        sa.assertTrue(f.isDisplayed());
        log(fn, "Enabled", "true", String.valueOf(f.isEnabled()), f.isEnabled());
        sa.assertTrue(f.isEnabled());

        String initVal = f.getAttribute("value");
        log(fn, "Initially empty", "Empty", initVal, initVal == null || initVal.isEmpty());

        Object[][] data = ExcelReader.getByTcPrefix(SheetConstants.SHEET_REMINDER, SheetConstants.TC.REMINDER);
        for (Object[] row : data) {
            String fieldName = row[SheetConstants.Cols.FIELD_NAME].toString().trim();
            if (!fieldName.equals("remarks")) continue;

            String input = row[SheetConstants.Cols.INPUT].toString().trim();
            String expected = row[SheetConstants.Cols.EXPECTED].toString().trim();
            String desc = row[SheetConstants.Cols.DESCRIPTION].toString();
            String checkType = row[SheetConstants.Cols.CHECK_TYPE].toString();

            f.clear();
            if (!input.isEmpty() && !input.equalsIgnoreCase("Empty")) f.sendKeys(input);
            String actual = f.getAttribute("value");

            switch (checkType) {
                case "equals": log(fn, desc, expected, actual, actual.equals(expected)); sa.assertEquals(actual, expected, desc); break;
                case "empty": log(fn, desc, "Empty", actual, actual.isEmpty()); sa.assertTrue(actual.isEmpty(), desc); break;
                case "info": logInfo(fn, desc, actual); break;
            }
        }
    }

    @Test(priority = 5)
    public void validateResetSave() throws Exception {
        // Reset button
        WebElement resetBtn = driver.findElement(By.id("reset"));
        log("Reset", "Displayed", "true", String.valueOf(resetBtn.isDisplayed()), resetBtn.isDisplayed());
        log("Reset", "Enabled", "true", String.valueOf(resetBtn.isEnabled()), resetBtn.isEnabled());

        // Fill data then reset
        new Select(driver.findElement(By.id("reminderType"))).selectByIndex(1);
        driver.findElement(By.id("reminderDate")).clear();
        driver.findElement(By.id("reminderDate")).sendKeys("11-05-2026");
        driver.findElement(By.id("remarks")).clear();
        driver.findElement(By.id("remarks")).sendKeys("Test Reset");
        Thread.sleep(300);

        resetBtn.click();
        Thread.sleep(500);

        String ddAfterReset = new Select(driver.findElement(By.id("reminderType"))).getFirstSelectedOption().getText().trim();
        log("Reset", "DD reset to default", "SELECT", ddAfterReset, ddAfterReset.contains("SELECT"));
        String dateAfterReset = driver.findElement(By.id("reminderDate")).getAttribute("value");
        log("Reset", "Date cleared", "Empty", dateAfterReset, dateAfterReset == null || dateAfterReset.isEmpty());
        String remarkAfterReset = driver.findElement(By.id("remarks")).getAttribute("value");
        log("Reset", "Remark cleared", "Empty", remarkAfterReset, remarkAfterReset == null || remarkAfterReset.isEmpty());

        // Save without data - mandatory check
        WebElement saveBtn = driver.findElement(By.id("save"));
        log("Save", "Displayed", "true", String.valueOf(saveBtn.isDisplayed()), saveBtn.isDisplayed());
        log("Save", "Enabled", "true", String.valueOf(saveBtn.isEnabled()), saveBtn.isEnabled());

        saveBtn.click();
        Thread.sleep(1000);
        String errorToast = getErrorToast();
        log("Save", "Save without data - mandatory", "Error toast", errorToast.isEmpty() ? "No toast" : errorToast, !errorToast.isEmpty());

        // Save with valid data
        new Select(driver.findElement(By.id("reminderType"))).selectByIndex(1);
        Thread.sleep(300);
        driver.findElement(By.id("reminderDate")).clear();
        driver.findElement(By.id("reminderDate")).sendKeys("11-05-2026");
        driver.findElement(By.id("reminderDate")).sendKeys(Keys.TAB);
        driver.findElement(By.id("reminderCreateDate")).clear();
        driver.findElement(By.id("reminderCreateDate")).sendKeys("11-05-2026");
        driver.findElement(By.id("reminderCreateDate")).sendKeys(Keys.TAB);
        driver.findElement(By.id("remarks")).clear();
        driver.findElement(By.id("remarks")).sendKeys("Reminder Valid Save");
        Thread.sleep(500);

        driver.findElement(By.id("save")).click();
        Thread.sleep(2000);
        String successToast = getSuccessToast();
        log("Save", "Save with valid data", "Success", successToast.isEmpty() ? "No toast" : successToast, !successToast.isEmpty());

        sa.assertAll();
    }
}
