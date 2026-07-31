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

public class CaseStatusPage {

    private WebDriver driver;
    private WebDriverWait wait;
    private JavascriptExecutor jse;
    private Actions act;

    // ========== Navigation / Tab Locators ==========
    @FindBy(xpath = "//a[contains(@href,'activeTab=Account Information')]")
    private WebElement accountInfoTab;

    @FindBy(xpath = "//a[contains(text(),'Case Status')]")
    private WebElement caseStatusTab;

    // ========== Frame ==========
    @FindBy(id = "viewCaseStatusFrame")
    private WebElement caseStatusFrame;

    // ========== Form Field Locators ==========
    @FindBy(id = "caseStatusId")
    private WebElement caseStatusId;

    @FindBy(id = "remarks")
    private WebElement remarks;

    // ========== Action Buttons ==========
    @FindBy(id = "saveData")
    private WebElement saveBtn;

    // ========== Table / List Locators ==========
    @FindBy(xpath = "(//a[contains(@onclick,'ViewData')])[1]")
    private WebElement viewFirstRowBtn;

    @FindBy(xpath = "(//a[contains(@class,'editBtn')])[1]")
    private WebElement editFirstRowBtn;

    @FindBy(xpath = "//input[@type='search' and @aria-controls='dt-basicDetails']")
    private WebElement searchBox;

    // ========== Constructor ==========
    public CaseStatusPage(WebDriver driver) {
        this.driver = driver;
        this.wait = new WebDriverWait(driver, Duration.ofSeconds(15));
        this.jse = (JavascriptExecutor) driver;
        this.act = new Actions(driver);
        PageFactory.initElements(driver, this);
    }

    // ========== Navigation ==========
    public void navigateToCaseStatusTab() throws InterruptedException {
        jse.executeScript("arguments[0].scrollIntoView({block:'center',behavior:'smooth'})", accountInfoTab);
        Thread.sleep(1000);
        accountInfoTab.click();
        Thread.sleep(2000);

        WebElement cstTab = wait.until(ExpectedConditions.elementToBeClickable(caseStatusTab));
        jse.executeScript("arguments[0].scrollIntoView({block:'center',behavior:'smooth'})", cstTab);
        Thread.sleep(1000);
        act.doubleClick(cstTab).build().perform();
        Thread.sleep(2000);
    }

    public void switchToCaseStatusFrame() throws InterruptedException {
        driver.switchTo().frame("viewCaseStatusFrame");
        Thread.sleep(500);
    }

    public void switchToParentAndBackToFrame() throws InterruptedException {
        driver.switchTo().parentFrame();
        Thread.sleep(500);
        driver.switchTo().frame("viewCaseStatusFrame");
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
        Thread.sleep(500);
    }

    public String getSelectedText(WebElement field) {
        Select s = new Select(field);
        return s.getFirstSelectedOption().getText().trim();
    }

    public boolean isDisplayed(WebElement field) {
        return field.isDisplayed();
    }

    public boolean isEnabled(WebElement field) {
        return field.isEnabled();
    }

    // ========== Form Field Getters ==========
    public WebElement getCaseStatusId() { return caseStatusId; }
    public WebElement getRemarks() { return remarks; }

    // ========== Save ==========
    public void clickSave() throws InterruptedException {
        jse.executeScript("arguments[0].scrollIntoView({block:'center'})", saveBtn);
        Thread.sleep(500);
        saveBtn.click();
        Thread.sleep(1000);
    }

    public boolean isSaveBtnVisible() { return saveBtn.isDisplayed(); }
    public boolean isSaveBtnEnabled() { return saveBtn.isEnabled(); }

    // ========== View / Edit Actions ==========
    public void scrollToSearch() throws InterruptedException {
        try {
            jse.executeScript("arguments[0].scrollIntoView({block:'center',behavior:'smooth'})", searchBox);
            Thread.sleep(1000);
        } catch (Exception e) { }
    }

    public void clickViewFirstRow() throws InterruptedException {
        List<WebElement> viewBtns = driver.findElements(By.xpath("//a[contains(@onclick,'ViewData')]"));
        if (viewBtns.size() > 0) {
            jse.executeScript("arguments[0].scrollIntoView({block:'center',behavior:'smooth'})", viewFirstRowBtn);
            Thread.sleep(300);
            viewFirstRowBtn.click();
            Thread.sleep(2000);
        }
    }

    public void clickEditFirstRow() throws InterruptedException {
        List<WebElement> editBtns = driver.findElements(By.cssSelector("a.editBtn"));
        if (editBtns.size() > 0) {
            jse.executeScript("arguments[0].scrollIntoView({block:'center',behavior:'smooth'})", editFirstRowBtn);
            Thread.sleep(300);
            editFirstRowBtn.click();
            Thread.sleep(2000);
        }
    }

    public int getViewButtonCount() {
        return driver.findElements(By.xpath("//a[contains(@onclick,'ViewData')]")).size();
    }

    public int getEditButtonCount() {
        return driver.findElements(By.cssSelector("a.editBtn")).size();
    }

    // ========== Fill Form for Save/Edit ==========
    public void fillCaseStatusForm(int statusIndex, String remarksText) throws InterruptedException {
        selectDropdownByIndex(caseStatusId, statusIndex);
        enterText(remarks, remarksText);
        Thread.sleep(500);
    }
}
