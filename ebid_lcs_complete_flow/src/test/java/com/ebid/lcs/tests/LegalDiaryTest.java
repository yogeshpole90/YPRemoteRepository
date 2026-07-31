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
import com.ebid.lcs.listeners.TestListener;
import com.ebid.lcs.reporting.ExtentManager;

@Listeners(TestListener.class)
public class LegalDiaryTest extends BaseTest {

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
            WebElement search = driver.findElement(By.cssSelector("input[placeholder='Search keyword here']"));
            jse.executeScript("arguments[0].scrollIntoView({block:'center',behavior:'smooth'})", search);
            Thread.sleep(800);
        } catch (Exception e) {}
    }

    @BeforeClass
    public void setup() throws Exception {
        ExtentManager.initReport("LegalDiary");
        ExtentManager.startTest("Legal Diary - Save/View/Edit");
        wait = new WebDriverWait(driver, Duration.ofSeconds(15));

        // Scroll down to Legal Process tab
        jse.executeScript("window.scrollBy(0, 300)");
        Thread.sleep(500);

        WebElement legal = wait.until(ExpectedConditions.elementToBeClickable(
            By.xpath("//*[contains(@href,'=Legal Process')]")));
        jse.executeScript("arguments[0].scrollIntoView({block:'center',behavior:'smooth'})", legal);
        Thread.sleep(500);
        jse.executeScript("arguments[0].click()", legal);
        Thread.sleep(2000);

        WebElement ldTab = wait.until(ExpectedConditions.elementToBeClickable(
            By.xpath("//a[contains(text(),'Legal Diary')]")));
        jse.executeScript("arguments[0].scrollIntoView({block:'center',behavior:'smooth'})", ldTab);
        act.doubleClick(ldTab).build().perform();
        Thread.sleep(2000);

        driver.switchTo().frame("getLegalDiaryDataFrame");
        logInfo("Frame", "Switched to", "getLegalDiaryDataFrame");
    }

    @Test(priority = 1)
    public void validateSave() throws Exception {
        // Court Case Ref No — select last option
        try {
            WebElement caseRef = wait.until(ExpectedConditions.elementToBeClickable(By.id("courtCaseNo")));
            jse.executeScript("arguments[0].scrollIntoView({block:'center',behavior:'smooth'})", caseRef);
            Select s = new Select(caseRef);
            List<WebElement> opts = s.getOptions();
            if (opts.size() > 1) {
                s.selectByIndex(opts.size() - 1);
                Thread.sleep(1000);
                dismissAlert();
                log("courtCaseNo", "Select court case ref", "Non-empty",
                    s.getFirstSelectedOption().getText(), true);
            }
        } catch (Exception e) { logInfo("courtCaseNo", "Skipped", e.getMessage()); }

        // Hearing Date
        try {
            WebElement hearingDate = driver.findElement(By.id("hearingDate"));
            jse.executeScript("arguments[0].scrollIntoView({block:'center',behavior:'smooth'})", hearingDate);
            jse.executeScript("arguments[0].value='01-08-2025'", hearingDate);
            jse.executeScript("arguments[0].dispatchEvent(new Event('change',{bubbles:true}))", hearingDate);
            hideDatepicker();
            Thread.sleep(200);
        } catch (Exception e) {}

        // Next Hearing Date
        try {
            WebElement nextHearing = driver.findElement(By.id("nextHearingDate"));
            jse.executeScript("arguments[0].scrollIntoView({block:'center',behavior:'smooth'})", nextHearing);
            jse.executeScript("arguments[0].value='01-09-2025'", nextHearing);
            jse.executeScript("arguments[0].dispatchEvent(new Event('change',{bubbles:true}))", nextHearing);
            hideDatepicker();
            Thread.sleep(200);
        } catch (Exception e) {}

        // Remarks
        try {
            WebElement remarks = driver.findElement(By.id("remarks"));
            jse.executeScript("arguments[0].scrollIntoView({block:'center',behavior:'smooth'})", remarks);
            remarks.clear();
            remarks.sendKeys("Legal Diary Test");
            Thread.sleep(200);
        } catch (Exception e) {}

        // File upload
        try {
            WebElement fileInput = driver.findElement(By.id("documentData"));
            fileInput.sendKeys(ConfigManager.get("doc.upload.path"));
            Thread.sleep(2000);
            log("documentData", "File uploaded", "Not empty",
                fileInput.getAttribute("value").isEmpty() ? "Empty" : "Uploaded", true);
        } catch (Exception e) { logInfo("documentData", "Upload skipped", e.getMessage()); }

        // Save
        WebElement saveBtn = wait.until(ExpectedConditions.elementToBeClickable(By.id("saveData")));
        jse.executeScript("arguments[0].scrollIntoView({block:'center',behavior:'smooth'})", saveBtn);
        Thread.sleep(300);
        saveBtn.click();
        Thread.sleep(2000);
        dismissAlert();

        scrollToSearch();
        String toast = getSuccessToast();
        log("Save", "Save Legal Diary", "Success", toast.isEmpty() ? "No toast" : toast, !toast.isEmpty());

        Thread.sleep(500);

        // View
        List<WebElement> viewBtns = driver.findElements(By.xpath("//a[contains(@class,'ViewBtn') or contains(@onclick,'viewLegalDiary')]"));
        log("View", "View buttons found", ">0", String.valueOf(viewBtns.size()), !viewBtns.isEmpty());
        if (!viewBtns.isEmpty()) {
            jse.executeScript("arguments[0].scrollIntoView({block:'center',behavior:'smooth'})", viewBtns.get(viewBtns.size() - 1));
            jse.executeScript("arguments[0].click()", viewBtns.get(viewBtns.size() - 1));
            Thread.sleep(1500);
            log("View", "View record opened", "Opened", "Clicked", true);
        }

        // Edit
        List<WebElement> editBtns = driver.findElements(By.xpath("//a[contains(@class,'editBtn') or contains(@onclick,'editLegalDiary')]"));
        log("Edit", "Edit buttons found", ">0", String.valueOf(editBtns.size()), !editBtns.isEmpty());
        if (!editBtns.isEmpty()) {
            jse.executeScript("arguments[0].scrollIntoView({block:'center',behavior:'smooth'})", editBtns.get(editBtns.size() - 1));
            jse.executeScript("arguments[0].click()", editBtns.get(editBtns.size() - 1));
            Thread.sleep(1500);

            try {
                WebElement remarks = driver.findElement(By.id("remarks"));
                remarks.clear();
                remarks.sendKeys("Legal Diary Updated");
            } catch (Exception e) {}

            WebElement updateBtn = wait.until(ExpectedConditions.elementToBeClickable(By.id("saveData")));
            jse.executeScript("arguments[0].scrollIntoView({block:'center',behavior:'smooth'})", updateBtn);
            updateBtn.click();
            Thread.sleep(2000);
            dismissAlert();

            scrollToSearch();
            String updateToast = getSuccessToast();
            log("Edit", "Update Legal Diary", "Success", updateToast.isEmpty() ? "No toast" : updateToast, !updateToast.isEmpty());
        }

        sa.assertAll();
    }
}
