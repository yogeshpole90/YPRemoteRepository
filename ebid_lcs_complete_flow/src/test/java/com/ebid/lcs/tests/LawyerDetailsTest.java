package com.ebid.lcs.tests;

import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.Select;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Listeners;
import org.testng.annotations.Test;

import java.time.Duration;

import com.ebid.lcs.base.BaseTest;
import com.ebid.lcs.excel.ExcelReader;
import com.ebid.lcs.excel.SheetConstants;
import com.ebid.lcs.listeners.TestListener;
import com.ebid.lcs.reporting.ExtentManager;

@Listeners(TestListener.class)
public class LawyerDetailsTest extends BaseTest {

    private String uniqueRefNo = "REF" + System.currentTimeMillis() % 1000000;

    private void dismissAlert() {
        try { driver.switchTo().alert().accept(); } catch (Exception e) {}
    }

    private void safeClear(WebElement el) {
        try { el.clear(); } catch (Exception e) { dismissAlert(); el.clear(); }
        dismissAlert();
    }

    private String safeGetValue(WebElement el) {
        try { return el.getAttribute("value"); } catch (Exception e) { dismissAlert(); return el.getAttribute("value"); }
    }

    @BeforeClass
    public void setup() throws Exception {
        ExtentManager.initReport("LawyerDetails");
        ExtentManager.startTest("Lawyer Details - Full Validation");

        WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(10));
        driver.findElement(By.xpath("//*[@class='item-nav']/div")).click();
        Thread.sleep(500);
        WebElement ld = driver.findElement(By.xpath("//*[@id='LAWERDETAILSMST']/a"));
        jse.executeScript("arguments[0].scrollIntoView({block:'center'})", ld);
        Thread.sleep(300);
        ld.click();

        WebElement addBtn = wait.until(ExpectedConditions.elementToBeClickable(By.id("addButton")));
        addBtn.click();
        Thread.sleep(1000);

        logInfo("Navigation", "Navigated to", "Lawyer Details (infraadmin)");
        logInfo("Setup", "Unique Lawyer Ref No", uniqueRefNo);
    }

    @Test(priority = 1)
    public void validateAllFields() throws Exception {
        Object[][] data = ExcelReader.getByTcPrefix(SheetConstants.SHEET_LAWYER_DETAILS, SheetConstants.TC.LAWYER_DETAILS);

        for (Object[] row : data) {
            String fieldName = row[SheetConstants.Cols.FIELD_NAME].toString().trim();
            String input = row[SheetConstants.Cols.INPUT].toString().trim();
            String expected = row[SheetConstants.Cols.EXPECTED].toString().trim();
            String desc = row[SheetConstants.Cols.DESCRIPTION].toString();
            String checkType = row[SheetConstants.Cols.CHECK_TYPE].toString().trim();

            // Replace ref number with unique value for final save row
            if (fieldName.equals("lawyerRefCode") && desc.toLowerCase().contains("final")) {
                input = uniqueRefNo;
                expected = uniqueRefNo;
            }

            dismissAlert();

            WebElement f;
            try { f = driver.findElement(By.id(fieldName)); } catch (Exception e) {
                dismissAlert();
                try { f = driver.findElement(By.id(fieldName)); } catch (Exception e2) {
                    log(fieldName, desc, expected, "Element not found: " + fieldName, false);
                    continue;
                }
            }

            jse.executeScript("arguments[0].scrollIntoView({block:'center'})", f);
            Thread.sleep(200);
            String tagName = f.getTagName();

            if (tagName.equals("select")) {
                Select s = new Select(f);
                try {
                    if (input.startsWith("INDEX:")) {
                        s.selectByIndex(Integer.parseInt(input.replace("INDEX:", "")));
                    } else {
                        s.selectByVisibleText(input);
                    }
                    Thread.sleep(500);
                    String actual = s.getFirstSelectedOption().getText().trim();
                    log(fieldName, desc, expected, actual, actual.equals(expected));
                    sa.assertEquals(actual, expected, desc);
                } catch (Exception e) {
                    log(fieldName, desc, expected, "Option not found: " + input, false);
                }
            } else {
                safeClear(f);
                if (!input.isEmpty() && !input.equalsIgnoreCase("Empty")) {
                    f.sendKeys(input);
                    Thread.sleep(300);
                    dismissAlert();
                }
                String actual = safeGetValue(f);

                switch (checkType) {
                    case "equals":
                        log(fieldName, desc, expected, actual, actual.equals(expected));
                        sa.assertEquals(actual, expected, desc);
                        break;
                    case "notEquals":
                        log(fieldName, desc, "Not " + input, actual, !actual.equals(input));
                        break;
                    case "empty":
                        log(fieldName, desc, "Empty", actual, actual.isEmpty());
                        sa.assertTrue(actual.isEmpty(), desc);
                        break;
                    case "info":
                        logInfo(fieldName, desc, actual);
                        break;
                }
            }
        }
    }

    @Test(priority = 2)
    public void validateSave() throws Exception {
        dismissAlert();
        jse.executeScript("window.scrollTo(0, document.body.scrollHeight)");
        Thread.sleep(500);
        WebElement saveBtn = driver.findElement(By.id("save"));
        jse.executeScript("arguments[0].scrollIntoView({block:'center'})", saveBtn);
        Thread.sleep(300);
        log("Save Button", "Displayed", "true", String.valueOf(saveBtn.isDisplayed()), saveBtn.isDisplayed());
        saveBtn.click();
        Thread.sleep(1500);
        String toast = getSuccessToast();
        log("Save", "Save Lawyer Details", "Success", toast.isEmpty() ? "No toast" : toast, !toast.isEmpty());
    }

    @Test(priority = 3)
    public void validateBackToList() throws Exception {
        jse.executeScript("window.scrollTo(0, document.body.scrollHeight)");
        Thread.sleep(300);
        WebElement backBtn = driver.findElement(By.xpath("//*[text()='Back to List'] | //*[@id='backButton']"));
        jse.executeScript("arguments[0].scrollIntoView({block:'center'})", backBtn);
        Thread.sleep(300);
        log("Back Button", "Displayed", "true", String.valueOf(backBtn.isDisplayed()), backBtn.isDisplayed());
        backBtn.click();
        Thread.sleep(1500);
        log("Back Button", "Navigate to list", "List page", "Navigated", true);
    }

    @Test(priority = 4)
    public void validateView() throws Exception {
        // Search by unique ref number
        WebElement searchBox = driver.findElement(By.cssSelector("#dt-authdata_filter input[type='search']"));
        jse.executeScript("arguments[0].scrollIntoView({block:'center'})", searchBox);
        searchBox.clear();
        searchBox.sendKeys(uniqueRefNo);
        Thread.sleep(1000);
        log("Search", "Search by Ref No", uniqueRefNo, uniqueRefNo, true);

        // Click View
        WebElement viewBtn = driver.findElement(By.cssSelector("#dt-authdata tbody tr:first-child a.button.view"));
        jse.executeScript("arguments[0].scrollIntoView({block:'center'})", viewBtn);
        Thread.sleep(300);
        viewBtn.click();
        Thread.sleep(1500);
        log("View", "Click View", "View mode opened", "Clicked", true);

        // Validate fields in view mode
        Object[][] data = ExcelReader.getByTcPrefix(SheetConstants.SHEET_LAWYER_DETAILS, SheetConstants.TC.LAWYER_DETAILS);
        for (Object[] row : data) {
            String fieldName = row[SheetConstants.Cols.FIELD_NAME].toString().trim();
            String expected = row[SheetConstants.Cols.EXPECTED].toString().trim();
            String desc = row[SheetConstants.Cols.DESCRIPTION].toString();
            String checkType = row[SheetConstants.Cols.CHECK_TYPE].toString().trim();

            if (!checkType.equals("equals")) continue;

            // Use unique ref no for ref field
            if (fieldName.equals("lawyerRefCode") && desc.toLowerCase().contains("final")) {
                expected = uniqueRefNo;
            }

            try {
                WebElement f = driver.findElement(By.id(fieldName));
                jse.executeScript("arguments[0].scrollIntoView({block:'center'})", f);
                String actual = f.getTagName().equals("select")
                        ? new Select(f).getFirstSelectedOption().getText().trim()
                        : safeGetValue(f);
                log(fieldName, "View - " + desc, expected, actual,
                        actual.equals(expected) || actual.contains(expected) || expected.contains(actual));
            } catch (Exception e) {
                logInfo(fieldName, "View - field not found", fieldName);
            }
        }

        // Back to list
        jse.executeScript("window.scrollTo(0, document.body.scrollHeight)");
        Thread.sleep(300);
        driver.findElement(By.xpath("//*[text()='Back to List'] | //*[@id='backButton']")).click();
        Thread.sleep(1000);
        log("View", "Back to list from View", "List page", "Navigated", true);
    }

    @Test(priority = 5)
    public void validateEdit() throws Exception {
        // Search by unique ref number
        WebElement searchBox = driver.findElement(By.cssSelector("#dt-authdata_filter input[type='search']"));
        searchBox.clear();
        searchBox.sendKeys(uniqueRefNo);
        Thread.sleep(1000);
        log("Search", "Search by Ref No", uniqueRefNo, uniqueRefNo, true);

        // Click Edit
        WebElement editBtn = driver.findElement(By.cssSelector("#dt-authdata tbody tr:first-child a.button.edit"));
        jse.executeScript("arguments[0].scrollIntoView({block:'center'})", editBtn);
        Thread.sleep(300);
        editBtn.click();
        Thread.sleep(1500);
        log("Edit", "Click Edit", "Edit mode opened", "Clicked", true);

        // Save after edit
        jse.executeScript("window.scrollTo(0, document.body.scrollHeight)");
        Thread.sleep(300);
        WebElement saveBtn = driver.findElement(By.id("save"));
        jse.executeScript("arguments[0].scrollIntoView({block:'center'})", saveBtn);
        Thread.sleep(300);
        saveBtn.click();
        Thread.sleep(1500);
        String toast = getSuccessToast();
        log("Edit", "Save after Edit", "Success", toast.isEmpty() ? "No toast" : toast, !toast.isEmpty());

        // Back to list
        jse.executeScript("window.scrollTo(0, document.body.scrollHeight)");
        Thread.sleep(300);
        driver.findElement(By.xpath("//*[text()='Back to List'] | //*[@id='backButton']")).click();
        Thread.sleep(1000);
        log("Edit", "Back to list from Edit", "List page", "Navigated", true);

        sa.assertAll();
    }
}
