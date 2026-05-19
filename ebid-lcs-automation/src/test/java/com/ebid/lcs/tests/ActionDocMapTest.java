package com.ebid.lcs.tests;

import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.Select;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Listeners;
import org.testng.annotations.Test;

import com.ebid.lcs.base.BaseTest;
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

        // Navigate to Action Doc Map
        driver.findElement(By.xpath("//*[@class='item-nav']/div")).click();
        Thread.sleep(500);
        WebElement adm = driver.findElement(By.xpath("//*[@id='ACTIONDOCMAP']/a"));
        jse.executeScript("arguments[0].scrollIntoView({block:'center'})", adm);
        Thread.sleep(300);
        adm.click();
        Thread.sleep(1000);

        logInfo("Navigation", "Navigated to", "Action Doc Map (infraadmin)");
    }

    @Test(priority = 1)
    public void validateAllFields() throws Exception {
        WebElement addBtn = driver.findElement(By.id("addButton"));
        addBtn.click();
        Thread.sleep(1000);
        log("Add Button", "Click Add", "Clicked", "Clicked", true);

        Object[][] data = ExcelReader.getByTcPrefix(SheetConstants.SHEET_ACTION_DOC_MAP, SheetConstants.TC.ACTION_DOC_MAP);

        for (Object[] row : data) {
            String fieldName = row[SheetConstants.Cols.FIELD_NAME].toString().trim();
            String input = row[SheetConstants.Cols.INPUT].toString().trim();
            String expected = row[SheetConstants.Cols.EXPECTED].toString().trim();
            String desc = row[SheetConstants.Cols.DESCRIPTION].toString();
            String checkType = row[SheetConstants.Cols.CHECK_TYPE].toString();

            try {
                WebElement f = driver.findElement(By.id(fieldName));
                jse.executeScript("arguments[0].scrollIntoView({block:'center'})", f);
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
                    if (!input.isEmpty() && !input.equalsIgnoreCase("Empty")) f.sendKeys(input);
                    String actual = f.getAttribute("value");

                    switch (checkType) {
                        case "equals": log(fieldName, desc, expected, actual, actual.equals(expected)); sa.assertEquals(actual, expected, desc); break;
                        case "empty": log(fieldName, desc, "Empty", actual, actual.isEmpty()); sa.assertTrue(actual.isEmpty(), desc); break;
                        case "info": logInfo(fieldName, desc, actual); break;
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
        WebElement saveBtn = driver.findElement(By.id("save"));
        jse.executeScript("arguments[0].scrollIntoView({block:'center'})", saveBtn);
        Thread.sleep(500);
        log("Save", "Displayed", "true", String.valueOf(saveBtn.isDisplayed()), saveBtn.isDisplayed());
        saveBtn.click();
        Thread.sleep(2000);
        String toast = getSuccessToast();
        log("Save", "Save Action Doc Map", "Success", toast.isEmpty() ? "No toast" : toast, !toast.isEmpty());
        sa.assertAll();
    }
}