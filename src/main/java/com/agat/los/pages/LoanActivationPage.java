package com.agat.los.pages;

import java.time.Duration;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.Keys;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

public class LoanActivationPage {

    private WebDriver driver;
    private JavascriptExecutor jse;
    private WebDriverWait wait;
    private Actions act;

    // ========== App Summary ==========
    private By appSummaryBtn = By.cssSelector("a.item-allSummary");
    private By searchBox     = By.cssSelector("#dt-authdata_filter input[type='search']");
    private By firstRow      = By.cssSelector("#dt-authdata tbody tr:first-child td:first-child");

    // ========== Overview ==========
    private By custNameLabel = By.xpath("//label[text()='Customer Name']/following-sibling::span");
    private By loanAmtLabel  = By.xpath("//label[text()='Loan Amount']/following-sibling::span");
    private By stageLabel    = By.xpath("//label[text()='Processing Stage']/following-sibling::span");

    // ========== DISBURSEMENT Tab ==========
    private By disbursementTab = By.xpath("//a[contains(@href,'activeTab=DISBURSEMENT')]");

    // ========== DrawDown Schedule Sub-Tab ==========
    private By drawDownSubTab = By.xpath("//a[contains(@onclick,'viewDrawDownScheduleFrame')]");
    private By drawDownFrame  = By.id("viewDrawDownScheduleFrame");

    // ========== DrawDown Form - Read-only ==========
    private By milestoneName   = By.id("milestoneName");
    private By productField    = By.id("product");
    private By subProductField = By.id("subProduct");
    private By schemeField     = By.id("scheme");

    // ========== DrawDown Form - Editable ==========
    private By milestoneDateField   = By.id("milestonedt");
    private By estDisburseDateField = By.id("estdisbursedt");

    // ========== DrawDown Save ==========
    private By drawDownSaveBtn = By.xpath("//button[normalize-space(text())='Save'] | //input[@value='Save']");

    // ========== Disbursement Details Sub-Tab ==========
    private By disbursementDetailsSubTab = By.xpath("//a[contains(@onclick,'viewDisbursementScheduleFrame')]");
    private By disbursementDetailsFrame  = By.id("viewDisbursementScheduleFrame");

    // ========== Disbursement Details Form ==========
    private By disbViewBtn       = By.cssSelector("a.ViewBtn");
    private By disbProductField  = By.id("product");
    private By disbSubProduct    = By.id("subProduct");
    private By disbScheme        = By.id("scheme");
    private By disbMilestoneName = By.id("milestoneName");
    private By disbMilestoneDate = By.id("milestonedt");
    private By disbDate          = By.id("disbursedt");
    private By disbAmtTxt        = By.id("disburseamt_txt");
    private By disbApprovedAmt   = By.id("estdisburseamt_txt");
    private By disbCustomerId    = By.id("customerId");
    private By disbStatus        = By.id("disbureStatus");
    private By modeOfDisbursement = By.id("modeofdisbursement");
    private By disbSaveBtn       = By.id("saveData");

    // ========== Interfacing Status ==========
    private By interfacingStatusBtn = By.id("interfaceBtn");

    // ========== Document Archive Tab ==========
    private By documentArchiveTab = By.xpath("//a[contains(@href,'activeTab=DOCUMENT ARCHIVE')]");
    private By docArchiveEditBtns = By.cssSelector("#dt-docDownloadArc tbody tr td a.editBtn");

    // ========== Document Archive Form ==========
    private By archivalDateField        = By.id("archivalDate");
    private By branchField              = By.id("branch");
    private By documentArchiveCustodian = By.id("documentArchiveCustodian");
    private By rackNumberField          = By.id("rackNumber");
    private By shelfNumberField         = By.id("shelfNumber");
    private By boxNumberField           = By.id("boxNumber");
    private By docArchiveSaveBtn        = By.id("save");

    // ========== Next / Remark / Submit ==========
    private By nextBtn         = By.cssSelector("#Next a");
    private By remarkField     = By.id("remark");
    private By remarkSubmitBtn = By.id("remarkSubmit");

    // ========== Constructor ==========
    public LoanActivationPage(WebDriver driver) {
        this.driver = driver;
        this.jse    = (JavascriptExecutor) driver;
        this.wait   = new WebDriverWait(driver, Duration.ofSeconds(15));
        this.act    = new Actions(driver);
        PageFactory.initElements(driver, this);
    }

    // ========== App Summary Navigation ==========
    public void navigateToAppFromInbox(String rawAppId) throws InterruptedException {
        String appId = cleanAppId(rawAppId);
        driver.findElement(appSummaryBtn).click();
        Thread.sleep(3000);
        WebElement search = driver.findElement(searchBox);
        search.clear();
        search.sendKeys(appId);
        Thread.sleep(2000);
        act.doubleClick(driver.findElement(firstRow)).build().perform();
        Thread.sleep(3000);

        // Scroll down to progress bar and click LOAN ACTIVATION stage link
        jse.executeScript("window.scrollTo({top: document.body.scrollHeight, behavior: 'smooth'})");
        Thread.sleep(2000);
        WebElement loanActLink = driver.findElement(By.xpath(
            "//tbody[tr[@class='stage-header-row']//h5[normalize-space(text())='LOAN ACTIVATION']]" +
            "//tr[not(@class='stage-header-row')]//td[@class='stage-child']//a"
        ));
        jse.executeScript("arguments[0].scrollIntoView({behavior:'smooth', block:'center'})", loanActLink);
        Thread.sleep(1000);
        jse.executeScript("arguments[0].click()", loanActLink);
        Thread.sleep(3000);
    }

    private String cleanAppId(String raw) {
        if (raw == null) return "";
        java.util.regex.Matcher m = java.util.regex.Pattern.compile("[A-Z]+-\\d+").matcher(raw);
        if (m.find()) return m.group();
        m = java.util.regex.Pattern.compile("\\d+").matcher(raw);
        if (m.find()) return m.group();
        return raw.trim();
    }

    // ========== Overview ==========
    public String getOverviewCustomerName() { return driver.findElement(custNameLabel).getText().trim(); }
    public String getOverviewLoanAmount()   { return driver.findElement(loanAmtLabel).getText().trim(); }
    public String getOverviewStage()        { return driver.findElement(stageLabel).getText().trim(); }

    public void switchToMainContent() { driver.switchTo().defaultContent(); }

    // ========== DISBURSEMENT Tab ==========
    public void clickDisbursementTab() throws InterruptedException {
        WebElement tab = wait.until(ExpectedConditions.elementToBeClickable(disbursementTab));
        jse.executeScript("arguments[0].scrollIntoView({behavior:'smooth', block:'center'})", tab);
        jse.executeScript("arguments[0].click()", tab);
        wait.until(ExpectedConditions.elementToBeClickable(drawDownSubTab));
    }

    // ========== DrawDown Schedule ==========
    public void clickDrawDownSubTab() throws InterruptedException {
        WebElement tab = wait.until(ExpectedConditions.elementToBeClickable(drawDownSubTab));
        jse.executeScript("arguments[0].scrollIntoView({behavior:'smooth', block:'center'})", tab);
        jse.executeScript("arguments[0].click()", tab);
        wait.until(ExpectedConditions.frameToBeAvailableAndSwitchToIt(drawDownFrame));
        driver.switchTo().defaultContent();
    }

    public void switchToDrawDownFrame() {
        wait.until(ExpectedConditions.frameToBeAvailableAndSwitchToIt(drawDownFrame));
    }

    public void clickViewBtn() throws InterruptedException {
        WebElement btn = driver.findElement(By.cssSelector("a.ViewBtn"));
        jse.executeScript("arguments[0].scrollIntoView({behavior:'smooth', block:'center', inline:'nearest'})", btn);
        Thread.sleep(500);
        jse.executeScript("arguments[0].parentElement.parentElement.scrollLeft = arguments[0].offsetLeft", btn);
        Thread.sleep(500);
        act.moveToElement(btn).click().build().perform();
        Thread.sleep(3000);
    }

    public String getMilestoneName()  { return driver.findElement(milestoneName).getAttribute("value").trim(); }
    public String getProduct()        {
        try { return driver.findElement(productField).findElement(By.cssSelector("option:checked")).getText().trim(); }
        catch (Exception e) { return ""; }
    }
    public String getSubProduct()     {
        try { return driver.findElement(subProductField).findElement(By.cssSelector("option:checked")).getText().trim(); }
        catch (Exception e) { return ""; }
    }
    public String getScheme()         {
        try { return driver.findElement(schemeField).findElement(By.cssSelector("option:checked")).getText().trim(); }
        catch (Exception e) { return ""; }
    }

    public void enterMilestoneDate(String date) throws InterruptedException {
        WebElement field = driver.findElement(milestoneDateField);
        jse.executeScript("arguments[0].scrollIntoView({behavior:'smooth', block:'center'})", field);
        Thread.sleep(300);
        jse.executeScript("arguments[0].value=arguments[1]", field, date);
        field.sendKeys(Keys.TAB);
        Thread.sleep(500);
    }

    public void enterEstDisbursementDate(String date) throws InterruptedException {
        WebElement field = driver.findElement(estDisburseDateField);
        jse.executeScript("arguments[0].value=arguments[1]", field, date);
        field.sendKeys(Keys.TAB);
        Thread.sleep(500);
    }

    public void tabThroughDrawDownFields() throws InterruptedException {
        String[] fieldIds = {"milestoneName", "product", "subProduct", "scheme", "milestonedt", "estdisbursedt"};
        for (String id : fieldIds) {
            try {
                driver.findElement(By.id(id)).sendKeys(Keys.TAB);
                Thread.sleep(300);
            } catch (Exception e) {}
        }
    }

    public void enterExpectedDisbursementPercentage(String value) throws InterruptedException {
        WebElement field = driver.findElement(By.id("expprojcomplper_txt"));
        jse.executeScript("arguments[0].scrollIntoView({behavior:'smooth', block:'center'})", field);
        Thread.sleep(300);
        field.click();
        field.clear();
        field.sendKeys(value);
        field.sendKeys(Keys.TAB);
        Thread.sleep(1000);
        jse.executeScript("$('#expprojcomplper').val('" + value + "')");
        Thread.sleep(500);
    }

    public String getApprovedAmount()            { return driver.findElement(By.id("estdisburseamt_txt")).getAttribute("value").trim(); }
    public String getEstTotalDisbursementAmount() { return driver.findElement(By.id("remainingBal_txt")).getAttribute("value").trim(); }
    public String getRemainingAmount()            { return driver.findElement(By.id("leftAmount_txt")).getAttribute("value").trim(); }

    public void clickSave() throws InterruptedException {
        WebElement btn = driver.findElement(drawDownSaveBtn);
        jse.executeScript("arguments[0].scrollIntoView({behavior:'smooth', block:'center'})", btn);
        Thread.sleep(500);
        jse.executeScript("arguments[0].click()", btn);
        Thread.sleep(2000);
        try { jse.executeScript("arguments[0].click()", driver.findElement(By.id("submitForm"))); Thread.sleep(2000); } catch (Exception e) {}
        try { driver.switchTo().alert().accept(); Thread.sleep(1000); } catch (Exception e) {}
    }

    // ========== Disbursement Details ==========
    public void clickDisbursementDetailsSubTab() throws InterruptedException {
        WebElement tab = driver.findElement(disbursementDetailsSubTab);
        jse.executeScript("arguments[0].scrollIntoView({behavior:'smooth', block:'center'})", tab);
        Thread.sleep(500);
        jse.executeScript("arguments[0].click()", tab);
        Thread.sleep(3000);
    }

    public void switchToDisbursementDetailsFrame() throws InterruptedException {
        WebElement frame = driver.findElement(disbursementDetailsFrame);
        jse.executeScript("arguments[0].scrollIntoView({behavior:'smooth', block:'center'})", frame);
        Thread.sleep(500);
        driver.switchTo().frame(frame);
        Thread.sleep(2000);
    }


    public String getDisbProduct()      {
        try { return driver.findElement(disbProductField).findElement(By.cssSelector("option:checked")).getText().trim(); }
        catch (Exception e) { return driver.findElement(By.id("select2-product-container")).getText().trim(); }
    }
    public String getDisbSubProduct()   {
        try { return driver.findElement(disbSubProduct).findElement(By.cssSelector("option:checked")).getText().trim(); }
        catch (Exception e) { return driver.findElement(By.id("select2-subProduct-container")).getText().trim(); }
    }
    public String getDisbScheme()       {
        try { return driver.findElement(disbScheme).findElement(By.cssSelector("option:checked")).getText().trim(); }
        catch (Exception e) { return driver.findElement(By.id("select2-scheme-container")).getText().trim(); }
    }
    public String getDisbMilestoneName()  { return driver.findElement(disbMilestoneName).getAttribute("value").trim(); }
    public String getDisbMilestoneDate()  { return driver.findElement(disbMilestoneDate).getAttribute("value").trim(); }
    public String getDisbursementDate()   { return driver.findElement(disbDate).getAttribute("value").trim(); }
    public String getDisbursementAmount() { return driver.findElement(disbAmtTxt).getAttribute("value").trim(); }
    public String getDisbApprovedAmount() { return driver.findElement(disbApprovedAmt).getAttribute("value").trim(); }
    public String getDisbCustomerId()     { return driver.findElement(disbCustomerId).getAttribute("value").trim(); }
    public String getDisbStatus()         {
        try { return driver.findElement(disbStatus).findElement(By.cssSelector("option:checked")).getText().trim(); }
        catch (Exception e) { return driver.findElement(By.id("select2-disbureStatus-container")).getText().trim(); }
    }

    public void selectModeOfDisbursement(String value) throws InterruptedException {
        jse.executeScript("$('#modeofdisbursement').val('" + value + "').trigger('change')");
        Thread.sleep(1000);
    }

    public String getModeOfDisbursement() {
        return driver.findElement(By.id("select2-modeofdisbursement-container")).getText().trim();
    }

    public void clickDisbSave() throws InterruptedException {
        WebElement btn = driver.findElement(disbSaveBtn);
        jse.executeScript("arguments[0].scrollIntoView({behavior:'smooth', block:'center'})", btn);
        Thread.sleep(500);
        jse.executeScript("arguments[0].click()", btn);
        Thread.sleep(2000);
        try { jse.executeScript("arguments[0].click()", driver.findElement(By.id("submitForm"))); Thread.sleep(2000); } catch (Exception e) {}
        try { driver.switchTo().alert().accept(); Thread.sleep(1000); } catch (Exception e) {}
    }

    // ========== Disbursement Details - View + Interfacing Status ==========
    public void clickDisbViewBtn() throws InterruptedException {
        wait.until(d -> {
            try { return d.findElement(By.cssSelector("a.ViewBtn")).isDisplayed(); }
            catch (Exception e) { return false; }
        });
        WebElement btn = driver.findElement(disbViewBtn);
        jse.executeScript("arguments[0].scrollIntoView({behavior:'smooth', block:'center', inline:'nearest'})", btn);
        Thread.sleep(500);
        jse.executeScript("arguments[0].parentElement.parentElement.scrollLeft = arguments[0].offsetLeft", btn);
        Thread.sleep(500);
        act.moveToElement(btn).click().build().perform();
        Thread.sleep(2000);
    }

    public void clickDisburseBtn() throws InterruptedException {
        WebElement btn = driver.findElement(By.id("disburseBtn"));
        jse.executeScript("arguments[0].scrollIntoView({behavior:'smooth', block:'center'})", btn);
        Thread.sleep(500);
        jse.executeScript("arguments[0].click()", btn);
        Thread.sleep(5000);
    }

    public void clickInterfacingStatusBtn() throws InterruptedException {
        WebElement btn = driver.findElement(interfacingStatusBtn);
        jse.executeScript("arguments[0].scrollIntoView({behavior:'smooth', block:'center'})", btn);
        Thread.sleep(500);
        jse.executeScript("arguments[0].click()", btn);
        Thread.sleep(2000);
    }

    // Returns {"pass": ["Customer Creation", ...], "fail": ["Update Ind [Fail]", ...]}
    public Map<String, List<String>> getInterfacingStatusResults() {
        List<String> pass = new ArrayList<>();
        List<String> fail = new ArrayList<>();
        List<WebElement> rows = driver.findElements(By.cssSelector("#interfacingList #tblData tbody tr"));
        for (WebElement row : rows) {
            List<WebElement> cells = row.findElements(By.tagName("td"));
            if (cells.size() >= 2) {
                String name   = cells.get(0).getText().trim();
                String status = cells.get(1).getText().trim();
                if (status.equalsIgnoreCase("Success")) pass.add(name);
                else fail.add(name + " [" + status + "]");
            }
        }
        return Map.of("pass", pass, "fail", fail);
    }

    public void closeInterfacingModal() throws InterruptedException {
        jse.executeScript("arguments[0].click()",
            driver.findElement(By.cssSelector(".mh-cross[data-dismiss='modal']")));
        Thread.sleep(1500);
    }

    // ========== Document Archive ==========
    public void clickDocumentArchiveTab() throws InterruptedException {
        driver.switchTo().defaultContent();
        jse.executeScript("window.scrollTo({top: document.body.scrollHeight, behavior: 'smooth'})");
        Thread.sleep(2000);
        WebElement tab = driver.findElement(documentArchiveTab);
        jse.executeScript("arguments[0].scrollIntoView({behavior:'smooth', block:'center'})", tab);
        Thread.sleep(1000);
        jse.executeScript("arguments[0].click()", tab);
        Thread.sleep(6000);
        jse.executeScript("window.scrollTo({top: document.body.scrollHeight, behavior: 'smooth'})");
        Thread.sleep(2000);
    }

    public int getDocArchiveRowCount() throws InterruptedException {
        driver.switchTo().defaultContent();
        jse.executeScript("window.scrollTo({top: document.body.scrollHeight, behavior: 'smooth'})");
        Thread.sleep(3000);
        List<WebElement> rows = driver.findElements(By.cssSelector("#dt-docDownloadArc tbody tr"));
        rows.removeIf(r -> r.getText().trim().isEmpty() ||
                      (r.getAttribute("class") != null && r.getAttribute("class").contains("dataTables_empty")));
        return rows.size();
    }

    public void clickDocArchiveEditBtn(int rowIndex) throws InterruptedException {
        jse.executeScript("window.scrollTo({top: document.body.scrollHeight, behavior: 'smooth'})");
        Thread.sleep(1000);
        List<WebElement> rows = driver.findElements(By.cssSelector("#dt-docDownloadArc tbody tr"));
        rows.removeIf(r -> r.getText().trim().isEmpty() ||
                      (r.getAttribute("class") != null && r.getAttribute("class").contains("dataTables_empty")));
        WebElement row = rows.get(rowIndex);
        // Try edit button first, fallback to double-click on row
        List<WebElement> btns = row.findElements(By.cssSelector("a.editBtn, a.EditBtn, a[title='Edit'], a.edit"));
        if (!btns.isEmpty()) {
            WebElement btn = btns.get(0);
            jse.executeScript("arguments[0].scrollIntoView({behavior:'smooth', block:'center'})", btn);
            Thread.sleep(500);
            jse.executeScript("arguments[0].click()", btn);
        } else {
            // No edit button — double click the row to open form
            WebElement firstCell = row.findElement(By.tagName("td"));
            jse.executeScript("arguments[0].scrollIntoView({behavior:'smooth', block:'center'})", firstCell);
            Thread.sleep(500);
            act.doubleClick(firstCell).build().perform();
        }
        Thread.sleep(3000);
    }

    public void fillDocumentArchiveForm(String archivalDate, String branchValue,
                                        String custodianValue, String rack,
                                        String shelf, String box) throws InterruptedException {
        WebElement dateField = driver.findElement(archivalDateField);
        jse.executeScript("arguments[0].scrollIntoView({behavior:'smooth', block:'center'})", dateField);
        Thread.sleep(300);
        jse.executeScript("arguments[0].value=arguments[1]", dateField, archivalDate);
        dateField.sendKeys(Keys.TAB);
        Thread.sleep(500);

        jse.executeScript("$('#branch').val('" + branchValue + "').trigger('change')");
        Thread.sleep(1000);

        // Select2 custodian — find option by text, set its value
        String custodianScript =
            "var opts = $('#documentArchiveCustodian option');" +
            "for(var i=0;i<opts.length;i++){" +
            "  if(opts[i].text.trim().toUpperCase().indexOf('" + custodianValue.toUpperCase() + "')>=0){" +
            "    $('#documentArchiveCustodian').val(opts[i].value).trigger('change'); break;" +
            "  }" +
            "}";
        jse.executeScript(custodianScript);
        Thread.sleep(1000);

        WebElement rackField = driver.findElement(rackNumberField);
        jse.executeScript("arguments[0].scrollIntoView({behavior:'smooth', block:'center'})", rackField);
        Thread.sleep(300);
        rackField.clear();
        rackField.sendKeys(rack);
        Thread.sleep(300);

        WebElement shelfField = driver.findElement(shelfNumberField);
        shelfField.clear();
        shelfField.sendKeys(shelf);
        Thread.sleep(300);

        WebElement boxField = driver.findElement(boxNumberField);
        boxField.clear();
        boxField.sendKeys(box);
        Thread.sleep(300);
    }

    public String getArchivalDate()        { return driver.findElement(archivalDateField).getAttribute("value").trim(); }
    public String getRackNumber()          { return driver.findElement(rackNumberField).getAttribute("value").trim(); }
    public String getShelfNumber()         { return driver.findElement(shelfNumberField).getAttribute("value").trim(); }
    public String getBoxNumber()           { return driver.findElement(boxNumberField).getAttribute("value").trim(); }
    public String getDocArchiveCustodian() {
        try { return driver.findElement(By.id("select2-documentArchiveCustodian-container")).getText().trim(); }
        catch (Exception e) { return driver.findElement(By.cssSelector("#documentArchiveCustodian option:checked")).getText().trim(); }
    }
    public String getDocArchiveBranch()    { return driver.findElement(By.id("select2-branch-container")).getText().trim(); }

    public void clickDocArchiveSave() throws InterruptedException {
        WebElement btn = driver.findElement(docArchiveSaveBtn);
        jse.executeScript("arguments[0].scrollIntoView({behavior:'smooth', block:'center'})", btn);
        Thread.sleep(500);
        jse.executeScript("arguments[0].click()", btn);
        Thread.sleep(2000);
        try { jse.executeScript("arguments[0].click()", driver.findElement(By.id("popUpYes"))); Thread.sleep(2000); } catch (Exception e) {}
        try { driver.switchTo().alert().accept(); Thread.sleep(1000); } catch (Exception e) {}
    }

    // ========== Next + Remark + Submit ==========
    public void clickNext() throws InterruptedException {
        WebElement btn = wait.until(ExpectedConditions.elementToBeClickable(nextBtn));
        jse.executeScript("arguments[0].scrollIntoView({behavior:'smooth', block:'center'})", btn);
        jse.executeScript("arguments[0].click()", btn);
        wait.until(ExpectedConditions.visibilityOfElementLocated(remarkField));
    }

    public void enterRemark(String text) {
        WebElement field = driver.findElement(remarkField);
        jse.executeScript("arguments[0].value=''", field);
        jse.executeScript("arguments[0].value=arguments[1]", field, text);
    }

    public void clickRemarkSubmit() throws InterruptedException {
        jse.executeScript("arguments[0].click()", driver.findElement(remarkSubmitBtn));
        Thread.sleep(2000);
    }

    public String getStageSuccessMessage() {
        try {
            WebElement msg = wait.until(ExpectedConditions.visibilityOfElementLocated(
                By.cssSelector("h5.blue-title")));
            return msg.getText().trim();
        } catch (Exception e) { return ""; }
    }
}
