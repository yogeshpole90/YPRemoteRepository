package com.agat.los.tests.stage03_aml;

import org.testng.annotations.BeforeClass;
import org.testng.annotations.Listeners;
import org.testng.annotations.Test;

import com.agat.los.base.BaseTest;
import com.agat.los.listeners.TestListener;
import com.agat.los.reporting.ExtentManager;

@Listeners(TestListener.class)
public class AMLVerificationTest extends BaseTest {

    @BeforeClass
    public void setup() throws Exception {
        ExtentManager.initReport("AMLVerification");
        ExtentManager.startTest("Stage 3 - AML Verification");
        logInfo("Stage", "Current Stage", "AML Verification");
        // Login as IST user for AML stage
        // loginAs("USER7", "abcd@1234");
    }

    @Test(priority = 1)
    public void validateAML() throws Exception {
        // TODO: AML check and approve
        logInfo("AML", "Status", "Waiting for HTML structure");
    }
}
