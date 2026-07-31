package com.agat.los.pages;

import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;

public class RepaymentRatiosPage {

    private WebDriver driver;
    private JavascriptExecutor jse;

    // ========== Navigation ==========
    private By repaymentRatiosTab = By.xpath("//a[contains(@onclick,'viewrepaymentratiosFrame')]");

    // ========== Frame ==========
    @FindBy(id = "viewrepaymentratiosFrame") private WebElement ratiosFrame;

    // ========== Constructor ==========
    public RepaymentRatiosPage(WebDriver driver) {
        this.driver = driver;
        this.jse = (JavascriptExecutor) driver;
        PageFactory.initElements(driver, this);
    }

    // ========== Navigation ==========
    public void clickRepaymentRatiosTab() throws InterruptedException {
        WebElement tab = driver.findElement(repaymentRatiosTab);
        jse.executeScript("arguments[0].scrollIntoView({behavior:'smooth', block:'center'})", tab);
        Thread.sleep(1000);
        jse.executeScript("arguments[0].click()", tab);
        Thread.sleep(3000);
    }

    public void switchToRatiosFrame() throws InterruptedException {
        jse.executeScript("arguments[0].scrollIntoView({behavior:'smooth', block:'center'})", ratiosFrame);
        Thread.sleep(1000);
        driver.switchTo().frame(ratiosFrame);
        Thread.sleep(2000);
    }

    public void switchToMainContent() {
        driver.switchTo().defaultContent();
    }

    // ========== Read table: category -> first column value (Main Applicant) ==========
    // Returns map: "Net Monthly Income Considered" -> 0.0, etc.
    public Map<String, Double> readRatiosMap() {
        Map<String, Double> map = new LinkedHashMap<>();
        try {
            List<WebElement> rows = driver.findElements(By.cssSelector("#dt-authdata tbody tr"));
            for (WebElement tr : rows) {
                List<WebElement> cells = tr.findElements(By.tagName("td"));
                if (cells.size() < 2) continue;
                String category = cells.get(0).getText().trim();
                String rawVal   = cells.get(1).getText().trim()
                        .replace("%", "").replace(",", "").trim();
                double val = 0.0;
                try { val = Double.parseDouble(rawVal); } catch (Exception ignored) {}
                map.put(category, val);
            }
        } catch (Exception e) {
            System.out.println("ERROR reading ratios table: " + e.getMessage());
        }
        return map;
    }

    // ========== Read hidden input fields (formula inputs) ==========
    public double getHiddenDouble(String id) {
        try {
            String val = driver.findElement(By.id(id)).getAttribute("value");
            if (val == null || val.isBlank()) return 0.0;
            return Double.parseDouble(val.trim());
        } catch (Exception e) { return 0.0; }
    }

    // Convenience: read all formula inputs for Primary applicant
    public double getNetMonthlyIncome()    { return getHiddenDouble("netMntlyIncConsi_Primry"); }
    public double getTotDebtDeduction()    { return getHiddenDouble("totDebtDeduction_Primry"); }
    public double getNewInstallment()      { return getHiddenDouble("newInstallment"); }
    public double getTotGrossIncome()      { return getHiddenDouble("totGrossIncConsi_Primry"); }
    public double getTotDedConsi()         { return getHiddenDouble("totDedConsi_Primry"); }
}
