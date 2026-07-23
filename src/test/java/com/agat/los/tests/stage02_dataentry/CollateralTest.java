package com.agat.los.tests.stage02_dataentry;

import org.openqa.selenium.By;
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

        // Click Product Details section (Collateral is under it)
        var productTab = driver.findElement(By.xpath("//a[contains(@href,'activeTab=PRODUCT DETAILS')]"));
        jse.executeScript("arguments[0].scrollIntoView({behavior:'smooth', block:'center'})", productTab);
        Thread.sleep(1000);
        jse.executeScript("arguments[0].click()", productTab);
        Thread.sleep(3000);

        log("Navigation", "Navigate to Product Details", "Done", "Done", true);
        sa.assertAll();
    }

    @Test(priority = 1)
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
