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

import java.util.List;

import com.ebid.lcs.base.BaseTest;
import com.ebid.lcs.config.ConfigManager;
import com.ebid.lcs.excel.ExcelReader;
import com.ebid.lcs.excel.SheetConstants;
import com.ebid.lcs.listeners.TestListener;
import com.ebid.lcs.reporting.ExtentManager;

@Listeners(TestListener.class)
public class FollowUpTest extends BaseTest {

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
        ExtentManager.initReport("FollowUp");
        ExtentManager.startTest("Follow Up - Full Validation");

        navigateToCase(ConfigManager.get("casenumber"));

        WebElement followUp = driver.findElement(By.xpath("//*[contains(@href,'Follow-Up')]"));
        jse.executeScript("arguments[0].scrollIntoView({block:'center',behavior:'smooth'})", followUp);
        Thread.sleep(1000);
        followUp.click();
        Thread.sleep(2000);

        WebDriverWait wait = new WebDriverWait(driver, 15);
        WebElement addFU = wait.until(ExpectedConditions.elementToBeClickable(By.xpath("//*[contains(text(),'Add Follow-Up')]")));
        jse.executeScript("arguments[0].scrollIntoView({block:'center',behavior:'smooth'})", addFU);
        Thread.sleep(1000);
        addFU.click();
        Thread.sleep(2000);
        driver.switchTo().frame("addcommunicationHistoryFrame");
        logInfo("Frame", "Switched to", "addcommunicationHistoryFrame");

        // Hardcoded loan account selection via jQuery trigger
        jse.executeScript(
            "var sel = $('#loanAcNoSelect');" +
            "sel.val(sel.find(\"option:contains('0000152011104001457')\").val()).trigger('change');"
        );
        Thread.sleep(1000);
        logInfo("loanAcNoSelect", "Loan Account Selected", "0000152011104001457");
    }

    @Test(priority = 1)
    public void validateAllFields() throws Exception {
        Object[][] data = ExcelReader.getByTcPrefix(SheetConstants.SHEET_FOLLOW_UP, SheetConstants.TC.FOLLOW_UP);

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
                    // Normal select dropdown
                    Select s = new Select(f);
                    if (checkType.equals("info")) {
                        String actual = s.getFirstSelectedOption().getText().trim();
                        logInfo(fieldName, desc, actual);
                    } else {
                        boolean selected = false;
                        List<WebElement> options = s.getOptions();
                        for (WebElement opt : options) {
                            if (opt.getText().trim().equalsIgnoreCase(input.trim())) {
                                opt.click();
                                selected = true;
                                break;
                            }
                        }
                        Thread.sleep(500);
                        if (selected) {
                            String actual = s.getFirstSelectedOption().getText().trim();
                            log(fieldName, desc, expected, actual, actual.equalsIgnoreCase(expected));
                            sa.assertEquals(actual.toLowerCase(), expected.toLowerCase(), desc);
                        } else {
                            log(fieldName, desc, expected, "Option not found: " + input, false);
                        }
                    }
                } else {
                    // Date or text field
                    String fieldId = fieldName.toLowerCase();
                    if (fieldId.contains("date") || fieldId.equals("resolve") || fieldId.equals("followupdate")) {
                        // Date field - use JS to set value
                        jse.executeScript("arguments[0].value='" + (input.equalsIgnoreCase("Empty") ? "" : input) + "'", f);
                        Thread.sleep(300);
                        hideDatepicker();
                    } else {
                        // Normal text field
                        f.clear();
                        Thread.sleep(200);
                        if (!input.isEmpty() && !input.equalsIgnoreCase("Empty")) {
                            f.sendKeys(input);
                        }
                        Thread.sleep(300);
                    }
                    String actual = f.getAttribute("value");

                    switch (checkType) {
                        case "equals": log(fieldName, desc, expected, actual, actual.equals(expected)); sa.assertEquals(actual, expected, desc); break;
                        case "notEquals": log(fieldName, desc, "Not " + input, actual, !actual.equals(input)); break;
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
        String fn = "Save";

        WebElement saveBtn = driver.findElement(By.id("saveData"));
        jse.executeScript("arguments[0].scrollIntoView({block:'center'})", saveBtn);
        Thread.sleep(500);
        log(fn, "Save button visible", "true", String.valueOf(saveBtn.isDisplayed()), saveBtn.isDisplayed());
        log(fn, "Save button enabled", "true", String.valueOf(saveBtn.isEnabled()), saveBtn.isEnabled());

        saveBtn.click();
        Thread.sleep(2000);

        // Switch to parent for toast
        driver.switchTo().parentFrame();
        Thread.sleep(500);
        String toast = getSuccessToast();
        log(fn, "Save Follow Up", "Success", toast.isEmpty() ? "No toast" : toast, !toast.isEmpty());

        sa.assertAll();
    }
}
