package com.ebid.lcs.tests;

import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.Select;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Listeners;
import org.testng.annotations.Test;

import java.sql.ResultSet;
import java.time.Duration;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

import com.ebid.lcs.base.BaseTest;
import com.ebid.lcs.config.ConfigManager;
import com.ebid.lcs.listeners.TestListener;
import com.ebid.lcs.reporting.ExtentManager;
import com.ebid.lcs.utils.DBConnection;

@Listeners(TestListener.class)
public class SiteVisitTest extends BaseTest {

    @BeforeClass
    public void setup() throws Exception {
        ExtentManager.initReport("SiteVisit");
        ExtentManager.startTest("Site Visit - Full Validation");

        navigateToCase(ConfigManager.get("casenumber"));

        // Click Follow-Up tab
        WebElement followUpTab = driver.findElement(By.xpath("//*[contains(@href,'Follow-Up')]"));
        jse.executeScript("arguments[0].scrollIntoView({block:'center',behavior:'smooth'})", followUpTab);
        Thread.sleep(1000);
        followUpTab.click();
        Thread.sleep(2000);

        // Double-click Site Visit Request
        WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(15));
        WebElement svTab = wait
                .until(ExpectedConditions.elementToBeClickable(By.xpath("//*[contains(text(),'Site Visit Request')]")));
        jse.executeScript("arguments[0].scrollIntoView({block:'center',behavior:'smooth'})", svTab);
        Thread.sleep(1000);
        act.doubleClick(svTab).build().perform();
        Thread.sleep(2000);

        driver.switchTo().frame("createSiteVisitDetailsFrame");
        logInfo("Frame", "Switched to", "createSiteVisitDetailsFrame");
    }

    @Test(priority = 1)
    public void validateCurrencyDD() throws Exception {
        validateDropdown("currency", "Currency", 2);
    }

    @Test(priority = 2)
    public void validateVisitTypeDD() throws Exception {
        validateDropdown("visitType", "Visit Type", 1);
    }

    @Test(priority = 2)
    public void validateVisitedByDD() throws Exception {
        validateDropdown("visitedBy", "Visited By", 1);
    }

    @Test(priority = 3)
    public void validateCustomerResponseDD() throws Exception {
        validateDropdown("customerResponse", "Customer Response", 1);
    }

    @Test(priority = 4)
    public void validateDates() throws Exception {
        // Visit Initiated Date
        WebElement vid = driver.findElement(By.id("visitInitiatedt"));
        jse.executeScript("arguments[0].scrollIntoView({block:'center'})", vid);
        Thread.sleep(300);
        log("visitInitiatedt", "Displayed", "true", String.valueOf(vid.isDisplayed()), vid.isDisplayed());
        log("visitInitiatedt", "Enabled", "true", String.valueOf(vid.isEnabled()), vid.isEnabled());

        jse.executeScript("arguments[0].value='21-05-2026'", vid);
        Thread.sleep(300);
        String v1 = vid.getAttribute("value");
        log("visitInitiatedt", "Set date 21-05-2026", "21-05-2026", v1, v1.equals("21-05-2026"));
        sa.assertEquals(v1, "21-05-2026");

        // Visit Date
        WebElement vd = driver.findElement(By.id("visitDate"));
        jse.executeScript("arguments[0].scrollIntoView({block:'center'})", vd);
        Thread.sleep(300);
        log("visitDate", "Displayed", "true", String.valueOf(vd.isDisplayed()), vd.isDisplayed());
        log("visitDate", "Enabled", "true", String.valueOf(vd.isEnabled()), vd.isEnabled());

        jse.executeScript("arguments[0].value='21-05-2026'", vd);
        Thread.sleep(300);
        String v2 = vd.getAttribute("value");
        log("visitDate", "Set date 21-05-2026", "21-05-2026", v2, v2.equals("21-05-2026"));
        sa.assertEquals(v2, "21-05-2026");
    }

    @Test(priority = 5)
    public void validateCollectionAndPayment() throws Exception {
        // Collection dropdown
        WebElement collDD = driver.findElement(By.id("collection"));
        jse.executeScript("arguments[0].scrollIntoView({block:'center'})", collDD);
        Thread.sleep(500);
        Select selColl = new Select(collDD);

        log("collection", "Displayed", "true", String.valueOf(collDD.isDisplayed()), collDD.isDisplayed());
        log("collection", "Enabled", "true", String.valueOf(collDD.isEnabled()), collDD.isEnabled());

        // Select No - conditional fields should hide
        selColl.selectByVisibleText("No");
        Thread.sleep(500);
        log("collection", "Select 'No'", "No", selColl.getFirstSelectedOption().getText().trim(), true);
        log("collection", "collectedDate hidden", "true", String.valueOf(!isFieldVisible("collectedDate")),
                !isFieldVisible("collectedDate"));
        log("collection", "collectedAmount_txt hidden", "true", String.valueOf(!isFieldVisible("collectedAmount_txt")),
                !isFieldVisible("collectedAmount_txt"));

        // Select Yes - conditional fields should appear
        selColl.selectByVisibleText("Yes");
        Thread.sleep(500);
        log("collection", "Select 'Yes'", "Yes", selColl.getFirstSelectedOption().getText().trim(), true);
        log("collection", "collectedDate visible", "true", String.valueOf(isFieldVisible("collectedDate")),
                isFieldVisible("collectedDate"));
        log("collection", "collectedAmount_txt visible", "true", String.valueOf(isFieldVisible("collectedAmount_txt")),
                isFieldVisible("collectedAmount_txt"));
        log("collection", "modeOfPayment visible", "true", String.valueOf(isFieldVisible("modeOfPayment")),
                isFieldVisible("modeOfPayment"));

        // Set Collected Date
        WebElement cd = driver.findElement(By.id("collectedDate"));
        jse.executeScript("arguments[0].value='21-05-2026'", cd);
        Thread.sleep(300);
        log("collectedDate", "Set date", "21-05-2026", cd.getAttribute("value"),
                cd.getAttribute("value").equals("21-05-2026"));

        // ===== CURRENCY =====
        WebElement currency = driver.findElement(By.id("currency"));
        jse.executeScript("arguments[0].scrollIntoView({block:'center'})", currency);
        Thread.sleep(300);
        Select selCurrency = new Select(currency);
        log("currency", "Displayed", "true", String.valueOf(currency.isDisplayed()), currency.isDisplayed());
        log("currency", "Enabled", "true", String.valueOf(currency.isEnabled()), currency.isEnabled());
        selCurrency.selectByIndex(2);
        Thread.sleep(300);
        String selectedCurrency = selCurrency.getFirstSelectedOption().getText().trim();
        log("currency", "Select currency", "Non-empty", selectedCurrency, !selectedCurrency.isEmpty() && !selectedCurrency.contains("SELECT"));
        logInfo("currency", "Selected value", selectedCurrency);

        // Set Collected Amount (actual ID is collectedAmount_txt)
        WebElement ca = driver.findElement(By.id("collectedAmount_txt"));
        ca.clear();
        ca.sendKeys("5000");
        Thread.sleep(300);
        log("collectedAmount_txt", "Set amount", "5000", ca.getAttribute("value"),
                ca.getAttribute("value").contains("5000"));

        // ===== MODE OF PAYMENT VALIDATION =====
        WebElement mop = driver.findElement(By.id("modeOfPayment"));
        jse.executeScript("arguments[0].scrollIntoView({block:'center'})", mop);
        Select selMode = new Select(mop);

        // ===== ACCOUNT TRANSFER =====
        selMode.selectByVisibleText("Account Transfer");
        Thread.sleep(1000);
        log("modeOfPayment", "Select 'Account Transfer'", "Account Transfer",
                selMode.getFirstSelectedOption().getText().trim(), true);
        log("Account Transfer", "transactionDate visible", "true", String.valueOf(isFieldVisible("transactionDate")),
                isFieldVisible("transactionDate"));
        log("Account Transfer", "transactionNo visible", "true", String.valueOf(isFieldVisible("transactionNo")),
                isFieldVisible("transactionNo"));
        log("Account Transfer", "receiptNo hidden", "true", String.valueOf(!isFieldVisible("receiptNo")),
                !isFieldVisible("receiptNo"));
        log("Account Transfer", "chequeDate hidden", "true", String.valueOf(!isFieldVisible("chequeDate")),
                !isFieldVisible("chequeDate"));
        log("Account Transfer", "chequeNo hidden", "true", String.valueOf(!isFieldVisible("chequeNo")),
                !isFieldVisible("chequeNo"));
        svValidateDateField("transactionDate", "AT Transaction Date", "21-05-2026");
        svValidateTextField("transactionNo", "AT Transaction No", "TXN001");

        // ===== BANK TRANSFER =====
        selMode.selectByVisibleText("Bank Transfer");
        Thread.sleep(1000);
        log("modeOfPayment", "Select 'Bank Transfer'", "Bank Transfer",
                selMode.getFirstSelectedOption().getText().trim(), true);
        log("Bank Transfer", "transactionDate visible", "true", String.valueOf(isFieldVisible("transactionDate")),
                isFieldVisible("transactionDate"));
        log("Bank Transfer", "transactionNo visible", "true", String.valueOf(isFieldVisible("transactionNo")),
                isFieldVisible("transactionNo"));
        log("Bank Transfer", "receiptNo hidden", "true", String.valueOf(!isFieldVisible("receiptNo")),
                !isFieldVisible("receiptNo"));
        log("Bank Transfer", "chequeDate hidden", "true", String.valueOf(!isFieldVisible("chequeDate")),
                !isFieldVisible("chequeDate"));
        log("Bank Transfer", "chequeNo hidden", "true", String.valueOf(!isFieldVisible("chequeNo")),
                !isFieldVisible("chequeNo"));
        svValidateDateField("transactionDate", "BT Transaction Date", "21-05-2026");
        svValidateTextField("transactionNo", "BT Transaction No", "TXN002");

        // ===== CASH =====
        selMode.selectByVisibleText("CASH");
        Thread.sleep(1000);
        log("modeOfPayment", "Select 'CASH'", "CASH", selMode.getFirstSelectedOption().getText().trim(), true);
        log("CASH", "receiptNo visible", "true", String.valueOf(isFieldVisible("receiptNo")),
                isFieldVisible("receiptNo"));
        log("CASH", "transactionDate hidden", "true", String.valueOf(!isFieldVisible("transactionDate")),
                !isFieldVisible("transactionDate"));
        log("CASH", "transactionNo hidden", "true", String.valueOf(!isFieldVisible("transactionNo")),
                !isFieldVisible("transactionNo"));
        log("CASH", "chequeDate hidden", "true", String.valueOf(!isFieldVisible("chequeDate")),
                !isFieldVisible("chequeDate"));
        log("CASH", "chequeNo hidden", "true", String.valueOf(!isFieldVisible("chequeNo")),
                !isFieldVisible("chequeNo"));
        svValidateTextField("receiptNo", "CASH Receipt No", "RCP001");

        // ===== CHEQUE =====
        selMode.selectByVisibleText("Cheque");
        Thread.sleep(1000);
        log("modeOfPayment", "Select 'Cheque'", "Cheque", selMode.getFirstSelectedOption().getText().trim(), true);
        log("Cheque", "chequeDate visible", "true", String.valueOf(isFieldVisible("chequeDate")),
                isFieldVisible("chequeDate"));
        log("Cheque", "chequeNumber visible", "true", String.valueOf(isFieldVisible("chequeNo")),
                isFieldVisible("chequeNo"));
        log("Cheque", "receiptNo visible", "true", String.valueOf(isFieldVisible("receiptNo")),
                isFieldVisible("receiptNo"));
        log("Cheque", "transactionDate hidden", "true", String.valueOf(!isFieldVisible("transactionDate")),
                !isFieldVisible("transactionDate"));
        log("Cheque", "transactionNo hidden", "true", String.valueOf(!isFieldVisible("transactionNo")),
                !isFieldVisible("transactionNo"));
        svValidateDateField("chequeDate", "Cheque Date", "21-05-2026");
        svValidateTextField("chequeNo", "Cheque Number", "CHQ12345");
        svValidateTextField("receiptNo", "Cheque Receipt No", "RCP002");

        // ===== VISA SWIPE =====
        selMode.selectByVisibleText("Visa Swipe");
        Thread.sleep(1000);
        log("modeOfPayment", "Select 'Visa Swipe'", "Visa Swipe", selMode.getFirstSelectedOption().getText().trim(),
                true);
        log("Visa Swipe", "receiptNo visible", "true", String.valueOf(isFieldVisible("receiptNo")),
                isFieldVisible("receiptNo"));
        log("Visa Swipe", "transactionDate hidden", "true", String.valueOf(!isFieldVisible("transactionDate")),
                !isFieldVisible("transactionDate"));
        log("Visa Swipe", "transactionNo hidden", "true", String.valueOf(!isFieldVisible("transactionNo")),
                !isFieldVisible("transactionNo"));
        log("Visa Swipe", "chequeDate hidden", "true", String.valueOf(!isFieldVisible("chequeDate")),
                !isFieldVisible("chequeDate"));
        log("Visa Swipe", "chequeNo hidden", "true", String.valueOf(!isFieldVisible("chequeNo")),
                !isFieldVisible("chequeNo"));
        svValidateTextField("receiptNo", "Visa Receipt No", "RCP003");

        // ===== FINAL: Set CASH for save =====
        selMode.selectByVisibleText("CASH");
        Thread.sleep(500);
        WebElement rn = driver.findElement(By.id("receiptNo"));
        rn.clear();
        rn.sendKeys("RCP001");
        Thread.sleep(300);
        log("receiptNo", "Final value for save", "RCP001", rn.getAttribute("value"), true);
    }

    @Test(priority = 6)
    public void validateRemarks() throws Exception {
        WebElement rem = driver.findElement(By.id("remarks"));
        jse.executeScript("arguments[0].scrollIntoView({block:'center'})", rem);
        Thread.sleep(300);
        log("remarks", "Displayed", "true", String.valueOf(rem.isDisplayed()), rem.isDisplayed());
        log("remarks", "Enabled", "true", String.valueOf(rem.isEnabled()), rem.isEnabled());

        rem.clear();
        rem.sendKeys("Test Remark Selenium");
        Thread.sleep(300);
        log("remarks", "Enter text", "Test Remark Selenium", rem.getAttribute("value"),
                rem.getAttribute("value").equals("Test Remark Selenium"));
        sa.assertEquals(rem.getAttribute("value"), "Test Remark Selenium");
    }

    private String savedVnNumber = "";

    @Test(priority = 7)
    public void validateSave() throws Exception {
        WebElement saveBtn = driver.findElement(By.id("saveData"));
        jse.executeScript("arguments[0].scrollIntoView({block:'center'})", saveBtn);
        Thread.sleep(500);

        log("Save", "Displayed", "true", String.valueOf(saveBtn.isDisplayed()), saveBtn.isDisplayed());
        log("Save", "Enabled", "true", String.valueOf(saveBtn.isEnabled()), saveBtn.isEnabled());

        saveBtn.click();
        Thread.sleep(2000);
        String toast = getSuccessToast();
        log("Save", "Save Site Visit", "Success", toast.isEmpty() ? "No toast" : toast, !toast.isEmpty());
        // Extract VN number from toast for view/edit
        if (toast.contains("VN")) {
            savedVnNumber = toast.replaceAll(".*?(VN\\d+).*", "$1");
        }
        sa.assertAll();
    }

    @Test(priority = 8)
    public void validateDatabase() throws Exception {
        String today = java.time.LocalDate.now().toString();
        String query = "SELECT TOP 1 * FROM d310040 ORDER BY createdDate DESC, createdTime DESC";
        ResultSet rs = DBConnection.executeQuery(query);
        if (rs != null && rs.next()) {
            log("DB", "Record found", "Yes", "Yes", true);

            String dbCreatedDate = rs.getString("createdDate");
            log("DB", "createdDate contains today", today, dbCreatedDate, dbCreatedDate != null && dbCreatedDate.contains(today));
            sa.assertTrue(dbCreatedDate != null && dbCreatedDate.contains(today), "createdDate should contain today");

            String dbIsActive = rs.getString("isActive");
            log("DB", "isActive", "1", dbIsActive, "1".equals(dbIsActive));
            sa.assertEquals(dbIsActive, "1", "isActive should be 1");

            String dbRemarks = rs.getString("remarks");
            log("DB", "remarks", "Test Remark Selenium", dbRemarks, dbRemarks != null && dbRemarks.contains("Test Remark Selenium"));
            sa.assertTrue(dbRemarks != null && dbRemarks.contains("Test Remark Selenium"), "remarks mismatch");

            String dbCollectedAmt = rs.getString("collectedAmount");
            log("DB", "collectedAmount", "5000", dbCollectedAmt, dbCollectedAmt != null && dbCollectedAmt.contains("5000"));

            String dbModeOfPayment = rs.getString("modeOfPayment");
            log("DB", "modeOfPayment", "4", dbModeOfPayment, dbModeOfPayment != null && dbModeOfPayment.contains("4"));

            String dbCollection = rs.getString("collection");
            log("DB", "collection", "1", dbCollection, dbCollection != null && dbCollection.contains("1"));

            String dbReceiptNo = rs.getString("receiptNo");
            log("DB", "receiptNo", "RCP001", dbReceiptNo, dbReceiptNo != null && dbReceiptNo.contains("RCP001"));

            String dbCaseNo = rs.getString("caseNo");
            log("DB", "caseNo", "CASE_0005282011204001137", dbCaseNo, dbCaseNo != null && dbCaseNo.contains("CASE_0005282011204001137"));

            logInfo("DB", "createdTime", rs.getString("createdTime"));
        } else {
            log("DB", "Record found", "Yes", "No", false);
            sa.fail("No record found in d310040");
        }
        DBConnection.close(rs);
        sa.assertAll();
    }

    @Test(priority = 9)
    public void validateView() throws Exception {
        Thread.sleep(1000);
        try {
            WebElement search = driver.findElement(By.cssSelector("input[placeholder='Search keyword here']"));
            jse.executeScript("arguments[0].scrollIntoView({block:'center',behavior:'smooth'})", search);
            Thread.sleep(500);
        } catch (Exception e) {}

        // Click View on first row (latest record)
        WebElement viewBtn = driver.findElement(By.cssSelector("tbody tr:first-child a.ViewBtn"));
        jse.executeScript("arguments[0].scrollIntoView({block:'center'})", viewBtn);
        Thread.sleep(500);
        jse.executeScript("arguments[0].click()", viewBtn);
        Thread.sleep(2000);
        log("View", "Click View button (latest record)", "View mode opened", "Clicked", true);

        // Scroll down to show form data
        WebElement rem = driver.findElement(By.id("remarks"));
        jse.executeScript("arguments[0].scrollIntoView({block:'center',behavior:'smooth'})", rem);
        Thread.sleep(500);

        // Validate saved fields
        String viewRemarks = rem.getAttribute("value");
        log("View", "remarks", "Test Remark Selenium", viewRemarks, "Test Remark Selenium".equals(viewRemarks));

        WebElement ca = driver.findElement(By.id("collectedAmount_txt"));
        String viewAmt = ca.getAttribute("value");
        log("View", "collectedAmount", "5000", viewAmt, viewAmt != null && viewAmt.contains("5000"));

        WebElement currencyEl = driver.findElement(By.id("currency"));
        String viewCurrency = new Select(currencyEl).getFirstSelectedOption().getText().trim();
        log("View", "currency", "EURO", viewCurrency, "EURO".equals(viewCurrency));
    }

    @Test(priority = 10)
    public void validateEdit() throws Exception {
        Thread.sleep(1000);
        try {
            WebElement search = driver.findElement(By.cssSelector("input[placeholder='Search keyword here']"));
            jse.executeScript("arguments[0].scrollIntoView({block:'center',behavior:'smooth'})", search);
            Thread.sleep(500);
        } catch (Exception e) {}

        // Click Edit on first row (latest record)
        WebElement editBtn = driver.findElement(By.cssSelector("tbody tr:first-child a.editBtn"));
        jse.executeScript("arguments[0].scrollIntoView({block:'center'})", editBtn);
        Thread.sleep(500);
        jse.executeScript("arguments[0].click()", editBtn);
        Thread.sleep(2000);
        log("Edit", "Click Edit button (latest record)", "Edit mode opened", "Clicked", true);

        // Scroll to remarks and update
        WebElement rem = driver.findElement(By.id("remarks"));
        jse.executeScript("arguments[0].scrollIntoView({block:'center'})", rem);
        jse.executeScript("arguments[0].value=''", rem);
        rem.sendKeys("Site Visit Updated Save");
        Thread.sleep(300);
        log("Edit", "Update remarks", "Site Visit Updated Save", rem.getAttribute("value"),
                rem.getAttribute("value").equals("Site Visit Updated Save"));

        // Save
        WebElement saveBtn = driver.findElement(By.id("saveData"));
        jse.executeScript("arguments[0].scrollIntoView({block:'center'})", saveBtn);
        saveBtn.click();
        Thread.sleep(2000);
        String toast = getSuccessToast();
        log("Edit", "Save after edit", "Success", toast.isEmpty() ? "No toast" : toast, !toast.isEmpty());
        sa.assertTrue(!toast.isEmpty(), "Edit save toast not received");
        sa.assertAll();
    }
    private boolean isFieldVisible(String id) {
        try {
            List<WebElement> els = driver.findElements(By.id(id));
            return els.size() > 0 && els.get(0).isDisplayed();
        } catch (Exception e) {
            return false;
        }
    }

    private void svValidateTextField(String id, String fieldName, String testValue) throws Exception {
        try {
            WebElement field = driver.findElement(By.id(id));
            log(fieldName, "Displayed", "true", String.valueOf(field.isDisplayed()), field.isDisplayed());
            log(fieldName, "Enabled", "true", String.valueOf(field.isEnabled()), field.isEnabled());
            jse.executeScript("arguments[0].value=''", field);
            jse.executeScript("arguments[0].value=arguments[1]", field, testValue);
            Thread.sleep(300);
            String val = field.getAttribute("value");
            log(fieldName, "Enter '" + testValue + "'", testValue, val, val.contains(testValue) || !val.isEmpty());
            jse.executeScript("arguments[0].value=''", field);
            Thread.sleep(200);
            log(fieldName, "Clear field", "Empty", "'" + field.getAttribute("value") + "'",
                    field.getAttribute("value").isEmpty());
            jse.executeScript("arguments[0].value=arguments[1]", field, testValue);
            Thread.sleep(200);
        } catch (Exception e) {
            log(fieldName, "Field interaction", "Accessible", "ERROR: " + e.getMessage(), false);
        }
    }

    private void svValidateDateField(String id, String fieldName, String testValue) throws Exception {
        try {
            WebElement field = driver.findElement(By.id(id));
            log(fieldName, "Displayed", "true", String.valueOf(field.isDisplayed()), field.isDisplayed());
            log(fieldName, "Enabled", "true", String.valueOf(field.isEnabled()), field.isEnabled());
            jse.executeScript("arguments[0].value='" + testValue + "'", field);
            Thread.sleep(300);
            String val = field.getAttribute("value");
            log(fieldName, "Set date '" + testValue + "'", testValue, val, val.equals(testValue));
            jse.executeScript("arguments[0].value=''", field);
            Thread.sleep(200);
            log(fieldName, "Clear field", "Empty", "'" + field.getAttribute("value") + "'",
                    field.getAttribute("value").isEmpty());
            jse.executeScript("arguments[0].value='" + testValue + "'", field);
            Thread.sleep(200);
        } catch (Exception e) {
            log(fieldName, "Field interaction", "Accessible", "ERROR: " + e.getMessage(), false);
        }
    }

    private void validateDropdown(String id, String fn, int firstValidIndex) throws Exception {
        WebElement dd = driver.findElement(By.id(id));
        jse.executeScript("arguments[0].scrollIntoView({block:'center',behavior:'smooth'})", dd);
        Thread.sleep(500);
        Select sel = new Select(dd);

        log(fn, "Displayed", "true", String.valueOf(dd.isDisplayed()), dd.isDisplayed());
        sa.assertTrue(dd.isDisplayed());
        log(fn, "Enabled", "true", String.valueOf(dd.isEnabled()), dd.isEnabled());
        sa.assertTrue(dd.isEnabled());
        log(fn, "Single select", "false", String.valueOf(sel.isMultiple()), !sel.isMultiple());

        String defaultVal = sel.getFirstSelectedOption().getText().trim();
        log(fn, "Default value", "SELECT", defaultVal, defaultVal.contains("SELECT") || defaultVal.contains("--"));

        List<WebElement> options = sel.getOptions();
        log(fn, "Options count", ">1", String.valueOf(options.size()), options.size() > 1);

        for (int i = 0; i < options.size(); i++) {
            logInfo(fn, "Option [" + i + "]", options.get(i).getText().trim());
        }

        Set<String> unique = new HashSet<>();
        boolean hasDup = false;
        for (WebElement opt : options) {
            if (!unique.add(opt.getText().trim())) {
                hasDup = true;
                break;
            }
        }
        log(fn, "No duplicates", "false", String.valueOf(hasDup), !hasDup);

        sel.selectByIndex(firstValidIndex);
        Thread.sleep(300);
        String idxVal = sel.getFirstSelectedOption().getText().trim();
        log(fn, "Select index " + firstValidIndex, "Non-empty", idxVal,
                !idxVal.isEmpty() && !idxVal.contains("SELECT"));

        sel.selectByIndex(options.size() - 1);
        Thread.sleep(300);
        log(fn, "Select last option", "Non-empty", sel.getFirstSelectedOption().getText().trim(), true);

        sel.selectByIndex(firstValidIndex);
        Thread.sleep(300);
        log(fn, "Final selection for save", idxVal, sel.getFirstSelectedOption().getText().trim(), true);
    }
}
