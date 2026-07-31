package com.agat.los.tests.stage04_credit;

import org.testng.annotations.BeforeClass;
import org.testng.annotations.Listeners;
import org.testng.annotations.Test;

import com.agat.los.base.BaseTest;
import com.agat.los.listeners.TestListener;
import com.agat.los.reporting.ExtentManager;

@Listeners(TestListener.class)
public class CreditCommitteeTest extends BaseTest {

    @BeforeClass
    public void setup() throws Exception {
        ExtentManager.initReport("CreditCommittee");
        ExtentManager.startTest("Stage 3 - Credit Approval - Credit Committee");
        loginAs("USER19", "abcd@1234");
        logInfo("Stage", "Current Stage", "Credit Approval - Credit Committee");
    }

    @Test(priority = 1)
    public void validateCreditCommittee() throws Exception {
        // TODO: Credit Committee approval flow
        logInfo("CC", "Status", "Waiting for HTML structure");
    }
}
