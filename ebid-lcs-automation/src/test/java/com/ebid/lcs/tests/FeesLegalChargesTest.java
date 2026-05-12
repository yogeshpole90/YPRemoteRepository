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

import com.ebid.lcs.base.BaseTest;
import com.ebid.lcs.config.ConfigManager;
import com.ebid.lcs.excel.ExcelReader;
import com.ebid.lcs.excel.SheetConstants;
import com.ebid.lcs.listeners.TestListener;
import com.ebid.lcs.reporting.ExtentManager;

@Listeners(TestListener.class)
public class FeesLegalChargesTest extends BaseTest {

    @BeforeClass
    public void setup() throws Exception {
        ExtentManager.initReport("FeesLegalCharges");
        ExtentManager.startTest("Fees & Legal Charges - Full Validation");

        navigateToCase(ConfigManager.get("casenumber"));

        // Navigate to Legal Process tab
        WebElement legal = driver.findElement(By.xpath("//*[contains(@href,'=Legal Process')]"));
        jse.executeScript("arguments[0].scrollIntoView({block:'center',behavior:'smooth'})", legal);
        Thread.sleep(1000);
        legal.click();
        Thread.sleep(2000);

        // Click Fees & Charges sub-tab
        WebDriverWait wait = new WebDriverWait(driver, 15);
        WebElement flcTab = wait.until(ExpectedConditions.elementToBeClickable(By.xpath("//a[contains(text(),'Fees') and contains(text(),'Charge')]")));
        jse.executeScript("arguments[0].scrollIntoView({block:'center'})", flcTab);
        Thread.sleep(500);
        act.doubleClick(flcTab).build().perform();
        Thread.sleep(2000);

        // Switch to frame
        WebElement frame = wait.until(ExpectedConditions.presenceOfElementLocated(By.id("viewFessAndChargeFrame")));
        jse.executeScript("arguments[0].scrollIntoView({block:'center'})", frame);
        Thread.sleep(500);
        driver.switchTo().frame("viewFessAndChargeFrame");
        logInfo("Frame", "Switched to", "viewFessAndChargeFrame");
    }

    @Test(priority = 1)
    public void validateAllFields() throws Exception {
        Object[][] data = ExcelReader.getByTcPrefix(SheetConstants.SHEET_FEES_LEGAL, SheetConstants.TC.FEES_LEGAL);

        for (Object[] row : data) {
            String fieldName = row[SheetConstants.Cols.FIELD_NAME].toString().trim();
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
                    sa.assertEquals(actual, expected, desc);
                } catch (Exception e) {
                    log(fieldName, desc, expected, "Option not found: " + input, false);
                }
            } else {
                f.clear();
                if (!input.isEmpty() && !input.equalsIgnoreCase("Empty")) f.sendKeys(input);
                if (fieldName.toLowerCase().contains("date")) {
                    f.sendKeys(Keys.TAB);
                    Thread.sleep(300);
                    f.sendKeys(Keys.ESCAPE);
                    Thread.sleep(200);
                }
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

    @Test(priority = 2)
    public void validateSave() throws Exception {
        WebElement saveBtn = driver.findElement(By.id("saveData"));
        jse.executeScript("arguments[0].scrollIntoView({block:'center'})", saveBtn);
        Thread.sleep(500);
        log("Save", "Displayed", "true", String.valueOf(saveBtn.isDisplayed()), saveBtn.isDisplayed());
        log("Save", "Enabled", "true", String.valueOf(saveBtn.isEnabled()), saveBtn.isEnabled());

        saveBtn.click();
        Thread.sleep(2000);
        String toast = getSuccessToast();
        log("Save", "Save Fees & Legal Charges", "Success", toast.isEmpty() ? "No toast" : toast, !toast.isEmpty());
        sa.assertAll();
    }
}
