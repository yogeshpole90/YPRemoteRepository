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
import com.ebid.lcs.utils.DBConnection;

import java.sql.ResultSet;

@Listeners(TestListener.class)
public class PhoneBookTest extends BaseTest {

    @BeforeClass
    public void setup() throws Exception {
        ExtentManager.initReport("PhoneBook");
        ExtentManager.startTest("PhoneBook - Full Validation");

        navigateToCase(ConfigManager.get("casenumber"));

        WebElement phoneBookIcon = driver
                .findElement(By.xpath("//a[contains(@data-target,'phoneBook') and contains(@title,'Phone Book')]"));
        jse.executeScript("arguments[0].scrollIntoView({block:'center'})", phoneBookIcon);
        Thread.sleep(500);
        phoneBookIcon.click();
        Thread.sleep(1000);

        logInfo("Navigation", "Opened", "PhoneBook");
    }

    @Test(priority = 1)
    public void validateAllFields() throws Exception {
        Object[][] data = ExcelReader.getByTcPrefix(SheetConstants.SHEET_PHONE_BOOK, SheetConstants.TC.PHONE_BOOK);

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
                        s.selectByVisibleText(input);
                        Thread.sleep(500);
                        String actual = s.getFirstSelectedOption().getText().trim();
                        log(fieldName, desc, expected, actual, actual.equals(expected));
                        sa.assertEquals(actual, expected, desc);
                    }
                } else {
                    f.clear();
                    Thread.sleep(200);
                    if (!input.isEmpty() && !input.equalsIgnoreCase("Empty")) {
                        f.sendKeys(input);
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
        sa.assertAll();
    }

    @Test(priority = 2)
    public void validateSave() throws Exception {
        String fn = "Save";

        WebElement saveBtn = driver.findElement(By.id("addButton"));
        jse.executeScript("arguments[0].scrollIntoView({block:'center'})", saveBtn);
        Thread.sleep(500);
        log(fn, "Save button visible", "true", String.valueOf(saveBtn.isDisplayed()), saveBtn.isDisplayed());

        saveBtn.click();
        Thread.sleep(2000);

        String toast = getSuccessToast();
        log(fn, "Save PhoneBook", "Success", toast.isEmpty() ? "No toast" : toast, !toast.isEmpty());
    }

    @Test(priority = 3, dependsOnMethods = "validateSave")
    public void validateDatabase() throws Exception {
        String today = java.time.LocalDate.now().toString();
        ResultSet rs = DBConnection.executeQuery(
            "SELECT TOP 1 * FROM D310072 ORDER BY createdDate DESC, createdTime DESC");

        if (!rs.next()) {
            log("DB", "Record found in D310072", "Yes", "No", false);
            sa.fail("No record found in D310072");
            DBConnection.close(rs);
            return;
        }

        log("DB", "Record found", "Yes", "Yes", true);

        String dbCreatedDate = rs.getString("createdDate");
        log("DB", "createdDate contains today", today, dbCreatedDate, dbCreatedDate != null && dbCreatedDate.contains(today));
        sa.assertTrue(dbCreatedDate != null && dbCreatedDate.contains(today), "createdDate should contain today");

        String dbIsActive = rs.getString("isActive");
        log("DB", "isActive", "1", dbIsActive, "1".equals(dbIsActive));
        sa.assertEquals(dbIsActive, "1", "isActive should be 1");

        String dbContactName = rs.getString("contactName");
        log("DB", "contactName", "Not Null", dbContactName, dbContactName != null && !dbContactName.isEmpty());

        String dbPhone = rs.getString("phone");
        log("DB", "phone", "Not Null", dbPhone, dbPhone != null && !dbPhone.isEmpty());

        String dbPhoneType = rs.getString("phoneType");
        log("DB", "phoneType", "Not Null", dbPhoneType, dbPhoneType != null && !dbPhoneType.isEmpty());

        logInfo("DB", "createdTime", rs.getString("createdTime"));

        DBConnection.close(rs);
        sa.assertAll();
    }
}
