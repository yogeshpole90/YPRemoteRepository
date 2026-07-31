package com.agat.los.pages;

import java.time.Duration;

import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.Keys;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.Select;
import org.openqa.selenium.support.ui.WebDriverWait;

/**
 * Base page class with common reusable actions for all pages.
 */
public class BasePage {

    protected WebDriver driver;
    protected JavascriptExecutor jse;
    protected WebDriverWait wait;

    public BasePage(WebDriver driver) {
        this.driver = driver;
        this.jse = (JavascriptExecutor) driver;
        this.wait = new WebDriverWait(driver, Duration.ofSeconds(15));
    }

    protected void scrollTo(WebElement element) throws InterruptedException {
        jse.executeScript("arguments[0].scrollIntoView({block:'center'})", element);
        Thread.sleep(500);
    }

    protected void jsClick(WebElement element) throws InterruptedException {
        jse.executeScript("arguments[0].click()", element);
        Thread.sleep(500);
    }

    protected void enterText(WebElement field, String value) throws InterruptedException {
        field.clear();
        field.sendKeys(value);
        Thread.sleep(300);
    }

    protected void selectByValue(WebElement field, String value) throws InterruptedException {
        new Select(field).selectByValue(value);
        Thread.sleep(500);
    }

    protected void selectByVisibleText(WebElement field, String text) throws InterruptedException {
        new Select(field).selectByVisibleText(text);
        Thread.sleep(500);
    }

    protected String getSelectedText(WebElement field) {
        return new Select(field).getFirstSelectedOption().getText().trim();
    }

    protected void acceptAlertIfPresent() throws InterruptedException {
        try {
            driver.switchTo().alert().accept();
            Thread.sleep(1000);
        } catch (Exception e) {}
    }

    protected void handleConfirmAndAlert() throws InterruptedException {
        try {
            WebElement confirmYes = driver.findElement(By.id("submitForm"));
            jse.executeScript("arguments[0].click()", confirmYes);
            Thread.sleep(1000);
        } catch (Exception e) {}
        acceptAlertIfPresent();
    }
}
