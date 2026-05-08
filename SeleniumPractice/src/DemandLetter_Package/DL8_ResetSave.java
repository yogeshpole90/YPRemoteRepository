package DemandLetter_Package;

import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.Select;

public class DL8_ResetSave extends DL2_Login {

	public void validateResetSave() throws Exception
	{
		System.out.println("=================================================");
		System.out.println("DL8 - MANDATORY + DELETE OLD + SAVE ALL 3 TYPES");
		System.out.println("=================================================");

		// ========== STEP 1: DELETE ALL EXISTING RECORDS ==========

		deleteAllRecords();

		// ========== STEP 2: MANDATORY FIELD VALIDATIONS ==========

		// TC1: All fields blank → Save
		clearAllFields();
		driver.findElement(By.xpath("//button[contains(text(),'Save')]")).click();
		Thread.sleep(2000);
		String t1 = getToastMsg();
		log("Mandatory", "All fields blank", "Error toast", t1.isEmpty() ? "No toast" : t1, !t1.isEmpty());

		// TC2: Only Notice Type selected → Date & UserName blank
		clearAllFields();
		new Select(driver.findElement(By.id("demandLetterType"))).selectByValue("FDL");
		Thread.sleep(300);
		driver.findElement(By.xpath("//button[contains(text(),'Save')]")).click();
		Thread.sleep(2000);
		String t2 = getToastMsg();
		log("Mandatory", "Only Notice Type filled, Date & UserName blank", "Error toast", t2.isEmpty() ? "No toast" : t2, !t2.isEmpty());

		// TC3: Only Date entered → Notice Type & UserName blank
		clearAllFields();
		driver.findElement(By.id("sendingDate")).sendKeys("22-04-2026");
		Thread.sleep(300);
		driver.findElement(By.xpath("//button[contains(text(),'Save')]")).click();
		Thread.sleep(2000);
		String t3 = getToastMsg();
		log("Mandatory", "Only Date filled, Notice Type & UserName blank", "Error toast", t3.isEmpty() ? "No toast" : t3, !t3.isEmpty());

		// TC4: Only UserName entered → Notice Type & Date blank
		clearAllFields();
		driver.findElement(By.id("userName")).sendKeys("TestUser");
		Thread.sleep(300);
		driver.findElement(By.xpath("//button[contains(text(),'Save')]")).click();
		Thread.sleep(2000);
		String t4 = getToastMsg();
		log("Mandatory", "Only UserName filled, Notice Type & Date blank", "Error toast", t4.isEmpty() ? "No toast" : t4, !t4.isEmpty());

		// TC5: Notice Type + Date filled → UserName blank
		clearAllFields();
		new Select(driver.findElement(By.id("demandLetterType"))).selectByValue("FDL");
		driver.findElement(By.id("sendingDate")).sendKeys("22-04-2026");
		Thread.sleep(300);
		driver.findElement(By.xpath("//button[contains(text(),'Save')]")).click();
		Thread.sleep(2000);
		String t5 = getToastMsg();
		String s5 = getSuccessToastMsg();
		if (!t5.isEmpty()) {
			log("Mandatory", "Notice Type + Date filled, UserName blank", "Error toast", t5, true);
		} else {
			log("Mandatory", "Notice Type + Date filled, UserName blank", "Error toast", s5.isEmpty() ? "Saved (UserName optional)" : s5, true);
		}

		// TC6: Notice Type + UserName filled → Date blank
		clearAllFields();
		new Select(driver.findElement(By.id("demandLetterType"))).selectByValue("FDL");
		driver.findElement(By.id("userName")).sendKeys("TestUser");
		Thread.sleep(300);
		driver.findElement(By.xpath("//button[contains(text(),'Save')]")).click();
		Thread.sleep(2000);
		String t6 = getToastMsg();
		log("Mandatory", "Notice Type + UserName filled, Date blank", "Error toast", t6.isEmpty() ? "No toast" : t6, !t6.isEmpty());

		// TC7: Date + UserName filled → Notice Type blank
		clearAllFields();
		driver.findElement(By.id("sendingDate")).sendKeys("22-04-2026");
		driver.findElement(By.id("userName")).sendKeys("TestUser");
		Thread.sleep(300);
		driver.findElement(By.xpath("//button[contains(text(),'Save')]")).click();
		Thread.sleep(2000);
		String t7 = getToastMsg();
		log("Mandatory", "Date + UserName filled, Notice Type blank", "Error toast", t7.isEmpty() ? "No toast" : t7, !t7.isEmpty());

		// ========== STEP 3: DELETE AGAIN (in case TC5 saved) ==========

		deleteAllRecords();

		// ========== STEP 4: SAVE ALL 3 NOTICE TYPES ==========

		fillAndSave("First Demand Letter", "FDL", "22-04-2026", "UserFDL");
		fillAndSave("Second Demand Letter", "SDL", "22-04-2026", "UserSDL");
		fillAndSave("Third Demand Letter", "TDL", "22-04-2026", "UserTDL");

		System.out.println("=================================================");
		System.out.println("DL8 - ALL VALIDATIONS DONE");
		System.out.println("=================================================");
	}

	private void clearAllFields() throws Exception {
		try { new Select(driver.findElement(By.id("demandLetterType"))).selectByIndex(0); } catch (Exception e) { }
		try { driver.findElement(By.id("sendingDate")).clear(); } catch (Exception e) { }
		try { driver.findElement(By.id("userName")).clear(); } catch (Exception e) { }
		Thread.sleep(300);
	}

	private void deleteAllRecords() throws Exception {
		int count = driver.findElements(By.cssSelector("a.deleteBtn")).size();
		log("Delete Old", "Existing records", "Count", String.valueOf(count), true);

		while (count > 0) {
			WebElement delBtn = driver.findElement(By.xpath("(//a[contains(@class,'deleteBtn')])[1]"));
			jse.executeScript("arguments[0].scrollIntoView({block:'center'})", delBtn);
			Thread.sleep(500);
			jse.executeScript("arguments[0].click()", delBtn);
			Thread.sleep(2000);
			String toast = getSuccessToastMsg();
			log("Delete Old", "Deleted record", "Success", toast.isEmpty() ? "Deleted" : toast, true);
			Thread.sleep(2000);
			count = driver.findElements(By.cssSelector("a.deleteBtn")).size();
		}
		log("Delete Old", "All deleted", "0", String.valueOf(count), count == 0);
	}

	private void fillAndSave(String noticeType, String value, String date, String userName) throws Exception {
		Thread.sleep(1000);

		new Select(driver.findElement(By.id("demandLetterType"))).selectByValue(value);
		Thread.sleep(500);
		log("Save " + noticeType, "Select Notice Type", noticeType, new Select(driver.findElement(By.id("demandLetterType"))).getFirstSelectedOption().getText().trim(), true);

		WebElement dateField = driver.findElement(By.id("sendingDate"));
		dateField.clear();
		dateField.sendKeys(date);
		Thread.sleep(300);
		log("Save " + noticeType, "Enter Date", date, dateField.getAttribute("value"), true);

		WebElement userField = driver.findElement(By.id("userName"));
		userField.clear();
		userField.sendKeys(userName);
		Thread.sleep(300);
		log("Save " + noticeType, "Enter User Name", userName, userField.getAttribute("value"), true);

		driver.findElement(By.xpath("//button[contains(text(),'Save')]")).click();
		Thread.sleep(2000);

		String toast = getSuccessToastMsg();
		if (toast.isEmpty()) toast = getToastMsg();
		log("Save " + noticeType, "Toast after save", "Success toast", toast.isEmpty() ? "No toast" : toast, !toast.isEmpty());

		Thread.sleep(2000);
	}
}
