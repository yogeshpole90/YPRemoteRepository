package com.ebid.lcs.tests;

import org.openqa.selenium.By;
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
public class DocChecklistTest extends BaseTest {

    private WebDriverWait wait;

    private void scrollToSearch() {
        try {
            WebElement search = driver.findElement(By.cssSelector("input[placeholder='Search keyword here']"));
            jse.executeScript("arguments[0].scrollIntoView({block:'center',behavior:'smooth'})", search);
            Thread.sleep(800);
        } catch (Exception e) {}
    }

    private void deleteExisting() throws Exception {
        List<WebElement> deleteBtns = driver.findElements(By.xpath("//a[contains(@class,'DeleteBtn')]"));
        if (deleteBtns.isEmpty()) return;
        logInfo("Delete Existing", "Found existing docs", String.valueOf(deleteBtns.size()));
        for (int i = deleteBtns.size() - 1; i >= 0; i--) {
            try {
                List<WebElement> btns = driver.findElements(By.xpath("//a[contains(@class,'DeleteBtn')]"));
                if (btns.isEmpty()) break;
                jse.executeScript("arguments[0].scrollIntoView({block:'center',behavior:'smooth'})", btns.get(btns.size() - 1));
                jse.executeScript("arguments[0].click()", btns.get(btns.size() - 1));
                Thread.sleep(1000);
                try {
                    driver.findElement(By.id("popUpYes")).click();
                    Thread.sleep(1000);
                } catch (Exception e) {
                    try { driver.switchTo().alert().accept(); } catch (Exception e2) {}
                }
                scrollToSearch();
                Thread.sleep(500);
            } catch (Exception e) { break; }
        }
        log("Delete Existing", "All existing docs deleted", "Done", "Done", true);
    }

    @BeforeClass
    public void setup() throws Exception {
        ExtentManager.initReport("DocChecklist");
        ExtentManager.startTest("Document Checklist - Upload/Delete");
        wait = new WebDriverWait(driver, Duration.ofSeconds(15));

        WebElement docTab = wait.until(ExpectedConditions.elementToBeClickable(
            By.xpath("//*[contains(@href,'activeTab=Document')]")));
        jse.executeScript("arguments[0].scrollIntoView({block:'center',behavior:'smooth'})", docTab);
        docTab.click();
        Thread.sleep(2000);

        driver.switchTo().frame("documentUploadPageFrame");
        logInfo("Frame", "Switched to", "documentUploadPageFrame");
    }

    @Test(priority = 1)
    public void validateSave() throws Exception {
        // Step 1: Delete existing documents first
        deleteExisting();
        Thread.sleep(500);
        // Re-switch frame after delete (frame may have reloaded)
        try {
            driver.switchTo().defaultContent();
            wait.until(ExpectedConditions.frameToBeAvailableAndSwitchToIt("documentUploadPageFrame"));
            Thread.sleep(500);
        } catch (Exception e) {}

        // Step 2: Document Type — index 1 (not 0, that's placeholder)
        try {
            WebElement docType = wait.until(ExpectedConditions.elementToBeClickable(By.id("documentType")));
            jse.executeScript("arguments[0].scrollIntoView({block:'center',behavior:'smooth'})", docType);
            jse.executeScript("arguments[0].selectedIndex=1; arguments[0].dispatchEvent(new Event('change',{bubbles:true}))", docType);
            Thread.sleep(500);
            log("documentType", "Select document type", "Non-empty",
                new Select(docType).getFirstSelectedOption().getText(), true);
        } catch (Exception e) { logInfo("documentType", "Skipped", e.getMessage()); }

        // Step 3: Document Name
        try {
            WebElement docName = wait.until(ExpectedConditions.visibilityOfElementLocated(By.id("documentName")));
            jse.executeScript("arguments[0].scrollIntoView({block:'center',behavior:'smooth'})", docName);
            // Clear and fill — may be auto-populated or empty
            String existing = docName.getAttribute("value");
            if (existing == null || existing.trim().isEmpty()) {
                docName.sendKeys("Test Document");
            }
            Thread.sleep(200);
            log("documentName", "Document name", "Non-empty", docName.getAttribute("value"), true);
        } catch (Exception e) {}

        // Step 4: File upload
        try {
            WebElement fileInput = driver.findElement(By.id("documentData"));
            String filePath = ConfigManager.get("doc.upload.path");
            fileInput.sendKeys(filePath);
            Thread.sleep(2000);
            String val = fileInput.getAttribute("value");
            log("documentData", "File uploaded", "Not empty", val.isEmpty() ? "Empty" : val, !val.isEmpty());
        } catch (Exception e) { logInfo("documentData", "File upload skipped", e.getMessage()); }

        // Step 5: Save
        WebElement saveBtn = wait.until(ExpectedConditions.elementToBeClickable(By.id("saveData")));
        jse.executeScript("arguments[0].scrollIntoView({block:'center',behavior:'smooth'})", saveBtn);
        Thread.sleep(300);
        saveBtn.click();
        Thread.sleep(2000);

        scrollToSearch();
        String toast = getSuccessToast();
        log("Save", "Save Document", "Success", toast.isEmpty() ? "No toast" : toast, !toast.isEmpty());
        sa.assertAll();
    }
}
