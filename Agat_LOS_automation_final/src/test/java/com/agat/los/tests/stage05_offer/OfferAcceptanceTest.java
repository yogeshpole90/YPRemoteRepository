package com.agat.los.tests.stage05_offer;

import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.interactions.Actions;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Listeners;
import org.testng.annotations.Test;

import com.agat.los.base.BaseTest;
import com.agat.los.config.ConfigManager;
import com.agat.los.listeners.TestListener;
import com.agat.los.pages.OfferAcceptancePage;
import com.agat.los.reporting.ExtentManager;

@Listeners(TestListener.class)
public class OfferAcceptanceTest extends BaseTest {

    private OfferAcceptancePage oaPage;

    @BeforeClass
    public void setup() throws Exception {
        driver = com.agat.los.driver.DriverManager.getDriver();
        jse = (JavascriptExecutor) driver;
        act = new Actions(driver);
        ExtentManager.initReport("OfferAcceptance");
        ExtentManager.startTest("Stage 4 - Offer Acceptance");
        oaPage = new OfferAcceptancePage(driver);
    }

    @Test(priority = 0)
    public void loginAsOfferUser() throws Exception {
        String user = ConfigManager.get("username");
        String pass = ConfigManager.get("password");
        loginAs(user, pass);
        logInfo("Login", "Offer Acceptance User", user);
    }

    @Test(priority = 1)
    public void navigateToApplication() throws Exception {
        String appId = ConfigManager.get("generated.appId");
        oaPage.navigateToAppFromInbox(appId);
        logInfo("Navigation", "Opened Application from Inbox", appId);
    }

    @Test(priority = 2)
    public void clickOfferAcceptanceStage() throws Exception {
        oaPage.clickOfferAcceptanceStageLink();
        log("Navigation", "Click Offer Acceptance Stage Link", "Next page opened", "Clicked", true);
        sa.assertAll();
    }

    @Test(priority = 3)
    public void clickOfferDetailsTab() throws Exception {
        oaPage.clickOfferDetailsTab();
        log("Navigation", "Click Offer Details Tab", "Offer Details page opened", "Clicked", true);
        sa.assertAll();
    }

    @Test(priority = 4)
    public void editOfferDetails() throws Exception {
        oaPage.switchToOfferDetailsFrame();
        oaPage.clickEditBtn();
        log("OfferDetails", "Click Edit Button", "Edit form opened", "Clicked", true);

        String newAmt = oaPage.updateOfferAmount();
        log("OfferDetails", "Updated Offer Amount (+5mn)", newAmt, newAmt, true);

        sa.assertAll();
    }

    @Test(priority = 5)
    public void validateOfferFields() throws Exception {
        String loanAmt = oaPage.getLoanAmountRequested();
        log("OfferDetails", "Loan Amount Requested", "Not empty", loanAmt, !loanAmt.isEmpty());

        String tenure = oaPage.getRequestedTenure();
        log("OfferDetails", "Requested Tenure", "30", tenure, tenure.contains("30"));

        String rate = oaPage.getEffectiveInterestRate();
        log("OfferDetails", "Effective Interest Rate", "Not empty", rate, !rate.isEmpty());

        String offerAmt = oaPage.getOfferAmount();
        log("OfferDetails", "Offer Amount", "Not empty", offerAmt, !offerAmt.isEmpty());

        String offerTenure = oaPage.getOfferTenure();
        log("OfferDetails", "Offer Tenure", "30", offerTenure, offerTenure.contains("30"));

        sa.assertAll();
    }

    @Test(priority = 6)
    public void acceptAndCalculate() throws Exception {
        oaPage.clickAccept();
        log("OfferDetails", "Click Accept Radio", "Accept selected", "Clicked", true);

        oaPage.clickCalculate();
        log("OfferDetails", "Click Calculate", "Repayment Schedule opened", "Clicked", true);

        oaPage.closeRepaymentSchedule();
        log("OfferDetails", "Close Repayment Schedule", "Modal closed", "Closed", true);

        sa.assertAll();
    }

    @Test(priority = 7)
    public void validateCalculatedFields() throws Exception {
        String installment = oaPage.getInstallment();
        log("OfferDetails", "Installment", "Not empty", installment, !installment.isEmpty());

        String financingAmt = oaPage.getFinancingAmount();
        log("OfferDetails", "Financing Amount", "Not empty", financingAmt, !financingAmt.isEmpty());

        String totalInterest = oaPage.getTotalInterest();
        log("OfferDetails", "Total Interest", "Not empty", totalInterest, !totalInterest.isEmpty());

        sa.assertAll();
    }

    @Test(priority = 8)
    public void enterCommentsAndSave() throws Exception {
        oaPage.enterComments("Offer accepted and verified");
        log("OfferDetails", "Enter Comments", "Comments entered", "Done", true);

        oaPage.clickSave();
        log("OfferDetails", "Click Save", "Saved successfully", "Done", true);

        sa.assertAll();
    }

    @Test(priority = 9)
    public void clickNextAndSubmit() throws Exception {
        oaPage.switchToMainContent();

        oaPage.clickNext();
        log("Navigation", "Click Next Button", "Remark popup opened", "Clicked", true);

        oaPage.enterRemark("Offer accepted. Moving to next stage.");
        log("Submit", "Enter Remark", "Remark entered", "Done", true);

        oaPage.clickRemarkSubmit();
        log("Submit", "Click Submit", "Stage submitted", "Done", true);

        sa.assertAll();
    }

    @Test(priority = 10)
    public void fetchLoanActivationUser() throws Exception {
        // Scroll down to progress bar and fetch Loan Activation allocated user
        String loanActUser = oaPage.fetchLoanActivationUser();
        log("ProgressBar", "Loan Activation Allocated User", "Not empty", loanActUser, !loanActUser.isEmpty());

        // Save to config so LoanActivationTest can use it
        ConfigManager.set("loan.activation.username", loanActUser);
        logInfo("ProgressBar", "Saved loan.activation.username", loanActUser);

        sa.assertAll();
    }
}
