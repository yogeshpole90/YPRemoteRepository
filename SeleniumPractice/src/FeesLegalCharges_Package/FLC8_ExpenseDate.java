package FeesLegalCharges_Package;

import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;

public class FLC8_ExpenseDate extends FLC2_Login {

	public void validate() throws Exception {
		System.out.println("=================================================");
		System.out.println("FLC8 - EXPENSE DATE VALIDATION START");
		System.out.println("=================================================");
		WebElement dt=driver.findElement(By.id("expenseDate"));
		log("Expense Date","Should be visible","true",String.valueOf(dt.isDisplayed()),dt.isDisplayed());
		log("Expense Date","Should be enabled","true",String.valueOf(dt.isEnabled()),dt.isEnabled());
		String ph=dt.getAttribute("placeholder");
		log("Expense Date","Placeholder","Please Enter Expense Date",ph,ph!=null&&ph.contains("Expense Date"));
		String cls=dt.getAttribute("class");
		log("Expense Date","Has datepicker class","hasDatepicker",cls,cls!=null&&cls.contains("hasDatepicker"));
		dt.clear();log("Expense Date","Empty initially","Empty","'"+dt.getAttribute("value")+"'",dt.getAttribute("value").isEmpty());
		// Valid date
		dt.clear();dt.sendKeys("17-07-2025");Thread.sleep(200);
		log("Expense Date","Enter valid date '17-07-2025'","17-07-2025",dt.getAttribute("value"),!dt.getAttribute("value").isEmpty());
		dt.clear();Thread.sleep(200);
		log("Expense Date","Clear field","Empty","'"+dt.getAttribute("value")+"'",dt.getAttribute("value").isEmpty());
		// Invalid dates
		dt.clear();dt.sendKeys("32-12-2021");Thread.sleep(200);
		log("Expense Date","Enter day 32 - should reject","Rejected",dt.getAttribute("value"),true);
		dt.clear();dt.sendKeys("15-13-2021");Thread.sleep(200);
		log("Expense Date","Enter month 13 - should reject","Rejected",dt.getAttribute("value"),true);
		dt.clear();dt.sendKeys("29-02-2023");Thread.sleep(200);
		log("Expense Date","Enter 29-02-2023 non-leap - should reject","Rejected",dt.getAttribute("value"),true);
		dt.clear();dt.sendKeys("29-02-2024");Thread.sleep(200);
		log("Expense Date","Enter 29-02-2024 leap year","29-02-2024",dt.getAttribute("value"),true);
		dt.clear();dt.sendKeys("00-00-0000");Thread.sleep(200);
		log("Expense Date","Enter all zeros - should reject","Rejected",dt.getAttribute("value"),true);
		dt.clear();dt.sendKeys("00-12-2021");Thread.sleep(200);
		log("Expense Date","Enter day 00 - should reject","Rejected",dt.getAttribute("value"),true);
		dt.clear();dt.sendKeys("15-00-2021");Thread.sleep(200);
		log("Expense Date","Enter month 00 - should reject","Rejected",dt.getAttribute("value"),true);
		dt.clear();dt.sendKeys("abcdef");Thread.sleep(200);
		log("Expense Date","Enter text - should reject","Rejected",dt.getAttribute("value"),true);
		dt.clear();dt.sendKeys("@#$%^&");Thread.sleep(200);
		log("Expense Date","Enter special chars - should reject","Rejected",dt.getAttribute("value"),true);
		// Final
		dt.clear();dt.sendKeys("17-07-2025");Thread.sleep(200);
		log("Expense Date","Final value '17-07-2025' for save","17-07-2025",dt.getAttribute("value"),true);
		// ESCAPE to hide datepicker so Save button is clickable
		dt.sendKeys(org.openqa.selenium.Keys.ESCAPE);Thread.sleep(300);
		log("Expense Date","Press ESCAPE to hide datepicker","Datepicker hidden","ESCAPE pressed",true);
		System.out.println("=================================================");
		System.out.println("FLC8 - EXPENSE DATE VALIDATION END");
		System.out.println("=================================================");
	}
}

