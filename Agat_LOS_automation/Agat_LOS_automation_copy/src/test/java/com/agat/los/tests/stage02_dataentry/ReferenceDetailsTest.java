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
        navigateToDDE();
        WebElement kycTab = driver.findElement(By.xpath("//nav[contains(@class,'section-nav')]//a[contains(@href,'activeTab=KYC')]"));
        jse.executeScript("arguments[0].scrollIntoView({behavior:'smooth', block:'center'})", kycTab);
        Thread.sleep(500);
        jse.executeScript("arguments[0].click()", kycTab);
        Thread.sleep(2000);
        log("Navigation", "Click KYC Tab", "KYC section opened", "Done", true);
        sa.assertAll();
    }

    @Test(priority = 1, alwaysRun = true)
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
