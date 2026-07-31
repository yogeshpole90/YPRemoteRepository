package com.agat.los.tests.stage04_credit;

import org.testng.annotations.BeforeClass;
import org.testng.annotations.Listeners;
import org.testng.annotations.Test;

import com.agat.los.base.BaseTest;
import com.agat.los.listeners.TestListener;
import com.agat.los.reporting.ExtentManager;

@Listeners(TestListener.class)
public class UnderwriterL1Test extends BaseTest {

    @BeforeClass
    public void setup() throws Exception {
        ExtentManager.initReport("UnderwriterL1");
        ExtentManager.startTest("Stage 3 - Credit Approval - Underwriter L1");
        loginAs("USER9", "abcd@1234");
        logInfo("Stage", "Current Stage", "Credit Approval - Underwriter L1");
    }

    @Test(priority = 1)
    public void validateUW1Approval() throws Exception {
        // TODO: UW1 approval flow
        logInfo("UW1", "Status", "Waiting for HTML structure");
    }
}
