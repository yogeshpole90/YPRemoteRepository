package com.agat.los.tests.stage02_dataentry;

import org.testng.annotations.BeforeClass;
import org.testng.annotations.Listeners;
import org.testng.annotations.Test;

import com.agat.los.base.BaseTest;
import com.agat.los.listeners.TestListener;
import com.agat.los.reporting.ExtentManager;

@Listeners(TestListener.class)
public class AddressTest extends BaseTest {

    @BeforeClass
    public void setup() throws Exception {
        ExtentManager.initReport("Address");
        ExtentManager.startTest("Stage 2 - Address Details");
        logInfo("Stage", "Current Stage", "Detail Data Entry - Address");
    }

    @Test(priority = 1)
    public void validateAddress() throws Exception {
        // TODO: Fill and validate address fields
        logInfo("Address", "Status", "Waiting for HTML structure");
    }
}
