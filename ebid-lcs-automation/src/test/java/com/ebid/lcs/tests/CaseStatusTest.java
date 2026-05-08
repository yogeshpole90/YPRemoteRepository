package com.ebid.lcs.tests;

import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.Select;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Listeners;
import org.testng.annotations.Test;

import com.ebid.lcs.base.BaseTest;
import com.ebid.lcs.config.ConfigManager;
import com.ebid.lcs.excel.ExcelReader;
import com.ebid.lcs.excel.SheetConstants;
import com.ebid.lcs.listeners.TestListener;
import com.ebid.lcs.reporting.ExtentManager;

@Listeners(TestListener.class)
public class CaseStatusTest extends BaseTest {

    @BeforeClass
    public void setup() throws Exception {
        ExtentManager.initReport("CaseStatus");
        ExtentManager.startTest("Case Status - Full Validation");

        navigateToCase(ConfigManager.get("casenumber"));

        WebElement tab = driver.findElement(By.xpath("//a[contains(@href,'activeTab=Account Information')]"));
        jse.executeScript("arguments[0].scrollIntoView({block:'center',behavior:'smooth'})", tab);
        Thread.sleep(1000);
        tab.click();
        Thread.sleep(2000);

        WebElement cstTab = driver.findElement(By.xpath("//a[contains(text(),'Case Status')]"));
        jse.executeScript("arguments[0].scrollIntoView({block:'center',behavior:'smooth'})", cstTab);
        Thread.sleep(1000);
        act.doubleClick(cstTab).build().perform();
        Thread.sleep(2000);

        driver.switchTo().frame("viewCaseStatusFrame");
        logInfo("Frame", "Switched to", "viewCaseStatusFrame");
    }

    @Test(priority = 1)
    public void validateCaseStatusDD() throws Exception {
        WebElement dd = driver.findElement(By.id("caseStatusId"));
        Select select = new Select(dd);
        String fn = "Case Status DD";

        log(fn, "Displayed", "true", String.valueOf(dd.isDisplayed()), dd.isDisplayed());
        log(fn, "Enabled", "true", String.valueOf(dd.isEnabled()), dd.isEnabled());

        Object[][] data = ExcelReader.getByTcPrefix(SheetConstants.SHEET_CASE_STATUS, SheetConstants.TC.CASE_STATUS);

        for (Object[] row : data) {
            String fieldName = row[SheetConstants.Cols.FIELD_NAME].toString();
            if (!fieldName.equals("caseStatusId")) continue;

            String input = row[SheetConstants.Cols.INPUT].toString();
            String expected = row[SheetConstants.Cols.EXPECTED].toString();
            String desc = row[SheetConstants.Cols.DESCRIPTION].toString();

            select.selectByVisibleText(input);
            Thread.sleep(500);
            String actual = select.getFirstSelectedOption().getText().trim();
            log(fn, desc, expected, actual, actual.equals(expected));
            sa.assertEquals(actual, expected, desc);
        }
    }

    @Test(priority = 2)
    public void validateRemarks() throws Exception {
        WebElement f = driver.findElement(By.id("remarks"));
        String fn = "Remarks";

        log(fn, "Displayed", "true", String.valueOf(f.isDisplayed()), f.isDisplayed());
        log(fn, "Enabled", "true", String.valueOf(f.isEnabled()), f.isEnabled());

        Object[][] data = ExcelReader.getByTcPrefix(SheetConstants.SHEET_CASE_STATUS, SheetConstants.TC.CASE_STATUS);

        for (Object[] row : data) {
            String fieldName = row[SheetConstants.Cols.FIELD_NAME].toString();
            if (!fieldName.equals("remarks")) continue;

            String input = row[SheetConstants.Cols.INPUT].toString();
            String expected = row[SheetConstants.Cols.EXPECTED].toString();
            String desc = row[SheetConstants.Cols.DESCRIPTION].toString();
            String checkType = row[SheetConstants.Cols.CHECK_TYPE].toString();

            f.clear();
            if (!input.isEmpty()) f.sendKeys(input);
            String actual = f.getAttribute("value");

            switch (checkType) {
                case "equals": log(fn, desc, expected, actual, actual.equals(expected)); break;
                case "empty": log(fn, desc, "Empty", actual, actual.isEmpty()); break;
            }
        }
    }

    @Test(priority = 3)
    public void validateSave() throws Exception {
        WebElement saveBtn = driver.findElement(By.id("saveData"));
        log("Save", "Displayed", "true", String.valueOf(saveBtn.isDisplayed()), saveBtn.isDisplayed());
        saveBtn.click();
        Thread.sleep(2000);
        String toast = getSuccessToast();
        log("Save", "Save record", "Success", toast.isEmpty() ? "No toast" : toast, !toast.isEmpty());
        sa.assertAll();
    }
}
