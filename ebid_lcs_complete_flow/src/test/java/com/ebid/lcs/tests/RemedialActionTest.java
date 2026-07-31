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
import com.ebid.lcs.listeners.TestListener;
import com.ebid.lcs.reporting.ExtentManager;

@Listeners(TestListener.class)
public class RemedialActionTest extends BaseTest {

    private WebDriverWait wait;

    @BeforeClass
    public void setup() throws Exception {
        ExtentManager.initReport("RemedialAction");
        ExtentManager.startTest("Remedial Action - Save/View");
        wait = new WebDriverWait(driver, Duration.ofSeconds(15));

        WebElement remedial = wait.until(ExpectedConditions.elementToBeClickable(
            By.xpath("(//*[contains(@href,'=Remedial Action')])[1]")));
        jse.executeScript("arguments[0].scrollIntoView({block:'center',behavior:'smooth'})", remedial);
        remedial.click();
        Thread.sleep(2000);

        driver.switchTo().frame("caseMstListPageFrame");
        logInfo("Frame", "Switched to", "caseMstListPageFrame");
    }

    @Test(priority = 1)
    public void validateSave() throws Exception {
        // actionId — required dropdown
        try {
            WebElement actionId = wait.until(ExpectedConditions.elementToBeClickable(By.id("actionId")));
            jse.executeScript("arguments[0].scrollIntoView({block:'center',behavior:'smooth'})", actionId);
            new Select(actionId).selectByIndex(1);
            Thread.sleep(300);
            log("actionId", "Select action", "Non-empty",
                new Select(actionId).getFirstSelectedOption().getText(), true);
        } catch (Exception e) { logInfo("actionId", "Skipped", e.getMessage()); }

        // commments — required text
        try {
            WebElement comments = wait.until(ExpectedConditions.visibilityOfElementLocated(By.id("commments")));
            jse.executeScript("arguments[0].scrollIntoView({block:'center',behavior:'smooth'})", comments);
            comments.clear();
            comments.sendKeys("Remedial Action Test");
            Thread.sleep(200);
        } catch (Exception e) { logInfo("commments", "Skipped", e.getMessage()); }

        // Save
        WebElement saveBtn = wait.until(ExpectedConditions.elementToBeClickable(By.id("save")));
        jse.executeScript("arguments[0].scrollIntoView({block:'center',behavior:'smooth'})", saveBtn);
        Thread.sleep(300);
        saveBtn.click();
        Thread.sleep(2000);

        // Toast / success message inside frame
        String toast = getSuccessToast();
        if (toast.isEmpty()) {
            List<WebElement> successMsg = driver.findElements(By.xpath("//*[contains(text(),'Saved Successfully')]"));
            if (!successMsg.isEmpty()) toast = successMsg.get(0).getText().trim();
        }
        log("Save", "Save Remedial Action", "Success", toast.isEmpty() ? "No message" : toast, !toast.isEmpty());

        Thread.sleep(1000);

        // View last record
        List<WebElement> viewBtns = driver.findElements(By.xpath("//a[contains(text(),'View')]"));
        log("View", "View buttons found", ">0", String.valueOf(viewBtns.size()), !viewBtns.isEmpty());
        if (!viewBtns.isEmpty()) {
            jse.executeScript("arguments[0].scrollIntoView({block:'center',behavior:'smooth'})", viewBtns.get(viewBtns.size() - 1));
            Thread.sleep(300);
            viewBtns.get(viewBtns.size() - 1).click();
            Thread.sleep(1500);
            log("View", "View record opened", "Opened", "Clicked", true);
        }

        sa.assertAll();
    }
}
