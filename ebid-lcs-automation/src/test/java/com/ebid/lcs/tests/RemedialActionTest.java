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
public class RemedialActionTest extends BaseTest {

    @BeforeClass
    public void setup() throws Exception {
        ExtentManager.initReport("RemedialAction");
        ExtentManager.startTest("Remedial Action - Full Validation");

        navigateToCase(ConfigManager.get("casenumber"));

        WebElement remedial = driver.findElement(By.xpath("(//*[contains(@href,'=Remedial Action')])[1]"));
        jse.executeScript("arguments[0].scrollIntoView({block:'center',behavior:'smooth'})", remedial);
        Thread.sleep(1000);
        remedial.click();
        Thread.sleep(2000);

        driver.switchTo().frame("caseMstListPageFrame");
        logInfo("Frame", "Switched to", "caseMstListPageFrame");
    }

    @Test(priority = 1)
    public void validateAllFields() throws Exception {
        Object[][] data = ExcelReader.getByTcPrefix(SheetConstants.SHEET_REMEDIAL, SheetConstants.TC.REMEDIAL);

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
        WebElement saveBtn = driver.findElement(By.id("save"));
        jse.executeScript("arguments[0].scrollIntoView({block:'center'})", saveBtn);
        Thread.sleep(500);
        log("Save", "Save button visible", "true", String.valueOf(saveBtn.isDisplayed()), saveBtn.isDisplayed());
        log("Save", "Save button enabled", "true", String.valueOf(saveBtn.isEnabled()), saveBtn.isEnabled());

        saveBtn.click();
        Thread.sleep(2000);

        // Success message inside frame
        List<WebElement> successMsg = driver.findElements(By.xpath("//*[contains(text(), 'Saved Successfully')]"));
        boolean msgFound = successMsg.size() > 0 && successMsg.get(0).isDisplayed();
        log("Save", "Save Remedial Action", "Saved Successfully",
                msgFound ? successMsg.get(0).getText().trim() : "No message", msgFound);

        Thread.sleep(2000);

        // Grid - View
        List<WebElement> viewBtns = driver.findElements(By.xpath("//a[contains(text(),'View')]"));
        log("View Button", "View buttons found", ">0", String.valueOf(viewBtns.size()), viewBtns.size() > 0);
        if (viewBtns.size() > 0) {
            jse.executeScript("arguments[0].scrollIntoView({block:'center'})", viewBtns.get(viewBtns.size() - 1));
            Thread.sleep(500);
            viewBtns.get(viewBtns.size() - 1).click();
            Thread.sleep(2000);
            log("View", "Click last View", "Opened", "Clicked", true);

            String actVal = driver.findElement(By.id("actionId")).getAttribute("value");
            String cmtVal = driver.findElement(By.id("commments")).getAttribute("value");
            log("View Data", "Action Name populated", "Not empty", actVal.isEmpty() ? "EMPTY" : actVal,
                    !actVal.isEmpty());
            log("View Data", "Comments populated", "Not empty", cmtVal.isEmpty() ? "EMPTY" : cmtVal, !cmtVal.isEmpty());
        }

        sa.assertAll();
    }

    @Test(priority = 3)
    public void validateDatabase() throws Exception {
        String today = java.time.LocalDate.now().toString();
        ResultSet rs = DBConnection.executeQuery(
            "SELECT TOP 1 * FROM d310047 ORDER BY createdDate DESC, createdTime DESC");

        if (!rs.next()) {
            log("DB", "Record found in d310047", "Yes", "No", false);
            sa.fail("No record found in d310047");
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

        String dbActionId = rs.getString("actionId");
        log("DB", "actionId", "Not Null", dbActionId, dbActionId != null && !dbActionId.isEmpty());

        logInfo("DB", "createdTime", rs.getString("createdTime"));

        DBConnection.close(rs);
        sa.assertAll();
    }
}
