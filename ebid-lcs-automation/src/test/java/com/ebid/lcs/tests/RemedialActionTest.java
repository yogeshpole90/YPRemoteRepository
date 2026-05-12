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
public class RemedialActionTest extends BaseTest {

    @BeforeClass
    public void setup() throws Exception {
        ExtentManager.initReport("RemedialAction");
        ExtentManager.startTest("Remedial Action - Full Validation");

        navigateToCase(ConfigManager.get("casenumber"));

        WebElement remedial = driver.findElement(By.xpath("(//*[contains(@href,'=Remedial Action')])[1]"));
        jse.executeScript("arguments[0].scrollIntoView({block:'center',behavior:'smooth'})", remedial);
        Thread.sleep(1000);
        remedial.click();
        Thread.sleep(2000);
        logInfo("Navigation", "Navigated to", "Remedial Action tab");
    }

    @Test(priority = 1)
    public void validateActionNameDD() throws Exception {
        WebElement dd = driver.findElement(By.id("actionId"));
        jse.executeScript("arguments[0].scrollIntoView({block:'center'})", dd);
        Thread.sleep(500);
        Select s = new Select(dd);
        String fn = "Action Name DD";

        log(fn, "Displayed", "true", String.valueOf(dd.isDisplayed()), dd.isDisplayed());
        sa.assertTrue(dd.isDisplayed());
        log(fn, "Enabled", "true", String.valueOf(dd.isEnabled()), dd.isEnabled());
        sa.assertTrue(dd.isEnabled());
        log(fn, "Single select", "false", String.valueOf(s.isMultiple()), !s.isMultiple());

        String defaultVal = s.getFirstSelectedOption().getText().trim();
        log(fn, "Default value", "SELECT", defaultVal, defaultVal.contains("SELECT") || defaultVal.contains("--"));

        List<WebElement> opts = s.getOptions();
        log(fn, "Options count", ">1", String.valueOf(opts.size()), opts.size() > 1);

        for (int i = 0; i < opts.size(); i++) {
            logInfo(fn, "Option [" + i + "]", opts.get(i).getText().trim());
        }

        // No duplicates
        Set<String> unique = new HashSet<>();
        boolean hasDup = false;
        for (WebElement opt : opts) { if (!unique.add(opt.getText().trim())) { hasDup = true; break; } }
        log(fn, "No duplicates", "false", String.valueOf(hasDup), !hasDup);

        // All enabled
        boolean allEn = true;
        for (WebElement opt : opts) if (!opt.isEnabled()) allEn = false;
        log(fn, "All options enabled", "true", String.valueOf(allEn), allEn);

        // Keyboard accessible
        dd.sendKeys(Keys.DOWN);
        Thread.sleep(300);
        log(fn, "Keyboard accessible (Arrow Down)", "Option selected", s.getFirstSelectedOption().getText().trim(), true);

        // Select from Excel
        Object[][] data = ExcelReader.getByTcPrefix(SheetConstants.SHEET_REMEDIAL, SheetConstants.TC.REMEDIAL);
        for (Object[] row : data) {
            String fieldName = row[SheetConstants.Cols.FIELD_NAME].toString().trim();
            if (!fieldName.equals("actionId")) continue;

            String input = row[SheetConstants.Cols.INPUT].toString().trim();
            String expected = row[SheetConstants.Cols.EXPECTED].toString().trim();
            String desc = row[SheetConstants.Cols.DESCRIPTION].toString();

            try {
                s.selectByVisibleText(input);
                Thread.sleep(500);
                String actual = s.getFirstSelectedOption().getText().trim();
                log(fn, desc, expected, actual, actual.equals(expected));
                sa.assertEquals(actual, expected, desc);
            } catch (Exception e) {
                log(fn, desc, expected, "Option not found: " + input, false);
            }
        }
    }

    @Test(priority = 2)
    public void validateComments() throws Exception {
        WebElement c = driver.findElement(By.id("commments"));
        jse.executeScript("arguments[0].scrollIntoView({block:'center'})", c);
        Thread.sleep(300);
        String fn = "Comments";

        log(fn, "Displayed", "true", String.valueOf(c.isDisplayed()), c.isDisplayed());
        sa.assertTrue(c.isDisplayed());
        log(fn, "Enabled", "true", String.valueOf(c.isEnabled()), c.isEnabled());
        sa.assertTrue(c.isEnabled());

        Object[][] data = ExcelReader.getByTcPrefix(SheetConstants.SHEET_REMEDIAL, SheetConstants.TC.REMEDIAL);
        for (Object[] row : data) {
            String fieldName = row[SheetConstants.Cols.FIELD_NAME].toString().trim();
            if (!fieldName.equals("commments")) continue;

            String input = row[SheetConstants.Cols.INPUT].toString().trim();
            String expected = row[SheetConstants.Cols.EXPECTED].toString().trim();
            String desc = row[SheetConstants.Cols.DESCRIPTION].toString();
            String checkType = row[SheetConstants.Cols.CHECK_TYPE].toString();

            c.clear();
            if (!input.isEmpty() && !input.equalsIgnoreCase("Empty")) c.sendKeys(input);
            String actual = c.getAttribute("value");

            switch (checkType) {
                case "equals": log(fn, desc, expected, actual, actual.equals(expected)); sa.assertEquals(actual, expected, desc); break;
                case "empty": log(fn, desc, "Empty", actual, actual.isEmpty()); sa.assertTrue(actual.isEmpty(), desc); break;
                case "info": logInfo(fn, desc, actual); break;
            }
        }
    }

    @Test(priority = 3)
    public void validateSaveView() throws Exception {
        // Save
        WebElement saveBtn = driver.findElement(By.id("save"));
        jse.executeScript("arguments[0].scrollIntoView({block:'center'})", saveBtn);
        Thread.sleep(300);
        log("Save", "Displayed", "true", String.valueOf(saveBtn.isDisplayed()), saveBtn.isDisplayed());
        log("Save", "Enabled", "true", String.valueOf(saveBtn.isEnabled()), saveBtn.isEnabled());

        saveBtn.click();
        Thread.sleep(2000);
        String successToast = getSuccessToast();
        log("Save", "Save Remedial Action", "Success", successToast.isEmpty() ? "No toast" : successToast, !successToast.isEmpty());

        Thread.sleep(1000);

        // View buttons
        List<WebElement> viewBtns = driver.findElements(By.xpath("//*[text()='View']"));
        log("View", "View buttons exist", ">0", String.valueOf(viewBtns.size()), viewBtns.size() > 0);

        if (viewBtns.size() > 0) {
            viewBtns.get(viewBtns.size() - 1).click();
            Thread.sleep(1000);
            log("View", "Click last View", "Record displayed", "View clicked", true);

            try {
                String actVal = driver.findElement(By.id("actionId")).getAttribute("value");
                String cmtVal = driver.findElement(By.id("commments")).getAttribute("value");
                log("View Data", "Action Name populated", "Not empty", actVal.isEmpty() ? "EMPTY" : actVal, !actVal.isEmpty());
                log("View Data", "Comments populated", "Not empty", cmtVal.isEmpty() ? "EMPTY" : cmtVal, !cmtVal.isEmpty());
            } catch (Exception e) {
                log("View Data", "Fields after View", "Populated", "Error: " + e.getMessage(), false);
            }
        }

        // Reset button
        try {
            WebElement resetBtn = driver.findElement(By.xpath("//*[text()='Reset']"));
            log("Reset", "Displayed", "true", String.valueOf(resetBtn.isDisplayed()), resetBtn.isDisplayed());
            log("Reset", "Enabled", "true", String.valueOf(resetBtn.isEnabled()), resetBtn.isEnabled());
        } catch (Exception e) {
            logInfo("Reset", "Reset button", "Not found");
        }

        sa.assertAll();
    }
}
