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

import java.util.HashSet;
import java.util.List;
import java.util.Set;

import com.ebid.lcs.base.BaseTest;
import com.ebid.lcs.config.ConfigManager;
import com.ebid.lcs.excel.ExcelReader;
import com.ebid.lcs.excel.SheetConstants;
import com.ebid.lcs.listeners.TestListener;
import com.ebid.lcs.reporting.ExtentManager;

@Listeners(TestListener.class)
public class FollowUpTest extends BaseTest {

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
        driver.switchTo().frame("addcommunicationHistoryFrame");
        logInfo("Frame", "Switched to", "addcommunicationHistoryFrame");
    }

    @Test(priority = 1)
    public void validateCommunicationType() throws Exception {
        validateDropdown("communicationType", "Communication Type");
    }

    @Test(priority = 2)
    public void validateAction() throws Exception {
        validateDropdown("action", "Action");
    }

    @Test(priority = 3)
    public void validateResult() throws Exception {
        validateDropdown("callStatus", "Result");
    }

    @Test(priority = 4)
    public void validateDates() throws Exception {
        // Result Date
        validateDateField("resolve", "Result Date");
        // Follow Up Date
        validateDateField("followUpDate", "Follow Up Date");
    }

    @Test(priority = 5)
    public void validateTextFields() throws Exception {
        Object[][] data = ExcelReader.getByTcPrefix(SheetConstants.SHEET_FOLLOW_UP, SheetConstants.TC.FOLLOW_UP);

        for (Object[] row : data) {
            String fieldName = row[SheetConstants.Cols.FIELD_NAME].toString().trim();
            String input = row[SheetConstants.Cols.INPUT].toString().trim();
            String expected = row[SheetConstants.Cols.EXPECTED].toString().trim();
            String desc = row[SheetConstants.Cols.DESCRIPTION].toString();
            String checkType = row[SheetConstants.Cols.CHECK_TYPE].toString();

            // Skip dropdown and date fields (handled in other tests)
            if (fieldName.equals("communicationType") || fieldName.equals("action") ||
                fieldName.equals("callStatus") || fieldName.equals("resolve") ||
                fieldName.equals("followUpDate")) continue;

            WebElement f = driver.findElement(By.id(fieldName));
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
                if (!input.isEmpty() && !input.equalsIgnoreCase("Empty")) f.sendKeys(input);
                if (fieldName.toLowerCase().contains("date")) f.sendKeys(Keys.TAB);
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

    @Test(priority = 6)
    public void validateSave() throws Exception {
        WebElement saveBtn = driver.findElement(By.id("saveData"));
        jse.executeScript("arguments[0].scrollIntoView({block:'center'})", saveBtn);
        Thread.sleep(500);

        log("Save", "Displayed", "true", String.valueOf(saveBtn.isDisplayed()), saveBtn.isDisplayed());
        log("Save", "Enabled", "true", String.valueOf(saveBtn.isEnabled()), saveBtn.isEnabled());

        saveBtn.click();
        Thread.sleep(3000);

        driver.switchTo().parentFrame();
        Thread.sleep(500);
        driver.switchTo().defaultContent();
        jse.executeScript("window.scrollTo(0,0)");
        Thread.sleep(1000);

        String toast = getSuccessToast();
        log("Save", "Save Follow Up", "Success", toast.isEmpty() ? "No toast" : toast, !toast.isEmpty());
        sa.assertAll();
    }

    // ========== HELPER: Dropdown Validation ==========
    private void validateDropdown(String id, String fn) throws Exception {
        WebElement dd = driver.findElement(By.id(id));
        jse.executeScript("arguments[0].scrollIntoView({block:'center',behavior:'smooth'})", dd);
        Thread.sleep(500);
        Select sel = new Select(dd);

        log(fn, "Displayed", "true", String.valueOf(dd.isDisplayed()), dd.isDisplayed());
        sa.assertTrue(dd.isDisplayed());
        log(fn, "Enabled", "true", String.valueOf(dd.isEnabled()), dd.isEnabled());
        sa.assertTrue(dd.isEnabled());
        log(fn, "Tag is select", "select", dd.getTagName(), dd.getTagName().equals("select"));
        log(fn, "Single select", "false", String.valueOf(sel.isMultiple()), !sel.isMultiple());
        sa.assertFalse(sel.isMultiple());

        // Default value
        String defaultVal = sel.getFirstSelectedOption().getText().trim();
        log(fn, "Default value", "SELECT", defaultVal, defaultVal.contains("SELECT"));

        // Options count
        List<WebElement> options = sel.getOptions();
        log(fn, "Options count", ">1", String.valueOf(options.size()), options.size() > 1);
        sa.assertTrue(options.size() > 1);

        // Print all options
        for (int i = 0; i < options.size(); i++) {
            logInfo(fn, "Option [" + i + "]", options.get(i).getText().trim());
        }

        // No duplicates
        Set<String> unique = new HashSet<>();
        boolean hasDup = false;
        for (WebElement opt : options) {
            if (!unique.add(opt.getText().trim())) { hasDup = true; break; }
        }
        log(fn, "No duplicate options", "false", String.valueOf(hasDup), !hasDup);

        // No empty text
        boolean hasEmpty = false;
        for (WebElement opt : options) {
            if (opt.getText().trim().isEmpty()) { hasEmpty = true; break; }
        }
        log(fn, "No empty option text", "false", String.valueOf(hasEmpty), !hasEmpty);

        // All options enabled
        boolean allEnabled = true;
        for (WebElement opt : options) if (!opt.isEnabled()) allEnabled = false;
        log(fn, "All options enabled", "true", String.valueOf(allEnabled), allEnabled);

        // Select index 1
        sel.selectByIndex(1);
        Thread.sleep(300);
        String idx1 = sel.getFirstSelectedOption().getText().trim();
        log(fn, "Select index 1", "Non-empty", idx1, !idx1.isEmpty());

        // Select last
        sel.selectByIndex(options.size() - 1);
        Thread.sleep(300);
        String last = sel.getFirstSelectedOption().getText().trim();
        log(fn, "Select last option", "Non-empty", last, !last.isEmpty());

        // Re-select index 1
        sel.selectByIndex(1);
        Thread.sleep(300);
        String resel = sel.getFirstSelectedOption().getText().trim();
        log(fn, "Re-select index 1", idx1, resel, resel.equals(idx1));

        // Keep selected for save
        log(fn, "Final selection for save", idx1, sel.getFirstSelectedOption().getText().trim(), true);
    }

    // ========== HELPER: Date Field Validation ==========
    private void validateDateField(String id, String fn) throws Exception {
        WebElement d = driver.findElement(By.id(id));
        jse.executeScript("arguments[0].scrollIntoView({block:'center',behavior:'smooth'})", d);
        Thread.sleep(300);

        log(fn, "Displayed", "true", String.valueOf(d.isDisplayed()), d.isDisplayed());
        sa.assertTrue(d.isDisplayed());
        log(fn, "Enabled", "true", String.valueOf(d.isEnabled()), d.isEnabled());
        sa.assertTrue(d.isEnabled());

        // Default value
        String defVal = d.getAttribute("value");
        logInfo(fn, "Default value", defVal);

        // Readonly check
        String ro = d.getAttribute("readonly");
        logInfo(fn, "Readonly", String.valueOf(ro));

        // Valid date
        jse.executeScript("arguments[0].value='26-03-2026'", d);
        Thread.sleep(300);
        String valid = d.getAttribute("value");
        log(fn, "Valid date 26-03-2026", "26-03-2026", valid, valid.equals("26-03-2026"));

        // Invalid date
        jse.executeScript("arguments[0].value='99-99-9999'", d);
        Thread.sleep(300);
        String invalid = d.getAttribute("value");
        logInfo(fn, "Invalid date 99-99-9999", invalid);

        // Day 32
        jse.executeScript("arguments[0].value='32-03-2026'", d);
        Thread.sleep(300);
        logInfo(fn, "Day 32", d.getAttribute("value"));

        // Month 13
        jse.executeScript("arguments[0].value='26-13-2026'", d);
        Thread.sleep(300);
        logInfo(fn, "Month 13", d.getAttribute("value"));

        // Alphabets
        jse.executeScript("arguments[0].value='abcdef'", d);
        Thread.sleep(300);
        logInfo(fn, "Alphabets input", d.getAttribute("value"));

        // Special chars
        jse.executeScript("arguments[0].value='@#$%&'", d);
        Thread.sleep(300);
        logInfo(fn, "Special chars", d.getAttribute("value"));

        // Empty
        jse.executeScript("arguments[0].value=''", d);
        Thread.sleep(300);
        String empty = d.getAttribute("value");
        log(fn, "Empty value", "Empty", empty, empty.isEmpty());

        // Final value for save
        jse.executeScript("arguments[0].value='26-03-2026'", d);
        Thread.sleep(300);
        log(fn, "Final value for save", "26-03-2026", d.getAttribute("value"), true);
    }
}
