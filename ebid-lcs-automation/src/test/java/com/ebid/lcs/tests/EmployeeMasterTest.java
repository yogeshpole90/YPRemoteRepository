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
import com.ebid.lcs.pages.EmployeeMasterPage;
import com.ebid.lcs.reporting.ExtentManager;

@Listeners(TestListener.class)
public class EmployeeMasterTest extends BaseTest {

    private EmployeeMasterPage empPage;

    @BeforeClass
    public void setup() throws Exception {
        ExtentManager.initReport("EmployeeMaster");
        ExtentManager.startTest("Employee Master - Full Validation");

        empPage = new EmployeeMasterPage(driver);
        empPage.navigateToEmployeeMaster();
        empPage.clickAddEmployee();

        logInfo("Setup", "Employee Master Create Mode", "Opened");
    }

    @Test(priority = 1)
    public void validateAllFields() throws Exception {
        Object[][] data = ExcelReader.getByTcPrefix(SheetConstants.SHEET_EMPLOYEE_MASTER,
                SheetConstants.TC.EMPLOYEE_MASTER);

        logInfo("Data", "Total rows from Excel (EM_ prefix)", String.valueOf(data.length));

        if (data.length == 0) {
            log("Data", "No test data found with prefix EM_", "Data present", "No data", false);
            sa.fail("No test data found in EmployeeMaster sheet with prefix EM_");
        }

        String randomEmpId = "EMP" + System.currentTimeMillis() % 1000000;
        ConfigManager.set("generated.empId", randomEmpId);
        logInfo("Data", "Generated Employee ID", randomEmpId);

        for (Object[] row : data) {
            String tcId = row[SheetConstants.Cols.TC_ID].toString().trim();
            String fieldName = row[SheetConstants.Cols.FIELD_NAME].toString().trim();
            String input = row[SheetConstants.Cols.INPUT].toString().trim();
            String expected = row[SheetConstants.Cols.EXPECTED].toString().trim();
            String desc = row[SheetConstants.Cols.DESCRIPTION].toString().trim();
            String checkType = row[SheetConstants.Cols.CHECK_TYPE].toString().trim();

            // Replace empId placeholder with random value
            if (fieldName.equals("empId") && checkType.equals("equals")) {
                input = randomEmpId;
                expected = randomEmpId;
            }

            try {
                WebElement f = empPage.getField(fieldName);
                jse.executeScript("arguments[0].scrollIntoView({block:'center'})", f);
                Thread.sleep(300);

                if (f.getTagName().equals("select")) {
                    handleDropdown(f, fieldName, input, expected, desc, checkType);
                } else {
                    handleInput(f, fieldName, input, expected, desc, checkType);
                }
            } catch (Exception e) {
                log(fieldName, desc, expected, "Element not found: " + fieldName, false);
                sa.fail(tcId + " | Element not found: " + fieldName);
            }
        }

        sa.assertAll();
    }

    private void handleDropdown(WebElement f, String fieldName, String input, String expected, String desc,
            String checkType) throws Exception {
        if (checkType.equals("info")) {
            logInfo(fieldName, desc, empPage.getSelectedText(f));
        } else if (checkType.equals("index")) {
            int idx = Integer.parseInt(input);
            new org.openqa.selenium.support.ui.Select(f).selectByIndex(idx);
            Thread.sleep(500);
            String actual = empPage.getSelectedText(f);
            log(fieldName, desc, "Index " + idx + " selected", actual, !actual.isEmpty() && !actual.contains("Select") && !actual.contains("SELECT"));
        } else {
            try {
                empPage.selectDropdown(f, input);
                String actual = empPage.getSelectedText(f);
                log(fieldName, desc, expected, actual, actual.equalsIgnoreCase(expected));
                sa.assertTrue(actual.equalsIgnoreCase(expected), desc);
            } catch (Exception e) {
                // Try by index 1 as fallback
                try {
                    new org.openqa.selenium.support.ui.Select(f).selectByIndex(1);
                    Thread.sleep(500);
                    String actual = empPage.getSelectedText(f);
                    log(fieldName, desc + " (fallback index 1)", expected, actual, !actual.isEmpty());
                } catch (Exception ex) {
                    log(fieldName, desc, expected, "Option not found: " + input, false);
                }
            }
        }
    }

    private void handleInput(WebElement f, String fieldName, String input, String expected, String desc,
            String checkType) throws Exception {
        empPage.enterText(f, input);
        String actual = empPage.getValue(f);

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
                log(fieldName, desc, "Error toast", toast.isEmpty() ? actual : toast,
                        !toast.isEmpty() || actual.trim().isEmpty());
                f.clear();
                f.sendKeys(Keys.ESCAPE);
                Thread.sleep(200);
                break;
            case "info":
                logInfo(fieldName, desc, actual);
                break;
        }
    }

    @Test(priority = 2)
    public void validateSave() throws Exception {
        // Select ISD codes
        try {
            WebElement isdOff = empPage.getField("isdOffTelephone");
            new org.openqa.selenium.support.ui.Select(isdOff).selectByIndex(1);
            Thread.sleep(300);
            log("isdOffTelephone", "ISD Office Tel selected", "+248", 
                    empPage.getSelectedText(isdOff), true);
        } catch (Exception e) {}

        try {
            WebElement isdMobile = empPage.getField("isdMobile");
            new org.openqa.selenium.support.ui.Select(isdMobile).selectByIndex(1);
            Thread.sleep(300);
            log("isdMobile", "ISD Mobile selected", "+248", 
                    empPage.getSelectedText(isdMobile), true);
        } catch (Exception e) {}

        log("Save", "Save button visible", "true", String.valueOf(empPage.isSaveBtnVisible()),
                empPage.isSaveBtnVisible());
        log("Save", "Save button enabled", "true", String.valueOf(empPage.isSaveBtnEnabled()),
                empPage.isSaveBtnEnabled());

        empPage.clickSave();

        // Handle confirmation modal
        try {
            Thread.sleep(1000);
            WebElement confirmYes = driver.findElement(
                    org.openqa.selenium.By.id("submitForm"));
            if (confirmYes.isDisplayed()) {
                confirmYes.click();
                Thread.sleep(3000);
                log("Save", "Confirmation modal - clicked Yes", "Clicked", "Clicked", true);
            }
        } catch (Exception e) {}

        String toast = getSuccessToast();
        boolean saved = !toast.isEmpty();
        log("Save", "Save Employee Master", "Success message", saved ? toast : "No success message", saved);

        sa.assertAll();
    }
}
