package com.agat.los.pages;

import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

import java.util.ArrayList;
import java.util.List;

public class PolicyReviewPage {

    private WebDriver driver;
    private JavascriptExecutor jse;

    // ========== Navigation ==========
    private By policyReviewTab = By.xpath("//a[contains(@href,'activeTab=POLICY REVIEW')]");

    // ========== Policy Check Frame ==========
    @FindBy(id = "viewBHCFrame") private WebElement policyCheckFrame;
    @FindBy(id = "generatePolCheck") private WebElement policyCheckBtn;
    @FindBy(id = "finalStatus") private WebElement finalStatusInput;

    // ========== Credit Score Frame ==========
    private By creditScoreTab = By.xpath("//a[contains(@onclick,'BreViewScreenFrame')]");
    @FindBy(id = "BreViewScreenFrame") private WebElement creditScoreFrame;
    @FindBy(id = "generateBRE") private WebElement creditScoreBtn;

    // ========== Constructor ==========
    public PolicyReviewPage(WebDriver driver) {
        this.driver = driver;
        this.jse = (JavascriptExecutor) driver;
        PageFactory.initElements(driver, this);
    }

    // ========== Navigation ==========
    public void clickPolicyReviewTab() throws InterruptedException {
        WebElement tab = driver.findElement(policyReviewTab);
        jse.executeScript("arguments[0].scrollIntoView({behavior:'smooth', block:'center'})", tab);
        Thread.sleep(1000);
        jse.executeScript("arguments[0].click()", tab);
        Thread.sleep(3000);
    }

    public void switchToMainContent() { driver.switchTo().defaultContent(); }

    // ==================== POLICY CHECK ====================

    public void switchToPolicyCheckFrame() throws InterruptedException {
        jse.executeScript("arguments[0].scrollIntoView({behavior:'smooth', block:'center'})", policyCheckFrame);
        Thread.sleep(1000);
        driver.switchTo().frame(policyCheckFrame);
        Thread.sleep(2000);
    }

    public void clickPolicyCheckButton() throws InterruptedException {
        jse.executeScript("arguments[0].scrollIntoView({behavior:'smooth', block:'center'})", policyCheckBtn);
        Thread.sleep(500);
        jse.executeScript("arguments[0].click()", policyCheckBtn);
        Thread.sleep(3000);
        // Handle "policy deviated" alert if appears
        try { driver.switchTo().alert().accept(); Thread.sleep(1000); } catch (Exception e) {}
    }

    public String getOverallStatus() {
        return finalStatusInput.getAttribute("value").trim();
    }

    /**
     * Returns list of failed policy rows as "Category | Dimension | Criteria | Status"
     */
    public List<String> getFailedPolicies() {
        List<String> failed = new ArrayList<>();
        List<WebElement> rows = driver.findElements(By.xpath("//table[@id='tblData']//tr[td]"));
        for (WebElement row : rows) {
            List<WebElement> cells = row.findElements(By.tagName("td"));
            if (cells.size() >= 6) {
                String policyType = cells.get(1).getText().trim();
                if (policyType.equalsIgnoreCase("Fail")) {
                    String category = cells.get(0).getText().trim();
                    String dimension = cells.get(2).getText().trim();
                    String criteria = cells.get(4).getText().trim();
                    String status = cells.get(5).getText().trim();
                    failed.add(category + " | " + dimension + " | " + criteria + " | " + status);
                }
            }
        }
        return failed;
    }

    /**
     * Returns count of passed, failed, and not applicable policies
     */
    public int[] getPolicyCounts() {
        int pass = 0, fail = 0, na = 0;
        List<WebElement> rows = driver.findElements(By.xpath("//table[@id='tblData']//tr[td]"));
        for (WebElement row : rows) {
            List<WebElement> cells = row.findElements(By.tagName("td"));
            if (cells.size() >= 2) {
                String type = cells.get(1).getText().trim();
                if (type.equalsIgnoreCase("Pass")) pass++;
                else if (type.equalsIgnoreCase("Fail")) fail++;
                else if (type.contains("Not Applicable")) na++;
            }
        }
        return new int[]{pass, fail, na};
    }

    // ==================== CREDIT SCORE CARD ====================

    public void clickCreditScoreTab() throws InterruptedException {
        switchToMainContent();
        WebElement tab = driver.findElement(creditScoreTab);
        jse.executeScript("arguments[0].scrollIntoView({behavior:'smooth', block:'center'})", tab);
        Thread.sleep(1000);
        jse.executeScript("arguments[0].click()", tab);
        Thread.sleep(3000);
    }

    public void switchToCreditScoreFrame() throws InterruptedException {
        jse.executeScript("arguments[0].scrollIntoView({behavior:'smooth', block:'center'})", creditScoreFrame);
        Thread.sleep(1000);
        driver.switchTo().frame(creditScoreFrame);
        Thread.sleep(2000);
    }

    public void clickInternalCreditScoreButton() throws InterruptedException {
        jse.executeScript("arguments[0].scrollIntoView({behavior:'smooth', block:'center'})", creditScoreBtn);
        Thread.sleep(500);
        jse.executeScript("arguments[0].click()", creditScoreBtn);
        Thread.sleep(3000);
        // Handle alert if any
        try { driver.switchTo().alert().accept(); Thread.sleep(1000); } catch (Exception e) {}
    }

    public void showAllEntries() throws InterruptedException {
        jse.executeScript("$(\"select[name='dt-authdata_length']\").val('25').trigger('change')");
        Thread.sleep(2000);
    }

    public String getScoreValue() {
        try { return driver.findElement(By.cssSelector(".score-value")).getText().trim(); } catch (Exception e) { return ""; }
    }

    public String getRiskLabel() {
        try { return driver.findElement(By.cssSelector(".risk-label-output")).getText().trim(); } catch (Exception e) { return ""; }
    }

    /**
     * Returns parameter value for a given rating parameter name from credit score table
     */
    public String getParameterValue(String parameterName) {
        List<WebElement> rows = driver.findElements(By.xpath("//table[@id='dt-authdata']//tbody/tr"));
        for (WebElement row : rows) {
            List<WebElement> cells = row.findElements(By.tagName("td"));
            if (cells.size() >= 3 && cells.get(1).getText().trim().contains(parameterName)) {
                return cells.get(2).getText().trim();
            }
        }
        return "";
    }

    /**
     * Returns all score card rows as list: "Dimension | Parameter | Value | Score"
     */
    public List<String> getAllScoreCardRows() {
        List<String> rows = new ArrayList<>();
        List<WebElement> tableRows = driver.findElements(By.xpath("//table[@id='dt-authdata']//tbody/tr"));
        for (WebElement row : tableRows) {
            List<WebElement> cells = row.findElements(By.tagName("td"));
            if (cells.size() >= 6) {
                String dimension = cells.get(0).getText().trim();
                String parameter = cells.get(1).getText().trim();
                String value = cells.get(2).getText().trim();
                String dimScore = cells.get(5).getText().trim();
                rows.add(dimension + " | " + parameter + " | " + value + " | " + dimScore);
            }
        }
        return rows;
    }
}
