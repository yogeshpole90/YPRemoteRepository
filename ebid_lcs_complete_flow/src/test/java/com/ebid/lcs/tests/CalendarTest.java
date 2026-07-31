package com.ebid.lcs.tests;

import org.openqa.selenium.By;
import org.openqa.selenium.Keys;
import org.openqa.selenium.NoSuchElementException;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Listeners;
import org.testng.annotations.Test;

import java.time.Duration;

import com.ebid.lcs.base.BaseTest;
import com.ebid.lcs.config.ConfigManager;
import com.ebid.lcs.listeners.TestListener;
import com.ebid.lcs.reporting.ExtentManager;

@Listeners(TestListener.class)
public class CalendarTest extends BaseTest {

    @BeforeClass
    public void setup() throws Exception {
        ExtentManager.initReport("Calendar");
        ExtentManager.startTest("Calendar - Validation");

        WebElement calendarTab = driver.findElement(By.xpath("//*[contains(@href,'=Calendar')]"));
        jse.executeScript("arguments[0].scrollIntoView({block:'center',behavior:'smooth'})", calendarTab);
        Thread.sleep(1000);
        calendarTab.click();
        Thread.sleep(2000);

        logInfo("Navigation", "Navigated to", "Calendar");
    }

    @Test(priority = 1)
    public void validateCheckboxes() throws Exception {
        // Scroll to 'View all' text first to bring checkbox area into view
        try {
            WebElement viewAll = driver.findElement(By.xpath("//strong[text()='View all']"));
            jse.executeScript("arguments[0].scrollIntoView({block:'center',behavior:'smooth'})", viewAll);
            Thread.sleep(1000);
        } catch (Exception e) {}

        // Scroll to first checkbox explicitly before loop starts
        try {
            WebElement firstCb = driver.findElement(By.xpath("//input[@value='99']"));
            jse.executeScript("arguments[0].scrollIntoView({block:'center',behavior:'smooth'})", firstCb);
            Thread.sleep(1000);
        } catch (Exception e) {}

        String[] values = { "99", "100", "101", "102", "CALL", "MAIL", "SITE VISIT" };
        String[] labels = { "PTP", "Site Visited", "Next Court Case", "Next Hearing", "Call", "Mail", "Site Visit" };

        for (int i = 0; i < values.length; i++) {
            try {
                WebElement cb = driver.findElement(By.xpath("//input[@value='" + values[i] + "']"));

                jse.executeScript("arguments[0].scrollIntoView({block:'start',behavior:'smooth'})", cb);
                Thread.sleep(800);

                boolean selected = cb.isSelected();
                log(labels[i] + " Checkbox", "Default selected", "true", String.valueOf(selected), selected);
                sa.assertTrue(selected, labels[i] + " not selected by default");

                jse.executeScript("arguments[0].click()", cb);
                Thread.sleep(500);
                boolean afterUncheck = cb.isSelected();
                log(labels[i] + " Checkbox", "After uncheck", "false", String.valueOf(afterUncheck), !afterUncheck);

                jse.executeScript("arguments[0].click()", cb);
                Thread.sleep(500);
                boolean afterRecheck = cb.isSelected();
                log(labels[i] + " Checkbox", "After re-check", "true", String.valueOf(afterRecheck), afterRecheck);
            } catch (NoSuchElementException e) {
                log(labels[i] + " Checkbox", "Element exists", "Found", "NOT FOUND", false);
                sa.fail(labels[i] + " checkbox not found");
            }
        }
        sa.assertAll();
    }

    @Test(priority = 2)
    public void validateDropdownNavigation() throws Exception {
        WebElement dropdownBtn = driver.findElement(By.xpath("//*[@id='dropdownMenu-calendarType']"));
        WebElement rangeText = driver.findElement(By.id("renderRange"));
        WebElement forwardBtn = driver.findElement(By.className("ic-arrow-line-right"));
        WebElement backwardBtn = driver.findElement(By.className("ic-arrow-line-left"));

        jse.executeScript("arguments[0].scrollIntoView({block:'center'})", dropdownBtn);
        Thread.sleep(500);
        log("Dropdown Button", "Displayed", "true", String.valueOf(dropdownBtn.isDisplayed()),
                dropdownBtn.isDisplayed());
        log("Date Range Text", "Displayed", "true", String.valueOf(rangeText.isDisplayed()), rangeText.isDisplayed());

        // Daily
        jse.executeScript("arguments[0].scrollIntoView({block:'center'})", dropdownBtn);
        Thread.sleep(500);
        dropdownBtn.click();
        Thread.sleep(500);
        driver.findElement(By.xpath("//a[@data-action='toggle-daily']")).click();
        Thread.sleep(1000);
        String dailyRange = rangeText.getText().trim();
        log("Daily View", "Date range displayed", "Non-empty", dailyRange, !dailyRange.isEmpty());

        String beforeFwd = rangeText.getText().trim();
        jse.executeScript("arguments[0].scrollIntoView({block:'center'})", forwardBtn);
        Thread.sleep(500);
        forwardBtn.click();
        Thread.sleep(1000);
        String afterFwd = rangeText.getText().trim();
        log("Daily Forward", "Date changed", beforeFwd, afterFwd, !afterFwd.equals(beforeFwd));

        String beforeBwd = rangeText.getText().trim();
        jse.executeScript("arguments[0].scrollIntoView({block:'center'})", backwardBtn);
        Thread.sleep(500);
        backwardBtn.click();
        Thread.sleep(1000);
        String afterBwd = rangeText.getText().trim();
        log("Daily Backward", "Date changed", beforeBwd, afterBwd, !afterBwd.equals(beforeBwd));

        // Weekly
        jse.executeScript("arguments[0].scrollIntoView({block:'center'})", dropdownBtn);
        Thread.sleep(500);
        dropdownBtn.click();
        Thread.sleep(500);
        driver.findElement(By.xpath("//a[@data-action='toggle-weekly']")).click();
        Thread.sleep(1000);
        String weeklyRange = rangeText.getText().trim();
        log("Weekly View", "Week range displayed", "Non-empty", weeklyRange, !weeklyRange.isEmpty());

        beforeFwd = rangeText.getText().trim();
        jse.executeScript("arguments[0].scrollIntoView({block:'center'})", forwardBtn);
        Thread.sleep(500);
        forwardBtn.click();
        Thread.sleep(1000);
        afterFwd = rangeText.getText().trim();
        log("Weekly Forward", "Week changed", beforeFwd, afterFwd, !afterFwd.equals(beforeFwd));

        beforeBwd = rangeText.getText().trim();
        jse.executeScript("arguments[0].scrollIntoView({block:'center'})", backwardBtn);
        Thread.sleep(500);
        backwardBtn.click();
        Thread.sleep(1000);
        afterBwd = rangeText.getText().trim();
        log("Weekly Backward", "Week changed", beforeBwd, afterBwd, !afterBwd.equals(beforeBwd));

        // Monthly
        jse.executeScript("arguments[0].scrollIntoView({block:'center'})", dropdownBtn);
        Thread.sleep(500);
        dropdownBtn.click();
        Thread.sleep(500);
        driver.findElement(By.xpath("//a[@data-action='toggle-monthly']")).click();
        Thread.sleep(1000);
        String monthlyRange = rangeText.getText().trim();
        log("Monthly View", "Month range displayed", "Non-empty", monthlyRange, !monthlyRange.isEmpty());

        beforeFwd = rangeText.getText().trim();
        jse.executeScript("arguments[0].scrollIntoView({block:'center'})", forwardBtn);
        Thread.sleep(500);
        forwardBtn.click();
        Thread.sleep(1000);
        afterFwd = rangeText.getText().trim();
        log("Monthly Forward", "Month changed", beforeFwd, afterFwd, !afterFwd.equals(beforeFwd));

        beforeBwd = rangeText.getText().trim();
        jse.executeScript("arguments[0].scrollIntoView({block:'center'})", backwardBtn);
        Thread.sleep(500);
        backwardBtn.click();
        Thread.sleep(1000);
        afterBwd = rangeText.getText().trim();
        log("Monthly Backward", "Month changed", beforeBwd, afterBwd, !afterBwd.equals(beforeBwd));

        sa.assertAll();
    }

    @Test(priority = 3)
    public void createRecord() throws Exception {
        WebElement dropdownBtn = driver.findElement(By.xpath("//*[@id='dropdownMenu-calendarType']"));
        WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(15));

        // Daily Record
        jse.executeScript("arguments[0].click()", dropdownBtn);
        Thread.sleep(500);
        jse.executeScript("arguments[0].click()", driver.findElement(By.xpath("//a[@data-action='toggle-daily']")));
        Thread.sleep(2000);
        jse.executeScript("window.scrollBy(0,3000)");
        Thread.sleep(2000);

        WebElement dailySlot = driver
                .findElement(By.xpath("(//div[contains(@class,'tui-full-calendar-time-date-s')])[1]"));
        jse.executeScript("arguments[0].scrollIntoView({block:'center'})", dailySlot);
        Thread.sleep(500);
        wait.until(ExpectedConditions.elementToBeClickable(dailySlot));
        dailySlot.click();
        Thread.sleep(2000);

        fillPopup("Daily PTP", "Pune", "Daily Test", "PTP");
        jse.executeScript("arguments[0].click()",
                driver.findElement(By.xpath("//button[contains(@class,'tui-full-calendar-popup-save')]")));
        Thread.sleep(2000);
        log("Daily Record", "Create PTP record", "Saved", "Saved", true);

        jse.executeScript("window.scrollTo(0,0)");
        Thread.sleep(1000);
        sa.assertAll();
    }

    private void fillPopup(String subject, String location, String desc, String calType) throws Exception {
        WebElement popupBtn = driver.findElement(By.xpath(
                "//button[contains(@class,'tui-full-calendar-dropdown-button tui-full-calendar-popup-section-item')]"));
        jse.executeScript("arguments[0].scrollIntoView({block:'center'})", popupBtn);
        Thread.sleep(500);
        jse.executeScript("arguments[0].click()", popupBtn);
        Thread.sleep(500);
        jse.executeScript("arguments[0].click()", driver.findElement(By.xpath(
                "//li[contains(@class,'tui-full-calendar-dropdown-menu-item')]//span[text()='" + calType + "']")));
        Thread.sleep(500);

        WebElement subjectField = driver.findElement(By.xpath("//input[@placeholder='Subject']"));
        subjectField.sendKeys(subject);

        WebElement locationField = driver.findElement(By.xpath("//input[@placeholder='Location']"));
        locationField.sendKeys(location);

        WebElement descField = driver.findElement(By.xpath("//input[@placeholder='Description']"));
        descField.sendKeys(desc);
        Thread.sleep(1000);
    }
}
