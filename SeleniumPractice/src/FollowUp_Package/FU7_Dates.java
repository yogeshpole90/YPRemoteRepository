package FollowUp_Package;

import org.openqa.selenium.*;
import org.testng.annotations.Test;

public class FU7_Dates extends FU2_Login {

	@Test
	public void validateDates() throws Exception
	{
		System.out.println("========== RESULT DATE & ACTION DATE ==========");
		int caseNo = 1;

		// ===== RESULT DATE (resolve) =====
		WebElement rd = driver.findElement(By.id("resolve"));

		// Case 1: Displayed
		boolean rdDisp = rd.isDisplayed();
		System.out.println("Case " + caseNo + " | resolve displayed | Expected: true | Actual: " + rdDisp);
		sa.assertTrue(rdDisp, "BUG: resolve not displayed");
		caseNo++;

		// Case 2: Enabled
		boolean rdEn = rd.isEnabled();
		System.out.println("Case " + caseNo + " | resolve enabled | Expected: true | Actual: " + rdEn);
		sa.assertTrue(rdEn, "BUG: resolve not enabled");
		caseNo++;

		// Case 3: Default value
		String rdDefault = rd.getAttribute("value");
		System.out.println("Case " + caseNo + " | resolve default value | Expected: empty or date | Actual: '" + rdDefault + "'");
		caseNo++;

		// Case 4: Readonly check
		String rdReadonly = rd.getAttribute("readonly");
		System.out.println("Case " + caseNo + " | resolve readonly | Expected: null(editable) or true | Actual: " + rdReadonly);
		caseNo++;

		// Case 5: Placeholder
		String rdPlaceholder = rd.getAttribute("placeholder");
		System.out.println("Case " + caseNo + " | resolve placeholder | Actual: " + rdPlaceholder);
		caseNo++;

		// Case 6: Field type
		String rdType = rd.getAttribute("type");
		System.out.println("Case " + caseNo + " | resolve type | Expected: text or date | Actual: " + rdType);
		caseNo++;

		// Case 7: Set empty
		jse.executeScript("arguments[0].value=''", rd);
		Thread.sleep(300);
		String rdEmpty = rd.getAttribute("value");
		System.out.println("Case " + caseNo + " | resolve empty input | Expected: '' | Actual: '" + rdEmpty + "'");
		sa.assertTrue(rdEmpty.isEmpty(), "BUG: resolve not empty after clear");
		caseNo++;

		// Case 8: Invalid date 99-99-9999
		jse.executeScript("arguments[0].value='99-99-9999'", rd);
		Thread.sleep(300);
		String rdInvalid = rd.getAttribute("value");
		System.out.println("Case " + caseNo + " | resolve invalid date | Input: 99-99-9999 | Actual: " + rdInvalid);
		sa.fail("BUG: resolve accepts invalid date 99-99-9999");
		caseNo++;

		// Case 9: Day = 00
		jse.executeScript("arguments[0].value='00-03-2026'", rd);
		Thread.sleep(300);
		String rdDay00 = rd.getAttribute("value");
		System.out.println("Case " + caseNo + " | resolve day=00 | Input: 00-03-2026 | Actual: " + rdDay00);
		sa.fail("BUG: resolve accepts day=00 (00-03-2026)");
		caseNo++;

		// Case 10: Month = 00
		jse.executeScript("arguments[0].value='26-00-2026'", rd);
		Thread.sleep(300);
		String rdMonth00 = rd.getAttribute("value");
		System.out.println("Case " + caseNo + " | resolve month=00 | Input: 26-00-2026 | Actual: " + rdMonth00);
		sa.fail("BUG: resolve accepts month=00 (26-00-2026)");
		caseNo++;

		// Case 11: Year = 0000
		jse.executeScript("arguments[0].value='26-03-0000'", rd);
		Thread.sleep(300);
		String rdYear00 = rd.getAttribute("value");
		System.out.println("Case " + caseNo + " | resolve year=0000 | Input: 26-03-0000 | Actual: " + rdYear00);
		sa.fail("BUG: resolve accepts year=0000 (26-03-0000)");
		caseNo++;

		// Case 12: Day = 32
		jse.executeScript("arguments[0].value='32-03-2026'", rd);
		Thread.sleep(300);
		String rdDay32 = rd.getAttribute("value");
		System.out.println("Case " + caseNo + " | resolve day=32 | Input: 32-03-2026 | Actual: " + rdDay32);
		sa.fail("BUG: resolve accepts day=32 (32-03-2026)");
		caseNo++;

		// Case 13: Month = 13
		jse.executeScript("arguments[0].value='26-13-2026'", rd);
		Thread.sleep(300);
		String rdMonth13 = rd.getAttribute("value");
		System.out.println("Case " + caseNo + " | resolve month=13 | Input: 26-13-2026 | Actual: " + rdMonth13);
		sa.fail("BUG: resolve accepts month=13 (26-13-2026)");
		caseNo++;

		// Case 14: Text instead of date
		jse.executeScript("arguments[0].value='abcdef'", rd);
		Thread.sleep(300);
		String rdText = rd.getAttribute("value");
		System.out.println("Case " + caseNo + " | resolve text input | Input: abcdef | Actual: " + rdText);
		sa.fail("BUG: resolve accepts text 'abcdef' instead of date");
		caseNo++;

		// Case 15: Special chars
		jse.executeScript("arguments[0].value='@#$%&'", rd);
		Thread.sleep(300);
		String rdSpecial = rd.getAttribute("value");
		System.out.println("Case " + caseNo + " | resolve special chars | Input: @#$%& | Actual: " + rdSpecial);
		sa.fail("BUG: resolve accepts special chars '@#$%&'");
		caseNo++;

		// Case 16: Past date
		jse.executeScript("arguments[0].value='01-01-2020'", rd);
		Thread.sleep(300);
		String rdPast = rd.getAttribute("value");
		System.out.println("Case " + caseNo + " | resolve past date | Input: 01-01-2020 | Actual: " + rdPast);
		caseNo++;

		// Case 17: Future date
		jse.executeScript("arguments[0].value='31-12-2030'", rd);
		Thread.sleep(300);
		String rdFuture = rd.getAttribute("value");
		System.out.println("Case " + caseNo + " | resolve future date | Input: 31-12-2030 | Actual: " + rdFuture);
		caseNo++;

		// Case 18: Valid date
		jse.executeScript("arguments[0].value='26-03-2026'", rd);
		Thread.sleep(300);
		String rdValid = rd.getAttribute("value");
		System.out.println("Case " + caseNo + " | resolve valid date | Input: 26-03-2026 | Expected: 26-03-2026 | Actual: " + rdValid);
		sa.assertEquals(rdValid, "26-03-2026", "BUG: resolve valid date mismatch");
		caseNo++;

		// Case 19: maxlength
		String rdMax = rd.getAttribute("maxlength");
		System.out.println("Case " + caseNo + " | resolve maxlength | Actual: " + rdMax);
		caseNo++;

		// ===== ACTION DATE (followUpDate) =====
		WebElement ad = driver.findElement(By.id("followUpDate"));

		// Case 20: Displayed
		boolean adDisp = ad.isDisplayed();
		System.out.println("Case " + caseNo + " | followUpDate displayed | Expected: true | Actual: " + adDisp);
		sa.assertTrue(adDisp, "BUG: followUpDate not displayed");
		caseNo++;

		// Case 21: Enabled
		boolean adEn = ad.isEnabled();
		System.out.println("Case " + caseNo + " | followUpDate enabled | Expected: true | Actual: " + adEn);
		sa.assertTrue(adEn, "BUG: followUpDate not enabled");
		caseNo++;

		// Case 22: Default value
		String adDefault = ad.getAttribute("value");
		System.out.println("Case " + caseNo + " | followUpDate default value | Expected: empty or date | Actual: '" + adDefault + "'");
		caseNo++;

		// Case 23: Readonly check
		String adReadonly = ad.getAttribute("readonly");
		System.out.println("Case " + caseNo + " | followUpDate readonly | Expected: null(editable) or true | Actual: " + adReadonly);
		caseNo++;

		// Case 24: Placeholder
		String adPlaceholder = ad.getAttribute("placeholder");
		System.out.println("Case " + caseNo + " | followUpDate placeholder | Actual: " + adPlaceholder);
		caseNo++;

		// Case 25: Field type
		String adType = ad.getAttribute("type");
		System.out.println("Case " + caseNo + " | followUpDate type | Expected: text or date | Actual: " + adType);
		caseNo++;

		// Case 26: Set empty
		jse.executeScript("arguments[0].value=''", ad);
		Thread.sleep(300);
		String adEmpty = ad.getAttribute("value");
		System.out.println("Case " + caseNo + " | followUpDate empty input | Expected: '' | Actual: '" + adEmpty + "'");
		sa.assertTrue(adEmpty.isEmpty(), "BUG: followUpDate not empty after clear");
		caseNo++;

		// Case 27: Invalid date 99-99-9999
		jse.executeScript("arguments[0].value='99-99-9999'", ad);
		Thread.sleep(300);
		String adInvalid = ad.getAttribute("value");
		System.out.println("Case " + caseNo + " | followUpDate invalid date | Input: 99-99-9999 | Actual: " + adInvalid);
		sa.fail("BUG: followUpDate accepts invalid date 99-99-9999");
		caseNo++;

		// Case 28: Day = 00
		jse.executeScript("arguments[0].value='00-03-2026'", ad);
		Thread.sleep(300);
		String adDay00 = ad.getAttribute("value");
		System.out.println("Case " + caseNo + " | followUpDate day=00 | Input: 00-03-2026 | Actual: " + adDay00);
		sa.fail("BUG: followUpDate accepts day=00 (00-03-2026)");
		caseNo++;

		// Case 29: Month = 00
		jse.executeScript("arguments[0].value='26-00-2026'", ad);
		Thread.sleep(300);
		String adMonth00 = ad.getAttribute("value");
		System.out.println("Case " + caseNo + " | followUpDate month=00 | Input: 26-00-2026 | Actual: " + adMonth00);
		sa.fail("BUG: followUpDate accepts month=00 (26-00-2026)");
		caseNo++;

		// Case 30: Year = 0000
		jse.executeScript("arguments[0].value='26-03-0000'", ad);
		Thread.sleep(300);
		String adYear00 = ad.getAttribute("value");
		System.out.println("Case " + caseNo + " | followUpDate year=0000 | Input: 26-03-0000 | Actual: " + adYear00);
		sa.fail("BUG: followUpDate accepts year=0000 (26-03-0000)");
		caseNo++;

		// Case 31: Day = 32
		jse.executeScript("arguments[0].value='32-03-2026'", ad);
		Thread.sleep(300);
		String adDay32 = ad.getAttribute("value");
		System.out.println("Case " + caseNo + " | followUpDate day=32 | Input: 32-03-2026 | Actual: " + adDay32);
		sa.fail("BUG: followUpDate accepts day=32 (32-03-2026)");
		caseNo++;

		// Case 32: Month = 13
		jse.executeScript("arguments[0].value='26-13-2026'", ad);
		Thread.sleep(300);
		String adMonth13 = ad.getAttribute("value");
		System.out.println("Case " + caseNo + " | followUpDate month=13 | Input: 26-13-2026 | Actual: " + adMonth13);
		sa.fail("BUG: followUpDate accepts month=13 (26-13-2026)");
		caseNo++;

		// Case 33: Text instead of date
		jse.executeScript("arguments[0].value='abcdef'", ad);
		Thread.sleep(300);
		String adText = ad.getAttribute("value");
		System.out.println("Case " + caseNo + " | followUpDate text input | Input: abcdef | Actual: " + adText);
		sa.fail("BUG: followUpDate accepts text 'abcdef'");
		caseNo++;

		// Case 34: Special chars
		jse.executeScript("arguments[0].value='@#$%&'", ad);
		Thread.sleep(300);
		String adSpecial = ad.getAttribute("value");
		System.out.println("Case " + caseNo + " | followUpDate special chars | Input: @#$%& | Actual: " + adSpecial);
		sa.fail("BUG: followUpDate accepts special chars '@#$%&'");
		caseNo++;

		// Case 35: Past date
		jse.executeScript("arguments[0].value='01-01-2020'", ad);
		Thread.sleep(300);
		String adPast = ad.getAttribute("value");
		System.out.println("Case " + caseNo + " | followUpDate past date | Input: 01-01-2020 | Actual: " + adPast);
		caseNo++;

		// Case 36: Future date
		jse.executeScript("arguments[0].value='31-12-2030'", ad);
		Thread.sleep(300);
		String adFuture = ad.getAttribute("value");
		System.out.println("Case " + caseNo + " | followUpDate future date | Input: 31-12-2030 | Actual: " + adFuture);
		caseNo++;

		// Case 37: Valid date
		jse.executeScript("arguments[0].value='26-03-2026'", ad);
		Thread.sleep(300);
		String adValid = ad.getAttribute("value");
		System.out.println("Case " + caseNo + " | followUpDate valid date | Input: 26-03-2026 | Expected: 26-03-2026 | Actual: " + adValid);
		sa.assertEquals(adValid, "26-03-2026", "BUG: followUpDate valid date mismatch");
		caseNo++;

		// Case 38: maxlength
		String adMax = ad.getAttribute("maxlength");
		System.out.println("Case " + caseNo + " | followUpDate maxlength | Actual: " + adMax);
		caseNo++;

		System.out.println("========== RESULT DATE & ACTION DATE Complete ==========\n");
	}
}
