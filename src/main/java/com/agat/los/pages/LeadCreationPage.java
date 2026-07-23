package com.agat.los.pages;

import java.time.Duration;
import java.util.Calendar;
import java.text.SimpleDateFormat;
import java.util.Date;

import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.Keys;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.Select;
import org.openqa.selenium.support.ui.WebDriverWait;

public class LeadCreationPage {

    private WebDriver driver;
    private JavascriptExecutor jse;
    private WebDriverWait wait;
    private Actions act;

    // ========== Lead Form Locators ==========
    @FindBy(css = "a.item-preLead")
    private WebElement newLeadBtn;

    @FindBy(id = "fName")
    private WebElement firstName;

    @FindBy(id = "sName")
    private WebElement middleName;

    @FindBy(id = "famName")
    private WebElement lastName;

    @FindBy(id = "countryCode")
    private WebElement countryCode;

    @FindBy(id = "mobileNo")
    private WebElement mobileNo;

    @FindBy(id = "dob")
    private WebElement dob;

    @FindBy(id = "loanOfficer")
    private WebElement loanOfficer;

    @FindBy(id = "saveData")
    private WebElement saveBtn;

    @FindBy(id = "submitForm")
    private WebElement confirmYes;

    // ========== Conversion Form Locators ==========
    @FindBy(id = "idType")
    private WebElement idType;

    @FindBy(id = "idNumber")
    private WebElement idNumber;

    @FindBy(id = "search")
    private WebElement searchBtn;

    @FindBy(id = "applicationType")
    private WebElement applicationType;

    @FindBy(id = "product")
    private WebElement product;

    @FindBy(id = "subProduct")
    private WebElement subProduct;

    @FindBy(id = "schemeTypeMapL")
    private WebElement scheme;

    @FindBy(id = "sendOtpMobile")
    private WebElement verifyMobileBtn;

    @FindBy(id = "mobileOtp")
    private WebElement otpField;

    @FindBy(id = "mobileVerify")
    private WebElement verifyOtpBtn;

    @FindBy(id = "requiredAmount_txt")
    private WebElement requestedAmount;

    @FindBy(id = "requestedTenure")
    private WebElement requestedTenure;

    @FindBy(id = "primarySource")
    private WebElement primarySource;

    @FindBy(id = "calculateEmi1")
    private WebElement calcEmiBtn;

    @FindBy(id = "loanStartDate")
    private WebElement loanStartDate;

    @FindBy(id = "firstEmiDate")
    private WebElement firstEmiDate;

    @FindBy(id = "saveAndGenerateBtn")
    private WebElement calcEmiModalBtn;

    @FindBy(id = "consentModClsBtn")
    private WebElement closeModalBtn;

    @FindBy(id = "convertpreLeadToApp")
    private WebElement convertBtn;

    @FindBy(id = "popUpYes")
    private WebElement popUpYes;

    @FindBy(id = "warningPopYesConvertAp")
    private WebElement convertWarningYes;

    @FindBy(id = "0")
    private WebElement existingCustomerRadio;

    // ========== Lead List Locators ==========
    @FindBy(css = "a.item-preLeadList")
    private WebElement leadListBtn;

    @FindBy(css = "a.item-summary")
    private WebElement inboxBtn;

    // ========== Constructor ==========
    public LeadCreationPage(WebDriver driver) {
        this.driver = driver;
        this.jse = (JavascriptExecutor) driver;
        this.wait = new WebDriverWait(driver, Duration.ofSeconds(15));
        this.act = new Actions(driver);
        PageFactory.initElements(driver, this);
    }

    // ========== Navigation Actions ==========
    public boolean isNewLeadBtnDisplayed() { return newLeadBtn.isDisplayed(); }

    public void clickNewLead() throws InterruptedException {
        jse.executeScript("arguments[0].scrollIntoView({block:'center'})", newLeadBtn);
        Thread.sleep(500);
        newLeadBtn.click();
        Thread.sleep(2000);
    }

    public void navigateToLeadList() throws InterruptedException {
        jse.executeScript("arguments[0].scrollIntoView({block:'center'})", leadListBtn);
        Thread.sleep(500);
        leadListBtn.click();
        Thread.sleep(3000);
    }

    // ========== Lead Form Actions ==========
    public String enterFirstName(String value) throws InterruptedException {
        firstName.clear();
        firstName.sendKeys(value);
        Thread.sleep(300);
        return firstName.getAttribute("value");
    }

    public String enterMiddleName(String value) throws InterruptedException {
        middleName.clear();
        middleName.sendKeys(value);
        Thread.sleep(300);
        return middleName.getAttribute("value");
    }

    public String enterLastName(String value) throws InterruptedException {
        lastName.clear();
        lastName.sendKeys(value);
        Thread.sleep(300);
        return lastName.getAttribute("value");
    }

    public String selectCountryCode(String value) throws InterruptedException {
        jse.executeScript("arguments[0].scrollIntoView({block:'center'})", countryCode);
        Thread.sleep(300);
        new Select(countryCode).selectByValue(value);
        Thread.sleep(500);
        return new Select(countryCode).getFirstSelectedOption().getText().trim();
    }

    public String enterMobileNo(String value) throws InterruptedException {
        mobileNo.clear();
        mobileNo.sendKeys(value);
        Thread.sleep(300);
        return mobileNo.getAttribute("value");
    }

    public String generateRandomMobile() {
        return String.format("9%08d", System.currentTimeMillis() % 100000000);
    }

    public String setDob(String value) throws InterruptedException {
        jse.executeScript("arguments[0].scrollIntoView({block:'center'})", dob);
        jse.executeScript("arguments[0].value=arguments[1]", dob, value);
        Thread.sleep(300);
        return dob.getAttribute("value");
    }

    public void setLoanOfficer(String value) throws InterruptedException {
        jse.executeScript("arguments[0].scrollIntoView({block:'center'})", loanOfficer);
        Thread.sleep(300);
        jse.executeScript("$('#loanOfficer').val('" + value + "').trigger('change')");
        Thread.sleep(500);
    }

    public String getLoanOfficerText() {
        return new Select(loanOfficer).getFirstSelectedOption().getText().trim();
    }

    // ========== Save Lead ==========
    public void clickSave() throws InterruptedException {
        jse.executeScript("arguments[0].scrollIntoView({block:'center'})", saveBtn);
        Thread.sleep(500);
        saveBtn.click();
        Thread.sleep(3000);
    }

    public boolean isSaveDisplayed() { return saveBtn.isDisplayed(); }

    public void clickConfirmYes() throws InterruptedException {
        try {
            if (confirmYes.isDisplayed()) {
                confirmYes.click();
                Thread.sleep(2000);
            }
        } catch (Exception e) {}
    }

    // ========== Lead List Actions ==========
    public void searchInLeadList(String leadId) throws InterruptedException {
        WebElement searchBox = wait.until(ExpectedConditions.presenceOfElementLocated(
                By.cssSelector("input[type='search']")));
        searchBox.clear();
        searchBox.sendKeys(leadId);
        Thread.sleep(2000);
    }

    public void doubleClickLeadRow(String leadId) throws InterruptedException {
        WebElement row = wait.until(ExpectedConditions.elementToBeClickable(
                By.xpath("//table//tbody//tr[td[contains(text(),'" + leadId + "')]]/td[1]")));
        act.doubleClick(row).build().perform();
        Thread.sleep(3000);
    }

    // ========== Conversion Form Actions ==========
    public String selectIdType(String visibleText) throws InterruptedException {
        jse.executeScript("arguments[0].scrollIntoView({block:'center'})", idType);
        Thread.sleep(300);
        new Select(idType).selectByVisibleText(visibleText);
        Thread.sleep(500);
        return new Select(idType).getFirstSelectedOption().getText().trim();
    }

    public String enterIdNumber(String value) throws InterruptedException {
        idNumber.clear();
        idNumber.sendKeys(value);
        Thread.sleep(300);
        return idNumber.getAttribute("value");
    }

    public String setConversionDob(String value) throws InterruptedException {
        WebElement dobField = driver.findElement(By.id("dob"));
        jse.executeScript("arguments[0].scrollIntoView({block:'center'})", dobField);
        jse.executeScript("arguments[0].value=arguments[1]", dobField, value);
        Thread.sleep(300);
        return dobField.getAttribute("value");
    }

    /**
     * Click Search and handle both scenarios:
     * - Existing customer: dedupe popup Yes/No appears -> click Yes -> returns true
     * - New customer: alert "No record found" appears -> accept -> returns false
     */
    public boolean clickSearchAndHandleDedupe() throws InterruptedException {
        jse.executeScript("arguments[0].scrollIntoView({block:'center'})", searchBtn);
        searchBtn.click();

        // Check if dedupe popup appears (existing customer)
        try {
            WebElement popUp = wait.until(ExpectedConditions.elementToBeClickable(By.id("popUpYes")));
            jse.executeScript("arguments[0].click()", popUp);
            wait.until(ExpectedConditions.invisibilityOfElementLocated(By.cssSelector("div.blockOverlay")));
            return true;
        } catch (Exception e) {}

        // No popup = new customer, handle alert ("No record found")
        acceptAlertIfPresent();
        return false;
    }

    public void acceptAlertIfPresent() throws InterruptedException {
        try {
            driver.switchTo().alert().accept();
            Thread.sleep(1000);
        } catch (Exception e) {}
    }

    public String selectApplicationType(String value) throws InterruptedException {
        jse.executeScript("arguments[0].scrollIntoView({block:'center'})", applicationType);
        Thread.sleep(300);
        new Select(applicationType).selectByValue(value);
        Thread.sleep(500);
        return new Select(applicationType).getFirstSelectedOption().getText().trim();
    }

    public String selectProduct(String value) throws InterruptedException {
        jse.executeScript("arguments[0].scrollIntoView({block:'center'})", product);
        Thread.sleep(300);
        new Select(product).selectByValue(value);
        Thread.sleep(1000);
        return new Select(product).getFirstSelectedOption().getText().trim();
    }

    public String selectSubProduct() throws InterruptedException {
        Thread.sleep(2000);
        try {
            new Select(subProduct).selectByIndex(1);
            Thread.sleep(500);
            return new Select(subProduct).getFirstSelectedOption().getText().trim();
        } catch (Exception e) { return "NO_OPTIONS"; }
    }

    public String selectScheme() throws InterruptedException {
        Thread.sleep(2000);
        try {
            new Select(scheme).selectByIndex(1);
            Thread.sleep(500);
            return new Select(scheme).getFirstSelectedOption().getText().trim();
        } catch (Exception e) { return "NO_OPTIONS"; }
    }

    // ========== OTP Actions ==========
    public void clickVerifyMobile() throws InterruptedException {
        wait.until(ExpectedConditions.elementToBeClickable(verifyMobileBtn));
        jse.executeScript("arguments[0].scrollIntoView({behavior:'smooth', block:'center'})", verifyMobileBtn);
        jse.executeScript("arguments[0].click()", verifyMobileBtn);
        // Accept "OTP sent successfully" alert
        try {
            wait.until(ExpectedConditions.alertIsPresent());
            driver.switchTo().alert().accept();
        } catch (Exception e) {}
        // Wait for OTP input to be ready
        wait.until(ExpectedConditions.visibilityOfElementLocated(By.id("mobileOtp")));
    }

    public String enterOtp(String otp) throws InterruptedException {
        otpField.clear();
        otpField.sendKeys(otp);
        Thread.sleep(300);
        return otpField.getAttribute("value");
    }

    public void clickVerifyOtp() throws InterruptedException {
        jse.executeScript("arguments[0].click()", verifyOtpBtn);
        Thread.sleep(3000);
        acceptAlertIfPresent();
    }

    // ========== Loan Details Actions ==========
    public String enterRequestedAmount(String value) throws InterruptedException {
        jse.executeScript("arguments[0].scrollIntoView({block:'center'})", requestedAmount);
        requestedAmount.click();
        requestedAmount.clear();
        requestedAmount.sendKeys(value);
        requestedAmount.sendKeys(Keys.TAB);
        Thread.sleep(500);
        return requestedAmount.getAttribute("value");
    }

    public String enterRequestedTenure(String value) throws InterruptedException {
        requestedTenure.clear();
        requestedTenure.sendKeys(value);
        Thread.sleep(300);
        return requestedTenure.getAttribute("value");
    }

    public String selectPrimarySource(String value) throws InterruptedException {
        jse.executeScript("arguments[0].scrollIntoView({block:'center'})", primarySource);
        Thread.sleep(300);
        new Select(primarySource).selectByValue(value);
        Thread.sleep(500);
        return new Select(primarySource).getFirstSelectedOption().getText().trim();
    }

    // ========== EMI Calculator Actions ==========
    public void clickCalculateEmi() throws InterruptedException {
        jse.executeScript("arguments[0].scrollIntoView({block:'center'})", calcEmiBtn);
        Thread.sleep(500);
        calcEmiBtn.click();
        wait.until(ExpectedConditions.elementToBeClickable(By.id("requiredAmt")));
        Thread.sleep(1000);
    }

    public String setLoanStartDate(String date) throws InterruptedException {
        jse.executeScript("arguments[0].scrollIntoView({block:'center'})", loanStartDate);
        jse.executeScript("arguments[0].value=arguments[1]", loanStartDate, date);
        loanStartDate.sendKeys(Keys.TAB);
        Thread.sleep(1000);
        return loanStartDate.getAttribute("value");
    }

    public String setFirstEmiDate(String date) throws InterruptedException {
        jse.executeScript("arguments[0].value=arguments[1]", firstEmiDate, date);
        jse.executeScript("arguments[0].dispatchEvent(new Event('change'))", firstEmiDate);
        jse.executeScript("arguments[0].dispatchEvent(new Event('blur'))", firstEmiDate);
        firstEmiDate.sendKeys(Keys.TAB);
        Thread.sleep(2000);
        return firstEmiDate.getAttribute("value");
    }

    public String getTodayDate() {
        return new SimpleDateFormat("dd-MM-yyyy").format(new Date());
    }

    public String getNextMonthDate() {
        Calendar cal = Calendar.getInstance();
        cal.add(Calendar.MONTH, 1);
        return new SimpleDateFormat("dd-MM-yyyy").format(cal.getTime());
    }

    public void clickCalculateEmiModal() throws InterruptedException {
        jse.executeScript("arguments[0].scrollIntoView({block:'center'})", calcEmiModalBtn);
        Thread.sleep(500);
        jse.executeScript("arguments[0].click()", calcEmiModalBtn);
        // Wait for CBS to populate simulated details — financeAmount becomes non-empty
        wait.until(ExpectedConditions.attributeToBeNotEmpty(
                driver.findElement(By.id("financeAmount")), "value"));
        Thread.sleep(1000);
        acceptAlertIfPresent();
    }

    public String getSimulatedDetail(String fieldId) {
        try {
            return driver.findElement(By.id(fieldId)).getAttribute("value");
        } catch (Exception e) { return ""; }
    }

    public void scrollRepaymentSchedule() throws InterruptedException {
        try {
            jse.executeScript("document.querySelector('#loanSimulator-view .modal-body').scrollTop = 9999");
        } catch (Exception e) {
            jse.executeScript("window.scrollTo(0, document.body.scrollHeight)");
        }
        Thread.sleep(2000);
    }

    public void closeRepaymentModal() throws InterruptedException {
        acceptAlertIfPresent();
        try {
            jse.executeScript("arguments[0].scrollIntoView({block:'center'})", closeModalBtn);
            Thread.sleep(500);
            jse.executeScript("arguments[0].click()", closeModalBtn);
        } catch (Exception e) {}
        Thread.sleep(2000);
    }

    // ========== Existing Customer Selection ==========
    public void selectExistingCustomerRadio() throws InterruptedException {
        jse.executeScript("arguments[0].scrollIntoView({block:'center'})", existingCustomerRadio);
        Thread.sleep(500);
        jse.executeScript("arguments[0].click()", existingCustomerRadio);
        Thread.sleep(1000);
    }

    // ========== Convert to Application ==========
    public boolean isConvertBtnDisplayed() {
        jse.executeScript("arguments[0].scrollIntoView({block:'center'})", convertBtn);
        return convertBtn.isDisplayed();
    }

    public void clickConvert() throws InterruptedException {
        jse.executeScript("arguments[0].click()", convertBtn);
        // Click Yes on warning popup if appears
        try {
            wait.until(ExpectedConditions.elementToBeClickable(convertWarningYes));
            convertWarningYes.click();
        } catch (Exception e) {}
        acceptAlertIfPresent();
    }

    public boolean clickPopUpYesIfPresent() throws InterruptedException {
        try {
            if (popUpYes.isDisplayed()) {
                jse.executeScript("arguments[0].click()", popUpYes);
                Thread.sleep(3000);
                return true;
            }
        } catch (Exception e) {
            acceptAlertIfPresent();
        }
        return false;
    }

    // ========== Inbox Actions ==========
    public void searchInInbox(String appId) throws InterruptedException {
        WebElement searchBox = wait.until(ExpectedConditions.elementToBeClickable(
                By.cssSelector("#dt-authdata_filter input[type='search']")));
        searchBox.clear();
        searchBox.sendKeys(appId);
        // Wait for table to filter
        Thread.sleep(3000);
    }

    public void doubleClickInboxRow(String appId) throws InterruptedException {
        WebElement appRow = wait.until(ExpectedConditions.elementToBeClickable(
                By.xpath("//table[@id='dt-authdata']//tbody//tr[td[contains(text(),'" + appId + "')]]/td[2]")));
        act.doubleClick(appRow).build().perform();
        Thread.sleep(4000);
    }

    public void navigateToInbox() throws InterruptedException {
        wait.until(ExpectedConditions.elementToBeClickable(inboxBtn));
        inboxBtn.click();
        Thread.sleep(4000);
    }

    // ========== Utility ==========
    public String extractIdFromToast(String toast) {
        return toast.replaceAll(".*?(\\d+).*", "$1");
    }
}
