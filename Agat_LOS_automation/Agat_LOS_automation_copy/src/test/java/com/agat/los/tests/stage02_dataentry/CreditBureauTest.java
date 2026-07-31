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
