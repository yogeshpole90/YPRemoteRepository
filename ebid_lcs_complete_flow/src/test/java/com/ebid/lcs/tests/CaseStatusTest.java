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
public class CaseStatusTest extends BaseTest {

    private void scrollToSearch() {
        try {
            WebElement search = driver.findElement(
                By.xpath("//input[@type='search' and @aria-controls='dt-basicDetails']"));
            jse.executeScript("arguments[0].scrollIntoView({block:'center',behavior:'smooth'})", search);
            Thread.sleep(800);
        } catch (Exception e) {}
    }

    @BeforeClass
    public void setup() throws Exception {
        ExtentManager.initReport("CaseStatus");
        ExtentManager.startTest("Case Status - Save/View/Edit");

        WebElement tab = new WebDriverWait(driver, Duration.ofSeconds(15))
            .until(ExpectedConditions.elementToBeClickable(By.xpath("//a[contains(@href,'activeTab=Account Information')]")));
        jse.executeScript("arguments[0].scrollIntoView({block:'center'})", tab);
        tab.click();
        Thread.sleep(1500);

        WebElement cstTab = new WebDriverWait(driver, Duration.ofSeconds(15))
            .until(ExpectedConditions.elementToBeClickable(By.xpath("//a[contains(text(),'Case Status')]")));
        jse.executeScript("arguments[0].scrollIntoView({block:'center'})", cstTab);
        act.doubleClick(cstTab).build().perform();
        Thread.sleep(2000);

        driver.switchTo().frame("viewCaseStatusFrame");
    }

    @Test(priority = 1)
    public void validateSave() throws Exception {
        new Select(driver.findElement(By.id("caseStatusId"))).selectByIndex(2);
        Thread.sleep(300);
        driver.findElement(By.id("remarks")).clear();
        driver.findElement(By.id("remarks")).sendKeys("Case Status Test");

        WebElement saveBtn = driver.findElement(By.id("saveData"));
        jse.executeScript("arguments[0].scrollIntoView({block:'center'})", saveBtn);
        saveBtn.click();
        Thread.sleep(2000);

        scrollToSearch();
        // Scroll to top for toast visibility
        jse.executeScript("window.scrollTo(0,0)");
        Thread.sleep(500);
        String toast = getSuccessToast();
        log("Save", "Save Case Status", "Success", toast.isEmpty() ? "No toast" : toast, !toast.isEmpty());

        driver.switchTo().parentFrame();
        Thread.sleep(500);
        driver.switchTo().frame("viewCaseStatusFrame");
        Thread.sleep(1000);
        sa.assertAll();
    }

    @Test(priority = 2)
    public void validateView() throws Exception {
        List<WebElement> viewBtns = driver.findElements(By.xpath("//a[contains(@onclick,'ViewData')]"));
        log("View", "View buttons found", ">0", String.valueOf(viewBtns.size()), !viewBtns.isEmpty());
        if (!viewBtns.isEmpty()) {
            jse.executeScript("arguments[0].scrollIntoView({block:'center'})", viewBtns.get(0));
            viewBtns.get(0).click();
            Thread.sleep(1500);
            log("View", "View record opened", "Opened", "Clicked", true);
        }
    }

    @Test(priority = 3)
    public void validateEdit() throws Exception {
        List<WebElement> editBtns = driver.findElements(By.cssSelector("a.editBtn"));
        log("Edit", "Edit buttons found", ">0", String.valueOf(editBtns.size()), !editBtns.isEmpty());
        if (!editBtns.isEmpty()) {
            jse.executeScript("arguments[0].scrollIntoView({block:'center'})", editBtns.get(0));
            editBtns.get(0).click();
            Thread.sleep(1500);

            driver.findElement(By.id("remarks")).clear();
            driver.findElement(By.id("remarks")).sendKeys("Case Status Updated");

            WebElement saveBtn = driver.findElement(By.id("saveData"));
            jse.executeScript("arguments[0].scrollIntoView({block:'center'})", saveBtn);
            saveBtn.click();
            Thread.sleep(2000);

            scrollToSearch();
            jse.executeScript("window.scrollTo(0,0)");
            Thread.sleep(500);
            String toast = getSuccessToast();
            log("Edit", "Update Case Status", "Success", toast.isEmpty() ? "No toast" : toast, !toast.isEmpty());
        }
        sa.assertAll();
    }
}
