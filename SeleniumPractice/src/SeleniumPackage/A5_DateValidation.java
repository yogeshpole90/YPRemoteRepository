package SeleniumPackage;

import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.testng.annotations.Test;

public class A5_DateValidation extends A1_LoginSetup {

	@Test
	public void date1()
	{
		WebElement ActDate = driver.findElement(By.id("actionDate"));

		//CaseNo 1 : Date field is Displayed
		System.out.println("============================================");
		sa.assertTrue(ActDate.isDisplayed(),"Not Displaying.");
		System.out.println("CaseNo1 : Action date is displaying");

		//CaseNo 2 : Date field is Enabled
		System.out.println("============================================");
		sa.assertTrue(ActDate.isEnabled(),"Is Disabled.");
		System.out.println("CaseNo2 : Action date is Enabled.");

		//CaseNo 3 : Date field is Clickable
		System.out.println("============================================");
		ActDate.click();
		System.out.println("CaseNo3 : Action Date is Clickable.");

		//CaseNo 4 : Default Date is Empty
		System.out.println("============================================");
		String defaultdate = ActDate.getAttribute("value");
		sa.assertTrue(defaultdate.isEmpty(),"Default Date is Not Empty.");
		System.out.println("defaultdate :- " + defaultdate);
		System.out.println("CaseNo4 : Default Date is Empty.");

		//CaseNo 5 : Valid Date Accepted
		System.out.println("============================================");
		ActDate.clear();
		ActDate.sendKeys("13-12-2021");
		String EnteredDate = ActDate.getAttribute("value");
		sa.assertEquals(EnteredDate, "13-12-2021", "Not Accepting Valid Date.");
		System.out.println("CaseNo5 : Accepting Entered Valid Date.");

		//CaseNo 6 : Invalid Date Format (MM-DD-YYYY instead of DD-MM-YYYY)
		System.out.println("============================================");
		ActDate.clear();
		ActDate.sendKeys("2021/12/31");
		String invalidFormat = ActDate.getAttribute("value");
		sa.assertNotEquals(invalidFormat, "2021/12/31", "Accepting Invalid Date Format YYYY/MM/DD.");
		System.out.println("CaseNo6 : Invalid Date Format not accepted.");

		//CaseNo 7 : Alphabets Not Allowed
		System.out.println("============================================");
		ActDate.clear();
		ActDate.sendKeys("abcdef");
		String alpha = ActDate.getAttribute("value");
		sa.assertTrue(alpha.isEmpty(), "Accepting Alphabets in Date field.");
		System.out.println("CaseNo7 : Not Accepting Alphabets.");

		//CaseNo 8 : Special Characters Not Allowed (except separator)
		System.out.println("============================================");
		ActDate.clear();
		ActDate.sendKeys("@#$%^&*");
		String splChar = ActDate.getAttribute("value");
		sa.assertTrue(splChar.isEmpty(), "Accepting Special Characters.");
		System.out.println("CaseNo8 : Not Accepting Special Characters.");

		//CaseNo 9 : Only Numeric Allowed (digits + separator)
		System.out.println("============================================");
		ActDate.clear();
		ActDate.sendKeys("13-12-2021");
		String numericDate = ActDate.getAttribute("value");
		sa.assertTrue(numericDate.matches("[0-9\\-/]+"), "Non-numeric characters present.");
		System.out.println("CaseNo9 : Only Numeric with separator allowed.");

		//CaseNo 10 : Invalid Day (32)
		System.out.println("============================================");
		ActDate.clear();
		ActDate.sendKeys("32-12-2021");
		String invalidDay = ActDate.getAttribute("value");
		sa.assertNotEquals(invalidDay, "32-12-2021", "Accepting Invalid Day 32.");
		System.out.println("CaseNo10 : Invalid Day 32 not accepted.");

		//CaseNo 11 : Invalid Month (13)
		System.out.println("============================================");
		ActDate.clear();
		ActDate.sendKeys("15-13-2021");
		String invalidMonth = ActDate.getAttribute("value");
		sa.assertNotEquals(invalidMonth, "15-13-2021", "Accepting Invalid Month 13.");
		System.out.println("CaseNo11 : Invalid Month 13 not accepted.");

		//CaseNo 12 : Feb 29 on Non-Leap Year
		System.out.println("============================================");
		ActDate.clear();
		ActDate.sendKeys("29-02-2023");
		String nonLeap = ActDate.getAttribute("value");
		sa.assertNotEquals(nonLeap, "29-02-2023", "Accepting Feb 29 on Non-Leap Year.");
		System.out.println("CaseNo12 : Feb 29 on Non-Leap Year not accepted.");

		//CaseNo 13 : Feb 29 on Leap Year (Valid)
		System.out.println("============================================");
		ActDate.clear();
		ActDate.sendKeys("29-02-2024");
		String leapDate = ActDate.getAttribute("value");
		sa.assertEquals(leapDate, "29-02-2024", "Not Accepting Feb 29 on Leap Year.");
		System.out.println("CaseNo13 : Feb 29 on Leap Year accepted.");

		//CaseNo 14 : Future Date Validation
		System.out.println("============================================");
		ActDate.clear();
		ActDate.sendKeys("01-01-2099");
		String futureDate = ActDate.getAttribute("value");
		sa.assertNotEquals(futureDate, "01-01-2099", "Accepting Far Future Date.");
		System.out.println("CaseNo14 : Future Date validation.");

		//CaseNo 15 : Past Date (very old)
		System.out.println("============================================");
		ActDate.clear();
		ActDate.sendKeys("01-01-1900");
		String pastDate = ActDate.getAttribute("value");
		sa.assertNotEquals(pastDate, "01-01-1900", "Accepting Very Old Past Date.");
		System.out.println("CaseNo15 : Very Old Past Date validation.");

		//CaseNo 16 : Blank/Empty Submission
		System.out.println("============================================");
		ActDate.clear();
		String emptyVal = ActDate.getAttribute("value");
		sa.assertTrue(emptyVal.isEmpty(), "Field not cleared properly.");
		System.out.println("CaseNo16 : Blank field validation.");

		//CaseNo 17 : Spaces Only
		System.out.println("============================================");
		ActDate.clear();
		ActDate.sendKeys("   ");
		String spaces = ActDate.getAttribute("value");
		sa.assertTrue(spaces.trim().isEmpty(), "Accepting Only Spaces.");
		System.out.println("CaseNo17 : Spaces only not accepted.");

		//CaseNo 18 : Day 00 Invalid
		System.out.println("============================================");
		ActDate.clear();
		ActDate.sendKeys("00-12-2021");
		String zeroDay = ActDate.getAttribute("value");
		sa.assertNotEquals(zeroDay, "00-12-2021", "Accepting Day as 00.");
		System.out.println("CaseNo18 : Day 00 not accepted.");

		//CaseNo 19 : Month 00 Invalid
		System.out.println("============================================");
		ActDate.clear();
		ActDate.sendKeys("15-00-2021");
		String zeroMonth = ActDate.getAttribute("value");
		sa.assertNotEquals(zeroMonth, "15-00-2021", "Accepting Month as 00.");
		System.out.println("CaseNo19 : Month 00 not accepted.");

		//CaseNo 20 : Alphanumeric Mix
		System.out.println("============================================");
		ActDate.clear();
		ActDate.sendKeys("1a-1b-20cd");
		String alphaNum = ActDate.getAttribute("value");
		sa.assertNotEquals(alphaNum, "1a-1b-20cd", "Accepting Alphanumeric Mix.");
		System.out.println("CaseNo20 : Alphanumeric mix not accepted.");

		//CaseNo 21 : Max Length Check (more than 10 chars for DD-MM-YYYY)
		System.out.println("============================================");
		ActDate.clear();
		ActDate.sendKeys("13-12-20211234");
		String maxLen = ActDate.getAttribute("value");
		sa.assertTrue(maxLen.length() <= 10, "Exceeding Max Length for Date.");
		System.out.println("CaseNo21 : Max length check passed. Length: " + maxLen.length());

		//CaseNo 22 : Placeholder/Format Hint Check
		System.out.println("============================================");
		ActDate.clear();
		String placeholder = ActDate.getAttribute("placeholder");
		if (placeholder != null && !placeholder.isEmpty()) {
			System.out.println("CaseNo22 : Placeholder found: " + placeholder);
		} else {
			System.out.println("CaseNo22 : No placeholder/format hint present.");
		}

		//CaseNo 23 : Read-Only Check (should NOT be readonly)
		System.out.println("============================================");
		String readOnly = ActDate.getAttribute("readonly");
		sa.assertNull(readOnly, "Date field is Read-Only.");
		System.out.println("CaseNo23 : Date field is not Read-Only.");

		System.out.println("============================================");
		System.out.println("A5_DateValidation - All cases executed.");
	}
}
