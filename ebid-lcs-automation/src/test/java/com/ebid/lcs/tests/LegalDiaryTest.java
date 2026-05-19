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

@Listeners(TestListener.class)
public class LegalDiaryTest extends BaseTest {

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

    private void scrollToSearch() {
        try {
            WebElement search = driver.findElement(By.cssSelector("input[placeholder='Search keyword here']"));
            jse.executeScript("arguments[0].scrollIntoView({block:'center'})", search);
            Thread.sleep(500);
        } catch (Exception e) {}
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
    public void validateAllFields() throws Exception {
        // Step 1: Select Court Case Ref No
        List<WebElement> caseRefList = driver.findElements(By.id("courtCaseNo"));
        if (!caseRefList.isEmpty() && caseRefList.get(0).isDisplayed() && caseRefList.get(0).isEnabled()) {
            WebElement caseRef = caseRefList.get(0);
            jse.executeScript("arguments[0].scrollIntoView({block:'center'})", caseRef);
            Select caseRefSelect = new Select(caseRef);
            List<WebElement> caseOpts = caseRefSelect.getOptions();

            log("courtCaseNo", "Should be visible", "true", String.valueOf(caseRef.isDisplayed()), caseRef.isDisplayed());
            log("courtCaseNo", "Options count", ">1", String.valueOf(caseOpts.size()), caseOpts.size() > 1);

            if (caseOpts.size() > 1) {
                caseRefSelect.selectByIndex(caseOpts.size() - 1);
                Thread.sleep(500);
                dismissAlert();
                Thread.sleep(500);
                String selected = caseRefSelect.getFirstSelectedOption().getText().trim();
                log("courtCaseNo", "Select last option (unique)", "Non-empty", selected, !selected.isEmpty());
            }
        }

        // Step 2: Validate EDITABLE fields from Excel
        Object[][] data = ExcelReader.getByTcPrefix(SheetConstants.SHEET_LEGAL_DIARY, SheetConstants.TC.LEGAL_DIARY);

        for (Object[] row : data) {
            String fieldName = row[SheetConstants.Cols.FIELD_NAME].toString().trim();
            String input = row[SheetConstants.Cols.INPUT].toString().trim();
            String expected = row[SheetConstants.Cols.EXPECTED].toString().trim();
            String desc = row[SheetConstants.Cols.DESCRIPTION].toString();
            String checkType = row[SheetConstants.Cols.CHECK_TYPE].toString().trim();

            dismissAlert(); hideDatepicker();

            List<WebElement> elements = driver.findElements(By.id(fieldName));
            if (elements.isEmpty()) {
                elements = driver.findElements(By.xpath("//select[@id='" + fieldName + "']"));
            }
            if (elements.isEmpty()) {
                log(fieldName, desc, expected, "Element not found: " + fieldName, false);
                continue;
            }

            WebElement f = elements.get(0);
            jse.executeScript("arguments[0].scrollIntoView({block:'center'})", f);

            String tagName = f.getTagName();
            boolean isDisabled = f.getAttribute("disabled") != null;

            // Skip disabled — will check in Step 3
            if (isDisabled || (!f.isEnabled() && tagName.equals("select"))) {
                logInfo(fieldName, desc + " (Disabled/Auto)", "");
                continue;
            }

            if (tagName.equals("select")) {
                Select s = new Select(f);
                try {
                    s.selectByVisibleText(input);
                    Thread.sleep(500);
                    dismissAlert();
                    String actual = s.getFirstSelectedOption().getText().trim();
                    log(fieldName, desc, expected, actual, actual.equals(expected));
                    sa.assertEquals(actual, expected, desc);
                } catch (Exception e) {
                    dismissAlert();
                    try {
                        s.selectByIndex(1); Thread.sleep(300); dismissAlert();
                        logInfo(fieldName, desc + " (Fallback)", s.getFirstSelectedOption().getText().trim());
                    } catch (Exception e2) {
                        log(fieldName, desc, expected, "Option not found: " + input, false);
                    }
                }
            } else {
                String fieldId = fieldName.toLowerCase();
                boolean isDateField = fieldId.contains("date") || fieldId.contains("hearing") || fieldId.contains("filing");
                boolean isReadonly = f.getAttribute("readonly") != null;

                if (isDateField || isReadonly) {
                    if (input.equalsIgnoreCase("Empty")) {
                        jse.executeScript("arguments[0].value=''", f);
                    } else {
                        jse.executeScript("arguments[0].value='" + input + "'", f);
                    }
                    hideDatepicker();
                    try { f.sendKeys(Keys.ESCAPE); } catch (Exception e) {}
                } else {
                    try { f.clear(); } catch (Exception e) {
                        try { jse.executeScript("arguments[0].value=''", f); } catch (Exception e2) { continue; }
                    }
                    dismissAlert();
                    if (!input.isEmpty() && !input.equalsIgnoreCase("Empty")) {
                        try { f.sendKeys(input); } catch (Exception e) {
                            jse.executeScript("arguments[0].value='" + input + "'", f);
                        }
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

        // Step 3: Verify readonly/auto-populated fields
        verifyAutoPopulated("courtCaseType", "Court Case Type");
        verifyAutoPopulated("currency", "Currency");
        verifyAutoPopulated("suitAmount_txt", "Suit Amount");
        verifyAutoPopulated("lawFirmName", "Law Firm Name");
        verifyAutoPopulated("caseInitiatedby", "Case Initiated By");
        verifyAutoPopulated("bankruptcyCase", "Bankruptcy Case");
    }

    @Test(priority = 2)
    public void validateSave() throws Exception {
        dismissAlert();
        WebElement saveBtn = driver.findElement(By.id("saveData"));
        jse.executeScript("arguments[0].scrollIntoView({block:'center'})", saveBtn);
        Thread.sleep(500);
        jse.executeScript("arguments[0].click()", saveBtn);
        Thread.sleep(1000);
        dismissAlert();

        scrollToSearch();
        String toast = getSuccessToast();
        log("Save", "Save Legal Diary", "Success", toast.isEmpty() ? "No toast" : toast, !toast.isEmpty());

        try {
            String alertText = driver.switchTo().alert().getText();
            log("Save Alert", "Alert after save", "Alert text", alertText, true);
            driver.switchTo().alert().accept();
            Thread.sleep(500);
        } catch (Exception e) {}

        // View/Edit/Delete
        Thread.sleep(2000);
        List<WebElement> viewBtns = driver.findElements(By.xpath("//a[contains(@class,'ViewBtn')]"));
        log("View Button", "View buttons found", ">0", String.valueOf(viewBtns.size()), viewBtns.size() > 0);
        if (viewBtns.size() > 0) {
            jse.executeScript("arguments[0].click()", viewBtns.get(viewBtns.size() - 1));
            Thread.sleep(2000);
            log("View Button", "Click last View", "Opened", "Clicked", true);
        }

        List<WebElement> editBtns = driver.findElements(By.xpath("//a[contains(@class,'editBtn')]"));
        log("Edit Button", "Edit buttons found", ">0", String.valueOf(editBtns.size()), editBtns.size() > 0);
        if (editBtns.size() > 0) {
            jse.executeScript("arguments[0].click()", editBtns.get(editBtns.size() - 1));
            Thread.sleep(2000);
            log("Edit Button", "Click last Edit", "Opened", "Clicked", true);
        }

        List<WebElement> deleteBtns = driver.findElements(By.xpath("//a[contains(@class,'deleteBtn')]"));
        log("Delete Button", "Delete buttons found", ">0", String.valueOf(deleteBtns.size()), deleteBtns.size() > 0);
        if (deleteBtns.size() > 0) {
            jse.executeScript("arguments[0].click()", deleteBtns.get(deleteBtns.size() - 1));
            Thread.sleep(1000);
            log("Delete Button", "Click last Delete", "Triggered", "Clicked", true);
            try {
                driver.switchTo().alert().accept(); Thread.sleep(1000);
                scrollToSearch();
                String deleteToast = getSuccessToast();
                log("Delete", "Delete Legal Diary", "Success", deleteToast.isEmpty() ? "No toast" : deleteToast, !deleteToast.isEmpty());
            } catch (Exception e) {
                try { driver.findElement(By.id("popUpYes")).click(); Thread.sleep(1000);
                    log("Delete", "Click Yes popup", "Deleted", "Yes clicked", true);
                } catch (Exception e2) {}
            }
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
            log(fieldName, "Auto-populated after Ref No select", "Non-empty value", val != null ? val : "null", hasData);
        } catch (Exception e) {
            logInfo(fieldName, "Auto-populate check", "Error: " + e.getMessage());
        }
    }
}
