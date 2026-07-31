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

public class DownPaymentPage {

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
    @FindBy(id = "currency")
    private WebElement currency;

    @FindBy(id = "overdueAmount")
    private WebElement overdueAmount;

    @FindBy(id = "dateOfPTPStart")
    private WebElement dateOfPTPStart;

    @FindBy(id = "remarks")
    private WebElement remarks;

    @FindBy(id = "scheduleType")
    private WebElement scheduleType;

    @FindBy(id = "planDate")
    private WebElement planDate;

    @FindBy(id = "plannedAmt")
    private WebElement plannedAmt;

    @FindBy(id = "paymentMode")
    private WebElement paymentMode;

    @FindBy(id = "receiptNo")
    private WebElement receiptNo;

    @FindBy(id = "remAmt")
    private WebElement remAmt;

    // ========== Schedule PTP Fields ==========
    @FindBy(id = "planDate1")
    private WebElement planDate1;

    @FindBy(id = "plannedAmt1")
    private WebElement plannedAmt1;

    @FindBy(id = "paymentMode1")
    private WebElement paymentMode1;

    // ========== Add Buttons ==========
    @FindBy(id = "add2")
    private WebElement addDPBtn;

    @FindBy(id = "add3")
    private WebElement addScheduleBtn;

    // ========== Save ==========
    @FindBy(id = "saveData")
    private WebElement saveBtn;

    // ========== Conditional Fields ==========
    @FindBy(id = "transactionDate")
    private WebElement transactionDate;

    @FindBy(id = "transactionNo")
    private WebElement transactionNo;

    @FindBy(id = "chequeDate")
    private WebElement chequeDate;

    @FindBy(id = "chequeNumber")
    private WebElement chequeNumber;

    // ========== Constructor ==========
    public DownPaymentPage(WebDriver driver) {
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

    // ========== Generic Actions ==========
    public WebElement getField(String fieldId) {
        return driver.findElement(By.id(fieldId));
    }

    public void enterText(WebElement field, String value) throws InterruptedException {
        field.clear();
        if (!value.isEmpty()) field.sendKeys(value);
        field.sendKeys(Keys.TAB);
        Thread.sleep(300);
        dismissAlert();
    }

    public void setValueByJS(WebElement field, String value) throws InterruptedException {
        jse.executeScript("arguments[0].value='" + value + "'", field);
        jse.executeScript("arguments[0].dispatchEvent(new Event('change'))", field);
        Thread.sleep(500);
        dismissAlert();
    }

    public void setDateByJS(WebElement field, String value) throws InterruptedException {
        jse.executeScript("arguments[0].value='" + value + "'", field);
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
        jse.executeScript("arguments[0].click()", saveBtn);
        Thread.sleep(3000);
        dismissAlert();
    }

    public void clickAddDP() throws InterruptedException {
        jse.executeScript("arguments[0].scrollIntoView({block:'center'})", addDPBtn);
        Thread.sleep(500);
        jse.executeScript("arguments[0].click()", addDPBtn);
        Thread.sleep(2000);
        dismissAlert();
    }

    public void clickAddSchedule() throws InterruptedException {
        jse.executeScript("arguments[0].scrollIntoView({block:'center'})", addScheduleBtn);
        Thread.sleep(500);
        jse.executeScript("arguments[0].click()", addScheduleBtn);
        Thread.sleep(2000);
        dismissAlert();
    }

    public boolean isSaveBtnVisible() { return saveBtn.isDisplayed(); }

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
    public WebElement getCurrency() { return currency; }
    public WebElement getOverdueAmount() { return overdueAmount; }
    public WebElement getDateOfPTPStart() { return dateOfPTPStart; }
    public WebElement getRemarks() { return remarks; }
    public WebElement getScheduleType() { return scheduleType; }
    public WebElement getPlanDate() { return planDate; }
    public WebElement getPlannedAmt() { return plannedAmt; }
    public WebElement getPaymentMode() { return paymentMode; }
    public WebElement getReceiptNo() { return receiptNo; }
    public WebElement getRemAmt() { return remAmt; }
    public WebElement getPlanDate1() { return planDate1; }
    public WebElement getPlannedAmt1() { return plannedAmt1; }
    public WebElement getPaymentMode1() { return paymentMode1; }
    public WebElement getTransactionDate() { return transactionDate; }
    public WebElement getTransactionNo() { return transactionNo; }
    public WebElement getChequeDate() { return chequeDate; }
    public WebElement getChequeNumber() { return chequeNumber; }
}
