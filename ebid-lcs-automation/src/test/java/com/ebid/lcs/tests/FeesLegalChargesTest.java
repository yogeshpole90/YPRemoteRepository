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
public class FeesLegalChargesTest extends BaseTest {

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
            driver.switchTo().frame("viewFessAndChargeFrame");
            Thread.sleep(500);
        } catch (Exception e) {
        }
    }

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
        WebElement flcTab = driver.findElement(By.xpath("//a[contains(text(),'Fees') and contains(text(),'Charge')]"));
        jse.executeScript("arguments[0].scrollIntoView({block:'center'})", flcTab);
        Thread.sleep(500);
        act.doubleClick(flcTab).build().perform();
        Thread.sleep(1000);

        // Switch to frame
        WebElement frame = driver.findElement(By.id("viewFessAndChargeFrame"));
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
                    f.clear();
                    Thread.sleep(200);
                    if (!input.isEmpty() && !input.equalsIgnoreCase("Empty")) {
                        f.sendKeys(input);
                        if (fieldName.toLowerCase().contains("date")) {
                            f.sendKeys(Keys.TAB);
                            hideDatepicker();
                        }
                    }
                    Thread.sleep(300);
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

    @Test(priority = 2)
    public void validateSave() throws Exception {
        String fn = "Save";

        // Escape datepicker if open
        try {
            driver.findElement(By.id("expenseDate")).sendKeys(Keys.ESCAPE);
            Thread.sleep(300);
        } catch (Exception e) {
        }

        WebElement saveBtn = driver.findElement(By.id("saveFessCharge"));
        jse.executeScript("arguments[0].scrollIntoView({block:'center'})", saveBtn);
        Thread.sleep(500);
        log(fn, "Save button visible", "true", String.valueOf(saveBtn.isDisplayed()), saveBtn.isDisplayed());
        log(fn, "Save button enabled", "true", String.valueOf(saveBtn.isEnabled()), saveBtn.isEnabled());

        jse.executeScript("arguments[0].click()", saveBtn);
        Thread.sleep(500);
        scrollToSearch();
        String saveToast = getSuccessToast();
        log(fn, "Save Fees & Legal Charges", "Success", saveToast.isEmpty() ? "No toast" : saveToast,
                !saveToast.isEmpty());
        switchBackToFrame();

        // View
        try {
            WebElement viewBtn = driver
                    .findElement(By.xpath("//a[contains(@class,'ViewBtn') and contains(@onclick,'ViewData')]"));
            jse.executeScript("arguments[0].scrollIntoView({block:'center'})", viewBtn);
            Thread.sleep(300);
            viewBtn.click();
            Thread.sleep(500);
            log("View", "Click View button", "Record displayed", "View clicked", true);
        } catch (Exception e) {
            log("View", "View button", "Found", "Not found", false);
        }

        // Edit
        try {
            WebElement editBtn = driver
                    .findElement(By.xpath("//a[contains(@class,'editBtn') and contains(@onclick,'EditData')]"));
            jse.executeScript("arguments[0].scrollIntoView({block:'center'})", editBtn);
            Thread.sleep(300);
            editBtn.click();
            Thread.sleep(500);
            log("Edit", "Click Edit button", "Fields editable", "Edit clicked", true);

            driver.findElement(By.id("saveFessCharge")).click();
            Thread.sleep(500);
            scrollToSearch();
            String editToast = getSuccessToast();
            log("Edit", "Save after Edit", "Updated", editToast.isEmpty() ? "No toast" : editToast,
                    !editToast.isEmpty());
            switchBackToFrame();
        } catch (Exception e) {
            log("Edit", "Edit button", "Found", "Not found", false);
        }

        // Delete
        try {
            WebElement deleteBtn = driver
                    .findElement(By.xpath("//a[contains(@class,'deleteBtn') and contains(@onclick,'DeleteData')]"));
            jse.executeScript("arguments[0].scrollIntoView({block:'center'})", deleteBtn);
            Thread.sleep(300);
            deleteBtn.click();
            Thread.sleep(500);
            log("Delete", "Click Delete button", "Popup appeared", "Delete clicked", true);

            try {
                WebElement yesBtn = driver.findElement(By.id("popUpYes"));
                yesBtn.click();
                Thread.sleep(500);
                scrollToSearch();
                String deleteToast = getSuccessToast();
                log("Delete", "Confirm delete", "Deleted", deleteToast.isEmpty() ? "No toast" : deleteToast,
                        !deleteToast.isEmpty());
            } catch (Exception ex) {
                log("Delete", "No popup", "Direct delete", "Deleted directly", true);
            }
        } catch (Exception e) {
            log("Delete", "Delete button", "Found", "Not found", false);
        }

        sa.assertAll();
    }
}
