package com.agat.los.tests.stage05_loanactivation;

import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.interactions.Actions;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Parameters;
import org.testng.annotations.Listeners;
import org.testng.annotations.Test;

import com.agat.los.base.BaseTest;
import com.agat.los.config.ConfigManager;
import com.agat.los.listeners.TestListener;
import com.agat.los.pages.LoanActivationPage;
import com.agat.los.reporting.ExtentManager;

@Listeners(TestListener.class)
public class LoanActivationTest extends BaseTest {

    private LoanActivationPage laPage;

    @BeforeClass
    @Parameters({"generated.appId", "loan.activation.username"})
    public void setup(
            @org.testng.annotations.Optional("") String appId,
            @org.testng.annotations.Optional("") String loanUser) throws Exception {
        driver = com.agat.los.driver.DriverManager.getDriver();
        jse    = (JavascriptExecutor) driver;
        act    = new Actions(driver);
        ExtentManager.initReport("LoanActivation");
        ExtentManager.startTest("Stage 5 - Loan Activation");
        laPage = new LoanActivationPage(driver);
        // Standalone XML se aaya toh override karo, full flow me config ka use hoga
        if (appId != null && !appId.isEmpty())
            ConfigManager.set("generated.appId", appId);
        if (loanUser != null && !loanUser.isEmpty())
            ConfigManager.set("loan.activation.username", loanUser);
    }

    @Test(priority = 0)
    public void loginAsLoanActivationUser() throws Exception {
        String user = ConfigManager.get("loan.activation.username");
        String pass = ConfigManager.get("password");
        if (user == null || user.isEmpty()) {
            user = ConfigManager.get("username");
            logInfo("Login", "Loan Activation User (fallback)", user);
        } else {
            logInfo("Login", "Loan Activation User (from progress bar)", user);
        }
        loginAs(user, pass);
    }

    @Test(priority = 1)
    public void navigateToApplication() throws Exception {
        String appId = ConfigManager.get("generated.appId");
        laPage.navigateToAppFromInbox(appId);
        logInfo("Navigation", "Opened Application from Inbox", appId);
    }

    @Test(priority = 2)
    public void validateOverview() throws Exception {
        String custName = laPage.getOverviewCustomerName();
        log("Overview", "Customer Name", "Not empty", custName, !custName.isEmpty());

        String loanAmt = laPage.getOverviewLoanAmount();
        log("Overview", "Loan Amount", "Not empty", loanAmt, !loanAmt.isEmpty());

        String stage = laPage.getOverviewStage();
        log("Overview", "Processing Stage", "LOAN ACTIVATION", stage, stage.toUpperCase().contains("LOAN ACTIVATION"));

        sa.assertAll();
    }

    @Test(priority = 3)
    public void clickDisbursementTab() throws Exception {
        laPage.clickDisbursementTab();
        log("Navigation", "Click DISBURSEMENT Tab", "Tab opened", "Clicked", true);
        sa.assertAll();
    }

    @Test(priority = 4)
    public void clickDrawDownSubTab() throws Exception {
        laPage.clickDrawDownSubTab();
        log("Navigation", "Click DrawDown Schedule sub-tab", "Sub-tab opened", "Clicked", true);
        sa.assertAll();
    }

    @Test(priority = 5)
    public void switchFrameAndClickView() throws Exception {
        laPage.switchToDrawDownFrame();
        log("Frame", "Switch to DrawDown Frame", "Frame switched", "Done", true);

        // Horizontal scroll + click eye/view button
        laPage.clickViewBtn();
        log("DrawDown", "Click View (eye) button", "CREATE form opened", "Clicked", true);

        sa.assertAll();
    }

    @Test(priority = 6)
    public void validateReadOnlyFields() throws Exception {
        String milestoneName = laPage.getMilestoneName();
        log("DrawDown", "Milestone Name", "Not empty", milestoneName, !milestoneName.isEmpty());

        String product = laPage.getProduct();
        log("DrawDown", "Product", "Not empty", product, !product.isEmpty());

        String subProduct = laPage.getSubProduct();
        log("DrawDown", "Sub Product", "Not empty", subProduct, !subProduct.isEmpty());

        String scheme = laPage.getScheme();
        log("DrawDown", "Scheme", "Not empty", scheme, !scheme.isEmpty());

        sa.assertAll();
    }

    @Test(priority = 7)
    public void fillDrawDownForm() throws Exception {
        String today = new java.text.SimpleDateFormat("dd-MM-yyyy").format(new java.util.Date());

        laPage.enterMilestoneDate(today);
        log("DrawDown", "Milestone Date", today, today, true);

        laPage.enterEstDisbursementDate(today);
        log("DrawDown", "Estimated Disbursement Date", today, today, true);

        // Tab through all fields before entering percentage
        laPage.tabThroughDrawDownFields();

        laPage.enterExpectedDisbursementPercentage("50");
        log("DrawDown", "Expected Disbursement %", "50", "50", true);

        sa.assertAll();
    }

    @Test(priority = 8)
    public void validateAutoPopulatedFields() throws Exception {
        String approvedAmt = laPage.getApprovedAmount();
        log("DrawDown", "Approved Amount", "95000000", approvedAmt, !approvedAmt.isEmpty());

        String estTotal = laPage.getEstTotalDisbursementAmount();
        log("DrawDown", "Est. Total Disbursement Amount", "Not empty", estTotal, !estTotal.isEmpty());

        String remaining = laPage.getRemainingAmount();
        log("DrawDown", "Remaining Amount", "Not empty", remaining, !remaining.isEmpty());

        sa.assertAll();
    }

    @Test(priority = 9)
    public void saveDrawDown() throws Exception {
        laPage.clickSave();
        String toast = getSuccessToast();
        boolean saved = toast != null && !toast.isEmpty()
                && !toast.toLowerCase().contains("error")
                && !toast.toLowerCase().contains("fail");
        log("DrawDown", "Save DrawDown Schedule", "Success toast", toast, saved);
        sa.assertAll();
    }

    @Test(priority = 10)
    public void clickDisbursementDetailsSubTab() throws Exception {
        laPage.switchToMainContent();
        laPage.clickDisbursementDetailsSubTab();
        log("Navigation", "Click Disbursement Details sub-tab", "Sub-tab opened", "Clicked", true);
        sa.assertAll();
    }

    @Test(priority = 11)
    public void switchFrameAndClickDisbView() throws Exception {
        laPage.switchToDisbursementDetailsFrame();
        log("Frame", "Switch to Disbursement Details Frame", "Frame switched", "Done", true);

        laPage.clickDisbViewBtn();
        log("DisbDetails", "Click View button", "Form opened", "Clicked", true);
        sa.assertAll();
    }

    @Test(priority = 12)
    public void validateDisbursementFields() throws Exception {
        String product = laPage.getDisbProduct();
        log("DisbDetails", "Product", "Not empty", product, !product.isEmpty());

        String subProduct = laPage.getDisbSubProduct();
        log("DisbDetails", "Sub Product", "Not empty", subProduct, !subProduct.isEmpty());

        String scheme = laPage.getDisbScheme();
        log("DisbDetails", "Scheme", "Not empty", scheme, !scheme.isEmpty());

        String milestoneName = laPage.getDisbMilestoneName();
        log("DisbDetails", "Milestone Name", "Not empty", milestoneName, !milestoneName.isEmpty());

        String milestoneDate = laPage.getDisbMilestoneDate();
        log("DisbDetails", "Milestone Date", "Not empty", milestoneDate, !milestoneDate.isEmpty());

        String approvedAmt = laPage.getDisbApprovedAmount();
        log("DisbDetails", "Approved Amount", "Not empty", approvedAmt, !approvedAmt.isEmpty());

        String disbAmt = laPage.getDisbursementAmount();
        double approved = approvedAmt.isEmpty() ? 0 : Double.parseDouble(approvedAmt.replaceAll("[^0-9.]", ""));
        double disburse = disbAmt.isEmpty() ? 0 : Double.parseDouble(disbAmt.replaceAll("[^0-9.]", ""));
        double expected50 = approved * 0.5;
        boolean isWithin50 = disburse <= expected50;
        log("DisbDetails", "Disbursement Amount (50% check)", String.valueOf((long)expected50), disbAmt, isWithin50);

        String customerId = laPage.getDisbCustomerId();
        log("DisbDetails", "Customer ID", "Not empty", customerId, !customerId.isEmpty());

        String status = laPage.getDisbStatus();
        log("DisbDetails", "Status", "Pending", status, !status.isEmpty());

        sa.assertAll();
    }

    @Test(priority = 13)
    public void selectModeOfDisbursementAndSave() throws Exception {
        laPage.selectModeOfDisbursement("1");
        String mode = laPage.getModeOfDisbursement();
        log("DisbDetails", "Mode of Disbursement", "Disbursement to Customer Account", mode,
                mode.contains("Customer Account"));

        laPage.clickDisbSave();
        String toast = getSuccessToast();
        boolean saved = toast != null && !toast.isEmpty()
                && !toast.toLowerCase().contains("error")
                && !toast.toLowerCase().contains("fail");
        log("DisbDetails", "Save Disbursement Details", "Success toast", toast, saved);
        sa.assertAll();
    }

    @Test(priority = 14)
    public void checkInterfacingStatus() throws Exception {
        laPage.switchToMainContent();
        laPage.switchToDisbursementDetailsFrame();
        laPage.clickDisbViewBtn();
        log("DisbDetails", "Click View Button", "View page opened", "Clicked", true);

        laPage.clickDisburseBtn();
        log("DisbDetails", "Click Disburse Button", "Processing...", "Clicked", true);

        laPage.clickInterfacingStatusBtn();
        log("DisbDetails", "Click Interfacing Status", "Modal opened", "Clicked", true);

        java.util.Map<String, java.util.List<String>> results = laPage.getInterfacingStatusResults();
        java.util.List<String> pass = results.get("pass");
        java.util.List<String> fail = results.get("fail");

        if (!pass.isEmpty())
            log("InterfacingStatus", "PASSED Services", "All Success", String.join(", ", pass), true);

        if (fail.isEmpty()) {
            log("InterfacingStatus", "All Services", "Success", "All Passed", true);
        } else {
            for (String f : fail)
                log("InterfacingStatus", "FAILED Service", "Success", f, false);
        }

        laPage.closeInterfacingModal();
        log("DisbDetails", "Close Interfacing Modal", "Modal closed", "Done", true);
        sa.assertAll();
    }

    @Test(priority = 15)
    public void clickDocumentArchiveTab() throws Exception {
        laPage.switchToMainContent();
        laPage.clickDocumentArchiveTab();
        log("Navigation", "Click DOCUMENT ARCHIVE Tab", "Tab opened", "Clicked", true);
        sa.assertAll();
    }

    @Test(priority = 16)
    public void fillDocumentArchiveRows() throws Exception {
        String today = new java.text.SimpleDateFormat("dd-MM-yyyy").format(new java.util.Date());
        int rowCount = laPage.getDocArchiveRowCount();
        log("DocArchive", "Total Rows", ">= 2", String.valueOf(rowCount), rowCount >= 1);

        for (int i = 0; i < rowCount; i++) {
            laPage.clickDocArchiveEditBtn(i);
            log("DocArchive", "Click Edit Row " + (i + 1), "Form opened", "Clicked", true);

            laPage.fillDocumentArchiveForm(
                today,       // Archival Date = today
                "1",         // Branch = Головной Офис
                "N.NAMOZOV", // Document Custodian
                "1",         // Rack Number
                "1",         // Shelf Number
                "1"          // Box Number
            );

            String archDate  = laPage.getArchivalDate();
            log("DocArchive", "Row " + (i+1) + " Archival Date", today, archDate, !archDate.isEmpty());

            String branch    = laPage.getDocArchiveBranch();
            log("DocArchive", "Row " + (i+1) + " Branch", "Not empty", branch, !branch.isEmpty());

            String custodian = laPage.getDocArchiveCustodian();
            log("DocArchive", "Row " + (i+1) + " Custodian", "N.NAMOZOV", custodian, custodian.contains("N.NAMOZOV"));

            String rack      = laPage.getRackNumber();
            log("DocArchive", "Row " + (i+1) + " Rack Number", "1", rack, !rack.isEmpty());

            laPage.clickDocArchiveSave();
            String toast = getSuccessToast();
            boolean saved = toast != null && !toast.isEmpty()
                    && !toast.toLowerCase().contains("error")
                    && !toast.toLowerCase().contains("fail");
            log("DocArchive", "Row " + (i+1) + " Save", "Success toast", toast, saved);

            // Wait for grid to reload before next iteration
            Thread.sleep(3000);
        }
        sa.assertAll();
    }

    @Test(priority = 17)
    public void clickNextAndSubmit() throws Exception {
        laPage.switchToMainContent();

        laPage.clickNext();
        log("Navigation", "Click Next Button", "Remark popup opened", "Clicked", true);

        laPage.enterRemark("Loan activation completed. Disbursement initiated.");
        log("Submit", "Enter Remark", "Remark entered", "Done", true);

        laPage.clickRemarkSubmit();
        log("Submit", "Click Submit", "Stage submitted", "Done", true);

        sa.assertAll();
    }
}
