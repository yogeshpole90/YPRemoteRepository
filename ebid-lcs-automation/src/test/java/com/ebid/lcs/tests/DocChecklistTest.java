package com.ebid.lcs.tests;

import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.Select;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Listeners;
import org.testng.annotations.Test;

import java.util.List;

import com.ebid.lcs.base.BaseTest;
import com.ebid.lcs.config.ConfigManager;
import com.ebid.lcs.excel.ExcelReader;
import com.ebid.lcs.excel.SheetConstants;
import com.ebid.lcs.listeners.TestListener;
import com.ebid.lcs.reporting.ExtentManager;
import com.ebid.lcs.utils.DBConnection;

import java.sql.ResultSet;

@Listeners(TestListener.class)
public class DocChecklistTest extends BaseTest {

    @BeforeClass
    public void setup() throws Exception {
        ExtentManager.initReport("DocChecklist");
        ExtentManager.startTest("Document Checklist - Full Validation");

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
    public void validateAllFields() throws Exception {
        Object[][] data = ExcelReader.getByTcPrefix(SheetConstants.SHEET_DOC_UPLOAD, SheetConstants.TC.DOC_UPLOAD);

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
                String type = f.getAttribute("type");

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
                } else if (type != null && type.equals("file")) {
                    // File upload - path from config using input as key
                    String filePath = ConfigManager.get(input);
                    f.sendKeys(filePath);
                    Thread.sleep(1000);
                    String actual = f.getAttribute("value");
                    log(fieldName, desc, "Not empty", actual, !actual.isEmpty());
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
    }

    @Test(priority = 2)
    public void validateSave() throws Exception {
        WebElement saveBtn = driver.findElement(By.id("saveData"));
        jse.executeScript("arguments[0].scrollIntoView({block:'center'})", saveBtn);
        Thread.sleep(500);
        log("Save", "Save button visible", "true", String.valueOf(saveBtn.isDisplayed()), saveBtn.isDisplayed());
        log("Save", "Save button enabled", "true", String.valueOf(saveBtn.isEnabled()), saveBtn.isEnabled());

        saveBtn.click();
        Thread.sleep(2000);

        // Scroll to search to capture toast
        try {
            WebElement search = driver.findElement(By.cssSelector("input[placeholder='Search keyword here']"));
            jse.executeScript("arguments[0].scrollIntoView({block:'center'})", search);
            Thread.sleep(500);
        } catch (Exception e) {
        }

        String toast = getSuccessToast();
        String docName = driver.findElement(By.id("documentName")).getAttribute("value");
        String expectedToast = "Document of Name: \"" + docName + "\" Uploaded Successfully";
        log("Save", "Save Document", expectedToast, toast.isEmpty() ? "No toast" : toast, toast.contains(docName));

        // Grid - Delete
        Thread.sleep(2000);
        List<WebElement> deleteBtns = driver.findElements(By.xpath("//a[contains(@class,'DeleteBtn')]"));
        log("Delete Button", "Delete buttons found", ">0", String.valueOf(deleteBtns.size()), deleteBtns.size() > 0);
        if (deleteBtns.size() > 0) {
            jse.executeScript("arguments[0].scrollIntoView({block:'center'})", deleteBtns.get(deleteBtns.size() - 1));
            Thread.sleep(500);
            jse.executeScript("arguments[0].click()", deleteBtns.get(deleteBtns.size() - 1));
            Thread.sleep(1000);
            log("Delete Button", "Click last Delete", "Triggered", "Clicked", true);
            try {
                driver.findElement(By.id("popUpYes")).click();
                Thread.sleep(1000);
                try {
                    WebElement search = driver.findElement(By.cssSelector("input[placeholder='Search keyword here']"));
                    jse.executeScript("arguments[0].scrollIntoView({block:'center'})", search);
                    Thread.sleep(500);
                } catch (Exception ex) {
                }
                String deleteToast = getSuccessToast();
                log("Delete", "Confirm delete", "Success", deleteToast.isEmpty() ? "No toast" : deleteToast,
                        !deleteToast.isEmpty());
            } catch (Exception e) {
                try {
                    driver.switchTo().alert().accept();
                    Thread.sleep(500);
                } catch (Exception e2) {
                }
                log("Delete", "Confirm delete", "Deleted", "Alert/Popup handled", true);
            }
        }

        sa.assertAll();
    }

    @Test(priority = 3)
    public void validateDatabase() throws Exception {
        String today = java.time.LocalDate.now().toString();
        String query = "SELECT TOP 1 * FROM D320108 ORDER BY createdDate DESC, createdTime DESC";
        ResultSet rs = DBConnection.executeQuery(query);

        if (rs == null || !rs.next()) {
            log("DB", "Record found in D320108", "Yes", "No", false);
            sa.fail("No record found in D320108");
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

        String dbCaseNo = rs.getString("caseNo");
        log("DB", "caseNo", "CASE_0005282011204001137", dbCaseNo, dbCaseNo != null && dbCaseNo.contains("CASE_0005282011204001137"));

        logInfo("DB", "createdTime", rs.getString("createdTime"));

        DBConnection.close(rs);
        sa.assertAll();
    }
}
