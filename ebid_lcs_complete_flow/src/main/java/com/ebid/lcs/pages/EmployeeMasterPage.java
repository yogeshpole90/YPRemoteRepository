package com.ebid.lcs.pages;

import java.time.Duration;

import org.openqa.selenium.By;
import org.openqa.selenium.Keys;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.Select;
import org.openqa.selenium.support.ui.WebDriverWait;

public class EmployeeMasterPage {

    private WebDriver driver;
    private WebDriverWait wait;

    // ========== Navigation Locators ==========
    @FindBy(xpath = "//*[@class='item-nav']/div")
    private WebElement hamburgerMenu;

    @FindBy(xpath = "//*[@id='Administration']/a")
    private WebElement adminLink;

    @FindBy(xpath = "//*[@id='usermgmtAdm']/a")
    private WebElement userMgmtLink;

    @FindBy(xpath = "//*[@id='EMPLOYEEMST']/a")
    private WebElement empMasterLink;

    @FindBy(id = "btnAddEmp")
    private WebElement addEmpBtn;

    // ========== Form Field Locators ==========
    @FindBy(id = "empId")
    private WebElement empId;

    @FindBy(id = "userSalutation")
    private WebElement salutation;

    @FindBy(id = "empName")
    private WebElement empName;

    @FindBy(id = "designation")
    private WebElement designation;

    @FindBy(id = "joinDate")
    private WebElement joinDate;

    @FindBy(id = "birthDate")
    private WebElement birthDate;

    @FindBy(id = "gender")
    private WebElement gender;

    @FindBy(id = "employmentType")
    private WebElement employmentType;

    @FindBy(id = "bloodGroup")
    private WebElement bloodGroup;

    @FindBy(id = "idProof")
    private WebElement idProof;

    @FindBy(id = "idNumber")
    private WebElement idNumber;

    @FindBy(id = "issueDate")
    private WebElement issueDate;

    @FindBy(id = "docIssuedBy")
    private WebElement docIssuedBy;

    @FindBy(id = "idProofName")
    private WebElement idProofName;

    @FindBy(id = "education")
    private WebElement education;

    @FindBy(id = "religion")
    private WebElement religion;

    @FindBy(id = "caste")
    private WebElement caste;

    @FindBy(id = "subCaste")
    private WebElement subCaste;

    @FindBy(id = "status")
    private WebElement status;

    @FindBy(id = "retireDate")
    private WebElement retireDate;

    @FindBy(id = "remark")
    private WebElement remark;

    @FindBy(id = "addrIdType")
    private WebElement addrIdType;

    @FindBy(id = "addrIdNo")
    private WebElement addrIdNo;

    @FindBy(id = "address1")
    private WebElement address1;

    @FindBy(id = "address2")
    private WebElement address2;

    @FindBy(id = "address3")
    private WebElement address3;

    @FindBy(id = "country")
    private WebElement country;

    @FindBy(id = "state")
    private WebElement state;

    @FindBy(id = "city")
    private WebElement city;

    @FindBy(id = "postalCode")
    private WebElement postalCode;

    @FindBy(id = "email")
    private WebElement email;

    @FindBy(id = "officeTel")
    private WebElement officeTel;

    @FindBy(id = "mobile")
    private WebElement mobile;

    @FindBy(id = "saveCustomer")
    private WebElement saveBtn;

    // ========== Constructor ==========
    public EmployeeMasterPage(WebDriver driver) {
        this.driver = driver;
        this.wait = new WebDriverWait(driver, Duration.ofSeconds(15));
        PageFactory.initElements(driver, this);
    }

    // ========== Navigation ==========
    public void navigateToEmployeeMaster() throws InterruptedException {
        hamburgerMenu.click();
        Thread.sleep(2000);

        wait.until(ExpectedConditions.elementToBeClickable(adminLink));
        adminLink.click();
        Thread.sleep(1000);

        wait.until(ExpectedConditions.elementToBeClickable(userMgmtLink));
        userMgmtLink.click();
        Thread.sleep(1000);

        wait.until(ExpectedConditions.elementToBeClickable(empMasterLink));
        empMasterLink.click();
        Thread.sleep(2000);
    }

    public void clickAddEmployee() throws InterruptedException {
        wait.until(ExpectedConditions.elementToBeClickable(addEmpBtn));
        addEmpBtn.click();
        Thread.sleep(2000);
    }

    // ========== Generic Actions ==========
    public WebElement getField(String fieldId) {
        return driver.findElement(By.id(fieldId));
    }

    public void enterText(WebElement field, String value) throws InterruptedException {
        field.clear();
        Thread.sleep(200);
        if (!value.isEmpty() && !value.equalsIgnoreCase("Empty")) {
            field.sendKeys(value);
            Thread.sleep(300);
            field.sendKeys(Keys.ESCAPE);
            Thread.sleep(200);
            field.sendKeys(Keys.TAB);
            Thread.sleep(500);
        }
    }

    public String getValue(WebElement field) {
        return field.getAttribute("value");
    }

    public void selectDropdown(WebElement field, String visibleText) throws InterruptedException {
        Select s = new Select(field);
        s.selectByVisibleText(visibleText);
        Thread.sleep(500);
        field.sendKeys(Keys.ESCAPE);
        Thread.sleep(300);
    }

    public String getSelectedText(WebElement field) {
        Select s = new Select(field);
        return s.getFirstSelectedOption().getText().trim();
    }

    public boolean isDisplayed(WebElement field) {
        return field.isDisplayed();
    }

    public boolean isEnabled(WebElement field) {
        return field.isEnabled();
    }

    // ========== Save ==========
    public void clickSave() throws InterruptedException {
        wait.until(ExpectedConditions.elementToBeClickable(saveBtn));
        saveBtn.click();
        Thread.sleep(3000);
    }

    public boolean isSaveBtnVisible() {
        return saveBtn.isDisplayed();
    }

    public boolean isSaveBtnEnabled() {
        return saveBtn.isEnabled();
    }
}
