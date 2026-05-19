package com.ebid.lcs.tests;

import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.Keys;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.ui.Select;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Listeners;
import org.testng.annotations.Test;

import com.ebid.lcs.base.BaseTest;
import com.ebid.lcs.config.ConfigManager;
import com.ebid.lcs.driver.DriverManager;
import com.ebid.lcs.excel.ExcelReader;
import com.ebid.lcs.excel.SheetConstants;
import com.ebid.lcs.listeners.TestListener;
import com.ebid.lcs.reporting.ExtentManager;

@Listeners(TestListener.class)
public class LegalOrderTest extends BaseTest {

    private void hideDatepicker() {
        try {
            jse.executeScript(
                    "var dp = document.querySelectorAll('.datepicker, .ui-datepicker, .daterangepicker, .bootstrap-datetimepicker-widget');"
                            +
                            "dp.forEach(function(el){ el.style.display='none'; });");
        } catch (Exception e) {
        }
        try {
            driver.findElement(By.tagName("body")).sendKeys(Keys.ESCAPE);
        } catch (Exception e) {
        }
    }

    private void scrollToSearch() {
        try {
            WebElement search = driver.findElement(By.xpath("//input[@placeholder='Search keyword here']"));
            jse.executeScript("arguments[0].scrollIntoView({block:'center',behavior:'smooth'})", search);
            Thread.sleep(1000);
        } catch (Exception e) {
        }
    }

    private void switchBackToFrame() {
        try {
            driver.switchTo().frame("getLegalDetailDataFrame");
            Thread.sleep(500);
        } catch (Exception e) {
        }
    }

    @BeforeClass(alwaysRun = true)
    public void setup() throws Exception {
        driver = DriverManager.getDriver();
        jse = (JavascriptExecutor) driver;
        act = new Actions(driver);

        ExtentManager.initReport("LegalOrder");
        ExtentManager.startTest("Legal Order - Full Validation");

        navigateToCase(ConfigManager.get("casenumber"));

        // Navigate to Legal Process tab
        WebElement legal = driver.findElement(By.xpath("//*[contains(@href,'=Legal Process')]"));
        jse.executeScript("arguments[0].scrollIntoView({block:'center',behavior:'smooth'})", legal);
        Thread.sleep(1000);
        legal.click();
        Thread.sleep(2000);

        // Legal Order tab
        WebElement loTab = driver.findElement(By.xpath("//a[contains(text(),'Legal Order')]"));
        jse.executeScript("arguments[0].scrollIntoView({block:'center',behavior:'smooth'})", loTab);
        Thread.sleep(1000);
        act.doubleClick(loTab).build().perform();
        Thread.sleep(2000);

        // Switch to frame
        driver.switchTo().frame("getLegalDetailDataFrame");
        logInfo("Frame", "Switched to", "getLegalDetailDataFrame");
    }

    @Test(priority = 1, groups = { "regression" })
    public void validateAllFields() throws Exception {
        Object[][] data = ExcelReader.getByTcPrefix(SheetConstants.SHEET_LEGAL_ORDER, SheetConstants.TC.LEGAL_ORDER);

        for (Object[] row : data) {
            String fieldName = row[SheetConstants.Cols.FIELD_NAME].toString().trim();
            String input = row[SheetConstants.Cols.INPUT].toString().trim();
            String expected = row[SheetConstants.Cols.EXPECTED].toString().trim();
            String desc = row[SheetConstants.Cols.DESCRIPTION].toString().trim();
            String checkType = row[SheetConstants.Cols.CHECK_TYPE].toString().trim();

            try {
                WebElement f = driver.findElement(By.id(fieldName));
                jse.executeScript("arguments[0].scrollIntoView({block:'center'})", f);
                Thread.sleep(300);
                String tagName = f.getTagName();

                if (tagName.equals("select")) {
                    Select s = new Select(f);
                    if (checkType.equals("info")) {
                        String actual = s.getFirstSelectedOption().getText().trim();
                        logInfo(fieldName, desc, actual);
                    } else {
                        try {
                            s.selectByVisibleText(input);
                            Thread.sleep(500);
                            String actual = s.getFirstSelectedOption().getText().trim();
                            log(fieldName, desc, expected, actual, actual.equals(expected));
                            sa.assertEquals(actual, expected, desc);
                        } catch (Exception e) {
                            log(fieldName, desc, expected, "Option not found: " + input, false);
                        }
                    }
                } else {
                    // Date or text field
                    f.clear();
                    Thread.sleep(200);
                    if (!input.isEmpty() && !input.equalsIgnoreCase("Empty")) {
                        f.sendKeys(input);
                        f.sendKeys(Keys.TAB);
                        hideDatepicker();
                    }
                    Thread.sleep(500);
                    String actual = f.getAttribute("value");

                    switch (checkType) {
                        case "equals":
                            log(fieldName, desc, expected, actual, actual.equals(expected));
                            sa.assertEquals(actual, expected, desc);
                            break;
                        case "notEquals":
                            log(fieldName, desc, "Not " + input, actual, !actual.equals(input));
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

    @Test(priority = 2, groups = { "regression" })
    public void validateSave() throws Exception {
        String fn = "Save";

        WebElement saveBtn = driver.findElement(By.id("saveBtn"));
        jse.executeScript("arguments[0].scrollIntoView({block:'center'})", saveBtn);
        Thread.sleep(500);
        log(fn, "Save button visible", "true", String.valueOf(saveBtn.isDisplayed()), saveBtn.isDisplayed());
        log(fn, "Save button enabled", "true", String.valueOf(saveBtn.isEnabled()), saveBtn.isEnabled());

        saveBtn.click();
        Thread.sleep(500);
        scrollToSearch();
        String saveToast = getSuccessToast();
        log(fn, "Save Legal Order", "Success", saveToast.isEmpty() ? "No toast" : saveToast, !saveToast.isEmpty());

        // Switch back to frame for View/Edit/Delete
        switchBackToFrame();

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
            Thread.sleep(500);
            scrollToSearch();
            String updateToast = getSuccessToast();
            log("Edit", "Save after Edit", "Updated", updateToast.isEmpty() ? "No toast" : updateToast,
                    !updateToast.isEmpty());
            switchBackToFrame();
        } catch (Exception e) {
            log("Edit", "Edit button", "Found", "Not found", false);
        }

        // Delete
        try {
            WebElement deleteBtn = driver.findElement(By.xpath("//*[contains(@class,'deleteBtn')]"));
            jse.executeScript("arguments[0].scrollIntoView({block:'center'})", deleteBtn);
            Thread.sleep(500);
            deleteBtn.click();
            Thread.sleep(500);
            scrollToSearch();
            String deleteToast = getSuccessToast();
            log("Delete", "Click Delete button", "Deleted", deleteToast.isEmpty() ? "No toast" : deleteToast,
                    !deleteToast.isEmpty());
        } catch (Exception e) {
            log("Delete", "Delete button", "Found", "Not found", false);
        }

        sa.assertAll();
    }
}
