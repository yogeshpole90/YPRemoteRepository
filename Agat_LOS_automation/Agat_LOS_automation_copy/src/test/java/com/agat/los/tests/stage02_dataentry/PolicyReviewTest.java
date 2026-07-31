package com.agat.los.tests.stage02_dataentry;

import org.openqa.selenium.By;
import org.openqa.selenium.interactions.Actions;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Listeners;
import org.testng.annotations.Test;

import com.agat.los.base.BaseTest;
import com.agat.los.config.ConfigManager;
import com.agat.los.listeners.TestListener;
import com.agat.los.pages.PolicyReviewPage;
import com.agat.los.reporting.ExtentManager;

import java.util.List;

@Listeners(TestListener.class)
public class PolicyReviewTest extends BaseTest {

    private PolicyReviewPage policyPage;

    @BeforeClass
    public void setup() throws Exception {
        driver = com.agat.los.driver.DriverManager.getDriver();
        jse = (org.openqa.selenium.JavascriptExecutor) driver;
        act = new Actions(driver);
        ExtentManager.initReport("PolicyReview_DDE");
        ExtentManager.startTest("Stage 2 - Policy Review");
        logInfo("Stage", "Current Stage", "Policy Review");
        policyPage = new PolicyReviewPage(driver);
    }

    @Test(priority = 0)
    public void navigateToApplication() throws Exception {
        navigateToDDE();
        log("Navigation", "Navigate to DDE", "DDE page opened", "Done", true);
        sa.assertAll();
    }

    @Test(priority = 1, alwaysRun = true)
    public void runPolicyCheck() throws Exception {
        policyPage.clickPolicyReviewTab();
        log("PolicyReview", "Click Policy Review tab", "Section opened", "Clicked", true);

        policyPage.switchToPolicyCheckFrame();

        // Click Policy Check button
        policyPage.clickPolicyCheckButton();
        log("PolicyCheck", "Click Policy Check button", "Policy executed", "Clicked", true);

        // Get Overall Status
        String overallStatus = policyPage.getOverallStatus();
        log("PolicyCheck", "Overall Status", "Not empty", overallStatus, !overallStatus.isEmpty());

        // Get counts
        int[] counts = policyPage.getPolicyCounts();
        log("PolicyCheck", "Pass Count", ">0", String.valueOf(counts[0]), counts[0] > 0);
        log("PolicyCheck", "Fail Count", "Noted", String.valueOf(counts[1]), true);
        log("PolicyCheck", "Not Applicable Count", "Noted", String.valueOf(counts[2]), true);

        // Log failed policies
        List<String> failedPolicies = policyPage.getFailedPolicies();
        if (failedPolicies.isEmpty()) {
            log("PolicyCheck", "Failed Policies", "None", "All Passed", true);
        } else {
            for (String failed : failedPolicies) {
                log("PolicyCheck", "FAILED POLICY", "Noted", failed, true);
            }
        }

        policyPage.switchToMainContent();
        sa.assertAll();
    }

    @Test(priority = 2, alwaysRun = true)
    public void runCreditScoreCard() throws Exception {
        policyPage.clickCreditScoreTab();
        log("CreditScore", "Click Credit Score Card tab", "Section opened", "Clicked", true);

        policyPage.switchToCreditScoreFrame();

        // Click Internal Credit Score button
        policyPage.clickInternalCreditScoreButton();
        log("CreditScore", "Click Internal Credit Score button", "Score calculated", "Clicked", true);

        // Show 25 entries to see all rows
        policyPage.showAllEntries();
        log("CreditScore", "Show 25 entries", "All rows visible", "Done", true);

        // Validate Score
        String score = policyPage.getScoreValue();
        log("CreditScore", "Score Value", "Not empty", score, !score.isEmpty());
        sa.assertTrue(!score.isEmpty(), "Credit score should not be empty");

        // Validate Risk Label
        String riskLabel = policyPage.getRiskLabel();
        log("CreditScore", "Risk Label", "Not empty", riskLabel, !riskLabel.isEmpty());
        sa.assertTrue(!riskLabel.isEmpty(), "Risk label should not be empty");

        // Validate key parameter values from input data
        String incomeValue = policyPage.getParameterValue("устойчивость дохода");
        log("CreditScore", "Income Parameter", "125000000", incomeValue, incomeValue.contains("125000000"));

        String employmentType = policyPage.getParameterValue("Характер трудовой");
        log("CreditScore", "Employment Type", "Salaried", employmentType, employmentType.contains("Salaried"));

        String employerType = policyPage.getParameterValue("Тип работодателя");
        log("CreditScore", "Employer Type", "Permanent", employerType, employerType.contains("Permanent"));

        String maritalStatus = policyPage.getParameterValue("Семейное положение");
        log("CreditScore", "Marital Status", "Not Married", maritalStatus, maritalStatus.contains("Not Married"));

        String education = policyPage.getParameterValue("Уровень образования");
        log("CreditScore", "Education", "High Education", education, education.contains("High Education"));

        String nationality = policyPage.getParameterValue("Гражданство");
        log("CreditScore", "Nationality", "Uzbek", nationality, nationality.contains("Uzbek"));

        policyPage.switchToMainContent();
        sa.assertAll();
    }
}
