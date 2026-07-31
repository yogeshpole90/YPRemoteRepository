package com.ebid.lcs.pages;

import java.time.Duration;
import java.util.List;

import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.Select;
import org.openqa.selenium.support.ui.WebDriverWait;

public class RemedialActionPage {

    private WebDriver driver;
    private WebDriverWait wait;
    private JavascriptExecutor jse;

    // ========== Navigation ==========
    @FindBy(xpath = "(//*[contains(@href,'=Remedial Action')])[1]")
    private WebElement remedialTab;

    // ========== Frame ==========
    private static final String FRAME_NAME = "caseMstListPageFrame";

    // ========== Form Fields ==========
    @FindBy(id = "actionId")
    private WebElement actionId;

    @FindBy(id = "commments")
    private WebElement comments;

    // ========== Action Buttons ==========
    @FindBy(id = "save")
    private WebElement saveBtn;

    // ========== Constructor ==========
    public RemedialActionPage(WebDriver driver) {
        this.driver = driver;
        this.wait = new WebDriverWait(driver, Duration.ofSeconds(15));
        this.jse = (JavascriptExecutor) driver;
        PageFactory.initElements(driver, this);
    }

    // ========== Navigation ==========
    public void navigateToRemedialAction() throws InterruptedException {
        jse.executeScript("arguments[0].scrollIntoView({block:'center',behavior:'smooth'})", remedialTab);
        Thread.sleep(1000);
        remedialTab.click();
        Thread.sleep(2000);
    }

    public void switchToRemedialFrame() throws InterruptedException {
        driver.switchTo().frame(FRAME_NAME);
        Thread.sleep(500);
    }

    // ========== Generic Actions ==========
    public WebElement getField(String fieldId) {
        return driver.findElement(By.id(fieldId));
    }

    public void enterText(WebElement field, String value) throws InterruptedException {
        field.clear();
        Thread.sleep(200);
        if (!value.isEmpty() && !value.equalsIgnoreCase("Empty")) {
            field.sendKeys(value);
        }
        Thread.sleep(300);
    }

    public String getValue(WebElement field) {
        return field.getAttribute("value");
    }

    public void selectDropdown(WebElement field, String visibleText) throws InterruptedException {
        Select s = new Select(field);
        s.selectByVisibleText(visibleText);
        Thread.sleep(500);
    }

    public String getSelectedText(WebElement field) {
        Select s = new Select(field);
        return s.getFirstSelectedOption().getText().trim();
    }

    // ========== Save ==========
    public void clickSave() throws InterruptedException {
        jse.executeScript("arguments[0].scrollIntoView({block:'center'})", saveBtn);
        Thread.sleep(500);
        saveBtn.click();
        Thread.sleep(2000);
    }

    public boolean isSaveBtnVisible() { return saveBtn.isDisplayed(); }
    public boolean isSaveBtnEnabled() { return saveBtn.isEnabled(); }

    // ========== View ==========
    public List<WebElement> getViewButtons() {
        return driver.findElements(By.xpath("//a[contains(text(),'View')]"));
    }

    public void clickLastView() throws InterruptedException {
        List<WebElement> btns = getViewButtons();
        if (btns.size() > 0) {
            WebElement lastBtn = btns.get(btns.size() - 1);
            jse.executeScript("arguments[0].scrollIntoView({block:'center'})", lastBtn);
            Thread.sleep(500);
            lastBtn.click();
            Thread.sleep(2000);
        }
    }

    // ========== Getters ==========
    public WebElement getActionId() { return actionId; }
    public WebElement getComments() { return comments; }
}
