package com.agat.los.tests.stage02_dataentry;

import org.openqa.selenium.By;
import org.openqa.selenium.interactions.Actions;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Listeners;
import org.testng.annotations.Test;

import com.agat.los.base.BaseTest;
import com.agat.los.config.ConfigManager;
import com.agat.los.listeners.TestListener;
import com.agat.los.pages.DocumentPage;
import com.agat.los.reporting.ExtentManager;

@Listeners(TestListener.class)
public class DocumentTest extends BaseTest {

    private DocumentPage docPage;
    private String DOC1;
    private String DOC2;

    @BeforeClass
    public void setup() throws Exception {
        driver = com.agat.los.driver.DriverManager.getDriver();
        jse = (org.openqa.selenium.JavascriptExecutor) driver;
        act = new Actions(driver);
        ExtentManager.initReport("Document_DDE");
        ExtentManager.startTest("Stage 2 - Document Upload");
        logInfo("Stage", "Current Stage", "Document");
        docPage = new DocumentPage(driver);
        DOC1 = ConfigManager.get("doc.file1");
        DOC2 = ConfigManager.get("doc.file2");
    }

    @Test(priority = 0)
    public void navigateToApplication() throws Exception {
        navigateToDDE();
        log("Navigation", "Navigate to DDE", "DDE page opened", "Done", true);
        sa.assertAll();
    }

    @Test(priority = 1, enabled = false)
    public void deleteExistingDocuments() throws Exception {
        docPage.clickDocumentTab();
        log("Document", "Click Document tab", "Document section opened", "Clicked", true);

        docPage.switchToDocumentFrame();

        docPage.deleteDocument(1);
        log("Document", "Delete Row 2 (if exists)", "Deleted", "Done", true);

        docPage.deleteDocument(0);
        log("Document", "Delete Row 1 (if exists)", "Deleted", "Done", true);

        sa.assertAll();
    }

    @Test(priority = 2, alwaysRun = true)
    public void uploadDocuments() throws Exception {
        docPage.clickDocumentTab();
        log("Document", "Click Document tab", "Document section opened", "Clicked", true);
        docPage.switchToDocumentFrame();

        boolean uploaded1 = docPage.uploadDocument(0, DOC1);
        log("Document", "Upload Row 1 (Registered Address)", "Uploaded", uploaded1 ? "Success" : "Failed", uploaded1);
        sa.assertTrue(uploaded1, "Row 1 upload failed");

        boolean uploaded2 = docPage.uploadDocument(1, DOC2);
        log("Document", "Upload Row 2 (Pasport+PINFL)", "Uploaded", uploaded2 ? "Success" : "Failed", uploaded2);
        sa.assertTrue(uploaded2, "Row 2 upload failed");

        sa.assertAll();
    }

    @Test(priority = 3, alwaysRun = true)
    public void viewIndividualDocument() throws Exception {
        String url = docPage.viewDocumentInGrid(0);
        boolean isValid = !url.isEmpty() && (url.contains("blob:") || url.contains(".pdf") || url.contains("document") || url.contains("view"));

        log("Document", "Grid View Row 1", "PDF loaded in new window", url.isEmpty() ? "No content" : url, isValid);
        sa.assertTrue(isValid, "Grid view should open document");

        sa.assertAll();
    }

    @Test(priority = 4, alwaysRun = true)
    public void validateExtraInfo() throws Exception {
        String status = docPage.clickExtraInfoAndGetStatus(0);
        String branch = docPage.getExtraInfoBranch();
        String custodian = docPage.getExtraInfoCustodian();

        log("Document", "Extra Info - Status", "PENDING", status, "PENDING".equals(status));
        log("Document", "Extra Info - Branch", "Markaziy", branch, "Markaziy".equals(branch));
        log("Document", "Extra Info - Custodian", "Not empty", custodian, !custodian.isEmpty());

        sa.assertEquals(status, "PENDING", "Status should be PENDING");
        sa.assertEquals(branch, "Markaziy", "Branch should be Markaziy");

        docPage.closeExtraInfoModal();
        log("Document", "Close Extra Info Modal", "Closed", "Done", true);

        sa.assertAll();
    }

    @Test(priority = 5, alwaysRun = true)
    public void viewAllDocuments() throws Exception {
        String url = docPage.viewAllDocuments();
        boolean isValid = !url.isEmpty();

        log("Document", "View All Documents", "PDF loaded in popup", url.isEmpty() ? "No content" : url, isValid);
        sa.assertTrue(isValid, "View All Documents should open with content");

        docPage.switchToMainContent();
        sa.assertAll();
    }

    @Test(priority = 6, alwaysRun = true)
    public void addNewDocument() throws Exception {
        docPage.clickDocumentTab();
        log("Document", "Click Document tab", "Document section opened", "Clicked", true);

        docPage.switchToDocumentFrame();

        try {
            docPage.addNewDocument(DOC1);
            log("Document", "Add New Document - OSV checked, LOANKIT selected, file uploaded", "Saved successfully", "Done", true);
        } finally {
            docPage.switchToMainContent();
        }

        sa.assertAll();
    }
}
