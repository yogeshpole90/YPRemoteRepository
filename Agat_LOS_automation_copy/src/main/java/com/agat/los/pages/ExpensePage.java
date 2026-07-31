package com.agat.los.pages;

import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

public class ExpensePage {

    private WebDriver driver;
    private JavascriptExecutor jse;

    // ========== Navigation ==========
    private By expensesSubTab = By.xpath("//a[contains(@onclick,'viewExpenditureFrame')]");

    // ========== Frame ==========
    @FindBy(id = "viewExpenditureFrame") private WebElement expenseFrame;

    // ========== Buttons ==========
    @FindBy(id = "addDeductionBt") private WebElement addBtn;
    @FindBy(id = "saveExpenseDtls") private WebElement saveBtn;

    // ========== Constructor ==========
    public ExpensePage(WebDriver driver) {
        this.driver = driver;
        this.jse = (JavascriptExecutor) driver;
        PageFactory.initElements(driver, this);
    }

    // ========== Navigation ==========
    public void clickExpensesSubTab() throws InterruptedException {
        WebElement tab = driver.findElement(expensesSubTab);
        jse.executeScript("arguments[0].scrollIntoView({behavior:'smooth', block:'center'})", tab);
        Thread.sleep(1000);
        jse.executeScript("arguments[0].click()", tab);
        Thread.sleep(3000);
    }

    public void switchToExpenseFrame() throws InterruptedException {
        jse.executeScript("arguments[0].scrollIntoView({behavior:'smooth', block:'center'})", expenseFrame);
        Thread.sleep(1000);
        driver.switchTo().frame(expenseFrame);
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

    // ========== Expense Type (Select2 - jQuery) ==========
    public void selectExpenseType(String value) throws InterruptedException {
        jse.executeScript("$('#deductionType').val('" + value + "').trigger('change')");
        Thread.sleep(1000);
    }

    // ========== Expense Amount (sendKeys + Tab for auto-calculate) ==========
    public void enterExpenseAmount(String amount) throws InterruptedException {
        WebElement amtField = driver.findElement(By.id("deductionAmt_txt"));
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
