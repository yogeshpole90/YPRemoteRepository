package com.agat.los.pages;

import java.time.Duration;

import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.WebDriverWait;

public class IdentificationPage {

    private WebDriver driver;
    private JavascriptExecutor jse;

    // ========== Locators ==========
    private By idTab = By.xpath("//a[contains(@onclick,'viewIdentifiDetailsId')]");

    @FindBy(id = "viewIdentifiDetailsIdFrame")
    private WebElement idFrame;

    @FindBy(id = "issueDate")
    private WebElement issueDate;

    @FindBy(id = "expiryDate")
    private WebElement expiryDate;

    @FindBy(id = "validityForId")
    private WebElement validityForId;

    @FindBy(id = "placeOfIssuance")
    private WebElement placeOfIssuance;

    @FindBy(id = "saveIdenty")
    private WebElement saveBtn;

    // ========== Constructor ==========
    public IdentificationPage(WebDriver driver) {
        this.driver = driver;
        this.jse = (JavascriptExecutor) driver;
        PageFactory.initElements(driver, this);
    }

    // ========== Navigation ==========
    public void clickIdentificationTab() throws InterruptedException {
        WebElement tab = driver.findElement(idTab);
        jse.executeScript("arguments[0].click()", tab);
        Thread.sleep(3000);
    }

    public void switchToIdentificationFrame() throws InterruptedException {
        driver.switchTo().frame(idFrame);
        Thread.sleep(2000);
    }

    public void switchToMainContent() { driver.switchTo().defaultContent(); }

    public boolean hasExistingRecord() {
        try {
            return !driver.findElements(By.cssSelector("a.editBtn")).isEmpty();
        } catch (Exception e) { return false; }
    }

    public void clickEditBtn() throws InterruptedException {
        WebElement editBtn = driver.findElement(By.cssSelector("a.editBtn"));
        jse.executeScript("arguments[0].click()", editBtn);
        Thread.sleep(3000);
    }

    // ========== Form Actions ==========
    public void scrollToIssueDate() throws InterruptedException {
        WebElement issueDateDiv = driver.findElement(By.id("issueDateDiv"));
        jse.executeScript("arguments[0].scrollIntoView({block:'center'})", issueDateDiv);
        Thread.sleep(500);
    }

    public String getIssueDateValue() {
        return issueDate.getAttribute("value");
    }

    public String getExpiryDateValue() {
        return expiryDate.getAttribute("value");
    }

    public String getValidityForId() {
        return validityForId.getAttribute("value");
    }

    public void selectPassportType() throws InterruptedException {
        jse.executeScript("$('#passportType').val($('#passportType option').eq(3).val()).trigger('change')");
        Thread.sleep(500);
    }

    public String enterPlaceOfIssuance(String value) throws InterruptedException {
        placeOfIssuance.clear();
        placeOfIssuance.sendKeys(value);
        Thread.sleep(300);
        return placeOfIssuance.getAttribute("value");
    }

    // ========== Save ==========
    public void clickSave() throws InterruptedException {
        jse.executeScript("arguments[0].scrollIntoView({block:'center'})", saveBtn);
        Thread.sleep(500);
        jse.executeScript("arguments[0].click()", saveBtn);
        Thread.sleep(1000);

        // Handle confirmation modal
        try {
            WebElement confirmYes = driver.findElement(By.id("submitForm"));
            jse.executeScript("arguments[0].click()", confirmYes);
            Thread.sleep(2000);
        } catch (Exception e) {}

        // Handle alert
        try {
            driver.switchTo().alert().accept();
            Thread.sleep(500);
        } catch (Exception e) {}

        // Scroll to search field for toast capture
        WebElement searchField = driver.findElement(By.cssSelector("input[placeholder='Search Keyword Here']"));
        jse.executeScript("arguments[0].scrollIntoView({block:'center'})", searchField);
        Thread.sleep(2000);
    }

    public String getToastMessage() {
        try {
            WebElement toastEl = driver.findElement(By.cssSelector(".toast-messages .toast-message, .toast-messages div"));
            return toastEl.getText().trim();
        } catch (Exception e) { return ""; }
    }
}
