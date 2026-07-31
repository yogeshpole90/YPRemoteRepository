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
public class CourtCaseTest extends BaseTest {

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
            WebElement search = driver.findElement(By.xpath("//input[@type='search' and @aria-controls='dt-basicDetails']"));
            jse.executeScript("arguments[0].scrollIntoView({block:'center',behavior:'smooth'})", search);
            Thread.sleep(800);
        } catch (Exception e) {}
    }

    private void backToFrame() throws Exception {
        driver.switchTo().parentFrame();
        Thread.sleep(500);
        // Scroll down to make Legal Process tab visible
        jse.executeScript("window.scrollBy(0, 300)");
        Thread.sleep(300);
        WebElement legalTab = driver.findElement(By.xpath("//*[contains(@href,'=Legal Process')]"));
        jse.executeScript("arguments[0].scrollIntoView({block:'center',behavior:'smooth'})", legalTab);
        Thread.sleep(500);
        driver.switchTo().frame("courtCaseMstListPageFrame");
        Thread.sleep(1000);
    }

    @BeforeClass(alwaysRun = true)
    public void setup() throws Exception {
        ExtentManager.initReport("CourtCase");
        ExtentManager.startTest("Court Case - Save/View/Edit");
        wait = new WebDriverWait(driver, Duration.ofSeconds(15));

        // Move to Legal stage
        moveToNextStage();

        // Scroll down to Legal Process tab
        jse.executeScript("window.scrollBy(0, 300)");
        Thread.sleep(500);

        WebElement legal = wait.until(ExpectedConditions.elementToBeClickable(
            By.xpath("//*[contains(@href,'=Legal Process')]")));
        jse.executeScript("arguments[0].scrollIntoView({block:'center',behavior:'smooth'})", legal);
        Thread.sleep(500);
        jse.executeScript("arguments[0].click()", legal);
        Thread.sleep(2000);

        driver.switchTo().frame("courtCaseMstListPageFrame");
        logInfo("Frame", "Switched to", "courtCaseMstListPageFrame");
    }

    @Test(priority = 1)
    public void validateSave() throws Exception {
        // courtCaseType
        try {
            WebElement courtType = wait.until(ExpectedConditions.elementToBeClickable(By.id("courtCaseType")));
            jse.executeScript("arguments[0].scrollIntoView({block:'center',behavior:'smooth'})", courtType);
            new Select(courtType).selectByIndex(1);
            Thread.sleep(300);
            log("courtCaseType", "Selected", "Non-empty",
                new Select(courtType).getFirstSelectedOption().getText(), true);
        } catch (Exception e) {}

        // courtName
        try {
            WebElement courtName = wait.until(ExpectedConditions.visibilityOfElementLocated(By.id("courtName")));
            jse.executeScript("arguments[0].scrollIntoView({block:'center',behavior:'smooth'})", courtName);
            courtName.clear();
            courtName.sendKeys("Test Court");
            Thread.sleep(200);
        } catch (Exception e) {}

        // suitAmount
        try {
            WebElement suitAmt = driver.findElement(By.id("suitAmount_txt"));
            jse.executeScript("arguments[0].scrollIntoView({block:'center',behavior:'smooth'})", suitAmt);
            suitAmt.clear();
            suitAmt.sendKeys("50000");
            suitAmt.sendKeys(Keys.TAB);
            Thread.sleep(300);
            dismissAlert();
        } catch (Exception e) {}

        // filingDate
        try {
            WebElement filingDate = driver.findElement(By.id("filingDate"));
            jse.executeScript("arguments[0].scrollIntoView({block:'center',behavior:'smooth'})", filingDate);
            jse.executeScript("arguments[0].value='01-07-2025'", filingDate);
            hideDatepicker();
            Thread.sleep(200);
        } catch (Exception e) {}

        // Save
        WebElement saveBtn = wait.until(ExpectedConditions.elementToBeClickable(By.xpath("//button[@id='save']")));
        jse.executeScript("arguments[0].scrollIntoView({block:'center',behavior:'smooth'})", saveBtn);
        Thread.sleep(300);
        saveBtn.click();
        Thread.sleep(1500);
        dismissAlert();

        scrollToSearch();
        String toast = getSuccessToast();
        log("Save", "Save Court Case", "Success", toast.isEmpty() ? "No toast" : toast, !toast.isEmpty());

        backToFrame();

        // View
        List<WebElement> viewBtns = driver.findElements(By.cssSelector("a.ViewBtn"));
        log("View", "View buttons found", ">0", String.valueOf(viewBtns.size()), !viewBtns.isEmpty());
        if (!viewBtns.isEmpty()) {
            jse.executeScript("arguments[0].scrollIntoView({block:'center',behavior:'smooth'})", viewBtns.get(viewBtns.size() - 1));
            viewBtns.get(viewBtns.size() - 1).click();
            Thread.sleep(1500);
            log("View", "View record opened", "Opened", "Clicked", true);
            backToFrame();
        }

        // Edit
        List<WebElement> editBtns = driver.findElements(By.cssSelector("a.editBtn"));
        log("Edit", "Edit buttons found", ">0", String.valueOf(editBtns.size()), !editBtns.isEmpty());
        if (!editBtns.isEmpty()) {
            jse.executeScript("arguments[0].scrollIntoView({block:'center',behavior:'smooth'})", editBtns.get(editBtns.size() - 1));
            editBtns.get(editBtns.size() - 1).click();
            Thread.sleep(1500);

            try {
                WebElement suitAmt = driver.findElement(By.id("suitAmount_txt"));
                jse.executeScript("arguments[0].value='99999'", suitAmt);
            } catch (Exception e) {}

            WebElement updateBtn = wait.until(ExpectedConditions.elementToBeClickable(By.xpath("//button[@id='save']")));
            jse.executeScript("arguments[0].scrollIntoView({block:'center',behavior:'smooth'})", updateBtn);
            updateBtn.click();
            Thread.sleep(1500);
            dismissAlert();
            scrollToSearch();
            String updateToast = getSuccessToast();
            log("Edit", "Update Court Case", "Success", updateToast.isEmpty() ? "No toast" : updateToast, !updateToast.isEmpty());
        }

        sa.assertAll();
    }
}
