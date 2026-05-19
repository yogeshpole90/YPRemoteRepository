package com.ebid.lcs.pages;

import java.util.List;

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

public class UserCreationPage {

    private WebDriver driver;
    private WebDriverWait wait;

    // ========== Navigation Locators ==========
    @FindBy(xpath = "//*[@class='item-nav']/div")
    private WebElement hamburgerMenu;

    @FindBy(xpath = "//*[@id='Administration']/a")
    private WebElement adminLink;

    @FindBy(xpath = "//*[@id='usermgmtAdm']/a")
    private WebElement userMgmtLink;

    @FindBy(xpath = "//a[contains(@href,'menuCode=USERMGMT')]")
    private WebElement userMasterLink;

    @FindBy(id = "addButton")
    private WebElement addBtn;

    // ========== Form Locators ==========
    @FindBy(id = "loginId")
    private WebElement loginId;

    @FindBy(id = "select2-employeeId-container")
    private WebElement empIdContainer;

    @FindBy(id = "roleCode")
    private WebElement roleCode;

    @FindBy(id = "select2-assignedBranch-container")
    private WebElement branchContainer;

    @FindBy(id = "userSalutation")
    private WebElement salutation;

    @FindBy(id = "userFName")
    private WebElement firstName;

    @FindBy(id = "userMName")
    private WebElement middleName;

    @FindBy(id = "userLName")
    private WebElement lastName;

    @FindBy(id = "userDisplayName")
    private WebElement displayName;

    @FindBy(id = "reportingUserCode")
    private WebElement reportingMgr;

    @FindBy(id = "userTypeCode")
    private WebElement userCategory;

    @FindBy(id = "preferLang")
    private WebElement language;

    @FindBy(id = "isdmobileNo1")
    private WebElement mobileIsd;

    @FindBy(id = "mobileNo1")
    private WebElement mobileNo;

    @FindBy(id = "emailId")
    private WebElement emailId;

    @FindBy(id = "btnSave")
    private WebElement saveBtn;

    // ========== Constructor ==========
    public UserCreationPage(WebDriver driver) {
        this.driver = driver;
        this.wait = new WebDriverWait(driver, 15);
        PageFactory.initElements(driver, this);
    }

    // ========== Navigation ==========
    public void navigateToUserMaster() throws InterruptedException {
        hamburgerMenu.click();
        Thread.sleep(2000);
        wait.until(ExpectedConditions.elementToBeClickable(adminLink));
        adminLink.click();
        Thread.sleep(1000);
        wait.until(ExpectedConditions.elementToBeClickable(userMgmtLink));
        userMgmtLink.click();
        Thread.sleep(1000);
        wait.until(ExpectedConditions.elementToBeClickable(userMasterLink));
        userMasterLink.click();
        Thread.sleep(2000);
    }

    public void clickAdd() throws InterruptedException {
        wait.until(ExpectedConditions.elementToBeClickable(addBtn));
        addBtn.click();
        Thread.sleep(2000);
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
            field.sendKeys(Keys.ESCAPE);
            Thread.sleep(200);
        }
    }

    // Tab press separately - call only when needed
    public void pressTab(WebElement field) throws InterruptedException {
        field.sendKeys(Keys.TAB);
        Thread.sleep(500);
    }

    public String getValue(WebElement field) {
        return field.getAttribute("value");
    }

    public void selectDropdown(WebElement field, String visibleText) throws InterruptedException {
        Select s = new Select(field);
        s.selectByVisibleText(visibleText);
        Thread.sleep(500);
        field.sendKeys(Keys.ESCAPE);
        Thread.sleep(300);
    }

    public String getSelectedText(WebElement field) {
        Select s = new Select(field);
        return s.getFirstSelectedOption().getText().trim();
    }

    // ========== Select2 Dropdown (Employee ID, Branch) ==========
    public void selectFromSelect2(WebElement container, String searchText) throws InterruptedException {
        container.click();
        Thread.sleep(1000);
        WebElement searchBox = driver.findElement(By.xpath("//input[@class='select2-search__field']"));
        searchBox.sendKeys(searchText);
        Thread.sleep(1500);
        List<WebElement> results = driver.findElements(By.xpath("//li[contains(@class,'select2-results__option')]"));
        if (results.size() > 0) {
            results.get(0).click();
            Thread.sleep(2000);
        } else {
            searchBox.sendKeys(Keys.ESCAPE);
        }
    }

    // Employee ID select - hardcoded 992, handles duplicate toast
    public String selectEmployeeId() throws InterruptedException {
        empIdContainer.click();
        Thread.sleep(1000);
        WebElement searchBox = driver.findElement(By.xpath("//input[@class='select2-search__field']"));
        searchBox.sendKeys("992");
        Thread.sleep(1500);
        List<WebElement> results = driver.findElements(By.xpath("//li[contains(@class,'select2-results__option')]"));
        if (results.size() > 0) {
            results.get(0).click();
            Thread.sleep(3000);

            // Check duplicate toast
            try {
                List<WebElement> toasts = driver.findElements(By.cssSelector("div.msg-toast.msg-error.msg-showing em"));
                for (WebElement t : toasts) {
                    if (t.getText().contains("already in use") || t.getText().contains("Already")) {
                        // Escape to close dropdown
                        driver.findElement(By.tagName("body")).sendKeys(Keys.ESCAPE);
                        Thread.sleep(500);
                        return "DUPLICATE - 992 already in use";
                    }
                }
            } catch (Exception e) { }

            // Escape to close any open overlay
            driver.findElement(By.tagName("body")).sendKeys(Keys.ESCAPE);
            Thread.sleep(500);
            return empIdContainer.getAttribute("title");
        } else {
            searchBox.sendKeys(Keys.ESCAPE);
            Thread.sleep(500);
            return "NOT FOUND";
        }
    }

    public String getSelect2Value(WebElement container) {
        return container.getAttribute("title");
    }

    // ========== Field State ==========
    public boolean isReadOnly(WebElement field) {
        return field.getAttribute("readonly") != null || field.getAttribute("disabled") != null || !field.isEnabled();
    }

    // ========== Save ==========
    public void clickSave() throws InterruptedException {
        JavascriptExecutor js = (JavascriptExecutor) driver;
        js.executeScript("arguments[0].scrollIntoView({block:'center'})", saveBtn);
        Thread.sleep(500);
        js.executeScript("arguments[0].click()", saveBtn);
        Thread.sleep(3000);
    }

    public boolean isSaveBtnVisible() { return saveBtn.isDisplayed(); }
    public boolean isSaveBtnEnabled() { return saveBtn.isEnabled(); }

    // ========== Getters for Page Elements ==========
    public WebElement getEmpIdContainer() { return empIdContainer; }
    public WebElement getBranchContainer() { return branchContainer; }
}
