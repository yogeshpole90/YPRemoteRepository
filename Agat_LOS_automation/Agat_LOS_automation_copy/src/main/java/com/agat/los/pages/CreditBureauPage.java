package com.agat.los.pages;

import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.Keys;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

public class CreditBureauPage {

    private WebDriver driver;
    private JavascriptExecutor jse;

    // ========== Navigation ==========
    private By creditBureauTab = By.xpath("//a[contains(@onclick,'viewCreditBureauExposureFrame')]");

    // ========== Frame ==========
    @FindBy(id = "viewCreditBureauExposureFrame") private WebElement creditBureauFrame;

    // ========== Buttons ==========
    @FindBy(id = "addAccountDetailsBtn") private WebElement addBtn;
    @FindBy(id = "saveCreditBureauExposure") private WebElement saveBtn;

    // ========== Constructor ==========
    public CreditBureauPage(WebDriver driver) {
        this.driver = driver;
        this.jse = (JavascriptExecutor) driver;
        PageFactory.initElements(driver, this);
    }

    // ========== Navigation ==========
    public void clickCreditBureauTab() throws InterruptedException {
        WebElement tab = driver.findElement(creditBureauTab);
        jse.executeScript("arguments[0].scrollIntoView({behavior:'smooth', block:'center'})", tab);
        Thread.sleep(1000);
        jse.executeScript("arguments[0].click()", tab);
        Thread.sleep(3000);
    }

    public void switchToCreditBureauFrame() throws InterruptedException {
        jse.executeScript("arguments[0].scrollIntoView({behavior:'smooth', block:'center'})", creditBureauFrame);
        Thread.sleep(1000);
        driver.switchTo().frame(creditBureauFrame);
        Thread.sleep(2000);
    }

    public void switchToMainContent() { driver.switchTo().defaultContent(); }

    // ========== Customer Selection ==========
    public void selectCustomer() throws InterruptedException {
        jse.executeScript("$('#customerType').val('101').trigger('change')");
        Thread.sleep(2000);
        jse.executeScript("$('#customerName').val($('#customerName option').eq(1).val()).trigger('change')");
        Thread.sleep(2000);
    }

    // ========== Report Type ==========
    public void selectReportType() throws InterruptedException {
        jse.executeScript("$('#reportType').val('177').trigger('change')");
        Thread.sleep(1000);
    }

    // ========== KATM Trigger = No ==========
    public void selectKATMNo() throws InterruptedException {
        WebElement noRadio = driver.findElement(By.id("cINetTriggerConsentN"));
        jse.executeScript("arguments[0].click()", noRadio);
        Thread.sleep(1000);
    }

    // ========== Customer Basic Details ==========
    public void fillCustomerDetails(String name, String pinfl, String dob) throws InterruptedException {
        WebElement nameField = driver.findElement(By.id("custNameEnglish"));
        nameField.clear();
        nameField.sendKeys(name);
        nameField.sendKeys(Keys.TAB);
        Thread.sleep(500);

        WebElement pinflField = driver.findElement(By.id("civilID"));
        pinflField.clear();
        pinflField.sendKeys(pinfl);
        pinflField.sendKeys(Keys.TAB);
        Thread.sleep(500);

        jse.executeScript("$('#dateOfBirth').val('" + dob + "')");
        Thread.sleep(500);
    }

    // ========== Account Details (Multiline) ==========
    public void fillAccountDetails() throws InterruptedException {
        // Name of Financial Institution
        WebElement fiName = driver.findElement(By.id("nameOfFinancialInstitution"));
        fiName.clear();
        fiName.sendKeys("Agat Credit");
        fiName.sendKeys(Keys.TAB);
        Thread.sleep(300);

        // Account No
        WebElement accNo = driver.findElement(By.id("accountNo"));
        accNo.clear();
        accNo.sendKeys("ACC" + (long)(Math.random() * 900000000L + 100000000L));
        accNo.sendKeys(Keys.TAB);
        Thread.sleep(300);

        // Facility Type - Потребительский кредит (Consumer Credit) = '30'
        jse.executeScript("$('#facilityType').val('30').trigger('change')");
        Thread.sleep(500);

        // Account Status - Open = '1'
        jse.executeScript("$('#accountStatus').val('1').trigger('change')");
        Thread.sleep(500);

        // Agreement Start Date
        jse.executeScript("$('#accountOpeningDate').val('01-01-2021')");
        Thread.sleep(300);

        // Agreement End Date
        WebElement endDate = driver.findElement(By.id("accountClosingDate"));
        jse.executeScript("$('#accountClosingDate').val('01-01-2022')");
        Thread.sleep(300);

        // Outstanding Balance
        WebElement outstanding = driver.findElement(By.id("outstandingBalance"));
        outstanding.clear();
        outstanding.sendKeys("5000000");
        outstanding.sendKeys(Keys.TAB);
        Thread.sleep(300);

        // Asset Classification
        WebElement asset = driver.findElement(By.id("assetClassification"));
        asset.clear();
        asset.sendKeys("Standard");
        asset.sendKeys(Keys.TAB);
        Thread.sleep(300);

        // Installment Amount
        WebElement installment = driver.findElement(By.id("installmentAmount"));
        installment.clear();
        installment.sendKeys("500000");
        installment.sendKeys(Keys.TAB);
        Thread.sleep(300);

        // Agreement Amount (Credit Limit)
        WebElement creditLimit = driver.findElement(By.id("creditLimit"));
        creditLimit.clear();
        creditLimit.sendKeys("10000000");
        creditLimit.sendKeys(Keys.TAB);
        Thread.sleep(300);

        // Disbursed Amount (dayPastDue)
        WebElement disbursed = driver.findElement(By.id("dayPastDue"));
        disbursed.clear();
        disbursed.sendKeys("10000000");
        disbursed.sendKeys(Keys.TAB);
        Thread.sleep(300);

        // Overdue Amount
        WebElement overdue = driver.findElement(By.id("overdueBalance"));
        overdue.clear();
        overdue.sendKeys("0");
        overdue.sendKeys(Keys.TAB);
        Thread.sleep(300);

        // Security Code
        WebElement security = driver.findElement(By.id("collateralType"));
        security.clear();
        security.sendKeys("Vehicle");
        security.sendKeys(Keys.TAB);
        Thread.sleep(300);

        // Monthly Obligation
        WebElement monthly = driver.findElement(By.id("monthlyObligation"));
        monthly.clear();
        monthly.sendKeys("500000");
        monthly.sendKeys(Keys.TAB);
        Thread.sleep(300);
    }

    // ========== Add ==========
    public void clickAdd() throws InterruptedException {
        jse.executeScript("arguments[0].scrollIntoView({behavior:'smooth', block:'center'})", addBtn);
        Thread.sleep(500);
        jse.executeScript("arguments[0].click()", addBtn);
        Thread.sleep(2000);
    }

    // ========== Save ==========
    public void clickSave() throws InterruptedException {
        jse.executeScript("arguments[0].scrollIntoView({behavior:'smooth', block:'center'})", saveBtn);
        Thread.sleep(500);
        jse.executeScript("arguments[0].click()", saveBtn);
        Thread.sleep(1000);
        handleConfirmAndAlert();
    }

    private void handleConfirmAndAlert() throws InterruptedException {
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

    private String getTodayDate() {
        java.time.LocalDate today = java.time.LocalDate.now();
        return String.format("%02d-%02d-%04d", today.getDayOfMonth(), today.getMonthValue(), today.getYear());
    }
}
