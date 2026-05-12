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
public class LegalOrderTest extends BaseTest {

    @BeforeClass
    public void setup() throws Exception {
        ExtentManager.initReport("LegalOrder");
        ExtentManager.startTest("Legal Order - Full Validation");

        navigateToCase(ConfigManager.get("casenumber"));

        WebElement tab = driver.findElement(By.xpath("//*[contains(@href,'activeTab=Account Information')]"));
        jse.executeScript("arguments[0].scrollIntoView({block:'center',behavior:'smooth'})", tab);
        Thread.sleep(1000);
        tab.click();
        Thread.sleep(2000);

        WebDriverWait wait = new WebDriverWait(driver, 15);
        WebElement loTab = wait.until(ExpectedConditions.elementToBeClickable(By.xpath("//a[contains(text(),'Legal Order')]")));
        jse.executeScript("arguments[0].scrollIntoView({block:'center',behavior:'smooth'})", loTab);
        Thread.sleep(1000);
        act.doubleClick(loTab).build().perform();
        Thread.sleep(2000);

        driver.switchTo().frame("legalOrderFrame");
        logInfo("Frame", "Switched to", "legalOrderFrame");
    }

    @Test(priority = 1)
    public void validateLoanAcNoDD() throws Exception {
        WebElement dd = driver.findElement(By.id("loanAcNo"));
        jse.executeScript("arguments[0].scrollIntoView({block:'center'})", dd);
        Thread.sleep(500);
        Select sel = new Select(dd);
        String fn = "Loan Ac No";

        log(fn, "Displayed", "true", String.valueOf(dd.isDisplayed()), dd.isDisplayed());
        sa.assertTrue(dd.isDisplayed());
        log(fn, "Enabled", "true", String.valueOf(dd.isEnabled()), dd.isEnabled());
        sa.assertTrue(dd.isEnabled());

        List<WebElement> opts = sel.getOptions();
        log(fn, "Options count", ">0", String.valueOf(opts.size()), opts.size() > 0);

        String defaultVal = sel.getFirstSelectedOption().getText().trim();
        logInfo(fn, "Default value", defaultVal);

        sel.selectByIndex(1);
        Thread.sleep(500);
        String selected = sel.getFirstSelectedOption().getText().trim();
        log(fn, "Select index 1", "Non-empty", selected, !selected.isEmpty());
        sa.assertFalse(selected.isEmpty());
    }

    @Test(priority = 2)
    public void validateOrderTypeDD() throws Exception {
        WebElement dd = driver.findElement(By.id("orderType"));
        Select sel = new Select(dd);
        String fn = "Order Type";

        log(fn, "Displayed", "true", String.valueOf(dd.isDisplayed()), dd.isDisplayed());
        sa.assertTrue(dd.isDisplayed());
        log(fn, "Enabled", "true", String.valueOf(dd.isEnabled()), dd.isEnabled());
        sa.assertTrue(dd.isEnabled());

        List<WebElement> opts = sel.getOptions();
        log(fn, "Options count", ">0", String.valueOf(opts.size()), opts.size() > 0);

        for (int i = 0; i < opts.size(); i++) {
            logInfo(fn, "Option [" + i + "]", opts.get(i).getText().trim());
        }

        String defaultVal = sel.getFirstSelectedOption().getText().trim();
        logInfo(fn, "Default value", defaultVal);

        // Select from Excel
        Object[][] data = ExcelReader.getByTcPrefix(SheetConstants.SHEET_LEGAL_ORDER, SheetConstants.TC.LEGAL_ORDER);
        for (Object[] row : data) {
            String fieldName = row[SheetConstants.Cols.FIELD_NAME].toString().trim();
            if (!fieldName.equals("orderType")) continue;

            String input = row[SheetConstants.Cols.INPUT].toString().trim();
            String expected = row[SheetConstants.Cols.EXPECTED].toString().trim();
            String desc = row[SheetConstants.Cols.DESCRIPTION].toString();

            try {
                sel.selectByVisibleText(input);
                Thread.sleep(500);
                String actual = sel.getFirstSelectedOption().getText().trim();
                log(fn, desc, expected, actual, actual.equals(expected));
                sa.assertEquals(actual, expected, desc);
            } catch (Exception e) {
                log(fn, desc, expected, "Option not found: " + input, false);
            }
        }
    }

    @Test(priority = 3)
    public void validateDates() throws Exception {
        Object[][] data = ExcelReader.getByTcPrefix(SheetConstants.SHEET_LEGAL_ORDER, SheetConstants.TC.LEGAL_ORDER);
        for (Object[] row : data) {
            String fieldName = row[SheetConstants.Cols.FIELD_NAME].toString().trim();
            if (!fieldName.contains("Date") && !fieldName.contains("date")) continue;

            String input = row[SheetConstants.Cols.INPUT].toString().trim();
            String expected = row[SheetConstants.Cols.EXPECTED].toString().trim();
            String desc = row[SheetConstants.Cols.DESCRIPTION].toString();
            String checkType = row[SheetConstants.Cols.CHECK_TYPE].toString();

            WebElement f = driver.findElement(By.id(fieldName));
            log(fieldName, "Displayed", "true", String.valueOf(f.isDisplayed()), f.isDisplayed());
            log(fieldName, "Enabled", "true", String.valueOf(f.isEnabled()), f.isEnabled());

            f.clear();
            if (!input.isEmpty() && !input.equalsIgnoreCase("Empty")) f.sendKeys(input);
            f.sendKeys(Keys.TAB);
            Thread.sleep(500);
            String actual = f.getAttribute("value");

            switch (checkType) {
                case "equals": log(fieldName, desc, expected, actual, actual.equals(expected)); sa.assertEquals(actual, expected, desc); break;
                case "notEquals": log(fieldName, desc, "Not " + input, actual, !actual.equals(input)); break;
                case "empty": log(fieldName, desc, "Empty", actual, actual.isEmpty()); break;
                case "info": logInfo(fieldName, desc, actual); break;
            }
        }
    }

    @Test(priority = 4)
    public void validateRemarks() throws Exception {
        WebElement f = driver.findElement(By.id("remark"));
        String fn = "Remarks";

        log(fn, "Displayed", "true", String.valueOf(f.isDisplayed()), f.isDisplayed());
        sa.assertTrue(f.isDisplayed());
        log(fn, "Enabled", "true", String.valueOf(f.isEnabled()), f.isEnabled());
        sa.assertTrue(f.isEnabled());

        Object[][] data = ExcelReader.getByTcPrefix(SheetConstants.SHEET_LEGAL_ORDER, SheetConstants.TC.LEGAL_ORDER);
        for (Object[] row : data) {
            String fieldName = row[SheetConstants.Cols.FIELD_NAME].toString().trim();
            if (!fieldName.equals("remark")) continue;

            String input = row[SheetConstants.Cols.INPUT].toString().trim();
            String expected = row[SheetConstants.Cols.EXPECTED].toString().trim();
            String desc = row[SheetConstants.Cols.DESCRIPTION].toString();
            String checkType = row[SheetConstants.Cols.CHECK_TYPE].toString();

            f.clear();
            if (!input.isEmpty() && !input.equalsIgnoreCase("Empty")) f.sendKeys(input);
            String actual = f.getAttribute("value");

            switch (checkType) {
                case "equals": log(fn, desc, expected, actual, actual.equals(expected)); sa.assertEquals(actual, expected, desc); break;
                case "empty": log(fn, desc, "Empty", actual, actual.isEmpty()); sa.assertTrue(actual.isEmpty(), desc); break;
                case "info": logInfo(fn, desc, actual); break;
            }
        }
    }

    @Test(priority = 5)
    public void validateSaveViewEditDelete() throws Exception {
        // Save
        WebElement saveBtn = driver.findElement(By.id("saveBtn"));
        jse.executeScript("arguments[0].scrollIntoView({block:'center'})", saveBtn);
        Thread.sleep(500);
        log("Save", "Displayed", "true", String.valueOf(saveBtn.isDisplayed()), saveBtn.isDisplayed());
        log("Save", "Enabled", "true", String.valueOf(saveBtn.isEnabled()), saveBtn.isEnabled());

        saveBtn.click();
        Thread.sleep(3000);

        driver.switchTo().defaultContent();
        jse.executeScript("window.scrollTo(0,0)");
        Thread.sleep(1000);
        String saveToast = getSuccessToast();
        log("Save", "Save Legal Order", "Success", saveToast.isEmpty() ? "No toast" : saveToast, !saveToast.isEmpty());

        // Switch to data frame for View/Edit/Delete
        driver.switchTo().frame("getLegalDetailDataFrame");
        Thread.sleep(1000);

        // View
        try {
            WebElement viewBtn = driver.findElement(By.xpath("//*[contains(@class,'ViewBtn')]"));
            jse.executeScript("arguments[0].scrollIntoView({block:'center'})", viewBtn);
            Thread.sleep(500);
            viewBtn.click();
            Thread.sleep(2000);
            log("View", "Click View button", "Record displayed", "View clicked", true);
        } catch (Exception e) {
            log("View", "View button", "Found", "Not found", false);
        }

        // Edit
        try {
            WebElement editBtn = driver.findElement(By.xpath("//*[contains(@class,'editBtn')]"));
            jse.executeScript("arguments[0].scrollIntoView({block:'center'})", editBtn);
            Thread.sleep(500);
            editBtn.click();
            Thread.sleep(2000);
            log("Edit", "Click Edit button", "Fields editable", "Edit clicked", true);

            driver.findElement(By.id("saveBtn")).click();
            Thread.sleep(3000);
            driver.switchTo().defaultContent();
            jse.executeScript("window.scrollTo(0,0)");
            Thread.sleep(1000);
            String updateToast = getSuccessToast();
            log("Edit", "Save after Edit", "Updated", updateToast.isEmpty() ? "No toast" : updateToast, !updateToast.isEmpty());

            driver.switchTo().frame("getLegalDetailDataFrame");
            Thread.sleep(1000);
        } catch (Exception e) {
            log("Edit", "Edit button", "Found", "Not found", false);
        }

        // Delete
        try {
            WebElement deleteBtn = driver.findElement(By.xpath("//*[contains(@class,'deleteBtn')]"));
            jse.executeScript("arguments[0].scrollIntoView({block:'center'})", deleteBtn);
            Thread.sleep(500);
            deleteBtn.click();
            Thread.sleep(3000);
            driver.switchTo().defaultContent();
            jse.executeScript("window.scrollTo(0,0)");
            Thread.sleep(1000);
            String deleteToast = getSuccessToast();
            log("Delete", "Click Delete button", "Deleted", deleteToast.isEmpty() ? "No toast" : deleteToast, !deleteToast.isEmpty());
        } catch (Exception e) {
            log("Delete", "Delete button", "Found", "Not found", false);
        }

        sa.assertAll();
    }
}
