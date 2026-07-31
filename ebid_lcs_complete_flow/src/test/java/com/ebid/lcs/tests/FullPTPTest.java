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
import com.ebid.lcs.listeners.TestListener;
import com.ebid.lcs.reporting.ExtentManager;

@Listeners(TestListener.class)
public class FullPTPTest extends BaseTest {

    private WebDriverWait wait;

    private void dismissAlert() {
        try { driver.switchTo().alert().accept(); } catch (Exception e) {}
    }

    private void hideDatepicker() {
        try {
            jse.executeScript("var dp=document.querySelectorAll('.datepicker,.ui-datepicker,.daterangepicker,.bootstrap-datetimepicker-widget');dp.forEach(function(el){el.style.display='none';});");
        } catch (Exception e) {}
        try { driver.findElement(By.tagName("body")).sendKeys(Keys.ESCAPE); } catch (Exception e) {}
    }

    private void scrollToSearch() {
        try {
            WebElement s = driver.findElement(By.cssSelector("input[placeholder='Search keyword here']"));
            jse.executeScript("arguments[0].scrollIntoView({block:'center',behavior:'smooth'})", s);
            Thread.sleep(800);
        } catch (Exception e) {}
    }

    @BeforeClass
    public void setup() throws Exception {
        ExtentManager.initReport("FullPTP");
        ExtentManager.startTest("Full PTP - Save/View/Edit");
        wait = new WebDriverWait(driver, Duration.ofSeconds(15));

        WebElement remedial = wait.until(ExpectedConditions.elementToBeClickable(
            By.xpath("(//*[contains(@href,'=Remedial Action')])[1]")));
        jse.executeScript("arguments[0].scrollIntoView({block:'center',behavior:'smooth'})", remedial);
        jse.executeScript("arguments[0].click()", remedial);
        Thread.sleep(2000);

        WebElement ptpTab = wait.until(ExpectedConditions.elementToBeClickable(
            By.xpath("//*[contains(text(),'Promise to pay')]")));
        jse.executeScript("arguments[0].scrollIntoView({block:'center',behavior:'smooth'})", ptpTab);
        act.doubleClick(ptpTab).build().perform();
        Thread.sleep(1000);

        // Wait for frame to be available before switching
        wait.until(ExpectedConditions.frameToBeAvailableAndSwitchToIt("fetchPTPMstTabFrame"));
        // Wait for currency field to be present inside frame
        wait.until(ExpectedConditions.presenceOfElementLocated(By.id("currency")));
        Thread.sleep(500);
        logInfo("Frame", "Switched to", "fetchPTPMstTabFrame");
    }

    @Test(priority = 1)
    public void validateSave() throws Exception {
        // 1. Currency — FIRST field on PTP screen — use JS to avoid clickable wait
        WebElement currency = wait.until(ExpectedConditions.presenceOfElementLocated(By.id("currency")));
        jse.executeScript("arguments[0].scrollIntoView({block:'center',behavior:'smooth'})", currency);
        jse.executeScript("arguments[0].selectedIndex=1; arguments[0].dispatchEvent(new Event('change',{bubbles:true}))", currency);
        Thread.sleep(300);
        log("currency", "Select currency", "Non-empty",
            new Select(currency).getFirstSelectedOption().getText(), true);

        // 2. Overdue Amount
        WebElement overdueAmt = wait.until(ExpectedConditions.visibilityOfElementLocated(By.id("overdueAmount")));
        jse.executeScript("arguments[0].scrollIntoView({block:'center',behavior:'smooth'})", overdueAmt);
        overdueAmt.clear();
        overdueAmt.sendKeys("1500000");
        overdueAmt.sendKeys(Keys.TAB);
        Thread.sleep(300);
        dismissAlert();

        // 3. PTP Start Date
        WebElement ptpDate = driver.findElement(By.id("dateOfPTPStart"));
        jse.executeScript("arguments[0].scrollIntoView({block:'center',behavior:'smooth'})", ptpDate);
        ptpDate.clear();
        ptpDate.sendKeys("01-07-2025");
        ptpDate.sendKeys(Keys.TAB);
        hideDatepicker();
        Thread.sleep(300);

        // 4. Remarks
        WebElement remarks = driver.findElement(By.id("remarks"));
        jse.executeScript("arguments[0].scrollIntoView({block:'center',behavior:'smooth'})", remarks);
        remarks.clear();
        remarks.sendKeys("PTP Test");
        Thread.sleep(200);

        // 5. Schedule Type = Full PTP (index 1)
        WebElement schedType = driver.findElement(By.id("scheduleType"));
        jse.executeScript("arguments[0].scrollIntoView({block:'center',behavior:'smooth'})", schedType);
        new Select(schedType).selectByIndex(1);
        // After scheduleType change, frame may reload — re-switch
        Thread.sleep(2000);
        try {
            driver.switchTo().defaultContent();
            wait.until(ExpectedConditions.frameToBeAvailableAndSwitchToIt("fetchPTPMstTabFrame"));
            wait.until(ExpectedConditions.presenceOfElementLocated(By.id("scheduleType")));
            Thread.sleep(500);
        } catch (Exception e) {}

        // 6. Planned Amount
        try {
            WebElement plannedAmt = driver.findElement(By.id("plannedAmt"));
            jse.executeScript("arguments[0].scrollIntoView({block:'center',behavior:'smooth'})", plannedAmt);
            jse.executeScript("arguments[0].value='1500000'", plannedAmt);
            jse.executeScript("arguments[0].dispatchEvent(new Event('change'))", plannedAmt);
            Thread.sleep(300);
            dismissAlert();
        } catch (Exception e) {}

        // 7. Plan Date
        try {
            WebElement planDate = driver.findElement(By.id("planDate"));
            jse.executeScript("arguments[0].scrollIntoView({block:'center',behavior:'smooth'})", planDate);
            jse.executeScript("arguments[0].value='01-07-2025'", planDate);
            hideDatepicker();
            Thread.sleep(200);
        } catch (Exception e) {}

        // 8. Payment Mode = CASH
        try {
            WebElement payMode = driver.findElement(By.id("paymentMode"));
            jse.executeScript("arguments[0].scrollIntoView({block:'center',behavior:'smooth'})", payMode);
            new Select(payMode).selectByIndex(1);
            Thread.sleep(300);
            try {
                WebElement receiptNo = driver.findElement(By.id("receiptNo"));
                receiptNo.clear();
                receiptNo.sendKeys("RCP001");
                Thread.sleep(200);
            } catch (Exception e) {}
        } catch (Exception e) {}

        // 9. Add button — wait up to 15 sec
        WebElement addBtn = wait.until(ExpectedConditions.presenceOfElementLocated(By.id("add")));
        jse.executeScript("arguments[0].scrollIntoView({block:'center',behavior:'smooth'})", addBtn);
        Thread.sleep(1000);
        jse.executeScript("arguments[0].scrollIntoView({block:'center',behavior:'smooth'})", addBtn);
        jse.executeScript("arguments[0].click()", addBtn);
        Thread.sleep(2000);
        dismissAlert();
        log("Add", "Click Add button", "Clicked", "Clicked", true);

        // 10. Save
        WebElement saveBtn = wait.until(ExpectedConditions.elementToBeClickable(By.id("saveData")));
        jse.executeScript("arguments[0].scrollIntoView({block:'center',behavior:'smooth'})", saveBtn);
        Thread.sleep(500);
        jse.executeScript("arguments[0].click()", saveBtn);
        Thread.sleep(2000);
        dismissAlert();

        // Wait for toast BEFORE refresh
        scrollToSearch();
        String toast = getSuccessToast();
        log("Save", "Save Full PTP", "Success", toast.isEmpty() ? "No toast" : toast, !toast.isEmpty());

        // Now refresh
        Thread.sleep(1000);
        driver.switchTo().defaultContent();
        driver.navigate().refresh();
        Thread.sleep(3000);
        sa.assertAll();
    }

    @Test(priority = 2)
    public void validateViewEdit() throws Exception {
        // Re-navigate to PTP frame after refresh
        WebElement remedial = wait.until(ExpectedConditions.elementToBeClickable(
            By.xpath("(//*[contains(@href,'=Remedial Action')])[1]")));
        jse.executeScript("arguments[0].click()", remedial);
        Thread.sleep(2000);

        WebElement ptpTab = wait.until(ExpectedConditions.elementToBeClickable(
            By.xpath("//*[contains(text(),'Promise to pay')]")));
        jse.executeScript("arguments[0].scrollIntoView({block:'center',behavior:'smooth'})", ptpTab);
        act.doubleClick(ptpTab).build().perform();
        Thread.sleep(2000);
        driver.switchTo().frame("fetchPTPMstTabFrame");

        // View
        List<WebElement> viewBtns = driver.findElements(By.xpath("//a[contains(@class,'ViewBtn')]"));
        log("View", "View buttons found", ">0", String.valueOf(viewBtns.size()), !viewBtns.isEmpty());
        if (!viewBtns.isEmpty()) {
            jse.executeScript("arguments[0].scrollIntoView({block:'center',behavior:'smooth'})", viewBtns.get(viewBtns.size() - 1));
            viewBtns.get(viewBtns.size() - 1).click();
            Thread.sleep(1500);
            log("View", "View record opened", "Opened", "Clicked", true);
        }

        // Edit
        List<WebElement> editBtns = driver.findElements(By.xpath("//a[contains(@class,'EditBtn')]"));
        log("Edit", "Edit buttons found", ">0", String.valueOf(editBtns.size()), !editBtns.isEmpty());
        if (!editBtns.isEmpty()) {
            jse.executeScript("arguments[0].scrollIntoView({block:'center',behavior:'smooth'})", editBtns.get(editBtns.size() - 1));
            editBtns.get(editBtns.size() - 1).click();
            Thread.sleep(1500);

            WebElement editRemarks = driver.findElement(By.id("remarks"));
            editRemarks.clear();
            editRemarks.sendKeys("PTP Updated");

            WebElement saveBtn = wait.until(ExpectedConditions.elementToBeClickable(By.id("saveData")));
            jse.executeScript("arguments[0].scrollIntoView({block:'center',behavior:'smooth'})", saveBtn);
            jse.executeScript("arguments[0].click()", saveBtn);
            Thread.sleep(2000);
            dismissAlert();

            scrollToSearch();
            String toast = getSuccessToast();
            log("Edit", "Update Full PTP", "Success", toast.isEmpty() ? "No toast" : toast, !toast.isEmpty());
        }
        sa.assertAll();
    }
}
