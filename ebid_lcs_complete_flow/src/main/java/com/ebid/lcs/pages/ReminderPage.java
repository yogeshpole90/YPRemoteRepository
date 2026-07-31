package com.ebid.lcs.pages;

import java.time.Duration;
import java.util.List;

import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.Keys;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.Select;
import org.openqa.selenium.support.ui.WebDriverWait;

public class ReminderPage {

    private WebDriver driver;
    private WebDriverWait wait;
    private JavascriptExecutor jse;
    private Actions act;

    // ========== Navigation / Tab Locators ==========
    @FindBy(xpath = "//a[contains(@href,'activeTab=Communication History')]")
    private WebElement communicationHistoryTab;

    @FindBy(xpath = "//a[contains(text(),'Reminder')]")
    private WebElement reminderTab;

    // ========== Frame ==========
    @FindBy(id = "fetchReminderDtlsPageFrame")
    private WebElement reminderFrame;

    // ========== Form Field Locators ==========
    @FindBy(id = "reminderType")
    private WebElement reminderType;

    @FindBy(id = "reminderDate")
    private WebElement reminderDate;

    @FindBy(id = "reminderCreateDate")
    private WebElement reminderCreateDate;

    @FindBy(id = "remarks")
    private WebElement remarks;

    // ========== Action Buttons ==========
    @FindBy(id = "save")
    private WebElement saveBtn;

    @FindBy(id = "submitForm")
    private WebElement confirmYesBtn;

    // ========== Table / List Locators ==========
    @FindBy(css = "#dt-basicDetails tbody tr:first-child a[onclick*='ViewData']")
    private WebElement viewFirstRowBtn;

    @FindBy(css = "#dt-basicDetails tbody tr:first-child a.editBtn")
    private WebElement editFirstRowBtn;

    @FindBy(css = "input[placeholder='Search keyword here']")
    private WebElement searchBox;

    // ========== Constructor ==========
    public ReminderPage(WebDriver driver) {
        this.driver = driver;
        this.wait = new WebDriverWait(driver, Duration.ofSeconds(15));
        this.jse = (JavascriptExecutor) driver;
        this.act = new Actions(driver);
        PageFactory.initElements(driver, this);
    }

    // ========== Navigation ==========
    public void navigateToReminderTab() throws InterruptedException {
        jse.executeScript("arguments[0].scrollIntoView({block:'center',behavior:'smooth'})", communicationHistoryTab);
        Thread.sleep(500);
        communicationHistoryTab.click();
        Thread.sleep(1000);

        jse.executeScript("arguments[0].scrollIntoView({block:'center',behavior:'smooth'})", reminderTab);
        Thread.sleep(500);
        act.doubleClick(reminderTab).build().perform();
        Thread.sleep(2000);
    }

    public void switchToReminderFrame() throws InterruptedException {
        jse.executeScript("arguments[0].scrollIntoView({block:'center'})", reminderFrame);
        Thread.sleep(500);
        driver.switchTo().frame("fetchReminderDtlsPageFrame");
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
            Thread.sleep(300);
        }
    }

    public String getValue(WebElement field) {
        return field.getAttribute("value");
    }

    public void selectDropdown(WebElement field, String visibleText) throws InterruptedException {
        Select s = new Select(field);
        s.selectByVisibleText(visibleText);
        Thread.sleep(500);
    }

    public void selectDropdownByIndex(WebElement field, int index) throws InterruptedException {
        Select s = new Select(field);
        s.selectByIndex(index);
        Thread.sleep(300);
    }

    public String getSelectedText(WebElement field) {
        Select s = new Select(field);
        return s.getFirstSelectedOption().getText().trim();
    }

    public List<WebElement> getDropdownOptions(WebElement field) {
        Select s = new Select(field);
        return s.getOptions();
    }

    public boolean isMultipleSelect(WebElement field) {
        Select s = new Select(field);
        return s.isMultiple();
    }

    public boolean isDisplayed(WebElement field) {
        return field.isDisplayed();
    }

    public boolean isEnabled(WebElement field) {
        return field.isEnabled();
    }

    // ========== Form Field Getters ==========
    public WebElement getReminderType() { return reminderType; }
    public WebElement getReminderDate() { return reminderDate; }
    public WebElement getReminderCreateDate() { return reminderCreateDate; }
    public WebElement getRemarks() { return remarks; }

    // ========== Save ==========
    public void clickSave() throws InterruptedException {
        jse.executeScript("arguments[0].scrollIntoView({block:'center'})", saveBtn);
        Thread.sleep(300);
        saveBtn.click();
        Thread.sleep(1000);
    }

    public void confirmSave() throws InterruptedException {
        try {
            if (confirmYesBtn.isDisplayed()) {
                confirmYesBtn.click();
                Thread.sleep(2000);
            }
        } catch (Exception e) { }
    }

    public boolean isSaveBtnVisible() { return saveBtn.isDisplayed(); }
    public boolean isSaveBtnEnabled() { return saveBtn.isEnabled(); }

    // ========== View / Edit Actions ==========
    public void scrollToSearch() throws InterruptedException {
        try {
            jse.executeScript("arguments[0].scrollIntoView({block:'center',behavior:'smooth'})", searchBox);
            Thread.sleep(500);
        } catch (Exception e) { }
    }

    public void clickViewFirstRow() throws InterruptedException {
        jse.executeScript("arguments[0].scrollIntoView({block:'center'})", viewFirstRowBtn);
        Thread.sleep(500);
        jse.executeScript("arguments[0].click()", viewFirstRowBtn);
        Thread.sleep(2000);
    }

    public void clickEditFirstRow() throws InterruptedException {
        jse.executeScript("arguments[0].scrollIntoView({block:'center'})", editFirstRowBtn);
        Thread.sleep(500);
        jse.executeScript("arguments[0].click()", editFirstRowBtn);
        Thread.sleep(2000);
    }

    // ========== Fill Form for Save/Edit ==========
    public void fillReminderForm(int typeIndex, String date, String createDate, String remarksText) throws InterruptedException {
        selectDropdownByIndex(reminderType, typeIndex);
        enterText(reminderDate, date);
        enterText(reminderCreateDate, createDate);
        enterText(remarks, remarksText);
        Thread.sleep(500);
    }
}
