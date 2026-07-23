package com.agat.los.pages;

import java.time.Duration;

import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

public class EmploymentPage {

    private WebDriver driver;
    private JavascriptExecutor jse;
    private WebDriverWait wait;

    // ========== Locators ==========
    private By empTab = By.xpath("//a[contains(@onclick,'viewemploymentIdFrame')]");

    @FindBy(id = "viewemploymentIdFrame")
    private WebElement empFrame;

    @FindBy(id = "phoneNumber")
    private WebElement phoneNumber;

    @FindBy(id = "website")
    private WebElement website;

    @FindBy(id = "email")
    private WebElement email;

    @FindBy(id = "remarks")
    private WebElement remarks;

    @FindBy(id = "saveEmployment")
    private WebElement saveBtn;

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
        jse.executeScript("arguments[0].scrollIntoView({behavior:'smooth', block:'center'})", tab);
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

    public void clickEditBtn() throws InterruptedException {
        WebElement editBtn = wait.until(ExpectedConditions.elementToBeClickable(By.cssSelector("a.editBtn")));
        jse.executeScript("arguments[0].click()", editBtn);
        wait.until(ExpectedConditions.elementToBeClickable(saveBtn));
    }

    // ========== Form Actions ==========
    public void fillEmploymentForm(String phone, String web, String emailVal, String remarksVal) throws InterruptedException {
        phoneNumber.clear();
        phoneNumber.sendKeys(phone);
        website.clear();
        website.sendKeys(web);
        email.clear();
        email.sendKeys(emailVal);
        remarks.clear();
        remarks.sendKeys(remarksVal);
    }

    // ========== Save ==========
    public void clickSave() throws InterruptedException {
        jse.executeScript("arguments[0].scrollIntoView({behavior:'smooth', block:'center'})", saveBtn);
        jse.executeScript("arguments[0].click()", saveBtn);
        try {
            WebElement confirmYes = wait.until(ExpectedConditions.elementToBeClickable(By.id("submitForm")));
            jse.executeScript("arguments[0].click()", confirmYes);
        } catch (Exception e) {}
        try { driver.switchTo().alert().accept(); } catch (Exception e) {}
        // Scroll to editBtn in frame so toast is visible (same as BasicDetails pattern)
        WebElement editBtn = wait.until(ExpectedConditions.visibilityOfElementLocated(By.cssSelector("a.editBtn")));
        jse.executeScript("arguments[0].scrollIntoView({behavior:'smooth', block:'center'})", editBtn);
        Thread.sleep(2000);
        driver.switchTo().defaultContent();
    }
}
