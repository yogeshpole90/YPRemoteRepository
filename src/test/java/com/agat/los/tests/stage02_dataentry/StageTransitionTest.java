package com.agat.los.tests.stage02_dataentry;

import org.openqa.selenium.By;
import org.openqa.selenium.interactions.Actions;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Listeners;
import org.testng.annotations.Test;

import com.agat.los.base.BaseTest;
import com.agat.los.config.ConfigManager;
import com.agat.los.listeners.TestListener;
import com.agat.los.pages.StageTransitionPage;
import com.agat.los.reporting.ExtentManager;

@Listeners(TestListener.class)
public class StageTransitionTest extends BaseTest {

    private StageTransitionPage transitionPage;

    @BeforeClass
    public void setup() throws Exception {
        driver = com.agat.los.driver.DriverManager.getDriver();
        jse = (org.openqa.selenium.JavascriptExecutor) driver;
        act = new Actions(driver);
        ExtentManager.initReport("StageTransition_DDE");
        ExtentManager.startTest("Stage 2 - Move to Next Stage");
        logInfo("Stage", "Current Stage", "DDE → Next");
        transitionPage = new StageTransitionPage(driver);
    }

    @Test(priority = 0, enabled = false)
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

    @Test(priority = 1, alwaysRun = true)
    public void moveToNextStage() throws Exception {
        navigateToDDE();
        // Click Next button
        transitionPage.clickNext();
        log("Transition", "Click Next", "Remarks modal opened", "Clicked", true);

        // Enter remarks
        transitionPage.enterRemarks("DDE completed - moving to next stage via automation");
        log("Transition", "Enter Remarks", "Text entered", "Done", true);

        // Click Submit
        transitionPage.clickSubmit();
        log("Transition", "Click Submit", "Submitted", "Clicked", true);

        // Capture success message
        String msg = transitionPage.getStageSuccessMessage();
        boolean isSuccess = !msg.isEmpty();
        log("Transition", "Success Message", "Successfully moved", msg.isEmpty() ? "No message found" : msg, isSuccess);
        sa.assertTrue(isSuccess, "Stage transition should show success message: " + msg);

        // Logout after DDE stage transition
        try {
            driver.findElement(By.cssSelector("a.item-logout")).click();
            Thread.sleep(2000);
            driver.findElement(By.id("logoutYes")).click();
            Thread.sleep(2000);
        } catch (Exception e) {}

        sa.assertAll();
    }
}
