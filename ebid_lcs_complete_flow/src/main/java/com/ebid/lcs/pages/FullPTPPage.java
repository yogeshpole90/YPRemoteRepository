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

public class FullPTPPage {

    private WebDriver driver;
    private WebDriverWait wait;
    private JavascriptExecutor jse;
    private Actions act;

    // ========== Navigation ==========
    @FindBy(xpath = "(//*[contains(@href,'=Remedial Action')])[1]")
    private WebElement remedialTab;

    @FindBy(xpath = "//*[contains(text(),'Promise to pay')]")
    private WebElement ptpTab;

    // ========== Frame ==========
    private static final String FRAME_NAME = "fetchPTPMstTabFrame";

    // ========== Form Fields ==========
    @FindBy(id = "overdueAmount")
    private WebElement overdueAmount;

    @FindBy(id = "dateOfPTPStart")
    private WebElement dateOfPTPStart;

    @FindBy(id = "remarks")
    private WebElement remarks;

    @FindBy(id = "scheduleType")
    private WebElement scheduleType;

    @FindBy(id = "paymentMode")
    private WebElement paymentMode;

    @FindBy(id = "plannedAmt")
    private WebElement plannedAmt;

    @FindBy(id = "remAmt")
    private WebElement remAmt;

    @FindBy(id = "planDate")
    private WebElement planDate;

    @FindBy(id = "currency")
    private WebElement currency;

    // ========== Conditional Fields ==========
    @FindBy(id = "transactionDate")
    private WebElement transactionDate;

    @FindBy(id = "transactionNo")
    private WebElement transactionNo;

    @FindBy(id = "receiptNo")
    private WebElement receiptNo;

    @FindBy(id = "chequeDate")
    private WebElement chequeDate;

    @FindBy(id = "chequeNumber")
    private WebElement chequeNumber;

    // ========== Action Buttons ==========
    @FindBy(id = "add")
    private WebElement addBtn;

    @FindBy(id = "saveData")
    private WebElement saveBtn;

    // ========== Constructor ==========
    public FullPTPPage(WebDriver driver) {
        this.driver = driver;
        this.wait = new WebDriverWait(driver, Duration.ofSeconds(15));
        this.jse = (JavascriptExecutor) driver;
        this.act = new Actions(driver);
        PageFactory.initElements(driver, this);
    }

    // ========== Navigation ==========
    public void navigateToPTP() throws InterruptedException {
        jse.executeScript("arguments[0].scrollIntoView({block:'center',behavior:'smooth'})", remedialTab);
        Thread.sleep(1000);
        jse.executeScript("arguments[0].click()", remedialTab);
        Thread.sleep(2000);

        jse.executeScript("arguments[0].scrollIntoView({block:'center'})", ptpTab);
        Thread.sleep(500);
        act.doubleClick(ptpTab).build().perform();
        jse.executeScript("window.scrollBy(0,3000)");
        Thread.sleep(2000);
    }

    public void switchToPTPFrame() throws InterruptedException {
        driver.switchTo().frame(FRAME_NAME);
        Thread.sleep(500);
    }

    public void switchToParentAndBack() throws InterruptedException {
        driver.switchTo().parentFrame();
        Thread.sleep(1000);
        driver.switchTo().frame(FRAME_NAME);
        Thread.sleep(500);
    }

    // ========== Generic Actions ==========
    public WebElement getField(String fieldId) {
        return driver.findElement(By.id(fieldId));
    }

    public void enterText(WebElement field, String value) throws InterruptedException {
        field.clear();
        if (!value.isEmpty()) field.sendKeys(value);
        dismissAlert();
    }

    public void setValueByJS(WebElement field, String value) {
        jse.executeScript("arguments[0].value='" + value + "'", field);
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

    public void selectDropdownByIndex(WebElement field, int index) throws InterruptedException {
        Select s = new Select(field);
        s.selectByIndex(index);
        Thread.sleep(500);
    }

    public String getSelectedText(WebElement field) {
        Select s = new Select(field);
        return s.getFirstSelectedOption().getText().trim();
    }

    public List<WebElement> getDropdownOptions(WebElement field) {
        Select s = new Select(field);
        return s.getOptions();
    }

    // ========== Save ==========
    public void clickAdd() throws InterruptedException {
        jse.executeScript("arguments[0].scrollIntoView({block:'center'})", addBtn);
        addBtn.click();
        Thread.sleep(2000);
    }

    public void clickSave() throws InterruptedException {
        jse.executeScript("arguments[0].scrollIntoView({block:'center'})", saveBtn);
        Thread.sleep(2000);
        saveBtn.click();
        Thread.sleep(2000);
    }

    // ========== View / Edit ==========
    public List<WebElement> getViewButtons() {
        return driver.findElements(By.xpath("//a[contains(@class,'ViewBtn')]"));
    }

    public List<WebElement> getEditButtons() {
        return driver.findElements(By.xpath("//a[contains(@class,'EditBtn')]"));
    }

    public List<WebElement> getDisableButtons() {
        return driver.findElements(By.xpath("//a[contains(text(),'Disable')]"));
    }

    // ========== Field Visibility ==========
    public boolean isFieldVisible(String id) {
        try {
            List<WebElement> els = driver.findElements(By.id(id));
            return els.size() > 0 && els.get(0).isDisplayed();
        } catch (Exception e) { return false; }
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

    // ========== Getters ==========
    public WebElement getOverdueAmount() { return overdueAmount; }
    public WebElement getDateOfPTPStart() { return dateOfPTPStart; }
    public WebElement getRemarks() { return remarks; }
    public WebElement getScheduleType() { return scheduleType; }
    public WebElement getPaymentMode() { return paymentMode; }
    public WebElement getPlannedAmt() { return plannedAmt; }
    public WebElement getRemAmt() { return remAmt; }
    public WebElement getPlanDate() { return planDate; }
    public WebElement getCurrency() { return currency; }
    public WebElement getTransactionDate() { return transactionDate; }
    public WebElement getTransactionNo() { return transactionNo; }
    public WebElement getReceiptNo() { return receiptNo; }
    public WebElement getChequeDate() { return chequeDate; }
    public WebElement getChequeNumber() { return chequeNumber; }
}
