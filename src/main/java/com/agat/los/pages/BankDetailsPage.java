package com.agat.los.pages;

import java.time.Duration;

import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

public class BankDetailsPage {

    private WebDriver driver;
    private JavascriptExecutor jse;

    // ========== Locators ==========
    private By bankTab = By.xpath("//a[contains(@onclick,'viewBankDetailsFrame')]");

    @FindBy(id = "viewBankDetailsFrame")
    private WebElement bankFrame;

    @FindBy(id = "accountNumber")
    private WebElement cardNumber;

    @FindBy(id = "expiryDate")
    private WebElement expiryDate;

    @FindBy(id = "nameOnCard")
    private WebElement nameOnCard;

    @FindBy(id = "saveBank")
    private WebElement saveBtn;

    // ========== Constructor ==========
    public BankDetailsPage(WebDriver driver) {
        this.driver = driver;
        this.jse = (JavascriptExecutor) driver;
        PageFactory.initElements(driver, this);
    }

    // ========== Navigation ==========
    public void clickBankDetailsTab() throws InterruptedException {
        WebElement tab = driver.findElement(bankTab);
        jse.executeScript("arguments[0].click()", tab);
        Thread.sleep(3000);
    }

    public void switchToBankFrame() throws InterruptedException {
        driver.switchTo().frame(bankFrame);
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
    public void fillBankForm(String cardNum, String expiry, String name) throws InterruptedException {
        // Account Type - Debit Card
        jse.executeScript("$('#accountType').val('2').trigger('change')");
        Thread.sleep(500);

        // Bank Name - Apex Bank
        jse.executeScript("$('#bankName').val('APB').trigger('change')");
        Thread.sleep(500);

        // Card Number
        cardNumber.clear();
        cardNumber.sendKeys(cardNum);
        Thread.sleep(300);

        // Expiry Date
        expiryDate.clear();
        expiryDate.sendKeys(expiry);
        Thread.sleep(300);

        // Name On Card
        nameOnCard.clear();
        nameOnCard.sendKeys(name);
        Thread.sleep(300);

        // Is Disbursed - Yes
        jse.executeScript("$('#isDisbursementAcc').val('Y').trigger('change')");
        Thread.sleep(500);
    }

    // ========== Save ==========
    public void clickSave() throws InterruptedException {
        jse.executeScript("arguments[0].scrollIntoView({block:'center'})", saveBtn);
        Thread.sleep(500);
        jse.executeScript("arguments[0].click()", saveBtn);
        Thread.sleep(1000);

        try {
            WebElement confirmYes = driver.findElement(By.id("submitForm"));
            jse.executeScript("arguments[0].click()", confirmYes);
            Thread.sleep(2000);
        } catch (Exception e) {}

        try {
            driver.switchTo().alert().accept();
            Thread.sleep(500);
        } catch (Exception e) {}
    }
}
