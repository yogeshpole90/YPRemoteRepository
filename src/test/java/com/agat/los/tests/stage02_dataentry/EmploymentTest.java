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
import com.agat.los.pages.EmploymentPage;
import com.agat.los.reporting.ExtentManager;

@Listeners(TestListener.class)
public class EmploymentTest extends BaseTest {

    private EmploymentPage empPage;

    @BeforeClass
    public void setup() throws Exception {
        driver = com.agat.los.driver.DriverManager.getDriver();
        jse = (org.openqa.selenium.JavascriptExecutor) driver;
        act = new Actions(driver);
        ExtentManager.initReport("Employment_DDE");
        ExtentManager.startTest("Stage 2 - Employment Details");
        logInfo("Stage", "Current Stage", "Employment Details");
        empPage = new EmploymentPage(driver);
    }

    @Test(priority = 0, groups = "standalone")
    public void navigateToApplication() throws Exception {
        navigateToDDE();
        WebElement kycTab = driver.findElement(By.xpath("//nav[contains(@class,'section-nav')]//a[contains(@href,'activeTab=KYC')]"));
        jse.executeScript("arguments[0].click()", kycTab);
        Thread.sleep(2000);
        log("Navigation", "Navigate to DDE KYC", "DDE page opened", "Done", true);
        sa.assertAll();
    }

    @Test(priority = 1, alwaysRun = true)
    public void fillEmploymentDetails() throws Exception {
        empPage.clickEmploymentTab();
        log("Employment", "Click Employment tab", "Form loaded", "Clicked", true);

        empPage.switchToEmploymentFrame();
        empPage.clickEditOrAddBtn();
        log("Employment", "Click Edit", "Form loaded", "Clicked", true);

        empPage.fillEmploymentForm("998901234", "www.test.com", "test@test.com", "Test remarks");
        log("Employment", "All fields filled", "Done", "Done", true);

        empPage.clickSave();
        log("Employment", "Save", "Saved successfully", "Done", true);

        empPage.switchToMainContent();
        sa.assertAll();
    }
}
