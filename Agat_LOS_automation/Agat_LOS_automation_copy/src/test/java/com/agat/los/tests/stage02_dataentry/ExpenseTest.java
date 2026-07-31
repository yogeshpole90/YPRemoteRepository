package com.agat.los.tests.stage02_dataentry;

import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.interactions.Actions;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Listeners;
import org.testng.annotations.Test;

import com.agat.los.base.BaseTest;
import com.agat.los.config.ConfigManager;
import com.agat.los.listeners.TestListener;
import com.agat.los.pages.ExpensePage;
import com.agat.los.reporting.ExtentManager;

@Listeners(TestListener.class)
public class ExpenseTest extends BaseTest {

    private ExpensePage expensePage;

    @BeforeClass
    public void setup() throws Exception {
        driver = com.agat.los.driver.DriverManager.getDriver();
        jse = (org.openqa.selenium.JavascriptExecutor) driver;
        act = new Actions(driver);
        ExtentManager.initReport("Expense_DDE");
        ExtentManager.startTest("Stage 2 - Expense");
        logInfo("Stage", "Current Stage", "Expense");
        expensePage = new ExpensePage(driver);
    }

    @Test(priority = 0)
    public void navigateToApplication() throws Exception {
        navigateToDDE();
        WebElement incExpTab = driver.findElement(By.xpath("//a[contains(@href,'activeTab=INCOME AND EXPENSES')]"));
        jse.executeScript("arguments[0].scrollIntoView({behavior:'smooth', block:'center'})", incExpTab);
        Thread.sleep(500);
        jse.executeScript("arguments[0].click()", incExpTab);
        Thread.sleep(2000);
        log("Navigation", "Navigate to Income & Expenses", "Done", "Done", true);
        sa.assertAll();
    }

    @Test(priority = 1, alwaysRun = true)
    public void addOtherExpense() throws Exception {
        expensePage.clickExpensesSubTab();
        log("Expense", "Click Expenses sub-tab", "Expenses form loaded", "Clicked", true);

        expensePage.switchToExpenseFrame();

        // Select Customer Type & Name to enable form
        expensePage.selectCustomer();
        log("Expense", "Select Customer", "Main Applicant selected", "Done", true);

        // Select Expense Type = Other Expenses (value='11')
        expensePage.selectExpenseType("11");
        log("Expense", "Select Expense Type", "Other Expenses", "Selected", true);

        // Enter Amount = 1000000
        expensePage.enterExpenseAmount("1000000");
        log("Expense", "Enter Amount", "1,000,000", "Entered", true);

        // Click Add
        expensePage.clickAdd();
        log("Expense", "Click Add", "Expense row added", "Clicked", true);

        // Save
        expensePage.clickSave();
        String toast = getSuccessToast();
        boolean isSaved = !toast.isEmpty() && !toast.toLowerCase().contains("reject") && !toast.toLowerCase().contains("error");
        log("Expense", "Save", "Saved successfully", toast.isEmpty() ? "No toast" : toast, isSaved);
        sa.assertTrue(isSaved, "Expense save failed: " + toast);

        expensePage.switchToMainContent();
        sa.assertAll();
    }
}
