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
public class DocUploadTest extends BaseTest {

    @BeforeClass
    public void setup() throws Exception {
        ExtentManager.initReport("DocUpload");
        ExtentManager.startTest("Document Upload - Full Validation");

        navigateToCase(ConfigManager.get("casenumber"));

        WebElement docTab = driver.findElement(By.xpath("//*[contains(@href,'activeTab=Document')]"));
        jse.executeScript("arguments[0].scrollIntoView({block:'center',behavior:'smooth'})", docTab);
        Thread.sleep(1000);
        docTab.click();
        Thread.sleep(2000);

        driver.switchTo().frame("documentUploadPageFrame");
        logInfo("Frame", "Switched to", "documentUploadPageFrame");
    }

    @Test(priority = 1)
    public void validateActionNameDD() throws Exception {
        WebDriverWait wait = new WebDriverWait(driver, 15);
        WebElement dd = wait.until(ExpectedConditions.presenceOfElementLocated(By.id("actionName")));
        Thread.sleep(1000);
        Select select = new Select(dd);
        String fn = "Action Name DD";

        log(fn, "Displayed", "true", String.valueOf(dd.isDisplayed()), dd.isDisplayed());
        log(fn, "Enabled", "true", String.valueOf(dd.isEnabled()), dd.isEnabled());

        // Log all available options
        for (WebElement opt : select.getOptions()) {
            logInfo(fn, "Available option", opt.getText().trim());
        }

        Object[][] data = ExcelReader.getByTcPrefix(SheetConstants.SHEET_DOC_UPLOAD, SheetConstants.TC.DOC_UPLOAD);
        String lastInput = "";

        for (Object[] row : data) {
            String fieldName = row[SheetConstants.Cols.FIELD_NAME].toString();
            if (!fieldName.equals("actionName")) continue;

            String input = row[SheetConstants.Cols.INPUT].toString().trim();
            String expected = row[SheetConstants.Cols.EXPECTED].toString().trim();
            String desc = row[SheetConstants.Cols.DESCRIPTION].toString();
            String checkType = row[SheetConstants.Cols.CHECK_TYPE].toString();
            lastInput = input;

            try {
                select.selectByVisibleText(input);
                Thread.sleep(500);
                String actual = select.getFirstSelectedOption().getText().trim();

                switch (checkType) {
                    case "equals":
                        log(fn, desc, expected, actual, actual.equals(expected));
                        sa.assertEquals(actual, expected, desc);
                        break;
                }
            } catch (Exception e) {
                log(fn, desc, expected, "Option not found: " + input, false);
            }
        }

        // Keep last valid option selected for save
        try {
            select.selectByVisibleText(lastInput);
        } catch (Exception e) {
            select.selectByIndex(1);
        }
        Thread.sleep(500);
    }

    @Test(priority = 2)
    public void validateDocumentName() throws Exception {
        WebElement f = driver.findElement(By.id("documentName"));
        String fn = "Document Name";

        log(fn, "Displayed", "true", String.valueOf(f.isDisplayed()), f.isDisplayed());
        log(fn, "Enabled", "true", String.valueOf(f.isEnabled()), f.isEnabled());

        Object[][] data = ExcelReader.getByTcPrefix(SheetConstants.SHEET_DOC_UPLOAD, SheetConstants.TC.DOC_UPLOAD);

        for (Object[] row : data) {
            String fieldName = row[SheetConstants.Cols.FIELD_NAME].toString();
            if (!fieldName.equals("documentName")) continue;

            String input = row[SheetConstants.Cols.INPUT].toString().trim();
            String expected = row[SheetConstants.Cols.EXPECTED].toString().trim();
            String desc = row[SheetConstants.Cols.DESCRIPTION].toString();
            String checkType = row[SheetConstants.Cols.CHECK_TYPE].toString();

            f.clear();
            if (!input.isEmpty() && !input.equalsIgnoreCase("Empty")) f.sendKeys(input);
            String actual = f.getAttribute("value");

            switch (checkType) {
                case "equals":
                    log(fn, desc, expected, actual, actual.equals(expected));
                    sa.assertEquals(actual, expected, desc);
                    break;
                case "empty":
                    log(fn, desc, "Empty", actual, actual.isEmpty());
                    sa.assertTrue(actual.isEmpty(), desc);
                    break;
            }
        }
    }

    @Test(priority = 3)
    public void validateFileUpload() throws Exception {
        WebElement f = driver.findElement(By.id("documentData"));
        String fn = "File Upload";

        log(fn, "Displayed", "true", String.valueOf(f.isDisplayed()), f.isDisplayed());
        log(fn, "Type is file", "file", f.getAttribute("type"), f.getAttribute("type").equals("file"));

        f.sendKeys("C:\\Users\\Yogesh.Pole\\Music\\COLLATERAL_SEIZED_LETTER.pdf");
        Thread.sleep(1000);
        String val = f.getAttribute("value");
        log(fn, "File uploaded", "Not empty", val, !val.isEmpty());
    }

    @Test(priority = 4)
    public void validateResetButton() throws Exception {
        WebElement resetBtn = driver.findElement(By.xpath("//button[contains(text(),'Reset') or @id='resetData']"));
        log("Reset", "Displayed", "true", String.valueOf(resetBtn.isDisplayed()), resetBtn.isDisplayed());
        log("Reset", "Enabled", "true", String.valueOf(resetBtn.isEnabled()), resetBtn.isEnabled());
    }

    @Test(priority = 5)
    public void validateSave() throws Exception {
        // Re-select action name for save
        Select select = new Select(driver.findElement(By.id("actionName")));
        select.selectByIndex(1);
        Thread.sleep(500);

        WebElement saveBtn = driver.findElement(By.id("saveData"));
        log("Save", "Displayed", "true", String.valueOf(saveBtn.isDisplayed()), saveBtn.isDisplayed());
        saveBtn.click();
        Thread.sleep(2000);
        String toast = getSuccessToast();
        log("Save", "Save record", "Success", toast.isEmpty() ? "No toast" : toast, !toast.isEmpty());
        sa.assertAll();
    }
}
