package com.agat.los.tests.stage04_credit;

import org.testng.annotations.BeforeClass;
import org.testng.annotations.Listeners;
import org.testng.annotations.Test;

import com.agat.los.base.BaseTest;
import com.agat.los.listeners.TestListener;
import com.agat.los.reporting.ExtentManager;

@Listeners(TestListener.class)
public class UnderwriterL2Test extends BaseTest {

    @BeforeClass
    public void setup() throws Exception {
        ExtentManager.initReport("UnderwriterL2");
        ExtentManager.startTest("Stage 3 - Credit Approval - Underwriter L2");
        loginAs("USER12", "abcd@1234");
        logInfo("Stage", "Current Stage", "Credit Approval - Underwriter L2");
    }

    @Test(priority = 1)
    public void validateUW2Approval() throws Exception {
        // TODO: UW2 approval flow
        logInfo("UW2", "Status", "Waiting for HTML structure");
    }
}
