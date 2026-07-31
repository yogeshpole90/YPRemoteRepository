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

    @Test(priority = 1)
    public void validateCreate() throws Exception {
        WebElement frame = wait.until(ExpectedConditions.presenceOfElementLocated(
            By.id("addNewDemandLetterFrame")));
        driver.switchTo().frame(frame);
        logInfo("Frame", "Switched to", "addNewDemandLetterFrame");

        // Fill minimal required fields
        try { new Select(driver.findElement(By.id("demandLetterType"))).selectByIndex(1); Thread.sleep(300); } catch (Exception e) {}
        try { driver.findElement(By.id("userName")).clear(); driver.findElement(By.id("userName")).sendKeys("Test User"); } catch (Exception e) {}
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
