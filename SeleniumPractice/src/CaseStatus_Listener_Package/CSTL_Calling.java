package CaseStatus_Listener_Package;

import Utility_Package.ReportManager;
import Utility_Package.TestListener;
import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.Select;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.DataProvider;
import org.testng.annotations.AfterClass;
import org.testng.annotations.Listeners;
import org.testng.annotations.Test;

@Listeners(TestListener.class)
public class CSTL_Calling extends CSTL_Login {

	int tc = 1;

	private void cstlLog(String id, String field, String action, String expected, String actual, boolean pass) {
		log(id + " | " + field, action, expected, actual, pass);
	}

	@BeforeClass
	public void setup() throws Exception {
		ReportManager.setTestSuiteName("CaseStatusListener");
		ReportManager.startTest("Case Status Listener - Excel Data Driven");
		cstLogin();
		CSTL_Frame frame = new CSTL_Frame();
		frame.switchToFrame();
	}

	// ===== STEP 2: Excel se data lo =====
	@DataProvider(name = "excelData")
	public Object[][] getData() throws Exception {
		System.out.println("=================================================");
		System.out.println("📊 READING DATA FROM EXCEL...");
		System.out.println("=================================================");
		Object[][] data = CSTL_ExcelReader.readExcelData();
		System.out.println("Total test data rows: " + data.length);
		return data;
	}

	// ===== STEP 3: Har row ke liye test chale =====
	@Test(dataProvider = "excelData")
	public void testFromExcel(String testID, String field, String action, String input, String expected) throws Exception {

		System.out.println("\n==================================================");
		System.out.println("▶️ RUNNING: " + testID + " | " + field + " | " + action);
		System.out.println("==================================================");

		switch (field) {

			case "CaseStatusDD":
				WebElement dd = driver.findElement(By.id("caseStatusId"));
				Select s = new Select(dd);
				s.selectByVisibleText(input);
				Thread.sleep(500);
				String selected = s.getFirstSelectedOption().getText().trim();
				cstlLog(testID, field, action + " → " + input, expected, selected, selected.equals(expected));
				sa.assertEquals(selected, expected, testID + " — DD mismatch");
				// Reset DD for next test
				s.selectByIndex(0);
				Thread.sleep(300);
				break;

			case "Remark":
				WebElement rm = driver.findElement(By.id("remarks"));
				rm.clear();
				rm.sendKeys(input);
				Thread.sleep(300);
				String rmVal = rm.getAttribute("value");
				cstlLog(testID, field, action + " → " + input, expected, rmVal, rmVal.equals(expected));
				sa.assertEquals(rmVal, expected, testID + " — Remark mismatch");
				rm.clear();
				Thread.sleep(300);
				break;

			case "Save":
				if (action.equals("selectAndSave")) {
					// input = "Legal|Save Remark from Excel"
					String[] parts = input.split("\\|");
					String ddValue = parts[0];
					String remarkValue = parts[1];

					// Select DD
					WebElement savDD = driver.findElement(By.id("caseStatusId"));
					Select savSelect = new Select(savDD);
					savSelect.selectByVisibleText(ddValue);
					Thread.sleep(500);

					// Enter Remark
					WebElement savRm = driver.findElement(By.id("remarks"));
					savRm.clear();
					savRm.sendKeys(remarkValue);
					Thread.sleep(300);

					// Click Save
					driver.findElement(By.id("saveData")).click();
					Thread.sleep(2000);

					String toast = getSuccessToastMsg();
					cstlLog(testID, field, "DD=" + ddValue + ", Remark=" + remarkValue, expected, toast.isEmpty() ? "No toast" : toast, toast.equals(expected));
					sa.assertEquals(toast, expected, testID + " — Save toast mismatch");

				} else if (action.equals("emptyAndSave")) {
					// Reset DD + Clear Remark
					WebElement emDD = driver.findElement(By.id("caseStatusId"));
					Select emSelect = new Select(emDD);
					emSelect.selectByIndex(0);
					Thread.sleep(300);
					WebElement emRm = driver.findElement(By.id("remarks"));
					emRm.clear();
					Thread.sleep(300);

					// Click Save
					driver.findElement(By.id("saveData")).click();
					Thread.sleep(1000);

					String errToast = getToastMsg();
					cstlLog(testID, field, "Empty save — no DD, no Remark", expected, errToast.isEmpty() ? "No toast" : errToast, errToast.equals(expected));
					sa.assertEquals(errToast, expected, testID + " — Empty save toast mismatch");
				}
				break;

			default:
				System.out.println("⚠️ Unknown field: " + field);
				break;
		}
	}

	// ===== STEP 4: End mein sab results =====
	@AfterClass(alwaysRun = true)
	public void teardown() {
		System.out.println("\n=================================================");
		System.out.println("\ud83d\udcca ALL TESTS FROM EXCEL COMPLETED");
		System.out.println("=================================================");
		sa.assertAll();
		ReportManager.flushReport();
		if (driver != null) { driver.quit(); driver = null; }
	}
}

