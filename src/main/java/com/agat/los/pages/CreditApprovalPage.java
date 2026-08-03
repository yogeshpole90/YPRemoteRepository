package com.agat.los.pages;

import java.time.Duration;

import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

public class CreditApprovalPage {

    private WebDriver driver;
    private JavascriptExecutor jse;
    private WebDriverWait wait;
    private Actions act;

    private By inboxBtn    = By.cssSelector("a.item-summary");
    private By inboxSearch  = By.cssSelector("#dt-authdata_filter input[type='search']");
    private By inboxFirstRow = By.cssSelector("#dt-authdata tbody tr:first-child td:first-child");

    // ========== Overview Locators ==========
    private By customerName = By.xpath("//label[text()='Customer Name']/following-sibling::span");
    private By idNumber = By.xpath("//label[text()='ID Number']/following-sibling::span");
    private By appNumber = By.xpath("//label[text()='Application Number']/following-sibling::span");
    private By processingStage = By.xpath("//label[text()='Processing Stage']/following-sibling::span");
    private By product = By.xpath("//label[text()='Product']/following-sibling::span");
    private By loanAmount = By.xpath("//label[text()='Loan Amount']/following-sibling::span");
    private By loanTenure = By.xpath("//label[text()='Loan Tenure']/following-sibling::span");
    private By approvedAmount = By.xpath("//label[text()='Approved Amount']/following-sibling::span");
    private By approvedTenure = By.xpath("//label[text()='Approved Tenure']/following-sibling::span");
    private By katmStatus = By.xpath("//label[text()='KATM Report Status']/following-sibling::h6");
    private By blacklistFound = By.xpath("//label[text()='BlackList:Found']/following-sibling::span");

    // ========== Progress Bar Locators ==========
    private By overallCompleted = By.cssSelector(".infoBox h1.blue-title");
    private By creditApprovalProgress = By.xpath("//span[@class='title' and text()='CREDIT APPROVAL']/preceding-sibling::span[@class='count']");

    // ========== Stage Link Locator ==========
    private By creditApprovalLink = By.xpath("//td[@class='stage-child']//a[contains(@href,'stageName=CREDIT APPROVAL')]");
    private By creditApprovalAllocatedUser = By.xpath("//td[@class='stage-child']//a[contains(@href,'stageName=CREDIT APPROVAL')]/ancestor::tr/td[5]/input");

    // ========== Recommendation Tab ==========
    private By recommendationTab = By.xpath("//a[contains(@href,'activeTab=RECOMMENDATION')]");

    // ========== Recommendation iframe ==========
    private By recommendationFrame = By.id("viewRecomendationFrame");

    // ========== Recommendation - Product Details Grid ==========
    private By productDetailsEditBtn = By.cssSelector("a.editBtn");

    // ========== Recommendation - Update Section ==========
    private By calculateBtn = By.id("details");
    private By closeRepaymentModal = By.id("closeRepaymentID");
    private By commentsField = By.id("comments");
    private By installmentField = By.id("installment_txt");
    private By financingAmountField = By.id("financingAmount_txt");
    private By totalInterestField = By.id("totalInterest_txt");
    private By irrField = By.id("irr_txt");
    private By saveBtn = By.id("saveFinan");

    // ========== Constructor ==========
    public CreditApprovalPage(WebDriver driver) {
        this.driver = driver;
        this.jse = (JavascriptExecutor) driver;
        this.wait = new WebDriverWait(driver, Duration.ofSeconds(15));
        this.act = new Actions(driver);
        PageFactory.initElements(driver, this);
    }

    // ========== App Summary Actions ==========
    public void navigateToAppFromInbox(String rawAppId) throws InterruptedException {
        String appId = cleanAppId(rawAppId);
        driver.findElement(inboxBtn).click();
        Thread.sleep(5000);
        WebElement searchBox = driver.findElement(inboxSearch);
        searchBox.clear();
        searchBox.sendKeys(appId);
        Thread.sleep(3000);
        act.doubleClick(driver.findElement(By.cssSelector("#dt-authdata tbody tr:first-child td:first-child"))).build().perform();
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

    // ========== Overview Getters ==========
    public String getCustomerName() { return driver.findElement(customerName).getText().trim(); }
    public String getIdNumber() { return driver.findElement(idNumber).getText().trim(); }
    public String getAppNumber() { return driver.findElement(appNumber).getText().trim(); }
    public String getProcessingStage() { return driver.findElement(processingStage).getText().trim(); }
    public String getProduct() { return driver.findElement(product).getText().trim(); }
    public String getLoanAmount() { return driver.findElement(loanAmount).getText().trim(); }
    public String getLoanTenure() { return driver.findElement(loanTenure).getText().trim(); }
    public String getApprovedAmount() { return driver.findElement(approvedAmount).getText().trim(); }
    public String getApprovedTenure() { return driver.findElement(approvedTenure).getText().trim(); }
    public String getKatmStatus() { return driver.findElement(katmStatus).getText().trim(); }
    public String getBlacklistFound() { return driver.findElement(blacklistFound).getText().trim(); }

    // ========== Progress Bar ==========
    public String getOverallCompleted() { return driver.findElement(overallCompleted).getText().trim(); }
    public String getCreditApprovalProgress() { return driver.findElement(creditApprovalProgress).getText().trim(); }

    // ========== Stage Navigation ==========
    public String getAllocatedUser() {
        return driver.findElement(creditApprovalAllocatedUser).getAttribute("value");
    }

    public void clickCreditApprovalStageLink() throws InterruptedException {
        WebElement link = wait.until(ExpectedConditions.elementToBeClickable(creditApprovalLink));
        jse.executeScript("arguments[0].scrollIntoView({behavior:'smooth', block:'center'})", link);
        jse.executeScript("arguments[0].click()", link);
        wait.until(ExpectedConditions.elementToBeClickable(recommendationTab));
    }

    public void clickRecommendationTab() throws InterruptedException {
        WebElement tab = wait.until(ExpectedConditions.elementToBeClickable(recommendationTab));
        jse.executeScript("arguments[0].scrollIntoView({behavior:'smooth', block:'center'})", tab);
        jse.executeScript("arguments[0].click()", tab);
        wait.until(ExpectedConditions.frameToBeAvailableAndSwitchToIt(recommendationFrame));
        driver.switchTo().defaultContent();
    }

    public void switchToRecommendationFrame() {
        wait.until(ExpectedConditions.frameToBeAvailableAndSwitchToIt(recommendationFrame));
    }

    public void switchToMainContent() {
        driver.switchTo().defaultContent();
    }

    // ========== Recommendation - Product Details ==========
    public void clickProductDetailsEdit() throws InterruptedException {
        wait.until(d -> {
            try { return d.findElement(productDetailsEditBtn).isDisplayed(); }
            catch (Exception e) { return false; }
        });
        WebElement editBtn = driver.findElement(productDetailsEditBtn);
        jse.executeScript("arguments[0].scrollIntoView({block:'center'})", editBtn);
        Thread.sleep(500);
        jse.executeScript("arguments[0].click()", editBtn);
        Thread.sleep(3000);
    }

    public String updateLoanAmount() throws InterruptedException {
        WebElement amtField = wait.until(ExpectedConditions.visibilityOfElementLocated(By.id("financingAmount_txt")));
        String currentVal = amtField.getAttribute("value").replaceAll("[^0-9.]", "");
        double current = Double.parseDouble(currentVal.isEmpty() ? "0" : currentVal);
        long newAmt = (long)(current - 5000000);
        jse.executeScript("$('#financingAmount_txt').val('" + newAmt + "').trigger('change').trigger('blur')");
        jse.executeScript("$('#financingAmount').val('" + newAmt + "')");
        Thread.sleep(300);
        return String.valueOf(newAmt);
    }

    // ========== Recommendation - Calculate & Close ==========
    public void clickCalculate() throws InterruptedException {
        WebElement btn = wait.until(ExpectedConditions.elementToBeClickable(calculateBtn));
        jse.executeScript("arguments[0].scrollIntoView({behavior:'smooth', block:'center'})", btn);
        jse.executeScript("arguments[0].click()", btn);
        wait.until(ExpectedConditions.attributeToBeNotEmpty(driver.findElement(installmentField), "value"));
    }

    public void closeRepaymentSchedule() throws InterruptedException {
        WebElement closeBtn = wait.until(ExpectedConditions.elementToBeClickable(closeRepaymentModal));
        jse.executeScript("arguments[0].click()", closeBtn);
        Thread.sleep(2000);
        wait.until(d -> {
            try {
                WebElement el = d.findElement(closeRepaymentModal);
                String display = el.getCssValue("display");
                return display.equals("none") || !el.isDisplayed();
            } catch (Exception e) { return true; }
        });
    }

    // ========== Recommendation - Comments ==========
    public void enterComments(String text) throws InterruptedException {
        try { wait.until(ExpectedConditions.frameToBeAvailableAndSwitchToIt(recommendationFrame)); } catch (Exception e) {}
        WebElement field = wait.until(ExpectedConditions.elementToBeClickable(commentsField));
        jse.executeScript("arguments[0].scrollIntoView({block:'center'})", field);
        Thread.sleep(300);
        jse.executeScript("arguments[0].value=''", field);
        field.sendKeys(text);
        Thread.sleep(300);
    }

    // ========== Recommendation - Calculated Values ==========
    public String getInstallment() { return driver.findElement(installmentField).getAttribute("value").trim(); }
    public String getFinancingAmount() { return driver.findElement(financingAmountField).getAttribute("value").trim(); }
    public String getTotalInterest() { return driver.findElement(totalInterestField).getAttribute("value").trim(); }
    public String getIRR() { return driver.findElement(irrField).getAttribute("value").trim(); }

    // ========== Recommendation - Save ==========
    public void clickSave() throws InterruptedException {
        WebElement btn = wait.until(ExpectedConditions.elementToBeClickable(saveBtn));
        jse.executeScript("arguments[0].scrollIntoView({behavior:'smooth', block:'center'})", btn);
        jse.executeScript("arguments[0].click()", btn);
        try {
            WebElement confirmYes = wait.until(ExpectedConditions.elementToBeClickable(By.id("submitForm")));
            jse.executeScript("arguments[0].click()", confirmYes);
        } catch (Exception e) {}
        try { driver.switchTo().alert().accept(); } catch (Exception e) {}
    }

    // ========== Next Button & Submit ==========
    private By nextBtn = By.cssSelector("#Next a");
    private By remarkField = By.id("remark");
    private By remarkSubmitBtn = By.id("remarkSubmit");

    public void clickNext() throws InterruptedException {
        WebElement btn = wait.until(ExpectedConditions.elementToBeClickable(nextBtn));
        jse.executeScript("arguments[0].scrollIntoView({behavior:'smooth', block:'center'})", btn);
        jse.executeScript("arguments[0].click()", btn);
        wait.until(ExpectedConditions.visibilityOfElementLocated(remarkField));
    }

    public void enterRemark(String text) {
        WebElement field = driver.findElement(remarkField);
        jse.executeScript("arguments[0].value=''", field);
        jse.executeScript("arguments[0].value=arguments[1]", field, text);
    }

    public void clickRemarkSubmit() throws InterruptedException {
        WebElement btn = wait.until(ExpectedConditions.elementToBeClickable(remarkSubmitBtn));
        jse.executeScript("arguments[0].click()", btn);
        Thread.sleep(2000);
    }

    public String getStageSuccessMessage() {
        try {
            WebElement msg = wait.until(ExpectedConditions.visibilityOfElementLocated(
                By.cssSelector("h5.blue-title")));
            return msg.getText().trim();
        } catch (Exception e) { return ""; }
    }
}
