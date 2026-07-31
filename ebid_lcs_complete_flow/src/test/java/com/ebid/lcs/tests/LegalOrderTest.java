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
public class LegalOrderTest extends BaseTest {

    private WebDriverWait wait;

    private void hideDatepicker() {
        try {
            jse.executeScript("var dp=document.querySelectorAll('.datepicker,.ui-datepicker,.daterangepicker,.bootstrap-datetimepicker-widget');dp.forEach(function(el){el.style.display='none';});");
        } catch (Exception e) {}
        try { driver.findElement(By.tagName("body")).sendKeys(Keys.ESCAPE); } catch (Exception e) {}
    }

    private void scrollToSearch() {
        try {
            WebElement search = driver.findElement(By.xpath("//input[@placeholder='Search keyword here']"));
            jse.executeScript("arguments[0].scrollIntoView({block:'center'})", search);
            Thread.sleep(800);
        } catch (Exception e) {}
    }

    private void switchBackToFrame() {
        try {
            driver.switchTo().frame("getLegalDetailDataFrame");
            Thread.sleep(500);
        } catch (Exception e) {}
    }

    @BeforeClass(alwaysRun = true)
    public void setup() throws Exception {
        ExtentManager.initReport("LegalOrder");
        ExtentManager.startTest("Legal Order - Save/View/Edit");
        wait = new WebDriverWait(driver, Duration.ofSeconds(15));

        // Dismiss any open modal (remark-popup from previous stage)
        try {
            WebElement closeModal = driver.findElement(
                By.xpath("//div[contains(@class,'modal') and contains(@style,'display: block')]//button[@data-dismiss='modal' or contains(@class,'close')]"));
            jse.executeScript("arguments[0].click()", closeModal);
            Thread.sleep(1000);
        } catch (Exception e) {}
        // Also try pressing Escape
        try { driver.findElement(By.tagName("body")).sendKeys(Keys.ESCAPE); Thread.sleep(500); } catch (Exception e) {}

        // Already in Legal stage — navigate to Legal Process tab
        // Scroll down to make tab visible
        jse.executeScript("window.scrollBy(0, 300)");
        Thread.sleep(500);

        WebElement legal = wait.until(ExpectedConditions.elementToBeClickable(
            By.xpath("//*[contains(@href,'=Legal Process')]")));
        jse.executeScript("arguments[0].scrollIntoView({block:'center',behavior:'smooth'})", legal);
        Thread.sleep(500);
        legal.click();
        Thread.sleep(2000);

        WebElement loTab = wait.until(ExpectedConditions.elementToBeClickable(
            By.xpath("//a[contains(text(),'Legal Order')]")));
        jse.executeScript("arguments[0].scrollIntoView({block:'center'})", loTab);
        act.doubleClick(loTab).build().perform();
        Thread.sleep(2000);

        driver.switchTo().frame("getLegalDetailDataFrame");
        logInfo("Frame", "Switched to", "getLegalDetailDataFrame");
    }

    @Test(priority = 1)
    public void validateSave() throws Exception {
        // Order Type
        try {
            WebElement orderType = wait.until(ExpectedConditions.elementToBeClickable(By.id("orderType")));
            jse.executeScript("arguments[0].scrollIntoView({block:'center'})", orderType);
            new Select(orderType).selectByIndex(1);
            Thread.sleep(300);
        } catch (Exception e) {}

        // Order Date
        try {
            WebElement orderDate = driver.findElement(By.id("orderDate"));
            jse.executeScript("arguments[0].scrollIntoView({block:'center'})", orderDate);
            orderDate.clear();
            orderDate.sendKeys("01-07-2025");
            orderDate.sendKeys(Keys.TAB);
            hideDatepicker();
            Thread.sleep(200);
        } catch (Exception e) {}

        // Remarks
        try {
            WebElement remarks = driver.findElement(By.id("remarks"));
            jse.executeScript("arguments[0].scrollIntoView({block:'center'})", remarks);
            remarks.clear();
            remarks.sendKeys("Legal Order Test");
            Thread.sleep(200);
        } catch (Exception e) {}

        // Save
        WebElement saveBtn = wait.until(ExpectedConditions.elementToBeClickable(By.id("saveBtn")));
        jse.executeScript("arguments[0].scrollIntoView({block:'center'})", saveBtn);
        Thread.sleep(300);
        saveBtn.click();
        Thread.sleep(1500);

        scrollToSearch();
        String toast = getSuccessToast();
        log("Save", "Save Legal Order", "Success", toast.isEmpty() ? "No toast" : toast, !toast.isEmpty());

        switchBackToFrame();

        // View
        List<WebElement> viewBtns = driver.findElements(By.xpath("//*[contains(@class,'ViewBtn')]"));
        log("View", "View buttons found", ">0", String.valueOf(viewBtns.size()), !viewBtns.isEmpty());
        if (!viewBtns.isEmpty()) {
            jse.executeScript("arguments[0].scrollIntoView({block:'center'})", viewBtns.get(0));
            viewBtns.get(0).click();
            Thread.sleep(1500);
            log("View", "View record opened", "Opened", "Clicked", true);
        }

        // Edit
        List<WebElement> editBtns = driver.findElements(By.xpath("//*[contains(@class,'editBtn')]"));
        log("Edit", "Edit buttons found", ">0", String.valueOf(editBtns.size()), !editBtns.isEmpty());
        if (!editBtns.isEmpty()) {
            jse.executeScript("arguments[0].scrollIntoView({block:'center'})", editBtns.get(0));
            editBtns.get(0).click();
            Thread.sleep(1500);

            try {
                WebElement remarks = driver.findElement(By.id("remarks"));
                remarks.clear();
                remarks.sendKeys("Legal Order Updated");
            } catch (Exception e) {}

            WebElement updateBtn = wait.until(ExpectedConditions.elementToBeClickable(By.id("saveBtn")));
            jse.executeScript("arguments[0].scrollIntoView({block:'center'})", updateBtn);
            updateBtn.click();
            Thread.sleep(1500);

            scrollToSearch();
            String updateToast = getSuccessToast();
            log("Edit", "Update Legal Order", "Success", updateToast.isEmpty() ? "No toast" : updateToast, !updateToast.isEmpty());
            switchBackToFrame();
        }

        sa.assertAll();
    }
}
