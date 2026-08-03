package com.agat.los.base;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.Keys;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.ui.Select;
import org.testng.annotations.AfterSuite;
import org.testng.annotations.BeforeSuite;
import org.testng.asserts.SoftAssert;

import com.agat.los.config.ConfigManager;
import com.agat.los.driver.DriverManager;
import com.agat.los.reporting.ExtentManager;
import com.agat.los.utils.ServerConfig;
import com.agat.los.utils.ToastUtil;

public class BaseTest {

    protected WebDriver driver;
    protected JavascriptExecutor jse;
    protected Actions act;
    protected SoftAssert sa = new SoftAssert();
    protected Logger logger = LogManager.getLogger(this.getClass());
    protected int tcCounter = 1;

    @BeforeSuite
    public void suiteSetup() throws Exception {
        driver = DriverManager.getDriver();
        jse = (JavascriptExecutor) driver;
        act = new Actions(driver);

        driver.get(ServerConfig.getActiveServer());
        Thread.sleep(1000);

        // Set automation start time in sessionStorage
        jse.executeScript("sessionStorage.setItem('autoStart', Date.now())");

        // Select language from config
        new Select(driver.findElement(By.id("languageCode"))).selectByValue(ConfigManager.get("language"));
        Thread.sleep(1000);

        // Login
        driver.findElement(By.id("loginId")).sendKeys(ConfigManager.get("dde.username"), Keys.TAB);
        driver.findElement(By.id("uiPwd")).sendKeys(ConfigManager.get("dde.password"), Keys.TAB);
        Thread.sleep(1000);
        driver.findElement(By.id("userLogin")).click();
        Thread.sleep(2000);
        logger.info("Login as " + ConfigManager.get("dde.username") + " successful");

        injectAutomationTag();
    }

    @AfterSuite
    public void suiteTeardown() {
        String appId = ConfigManager.get("generated.appId");
        String ddeUser     = ConfigManager.get("username");
        String creditUser  = ConfigManager.get("credit.approval.username");
        String offerUser   = ConfigManager.get("username");
        String loanActUser = ConfigManager.get("loan.activation.username") != null
                           && !ConfigManager.get("loan.activation.username").isEmpty()
                           ? ConfigManager.get("loan.activation.username")
                           : ConfigManager.get("username");

        System.out.println("\n");
        System.out.println("════════════════════════════════════════════════════════════");
        System.out.println("   ✅  TEST EXECUTION COMPLETED");
        System.out.println("════════════════════════════════════════════════════════════");
        System.out.println("   📋 APPLICATION ID  : " + (appId != null ? appId : "NOT GENERATED"));
        System.out.println("════════════════════════════════════════════════════════════");
        System.out.println("   📊 STAGE-WISE USER SUMMARY");
        System.out.println("   ├─ Stage 1  : Lead Creation          → " + ddeUser);
        System.out.println("   ├─ Stage 2  : DDE (KYC+Product+Docs)  → " + ddeUser);
        System.out.println("   ├─ Stage 3  : Credit Approval         → " + creditUser);
        System.out.println("   ├─ Stage 4  : Offer Acceptance        → " + offerUser);
        System.out.println("   └─ Stage 5  : Loan Activation         → " + loanActUser);
        System.out.println("════════════════════════════════════════════════════════════");
        System.out.println("\n");
        logger.info("========== APPLICATION ID: " + appId + " ==========");
        ExtentManager.flush();
        DriverManager.quit();
    }

    protected void injectAutomationTag() {
        try {
            jse.executeScript(
                "if(document.getElementById('automation-tag'))return;" +
                "var style=document.createElement('style');" +
                "style.innerHTML='@keyframes autoBlink{0%,100%{opacity:1}50%{opacity:0.3}}';" +
                "document.head.appendChild(style);" +
                "var tag=document.createElement('div');" +
                "tag.id='automation-tag';" +
                "tag.style.cssText='position:fixed;top:10px;right:10px;z-index:99999;background:#d32f2f;color:white;padding:14px 22px;font-size:16px;font-weight:bold;border-radius:8px;box-shadow:0 4px 12px rgba(0,0,0,0.4);pointer-events:none;text-align:center;animation:autoBlink 1.5s infinite;';" +
                "tag.innerHTML='<div>\\ud83e\\udd16 AUTOMATION RUNNING</div><div id=auto-timer style=font-size:13px;margin-top:4px;>00:00</div>';" +
                "document.body.appendChild(tag);" +
                "setInterval(function(){var st=parseInt(sessionStorage.getItem('autoStart')||Date.now());var e=Math.floor((Date.now()-st)/1000);var m=String(Math.floor(e/60)).padStart(2,'0');var s=String(e%60).padStart(2,'0');var t=document.getElementById('auto-timer');if(t)t.innerText=m+':'+s;},1000);"
            );
        } catch (Exception e) {}
    }

    // Log helper - also re-injects tag if page changed
    protected void log(String field, String desc, String expected, String actual, boolean pass) {
        injectAutomationTag();
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
        injectAutomationTag();
        String logMsg = "[" + field + "] " + desc + " | Value: " + value;
        System.out.println("----------------------------------------------");
        System.out.println("TC_" + (tcCounter++) + " | Field: " + field);
        System.out.println("  Test     : " + desc);
        System.out.println("  Value    : " + value);
        System.out.println("  Status   : \u2139\ufe0f INFO");
        logger.info(logMsg);
        ExtentManager.info(field, desc, value);
    }

    protected String getSuccessToast() { return ToastUtil.getSuccessToast(driver); }
    protected String getErrorToast() { return ToastUtil.getErrorToast(driver); }

    // ========== Common DDE Navigation ==========
    protected void navigateToDDE() throws Exception {
        // If DDE tabs already visible, skip navigation
        try {
            driver.findElement(By.xpath("//nav[contains(@class,'section-nav')]//a[contains(@href,'activeTab=KYC')]"));
            return; // already on DDE page
        } catch (Exception ignored) {}

        String appId = extractCleanAppId(ConfigManager.get("generated.appId"));
        driver.findElement(By.cssSelector("a.item-summary")).click();
        Thread.sleep(2000);
        WebElement searchBox = driver.findElement(By.cssSelector("#dt-authdata_filter input[type='search']"));
        searchBox.clear();
        searchBox.sendKeys(appId);
        Thread.sleep(2000);
        act.doubleClick(driver.findElement(By.cssSelector("#dt-authdata tbody tr:first-child td:nth-child(2)"))).build().perform();
        Thread.sleep(3000);
        WebElement ddeLink = driver.findElement(By.xpath("//td[@class='stage-child']//a[contains(@href,'stageName=DETAILED DATA ENTRY')]"));
        jse.executeScript("arguments[0].scrollIntoView({behavior:'smooth', block:'center'})", ddeLink);
        Thread.sleep(500);
        jse.executeScript("arguments[0].click()", ddeLink);
        Thread.sleep(3000);
    }

    protected String extractCleanAppId(String raw) {
        if (raw == null) return "";
        java.util.regex.Matcher m = java.util.regex.Pattern.compile("[A-Z]+-\\d+").matcher(raw);
        if (m.find()) return m.group();
        m = java.util.regex.Pattern.compile("\\d+").matcher(raw);
        if (m.find()) return m.group();
        return raw.trim();
    }

    protected void loginAs(String userId, String password) throws Exception {
        // Logout: click logout icon → Yes → Re-Login
        try {
            WebElement logoutBtn = driver.findElement(By.cssSelector("a.item-logout"));
            jse.executeScript("arguments[0].click()", logoutBtn);
            Thread.sleep(2000);
            WebElement yesBtn = driver.findElement(By.id("logoutYes"));
            jse.executeScript("arguments[0].click()", yesBtn);
            Thread.sleep(2000);
            WebElement reloginBtn = driver.findElement(By.id("relogin"));
            jse.executeScript("arguments[0].click()", reloginBtn);
            Thread.sleep(2000);
        } catch (Exception e) {
            driver.get(ServerConfig.getActiveServer());
            Thread.sleep(2000);
        }

        // Select language from config
        new Select(driver.findElement(By.id("languageCode"))).selectByValue(ConfigManager.get("language"));
        Thread.sleep(1000);

        driver.findElement(By.id("loginId")).clear();
        driver.findElement(By.id("loginId")).sendKeys(userId, Keys.TAB);
        driver.findElement(By.id("uiPwd")).clear();
        driver.findElement(By.id("uiPwd")).sendKeys(password, Keys.TAB);
        Thread.sleep(1000);
        driver.findElement(By.id("userLogin")).click();
        Thread.sleep(2000);
        logger.info("Login as " + userId + " successful");
        injectAutomationTag();
    }
}
