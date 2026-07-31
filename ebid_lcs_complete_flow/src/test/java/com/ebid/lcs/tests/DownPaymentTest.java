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

import com.ebid.lcs.base.BaseTest;
import com.ebid.lcs.listeners.TestListener;
import com.ebid.lcs.reporting.ExtentManager;

@Listeners(TestListener.class)
public class DownPaymentTest extends BaseTest {

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
        ExtentManager.initReport("Downpayment");
        ExtentManager.startTest("Downpayment - Save");
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

        wait.until(ExpectedConditions.frameToBeAvailableAndSwitchToIt("fetchPTPMstTabFrame"));
        wait.until(ExpectedConditions.presenceOfElementLocated(By.id("currency")));
        Thread.sleep(500);
        logInfo("Frame", "Switched to", "fetchPTPMstTabFrame");
    }

    @Test(priority = 1)
    public void validateSave() throws Exception {
        // 1. Currency — FIRST field — JS select
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
        remarks.sendKeys("Downpayment Test");
        Thread.sleep(200);

        // 5. Schedule Type = Downpayment + Schedule PTP
        WebElement schedType = driver.findElement(By.id("scheduleType"));
        jse.executeScript("arguments[0].scrollIntoView({block:'center',behavior:'smooth'})", schedType);
        new Select(schedType).selectByIndex(2);
        // After scheduleType change, frame may reload — re-switch
        Thread.sleep(2000);
        try {
            driver.switchTo().defaultContent();
            wait.until(ExpectedConditions.frameToBeAvailableAndSwitchToIt("fetchPTPMstTabFrame"));
            wait.until(ExpectedConditions.presenceOfElementLocated(By.id("scheduleType")));
            Thread.sleep(500);
        } catch (Exception e) {}

        // 6. Remaining Amount
        try {
            WebElement remAmt = driver.findElement(By.id("remAmt"));
            jse.executeScript("arguments[0].scrollIntoView({block:'center',behavior:'smooth'})", remAmt);
            remAmt.clear();
            remAmt.sendKeys("1500000");
            remAmt.sendKeys(Keys.TAB);
            Thread.sleep(300);
            dismissAlert();
        } catch (Exception e) {}

        // 7. Downpayment section
        try {
            WebElement planDate = driver.findElement(By.id("planDate"));
            jse.executeScript("arguments[0].scrollIntoView({block:'center',behavior:'smooth'})", planDate);
            jse.executeScript("arguments[0].value='01-07-2025'", planDate);
            hideDatepicker();
            Thread.sleep(200);

            WebElement plannedAmt = driver.findElement(By.id("plannedAmt"));
            jse.executeScript("arguments[0].value='70000.0'", plannedAmt);
            jse.executeScript("arguments[0].dispatchEvent(new Event('change'))", plannedAmt);
            Thread.sleep(300);
            dismissAlert();

            new Select(driver.findElement(By.id("paymentMode"))).selectByVisibleText("CASH");
            Thread.sleep(300);
            driver.findElement(By.id("receiptNo")).sendKeys("RCP001");
            Thread.sleep(200);
        } catch (Exception e) {}

        // 8. Schedule PTP section
        try {
            WebElement planDate1 = driver.findElement(By.id("planDate1"));
            jse.executeScript("arguments[0].scrollIntoView({block:'center',behavior:'smooth'})", planDate1);
            jse.executeScript("arguments[0].value='07-01-2025'", planDate1);
            hideDatepicker();
            Thread.sleep(200);

            WebElement plannedAmt1 = driver.findElement(By.id("plannedAmt1"));
            jse.executeScript("arguments[0].value='1430000.0'", plannedAmt1);
            jse.executeScript("arguments[0].dispatchEvent(new Event('change'))", plannedAmt1);
            Thread.sleep(300);
            dismissAlert();

            new Select(driver.findElement(By.id("paymentMode1"))).selectByVisibleText("CASH");
            Thread.sleep(300);
            try {
                driver.findElement(By.id("receiptNo1")).sendKeys("RCP002");
                Thread.sleep(200);
            } catch (Exception e) {}
        } catch (Exception e) {}

        // 9. Add DP — use presence not clickable
        WebElement add2 = wait.until(ExpectedConditions.presenceOfElementLocated(By.id("add2")));
        jse.executeScript("arguments[0].scrollIntoView({block:'center',behavior:'smooth'})", add2);
        Thread.sleep(500);
        jse.executeScript("arguments[0].scrollIntoView({block:'center',behavior:'smooth'})", add2);
        jse.executeScript("arguments[0].click()", add2);
        Thread.sleep(2000);
        dismissAlert();
        log("Add", "Click DP Add", "Clicked", "Clicked", true);

        // 10. Add Schedule — use presence not clickable
        WebElement add3 = wait.until(ExpectedConditions.presenceOfElementLocated(By.id("add3")));
        jse.executeScript("arguments[0].scrollIntoView({block:'center',behavior:'smooth'})", add3);
        Thread.sleep(500);
        jse.executeScript("arguments[0].scrollIntoView({block:'center',behavior:'smooth'})", add3);
        jse.executeScript("arguments[0].click()", add3);
        Thread.sleep(2000);
        dismissAlert();
        log("Add", "Click Schedule Add", "Clicked", "Clicked", true);

        // 11. Save
        WebElement saveBtn = wait.until(ExpectedConditions.elementToBeClickable(By.id("saveData")));
        jse.executeScript("arguments[0].scrollIntoView({block:'center',behavior:'smooth'})", saveBtn);
        Thread.sleep(500);
        jse.executeScript("arguments[0].click()", saveBtn);
        Thread.sleep(2000);
        dismissAlert();

        // Wait for toast BEFORE refresh
        scrollToSearch();
        String toast = getSuccessToast();
        log("Save", "Save Downpayment", "Success", toast.isEmpty() ? "No toast" : toast, !toast.isEmpty());

        // Now refresh
        Thread.sleep(1000);
        driver.switchTo().defaultContent();
        driver.navigate().refresh();
        Thread.sleep(3000);
        sa.assertAll();
    }
}
