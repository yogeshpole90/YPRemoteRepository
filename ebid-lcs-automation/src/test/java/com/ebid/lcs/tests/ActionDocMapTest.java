package com.ebid.lcs.tests;

import org.openqa.selenium.support.ui.Select;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Listeners;
import org.testng.annotations.Test;

import com.ebid.lcs.base.BaseTest;
import com.ebid.lcs.excel.ExcelReader;
import com.ebid.lcs.excel.SheetConstants;
import com.ebid.lcs.listeners.TestListener;
import com.ebid.lcs.pages.ActionDocMapPage;
import com.ebid.lcs.reporting.ExtentManager;

@Listeners(TestListener.class)
public class ActionDocMapTest extends BaseTest {

    private ActionDocMapPage page;

    @BeforeClass
    public void setup() throws Exception {
        ExtentManager.initReport("ActionDocMap");
        ExtentManager.startTest("Action Doc Map - Full Validation");

        page = new ActionDocMapPage(driver);
        page.navigateToActionDocMap();
        logInfo("Navigation", "Navigated to", "Action Doc Map (infraadmin)");
    }

    @Test(priority = 1)
    public void validateAllFields() throws Exception {
        page.clickAdd();
        log("Add Button", "Click Add", "Clicked", "Clicked", true);

        Object[][] data = ExcelReader.getByTcPrefix(SheetConstants.SHEET_ACTION_DOC_MAP, SheetConstants.TC.ACTION_DOC_MAP);

        for (Object[] row : data) {
            String fieldName = row[SheetConstants.Cols.FIELD_NAME].toString().trim();
            String input = row[SheetConstants.Cols.INPUT].toString().trim();
            String expected = row[SheetConstants.Cols.EXPECTED].toString().trim();
            String desc = row[SheetConstants.Cols.DESCRIPTION].toString();
            String checkType = row[SheetConstants.Cols.CHECK_TYPE].toString();

            try {
                String tagName = page.getTagName(fieldName);

                if (tagName.equals("select")) {
                    page.selectDropdown(fieldName, input);
                    String actual = page.getSelectedText(fieldName);
                    log(fieldName, desc, expected, actual, actual.equals(expected));
                    sa.assertEquals(actual, expected, desc);
                } else {
                    page.enterText(fieldName, input);
                    String actual = page.getValue(fieldName);
                    switch (checkType) {
                        case "equals": log(fieldName, desc, expected, actual, actual.equals(expected)); sa.assertEquals(actual, expected, desc); break;
                        case "empty": log(fieldName, desc, "Empty", actual, actual.isEmpty()); sa.assertTrue(actual.isEmpty(), desc); break;
                        case "info": logInfo(fieldName, desc, actual); break;
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
        log("Save", "Displayed", "true", String.valueOf(page.isSaveDisplayed()), page.isSaveDisplayed());
        page.clickSave();
        String toast = getSuccessToast();
        log("Save", "Save Action Doc Map", "Success", toast.isEmpty() ? "No toast" : toast, !toast.isEmpty());
    }

    @Test(priority = 3)
    public void validateBackToList() throws Exception {
        log("Back Button", "Displayed", "true", String.valueOf(page.isBackDisplayed()), page.isBackDisplayed());
        page.clickBack();
        log("Back Button", "Navigate to list page", "List page", "Navigated", true);
    }

    @Test(priority = 4)
    public void validateView() throws Exception {
        // Search using documentName value from Excel
        Object[][] d = ExcelReader.getByTcPrefix(SheetConstants.SHEET_ACTION_DOC_MAP, SheetConstants.TC.ACTION_DOC_MAP);
        String searchText = "";
        for (Object[] r : d) {
            if (r[SheetConstants.Cols.FIELD_NAME].toString().trim().equals("documentName")) {
                searchText = r[SheetConstants.Cols.EXPECTED].toString().trim(); break;
            }
        }
        page.searchRecord(searchText);
        page.clickView();
        log("View", "Click View", "View mode opened", "Clicked", true);

        // Verify fields in view mode
        Object[][] data = ExcelReader.getByTcPrefix(SheetConstants.SHEET_ACTION_DOC_MAP, SheetConstants.TC.ACTION_DOC_MAP);
        for (Object[] row : data) {
            String fieldName = row[SheetConstants.Cols.FIELD_NAME].toString().trim();
            String expected = row[SheetConstants.Cols.EXPECTED].toString().trim();
            String desc = row[SheetConstants.Cols.DESCRIPTION].toString();
            try {
                String tagName = page.getTagName(fieldName);
                String actual = tagName.equals("select") ? page.getSelectedText(fieldName) : page.getValue(fieldName);
                log(fieldName, "View - " + desc, expected, actual, actual.contains(expected) || expected.contains(actual));
            } catch (Exception e) {
                logInfo(fieldName, "View - Not found", e.getMessage());
            }
        }

        page.clickBack();
        log("View", "Back to list from View", "List page", "Navigated", true);
    }

    @Test(priority = 5)
    public void validateUpdate() throws Exception {
        Object[][] d = ExcelReader.getByTcPrefix(SheetConstants.SHEET_ACTION_DOC_MAP, SheetConstants.TC.ACTION_DOC_MAP);
        String searchText = "";
        for (Object[] r : d) {
            if (r[SheetConstants.Cols.FIELD_NAME].toString().trim().equals("documentName")) {
                searchText = r[SheetConstants.Cols.EXPECTED].toString().trim(); break;
            }
        }
        page.searchRecord(searchText);
        page.clickEdit();
        log("Edit", "Click Edit", "Edit mode opened", "Clicked", true);

        // Re-enter fields
        Object[][] data = ExcelReader.getByTcPrefix(SheetConstants.SHEET_ACTION_DOC_MAP, SheetConstants.TC.ACTION_DOC_MAP);
        for (Object[] row : data) {
            String fieldName = row[SheetConstants.Cols.FIELD_NAME].toString().trim();
            String input = row[SheetConstants.Cols.INPUT].toString().trim();
            String expected = row[SheetConstants.Cols.EXPECTED].toString().trim();
            String desc = row[SheetConstants.Cols.DESCRIPTION].toString();
            try {
                String tagName = page.getTagName(fieldName);
                if (tagName.equals("select")) {
                    page.selectDropdown(fieldName, input);
                    String actual = page.getSelectedText(fieldName);
                    log(fieldName, "Edit - " + desc, expected, actual, actual.equals(expected));
                } else {
                    page.enterText(fieldName, input);
                    String actual = page.getValue(fieldName);
                    log(fieldName, "Edit - " + desc, expected, actual, actual.equals(expected));
                }
            } catch (Exception e) {
                logInfo(fieldName, "Edit - Not found", e.getMessage());
            }
        }

        page.clickSave();
        String toast = getSuccessToast();
        log("Update", "Save after Edit", "Success", toast.isEmpty() ? "No toast" : toast, !toast.isEmpty());

        page.clickBack();
        log("Update", "Back to list from Edit", "List page", "Navigated", true);
    }

    @Test(priority = 6)
    public void validateDisable() throws Exception {
        Object[][] d = ExcelReader.getByTcPrefix(SheetConstants.SHEET_ACTION_DOC_MAP, SheetConstants.TC.ACTION_DOC_MAP);
        String searchText = "";
        for (Object[] r : d) {
            if (r[SheetConstants.Cols.FIELD_NAME].toString().trim().equals("documentName")) {
                searchText = r[SheetConstants.Cols.EXPECTED].toString().trim(); break;
            }
        }
        page.searchRecord(searchText);
        page.clickDisable();
        log("Disable", "Click Disable", "Confirmation", "Clicked", true);

        page.confirmDisable();
        log("Disable", "Confirm disable", "Record disabled", "Confirmed", true);

        String toast = getSuccessToast();
        log("Disable", "Disable Action Doc Map", "Success", toast.isEmpty() ? "No toast" : toast, !toast.isEmpty());
        sa.assertAll();
    }
}
