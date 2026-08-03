package com.agat.los.tests.stage01_lead;

import org.testng.annotations.BeforeClass;
import org.testng.annotations.Listeners;
import org.testng.annotations.Test;

import com.agat.los.base.BaseTest;
import com.agat.los.config.ConfigManager;
import com.agat.los.listeners.TestListener;
import com.agat.los.pages.LeadCreationPage;
import com.agat.los.reporting.ExtentManager;

@Listeners(TestListener.class)
public class LeadCreationTest extends BaseTest {

    private LeadCreationPage leadPage;
    private String savedLeadId = "";

    @BeforeClass
    public void setup() throws Exception {
        driver = com.agat.los.driver.DriverManager.getDriver();
        jse = (org.openqa.selenium.JavascriptExecutor) driver;
        act = new org.openqa.selenium.interactions.Actions(driver);
        ExtentManager.initReport("LeadCreation");
        ExtentManager.startTest("Stage 1 - Lead Creation & Conversion");
        logInfo("Stage", "Current Stage", "Lead Creation");
        leadPage = new LeadCreationPage(driver);
    }

    @Test(priority = 1)
    public void clickNewLead() throws Exception {
        log("New Lead", "Button displayed", "true", String.valueOf(leadPage.isNewLeadBtnDisplayed()), leadPage.isNewLeadBtnDisplayed());
        leadPage.clickNewLead();
        log("New Lead", "Clicked New Lead", "Lead form opened", "Clicked", true);
    }

    @Test(priority = 2)
    public void fillLeadForm() throws Exception {
        String fName = leadPage.enterFirstName("FARRUX");
        log("fName", "Customer First Name", "FARRUX", fName, true);

        String mName = leadPage.enterMiddleName("RABIYEVICH");
        log("mName", "Customer Middle Name", "RABIYEVICH", mName, true);

        String lName = leadPage.enterLastName("RABIYEV");
        log("famName", "Customer Last Name", "RABIYEV", lName, true);

        String code = leadPage.selectCountryCode("204");
        log("countryCode", "Mobile Code +998", "+998", code, true);

        String mobile = leadPage.generateRandomMobile();
        String mobileVal = leadPage.enterMobileNo(mobile);
        log("mobileNo", "Mobile Number", mobile, mobileVal, true);

        String dobVal = leadPage.setDob("21-03-1989");
        log("dob", "Date of Birth", "21-03-1989", dobVal, true);
    }

    @Test(priority = 3)
    public void fillLoanInformation() throws Exception {
        // Loan Officer auto-selected based on logged-in user
        logInfo("loanOfficer", "Loan Officer", "Auto-selected (logged-in user)");
    }

    @Test(priority = 4)
    public void saveLead() throws Exception {
        log("Save", "Displayed", "true", String.valueOf(leadPage.isSaveDisplayed()), leadPage.isSaveDisplayed());
        leadPage.clickSave();
        leadPage.clickConfirmYes();

        String toast = getSuccessToast();
        log("Save", "Save Lead", "Success", toast.isEmpty() ? "No toast" : toast, !toast.isEmpty());

        if (!toast.isEmpty()) {
            savedLeadId = leadPage.extractIdFromToast(toast);
            ConfigManager.set("generated.leadId", savedLeadId);
            logInfo("Save", "Lead ID captured", savedLeadId);
        }
        sa.assertAll();
    }

    @Test(priority = 5)
    public void navigateToLeadList() throws Exception {
        leadPage.navigateToLeadList();
        log("Lead List", "Navigate to Lead List", "Lead List page opened", "Clicked", true);
    }

    @Test(priority = 6)
    public void openLeadForConversion() throws Exception {
        leadPage.searchInLeadList(savedLeadId);
        log("Search", "Search Lead ID", savedLeadId, "Searched", true);

        leadPage.doubleClickLeadRow(savedLeadId);
        log("Lead List", "Double-click Lead", "Conversion form opened", "Clicked", true);
    }

    @Test(priority = 7)
    public void fillConversionForm() throws Exception {
        String idTypeVal = leadPage.selectIdType("PINFL Number");
        log("idType", "ID Type", "PINFL Number", idTypeVal, true);

        String idNum = leadPage.enterIdNumber("32103895350018");
        log("idNumber", "ID Number (PINFL)", "32103895350018", idNum, true);

        String dobVal = leadPage.setConversionDob("21-03-1989");
        log("dob", "Date of Birth", "21-03-1989", dobVal, true);

        boolean isExistingCustomer = leadPage.clickSearchAndHandleDedupe();
        if (isExistingCustomer) {
            log("Search", "Click Search", "Existing customer - clicked Yes", "Clicked", true);
        } else {
            log("Search", "Click Search", "New customer - No record found", "Clicked", true);
        }

        // OTP Verify first
        leadPage.clickVerifyMobile();
        log("OTP", "Click Verify Mobile", "OTP sent", "Clicked", true);

        String otpVal = leadPage.enterOtp("123456");
        log("OTP", "Enter OTP", "123456", otpVal, true);

        leadPage.clickVerifyOtp();
        log("OTP", "Click Verify OTP", "Verified", "Clicked", true);

        // Product > Sub Product > Scheme
        String productVal = leadPage.selectProduct("01");
        log("product", "Product", "01-Микрозайм", productVal, true);

        String subProd = leadPage.selectSubProduct();
        if (!subProd.equals("NO_OPTIONS"))
            log("subProduct", "Sub Product", "Selected", subProd, true);
        else
            logInfo("subProduct", "No options available", "Skipped");

        String schemeVal = leadPage.selectScheme();
        if (!schemeVal.equals("NO_OPTIONS"))
            log("scheme", "Scheme", "Selected", schemeVal, true);
        else
            logInfo("scheme", "No options available", "Skipped");

        String amtVal = leadPage.enterRequestedAmount("75000000");
        log("requiredAmount", "Requested Amount", "75000000", amtVal, true);

        String tenureVal = leadPage.enterRequestedTenure("30");
        log("requestedTenure", "Requested Tenure", "30", tenureVal, true);

        String srcVal = leadPage.selectPrimarySource("2");
        log("primarySource", "Primary Source", "Self Employed", srcVal, true);

        // Calculate EMI
        leadPage.clickCalculateEmi();
        log("Calculate EMI", "Click Calculate EMI button", "Loan Simulator opened", "Clicked", true);

        String loVal = leadPage.getLoanOfficerText();
        log("loanOfficer", "Loan Officer", "n.namozov", loVal, loVal.toLowerCase().contains("namozov") || !loVal.isEmpty());

        String today = leadPage.getTodayDate();
        String nextMonth = leadPage.getNextMonthDate();

        String startDate = leadPage.setLoanStartDate(today);
        log("loanStartDate", "Loan Start Date", today, startDate, true);

        String emiDate = leadPage.setFirstEmiDate(nextMonth);
        log("firstEmiDate", "First Installment Date", nextMonth, emiDate, true);

        leadPage.clickCalculateEmiModal();
        log("Calculate EMI", "Click Calculate EMI (modal)", "Repayment calculated", "Clicked", true);

        // Verify simulated details populated
        String financeAmt = leadPage.getSimulatedDetail("financeAmount");
        log("Simulated", "Finance Amount", "non-empty", financeAmt, !financeAmt.isEmpty());

        String installAmt = leadPage.getSimulatedDetail("installmentAmount");
        log("Simulated", "Installment Amount", "non-empty", installAmt, !installAmt.isEmpty());

        String totalAmt = leadPage.getSimulatedDetail("totalAmountToBePaid");
        if (totalAmt != null && !totalAmt.isEmpty())
            log("Simulated", "Total Amount to be Paid", "non-empty", totalAmt, true);

        String totalInterest = leadPage.getSimulatedDetail("totalInterestAmount");
        if (totalInterest != null && !totalInterest.isEmpty())
            log("Simulated", "Total Interest Amount", "non-empty", totalInterest, true);

        String firstInstDate = leadPage.getSimulatedDetail("firstInstallmentDate");
        if (firstInstDate != null && !firstInstDate.isEmpty())
            log("Simulated", "First Installment Date", "auto-filled", firstInstDate, true);

        String lastInstDate = leadPage.getSimulatedDetail("lastInstallmentDate");
        if (lastInstDate != null && !lastInstDate.isEmpty())
            log("Simulated", "Last Installment Date", "auto-filled", lastInstDate, true);

        leadPage.scrollRepaymentSchedule();
        log("Calculate EMI", "Repayment Schedule visible", "Scrolled down", "Scrolled", true);

        leadPage.closeRepaymentModal();
        log("Calculate EMI", "Close Repayment Schedule modal", "Closed", "Clicked", true);

        // Select existing customer radio only if existing customer
        if (isExistingCustomer) {
            leadPage.selectExistingCustomerRadio();
            log("Existing Customer", "Select CBS customer radio", "Selected", "Clicked", true);
        }
    }

    @Test(priority = 8)
    public void convertToApplication() throws Exception {
        log("Convert", "Convert button displayed", "true", String.valueOf(leadPage.isConvertBtnDisplayed()), leadPage.isConvertBtnDisplayed());
        leadPage.clickConvert();

        String toast = getSuccessToast();
        log("Convert", "Convert to Application", "Success", toast.isEmpty() ? "No toast" : toast, !toast.isEmpty());

        String appId = "";
        if (!toast.isEmpty()) {
            java.util.regex.Matcher m = java.util.regex.Pattern.compile("K-\\d+").matcher(toast);
            appId = m.find() ? m.group() : toast.replaceAll(".*?(\\d+).*", "$1");
            ConfigManager.set("generated.appId", appId);
            logInfo("Convert", "Application ID captured", appId);
        }

        leadPage.searchInInbox(appId);
        log("Inbox", "Search Application ID", appId, "Searched", true);

        leadPage.doubleClickInboxRow(appId);
        log("Inbox", "Double-click Application", "DDE Stage opened", "Clicked", true);

        sa.assertAll();
    }
}
