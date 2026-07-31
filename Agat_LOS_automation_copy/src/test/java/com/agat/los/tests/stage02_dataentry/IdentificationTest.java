package com.agat.los.tests.stage02_dataentry;

import org.testng.annotations.BeforeClass;
import org.testng.annotations.Listeners;
import org.testng.annotations.Test;

import com.agat.los.base.BaseTest;
import com.agat.los.listeners.TestListener;
import com.agat.los.pages.IdentificationPage;
import com.agat.los.reporting.ExtentManager;

@Listeners(TestListener.class)
public class IdentificationTest extends BaseTest {

    private IdentificationPage idPage;

    @BeforeClass
    public void setup() throws Exception {
        driver = com.agat.los.driver.DriverManager.getDriver();
        jse = (org.openqa.selenium.JavascriptExecutor) driver;
        act = new org.openqa.selenium.interactions.Actions(driver);
        ExtentManager.initReport("Identification_DDE");
        ExtentManager.startTest("Stage 2 - Identification Details");
        logInfo("Stage", "Current Stage", "Identification Details");
        idPage = new IdentificationPage(driver);
    }

    @Test(priority = 1)
    public void fillIdentificationDetails() throws Exception {
        idPage.clickIdentificationTab();
        log("Identification", "Click Identification tab", "Form loaded", "Clicked", true);

        idPage.switchToIdentificationFrame();
        idPage.clickEditBtn();
        log("Identification", "Click Edit", "Form loaded", "Clicked", true);

        idPage.scrollToIssueDate();

        String issueDateVal = idPage.getIssueDateValue();
        log("Identification", "Issue Date (autofilled)", "Not empty", issueDateVal.isEmpty() ? "EMPTY" : issueDateVal, !issueDateVal.isEmpty());

        String expiryDateVal = idPage.getExpiryDateValue();
        log("Identification", "Expiry Date (autofilled)", "Not empty", expiryDateVal.isEmpty() ? "EMPTY" : expiryDateVal, !expiryDateVal.isEmpty());

        String placeVal = idPage.enterPlaceOfIssuance("uzbekistan");
        log("Identification", "Place of Issuance", "uzbekistan", placeVal, true);

        idPage.selectPassportType();
        log("Identification", "Passport Type", "Selected", "Index 1 selected", true);

        idPage.clickSave();
        String toast = idPage.getToastMessage();
        log("Identification", "Save", "Saved successfully", toast.isEmpty() ? "Done" : toast, true);

        idPage.switchToMainContent();
        sa.assertAll();
    }
}
