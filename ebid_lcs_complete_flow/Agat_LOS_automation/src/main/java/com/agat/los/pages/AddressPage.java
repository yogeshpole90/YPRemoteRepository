package com.agat.los.pages;

import java.time.Duration;

import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.Keys;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.WebDriverWait;

public class AddressPage {

    private WebDriver driver;
    private JavascriptExecutor jse;

    // ========== Locators ==========
    private By addressTab = By.xpath("//a[contains(@onclick,'viewAddressDetails')]");

    @FindBy(id = "viewAddressDetailsFrame")
    private WebElement addressFrame;

    // ========== Constructor ==========
    public AddressPage(WebDriver driver) {
        this.driver = driver;
        this.jse = (JavascriptExecutor) driver;
        PageFactory.initElements(driver, this);
    }

    // ========== Navigation ==========
    public void clickAddressTab() throws InterruptedException {
        WebElement tab = driver.findElement(addressTab);
        jse.executeScript("arguments[0].click()", tab);
        Thread.sleep(3000);
    }

    public void switchToAddressFrame() throws InterruptedException {
        driver.switchTo().frame(addressFrame);
        Thread.sleep(2000);
    }

    public void switchToMainContent() { driver.switchTo().defaultContent(); }

    // ========== Address Form Actions ==========
    public boolean hasExistingRecord() {
        try {
            return !driver.findElements(By.cssSelector("a.editBtn")).isEmpty();
        } catch (Exception e) { return false; }
    }

    public void clickEditBtn() throws InterruptedException {
        WebElement editBtn = driver.findElement(By.cssSelector("a.editBtn"));
        jse.executeScript("arguments[0].scrollIntoView({block:'center'})", editBtn);
        Thread.sleep(500);
        jse.executeScript("arguments[0].click()", editBtn);
        Thread.sleep(3000);
    }

    public void fillAddressForm(String addrType, String startDate, String province,
                                String mohallaSearch, String addrLine, String regDate, String cadastre) throws InterruptedException {
        jse.executeScript("$('#addressType').val('" + addrType + "').trigger('change')");
        Thread.sleep(500);

        // Checkboxes
        WebElement permChk = driver.findElement(By.id("isPermantAdd"));
        if (!permChk.isSelected()) jse.executeScript("arguments[0].click()", permChk);
        Thread.sleep(300);
        WebElement busChk = driver.findElement(By.id("isBusinessAdd"));
        if (!busChk.isSelected()) jse.executeScript("arguments[0].click()", busChk);
        Thread.sleep(300);

        jse.executeScript("arguments[0].value='" + startDate + "'", driver.findElement(By.id("addressStartDate")));
        Thread.sleep(300);

        jse.executeScript("$('#province').val('" + province + "').trigger('change')");
        Thread.sleep(2000);
        jse.executeScript("$('#district').find('option').eq(1).prop('selected',true); $('#district').trigger('change')");
        Thread.sleep(2000);
        jse.executeScript("$('#area').find('option').eq(1).prop('selected',true); $('#area').trigger('change')");
        Thread.sleep(2000);

        // Mohalla search — re-fetch after open to avoid stale element
        jse.executeScript("$('#village').select2('open')");
        Thread.sleep(1000);
        WebElement mohSearch = driver.findElement(By.cssSelector(".select2-search__field"));
        mohSearch.sendKeys(mohallaSearch);
        Thread.sleep(2000);
        driver.findElement(By.cssSelector(".select2-search__field")).sendKeys(Keys.ENTER);
        Thread.sleep(1000);

        // Address Line
        WebElement addrLine1 = driver.findElement(By.id("addressLine2"));
        addrLine1.clear();
        addrLine1.sendKeys(addrLine);
        Thread.sleep(300);

        // Registration Date
        jse.executeScript("arguments[0].value='" + regDate + "'", driver.findElement(By.id("registrationDate")));
        Thread.sleep(300);

        // Cadastre
        WebElement cadField = driver.findElement(By.id("cadastre"));
        cadField.clear();
        cadField.sendKeys(cadastre);
        Thread.sleep(300);
    }

    // ========== Guarantor Selection ==========
    public void selectGuarantorForAddress() throws InterruptedException {
        jse.executeScript("$('#custType').val('103').trigger('change')");
        Thread.sleep(1000);
        jse.executeScript("$('#custName').find('option').eq(2).prop('selected',true); $('#custName').trigger('change')");
        Thread.sleep(1000);
    }

    // ========== Save ==========
    public void clickAddressSave() throws InterruptedException {
        WebElement saveBtn = driver.findElement(By.id("saveAddr"));
        jse.executeScript("arguments[0].scrollIntoView({behavior:'smooth', block:'center'})", saveBtn);
        Thread.sleep(500);
        jse.executeScript("arguments[0].click()", saveBtn);
        Thread.sleep(3000);

        // Handle alert if any
        try {
            driver.switchTo().alert().accept();
            Thread.sleep(500);
        } catch (Exception e) {}

        // Scroll to search field for toast capture
        try {
            WebElement searchField = driver.findElement(By.cssSelector("input[placeholder='Search Keyword Here']"));
            jse.executeScript("arguments[0].scrollIntoView({behavior:'smooth', block:'center'})", searchField);
            Thread.sleep(2000);
        } catch (Exception e) {}
    }
}
