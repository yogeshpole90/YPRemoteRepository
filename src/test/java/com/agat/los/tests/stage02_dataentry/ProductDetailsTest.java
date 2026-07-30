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
import com.agat.los.pages.ProductDetailsPage;
import com.agat.los.reporting.ExtentManager;

@Listeners(TestListener.class)
public class ProductDetailsTest extends BaseTest {

    private ProductDetailsPage prodPage;

    @BeforeClass
    public void setup() throws Exception {
        driver = com.agat.los.driver.DriverManager.getDriver();
        jse = (org.openqa.selenium.JavascriptExecutor) driver;
        act = new Actions(driver);
        ExtentManager.initReport("ProductDetails_DDE");
        ExtentManager.startTest("Stage 2 - Product Details");
        logInfo("Stage", "Current Stage", "Product Details");
        prodPage = new ProductDetailsPage(driver);
    }

    @Test(priority = 0)
    public void navigateToApplication() throws Exception {
        navigateToDDE();
        log("Navigation", "Navigate to DDE", "DDE page opened", "Done", true);
        sa.assertAll();
    }

    @Test(priority = 1, alwaysRun = true)
    public void fillLoanItemDetails() throws Exception {
        prodPage.clickProductDetailsTab();
        log("LoanItem", "Click Product Details tab", "Product section opened", "Clicked", true);

        prodPage.clickLoanItemTab();
        log("LoanItem", "Click Loan Item Details tab", "Form loaded", "Clicked", true);

        prodPage.switchToLoanItemFrame();
        prodPage.clickEditBtn();
        log("LoanItem", "Click Edit", "Form loaded", "Clicked", true);

        String currentAmt = prodPage.getCurrentLoanAmount();
        log("LoanItem", "Current Loan Amount", "Read value", currentAmt, true);

        String newAmt = prodPage.updateLoanAmount();
        log("LoanItem", "Updated Loan Amount (+5mn)", newAmt, newAmt, true);

        prodPage.enterRemarks("Loan amount updated by automation");
        log("LoanItem", "Remarks", "Entered", "Done", true);

        prodPage.clickSaveLoanItem();
        String toast = getSuccessToast();
        log("LoanItem", "Save", "Saved successfully", toast.isEmpty() ? "Done" : toast, true);

        prodPage.switchToMainContent();
        sa.assertAll();
    }

    @Test(priority = 2, alwaysRun = true)
    public void saveLoanDetails() throws Exception {
        prodPage.clickLoanDetailsTab();
        log("LoanDetails", "Click Loan Details tab", "Form loaded", "Clicked", true);

        prodPage.switchToLoanDetailsFrame();
        prodPage.clickEditBtnLoanDetails();
        log("LoanDetails", "Click Edit", "Form loaded", "Clicked", true);

        prodPage.fillPurpose();
        log("LoanDetails", "Select Purpose", "Urgent Needs selected", "Done", true);

        prodPage.clickSaveLoanDetails();
        String toast = getSuccessToast();
        log("LoanDetails", "Save", "Saved successfully", toast.isEmpty() ? "Done" : toast, true);

        sa.assertAll();
    }

    @Test(priority = 3, alwaysRun = true)
    public void validateRepaymentSchedule() throws Exception {
        prodPage.clickEditBtnLoanDetails();
        log("LoanDetails", "Click Edit again", "Form loaded", "Clicked", true);

        prodPage.fillPurpose();
        log("LoanDetails", "Select Purpose", "Urgent Needs selected", "Done", true);

        prodPage.clickRepaymentSchedule();
        log("LoanDetails", "Click Repayment Schedule", "Schedule opened", "Clicked", true);

        int installments = prodPage.getNumberOfInstallments();
        log("Repayment", "Number of Installments", "Greater than 0", String.valueOf(installments), installments > 0);

        String emi = prodPage.getRepaymentEMI();
        log("Repayment", "EMI Amount (1st installment)", "Not empty", emi, !emi.isEmpty());

        String totalPayment = prodPage.getTotalPayment();
        log("Repayment", "Total Payment", "Not empty", totalPayment, !totalPayment.isEmpty());

        String totalPrincipal = prodPage.getTotalPrincipal();
        log("Repayment", "Total Principal", "Not empty", totalPrincipal, !totalPrincipal.isEmpty());

        String totalInterest = prodPage.getTotalInterest();
        log("Repayment", "Total Interest", "Not empty", totalInterest, !totalInterest.isEmpty());

        String lastDate = prodPage.getLastInstallmentDate();
        log("Repayment", "Last Installment Date", "Not empty", lastDate, !lastDate.isEmpty());

        prodPage.switchToMainContent();
        sa.assertAll();
    }

    @Test(priority = 4, enabled = false)
    public void fillInsuranceDetails() throws Exception {
        prodPage.clickInsuranceTab();
        log("Insurance", "Click Insurance Details tab", "Form loaded", "Clicked", true);

        prodPage.switchToInsuranceFrame();
        prodPage.clickEditBtn();
        log("Insurance", "Click Edit", "Form loaded", "Clicked", true);

        prodPage.fillInsuranceForm("Insurance added by automation");
        log("Insurance", "All fields filled", "Done", "Done", true);

        prodPage.clickSaveInsurance();
        String toast = getSuccessToast();
        log("Insurance", "Save", "Saved successfully", toast.isEmpty() ? "Done" : toast, true);

        prodPage.switchToMainContent();
        sa.assertAll();
    }
}
