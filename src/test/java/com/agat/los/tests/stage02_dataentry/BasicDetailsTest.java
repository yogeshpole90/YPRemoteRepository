package com.agat.los.tests.stage02_dataentry;

import org.testng.annotations.BeforeClass;
import org.testng.annotations.Listeners;
import org.testng.annotations.Test;

import com.agat.los.base.BaseTest;
import com.agat.los.config.ConfigManager;
import com.agat.los.listeners.TestListener;
import com.agat.los.pages.AddressPage;
import com.agat.los.pages.BasicDetailsPage;
import com.agat.los.reporting.ExtentManager;

@Listeners(TestListener.class)
public class BasicDetailsTest extends BaseTest {

    private BasicDetailsPage bdPage;
    private AddressPage addrPage;

    @BeforeClass
    public void setup() throws Exception {
        driver = com.agat.los.driver.DriverManager.getDriver();
        jse = (org.openqa.selenium.JavascriptExecutor) driver;
        act = new org.openqa.selenium.interactions.Actions(driver);
        ExtentManager.initReport("BasicDetails_DDE");
        ExtentManager.startTest("Stage 2 - DDE Overview & Navigation");
        logInfo("Stage", "Current Stage", "DETAILED DATA ENTRY");
        bdPage = new BasicDetailsPage(driver);
        addrPage = new AddressPage(driver);
    }

    @Test(priority = 1)
    public void validateOverviewFields() throws Exception {
        bdPage.scrollToOverview();

        String custName = bdPage.getOverviewCustomerName();
        log("Overview", "Customer Name", "RABIYEV FARRUX", custName, custName.contains("RABIYEV"));

        String idNumber = bdPage.getOverviewIdNumber();
        log("Overview", "ID Number", "32103895350018", idNumber, idNumber.contains("32103895350018"));

        String appType = bdPage.getOverviewAppType();
        log("Overview", "Application Type", "Новый", appType, appType.contains("Новый"));

        String stage = bdPage.getOverviewStage();
        log("Overview", "Processing Stage", "DETAILED DATA ENTRY", stage, stage.contains("DETAILED DATA ENTRY"));

        String product = bdPage.getOverviewProduct();
        log("Overview", "Product", "Micro Loan", product, product.contains("Micro Loan"));

        String loanAmt = bdPage.getOverviewLoanAmount();
        log("Overview", "Loan Amount", "90,000,000", loanAmt, loanAmt.contains("90,000,000"));

        String tenure = bdPage.getOverviewLoanTenure();
        log("Overview", "Loan Tenure", "30", tenure, tenure.contains("30"));

        sa.assertAll();
    }

    @Test(priority = 2)
    public void clickDDEStageLink() throws Exception {
        String allocatedUser = bdPage.getAllocatedUser();
        String loggedInUser = bdPage.getLoggedInUser();
        logInfo("Stage", "DDE Allocated To", allocatedUser);
        logInfo("Stage", "Logged In As", loggedInUser);

        if (!allocatedUser.equalsIgnoreCase(loggedInUser)) {
            loginAs(allocatedUser, ConfigManager.get("password"));
            Thread.sleep(2000);
            bdPage.navigateToAppFromInbox(ConfigManager.get("generated.appId"));
        }

        bdPage.clickDDELink();
        log("DDE", "Click DDE Stage Link", "DDE tabs opened", "Clicked", true);
        sa.assertAll();
    }

    @Test(priority = 3)
    public void validateAppSummaryFields() throws Exception {
        Thread.sleep(2000);

        String custName = bdPage.getSummaryCustomerName();
        log("AppSummary", "Customer Name", "RABIYEV FARRUX", custName, custName.contains("RABIYEV"));

        String idType = bdPage.getSummaryIdType();
        log("AppSummary", "ID Type", "PINFL Number", idType, idType.contains("PINFL"));

        String idNum = bdPage.getSummaryIdNumber();
        log("AppSummary", "ID Number", "32103895350018", idNum, idNum.contains("32103895350018"));

        String dob = bdPage.getSummaryDob();
        log("AppSummary", "Date Of Birth", "21-03-1989", dob, dob.contains("21-03-1989"));

        bdPage.scrollToFinanceDetails();

        String tenure = bdPage.getSummaryTenure();
        log("AppSummary", "Tenure Months", "30", tenure, tenure.contains("30"));

        String finAmt = bdPage.getSummaryFinancingAmount();
        log("AppSummary", "Financing Amount", "90,000,000", finAmt, finAmt.contains("90,000,000"));

        String installments = bdPage.getSummaryInstallments();
        log("AppSummary", "Number of Installment", "30", installments, installments.contains("30"));

        String totalExp = bdPage.getSummaryTotalExposure();
        log("AppSummary", "Total Exposure", "90,000,000", totalExp, totalExp.contains("90,000,000"));

        sa.assertAll();
    }

    @Test(priority = 4)
    public void clickKYCTab() throws Exception {
        bdPage.clickKYCTab();
        log("DDE", "Click KYC Tab", "KYC page opened", "Clicked", true);
        sa.assertAll();
    }

    @Test(priority = 5)
    public void editBasicDetails() throws Exception {
        bdPage.switchToBasicDetailsFrame();
        bdPage.clickEditBtn();
        log("BasicDetails", "Click Edit", "Form loaded", "Clicked", true);

        bdPage.fillBasicDetailsForm("M", "21-03-1989", "1", "UZB", "uzbekistan", "03", "4", "2", "2");
        log("BasicDetails", "All fields filled", "Done", "Done", true);

        bdPage.clickBasicDetailsSave();
        log("BasicDetails", "Save", "Saved successfully", "Done", true);

        bdPage.switchToMainContent();
        sa.assertAll();
    }

    @Test(priority = 7)
    public void fillAddressDetails() throws Exception {
        addrPage.clickAddressTab();
        log("Address", "Click Address Details tab", "Address form loaded", "Clicked", true);

        addrPage.switchToAddressFrame();
        if (addrPage.hasExistingRecord()) {
            addrPage.clickEditBtn();
            log("Address", "Click Edit", "Form loaded", "Editing existing record", true);
        } else {
            log("Address", "Click Edit", "Form loaded", "No record - skipped", false);
            addrPage.switchToMainContent();
            sa.assertAll();
            return;
        }
        addrPage.fillAddressForm("1", "11-06-2022", "10", "abd", "test", "11-06-2023", "test");
        log("Address", "All address fields filled", "Done", "Done", true);

        addrPage.clickAddressSave();
        log("Address", "Save", "Address saved successfully", "Done", true);

        addrPage.switchToMainContent();
        sa.assertAll();
    }
}
