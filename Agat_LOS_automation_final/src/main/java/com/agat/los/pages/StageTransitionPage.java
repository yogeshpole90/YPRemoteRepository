package com.agat.los.pages;

import java.time.Duration;

import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

public class StageTransitionPage {

    private WebDriver driver;
    private JavascriptExecutor jse;
    private WebDriverWait wait;

    @FindBy(id = "Next") private WebElement nextBtn;
    @FindBy(id = "remark") private WebElement remarkTextarea;
    @FindBy(id = "remarkSubmit") private WebElement remarkSubmitBtn;

    public StageTransitionPage(WebDriver driver) {
        this.driver = driver;
        this.jse = (JavascriptExecutor) driver;
        this.wait = new WebDriverWait(driver, Duration.ofSeconds(15));
        PageFactory.initElements(driver, this);
    }

    public void clickNext() throws InterruptedException {
        wait.until(ExpectedConditions.elementToBeClickable(nextBtn));
        jse.executeScript("arguments[0].scrollIntoView({behavior:'smooth', block:'center'})", nextBtn);
        jse.executeScript("arguments[0].click()", nextBtn);
        wait.until(ExpectedConditions.visibilityOfElementLocated(By.id("remark")));
    }

    public void enterRemarks(String text) {
        jse.executeScript("arguments[0].value=''", remarkTextarea);
        jse.executeScript("arguments[0].value=arguments[1]", remarkTextarea, text);
    }

    public void clickSubmit() throws InterruptedException {
        wait.until(ExpectedConditions.elementToBeClickable(remarkSubmitBtn));
        jse.executeScript("arguments[0].click()", remarkSubmitBtn);
        Thread.sleep(2000);
        // Check if KATM error message appeared
        try {
            WebElement katmError = driver.findElement(
                By.xpath("//*[contains(text(),'KATM Report is mandatory')]"));
            if (katmError.isDisplayed()) {
                throw new RuntimeException("KATM Report not fetched! Please fill Credit Bureau before stage transition.");
            }
        } catch (org.openqa.selenium.NoSuchElementException e) {}
    }

    public String getStageSuccessMessage() {
        try {
            WebElement msg = wait.until(ExpectedConditions.visibilityOfElementLocated(
                By.cssSelector("h5.blue-title")));
            return msg.getText().trim();
        } catch (Exception e) { return ""; }
    }
}
