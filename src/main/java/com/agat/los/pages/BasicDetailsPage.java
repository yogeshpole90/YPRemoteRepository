package com.agat.los.pages;

import java.time.Duration;

import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.Keys;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.Select;
import org.openqa.selenium.support.ui.WebDriverWait;

public class BasicDetailsPage {

    private WebDriver driver;
    private JavascriptExecutor jse;
    private WebDriverWait wait;
    private Actions act;

    // ========== Overview Locators ==========
    private By custNameLabel = By.xpath("//label[text()='Customer Name']/following-sibling::span");
    private By idNumberLabel = By.xpath("//label[text()='ID Number']/following-sibling::span");
    private By appTypeLabel = By.xpath("//label[text()='Application Type']/following-sibling::span");
    private By stageLabel = By.xpath("//label[text()='Processing Stage']/following-sibling::span");
    private By productLabel = By.xpath("//label[text()='Product']/following-sibling::span");
    private By loanAmtLabel = By.xpath("//label[text()='Loan Amount']/following-sibling::span");
    private By loanTenureLabel = By.xpath("//label[text()='Loan Tenure']/following-sibling::span");

    // ========== App Summary Locators ==========
    private By summCustName = By.xpath("//h6[text()='Customer Details']/following-sibling::div//label[text()='Customer Name']/following-sibling::h6");
    private By summIdType = By.xpath("//label[text()='ID Type']/following-sibling::h6");
    private By summIdNumber = By.xpath("//label[text()='ID Number']/following-sibling::h6");
    private By summDob = By.xpath("//label[text()='Date Of Birth']/following-sibling::h6");
    private By summTenure = By.xpath("//label[text()='Tenure Months']/following-sibling::h6");
    private By summFinAmt = By.xpath("//label[text()='Financing Amount']/following-sibling::h6");
    private By summInstallments = By.xpath("//label[text()='Number of Installment']/following-sibling::h6");
    private By summTotalExp = By.xpath("//label[contains(text(),'Total Exposure')]/following-sibling::h6");
    private By finDetailsTitle = By.xpath("//h6[text()='Finance Details']");
    private By exposureTitle = By.xpath("//h6[text()='Exposure']");

    // ========== DDE Navigation Locators ==========
    private By ddeLink = By.xpath("//td[@class='stage-child']//a[contains(@href,'stageName=DETAILED DATA ENTRY')]");
    private By allocatedUserXpath = By.xpath("//td[@class='stage-child']//a[contains(@href,'stageName=DETAILED DATA ENTRY')]/ancestor::tr/td[5]/input");
    private By loggedInUserSpan = By.cssSelector("span.hi-user-name");
    private By kycTab = By.xpath("//nav[contains(@class,'section-nav')]//a[contains(@href,'activeTab=KYC')]");

    // ========== Basic Details Form Locators ==========
    @FindBy(id = "viewBasicDetailsFrame")
    private WebElement basicDetailsFrame;

    // ========== Constructor ==========
    public BasicDetailsPage(WebDriver driver) {
        this.driver = driver;
        this.jse = (JavascriptExecutor) driver;
        this.wait = new WebDriverWait(driver, Duration.ofSeconds(15));
        this.act = new Actions(driver);
        PageFactory.initElements(driver, this);
    }

    // ========== Overview Actions ==========
    public String getOverviewCustomerName() { return driver.findElement(custNameLabel).getText().trim(); }
    public String getOverviewIdNumber() { return driver.findElement(idNumberLabel).getText().trim(); }
    public String getOverviewAppType() { return driver.findElement(appTypeLabel).getText().trim(); }
    public String getOverviewStage() { return driver.findElement(stageLabel).getText().trim(); }
    public String getOverviewProduct() { return driver.findElement(productLabel).getText().trim(); }
    public String getOverviewLoanAmount() { return driver.findElement(loanAmtLabel).getText().trim(); }
    public String getOverviewLoanTenure() { return driver.findElement(loanTenureLabel).getText().trim(); }

    public void scrollToOverview() throws InterruptedException {
        try {
            wait.until(org.openqa.selenium.support.ui.ExpectedConditions
                .visibilityOfElementLocated(By.xpath("//label[text()='Customer Name']/following-sibling::span")));
            WebElement header = driver.findElement(By.xpath("//h5[text()='Overview']"));
            jse.executeScript("arguments[0].scrollIntoView({block:'center'})", header);
            Thread.sleep(1000);
        } catch (Exception e) {
            jse.executeScript("window.scrollTo(0,0)");
            Thread.sleep(2000);
        }
    }

    // ========== App Summary Actions ==========
    public String getSummaryCustomerName() { return driver.findElement(summCustName).getText().trim(); }
    public String getSummaryIdType() { return driver.findElement(summIdType).getText().trim(); }
    public String getSummaryIdNumber() { return driver.findElement(summIdNumber).getText().trim(); }
    public String getSummaryDob() { return driver.findElement(summDob).getText().trim(); }
    public String getSummaryTenure() { return driver.findElement(summTenure).getText().trim(); }
    public String getSummaryFinancingAmount() { return driver.findElement(summFinAmt).getText().trim(); }
    public String getSummaryInstallments() { return driver.findElement(summInstallments).getText().trim(); }

    public String getSummaryTotalExposure() throws InterruptedException {
        WebElement exp = driver.findElement(exposureTitle);
        jse.executeScript("arguments[0].scrollIntoView({block:'center'})", exp);
        Thread.sleep(500);
        return driver.findElement(summTotalExp).getText().trim();
    }

    public void scrollToFinanceDetails() throws InterruptedException {
        WebElement fin = driver.findElement(finDetailsTitle);
        jse.executeScript("arguments[0].scrollIntoView({block:'center'})", fin);
        Thread.sleep(500);
    }

    // ========== DDE Navigation ==========
    public String getAllocatedUser() { return driver.findElement(allocatedUserXpath).getAttribute("value"); }
    public String getLoggedInUser() { return driver.findElement(loggedInUserSpan).getText().trim(); }

    public void clickDDELink() throws InterruptedException {
        WebElement link = driver.findElement(ddeLink);
        jse.executeScript("arguments[0].scrollIntoView({behavior:'smooth', block:'center'})", link);
        Thread.sleep(300);
        jse.executeScript("arguments[0].click()", link);
        wait.until(org.openqa.selenium.support.ui.ExpectedConditions
            .visibilityOfElementLocated(kycTab));
    }

    public void clickKYCTab() throws InterruptedException {
        WebElement tab = driver.findElement(kycTab);
        jse.executeScript("arguments[0].scrollIntoView({behavior:'smooth', block:'center'})", tab);
        Thread.sleep(300);
        jse.executeScript("arguments[0].click()", tab);
        wait.until(org.openqa.selenium.support.ui.ExpectedConditions
            .visibilityOfElementLocated(By.id("viewBasicDetailsFrame")));
    }

    // ========== App Summary Re-navigation ==========
    public void navigateToAppFromInbox(String rawAppId) throws InterruptedException {
        String appId = cleanAppId(rawAppId);
        driver.findElement(By.cssSelector("a.item-summary")).click();
        Thread.sleep(3000);
        WebElement searchBox = driver.findElement(By.cssSelector("#dt-authdata_filter input[type='search']"));
        searchBox.clear();
        searchBox.sendKeys(appId);
        Thread.sleep(2000);
        wait.until(d -> {
            try { return d.findElement(By.cssSelector("#dt-authdata tbody tr:first-child td:nth-child(2)")).getText().contains(appId); }
            catch (Exception e) { return false; }
        });
        act.doubleClick(driver.findElement(By.cssSelector("#dt-authdata tbody tr:first-child td:nth-child(2)"))).build().perform();
        Thread.sleep(3000);
    }

    private String cleanAppId(String raw) {
        if (raw == null) return "";
        java.util.regex.Matcher m = java.util.regex.Pattern.compile("[A-Z]+-\\d+").matcher(raw);
        if (m.find()) return m.group();
        m = java.util.regex.Pattern.compile("\\d+").matcher(raw);
        if (m.find()) return m.group();
        return raw.trim();
    }

    // ========== Basic Details Form (inside iframe) ==========
    public void switchToBasicDetailsFrame() throws InterruptedException {
        driver.switchTo().frame(basicDetailsFrame);
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
        jse.executeScript("arguments[0].scrollIntoView({block:'center'})", editBtn);
        Thread.sleep(500);
        jse.executeScript("arguments[0].click()", editBtn);
        Thread.sleep(3000);
    }

    public void fillBasicDetailsForm(String gender, String dobVal, String maritalStatus, String country,
                                     String placeOfBirth, String education, String dependents,
                                     String primarySrc, String relatedParty) throws InterruptedException {
        WebElement genderField = driver.findElement(By.id("genderId"));
        jse.executeScript("arguments[0].scrollIntoView({block:'center'})", genderField);
        Thread.sleep(500);

        jse.executeScript("$('#gender').val('1').trigger('change')");
        Thread.sleep(300);
        jse.executeScript("$('#dateOfBirth').val('" + dobVal + "')");
        Thread.sleep(300);
        jse.executeScript("$('#maritalStatus').val('" + maritalStatus + "').trigger('change')");
        Thread.sleep(500);
        jse.executeScript("$('#nationality').val('01').trigger('change')");
        Thread.sleep(500);
        jse.executeScript("$('#countryofBirth').val('" + country + "').trigger('change')");
        Thread.sleep(300);
        jse.executeScript("$('#placeOfBirth').val('" + placeOfBirth + "')");
        Thread.sleep(300);
        jse.executeScript("$('#educationLevel').val('3').trigger('change')");
        Thread.sleep(300);

        WebElement depField = driver.findElement(By.id("noOfDependents"));
        depField.clear();
        depField.sendKeys(dependents);
        Thread.sleep(300);

        jse.executeScript("$('#primarySource').val('" + primarySrc + "').trigger('change')");
        Thread.sleep(300);
        jse.executeScript("$('#relatedPartyToKFICRetail').val('" + relatedParty + "').trigger('change')");
        Thread.sleep(300);
    }

    public void fillGuarantorAmount(String amount) throws InterruptedException {
        jse.executeScript("$('#guarantorAmtRetail').val('" + amount + "')");
        Thread.sleep(300);
    }

    public void clickBasicDetailsSave() throws InterruptedException {
        WebElement saveBtn = driver.findElement(By.id("basicDetailsSave"));
        jse.executeScript("arguments[0].scrollIntoView({behavior:'smooth', block:'center'})", saveBtn);
        Thread.sleep(500);
        jse.executeScript("arguments[0].click()", saveBtn);
        Thread.sleep(1000);
        handleConfirmAndAlert();
        scrollToSearchField();
    }

    // ========== Utility ==========
    private void handleConfirmAndAlert() throws InterruptedException {
        try {
            WebElement confirmYes = driver.findElement(By.id("submitForm"));
            jse.executeScript("arguments[0].click()", confirmYes);
            Thread.sleep(1000);
        } catch (Exception e) {}
        try {
            driver.switchTo().alert().accept();
            Thread.sleep(500);
        } catch (Exception e) {}
    }

    private void scrollToSearchField() throws InterruptedException {
        WebElement searchField = driver.findElement(By.cssSelector("input[placeholder='Search Keyword Here']"));
        jse.executeScript("arguments[0].scrollIntoView({behavior:'smooth', block:'center'})", searchField);
        Thread.sleep(2000);
    }
}
