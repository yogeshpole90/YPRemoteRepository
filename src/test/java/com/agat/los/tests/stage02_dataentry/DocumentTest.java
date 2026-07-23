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
        driver.findElement(By.cssSelector("a.item-summary")).click();
        Thread.sleep(3000);

        String appId = ConfigManager.get("generated.appId");
        var searchBox = driver.findElement(By.cssSelector("#dt-authdata_filter input[type='search']"));
        searchBox.clear();
        searchBox.sendKeys(appId);
        Thread.sleep(2000);

        act.doubleClick(driver.findElement(By.cssSelector("#dt-authdata tbody tr:first-child td:nth-child(2)"))).build().perform();
        Thread.sleep(3000);

        var ddeLink = driver.findElement(By.xpath("//td[@class='stage-child']//a[contains(@href,'stageName=DETAILED DATA ENTRY')]"));
        jse.executeScript("arguments[0].scrollIntoView({behavior:'smooth', block:'center'})", ddeLink);
        Thread.sleep(1500);
        jse.executeScript("arguments[0].click()", ddeLink);
        Thread.sleep(3000);

        log("Navigation", "Navigate to DDE", "DDE page opened", "Done", true);
        sa.assertAll();
    }

    @Test(priority = 1, enabled = false)
    public void deleteExistingDocuments() throws Exception {
        docPage.clickDocumentTab();
        log("Document", "Click Document tab", "Document section opened", "Clicked", true);

        docPage.switchToDocumentFrame();

        // Delete Row 2 first (reverse order to avoid index shift)
        docPage.deleteDocument(1);
        log("Document", "Delete Row 2 (if exists)", "Deleted", "Done", true);

        // Delete Row 1
        docPage.deleteDocument(0);
        log("Document", "Delete Row 1 (if exists)", "Deleted", "Done", true);

        sa.assertAll();
    }

    @Test(priority = 2)
    public void uploadDocuments() throws Exception {
        docPage.clickDocumentTab();
        log("Document", "Click Document tab", "Document section opened", "Clicked", true);
        docPage.switchToDocumentFrame();

        // Upload Row 1
        boolean uploaded1 = docPage.uploadDocument(0, DOC1);
        log("Document", "Upload Row 1 (Registered Address)", "Uploaded", uploaded1 ? "Success" : "Failed", uploaded1);
        sa.assertTrue(uploaded1, "Row 1 upload failed");

        // Upload Row 2
        boolean uploaded2 = docPage.uploadDocument(1, DOC2);
        log("Document", "Upload Row 2 (Pasport+PINFL)", "Uploaded", uploaded2 ? "Success" : "Failed", uploaded2);
        sa.assertTrue(uploaded2, "Row 2 upload failed");

        sa.assertAll();
    }

    @Test(priority = 3)
    public void viewIndividualDocument() throws Exception {
        // Grid View - Row 1
        String url = docPage.viewDocumentInGrid(0);
        boolean isValid = !url.isEmpty() && (url.contains("blob:") || url.contains(".pdf") || url.contains("document") || url.contains("view"));

        log("Document", "Grid View Row 1", "PDF loaded in new window", url.isEmpty() ? "No content" : url, isValid);
        sa.assertTrue(isValid, "Grid view should open document");

        sa.assertAll();
    }

    @Test(priority = 4)
    public void validateExtraInfo() throws Exception {
        // Extra Info for Row 1
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

    @Test(priority = 5)
    public void viewAllDocuments() throws Exception {
        // View All Documents button
        String url = docPage.viewAllDocuments();
        boolean isValid = !url.isEmpty();

        log("Document", "View All Documents", "PDF loaded in popup", url.isEmpty() ? "No content" : url, isValid);
        sa.assertTrue(isValid, "View All Documents should open with content");

        docPage.switchToMainContent();
        sa.assertAll();
    }
}
