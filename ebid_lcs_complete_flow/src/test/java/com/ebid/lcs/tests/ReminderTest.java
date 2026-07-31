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

import com.ebid.lcs.base.BaseTest;
import com.ebid.lcs.listeners.TestListener;
import com.ebid.lcs.reporting.ExtentManager;

@Listeners(TestListener.class)
public class ReminderTest extends BaseTest {

    private WebDriverWait wait;

    private void scrollToSearch() {
        try {
            WebElement search = driver.findElement(By.cssSelector("input[placeholder='Search keyword here']"));
            jse.executeScript("arguments[0].scrollIntoView({block:'center',behavior:'smooth'})", search);
            Thread.sleep(800);
        } catch (Exception e) {}
    }

    @BeforeClass
    public void setup() throws Exception {
        ExtentManager.initReport("Reminder");
        ExtentManager.startTest("Reminder - Save/View/Edit");
        wait = new WebDriverWait(driver, Duration.ofSeconds(15));

        WebElement commTab = wait.until(ExpectedConditions.elementToBeClickable(
            By.xpath("//a[contains(@href,'activeTab=Communication History')]")));
        jse.executeScript("arguments[0].scrollIntoView({block:'center'})", commTab);
        commTab.click();
        Thread.sleep(1500);

        WebElement rmTab = wait.until(ExpectedConditions.elementToBeClickable(
            By.xpath("//a[contains(text(),'Reminder')]")));
        jse.executeScript("arguments[0].scrollIntoView({block:'center'})", rmTab);
        act.doubleClick(rmTab).build().perform();
        Thread.sleep(2000);

        driver.switchTo().frame("fetchReminderDtlsPageFrame");
        logInfo("Frame", "Switched to", "fetchReminderDtlsPageFrame");
    }

    @Test(priority = 1)
    public void validateSave() throws Exception {
        new Select(driver.findElement(By.id("reminderType"))).selectByIndex(1);
        Thread.sleep(300);

        driver.findElement(By.id("reminderDate")).clear();
        driver.findElement(By.id("reminderDate")).sendKeys("11-05-2026");
        driver.findElement(By.id("reminderCreateDate")).clear();
        driver.findElement(By.id("reminderCreateDate")).sendKeys("11-05-2026");
        driver.findElement(By.id("remarks")).clear();
        driver.findElement(By.id("remarks")).sendKeys("Reminder Test");

        WebElement saveBtn = driver.findElement(By.id("save"));
        jse.executeScript("arguments[0].scrollIntoView({block:'center'})", saveBtn);
        Thread.sleep(300);
        saveBtn.click();
        Thread.sleep(1000);

        try {
            WebElement yes = driver.findElement(By.id("submitForm"));
            if (yes.isDisplayed()) { yes.click(); Thread.sleep(2000); }
        } catch (Exception e) {}

        // Scroll to search box — toast appears near top of frame
        scrollToSearch();

        String toast = getSuccessToast();
        log("Save", "Save Reminder", "Success", toast.isEmpty() ? "No toast" : toast, !toast.isEmpty());

        // Wait for table row to appear
        wait.until(ExpectedConditions.presenceOfElementLocated(
            By.cssSelector("#dt-basicDetails tbody tr")));
        Thread.sleep(500);
        sa.assertAll();
    }

    @Test(priority = 2)
    public void validateView() throws Exception {
        scrollToSearch();

        WebElement viewBtn = wait.until(ExpectedConditions.elementToBeClickable(
            By.cssSelector("#dt-basicDetails tbody tr:first-child a[onclick*='ViewData']")));
        jse.executeScript("arguments[0].scrollIntoView({block:'center'})", viewBtn);
        jse.executeScript("arguments[0].click()", viewBtn);
        Thread.sleep(1500);
        log("View", "View Reminder record", "Opened", "Clicked", true);
    }

    @Test(priority = 3)
    public void validateEdit() throws Exception {
        scrollToSearch();

        WebElement editBtn = wait.until(ExpectedConditions.elementToBeClickable(
            By.cssSelector("#dt-basicDetails tbody tr:first-child a.editBtn")));
        jse.executeScript("arguments[0].scrollIntoView({block:'center'})", editBtn);
        jse.executeScript("arguments[0].click()", editBtn);
        Thread.sleep(1500);

        new Select(driver.findElement(By.id("reminderType"))).selectByIndex(1);
        Thread.sleep(200);
        driver.findElement(By.id("reminderDate")).clear();
        driver.findElement(By.id("reminderDate")).sendKeys("11-05-2026");
        driver.findElement(By.id("reminderCreateDate")).clear();
        driver.findElement(By.id("reminderCreateDate")).sendKeys("11-05-2026");

        WebElement remarks = driver.findElement(By.id("remarks"));
        remarks.clear();
        remarks.sendKeys("Reminder Updated");

        WebElement saveBtn = driver.findElement(By.id("save"));
        jse.executeScript("arguments[0].scrollIntoView({block:'center'})", saveBtn);
        Thread.sleep(300);
        saveBtn.click();
        Thread.sleep(1000);

        try {
            WebElement yes = driver.findElement(By.id("submitForm"));
            if (yes.isDisplayed()) { yes.click(); Thread.sleep(2000); }
        } catch (Exception e) {}

        scrollToSearch();
        String toast = getSuccessToast();
        log("Edit", "Update Reminder", "Success", toast.isEmpty() ? "No toast" : toast, !toast.isEmpty());
        sa.assertAll();
    }
}
