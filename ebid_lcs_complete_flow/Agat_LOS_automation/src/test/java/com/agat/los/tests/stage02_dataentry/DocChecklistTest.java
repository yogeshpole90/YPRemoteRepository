package com.agat.los.tests.stage02_dataentry;

import org.testng.annotations.BeforeClass;
import org.testng.annotations.Listeners;
import org.testng.annotations.Test;

import com.agat.los.base.BaseTest;
import com.agat.los.listeners.TestListener;
import com.agat.los.reporting.ExtentManager;

@Listeners(TestListener.class)
public class DocChecklistTest extends BaseTest {

    @BeforeClass
    public void setup() throws Exception {
        ExtentManager.initReport("DocChecklist");
        ExtentManager.startTest("Stage 2 - Document Checklist");
        logInfo("Stage", "Current Stage", "Detail Data Entry - Document Checklist");
    }

    @Test(priority = 1)
    public void validateDocChecklist() throws Exception {
        // TODO: Upload mandatory documents
        logInfo("DocChecklist", "Status", "Waiting for HTML structure");
    }
}
