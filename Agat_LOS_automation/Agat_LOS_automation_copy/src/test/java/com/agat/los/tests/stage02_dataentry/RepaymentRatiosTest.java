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
import com.agat.los.pages.RepaymentRatiosPage;
import com.agat.los.reporting.ExtentManager;

import java.util.Map;

@Listeners(TestListener.class)
public class RepaymentRatiosTest extends BaseTest {

    private RepaymentRatiosPage rrPage;

    private static final String ROW_NET_MONTHLY_INCOME = "Net Monthly Income Considered";
    private static final String ROW_CURRENT_DTI        = "Current DTI %";
    private static final String ROW_REVISED_DTI        = "Revised DTI %";
    private static final String ROW_NET_DISPOSABLE_INC = "Net Disposable Income";
    private static final String ROW_NET_DISPOSABLE_PCT = "Net Disposable Income %";

    @BeforeClass
    public void setup() throws Exception {
        driver = com.agat.los.driver.DriverManager.getDriver();
        jse = (org.openqa.selenium.JavascriptExecutor) driver;
        act = new Actions(driver);
        ExtentManager.initReport("RepaymentRatios_DDE");
        ExtentManager.startTest("Stage 2 - Repayment Ratios");
        logInfo("Stage", "Current Stage", "Repayment Ratios");
        rrPage = new RepaymentRatiosPage(driver);
    }

    @Test(priority = 0)
    public void navigateToApplication() throws Exception {
        navigateToDDE();
        WebElement incExpTab = driver.findElement(By.xpath("//a[contains(@href,'activeTab=INCOME AND EXPENSES')]"));
        jse.executeScript("arguments[0].scrollIntoView({behavior:'smooth', block:'center'})", incExpTab);
        Thread.sleep(500);
        jse.executeScript("arguments[0].click()", incExpTab);
        Thread.sleep(2000);
        log("Navigation", "Navigate to Income & Expenses", "Done", "Done", true);
        sa.assertAll();
    }

    @Test(priority = 1, alwaysRun = true)
    public void validateRepaymentRatios() throws Exception {
        rrPage.clickRepaymentRatiosTab();
        log("RepaymentRatios", "Click Repayment Ratios tab", "Tab opened", "Clicked", true);

        rrPage.switchToRatiosFrame();

        Map<String, Double> actual = rrPage.readRatiosMap();

        double actNetMonthly = actual.getOrDefault(ROW_NET_MONTHLY_INCOME, 0.0);
        double actCurrentDti = actual.getOrDefault(ROW_CURRENT_DTI,        0.0);
        double actRevisedDti = actual.getOrDefault(ROW_REVISED_DTI,        0.0);
        double actNetDispInc = actual.getOrDefault(ROW_NET_DISPOSABLE_INC, 0.0);
        double actNetDispPct = actual.getOrDefault(ROW_NET_DISPOSABLE_PCT, 0.0);

        logInfo("RepaymentRatios", "Net Monthly Income",    String.valueOf(actNetMonthly));
        logInfo("RepaymentRatios", "Current DTI %",         String.valueOf(actCurrentDti));
        logInfo("RepaymentRatios", "Revised DTI %",         String.valueOf(actRevisedDti));
        logInfo("RepaymentRatios", "Net Disposable Income", String.valueOf(actNetDispInc));
        logInfo("RepaymentRatios", "Net Disposable Inc %",  String.valueOf(actNetDispPct));

        if (actNetMonthly <= 0) {
            logInfo("RepaymentRatios", "Validation Skipped", "Net Monthly Income is 0");
            rrPage.switchToMainContent();
            sa.assertAll();
            return;
        }

        // ---- Formulas (Excel): ----
        // Current DTI %         = totalCurrentObligations / netMonthly * 100       → log only
        // Revised DTI %         = (totalCurrentObligations + newEMI) / netMonthly * 100
        //                         must be >= currentDTI (new EMI adds to obligations)
        // Net Disposable Income = netMonthly - totalObligations - expenses
        //                         cross-check: netDispInc / netMonthly * 100 == netDispPct
        // Net Disposable Inc %  = netDispInc / netMonthly * 100
        //                         cross-check: netMonthly * netDispPct / 100 == netDispInc

        // 1. Net Monthly Income — not empty
        log("RepaymentRatios", ROW_NET_MONTHLY_INCOME,
                "Not empty", String.valueOf(actNetMonthly), actNetMonthly > 0);

        // 2. Current DTI — log only
        log("RepaymentRatios", ROW_CURRENT_DTI,
                "Displayed", actCurrentDti + "%", true);

        // 3. Revised DTI >= Current DTI
        boolean revisedDtiValid = actRevisedDti >= actCurrentDti;
        log("RepaymentRatios", ROW_REVISED_DTI,
                ">= Current DTI (" + actCurrentDti + "%)", actRevisedDti + "%", revisedDtiValid);
        sa.assertTrue(revisedDtiValid,
                "Revised DTI should be >= Current DTI. current=" + actCurrentDti + " revised=" + actRevisedDti);

        // 4. Net Disposable Income cross-check: netMonthly * netDispPct / 100 == netDispInc
        double expNetDispInc = round2(actNetMonthly * actNetDispPct / 100.0);
        boolean netDispIncValid = approxEqual(actNetDispInc, expNetDispInc);
        log("RepaymentRatios", ROW_NET_DISPOSABLE_INC,
                String.valueOf(expNetDispInc), String.valueOf(actNetDispInc), netDispIncValid);
        sa.assertTrue(netDispIncValid,
                "Net Disposable Income mismatch: expected=" + expNetDispInc + " actual=" + actNetDispInc);

        // 5. Net Disposable Income % cross-check: netDispInc / netMonthly * 100 == netDispPct
        double expNetDispPct = round2(actNetDispInc / actNetMonthly * 100.0);
        boolean netDispPctValid = approxEqual(actNetDispPct, expNetDispPct);
        log("RepaymentRatios", ROW_NET_DISPOSABLE_PCT,
                expNetDispPct + "%", actNetDispPct + "%", netDispPctValid);
        sa.assertTrue(netDispPctValid,
                "Net Disposable Income % mismatch: expected=" + expNetDispPct + " actual=" + actNetDispPct);

        rrPage.switchToMainContent();
        sa.assertAll();
    }

    private double round2(double val) {
        return Math.round(val * 100.0) / 100.0;
    }

    // ±0.1 for % values, ±0.5% relative for large amounts
    private boolean approxEqual(double a, double b) {
        if (Math.abs(b) < 1.0) return Math.abs(a - b) <= 0.1;
        return Math.abs(a - b) / Math.abs(b) <= 0.005;
    }
}
