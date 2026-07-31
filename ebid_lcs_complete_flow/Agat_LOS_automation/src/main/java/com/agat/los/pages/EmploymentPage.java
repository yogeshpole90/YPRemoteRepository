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

public class EmploymentPage {

    private WebDriver driver;
    private JavascriptExecutor jse;
    private WebDriverWait wait;

    // ========== Locators ==========
    private By empTab = By.xpath("//a[contains(@onclick,'viewemploymentIdFrame')]");

    @FindBy(id = "viewemploymentIdFrame") private WebElement empFrame;
    @FindBy(id = "saveEmployment")         private WebElement saveBtn;

    // ========== Constructor ==========
    public EmploymentPage(WebDriver driver) {
        this.driver = driver;
        this.jse = (JavascriptExecutor) driver;
        this.wait = new WebDriverWait(driver, Duration.ofSeconds(15));
        PageFactory.initElements(driver, this);
    }

    // ========== Navigation ==========
    public void clickEmploymentTab() throws InterruptedException {
        WebElement tab = wait.until(ExpectedConditions.elementToBeClickable(empTab));
        jse.executeScript("arguments[0].scrollIntoView(true)", tab);
        jse.executeScript("arguments[0].click()", tab);
        wait.until(ExpectedConditions.frameToBeAvailableAndSwitchToIt(empFrame));
        driver.switchTo().defaultContent();
    }

    public void switchToEmploymentFrame() {
        wait.until(ExpectedConditions.frameToBeAvailableAndSwitchToIt(empFrame));
    }

    public void switchToMainContent() { driver.switchTo().defaultContent(); }

    public boolean hasExistingRecord() {
        try { return !driver.findElements(By.cssSelector("a.editBtn")).isEmpty(); }
        catch (Exception e) { return false; }
    }

    public void clickEditOrAddBtn() throws InterruptedException {
        Thread.sleep(1500);
        if (hasExistingRecord()) {
            WebElement editBtn = wait.until(ExpectedConditions.elementToBeClickable(By.cssSelector("a.editBtn")));
            jse.executeScript("arguments[0].click()", editBtn);
        }
        // CREATE mode — form already visible, no button needed
        wait.until(ExpectedConditions.elementToBeClickable(saveBtn));
    }

    // ========== Select2 helper ==========
    private void selectByValue(String selectId, String value) throws InterruptedException {
        WebElement select = driver.findElement(By.id(selectId));
        jse.executeScript("arguments[0].scrollIntoView(true)", select);
        new Select(select).selectByValue(value);
        // trigger change event for select2
        jse.executeScript("$('#" + selectId + "').trigger('change')");
        Thread.sleep(500);
    }

    private void fillInput(String inputId, String value) throws InterruptedException {
        WebElement el = driver.findElement(By.id(inputId));
        jse.executeScript("arguments[0].scrollIntoView(true)", el);
        el.clear();
        el.sendKeys(value);
    }

    // ========== Fill Form (Salaried) ==========
    public void fillEmploymentForm(String phone, String web, String emailVal, String remarksVal) throws InterruptedException {
        // Customer Type = Main Applicant (101)
        selectByValue("customerType", "101");
        Thread.sleep(500);

        // Customer Name = first available option (auto-selected)
        jse.executeScript("$('#customerName option:eq(1)').prop('selected', true).trigger('change')");
        Thread.sleep(500);

        // Nature Of Employment = Salaried (1)
        selectByValue("natureOfEmplmt", "1");
        Thread.sleep(1000); // salaried fields appear after this

        // Name Of Employer
        fillInput("nameofEmployer", "Test Employer Ltd");

        // Employer Id Number
        fillInput("employerIdNumber", "EMP123456");

        // Position
        fillInput("position", "Engineer");

        // Employment Type = Permanent (1)
        selectByValue("employmentType", "1");

        // Sector = value 1
        selectByValue("sector", "1");

        // Employer Address - use JS to avoid validation alert
        WebElement addrEl = driver.findElement(By.id("employerAddress"));
        jse.executeScript("arguments[0].scrollIntoView(true)", addrEl);
        jse.executeScript("arguments[0].value = arguments[1]", addrEl, "123 Test Street");
        try { driver.switchTo().alert().accept(); } catch (Exception e) {}
        Thread.sleep(300);

        // Phone Number
        fillInput("phoneNumber", phone);

        // Website
        fillInput("website", web);

        // Email
        fillInput("email", emailVal);

        // Remarks - use JS to avoid validation alert
        WebElement remarksEl = driver.findElement(By.id("remarks"));
        jse.executeScript("arguments[0].scrollIntoView(true)", remarksEl);
        jse.executeScript("arguments[0].value = arguments[1]", remarksEl, remarksVal);
        try { driver.switchTo().alert().accept(); } catch (Exception e) {}
    }

    // ========== Save ==========
    public void clickSave() throws InterruptedException {
        jse.executeScript("arguments[0].scrollIntoView(true)", saveBtn);
        jse.executeScript("arguments[0].click()", saveBtn);
        try {
            WebElement confirmYes = wait.until(ExpectedConditions.elementToBeClickable(By.id("submitForm")));
            jse.executeScript("arguments[0].click()", confirmYes);
        } catch (Exception e) {}
        try { driver.switchTo().alert().accept(); } catch (Exception e) {}
        Thread.sleep(2000);
        driver.switchTo().defaultContent();
    }
}
