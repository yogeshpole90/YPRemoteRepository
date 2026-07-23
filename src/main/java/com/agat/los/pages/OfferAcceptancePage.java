package com.agat.los.pages;

import java.time.Duration;
import java.util.List;

import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

public class OfferAcceptancePage {

    private WebDriver driver;
    private JavascriptExecutor jse;
    private WebDriverWait wait;
    private Actions act;

    private By inboxBtn             = By.cssSelector("a.item-summary");
    private By inboxRows            = By.cssSelector("#dt-authdata tbody tr");
    private By offerAcceptanceLink  = By.xpath("//td[@class='stage-child']//a[contains(@href,'stageName=OFFER ACCEPTANCE')]");
    private By offerDetailsTab      = By.xpath("//a[contains(@href,'activeTab=OFFER DETAILS')]");
    private By offerDetailsFrame    = By.id("viewOfferDetailsFrame");
    private By editBtn              = By.cssSelector("a.Editbtn");
    private By acceptRadio          = By.id("offerStatus1");
    private By calculateBtn         = By.id("calculate");
    private By closeRepaymentBtn    = By.cssSelector("[data-dismiss='modal']");
    private By installmentField     = By.id("installment");
    private By financingAmountField = By.id("financingAmount");
    private By totalInterestField   = By.id("totalInterest");
    private By irrField             = By.id("irr");
    private By loanAmountRequested  = By.id("loanAmountRequested");
    private By requestedTenure      = By.id("requestedTenure");
    private By effectiveInterestRate= By.id("effectiveInterestRate");
    private By recommendedAmount    = By.id("recommendedAmount");
    private By loanTenure           = By.id("loanTenure");
    private By offsetRate           = By.id("offsetRate");
    private By finalROI             = By.id("finalROI");
    private By offerAmount          = By.id("offerAmount");
    private By offerTenure          = By.id("offerTenure");
    private By commentsField        = By.id("comments");
    private By saveBtn              = By.id("saveData");
    private By nextBtn              = By.cssSelector("#Next a");
    private By remarkField          = By.id("remark");
    private By remarkSubmitBtn      = By.id("remarkSubmit");

    public OfferAcceptancePage(WebDriver driver) {
        this.driver = driver;
        this.jse = (JavascriptExecutor) driver;
        this.wait = new WebDriverWait(driver, Duration.ofSeconds(15));
        this.act = new Actions(driver);
        PageFactory.initElements(driver, this);
    }

    // ========== Inbox Navigation ==========
    public void navigateToAppFromInbox(String appId) throws InterruptedException {
        driver.findElement(inboxBtn).click();
        Thread.sleep(2000);
        WebElement searchBox = wait.until(ExpectedConditions.elementToBeClickable(
            By.cssSelector("#dt-authdata_filter input[type='search']")));
        searchBox.clear();
        searchBox.sendKeys(appId);
        WebElement row = wait.until(ExpectedConditions.elementToBeClickable(
            By.xpath("//table[@id='dt-authdata']//tbody//tr[td[contains(text(),'" + appId + "')]]/td[1]")));
        act.doubleClick(row).build().perform();
        Thread.sleep(2000);
    }

    public void navigateToAppFromInbox() throws InterruptedException {
        driver.findElement(inboxBtn).click();
        Thread.sleep(2000);
        List<WebElement> rows = wait.until(ExpectedConditions.presenceOfAllElementsLocatedBy(inboxRows));
        for (WebElement row : rows) {
            List<WebElement> cells = row.findElements(By.tagName("td"));
            if (cells.size() >= 9 && cells.get(8).getText().trim().equals("60")) {
                act.doubleClick(cells.get(0)).build().perform();
                Thread.sleep(2000);
                return;
            }
        }
        act.doubleClick(driver.findElement(By.cssSelector("#dt-authdata tbody tr:first-child td:first-child"))).build().perform();
        Thread.sleep(2000);
    }

    // ========== Stage Navigation ==========
    public void clickOfferAcceptanceStageLink() throws InterruptedException {
        WebElement link = wait.until(ExpectedConditions.elementToBeClickable(offerAcceptanceLink));
        jse.executeScript("arguments[0].scrollIntoView({behavior:'smooth', block:'center'})", link);
        jse.executeScript("arguments[0].click()", link);
        wait.until(ExpectedConditions.elementToBeClickable(offerDetailsTab));
    }

    public void clickOfferDetailsTab() throws InterruptedException {
        WebElement tab = wait.until(ExpectedConditions.elementToBeClickable(offerDetailsTab));
        jse.executeScript("arguments[0].scrollIntoView({behavior:'smooth', block:'center'})", tab);
        jse.executeScript("arguments[0].click()", tab);
        wait.until(ExpectedConditions.frameToBeAvailableAndSwitchToIt(offerDetailsFrame));
        driver.switchTo().defaultContent();
    }

    public void switchToOfferDetailsFrame() {
        wait.until(ExpectedConditions.frameToBeAvailableAndSwitchToIt(offerDetailsFrame));
    }

    public void switchToMainContent() { driver.switchTo().defaultContent(); }

    // ========== Edit Button - scroll down after click ==========
    public void clickEditBtn() throws InterruptedException {
        WebElement edit = wait.until(ExpectedConditions.elementToBeClickable(editBtn));
        jse.executeScript("arguments[0].scrollIntoView({behavior:'smooth', block:'center'})", edit);
        jse.executeScript("arguments[0].click()", edit);
        // Scroll down to validate fields after edit
        WebElement acceptEl = wait.until(ExpectedConditions.elementToBeClickable(acceptRadio));
        jse.executeScript("arguments[0].scrollIntoView({behavior:'smooth', block:'center'})", acceptEl);
    }

    // ========== Field Getters ==========
    public String getLoanAmountRequested() {
        try { return driver.findElement(loanAmountRequested).getAttribute("value").trim(); } catch (Exception e) { return ""; }
    }
    public String getRequestedTenure() {
        try { return driver.findElement(requestedTenure).getAttribute("value").trim(); } catch (Exception e) { return ""; }
    }
    public String getEffectiveInterestRate() {
        try { return driver.findElement(effectiveInterestRate).getAttribute("value").trim(); } catch (Exception e) { return ""; }
    }
    public String getRecommendedAmount() {
        try { return driver.findElement(recommendedAmount).getAttribute("value").trim(); } catch (Exception e) { return ""; }
    }
    public String getLoanTenure() {
        try { return driver.findElement(loanTenure).getAttribute("value").trim(); } catch (Exception e) { return ""; }
    }
    public String getOffsetRate() {
        try { return driver.findElement(offsetRate).getAttribute("value").trim(); } catch (Exception e) { return ""; }
    }
    public String getFinalROI() {
        try { return driver.findElement(finalROI).getAttribute("value").trim(); } catch (Exception e) { return ""; }
    }
    public String getOfferAmount() {
        try { return driver.findElement(offerAmount).getAttribute("value").trim(); } catch (Exception e) { return ""; }
    }
    public String getOfferTenure() {
        try { return driver.findElement(offerTenure).getAttribute("value").trim(); } catch (Exception e) { return ""; }
    }

    // ========== Accept + Calculate ==========
    public String updateOfferAmount() throws InterruptedException {
        WebElement field = wait.until(ExpectedConditions.visibilityOfElementLocated(offerAmount));
        String currentVal = field.getAttribute("value").replaceAll("[^0-9.]", "");
        double current = Double.parseDouble(currentVal.isEmpty() ? "0" : currentVal);
        long newAmt = (long)(current - 5000000);
        jse.executeScript("arguments[0].value=''", field);
        field.clear();
        field.sendKeys(String.valueOf(newAmt));
        jse.executeScript("arguments[0].dispatchEvent(new Event('change'))", field);
        jse.executeScript("arguments[0].dispatchEvent(new Event('blur'))", field);
        Thread.sleep(300);
        return String.valueOf(newAmt);
    }

    public void clickAccept() throws InterruptedException {
        WebElement radio = wait.until(ExpectedConditions.elementToBeClickable(acceptRadio));
        jse.executeScript("arguments[0].scrollIntoView({behavior:'smooth', block:'center'})", radio);
        jse.executeScript("arguments[0].click()", radio);
    }

    public void clickCalculate() throws InterruptedException {
        WebElement btn = wait.until(ExpectedConditions.elementToBeClickable(calculateBtn));
        jse.executeScript("arguments[0].scrollIntoView({behavior:'smooth', block:'center'})", btn);
        jse.executeScript("arguments[0].click()", btn);
        wait.until(ExpectedConditions.attributeToBeNotEmpty(driver.findElement(installmentField), "value"));
    }

    public void closeRepaymentSchedule() throws InterruptedException {
        WebElement btn = wait.until(ExpectedConditions.elementToBeClickable(closeRepaymentBtn));
        jse.executeScript("arguments[0].click()", btn);
        wait.until(ExpectedConditions.invisibilityOfElementLocated(closeRepaymentBtn));
    }

    // ========== Calculated Fields ==========
    public String getInstallment()      { return driver.findElement(installmentField).getAttribute("value").trim(); }
    public String getFinancingAmount()  { return driver.findElement(financingAmountField).getAttribute("value").trim(); }
    public String getTotalInterest()    { return driver.findElement(totalInterestField).getAttribute("value").trim(); }
    public String getIRR()              { return driver.findElement(irrField).getAttribute("value").trim(); }

    // ========== Comments + Save ==========
    public void enterComments(String text) throws InterruptedException {
        WebElement field = wait.until(ExpectedConditions.elementToBeClickable(commentsField));
        jse.executeScript("arguments[0].scrollIntoView({behavior:'smooth', block:'center'})", field);
        field.clear();
        field.sendKeys(text);
    }

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

    // ========== Progress Bar - Loan Activation User ==========
    public String fetchLoanActivationUser() throws InterruptedException {
        driver.switchTo().defaultContent();
        jse.executeScript("window.scrollTo({top: document.body.scrollHeight, behavior: 'smooth'})");
        Thread.sleep(1000);
        WebElement userInput = wait.until(ExpectedConditions.presenceOfElementLocated(By.xpath(
            "//tbody[tr[@class='stage-header-row']//h5[normalize-space(text())='LOAN ACTIVATION']]" +
            "//tr[not(@class='stage-header-row')]//td[5]/input[@type='hidden']")));
        return userInput.getAttribute("value").trim();
    }

    // ========== Next + Remark + Submit ==========
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
