package com.ebid.lcs.pages;

import java.time.Duration;

import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

public class CalendarPage {

    private WebDriver driver;
    private WebDriverWait wait;
    private JavascriptExecutor jse;

    // ========== Navigation ==========
    @FindBy(xpath = "//*[contains(@href,'=Calendar')]")
    private WebElement calendarTab;

    // ========== Checkboxes ==========
    @FindBy(xpath = "//strong[text()='View all']")
    private WebElement viewAllLabel;

    // ========== Calendar Controls ==========
    @FindBy(xpath = "//*[@id='dropdownMenu-calendarType']")
    private WebElement dropdownBtn;

    @FindBy(id = "renderRange")
    private WebElement rangeText;

    @FindBy(className = "ic-arrow-line-right")
    private WebElement forwardBtn;

    @FindBy(className = "ic-arrow-line-left")
    private WebElement backwardBtn;

    @FindBy(xpath = "//a[@data-action='toggle-daily']")
    private WebElement dailyOption;

    @FindBy(xpath = "//a[@data-action='toggle-weekly']")
    private WebElement weeklyOption;

    @FindBy(xpath = "//a[@data-action='toggle-monthly']")
    private WebElement monthlyOption;

    // ========== Popup ==========
    @FindBy(xpath = "//input[@placeholder='Subject']")
    private WebElement subjectField;

    @FindBy(xpath = "//input[@placeholder='Location']")
    private WebElement locationField;

    @FindBy(xpath = "//input[@placeholder='Description']")
    private WebElement descField;

    @FindBy(xpath = "//button[contains(@class,'tui-full-calendar-popup-save')]")
    private WebElement popupSaveBtn;

    @FindBy(xpath = "//button[contains(@class,'tui-full-calendar-dropdown-button tui-full-calendar-popup-section-item')]")
    private WebElement popupCalTypeBtn;

    // ========== Constructor ==========
    public CalendarPage(WebDriver driver) {
        this.driver = driver;
        this.wait = new WebDriverWait(driver, Duration.ofSeconds(15));
        this.jse = (JavascriptExecutor) driver;
        PageFactory.initElements(driver, this);
    }

    // ========== Navigation ==========
    public void navigateToCalendar() throws InterruptedException {
        jse.executeScript("arguments[0].scrollIntoView({block:'center',behavior:'smooth'})", calendarTab);
        Thread.sleep(1000);
        calendarTab.click();
        Thread.sleep(2000);
    }

    // ========== Checkbox Operations ==========
    public WebElement getCheckbox(String value) {
        return driver.findElement(By.xpath("//input[@value='" + value + "']"));
    }

    public void scrollToViewAll() throws InterruptedException {
        try {
            jse.executeScript("arguments[0].scrollIntoView({block:'center',behavior:'smooth'})", viewAllLabel);
            Thread.sleep(1000);
        } catch (Exception e) {}
    }

    public void clickCheckbox(WebElement cb) throws InterruptedException {
        jse.executeScript("arguments[0].scrollIntoView({block:'start',behavior:'smooth'})", cb);
        Thread.sleep(800);
        jse.executeScript("arguments[0].click()", cb);
        Thread.sleep(500);
    }

    // ========== Calendar View Controls ==========
    public void selectDailyView() throws InterruptedException {
        jse.executeScript("arguments[0].scrollIntoView({block:'center'})", dropdownBtn);
        Thread.sleep(500);
        dropdownBtn.click();
        Thread.sleep(500);
        dailyOption.click();
        Thread.sleep(1000);
    }

    public void selectWeeklyView() throws InterruptedException {
        jse.executeScript("arguments[0].scrollIntoView({block:'center'})", dropdownBtn);
        Thread.sleep(500);
        dropdownBtn.click();
        Thread.sleep(500);
        weeklyOption.click();
        Thread.sleep(1000);
    }

    public void selectMonthlyView() throws InterruptedException {
        jse.executeScript("arguments[0].scrollIntoView({block:'center'})", dropdownBtn);
        Thread.sleep(500);
        dropdownBtn.click();
        Thread.sleep(500);
        monthlyOption.click();
        Thread.sleep(1000);
    }

    public String getRangeText() {
        return rangeText.getText().trim();
    }

    public void clickForward() throws InterruptedException {
        jse.executeScript("arguments[0].scrollIntoView({block:'center'})", forwardBtn);
        Thread.sleep(500);
        forwardBtn.click();
        Thread.sleep(1000);
    }

    public void clickBackward() throws InterruptedException {
        jse.executeScript("arguments[0].scrollIntoView({block:'center'})", backwardBtn);
        Thread.sleep(500);
        backwardBtn.click();
        Thread.sleep(1000);
    }

    public boolean isDropdownBtnDisplayed() { return dropdownBtn.isDisplayed(); }
    public boolean isRangeTextDisplayed() { return rangeText.isDisplayed(); }

    // ========== Create Record ==========
    public WebElement getDailySlot() {
        return driver.findElement(By.xpath("(//div[contains(@class,'tui-full-calendar-time-date-s')])[1]"));
    }

    public void clickDailySlot() throws InterruptedException {
        WebElement slot = getDailySlot();
        jse.executeScript("arguments[0].scrollIntoView({block:'center'})", slot);
        Thread.sleep(500);
        wait.until(ExpectedConditions.elementToBeClickable(slot));
        slot.click();
        Thread.sleep(2000);
    }

    public void fillPopup(String subject, String location, String desc, String calType) throws InterruptedException {
        jse.executeScript("arguments[0].scrollIntoView({block:'center'})", popupCalTypeBtn);
        Thread.sleep(500);
        jse.executeScript("arguments[0].click()", popupCalTypeBtn);
        Thread.sleep(500);
        WebElement typeOption = driver.findElement(By.xpath(
                "//li[contains(@class,'tui-full-calendar-dropdown-menu-item')]//span[text()='" + calType + "']"));
        jse.executeScript("arguments[0].click()", typeOption);
        Thread.sleep(500);

        subjectField.sendKeys(subject);
        locationField.sendKeys(location);
        descField.sendKeys(desc);
        Thread.sleep(1000);
    }

    public void clickPopupSave() throws InterruptedException {
        jse.executeScript("arguments[0].click()", popupSaveBtn);
        Thread.sleep(2000);
    }

    public void scrollToTop() throws InterruptedException {
        jse.executeScript("window.scrollTo(0,0)");
        Thread.sleep(1000);
    }

    public void scrollDown() throws InterruptedException {
        jse.executeScript("window.scrollBy(0,3000)");
        Thread.sleep(2000);
    }
}
