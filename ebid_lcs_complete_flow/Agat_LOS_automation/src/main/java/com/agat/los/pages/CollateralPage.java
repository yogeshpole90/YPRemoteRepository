package com.agat.los.pages;

import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

public class CollateralPage {

    private WebDriver driver;
    private JavascriptExecutor jse;

    // ========== Locators ==========
    private By collateralTab = By.xpath("//a[contains(@onclick,'viewCollateralBasicDtlFrame')]");

    @FindBy(id = "viewCollateralBasicDtlFrame")
    private WebElement collateralFrame;

    @FindBy(id = "saveCollateral")
    private WebElement saveBtn;

    // ========== Constructor ==========
    public CollateralPage(WebDriver driver) {
        this.driver = driver;
        this.jse = (JavascriptExecutor) driver;
        PageFactory.initElements(driver, this);
    }

    // ========== Navigation ==========
    public void clickCollateralTab() throws InterruptedException {
        WebElement tab = driver.findElement(collateralTab);
        jse.executeScript("arguments[0].scrollIntoView({behavior:'smooth', block:'center'})", tab);
        Thread.sleep(1000);
        jse.executeScript("arguments[0].click()", tab);
        Thread.sleep(3000);
    }

    public void switchToCollateralFrame() throws InterruptedException {
        jse.executeScript("arguments[0].scrollIntoView({behavior:'smooth', block:'center'})", collateralFrame);
        Thread.sleep(1000);
        driver.switchTo().frame(collateralFrame);
        Thread.sleep(2000);
    }

    public void switchToMainContent() {
        driver.switchTo().defaultContent();
    }

    // ========== Form Fill ==========
    public void fillCollateralForm() throws InterruptedException {
        // Collateral Type - Vehicle
        jse.executeScript("$('#collateralType').val('33').trigger('change')");
        Thread.sleep(2000);

        // Collateral Sub Type - Agricultural machinery
        jse.executeScript("$('#collateralSubType').val('1').trigger('change')");
        Thread.sleep(1000);

        // Usage Type - New
        jse.executeScript("$('#usageType').val('1').trigger('change')");
        Thread.sleep(500);

        // Vehicle Category - Motor Vehicle
        jse.executeScript("$('#vehCat').val('1').trigger('change')");
        Thread.sleep(500);

        // Vehicle Maker - BMW
        jse.executeScript("$('#vehMaker').val('5').trigger('change')");
        Thread.sleep(2000);

        // Vehicle Model - index 1 (first actual option after placeholder)
        jse.executeScript("$('#vehModel').val($('#vehModel option').eq(1).val()).trigger('change')");
        Thread.sleep(1000);

        // Vehicle Sub Category - index 1 (first actual option after placeholder)
        jse.executeScript("$('#vehSubCat').val($('#vehSubCat option').eq(1).val()).trigger('change')");
        Thread.sleep(1000);

        // Country of Registration - index 1 (only one option loads)
        jse.executeScript(
                "$('#manufactureCountry').val($('#manufactureCountry option').eq(1).val()).trigger('change')");
        Thread.sleep(500);

        // Colour
        driver.findElement(By.id("colour")).sendKeys("White");
        Thread.sleep(300);

        // Year of Manufacture - 2024
        jse.executeScript("$('#manufactureYear').val('2024').trigger('change')");
        Thread.sleep(500);

        // VIN Number - random 17 char
        String vin = String.format("VIN%014d", System.currentTimeMillis() % 100000000000000L);
        driver.findElement(By.id("vinNumber")).sendKeys(vin.substring(0, 17));
        Thread.sleep(300);

        // Plate Number
        driver.findElement(By.id("plateNumber")).sendKeys("01A" + (int) (Math.random() * 9000 + 1000) + "AA");
        Thread.sleep(300);

        // Engine Number
        driver.findElement(By.id("engineNumber")).sendKeys("ENG" + (long) (Math.random() * 900000000L + 100000000L));
        Thread.sleep(300);

        // Vehicle Description
        driver.findElement(By.id("vehicleDesc")).sendKeys("Test Vehicle Automation");
        Thread.sleep(300);

        // Dealer Name
        driver.findElement(By.id("vehDealer")).sendKeys("Test Dealer");
        Thread.sleep(300);

        // Vehicle Price
        jse.executeScript("$('#vehiclePrice_txt').val('125000000').blur()");
        Thread.sleep(300);
        jse.executeScript("$('#vehiclePrice').val('125000000')");
        Thread.sleep(300);

        // Collateral Currency - UZS
        jse.executeScript("$('#collCurrencyVeh').val('UZS').trigger('change')");
        Thread.sleep(500);

        // Is Applicant Owner - YES
        jse.executeScript("$('#isApplicantOwner').val('1').trigger('change')");
        Thread.sleep(500);

        // RTO Passport Number
        driver.findElement(By.id("techpaspSn")).sendKeys("RTO" + (int) (Math.random() * 9000000 + 1000000));
        Thread.sleep(300);

        // RTO Passport Date - today
        jse.executeScript("$('#techpaspd').val('" + getTodayDate() + "')");
        Thread.sleep(300);

        // Body Type - SUV
        jse.executeScript("$('#bodyType').val('3').trigger('change')");
        Thread.sleep(500);

        // Owner Issuer
        driver.findElement(By.id("ownerIssuer")).sendKeys("Test Issuer");
        Thread.sleep(300);
    }

    public void fillOwnerDetails() throws InterruptedException {
        // Customer Type - Main Applicant
        jse.executeScript("$('#custTypeVeh').val('101').trigger('change')");
        Thread.sleep(3000);

        // Customer Name - index 1 (first actual option after placeholder, auto-fetches
        // other fields)
        jse.executeScript("$('#custNameVeh').val($('#custNameVeh option').eq(1).val()).trigger('change')");
        Thread.sleep(3000);

        // Address - manually enter
        jse.executeScript("arguments[0].scrollIntoView({behavior:'smooth', block:'center'})",
                driver.findElement(By.id("address")));
        Thread.sleep(500);
        driver.findElement(By.id("address")).clear();
        driver.findElement(By.id("address")).sendKeys("Tashkent Uzbekistan");
        Thread.sleep(300);
    }

    public void fillValuationDetails() throws InterruptedException {
        // Valuator Name - Loan Officer
        jse.executeScript("$('#valuatorName').val('2').trigger('change')");
        Thread.sleep(500);

        // Valuation Report Date - today
        jse.executeScript("$('#valuationReportDate').val('" + getTodayDate() + "')");
        Thread.sleep(300);

        // Valuation Number
        driver.findElement(By.id("valuationNumber")).sendKeys(String.valueOf((long) (Math.random() * 900000 + 100000)));
        Thread.sleep(300);

        // Valuation Amount
        jse.executeScript("$('#valuationAmount_txt').val('125000000').blur()");
        Thread.sleep(300);
        jse.executeScript("$('#valuationAmount').val('125000000')");
        Thread.sleep(300);

        // Available Collateral Amount
        jse.executeScript("$('#availableCollateralAmt_txt').val('125000000').blur()");
        Thread.sleep(300);
        jse.executeScript("$('#availableCollateralAmt').val('125000000')");
        Thread.sleep(300);

        // Nature of Charges - Hypothecation
        jse.executeScript("$('#natureOfChargeVeh').val('2').trigger('change')");
        Thread.sleep(500);
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
        } catch (Exception e) {
        }

        try {
            driver.switchTo().alert().accept();
            Thread.sleep(500);
        } catch (Exception e) {
        }
    }

    // ========== Utility ==========
    private String getTodayDate() {
        java.time.LocalDate today = java.time.LocalDate.now();
        return String.format("%02d-%02d-%04d", today.getDayOfMonth(), today.getMonthValue(), today.getYear());
    }
}
