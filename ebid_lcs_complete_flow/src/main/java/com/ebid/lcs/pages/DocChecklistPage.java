package com.ebid.lcs.pages;

import java.time.Duration;
import java.util.List;

import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.Select;
import org.openqa.selenium.support.ui.WebDriverWait;

public class DocChecklistPage {

    private WebDriver driver;
    private WebDriverWait wait;
    private JavascriptExecutor jse;

    // ========== Navigation ==========
    @FindBy(xpath = "//*[contains(@href,'activeTab=Document')]")
    private WebElement documentTab;

    // ========== Frame ==========
    private static final String FRAME_NAME = "documentUploadPageFrame";

    // ========== Form Fields ==========
    @FindBy(id = "documentName")
    private WebElement documentName;

    // ========== Action Buttons ==========
    @FindBy(id = "saveData")
    private WebElement saveBtn;

    @FindBy(id = "popUpYes")
    private WebElement popUpYes;

    // ========== Table Locators ==========
    @FindBy(css = "input[placeholder='Search keyword here']")
    private WebElement searchBox;

    // ========== Constructor ==========
    public DocChecklistPage(WebDriver driver) {
        this.driver = driver;
        this.wait = new WebDriverWait(driver, Duration.ofSeconds(15));
        this.jse = (JavascriptExecutor) driver;
        PageFactory.initElements(driver, this);
    }

    // ========== Navigation ==========
    public void navigateToDocumentTab() throws InterruptedException {
        jse.executeScript("arguments[0].scrollIntoView({block:'center',behavior:'smooth'})", documentTab);
        Thread.sleep(1000);
        documentTab.click();
        Thread.sleep(2000);
    }

    public void switchToDocFrame() throws InterruptedException {
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
        }
        Thread.sleep(300);
    }

    public void uploadFile(WebElement field, String filePath) throws InterruptedException {
        field.sendKeys(filePath);
        Thread.sleep(1000);
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
        saveBtn.click();
        Thread.sleep(2000);
    }

    public boolean isSaveBtnVisible() { return saveBtn.isDisplayed(); }
    public boolean isSaveBtnEnabled() { return saveBtn.isEnabled(); }

    // ========== Delete ==========
    public List<WebElement> getDeleteButtons() {
        return driver.findElements(By.xpath("//a[contains(@class,'DeleteBtn')]"));
    }

    public void clickLastDelete() throws InterruptedException {
        List<WebElement> btns = getDeleteButtons();
        if (btns.size() > 0) {
            WebElement lastBtn = btns.get(btns.size() - 1);
            jse.executeScript("arguments[0].scrollIntoView({block:'center'})", lastBtn);
            Thread.sleep(500);
            jse.executeScript("arguments[0].click()", lastBtn);
            Thread.sleep(1000);
        }
    }

    public void confirmDelete() throws InterruptedException {
        popUpYes.click();
        Thread.sleep(1000);
    }

    // ========== Helpers ==========
    public void scrollToSearch() throws InterruptedException {
        try {
            jse.executeScript("arguments[0].scrollIntoView({block:'center'})", searchBox);
            Thread.sleep(500);
        } catch (Exception e) {}
    }

    public String getDocumentName() {
        return documentName.getAttribute("value");
    }
}
