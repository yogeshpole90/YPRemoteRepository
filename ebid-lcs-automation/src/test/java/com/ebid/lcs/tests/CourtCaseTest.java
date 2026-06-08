package com.ebid.lcs.tests;

import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.Keys;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.ui.Select;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Listeners;
import org.testng.annotations.Test;

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
public class CourtCaseTest extends BaseTest {

    private void dismissAlert() {
        try 
        {
        	driver.switchTo().alert().accept(); 
        } 
        catch (Exception e)
        {
        	
        }
    }

    private void hideDatepicker() {
        try {
            jse.executeScript(
                "var dp = document.querySelectorAll('.datepicker, .ui-datepicker, .daterangepicker, .bootstrap-datetimepicker-widget');" +
                "dp.forEach(function(el){ el.style.display='none'; });"
            );
        } catch (Exception e) {}
        try { driver.findElement(By.tagName("body")).sendKeys(Keys.ESCAPE); } catch (Exception e) {}
    }

    private void scrollToSearch() {
        try {
            WebElement search = driver.findElement(By.xpath("//input[@type='search' and @aria-controls='dt-basicDetails']"));
            jse.executeScript("arguments[0].scrollIntoView({block:'center',behavior:'smooth'})", search);
            Thread.sleep(1000);
        } catch (Exception e) {}
    }

    @BeforeClass(alwaysRun = true)
    public void setup() throws Exception {
        driver = DriverManager.getDriver();
        jse = (JavascriptExecutor) driver;
        act = new Actions(driver);

        ExtentManager.initReport("CourtCase");
        ExtentManager.startTest("Court Case - Full Validation");

        navigateToCase(ConfigManager.get("casenumber"));

        WebElement legal = driver.findElement(By.xpath("//*[contains(@href,'=Legal Process')]"));
        jse.executeScript("arguments[0].scrollIntoView({block:'center',behavior:'smooth'})", legal);
        Thread.sleep(1000);
        jse.executeScript("arguments[0].click()", legal);
        Thread.sleep(2000);

        // Switch to court case frame
        driver.switchTo().frame("courtCaseMstListPageFrame");
        logInfo("Frame", "Switched to", "courtCaseMstListPageFrame");
    }

    @Test(priority = 1 , groups = {"sanity"})
    public void validateAllFields() throws Exception {
        Object[][] data = ExcelReader.getByTcPrefix(SheetConstants.SHEET_COURT_CASE, SheetConstants.TC.COURT_CASE);

        for (Object[] row : data) {
            String fieldName = row[SheetConstants.Cols.FIELD_NAME].toString().trim();
            String input = row[SheetConstants.Cols.INPUT].toString().trim();
            String expected = row[SheetConstants.Cols.EXPECTED].toString().trim();
            String desc = row[SheetConstants.Cols.DESCRIPTION].toString();
            String checkType = row[SheetConstants.Cols.CHECK_TYPE].toString().trim();

            dismissAlert(); hideDatepicker();

            WebElement f;
            try { f = driver.findElement(By.id(fieldName)); } catch (Exception e) {
                dismissAlert();
                try { f = driver.findElement(By.id(fieldName)); } catch (Exception e2) {
                    log(fieldName, desc, expected, "Element not found: " + fieldName, false);
                    continue;
                }
            }

            jse.executeScript("arguments[0].scrollIntoView({block:'center'})", f);
            Thread.sleep(200);
            String tagName = f.getTagName();

            if (tagName.equals("select")) {
                Select s = new Select(f);
                try {
                    s.selectByVisibleText(input);
                    Thread.sleep(500);
                    String actual = s.getFirstSelectedOption().getText().trim();
                    if (checkType.equals("info")) {
                        logInfo(fieldName, desc, actual);
                    } else {
                        log(fieldName, desc, expected, actual, actual.equals(expected));
                        sa.assertEquals(actual, expected, desc);
                    }
                } catch (Exception e) {
                    log(fieldName, desc, expected, "Option not found: " + input, false);
                }
            } else {
                // Check if field is date type or amount (use JS for suitAmount_txt)
                String fieldId = fieldName.toLowerCase();
                if (fieldId.contains("suitamount")) {
                    // Amount field — clear and sendKeys (JS causes format mismatch)
                    f.clear();
                    Thread.sleep(200);
                    if (!input.isEmpty() && !input.equalsIgnoreCase("Empty")) f.sendKeys(input);
                    f.sendKeys(Keys.TAB);
                    try { Thread.sleep(300); } catch (Exception e) {}
                    dismissAlert();
                } else if (fieldId.contains("date")) {
                    // Date field — sendKeys + Tab + hideDatepicker
                    f.clear();
                    if (!input.isEmpty() && !input.equalsIgnoreCase("Empty")) f.sendKeys(input);
                    f.sendKeys(Keys.TAB);
                    hideDatepicker();
                    try { Thread.sleep(300); } catch (Exception e) {}
                    dismissAlert();
                } else {
                    // Normal text field
                    try { f.clear(); } catch (Exception e) { dismissAlert(); f.clear(); }
                    dismissAlert();
                    if (!input.isEmpty() && !input.equalsIgnoreCase("Empty")) {
                        f.sendKeys(input);
                        try { Thread.sleep(200); } catch (Exception e) {}
                        dismissAlert();
                    }
                }

                String actual;
                try { actual = f.getAttribute("value"); } catch (Exception e) { dismissAlert(); actual = f.getAttribute("value"); }

                switch (checkType) {
                    case "equals": log(fieldName, desc, expected, actual, actual.equals(expected)); sa.assertEquals(actual, expected, desc); break;
                    case "notEquals": log(fieldName, desc, "Not " + input, actual, !actual.equals(input)); break;
                    case "empty": log(fieldName, desc, "Empty", actual, actual.isEmpty()); sa.assertTrue(actual.isEmpty(), desc); break;
                    case "info": logInfo(fieldName, desc, actual); break;
                }
            }
        }
    }

    @Test(priority = 2,groups = {"sanity"})
    public void validateSave() throws Exception {
        dismissAlert();
        WebElement saveBtn = driver.findElement(By.xpath("//button[@id='save']"));
        jse.executeScript("arguments[0].scrollIntoView({block:'center'})", saveBtn);
        Thread.sleep(500);
        saveBtn.click();
        Thread.sleep(1000);
        dismissAlert();

        // Scroll to search for toast capture
        scrollToSearch();
        String toast = getSuccessToast();
        log("Save", "Save Court Case", "Success", toast.isEmpty() ? "No toast" : toast, !toast.isEmpty());

        // Back to table frame for View/Edit/Delete
        Thread.sleep(2000);
        driver.switchTo().parentFrame();
        WebElement legalTab = driver.findElement(By.xpath("//*[contains(@href,'=Legal Process')]"));
        jse.executeScript("arguments[0].scrollIntoView({block:'center'})", legalTab);
        driver.switchTo().frame("courtCaseMstListPageFrame");
        Thread.sleep(1000);

        // View
        List<WebElement> viewBtns = driver.findElements(By.cssSelector("a.ViewBtn"));
        log("View Button", "View buttons found", ">0", String.valueOf(viewBtns.size()), viewBtns.size() > 0);
        if (viewBtns.size() > 0) {
            driver.findElement(By.xpath("(//a[contains(@class,'ViewBtn')])[last()]")).click();
            Thread.sleep(2000);
            log("View Button", "Click last View", "Opened", "Clicked", true);

            // Back to table
            driver.switchTo().parentFrame();
            jse.executeScript("arguments[0].scrollIntoView({block:'center'})", legalTab);
            driver.switchTo().frame("courtCaseMstListPageFrame");
            Thread.sleep(1000);
        }

        // Edit
        List<WebElement> editBtns = driver.findElements(By.cssSelector("a.editBtn"));
        log("Edit Button", "Edit buttons found", ">0", String.valueOf(editBtns.size()), editBtns.size() > 0);
        if (editBtns.size() > 0) {
            driver.findElement(By.xpath("(//a[contains(@class,'editBtn')])[last()]")).click();
            Thread.sleep(2000);
            log("Edit Button", "Click last Edit", "Opened", "Clicked", true);

            // Modify suit amount and save
            try {
                WebElement suitAmt = driver.findElement(By.id("suitAmount_txt"));
                jse.executeScript("arguments[0].scrollIntoView({block:'center'})", suitAmt);
                jse.executeScript("arguments[0].value='99999'", suitAmt);
                log("Edit", "Modify Suit Amount", "99999", suitAmt.getAttribute("value"), true);
            } catch (Exception e) {}

            WebElement updateBtn = driver.findElement(By.xpath("//button[@id='save']"));
            jse.executeScript("arguments[0].scrollIntoView({block:'center'})", updateBtn);
            Thread.sleep(500);
            updateBtn.click(); Thread.sleep(1000); dismissAlert();
            scrollToSearch();
            String updateToast = getSuccessToast();
            log("Update", "Update Court Case", "Success", updateToast.isEmpty() ? "No toast" : updateToast, !updateToast.isEmpty());

            // Back to table
            Thread.sleep(2000);
            driver.switchTo().parentFrame();
            jse.executeScript("arguments[0].scrollIntoView({block:'center'})", legalTab);
            driver.switchTo().frame("courtCaseMstListPageFrame");
            Thread.sleep(1000);
        }

        // Delete
        List<WebElement> deleteBtns = driver.findElements(By.cssSelector("a.deleteBtn"));
        log("Delete Button", "Delete buttons found", ">0", String.valueOf(deleteBtns.size()), deleteBtns.size() > 0);
        if (deleteBtns.size() > 0) {
            driver.findElement(By.xpath("(//a[contains(@class,'deleteBtn')])[last()]")).click();
            Thread.sleep(1000);
            log("Delete Button", "Click last Delete", "Popup", "Clicked", true);
            try {
                driver.findElement(By.id("popUpYes")).click(); Thread.sleep(1000);
                scrollToSearch();
                String deleteToast = getSuccessToast();
                log("Delete", "Delete Court Case", "Success", deleteToast.isEmpty() ? "No toast" : deleteToast, !deleteToast.isEmpty());
            } catch (Exception e) {
                dismissAlert();
                log("Delete", "Confirm delete", "Deleted", "Alert/Popup handled", true);
            }
        }

        sa.assertAll();
    }

    @Test(priority = 3, groups = {"sanity"})
    public void validateDatabase() throws Exception {
        String today = java.time.LocalDate.now().toString();
        ResultSet rs = DBConnection.executeQuery(
            "SELECT TOP 1 * FROM d310050 ORDER BY createdDate DESC, createdTime DESC");

        if (!rs.next()) {
            log("DB", "Record found in d310050", "Yes", "No", false);
            sa.fail("No record found in d310050");
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

        String dbCaseNo = rs.getString("caseNo");
        log("DB", "caseNo", "CASE_0005282011204001137", dbCaseNo, dbCaseNo != null && dbCaseNo.contains("CASE_0005282011204001137"));

        String dbCourtName = rs.getString("courtName");
        log("DB", "courtName", "Not Null", dbCourtName, dbCourtName != null && !dbCourtName.isEmpty());

        String dbSuitAmt = rs.getString("suitAmount");
        log("DB", "suitAmount", "Not Null", dbSuitAmt, dbSuitAmt != null && !dbSuitAmt.isEmpty());

        logInfo("DB", "createdTime", rs.getString("createdTime"));

        DBConnection.close(rs);
        sa.assertAll();
    }
}
