package com.agat.los.tests.stage02_dataentry;

import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.interactions.Actions;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Listeners;
import org.testng.annotations.Test;

import com.agat.los.base.BaseTest;
import com.agat.los.listeners.TestListener;
import com.agat.los.pages.BankDetailsPage;
import com.agat.los.reporting.ExtentManager;

@Listeners(TestListener.class)
public class BankDetailsTest extends BaseTest {

    private BankDetailsPage bankPage;

    @BeforeClass
    public void setup() throws Exception {
        driver = com.agat.los.driver.DriverManager.getDriver();
        jse = (org.openqa.selenium.JavascriptExecutor) driver;
        act = new Actions(driver);
        ExtentManager.initReport("BankDetails_DDE");
        ExtentManager.startTest("Stage 2 - Bank Details");
        logInfo("Stage", "Current Stage", "Bank Details");
        bankPage = new BankDetailsPage(driver);
    }

    @Test(priority = 0)
    public void navigateToApplication() throws Exception {
        navigateToDDE();
        WebElement kycTab = driver
                .findElement(By.xpath("//nav[contains(@class,'section-nav')]//a[contains(@href,'activeTab=KYC')]"));
        jse.executeScript("arguments[0].click()", kycTab);
        Thread.sleep(2000);
        log("Navigation", "Navigate to DDE KYC", "DDE page opened", "Done", true);
        sa.assertAll();
    }

    @Test(priority = 1, alwaysRun = true)
    public void fillBankDetails() throws Exception {
        // Ensure we are on KYC tab before clicking Bank Details
        try {
            var kycTab = driver
                    .findElement(By.xpath("//nav[contains(@class,'section-nav')]//a[contains(@href,'activeTab=KYC')]"));
            jse.executeScript("arguments[0].click()", kycTab);
            Thread.sleep(2000);
        } catch (Exception e) {
        }

        bankPage.clickBankDetailsTab();
        log("BankDetails", "Click Bank Details tab", "Form loaded", "Clicked", true);

        bankPage.switchToBankFrame();
        bankPage.clickEditBtn();
        log("BankDetails", "Click Edit", "Form loaded", "Clicked", true);

        bankPage.fillBankForm("8600123456789012", "12/28", "RABIYEV FARRUX");
        log("BankDetails", "All fields filled", "Done", "Done", true);

        bankPage.clickSave();
        String toast = getSuccessToast();
        log("BankDetails", "Save", "Saved successfully", toast.isEmpty() ? "Done" : toast, !toast.isEmpty());

        bankPage.switchToMainContent();
        sa.assertAll(); // sa.assertAll();

    }
}
