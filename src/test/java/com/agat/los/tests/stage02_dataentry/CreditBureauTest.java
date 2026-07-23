package com.agat.los.tests.stage02_dataentry;

import org.openqa.selenium.By;
import org.openqa.selenium.interactions.Actions;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Listeners;
import org.testng.annotations.Test;

import com.agat.los.base.BaseTest;
import com.agat.los.config.ConfigManager;
import com.agat.los.listeners.TestListener;
import com.agat.los.pages.CreditBureauPage;
import com.agat.los.reporting.ExtentManager;

@Listeners(TestListener.class)
public class CreditBureauTest extends BaseTest {

    private CreditBureauPage cbPage;

    @BeforeClass
    public void setup() throws Exception {
        driver = com.agat.los.driver.DriverManager.getDriver();
        jse = (org.openqa.selenium.JavascriptExecutor) driver;
        act = new Actions(driver);
        ExtentManager.initReport("CreditBureau_DDE");
        ExtentManager.startTest("Stage 2 - Credit Bureau Exposure");
        logInfo("Stage", "Current Stage", "Credit Bureau Exposure");
        cbPage = new CreditBureauPage(driver);
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

        // Click Income & Expenses tab (Credit Bureau is under it)
        var incExpTab = driver.findElement(By.xpath("//a[contains(@href,'activeTab=INCOME AND EXPENSES')]"));
        jse.executeScript("arguments[0].scrollIntoView({behavior:'smooth', block:'center'})", incExpTab);
        Thread.sleep(1000);
        jse.executeScript("arguments[0].click()", incExpTab);
        Thread.sleep(3000);

        log("Navigation", "Navigate to Income & Expenses", "Done", "Done", true);
        sa.assertAll();
    }

    @Test(priority = 1)
    public void fillCreditBureauDetails() throws Exception {
        cbPage.clickCreditBureauTab();
        log("CreditBureau", "Click Credit Bureau tab", "Form loaded", "Clicked", true);

        cbPage.switchToCreditBureauFrame();

        cbPage.selectCustomer();
        log("CreditBureau", "Select Customer", "Main Applicant", "Done", true);

        cbPage.selectReportType();
        log("CreditBureau", "Select Report Type", "KATM Report-177", "Done", true);

        cbPage.selectKATMNo();
        log("CreditBureau", "KATM Trigger", "No", "Selected", true);

        cbPage.fillCustomerDetails("RABIYEV FARRUX RABIYEVICH", "32103895350018", "21-03-1989");
        log("CreditBureau", "Customer Details", "Name, PINFL, DOB filled", "Done", true);

        cbPage.fillAccountDetails();
        log("CreditBureau", "Account Details", "All fields filled", "Done", true);

        cbPage.clickAdd();
        log("CreditBureau", "Click Add", "Account row added", "Clicked", true);

        cbPage.clickSave();
        String toast = getSuccessToast();
        boolean isSaved = !toast.isEmpty() && !toast.toLowerCase().contains("reject") && !toast.toLowerCase().contains("error");
        log("CreditBureau", "Save", "Saved successfully", toast.isEmpty() ? "No toast" : toast, isSaved);
        sa.assertTrue(isSaved, "Credit Bureau save failed: " + toast);

        cbPage.switchToMainContent();
        sa.assertAll();
    }
}
