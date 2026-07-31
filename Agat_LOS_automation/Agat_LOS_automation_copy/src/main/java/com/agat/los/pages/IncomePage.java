package com.agat.los.pages;

import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

public class IncomePage {

    private WebDriver driver;
    private JavascriptExecutor jse;

    // ========== Navigation ==========
    private By incomeExpensesTab = By.xpath("//a[contains(@href,'activeTab=INCOME AND EXPENSES')]");
    private By incomeSubTab = By.xpath("//a[contains(@onclick,'viewIncomeDtsSummaryFrame')]");

    // ========== Frame ==========
    @FindBy(id = "viewIncomeDtsSummaryFrame") private WebElement incomeFrame;

    // ========== Buttons ==========
    @FindBy(id = "addIncomebt") private WebElement addBtn;
    @FindBy(id = "saveIncomeDtls") private WebElement saveBtn;

    // ========== Constructor ==========
    public IncomePage(WebDriver driver) {
        this.driver = driver;
        this.jse = (JavascriptExecutor) driver;
        PageFactory.initElements(driver, this);
    }

    // ========== Navigation ==========
    public void clickIncomeExpensesTab() throws InterruptedException {
        WebElement tab = driver.findElement(incomeExpensesTab);
        jse.executeScript("arguments[0].scrollIntoView({behavior:'smooth', block:'center'})", tab);
        Thread.sleep(1000);
        jse.executeScript("arguments[0].click()", tab);
        Thread.sleep(3000);
    }

    public void clickIncomeSubTab() throws InterruptedException {
        WebElement tab = driver.findElement(incomeSubTab);
        jse.executeScript("arguments[0].scrollIntoView({behavior:'smooth', block:'center'})", tab);
        Thread.sleep(1000);
        jse.executeScript("arguments[0].click()", tab);
        Thread.sleep(3000);
    }

    public void switchToIncomeFrame() throws InterruptedException {
        jse.executeScript("arguments[0].scrollIntoView({behavior:'smooth', block:'center'})", incomeFrame);
        Thread.sleep(1000);
        driver.switchTo().frame(incomeFrame);
        Thread.sleep(2000);
    }

    public void switchToMainContent() { driver.switchTo().defaultContent(); }

    // ========== Customer Type & Name (pre-select to enable form) ==========
    public void selectCustomer() throws InterruptedException {
        jse.executeScript("$('#customerType').val('101').trigger('change')");
        Thread.sleep(2000);
        jse.executeScript("$('#customerName').val($('#customerName option').eq(1).val()).trigger('change')");
        Thread.sleep(2000);
    }

    // ========== Income Type (Select2 - jQuery) ==========
    public void selectIncomeType(String value) throws InterruptedException {
        jse.executeScript("$('#incomeType').val('" + value + "').trigger('change')");
        Thread.sleep(1000);
    }

    // ========== Income Amount (dual input + Tab for auto-calculate) ==========
    public void enterIncomeAmount(String amount) throws InterruptedException {
        WebElement amtField = driver.findElement(By.id("incomeAmt_txt"));
        amtField.clear();
        amtField.sendKeys(amount);
        Thread.sleep(300);
        amtField.sendKeys(org.openqa.selenium.Keys.TAB);
        Thread.sleep(1000);
    }

    // ========== Add ==========
    public void clickAdd() throws InterruptedException {
        jse.executeScript("arguments[0].scrollIntoView({behavior:'smooth', block:'center'})", addBtn);
        Thread.sleep(500);
        jse.executeScript("arguments[0].click()", addBtn);
        Thread.sleep(2000);
    }

    // ========== Comments ==========
    public void enterComments(String text) throws InterruptedException {
        WebElement comments = driver.findElement(By.id("comments"));
        comments.clear();
        comments.sendKeys(text);
        Thread.sleep(300);
    }

    // ========== Save ==========
    public void clickSave() throws InterruptedException {
        jse.executeScript("arguments[0].scrollIntoView({behavior:'smooth', block:'center'})", saveBtn);
        Thread.sleep(500);
        jse.executeScript("arguments[0].click()", saveBtn);
        Thread.sleep(1000);
        handleConfirmAndAlert();
    }

    private void handleConfirmAndAlert() throws InterruptedException {
        try {
            WebElement confirmYes = driver.findElement(By.id("submitForm"));
            jse.executeScript("arguments[0].click()", confirmYes);
            Thread.sleep(2000);
        } catch (Exception e) {}
        try {
            driver.switchTo().alert().accept();
            Thread.sleep(500);
        } catch (Exception e) {}
    }
}
