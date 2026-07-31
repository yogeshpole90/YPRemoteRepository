package com.ebid.lcs.base;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.Keys;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.annotations.AfterSuite;
import org.testng.annotations.BeforeSuite;
import org.testng.annotations.BeforeTest;
import org.testng.asserts.SoftAssert;
import java.time.Duration;
import java.util.List;

import com.ebid.lcs.config.ConfigManager;
import com.ebid.lcs.driver.DriverManager;
import com.ebid.lcs.reporting.ExtentManager;
import com.ebid.lcs.utils.ServerConfig;
import com.ebid.lcs.utils.ToastUtil;

public class BaseTest {

    protected WebDriver driver;
    protected JavascriptExecutor jse;
    protected Actions act;
    protected SoftAssert sa = new SoftAssert();
    protected Logger logger = LogManager.getLogger(this.getClass());
    protected int tcCounter = 1;

    private static boolean caseNavigated = false;

    @BeforeTest
    public void suiteSetup() throws Exception {
        driver = DriverManager.getDriver();
        jse = (JavascriptExecutor) driver;
        act = new Actions(driver);

        // Login only once
        if (!caseNavigated) {
            driver.get(ServerConfig.getActiveServer());
            Thread.sleep(1000);
            driver.findElement(By.id("loginId")).sendKeys(ConfigManager.get("username"), Keys.TAB);
            driver.findElement(By.id("uiPwd")).sendKeys(ConfigManager.get("password"), Keys.TAB);
            Thread.sleep(2000);
            driver.findElement(By.id("userLogin")).click();
            Thread.sleep(2000);
            logger.info("Login as " + ConfigManager.get("username") + " successful");
            // Inject smooth scroll CSS globally
            jse.executeScript("document.documentElement.style.scrollBehavior='smooth';");
        }

        // Navigate to case only once
        if (!caseNavigated) {
            driver.switchTo().defaultContent();
            String cn = ConfigManager.get("casenumber");
            String[] candidates = { cn, "CASE_0000012011102001438" };
            for (String c : candidates) {
                try {
                    driver.findElement(By.xpath("//*[@class='item-nav']/div/div")).click();
                    Thread.sleep(500);
                    driver.findElement(By.xpath("(//li[@id = 'COMMONCOLLECTORLIST'])/a")).click();
                    Thread.sleep(3000);
                    WebElement searchBox = driver.findElement(By.xpath("//*[@type='search']"));
                    searchBox.clear();
                    searchBox.sendKeys(c);
                    Thread.sleep(2500);
                    List<WebElement> rows = driver.findElements(By.xpath("//*[contains(text(),'" + c + "')]"));
                    if (rows.isEmpty()) { logger.warn("Case not found: " + c); continue; }
                    jse.executeScript("arguments[0].scrollIntoView({block:'center'})", rows.get(0));
                    Thread.sleep(500);
                    act.doubleClick(rows.get(0)).build().perform();
                    new WebDriverWait(driver, Duration.ofSeconds(20))
                        .until(ExpectedConditions.presenceOfElementLocated(By.xpath("//*[contains(@href,'activeTab=')]")));
                    Thread.sleep(1000);
                    ConfigManager.set("casenumber", c);
                    logger.info("Navigated to case: " + c);
                    caseNavigated = true;
                    return;
                } catch (Exception e) {
                    logger.warn("Case not found: " + c + " trying next");
                }
            }
            throw new RuntimeException("No valid case found");
        }

        // For subsequent tests — just switch to default content
        driver.switchTo().defaultContent();
        Thread.sleep(500);
    }

    @AfterSuite
    public void suiteTeardown() {
        ExtentManager.flush();
        DriverManager.quit();
    }

    // Navigate to case — tries casenumber first, then fallback
    protected void navigateToCase(String caseNumber) throws Exception {
        String[] candidates = { caseNumber, "CASE_0000012011102001438" };
        for (String cn : candidates) {
            try {
                driver.switchTo().defaultContent();
                Thread.sleep(500);

                driver.findElement(By.xpath("//*[@class='item-nav']/div/div")).click();
                Thread.sleep(500);
                driver.findElement(By.xpath("(//li[@id = 'COMMONCOLLECTORLIST'])/a")).click();
                Thread.sleep(3000);

                WebElement searchBox = driver.findElement(By.xpath("//*[@type='search']"));
                searchBox.clear();
                searchBox.sendKeys(cn);
                Thread.sleep(2500);

                List<WebElement> rows = driver.findElements(By.xpath("//*[contains(text(),'" + cn + "')]"));
                if (rows.isEmpty()) {
                    logger.warn("Case not found: " + cn + " — trying next");
                    continue;
                }

                // scroll into view then double click
                jse.executeScript("arguments[0].scrollIntoView({block:'center'})", rows.get(0));
                Thread.sleep(500);
                act.doubleClick(rows.get(0)).build().perform();

                // wait until case page loads — look for tab nav
                new WebDriverWait(driver, Duration.ofSeconds(20))
                    .until(ExpectedConditions.presenceOfElementLocated(
                        By.xpath("//*[contains(@href,'activeTab=') or contains(@class,'item-nav')]"))
                    );
                Thread.sleep(1000);

                logger.info("Navigated to case: " + cn);
                ConfigManager.set("casenumber", cn);
                return;
            } catch (Exception e) {
                logger.warn("Case not found: " + cn + " — trying next");
            }
        }
        throw new RuntimeException("No valid case found from candidates: " + String.join(", ", candidates));
    }

    // Log helper
    protected void log(String field, String desc, String expected, String actual, boolean pass) {
        String status = pass ? "PASS" : "FAIL";
        String logMsg = "[" + field + "] " + desc + " | Expected: " + expected + " | Actual: " + actual + " | Status: " + status;
        System.out.println("----------------------------------------------");
        System.out.println("TC_" + (tcCounter++) + " | Field: " + field);
        System.out.println("  Test     : " + desc);
        System.out.println("  Expected : " + expected);
        System.out.println("  Actual   : " + actual);
        System.out.println("  Status   : " + (pass ? "\u2705 PASS" : "\u274c FAIL"));
        if (pass) {
            logger.info(logMsg);
            ExtentManager.pass(field, desc, expected, actual);
        } else {
            logger.error(logMsg);
            ExtentManager.fail(field, desc, expected, actual, driver);
        }
    }

    protected void logInfo(String field, String desc, String value) {
        String logMsg = "[" + field + "] " + desc + " | Value: " + value;
        System.out.println("----------------------------------------------");
        System.out.println("TC_" + (tcCounter++) + " | Field: " + field);
        System.out.println("  Test     : " + desc);
        System.out.println("  Value    : " + value);
        System.out.println("  Status   : \u2139\ufe0f INFO");
        logger.info(logMsg);
        ExtentManager.info(field, desc, value);
    }

    protected void smoothScroll(WebElement el) {
        jse.executeScript("arguments[0].scrollIntoView({block:'center',behavior:'smooth'})", el);
        try { Thread.sleep(400); } catch (Exception e) {}
    }

    // Move to Next Stage — click Next button, fill modal, submit, re-navigate to case
    protected void moveToNextStage() throws Exception {
        driver.switchTo().defaultContent();
        Thread.sleep(500);

        // Click Move to Next button
        WebElement nextBtn = new WebDriverWait(driver, Duration.ofSeconds(10))
            .until(ExpectedConditions.elementToBeClickable(By.id("Next")));
        jse.executeScript("arguments[0].scrollIntoView({block:'center'})", nextBtn);
        nextBtn.click();
        Thread.sleep(2000);

        // nextStage dropdown — always select Legal Stage
        try {
            WebElement nextStage = driver.findElement(By.id("nextStage"));
            new org.openqa.selenium.support.ui.Select(nextStage).selectByValue("11~~Legal+Stage~~Legal");
            Thread.sleep(300);
            logger.info("nextStage selected: Legal Stage");
        } catch (Exception e) { logger.warn("nextStage not found: " + e.getMessage()); }

        // Modal — select allocatedTo index 1
        WebElement allocatedTo = driver.findElement(By.id("allocatedTo"));
        org.openqa.selenium.support.ui.Select allocSel = new org.openqa.selenium.support.ui.Select(allocatedTo);
        try { allocSel.selectByIndex(1); } catch (Exception e) { logger.warn("allocatedTo select failed: " + e.getMessage()); }
        Thread.sleep(300);

        // Remarks
        WebElement remarkData = driver.findElement(By.id("remarkData"));
        remarkData.clear();
        remarkData.sendKeys("Moving to Legal stage");
        Thread.sleep(300);

        // Submit
        driver.findElement(By.id("remarkSubmit")).click();
        Thread.sleep(3000);
        logger.info("Moved to next stage successfully");

        // After submit — lands on inbox, search case and re-open
        String cn = ConfigManager.get("casenumber");
        driver.switchTo().defaultContent();
        Thread.sleep(1000);

        driver.findElement(By.xpath("//*[@class='item-nav']/div/div")).click();
        Thread.sleep(500);
        driver.findElement(By.xpath("(//li[@id = 'COMMONCOLLECTORLIST'])/a")).click();
        Thread.sleep(3000);
        WebElement searchBox = driver.findElement(By.xpath("//*[@type='search']"));
        searchBox.clear();
        searchBox.sendKeys(cn);
        Thread.sleep(2500);
        java.util.List<WebElement> rows = driver.findElements(
            By.xpath("//*[contains(text(),'" + cn + "')]"));
        if (!rows.isEmpty()) {
            jse.executeScript("arguments[0].scrollIntoView({block:'center'})", rows.get(0));
            Thread.sleep(300);
            act.doubleClick(rows.get(0)).build().perform();
            new WebDriverWait(driver, Duration.ofSeconds(20))
                .until(ExpectedConditions.presenceOfElementLocated(
                    By.xpath("//*[contains(@href,'activeTab=')]")));
            Thread.sleep(1000);
            logger.info("Re-navigated to case after stage move: " + cn);
        }
    }

    protected String getErrorToast() { return ToastUtil.getErrorToast(driver); }
    protected String getSuccessToast() { return ToastUtil.getSuccessToast(driver); }

    protected void loginAsAdmin() throws Exception {
        // Logout current user first
        try {
            driver.findElement(By.xpath("//*[@id='logoutForm']/a")).click();
            Thread.sleep(1000);
        } catch (Exception e) {
            // No logout button, go to login page directly
        }

        driver.get(ServerConfig.getActiveServer());
        Thread.sleep(1000);
        driver.findElement(By.id("loginId")).clear();
        driver.findElement(By.id("loginId")).sendKeys(ConfigManager.get("admin.username"), Keys.TAB);
        driver.findElement(By.id("uiPwd")).clear();
        driver.findElement(By.id("uiPwd")).sendKeys(ConfigManager.get("admin.password"), Keys.TAB);
        Thread.sleep(1000);
        driver.findElement(By.id("userLogin")).click();
        Thread.sleep(2000);
        logger.info("Login as " + ConfigManager.get("admin.username") + " successful");
    }
}