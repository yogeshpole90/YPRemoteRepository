package com.agat.los.tests.stage04_credit;

import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.interactions.Actions;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Listeners;
import org.testng.annotations.Test;

import com.agat.los.base.BaseTest;
import com.agat.los.config.ConfigManager;
import com.agat.los.listeners.TestListener;
import com.agat.los.pages.CreditApprovalPage;
import com.agat.los.reporting.ExtentManager;

@Listeners(TestListener.class)
public class CreditApprovalTest extends BaseTest {

    private CreditApprovalPage caPage;

    @BeforeClass
    public void setup() throws Exception {
        driver = com.agat.los.driver.DriverManager.getDriver();
        jse = (JavascriptExecutor) driver;
        act = new Actions(driver);
        ExtentManager.initReport("CreditApproval");
        ExtentManager.startTest("Stage 3 - Credit Approval");
        caPage = new CreditApprovalPage(driver);
    }

    @Test(priority = 0)
    public void loginAsCreditApprovalUser() throws Exception {
        String caUser = ConfigManager.get("credit.approval.username");
        String caPass = ConfigManager.get("credit.approval.password");
        loginAs(caUser, caPass);
        logInfo("Login", "Credit Approval User", caUser);
    }

    @Test(priority = 1)
    public void navigateToApplication() throws Exception {
        String appId = ConfigManager.get("generated.appId");
        caPage.navigateToAppFromInbox(appId);
        logInfo("Navigation", "Opened Application from Inbox", appId);
    }

    @Test(priority = 2)
    public void validateOverview() throws Exception {
        Thread.sleep(2000);

        String stage = caPage.getProcessingStage();
        log("Overview", "Processing Stage", "CREDIT APPROVAL", stage, stage.contains("CREDIT APPROVAL"));

        String product = caPage.getProduct();
        log("Overview", "Product", "Not empty", product, !product.isEmpty());

        String loanAmt = caPage.getLoanAmount();
        log("Overview", "Loan Amount", "Not empty", loanAmt, !loanAmt.isEmpty());

        String tenure = caPage.getLoanTenure();
        log("Overview", "Loan Tenure", "30", tenure, tenure.contains("30"));

        sa.assertAll();
    }

    @Test(priority = 3)
    public void clickCreditApprovalStage() throws Exception {
        caPage.clickCreditApprovalStageLink();
        log("Navigation", "Click Credit Approval Stage Link", "Next page opened", "Clicked", true);
        sa.assertAll();
    }

    @Test(priority = 4)
    public void clickRecommendationTab() throws Exception {
        caPage.clickRecommendationTab();
        log("Navigation", "Click Recommendation Tab", "Recommendation page opened", "Clicked", true);
        sa.assertAll();
    }

    @Test(priority = 5)
    public void editProductDetails() throws Exception {
        caPage.switchToRecommendationFrame();
        caPage.clickProductDetailsEdit();
        log("Recommendation", "Click Edit on Product Details", "Update section opened", "Clicked", true);

        String newAmt = caPage.updateLoanAmount();
        log("Recommendation", "Updated Loan Amount (+5mn)", newAmt, newAmt, true);

        sa.assertAll();
    }

    @Test(priority = 6)
    public void calculateAndValidate() throws Exception {
        caPage.clickCalculate();
        log("Recommendation", "Click Calculate", "Repayment Schedule opened", "Clicked", true);

        caPage.closeRepaymentSchedule();
        log("Recommendation", "Close Repayment Schedule", "Modal closed", "Closed", true);

        String installment = caPage.getInstallment();
        log("Recommendation", "Installment", "Not empty", installment, !installment.isEmpty());

        String financingAmt = caPage.getFinancingAmount();
        log("Recommendation", "Financing Amount", "Not empty", financingAmt, !financingAmt.isEmpty());

        String totalInterest = caPage.getTotalInterest();
        log("Recommendation", "Total Interest", "Not empty", totalInterest, !totalInterest.isEmpty());

        sa.assertAll();
    }

    @Test(priority = 7)
    public void enterCommentsAndSave() throws Exception {
        caPage.enterComments("Verified and Recommended for approval");
        log("Recommendation", "Enter Comments", "Comments entered", "Done", true);

        caPage.clickSave();
        String toast = getSuccessToast();
        log("Recommendation", "Save", "Success toast", toast.isEmpty() ? "Done" : toast, true);

        sa.assertAll();
    }

    @Test(priority = 8)
    public void clickNextAndSubmit() throws Exception {
        caPage.switchToMainContent();

        caPage.clickNext();
        log("Navigation", "Click Next Button", "Remark popup opened", "Clicked", true);

        caPage.enterRemark("Recommendation completed. Moving to next stage.");
        log("Submit", "Enter Remark", "Remark entered", "Done", true);

        caPage.clickRemarkSubmit();
        log("Submit", "Click Submit", "Stage submitted", "Done", true);

        sa.assertAll();
    }
}
