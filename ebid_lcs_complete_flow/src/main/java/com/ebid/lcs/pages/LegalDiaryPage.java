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

public class LegalDiaryPage {

    private WebDriver driver;
    private WebDriverWait wait;
    private JavascriptExecutor jse;
    private Actions act;

    // ========== Navigation ==========
    @FindBy(xpath = "//*[contains(@href,'=Legal Process')]")
    private WebElement legalProcessTab;

    @FindBy(xpath = "//a[contains(text(),'Legal Diary')]")
    private WebElement legalDiaryTab;

    // ========== Frame ==========
    private static final String FRAME_NAME = "getLegalDiaryDataFrame";

    // ========== Form Fields ==========
    @FindBy(id = "courtCaseNo")
    private WebElement courtCaseNo;

    @FindBy(id = "documentData")
    private WebElement fileInput;

    // ========== Auto-populated Fields ==========
    @FindBy(id = "courtCaseType")
    private WebElement courtCaseType;

    @FindBy(id = "currency")
    private WebElement currency;

    @FindBy(id = "suitAmount_txt")
    private WebElement suitAmount;

    @FindBy(id = "lawFirmName")
    private WebElement lawFirmName;

    @FindBy(id = "caseInitiatedby")
    private WebElement caseInitiatedBy;

    // ========== Action Buttons ==========
    @FindBy(id = "saveData")
    private WebElement saveBtn;

    @FindBy(id = "popUpYes")
    private WebElement popUpYes;

    // ========== Constructor ==========
    public LegalDiaryPage(WebDriver driver) {
        this.driver = driver;
        this.wait = new WebDriverWait(driver, Duration.ofSeconds(15));
        this.jse = (JavascriptExecutor) driver;
        this.act = new Actions(driver);
        PageFactory.initElements(driver, this);
    }

    // ========== Navigation ==========
    public void navigateToLegalDiary() throws InterruptedException {
        jse.executeScript("arguments[0].scrollIntoView({block:'center',behavior:'smooth'})", legalProcessTab);
        Thread.sleep(1000);
        jse.executeScript("arguments[0].click()", legalProcessTab);
        Thread.sleep(2000);

        jse.executeScript("arguments[0].scrollIntoView({block:'center'})", legalDiaryTab);
        Thread.sleep(500);
        act.doubleClick(legalDiaryTab).build().perform();
        Thread.sleep(2000);
    }

    public void switchToLegalDiaryFrame() throws InterruptedException {
        driver.switchTo().frame(FRAME_NAME);
        Thread.sleep(500);
    }

    // ========== Generic Actions ==========
    public WebElement getField(String fieldId) {
        return driver.findElement(By.id(fieldId));
    }

    public List<WebElement> getFields(String fieldId) {
        return driver.findElements(By.id(fieldId));
    }

    public void enterText(WebElement field, String value) throws InterruptedException {
        try { field.clear(); } catch (Exception e) { jse.executeScript("arguments[0].value=''", field); }
        dismissAlert();
        if (!value.isEmpty() && !value.equalsIgnoreCase("Empty")) {
            try { field.sendKeys(value); } catch (Exception e) { jse.executeScript("arguments[0].value='" + value + "'", field); }
            dismissAlert();
        }
    }

    public void setDateByJS(WebElement field, String value) throws InterruptedException {
        jse.executeScript("arguments[0].value='" + (value.equalsIgnoreCase("Empty") ? "" : value) + "'", field);
        jse.executeScript("arguments[0].dispatchEvent(new Event('change', {bubbles:true}));", field);
        hideDatepicker();
        dismissAlert();
    }

    public String getValue(WebElement field) {
        return field.getAttribute("value");
    }

    public void selectDropdown(WebElement field, String visibleText) throws InterruptedException {
        Select s = new Select(field);
        s.selectByVisibleText(visibleText);
        Thread.sleep(300);
        dismissAlert();
    }

    public void selectDropdownByIndex(WebElement field, int index) throws InterruptedException {
        Select s = new Select(field);
        s.selectByIndex(index);
        Thread.sleep(300);
        dismissAlert();
    }

    public String getSelectedText(WebElement field) {
        Select s = new Select(field);
        return s.getFirstSelectedOption().getText().trim();
    }

    // ========== Court Case Ref ==========
    public void selectCourtCaseRef(int index) throws InterruptedException {
        Select s = new Select(courtCaseNo);
        s.selectByIndex(index);
        Thread.sleep(1000);
        dismissAlert();
    }

    public String getCourtCaseRefText() {
        Select s = new Select(courtCaseNo);
        return s.getFirstSelectedOption().getText().trim();
    }

    public int getCourtCaseRefOptionsCount() {
        Select s = new Select(courtCaseNo);
        return s.getOptions().size();
    }

    // ========== File Upload ==========
    public void uploadDocument(String filePath) throws InterruptedException {
        fileInput.sendKeys(filePath);
        Thread.sleep(2000);
    }

    public String getFileInputValue() {
        return fileInput.getAttribute("value");
    }

    // ========== Save ==========
    public void clickSave() throws InterruptedException {
        jse.executeScript("arguments[0].scrollIntoView({block:'center'})", saveBtn);
        Thread.sleep(500);
        saveBtn.click();
        Thread.sleep(3000);
        dismissAlert();
    }

    public boolean isSaveBtnVisible() { return saveBtn.isDisplayed(); }

    // ========== View / Edit / Delete ==========
    public List<WebElement> getViewButtons() {
        return driver.findElements(By.xpath("//a[contains(@class,'ViewBtn') or contains(@onclick,'viewLegalDiary')]"));
    }

    public List<WebElement> getEditButtons() {
        return driver.findElements(By.xpath("//a[contains(@class,'editBtn') or contains(@onclick,'editLegalDiary')]"));
    }

    public List<WebElement> getDeleteButtons() {
        return driver.findElements(By.xpath("//a[contains(@class,'deleteBtn') or contains(@onclick,'deleteLegalDiary')]"));
    }

    public void clickLastView() throws InterruptedException {
        List<WebElement> btns = getViewButtons();
        if (!btns.isEmpty()) {
            WebElement lastBtn = btns.get(btns.size() - 1);
            jse.executeScript("arguments[0].scrollIntoView({block:'center'})", lastBtn);
            Thread.sleep(300);
            jse.executeScript("arguments[0].click()", lastBtn);
            Thread.sleep(2000);
        }
    }

    public void clickLastEdit() throws InterruptedException {
        List<WebElement> btns = getEditButtons();
        if (!btns.isEmpty()) {
            WebElement lastBtn = btns.get(btns.size() - 1);
            jse.executeScript("arguments[0].scrollIntoView({block:'center'})", lastBtn);
            Thread.sleep(300);
            jse.executeScript("arguments[0].click()", lastBtn);
            Thread.sleep(2000);
        }
    }

    public void clickLastDelete() throws InterruptedException {
        List<WebElement> btns = getDeleteButtons();
        if (!btns.isEmpty()) {
            WebElement lastBtn = btns.get(btns.size() - 1);
            jse.executeScript("arguments[0].scrollIntoView({block:'center'})", lastBtn);
            Thread.sleep(300);
            jse.executeScript("arguments[0].click()", lastBtn);
            Thread.sleep(1000);
        }
    }

    public void confirmDelete() throws InterruptedException {
        try {
            driver.switchTo().alert().accept();
            Thread.sleep(1000);
        } catch (Exception e) {
            try {
                popUpYes.click();
                Thread.sleep(1000);
            } catch (Exception e2) {}
        }
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
    public WebElement getCourtCaseNo() { return courtCaseNo; }
    public WebElement getCourtCaseType() { return courtCaseType; }
    public WebElement getCurrency() { return currency; }
    public WebElement getSuitAmount() { return suitAmount; }
    public WebElement getLawFirmName() { return lawFirmName; }
    public WebElement getCaseInitiatedBy() { return caseInitiatedBy; }
}
