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
            String actual = ucPage.selectEmployeeId();
            boolean pass = !actual.contains("DUPLICATE") && !actual.equals("NOT FOUND");
            log(fieldName, desc, "992 selected", actual, pass);
            if (actual.contains("DUPLICATE")) {
                logInfo(fieldName, "Duplicate check", "EmpID 992 already in use - expected behavior");
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
        // Check if field is read-only (auto-fetched from Employee ID)
        if (checkType.equals("readOnly")) {
            String actual = ucPage.getValue(f);
            logInfo(fieldName, desc, actual.isEmpty() ? "Empty" : actual);
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
        log("Save", "Save button visible", "true", String.valueOf(ucPage.isSaveBtnVisible()), ucPage.isSaveBtnVisible());
        log("Save", "Save button enabled", "true", String.valueOf(ucPage.isSaveBtnEnabled()), ucPage.isSaveBtnEnabled());

        ucPage.clickSave();

        String toast = getErrorToast();
        log("Save", "Save User", toast.isEmpty() ? "No error" : "Error: " + toast, toast.isEmpty() ? "Success" : toast, toast.isEmpty());

        sa.assertAll();
    }
}
