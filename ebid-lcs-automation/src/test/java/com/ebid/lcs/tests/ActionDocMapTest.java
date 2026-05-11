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
public class ActionDocMapTest extends BaseTest {

    @BeforeClass
    public void setup() throws Exception {
        ExtentManager.initReport("ActionDocMap");
        ExtentManager.startTest("Action Doc Map - Full Validation");

        driver.findElement(By.xpath("//*[@class='item-nav']/div/div")).click();
        Thread.sleep(500);
        driver.findElement(By.xpath("(//li[@id = 'COMMONCOLLECTORLIST'])/a")).click();
        Thread.sleep(500);
        driver.findElement(By.xpath("//*[contains(@href,'menuCode=ACTIONDOCMAP')]")).click();
        Thread.sleep(2000);

        logInfo("Navigation", "Navigated to", "Action Doc Map");
    }

    @Test(priority = 1)
    public void validateAllFields() throws Exception {
        WebElement addBtn = driver.findElement(By.id("addBtn"));
        addBtn.click();
        Thread.sleep(1000);
        log("Add Button", "Click Add", "Clicked", "Clicked", true);

        Object[][] data = ExcelReader.getByTcPrefix(SheetConstants.SHEET_ACTION_DOC_MAP, SheetConstants.TC.ACTION_DOC_MAP);

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
                String actual = f.getAttribute("value");

                switch (checkType) {
                    case "equals": log(fieldName, desc, expected, actual, actual.equals(expected)); sa.assertEquals(actual, expected, desc); break;
                    case "empty": log(fieldName, desc, "Empty", actual, actual.isEmpty()); break;
                }
            }
        }
    }

    @Test(priority = 2)
    public void validateSave() throws Exception {
        WebElement saveBtn = driver.findElement(By.id("saveData"));
        log("Save", "Displayed", "true", String.valueOf(saveBtn.isDisplayed()), saveBtn.isDisplayed());
        saveBtn.click();
        Thread.sleep(2000);
        String toast = getSuccessToast();
        log("Save", "Save Action Doc Map", "Success", toast.isEmpty() ? "No toast" : toast, !toast.isEmpty());
        sa.assertAll();
    }
}
