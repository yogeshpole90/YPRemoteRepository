package com.ebid.lcs.pages;

import java.time.Duration;

import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.Keys;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.Select;
import org.openqa.selenium.support.ui.WebDriverWait;

public class FollowUpPage {

    private WebDriver driver;
    private WebDriverWait wait;
    private JavascriptExecutor jse;

    // ========== Navigation ==========
    @FindBy(xpath = "//*[contains(@href,'Follow-Up')]")
    private WebElement followUpTab;

    @FindBy(xpath = "//*[contains(text(),'Add Follow-Up')]")
    private WebElement addFollowUpBtn;

    // ========== Frame ==========
    private static final String FRAME_NAME = "addcommunicationHistoryFrame";

    // ========== Select2 - Loan Account ==========
    @FindBy(css = "#loanAcNoSelect ~ .select2-container")
    private WebElement loanAcSelect2Container;

    // ========== Action Buttons ==========
    @FindBy(id = "saveData")
    private WebElement saveBtn;

    // ========== Constructor ==========
    public FollowUpPage(WebDriver driver) {
        this.driver = driver;
        this.wait = new WebDriverWait(driver, Duration.ofSeconds(15));
        this.jse = (JavascriptExecutor) driver;
        PageFactory.initElements(driver, this);
    }

    // ========== Navigation ==========
    public void navigateToFollowUp() throws InterruptedException {
        jse.executeScript("arguments[0].scrollIntoView({block:'center',behavior:'smooth'})", followUpTab);
        Thread.sleep(1000);
        followUpTab.click();
        Thread.sleep(2000);

        WebElement addFU = wait.until(ExpectedConditions.elementToBeClickable(addFollowUpBtn));
        jse.executeScript("arguments[0].scrollIntoView({block:'center',behavior:'smooth'})", addFU);
        Thread.sleep(1000);
        addFU.click();
        Thread.sleep(2000);
    }

    public void switchToFollowUpFrame() throws InterruptedException {
        driver.switchTo().frame(FRAME_NAME);
        Thread.sleep(500);
    }

    public void selectLoanAccount() throws InterruptedException {
        WebDriverWait wait2 = new WebDriverWait(driver, Duration.ofSeconds(10));
        WebElement container = wait2.until(ExpectedConditions.elementToBeClickable(loanAcSelect2Container));
        container.click();
        Thread.sleep(500);
        WebElement firstOption = wait2.until(ExpectedConditions.elementToBeClickable(
            By.cssSelector(".select2-results__option:first-child")));
        firstOption.click();
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

    public void setDateByJS(WebElement field, String value) throws InterruptedException {
        if (value.equalsIgnoreCase("Empty")) {
            jse.executeScript("arguments[0].value=''", field);
        } else {
            jse.executeScript("arguments[0].value='" + value + "'", field);
        }
        hideDatepicker();
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

    public void selectDropdownByIndex(WebElement field, int index) throws InterruptedException {
        Select s = new Select(field);
        s.selectByIndex(index);
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
        Thread.sleep(3000);
    }

    public boolean isSaveBtnVisible() { return saveBtn.isDisplayed(); }
    public boolean isSaveBtnEnabled() { return saveBtn.isEnabled(); }

    // ========== Helpers ==========
    public void switchToParentFrame() throws InterruptedException {
        driver.switchTo().parentFrame();
        Thread.sleep(500);
    }

    public void switchToDefaultContent() throws InterruptedException {
        driver.switchTo().defaultContent();
        jse.executeScript("window.scrollTo(0,0)");
        Thread.sleep(500);
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
