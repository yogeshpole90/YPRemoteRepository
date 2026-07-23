package com.agat.los.tests.stage02_dataentry;

import org.openqa.selenium.By;
import org.openqa.selenium.interactions.Actions;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Listeners;
import org.testng.annotations.Test;

import com.agat.los.base.BaseTest;
import com.agat.los.config.ConfigManager;
import com.agat.los.listeners.TestListener;
import com.agat.los.pages.IncomePage;
import com.agat.los.reporting.ExtentManager;

@Listeners(TestListener.class)
public class IncomeTest extends BaseTest {

    private IncomePage incomePage;

    @BeforeClass
    public void setup() throws Exception {
        driver = com.agat.los.driver.DriverManager.getDriver();
        jse = (org.openqa.selenium.JavascriptExecutor) driver;
        act = new Actions(driver);
        ExtentManager.initReport("Income_DDE");
        ExtentManager.startTest("Stage 2 - Income");
        logInfo("Stage", "Current Stage", "Income");
        incomePage = new IncomePage(driver);
    }

    @Test(priority = 0)
    public void navigateToApplication() throws Exception {
        driver.findElement(By.cssSelector("a.item-summary")).click();
        Thread.sleep(3000);

        String appId = ConfigManager.get("generated.appId");
        var searchBox = driver.findElement(By.cssSelector("#dt-authdata_filter input[type='search']"));
        searchBox.clear();
        searchBox.sendKeys(appId);
        Thread.sleep(2000);

        act.doubleClick(driver.findElement(By.cssSelector("#dt-authdata tbody tr:first-child td:nth-child(2)"))).build().perform();
        Thread.sleep(3000);

        var ddeLink = driver.findElement(By.xpath("//td[@class='stage-child']//a[contains(@href,'stageName=DETAILED DATA ENTRY')]"));
        jse.executeScript("arguments[0].scrollIntoView({behavior:'smooth', block:'center'})", ddeLink);
        Thread.sleep(1500);
        jse.executeScript("arguments[0].click()", ddeLink);
        Thread.sleep(3000);

        log("Navigation", "Navigate to DDE", "DDE page opened", "Done", true);
        sa.assertAll();
    }

    @Test(priority = 1)
    public void addOtherIncome() throws Exception {
        incomePage.clickIncomeExpensesTab();
        log("Income", "Click Income & Expenses tab", "Section opened", "Clicked", true);

        incomePage.clickIncomeSubTab();
        log("Income", "Click Income sub-tab", "Income form loaded", "Clicked", true);

        incomePage.switchToIncomeFrame();

        // Select Customer Type & Name to enable form
        incomePage.selectCustomer();
        log("Income", "Select Customer", "Main Applicant selected", "Done", true);

        // Select Income Type = Other Income (value='14')
        incomePage.selectIncomeType("14");
        log("Income", "Select Income Type", "Other Income", "Selected", true);

        // Enter Amount = 125000000
        incomePage.enterIncomeAmount("125000000");
        log("Income", "Enter Amount", "125,000,000", "Entered", true);

        // Click Add
        incomePage.clickAdd();
        log("Income", "Click Add", "Income row added", "Clicked", true);

        sa.assertAll();
    }

    @Test(priority = 2)
    public void saveIncome() throws Exception {
        incomePage.enterComments("Income details added by automation");
        log("Income", "Enter Comments", "Comments entered", "Done", true);

        incomePage.clickSave();
        String toast = getSuccessToast();
        boolean isSaved = !toast.isEmpty() && !toast.toLowerCase().contains("reject") && !toast.toLowerCase().contains("error");
        log("Income", "Save", "Saved successfully", toast.isEmpty() ? "No toast" : toast, isSaved);
        sa.assertTrue(isSaved, "Income save failed: " + toast);

        incomePage.switchToMainContent();
        sa.assertAll();
    }
}
