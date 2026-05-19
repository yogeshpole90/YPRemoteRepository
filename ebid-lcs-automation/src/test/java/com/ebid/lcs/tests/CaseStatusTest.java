package com.ebid.lcs.tests;

import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.Select;
import org.openqa.selenium.support.ui.WebDriverWait;
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

        WebDriverWait wait = new WebDriverWait(driver, 15);
        WebElement cstTab = wait
                .until(ExpectedConditions.elementToBeClickable(By.xpath("//a[contains(text(),'Case Status')]")));
        jse.executeScript("arguments[0].scrollIntoView({block:'center',behavior:'smooth'})", cstTab);
        Thread.sleep(1000);
        act.doubleClick(cstTab).build().perform();
        Thread.sleep(2000);

        driver.switchTo().frame("viewCaseStatusFrame");
        logInfo("Frame", "Switched to", "viewCaseStatusFrame");
    }

    @Test(priority = 1)
    public void validateAllFields() throws Exception {
        Object[][] data = ExcelReader.getByTcPrefix(SheetConstants.SHEET_CASE_STATUS, SheetConstants.TC.CASE_STATUS);

        for (Object[] row : data) {
            String fieldName = row[SheetConstants.Cols.FIELD_NAME].toString().trim();
            String input = row[SheetConstants.Cols.INPUT].toString().trim();
            String expected = row[SheetConstants.Cols.EXPECTED].toString().trim();
            String desc = row[SheetConstants.Cols.DESCRIPTION].toString().trim();
            String checkType = row[SheetConstants.Cols.CHECK_TYPE].toString().trim();

            try {
                WebElement f = driver.findElement(By.id(fieldName));
                jse.executeScript("arguments[0].scrollIntoView({block:'center',behavior:'smooth'})", f);
                Thread.sleep(300);
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
                    Thread.sleep(200);
                    if (!input.isEmpty() && !input.equalsIgnoreCase("Empty"))
                        f.sendKeys(input);
                    Thread.sleep(300);
                    String actual = f.getAttribute("value");

                    switch (checkType) {
                        case "equals":
                            log(fieldName, desc, expected, actual, actual.equals(expected));
                            sa.assertEquals(actual, expected, desc);
                            break;
                        case "empty":
                            log(fieldName, desc, "Empty", actual, actual.isEmpty());
                            sa.assertTrue(actual.isEmpty(), desc);
                            break;
                        case "info":
                            logInfo(fieldName, desc, actual);
                            break;
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
        jse.executeScript("arguments[0].scrollIntoView({block:'center',behavior:'smooth'})", saveBtn);
        Thread.sleep(300);
        log(fn, "Save button visible", "true", String.valueOf(saveBtn.isDisplayed()), saveBtn.isDisplayed());
        log(fn, "Save button enabled", "true", String.valueOf(saveBtn.isEnabled()), saveBtn.isEnabled());

        // Save without data - mandatory check
        new Select(driver.findElement(By.id("caseStatusId"))).selectByIndex(0);
        Thread.sleep(300);
        driver.findElement(By.id("remarks")).clear();
        Thread.sleep(300);
        driver.findElement(By.id("saveData")).click();
        Thread.sleep(1000);
        String errorToast = getErrorToast();
        log(fn, "Save without data - mandatory check", "Error toast", errorToast.isEmpty() ? "No toast" : errorToast,
                !errorToast.isEmpty());

        // Save with valid data - select first valid option from DD
        Thread.sleep(1000);
        Select caseDD = new Select(driver.findElement(By.id("caseStatusId")));
        caseDD.selectByVisibleText("Legal");
        Thread.sleep(500);
        String selectedVal = caseDD.getFirstSelectedOption().getText().trim();
        log(fn, "DD selected for save", "Legal", selectedVal, selectedVal.equals("Legal"));

        driver.findElement(By.id("remarks")).clear();
        driver.findElement(By.id("remarks")).sendKeys("Case Status Remark");
        Thread.sleep(500);

        WebElement saveBtnFinal = driver.findElement(By.id("saveData"));
        jse.executeScript("arguments[0].scrollIntoView({block:'center',behavior:'smooth'})", saveBtnFinal);
        Thread.sleep(500);
        saveBtnFinal.click();
        Thread.sleep(500);

        String successToast = getSuccessToast();
        log(fn, "Save with valid data", "Success toast", successToast.isEmpty() ? "No toast" : successToast,
                !successToast.isEmpty());

        sa.assertAll();
    }
}
