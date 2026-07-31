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
public class FollowUpTest extends BaseTest {

    private WebDriverWait wait;

    private void hideDatepicker() {
        try {
            jse.executeScript("var dp=document.querySelectorAll('.datepicker,.ui-datepicker,.daterangepicker,.bootstrap-datetimepicker-widget');dp.forEach(function(el){el.style.display='none';});");
        } catch (Exception e) {}
        try { driver.findElement(By.tagName("body")).sendKeys(Keys.ESCAPE); } catch (Exception e) {}
    }

    private void scrollToSearch() {
        try {
            WebElement search = driver.findElement(By.cssSelector("input[placeholder='Search keyword here']"));
            jse.executeScript("arguments[0].scrollIntoView({block:'center',behavior:'smooth'})", search);
            Thread.sleep(500);
        } catch (Exception e) {}
    }

    @BeforeClass
    public void setup() throws Exception {
        ExtentManager.initReport("FollowUp");
        ExtentManager.startTest("FollowUp - Save");
        wait = new WebDriverWait(driver, Duration.ofSeconds(10));

        WebElement followUp = wait.until(ExpectedConditions.elementToBeClickable(
            By.xpath("//*[contains(@href,'Follow-Up')]")));
        jse.executeScript("arguments[0].scrollIntoView({block:'center',behavior:'smooth'})", followUp);
        followUp.click();
        Thread.sleep(1000);

        WebElement addFU = wait.until(ExpectedConditions.elementToBeClickable(
            By.xpath("//*[contains(text(),'Add Follow-Up')]")));
        jse.executeScript("arguments[0].scrollIntoView({block:'center',behavior:'smooth'})", addFU);
        addFU.click();
        Thread.sleep(1500);

        driver.switchTo().frame("addcommunicationHistoryFrame");
        logInfo("Frame", "Switched to", "addcommunicationHistoryFrame");

        // Click select2 container inside frame
        WebElement select2 = wait.until(ExpectedConditions.elementToBeClickable(
            By.cssSelector("#loanAcNoSelect ~ .select2-container")));
        jse.executeScript("arguments[0].scrollIntoView({block:'center',behavior:'smooth'})", select2);
        select2.click();
        Thread.sleep(500);

        // select2 dropdown renders outside frame — switch to defaultContent to click option
        driver.switchTo().defaultContent();
        Thread.sleep(300);
        try {
            WebElement firstOpt = wait.until(ExpectedConditions.elementToBeClickable(
                By.cssSelector(".select2-results__option:first-child")));
            firstOpt.click();
            Thread.sleep(500);
        } catch (Exception e) {
            // Try inside frame
            driver.switchTo().frame("addcommunicationHistoryFrame");
            WebElement firstOpt = wait.until(ExpectedConditions.elementToBeClickable(
                By.cssSelector(".select2-results__option:first-child")));
            firstOpt.click();
            Thread.sleep(500);
            driver.switchTo().defaultContent();
        }

        // Re-enter frame after loan account selection
        driver.switchTo().frame("addcommunicationHistoryFrame");
        Thread.sleep(2000); // wait for AJAX to populate fields

        // Verify frame loaded by checking any element
        try {
            wait.until(ExpectedConditions.presenceOfElementLocated(By.id("saveData")));
        } catch (Exception e) {
            // Frame may have reloaded — re-switch
            driver.switchTo().defaultContent();
            Thread.sleep(500);
            driver.switchTo().frame("addcommunicationHistoryFrame");
            Thread.sleep(1000);
        }
        logInfo("loanAcNoSelect", "Loan Account Selected", "First option");
    }

    @Test(priority = 1)
    public void validateSave() throws Exception {
        // Communication Type (typeOfFollowUp)
        try {
            WebElement commType = driver.findElement(By.id("typeOfFollowUp"));
            jse.executeScript("arguments[0].scrollIntoView({block:'center',behavior:'smooth'})", commType);
            jse.executeScript("arguments[0].selectedIndex=1; arguments[0].dispatchEvent(new Event('change',{bubbles:true}))", commType);
            Thread.sleep(500);
            String selected = new Select(commType).getFirstSelectedOption().getText();
            log("typeOfFollowUp", "Communication Type selected", "Non-empty", selected, !selected.contains("SELECT"));
        } catch (Exception e) { logInfo("typeOfFollowUp", "Skipped", e.getMessage()); }

        // Action (actionMap)
        try {
            WebElement action = driver.findElement(By.id("actionMap"));
            jse.executeScript("arguments[0].scrollIntoView({block:'center',behavior:'smooth'})", action);
            jse.executeScript("arguments[0].selectedIndex=1; arguments[0].dispatchEvent(new Event('change',{bubbles:true}))", action);
            Thread.sleep(300);
            log("actionMap", "Action selected", "Non-empty",
                new Select(action).getFirstSelectedOption().getText(), true);
        } catch (Exception e) { logInfo("actionMap", "Skipped", e.getMessage()); }

        // Result (resultMap)
        try {
            WebElement result = driver.findElement(By.id("resultMap"));
            jse.executeScript("arguments[0].scrollIntoView({block:'center',behavior:'smooth'})", result);
            jse.executeScript("arguments[0].selectedIndex=1; arguments[0].dispatchEvent(new Event('change',{bubbles:true}))", result);
            Thread.sleep(300);
            log("resultMap", "Result selected", "Non-empty",
                new Select(result).getFirstSelectedOption().getText(), true);
        } catch (Exception e) { logInfo("resultMap", "Skipped", e.getMessage()); }

        // Result Date
        try {
            WebElement resultDate = driver.findElement(By.id("resultDate"));
            jse.executeScript("arguments[0].value='01-07-2025'; arguments[0].dispatchEvent(new Event('change',{bubbles:true}))", resultDate);
            hideDatepicker();
            Thread.sleep(200);
        } catch (Exception e) {}

        // Action Date (followUpDate)
        try {
            WebElement followUpDate = driver.findElement(By.id("followUpDate"));
            jse.executeScript("arguments[0].value='01-07-2025'; arguments[0].dispatchEvent(new Event('change',{bubbles:true}))", followUpDate);
            hideDatepicker();
            Thread.sleep(200);
        } catch (Exception e) {}

        // Cash Type (cashType)
        try {
            WebElement cashType = driver.findElement(By.id("cashType"));
            jse.executeScript("arguments[0].scrollIntoView({block:'center',behavior:'smooth'})", cashType);
            jse.executeScript("arguments[0].selectedIndex=1; arguments[0].dispatchEvent(new Event('change',{bubbles:true}))", cashType);
            Thread.sleep(200);
        } catch (Exception e) {}

        // Party Contact Name (partyContactName)
        try {
            WebElement partyName = driver.findElement(By.id("partyContactName"));
            jse.executeScript("arguments[0].scrollIntoView({block:'center',behavior:'smooth'})", partyName);
            partyName.clear();
            partyName.sendKeys("Test Party");
            Thread.sleep(200);
        } catch (Exception e) {}

        // Status (status)
        try {
            WebElement status = driver.findElement(By.id("status"));
            jse.executeScript("arguments[0].scrollIntoView({block:'center',behavior:'smooth'})", status);
            jse.executeScript("arguments[0].selectedIndex=1; arguments[0].dispatchEvent(new Event('change',{bubbles:true}))", status);
            Thread.sleep(200);
        } catch (Exception e) {}

        // followUpType (if separate from typeOfFollowUp)
        try {
            WebElement followUpType = driver.findElement(By.id("followUpType"));
            jse.executeScript("arguments[0].selectedIndex=1; arguments[0].dispatchEvent(new Event('change',{bubbles:true}))", followUpType);
            Thread.sleep(200);
        } catch (Exception e) {}

        // Remark/Narration (remarks)
        try {
            WebElement remarks = driver.findElement(By.id("remarks"));
            jse.executeScript("arguments[0].scrollIntoView({block:'center',behavior:'smooth'})", remarks);
            remarks.clear();
            remarks.sendKeys("FollowUp Test");
            Thread.sleep(200);
        } catch (Exception e) {}

        // Save
        WebElement saveBtn = driver.findElement(By.id("saveData"));
        jse.executeScript("arguments[0].scrollIntoView({block:'center',behavior:'smooth'})", saveBtn);
        Thread.sleep(300);
        saveBtn.click();
        Thread.sleep(1500);

        // Toast — check in frame first, then parentFrame
        scrollToSearch();
        String toast = getSuccessToast();
        if (toast.isEmpty()) {
            driver.switchTo().parentFrame();
            Thread.sleep(300);
            toast = getSuccessToast();
            driver.switchTo().frame("addcommunicationHistoryFrame");
        }
        log("Save", "Save FollowUp", "Success", toast.isEmpty() ? "No toast" : toast, !toast.isEmpty());
        sa.assertAll();
    }
}
