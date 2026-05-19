package com.ebid.lcs.tests;

import org.openqa.selenium.Keys;
import org.openqa.selenium.WebElement;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Listeners;
import org.testng.annotations.Test;

import com.ebid.lcs.base.BaseTest;
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

        for (Object[] row : data) {
            String tcId = row[SheetConstants.Cols.TC_ID].toString().trim();
            String fieldName = row[SheetConstants.Cols.FIELD_NAME].toString().trim();
            String input = row[SheetConstants.Cols.INPUT].toString().trim();
            String expected = row[SheetConstants.Cols.EXPECTED].toString().trim();
            String desc = row[SheetConstants.Cols.DESCRIPTION].toString().trim();
            String checkType = row[SheetConstants.Cols.CHECK_TYPE].toString().trim();

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
    }

    private void handleDropdown(WebElement f, String fieldName, String input, String expected, String desc,
            String checkType) throws Exception {
        if (checkType.equals("info")) {
            logInfo(fieldName, desc, empPage.getSelectedText(f));
        } else {
            try {
                empPage.selectDropdown(f, input);
                String actual = empPage.getSelectedText(f);
                log(fieldName, desc, expected, actual, actual.equals(expected));
                sa.assertEquals(actual, expected, desc);
            } catch (Exception e) {
                log(fieldName, desc, expected, "Option not found: " + input, false);
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
        log("Save", "Save button visible", "true", String.valueOf(empPage.isSaveBtnVisible()),
                empPage.isSaveBtnVisible());
        log("Save", "Save button enabled", "true", String.valueOf(empPage.isSaveBtnEnabled()),
                empPage.isSaveBtnEnabled());

        empPage.clickSave();

        String toast = getSuccessToast();
        boolean saved = !toast.isEmpty();
        log("Save", "Save Employee Master", "Success message", saved ? toast : "No success message", saved);

        sa.assertAll();
    }
}
