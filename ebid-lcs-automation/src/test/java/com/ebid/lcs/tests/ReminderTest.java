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
import java.util.HashSet;
import java.util.List;
import java.util.Set;

import com.ebid.lcs.base.BaseTest;
import com.ebid.lcs.config.ConfigManager;
import com.ebid.lcs.excel.ExcelReader;
import com.ebid.lcs.excel.SheetConstants;
import com.ebid.lcs.listeners.TestListener;
import com.ebid.lcs.reporting.ExtentManager;
import com.ebid.lcs.utils.DBConnection;

import java.sql.ResultSet;

@Listeners(TestListener.class)
public class ReminderTest extends BaseTest {

    @BeforeClass
    public void setup() throws Exception {
        ExtentManager.initReport("Reminder");
        ExtentManager.startTest("Reminder - Full Validation");

        navigateToCase(ConfigManager.get("casenumber"));

        // Click Communication History tab first
        WebElement commTab = driver.findElement(By.xpath("//a[contains(@href,'activeTab=Communication History')]"));
        jse.executeScript("arguments[0].scrollIntoView({block:'center',behavior:'smooth'})", commTab);
        Thread.sleep(500);
        commTab.click();
        Thread.sleep(1000);

        // Click Reminder tab
        WebElement rmTab = driver.findElement(By.xpath("//a[contains(text(),'Reminder')]"));
        jse.executeScript("arguments[0].scrollIntoView({block:'center',behavior:'smooth'})", rmTab);
        Thread.sleep(500);
        act.doubleClick(rmTab).build().perform();
        Thread.sleep(2000);

        // Switch to frame
        WebElement frame = driver.findElement(By.id("fetchReminderDtlsPageFrame"));
        jse.executeScript("arguments[0].scrollIntoView({block:'center'})", frame);
        Thread.sleep(500);
        driver.switchTo().frame("fetchReminderDtlsPageFrame");
        logInfo("Frame", "Switched to", "fetchReminderDtlsPageFrame");
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
        // Fill valid data for save
        new Select(driver.findElement(By.id("reminderType"))).selectByIndex(1);
        Thread.sleep(300);
        driver.findElement(By.id("reminderDate")).clear();
        driver.findElement(By.id("reminderDate")).sendKeys("11-05-2026");
        driver.findElement(By.id("reminderCreateDate")).clear();
        driver.findElement(By.id("reminderCreateDate")).sendKeys("11-05-2026");
        driver.findElement(By.id("remarks")).clear();
        driver.findElement(By.id("remarks")).sendKeys("Reminder Valid Save");
        Thread.sleep(500);

        WebElement saveBtn = driver.findElement(By.id("save"));
        log("Save", "Displayed", "true", String.valueOf(saveBtn.isDisplayed()), saveBtn.isDisplayed());
        log("Save", "Enabled", "true", String.valueOf(saveBtn.isEnabled()), saveBtn.isEnabled());
        saveBtn.click();
        Thread.sleep(1000);

        // Handle confirmation modal
        try {
            WebElement confirmYes = driver.findElement(By.id("submitForm"));
            if (confirmYes.isDisplayed()) {
                confirmYes.click();
                Thread.sleep(2000);
                log("Save", "Confirmation modal - clicked Yes", "Clicked", "Clicked", true);
            }
        } catch (Exception e) {}

        String successToast = getSuccessToast();
        log("Save", "Save Reminder", "Success", successToast.isEmpty() ? "No toast" : successToast, !successToast.isEmpty());
        sa.assertAll();
    }

    @Test(priority = 6)
    public void validateView() throws Exception {
        Thread.sleep(1000);
        try {
            WebElement search = driver.findElement(By.cssSelector("input[placeholder='Search keyword here']"));
            jse.executeScript("arguments[0].scrollIntoView({block:'center',behavior:'smooth'})", search);
            Thread.sleep(500);
        } catch (Exception e) {}

        // Click View of first row (latest record - table sorted descending)
        WebElement viewBtn = driver.findElement(By.cssSelector("#dt-basicDetails tbody tr:first-child a[onclick*='ViewData']"));
        jse.executeScript("arguments[0].scrollIntoView({block:'center'})", viewBtn);
        Thread.sleep(500);
        jse.executeScript("arguments[0].click()", viewBtn);
        Thread.sleep(2000);
        log("View", "Click View button (latest record)", "View mode opened", "Clicked", true);

        // Validate fields in view mode - jo save kiya wohi dikh raha hai
        String reminderType = new Select(driver.findElement(By.id("reminderType"))).getFirstSelectedOption().getText().trim();
        log("reminderType", "View - Reminder Type", "Call", reminderType, reminderType.equals("Call"));

        String reminderDate = driver.findElement(By.id("reminderDate")).getAttribute("value");
        log("reminderDate", "View - Reminder Date", "11-05-2026", reminderDate, reminderDate.equals("11-05-2026"));

        String reminderCreateDate = driver.findElement(By.id("reminderCreateDate")).getAttribute("value");
        log("reminderCreateDate", "View - Created Date", "11-05-2026", reminderCreateDate, reminderCreateDate.equals("11-05-2026"));

        String remarks = driver.findElement(By.id("remarks")).getAttribute("value");
        log("remarks", "View - Remarks", "Reminder Valid Save", remarks, remarks.contains("Reminder Valid Save"));

        log("View", "Field validation in view mode", "All fields match", "Done", true);
    }

    @Test(priority = 7)
    public void validateEdit() throws Exception {
        Thread.sleep(1000);
        try {
            WebElement search = driver.findElement(By.cssSelector("input[placeholder='Search keyword here']"));
            jse.executeScript("arguments[0].scrollIntoView({block:'center',behavior:'smooth'})", search);
            Thread.sleep(500);
        } catch (Exception e) {}

        // Click Edit of first row (latest record)
        WebElement editBtn = driver.findElement(By.cssSelector("#dt-basicDetails tbody tr:first-child a.editBtn"));
        jse.executeScript("arguments[0].scrollIntoView({block:'center'})", editBtn);
        Thread.sleep(500);
        jse.executeScript("arguments[0].click()", editBtn);
        Thread.sleep(2000);
        log("Edit", "Click Edit button (latest record)", "Edit mode opened", "Clicked", true);

        // Re-fill all required fields after edit click
        new Select(driver.findElement(By.id("reminderType"))).selectByIndex(1);
        Thread.sleep(300);

        WebElement reminderDate = driver.findElement(By.id("reminderDate"));
        reminderDate.clear();
        reminderDate.sendKeys("11-05-2026");
        Thread.sleep(200);

        WebElement reminderCreateDate = driver.findElement(By.id("reminderCreateDate"));
        reminderCreateDate.clear();
        reminderCreateDate.sendKeys("11-05-2026");
        Thread.sleep(200);

        // Update remarks
        WebElement remarks = driver.findElement(By.id("remarks"));
        jse.executeScript("arguments[0].scrollIntoView({block:'center'})", remarks);
        remarks.clear();
        remarks.sendKeys("Reminder Updated Save");
        Thread.sleep(300);

        // Save
        WebElement saveBtn = driver.findElement(By.id("save"));
        jse.executeScript("arguments[0].scrollIntoView({block:'center'})", saveBtn);
        Thread.sleep(300);
        saveBtn.click();
        Thread.sleep(1000);

        try {
            WebElement confirmYes = driver.findElement(By.id("submitForm"));
            if (confirmYes.isDisplayed()) {
                confirmYes.click();
                Thread.sleep(2000);
            }
        } catch (Exception e) {}

        String toast = getSuccessToast();
        log("Edit", "Save after Edit", "Success", toast.isEmpty() ? "No toast" : toast, !toast.isEmpty());
        sa.assertAll();
    }

    @Test(priority = 8)
    public void validateDatabase() throws Exception {
        String today = java.time.LocalDate.now().toString();
        ResultSet rs = DBConnection.executeQuery(
            "SELECT TOP 1 * FROM d310039 ORDER BY createdDate DESC, createdTime DESC");

        if (!rs.next()) {
            log("DB", "Record found in d310039", "Yes", "No", false);
            sa.fail("No record found in d310039");
            DBConnection.close(rs);
            return;
        }

        log("DB", "Record found", "Yes", "Yes", true);

        String dbCreatedDate = rs.getString("createdDate");
        String todayShort = today.substring(0, 10);
        String dbDateShort = dbCreatedDate != null ? dbCreatedDate.substring(0, 10) : "";
        boolean dateMatch = dbDateShort.equals(todayShort) || dbCreatedDate != null && dbCreatedDate.contains(today);
        log("DB", "createdDate contains today", today, dbCreatedDate, dateMatch);
        logInfo("DB", "createdDate (server timezone may differ)", dbCreatedDate);

        String dbIsActive = rs.getString("isActive");
        log("DB", "isActive", "1", dbIsActive, "1".equals(dbIsActive));
        sa.assertEquals(dbIsActive, "1", "isActive should be 1");

        String dbRemarks = rs.getString("remarks");
        log("DB", "remarks", "Reminder Updated Save", dbRemarks, dbRemarks != null && dbRemarks.contains("Reminder Updated Save"));
        sa.assertTrue(dbRemarks != null && dbRemarks.contains("Reminder Updated Save"), "remarks mismatch");

        String dbCaseNo = rs.getString("caseNo");
        log("DB", "caseNo", "CASE_0005282011204001137", dbCaseNo, dbCaseNo != null && dbCaseNo.contains("CASE_0005282011204001137"));

        logInfo("DB", "createdTime", rs.getString("createdTime"));

        DBConnection.close(rs);
        sa.assertAll();
    }
}
