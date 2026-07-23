package com.agat.los.pages;

import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

import java.util.ArrayList;
import java.util.List;
import java.util.Set;

public class DocumentPage {

    private WebDriver driver;
    private JavascriptExecutor jse;

    // ========== Navigation ==========
    private By documentTab = By.xpath("//a[contains(@href,'activeTab=DOCUMENT')]");

    // ========== Frame ==========
    @FindBy(id = "documentUploadLOSCollectionFrame") private WebElement documentFrame;

    // ========== Upload Modal ==========
    @FindBy(id = "documentDataModal") private WebElement fileInput;
    @FindBy(id = "saveDocModal") private WebElement saveDocBtn;

    // ========== View All Documents ==========
    private By viewAllDocsBtn = By.xpath("//button[contains(@onclick,'viewAllDocs')]");

    // ========== Constructor ==========
    public DocumentPage(WebDriver driver) {
        this.driver = driver;
        this.jse = (JavascriptExecutor) driver;
        PageFactory.initElements(driver, this);
    }

    // ========== Navigation ==========
    public void clickDocumentTab() throws InterruptedException {
        WebElement tab = driver.findElement(documentTab);
        jse.executeScript("arguments[0].scrollIntoView({behavior:'smooth', block:'center'})", tab);
        Thread.sleep(1000);
        jse.executeScript("arguments[0].click()", tab);
        Thread.sleep(3000);
    }

    public void switchToDocumentFrame() throws InterruptedException {
        jse.executeScript("arguments[0].scrollIntoView({behavior:'smooth', block:'center'})", documentFrame);
        Thread.sleep(1000);
        driver.switchTo().frame(documentFrame);
        Thread.sleep(2000);
    }

    public void switchToMainContent() { driver.switchTo().defaultContent(); }

    // ========== Scroll element into view ==========
    private void scrollToElement(WebElement el) throws InterruptedException {
        jse.executeScript("arguments[0].scrollIntoView({behavior:'smooth', block:'center', inline:'center'})", el);
        Thread.sleep(1000);
    }

    // ========== Delete Document (if exists) ==========
    public boolean deleteDocument(int rowIndex) throws InterruptedException {
        List<WebElement> deleteLinks = driver.findElements(By.xpath("//table//tbody/tr/td[11]/a"));
        if (rowIndex >= deleteLinks.size()) return false;

        WebElement deleteLink = deleteLinks.get(rowIndex);
        scrollToElement(deleteLink);
        jse.executeScript("arguments[0].click()", deleteLink);
        Thread.sleep(2000);

        // Confirm delete
        try {
            WebElement confirmBtn = driver.findElement(By.cssSelector(".swal2-confirm, #submitForm, .swal-button--confirm"));
            jse.executeScript("arguments[0].click()", confirmBtn);
            Thread.sleep(2000);
        } catch (Exception e) {}
        try { driver.switchTo().alert().accept(); Thread.sleep(1000); } catch (Exception e) {}

        return true;
    }

    // ========== Upload Document ==========
    public boolean uploadDocument(int rowIndex, String filePath) throws InterruptedException {
        List<WebElement> uploadLinks = driver.findElements(By.xpath("//table//tbody/tr/td[8]/a"));
        if (rowIndex >= uploadLinks.size()) return false;

        // Re-fetch to avoid stale reference
        jse.executeScript("arguments[0].scrollIntoView({behavior:'smooth', block:'center', inline:'center'})",
            driver.findElements(By.xpath("//table//tbody/tr/td[8]/a")).get(rowIndex));
        Thread.sleep(500);
        jse.executeScript("arguments[0].click()",
            driver.findElements(By.xpath("//table//tbody/tr/td[8]/a")).get(rowIndex));
        Thread.sleep(2000);

        // Check if "already uploaded" validation alert
        try {
            WebElement swalPopup = driver.findElement(By.cssSelector(".swal2-popup, .swal-overlay--show-modal"));
            if (swalPopup.isDisplayed()) {
                WebElement okBtn = driver.findElement(By.cssSelector(".swal2-confirm, .swal-button"));
                jse.executeScript("arguments[0].click()", okBtn);
                Thread.sleep(1000);
                return false;
            }
        } catch (Exception e) {}

        // Modal opened - upload file
        fileInput.sendKeys(filePath);
        Thread.sleep(2000);

        jse.executeScript("arguments[0].click()", saveDocBtn);
        Thread.sleep(3000);

        // Handle alert after save
        try { driver.switchTo().alert().accept(); Thread.sleep(1000); } catch (Exception e) {}

        return true;
    }

    // ========== Extra Info ==========
    public String clickExtraInfoAndGetStatus(int rowIndex) throws InterruptedException {
        List<WebElement> extraInfoBtns = driver.findElements(By.cssSelector("button.more-info-btn"));
        if (rowIndex >= extraInfoBtns.size()) return "";

        scrollToElement(extraInfoBtns.get(rowIndex));
        jse.executeScript("arguments[0].click()", extraInfoBtns.get(rowIndex));
        Thread.sleep(2000);

        String status = "";
        try {
            status = driver.findElement(By.id("status1")).getText().trim();
        } catch (Exception e) {}
        return status;
    }

    public String getExtraInfoBranch() {
        try { return driver.findElement(By.id("branch1")).getText().trim(); } catch (Exception e) { return ""; }
    }

    public String getExtraInfoCustodian() {
        try { return driver.findElement(By.id("documentCustodian1")).getText().trim(); } catch (Exception e) { return ""; }
    }

    public void closeExtraInfoModal() throws InterruptedException {
        // Wait and close the modal properly
        Thread.sleep(500);
        WebElement closeBtn = driver.findElement(By.xpath("//div[contains(@class,'modal') and contains(@style,'display: block')]//button[@data-dismiss='modal']"));
        jse.executeScript("arguments[0].click()", closeBtn);
        Thread.sleep(1500);
        // Ensure modal is fully closed
        jse.executeScript("$('.modal').modal('hide')");
        Thread.sleep(1000);
    }

    // ========== Grid View (Individual Doc) ==========
    public String viewDocumentInGrid(int rowIndex) throws InterruptedException {
        String originalWindow = driver.getWindowHandle();

        List<WebElement> viewLinks = driver.findElements(By.xpath("//table//tbody/tr/td[9]/a"));
        if (rowIndex >= viewLinks.size()) return "";

        scrollToElement(viewLinks.get(rowIndex));
        jse.executeScript("arguments[0].click()", viewLinks.get(rowIndex));
        Thread.sleep(3000);

        // Switch to new window
        String newWindow = switchToNewWindow(originalWindow);
        if (newWindow.isEmpty()) return "";

        String url = driver.getCurrentUrl();

        // Close and switch back
        driver.close();
        driver.switchTo().window(originalWindow);
        Thread.sleep(1000);

        // Switch back to frame
        driver.switchTo().frame(documentFrame);
        Thread.sleep(1000);

        return url;
    }

    // ========== View All Documents ==========
    public String viewAllDocuments() throws InterruptedException {
        String originalWindow = driver.getWindowHandle();

        WebElement btn = driver.findElement(viewAllDocsBtn);
        jse.executeScript("arguments[0].scrollIntoView({behavior:'smooth', block:'center'})", btn);
        Thread.sleep(500);
        jse.executeScript("arguments[0].click()", btn);
        Thread.sleep(3000);

        // Switch to new window
        String newWindow = switchToNewWindow(originalWindow);
        if (newWindow.isEmpty()) return "";

        String url = driver.getCurrentUrl();
        String pageSource = driver.getPageSource();
        boolean hasContent = !pageSource.isEmpty() && pageSource.length() > 100;

        // Close and switch back
        driver.close();
        driver.switchTo().window(originalWindow);
        Thread.sleep(1000);

        // Switch back to frame
        driver.switchTo().frame(documentFrame);
        Thread.sleep(1000);

        return hasContent ? url : "";
    }

    // ========== Utility ==========
    private String switchToNewWindow(String originalWindow) throws InterruptedException {
        Set<String> handles = driver.getWindowHandles();
        for (String handle : handles) {
            if (!handle.equals(originalWindow)) {
                driver.switchTo().window(handle);
                Thread.sleep(2000);
                return handle;
            }
        }
        return "";
    }
}
