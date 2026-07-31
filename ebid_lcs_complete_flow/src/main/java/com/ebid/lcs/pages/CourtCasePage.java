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
import org.openqa.selenium.support.ui.Select;
import org.openqa.selenium.support.ui.WebDriverWait;

public class CourtCasePage {

    private WebDriver driver;
    private WebDriverWait wait;
    private JavascriptExecutor jse;

    // ========== Navigation ==========
    @FindBy(xpath = "//*[contains(@href,'=Legal Process')]")
    private WebElement legalProcessTab;

    // ========== Frame ==========
    private static final String FRAME_NAME = "courtCaseMstListPageFrame";

    // ========== Form Fields ==========
    @FindBy(id = "suitAmount_txt")
    private WebElement suitAmount;

    // ========== Action Buttons ==========
    @FindBy(xpath = "//button[@id='save']")
    private WebElement saveBtn;

    @FindBy(id = "popUpYes")
    private WebElement popUpYes;

    // ========== Table Locators ==========
    @FindBy(xpath = "//input[@type='search' and @aria-controls='dt-basicDetails']")
    private WebElement searchBox;

    // ========== Constructor ==========
    public CourtCasePage(WebDriver driver) {
        this.driver = driver;
        this.wait = new WebDriverWait(driver, Duration.ofSeconds(15));
        this.jse = (JavascriptExecutor) driver;
        PageFactory.initElements(driver, this);
    }

    // ========== Navigation ==========
    public void navigateToLegalProcess() throws InterruptedException {
        jse.executeScript("arguments[0].scrollIntoView({block:'center',behavior:'smooth'})", legalProcessTab);
        Thread.sleep(1000);
        jse.executeScript("arguments[0].click()", legalProcessTab);
        Thread.sleep(2000);
    }

    public void switchToCourtCaseFrame() throws InterruptedException {
        driver.switchTo().frame(FRAME_NAME);
        Thread.sleep(500);
    }

    public void switchToParentAndBackToFrame() throws InterruptedException {
        driver.switchTo().parentFrame();
        jse.executeScript("arguments[0].scrollIntoView({block:'center'})", legalProcessTab);
        driver.switchTo().frame(FRAME_NAME);
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
            Thread.sleep(200);
        }
    }

    public void enterDateField(WebElement field, String value) throws InterruptedException {
        field.clear();
        if (!value.isEmpty() && !value.equalsIgnoreCase("Empty")) {
            field.sendKeys(value);
        }
        field.sendKeys(Keys.TAB);
        hideDatepicker();
        Thread.sleep(300);
        dismissAlert();
    }

    public void enterAmountField(WebElement field, String value) throws InterruptedException {
        field.clear();
        Thread.sleep(200);
        if (!value.isEmpty() && !value.equalsIgnoreCase("Empty")) {
            field.sendKeys(value);
        }
        field.sendKeys(Keys.TAB);
        Thread.sleep(300);
        dismissAlert();
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
        Thread.sleep(1000);
        dismissAlert();
    }

    public boolean isSaveBtnVisible() { return saveBtn.isDisplayed(); }
    public boolean isSaveBtnEnabled() { return saveBtn.isEnabled(); }

    // ========== View / Edit / Delete ==========
    public void scrollToSearch() throws InterruptedException {
        try {
            jse.executeScript("arguments[0].scrollIntoView({block:'center',behavior:'smooth'})", searchBox);
            Thread.sleep(1000);
        } catch (Exception e) {}
    }

    public List<WebElement> getViewButtons() {
        return driver.findElements(By.cssSelector("a.ViewBtn"));
    }

    public void clickLastView() throws InterruptedException {
        driver.findElement(By.xpath("(//a[contains(@class,'ViewBtn')])[last()]")).click();
        Thread.sleep(2000);
    }

    public List<WebElement> getEditButtons() {
        return driver.findElements(By.cssSelector("a.editBtn"));
    }

    public void clickLastEdit() throws InterruptedException {
        driver.findElement(By.xpath("(//a[contains(@class,'editBtn')])[last()]")).click();
        Thread.sleep(2000);
    }

    public List<WebElement> getDeleteButtons() {
        return driver.findElements(By.cssSelector("a.deleteBtn"));
    }

    public void clickLastDelete() throws InterruptedException {
        driver.findElement(By.xpath("(//a[contains(@class,'deleteBtn')])[last()]")).click();
        Thread.sleep(1000);
    }

    public void confirmDelete() throws InterruptedException {
        popUpYes.click();
        Thread.sleep(1000);
    }

    // ========== Helpers ==========
    public void dismissAlert() {
        try { driver.switchTo().alert().accept(); } catch (Exception e) {}
    }

    public void hideDatepicker() {
        try {
            jse.executeScript(
                "var dp = document.querySelectorAll('.datepicker, .ui-datepicker, .daterangepicker, .bootstrap-datetimepicker-widget');" +
                "dp.forEach(function(el){ el.style.display='none'; });");
        } catch (Exception e) {}
        try { driver.findElement(By.tagName("body")).sendKeys(Keys.ESCAPE); } catch (Exception e) {}
    }

    public WebElement getSuitAmount() { return suitAmount; }
}
