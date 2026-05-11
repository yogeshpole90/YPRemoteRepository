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

        // Login
        driver.get(ServerConfig.getActiveServer());
        driver.findElement(By.id("loginId")).sendKeys(ConfigManager.get("username"), Keys.TAB);
        driver.findElement(By.id("uiPwd")).sendKeys(ConfigManager.get("password"), Keys.TAB);
        Thread.sleep(2000);
        driver.findElement(By.id("userLogin")).click();
        Thread.sleep(2000);
        logger.info("Login successful");
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
        if (ExtentManager.getTest() != null) {
            if (pass) {
                ExtentManager.getTest().pass(field + " | " + desc + " | Expected: " + expected + " | Actual: " + actual);
            } else {
                ExtentManager.getTest().fail(field + " | " + desc + " | Expected: " + expected + " | Actual: " + actual);
                ExtentManager.attachScreenshot(driver, "FAIL_" + field.replace(" ", "_"));
            }
        }
    }

    protected void logInfo(String field, String desc, String value) {
        System.out.println("----------------------------------------------");
        System.out.println("TC_" + (tcCounter++) + " | Field: " + field);
        System.out.println("  Test     : " + desc);
        System.out.println("  Value    : " + value);
        System.out.println("  Status   : \u2139\ufe0f INFO");
        if (ExtentManager.getTest() != null) {
            ExtentManager.getTest().info(field + " | " + desc + " | Value: " + value);
        }
    }

    protected String getErrorToast() { return ToastUtil.getErrorToast(driver); }
    protected String getSuccessToast() { return ToastUtil.getSuccessToast(driver); }
}
