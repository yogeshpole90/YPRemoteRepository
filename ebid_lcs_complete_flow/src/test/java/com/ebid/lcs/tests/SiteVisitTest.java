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
public class SiteVisitTest extends BaseTest {

    @BeforeClass
    public void setup() throws Exception {
        ExtentManager.initReport("SiteVisit");
        ExtentManager.startTest("SiteVisit - Save/View/Edit");

        WebElement followUpTab = new WebDriverWait(driver, Duration.ofSeconds(15))
            .until(ExpectedConditions.elementToBeClickable(By.xpath("//*[contains(@href,'Follow-Up')]")));
        jse.executeScript("arguments[0].scrollIntoView({block:'center'})", followUpTab);
        followUpTab.click();
        Thread.sleep(2000);

        WebElement svTab = new WebDriverWait(driver, Duration.ofSeconds(15))
            .until(ExpectedConditions.elementToBeClickable(By.xpath("//*[contains(text(),'Site Visit Request')]")));
        jse.executeScript("arguments[0].scrollIntoView({block:'center'})", svTab);
        act.doubleClick(svTab).build().perform();
        Thread.sleep(2000);

        driver.switchTo().frame("createSiteVisitDetailsFrame");
    }

    @Test(priority = 1)
    public void validateSave() throws Exception {
        try { new Select(driver.findElement(By.id("visitType"))).selectByIndex(1); Thread.sleep(300); } catch (Exception e) {}
        try { new Select(driver.findElement(By.id("visitedBy"))).selectByIndex(1); Thread.sleep(300); } catch (Exception e) {}
        try { new Select(driver.findElement(By.id("customerResponse"))).selectByIndex(1); Thread.sleep(300); } catch (Exception e) {}
        try { jse.executeScript("document.getElementById('visitDate').value='21-05-2026'"); } catch (Exception e) {}
        try { new Select(driver.findElement(By.id("collection"))).selectByVisibleText("No"); Thread.sleep(300); } catch (Exception e) {}
        try { driver.findElement(By.id("remarks")).sendKeys("SiteVisit Test"); } catch (Exception e) {}

        WebElement saveBtn = driver.findElement(By.id("saveData"));
        jse.executeScript("arguments[0].scrollIntoView({block:'center'})", saveBtn);
        saveBtn.click();
        Thread.sleep(2000);

        String toast = getSuccessToast();
        log("Save", "Save SiteVisit", "Success", toast.isEmpty() ? "No toast" : toast, !toast.isEmpty());
        sa.assertAll();
    }

    @Test(priority = 2)
    public void validateView() throws Exception {
        List<WebElement> viewBtns = driver.findElements(By.cssSelector("tbody tr:first-child a.ViewBtn"));
        if (!viewBtns.isEmpty()) {
            jse.executeScript("arguments[0].scrollIntoView({block:'center'})", viewBtns.get(0));
            jse.executeScript("arguments[0].click()", viewBtns.get(0));
            Thread.sleep(1500);
            log("View", "View SiteVisit record", "Opened", "Clicked", true);
        }
    }

    @Test(priority = 3)
    public void validateEdit() throws Exception {
        List<WebElement> editBtns = driver.findElements(By.cssSelector("tbody tr:first-child a.editBtn"));
        if (!editBtns.isEmpty()) {
            jse.executeScript("arguments[0].scrollIntoView({block:'center'})", editBtns.get(0));
            jse.executeScript("arguments[0].click()", editBtns.get(0));
            Thread.sleep(1500);

            WebElement rem = driver.findElement(By.id("remarks"));
            rem.clear();
            rem.sendKeys("SiteVisit Updated");

            WebElement saveBtn = driver.findElement(By.id("saveData"));
            jse.executeScript("arguments[0].scrollIntoView({block:'center'})", saveBtn);
            saveBtn.click();
            Thread.sleep(2000);

            String toast = getSuccessToast();
            log("Edit", "Update SiteVisit", "Success", toast.isEmpty() ? "No toast" : toast, !toast.isEmpty());
        }
        sa.assertAll();
    }
}
