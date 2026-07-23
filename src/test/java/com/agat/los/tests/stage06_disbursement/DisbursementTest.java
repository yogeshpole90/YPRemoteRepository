package com.agat.los.tests.stage06_disbursement;

import org.testng.annotations.BeforeClass;
import org.testng.annotations.Listeners;
import org.testng.annotations.Test;

import com.agat.los.base.BaseTest;
import com.agat.los.listeners.TestListener;
import com.agat.los.reporting.ExtentManager;

@Listeners(TestListener.class)
public class DisbursementTest extends BaseTest {

    @BeforeClass
    public void setup() throws Exception {
        ExtentManager.initReport("Disbursement");
        ExtentManager.startTest("Stage 6 - Disbursement");
        logInfo("Stage", "Current Stage", "Disbursement");
    }

    @Test(priority = 1)
    public void validateDisbursement() throws Exception {
        // TODO: Disbursement flow
        logInfo("Disbursement", "Status", "Waiting for HTML structure");
    }
}
