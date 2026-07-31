package com.ebid.lcs.pages;

import java.time.Duration;

import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.Select;
import org.openqa.selenium.support.ui.WebDriverWait;

public class ActionDocMapPage {

    private WebDriver driver;
    private WebDriverWait wait;
    private JavascriptExecutor jse;

    // ========== Navigation Locators ==========
    @FindBy(xpath = "//*[@class='item-nav']/div")
    private WebElement hamburgerMenu;

    @FindBy(xpath = "//*[@id='ACTIONDOCMAP']/a")
    private WebElement actionDocMapLink;

    // ========== Form Locators ==========
    @FindBy(id = "addButton")
    private WebElement addBtn;

    @FindBy(id = "actionName")
    private WebElement actionName;

    @FindBy(id = "documentName")
    private WebElement documentName;

    @FindBy(id = "ifMandatoryUpload")
    private WebElement mandatoryUpload;

    @FindBy(id = "ifOriginal")
    private WebElement ifOriginal;

    @FindBy(id = "save")
    private WebElement saveBtn;

    @FindBy(id = "backButton")
    private WebElement backBtn;

    // ========== List Page Locators ==========
    @FindBy(xpath = "//*[@type='search']")
    private WebElement searchBox;

    @FindBy(xpath = "//a[contains(@class,'button view')]")
    private WebElement viewBtn;

    @FindBy(xpath = "//a[contains(@class,'button edit')]")
    private WebElement editBtn;

    @FindBy(xpath = "//a[contains(@class,'button delete')]")
    private WebElement disableBtn;

    @FindBy(id = "popUpYes")
    private WebElement popUpYesBtn;

    // ========== Constructor ==========
    public ActionDocMapPage(WebDriver driver) {
        this.driver = driver;
        this.wait = new WebDriverWait(driver, Duration.ofSeconds(15));
        this.jse = (JavascriptExecutor) driver;
        PageFactory.initElements(driver, this);
    }

    // ========== Navigation ==========
    public void navigateToActionDocMap() throws InterruptedException {
        hamburgerMenu.click();
        Thread.sleep(500);
        wait.until(ExpectedConditions.elementToBeClickable(actionDocMapLink));
        jse.executeScript("arguments[0].scrollIntoView({block:'center'})", actionDocMapLink);
        Thread.sleep(300);
        actionDocMapLink.click();
        Thread.sleep(1000);
    }

    public void clickAdd() throws InterruptedException {
        wait.until(ExpectedConditions.elementToBeClickable(addBtn));
        addBtn.click();
        Thread.sleep(1000);
    }

    // ========== Generic Field Actions ==========
    public WebElement getField(String fieldId) {
        return driver.findElement(By.id(fieldId));
    }

    public void enterText(String fieldId, String value) throws InterruptedException {
        WebElement f = driver.findElement(By.id(fieldId));
        jse.executeScript("arguments[0].scrollIntoView({block:'center'})", f);
        Thread.sleep(300);
        f.clear();
        if (!value.isEmpty() && !value.equalsIgnoreCase("Empty")) f.sendKeys(value);
    }

    public String getValue(String fieldId) {
        return driver.findElement(By.id(fieldId)).getAttribute("value");
    }

    public void selectDropdown(String fieldId, String visibleText) throws InterruptedException {
        WebElement f = driver.findElement(By.id(fieldId));
        jse.executeScript("arguments[0].scrollIntoView({block:'center'})", f);
        Thread.sleep(300);
        Select s = new Select(f);
        s.selectByVisibleText(visibleText);
        Thread.sleep(500);
    }

    public String getSelectedText(String fieldId) {
        Select s = new Select(driver.findElement(By.id(fieldId)));
        return s.getFirstSelectedOption().getText().trim();
    }

    public String getTagName(String fieldId) {
        return driver.findElement(By.id(fieldId)).getTagName();
    }

    // ========== Save & Back ==========
    public void scrollDown() throws InterruptedException {
        jse.executeScript("window.scrollBy(0,500)");
        Thread.sleep(300);
    }

    public void clickSave() throws InterruptedException {
        scrollDown();
        wait.until(ExpectedConditions.elementToBeClickable(saveBtn));
        saveBtn.click();
        Thread.sleep(2000);
    }

    public boolean isSaveDisplayed() {
        return saveBtn.isDisplayed();
    }

    public void clickBack() throws InterruptedException {
        scrollDown();
        wait.until(ExpectedConditions.elementToBeClickable(backBtn));
        backBtn.click();
        Thread.sleep(1000);
    }

    public boolean isBackDisplayed() {
        return backBtn.isDisplayed();
    }

    // ========== List Page Actions ==========
    public void searchRecord(String text) throws InterruptedException {
        wait.until(ExpectedConditions.visibilityOf(searchBox));
        searchBox.clear();
        searchBox.sendKeys(text);
        Thread.sleep(1000);
    }

    public void clickView() throws InterruptedException {
        wait.until(ExpectedConditions.elementToBeClickable(viewBtn));
        viewBtn.click();
        Thread.sleep(1500);
    }

    public void clickEdit() throws InterruptedException {
        wait.until(ExpectedConditions.elementToBeClickable(editBtn));
        editBtn.click();
        Thread.sleep(1500);
    }

    public void clickDisable() throws InterruptedException {
        wait.until(ExpectedConditions.elementToBeClickable(disableBtn));
        disableBtn.click();
        Thread.sleep(1000);
    }

    public void confirmDisable() throws InterruptedException {
        try {
            Thread.sleep(1000);
            WebElement yesBtn = driver.findElement(By.id("popUpYes"));
            jse.executeScript("arguments[0].click()", yesBtn);
            Thread.sleep(1500);
        } catch (Exception e) {
            try {
                driver.switchTo().alert().accept();
                Thread.sleep(1000);
            } catch (Exception ex) {}
        }
    }
}
