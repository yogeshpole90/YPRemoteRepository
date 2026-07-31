package com.ebid.lcs.pages;

import java.time.Duration;
import java.util.List;

import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.Select;
import org.openqa.selenium.support.ui.WebDriverWait;

public class SiteVisitPage {

    private WebDriver driver;
    private WebDriverWait wait;
    private JavascriptExecutor jse;
    private Actions act;

    // ========== Navigation ==========
    @FindBy(xpath = "//*[contains(@href,'Follow-Up')]")
    private WebElement followUpTab;

    @FindBy(xpath = "//*[contains(text(),'Site Visit Request')]")
    private WebElement siteVisitTab;

    // ========== Frame ==========
    private static final String FRAME_NAME = "createSiteVisitDetailsFrame";

    // ========== Form Fields ==========
    @FindBy(id = "currency")
    private WebElement currency;

    @FindBy(id = "visitType")
    private WebElement visitType;

    @FindBy(id = "visitedBy")
    private WebElement visitedBy;

    @FindBy(id = "customerResponse")
    private WebElement customerResponse;

    @FindBy(id = "visitInitiatedt")
    private WebElement visitInitiatedt;

    @FindBy(id = "visitDate")
    private WebElement visitDate;

    @FindBy(id = "collection")
    private WebElement collection;

    @FindBy(id = "collectedDate")
    private WebElement collectedDate;

    @FindBy(id = "collectedAmount_txt")
    private WebElement collectedAmount;

    @FindBy(id = "modeOfPayment")
    private WebElement modeOfPayment;

    @FindBy(id = "remarks")
    private WebElement remarks;

    // ========== Conditional Fields ==========
    @FindBy(id = "transactionDate")
    private WebElement transactionDate;

    @FindBy(id = "transactionNo")
    private WebElement transactionNo;

    @FindBy(id = "receiptNo")
    private WebElement receiptNo;

    @FindBy(id = "chequeDate")
    private WebElement chequeDate;

    @FindBy(id = "chequeNo")
    private WebElement chequeNo;

    // ========== Action Buttons ==========
    @FindBy(id = "saveData")
    private WebElement saveBtn;

    // ========== Table Locators ==========
    @FindBy(css = "input[placeholder='Search keyword here']")
    private WebElement searchBox;

    @FindBy(css = "tbody tr:first-child a.ViewBtn")
    private WebElement viewFirstRowBtn;

    @FindBy(css = "tbody tr:first-child a.editBtn")
    private WebElement editFirstRowBtn;

    // ========== Constructor ==========
    public SiteVisitPage(WebDriver driver) {
        this.driver = driver;
        this.wait = new WebDriverWait(driver, Duration.ofSeconds(15));
        this.jse = (JavascriptExecutor) driver;
        this.act = new Actions(driver);
        PageFactory.initElements(driver, this);
    }

    // ========== Navigation ==========
    public void navigateToSiteVisit() throws InterruptedException {
        jse.executeScript("arguments[0].scrollIntoView({block:'center',behavior:'smooth'})", followUpTab);
        Thread.sleep(1000);
        followUpTab.click();
        Thread.sleep(2000);

        WebElement svTab = wait.until(ExpectedConditions.elementToBeClickable(siteVisitTab));
        jse.executeScript("arguments[0].scrollIntoView({block:'center',behavior:'smooth'})", svTab);
        Thread.sleep(1000);
        act.doubleClick(svTab).build().perform();
        Thread.sleep(2000);
    }

    public void switchToSiteVisitFrame() throws InterruptedException {
        driver.switchTo().frame(FRAME_NAME);
        Thread.sleep(500);
    }

    // ========== Generic Actions ==========
    public WebElement getField(String fieldId) {
        return driver.findElement(By.id(fieldId));
    }

    public void enterText(WebElement field, String value) throws InterruptedException {
        field.clear();
        if (!value.isEmpty() && !value.equalsIgnoreCase("Empty")) {
            field.sendKeys(value);
        }
        Thread.sleep(300);
    }

    public void setDateByJS(WebElement field, String value) throws InterruptedException {
        jse.executeScript("arguments[0].value='" + value + "'", field);
        Thread.sleep(300);
    }

    public void setTextByJS(WebElement field, String value) throws InterruptedException {
        jse.executeScript("arguments[0].value=''", field);
        jse.executeScript("arguments[0].value=arguments[1]", field, value);
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

    public boolean isDisplayed(WebElement field) { return field.isDisplayed(); }
    public boolean isEnabled(WebElement field) { return field.isEnabled(); }

    // ========== Save ==========
    public void clickSave() throws InterruptedException {
        jse.executeScript("arguments[0].scrollIntoView({block:'center'})", saveBtn);
        Thread.sleep(500);
        saveBtn.click();
        Thread.sleep(2000);
    }

    public boolean isSaveBtnVisible() { return saveBtn.isDisplayed(); }
    public boolean isSaveBtnEnabled() { return saveBtn.isEnabled(); }

    // ========== View / Edit ==========
    public void scrollToSearch() throws InterruptedException {
        try {
            jse.executeScript("arguments[0].scrollIntoView({block:'center',behavior:'smooth'})", searchBox);
            Thread.sleep(500);
        } catch (Exception e) {}
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

    // ========== Field Visibility ==========
    public boolean isFieldVisible(String id) {
        try {
            List<WebElement> els = driver.findElements(By.id(id));
            return els.size() > 0 && els.get(0).isDisplayed();
        } catch (Exception e) { return false; }
    }

    // ========== Getters ==========
    public WebElement getCurrency() { return currency; }
    public WebElement getVisitType() { return visitType; }
    public WebElement getVisitedBy() { return visitedBy; }
    public WebElement getCustomerResponse() { return customerResponse; }
    public WebElement getVisitInitiatedt() { return visitInitiatedt; }
    public WebElement getVisitDate() { return visitDate; }
    public WebElement getCollection() { return collection; }
    public WebElement getCollectedDate() { return collectedDate; }
    public WebElement getCollectedAmount() { return collectedAmount; }
    public WebElement getModeOfPayment() { return modeOfPayment; }
    public WebElement getRemarks() { return remarks; }
    public WebElement getTransactionDate() { return transactionDate; }
    public WebElement getTransactionNo() { return transactionNo; }
    public WebElement getReceiptNo() { return receiptNo; }
    public WebElement getChequeDate() { return chequeDate; }
    public WebElement getChequeNo() { return chequeNo; }
}
