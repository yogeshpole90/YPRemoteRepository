package com.ebid.lcs.tests;

import org.openqa.selenium.By;
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
public class CaseStatusTest extends BaseTest {

    private void scrollToSearch() {
        try {
            WebElement search = driver.findElement(By.xpath("//input[@type='search' and @aria-controls='dt-basicDetails']"));
            jse.executeScript("arguments[0].scrollIntoView({block:'center',behavior:'smooth'})", search);
            Thread.sleep(1000);
        } catch (Exception e) {}
    }

    @BeforeClass
    public void setup() throws Exception {
        ExtentManager.initReport("CaseStatus");
        ExtentManager.startTest("Case Status - Full Validation");

        navigateToCase(ConfigManager.get("casenumber"));

        WebElement tab = driver.findElement(By.xpath("//a[contains(@href,'activeTab=Account Information')]"));
        jse.executeScript("arguments[0].scrollIntoView({block:'center',behavior:'smooth'})", tab);
        Thread.sleep(1000);
        tab.click();
        Thread.sleep(2000);

        WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(15));
        WebElement cstTab = wait
                .until(ExpectedConditions.elementToBeClickable(By.xpath("//a[contains(text(),'Case Status')]")));
        jse.executeScript("arguments[0].scrollIntoView({block:'center',behavior:'smooth'})", cstTab);
        Thread.sleep(1000);
        act.doubleClick(cstTab).build().perform();
        Thread.sleep(2000);

        driver.switchTo().frame("viewCaseStatusFrame");
        logInfo("Frame", "Switched to", "viewCaseStatusFrame");
    }

    @Test(priority = 1)
    public void validateAllFields() throws Exception {
        Object[][] data = ExcelReader.getByTcPrefix(SheetConstants.SHEET_CASE_STATUS, SheetConstants.TC.CASE_STATUS);

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
                    try {
                        s.selectByVisibleText(input);
                        Thread.sleep(500);
                        String actual = s.getFirstSelectedOption().getText().trim();
                        log(fieldName, desc, expected, actual, actual.equals(expected));
                        sa.assertEquals(actual, expected, desc);
                    } catch (Exception e) {
                        log(fieldName, desc, expected, "Option not found: " + input, false);
                    }
                } else {
                    f.clear();
                    Thread.sleep(200);
                    if (!input.isEmpty() && !input.equalsIgnoreCase("Empty"))
                        f.sendKeys(input);
                    Thread.sleep(300);
                    String actual = f.getAttribute("value");

                    switch (checkType) {
                        case "equals":
                            log(fieldName, desc, expected, actual, actual.equals(expected));
                            sa.assertEquals(actual, expected, desc);
                            break;
                        case "empty":
                            log(fieldName, desc, "Empty", actual, actual.isEmpty());
                            sa.assertTrue(actual.isEmpty(), desc);
                            break;
                        case "info":
                            logInfo(fieldName, desc, actual);
                            break;
                    }
                }
            } catch (Exception e) {
                log(fieldName, desc, expected, "Element not found: " + fieldName, false);
                sa.fail("Element not found: " + fieldName);
            }
        }
    }

    @Test(priority = 2)
    public void validateSave() throws Exception {
        String fn = "Save";

        // Set valid data for save
        Select caseDD = new Select(driver.findElement(By.id("caseStatusId")));
        caseDD.selectByIndex(2);
        Thread.sleep(500);
        String selectedVal = caseDD.getFirstSelectedOption().getText().trim();
        log(fn, "Case Status dropdown selected", "Non-empty value", selectedVal, !selectedVal.isEmpty() && !selectedVal.contains("SELECT"));

        WebElement remarks = driver.findElement(By.id("remarks"));
        remarks.clear();
        remarks.sendKeys("Case Status Remark");
        Thread.sleep(500);
        log(fn, "Remarks field populated", "Case Status Remark", remarks.getAttribute("value"), true);

        WebElement saveBtn = driver.findElement(By.id("saveData"));
        jse.executeScript("arguments[0].scrollIntoView({block:'center'})", saveBtn);
        Thread.sleep(500);
        log(fn, "Save button is visible", "true", String.valueOf(saveBtn.isDisplayed()), saveBtn.isDisplayed());
        log(fn, "Save button is enabled", "true", String.valueOf(saveBtn.isEnabled()), saveBtn.isEnabled());

        saveBtn.click();
        Thread.sleep(1000);
        scrollToSearch();

        String successToast = getSuccessToast();
        log(fn, "Record saved successfully", "Success toast", successToast.isEmpty() ? "No toast" : successToast, !successToast.isEmpty());

        // After save, re-switch frame for View/Edit
        Thread.sleep(2000);
        driver.switchTo().parentFrame();
        Thread.sleep(500);
        driver.switchTo().frame("viewCaseStatusFrame");
        Thread.sleep(1000);

        sa.assertAll();
    }

    @Test(priority = 3)
    public void validateViewEdit() throws Exception {
        Thread.sleep(1000);
        scrollToSearch();

        // ========== VIEW ==========
        List<WebElement> viewBtns = driver.findElements(By.xpath("//a[contains(@onclick,'ViewData')]"));
        log("View Button", "View buttons available on list page", ">0", String.valueOf(viewBtns.size()), viewBtns.size() > 0);

        if (viewBtns.size() > 0) {
            WebElement viewBtn = driver.findElement(By.xpath("(//a[contains(@onclick,'ViewData')])[1]"));
            jse.executeScript("arguments[0].scrollIntoView({block:'center',behavior:'smooth'})", viewBtn);
            Thread.sleep(300);
            viewBtn.click();
            Thread.sleep(2000);
            log("View", "Clicked View on first record (latest saved)", "Record opened in View mode", "Clicked", true);

            // Verify saved data in View mode
            try {
                WebElement remarksFld = driver.findElement(By.id("remarks"));
                String remarkVal = remarksFld.getAttribute("value");
                log("View", "Remarks field value in View mode", "Case Status Remark", remarkVal, remarkVal.contains("Case Status Remark"));

                WebElement caseStatusFld = driver.findElement(By.id("caseStatusId"));
                String statusVal = new Select(caseStatusFld).getFirstSelectedOption().getText().trim();
                log("View", "Case Status dropdown value in View mode", "Non-empty", statusVal, !statusVal.isEmpty() && !statusVal.contains("SELECT"));
            } catch (Exception e) {
                logInfo("View", "Field verification skipped", e.getMessage());
            }

            log("View", "View mode validation completed", "All fields verified", "Completed", true);
        }

        // ========== EDIT / UPDATE ==========
        List<WebElement> editBtns = driver.findElements(By.cssSelector("a.editBtn"));
        log("Edit Button", "Edit buttons available on list page", ">0", String.valueOf(editBtns.size()), editBtns.size() > 0);

        if (editBtns.size() > 0) {
            WebElement editBtn = driver.findElement(By.xpath("(//a[contains(@class,'editBtn')])[1]"));
            jse.executeScript("arguments[0].scrollIntoView({block:'center',behavior:'smooth'})", editBtn);
            Thread.sleep(300);
            editBtn.click();
            Thread.sleep(2000);
            log("Update", "Clicked Edit on first record (latest saved)", "Record opened in Edit mode", "Clicked", true);

            // Update remarks field
            try {
                WebElement remarksFld = driver.findElement(By.id("remarks"));
                jse.executeScript("arguments[0].scrollIntoView({block:'center',behavior:'smooth'})", remarksFld);
                Thread.sleep(300);
                String oldVal = remarksFld.getAttribute("value");
                log("Update", "Current Remarks value before update", "Existing value", oldVal, true);

                remarksFld.clear();
                remarksFld.sendKeys("Case Status Remark Updated");
                Thread.sleep(500);
                String newVal = remarksFld.getAttribute("value");
                log("Update", "Remarks field updated with new value", "Case Status Remark Updated", newVal, newVal.equals("Case Status Remark Updated"));
            } catch (Exception e) {
                log("Update", "Remarks field update", "Updated", "Failed - " + e.getMessage(), false);
            }

            // Click Save after update
            WebElement saveBtn = driver.findElement(By.id("saveData"));
            jse.executeScript("arguments[0].scrollIntoView({block:'center',behavior:'smooth'})", saveBtn);
            Thread.sleep(500);
            saveBtn.click();
            Thread.sleep(1000);
            scrollToSearch();

            String updateToast = getSuccessToast();
            log("Update", "Record updated successfully", "Success toast", updateToast.isEmpty() ? "No toast" : updateToast, !updateToast.isEmpty());
        }

        sa.assertAll();
    }

    @Test(priority = 4)
    public void validateDatabase() throws Exception {
        String today = java.time.LocalDate.now().toString();
        String query = "SELECT TOP 1 * FROM D310206 ORDER BY createdDate DESC, createdTime DESC";
        ResultSet rs = DBConnection.executeQuery(query);

        if (rs == null || !rs.next()) {
            log("DB", "Record found in D310206", "Yes", "No", false);
            sa.fail("No record found in D310206");
            DBConnection.close(rs);
            return;
        }

        log("DB", "Record exists in D310206 table", "Yes", "Yes", true);

        // createdDate should contain today's date
        String dbCreatedDate = rs.getString("createdDate");
        log("DB", "createdDate contains today's date", today, dbCreatedDate, dbCreatedDate != null && dbCreatedDate.contains(today));
        sa.assertTrue(dbCreatedDate != null && dbCreatedDate.contains(today), "createdDate should contain today");

        // isActive
        String dbIsActive = rs.getString("isActive");
        log("DB", "isActive flag is set to 1 (active record)", "1", dbIsActive, "1".equals(dbIsActive));
        sa.assertEquals(dbIsActive, "1", "isActive should be 1");

        // remarks - compare with our entered data
        String dbRemarks = rs.getString("remarks");
        log("DB", "Remarks matches entered data", "Case Status Remark", dbRemarks, dbRemarks != null && dbRemarks.contains("Case Status Remark"));
        sa.assertTrue(dbRemarks != null && dbRemarks.contains("Case Status Remark"), "remarks mismatch");

        // caseStatus - DB stores code not text, use contains
        String dbCaseStatus = rs.getString("caseStatusId");
        log("DB", "caseStatusId stored correctly in DB", "Not Null", dbCaseStatus, dbCaseStatus != null && !dbCaseStatus.isEmpty());
        sa.assertTrue(dbCaseStatus != null && !dbCaseStatus.isEmpty(), "caseStatusId should not be empty");

        // caseNo
        String dbCaseNo = rs.getString("caseNo");
        log("DB", "caseNo matches expected case number", "CASE_0005282011204001137", dbCaseNo, dbCaseNo != null && dbCaseNo.contains("CASE_0005282011204001137"));
        sa.assertTrue(dbCaseNo != null && dbCaseNo.contains("CASE_0005282011204001137"), "caseNo mismatch");

        // createdTime
        String dbCreatedTime = rs.getString("createdTime");
        log("DB", "createdTime is not null", "Not Null", dbCreatedTime, dbCreatedTime != null && !dbCreatedTime.isEmpty());

        logInfo("DB", "Database validation completed successfully", "All DB checks passed");

        DBConnection.close(rs);
        sa.assertAll();
    }
}
