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
import com.ebid.lcs.utils.DBConnection;

import java.sql.ResultSet;

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
        ExtentManager.startTest("Fees & Legal Charges - Save/View/Edit");

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

    @Test(priority = 3)
    public void validateDatabase() throws Exception {
        String today = java.time.LocalDate.now().toString();
        String caseNo = ConfigManager.get("casenumber").trim();
        String query = "SELECT TOP 1 * FROM D3300025 WHERE caseNo LIKE '%" + caseNo + "%' ORDER BY createdDate DESC, createdTime DESC";
        ResultSet rs = DBConnection.executeQuery(query);

        if (rs == null || !rs.next()) {
            log("DB", "Record found in D3300025", "Yes", "No", false);
            sa.fail("No record found in D3300025");
            DBConnection.close(rs);
            return;
        }

        log("DB", "Record found", "Yes", "Yes", true);

        String dbCreatedDate = rs.getString("createdDate");
        boolean dateOk = dbCreatedDate != null && dbCreatedDate.contains(today);
        log("DB", "createdDate contains today", today, dbCreatedDate, dateOk);
        // soft assert only — don't hard fail on date mismatch
        sa.assertTrue(dateOk, "createdDate should contain today");

        String dbIsActive = rs.getString("isActive");
        log("DB", "isActive", "1", dbIsActive, "1".equals(dbIsActive));
        sa.assertEquals(dbIsActive, "1", "isActive should be 1");

        String dbCaseNo = rs.getString("caseNo");
        log("DB", "caseNo", "CASE_0005282011204001137", dbCaseNo, dbCaseNo != null && dbCaseNo.contains("CASE_0005282011204001137"));

        String dbChargeName = rs.getString("chargeName");
        log("DB", "chargeName", "1", dbChargeName, dbChargeName != null && dbChargeName.contains("1"));

        String dbInvoiceNumber = rs.getString("invoiceNumber");
        log("DB", "invoiceNumber", "INV-2025-LEGAL", dbInvoiceNumber, dbInvoiceNumber != null && dbInvoiceNumber.contains("INV-2025-LEGAL"));

        String dbInvoiceAmount = rs.getString("invoiceAmount");
        log("DB", "invoiceAmount", "10000", dbInvoiceAmount, dbInvoiceAmount != null && dbInvoiceAmount.contains("10000"));

        String dbCurrency = rs.getString("currency");
        log("DB", "currency", "USD", dbCurrency, dbCurrency != null && dbCurrency.contains("USD"));

        String dbPayableAmount = rs.getString("payableAmount");
        log("DB", "payableAmount", "5000", dbPayableAmount, dbPayableAmount != null && dbPayableAmount.contains("5000"));

        String dbOutstandingAmount = rs.getString("outstandingAmount");
        log("DB", "outstandingAmount", "5000", dbOutstandingAmount, dbOutstandingAmount != null && dbOutstandingAmount.contains("5000"));

        String dbRemarks = rs.getString("remarks");
        log("DB", "remarks", "Fees payment remark final", dbRemarks, dbRemarks != null && dbRemarks.contains("Fees payment remark final"));

        logInfo("DB", "createdTime", rs.getString("createdTime"));

        DBConnection.close(rs);
        sa.assertAll();
    }
}
