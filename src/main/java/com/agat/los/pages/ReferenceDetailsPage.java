package com.agat.los.pages;

import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

public class ReferenceDetailsPage {

    private WebDriver driver;
    private JavascriptExecutor jse;

    // ========== Locators ==========
    private By refTab = By.xpath("//a[contains(@onclick,'viewReferenceDetailsFrame')]");

    @FindBy(id = "viewReferenceDetailsFrame")
    private WebElement refFrame;

    @FindBy(id = "contactNumber")
    private WebElement contactNumber;

    @FindBy(id = "mobileOtp")
    private WebElement mobileOtp;

    @FindBy(id = "sendOtpMobile")
    private WebElement verifyMobileBtn;

    @FindBy(id = "mobileVerify")
    private WebElement verifyBtn;

    @FindBy(id = "referanceSave")
    private WebElement saveBtn;

    // ========== Constructor ==========
    public ReferenceDetailsPage(WebDriver driver) {
        this.driver = driver;
        this.jse = (JavascriptExecutor) driver;
        PageFactory.initElements(driver, this);
    }

    // ========== Navigation ==========
    public void clickReferenceTab() throws InterruptedException {
        WebElement tab = driver.findElement(refTab);
        jse.executeScript("arguments[0].scrollIntoView({behavior:'smooth', block:'center'})", tab);
        Thread.sleep(1000);
        jse.executeScript("arguments[0].click()", tab);
        Thread.sleep(3000);
    }

    public void switchToReferenceFrame() throws InterruptedException {
        jse.executeScript("arguments[0].scrollIntoView({behavior:'smooth', block:'center'})", refFrame);
        Thread.sleep(1000);
        driver.switchTo().frame(refFrame);
        Thread.sleep(2000);
    }

    public void switchToMainContent() { driver.switchTo().defaultContent(); }

    // ========== Form Actions ==========
    public void fillReferenceForm(String phone) throws InterruptedException {
        // Contact Type - Other Phone No
        jse.executeScript("$('#contactType').val('1').trigger('change')");
        Thread.sleep(500);

        // Contact Number - scroll to it first
        jse.executeScript("arguments[0].scrollIntoView({behavior:'smooth', block:'center'})", contactNumber);
        Thread.sleep(500);
        contactNumber.clear();
        contactNumber.sendKeys(phone);
        Thread.sleep(300);

        // Status - Active
        jse.executeScript("$('#status').val('1').trigger('change')");
        Thread.sleep(500);
    }

    public void verifyMobile(String otp) throws InterruptedException {
        // Click Verify Mobile button
        jse.executeScript("arguments[0].click()", verifyMobileBtn);
        Thread.sleep(1000);

        // Accept alert
        try {
            driver.switchTo().alert().accept();
            Thread.sleep(2000);
        } catch (Exception e) {}

        // Enter OTP
        mobileOtp.clear();
        mobileOtp.sendKeys(otp);
        Thread.sleep(500);

        // Click Verify
        jse.executeScript("arguments[0].click()", verifyBtn);
        Thread.sleep(1000);

        // Accept alert
        try {
            driver.switchTo().alert().accept();
            Thread.sleep(2000);
        } catch (Exception e) {}
    }

    // ========== Save ==========
    public void clickSave() throws InterruptedException {
        jse.executeScript("arguments[0].scrollIntoView({behavior:'smooth', block:'center'})", saveBtn);
        Thread.sleep(1000);
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
