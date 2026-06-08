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
public class LegalDiaryTest extends BaseTest {

    private String selectedCaseRefNo = "";

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
        try { driver.findElement(By.tagName("body")).sendKeys(Keys.ESCAPE); } catch (Exception e) {}
    }

    @BeforeClass
    public void setup() throws Exception {
        ExtentManager.initReport("LegalDiary");
        ExtentManager.startTest("Legal Diary - Full Validation");

        navigateToCase(ConfigManager.get("casenumber"));

        WebElement legal = driver.findElement(By.xpath("//*[contains(@href,'=Legal Process')]"));
        jse.executeScript("arguments[0].scrollIntoView({block:'center',behavior:'smooth'})", legal);
        Thread.sleep(1000);
        jse.executeScript("arguments[0].click()", legal);
        Thread.sleep(2000);

        WebElement ldTab = driver.findElement(By.xpath("//a[contains(text(),'Legal Diary')]"));
        jse.executeScript("arguments[0].scrollIntoView({block:'center'})", ldTab);
        Thread.sleep(500);
        act.doubleClick(ldTab).build().perform();
        Thread.sleep(2000);

        driver.switchTo().frame("getLegalDiaryDataFrame");
        logInfo("Frame", "Switched to", "getLegalDiaryDataFrame");
    }

    @Test(priority = 1)
    public void validateCourtCaseRef() throws Exception {
        WebElement caseRef = driver.findElement(By.id("courtCaseNo"));
        jse.executeScript("arguments[0].scrollIntoView({block:'center'})", caseRef);
        Select caseRefSelect = new Select(caseRef);
        List<WebElement> opts = caseRefSelect.getOptions();

        log("courtCaseNo", "Visible", "true", String.valueOf(caseRef.isDisplayed()), caseRef.isDisplayed());
        log("courtCaseNo", "Options count", ">1", String.valueOf(opts.size()), opts.size() > 1);

        if (opts.size() > 1) {
            caseRefSelect.selectByIndex(opts.size() - 1);
            Thread.sleep(1000);
            dismissAlert();
            selectedCaseRefNo = caseRefSelect.getFirstSelectedOption().getText().trim();
            log("courtCaseNo", "Select latest ref no", "Selected", selectedCaseRefNo, !selectedCaseRefNo.isEmpty());
            logInfo("courtCaseNo", "Selected Ref No", selectedCaseRefNo);
        }

        verifyAutoPopulated("courtCaseType", "Court Case Type");
        verifyAutoPopulated("currency", "Currency");
        verifyAutoPopulated("suitAmount_txt", "Suit Amount");
        verifyAutoPopulated("lawFirmName", "Law Firm Name");
        verifyAutoPopulated("caseInitiatedby", "Case Initiated By");
    }

    @Test(priority = 2)
    public void validateAllFields() throws Exception {
        Object[][] data = ExcelReader.getByTcPrefix(SheetConstants.SHEET_LEGAL_DIARY, SheetConstants.TC.LEGAL_DIARY);

        for (Object[] row : data) {
            String fieldName = row[SheetConstants.Cols.FIELD_NAME].toString().trim();
            String input = row[SheetConstants.Cols.INPUT].toString().trim();
            String expected = row[SheetConstants.Cols.EXPECTED].toString().trim();
            String desc = row[SheetConstants.Cols.DESCRIPTION].toString();
            String checkType = row[SheetConstants.Cols.CHECK_TYPE].toString().trim();

            dismissAlert();
            hideDatepicker();

            List<WebElement> elements = driver.findElements(By.id(fieldName));
            if (elements.isEmpty()) {
                log(fieldName, desc, expected, "Element not found: " + fieldName, false);
                continue;
            }

            WebElement f = elements.get(0);
            jse.executeScript("arguments[0].scrollIntoView({block:'center'})", f);
            Thread.sleep(200);

            String tagName = f.getTagName();
            if (f.getAttribute("disabled") != null) {
                logInfo(fieldName, desc + " (Disabled/Auto)", "");
                continue;
            }

            if (tagName.equals("select")) {
                Select s = new Select(f);
                if (checkType.equals("index")) {
                    int idx = Integer.parseInt(input);
                    s.selectByIndex(idx);
                    Thread.sleep(300);
                    dismissAlert();
                    String actual = s.getFirstSelectedOption().getText().trim();
                    log(fieldName, desc, "Index " + idx, actual, !actual.isEmpty() && !actual.contains("SELECT"));
                } else {
                    try {
                        s.selectByVisibleText(input);
                        Thread.sleep(300);
                        dismissAlert();
                        String actual = s.getFirstSelectedOption().getText().trim();
                        log(fieldName, desc, expected, actual, actual.equals(expected));
                        sa.assertEquals(actual, expected, desc);
                    } catch (Exception e) {
                        log(fieldName, desc, expected, "Option not found: " + input, false);
                    }
                }
            } else {
                boolean isDateField = fieldName.toLowerCase().contains("date") || fieldName.toLowerCase().contains("hearing");

                if (isDateField || f.getAttribute("readonly") != null) {
                    jse.executeScript("arguments[0].value='" + (input.equalsIgnoreCase("Empty") ? "" : input) + "'", f);
                    jse.executeScript("arguments[0].dispatchEvent(new Event('change', {bubbles:true}));", f);
                    hideDatepicker();
                    dismissAlert();
                } else {
                    try { f.clear(); } catch (Exception e) { jse.executeScript("arguments[0].value=''", f); }
                    dismissAlert();
                    if (!input.isEmpty() && !input.equalsIgnoreCase("Empty")) {
                        try { f.sendKeys(input); } catch (Exception e) { jse.executeScript("arguments[0].value='" + input + "'", f); }
                        dismissAlert();
                    }
                }

                String actual = f.getAttribute("value");
                switch (checkType) {
                    case "equals": log(fieldName, desc, expected, actual, actual.equals(expected)); sa.assertEquals(actual, expected, desc); break;
                    case "notEquals": log(fieldName, desc, "Not " + input, actual, !actual.equals(input)); break;
                    case "empty": log(fieldName, desc, "Empty", actual, actual.isEmpty()); sa.assertTrue(actual.isEmpty(), desc); break;
                    case "info": logInfo(fieldName, desc, actual); break;
                }
            }
        }
    }

    @Test(priority = 3)
    public void validateSave() throws Exception {
        dismissAlert();

        // Upload document from config
        try {
            String docPath = ConfigManager.get("doc.upload.path");
            WebElement fileInput = driver.findElement(By.id("documentData"));
            fileInput.sendKeys(docPath);
            Thread.sleep(2000);
            log("File Upload", "Upload document", "File selected", fileInput.getAttribute("value").isEmpty() ? "No file" : "Uploaded", !fileInput.getAttribute("value").isEmpty());
        } catch (Exception e) {
            logInfo("File Upload", "Upload skipped", e.getMessage());
        }

        WebElement saveBtn = driver.findElement(By.id("saveData"));
        jse.executeScript("arguments[0].scrollIntoView({block:'center'})", saveBtn);
        Thread.sleep(500);
        log("Save Button", "Visible", "true", String.valueOf(saveBtn.isDisplayed()), saveBtn.isDisplayed());
        saveBtn.click();
        Thread.sleep(3000);
        dismissAlert();

        String toast = getSuccessToast();
        log("Save", "Save Legal Diary", "Success", toast.isEmpty() ? "No toast" : toast, !toast.isEmpty());
        sa.assertAll();
    }

    @Test(priority = 4)
    public void validateView() throws Exception {
        Thread.sleep(1000);
        List<WebElement> viewBtns = driver.findElements(By.xpath("//a[contains(@class,'ViewBtn') or contains(@onclick,'viewLegalDiary')]"));
        log("View Button", "Found", ">0", String.valueOf(viewBtns.size()), viewBtns.size() > 0);

        if (!viewBtns.isEmpty()) {
            WebElement lastView = viewBtns.get(viewBtns.size() - 1);
            jse.executeScript("arguments[0].scrollIntoView({block:'center'})", lastView);
            Thread.sleep(300);
            jse.executeScript("arguments[0].click()", lastView);
            Thread.sleep(2000);
            log("View", "Click View (last record)", "Opened", "Clicked", true);

            // Validate final values in view mode - jo enter kiya wo hi dikh raha hai
            Object[][] data = ExcelReader.getByTcPrefix(SheetConstants.SHEET_LEGAL_DIARY, SheetConstants.TC.LEGAL_DIARY);
            for (Object[] row : data) {
                String fieldName = row[SheetConstants.Cols.FIELD_NAME].toString().trim();
                String expected = row[SheetConstants.Cols.EXPECTED].toString().trim();
                String desc = row[SheetConstants.Cols.DESCRIPTION].toString();
                String checkType = row[SheetConstants.Cols.CHECK_TYPE].toString().trim();

                // Only validate final rows
                if (!checkType.equals("equals") || !desc.toLowerCase().contains("final")) continue;
                if (expected.isEmpty()) continue;

                try {
                    WebElement f = driver.findElement(By.id(fieldName));
                    jse.executeScript("arguments[0].scrollIntoView({block:'center'})", f);
                    String actual = f.getTagName().equals("select")
                            ? new Select(f).getFirstSelectedOption().getText().trim()
                            : f.getAttribute("value");
                    boolean pass = actual.equals(expected) || actual.contains(expected);
                    log(fieldName, "View - " + desc, expected, actual, pass);
                } catch (Exception e) {
                    logInfo(fieldName, "View - not found", fieldName);
                }
            }

            // Close view
            try {
                WebElement closeBtn = driver.findElement(By.xpath("//button[contains(@class,'close') or @id='closeBtn' or contains(@onclick,'close')]"));
                jse.executeScript("arguments[0].click()", closeBtn);
                Thread.sleep(1000);
            } catch (Exception e) {
                logInfo("View", "Close button", "Not found - continuing");
            }
            log("View", "View data validation done", "Completed", "Done", true);
        }
    }

    @Test(priority = 5)
    public void validateEdit() throws Exception {
        Thread.sleep(1000);
        List<WebElement> editBtns = driver.findElements(By.xpath("//a[contains(@class,'editBtn') or contains(@onclick,'editLegalDiary')]"));
        log("Edit Button", "Found", ">0", String.valueOf(editBtns.size()), editBtns.size() > 0);

        if (!editBtns.isEmpty()) {
            WebElement lastEdit = editBtns.get(editBtns.size() - 1);
            jse.executeScript("arguments[0].scrollIntoView({block:'center'})", lastEdit);
            Thread.sleep(300);
            jse.executeScript("arguments[0].click()", lastEdit);
            Thread.sleep(2000);
            log("Edit", "Click Edit (last record)", "Opened", "Clicked", true);

            WebElement saveBtn = driver.findElement(By.id("saveData"));
            jse.executeScript("arguments[0].scrollIntoView({block:'center'})", saveBtn);
            Thread.sleep(300);
            saveBtn.click();
            Thread.sleep(2000);
            dismissAlert();
            String toast = getSuccessToast();
            log("Edit", "Save after Edit", "Success", toast.isEmpty() ? "No toast" : toast, !toast.isEmpty());
        }
        sa.assertAll();
    }

    @Test(priority = 6)
    public void validateDatabase() throws Exception {
        String today = java.time.LocalDate.now().toString();
        ResultSet rs = DBConnection.executeQuery(
            "SELECT TOP 1 * FROM d310038 ORDER BY createdDate DESC, createdTime DESC");

        if (!rs.next()) {
            log("DB", "Record found in d310038", "Yes", "No", false);
            sa.fail("No record found in d310038");
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

        String dbCourtCaseNo = rs.getString("courtCaseNo");
        log("DB", "courtCaseNo", selectedCaseRefNo, dbCourtCaseNo, dbCourtCaseNo != null && !dbCourtCaseNo.isEmpty());

        String dbRemarks = rs.getString("remarks");
        log("DB", "remarks", "Legal diary validation save", dbRemarks, dbRemarks != null && dbRemarks.contains("Legal diary validation save"));

        logInfo("DB", "createdTime", rs.getString("createdTime"));

        DBConnection.close(rs);
        sa.assertAll();
    }

    @Test(priority = 7)
    public void validateDelete() throws Exception {
        Thread.sleep(1000);
        List<WebElement> deleteBtns = driver.findElements(By.xpath("//a[contains(@class,'deleteBtn') or contains(@onclick,'deleteLegalDiary')]"));
        log("Delete Button", "Found", ">0", String.valueOf(deleteBtns.size()), deleteBtns.size() > 0);

        if (!deleteBtns.isEmpty()) {
            WebElement lastDelete = deleteBtns.get(deleteBtns.size() - 1);
            jse.executeScript("arguments[0].scrollIntoView({block:'center'})", lastDelete);
            Thread.sleep(300);
            jse.executeScript("arguments[0].click()", lastDelete);
            Thread.sleep(1000);
            log("Delete", "Click Delete (last record)", "Triggered", "Clicked", true);

            // Handle confirmation
            try {
                driver.switchTo().alert().accept();
                Thread.sleep(1000);
            } catch (Exception e) {
                try {
                    driver.findElement(By.id("popUpYes")).click();
                    Thread.sleep(1000);
                    log("Delete", "Click Yes popup", "Deleted", "Yes clicked", true);
                } catch (Exception e2) {}
            }

            String deleteToast = getSuccessToast();
            log("Delete", "Delete Legal Diary", "Success", deleteToast.isEmpty() ? "No toast" : deleteToast, !deleteToast.isEmpty());
        }

        sa.assertAll();
    }

    private void verifyAutoPopulated(String id, String fieldName) {
        try {
            List<WebElement> els = driver.findElements(By.id(id));
            if (els.isEmpty()) { logInfo(fieldName, "Auto-populate check", "Element not found"); return; }
            WebElement f = els.get(0);
            jse.executeScript("arguments[0].scrollIntoView({block:'center'})", f);
            String val;
            if (f.getTagName().equals("select")) {
                val = (String) jse.executeScript("var s=arguments[0]; return s.options[s.selectedIndex]?s.options[s.selectedIndex].text:'';", f);
            } else {
                val = f.getAttribute("value");
            }
            boolean hasData = val != null && !val.trim().isEmpty() && !val.contains("SELECT") && !val.equalsIgnoreCase("Select");
            log(fieldName, "Auto-populated after Ref No select", "Non-empty", val != null ? val : "null", hasData);
        } catch (Exception e) {
            logInfo(fieldName, "Auto-populate check", "Error: " + e.getMessage());
        }
    }
}
