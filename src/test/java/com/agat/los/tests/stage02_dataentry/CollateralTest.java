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
import com.agat.los.pages.CollateralPage;
import com.agat.los.reporting.ExtentManager;

@Listeners(TestListener.class)
public class CollateralTest extends BaseTest {

    private CollateralPage collPage;

    @BeforeClass
    public void setup() throws Exception {
        driver = com.agat.los.driver.DriverManager.getDriver();
        jse = (org.openqa.selenium.JavascriptExecutor) driver;
        act = new Actions(driver);
        ExtentManager.initReport("Collateral_DDE");
        ExtentManager.startTest("Stage 2 - Collateral Details");
        logInfo("Stage", "Current Stage", "Collateral Details");
        collPage = new CollateralPage(driver);
    }

    @Test(priority = 0)
    public void navigateToApplication() throws Exception {
        navigateToDDE();
        WebElement productTab = driver.findElement(By.xpath("//a[contains(@href,'activeTab=PRODUCT DETAILS')]"));
        jse.executeScript("arguments[0].scrollIntoView({behavior:'smooth', block:'center'})", productTab);
        Thread.sleep(500);
        jse.executeScript("arguments[0].click()", productTab);
        Thread.sleep(2000);
        log("Navigation", "Navigate to Product Details", "Done", "Done", true);
        sa.assertAll();
    }

    @Test(priority = 1, alwaysRun = true)
    public void fillCollateralDetails() throws Exception {
        collPage.clickCollateralTab();
        log("Collateral", "Click Collateral Details tab", "Form loaded", "Clicked", true);

        collPage.switchToCollateralFrame();
        log("Collateral", "Switch to frame", "Done", "Done", true);

        collPage.fillCollateralForm();
        log("Collateral", "Vehicle details filled", "Done", "Done", true);

        collPage.fillOwnerDetails();
        log("Collateral", "Owner details filled", "Done", "Done", true);

        collPage.fillValuationDetails();
        log("Collateral", "Valuation details filled", "Done", "Done", true);

        collPage.clickSave();
        String toast = getSuccessToast();
        boolean isSaved = !toast.isEmpty() && !toast.toLowerCase().contains("reject") && !toast.toLowerCase().contains("error") && !toast.toLowerCase().contains("fail");
        log("Collateral", "Save Collateral", "Saved successfully", toast.isEmpty() ? "No toast" : toast, isSaved);
        sa.assertTrue(isSaved, "Save failed or rejected: " + toast);

        collPage.switchToMainContent();
        sa.assertAll();
    }
}
