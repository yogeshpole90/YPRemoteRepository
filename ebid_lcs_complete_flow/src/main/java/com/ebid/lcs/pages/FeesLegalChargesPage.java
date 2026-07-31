package com.ebid.lcs.pages;

import java.time.Duration;

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

public class FeesLegalChargesPage {

    private WebDriver driver;
    private WebDriverWait wait;
    private JavascriptExecutor jse;
    private Actions act;

    // ========== Navigation ==========
    @FindBy(xpath = "//*[contains(@href,'=Legal Process')]")
    private WebElement legalProcessTab;

    @FindBy(xpath = "//a[contains(text(),'Fees') and contains(text(),'Charge')]")
    private WebElement feesChargesTab;

    // ========== Frame ==========
    @FindBy(id = "viewFessAndChargeFrame")
    private WebElement feesFrame;

    private static final String FRAME_NAME = "viewFessAndChargeFrame";

    // ========== Action Buttons ==========
    @FindBy(id = "saveFessCharge")
    private WebElement saveBtn;

    @FindBy(id = "popUpYes")
    private WebElement popUpYes;

    // ========== Table Locators ==========
    @FindBy(xpath = "//input[@placeholder='Search keyword here']")
    private WebElement searchBox;

    @FindBy(xpath = "//a[contains(@class,'ViewBtn') and contains(@onclick,'ViewData')]")
    private WebElement viewBtn;

    @FindBy(xpath = "//a[contains(@class,'editBtn') and contains(@onclick,'EditData')]")
    private WebElement editBtn;

    @FindBy(xpath = "//a[contains(@class,'deleteBtn') and contains(@onclick,'DeleteData')]")
    private WebElement deleteBtn;

    // ========== Constructor ==========
    public FeesLegalChargesPage(WebDriver driver) {
        this.driver = driver;
        this.wait = new WebDriverWait(driver, Duration.ofSeconds(15));
        this.jse = (JavascriptExecutor) driver;
        this.act = new Actions(driver);
        PageFactory.initElements(driver, this);
    }

    // ========== Navigation ==========
    public void navigateToFeesCharges() throws InterruptedException {
        jse.executeScript("arguments[0].scrollIntoView({block:'center',behavior:'smooth'})", legalProcessTab);
        Thread.sleep(1000);
        legalProcessTab.click();
        Thread.sleep(2000);

        jse.executeScript("arguments[0].scrollIntoView({block:'center'})", feesChargesTab);
        Thread.sleep(500);
        act.doubleClick(feesChargesTab).build().perform();
        Thread.sleep(1000);
    }

    public void switchToFeesFrame() throws InterruptedException {
        jse.executeScript("arguments[0].scrollIntoView({block:'center'})", feesFrame);
        Thread.sleep(500);
        driver.switchTo().frame(FRAME_NAME);
        Thread.sleep(500);
    }

    public void switchBackToFrame() throws InterruptedException {
        driver.switchTo().frame(FRAME_NAME);
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
            if (field.getAttribute("id").toLowerCase().contains("date")) {
                field.sendKeys(Keys.TAB);
                hideDatepicker();
            }
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
        jse.executeScript("arguments[0].click()", saveBtn);
        Thread.sleep(500);
    }

    public boolean isSaveBtnVisible() { return saveBtn.isDisplayed(); }
    public boolean isSaveBtnEnabled() { return saveBtn.isEnabled(); }

    // ========== View / Edit / Delete ==========
    public void clickView() throws InterruptedException {
        jse.executeScript("arguments[0].scrollIntoView({block:'center'})", viewBtn);
        Thread.sleep(300);
        viewBtn.click();
        Thread.sleep(500);
    }

    public void clickEdit() throws InterruptedException {
        jse.executeScript("arguments[0].scrollIntoView({block:'center'})", editBtn);
        Thread.sleep(300);
        editBtn.click();
        Thread.sleep(500);
    }

    public void clickDelete() throws InterruptedException {
        jse.executeScript("arguments[0].scrollIntoView({block:'center'})", deleteBtn);
        Thread.sleep(300);
        deleteBtn.click();
        Thread.sleep(500);
    }

    public void confirmDelete() throws InterruptedException {
        popUpYes.click();
        Thread.sleep(500);
    }

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
