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

import java.time.Duration;
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
public class DemandLetterTest extends BaseTest {

    private static final String DL_PREFIX = "SDL";
    private WebDriverWait wait;

    private void hideDatepicker() {
        try {
            jse.executeScript(
                "var dp = document.querySelectorAll('.datepicker, .ui-datepicker, .daterangepicker, .bootstrap-datetimepicker-widget');" +
                "dp.forEach(function(el){ el.style.display='none'; });"
            );
        } catch (Exception e) {}
        try { driver.findElement(By.tagName("body")).sendKeys(Keys.ESCAPE); } catch (Exception e) {}
    }

    private void setDateField(WebElement f, String input) throws Exception {
        jse.executeScript("arguments[0].value=''", f);
        Thread.sleep(200);
        if (!input.isEmpty() && !input.equalsIgnoreCase("Empty")) {
            f.click();
            Thread.sleep(200);
            f.sendKeys(input);
            Thread.sleep(300);
            f.sendKeys(Keys.TAB);
            Thread.sleep(300);
        }
        hideDatepicker();
        Thread.sleep(300);
    }

    private void scrollToSearch() {
        try {
            WebElement search = driver.findElement(By.xpath("//input[@type='search' and @aria-controls='dt-basicDetails']"));
            jse.executeScript("arguments[0].scrollIntoView({block:'center',behavior:'smooth'})", search);
            Thread.sleep(1000);
        } catch (Exception e) {}
    }

    @BeforeClass
    public void setup() throws Exception {
        ExtentManager.initReport("DemandLetter");
        ExtentManager.startTest("Demand Letter - CRUD Validation");

        wait = new WebDriverWait(driver, Duration.ofSeconds(15));
        navigateToCase(ConfigManager.get("casenumber"));

        WebElement docTab = wait.until(ExpectedConditions.elementToBeClickable(
            By.xpath("//*[contains(@href,'activeTab=Document')]")));
        jse.executeScript("arguments[0].scrollIntoView({block:'center',behavior:'smooth'})", docTab);
        Thread.sleep(1000);
        docTab.click();
        Thread.sleep(2000);

        WebElement dlTab = wait.until(ExpectedConditions.elementToBeClickable(
            By.xpath("//a[contains(text(),'Demand Letter')]")));
        jse.executeScript("arguments[0].scrollIntoView({block:'center',behavior:'smooth'})", dlTab);
        Thread.sleep(1000);
        act.doubleClick(dlTab).build().perform();
        Thread.sleep(2000);
        logInfo("Navigation", "Navigated to", "Demand Letter Tab");
    }

    // ==================== CREATE ====================
    @Test(priority = 1)
    public void validateCreate() throws Exception {
        WebElement frame = wait.until(ExpectedConditions.presenceOfElementLocated(
            By.id("addNewDemandLetterFrame")));
        driver.switchTo().frame(frame);
        logInfo("Frame", "Switched to", "addNewDemandLetterFrame");

        Object[][] data = ExcelReader.getByTcPrefix(SheetConstants.SHEET_DEMAND_LETTER, SheetConstants.TC.DEMAND_LETTER);

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
                        boolean selected = false;
                        List<WebElement> options = s.getOptions();
                        for (WebElement opt : options) {
                            if (opt.getText().trim().equalsIgnoreCase(input.trim())) {
                                opt.click();
                                selected = true;
                                break;
                            }
                        }
                        Thread.sleep(500);
                        if (selected) {
                            String actual = s.getFirstSelectedOption().getText().trim();
                            log(fieldName, desc, expected, actual, actual.equalsIgnoreCase(expected));
                            sa.assertEquals(actual.toLowerCase(), expected.toLowerCase(), desc);
                        } else {
                            log(fieldName, desc, expected, "Option not found: " + input, false);
                            sa.fail("Option not found: " + input + " for field: " + fieldName);
                        }
                    }
                } else if (tagName.equals("textarea")) {
                    f.clear();
                    Thread.sleep(200);
                    if (!input.isEmpty() && !input.equalsIgnoreCase("Empty")) {
                        f.sendKeys(input);
                    }
                    Thread.sleep(300);
                    String actual = f.getAttribute("value");
                    switch (checkType) {
                        case "equals": log(fieldName, desc, expected, actual, actual.equals(expected)); sa.assertEquals(actual, expected, desc); break;
                        case "empty": log(fieldName, desc, "Empty", actual, actual.isEmpty()); sa.assertTrue(actual.isEmpty(), desc); break;
                        case "info": logInfo(fieldName, desc, actual); break;
                    }
                } else {
                    String fieldId = fieldName.toLowerCase();
                    if (fieldId.contains("date")) {
                        setDateField(f, input);
                        // Re-find element after datepicker interaction
                        f = driver.findElement(By.id(fieldName));
                    } else {
                        f.clear();
                        Thread.sleep(200);
                        if (!input.isEmpty() && !input.equalsIgnoreCase("Empty")) {
                            f.sendKeys(input);
                        }
                        Thread.sleep(300);
                    }
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

    @Test(priority = 2, dependsOnMethods = "validateCreate")
    public void validateSave() throws Exception {
        String fn = "Save";

        WebElement saveBtn = driver.findElement(By.id("saveData"));
        jse.executeScript("arguments[0].scrollIntoView({block:'center',behavior:'smooth'})", saveBtn);
        Thread.sleep(500);
        log(fn, "Save button is visible", "true", String.valueOf(saveBtn.isDisplayed()), saveBtn.isDisplayed());
        log(fn, "Save button is enabled", "true", String.valueOf(saveBtn.isEnabled()), saveBtn.isEnabled());

        saveBtn.click();
        Thread.sleep(1000);
        scrollToSearch();

        String toast = getSuccessToast();
        log(fn, "Record saved successfully", "Success toast", toast.isEmpty() ? "No toast" : toast, !toast.isEmpty());

        sa.assertAll();
    }

    // ==================== DATABASE VALIDATION ====================
    @Test(priority = 3)
    public void validateDatabase() throws Exception {
        String today = java.time.LocalDate.now().toString();
        String query = "SELECT TOP 1 * FROM D310046 ORDER BY createdDate DESC, createdTime DESC";
        ResultSet rs = DBConnection.executeQuery(query);

        if (rs == null || !rs.next()) {
            log("DB", "Record found in d310044", "Yes", "No", false);
            sa.fail("No record found in d310044");
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

        String dbDemandLetterType = rs.getString("demandLetterType");
        log("DB", "demandLetterType", "SDL", dbDemandLetterType, dbDemandLetterType != null && dbDemandLetterType.contains("SDL"));

        String dbUserName = rs.getString("userName");
        log("DB", "userName", "Automation User", dbUserName, dbUserName != null && dbUserName.contains("Automation User"));

        String dbCaseNo = rs.getString("caseNo");
        log("DB", "caseNo", "CASE_0005282011204001137", dbCaseNo, dbCaseNo != null && dbCaseNo.contains("CASE_0005282011204001137"));

        logInfo("DB", "createdTime", rs.getString("createdTime"));

        DBConnection.close(rs);
        sa.assertAll();
    }
}
