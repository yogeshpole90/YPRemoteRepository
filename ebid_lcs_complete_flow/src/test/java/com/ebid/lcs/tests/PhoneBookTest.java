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
public class PhoneBookTest extends BaseTest {

    private WebDriverWait wait;

    @BeforeClass
    public void setup() throws Exception {
        ExtentManager.initReport("PhoneBook");
        ExtentManager.startTest("PhoneBook - Save/Close");
        wait = new WebDriverWait(driver, Duration.ofSeconds(15));

        WebElement phoneBookIcon = wait.until(ExpectedConditions.presenceOfElementLocated(
            By.xpath("//a[contains(@data-target,'phoneBook') and contains(@title,'Phone Book')]")));
        jse.executeScript("arguments[0].scrollIntoView({block:'center',behavior:'smooth'})", phoneBookIcon);
        jse.executeScript("arguments[0].click()", phoneBookIcon);
        Thread.sleep(1500);
    }

    @Test(priority = 1)
    public void validateSave() throws Exception {
        // phoneType
        try {
            WebElement phoneType = wait.until(ExpectedConditions.elementToBeClickable(By.id("phoneType")));
            jse.executeScript("arguments[0].scrollIntoView({block:'center',behavior:'smooth'})", phoneType);
            new Select(phoneType).selectByIndex(1);
            Thread.sleep(300);
            log("phoneType", "Selected", "Non-empty",
                new Select(phoneType).getFirstSelectedOption().getText(), true);
        } catch (Exception e) { logInfo("phoneType", "Skipped", e.getMessage()); }

        // contactName
        try {
            WebElement contactName = wait.until(ExpectedConditions.visibilityOfElementLocated(By.id("contactName")));
            jse.executeScript("arguments[0].scrollIntoView({block:'center',behavior:'smooth'})", contactName);
            contactName.clear();
            contactName.sendKeys("Test Contact");
            Thread.sleep(200);
        } catch (Exception e) {}

        // phone
        try {
            WebElement phone = driver.findElement(By.id("phone"));
            jse.executeScript("arguments[0].scrollIntoView({block:'center',behavior:'smooth'})", phone);
            phone.clear();
            phone.sendKeys("9999999999");
            Thread.sleep(200);
        } catch (Exception e) {}

        // isActive — select Yes/1
        try {
            WebElement isActive = driver.findElement(By.id("isActive"));
            jse.executeScript("arguments[0].scrollIntoView({block:'center',behavior:'smooth'})", isActive);
            String tagName = isActive.getTagName();
            if (tagName.equals("select")) {
                Select s = new Select(isActive);
                // Try selecting "Yes" or "1" or index 1
                try { s.selectByVisibleText("Yes"); }
                catch (Exception e) {
                    try { s.selectByValue("1"); }
                    catch (Exception e2) { s.selectByIndex(1); }
                }
                Thread.sleep(300);
                log("isActive", "Selected", "Yes/1", s.getFirstSelectedOption().getText(), true);
            } else {
                // checkbox or radio
                if (!isActive.isSelected()) {
                    jse.executeScript("arguments[0].click()", isActive);
                    Thread.sleep(200);
                }
                log("isActive", "Checked", "true", String.valueOf(isActive.isSelected()), isActive.isSelected());
            }
        } catch (Exception e) { logInfo("isActive", "Skipped", e.getMessage()); }

        // Any other required fields — extension, email etc.
        try {
            WebElement ext = driver.findElement(By.id("extension"));
            jse.executeScript("arguments[0].scrollIntoView({block:'center',behavior:'smooth'})", ext);
            ext.clear();
            ext.sendKeys("100");
            Thread.sleep(200);
        } catch (Exception e) {}

        // Save
        WebElement saveBtn = wait.until(ExpectedConditions.elementToBeClickable(By.id("addButton")));
        jse.executeScript("arguments[0].scrollIntoView({block:'center',behavior:'smooth'})", saveBtn);
        Thread.sleep(300);
        saveBtn.click();
        Thread.sleep(2000);

        // Scroll to search for toast
        try {
            WebElement search = driver.findElement(By.cssSelector("input[placeholder='Search keyword here']"));
            jse.executeScript("arguments[0].scrollIntoView({block:'center',behavior:'smooth'})", search);
            Thread.sleep(800);
        } catch (Exception e) {}

        String toast = getSuccessToast();
        log("Save", "Save PhoneBook", "Success", toast.isEmpty() ? "No toast" : toast, !toast.isEmpty());

        // Close modal
        try {
            WebElement closeBtn = driver.findElement(
                By.xpath("//div[contains(@id,'phoneBook') or contains(@id,'PhoneBook')]//button[contains(@class,'close') or @data-dismiss='modal']"));
            jse.executeScript("arguments[0].scrollIntoView({block:'center',behavior:'smooth'})", closeBtn);
            jse.executeScript("arguments[0].click()", closeBtn);
            Thread.sleep(1000);
            log("Close", "Close PhoneBook modal", "Closed", "Clicked", true);
        } catch (Exception e) {
            // Try generic modal close
            try {
                WebElement closeBtn = driver.findElement(
                    By.xpath("//button[@data-dismiss='modal' or contains(@class,'close')]"));
                jse.executeScript("arguments[0].click()", closeBtn);
                Thread.sleep(1000);
                log("Close", "Close modal (generic)", "Closed", "Clicked", true);
            } catch (Exception e2) {
                logInfo("Close", "Close button not found", e2.getMessage());
            }
        }

        sa.assertAll();
    }
}
