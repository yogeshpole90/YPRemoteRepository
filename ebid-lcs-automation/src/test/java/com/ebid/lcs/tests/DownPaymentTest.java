package com.ebid.lcs.tests;

import org.openqa.selenium.By;
import org.openqa.selenium.Keys;
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
public class DownPaymentTest extends BaseTest {

    @BeforeClass
    public void setup() throws Exception {
        ExtentManager.initReport("Downpayment");
        ExtentManager.startTest("Downpayment - Full Validation");

        navigateToCase(ConfigManager.get("casenumber"));

        WebElement remedial = driver.findElement(By.xpath("(//*[contains(@href,'=Remedial Action')])[1]"));
        jse.executeScript("arguments[0].scrollIntoView({block:'center',behavior:'smooth'})", remedial);
        Thread.sleep(1000);
        remedial.click();
        Thread.sleep(2000);

        WebElement ptpTab = driver.findElement(By.xpath("//*[contains(text(),'Promise to pay')]"));
        act.doubleClick(ptpTab).build().perform();
        jse.executeScript("window.scrollBy(0,3000)");
        Thread.sleep(2000);

        driver.switchTo().frame("fetchPTPMstTabFrame");
        logInfo("Frame", "Switched to", "fetchPTPMstTabFrame");
    }

    @Test(priority = 1)
    public void validateAllFields() throws Exception {
        Object[][] data = ExcelReader.getByTcPrefix(SheetConstants.SHEET_DOWNPAYMENT, SheetConstants.TC.DOWNPAYMENT);

        for (Object[] row : data) {
            String fieldName = row[SheetConstants.Cols.FIELD_NAME].toString();
            String input = row[SheetConstants.Cols.INPUT].toString();
            String expected = row[SheetConstants.Cols.EXPECTED].toString();
            String desc = row[SheetConstants.Cols.DESCRIPTION].toString();
            String checkType = row[SheetConstants.Cols.CHECK_TYPE].toString();

            WebElement f = driver.findElement(By.id(fieldName));
            String tagName = f.getTagName();

            if (tagName.equals("select")) {
                Select s = new Select(f);
                s.selectByVisibleText(input);
                Thread.sleep(500);
                String actual = s.getFirstSelectedOption().getText().trim();
                log(fieldName, desc, expected, actual, actual.equals(expected));
                sa.assertEquals(actual, expected, desc);
            } else {
                f.clear();
                if (!input.isEmpty()) f.sendKeys(input);
                if (fieldName.toLowerCase().contains("date")) f.sendKeys(Keys.TAB);
                String actual = f.getAttribute("value");

                switch (checkType) {
                    case "equals": log(fieldName, desc, expected, actual, actual.equals(expected)); sa.assertEquals(actual, expected, desc); break;
                    case "notEquals": log(fieldName, desc, "Not " + input, actual, !actual.equals(input)); break;
                    case "empty": log(fieldName, desc, "Empty", actual, actual.isEmpty()); break;
                }
            }
        }
    }

    @Test(priority = 2)
    public void validateAddSave() throws Exception {
        WebElement addBtn = driver.findElement(By.id("add"));
        jse.executeScript("arguments[0].scrollIntoView({block:'center'})", addBtn);
        addBtn.click();
        log("Add Button", "Click Add", "Clicked", "Clicked", true);
        Thread.sleep(2000);

        WebElement saveBtn = driver.findElement(By.id("saveData"));
        jse.executeScript("arguments[0].scrollIntoView({block:'center'})", saveBtn);
        log("Save", "Displayed", "true", String.valueOf(saveBtn.isDisplayed()), saveBtn.isDisplayed());
        saveBtn.click();
        Thread.sleep(1000);

        try {
            String alertText = driver.switchTo().alert().getText();
            log("Save", "Alert validation", "Alert shown", alertText, true);
            driver.switchTo().alert().accept();
            Thread.sleep(500);
        } catch (Exception e) {
            // No alert - check toast
            Thread.sleep(1000);
            String toast = getSuccessToast();
            log("Save", "Save Downpayment", "Success", toast.isEmpty() ? "No toast" : toast, !toast.isEmpty());
        }
        sa.assertAll();
    }
}
