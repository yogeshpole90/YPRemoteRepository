package com.ebid.lcs.pages;

import java.time.Duration;

import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.Select;
import org.openqa.selenium.support.ui.WebDriverWait;

public class PhoneBookPage {

    private WebDriver driver;
    private WebDriverWait wait;
    private JavascriptExecutor jse;

    // ========== Navigation ==========
    @FindBy(xpath = "//a[contains(@data-target,'phoneBook') and contains(@title,'Phone Book')]")
    private WebElement phoneBookIcon;

    // ========== Action Buttons ==========
    @FindBy(id = "addButton")
    private WebElement saveBtn;

    // ========== Constructor ==========
    public PhoneBookPage(WebDriver driver) {
        this.driver = driver;
        this.wait = new WebDriverWait(driver, Duration.ofSeconds(15));
        this.jse = (JavascriptExecutor) driver;
        PageFactory.initElements(driver, this);
    }

    // ========== Navigation ==========
    public void openPhoneBook() throws InterruptedException {
        jse.executeScript("arguments[0].scrollIntoView({block:'center'})", phoneBookIcon);
        Thread.sleep(500);
        phoneBookIcon.click();
        Thread.sleep(1000);
    }

    // ========== Generic Actions ==========
    public WebElement getField(String fieldId) {
        return driver.findElement(By.id(fieldId));
    }

    public void enterText(WebElement field, String value) throws InterruptedException {
        field.clear();
        Thread.sleep(200);
        if (!value.isEmpty() && !value.equalsIgnoreCase("Empty")) {
            field.sendKeys(value);
        }
        Thread.sleep(300);
    }

    public String getValue(WebElement field) {
        return field.getAttribute("value");
    }

    public void selectDropdown(WebElement field, String visibleText) throws InterruptedException {
        Select s = new Select(field);
        s.selectByVisibleText(visibleText);
        Thread.sleep(500);
    }

    public String getSelectedText(WebElement field) {
        Select s = new Select(field);
        return s.getFirstSelectedOption().getText().trim();
    }

    // ========== Save ==========
    public void clickSave() throws InterruptedException {
        jse.executeScript("arguments[0].scrollIntoView({block:'center'})", saveBtn);
        Thread.sleep(500);
        saveBtn.click();
        Thread.sleep(2000);
    }

    public boolean isSaveBtnVisible() { return saveBtn.isDisplayed(); }
}
