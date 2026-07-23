package com.agat.los.tests.stage02_dataentry;

import org.openqa.selenium.By;
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
        String appId = ConfigManager.get("generated.appId");

        // Navigate to inbox first
        new com.agat.los.pages.LeadCreationPage(driver).navigateToInbox();

        var searchBox = driver.findElement(By.cssSelector("#dt-authdata_filter input[type='search']"));
        searchBox.clear();
        searchBox.sendKeys(appId);
        Thread.sleep(2000);

        act.doubleClick(driver.findElement(
                By.xpath("//table[@id='dt-authdata']//tbody//tr[td[contains(text(),'" + appId + "')]]/td[2]"))).build().perform();
        Thread.sleep(2000);

        var ddeLink = driver.findElement(By.xpath("//td[@class='stage-child']//a[contains(@href,'stageName=DETAILED DATA ENTRY')]"));
        jse.executeScript("arguments[0].scrollIntoView({behavior:'smooth', block:'center'})", ddeLink);
        Thread.sleep(500);
        jse.executeScript("arguments[0].click()", ddeLink);
        Thread.sleep(2000);

        log("Navigation", "Navigate to DDE", "DDE page opened", "Done", true);
        sa.assertAll();
    }

    @Test(priority = 1)
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

    @Test(priority = 2)
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

    @Test(priority = 3)
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
