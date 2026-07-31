package com.ebid.lcs.pages;

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

public class LawyerDetailsPage {

    private WebDriver driver;
    private WebDriverWait wait;
    private JavascriptExecutor jse;

    // ========== Navigation ==========
    @FindBy(xpath = "//*[@class='item-nav']/div")
    private WebElement hamburgerMenu;

    @FindBy(xpath = "//*[@id='LAWERDETAILSMST']/a")
    private WebElement lawyerDetailsLink;

    @FindBy(id = "addButton")
    private WebElement addBtn;

    // ========== Action Buttons ==========
    @FindBy(id = "save")
    private WebElement saveBtn;

    @FindBy(xpath = "//*[text()='Back to List'] | //*[@id='backButton']")
    private WebElement backBtn;

    // ========== Table Locators ==========
    @FindBy(css = "#dt-authdata_filter input[type='search']")
    private WebElement searchBox;

    @FindBy(css = "#dt-authdata tbody tr:first-child a.button.view")
    private WebElement viewBtn;

    @FindBy(css = "#dt-authdata tbody tr:first-child a.button.edit")
    private WebElement editBtn;

    // ========== Constructor ==========
    public LawyerDetailsPage(WebDriver driver) {
        this.driver = driver;
        this.wait = new WebDriverWait(driver, Duration.ofSeconds(15));
        this.jse = (JavascriptExecutor) driver;
        PageFactory.initElements(driver, this);
    }

    // ========== Navigation ==========
    public void navigateToLawyerDetails() throws InterruptedException {
        hamburgerMenu.click();
        Thread.sleep(500);
        jse.executeScript("arguments[0].scrollIntoView({block:'center'})", lawyerDetailsLink);
        Thread.sleep(300);
        lawyerDetailsLink.click();

        WebElement addButton = wait.until(ExpectedConditions.elementToBeClickable(addBtn));
        addButton.click();
        Thread.sleep(1000);
    }

    // ========== Generic Actions ==========
    public WebElement getField(String fieldId) {
        return driver.findElement(By.id(fieldId));
    }

    public void enterText(WebElement field, String value) throws InterruptedException {
        safeClear(field);
        if (!value.isEmpty() && !value.equalsIgnoreCase("Empty")) {
            field.sendKeys(value);
            Thread.sleep(300);
            dismissAlert();
        }
    }

    public String getValue(WebElement field) {
        return safeGetValue(field);
    }

    public void selectDropdown(WebElement field, String visibleText) throws InterruptedException {
        Select s = new Select(field);
        if (visibleText.startsWith("INDEX:")) {
            s.selectByIndex(Integer.parseInt(visibleText.replace("INDEX:", "")));
        } else {
            s.selectByVisibleText(visibleText);
        }
        Thread.sleep(500);
    }

    public String getSelectedText(WebElement field) {
        Select s = new Select(field);
        return s.getFirstSelectedOption().getText().trim();
    }

    // ========== Save ==========
    public void clickSave() throws InterruptedException {
        dismissAlert();
        jse.executeScript("window.scrollTo(0, document.body.scrollHeight)");
        Thread.sleep(500);
        jse.executeScript("arguments[0].scrollIntoView({block:'center'})", saveBtn);
        Thread.sleep(300);
        saveBtn.click();
        Thread.sleep(1500);
    }

    public boolean isSaveBtnVisible() { return saveBtn.isDisplayed(); }

    // ========== Back ==========
    public void clickBack() throws InterruptedException {
        jse.executeScript("window.scrollTo(0, document.body.scrollHeight)");
        Thread.sleep(300);
        jse.executeScript("arguments[0].scrollIntoView({block:'center'})", backBtn);
        Thread.sleep(300);
        backBtn.click();
        Thread.sleep(1500);
    }

    // ========== Search ==========
    public void searchByText(String text) throws InterruptedException {
        jse.executeScript("arguments[0].scrollIntoView({block:'center'})", searchBox);
        searchBox.clear();
        searchBox.sendKeys(text);
        Thread.sleep(1000);
    }

    // ========== View / Edit ==========
    public void clickView() throws InterruptedException {
        jse.executeScript("arguments[0].scrollIntoView({block:'center'})", viewBtn);
        Thread.sleep(300);
        viewBtn.click();
        Thread.sleep(1500);
    }

    public void clickEdit() throws InterruptedException {
        jse.executeScript("arguments[0].scrollIntoView({block:'center'})", editBtn);
        Thread.sleep(300);
        editBtn.click();
        Thread.sleep(1500);
    }

    // ========== Helpers ==========
    public void dismissAlert() {
        try { driver.switchTo().alert().accept(); } catch (Exception e) {}
    }

    private void safeClear(WebElement el) {
        try { el.clear(); } catch (Exception e) { dismissAlert(); el.clear(); }
        dismissAlert();
    }

    private String safeGetValue(WebElement el) {
        try { return el.getAttribute("value"); } catch (Exception e) { dismissAlert(); return el.getAttribute("value"); }
    }
}
