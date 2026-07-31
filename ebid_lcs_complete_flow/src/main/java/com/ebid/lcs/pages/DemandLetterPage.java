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

public class DemandLetterPage {

    private WebDriver driver;
    private WebDriverWait wait;
    private JavascriptExecutor jse;
    private Actions act;

    // ========== Navigation ==========
    @FindBy(xpath = "//*[contains(@href,'activeTab=Document')]")
    private WebElement documentTab;

    @FindBy(xpath = "//a[contains(text(),'Demand Letter')]")
    private WebElement demandLetterTab;

    // ========== Frame ==========
    @FindBy(id = "addNewDemandLetterFrame")
    private WebElement demandLetterFrame;

    // ========== Action Buttons ==========
    @FindBy(id = "saveData")
    private WebElement saveBtn;

    // ========== Table Locators ==========
    @FindBy(xpath = "//input[@type='search' and @aria-controls='dt-basicDetails']")
    private WebElement searchBox;

    // ========== Constructor ==========
    public DemandLetterPage(WebDriver driver) {
        this.driver = driver;
        this.wait = new WebDriverWait(driver, Duration.ofSeconds(15));
        this.jse = (JavascriptExecutor) driver;
        this.act = new Actions(driver);
        PageFactory.initElements(driver, this);
    }

    // ========== Navigation ==========
    public void navigateToDemandLetter() throws InterruptedException {
        WebElement docTab = wait.until(ExpectedConditions.elementToBeClickable(documentTab));
        jse.executeScript("arguments[0].scrollIntoView({block:'center',behavior:'smooth'})", docTab);
        Thread.sleep(1000);
        docTab.click();
        Thread.sleep(2000);

        WebElement dlTab = wait.until(ExpectedConditions.elementToBeClickable(demandLetterTab));
        jse.executeScript("arguments[0].scrollIntoView({block:'center',behavior:'smooth'})", dlTab);
        Thread.sleep(1000);
        act.doubleClick(dlTab).build().perform();
        Thread.sleep(2000);
    }

    public void switchToDemandLetterFrame() throws InterruptedException {
        WebElement frame = wait.until(ExpectedConditions.presenceOfElementLocated(By.id("addNewDemandLetterFrame")));
        driver.switchTo().frame(frame);
        Thread.sleep(500);
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

    public void setDateField(WebElement field, String value) throws InterruptedException {
        jse.executeScript("arguments[0].value=''", field);
        Thread.sleep(200);
        if (!value.isEmpty() && !value.equalsIgnoreCase("Empty")) {
            field.click();
            Thread.sleep(200);
            field.sendKeys(value);
            Thread.sleep(300);
            field.sendKeys(Keys.TAB);
            Thread.sleep(300);
        }
        hideDatepicker();
        Thread.sleep(300);
    }

    public String getValue(WebElement field) {
        return field.getAttribute("value");
    }

    public void selectDropdown(WebElement field, String visibleText) throws InterruptedException {
        Select s = new Select(field);
        List<WebElement> options = s.getOptions();
        for (WebElement opt : options) {
            if (opt.getText().trim().equalsIgnoreCase(visibleText.trim())) {
                opt.click();
                break;
            }
        }
        Thread.sleep(500);
    }

    public String getSelectedText(WebElement field) {
        Select s = new Select(field);
        return s.getFirstSelectedOption().getText().trim();
    }

    // ========== Save ==========
    public void clickSave() throws InterruptedException {
        jse.executeScript("arguments[0].scrollIntoView({block:'center',behavior:'smooth'})", saveBtn);
        Thread.sleep(500);
        saveBtn.click();
        Thread.sleep(1000);
    }

    public boolean isSaveBtnVisible() { return saveBtn.isDisplayed(); }
    public boolean isSaveBtnEnabled() { return saveBtn.isEnabled(); }

    // ========== Helpers ==========
    public void scrollToSearch() throws InterruptedException {
        try {
            jse.executeScript("arguments[0].scrollIntoView({block:'center',behavior:'smooth'})", searchBox);
            Thread.sleep(1000);
        } catch (Exception e) {}
    }

    public void hideDatepicker() {
        try {
            jse.executeScript(
                "var dp = document.querySelectorAll('.datepicker, .ui-datepicker, .daterangepicker, .bootstrap-datetimepicker-widget');" +
                "dp.forEach(function(el){ el.style.display='none'; });");
        } catch (Exception e) {}
        try { driver.findElement(By.tagName("body")).sendKeys(Keys.ESCAPE); } catch (Exception e) {}
    }
}
