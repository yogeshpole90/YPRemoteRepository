package com.agat.los.tests.stage04_credit;

import org.testng.annotations.BeforeClass;
import org.testng.annotations.Listeners;
import org.testng.annotations.Test;

import com.agat.los.base.BaseTest;
import com.agat.los.listeners.TestListener;
import com.agat.los.reporting.ExtentManager;

@Listeners(TestListener.class)
public class TeamLeaderTest extends BaseTest {

    @BeforeClass
    public void setup() throws Exception {
        ExtentManager.initReport("TeamLeaderBranch");
        ExtentManager.startTest("Stage 3 - Credit Approval - Team Leader Branch");
        // Login as TL_B user
        loginAs("USER5", "abcd@1234");
        logInfo("Stage", "Current Stage", "Credit Approval - Team Leader Branch Manager");
    }

    @Test(priority = 1)
    public void validateTeamLeaderApproval() throws Exception {
        // TODO: TL_B approval flow
        logInfo("TL_B", "Status", "Waiting for HTML structure");
    }
}
