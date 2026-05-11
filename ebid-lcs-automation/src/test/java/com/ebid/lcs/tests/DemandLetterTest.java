package com.ebid.lcs.tests;

import org.openqa.selenium.By;
import org.openqa.selenium.Keys;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.Select;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Listeners;
import org.testng.annotations.Test;

import com.ebid.lcs.base.BaseTest;
import com.ebid.lcs.config.ConfigManager;
import com.ebid.lcs.excel.ExcelReader;
import com.ebid.lcs.excel.SheetConstants;
import com.ebid.lcs.listeners.TestListener;
import com.ebid.lcs.reporting.ExtentManager;

@Listeners(TestListener.class)
public class DemandLetterTest extends BaseTest {

    @BeforeClass
    public void setup() throws Exception {
        ExtentManager.initReport("DemandLetter");
        ExtentManager.startTest("Demand Letter - Full Validation");

        navigateToCase(ConfigManager.get("casenumber"));

        WebElement docTab = driver.findElement(By.xpath("//*[contains(@href,'activeTab=Document')]"));
        jse.executeScript("arguments[0].scrollIntoView({block:'center',behavior:'smooth'})", docTab);
        Thread.sleep(1000);
        docTab.click();
        Thread.sleep(2000);

        WebDriverWait wait = new WebDriverWait(driver, 15);
        WebElement dlTab = wait.until(ExpectedConditions.elementToBeClickable(By.xpath("//a[contains(text(),'Demand Letter')]")));
        jse.executeScript("arguments[0].scrollIntoView({block:'center',behavior:'smooth'})", dlTab);
        Thread.sleep(1000);
        act.doubleClick(dlTab).build().perform();
        Thread.sleep(2000);

        WebElement frame = wait.until(ExpectedConditions.presenceOfElementLocated(By.id("addNewDemandLetterFrame")));
        driver.switchTo().frame(frame);
        logInfo("Frame", "Switched to", "addNewDemandLetterFrame");
    }

    @Test(priority = 1)
    public void validateAllFields() throws Exception {
        Object[][] data = ExcelReader.getByTcPrefix(SheetConstants.SHEET_DEMAND_LETTER, SheetConstants.TC.DEMAND_LETTER);

        for (Object[] row : data) {
            String fieldName = row[SheetConstants.Cols.FIELD_NAME].toString();
            String input = row[SheetConstants.Cols.INPUT].toString();
            String expected = row[SheetConstants.Cols.EXPECTED].toString();
            String desc = row[SheetConstants.Cols.DESCRIPTION].toString();
            String checkType = row[SheetConstants.Cols.CHECK_TYPE].toString();

            WebElement f = driver.findElement(By.id(fieldName));
            String tagName = f.getTagName();

            if (tagName.equals("select")) {
                Select s = new Select(f);
                s.selectByVisibleText(input);
                Thread.sleep(500);
                String actual = s.getFirstSelectedOption().getText().trim();
                log(fieldName, desc, expected, actual, actual.equals(expected));
            } else {
                f.clear();
                if (!input.isEmpty()) f.sendKeys(input);
                if (fieldName.contains("Date") || fieldName.contains("date")) f.sendKeys(Keys.TAB);
                String actual = f.getAttribute("value");

                switch (checkType) {
                    case "equals": log(fieldName, desc, expected, actual, actual.equals(expected)); break;
                    case "notEquals": log(fieldName, desc, "Not " + input, actual, !actual.equals(input)); break;
                    case "empty": log(fieldName, desc, "Empty", actual, actual.isEmpty()); break;
                }
            }
        }
        sa.assertAll();
    }
}
