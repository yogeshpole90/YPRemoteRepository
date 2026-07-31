package com.agat.los.pages;

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

public class ProductDetailsPage {

    private WebDriver driver;
    private JavascriptExecutor jse;
    private WebDriverWait wait;

    // ========== Section Navigation ==========
    private By productDetailsTab = By.xpath("//a[contains(@href,'activeTab=PRODUCT DETAILS')]");

    // ========== Loan Item Details Locators ==========
    private By loanItemTab = By.xpath("//a[contains(@onclick,'viewLoanItemDetailsFrame')]");
    @FindBy(id = "viewLoanItemDetailsFrame") private WebElement loanItemFrame;
    @FindBy(id = "reqLoanAmt") private WebElement reqLoanAmtHidden;
    @FindBy(id = "reqLoanAmt_txt") private WebElement reqLoanAmtTxt;
    @FindBy(id = "remarks") private WebElement remarks;
    @FindBy(id = "saveLoanItem") private WebElement saveLoanItemBtn;

    // ========== Loan Details Locators ==========
    private By loanDetailsTab = By.xpath("//a[contains(@onclick,'viewproductIdFrame')]");
    @FindBy(id = "viewproductIdFrame") private WebElement loanDetailsFrame;
    @FindBy(id = "saveproduct") private WebElement saveLoanDetailsBtn;
    @FindBy(id = "repaymentBtn1") private WebElement repaymentBtn;

    // ========== Insurance Details Locators ==========
    private By insuranceTab = By.xpath("//a[contains(@onclick,'viewInsuranceDetailsFrame')]");
    @FindBy(id = "viewInsuranceDetailsFrame") private WebElement insuranceFrame;
    @FindBy(id = "mainPolicyNo") private WebElement policyNumber;
    @FindBy(id = "saveInsurance") private WebElement saveInsuranceBtn;

    // ========== Constructor ==========
    public ProductDetailsPage(WebDriver driver) {
        this.driver = driver;
        this.jse = (JavascriptExecutor) driver;
        this.wait = new WebDriverWait(driver, Duration.ofSeconds(15));
        PageFactory.initElements(driver, this);
    }

    public void switchToMainContent() { driver.switchTo().defaultContent(); }

    // ========== Common Navigation ==========
    public void clickProductDetailsTab() throws InterruptedException {
        WebElement tab = wait.until(ExpectedConditions.elementToBeClickable(productDetailsTab));
        jse.executeScript("arguments[0].scrollIntoView({behavior:'smooth', block:'center'})", tab);
        jse.executeScript("arguments[0].click()", tab);
        wait.until(ExpectedConditions.elementToBeClickable(loanItemTab));
    }

    public void clickEditBtn() throws InterruptedException {
        WebElement editBtn = wait.until(ExpectedConditions.elementToBeClickable(By.cssSelector("a.editBtn")));
        jse.executeScript("arguments[0].scrollIntoView({behavior:'smooth', block:'center'})", editBtn);
        jse.executeScript("arguments[0].click()", editBtn);
        wait.until(ExpectedConditions.or(
            ExpectedConditions.elementToBeClickable(By.id("saveproduct")),
            ExpectedConditions.elementToBeClickable(By.id("saveLoanItem")),
            ExpectedConditions.elementToBeClickable(By.id("saveInsurance"))
        ));
    }

    public void clickEditBtnLoanDetails() throws InterruptedException {
        WebElement editBtn = wait.until(ExpectedConditions.elementToBeClickable(By.cssSelector("a.editBtn")));
        jse.executeScript("arguments[0].scrollIntoView({behavior:'smooth', block:'center'})", editBtn);
        jse.executeScript("arguments[0].click()", editBtn);
        wait.until(ExpectedConditions.elementToBeClickable(By.id("saveproduct")));
    }

    // ========== Loan Item Details ==========
    public void clickLoanItemTab() throws InterruptedException {
        WebElement tab = wait.until(ExpectedConditions.elementToBeClickable(loanItemTab));
        jse.executeScript("arguments[0].scrollIntoView({behavior:'smooth', block:'center'})", tab);
        jse.executeScript("arguments[0].click()", tab);
        wait.until(ExpectedConditions.frameToBeAvailableAndSwitchToIt(loanItemFrame));
        driver.switchTo().defaultContent();
    }

    public void switchToLoanItemFrame() {
        wait.until(ExpectedConditions.frameToBeAvailableAndSwitchToIt(loanItemFrame));
    }

    public String getCurrentLoanAmount() {
        return reqLoanAmtHidden.getAttribute("value");
    }

    public String updateLoanAmount() throws InterruptedException {
        String currentVal = reqLoanAmtHidden.getAttribute("value");
        double current = Double.parseDouble(currentVal);
        double updated = current - 5000000;
        String newAmt = String.valueOf((long) updated);
        jse.executeScript("$('#reqLoanAmt_txt').val('" + newAmt + "').blur()");
        jse.executeScript("$('#reqLoanAmt').val('" + newAmt + "')");
        Thread.sleep(300);
        return newAmt;
    }

    public void enterRemarks(String value) {
        remarks.clear();
        remarks.sendKeys(value);
    }

    public void clickSaveLoanItem() throws InterruptedException {
        jse.executeScript("arguments[0].scrollIntoView({behavior:'smooth', block:'center'})", saveLoanItemBtn);
        jse.executeScript("arguments[0].click()", saveLoanItemBtn);
        try { driver.switchTo().alert().accept(); } catch (Exception e) {}
        WebElement editBtn = wait.until(ExpectedConditions.elementToBeClickable(By.cssSelector("a.editBtn")));
        jse.executeScript("arguments[0].scrollIntoView({behavior:'smooth', block:'center'})", editBtn);
        jse.executeScript("window.scrollTo(0, 0)");
        driver.switchTo().defaultContent();
        wait.until(ExpectedConditions.elementToBeClickable(loanDetailsTab));
    }

    // ========== Loan Details ==========
    public void clickLoanDetailsTab() throws InterruptedException {
        WebElement tab = wait.until(ExpectedConditions.elementToBeClickable(loanDetailsTab));
        jse.executeScript("arguments[0].scrollIntoView({behavior:'smooth', block:'center'})", tab);
        jse.executeScript("arguments[0].click()", tab);
        wait.until(ExpectedConditions.frameToBeAvailableAndSwitchToIt(loanDetailsFrame));
        driver.switchTo().defaultContent();
    }

    public void switchToLoanDetailsFrame() {
        driver.switchTo().defaultContent();
        wait.until(ExpectedConditions.frameToBeAvailableAndSwitchToIt(loanDetailsFrame));
    }

    public void fillPurpose() throws InterruptedException {
        WebElement sel = wait.until(ExpectedConditions.presenceOfElementLocated(By.id("financingPurpose")));
        Select select = new Select(sel);
        if (select.getFirstSelectedOption().getAttribute("value").trim().isEmpty()) {
            select.selectByValue("3"); // Urgent Needs
            Thread.sleep(300);
        }
    }

    public void clickSaveLoanDetails() throws InterruptedException {
        jse.executeScript("arguments[0].scrollIntoView({behavior:'smooth', block:'center'})", saveLoanDetailsBtn);
        jse.executeScript("arguments[0].click()", saveLoanDetailsBtn);
        try { driver.switchTo().alert().accept(); } catch (Exception e) {}
        wait.until(ExpectedConditions.elementToBeClickable(By.cssSelector("a.editBtn")));
        jse.executeScript("arguments[0].scrollIntoView({behavior:'smooth', block:'center'})",
            driver.findElement(By.cssSelector("a.editBtn")));
        Thread.sleep(1500);
    }

    // ========== Repayment Schedule ==========
    public void clickRepaymentSchedule() throws InterruptedException {
        wait.until(ExpectedConditions.elementToBeClickable(repaymentBtn));
        jse.executeScript("arguments[0].scrollIntoView({behavior:'smooth', block:'center'})", repaymentBtn);
        jse.executeScript("arguments[0].click()", repaymentBtn);
        // Dismiss any validation alert (e.g. "Purpose is required")
        try {
            wait.until(ExpectedConditions.alertIsPresent());
            driver.switchTo().alert().accept();
            throw new RuntimeException("Repayment blocked by alert - check mandatory fields");
        } catch (org.openqa.selenium.TimeoutException e) {}
        wait.until(ExpectedConditions.visibilityOfElementLocated(By.id("tblData")));
    }

    public String getRepaymentEMI() {
        try { return driver.findElement(By.xpath("//table[@id='tblData']/tbody/tr[1]/td[3]")).getText().trim(); }
        catch (Exception e) { return ""; }
    }

    public String getTotalPayment() {
        try { return driver.findElement(By.xpath("//table[@id='tblData']/tbody/tr[last()]/td[2]/b")).getText().trim(); }
        catch (Exception e) { return ""; }
    }

    public String getTotalPrincipal() {
        try { return driver.findElement(By.xpath("//table[@id='tblData']/tbody/tr[last()]/td[4]/b")).getText().trim(); }
        catch (Exception e) { return ""; }
    }

    public String getTotalInterest() {
        try { return driver.findElement(By.xpath("//table[@id='tblData']/tbody/tr[last()]/td[5]/b")).getText().trim(); }
        catch (Exception e) { return ""; }
    }

    public int getNumberOfInstallments() {
        try { return driver.findElements(By.xpath("//table[@id='tblData']/tbody/tr")).size() - 1; }
        catch (Exception e) { return 0; }
    }

    public String getLastInstallmentDate() {
        try {
            int totalRows = driver.findElements(By.xpath("//table[@id='tblData']/tbody/tr")).size();
            return driver.findElement(By.xpath("//table[@id='tblData']/tbody/tr[" + (totalRows - 1) + "]/td[2]")).getText().trim();
        } catch (Exception e) { return ""; }
    }

    // ========== Insurance Details ==========
    public void clickInsuranceTab() throws InterruptedException {
        WebElement tab = wait.until(ExpectedConditions.elementToBeClickable(insuranceTab));
        jse.executeScript("arguments[0].scrollIntoView({behavior:'smooth', block:'center'})", tab);
        jse.executeScript("arguments[0].click()", tab);
        wait.until(ExpectedConditions.frameToBeAvailableAndSwitchToIt(insuranceFrame));
        driver.switchTo().defaultContent();
    }

    public void switchToInsuranceFrame() {
        wait.until(ExpectedConditions.frameToBeAvailableAndSwitchToIt(insuranceFrame));
    }

    public void fillInsuranceForm(String remarksText) throws InterruptedException {
        String policyNo = String.valueOf((long)(Math.random() * 9000000000L) + 1000000000L);
        policyNumber.clear();
        policyNumber.sendKeys(policyNo);
        jse.executeScript("$('#insuranceYear').val('1').trigger('change')");
        Thread.sleep(300);
        WebElement remarksField = driver.findElement(By.id("remarks"));
        remarksField.clear();
        remarksField.sendKeys(remarksText);
    }

    public void clickSaveInsurance() throws InterruptedException {
        jse.executeScript("arguments[0].scrollIntoView({behavior:'smooth', block:'center'})", saveInsuranceBtn);
        jse.executeScript("arguments[0].click()", saveInsuranceBtn);
        handleConfirmAndAlert();
    }

    // ========== Utility ==========
    private void handleConfirmAndAlert() throws InterruptedException {
        try {
            new WebDriverWait(driver, Duration.ofSeconds(5))
                .until(ExpectedConditions.elementToBeClickable(By.id("submitForm")));
            jse.executeScript("arguments[0].click()", driver.findElement(By.id("submitForm")));
        } catch (Exception e) {}
        try { driver.switchTo().alert().accept(); } catch (Exception e) {}
    }
}
