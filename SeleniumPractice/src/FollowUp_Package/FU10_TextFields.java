package FollowUp_Package;

import org.openqa.selenium.*;
import org.testng.annotations.Test;

public class FU10_TextFields extends FU2_Login {

	@Test
	public void validateTextFields() throws Exception
	{
		System.out.println("========== PARTY CONTACT / STATUS / REMARK ==========");
		int caseNo = 1;

		// ===== PARTY CONTACT NAME =====
		WebElement party = driver.findElement(By.id("partyContactName"));

		boolean pDisp = party.isDisplayed();
		System.out.println("Case " + caseNo + " | partyContactName displayed | Expected: true | Actual: " + pDisp);
		sa.assertTrue(pDisp, "BUG: partyContactName not displayed | Expected: true | Actual: " + pDisp);
		caseNo++;

		boolean pEn = party.isEnabled();
		System.out.println("Case " + caseNo + " | partyContactName enabled | Expected: true | Actual: " + pEn);
		sa.assertTrue(pEn, "BUG: partyContactName not enabled | Expected: true | Actual: " + pEn);
		caseNo++;

		party.clear();
		String pEmpty = party.getAttribute("value");
		System.out.println("Case " + caseNo + " | partyContactName empty | Expected: '' | Actual: '" + pEmpty + "'");
		sa.assertTrue(pEmpty.isEmpty(), "BUG: partyContactName not empty after clear | Expected: '' | Actual: '" + pEmpty + "'");
		caseNo++;

		party.sendKeys("Test Contact");
		String pText = party.getAttribute("value");
		System.out.println("Case " + caseNo + " | partyContactName text | Expected: Test Contact | Actual: " + pText);
		sa.assertEquals(pText, "Test Contact", "BUG: partyContactName mismatch | Expected: Test Contact | Actual: " + pText);
		caseNo++;

		party.clear();
		party.sendKeys("@#$%&*!");
		String pSpecial = party.getAttribute("value");
		System.out.println("Case " + caseNo + " | partyContactName special chars | Input: @#$%&*! | Actual: " + pSpecial);
		sa.assertFalse(pSpecial.equals("@#$%&*!"), "BUG: partyContactName accepts special chars | Input: @#$%&*! | Actual: " + pSpecial + " | Expected: should reject");
		caseNo++;

		party.clear();
		party.sendKeys("12345");
		String pNum = party.getAttribute("value");
		System.out.println("Case " + caseNo + " | partyContactName numeric | Input: 12345 | Actual: " + pNum);
		caseNo++;

		party.clear();
		party.sendKeys("Contact123");
		String pAlpha = party.getAttribute("value");
		System.out.println("Case " + caseNo + " | partyContactName alphanumeric | Input: Contact123 | Actual: " + pAlpha);
		caseNo++;

		party.clear();
		party.sendKeys("  Test  ");
		String pSpaces = party.getAttribute("value");
		System.out.println("Case " + caseNo + " | partyContactName leading/trailing spaces | Input: '  Test  ' | Actual: '" + pSpaces + "'");
		caseNo++;

		party.clear();
		party.sendKeys("This is a very long party contact name to check the maximum character limit of this field testing");
		String pLong = party.getAttribute("value");
		System.out.println("Case " + caseNo + " | partyContactName long text | Input length: 97 | Actual length: " + pLong.length());
		caseNo++;

		party.clear();
		party.sendKeys("' OR 1=1 --");
		String pSql = party.getAttribute("value");
		System.out.println("Case " + caseNo + " | partyContactName SQL injection | Input: ' OR 1=1 -- | Actual: " + pSql);
		sa.assertFalse(pSql.equals("' OR 1=1 --"), "BUG: partyContactName accepts SQL injection | Input: ' OR 1=1 -- | Actual: " + pSql + " | Expected: should reject");
		caseNo++;

		party.clear();
		party.sendKeys("<script>alert('x')</script>");
		String pHtml = party.getAttribute("value");
		System.out.println("Case " + caseNo + " | partyContactName HTML injection | Input: <script> | Actual: " + pHtml);
		sa.assertFalse(pHtml.contains("<script>"), "BUG: partyContactName accepts HTML injection | Input: <script> | Actual: " + pHtml + " | Expected: should reject");
		caseNo++;

		String pMax = party.getAttribute("maxlength");
		System.out.println("Case " + caseNo + " | partyContactName maxlength | Actual: " + pMax);
		caseNo++;

		String pPlace = party.getAttribute("placeholder");
		System.out.println("Case " + caseNo + " | partyContactName placeholder | Actual: " + pPlace);
		caseNo++;

		String pType = party.getAttribute("type");
		System.out.println("Case " + caseNo + " | partyContactName type | Expected: text | Actual: " + pType);
		sa.assertEquals(pType, "text", "BUG: partyContactName type | Expected: text | Actual: " + pType);
		caseNo++;

		party.clear();
		party.sendKeys("Test Contact");

		// ===== STATUS =====
		WebElement status = driver.findElement(By.id("status"));

		boolean sDisp = status.isDisplayed();
		System.out.println("Case " + caseNo + " | status displayed | Expected: true | Actual: " + sDisp);
		sa.assertTrue(sDisp, "BUG: status not displayed | Expected: true | Actual: " + sDisp);
		caseNo++;

		boolean sEn = status.isEnabled();
		System.out.println("Case " + caseNo + " | status enabled | Expected: true | Actual: " + sEn);
		sa.assertTrue(sEn, "BUG: status not enabled | Expected: true | Actual: " + sEn);
		caseNo++;

		status.clear();
		String sEmpty = status.getAttribute("value");
		System.out.println("Case " + caseNo + " | status empty | Expected: '' | Actual: '" + sEmpty + "'");
		sa.assertTrue(sEmpty.isEmpty(), "BUG: status not empty | Expected: '' | Actual: '" + sEmpty + "'");
		caseNo++;

		status.sendKeys("Active");
		String sText = status.getAttribute("value");
		System.out.println("Case " + caseNo + " | status text | Expected: Active | Actual: " + sText);
		sa.assertEquals(sText, "Active", "BUG: status mismatch | Expected: Active | Actual: " + sText);
		caseNo++;

		status.clear();
		status.sendKeys("@#$%");
		String sSpecial = status.getAttribute("value");
		System.out.println("Case " + caseNo + " | status special chars | Input: @#$% | Actual: " + sSpecial);
		sa.assertFalse(sSpecial.equals("@#$%"), "BUG: status accepts special chars | Input: @#$% | Actual: " + sSpecial + " | Expected: should reject");
		caseNo++;

		status.clear();
		status.sendKeys("12345");
		String sNum = status.getAttribute("value");
		System.out.println("Case " + caseNo + " | status numeric | Input: 12345 | Actual: " + sNum);
		caseNo++;

		status.clear();
		status.sendKeys("This is a very long status text to check max character limit of field");
		String sLong = status.getAttribute("value");
		System.out.println("Case " + caseNo + " | status long text | Input length: 69 | Actual length: " + sLong.length());
		caseNo++;

		status.clear();
		status.sendKeys("' OR 1=1 --");
		String sSql = status.getAttribute("value");
		System.out.println("Case " + caseNo + " | status SQL injection | Input: ' OR 1=1 -- | Actual: " + sSql);
		sa.assertFalse(sSql.equals("' OR 1=1 --"), "BUG: status accepts SQL injection | Input: ' OR 1=1 -- | Actual: " + sSql + " | Expected: should reject");
		caseNo++;

		String sMax = status.getAttribute("maxlength");
		System.out.println("Case " + caseNo + " | status maxlength | Actual: " + sMax);
		caseNo++;

		String sPlace = status.getAttribute("placeholder");
		System.out.println("Case " + caseNo + " | status placeholder | Actual: " + sPlace);
		caseNo++;

		String sType = status.getAttribute("type");
		System.out.println("Case " + caseNo + " | status type | Expected: text | Actual: " + sType);
		sa.assertEquals(sType, "text", "BUG: status type | Expected: text | Actual: " + sType);
		caseNo++;

		status.clear();
		status.sendKeys("Active");

		// ===== REMARK =====
		WebElement remark = driver.findElement(By.id("remark"));

		boolean rDisp = remark.isDisplayed();
		System.out.println("Case " + caseNo + " | remark displayed | Expected: true | Actual: " + rDisp);
		sa.assertTrue(rDisp, "BUG: remark not displayed | Expected: true | Actual: " + rDisp);
		caseNo++;

		boolean rEn = remark.isEnabled();
		System.out.println("Case " + caseNo + " | remark enabled | Expected: true | Actual: " + rEn);
		sa.assertTrue(rEn, "BUG: remark not enabled | Expected: true | Actual: " + rEn);
		caseNo++;

		remark.clear();
		String rEmpty = remark.getAttribute("value");
		System.out.println("Case " + caseNo + " | remark empty | Expected: '' | Actual: '" + rEmpty + "'");
		sa.assertTrue(rEmpty.isEmpty(), "BUG: remark not empty | Expected: '' | Actual: '" + rEmpty + "'");
		caseNo++;

		remark.sendKeys("Test Remark Selenium");
		String rText = remark.getAttribute("value");
		System.out.println("Case " + caseNo + " | remark text | Expected: Test Remark Selenium | Actual: " + rText);
		sa.assertEquals(rText, "Test Remark Selenium", "BUG: remark mismatch | Expected: Test Remark Selenium | Actual: " + rText);
		caseNo++;

		remark.clear();
		remark.sendKeys("@#$%&*!(){}[]");
		String rSpecial = remark.getAttribute("value");
		System.out.println("Case " + caseNo + " | remark special chars | Input: @#$%&*!(){}[] | Actual: " + rSpecial);
		sa.assertFalse(rSpecial.equals("@#$%&*!(){}[]"), "BUG: remark accepts special chars | Input: @#$%&*!(){}[] | Actual: " + rSpecial + " | Expected: should reject");
		caseNo++;

		remark.clear();
		remark.sendKeys("9876543210");
		String rNum = remark.getAttribute("value");
		System.out.println("Case " + caseNo + " | remark numeric | Input: 9876543210 | Actual: " + rNum);
		caseNo++;

		remark.clear();
		remark.sendKeys("Remark123Test");
		String rAlpha = remark.getAttribute("value");
		System.out.println("Case " + caseNo + " | remark alphanumeric | Input: Remark123Test | Actual: " + rAlpha);
		sa.assertEquals(rAlpha, "Remark123Test", "BUG: remark alphanumeric mismatch | Expected: Remark123Test | Actual: " + rAlpha);
		caseNo++;

		remark.clear();
		remark.sendKeys("  Test Remark  ");
		String rSpaces = remark.getAttribute("value");
		System.out.println("Case " + caseNo + " | remark leading/trailing spaces | Input: '  Test Remark  ' | Actual: '" + rSpaces + "'");
		caseNo++;

		remark.clear();
		remark.sendKeys("This is a very long remark to check the maximum character limit of the remark field in follow up communication history form testing");
		String rLong = remark.getAttribute("value");
		System.out.println("Case " + caseNo + " | remark long text | Input length: 128 | Actual length: " + rLong.length());
		caseNo++;

		remark.clear();
		remark.sendKeys("' OR 1=1 --; DROP TABLE users;");
		String rSql = remark.getAttribute("value");
		System.out.println("Case " + caseNo + " | remark SQL injection | Input: ' OR 1=1 --; DROP TABLE | Actual: " + rSql);
		sa.assertFalse(rSql.contains("DROP TABLE"), "BUG: remark accepts SQL injection | Input: DROP TABLE | Actual: " + rSql + " | Expected: should reject");
		caseNo++;

		remark.clear();
		remark.sendKeys("<script>alert('xss')</script>");
		String rHtml = remark.getAttribute("value");
		System.out.println("Case " + caseNo + " | remark HTML injection | Input: <script> | Actual: " + rHtml);
		sa.assertFalse(rHtml.contains("<script>"), "BUG: remark accepts HTML injection | Input: <script> | Actual: " + rHtml + " | Expected: should reject");
		caseNo++;

		remark.clear();
		remark.sendKeys("     ");
		String rOnlySpaces = remark.getAttribute("value");
		System.out.println("Case " + caseNo + " | remark only spaces | Input: '     ' | Actual: '" + rOnlySpaces + "'");
		sa.assertTrue(rOnlySpaces.trim().isEmpty(), "BUG: remark accepts only spaces as valid input | Input: '     ' | Actual: '" + rOnlySpaces + "' | Expected: should reject or trim");
		caseNo++;

		String rMax = remark.getAttribute("maxlength");
		System.out.println("Case " + caseNo + " | remark maxlength | Actual: " + rMax);
		caseNo++;

		String rPlace = remark.getAttribute("placeholder");
		System.out.println("Case " + caseNo + " | remark placeholder | Actual: " + rPlace);
		caseNo++;

		String rTag = remark.getTagName();
		System.out.println("Case " + caseNo + " | remark tagName | Expected: input or textarea | Actual: " + rTag);
		caseNo++;

		remark.clear();
		remark.sendKeys("Test Remark Selenium");

		System.out.println("========== PARTY CONTACT / STATUS / REMARK Complete ==========\n");
	}
}
