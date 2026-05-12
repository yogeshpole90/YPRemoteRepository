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
public class SiteVisitTest extends BaseTest {

    @BeforeClass
    public void setup() throws Exception {
        ExtentManager.initReport("SiteVisit");
        ExtentManager.startTest("Site Visit - Full Validation");

        navigateToCase(ConfigManager.get("casenumber"));

        WebElement tab = driver.findElement(By.xpath("//*[contains(@href,'activeTab=Account Information')]"));
        jse.executeScript("arguments[0].scrollIntoView({block:'center',behavior:'smooth'})", tab);
        Thread.sleep(1000);
        tab.click();
        Thread.sleep(2000);

        WebDriverWait wait = new WebDriverWait(driver, 15);
        WebElement svTab = wait.until(ExpectedConditions.elementToBeClickable(By.xpath("//a[contains(text(),'Site Visit')]")));
        jse.executeScript("arguments[0].scrollIntoView({block:'center',behavior:'smooth'})", svTab);
        Thread.sleep(1000);
        act.doubleClick(svTab).build().perform();
        Thread.sleep(2000);

        driver.switchTo().frame("siteVisitFrame");
        logInfo("Frame", "Switched to", "siteVisitFrame");
    }

    @Test(priority = 1)
    public void validateVisitTypeDD() throws Exception {
        validateDropdown("visitType", "Visit Type");
    }

    @Test(priority = 2)
    public void validateVisitedByDD() throws Exception {
        validateDropdown("visitedBy", "Visited By");
    }

    @Test(priority = 3)
    public void validateCustomerResponseDD() throws Exception {
        validateDropdown("customerResponse", "Customer Response");
    }

    @Test(priority = 4)
    public void validateDates() throws Exception {
        Object[][] data = ExcelReader.getByTcPrefix(SheetConstants.SHEET_SITE_VISIT, SheetConstants.TC.SITE_VISIT);
        for (Object[] row : data) {
            String fieldName = row[SheetConstants.Cols.FIELD_NAME].toString().trim();
            if (!fieldName.contains("Date") && !fieldName.contains("date")) continue;

            String input = row[SheetConstants.Cols.INPUT].toString().trim();
            String expected = row[SheetConstants.Cols.EXPECTED].toString().trim();
            String desc = row[SheetConstants.Cols.DESCRIPTION].toString();
            String checkType = row[SheetConstants.Cols.CHECK_TYPE].toString();

            WebElement f = driver.findElement(By.id(fieldName));
            f.clear();
            if (!input.isEmpty() && !input.equalsIgnoreCase("Empty")) f.sendKeys(input);
            f.sendKeys(Keys.TAB);
            Thread.sleep(300);
            String actual = f.getAttribute("value");

            switch (checkType) {
                case "equals": log(fieldName, desc, expected, actual, actual.equals(expected)); sa.assertEquals(actual, expected, desc); break;
                case "notEquals": log(fieldName, desc, "Not " + input, actual, !actual.equals(input)); break;
                case "empty": log(fieldName, desc, "Empty", actual, actual.isEmpty()); break;
                case "info": logInfo(fieldName, desc, actual); break;
            }
        }
    }

    @Test(priority = 5)
    public void validateTextFields() throws Exception {
        Object[][] data = ExcelReader.getByTcPrefix(SheetConstants.SHEET_SITE_VISIT, SheetConstants.TC.SITE_VISIT);
        for (Object[] row : data) {
            String fieldName = row[SheetConstants.Cols.FIELD_NAME].toString().trim();
            // Skip dropdowns and dates
            if (fieldName.equals("visitType") || fieldName.equals("visitedBy") ||
                fieldName.equals("customerResponse") || fieldName.contains("Date") ||
                fieldName.contains("date")) continue;

            String input = row[SheetConstants.Cols.INPUT].toString().trim();
            String expected = row[SheetConstants.Cols.EXPECTED].toString().trim();
            String desc = row[SheetConstants.Cols.DESCRIPTION].toString();
            String checkType = row[SheetConstants.Cols.CHECK_TYPE].toString();

            WebElement f = driver.findElement(By.id(fieldName));
            String tagName = f.getTagName();

            if (tagName.equals("select")) {
                Select s = new Select(f);
                try {
                    s.selectByVisibleText(input);
                    Thread.sleep(500);
                    String actual = s.getFirstSelectedOption().getText().trim();
                    log(fieldName, desc, expected, actual, actual.equals(expected));
                } catch (Exception e) {
                    log(fieldName, desc, expected, "Option not found: " + input, false);
                }
            } else {
                f.clear();
                if (!input.isEmpty() && !input.equalsIgnoreCase("Empty")) f.sendKeys(input);
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
        Thread.sleep(2000);
        String toast = getSuccessToast();
        log("Save", "Save Site Visit", "Success", toast.isEmpty() ? "No toast" : toast, !toast.isEmpty());
        sa.assertAll();
    }

    // ========== HELPER ==========
    private void validateDropdown(String id, String fn) throws Exception {
        WebElement dd = driver.findElement(By.id(id));
        jse.executeScript("arguments[0].scrollIntoView({block:'center',behavior:'smooth'})", dd);
        Thread.sleep(500);
        Select sel = new Select(dd);

        log(fn, "Displayed", "true", String.valueOf(dd.isDisplayed()), dd.isDisplayed());
        sa.assertTrue(dd.isDisplayed());
        log(fn, "Enabled", "true", String.valueOf(dd.isEnabled()), dd.isEnabled());
        sa.assertTrue(dd.isEnabled());
        log(fn, "Single select", "false", String.valueOf(sel.isMultiple()), !sel.isMultiple());

        String defaultVal = sel.getFirstSelectedOption().getText().trim();
        log(fn, "Default value", "SELECT", defaultVal, defaultVal.contains("SELECT") || defaultVal.contains("--"));

        List<WebElement> options = sel.getOptions();
        log(fn, "Options count", ">1", String.valueOf(options.size()), options.size() > 1);

        for (int i = 0; i < options.size(); i++) {
            logInfo(fn, "Option [" + i + "]", options.get(i).getText().trim());
        }

        Set<String> unique = new HashSet<>();
        boolean hasDup = false;
        for (WebElement opt : options) { if (!unique.add(opt.getText().trim())) { hasDup = true; break; } }
        log(fn, "No duplicates", "false", String.valueOf(hasDup), !hasDup);

        sel.selectByIndex(1);
        Thread.sleep(300);
        String idx1 = sel.getFirstSelectedOption().getText().trim();
        log(fn, "Select index 1", "Non-empty", idx1, !idx1.isEmpty());

        sel.selectByIndex(options.size() - 1);
        Thread.sleep(300);
        log(fn, "Select last option", "Non-empty", sel.getFirstSelectedOption().getText().trim(), true);

        sel.selectByIndex(1);
        Thread.sleep(300);
        log(fn, "Final selection for save", idx1, sel.getFirstSelectedOption().getText().trim(), true);
    }
}
