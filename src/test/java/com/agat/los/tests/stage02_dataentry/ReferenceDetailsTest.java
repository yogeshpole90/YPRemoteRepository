package com.agat.los.tests.stage02_dataentry;

import org.openqa.selenium.By;
import org.openqa.selenium.interactions.Actions;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Listeners;
import org.testng.annotations.Test;

import com.agat.los.base.BaseTest;
import com.agat.los.config.ConfigManager;
import com.agat.los.listeners.TestListener;
import com.agat.los.pages.ReferenceDetailsPage;
import com.agat.los.reporting.ExtentManager;

@Listeners(TestListener.class)
public class ReferenceDetailsTest extends BaseTest {

    private ReferenceDetailsPage refPage;

    @BeforeClass
    public void setup() throws Exception {
        driver = com.agat.los.driver.DriverManager.getDriver();
        jse = (org.openqa.selenium.JavascriptExecutor) driver;
        act = new Actions(driver);
        ExtentManager.initReport("ReferenceDetails_DDE");
        ExtentManager.startTest("Stage 2 - Reference Details");
        logInfo("Stage", "Current Stage", "Reference Details");
        refPage = new ReferenceDetailsPage(driver);
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
        log("Navigation", "Click DDE Stage", "DDE page opened", "Done", true);

        // Smooth scroll to KYC tab
        var kycTab = driver.findElement(By.xpath("//nav[contains(@class,'section-nav')]//a[contains(@href,'activeTab=KYC')]"));
        jse.executeScript("arguments[0].scrollIntoView({behavior:'smooth', block:'center'})", kycTab);
        Thread.sleep(1500);
        jse.executeScript("arguments[0].click()", kycTab);
        Thread.sleep(3000);
        log("Navigation", "Click KYC Tab", "KYC section opened", "Done", true);
        sa.assertAll();
    }

    @Test(priority = 1)
    public void fillReferenceDetails() throws Exception {
        refPage.clickReferenceTab();
        log("Reference", "Click Reference Details tab", "Form loaded", "Clicked", true);

        refPage.switchToReferenceFrame();

        String randomPhone = String.valueOf((long)(Math.random() * 900000000L) + 100000000L);
        refPage.fillReferenceForm(randomPhone);
        log("Reference", "All fields filled (Phone: " + randomPhone + ")", "Done", "Done", true);

        refPage.verifyMobile("123456");
        log("Reference", "OTP Verification", "Verified", "Done", true);

        refPage.clickSave();
        String toast = getSuccessToast();
        log("Reference", "Save", "Saved successfully", toast.isEmpty() ? "Done" : toast, true);

        refPage.switchToMainContent();
        sa.assertAll();
    }
}
