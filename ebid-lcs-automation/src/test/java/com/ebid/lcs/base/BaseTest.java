package com.ebid.lcs.base;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.Keys;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.interactions.Actions;
import org.testng.annotations.AfterSuite;
import org.testng.annotations.BeforeSuite;
import org.testng.asserts.SoftAssert;

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

    @BeforeSuite
    public void suiteSetup() throws Exception {
        driver = DriverManager.getDriver();
        jse = (JavascriptExecutor) driver;
        act = new Actions(driver);

        // Check if this is an admin test - skip Shelly login
        String className = this.getClass().getSimpleName();
        boolean isAdminTest = className.equals("ActionDocMapTest") 
                           || className.equals("LawFirmTest") 
                           || className.equals("LawyerDetailsTest");

        driver.get(ServerConfig.getActiveServer());
        Thread.sleep(1000);

        if (isAdminTest) {
            driver.findElement(By.id("loginId")).sendKeys(ConfigManager.get("admin.username"), Keys.TAB);
            driver.findElement(By.id("uiPwd")).sendKeys(ConfigManager.get("admin.password"), Keys.TAB);
            Thread.sleep(1000);
            driver.findElement(By.id("userLogin")).click();
            Thread.sleep(2000);
            logger.info("Login as infraadmin successful");
        } else {
            driver.findElement(By.id("loginId")).sendKeys(ConfigManager.get("username"), Keys.TAB);
            driver.findElement(By.id("uiPwd")).sendKeys(ConfigManager.get("password"), Keys.TAB);
            Thread.sleep(2000);
            driver.findElement(By.id("userLogin")).click();
            Thread.sleep(2000);
            logger.info("Login as Shelly successful");
        }
    }

    @AfterSuite
    public void suiteTeardown() {
        ExtentManager.flush();
        DriverManager.quit();
    }

    // Navigate to case
    protected void navigateToCase(String caseNumber) throws Exception {
        driver.findElement(By.xpath("//*[@class='item-nav']/div/div")).click();
        Thread.sleep(500);
        driver.findElement(By.xpath("(//li[@id = 'COMMONCOLLECTORLIST'])/a")).click();
        Thread.sleep(2000);
        driver.findElement(By.xpath("//*[@type='search']")).sendKeys(caseNumber);
        Thread.sleep(2000);
        WebElement caseRow = driver.findElement(By.xpath("//*[text()='" + caseNumber + "']"));
        act.doubleClick(caseRow).build().perform();
        Thread.sleep(2000);
        logger.info("Navigated to case: " + caseNumber);
    }

    // Log helper
    protected void log(String field, String desc, String expected, String actual, boolean pass) {
        System.out.println("----------------------------------------------");
        System.out.println("TC_" + (tcCounter++) + " | Field: " + field);
        System.out.println("  Test     : " + desc);
        System.out.println("  Expected : " + expected);
        System.out.println("  Actual   : " + actual);
        System.out.println("  Status   : " + (pass ? "\u2705 PASS" : "\u274c FAIL"));
        if (pass) {
            ExtentManager.pass(field, desc, expected, actual);
        } else {
            ExtentManager.fail(field, desc, expected, actual, driver);
        }
    }

    protected void logInfo(String field, String desc, String value) {
        System.out.println("----------------------------------------------");
        System.out.println("TC_" + (tcCounter++) + " | Field: " + field);
        System.out.println("  Test     : " + desc);
        System.out.println("  Value    : " + value);
        System.out.println("  Status   : \u2139\ufe0f INFO");
        ExtentManager.info(field, desc, value);
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
        logger.info("Login as infraadmin successful");
    }
}
