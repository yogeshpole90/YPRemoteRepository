package com.ebid.lcs.tests;

import org.openqa.selenium.Keys;
import org.openqa.selenium.WebElement;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Listeners;
import org.testng.annotations.Test;

import com.ebid.lcs.base.BaseTest;
import com.ebid.lcs.config.ConfigManager;
import com.ebid.lcs.excel.ExcelReader;
import com.ebid.lcs.excel.SheetConstants;
import com.ebid.lcs.listeners.TestListener;
import com.ebid.lcs.pages.UserCreationPage;
import com.ebid.lcs.reporting.ExtentManager;

@Listeners(TestListener.class)
public class UserCreationTest extends BaseTest {

    private UserCreationPage ucPage;

    @BeforeClass
    public void setup() throws Exception {
        ExtentManager.initReport("UserCreation");
        ExtentManager.startTest("User Creation - Full Validation");

        ucPage = new UserCreationPage(driver);
        ucPage.navigateToUserMaster();
        ucPage.clickAdd();

        logInfo("Setup", "User Creation Page Opened", "Create Mode");
    }

    @Test(priority = 1)
    public void validateAllFields() throws Exception {
        Object[][] data = ExcelReader.getByTcPrefix(SheetConstants.SHEET_USER_CREATION, SheetConstants.TC.USER_CREATION);

        for (Object[] row : data) {
            String tcId = row[SheetConstants.Cols.TC_ID].toString().trim();
            String fieldName = row[SheetConstants.Cols.FIELD_NAME].toString().trim();
            String input = row[SheetConstants.Cols.INPUT].toString().trim();
            String expected = row[SheetConstants.Cols.EXPECTED].toString().trim();
            String desc = row[SheetConstants.Cols.DESCRIPTION].toString().trim();
            String checkType = row[SheetConstants.Cols.CHECK_TYPE].toString().trim();

            try {
                // Select2 dropdowns (Employee ID, Branch)
                if (fieldName.equals("select2-employeeId-container")) {
                    handleSelect2(ucPage.getEmpIdContainer(), fieldName, input, expected, desc, checkType);
                } else if (fieldName.equals("select2-assignedBranch-container")) {
                    handleSelect2(ucPage.getBranchContainer(), fieldName, input, expected, desc, checkType);
                } else {
                    WebElement f = ucPage.getField(fieldName);
                    jse.executeScript("arguments[0].scrollIntoView({block:'center'})", f);
                    Thread.sleep(300);

                    if (f.getTagName().equals("select")) {
                        handleDropdown(f, fieldName, input, expected, desc, checkType);
                    } else {
                        handleInput(f, fieldName, input, expected, desc, checkType);
                    }
                }
            } catch (Exception e) {
                log(fieldName, desc, expected, "Element not found: " + fieldName, false);
                sa.fail(tcId + " | Element not found: " + fieldName);
            }
        }
    }

    private void handleSelect2(WebElement container, String fieldName, String input, String expected, String desc, String checkType) throws Exception {
        if (checkType.equals("info")) {
            logInfo(fieldName, desc, ucPage.getSelect2Value(container));
        } else if (fieldName.equals("select2-employeeId-container")) {
            // Use generated empId from EmployeeMasterTest
            String generatedEmpId = ConfigManager.get("generated.empId");
            if (generatedEmpId != null && !generatedEmpId.isEmpty()) {
                ucPage.selectFromSelect2(container, generatedEmpId);
                String actual = ucPage.getSelect2Value(container);
                log(fieldName, desc, generatedEmpId, actual != null ? actual : "null", 
                        actual != null && actual.contains(generatedEmpId));
            } else {
                // Fallback - select first available
                String actual = ucPage.selectEmployeeId();
                boolean pass = !actual.contains("DUPLICATE") && !actual.equals("NOT FOUND");
                log(fieldName, desc, "Auto-selected", actual, pass);
            }
        } else {
            ucPage.selectFromSelect2(container, input);
            String actual = ucPage.getSelect2Value(container);
            log(fieldName, desc, expected, actual != null ? actual : "null", actual != null && actual.contains(expected));
            sa.assertTrue(actual != null && actual.contains(expected), desc);
        }
    }

    private void handleDropdown(WebElement f, String fieldName, String input, String expected, String desc, String checkType) throws Exception {
        if (checkType.equals("info")) {
            logInfo(fieldName, desc, ucPage.getSelectedText(f));
        } else if (checkType.equals("readOnly")) {
            String actual = ucPage.isReadOnly(f) ? ucPage.getSelectedText(f) : "Editable";
            logInfo(fieldName, desc, actual);
        } else if (checkType.equals("index")) {
            org.openqa.selenium.support.ui.Select s = new org.openqa.selenium.support.ui.Select(f);
            int idx = Integer.parseInt(input);
            s.selectByIndex(idx);
            Thread.sleep(500);
            String actual = s.getFirstSelectedOption().getText().trim();
            log(fieldName, desc, "Index " + idx, actual, !actual.isEmpty() && !actual.contains("Select"));
        } else {
            try {
                ucPage.selectDropdown(f, input);
                String actual = ucPage.getSelectedText(f);
                log(fieldName, desc, expected, actual, actual.equals(expected));
                sa.assertEquals(actual, expected, desc);
            } catch (Exception e) {
                log(fieldName, desc, expected, "Option not found: " + input, false);
            }
        }
    }

    private void handleInput(WebElement f, String fieldName, String input, String expected, String desc, String checkType) throws Exception {
        if (checkType.equals("readOnly")) {
            String actual = ucPage.getValue(f);
            logInfo(fieldName, desc, actual.isEmpty() ? "Empty" : actual);
            return;
        }

        // Radio button
        if (f.getAttribute("type") != null && f.getAttribute("type").equals("radio")) {
            jse.executeScript("arguments[0].click()", f);
            Thread.sleep(300);
            log(fieldName, desc, "Selected", String.valueOf(f.isSelected()), f.isSelected());
            return;
        }

        // File upload
        if (f.getAttribute("type") != null && f.getAttribute("type").equals("file")) {
            f.sendKeys(input);
            Thread.sleep(1000);
            log(fieldName, desc, "File uploaded", f.getAttribute("value").isEmpty() ? "No file" : "Uploaded", !f.getAttribute("value").isEmpty());
            return;
        }

        ucPage.enterText(f, input);
        String actual = ucPage.getValue(f);

        switch (checkType) {
            case "equals":
                log(fieldName, desc, expected, actual, actual.equals(expected));
                sa.assertEquals(actual, expected, desc);
                break;
            case "notEquals":
                log(fieldName, desc, "Not " + input, actual, !actual.equals(input));
                sa.assertNotEquals(actual, input, desc);
                break;
            case "empty":
                log(fieldName, desc, "Empty", actual, actual.trim().isEmpty());
                sa.assertTrue(actual.trim().isEmpty(), desc);
                break;
            case "toast":
                String toast = getErrorToast();
                log(fieldName, desc, "Error toast", toast.isEmpty() ? actual : toast, !toast.isEmpty() || actual.trim().isEmpty());
                f.clear();
                f.sendKeys(Keys.ESCAPE);
                Thread.sleep(200);
                break;
            case "tab":
                ucPage.pressTab(f);
                Thread.sleep(2000);
                logInfo(fieldName, desc, "Tab pressed");
                break;
            case "info":
                logInfo(fieldName, desc, actual);
                break;
        }
    }

    @Test(priority = 2)
    public void validateSave() throws Exception {
        // Hardcoded: HNW Category
        try {
            WebElement hnw = ucPage.getField("hnwCategory");
            jse.executeScript("arguments[0].scrollIntoView({block:'center'})", hnw);
            new org.openqa.selenium.support.ui.Select(hnw).selectByIndex(2);
            Thread.sleep(300);
            log("hnwCategory", "HNW selected (index 2)", "NORMAL", new org.openqa.selenium.support.ui.Select(hnw).getFirstSelectedOption().getText().trim(), true);
        } catch (Exception e) { log("hnwCategory", "HNW selection", "Selected", "Not found", false); }

        // Hardcoded: Photo upload
        try {
            WebElement photo = ucPage.getField("photo");
            jse.executeScript("arguments[0].style.display='block'; arguments[0].style.visibility='visible';", photo);
            Thread.sleep(300);
            photo.sendKeys("C:\\Users\\Yogesh.Pole\\Music\\download (3).jpg");
            Thread.sleep(1000);
            log("photo", "Photo uploaded", "File", photo.getAttribute("value").isEmpty() ? "No file" : "Uploaded", !photo.getAttribute("value").isEmpty());
        } catch (Exception e) { log("photo", "Photo upload", "Uploaded", "Error: " + e.getMessage(), false); }

        // Hardcoded: Multiple Branch Access - Yes
        try {
            WebElement mulY = ucPage.getField("mulBranchAcccessY");
            jse.executeScript("arguments[0].scrollIntoView({block:'center'})", mulY);
            jse.executeScript("arguments[0].click()", mulY);
            Thread.sleep(300);
            log("mulBranchAcccessY", "Multiple Branch - Yes", "Selected", String.valueOf(mulY.isSelected()), mulY.isSelected());
        } catch (Exception e) { log("mulBranchAcccessY", "Multiple Branch", "Selected", "Not found", false); }

        // Hardcoded: Branch Access List - select first option
        try {
            WebElement branchAccess = ucPage.getField("select2-userBaseBranchCode-container");
            jse.executeScript("arguments[0].scrollIntoView({block:'center'})", branchAccess);
            branchAccess.click();
            Thread.sleep(1000);
            java.util.List<org.openqa.selenium.WebElement> opts = driver.findElements(org.openqa.selenium.By.xpath("//ul[@id='select2-userBaseBranchCode-results']/li"));
            if (opts.size() > 0) { opts.get(0).click(); Thread.sleep(500); }
            driver.findElement(org.openqa.selenium.By.tagName("body")).sendKeys(org.openqa.selenium.Keys.ESCAPE);
            Thread.sleep(300);
            log("branchAccessList", "Branch Access selected", "Selected", "Done", true);
        } catch (Exception e) { log("branchAccessList", "Branch Access", "Selected", "Not found", false); }

        // Hardcoded: Concurrent Login - Yes
        try {
            WebElement conY = ucPage.getField("allowConcurrentLoginY");
            jse.executeScript("arguments[0].scrollIntoView({block:'center'})", conY);
            jse.executeScript("arguments[0].click()", conY);
            Thread.sleep(300);
            log("allowConcurrentLoginY", "Concurrent Login - Yes", "Selected", String.valueOf(conY.isSelected()), conY.isSelected());
        } catch (Exception e) { log("allowConcurrentLoginY", "Concurrent Login", "Selected", "Not found", false); }

        // Hardcoded: Forced Auto Expiry - No
        try {
            WebElement fpN = ucPage.getField("forcePwdChgN");
            jse.executeScript("arguments[0].scrollIntoView({block:'center'})", fpN);
            jse.executeScript("arguments[0].click()", fpN);
            Thread.sleep(300);
            log("forcePwdChgN", "Auto Expiry - No", "Selected", String.valueOf(fpN.isSelected()), fpN.isSelected());
        } catch (Exception e) { log("forcePwdChgN", "Auto Expiry", "Selected", "Not found", false); }

        // Save
        log("Save", "Save button visible", "true", String.valueOf(ucPage.isSaveBtnVisible()), ucPage.isSaveBtnVisible());
        log("Save", "Save button enabled", "true", String.valueOf(ucPage.isSaveBtnEnabled()), ucPage.isSaveBtnEnabled());

        ucPage.clickSave();

        String toast = getErrorToast();
        log("Save", "Save User", toast.isEmpty() ? "No error" : "Error: " + toast, toast.isEmpty() ? "Success" : toast, toast.isEmpty());

        sa.assertAll();
    }
}
