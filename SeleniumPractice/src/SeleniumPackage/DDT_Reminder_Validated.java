package SeleniumPackage;

import java.io.FileInputStream;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.List;
import java.util.concurrent.TimeUnit;

import org.apache.poi.xssf.usermodel.XSSFSheet;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;
import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.Keys;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.ui.Select;
import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;
import org.testng.asserts.SoftAssert;

public class DDT_Reminder_Validated {

	WebDriver driver;
	JavascriptExecutor jse;
	Actions act;
	SoftAssert sa;
	int rowCount = 0;

	// ========== HIGHLIGHT METHOD ==========
	public void highlight(WebElement element) {
		jse.executeScript("arguments[0].style.backgroundColor='lightblue'", element);
	}

	@DataProvider(name = "Giver")
	public Object[][] excel() throws Exception {
		FileInputStream fis = new FileInputStream("D:\\Excel_File_For_Selenium\\PTP_TestData.xlsx");
		XSSFWorkbook wb = new XSSFWorkbook(fis);
		XSSFSheet sh = wb.getSheet("Reminder");

		int totrow = sh.getPhysicalNumberOfRows();
		int totcol = sh.getRow(0).getPhysicalNumberOfCells();

		Object[][] data = new Object[totrow - 1][totcol];
		for (int i = 1; i < totrow; i++) {
			for (int j = 0; j < totcol; j++) {
				if (sh.getRow(i) != null && sh.getRow(i).getCell(j) != null) {
					data[i - 1][j] = sh.getRow(i).getCell(j).toString();
				} else {
					data[i - 1][j] = "";
				}
			}
		}
		wb.close();
		return data;
	}

	@BeforeClass
	public void login() throws Exception {
		System.setProperty("webdriver.chrome.driver", "D:\\chromedriver-145\\chromedriver-win64\\chromedriver.exe");
		driver = new ChromeDriver();
		driver.manage().window().maximize();
		driver.manage().timeouts().implicitlyWait(10, TimeUnit.SECONDS);

		driver.get("http://10.10.230.15:8181/lcs-finairoLending-1.0.1");

		driver.findElement(By.id("loginId")).sendKeys("Shelly", Keys.TAB);
		driver.findElement(By.id("uiPwd")).sendKeys("abcd1234", Keys.TAB);
		Thread.sleep(2000);
		driver.findElement(By.id("userLogin")).click();

		jse = (JavascriptExecutor) driver;
		act = new Actions(driver);

		driver.findElement(By.xpath("//*[@class='item-nav']/div")).click();
		driver.findElement(By.xpath("//*[@id='COMMONCOLLECTORLIST']/a")).click();

		driver.findElement(By.xpath("//*[@type='search']")).sendKeys("610");
		WebElement case1 = driver.findElement(By.xpath("//*[text()='610']"));
		act.doubleClick(case1).build().perform();

		WebElement commhis = driver.findElement(By.xpath("//*[contains(@href,'=Communication History')]"));
		jse.executeScript("arguments[0].scrollIntoView({block:'center',behavior:'smooth'})", commhis);
		Thread.sleep(1000);
		commhis.click();
	}

	@Test(dataProvider = "Giver")
	public void test(String reminderType1, String reminderDate1, String remicrdate1,
			String remarks1) throws Exception {

		sa = new SoftAssert();
		rowCount++;
		System.out.println("=================================================");
		System.out.println("========== ROW " + rowCount + " START ==========");
		System.out.println("Data → Type='" + reminderType1 + "' | Date='" + reminderDate1
				+ "' | CreateDate='" + remicrdate1 + "' | Remarks='" + remarks1 + "'");
		System.out.println("=================================================");

		String today = LocalDate.now().format(DateTimeFormatter.ofPattern("dd-MM-yyyy"));

		// =========Parent Frame================//
		driver.switchTo().parentFrame();
		WebElement rem = driver.findElement(By.xpath("(//a[contains(text(),'Reminder')])[1]"));
		jse.executeScript("arguments[0].scrollIntoView({block:'center',behavior:'smooth'})", rem);

		// ===========Child Frame=============//
		driver.switchTo().frame("fetchReminderDtlsPageFrame");

		// =====================================================
		// 1. REMINDER TYPE (Dropdown) — 9 Validations
		// =====================================================
		WebElement remindType = driver.findElement(By.id("reminderType"));
		jse.executeScript("arguments[0].scrollIntoView({block:'center',behavior:'smooth'})", remindType);
		Select s1 = new Select(remindType);

		// D-V1: Displayed
		boolean typeDisp = remindType.isDisplayed();
		sa.assertTrue(typeDisp, "Row " + rowCount + " - RemType: Expected=Displayed | Actual=Not Displayed");
		System.out.println("RemType D-V1 : Expected=Displayed | Actual=" + (typeDisp ? "Displayed" : "Not Displayed") + " | " + (typeDisp ? "PASS ✅" : "FAIL ❌"));

		// D-V2: Enabled
		boolean typeEn = remindType.isEnabled();
		sa.assertTrue(typeEn, "Row " + rowCount + " - RemType: Expected=Enabled | Actual=Disabled");
		System.out.println("RemType D-V2 : Expected=Enabled | Actual=" + (typeEn ? "Enabled" : "Disabled") + " | " + (typeEn ? "PASS ✅" : "FAIL ❌"));

		// D-V3: Multi-select check
		boolean isMulti = s1.isMultiple();
		sa.assertFalse(isMulti, "Row " + rowCount + " - RemType: Expected=Single-Select | Actual=Multi-Select");
		System.out.println("RemType D-V3 : Expected=Single-Select | Actual=" + (isMulti ? "Multi-Select" : "Single-Select") + " | " + (!isMulti ? "PASS ✅" : "FAIL ❌"));

		// D-V4: Total options > 1
		List<WebElement> options = s1.getOptions();
		sa.assertTrue(options.size() > 1, "Row " + rowCount + " - RemType: Expected=Options>1 | Actual=" + options.size());
		System.out.println("RemType D-V4 : Expected=Options>1 | Actual=" + options.size() + " | " + (options.size() > 1 ? "PASS ✅" : "FAIL ❌"));

		// D-V5: Print all options
		System.out.print("RemType D-V5 : All Options → ");
		for (WebElement op : options) 
		{
			System.out.print(op.getText() + " , "); 
		}
		System.out.println();

		// D-V6: Default selected value
		String defaultVal = s1.getFirstSelectedOption().getText();
		System.out.println("RemType D-V6 : Default Selected → '" + defaultVal + "'");

		// D-V7: Keyboard arrow down test
		remindType.sendKeys(Keys.DOWN);
		Thread.sleep(300);
		String afterArrow = s1.getFirstSelectedOption().getText();
		boolean arrowWorked = !afterArrow.equals(defaultVal);
		System.out.println("RemType D-V7 : Arrow Down → Before='" + defaultVal + "' | After='" + afterArrow + "' | " + (arrowWorked ? "PASS ✅" : "SAME VALUE"));

		// D-V8: Mandatory / Blank check
		if (reminderType1 == null || reminderType1.trim().isEmpty()) {
			sa.fail("Row " + rowCount + " - RemType: Expected=Non-Blank(Mandatory) | Actual=BLANK");
			System.out.println("RemType D-V8 : Expected=Non-Blank(Mandatory) | Actual=BLANK | FAIL ❌");
		} else {
			// D-V9: Set Excel value + verify
			try {
				highlight(remindType);
				s1.selectByVisibleText(reminderType1);
				String actual = s1.getFirstSelectedOption().getText();
				sa.assertEquals(actual, reminderType1, "Row " + rowCount + " - RemType: Expected='" + reminderType1 + "' | Actual='" + actual + "'");
				System.out.println("RemType D-V9 : Expected='" + reminderType1 + "' | Actual='" + actual + "' | " + (actual.equals(reminderType1) ? "PASS ✅" : "FAIL ❌"));
			} catch (Exception e) {
				sa.fail("Row " + rowCount + " - RemType: Expected='" + reminderType1 + "' | Actual=NOT FOUND in dropdown");
				System.out.println("RemType D-V9 : Expected='" + reminderType1 + "' | Actual=NOT FOUND in dropdown | FAIL ❌");
			}
		}

		// =====================================================
		// 2. REMINDER DATE — 12 Validations
		// =====================================================
		System.out.println("--- REMINDER DATE ---");
		WebElement remDate = driver.findElement(By.id("reminderDate"));
		validateDateField(remDate, reminderDate1, "RemDate", today);

		// =====================================================
		// 3. REMINDER CREATE DATE — 12 Validations
		// =====================================================
		System.out.println("--- REMINDER CREATE DATE ---");
		WebElement crDate = driver.findElement(By.id("reminderCreateDate"));
		validateDateField(crDate, remicrdate1, "CrDate", today);

		// =====================================================
		// 4. REMARKS (TextArea) — 5 Validations
		// =====================================================
		System.out.println("--- REMARKS ---");
		WebElement remarksEl = driver.findElement(By.id("remarks"));

		// R-V1: Displayed
		boolean remDisp = remarksEl.isDisplayed();
		sa.assertTrue(remDisp, "Row " + rowCount + " - Remarks: Expected=Displayed | Actual=Not Displayed");
		System.out.println("Remarks R-V1 : Expected=Displayed | Actual=" + (remDisp ? "Displayed" : "Not Displayed") + " | " + (remDisp ? "PASS ✅" : "FAIL ❌"));

		// R-V2: Enabled
		boolean remEn = remarksEl.isEnabled();
		sa.assertTrue(remEn, "Row " + rowCount + " - Remarks: Expected=Enabled | Actual=Disabled");
		System.out.println("Remarks R-V2 : Expected=Enabled | Actual=" + (remEn ? "Enabled" : "Disabled") + " | " + (remEn ? "PASS ✅" : "FAIL ❌"));

		// R-V3: MaxLength check
		String maxLen = remarksEl.getAttribute("maxlength");
		System.out.println("Remarks R-V3 : MaxLength → " + (maxLen != null ? maxLen : "No limit set"));

		// R-V4: Blank check
		if (remarks1 == null || remarks1.trim().isEmpty()) {
			System.out.println("Remarks R-V4 : Expected=Any Value | Actual=BLANK | SKIPPED (may not be mandatory)");
		} else {
			// R-V5: Enter value + verify
			highlight(remarksEl);
			remarksEl.sendKeys(remarks1);
			String actualRem = remarksEl.getAttribute("value");
			sa.assertEquals(actualRem, remarks1, "Row " + rowCount + " - Remarks: Expected='" + remarks1 + "' | Actual='" + actualRem + "'");
			System.out.println("Remarks R-V5 : Expected='" + remarks1 + "' | Actual='" + actualRem + "' | " + (remarks1.equals(actualRem) ? "PASS ✅" : "FAIL ❌"));
		}

		// =====================================================
		// ALL VALIDATIONS DONE — NOW SAVE
		// =====================================================
		System.out.println("--- SAVE BUTTON ---");
		WebElement saveBtn = driver.findElement(By.id("save"));

		boolean saveDisp = saveBtn.isDisplayed();
		sa.assertTrue(saveDisp, "Row " + rowCount + " - Save: Expected=Displayed | Actual=Not Displayed");
		System.out.println("Save S-V1 : Expected=Displayed | Actual=" + (saveDisp ? "Displayed" : "Not Displayed") + " | " + (saveDisp ? "PASS ✅" : "FAIL ❌"));

		boolean saveEn = saveBtn.isEnabled();
		sa.assertTrue(saveEn, "Row " + rowCount + " - Save: Expected=Enabled | Actual=Disabled");
		System.out.println("Save S-V2 : Expected=Enabled | Actual=" + (saveEn ? "Enabled" : "Disabled") + " | " + (saveEn ? "PASS ✅" : "FAIL ❌"));

		String btnText = saveBtn.getText();
		if (btnText.isEmpty())
		btnText = saveBtn.getAttribute("value");
		System.out.println("Save S-V3 : Button Text → '" + btnText + "'");

		highlight(saveBtn);
		saveBtn.click();
		Thread.sleep(1000);
		System.out.println("Save S-V4 : Clicked ✅");

		boolean hasError = driver.getPageSource().contains("Error");
		sa.assertFalse(hasError, "Row " + rowCount + " - Save: Expected=No Error | Actual=Error Found");
		System.out.println("Save S-V5 : Expected=No Error | Actual=" + (hasError ? "Error Found" : "No Error") + " | " + (!hasError ? "PASS ✅" : "FAIL ❌"));

		// =====================================================
		// ROW RESULT
		// =====================================================
		System.out.println("=================================================");
		System.out.println("========== ROW " + rowCount + " DONE ==========");
		sa.assertAll();

		// =========Refresh for next row=========
		Thread.sleep(800);
		driver.switchTo().parentFrame();
		driver.switchTo().parentFrame();
		WebElement rem1 = driver.findElement(By.xpath("(//a[contains(text(),'Reminder')])[1]"));
		jse.executeScript("arguments[0].scrollIntoView({block:'center',behavior:'smooth'})", rem1);
		driver.navigate().refresh();
		Thread.sleep(2000);
	}

	// =====================================================
	// DATE VALIDATION METHOD — 12 Checks (reused for both date fields)
	// =====================================================
	public void validateDateField(WebElement dateField, String dateValue, String fieldName, String today) throws Exception {

		// DT-V1: Displayed
		boolean disp = dateField.isDisplayed();
		sa.assertTrue(disp, "Row " + rowCount + " - " + fieldName + ": Expected=Displayed | Actual=Not Displayed");
		System.out.println(fieldName + " DT-V1 : Expected=Displayed | Actual=" + (disp ? "Displayed" : "Not Displayed") + " | " + (disp ? "PASS ✅" : "FAIL ❌"));

		// DT-V2: Enabled
		boolean en = dateField.isEnabled();
		sa.assertTrue(en, "Row " + rowCount + " - " + fieldName + ": Expected=Enabled | Actual=Disabled");
		System.out.println(fieldName + " DT-V2 : Expected=Enabled | Actual=" + (en ? "Enabled" : "Disabled") + " | " + (en ? "PASS ✅" : "FAIL ❌"));

		// DT-V3: ReadOnly check
		String ro = dateField.getAttribute("readonly");
		System.out.println(fieldName + " DT-V3 : Expected=Editable(null) | Actual=" + (ro == null ? "Editable(null)" : "ReadOnly(true)"));

		// DT-V4: sendKeys test (type something and check if field accepts)
		dateField.clear();
		dateField.sendKeys("test123");
		String afterType = dateField.getAttribute("value");
		System.out.println(fieldName + " DT-V4 : sendKeys Test → Typed='test123' | Field Value='" + afterType + "' | " + (afterType.contains("test123") ? "Accepted" : "Rejected/Filtered"));
		dateField.clear();

		// DT-V5: Mandatory / Blank check
		if (dateValue == null || dateValue.trim().isEmpty()) {
			sa.fail("Row " + rowCount + " - " + fieldName + ": Expected=Non-Blank(Mandatory) | Actual=BLANK");
			System.out.println(fieldName + " DT-V5 : Expected=Non-Blank(Mandatory) | Actual=BLANK | FAIL ❌");
			return;
		}

		// DT-V6: Format check (dd-MM-yyyy)
		boolean validFormat = dateValue.matches("\\d{2}-\\d{2}-\\d{4}");
		sa.assertTrue(validFormat, "Row " + rowCount + " - " + fieldName + ": Expected Format=dd-MM-yyyy | Actual='" + dateValue + "'");
		System.out.println(fieldName + " DT-V6 : Expected Format=dd-MM-yyyy | Actual='" + dateValue + "' | " + (validFormat ? "PASS ✅" : "FAIL ❌"));

		if (validFormat) {
			int day = Integer.parseInt(dateValue.substring(0, 2));
			int month = Integer.parseInt(dateValue.substring(3, 5));
			int year = Integer.parseInt(dateValue.substring(6, 10));

			// DT-V7: Zero day check (00-xx-xxxx)
			sa.assertNotEquals(day, 0, "Row " + rowCount + " - " + fieldName + ": Expected Day!=00 | Actual Day=00");
			System.out.println(fieldName + " DT-V7 : Expected Day!=00 | Actual Day=" + String.format("%02d", day) + " | " + (day != 0 ? "PASS ✅" : "FAIL ❌"));

			// DT-V8: Zero month check (xx-00-xxxx)
			sa.assertNotEquals(month, 0, "Row " + rowCount + " - " + fieldName + ": Expected Month!=00 | Actual Month=00");
			System.out.println(fieldName + " DT-V8 : Expected Month!=00 | Actual Month=" + String.format("%02d", month) + " | " + (month != 0 ? "PASS ✅" : "FAIL ❌"));

			// DT-V9: Day > 31 check
			sa.assertTrue(day <= 31, "Row " + rowCount + " - " + fieldName + ": Expected Day<=31 | Actual Day=" + day);
			System.out.println(fieldName + " DT-V9 : Expected Day<=31 | Actual Day=" + day + " | " + (day <= 31 ? "PASS ✅" : "FAIL ❌"));

			// DT-V10: Month > 12 check
			sa.assertTrue(month <= 12, "Row " + rowCount + " - " + fieldName + ": Expected Month<=12 | Actual Month=" + month);
			System.out.println(fieldName + " DT-V10: Expected Month<=12 | Actual Month=" + month + " | " + (month <= 12 ? "PASS ✅" : "FAIL ❌"));

			// DT-V11: Past / Current / Future date check
			try {
				LocalDate inputDate = LocalDate.of(year, month, day);
				LocalDate todayDate = LocalDate.now();
				String dateType = inputDate.isBefore(todayDate) ? "PAST" : inputDate.isEqual(todayDate) ? "CURRENT" : "FUTURE";
				System.out.println(fieldName + " DT-V11: Date Type → " + dateType + " | Input=" + dateValue + " | Today=" + today);
			} catch (Exception e) {
				sa.fail("Row " + rowCount + " - " + fieldName + ": Invalid date - " + dateValue);
				System.out.println(fieldName + " DT-V11: INVALID DATE → '" + dateValue + "' (e.g. Feb 30, Apr 31) | FAIL ❌");
			}
		}

		// DT-V12: Enter final value + verify
		highlight(dateField);
		dateField.clear();
		dateField.sendKeys(dateValue);
		Thread.sleep(300);
		dateField.sendKeys(Keys.ESCAPE);
		String actualVal = dateField.getAttribute("value");
		sa.assertEquals(actualVal, dateValue, "Row " + rowCount + " - " + fieldName + ": Expected='" + dateValue + "' | Actual='" + actualVal + "'");
		System.out.println(fieldName + " DT-V12: Expected='" + dateValue + "' | Actual='" + actualVal + "' | " + (dateValue.equals(actualVal) ? "PASS ✅" : "FAIL ❌"));
	}

	@AfterClass
	public void done() {
		System.out.println("=================================================");
		System.out.println("ALL ROWS COMPLETED - Total: " + rowCount);
		System.out.println("=================================================");
	}
}
